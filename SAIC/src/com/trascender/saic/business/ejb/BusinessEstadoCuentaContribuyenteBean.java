
package com.trascender.saic.business.ejb;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.ejb.CreateException;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import org.hibernate.mapping.Array;

import ar.trascender.criterio.clases.Criterio;
import ar.trascender.criterio.clases.Orden;
import ar.trascender.criterio.clases.Proyeccion;
import ar.trascender.criterio.clases.Restriccion;

import com.trascender.catastro.business.interfaces.BusinessRegistroParcelarioLocal;
import com.trascender.catastro.recurso.filtros.FiltroParcela;
import com.trascender.catastro.recurso.persistent.NomenclaturaCatastral;
import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.framework.business.interfaces.BusinessPersonaLocal;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.filtros.FiltroPersonaFisica;
import com.trascender.framework.recurso.persistent.ParametroSistemaString;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.framework.recurso.transients.Grupo;
import com.trascender.framework.recurso.transients.Periodo;
import com.trascender.framework.recurso.transients.Recurso;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.habilitaciones.recurso.persistent.DocHabilitanteEspecializado;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.TipoObligacion;
import com.trascender.habilitaciones.recurso.persistent.osp.DocumentoOSP;
import com.trascender.habilitaciones.recurso.persistent.refinanciacion.DocumentoRef;
import com.trascender.habilitaciones.recurso.persistent.tgi.DocumentoTGI;
import com.trascender.saic.business.interfaces.BusinessEstadoCuentaContribuyenteLocal;
import com.trascender.saic.business.interfaces.BusinessReLiquidacionLocal;
import com.trascender.saic.exception.SaicException;
import com.trascender.saic.recurso.filtros.FiltroLiquidacionTasaRefer;
import com.trascender.saic.recurso.persistent.AlicuotaLiquidada;
import com.trascender.saic.recurso.persistent.EstadoCuentaTemporalOSP;
import com.trascender.saic.recurso.persistent.EstadoCuentaTemporalPFO;
import com.trascender.saic.recurso.persistent.EstadoCuentaTemporalSHPS;
import com.trascender.saic.recurso.persistent.EstadoCuentaTemporalTGI;
import com.trascender.saic.recurso.persistent.LiquidacionTasa;
import com.trascender.saic.recurso.persistent.ParametroValuadoAlicuota;
import com.trascender.saic.recurso.persistent.PlantillaPlanDePago;
import com.trascender.saic.recurso.persistent.RegistroDeuda;
import com.trascender.saic.recurso.persistent.RegistroDeuda.EstadoRegistroDeuda;
import com.trascender.saic.recurso.persistent.RegistroDeuda.TipoDeuda;
import com.trascender.saic.recurso.persistent.TasaTGI;
import com.trascender.saic.recurso.persistent.auditoriaTributaria.AuditoriaTributaria;
import com.trascender.saic.recurso.persistent.refinanciacion.CuotaRefinanciacion;
import com.trascender.saic.recurso.references.LiquidacionTasaRefer;
import com.trascender.saic.recurso.transients.EstadoCuentaContribuyente;
import com.trascender.saic.recurso.transients.LiquidacionTasaAgrupada;

/**
 * @ejb.bean name="BusinessEstadoCuentaContribuyente" display-name="Name for BusinessEstadoCuentaContribuyente"
 *           description="Description for BusinessEstadoCuentaContribuyente" jndi-name="ejb/BusinessEstadoCuentaContribuyente" type="Stateless"
 *           view-type="local"
 */
@Stateless(name = "BusinessEstadoCuentaContribuyenteLocal")
public class BusinessEstadoCuentaContribuyenteBean implements BusinessEstadoCuentaContribuyenteLocal {

	private static final long serialVersionUID = -2044260413161599292L;

	@EJB
	private BusinessReLiquidacionLocal businessReliquidacion;

	@EJB
	private BusinessReLiquidacionLocal businessReLiquidacionLocal;

	@EJB
	private BusinessRegistroParcelarioLocal businessParcela;

	@EJB
	private BusinessPersonaLocal businessPersona;

	@Resource(mappedName = "java:/vipiansDS", shareable = true)
	private DataSource datasource;

	static {
		Grupo grupo = new Grupo();
		grupo.setId(serialVersionUID);
		grupo.setNombre("SAI|Adm. Estado de Cuenta");

		Recurso locEstadoCuenta = new Recurso();
		locEstadoCuenta.setIdRecurso(LiquidacionTasa.codigoEstadoCuenta);
		locEstadoCuenta.setNombre("Estado de Cuenta");
		locEstadoCuenta.setClase(LiquidacionTasa.class);
		grupo.getListaRecursos().add(locEstadoCuenta);

		Recurso locRecurso = new Recurso();
		locRecurso.setIdRecurso(AuditoriaTributaria.serialVersionUID);
		locRecurso.setNombre("Auditoria Tributaria");
		locRecurso.setClase(AuditoriaTributaria.class);
		grupo.getListaRecursos().add(locRecurso);
		
		Recurso plantilla = new Recurso();
		plantilla.setIdRecurso(PlantillaPlanDePago.serialVersionUID);
		plantilla.setNombre("Plantilla Plan de Pago");
		plantilla.setClase(PlantillaPlanDePago.class);
		plantilla.setAtributosConsultables("Nombre", "nombre");
		grupo.getListaRecursos().add(plantilla);

		SecurityMgr.getInstance().addGrupo(grupo);
	}

	@PersistenceContext(name = "Vipians")
	private EntityManager entityManager;

	/**
	 * @ejb.create-method
	 */
	public void ejbCreate() throws CreateException {
		// try {
		// this.businessReliquidacion = ((BusinessReLiquidacionLocalHome) PortableRemoteObject
		// .narrow(new InitialContext()
		// .lookup(BusinessReLiquidacionLocalHome.JNDI_NAME),BusinessReLiquidacionLocalHome.class)).create();
		// } catch (Exception e) {
		// e.printStackTrace();
		// throw new CreateException("No se ha podido inicializar el EJB de reliquidacion");
		// }
	}

	public BusinessEstadoCuentaContribuyenteBean() {
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
	 * Recupera el listado de obligaciones de un contribuyente
	 * 
	 * @param pPersona
	 *            contribuyente asociado
	 * @param pEstadoObligacion
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	@Override
	@SuppressWarnings("unchecked")
	public java.util.List getListaObligacionesContribuyente(Long pIdTramite, com.trascender.framework.recurso.persistent.Persona pPersona,
			com.trascender.habilitaciones.recurso.persistent.Obligacion.Estado pEstadoObligacion) throws Exception {

		Criterio locCriterio = Criterio.getInstance(this.entityManager, Obligacion.class).add(Restriccion.IGUAL("idObligacion", pIdTramite)).add(Restriccion.IGUAL("persona", pPersona))
				.add(Restriccion.IGUAL("estado", pEstadoObligacion));

		List locLista = locCriterio.list();

		for(Object cadaObject : locLista) {
			locLista.toString();
		}

		return locLista;

	}

	@Override
	public java.util.List getListaDeudasContribuyente(Obligacion pObligacion) throws Exception {
		return new ArrayList(this.getListaDeudasContribuyente(pObligacion, null));

	}

	/**
	 * Recupera el listado de LiquidacionTasa de una obligacion
	 * 
	 * @param pPersona
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Set getListaDeudasContribuyente(Obligacion pObligacion, Periodo pPeriodo) throws Exception {

		Criterio locListaIdsTasasTGINoValidas = Criterio.getInstance(this.entityManager, TasaTGI.class).setProyeccion(Proyeccion.PROP("idDocGeneradorDeuda"))
				.add(Restriccion.OR(Restriccion.IGUAL("estado", TasaTGI.Estado.INACTIVO), Restriccion.IGUAL("estado", TasaTGI.Estado.TEMPORALMENTE_INACTIVO)))
				.add(Restriccion.IGUAL("obligacion", pObligacion));
		locListaIdsTasasTGINoValidas.setModoDebug(true);

		Criterio locCriterio = null;
		if(pObligacion.getDocumentoEspecializado() instanceof DocumentoRef) {
			locCriterio = Criterio.getInstance(this.entityManager, CuotaRefinanciacion.class);
			System.out.println("CUOTAREFINANCIACION");
		} else {
			locCriterio = Criterio.getInstance(this.entityManager, RegistroDeuda.class);
			System.out.println("REGISTRODEUDA");
		}
		// locCriterio.setFetchJoin("periodo")
		locCriterio.setModoDebug(true).add(Restriccion.IGUAL("docGeneradorDeuda.obligacion", pObligacion)).add(Restriccion.NULO("registroCancelacion"))
				.add(Restriccion.OR(Restriccion.IGUAL("estado", RegistroDeuda.EstadoRegistroDeuda.VENCIDA), Restriccion.IGUAL("estado", RegistroDeuda.EstadoRegistroDeuda.VIGENTE)));

		if(pPeriodo != null) {
			locCriterio.add(Restriccion.IGUAL("periodo", pPeriodo));
		}

		List listaTasasTGIInválidas = locListaIdsTasasTGINoValidas.list();
		System.out.println("LISta tasas TGI = " + listaTasasTGIInválidas);

		if(!listaTasasTGIInválidas.isEmpty()) {
			System.out.println("AGREGO " + listaTasasTGIInválidas.size() + " TASAS INVALIDAS TGI");
			locCriterio.add(Restriccion.NOT(Restriccion.EN("docGeneradorDeuda.idDocGeneradorDeuda", listaTasasTGIInválidas)));
		}

		List locListadoDeudas = locCriterio.list();
		Set locListaRetorno = new HashSet();

		for(Object cadaObject : locListadoDeudas) {
			if(cadaObject instanceof CuotaRefinanciacion) {
				CuotaRefinanciacion locCuotaRefinanciacion = (CuotaRefinanciacion) cadaObject;
				locCuotaRefinanciacion.toString();
				locCuotaRefinanciacion.getValor();
				locCuotaRefinanciacion.getDocGeneradorDeuda().toString();
				locCuotaRefinanciacion.getDocGeneradorDeuda().getObligacion().toString();
				if(locCuotaRefinanciacion.getEstado().equals(RegistroDeuda.EstadoRegistroDeuda.VENCIDA)) {
					locListaRetorno.add(locCuotaRefinanciacion);
				}
			} else {
				LiquidacionTasa locLiquidacionTasa = (LiquidacionTasa) cadaObject;
				locLiquidacionTasa.toString();
				locLiquidacionTasa.getMonto();
				locLiquidacionTasa.getMontoMultas();
				locLiquidacionTasa.getListaVencimientos().toString();
				locLiquidacionTasa.getDocGeneradorDeuda().toString();
				locLiquidacionTasa.getDocGeneradorDeuda().getObligacion().toString();
				if(locLiquidacionTasa.getEstado().equals(RegistroDeuda.EstadoRegistroDeuda.VENCIDA)) {
					locListaRetorno.add(locLiquidacionTasa);
				}

				for(AlicuotaLiquidada cadaAlicuota : locLiquidacionTasa.getListaAlicuotasLiquidadas()) {
					cadaAlicuota.toString();
				}
			}

		}
		return locListaRetorno;
	}

	/**
	 * Business method Recupera el listado de EstadoCuentaContribuyente
	 * 
	 * @ejb.interface-method view-type= "local"
	 * @param pPersona
	 * @param pEstadoObligacion
	 * @return
	 * @throws Exception
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<EstadoCuentaContribuyente> getListaEstadosCuentaContribuyente(Persona pPersona, Obligacion.Estado pEstadoObligacion) throws Exception {

		List<DocHabilitanteEspecializado> locListaDocHabEspecializados = this.getListaDocEspecializadosContribuyente(pPersona);

		List<EstadoCuentaContribuyente> locListaRetorno = new ArrayList<EstadoCuentaContribuyente>();
		for(Object cadaObject : locListaDocHabEspecializados) {
			DocHabilitanteEspecializado locDocHabilitanteEspecializado = (DocHabilitanteEspecializado) cadaObject;

			Obligacion locObligacion = locDocHabilitanteEspecializado.getObligacion();
			locObligacion.toString();
			List<RegistroDeuda> locListaDeudas = this.getListaDeudasContribuyente(locObligacion);
			Double locTotalAdeudado = 0D;
			for(RegistroDeuda cadaRegistroDeuda : locListaDeudas) {
				locTotalAdeudado += cadaRegistroDeuda.getMonto();
			}
			EstadoCuentaContribuyente locEstadoCuentaContribuyente = new EstadoCuentaContribuyente(locObligacion, locTotalAdeudado);
			locListaRetorno.add(locEstadoCuentaContribuyente);
		}

		return locListaRetorno;
	}

	/**
	 * Recupera el listado de LiquidacionTasa de una obligación
	 * 
	 * @param pObligacion
	 * @param pEstadoRegistroDeuda
	 * @param pEstado
	 * @param pPeriodo
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	@Override
	@SuppressWarnings("unchecked")
	public java.util.List getListaRegistrosDeudaObligacion(com.trascender.habilitaciones.recurso.persistent.Obligacion pObligacion, EstadoRegistroDeuda pEstadoRegistroDeuda,
			String pEstado, Periodo pPeriodo) throws Exception {

		Criterio locListaIdsTasasTGINoValidas = Criterio.getInstance(this.entityManager, TasaTGI.class).setProyeccion(Proyeccion.PROP("idDocGeneradorDeuda"))
				.add(Restriccion.OR(Restriccion.IGUAL("estado", TasaTGI.Estado.INACTIVO), Restriccion.IGUAL("estado", TasaTGI.Estado.TEMPORALMENTE_INACTIVO)))
				.add(Restriccion.IGUAL("obligacion", pObligacion));

		Criterio locCriterio = null;

		if(pObligacion.getDocumentoEspecializado() instanceof DocumentoRef) {
			locCriterio = Criterio.getInstance(this.entityManager, CuotaRefinanciacion.class);
		} else {
			locCriterio = Criterio.getInstance(this.entityManager, LiquidacionTasa.class);
		}

		locCriterio.add(Restriccion.IGUAL("docGeneradorDeuda.obligacion", pObligacion));

		// locCriterio
		// // .setFetchJoin("periodo")
		// .add(Restriccion.IGUAL("docGeneradorDeuda.obligacion",pObligacion))
		// .add(Restriccion.OR(Restriccion.IGUAL("estado", RegistroDeuda.EstadoRegistroDeuda.VENCIDA)
		// ,
		// Restriccion.IGUAL("estado", RegistroDeuda.EstadoRegistroDeuda.VIGENTE)
		// )
		// );

		List listaTasasTGIInválidas = locListaIdsTasasTGINoValidas.list();

		if(!listaTasasTGIInválidas.isEmpty()) {
			locCriterio.add(Restriccion.NOT(Restriccion.EN("locDocGeneradorDeuda.idDocGeneradorDeuda", listaTasasTGIInválidas)));
		}

		locCriterio.add(Restriccion.IGUAL("cuotaLiquidacion", pPeriodo)).add(Orden.ASC("cuotaLiquidacion.fechaInicio"));

		List locListadoDeudas = locCriterio.list();

		List<RegistroDeuda> locListaDeudasRetorno = new ArrayList<RegistroDeuda>();

		for(Object cadaObject : locListadoDeudas) {
			RegistroDeuda locRegistroDeuda = (RegistroDeuda) cadaObject;
			locRegistroDeuda.toString();
			locRegistroDeuda.getCuotaLiquidacion().toString();
			locRegistroDeuda.getMonto();
			locRegistroDeuda.getDocGeneradorDeuda().toString();
			locRegistroDeuda.getDocGeneradorDeuda().getObligacion().toString();
			locRegistroDeuda.getDocGeneradorDeuda().getObligacion().getPersona().toString();
			locRegistroDeuda.getFechaVencimiento();
			if(locRegistroDeuda.getRegistroCancelacion() != null) {
				locRegistroDeuda.getRegistroCancelacion().toString();
			}

			boolean locAgregarEstadoRegistroDeuda = false;
			if(pEstadoRegistroDeuda != null) {
				if(locRegistroDeuda.getEstado().equals(pEstadoRegistroDeuda)) {
					locAgregarEstadoRegistroDeuda = true;
				}
			} else {
				locAgregarEstadoRegistroDeuda = true;
			}

			boolean locAgregarEstado = false;

			if(pEstado != null && !pEstado.equals("")) {
				// como al traer fecha vencimiento si es esta vencida es
				// null entonces comparo con el parametro pasado
				if(pEstado.trim().equals("CANCELADA")
						&& !(locRegistroDeuda.getEstado().equals(EstadoRegistroDeuda.VENCIDA) || locRegistroDeuda.getEstado().equals(EstadoRegistroDeuda.VIGENTE))) {
					locAgregarEstado = true;
				}

				if(pEstado.trim().toUpperCase().equals("NO CANCELADA")
						&& (locRegistroDeuda.getEstado().equals(EstadoRegistroDeuda.VENCIDA) || locRegistroDeuda.getEstado().equals(EstadoRegistroDeuda.VIGENTE))) {
					locAgregarEstado = true;
				}
			} else {
				locAgregarEstado = true;
			}

			if(locAgregarEstadoRegistroDeuda && locAgregarEstado) {
				locListaDeudasRetorno.add(locRegistroDeuda);
			}
		}
		return locListaDeudasRetorno;
	}

	/**
	 * 
	 * @param pListaObligaciones
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "local"
	 */
	@Override
	@SuppressWarnings("unchecked")
	public java.util.List getListaDeudasContribuyente(java.util.List pListaObligaciones) throws Exception {
		Persona locPersona = null;
		List<Long> locListaIdsObligaciones = new ArrayList<Long>();

		for(Object cadaObject : pListaObligaciones) {
			Obligacion cadaObligacion = (Obligacion) cadaObject;
			if(locPersona == null) {
				locPersona = cadaObligacion.getPersona();
				locListaIdsObligaciones.add(cadaObligacion.getIdObligacion());
			} else {
				if(!cadaObligacion.getPersona().equals(locPersona))
					throw new SaicException(5084);
			}

		}

		// Criterio locCriterioIdsTasasTGINoValidasCriteria = Criterio.getInstance(this.entityManager,TasaTGI.class)
		// .setProyeccion(Proyeccion.PROP("idDocGeneradorDeuda"))
		// .add(
		// Restriccion.OR(
		// Restriccion.IGUAL("estado",TasaTGI.Estado.INACTIVO)
		// ,
		// Restriccion.IGUAL("estado", TasaTGI.Estado.TEMPORALMENTE_INACTIVO)
		// )
		// )
		// .add(Restriccion.EN("obligacion.idObligacion",locListaIdsObligaciones));

		// List locListaIdsTasasTGINoValidas = locCriterioIdsTasasTGINoValidasCriteria.list();

		Criterio locCriterio = Criterio.getInstance(this.entityManager, RegistroDeuda.class).add(Restriccion.EN("docGeneradorDeuda.obligacion.idObligacion", locListaIdsObligaciones))
				.setModoDebug(true);

		// if (!locListaIdsTasasTGINoValidas.isEmpty()){
		// locCriterio.add(Restriccion.NOT(Restriccion.EN("docGeneradorDeuda.idDocGeneradorDeuda", locListaIdsTasasTGINoValidas)));
		// }

		this.entityManager.clear();
		List<RegistroDeuda> locListaRegistrosDeuda = locCriterio.list(); // acaa

		List<RegistroDeuda> locListaRetorno = new ArrayList<RegistroDeuda>();

		for(RegistroDeuda locRegistroDeuda : locListaRegistrosDeuda) {
			locRegistroDeuda = this.entityManager.merge(locRegistroDeuda);
			locRegistroDeuda.toString();
			locRegistroDeuda.getCuotaLiquidacion().toString();
			if(locRegistroDeuda.getEstado().equals(EstadoRegistroDeuda.VENCIDA)) {
				if(locRegistroDeuda.getTipoDeuda().equals(TipoDeuda.LIQUIDACION)) {
					LiquidacionTasa locLiquidacionTasa = (LiquidacionTasa) locRegistroDeuda;

					List<String> locListaParametrosValuadosVacia = new ArrayList<String>(0);
					List<ParametroValuadoAlicuota> locListaParametrosAlicuotaVacias = new ArrayList<ParametroValuadoAlicuota>(0);
					// Tomo la primer reliquidacion que se retorna
					System.out.println("Liq: " + locLiquidacionTasa + " fecha: " + SecurityMgr.getInstance().getFechaActual().getTime());
//					List<Reliquidacion> locListaReliquidaciones = this.businessReliquidacion.reliquidarObligacion(locLiquidacionTasa, 
//							SecurityMgr.getInstance().getFechaActual()
//							.getTime(), locListaParametrosValuadosVacia, 
//							locListaParametrosAlicuotaVacias, 
//							null, true, false);
//					for(Reliquidacion cadaReliquidacion : locListaReliquidaciones) {
//						cadaReliquidacion.getLiquidacionTasa().toString();
//						cadaReliquidacion.getLiquidacionTasa().getTipoTasa().toString();
//
//						LiquidacionTasa locLiquidacionTasaDeReliq = cadaReliquidacion.getLiquidacionTasa();
//						locLiquidacionTasaDeReliq.toString();
//
//						// Me interesa que tenga igual id al del registro deuda que reliquido por controles agregados al refinanciar
//						locLiquidacionTasaDeReliq.setIdRegistroDeuda(locRegistroDeuda.getIdRegistroDeuda());
//						// En éste caso me importa que la fecha de emisión de la reliquidación sea la misma que la del registro deuda actual
//						locLiquidacionTasaDeReliq.setFechaEmision(locRegistroDeuda.getFechaEmision());
//						locLiquidacionTasaDeReliq.setInteres(cadaReliquidacion.getLiquidacionTasa().getInteres());
//						locLiquidacionTasaDeReliq.setRecargo(cadaReliquidacion.getLiquidacionTasa().getRecargo());
//						locLiquidacionTasaDeReliq.setTipoDeuda(TipoDeuda.LIQUIDACION);
//						// Si la deuda es 2do tercio por ejm tengo q devolver la reliquidada de ese periodo, sino se devuelve la correspondiente al bimestre
//						// if(locLiquidacionTasaDeReliq.getDocGeneradorDeuda().getObligacion().getDocumentoEspecializado() instanceof DocumentoTGI){
//						//
//						// if(locLiquidacionTasa.getTipoTasa().getPeriodicidad().equals(Periodicidad.ANUAL) &&
//						// locLiquidacionTasa.getTipoTasa().getPeriodicidadCuotas().equals(Periodicidad.MENSUAL)){
//						// if(locLiquidacionTasaDeReliq.getTipoTasa().getPeriodicidad().equals(Periodicidad.ANUAL) &&
//						// locLiquidacionTasaDeReliq.getTipoTasa().getPeriodicidadCuotas().equals(Periodicidad.MENSUAL)){
//						// if(locLiquidacionTasa.getNumeroCuota().equals(locLiquidacionTasaDeReliq.getNumeroCuota())){
//						// //Mi deuda es un tercio
//						// locListaRetorno.add(locLiquidacionTasaDeReliq);
//						// }
//						// }
//						// }
//						// else if(locLiquidacionTasaDeReliq.getTipoTasa().getPeriodicidad().equals(Periodicidad.BIMESTRAL) &&
//						// locLiquidacionTasa.getTipoTasa().getPeriodicidad().equals(Periodicidad.BIMESTRAL)){
//						// locListaRetorno.add(locLiquidacionTasaDeReliq);
//						// }
//						// }
//						// else{
//						locListaRetorno.add(locLiquidacionTasaDeReliq);
//						// }
//					}

				} else {
					locRegistroDeuda.toString();
					locListaRetorno.add(locRegistroDeuda);
				}
			}
			this.entityManager.detach(locRegistroDeuda);
		}

		System.out.println("Deudas Encontradas: " + locListaRetorno.size());
		// for(RegistroDeuda cadaRegistroDeuda : locListaRetorno){
		// cadaRegistroDeuda.toString();
		// cadaRegistroDeuda.getDocGeneradorDeuda().toString();
		// cadaRegistroDeuda.getDocGeneradorDeuda().getObligacion().getPersona().toString();
		// cadaRegistroDeuda.getDocGeneradorDeuda().getObligacion().toString();
		// if(cadaRegistroDeuda instanceof LiquidacionTasa){
		// for(AlicuotaLiquidada cadaAlicuota: ((LiquidacionTasa)cadaRegistroDeuda).getListaAlicuotasLiquidadas()){
		// cadaAlicuota.toString();
		// }
		// }
		// System.out.println("Cada reliquidacion: "+cadaRegistroDeuda);
		// }
		// entityManager.clear();
		return locListaRetorno;
	}

	/**
	 * Business method
	 * 
	 * @ejb.interface-method view-type= "local"
	 * @param pPersona
	 * @param pEstadoObligacion
	 * @return
	 * @throws Exception
	 */
	@Override
	@SuppressWarnings("unchecked")
	public java.util.List getListaObligacionesContribuyente(Persona pPersona, Obligacion.Estado pEstadoObligacion) throws Exception {

		List locListaObligacionesContribuyente = Criterio.getInstance(this.entityManager, Obligacion.class).add(Restriccion.IGUAL("persona", pPersona))
				.add(Restriccion.IGUAL("estado", pEstadoObligacion)).list();

		for(Object cadaObject : locListaObligacionesContribuyente) {
			cadaObject.toString();
		}

		return locListaObligacionesContribuyente;
	}

	@Override
	@SuppressWarnings("unchecked")
	public java.util.List getListaDocEspecializadosContribuyente(Persona pPersona) throws Exception {
		List<DocHabilitanteEspecializado> locListaDocHabEspecializados = Criterio.getInstance(this.entityManager, DocHabilitanteEspecializado.class)
				.add(Restriccion.IGUAL("obligacion.persona", pPersona)).list();

		for(DocHabilitanteEspecializado cadaDocHabEspecializado : locListaDocHabEspecializados) {
			cadaDocHabEspecializado.toString();
			cadaDocHabEspecializado.getObligacion().getPersona().toString();
			cadaDocHabEspecializado.getObligacion().toString();
			if(cadaDocHabEspecializado.getParcela() != null) {
				cadaDocHabEspecializado.getParcela().toString();
				if(cadaDocHabEspecializado.getParcela().getTituloPropiedad() != null) {
					cadaDocHabEspecializado.getParcela().getTituloPropiedad().toString();
				}
			}

		}
		return locListaDocHabEspecializados;
	}

	/**
	 * Business method
	 * 
	 * @ejb.interface-method view-type= "local"
	 * @return
	 * @throws Exception
	 */
	@Override
	public java.util.List<EstadoCuentaTemporalTGI> getListaEstadoCuentasTemporalTGI() throws Exception {
		List<EstadoCuentaTemporalTGI> locListaEstadoCuentasTGI = Criterio.getInstance(this.entityManager, EstadoCuentaTemporalTGI.class)
				.setProyeccion(Proyeccion.NEW(EstadoCuentaTemporalTGI.class, "idPersona", "idParcela", "tipoObligacion", "idRegistroDeuda"))
				.add(ar.trascender.criterio.clases.Grupo.POR("tipoObligacion")).add(ar.trascender.criterio.clases.Grupo.POR("idParcela"))
				.add(ar.trascender.criterio.clases.Grupo.POR("idPersona")).add(ar.trascender.criterio.clases.Grupo.POR("idRegistroDeuda")).setModoDebug(true).list();

		return locListaEstadoCuentasTGI;
	}

	/**
	 * Business method
	 * 
	 * @ejb.interface-method view-type= "local"
	 * @return
	 * @throws Exception
	 */
	@Override
	@SuppressWarnings("unchecked")
	public java.util.List<EstadoCuentaTemporalSHPS> getListaEstadoCuentasTemporalSHPS() throws Exception {
		List<EstadoCuentaTemporalSHPS> locListaEstadoCuentasSHPS = new ArrayList<EstadoCuentaTemporalSHPS>();
		locListaEstadoCuentasSHPS = Criterio.getInstance(this.entityManager, EstadoCuentaTemporalSHPS.class).list();

		if(!locListaEstadoCuentasSHPS.isEmpty()) {
			for(EstadoCuentaTemporalSHPS cadaEstadoCuentaTemporal : locListaEstadoCuentasSHPS) {
				// cadaEstadoCuentaTemporal.getParcela().toString();
				// cadaEstadoCuentaTemporal.getPersona().toString();
			}
		}
		return locListaEstadoCuentasSHPS;
	}

	/**
	 * Business method
	 * 
	 * @ejb.interface-method view-type= "local"
	 * @return
	 * @throws Exception
	 */
	@Override
	@SuppressWarnings("unchecked")
	public java.util.List<EstadoCuentaTemporalPFO> getListaEstadoCuentasTemporalPFO() throws Exception {
		List<EstadoCuentaTemporalPFO> locListaEstadoCuentasPFO = new ArrayList<EstadoCuentaTemporalPFO>();

		locListaEstadoCuentasPFO = Criterio.getInstance(this.entityManager, EstadoCuentaTemporalPFO.class).list();

		if(!locListaEstadoCuentasPFO.isEmpty()) {
			for(EstadoCuentaTemporalPFO cadaEstadoCuentaTemporal : locListaEstadoCuentasPFO) {
				// cadaEstadoCuentaTemporal.getParcela().toString();
				// cadaEstadoCuentaTemporal.getPersona().toString();
			}
		}
		return locListaEstadoCuentasPFO;
	}

	/**
	 * Business method
	 * 
	 * @ejb.interface-method view-type= "local"
	 * @return
	 * @throws Exception
	 */
	@Override
	@SuppressWarnings("unchecked")
	public java.util.List<EstadoCuentaTemporalOSP> getListaEstadoCuentasTemporalOSP() throws Exception {
		List<EstadoCuentaTemporalOSP> locListaEstadoCuentasOSP = new ArrayList<EstadoCuentaTemporalOSP>();
		locListaEstadoCuentasOSP = Criterio.getInstance(this.entityManager, EstadoCuentaTemporalOSP.class)
				.setProyeccion(Proyeccion.NEW(EstadoCuentaTemporalOSP.class, "idPersona", "idParcela", "tipoObligacion", "idRegistroDeuda"))
				.add(ar.trascender.criterio.clases.Grupo.POR("tipoObligacion")).add(ar.trascender.criterio.clases.Grupo.POR("idParcela"))
				.add(ar.trascender.criterio.clases.Grupo.POR("idPersona")).add(ar.trascender.criterio.clases.Grupo.POR("idRegistroDeuda")).list();
		return locListaEstadoCuentasOSP;
	}

	/**
	 * Devuelve una lista con las obligaciones TGI y/o OSP para X contribuyente
	 * 
	 * @param pPersona
	 * @param pTiposObligacion
	 * @return
	 * @throws Exception
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Set<Obligacion> getListaObligacionesContribuyentePorTasa(Persona pPersona, TipoObligacion... pTiposObligacion) throws Exception {
		Set locListaObligacionesRetorno = new HashSet();
		for(TipoObligacion cadaTipo : pTiposObligacion) {
			Class locClase = null;

			if(cadaTipo.getNombre().equals("OYSP"))
				locClase = DocumentoOSP.class;
			else if(cadaTipo.getNombre().equals("TGI"))
				locClase = DocumentoTGI.class;
			else
				throw new SaicException(85);

			List<Object[]> locListaCriterio = Criterio.getInstance(this.entityManager, locClase).add(Restriccion.IGUAL("obligacion.persona", pPersona))
					.add(Restriccion.IGUAL("obligacion.estado", Obligacion.Estado.CREADO)).setProyeccion(Proyeccion.PROP("obligacion")).list();

			for(Object cadaProperty : locListaCriterio) {
				// for(Object cadaLineaProperty : cadaProperty){
				// locListaObligacionesRetorno.add((Obligacion)cadaLineaProperty);
				// }
				locListaObligacionesRetorno.add(cadaProperty);
			}
		}

		return locListaObligacionesRetorno;
	}

	@Override
	public FiltroLiquidacionTasaRefer findListaLiquidacionTasaRefer(FiltroLiquidacionTasaRefer pFiltro) throws Exception {

		// Levantamos las parcelas
		Map<Long, Parcela> locMapaParcelas = new HashMap<Long, Parcela>();

		if(pFiltro.getParcela() != null) {
			locMapaParcelas.put(pFiltro.getParcela().getIdParcela(), pFiltro.getParcela());
		}
		if(pFiltro.getNumeroParcela() != null && !pFiltro.getListaTipoObligacion().get(0).getNombre().equals("SHPS")) {
			FiltroParcela locFiltro = new FiltroParcela();
			NomenclaturaCatastral locNomenclatura = new NomenclaturaCatastral();
			locNomenclatura.setNroParcela(pFiltro.getNumeroParcela());
			locFiltro.setNomenClaturaCatastral(locNomenclatura);
			locFiltro = businessParcela.findListaParcelas(locFiltro);
			//Si no hay parcelas no se sigue con la busqueda.
			if (locFiltro.getListaResultados().isEmpty()) {
				pFiltro.setListaResultados(new ArrayList<LiquidacionTasaRefer>());
				return pFiltro;
			}
			for(Parcela cadaParcela : locFiltro.getListaResultados()) {
				locMapaParcelas.put(cadaParcela.getIdParcela(), cadaParcela);
			}
		}

		// Levantamos las personas:
		Map<Long, Persona> locMapaPersonas = new HashMap<Long, Persona>();
		if(pFiltro.getPersona() != null) {
			locMapaPersonas.put(pFiltro.getPersona().getIdPersona(), pFiltro.getPersona());
		}
		if(pFiltro.getDni() != null) {
			FiltroPersonaFisica locFiltroFisica = new FiltroPersonaFisica();
			locFiltroFisica.setNumeroDocumento(pFiltro.getDni());
			for(PersonaFisica cadaPersona : businessPersona.findPersonaFisica(locFiltroFisica).getListaResultados()) {
				locMapaPersonas.put(cadaPersona.getIdPersona(), cadaPersona);
			}
		}

		try {
//			String consulta = "select p_sumar_distintos(array_agg(rd.id_registro_deuda::numeric), array_agg(monto_calculado::numeric)) as suma, "
			String consulta = "select sum(monto_calculado) as suma, "
					+ "doc_hab.id_parcela,"
					+ "every(rd.id_registro_cancelacion is not null) as cancelada, "
					+ "case when every(o.estado = 'ANULADO') and every(rd.estado <> 'PAGADA') then 'ANULADA' else rd.estado end, "
					+ "(select array_agg(distinct cast(rd.id_registro_deuda as numeric))) as ids_registros_deuda, "
					+ "cal.anio||' - '||per.nombre, rd.tipo||' ('||array_to_string(array_sort(array_agg(tipo_doc_hab_especializado::varchar)), '-')||')', "
					+ "cal.anio, "
					+ "case when every(doc_hab.tipo_doc_hab_especializado = 'SHPS') then doc_hab.numero_inscripcion else par.nro_parcela end, "
					+ "case when pf.apellido is not null then (pf.apellido || ', ' ||pf.nombre) "
					+ "else pj.razon_social end ||' ['||p.cuim||']' as persona, "
					+ "dom_par.domicilio_armado as domicilio_parcelario, "
					+ "case when ( bool_or(lt.fecha_apremio is not null) or bool_or(lt.fecha_notificacion is not null) ) then '*' else null end as aviso "
					
					+ "from registro_deuda rd inner join liquidacion_tasa lt on lt.id_registro_deuda = rd.id_registro_deuda "
					+ "inner join doc_generador_deuda doc on rd.id_doc_generador_deuda = doc.id_doc_generador_deuda " + "inner join obligacion o on doc.id_obligacion = o.id_obligacion "
					+ "inner join doc_hab_especializado doc_hab on doc_hab.id_obligacion = o.id_obligacion "
					+ "inner join cuota_liquidacion cuota on cuota.id_cuota_liquidacion = lt.id_cuota_liquidacion " + "inner join periodo per on per.id_periodo = cuota.id_periodo "
					+ "inner join calendario cal on cal.id_calendario = per.id_calendario " + "join persona p on p.id_persona = o.id_persona "
					+ "left join parcela par on doc_hab.id_parcela = par.id_parcela " + "left join persona_fisica pf on p.id_persona = pf.id_persona "
					+ "left join persona_juridica pj on p.id_persona = pj.id_persona "
					+ "left join domicilio dom_par on dom_par.id_domicilio = par.id_domicilio ";

			// /////////
//			ParametroSistemaString locParametroMostrador = this.getParametroSistemaString("OMITIR_MOSTRADOR");
			if(pFiltro.isOmitirMostrador()) {
				consulta += "left join atributo_dinamico atri on atri.id_entidad = par.id_parcela "
						+ "left join opcion_atri_dinam_listado opcion on opcion.id_opcion_atri_listado = atri.id_opcion "
						+ "where atri.id_recurso = -1724036473956407989 and opcion.valor = 'Calle' and (";
			} else {
				consulta += "where (";
			}

			if(pFiltro.getListaTipoObligacion() != null) {
				boolean flagOr = false;
				for(TipoObligacion cadaTipo : pFiltro.getListaTipoObligacion()) {
					consulta += (flagOr ? "or" : "") + " doc_hab.tipo_doc_hab_especializado like '" + (cadaTipo.getNombre().equals("OYSP") ? "OSP" : cadaTipo.getNombre()) + "' ";
					flagOr = true;
				}
			}
			consulta += ")";

			int ind = 1;

			if(pFiltro.getCuota() != null) {
				consulta += "and cuota.numero = ? ";
				ind++;
			}

			if(pFiltro.getPeriodo() != null) {
				consulta += "and per.numero = ? ";
				ind++;
			}

			if(pFiltro.getCalendario() != null) {
				consulta += "and cal.nombre = ? ";
				ind++;
			}

			if(pFiltro.getAnio() != null) {
				consulta += "and cal.anio = ? ";
				ind++;
			}
			if (pFiltro.getNumeroParcela() != null
					&& !pFiltro.getNumeroParcela().trim().isEmpty()
					&& pFiltro.getListaTipoObligacion().get(0).getNombre().equals("SHPS")) {
				consulta += "and doc_hab.numero_inscripcion like '" + pFiltro.getNumeroParcela()+"'";
			}
			if(!locMapaParcelas.isEmpty()) {
				String parcelas = "(";
				for(Iterator<Long> iterator = locMapaParcelas.keySet().iterator(); iterator.hasNext();) {
					Long cadaIdParcela = iterator.next();
					parcelas += cadaIdParcela;
					if(iterator.hasNext())
						parcelas += ",";
				}
				parcelas += ")";
				consulta += "and doc_hab.id_parcela in " + parcelas;
			}

			if(!locMapaPersonas.isEmpty()) {
				String personas = "(";
				for(Iterator<Long> iterator = locMapaPersonas.keySet().iterator(); iterator.hasNext();) {
					Long cadaIdPersona = iterator.next();
					personas += cadaIdPersona;
					if(iterator.hasNext())
						personas += ",";
				}
				personas += ")";
				consulta += "and o.id_persona in " + personas;
			}
			if(pFiltro.getTipoLiquidacion() != null) {
				consulta += "and rd.tipo = ? ";
				ind++;
			}
			if(pFiltro.getEstadoLiquidacion() != null) {
				if(pFiltro.getEstadoLiquidacion().name().contains("PAGADA")) {
					consulta += "and rd.estado like 'PAGADA%' ";
				} else if(pFiltro.getEstadoLiquidacion().name().equals("VENCIDA")) {
					// aca va la parte del registro cancelacion != null, despues va el having
					consulta += "and rd.id_registro_cancelacion is null and (rd.estado = 'VIGENTE' or rd.ESTADO = 'VENCIDA') ";
				} else if(pFiltro.getEstadoLiquidacion().name().equals("ANULADA")) {
					consulta += "and o.estado = 'ANULADO' ";
				} else {
					consulta += "and rd.estado = '" + pFiltro.getEstadoLiquidacion().name() + "'";
				}
			} else {
				consulta += "and rd.estado in ('PAGADA', 'VIGENTE', 'VENCIDA', 'REFINANCIADA') ";
			}

			consulta += " group by rd.estado,per.nombre, cuota.nombre, rd.tipo, par.nro_parcela, " 
					+ "doc_hab.id_parcela, cal.anio, cal.nombre, per.numero, dom_par.domicilio_armado, "
					+ "p.cuim, pf.apellido, pf.nombre, pj.razon_social, doc_hab.numero_inscripcion ";

			if(pFiltro.isNoAgrupar()) {
				consulta += ", rd.id_registro_deuda ";
			}
			consulta += " order by cal.anio desc, per.numero desc ";
			Connection con = datasource.getConnection();
			CallableStatement cs = con.prepareCall(consulta);
			if(pFiltro.getTipoLiquidacion() != null) {
				cs.setString(--ind, pFiltro.getTipoLiquidacion().name());
			}
			if(pFiltro.getAnio() != null) {
				cs.setInt(--ind, pFiltro.getAnio());
			}
			if(pFiltro.getCalendario() != null) {
				cs.setString(--ind, pFiltro.getCalendario().getNombre());
			}
			if(pFiltro.getPeriodo() != null) {
				cs.setInt(--ind, pFiltro.getPeriodo().getNumero());
			}
			if(pFiltro.getCuota() != null) {
				cs.setInt(--ind, pFiltro.getCuota().getNumero());
			}

			System.out.println("--------");
			System.out.println(consulta);
			ResultSet rs = cs.executeQuery();
			List<LiquidacionTasaRefer> locListaResultado = getListaLiquidacionTasaRefer(rs);

			boolean bandera = true;
			if(!locListaResultado.isEmpty() && bandera) {
				// Juntamos todos los ID's en una sola lista.
				String listadoIds = "";
				for(LiquidacionTasaRefer cadaLiquidacion : locListaResultado) {
					for(Long id : cadaLiquidacion.getIdsRegistrosDeuda()) {
						listadoIds += id + ",";
					}
				}
				// Quitamos la ultima coma.
				listadoIds = listadoIds.substring(0, listadoIds.length() - 1);

				// Calculamos los modificadores.
//				PreparedStatement psGetSumaModificadores = con.prepareStatement("select id_registro_deuda, sum(valor) from RELA_MODIF_LIQ_LIQ_T rela_mod "
//						+ "left join modificador_liquidacion mod_liq on mod_liq.id_modificador_liquidacion = rela_mod.id_modificador_liquidacion "
//						+ "where rela_mod.id_registro_deuda in (" + listadoIds + ") " + "group by id_registro_deuda");
//				Map<Long, Double> mapaValores = new HashMap<Long, Double>();
//				ResultSet rsValores = psGetSumaModificadores.executeQuery();
//				while(rsValores.next()) {
//					mapaValores.put(rsValores.getLong(1), rsValores.getDouble(2));
//				}
				PreparedStatement psGetFechaVencimientos = 
						con.prepareStatement("select id_registro_deuda, max(fecha) from rela_venc_liq_t rela_venc "
						+ "left join vencimiento venc on venc.id_vencimiento = rela_venc.id_vencimiento " 
						+ "where rela_venc.id_registro_deuda in (" + listadoIds + ") "
						+ "group by id_registro_deuda");
				Map<Long, Date> mapaFechas = new HashMap<Long, Date>();
				ResultSet rsFechas = psGetFechaVencimientos.executeQuery();
				while(rsFechas.next()) {
					mapaFechas.put(rsFechas.getLong(1), rsFechas.getDate(2));
				}
				for(Iterator<LiquidacionTasaRefer> iterator = locListaResultado.iterator(); iterator.hasNext();) {
					LiquidacionTasaRefer cadaLiquidacionTasaRefer = iterator.next();
//					Double valorModificadores = 0D;
//					for(Long idRegDeuda : cadaLiquidacionTasaRefer.getIdsRegistrosDeuda()) {
//						Double locValor = mapaValores.get(idRegDeuda);
//						if(locValor != null) {
//							valorModificadores += locValor;
//						}
//					}
//					cadaLiquidacionTasaRefer.setTotal(cadaLiquidacionTasaRefer.getTotal() + valorModificadores);
					// Quitamos no ceros
					if(pFiltro.isNoCero()) {
						if(cadaLiquidacionTasaRefer.getTotal().equals(0D)) {
							iterator.remove();
							continue;
						}
					}
					// Fecha de vencimiento, una porque son iguales para mas de un registro deuda.
					cadaLiquidacionTasaRefer.setMaxFechaVencimiento(mapaFechas.get(cadaLiquidacionTasaRefer.getIdsRegistrosDeuda().iterator().next()));
					// Si pide solo vencidas quitamos las otras.
					if(pFiltro.getEstadoLiquidacion() != null && pFiltro.getEstadoLiquidacion().equals(RegistroDeuda.EstadoRegistroDeuda.VENCIDA)
							&& !cadaLiquidacionTasaRefer.getEstado().equals("VENCIDA")) {
						iterator.remove();
						continue;
					}
					// //Parcelas
					// if (cadaLiquidacionTasaRefer.getIdParcela() != null
					// && cadaLiquidacionTasaRefer.getIdParcela().longValue() != 0l) {
					// Parcela locParcela = getParcelaNoNula(cadaLiquidacionTasaRefer.getIdParcela(), locMapaParcelas);
					// cadaLiquidacionTasaRefer.setParcela("N° " + locParcela.getNomenclaturaCatastral().getNroParcela());
					// }
					// Persona locPersona = getPersonaNoNula(cadaLiquidacionTasaRefer.getIdPersona(), locMapaPersonas);
					// cadaLiquidacionTasaRefer.setStringPersona(locPersona.getDenominacion() + "["+locPersona.getCuim()+"]");

				}
			}
			con.close();
			pFiltro.setListaResultados(locListaResultado);
			return pFiltro;
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	private Parcela getParcelaNoNula(Long idParcela, Map<Long, Parcela> mapaParcelas) {
		Parcela locParcela = mapaParcelas.get(idParcela);
		if(locParcela == null) {
			locParcela = entityManager.find(Parcela.class, idParcela);
			mapaParcelas.put(idParcela, locParcela);
		}
		return locParcela;
	}

	private Persona getPersonaNoNula(Long idPersona, Map<Long, Persona> mapaPersona) {
		Persona locPersona = mapaPersona.get(idPersona);
		if(locPersona == null) {
			locPersona = entityManager.find(Persona.class, idPersona);
			mapaPersona.put(idPersona, locPersona);
		}
		return locPersona;
	}

	private List<LiquidacionTasaRefer> getListaLiquidacionTasaRefer(ResultSet rs) throws Exception {
		List<LiquidacionTasaRefer> locListaResultado = new ArrayList<LiquidacionTasaRefer>();
		while(rs.next()) {
			LiquidacionTasaRefer locLiquidacion = new LiquidacionTasaRefer();
			locLiquidacion.setTotal(rs.getDouble(1));
			locLiquidacion.setIdParcela(rs.getLong(2));
			// locLiquidacion.setIdPersona(rs.getLong(3));
			locLiquidacion.setCancelada(rs.getBoolean(3));
			locLiquidacion.setEstado(rs.getString(4));
			Set<Long> listaIdsRegistroDeuda = new HashSet<Long>();
			BigDecimal[] arrayBg = (BigDecimal[]) rs.getArray(5).getArray();
			for(BigDecimal cadaBg : arrayBg) {
				listaIdsRegistroDeuda.add(cadaBg.longValue());
			}
			locLiquidacion.setIdsRegistrosDeuda(listaIdsRegistroDeuda);
			locLiquidacion.setPeriodo(rs.getString(6));
			locLiquidacion.setTipo(rs.getString(7));
			locLiquidacion.setAnio(rs.getInt(8));
			locLiquidacion.setParcela(rs.getString(9));
			locLiquidacion.setStringPersona(rs.getString(10));
			locLiquidacion.setDomicilioParcelario(rs.getString(11));
			locLiquidacion.setAviso(rs.getString(12));
			locListaResultado.add(locLiquidacion);
		}
		return locListaResultado;
	}

	@Override
	public LiquidacionTasaAgrupada inicializarLiquidacionTasaAgrupada(LiquidacionTasaRefer pLiquidacion) {
		LiquidacionTasaAgrupada locLiquidacionTasaAgrupada = new LiquidacionTasaAgrupada();
		for(Long id : pLiquidacion.getIdsRegistrosDeuda()) {
			LiquidacionTasa cadaLiquidacion = entityManager.find(LiquidacionTasa.class, id);
			cadaLiquidacion.getStringFormula();
			cadaLiquidacion.getStringModificadoresLiquidacion();
			cadaLiquidacion.getStringObligacion();
			cadaLiquidacion.getStringParametrosValuados();
			cadaLiquidacion.getStringParametrosValuadosAlicuota();
			cadaLiquidacion.getStringPeriodoLiquidado();
			cadaLiquidacion.getStringVencimientos();
			if(cadaLiquidacion.getDocGeneradorDeuda().getObligacion().getParcela() != null) {
				cadaLiquidacion.getDocGeneradorDeuda().getObligacion().getParcela().getDomicilioParcelario().toString();
			}
			cadaLiquidacion.getPersona().getDomicilioPostal().toString();
			cadaLiquidacion.getDocGeneradorDeuda().getObligacion().getDocumentoEspecializado().getListaAsocRegAlicuota().size();
			if(cadaLiquidacion.getRegistroCancelacion() != null) {
				cadaLiquidacion.getRegistroCancelacion().toString();
			}
			locLiquidacionTasaAgrupada.getListaLiquidacionesTasa().add(cadaLiquidacion);
		}
		return locLiquidacionTasaAgrupada;
	}

	private ParametroSistemaString getParametroSistemaString(String pNombre) {
		return Criterio.getInstance(entityManager, ParametroSistemaString.class).add(Restriccion.IGUAL("nombre", pNombre)).setModoDebug(true).uniqueResult();
	}

}