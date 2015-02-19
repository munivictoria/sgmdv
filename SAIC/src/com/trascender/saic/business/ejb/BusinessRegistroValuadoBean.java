
package com.trascender.saic.business.ejb;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ar.trascender.criterio.clases.Criterio;
import ar.trascender.criterio.clases.Funcion;
import ar.trascender.criterio.clases.Orden;
import ar.trascender.criterio.clases.Proyeccion;
import ar.trascender.criterio.clases.Restriccion;
import ar.trascender.criterio.clases.TuplaCase;
import ar.trascender.criterio.enums.TipoSubconsulta;

import com.trascender.catastro.recurso.persistent.Calle;
import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
import com.trascender.framework.recurso.transients.AtributoConsultable.Tipo;
import com.trascender.framework.recurso.transients.Calendario;
import com.trascender.framework.recurso.transients.Grupo;
import com.trascender.framework.recurso.transients.Periodo;
import com.trascender.framework.recurso.transients.Recurso;
import com.trascender.framework.util.Periodicidad;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.habilitaciones.business.interfaces.BusinessObligacionLocal;
import com.trascender.habilitaciones.recurso.persistent.AsocRegAlicuota;
import com.trascender.habilitaciones.recurso.persistent.CuotaLiquidacion;
import com.trascender.habilitaciones.recurso.persistent.DeclaracionJuradaSHPS;
import com.trascender.habilitaciones.recurso.persistent.DocHabilitanteEspecializado;
import com.trascender.habilitaciones.recurso.persistent.LineaDeclaracionJuradaSHPS;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.PeriodoLiquidacion;
import com.trascender.habilitaciones.recurso.persistent.RegAlicuota;
import com.trascender.habilitaciones.recurso.persistent.RegistroValuado;
import com.trascender.habilitaciones.recurso.persistent.Sellado;
import com.trascender.habilitaciones.recurso.persistent.ValorMedidor;
import com.trascender.habilitaciones.recurso.persistent.osp.DocumentoOSP;
import com.trascender.habilitaciones.recurso.persistent.osp.ServicioOSP;
import com.trascender.habilitaciones.recurso.persistent.shps.DocumentoSHPS;
import com.trascender.habilitaciones.recurso.persistent.shps.Rubro;
import com.trascender.habilitaciones.recurso.persistent.tasaMenor.DocumentoTasaMenor;
import com.trascender.habilitaciones.recurso.persistent.tasaMenor.PlantillaDocumentoTasaMenor;
import com.trascender.habilitaciones.recurso.persistent.tasaMenor.RegistroValuadoTasaMenor;
import com.trascender.saic.business.interfaces.BusinessRegistroValuadoLocal;
import com.trascender.saic.exception.SaicException;
import com.trascender.saic.recurso.filtros.FiltroDDJJSHPS;
import com.trascender.saic.recurso.filtros.FiltroValorMedidor;
import com.trascender.saic.recurso.persistent.DocGeneradorDeuda;
import com.trascender.saic.recurso.persistent.PagoSellado;

/**
 * @ejb.bean name="BusinessRegistroValuado" display-name="Name for BusinessRegistroValuado" description="Description for BusinessRegistroValuado"
 *           jndi-name="ejb/BusinessRegistroValuado" type="Stateless" view-type="local"
 */

@Stateless(name = "BusinessRegistroValuadoLocal")
public class BusinessRegistroValuadoBean implements BusinessRegistroValuadoLocal {

	@EJB
	private BusinessObligacionLocal businessObligacion;

	@PersistenceContext(name = "Vipians")
	private EntityManager entityManager;

	private static final long serialVersionUID = -1788289619891872784L;
	static {
		Grupo grupo = new Grupo();
		grupo.setId(serialVersionUID);
		grupo.setNombre("SAI|Adm. de Registros Valuados");

		Recurso regAlicuota = new Recurso();
		regAlicuota.setIdRecurso(RegistroValuado.serialVersionUID);
		regAlicuota.setNombre("Registro Valuado");
		regAlicuota.setClase(RegistroValuado.class);
		grupo.getListaRecursos().add(regAlicuota);

		Recurso declaracionJuradaSHPS = new Recurso();
		declaracionJuradaSHPS.setIdRecurso(DeclaracionJuradaSHPS.serialVersionUID);
		declaracionJuradaSHPS.setNombre("Declaración Jurada SHPS");
		declaracionJuradaSHPS.setAtributosConsultables("Período", "periodoString", "Contribuyente", "docHabilitanteEspecializado.obligacion.persona",
				"Nro Inscripción", "docHabilitanteEspecializado.numeroInscripcion",
				"Monto Imponible","montoImponible", Tipo.MONTO, "Retenciones", "descuentoPorRetenciones");
		declaracionJuradaSHPS.setClase(DeclaracionJuradaSHPS.class);
		grupo.getListaRecursos().add(declaracionJuradaSHPS);

		Recurso valorMedidor = new Recurso();
		valorMedidor.setIdRecurso(ValorMedidor.serialVersionUID);
		valorMedidor.setNombre("Valor de Medidor");
		valorMedidor.setAtributosConsultables("Período", "cuotaLiquidacion.periodo", "Cuota", "cuotaLiquidacion", "Obligación", "docHabilitanteEspecializado.obligacion",
				"Monto Imponible", "montoImponible", Tipo.MONTO, "Estado", "estado");
		valorMedidor.setClase(ValorMedidor.class);
		grupo.getListaRecursos().add(valorMedidor);

		Recurso registroValuadoTasaMenor = new Recurso();
		registroValuadoTasaMenor.setIdRecurso(RegistroValuadoTasaMenor.serialVersionUID);
		registroValuadoTasaMenor.setNombre("Registro Valuado Tasa Menor");
		registroValuadoTasaMenor.setClase(RegistroValuadoTasaMenor.class);
		grupo.getListaRecursos().add(registroValuadoTasaMenor);

		Recurso periodo = new Recurso();
		periodo.setIdRecurso(Periodo.serialVersionUID);
		periodo.setNombre("Período");
		periodo.setClase(Periodo.class);
		grupo.getListaRecursos().add(periodo);

		SecurityMgr.getInstance().addGrupo(grupo);
	}

	public BusinessRegistroValuadoBean() {
	}

	public void ejbActivate() throws EJBException, RemoteException {
	}

	public void ejbPassivate() throws EJBException, RemoteException {
	}

	public void ejbRemove() throws EJBException, RemoteException {
	}

	public void setSessionContext(SessionContext ctx) throws EJBException, RemoteException {
	}

	/**
	 * Default create method
	 * 
	 * @throws CreateException
	 * @ejb.create-method
	 */
	public void ejbCreate() throws CreateException {
	}

	/**
	 * 
	 * @param pPersona
	 * @param pPeriodo
	 * @return
	 * @ejb.interface-method view-type = "local"
	 */
	@Override
	@SuppressWarnings("unchecked")
	public DeclaracionJuradaSHPS getNuevaDDJJSHPS(Persona pPersona, CuotaLiquidacion pCuota, String pNroInscripcion, ArrayList pListaAtributosDinamicos) throws Exception {
		Criterio locCriterio = Criterio
				.getInstance(this.entityManager, DocumentoSHPS.class)
				.add(Restriccion.IGUAL("obligacion.persona", pPersona))
				.add(Restriccion.IGUAL("numeroInscripcion", pNroInscripcion))
				.add(Restriccion.NOT(Restriccion.OR(
						Restriccion.IGUAL("obligacion.estado", Obligacion.Estado.TERMINADO),
						Restriccion.IGUAL("obligacion.estado", Obligacion.Estado.ANULADO))));

		AtributoDinamico.addRestriccionesCriterio(locCriterio, DocumentoSHPS.serialVersionUID, "idDocHabilitanteEspecializado", pListaAtributosDinamicos);

		List listado = locCriterio.list();

		switch(listado.size()) {
			case 0:
				System.out.println("No hay elementos de busqueda");
				return null;
			case 1:
				DocumentoSHPS locDocumento = (DocumentoSHPS) listado.get(0);
				locDocumento.toString();
				locDocumento.getObligacion().toString();
				if(!locDocumento.getRegistroValuado(pCuota).isEmpty()) {
					throw new SaicException(8);
				}
				DeclaracionJuradaSHPS locDeclaracion = new DeclaracionJuradaSHPS();

				locDeclaracion.setDocHabilitanteEspecializado(locDocumento);
				locDeclaracion.setCuotaLiquidacion(pCuota);

				for(RegAlicuota cadaRubro : locDocumento.getListaRegAlicuotas()) {
					cadaRubro.toString();
					
					LineaDeclaracionJuradaSHPS locLineaDeclaracionJuradaSHPS = new LineaDeclaracionJuradaSHPS();
					locLineaDeclaracionJuradaSHPS.setRubro((Rubro) cadaRubro);
					locLineaDeclaracionJuradaSHPS.setDeclaracionJuradaSHPS(locDeclaracion);
					locLineaDeclaracionJuradaSHPS.setImporte(0d);

					locDeclaracion.getListaLineasDDJJSHPS().add(locLineaDeclaracionJuradaSHPS);
				}
				return locDeclaracion;
			default:
				throw new SaicException(7);
		}
	}

	/**
	 * 
	 * @param pPersona
	 * @param pAño
	 * @param pPeriodicidad
	 * @param pNumeroPeriodo
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	@Override
	@SuppressWarnings("unchecked")
	public FiltroDDJJSHPS getListaDDJJSHPS(FiltroDDJJSHPS pFiltro) throws Exception {

		Criterio locCriterio = Criterio.getInstance(this.entityManager, DeclaracionJuradaSHPS.class)

		.add(Restriccion.IGUAL("docHabilitanteEspecializado.numeroInscripcion", pFiltro.getNroInscripcion()))
				.add(Restriccion.IGUAL("cuotaLiquidacion.periodo.calendario.anio", pFiltro.getAnio()))
				.add(Restriccion.IGUAL("cuotaLiquidacion.periodo.calendario", pFiltro.getCalendario())).add(Restriccion.IGUAL("cuotaLiquidacion.periodo", pFiltro.getPeriodo()))
				.add(Restriccion.IGUAL("cuotaLiquidacion", pFiltro.getCuota()));

		if(pFiltro.getPersona() != null) {
			locCriterio.add(Restriccion.IGUAL("docHabilitanteEspecializado.obligacion.persona", pFiltro.getPersona()));
		} else {
			locCriterio.add(Restriccion.EN("docHabilitanteEspecializado.obligacion.persona.idPersona", pFiltro.getListaIdPersonas()));
		}

		pFiltro.procesarYListar(locCriterio);

		for(DeclaracionJuradaSHPS cadaDeclaracion : pFiltro.getListaResultados()) {
			DeclaracionJuradaSHPS locDeclaracionJuradaSHPS = cadaDeclaracion;
			locDeclaracionJuradaSHPS.toString();
			locDeclaracionJuradaSHPS.getCuotaLiquidacion().toString();
			locDeclaracionJuradaSHPS.getDocHabilitanteEspecializado().toString();
			locDeclaracionJuradaSHPS.getDocHabilitanteEspecializado().getObligacion().getPersona().toString();
			for(RegAlicuota cadaRubro : locDeclaracionJuradaSHPS.getDocHabilitanteEspecializado().getListaRegAlicuotas()) {
				cadaRubro.toString();
			}
		}

		return pFiltro;
	}

	@Override
	public DeclaracionJuradaSHPS getDDJJSHPSporId(long pIdDeclaracion) {
		DeclaracionJuradaSHPS locDDJJSHPS = null;

		locDDJJSHPS = (DeclaracionJuradaSHPS) Criterio.getInstance(this.entityManager, DeclaracionJuradaSHPS.class).add(Restriccion.IGUAL("idRegistroValuado", pIdDeclaracion))
				.uniqueResult();

		locDDJJSHPS.getDocHabilitanteEspecializado().getObligacion().getPersona().toString();
		locDDJJSHPS.getListaLineasDDJJSHPS().size();
		for(LineaDeclaracionJuradaSHPS cadaLinea : locDDJJSHPS.getListaLineasDDJJSHPS()) {
			cadaLinea.getRubro().toString();
		}

		return locDDJJSHPS;
	}

	/**
	 * Agrega un registro Valuado
	 * 
	 * @param pRegistroValuado
	 *            registro valuado a agregar
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	@Override
	public void addRegistroValuado(com.trascender.habilitaciones.recurso.persistent.RegistroValuado pRegistroValuado) throws Exception {
		this.entityManager.persist(pRegistroValuado);
		this.entityManager.flush();
	}

	/**
	 * Actualiza los datos de un registro valuado
	 * 
	 * @param pRegistroValuado
	 *            registro valuado a actualizar
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	@Override
	public void updateRegistroValuado(RegistroValuado pRegistroValuado) throws Exception {
		this.entityManager.merge(pRegistroValuado);
	}

	/**
	 * Elimina un registro valuado
	 * 
	 * @param pRegistroValuado
	 *            registro valuado a eliminar
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	@Override
	public void deleteRegistroValuado(com.trascender.habilitaciones.recurso.persistent.RegistroValuado pRegistroValuado) throws Exception {
		// AHORA DEBERÍA PODER ELIMINARSE FÍSICAMENTE EL REGISTRO VALUADO
		// QUE SE ENCUENTRA EN ESTADO CARGADO
		// EN CASO QUE SE ENCUENTRE EN ESTADO LIQUIDADO NO SE ELIMINA MÁS
		pRegistroValuado.setEstado(RegistroValuado.Estado.CARGADO);
		this.updateRegistroValuado(pRegistroValuado);
	}

	/**
	 * Recupera un listado de registros valuados
	 * 
	 * @return Listado de registros valuados
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	@Override
	@SuppressWarnings("unchecked")
	public java.util.List findListaRegistrosValuados() throws Exception {
		Criterio crit = Criterio.getInstance(this.entityManager, RegistroValuado.class);
		// .setFetchJoin("docHabilitanteEspecializado");

		List locLista = crit.list();

		for(Object cadaObject : locLista) {
			RegistroValuado locRegistroValuado = (RegistroValuado) cadaObject;
			locRegistroValuado.toString();
			if(locRegistroValuado.getDocHabilitanteEspecializado() != null) {
				locRegistroValuado.getDocHabilitanteEspecializado().toString();
			}
			if(locRegistroValuado.getRegistroValuadoAnterior() != null) {
				locRegistroValuado.getRegistroValuadoAnterior().toString();
			}
		}
		return crit.list();
	}

	/**
	 * 
	 * @param pListaDDJJSHPS
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	@Override
	@SuppressWarnings("unchecked")
	public void addDDJJSHPS(DeclaracionJuradaSHPS pDDJJSHPS) throws Exception {
		pDDJJSHPS.setEstado(RegistroValuado.Estado.CARGADO);
		pDDJJSHPS.calcularMontoImponible();
		this.entityManager.merge(pDDJJSHPS);
	}

	public DeclaracionJuradaSHPS addDDJJSHPSParaLiquidar(DeclaracionJuradaSHPS pDDJJSHPS) throws Exception {
		pDDJJSHPS.setEstado(RegistroValuado.Estado.CARGADO);
		pDDJJSHPS.calcularMontoImponible();
		pDDJJSHPS = this.entityManager.merge(pDDJJSHPS);

		pDDJJSHPS = this.getDDJJSHPSporId(pDDJJSHPS.getIdRegistroValuado());

		return pDDJJSHPS;
	}

	/**
	 * 
	 * @param pDeclaracionJuradaSHPS
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	@Override
	public void updateDDJJSHPS(DeclaracionJuradaSHPS pDeclaracionJuradaSHPS) throws Exception {
		if(pDeclaracionJuradaSHPS.getEstado().equals(RegistroValuado.Estado.LIQUIDADO)) {
			throw new SaicException(1);
		}
		pDeclaracionJuradaSHPS.calcularMontoImponible();
		this.entityManager.merge(pDeclaracionJuradaSHPS);
	}

	/**
	 * Business method
	 * 
	 * @ejb.interface-method view-type="local"
	 * @param pDeclaracionJuradaSHPS
	 * @throws Exception
	 */
	@Override
	public void deleteDDJJSHPS(DeclaracionJuradaSHPS pDeclaracionJuradaSHPS) throws Exception {
		pDeclaracionJuradaSHPS = this.entityManager.find(DeclaracionJuradaSHPS.class, pDeclaracionJuradaSHPS.getIdRegistroValuado());
		this.entityManager.remove(pDeclaracionJuradaSHPS);
	}

	/**
	 * 
	 * @param pIdRegistroValuado
	 * @return
	 * @throws Exception
	 */
	@Override
	public ValorMedidor getValorMedidorPorId(long pIdRegistroValuado) throws Exception {
		ValorMedidor pValorMedidor = (ValorMedidor) Criterio.getInstance(this.entityManager, ValorMedidor.class).add(Restriccion.IGUAL("idRegistroValuado", pIdRegistroValuado))
				.uniqueResult();

		pValorMedidor.getDocHabilitanteEspecializado().toString();
		pValorMedidor.getDocHabilitanteEspecializado().getObligacion().toString();
		pValorMedidor.getCuotaLiquidacion().toString();
		pValorMedidor.getStringPersona().toString();
		pValorMedidor.getStringDireccion().toString();

		if(!pValorMedidor.getDocHabilitanteEspecializado().getListaRegistrosValuados().isEmpty()) {

			for(RegistroValuado cadaRegistroValuado : pValorMedidor.getDocHabilitanteEspecializado().getListaRegistrosValuados()) {
				RegistroValuado locValorMedidor = cadaRegistroValuado;
				locValorMedidor.toString();
				locValorMedidor.getCuotaLiquidacion().toString();
				locValorMedidor.getStringLecturaAnterior();
			}
			pValorMedidor.getDocHabilitanteEspecializado().getListaRegistrosValuados().toString();
		}
		if(pValorMedidor.getRegistroValuadoAnterior() != null) {
			pValorMedidor.getStringLecturaAnterior().toString();
			if(pValorMedidor.getRegistroValuadoAnterior().getStringLecturaAnterior() != null) {
				pValorMedidor.getRegistroValuadoAnterior().getStringLecturaAnterior().toString();
			}
		}

		return pValorMedidor;
	}

	/**
	 * 
	 * @param pCalle
	 * @param pPeriodo
	 * @param pServicio
	 * @param pCodigoMedidor
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	@Override
	@SuppressWarnings("unchecked")
	public java.util.List getListaNuevasMedidasServiciosOSP(Calle pCalle, CuotaLiquidacion pCuota, ServicioOSP pServicio, String pCodigoMedidor) throws Exception {
		List<ValorMedidor> listaRetorno = new ArrayList<ValorMedidor>();

		if(pCuota == null) {
			throw new SaicException(311);
		}

		Criterio criterioParcela = null;
		if(pCalle != null) {
			criterioParcela = Criterio.getInstance(this.entityManager, Parcela.class)
					.setProyeccion(Proyeccion.PROP("idParcela"))
					.add(Restriccion.IGUAL("domicilioParcelario.relacionCalle.idAbstractCalle", pCalle.getIdCalle()));
		}

		Criterio busquedaDocumentos = Criterio.getInstance(this.entityManager, DocumentoOSP.class)
				.add(Orden.ASC("listaAsocRegAlicuota.codigoMedidor"))
				.add(Restriccion.LIKE("listaAsocRegAlicuota.codigoMedidor", pCodigoMedidor))
				.add(Restriccion.NOT(
						Restriccion.OR(
								Restriccion.IGUAL("obligacion.estado", Obligacion.Estado.TERMINADO), 
								Restriccion.IGUAL("obligacion.estado", Obligacion.Estado.ANULADO))));

		if(criterioParcela != null) {	
			busquedaDocumentos.add(Restriccion.SUBCONSULTA("parcela", TipoSubconsulta.IN, criterioParcela));
		}

		if(pServicio != null && pServicio.getIdTipoAlicuota() != -1) {
			busquedaDocumentos.crearAlias("listaAsocRegAlicuota.registroAlicuota", "locRegistroAlicuota")
				.add(Restriccion.IGUAL("locRegistroAlicuota.idTipoAlicuota", pServicio.getIdTipoAlicuota()));
		} else {
			busquedaDocumentos.crearAlias("listaAsocRegAlicuota.registroAlicuota", "cadaRegAlicuota");
			busquedaDocumentos.add(Restriccion.IGUAL("cadaRegAlicuota.medido", true))
				.add(Restriccion.IGUAL("cadaRegAlicuota.estado", RegAlicuota.Estado.ACTIVO));
		}

			
		busquedaDocumentos.setModoDebug(true);
		List listaDocumentos = busquedaDocumentos.list();
		// HACER: si listadocumento != null lo de abajo sino listaRetorno
		for(Object objDocumento : listaDocumentos) {
			DocumentoOSP locDocumentoOSP = (DocumentoOSP) objDocumento;
			// locDocumentoOSP.getParcela().toString();
			// FIXME Ver esto con fer como manejar la periodicidad ya que el documento tiene muchos registros.
			for(AsocRegAlicuota cadaAsocRegAlicuota : locDocumentoOSP.getListaAsocRegAlicuota()) {
				ServicioOSP locServicioOSP = (ServicioOSP) cadaAsocRegAlicuota.getRegistroAlicuota();
				RegistroValuado locRegistro = locDocumentoOSP.getRegistroValuado(pCuota, cadaAsocRegAlicuota.getRegistroAlicuota());

				if(locServicioOSP.isMedido() && locRegistro == null) {
					ValorMedidor locValor = new ValorMedidor();
					locValor.setDocHabilitanteEspecializado(locDocumentoOSP);
					locValor.setEstado(RegistroValuado.Estado.CARGADO);
					locValor.setFecha(Calendar.getInstance().getTime());
					locValor.setMontoImponible(0d);
					locValor.setCuotaLiquidacion(pCuota);
					locValor.setServicioOSP(locServicioOSP);

					locValor.setLectura(0D);
					if(pCodigoMedidor != null && !pCodigoMedidor.trim().isEmpty()) {
						locValor.setCodigoMedidor(pCodigoMedidor);
					} else {
						locValor.setCodigoMedidor(locDocumentoOSP.getCodigoMedidor());
					}

					// Si posee registro valuado anterior se setea a
					// locValor para obtener el valor la lectura anterior.
					for(RegistroValuado locRegistroValuado : locDocumentoOSP.getListaRegistrosValuados()) {
						if(locRegistroValuado.getCuotaLiquidacion().getPeriodo().getCalendario().equals(pCuota.getPeriodo().getCalendario().getPeriodicidad())
								&& ((pCuota.getNumero() - locRegistroValuado.getCuotaLiquidacion().getNumero()) == 1)) {
							locValor.setRegistroValuadoAnterior(locRegistroValuado);
						}
					}

					locValor.getStringDireccion();
					locValor.getStringPersona();
					listaRetorno.add(locValor);
				}
			}
		}
		return listaRetorno;
	}

	/**
	 * 
	 * @param pValoresMedidores
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	@Override
	@SuppressWarnings("unchecked")
	public void addListaValorMedidor(java.util.List pValoresMedidores) throws Exception {
		for(Object o : pValoresMedidores) {
			ValorMedidor locValorMedidor = (ValorMedidor) o;
			locValorMedidor.getMontoImponible();
			this.entityManager.merge(locValorMedidor);
		}
	}

	/**
	 * 
	 * @param pValorMedidor
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	@Override
	public void updateValorMedidor(ValorMedidor pValorMedidor) throws Exception {
		this.entityManager.merge(pValorMedidor);
	}

	/**
	 * 
	 * @param pCalle
	 * @param pAño
	 * @param pPeriodicidad
	 * @param pNumeroPeriodo
	 * @param pServicioOSP
	 * @param pCodigoMedidor
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	@Override
	@SuppressWarnings("unchecked")
	public FiltroValorMedidor findListaValoresMedidor(FiltroValorMedidor pFiltro) throws Exception {

		Criterio crit = Criterio.getInstance(this.entityManager, ValorMedidor.class);

		List listaParcelas = null;
		if(pFiltro.getCalle() != null) {
			listaParcelas = Criterio.getInstance(this.entityManager, Parcela.class)
					.add(Restriccion.IGUAL("domicilioParcelario.relacionCalle.idAbstractCalle", pFiltro.getCalle().getIdCalle())).list();

			crit.add(Restriccion.EN("docHabilitanteEspecializado.parcela", listaParcelas));
		}

		if(pFiltro.getServicioOSP() != null && pFiltro.getServicioOSP().getIdTipoAlicuota() != -1) {
			crit.add(Restriccion.IGUAL("servicioOSP", pFiltro.getServicioOSP()));
		}

		crit.add(Restriccion.IGUAL("cuotaLiquidacion", pFiltro.getCuota())).add(Restriccion.IGUAL("cuotaLiquidacion.periodo", pFiltro.getPeriodo()))
				.add(Restriccion.IGUAL("cuotaLiquidacion.periodo.calendario", pFiltro.getCalendario()))
				.add(Restriccion.IGUAL("cuotaLiquidacion.periodo.calendario.anio", pFiltro.getAnio()));

		List retorno = null;
		if(pFiltro.getCodigoMedidor() != null && pFiltro.getCodigoMedidor().length() > 0) {
			DocumentoOSP locDocumentoOSP = (DocumentoOSP) Criterio.getInstance(this.entityManager, DocumentoOSP.class)
					.add(Restriccion.IGUAL("estado", DocHabilitanteEspecializado.Estado.ACTIVO)).add(Restriccion.IGUAL("codigoMedidor", pFiltro.getCodigoMedidor())).uniqueResult();

			if(locDocumentoOSP != null) {
				crit.add(Restriccion.IGUAL("docHabilitanteEspecializado.idDocHabilitanteEspecializado", locDocumentoOSP.getIdDocHabilitanteEspecializado()));
			}
		}

		pFiltro.procesarYListar(crit);

		for(Object o : pFiltro.getListaResultados()) {
			ValorMedidor locValor = (ValorMedidor) o;
			locValor.toString();
			locValor.getDocHabilitanteEspecializado().toString();
			locValor.getStringDireccion();
			locValor.getStringPersona();
			locValor.getStringObligacion().toString();
			locValor.getStringCodigoMedidor();
		}
		return pFiltro;
	}

	@Override
	public List<RegistroValuadoTasaMenor> generarRegistrosTasaMenor(Persona pPersona, CuotaLiquidacion pCuota, PlantillaDocumentoTasaMenor pPlantilla) throws TrascenderException {
		if(pCuota == null) {
			throw new SaicException(502);
		}

		if(pPlantilla == null) {
			throw new SaicException(503);
		}

		pPlantilla = entityManager.merge(pPlantilla);

		// Se busca los Documentos de Obligaciones validas, y a las cuales el usuario haya anexado Plantillas
		// de atributos dinamicos a su lista de Registros Valuados.
		List<DocumentoTasaMenor> locListaDocumentos = Criterio
				.getInstance(entityManager, DocumentoTasaMenor.class)
				.add(Restriccion.IGUAL("plantillaDocumentoTasaMenor", pPlantilla))
				.add(Restriccion.NOT(Restriccion.ESTA_VACIO("plantillaDocumentoTasaMenor.listaPlantillasRegistroValuado")))
				.add(Restriccion.IGUAL("obligacion.persona", pPersona))
				.add(Restriccion.NOT(Restriccion.OR(Restriccion.IGUAL("obligacion.estado", Obligacion.Estado.TERMINADO),
						Restriccion.IGUAL("obligacion.estado", Obligacion.Estado.ANULADO)))).list();

		List<RegistroValuadoTasaMenor> locListaResultado = new ArrayList<RegistroValuadoTasaMenor>();
		RegistroValuadoTasaMenor locRegistroValuado = null;
		for(DocumentoTasaMenor cadaDocumento : locListaDocumentos) {
			for(RegAlicuota cadaRegAlicuota : cadaDocumento.getListaRegAlicuotas()) {
				locRegistroValuado = (RegistroValuadoTasaMenor) cadaDocumento.getRegistroValuado(pCuota, cadaRegAlicuota);
				// Si no hay registro valuado para ese período, se genera.
				if(locRegistroValuado == null) {
					RegistroValuadoTasaMenor nuevoRegistro = pPlantilla.generarRegistroValuado();
					nuevoRegistro.setDocHabilitanteEspecializado(cadaDocumento);
					nuevoRegistro.setEstado(RegistroValuado.Estado.CARGADO);
					nuevoRegistro.setCuotaLiquidacion(pCuota);
					locListaResultado.add(nuevoRegistro);
				}
			}
		}
		return locListaResultado;
	}

	@Override
	public void addRegistrosValuadosTasaMenor(List<RegistroValuadoTasaMenor> pListaRegistrosValuados) throws TrascenderException {
		for(RegistroValuadoTasaMenor cadaRegistroValuado : pListaRegistrosValuados) {
			if(cadaRegistroValuado.getListaAtributosDinamicos().size() > 0) {
				// Se setea un monto imponible pues no puede ir nulo a la base, pero
				// los verdaderos valores estan en los Atributos Dinamicos
				cadaRegistroValuado.setMontoImponible(0D);
				entityManager.persist(cadaRegistroValuado);
			}
		}
		// Por los atributos dinamicos
		entityManager.flush();
	}

	@Override
	public void updateRegistroValuadoTasaMenor(RegistroValuadoTasaMenor pRegistroValuado) throws TrascenderException {
		if(pRegistroValuado.getEstado().equals(RegistroValuado.Estado.LIQUIDADO)) {
			throw new SaicException(504);
		}
		entityManager.merge(pRegistroValuado);
		// Por los atributos dinamicos
		entityManager.flush();
	}

	@Override
	public void deleteRegistroValuadoTasaMenor(RegistroValuadoTasaMenor pRegistroValuadoTasaMenor) throws Exception {
		if(pRegistroValuadoTasaMenor.getEstado().equals(RegistroValuado.Estado.LIQUIDADO)) {
			throw new SaicException(505);
		}
		pRegistroValuadoTasaMenor = entityManager.merge(pRegistroValuadoTasaMenor);
		entityManager.remove(pRegistroValuadoTasaMenor);
	}

	@Override
	public List<RegistroValuadoTasaMenor> findListaRegistrosValuadosTasaMenor(Persona pPersona, CuotaLiquidacion pCuota, PeriodoLiquidacion pPeriodo, Calendario pCalendario,
			PlantillaDocumentoTasaMenor pPlantilla) {
		Criterio locCriterio = Criterio.getInstance(entityManager, RegistroValuadoTasaMenor.class).add(Restriccion.IGUAL("docHabilitanteEspecializado.obligacion.persona", pPersona))
				.add(Restriccion.IGUAL("cuotaLiquidacion", pCuota)).add(Restriccion.IGUAL("cuotaLiquidacion.periodo", pPeriodo))
				.add(Restriccion.IGUAL("cuotaLiquidacion.periodo.calendario", pCalendario)).add(Restriccion.IGUAL("plantillaDocTasaMenor", pPlantilla));
		List<RegistroValuadoTasaMenor> locListaRegistros = locCriterio.list();
		for(RegistroValuadoTasaMenor cadaRegistro : locListaRegistros) {
			cadaRegistro.toString();
			cadaRegistro.getDocHabilitanteEspecializado().toString();
			cadaRegistro.getStringObligacion().toString();
			cadaRegistro.getDocHabilitanteEspecializado().getObligacion().getPersona().toString();
		}
		return locListaRegistros;
	}

	@Override
	public RegistroValuadoTasaMenor getRegistroValuadoTasaMenorPorId(long pId) {
		RegistroValuadoTasaMenor locRegistro = entityManager.find(RegistroValuadoTasaMenor.class, pId);
		if(locRegistro != null) {
			locRegistro.getListaAtributosDinamicos().size();
			locRegistro.getDocHabilitanteEspecializado().toString();
			locRegistro.generarAtributosDinamicos();
		}
		return locRegistro;
	}

	/**
	 * Recupera el listado de pagos de sellados de una obligacion
	 * 
	 * @param pPersona
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	@Override
	@SuppressWarnings("unchecked")
	public java.util.List findListaPagoSellado(com.trascender.framework.recurso.persistent.Persona pPersona, com.trascender.habilitaciones.recurso.persistent.Obligacion pObligacion,
			Boolean pPagado) throws Exception {
		if((pPersona == null) && (pObligacion == null)) {
			throw new SaicException(4);
		}
		if(pObligacion != null) {
			this.generarSellado(pObligacion);
		} else {
			this.generarSellado(pPersona);
		}

		this.entityManager.flush();

		Criterio crit = Criterio.getInstance(this.entityManager, PagoSellado.class).crearAlias("docGeneradorDeuda.obligacion", "locObligacion")
				.add(Restriccion.IGUAL("sellado.pagado", false));

		// Agrego los pParams
		crit.add(Restriccion.IGUAL("locObligacion.persona", pPersona)).add(Restriccion.IGUAL("locObligacion", pObligacion));

		List locLista = crit.list();
		List<PagoSellado> locRetorno = new ArrayList<PagoSellado>();

		for(Object o : locLista) {
			PagoSellado locSellado = (PagoSellado) o;
			if(pPagado != null) {
				if(pPagado.equals(locSellado.getRegistroCancelacion() != null)) {
					locRetorno.add(locSellado);
				}
			} else {
				locRetorno.add(locSellado);
			}
			locSellado.getPersona().toString();
			locSellado.getDocGeneradorDeuda().toString();
		}
		return locRetorno;
	}

	@SuppressWarnings("unchecked")
	private void generarSellado(Persona pPersona) throws Exception {
		List locLista = this.businessObligacion.findListaObligaciones(pPersona, null, null, null, null);
		for(Object cadaObj : locLista) {
			Obligacion locObligacion = (Obligacion) cadaObj;
			this.generarSellado(locObligacion);
		}
	}

	@SuppressWarnings("unchecked")
	private void generarSellado(Obligacion pObligacion) throws Exception {
		List listaSellados = this.obtenerSellados(pObligacion);
		List<Sellado> listaSelladosFiltrados = this.filtrarSelladosCreados(listaSellados);
		List<PagoSellado> listaPagoSelladosGenerados = this.generarSellado(listaSelladosFiltrados);
		DocGeneradorDeuda locDocGeneradorDeuda = this.getDocGeneradorDeudaSelladoFromObligacion(pObligacion);

		Integer locCantidad = locDocGeneradorDeuda.getCantidadRegDeuda();
		for(PagoSellado locCadaPagoSellado : listaPagoSelladosGenerados) {
			locCantidad++;
			locCadaPagoSellado.setDocGeneradorDeuda(locDocGeneradorDeuda);
			locCadaPagoSellado.setNumeroRegistroDeuda(locCantidad);
			locDocGeneradorDeuda.getListaRegistrosDeuda().add(locCadaPagoSellado);
			this.entityManager.persist(locCadaPagoSellado);
		}
		this.entityManager.merge(locDocGeneradorDeuda);
	}

	@SuppressWarnings("unchecked")
	private List<Sellado> filtrarSelladosCreados(List pListaSellados) {
		List<Sellado> locListaSelladosRetorno = new ArrayList<Sellado>();
		for(Object CadaObj : pListaSellados) {
			Sellado cadaSellado = (Sellado) CadaObj;
			if((Long) Criterio.getInstance(this.entityManager, PagoSellado.class).setProyeccion(Proyeccion.PROP("idRegistroDeuda")).setProyeccion(Proyeccion.COUNT())
					.add(Restriccion.IGUAL("sellado", cadaSellado)).uniqueResult() == 0) {
				locListaSelladosRetorno.add(cadaSellado);
			}
		}
		return locListaSelladosRetorno;
	}

	/**
	 * Retorna una lista de PagoSellado todos recién generados
	 * 
	 * @param pListaSellados
	 *            lista de Sellado que deben generarse (no se filtra aquellos que están ya generados)
	 * @return lista de PagoSellado con los PagoSellado nuevos
	 */
	private List<PagoSellado> generarSellado(List<Sellado> pListaSellados) {
		List<PagoSellado> locListaPagosSellado = new ArrayList<PagoSellado>();
		for(Sellado locSellado : pListaSellados) {
			PagoSellado locPagoSellado = this.createNuevoPagoSelladoFromSellado(locSellado);
			locListaPagosSellado.add(locPagoSellado);
		}
		return locListaPagosSellado;
	}

	/**
	 * Genera un pago sellado a partir de un sellado (debe ser asignado a un docGeneradorDeuda ántes de ser almacenado)
	 * 
	 * @param locSellado
	 * @return PagoSellado generado
	 */
	private PagoSellado createNuevoPagoSelladoFromSellado(Sellado locSellado) {
		return new PagoSellado(locSellado);
	}

	/**
	 * Obtiene la lista de todos los sellados de una obligacion
	 * 
	 * @param obligacion
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List obtenerSellados(Obligacion pObligacion) {
		return Criterio.getInstance(this.entityManager, Sellado.class).add(Restriccion.IGUAL("obligacion", pObligacion)).list();
	}

	/**
	 * Obtiene un documento generador de deuda desde una obligacion
	 * 
	 * @param pObligacion
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private DocGeneradorDeuda getDocGeneradorDeudaSelladoFromObligacion(Obligacion pObligacion) throws Exception {
		if(pObligacion == null) {
			throw new SaicException(5);
		}

		Criterio locCriteria = Criterio.getInstance(this.entityManager, DocGeneradorDeuda.class).add(Restriccion.IGUAL("obligacion", pObligacion))
				.add(Restriccion.IGUAL("tipoDocGeneradorDeuda", DocGeneradorDeuda.TipoDocGeneradorDeuda.SELLADO))
				.add(Restriccion.OR(Restriccion.NULO("fechaHasta"), Restriccion.MAYOR("fechaHasta", SecurityMgr.getInstance().getFechaActual().getTime())));

		List locListaDocGeneradorDeuda = locCriteria.list();
		DocGeneradorDeuda locDocRetorno = null;

		if(locListaDocGeneradorDeuda.isEmpty()) {
			locDocRetorno = this.crearNuevoDocGeneradorDeuda(pObligacion);
		} else {
			locDocRetorno = (DocGeneradorDeuda) locListaDocGeneradorDeuda.get(0);
		}
		// el caso sería que nunca hay más de un documento generador de deuda
		// así que debería haber al menos uno por obligacion
		return locDocRetorno;
	}

	private DocGeneradorDeuda crearNuevoDocGeneradorDeuda(Obligacion pObligacion) throws Exception {
		DocGeneradorDeuda locDocRetorno = new DocGeneradorDeuda();
		locDocRetorno.setCantidadRegDeuda(0);
		locDocRetorno.setTipoDocGeneradorDeuda(DocGeneradorDeuda.TipoDocGeneradorDeuda.SELLADO);
		locDocRetorno.setFechaDesde(Calendar.getInstance().getTime());
		locDocRetorno.setNroUtltimoRegistroDeudaLiquidado(0);
		locDocRetorno.setPeriodicidad(Periodicidad.ANUAL);
		locDocRetorno.setNombre("Sellados de " + pObligacion.getNombre());
		locDocRetorno.setObligacion(pObligacion);

		this.entityManager.persist(locDocRetorno);
		this.entityManager.flush();
		this.entityManager.refresh(locDocRetorno);

		Long id = locDocRetorno.getIdDocGeneradorDeuda();
		if(id == -1)
			throw new Exception("NO DEVUELVE UN ID VALIDO");// EXCEPCION DE CONTROL POR SI EL ENTITY NO ME GENERA UN ID
		locDocRetorno.setIdDocGeneradorDeuda(id);
		return locDocRetorno;
	}
}