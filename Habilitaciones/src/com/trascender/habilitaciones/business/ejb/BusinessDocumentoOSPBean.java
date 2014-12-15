
package com.trascender.habilitaciones.business.ejb;

import java.rmi.RemoteException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.CreateException;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ar.trascender.criterio.clases.Criterio;
import ar.trascender.criterio.clases.Orden;
import ar.trascender.criterio.clases.Proyeccion;
import ar.trascender.criterio.clases.Restriccion;

import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.framework.business.interfaces.BusinessParametroLocal;
import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
import com.trascender.framework.recurso.transients.AtributoConsultable.Tipo;
import com.trascender.framework.recurso.transients.Grupo;
import com.trascender.framework.recurso.transients.Recurso;
import com.trascender.framework.util.BusquedaPorLog;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.framework.util.TrascenderEnverListener;
import com.trascender.habilitaciones.business.interfaces.BusinessDocumentoOSPLocal;
import com.trascender.habilitaciones.exception.HabilitacionesException;
import com.trascender.habilitaciones.recurso.filtros.FiltroConsumoBasico;
import com.trascender.habilitaciones.recurso.filtros.FiltroObligacionOSP;
import com.trascender.habilitaciones.recurso.persistent.DocHabilitanteEspecializado;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.RegistroValuado;
import com.trascender.habilitaciones.recurso.persistent.ValorMedidor;
import com.trascender.habilitaciones.recurso.persistent.osp.AsocServicioOsp;
import com.trascender.habilitaciones.recurso.persistent.osp.ConsumoBasico;
import com.trascender.habilitaciones.recurso.persistent.osp.DocumentoOSP;
import com.trascender.habilitaciones.recurso.persistent.osp.ServicioOSP;

@Stateless(name = "ejb/BusinessDocumentoOSPLocal")
public class BusinessDocumentoOSPBean implements BusinessDocumentoOSPLocal {

	private static final long serialVersionUID = -4742192133238686517L;

	static {
		Grupo grupo = new Grupo();
		grupo.setId(serialVersionUID);
		grupo.setNombre("HAB|Adm. Obligación OSP");

		Recurso documentoOSP = new Recurso();
		documentoOSP.setNombre("Obligación OSP");
		documentoOSP.setIdRecurso(DocumentoOSP.serialVersionUID);
		documentoOSP.setAtributosConsultables("Nº Cuenta", "numeroCuenta", "Contribuyente", "persona", "Parcela", "parcela", "Servicios", "servicios", Tipo.TEXTO_LARGO, "Estado",
				"estado");
		documentoOSP.setClase(DocumentoOSP.class);
		grupo.getListaRecursos().add(documentoOSP);

		Recurso servicioOSP = new Recurso();
		servicioOSP.setIdRecurso(ServicioOSP.serialVersionUID);
		servicioOSP.setNombre("Códigos de Servicio de OySP");
		servicioOSP.setAtributosConsultables("Código", "codigo", "Descripción", "nombre", Tipo.TEXTO_LARGO, "Valor mínimo", "valor", Tipo.MONTO, "Medido", "medido");
		servicioOSP.setClase(ServicioOSP.class);
		grupo.getListaRecursos().add(servicioOSP);

		Recurso consumoBasico = new Recurso();
		consumoBasico.setNombre("Adm. de Consumos Básicos");
		consumoBasico.setIdRecurso(ConsumoBasico.serialVersionUID);
		consumoBasico.setAtributosConsultables("Sup. Mejoras Mínimo", "superficieMejorasMinimo", "Sup. Mejoras Máximo", "superficieMejorasMaximo", "Consumo Inicial", "consumoInicial",
				"Cons. por Excedente", "consumoPorExcedente");
		consumoBasico.setClase(ConsumoBasico.class);
		grupo.getListaRecursos().add(consumoBasico);

		SecurityMgr.getInstance().addGrupo(grupo);
	}

	@EJB
	private BusinessParametroLocal businessParametro;

	@PersistenceContext(name = "vipians")
	private EntityManager entityManager;

	@PostConstruct
	public void asd() {

	}

	public BusinessDocumentoOSPBean() {
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
	 * Agrega un documentoOSP
	 * 
	 * @param pDocumentoOSP
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public com.trascender.habilitaciones.recurso.persistent.osp.DocumentoOSP addDocumentoOSP(com.trascender.habilitaciones.recurso.persistent.osp.DocumentoOSP pDocumentoOSP)
			throws Exception {

		this.validarDocumentoOSP(pDocumentoOSP);

		TrascenderEnverListener.setValoresEnAuditoriaBean(pDocumentoOSP);
		entityManager.merge(pDocumentoOSP);
		this.setConsumoBasico(pDocumentoOSP);

		// Flush para que funcionen los atributos dinamicos.
		this.entityManager.flush();

		this.actualizarCantidadPropiedades(pDocumentoOSP);
		return pDocumentoOSP;
	}

	/**
	 * Valida que no aya un documento OSP con un mismo numero de cuenta y subCuenta
	 * 
	 * @param pDocumentoOSP
	 * @throws HabilitacionesException
	 */
	private void validarDocumentoOSP(DocumentoOSP pDocumentoOSP) throws Exception {

		Criterio locCriterio = Criterio.getInstance(this.entityManager, DocumentoOSP.class).add(Restriccion.IGUAL("numeroCuenta", pDocumentoOSP.getNumeroCuenta()))
				.add(Restriccion.IGUAL("numeroSubCuenta", pDocumentoOSP.getNumeroSubCuenta())).add(Restriccion.IGUAL("estado", DocHabilitanteEspecializado.Estado.ACTIVO))
				.setProyeccion(Proyeccion.COUNT());

		if(pDocumentoOSP.getIdDocHabilitanteEspecializado() != -1) {
			locCriterio.add(Restriccion.DISTINTO("idDocHabilitanteEspecializado", pDocumentoOSP.getIdDocHabilitanteEspecializado()));
		}

		if((Long) locCriterio.uniqueResult() > 0) {
			throw new HabilitacionesException(24);
		}

		// if (pDocumentoOSP.getCodigoMedidor() != null){
		// locCriterio = Criterio
		// .getInstance(this.entityManager, DocumentoOSP.class)
		// .add(Restriccion.NOT(Restriccion.NULO("codigoMedidor")))
		// .add(Restriccion.IGUAL("codigoMedidor", pDocumentoOSP.getCodigoMedidor()))
		// .add(Restriccion.DISTINTO("idDocHabilitanteEspecializado", pDocumentoOSP.getIdDocHabilitanteEspecializado()))
		// .add(Restriccion.DISTINTO("estado", DocHabilitanteEspecializado.Estado.INACTIVO))
		// .setModoDebug(true)
		// .setProyeccion(Proyeccion.COUNT());
		//
		// if ((Long) locCriterio.uniqueResult() > 0) {
		// throw new HabilitacionesException(25);
		// }
		// }
	}

	/**
	 * Actualiza los datos del documento OSP
	 * 
	 * @param pDocumentoOSP
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public com.trascender.habilitaciones.recurso.persistent.osp.DocumentoOSP updateDocumentoOSP(com.trascender.habilitaciones.recurso.persistent.osp.DocumentoOSP pDocumentoOSP)
			throws Exception {

		this.validarDocumentoOSP(pDocumentoOSP);

		this.validarAsocServicioOsp(pDocumentoOSP);

		this.entityManager.merge(pDocumentoOSP);

		try {// FIXME dejo esto afk despues arreglar
				// pDocumentoOSP.getObligacion().toString();
				// pDocumentoOSP.getObligacion().getPersona().toString();
				// pDocumentoOSP.getObligacion().getPersona().getDomicilioPostal().toString();
				// com.trascender.habilitaciones.util.Util.getInstance(this.entityManager).deleteDomicilioExcluyente(locDocOspViejo,
				// pDocumentoOSP);
		} catch(Exception e) {
			// no necesito hacer nada
		}

		this.setConsumoBasico(pDocumentoOSP);
		this.actualizarCantidadPropiedades(pDocumentoOSP);
		return pDocumentoOSP;
	}

	/**
	 * Setea el estado de la obligación en terminado
	 * 
	 * @param pDocumentoOSP
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public void deleteDocumentoOSP(com.trascender.habilitaciones.recurso.persistent.osp.DocumentoOSP pDocumentoOSP) throws Exception {
		this.entityManager.merge(pDocumentoOSP);
		pDocumentoOSP.getObligacion().terminar();
	}

	/**
	 * Recupera el listado de documentos OSP asociados con la persona
	 * 
	 * @param pPersona
	 *            persona asociada
	 * @return Listado de documentos habilitantes
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	@SuppressWarnings("unchecked")
	public java.util.List findListaDocumentosOSP(com.trascender.framework.recurso.persistent.Persona pPersona, Parcela pParcela, Integer pNumeroCuenta) throws Exception {

		Criterio locCriterio = Criterio.getInstance(this.entityManager, DocumentoOSP.class).setModoDebug(true).crearAlias("obligacion", "locObligacion")
				.add(Restriccion.IGUAL("locObligacion.persona", pPersona)).add(Restriccion.IGUAL("numeroCuenta", pNumeroCuenta)).add(Restriccion.IGUAL("parcela", pParcela));

		List<DocumentoOSP> locListaResultado = locCriterio.list();

		for(DocumentoOSP cadaDocumento : locListaResultado) {
			cadaDocumento.getObligacion().toString();
			cadaDocumento.getObligacion().getPersona().toString();
			cadaDocumento.getParcela().toString();
			cadaDocumento.getListaAsocRegAlicuota().size();
			this.setConsumoBasico(cadaDocumento);
		}

		return locListaResultado;
	}

	/**
	 * Recupera el listado de obligaciones cuyos documentos son del tipo DocumentoOSP y cumplen con los parámetros ingresados
	 * 
	 * @param pPersona
	 * @param pNumeroRegistro
	 * @param pNumeroCuenta
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	@SuppressWarnings("unchecked")
	public FiltroObligacionOSP findListaObligacionesOSP(FiltroObligacionOSP pFiltro) throws Exception {

		Criterio locCriterio = Criterio.getInstance(this.entityManager, Obligacion.class).setModoDebug(true).crearAlias("documentoEspecializado", "locDocEsp")
				.crearAlias("locDocEsp.parcela", "locParcela").add(Restriccion.JPQL("TYPE (locDocEsp) = ".concat(DocumentoOSP.class.getSimpleName())));

		AtributoDinamico.addRestriccionesCriterio(locCriterio, DocumentoOSP.serialVersionUID, "idDocHabilitanteEspecializado", "locDocEsp", pFiltro.getListaAtributosDinamicos());

		locCriterio.add(Restriccion.IGUAL("locDocEsp.numeroCuenta", pFiltro.getNumeroCuenta())).add(Restriccion.IGUAL("estado", pFiltro.getEstado()));

		if(pFiltro.getPoseeExenciones() != null) {
			if(!pFiltro.getPoseeExenciones()) {
				locCriterio.add(Restriccion.ESTA_VACIO("listaRegistrosExencion"));
			} else {
				locCriterio.add(Restriccion.NOT(Restriccion.ESTA_VACIO("listaRegistrosExencion")));
			}
		}

		if(pFiltro.getPersona() != null) {
			locCriterio.add(Restriccion.IGUAL("persona", pFiltro.getPersona()));
		} else if(pFiltro.getListaIdPersonas() != null) {
			locCriterio.add(Restriccion.EN("persona.id", pFiltro.getListaIdPersonas()));
		}

		if(pFiltro.getParcela() != null) {
			locCriterio.add(Restriccion.JPQL("EXISTS (SELECT d FROM DocHabilitanteEspecializado d WHERE d.obligacion = e AND d.parcela.idParcela = ".concat(pFiltro.getParcela()
					.getIdParcela() + ")")));
		}

		BusquedaPorLog.addRestriccionesCriterio(locCriterio, DocumentoOSP.serialVersionUID, "idDocHabilitanteEspecializado", pFiltro.getListaBusquedaPorLogs(), "locDocEsp");

		pFiltro.procesarYListar(locCriterio);

		for(Obligacion cadaObligacion : pFiltro.getListaResultados()) {
			cadaObligacion.toString();
			cadaObligacion.getPersona().toString();
			cadaObligacion.getDocumentoEspecializado().toString();
			cadaObligacion.getDocumentoEspecializado().getParcela().toString();
			cadaObligacion.getDocumentoEspecializado().getListaAsocRegAlicuota().size();
		}
		return pFiltro;
	}

	/**
	 * Recupera el documento habilitante asociado con la obligación
	 * 
	 * @param pObligacion
	 *            obligación asociada
	 * @return DocumentoOSP asociado con la obligación
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public com.trascender.habilitaciones.recurso.persistent.osp.DocumentoOSP getDocumentoOSP(com.trascender.habilitaciones.recurso.persistent.Obligacion pObligacion) throws Exception {

		DocumentoOSP locDocumentoOSP = Criterio.getInstance(this.entityManager, DocumentoOSP.class).add(Restriccion.IGUAL("obligacion", pObligacion)).uniqueResult();

		locDocumentoOSP.toString();
		locDocumentoOSP.getParcela().toString();
		locDocumentoOSP.getDomicilio().getDomicilioCompleto().toString();
		if(locDocumentoOSP.getListaRegAlicuotas() != null) {
			locDocumentoOSP.getListaRegAlicuotas().size();
		}

		locDocumentoOSP.getListaAtributosDinamicos().size();
		locDocumentoOSP.getListaLogsAuditoria().size();
		this.setConsumoBasico(locDocumentoOSP);

		return locDocumentoOSP;
	}

	/**
	 * Recupera el documento habilitante OSP asociado a un Valor Medidor
	 * 
	 * @param pIdDocHabEsp
	 *            id del Documento Habilitante Especializado asociado a Valor Medidor
	 * @return DocumentoOSP asociado con Valor Medidor
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public DocumentoOSP getDocumentoOSPPorId(long pIdDocHabEsp) throws Exception {
		DocumentoOSP locDocumentoOSP = (DocumentoOSP) Criterio.getInstance(this.entityManager, DocumentoOSP.class).add(Restriccion.IGUAL("idDocHabilitanteEspecializado", pIdDocHabEsp))
				.uniqueResult();

		if(locDocumentoOSP != null) {
			locDocumentoOSP.toString();
			locDocumentoOSP.getObligacion().getPersona().toString();
		}

		if(!locDocumentoOSP.getListaRegistrosValuados().isEmpty()) {
			if(!locDocumentoOSP.getListaRegistrosValuados().isEmpty()) {
				for(RegistroValuado cadaRegistroValuado : locDocumentoOSP.getListaRegistrosValuados()) {
					ValorMedidor locValorMedidor = (ValorMedidor) cadaRegistroValuado;
					locValorMedidor.getCuotaLiquidacion().toString();
					if(locValorMedidor.getServicioOSP().isMedido()) {
						locValorMedidor.getCodigoMedidor();
						locValorMedidor.getLectura().toString();
						if(locValorMedidor.getStringLecturaAnterior() != null) {
							locValorMedidor.getStringLecturaAnterior().toString();
						}
					}
					locValorMedidor.getMontoImponible().toString();
					locValorMedidor.toString();
				}
				locDocumentoOSP.getListaRegistrosValuados().toString();

			}
			locDocumentoOSP.getParcela().toString();
			locDocumentoOSP.getDomicilio().toString();

			locDocumentoOSP.getListaAtributosDinamicos().size();

			if(locDocumentoOSP.getObligacion() != null) {
				locDocumentoOSP.getObligacion().toString();
				locDocumentoOSP.getObligacion().getPersona().toString();
			}
		}

		this.setConsumoBasico(locDocumentoOSP);

		return locDocumentoOSP;
	}

	/**
	 * prepara el consumo básico de un documento osp
	 * 
	 * @param pDocumentoOSP
	 */
	private void setConsumoBasico(DocumentoOSP pDocumentoOSP) {

		Double locValor = pDocumentoOSP.getParcela().getSuperficieMejoras();

		ConsumoBasico locConsumoBasico = (ConsumoBasico) Criterio.getInstance(this.entityManager, ConsumoBasico.class).add(Restriccion.IGUAL("activo", new Boolean(true)))
				.add(Restriccion.MAYOR("superficieMejorasMinimo", locValor)).add(Restriccion.MENOR("superficieMejorasMaximo", locValor)).uniqueResult();

		if(locConsumoBasico != null) {
			locConsumoBasico.toString();
			pDocumentoOSP.setConsumoBasico(locConsumoBasico);
		}
	}

	/**
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	public com.trascender.habilitaciones.recurso.persistent.osp.ConsumoBasico addConsumoBasico(com.trascender.habilitaciones.recurso.persistent.osp.ConsumoBasico pConsumoBasico)
			throws Exception {

		this.validarConsumoBasico(pConsumoBasico);

		this.entityManager.persist(pConsumoBasico);
		this.entityManager.flush();
		this.entityManager.refresh(pConsumoBasico);

		return pConsumoBasico;
	}

	/**
	 * Valida que los consumos maximos no sean incosistentes y que los rangos sean validos.
	 * 
	 * @param pConsumoBasico
	 * @throws Exception
	 */
	private void validarConsumoBasico(ConsumoBasico pConsumoBasico) throws Exception {

		if(pConsumoBasico == null) {
			throw new HabilitacionesException(84);
		}

		if((pConsumoBasico.getConsumoInicial() < 0) || (pConsumoBasico.getConsumoPorExcedente() < 0) || (pConsumoBasico.getSuperficieMejorasMaximo() < 0)
				|| (pConsumoBasico.getSuperficieMejorasMinimo() < 0)) {
			throw new HabilitacionesException(82);
		}

		this.validarTablaConsumoBasico();
	}

	/**
	 * Valida los rangos del consumo básico
	 * 
	 * @param consumoBasico
	 */
	@SuppressWarnings("unchecked")
	private void validarTablaConsumoBasico() throws Exception {
		List locListaConsumosBasicos = Criterio.getInstance(this.entityManager, ConsumoBasico.class).add(Restriccion.IGUAL("activo", new Boolean(true)))
				.add(Orden.ASC("superficieMejorasMinimo")).list();

		ConsumoBasico locConsumoBasicoAnterior = null;

		for(Object cadaObj : locListaConsumosBasicos) {
			ConsumoBasico cadaConsumoBasico = (ConsumoBasico) cadaObj;
			cadaConsumoBasico.toString();
			if(locConsumoBasicoAnterior != null) {
				if(locConsumoBasicoAnterior.getSuperficieMejorasMaximo() >= cadaConsumoBasico.getSuperficieMejorasMinimo()) {
					throw new HabilitacionesException(85);
				}
			}
			locConsumoBasicoAnterior = cadaConsumoBasico;
		}
	}

	/**
	 * 
	 * @param pConsumoBasico
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public com.trascender.habilitaciones.recurso.persistent.osp.ConsumoBasico updateConsumoBasico(com.trascender.habilitaciones.recurso.persistent.osp.ConsumoBasico pConsumoBasico)
			throws Exception {

		if(pConsumoBasico.isActivo())
			this.validarConsumoBasico(pConsumoBasico);

		this.entityManager.merge(pConsumoBasico);

		return pConsumoBasico;
	}

	/**
	 * 
	 * @param pConsumoBasico
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public void deleteConsumoBasico(com.trascender.habilitaciones.recurso.persistent.osp.ConsumoBasico pConsumoBasico) throws Exception {

		if(!pConsumoBasico.isActivo())
			this.updateConsumoBasico(pConsumoBasico);
	}

	/**
	 * 
	 * @param pDocumentoOSP
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public com.trascender.habilitaciones.recurso.persistent.osp.ConsumoBasico getConsumoBasico(com.trascender.habilitaciones.recurso.persistent.osp.DocumentoOSP pDocumentoOSP)
			throws Exception {
		this.setConsumoBasico(pDocumentoOSP);
		return pDocumentoOSP.getConsumoBasico();
	}

	public FiltroConsumoBasico getListaConsumosBasicos(FiltroConsumoBasico pFiltro) throws Exception {
		Criterio locCriterio = Criterio.getInstance(this.entityManager, ConsumoBasico.class).add(Restriccion.IGUAL("activo", new Boolean(true)));

		pFiltro.procesarYListar(locCriterio);
		return pFiltro;
	}

	private void validarAsocServicioOsp(DocumentoOSP pDocOsp) {

		Criterio locCriterio = Criterio.getInstance(this.entityManager, AsocServicioOsp.class).add(Restriccion.IGUAL("docHabilitanteEspecializado", pDocOsp))
				.add(Restriccion.NOT(Restriccion.EN("e", pDocOsp.getListaAsocRegAlicuota())));

		System.out.println("Lista criterio: " + locCriterio.list());
	}

	/**
	 * Devuelve el último numero de cuenta OSP ocupado + 1
	 */
	public Integer getSugerenciaNumeroCuenta() {
		Criterio locCriterio = Criterio.getInstance(this.entityManager, DocumentoOSP.class).add(Restriccion.MENOR("numeroCuenta", 89999)).setProyeccion(Proyeccion.MAX("numeroCuenta"));

		Integer numero = locCriterio.uniqueResult();
		if(numero == null)
			numero = 0;
		return numero + 1;
	}

	/**
	 * Actualiza la cantidad de propiedades que una persona posee
	 * 
	 * @param pPersona
	 * @param pPropiedad
	 *            cantidad de propiedades que agrega
	 * @throws Exception
	 */
	private void actualizarCantidadPropiedades(DocumentoOSP pDocumento) throws Exception {

		Long cantidad = Criterio.getInstance(this.entityManager, Obligacion.class).add(Restriccion.IGUAL("persona", pDocumento.getObligacion().getPersona()))
				.add(ar.trascender.criterio.clases.Grupo.POR("documentoEspecializado.parcela.idParcela")).setProyeccion(Proyeccion.COUNT()).uniqueResult();

		pDocumento.getObligacion().getPersona().setCantidadPropiedades(cantidad.intValue());

		entityManager.merge(pDocumento.getObligacion().getPersona());
	}
}
