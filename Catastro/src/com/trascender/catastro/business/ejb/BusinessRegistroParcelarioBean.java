
package com.trascender.catastro.business.ejb;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.ejb.CreateException;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.PersistenceContext;

import ar.trascender.criterio.clases.Criterio;
import ar.trascender.criterio.clases.Proyeccion;
import ar.trascender.criterio.clases.Restriccion;
import ar.trascender.criterio.enums.Posicion;

import com.trascender.catastro.business.interfaces.BusinessCodigosCatastralesLocal;
import com.trascender.catastro.business.interfaces.BusinessRegistroParcelarioLocal;
import com.trascender.catastro.business.interfaces.BusinessRegistroPropiedadLocal;
import com.trascender.catastro.exception.CatastroException;
import com.trascender.catastro.exception.ValorBasicoPorMejoraException;
import com.trascender.catastro.recurso.filtros.FiltroDeclaracionJurada;
import com.trascender.catastro.recurso.filtros.FiltroParcela;
import com.trascender.catastro.recurso.filtros.FiltroPlanoConstruccion;
import com.trascender.catastro.recurso.filtros.FiltroPlanoMensura;
import com.trascender.catastro.recurso.filtros.FiltroSubParcela;
import com.trascender.catastro.recurso.persistent.AsociacionParcela;
import com.trascender.catastro.recurso.persistent.Calle;
import com.trascender.catastro.recurso.persistent.CoeficienteDepreciacion;
import com.trascender.catastro.recurso.persistent.CoeficienteDepreciacion.EstadoCoeficiente;
import com.trascender.catastro.recurso.persistent.Cuadra;
import com.trascender.catastro.recurso.persistent.DeclaracionJurada;
import com.trascender.catastro.recurso.persistent.FirmantePlanoConstruccion;
import com.trascender.catastro.recurso.persistent.Manzana;
import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.catastro.recurso.persistent.Parcela.Estado;
import com.trascender.catastro.recurso.persistent.ParcelaPorCuadra;
import com.trascender.catastro.recurso.persistent.PlanoConstruccion;
import com.trascender.catastro.recurso.persistent.PlanoMensura;
import com.trascender.catastro.recurso.persistent.Planta;
import com.trascender.catastro.recurso.persistent.RegistroMejora;
import com.trascender.catastro.recurso.persistent.RegistroPropietario;
import com.trascender.catastro.recurso.persistent.SubParcela;
import com.trascender.catastro.recurso.persistent.TituloPropiedad;
import com.trascender.catastro.recurso.persistent.TituloPropiedadParcelario;
import com.trascender.catastro.recurso.persistent.ValorBasicoMejora;
import com.trascender.catastro.recurso.persistent.VolanteCatastral;
import com.trascender.catastro.recurso.rfr.DocHabEspecializadoRfr;
import com.trascender.framework.business.interfaces.BusinessParametroLocal;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
import com.trascender.framework.recurso.persistent.referencia.ObligacionRfr;
import com.trascender.framework.recurso.transients.AtributoConsultable.Tipo;
import com.trascender.framework.recurso.transients.AuxIdEntidad;
import com.trascender.framework.recurso.transients.Grupo;
import com.trascender.framework.recurso.transients.Recurso;
import com.trascender.framework.util.BusquedaPorLog;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.framework.util.TrascenderEnverListener;

/**
 * @ejb.bean name="BusinessRegistroParcelario" display-name="Name for BusinessRegistroParcelario" description="Description for BusinessRegistroParcelario"
 *           jndi-name="ejb/BusinessRegistroParcelario" type="Stateless" view-type="local"
 */
@Stateless(name = "BusinessRegistroParcelarioLocal")
public class BusinessRegistroParcelarioBean implements BusinessRegistroParcelarioLocal {
	static {
		Grupo grupo = new Grupo();
		Recurso parcela = new Recurso();
		Recurso declaracionJurada = new Recurso();
		Recurso registroMejora = new Recurso();
		Recurso planoConstruccion = new Recurso();
		Recurso planoMensura = new Recurso();
		Recurso volanteCatastral = new Recurso();
		Recurso subParcela = new Recurso();

		parcela.setIdRecurso(Parcela.serialVersionUID);
		parcela.setNombre("Parcela");
		parcela.setAtributosConsultables("Titulares", "stringListaPropietarios", Tipo.TEXTO_LARGO, "Nº de Partida Provincial", "nroPartidaProvincial", "Nº de Parcela", "nroParcela",
				"Calle", "calle", "Altura", "altura");
		parcela.setClase(Parcela.class);

		declaracionJurada.setIdRecurso(DeclaracionJurada.serialVersionUID);
		declaracionJurada.setNombre("Declaración Jurada");
		declaracionJurada.setAtributosConsultables("Código", "numero", "Fecha de Inscripción", "fechaInscripcion", Tipo.FECHA, "Parcela", "parcela");
		declaracionJurada.setClase(DeclaracionJurada.class);

		registroMejora.setIdRecurso(RegistroMejora.serialVersionUID);
		registroMejora.setNombre("Registro de Mejora");
		registroMejora.setClase(RegistroMejora.class);

		planoConstruccion.setIdRecurso(PlanoConstruccion.serialVersionUID);
		planoConstruccion.setNombre("Plano de Construcción");
		planoConstruccion.setAtributosConsultables("Nº Plano Construcción", "plano", "Fecha de Inscripción", "fechaInscripcion", Tipo.FECHA, "Parcela", "parcela", "Estado", "estado");
		planoConstruccion.setClase(PlanoConstruccion.class);

		planoMensura.setIdRecurso(PlanoMensura.serialVersionUID);
		planoMensura.setNombre("Plano de Mensura");
		planoMensura.setAtributosConsultables("Nº Plano Mensura", "plano", "Fecha de Inscripción", "fechaInscripcion", Tipo.FECHA, "Parcela", "parcela");
		planoMensura.setClase(PlanoMensura.class);

		volanteCatastral.setIdRecurso(VolanteCatastral.serialVersionUID);
		volanteCatastral.setNombre("Volante Catastral");
		volanteCatastral.setClase(VolanteCatastral.class);

		subParcela.setIdRecurso(SubParcela.serialVersionUID);
		subParcela.setNombre("Sub-Parcela");
		subParcela.setAtributosConsultables("Titular", "titular", "Superficie", "superficie", "Parcela padre", "padre");
		subParcela.setClase(SubParcela.class);

		grupo.setNombre(BusinessRegistroParcelarioBean.NAME);
		grupo.setId(BusinessRegistroParcelarioBean.serialVersionUID);

		grupo.getListaRecursos().add(parcela);
		grupo.getListaRecursos().add(subParcela);
		grupo.getListaRecursos().add(declaracionJurada);
		grupo.getListaRecursos().add(registroMejora);
		grupo.getListaRecursos().add(planoConstruccion);
		grupo.getListaRecursos().add(planoMensura);
		grupo.getListaRecursos().add(volanteCatastral);

		SecurityMgr.getInstance().addGrupo(grupo);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -7532924942226754459L;

	@PersistenceContext(name = "Vipians")
	private EntityManager entityManager;

	public static final String NAME = "CAT|Adm. de Registro Parcelario";

	public BusinessRegistroParcelarioBean() {
		super();
	}

	@EJB
	private BusinessCodigosCatastralesLocal businessCodigosCatastrales;

	@EJB
	private BusinessParametroLocal businessParametro;

	@EJB
	private BusinessRegistroPropiedadLocal businessRegistroPropietario;

	/**
	 * Default create method
	 * 
	 * @throws CreateException
	 * @ejb.create-method
	 */
	public void ejbCreate() throws CreateException {
	}

	public void setSessionContext(SessionContext ctx) throws EJBException, RemoteException {
	}

	public void ejbRemove() throws EJBException, RemoteException {
	}

	public void ejbActivate() throws EJBException, RemoteException {

	}

	public void ejbPassivate() throws EJBException, RemoteException {
	}

	/**
	 * Agrega una parcela
	 * 
	 * @param parcela
	 *            que se desea agregar
	 * @return pParcela parcela actualizada con el id generado
	 * @throws Exception
	 */
	public com.trascender.catastro.recurso.persistent.Parcela addParcela(com.trascender.catastro.recurso.persistent.Parcela pParcela) throws Exception {

		this.validarParcela(pParcela);
		this.validarTituloPropiedad(pParcela.getTituloPropiedad());

		if(pParcela.getManzana() != null) {

			List<ParcelaPorCuadra> listado = new ArrayList<ParcelaPorCuadra>(pParcela.getListaParcelasPorCuadra().size());

			for(ParcelaPorCuadra cadaParcela : pParcela.getListaParcelasPorCuadra()) {
				cadaParcela.setParcela(pParcela);
				if(cadaParcela.getMetrosPorCuadra().equals(0D)) {
					listado.add(cadaParcela);
				}
			}
			pParcela.getListaParcelasPorCuadra().removeAll(listado);
		} else {
			if(pParcela.getListaParcelasPorCuadra() != null) {
				pParcela.getListaParcelasPorCuadra().clear();
			}
		}

		TrascenderEnverListener.setValoresEnAuditoriaBean(pParcela);

		pParcela = this.entityManager.merge(pParcela);

		pParcela.postPersist();
		entityManager.flush();
		return pParcela;

	}

	/**
	 * Valida que una parcela no tenga el mismo nro de partida provincial
	 * 
	 * @param pParcela
	 */
	private void validarParcela(Parcela pParcela) throws Exception {
		// Valido Nullidades
		if(pParcela == null) {
			throw new CatastroException(70);
		}

		if(this.isParcelaRepetidaEnManzana(pParcela, pParcela.getManzana())) {
			throw new CatastroException(45);
		}

		if(pParcela.getListaRegistrosMejoraActivos().size() > 0 && pParcela.getPlanta().getTipoEdificacion().equals(Planta.Edificacion.BALDIO)) {
			throw new CatastroException(108);
		}

		if(pParcela.isSubParcelada()) {
			// Double locSupAnterior = (Double) Criterio.getInstance(this.entityManager, Parcela.class)
			// .add(Restriccion.IGUAL("idParcela", pParcela.getIdParcela()))
			// .setProyeccion(Proyeccion.PROP("superficie"))
			// .uniqueResult();

			if(pParcela.getSuperficie() < pParcela.getSupercieSubParcelas()) {
				throw new CatastroException(719);
			}
		}

		// ValidadorDinamicoCore.getInstance(this.entityManager, Parcela.class).empezarValidacion(pParcela);

		for(RegistroPropietario cadaRegistro : pParcela.getTituloPropiedad().getListaRegistrosPropietarios()) {
			cadaRegistro.setTituloPropiedad(pParcela.getTituloPropiedad());
		}
		
		for (PlanoConstruccion cadaPlano : pParcela.getListaPlanosConstruccion()) {
			for (FirmantePlanoConstruccion cadaFirmante : cadaPlano.getListaFirmantePlanoConstruccion()) {
				cadaFirmante.setPlanoConstruccion(cadaPlano);
			}
		}

	}

	private void validarTituloPropiedad(TituloPropiedad pTituloPropiedad) throws Exception {
		for(RegistroPropietario cadaRegistroPropietario : pTituloPropiedad.getListaRegistrosPropietarios()) {
			cadaRegistroPropietario.setTituloPropiedad(pTituloPropiedad);
		}
	}

	public void deleteParcela(Parcela pParcela) {
		pParcela.setEstado(Parcela.Estado.ELIMINADO);
		TrascenderEnverListener.setValoresEnAuditoriaBean(pParcela);

		this.entityManager.merge(pParcela);
		this.entityManager.flush();
	}

	public com.trascender.catastro.recurso.persistent.Parcela updateParcela(com.trascender.catastro.recurso.persistent.Parcela pParcela) throws Exception {
		this.validarParcela(pParcela);
		this.validarAsociacionesParcela(pParcela);
		this.validarPropietarioObligacion(pParcela);
		if(pParcela.getManzana() != null) {
			Iterator<ParcelaPorCuadra> locIterator = pParcela.getListaParcelasPorCuadra().iterator();
			while(locIterator.hasNext()) {
				ParcelaPorCuadra cadaParcelaPorCuadra = locIterator.next();
				if(cadaParcelaPorCuadra.getMetrosPorCuadra().equals(new Double(0))) {
					locIterator.remove();
					continue;
				}
				cadaParcelaPorCuadra.setParcela(pParcela);
			}
		} else {
			pParcela.getListaParcelasPorCuadra().clear();
		}
		validarTituloPropiedad(pParcela.getTituloPropiedad());
		TrascenderEnverListener.setValoresEnAuditoriaBean(pParcela);

		pParcela = this.entityManager.merge(pParcela);
		entityManager.flush();
		return pParcela;
	}

	public FiltroSubParcela findListaSubParcela(FiltroSubParcela filtro) throws Exception {

		Criterio locCriterio = Criterio.getInstance(this.entityManager, SubParcela.class).add(Restriccion.IGUAL("padre", filtro.getParcelaPadre()))
				.add(Restriccion.IGUAL("titular", filtro.getTitular()));

		filtro.procesarYListar(locCriterio);

		for(SubParcela cadaSubParcela : filtro.getListaResultados()) {
			cadaSubParcela.toString();
		}
		return filtro;
	}

	/**
	 * Recupera un listado de parcelas que pertenecen a una cuadra
	 * 
	 * @param pCuadra
	 *            cuadra a la que pertenecen las parcelas
	 * @ejb.interface-method view-type = "local"
	 */
	@SuppressWarnings("unchecked")
	public FiltroParcela findListaParcelas(FiltroParcela filtro) {

		Criterio locCriterio = Criterio.getInstance(this.entityManager, Parcela.class)
				.add(Restriccion.IGUAL("manzana", filtro.getManzana()))
				.add(Restriccion.IGUAL("nroRegistro", filtro.getNroRegistro()))
				.add(Restriccion.IGUAL("nroMatricula", filtro.getNroMatricula()))
				.add(Restriccion.IGUAL("nroPartidaProvincial", filtro.getNroPartidaProvincial()))
				.add(Restriccion.IGUAL("nroCuenta", filtro.getNroCuenta()))
				.add(Restriccion.IGUAL("estado", filtro.getEstado()))
				.add(Restriccion.JPQL("TYPE(e) = Parcela"))

				.add(Restriccion.ILIKE("listaPlanosConstruccion.plano", filtro.getPlanoConstruccion()))
				.add(Restriccion.ILIKE("listaPlanosConstruccion.tomo", filtro.getTomoConstruccion()))
				.add(Restriccion.ILIKE("listaPlanosConstruccion.folio", filtro.getFolioConstruccion()))
				.add(Restriccion.IGUAL("listaPlanosConstruccion.parcela", filtro.getParcelaConstruccion()))
				.add(Restriccion.IGUAL("listaPlanosConstruccion.fechaInscripcion", filtro.getFechaInscripcionConstruccion()))
				.add(Restriccion.IGUAL("listaPlanosConstruccion.estado", filtro.getEstadoConstruccion()))

				.add(Restriccion.ILIKE("planoMensura.plano", filtro.getPlanoMensura()))
				.add(Restriccion.ILIKE("planoMensura.tomo", filtro.getTomoMensura()))
				.add(Restriccion.ILIKE("planoMensura.folio", filtro.getFolioMensura()))
				.add(Restriccion.IGUAL("planoMensura.parcela", filtro.getParcelaMensura()))
				.add(Restriccion.IGUAL("planoMensura.fechaInscripcion", filtro.getFechaInscripcionMensura()))
				.add(Restriccion.IGUAL("planoMensura.estado", filtro.getEstadoMensura()))

				.add(Restriccion.IGUAL("tituloPropiedad.fechaDesde", filtro.getFechaDesdeTituloPropiedad()))
				.add(Restriccion.IGUAL("tituloPropiedad.fechaHasta", filtro.getFechaHastaTituloPropiedad()))
				.add(Restriccion.IGUAL("tituloPropiedad.persona", filtro.getPersonaTituloPropiedad()))
				.add(Restriccion.IGUAL("tituloPropiedad.parcela", filtro.getParcelaTituloPropiedad()))
				.add(Restriccion.IGUAL("tituloPropiedad.tipoTransaccionCatastral", filtro.getTipoTransaccionCatastralTituloPropiedad()))
				.add(Restriccion.IGUAL("tituloPropiedad.nroTomo", filtro.getNrotomoTituloPropiedad()))
				.add(Restriccion.IGUAL("tituloPropiedad.nroFolio", filtro.getNroFolioTituloPropiedad()))
				.add(Restriccion.IGUAL("tituloPropiedad.asiento", filtro.getNroAsientoTituloPropiedad()))
				.add(Restriccion.IGUAL("domicilioParcelario.numero", filtro.getNumeroDomicilio())).setDistinct(true).setModoDebug(true);

		if(filtro.getPersona() != null) {
			locCriterio.add(Restriccion.IGUAL("tituloPropiedad.listaRegistrosPropietarios.persona", filtro.getPersona()));
		} else if(filtro.getListaIdPersonas() != null && !filtro.getListaIdPersonas().isEmpty()) {
			locCriterio.crearAlias("tituloPropiedad.listaRegistrosPropietarios", "cadaPropietario")
				.add(Restriccion.EN("cadaPropietario.persona.id", filtro.getListaIdPersonas()));
		}

		if(filtro.getZona() != null) {
			locCriterio.crearAliasLeft("listaAsociacionParcela.zona", "cadaZonaDirecta");
			locCriterio.add(Restriccion.OR(
					Restriccion.IGUAL("cadaZonaDirecta", filtro.getZona()),
					Restriccion.JPQL("e.manzana = (SELECT cadaManzana FROM AsociacionParcelaManzana asocManzana " + "JOIN asocManzana.manzana cadaManzana "
							+ "WHERE asocManzana.zona.idZona =" + filtro.getZona().getIdZona() + ")"),
					Restriccion.JPQL(" EXISTS (SELECT cadaParcelaPorCuadra1.parcela FROM AsociacionParcelaCuadra asocCuadra "
							+ "JOIN asocCuadra.cuadra cadaCuadra1 JOIN cadaCuadra1.listaParcelasPorCuadra cadaParcelaPorCuadra1 " + "WHERE asocCuadra.zona.idZona ="
							+ filtro.getZona().getIdZona() + ")"),
					Restriccion.JPQL(" EXISTS (SELECT cadaParcelaPorCuadra2.parcela FROM AsociacionParcelaCalle asocCalle "
							+ "JOIN asocCalle.calle cadaCalle JOIN cadaCalle.listaCuadras cadaCuadra2 JOIN cadaCuadra2.listaParcelasPorCuadra cadaParcelaPorCuadra2 "
							+ "WHERE asocCalle.zona.idZona =" + filtro.getZona().getIdZona() + ")")));
		}

		AtributoDinamico.addRestriccionesCriterio(locCriterio, Parcela.serialVersionUID, "idParcela", filtro.getListaAtributosDinamicos());
		AtributoDinamico.addRestriccionesCriterio(locCriterio, PlanoMensura.serialVersionUID, "planoMensura.idPlanoMensura", filtro.getListaAtributosDinamicosPlanoMensura());
		AtributoDinamico.addRestriccionesCriterio(locCriterio, PlanoConstruccion.serialVersionUID, "listaPlanosConstruccion.idPlanoConstruccion",
				filtro.getListaAtributosDinamicosPlanoConstruccion());
		AtributoDinamico.addRestriccionesCriterio(locCriterio, TituloPropiedadParcelario.serialVersionUID, "tituloPropiedad.idTituloPropiedad",
				filtro.getListaAtributosDinamicosTituloPropiedad());

		List<BusquedaPorLog> locLogsParcela = new ArrayList();
		List<BusquedaPorLog> locLogsTituloPropiedad = new ArrayList();
		List<BusquedaPorLog> locLogsPlanoMensura = new ArrayList();
		List<BusquedaPorLog> locLogsPlanoConstruccion = new ArrayList();
		List<BusquedaPorLog> locLogsRegistroMejora = new ArrayList();

		for(BusquedaPorLog cadaLog : filtro.getListaBusquedaPorLogs()) {
			if(cadaLog.getNombrePropiedad() != null) {
				if(cadaLog.getNombrePropiedad().equals("Parcela")) {
					cadaLog.setNombrePropiedad("");
					locLogsParcela.add(cadaLog);
					continue;
				}
				if(cadaLog.getNombrePropiedad().equals("Título Propiedad")) {
					cadaLog.setNombrePropiedad("");
					locLogsTituloPropiedad.add(cadaLog);
					continue;
				}
				if(cadaLog.getNombrePropiedad().equals("Plano Mensura")) {
					cadaLog.setNombrePropiedad("");
					locLogsPlanoMensura.add(cadaLog);
					continue;
				}
				if(cadaLog.getNombrePropiedad().equals("Plano Construcción")) {
					cadaLog.setNombrePropiedad("");
					locLogsPlanoConstruccion.add(cadaLog);
					continue;
				}
				if(cadaLog.getNombrePropiedad().equals("Registro Mejora")) {
					cadaLog.setNombrePropiedad("");
					locLogsRegistroMejora.add(cadaLog);
					continue;
				}

				if(cadaLog.getNombrePropiedad().startsWith("Parcela")) {
					cadaLog.setNombrePropiedad(cadaLog.getNombrePropiedad().substring(11));
					locLogsParcela.add(cadaLog);
				} else if(cadaLog.getNombrePropiedad().startsWith("Título Propiedad")) {
					cadaLog.setNombrePropiedad(cadaLog.getNombrePropiedad().substring(20));
					locLogsTituloPropiedad.add(cadaLog);
				} else if(cadaLog.getNombrePropiedad().startsWith("Plano Mensura")) {
					cadaLog.setNombrePropiedad(cadaLog.getNombrePropiedad().substring(17));
					locLogsPlanoMensura.add(cadaLog);
				} else if(cadaLog.getNombrePropiedad().startsWith("Plano Construcción")) {
					cadaLog.setNombrePropiedad(cadaLog.getNombrePropiedad().substring(22));
					locLogsPlanoConstruccion.add(cadaLog);
				} else if (cadaLog.getNombrePropiedad().startsWith("Registro Mejora")) {
					cadaLog.setNombrePropiedad(cadaLog.getNombrePropiedad().substring(19));
					locLogsRegistroMejora.add(cadaLog);
				}
			}
		}
		BusquedaPorLog.addRestriccionesCriterio(locCriterio, Parcela.serialVersionUID, "idParcela", locLogsParcela);
		BusquedaPorLog.addRestriccionesCriterio(locCriterio, TituloPropiedadParcelario.serialVersionUID, "tituloPropiedad.idTituloPropiedad", locLogsTituloPropiedad);
		BusquedaPorLog.addRestriccionesCriterio(locCriterio, PlanoMensura.serialVersionUID, "planoMensura.idPlanoMensura", locLogsPlanoMensura);
		if (!locLogsPlanoConstruccion.isEmpty()) {
			locCriterio.crearAlias("listaPlanosConstruccion", "cadaPlanoConstruccion");
			BusquedaPorLog.addRestriccionesCriterio(locCriterio, PlanoConstruccion.serialVersionUID, "idPlanoConstruccion", locLogsPlanoConstruccion, "cadaPlanoConstruccion");
		}
		if (!locLogsRegistroMejora.isEmpty()) {
			locCriterio.crearAlias("listaRegistrosMejora", "cadaRegistroMejora");
			BusquedaPorLog.addRestriccionesCriterio(locCriterio, RegistroMejora.serialVersionUID, "idRegistroMejora", locLogsRegistroMejora, "cadaRegistroMejora");
		}

		// Separo la restriccion de Cuadra para no forzar al criterio a hacer el alias, pues
		// hace un join innecesario si la cuadra es nula.
		if(filtro.getCuadra() != null) {
			locCriterio.crearAlias("listaParcelasPorCuadra.cuadra", "cadaCuadra").add(Restriccion.IGUAL("cadaCuadra", filtro.getCuadra()));
		}

		if(filtro.getCalle() != null) {
			locCriterio.add(Restriccion.IGUAL("domicilioParcelario.relacionCalle.idAbstractCalle", filtro.getCalle().getIdCalle()));
		}

		if(filtro.getNomenClaturaCatastral() != null) {
			locCriterio.crearAlias("nomenclaturaCatastral", "locNomenclatura")
					.add(Restriccion.IGUAL("locNomenclatura.departamento", filtro.getNomenClaturaCatastral().getDepartamento()))
					.add(Restriccion.IGUAL("locNomenclatura.pedania", filtro.getNomenClaturaCatastral().getPedania()))
					.add(Restriccion.IGUAL("locNomenclatura.circunscripcion", filtro.getNomenClaturaCatastral().getCircunscripcion()))
					.add(Restriccion.IGUAL("locNomenclatura.distrito", filtro.getNomenClaturaCatastral().getDistrito()))
					.add(Restriccion.IGUAL("locNomenclatura.subDistrito", filtro.getNomenClaturaCatastral().getSubDistrito()))
					.add(Restriccion.IGUAL("locNomenclatura.seccion", filtro.getNomenClaturaCatastral().getSeccion()))
					.add(Restriccion.IGUAL("locNomenclatura.quinta", filtro.getNomenClaturaCatastral().getQuinta()))
					.add(Restriccion.IGUAL("locNomenclatura.chacra", filtro.getNomenClaturaCatastral().getChacra()))
					.add(Restriccion.IGUAL("locNomenclatura.lote", filtro.getNomenClaturaCatastral().getLote()));

			if(filtro.getNomenClaturaCatastral().getNroParcela() != null) {
				locCriterio.add(Restriccion.EN("locNomenclatura.nroParcela", Arrays.asList(filtro.getNomenClaturaCatastral().getNroParcela().replace(" ", ",").split(","))));
			}
		}

		filtro.procesarYListar(locCriterio);

		for(Parcela cadaParcela : filtro.getListaResultados()) {
			cadaParcela.toString();
			cadaParcela.getDomicilioParcelario().toString();
			cadaParcela.getManzana().toString();
			cadaParcela.toString();
			cadaParcela.getListaAtributosDinamicos().toString();
		}

		return filtro;
	}

	/**
	 * 
	 * @param pCalle
	 *            calle a la que pertenecen las parcelas
	 * @param pNumeracionDesde
	 *            numeraci?n desde la que comienzan las parcelas
	 * @param pNumeracionHasta
	 *            numeraci?n hasta la cual comienzan las parcelas
	 * @return Listado de parcelas
	 */
	@SuppressWarnings("unchecked")
	public FiltroParcela findListaParcelasPorDomicilio(FiltroParcela filtro) {

		Criterio locCriterio = Criterio.getInstance(this.entityManager, Parcela.class).add(Restriccion.IGUAL("calle", filtro.getCalle()));
		// .add(Restriccion.MAYOR("nroParcela", filtro.getNumeracionDesde()))
		// .add(Restriccion.MENOR("nroParcela", filtro.getNumeracionHasta()));

		filtro.procesarYListar(locCriterio);

		return filtro;
	}

	/**
	 * Recupera una parcela por el id
	 * 
	 * @parma pIdParcela n?mero de identificaci?n de la parcela
	 */
	public com.trascender.catastro.recurso.persistent.Parcela getParcelaPorId(long pIdParcela) {
		Parcela locParcela = null;
		try {
			locParcela = (Parcela) Criterio.getInstance(this.entityManager, Parcela.class)
					.add(Restriccion.IGUAL("idParcela", pIdParcela))
					.crearFetchAlias("manzana", "locManzana")
					.setModoDebug(true)
					.uniqueResult();

			if(locParcela != null) {
				for(ParcelaPorCuadra locParcelaPorCuadra : locParcela.getListaParcelasPorCuadra()) {
					locParcelaPorCuadra.toString();
					locParcelaPorCuadra.getCuadra().toString();
				}

				for (AsociacionParcela cadaAsocParcela : locParcela.getListaAsociacionParcela()) {
					cadaAsocParcela.toString();
				}

				for(SubParcela cadaSubparcela : locParcela.getListaSubParcelas()) {
					cadaSubparcela.toString();
					for(ParcelaPorCuadra cadParcelaPorCuad : cadaSubparcela.getListaParcelasPorCuadra()) {
						cadParcelaPorCuad.toString();
					}
				}

				locParcela.getListaAtributosDinamicos().size();
				if(locParcela.getPlanoMensura() != null) {
					locParcela.getPlanoMensura().toString();
					locParcela.getPlanoMensura().getListaLogsAuditoria().size();
					locParcela.getPlanoMensura().getListaAtributosDinamicos().size();
				}

				locParcela.getListaLogsAuditoria().size();

				locParcela.toString();
				if(locParcela.getManzana() != null) {
					locParcela.getManzana().toString();
				}
				if(locParcela.getDomicilioParcelario() != null) {
					locParcela.getDomicilioParcelario().toString();
				}

				TituloPropiedad locTituloPropiedad = locParcela.getTituloPropiedad();

				if(locTituloPropiedad != null) {
					for(RegistroPropietario locRegistroPropietario : locTituloPropiedad.getListaRegistrosPropietarios()) {
						locRegistroPropietario.toString();
						locRegistroPropietario.getPersona().toString();

						if(locRegistroPropietario.getPersona().getDomicilio() != null) {
							locRegistroPropietario.getPersona().getDomicilio().getDomicilioCompleto().toString();
						}
						if(locRegistroPropietario.getPersona().getDomicilioPostal() != null) {
							locRegistroPropietario.getPersona().getDomicilioPostal().getDomicilioCompleto().toString();
						}

					}
					locTituloPropiedad.toString();
					((TituloPropiedadParcelario) locTituloPropiedad).getListaAtributosDinamicos().size();
					((TituloPropiedadParcelario) locTituloPropiedad).getListaLogsAuditoria().size();
				}

				for(RegistroMejora locRegistroMejora : locParcela.getListaRegistrosMejora()) {
					locRegistroMejora.toString();
					locRegistroMejora.getCategoria().toString();
					if(locRegistroMejora.getDeclaracionJurada() != null) {
						locRegistroMejora.getDeclaracionJurada().toString();
					}
					locRegistroMejora.getListaLogsAuditoria().size();
					locRegistroMejora.getListaAtributosDinamicos().size();
				}

				for(ParcelaPorCuadra locParcelaPorCuadra : locParcela.getListaParcelasPorCuadra()) {
					locParcelaPorCuadra.toString();
					locParcelaPorCuadra.getCuadra().toString();
				}

				locParcela.getListaAsociacionParcela().size();

				for(SubParcela cadaSubparcela : locParcela.getListaSubParcelas()) {
					cadaSubparcela.toString();
					for(ParcelaPorCuadra cadParcelaPorCuad : cadaSubparcela.getListaParcelasPorCuadra()) {
						cadParcelaPorCuad.toString();
					}
				}

				locParcela.getListaAtributosDinamicos().size();
				if(locParcela.getPlanoMensura() != null) {
					locParcela.getPlanoMensura().toString();
				}

				for(PlanoConstruccion cadaPlanoConstruccion : locParcela.getListaPlanosConstruccion()) {
					cadaPlanoConstruccion.toString();
					cadaPlanoConstruccion.getListaAtributosDinamicos().size();
					cadaPlanoConstruccion.getListaLogsAuditoria().size();
					cadaPlanoConstruccion.getListaFirmantePlanoConstruccion().size();
				}
				locParcela.toString();

			}
		} catch(Exception e) {
			e.printStackTrace();
		}

		return locParcela;

	}

	/**
	 * 
	 * @param pParcela
	 * @param pListaSubParcelas
	 * @throws Exception
	 */
	public void subParcelarParcela(Parcela pParcela, List<? extends SubParcela> pListaSubParcelas) throws Exception {
		this.validarSubparcelamiento(pParcela, pListaSubParcelas);

		for(SubParcela cadaSubParcela : pListaSubParcelas) {
			this.prepararSubParcela(cadaSubParcela);

			if(!pParcela.getListaSubParcelas().contains(cadaSubParcela)) {
				try {
					pParcela.addSubParcela(cadaSubParcela);
				} catch(Exception e) {
					throw e;
				}
			}

		}

		this.entityManager.merge(pParcela);
	}

	/**
	 * Setea los valores q las sub-parcelas deben poseer y los que no deberia
	 * 
	 * @param pSubParcela
	 */
	private void prepararSubParcela(SubParcela pSubParcela) throws Exception {
		pSubParcela.setNroCuenta(null);
		pSubParcela.setNroMatricula(null);
		pSubParcela.setNroPartidaProvincial(null);
		pSubParcela.setNroRegistro(null);
		pSubParcela.setTituloPropiedad(null);
	}

	/**
	 * @param pParcela
	 * @param pListaSubParcelas
	 * @throws Exception
	 */
	private void validarSubparcelamiento(Parcela pParcela, List<? extends SubParcela> pListaSubParcelas) throws Exception {
		if(pParcela == null || pListaSubParcelas == null || pListaSubParcelas.isEmpty()) {
			throw new CatastroException(705);
		}

		for(SubParcela cadaSubParcela : pListaSubParcelas) {
			if(cadaSubParcela.getPadre() != null) {
				if(pParcela.getIdParcela() != cadaSubParcela.getPadre().getIdParcela()) {
					throw new CatastroException(706);
				}

				if(cadaSubParcela.getIdParcela() != -1) {
					throw new CatastroException(717);
				}
			} else {
				throw new CatastroException(707);
			}

		}

		if(pParcela.isSubParcelada()) {
			for(Parcela cadaParcela : pParcela.getListaSubParcelas()) {
				for(Parcela cadaSubNewParcela : pListaSubParcelas) {
					if(cadaParcela.hashCode() == cadaSubNewParcela.hashCode()) {
						throw new CatastroException(708);
					}
				}
			}
		}

		if(this.getSuperficieSubParcelariaTotal(pParcela.getListaSubParcelas()) > pParcela.getSuperficie()) {
			throw new CatastroException(718);
		}
	}

	/**
	 * Unifica una parcela o remueve una subParcela
	 * 
	 * @param pParcela
	 * @param pSubParcela
	 *            la subparcela que se va a eliminar
	 * @param pSubParcelasActualizadas
	 *            las subParcelas que quedan con la superficie actualizada.
	 * @throws Exception
	 * @Atachment Despues de hacer una union dar de baja n obligaciones q tenga asociadas.
	 */
	public void unionSubParcelaria(Parcela pParcela, SubParcela pSubParcela) throws Exception {
		// dar de baja obligaciones subparcelarias y dar de alta la anterior
		if(pParcela == null || pSubParcela == null || !pParcela.getListaSubParcelas().contains(pSubParcela)) {
			throw new CatastroException(703);
		}

		this.verificarAuntenticdadSubParcelas(pParcela, pParcela.getListaSubParcelas());

		// if(pParcela.getListaSubParcelas().size() == 2){
		//
		// for(SubParcela cadaSubParcela : pParcela.getListaSubParcelas()){
		// cadaSubParcela.setDomicilioParcelario(null);
		// }
		// //A este momento deberian cancelarse las obligaciones asociadas a cada subParcela
		// //pero como desde catastro no se puede, se lo delega a la Interfaz que si tiene visibilidad
		// pParcela.getListaSubParcelas().clear();
		// }else {
		pSubParcela.setDomicilioParcelario(null);
		pParcela.getListaSubParcelas().remove(pSubParcela);

		/*
		 * Verifico que el tamanio de las nuevas subParcelas se reparta en las que quedan
		 */
		if(!(this.getSuperficieSubParcelariaTotal(pParcela.getListaSubParcelas()) <= pParcela.getSuperficie())) {
			throw new CatastroException(704);
		}

		// }

		this.entityManager.merge(pParcela);
	}

	/**
	 * Devuelve la superficie total de un conjunto de subparcelas
	 * 
	 * @param pSubParcelasActualizadas
	 * @return
	 */
	private Double getSuperficieSubParcelariaTotal(Collection<? extends SubParcela> pSubParcelasActualizadas) {
		Double locAcumuladorSuperficie = 0d;
		for(SubParcela cadaSubparcela : pSubParcelasActualizadas) {
			locAcumuladorSuperficie += cadaSubparcela.getSuperficie();
		}
		return locAcumuladorSuperficie;
	}

	/**
	 * Verifica que las subparcelas sean de la parcela
	 * 
	 * @param pParcela
	 * @param pSubParcelas
	 * @throws Exception
	 */
	private void verificarAuntenticdadSubParcelas(Parcela pParcela, Collection<SubParcela> pSubParcelas) throws Exception {
		int locContadorVerificador = 0;

		if(pParcela.getListaSubParcelas().size() != pSubParcelas.size()) {
			throw new CatastroException(702);
		}

		for(SubParcela cadaSubpParcela : pSubParcelas) {
			if(cadaSubpParcela.getPadre().hashCode() == pParcela.hashCode()) {
				locContadorVerificador++;
			}
		}

		if(locContadorVerificador != pParcela.getListaSubParcelas().size()) {
			throw new CatastroException(702);
		}

	}

	/**
	 * Find lista volantes catastrales
	 * 
	 * @param pNumVolanteCatastral
	 *            n?mero del volante catastral
	 * @param pParcela
	 *            parcela a al que pertenece el volante catastral
	 */
	@SuppressWarnings("unchecked")
	public java.util.List findListaVolanteCatastral(java.lang.Integer pNumVolanteCatastral, com.trascender.catastro.recurso.persistent.Parcela pParcela) {
		List locLista = null;

		try {
			locLista = Criterio.getInstance(this.entityManager, VolanteCatastral.class).add(Restriccion.IGUAL("nroVolanteCatastral", pNumVolanteCatastral))
					.add(Restriccion.IGUAL("parcela", pParcela)).list();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return locLista;

	}

	/**
	 * Listado de planos de mensura
	 * 
	 * @param pCodigoPlanoMensura
	 *            c?digo de planos de mensura
	 * @param pParcela
	 *            parcela a la que pertenece el plano de mensura
	 * @param pFechaInscripcion
	 *            fecha de inscripcion del plano de mensura
	 * @ejb.interface-method view-type = "local"
	 */
	@SuppressWarnings("unchecked")
	public FiltroPlanoMensura findListaPlanoMensura(FiltroPlanoMensura filtro) throws Exception {

		Criterio locCriterio = Criterio.getInstance(this.entityManager, PlanoMensura.class).add(Restriccion.LIKE("plano", filtro.getPlano(), false, Posicion.AL_PRINCIPIO))
				.add(Restriccion.LIKE("tomo", filtro.getTomo(), false, Posicion.AL_PRINCIPIO)).add(Restriccion.LIKE("folio", filtro.getFolio(), false, Posicion.AL_PRINCIPIO))
				.add(Restriccion.IGUAL("parcela", filtro.getParcela())).add(Restriccion.IGUAL("fechaInscripcion", filtro.getFechaInscripcion()))
				.add(Restriccion.IGUAL("estado", filtro.getEstado())).setModoDebug(true);

		filtro.procesarYListar(locCriterio);

		return filtro;
	}

	public PlanoMensura getPlanoMensuraPorId(long id) throws Exception {
		PlanoMensura locPlano = this.entityManager.find(PlanoMensura.class, id);
		if(locPlano != null) {
			locPlano.getParcela().toString();
			locPlano.getParcela().getListaSubParcelas().toString();
		}
		return locPlano;
	}

	@SuppressWarnings("unchecked")
	public FiltroDeclaracionJurada findListaDeclaracionJurada(FiltroDeclaracionJurada filtro) throws Exception {

		Criterio locCriterio = Criterio.getInstance(this.entityManager, DeclaracionJurada.class).add(Restriccion.LIKE("numero", filtro.getCodigoDDJJ(), false, Posicion.AL_PRINCIPIO))
				.add(Restriccion.MAYOR("fechaInscripcion", filtro.getDesde())).add(Restriccion.MENOR("fechaInscripcion", filtro.getHasta()))
				.add(Restriccion.IGUAL("parcela", filtro.getParcela()));

		filtro.procesarYListar(locCriterio);

		return filtro;

	}

	/**
	 * Recupera una lista de resgistro de mejoras segun los parametros
	 */
	@SuppressWarnings("unchecked")
	public List findListaRegistroMejora(Parcela pParcela) {

		return Criterio.getInstance(this.entityManager, RegistroMejora.class).add(Restriccion.IGUAL("parcela", pParcela)).add(Restriccion.IGUAL("activo", new Boolean(true))).list();
	}

	/**
	 * Recupera un listado de planos de contruccion
	 * 
	 * @param pCodigo
	 *            código del plano de construccion
	 * @param pParcela
	 *            parcela a la que pertenece el plano de contruccion
	 */
	@SuppressWarnings("unchecked")
	public FiltroPlanoConstruccion findListaPlanoConstruccion(FiltroPlanoConstruccion filtro) throws Exception {

		Criterio locCriterio = Criterio.getInstance(this.entityManager, PlanoConstruccion.class).add(Restriccion.ILIKE("plano", filtro.getPlano()))
				.add(Restriccion.ILIKE("tomo", filtro.getTomo())).add(Restriccion.ILIKE("folio", filtro.getFolio())).add(Restriccion.IGUAL("parcela", filtro.getParcela()))
				.add(Restriccion.IGUAL("fechaInscripcion", filtro.getFechaInscripcion())).add(Restriccion.IGUAL("estado", filtro.getEstado()));

		// Criterio locCriterio = Criterio.getInstance(this.entityManager, PlanoConstruccion.class)
		// .add(Restriccion.ILIKE("plano", filtro.getPlanoConstruccion()))
		// .add(Restriccion.ILIKE("tomo", filtro.getTomoConstruccion()))
		// .add(Restriccion.ILIKE("folio", filtro.getFolioConstruccion()))
		// .add(Restriccion.IGUAL("parcela", filtro.getParcelaConstruccion()))
		// .add(Restriccion.IGUAL("fechaInscripcion", filtro.getFechaInscripcionConstruccion()))
		// .add(Restriccion.IGUAL("estado", filtro.getEstadoConstruccion()));

		filtro.procesarYListar(locCriterio);

		return filtro;

	}

	/**
	 * Agrega una nueva declaraci?n jurada
	 * 
	 * @param pDeclaracionJurada
	 *            declaraci�n jurada a agregar
	 * @ejb.interface-method view-type = "local"
	 */
	public com.trascender.catastro.recurso.persistent.DeclaracionJurada addDeclaracionJurada(com.trascender.catastro.recurso.persistent.DeclaracionJurada pDeclaracionJurada)
			throws Exception {

		this.validarDeclaracionJurada(pDeclaracionJurada);

		this.entityManager.merge(pDeclaracionJurada);

		return pDeclaracionJurada;

	}

	/**
	 * Valida que no exista una declaracion jurada con el mismo codigo para una misma parcela
	 */
	private void validarDeclaracionJurada(DeclaracionJurada pDeclaracionJurada) throws Exception {

		Criterio locCriterio = Criterio.getInstance(this.entityManager, DeclaracionJurada.class);

		locCriterio.add(Restriccion.IGUAL("numero", pDeclaracionJurada.getNumero()))
		// .add(Restriccion.IGUAL("parcela", pDeclaracionJurada.getParcela()))
				.setModoDebug(true).setProyeccion(Proyeccion.COUNT());

		if(pDeclaracionJurada.getIdDeclaracionJurada() != -1) {
			locCriterio.add(Restriccion.NOT(Restriccion.IGUAL("idDeclaracionJurada", pDeclaracionJurada.getIdDeclaracionJurada())));
		}

		if((Long) locCriterio.uniqueResult() > 0) {
			throw new CatastroException(378);
		}

	}

	/**
	 * Actualiza los datos de una declaracion jurada
	 * 
	 * @param pDeclaracionJurada
	 *            declaracion jurada que se desea actualizar
	 */
	public com.trascender.catastro.recurso.persistent.DeclaracionJurada updateDeclaracionJurada(com.trascender.catastro.recurso.persistent.DeclaracionJurada pDeclaracionJurada)
			throws Exception {

		this.validarDeclaracionJurada(pDeclaracionJurada);

		this.entityManager.merge(pDeclaracionJurada);

		return pDeclaracionJurada;

	}

	/**
	 * Agrega los datos de un Volante Catastral
	 * 
	 * @param pVolanteCatastral
	 *            datos de un Volante Catastral
	 */
	public com.trascender.catastro.recurso.persistent.VolanteCatastral addVolanteCatastral(com.trascender.catastro.recurso.persistent.VolanteCatastral pVolanteCatastral)
			throws java.lang.Exception {

		Integer locNroVolante = (Integer) this.entityManager.createQuery("SELECT MAX(v.nroVolanteCatastral) FROM VolanteCatastral v").getSingleResult();

		if(locNroVolante == null) {
			locNroVolante = 1;
		}

		pVolanteCatastral.setNroVolanteCatastral(locNroVolante + 1);

		this.entityManager.setFlushMode(FlushModeType.COMMIT);
		this.entityManager.persist(pVolanteCatastral);
		this.entityManager.flush();
		this.entityManager.refresh(pVolanteCatastral);

		return pVolanteCatastral;

	}

	/**
	 * actualiza los datos de un volante catastral
	 */
	public VolanteCatastral updateVolanteCatastral(VolanteCatastral pVolanteCatastral) throws java.lang.Exception {

		this.entityManager.merge(pVolanteCatastral);

		return pVolanteCatastral;

	}

	/**
	 * Business method
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	public void deleteVolanteCatastral(VolanteCatastral pVolanteCatastral) throws Exception {
		pVolanteCatastral = this.entityManager.merge(pVolanteCatastral);
		this.entityManager.remove(pVolanteCatastral);

	}

	/**
	 * Agrega un plano de mensura al sistema
	 * 
	 * @param pPlanoMensura
	 *            plano de mensura a agregar
	 * @return plano de mensura acutalizado
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public PlanoMensura addPlanoMensura(PlanoMensura pPlanoMensura) throws Exception {
		this.validarPlanoMensura(pPlanoMensura);
		System.out.println(pPlanoMensura + "  " + pPlanoMensura.getFechaInscripcion() + "----- " + pPlanoMensura.getParcela());
		this.entityManager.persist(pPlanoMensura);
		this.entityManager.flush();
		return pPlanoMensura;

	}

	/**
	 * Valida que no aya un plano mensura con el mismo codigo
	 * 
	 * @param pPlanoMensura
	 * @throws Exception
	 *             lanza una excepcion si el codigo esta repetido
	 */
	private void validarPlanoMensura(PlanoMensura pPlanoMensura) throws Exception {
		if(pPlanoMensura.getParcela() instanceof SubParcela) {
			throw new CatastroException(716);
		}

		Criterio locCriterio = Criterio.getInstance(this.entityManager, PlanoMensura.class);

		locCriterio.add(Restriccion.IGUAL("plano", pPlanoMensura.getPlano())).add(Restriccion.IGUAL("parcela", pPlanoMensura.getParcela())).setProyeccion(Proyeccion.COUNT());

		if(pPlanoMensura.getIdPlanoMensura() != -1) {
			locCriterio.add(Restriccion.NOT(Restriccion.IGUAL("idPlanoMensura", pPlanoMensura.getIdPlanoMensura())));
		}

		if((Long) locCriterio.uniqueResult() > 0) {
			throw new CatastroException(77);
		}

		Parcela locParcela = pPlanoMensura.getParcela();
		if(locParcela.getNroMatricula() == null || locParcela.getNroPartidaProvincial() == null || locParcela.getNroRegistro() == null || locParcela.getNroCuenta() == null) {
			throw new CatastroException(89);
		}
	}

	/**
	 * Actualiza los datos de un plano de mensura
	 * 
	 * @param pPlanoMensura
	 *            Plano de mensura a actualizar
	 * @return PlanoMensura actualizado
	 * @throws Exception
	 */
	public com.trascender.catastro.recurso.persistent.PlanoMensura updatePlanoMensura(com.trascender.catastro.recurso.persistent.PlanoMensura pPlanoMensura) throws Exception {

		this.validarPlanoMensura(pPlanoMensura);
		this.entityManager.merge(pPlanoMensura);
		this.entityManager.flush();
		return pPlanoMensura;
	}

	/**
	 * Elimina f�sicamente un plano de mensura
	 * 
	 * @param pPlanoMensura
	 *            plano de mensura a eliminar
	 * @throws Exception
	 */
	public void deletePlanoMensura(com.trascender.catastro.recurso.persistent.PlanoMensura pPlanoMensura) throws Exception {

		pPlanoMensura = (PlanoMensura) Criterio.getInstance(this.entityManager, PlanoMensura.class).add(Restriccion.IGUAL("idPlanoMensura", pPlanoMensura.getIdPlanoMensura()))
				.uniqueResult();

		this.entityManager.remove(pPlanoMensura);

	}

	/**
	 * Agrega al sistema un registro de mejora
	 * 
	 * @param pRegistroMejora
	 *            registro de mejora a agregar
	 * @return registro de mejora acutalizado
	 * @throws Exception
	 */
	public com.trascender.catastro.recurso.persistent.RegistroMejora addRegistroMejora(com.trascender.catastro.recurso.persistent.RegistroMejora pRegistroMejora) throws Exception {

		pRegistroMejora.setActivo(true);
		this.validarRegistroMejora(pRegistroMejora);

		this.entityManager.persist(pRegistroMejora);
		this.entityManager.flush();
		this.entityManager.refresh(pRegistroMejora);

		return pRegistroMejora;

	}

	/**
	 * Valida que el registro mejora tenga un estado (por default BUENO) y setea el registro de mejora en la parcela a la cual pertenece.
	 * 
	 * @param pRegistroMejora
	 */
	private void validarRegistroMejora(RegistroMejora pRegistroMejora) {
		if(pRegistroMejora.getEstadoMejora() == null && pRegistroMejora.getIdRegistroMejora() == -1) {
			pRegistroMejora.setEstadoMejora(EstadoCoeficiente.BUENO);
		}

	}

	/**
	 * Actualiza los datos del registro de mejora
	 * 
	 * @param pRegistroMejora
	 *            registro de mejora
	 * @return Registro de mejora actualizado
	 * @throws Exception
	 */
	public com.trascender.catastro.recurso.persistent.RegistroMejora updateRegistroMejora(com.trascender.catastro.recurso.persistent.RegistroMejora pRegistroMejora) throws Exception {

		if(pRegistroMejora == null || pRegistroMejora.getParcela() == null) {
			throw new CatastroException(379);
		}

		// this.validarRegistroMejora(pRegistroMejora);

		pRegistroMejora = this.entityManager.merge(pRegistroMejora);

		Parcela locParcela = pRegistroMejora.getParcela();
		if(locParcela != null) {
			if(!locParcela.getListaRegistrosMejora().contains(pRegistroMejora)) {
				locParcela.getListaRegistrosMejora().add(pRegistroMejora);
			}
		}

		this.entityManager.merge(locParcela);

		return pRegistroMejora;

	}

	/**
	 * Realiza un a b�squeda de los registros de mejora
	 * 
	 * @param pAnioConstruccion
	 *            anio de construcci�n del registro de mejora
	 * @param pEstadoMejora
	 *            estado en que se encuentra la mejora
	 * @param pParcela
	 *            parcela a la que pertenece la mejora
	 * @param pDeclaracionJurada
	 *            declaraci�n jurada a la que pertenece la mejora
	 * @param pActivo
	 *            si no ha sido eliminado
	 * @return List listado de registros de mejoras filstrados por los par�metros ingresados
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public java.util.List findListaRegistrosMejora(java.lang.Integer pAnioConstruccion,
			com.trascender.catastro.recurso.persistent.CoeficienteDepreciacion.EstadoCoeficiente pEstadoMejora, com.trascender.catastro.recurso.persistent.Parcela pParcela,
			com.trascender.catastro.recurso.persistent.DeclaracionJurada pDeclaracionJurada, Boolean pActivo) throws Exception {

		return Criterio.getInstance(this.entityManager, RegistroMejora.class).add(Restriccion.IGUAL("estadoMejora", pEstadoMejora))
				.add(Restriccion.IGUAL("anioConstruccion", pAnioConstruccion)).add(Restriccion.IGUAL("parcela", pParcela))
				.add(Restriccion.IGUAL("declaracionJurada", pDeclaracionJurada)).add(Restriccion.IGUAL("activo", pActivo)).list();
	}

	/**
	 * 
	 * @param pNumero
	 * @param pParcela
	 * @return
	 * @throws TrascenderException
	 */
	@SuppressWarnings("unchecked")
	public FiltroPlanoConstruccion findListaPlanosConstruccion(FiltroPlanoConstruccion filtro) throws TrascenderException {

		Criterio locCriterio = Criterio.getInstance(this.entityManager, PlanoConstruccion.class).add(Restriccion.IGUAL("parcela", filtro.getParcela()))
				.add(Restriccion.ILIKE("plano", filtro.getNumero())).setModoDebug(true);

		filtro.procesarYListar(locCriterio);

		return filtro;

	}

	/**
	 * @param pPlanoConstruccion
	 * @throws TrascenderException
	 */
	public com.trascender.catastro.recurso.persistent.PlanoConstruccion updatePlanoConstruccion(com.trascender.catastro.recurso.persistent.PlanoConstruccion pPlanoConstruccion)
			throws Exception {

		this.validarPlanoConstruccion(pPlanoConstruccion);
		this.entityManager.merge(pPlanoConstruccion);
		this.entityManager.flush();
		return pPlanoConstruccion;
	}

	/**
	 * @param pPlanoConstruccion
	 * @throws TrascenderException
	 */
	public com.trascender.catastro.recurso.persistent.PlanoConstruccion addPlanoConstruccion(com.trascender.catastro.recurso.persistent.PlanoConstruccion pPlanoConstruccion)
			throws Exception {

		this.validarPlanoConstruccion(pPlanoConstruccion);
		this.entityManager.merge(pPlanoConstruccion);
		this.entityManager.flush();
		return pPlanoConstruccion;

	}

	/**
	 * Valida que no aya asociado un plano de construccion con el mismo codigo para una misma parcela
	 * 
	 * @param pSession
	 * @param pPlanoConstruccion
	 * @throws Exception
	 */
	private void validarPlanoConstruccion(PlanoConstruccion pPlanoConstruccion) throws Exception {
		if(pPlanoConstruccion == null || pPlanoConstruccion.getParcela() == null) {
			throw new CatastroException(84);
		}

		Criterio locCriterio = Criterio.getInstance(this.entityManager, PlanoConstruccion.class).setProyeccion(Proyeccion.COUNT());

		locCriterio.add(Restriccion.IGUAL("parcela", pPlanoConstruccion.getParcela())).add(Restriccion.ILIKE("plano", pPlanoConstruccion.getPlano()));

		if(pPlanoConstruccion.getIdPlanoConstruccion() != -1) {
			locCriterio.add(Restriccion.NOT(Restriccion.IGUAL("idPlanoConstruccion", pPlanoConstruccion.getIdPlanoConstruccion())));
		}

		if((Long) locCriterio.uniqueResult() > 0) {
			throw new CatastroException(85);
		}

	}

	/**
	 * 
	 * @param pPlanoConstruccion
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "local"
	 */
	public void deletePlanoConstruccion(com.trascender.catastro.recurso.persistent.PlanoConstruccion pPlanoConstruccion) throws Exception {
		pPlanoConstruccion = this.entityManager.find(PlanoConstruccion.class, pPlanoConstruccion.getIdPlanoConstruccion());
		this.entityManager.remove(pPlanoConstruccion);

	}

	/**
	 * @param pParcela
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	@SuppressWarnings("unchecked")
	public Set<ParcelaPorCuadra> getListaCuadrasPorParcela(com.trascender.catastro.recurso.persistent.Parcela pParcela) throws Exception {

		if(pParcela == null) {
			throw new CatastroException(43);
		}
		if(pParcela.getManzana() == null) {
			throw new CatastroException(44);
		}

		Manzana locManzana = pParcela.getManzana();

		if(locManzana.getIdManzana() != -1) {
			System.out.println(locManzana.getIdManzana() + " " + locManzana);

			locManzana = (Manzana) Criterio.getInstance(this.entityManager, Manzana.class).add(Restriccion.IGUAL("idManzana", pParcela.getManzana().getIdManzana())).uniqueResult();

			locManzana.toString();
			for(Cuadra cadaParcelaPorCuadra : locManzana.getListaCuadrasDelimitantes()) {
				cadaParcelaPorCuadra.toString();
			}
		}

		Set<ParcelaPorCuadra> locListaRetorno = pParcela.getListaParcelasPorCuadra();

		for(Cuadra cadaCuadra : locManzana.getListaCuadrasDelimitantes()) {
			boolean existe = false;
			for(ParcelaPorCuadra cadaParcelaPorCuadra : locListaRetorno) {
				existe = existe || cadaParcelaPorCuadra.getCuadra().equals(cadaCuadra);
			}

			if(!existe) {
				cadaCuadra.toString();
				ParcelaPorCuadra locParcelaPorCuadra = new ParcelaPorCuadra();
				locParcelaPorCuadra.setCuadra(cadaCuadra);
				locParcelaPorCuadra.setParcela(pParcela);
				locParcelaPorCuadra.setMetrosPorCuadra(0d);
				pParcela.getListaParcelasPorCuadra().add(locParcelaPorCuadra);
			}
		}

		locManzana.toString();
		for(ParcelaPorCuadra locParcelaPorCuadra : locListaRetorno) {
			locParcelaPorCuadra.getCuadra().toString();
			locParcelaPorCuadra.getParcela().toString();
		}

		return locListaRetorno;
	}

	/**
	 * 
	 * @param pIdParcela
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	@SuppressWarnings("unchecked")
	public java.util.List findListaParcelasPorCuadra(long pIdParcela) throws Exception {
		List<ParcelaPorCuadra> locListaParcelasPorCuadra = Criterio.getInstance(this.entityManager, ParcelaPorCuadra.class).crearAlias("cuadra", "locCuadra")
				.add(Restriccion.IGUAL("locCuadra.idCuadra", pIdParcela)).list();

		for(ParcelaPorCuadra cadaParcelaPorCuadra : locListaParcelasPorCuadra) {
			cadaParcelaPorCuadra.toString();
			cadaParcelaPorCuadra.getParcela().toString();
			if(cadaParcelaPorCuadra.getParcela().getTituloPropiedad() != null) {
				cadaParcelaPorCuadra.getParcela().getTituloPropiedad().toString();
				cadaParcelaPorCuadra.getParcela().getTituloPropiedad().getListaRegistrosPropietarios().toString();
			}

			cadaParcelaPorCuadra.getParcela().getListaParcelasPorCuadra().toString();
			cadaParcelaPorCuadra.getCuadra().toString();
		}

		return locListaParcelasPorCuadra;

	}

	/**
	 * verifica que el número de parcela sea único en la manzanathis
	 * 
	 * @param pParcela
	 *            parcela a verficar
	 * @param pManzana
	 *            manzana a verificar
	 * @return true=si existe y es distinta, false=si no existe
	 */
	private boolean isParcelaRepetidaEnManzana(Parcela pParcela, Manzana pManzana) {
		List<Long> locListaResultado = Criterio.getInstance(this.entityManager, Parcela.class).add(Restriccion.IGUAL("manzana", pManzana))
				.add(Restriccion.IGUAL("nroRegistro", pParcela.getNroRegistro())).add(Restriccion.NOT(Restriccion.IGUAL("idParcela", pParcela.getIdParcela())))
				.setProyeccion(Proyeccion.PROP("idParcela")).list();

		if(locListaResultado.contains(pParcela.getIdParcela())) {
			return true;
		}
		return false;
	}

	/**
	 * Calcula el avaluo por mejoras de una parcela
	 * 
	 * @ejb.interface-method view-type = "local"this
	 */
	@SuppressWarnings("unchecked")
	public Double calcularAvaluoMejoras(com.trascender.catastro.recurso.persistent.Parcela pParcela) throws Exception {
		double avaluo = 0d;
		List listaRegistrosMejora = this.findListaRegistroMejora(pParcela);
		for(Object eachRegistro : listaRegistrosMejora) {
			RegistroMejora locRegistroMejora = (RegistroMejora) eachRegistro;
			int anio = Calendar.getInstance().get(Calendar.YEAR) - locRegistroMejora.getAnioConstruccion();
			CoeficienteDepreciacion locCoeficiente = this.businessCodigosCatastrales.findCoeficienteDepreciacion(anio, locRegistroMejora.getCategoria());

			double valorCoeficienteDepreciacion = 1;
			if(locCoeficiente != null) {
				valorCoeficienteDepreciacion = locCoeficiente.getValor().get(locRegistroMejora.getEstadoMejora());
			}

			ValorBasicoMejora locValorBasicoMejora = this.businessCodigosCatastrales.findValorBasicoMejora(Calendar.getInstance().get(Calendar.YEAR), locRegistroMejora.getCategoria());

			double valorBasicoMejora = 0;
			if(locValorBasicoMejora != null) {
				valorBasicoMejora = locValorBasicoMejora.getValor();
			} else {
				throw new ValorBasicoPorMejoraException(Calendar.getInstance().get(Calendar.YEAR), locRegistroMejora.getCategoria());
			}

			avaluo += (locRegistroMejora.getSuperficie() * valorCoeficienteDepreciacion * valorBasicoMejora);
		}
		return avaluo;
	}

	/**
	 * 
	 * @param pParcela
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	@SuppressWarnings("unchecked")
	public Double getSuperficieMejoras(com.trascender.catastro.recurso.persistent.Parcela pParcela) throws Exception {
		double superficieMejora = 0d;
		List locListaRegistroMejoras = Criterio.getInstance(this.entityManager, RegistroMejora.class).add(Restriccion.IGUAL("parcela", pParcela)).list();

		for(Object cadaObject : locListaRegistroMejoras) {
			RegistroMejora locRegistroMejora = (RegistroMejora) cadaObject;
			superficieMejora += locRegistroMejora.getSuperficie();
		}

		return superficieMejora;
	}

	/**
	 * 
	 */
	public VolanteCatastral generarVolanteCatastral(Parcela pParcela) throws Exception {

		VolanteCatastral locVolanteCatastral = new VolanteCatastral();
		locVolanteCatastral.setAvaluoTotalMejoras(pParcela.getAvaluoPorMejoras());

		if(pParcela.getAvaluoTerreno() == null) {
			locVolanteCatastral.setAvaluoTotalTerreno(0d);
		} else {
			locVolanteCatastral.setAvaluoTotalTerreno(pParcela.getAvaluoTerreno());
		}

		locVolanteCatastral.setFecha(Calendar.getInstance().getTime());
		locVolanteCatastral.setParcela(pParcela);
		// locVolanteCatastral.setRadio(pParcela.getRadioCentrico());

		Criterio locCriterio = Criterio.getInstance(entityManager, VolanteCatastral.class).setProyeccion(Proyeccion.MAX("nroVolanteCatastral"));
		Integer i = locCriterio.uniqueResult();
		System.out.println("NUEVO NRO VOLANTE CATASTRA********************" + i);
		if(i == null) {
			i = 0;
		}
		i = i + 1;

		locVolanteCatastral.setNroVolanteCatastral(i);

		entityManager.merge(locVolanteCatastral);

		// locVolanteCatastral=this.addVolanteCatastral(locVolanteCatastral);

		return locVolanteCatastral;
	}

	/**
	 * Recupera el listado de los ids de todas las parcelas de una calle
	 * 
	 * @param pCalle
	 * @return List con los números de identificación de las parcelas
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	@SuppressWarnings("unchecked")
	public List findListaIdsParcelasPorCalle(Calle pCalle) throws Exception {
		List locListaResultado = new ArrayList();

		List<Object[]> listaListaObjetos = Criterio.getInstance(this.entityManager, Parcela.class).crearAlias("listaParcelasPorCuadra.cuadra.calle", "locCalle")
				.add(Restriccion.IGUAL("locCalle", pCalle)).setProyeccion(Proyeccion.PROP("idParcela")).list();

		for(Object[] cadaObjeto : listaListaObjetos) {
			locListaResultado.add(cadaObjeto[0]);
		}

		return locListaResultado;
	}

	public Calle getCallePorId(Long pId) throws Exception {
		return Criterio.getInstance(this.entityManager, Calle.class).add(Restriccion.IGUAL("idCalle", pId)).uniqueResult();
	}

	/**
	 * 
	 */
	public Parcela mensurarSubParcela(SubParcela pSubParcela, PlanoMensura pPlanoMensura) throws Exception {
		if(pSubParcela == null) {
			throw new CatastroException(712);
		}

		if(pPlanoMensura == null) {
			throw new CatastroException(713);
		}

		System.out.println(pPlanoMensura);

		if(pSubParcela.getNroCuenta() == null || pSubParcela.getNroMatricula() == null || pSubParcela.getNroPartidaProvincial() == null) {
			throw new CatastroException(715);
		}

		Parcela locParcela = this.addParcela(pSubParcela.parseToParcela());

		Parcela locParcelaPadre = this.getParcelaPorId(pSubParcela.getPadre().getIdParcela());
		if(locParcelaPadre.getListaSubParcelas().contains(pSubParcela)) {
			locParcelaPadre.getListaSubParcelas().remove(pSubParcela);
		} else {
			throw new CatastroException(714);
		}

		try {
			this.entityManager.createQuery("UPDATE DocHabilitanteEspecializado doc SET doc.parcela = :locParcela " + "WHERE doc.parcela = :locParcelaOriginal")
					.setParameter("locParcela", locParcela).setParameter("locParcelaOriginal", pSubParcela).executeUpdate();
			this.entityManager.merge(locParcelaPadre);
			pPlanoMensura.setParcela(locParcela);
			System.out.println(pPlanoMensura + "  " + pPlanoMensura.getFechaInscripcion());
			this.addPlanoMensura(pPlanoMensura);
		} catch(Exception e) {
			// hago los rollbacks aca
			locParcelaPadre.addSubParcela(pSubParcela);
			this.entityManager.createQuery("UPDATE DocHabilitanteEspecializado doc SET doc.parcela = :locParcela").setParameter("locParcela", pSubParcela).executeUpdate();
			this.entityManager.remove(locParcela);
			this.entityManager.flush();
			e.printStackTrace();
		}

		return locParcela;
	}

	public String getSugerenciaNumeroParcela() {
		Object resultado = null;

		try {
			resultado = Criterio.getInstance(this.entityManager, Parcela.class).add(Restriccion.IGUAL("estado", Estado.ACTIVO))
					.add(Restriccion.MENOR("cast(nomenclaturaCatastral.nroParcela as integer)", 50000).SIN_PROCESAR_ENTIDADES())
					.setProyeccion(Proyeccion.PROP("MAX(cast(nomenclaturaCatastral.nroParcela as integer))").SIN_PROCESAR_ENTIDADES()).uniqueResult();
		} catch(Exception e) {
			return "";
		}

		return resultado != null ? (Integer.valueOf(resultado.toString()) == null ? "1" : (Integer.valueOf(resultado.toString())) + 1).toString() : "1";
	}

	public Long getSugerenciaNumeroRegistro() {
		Criterio locCriterio = Criterio.getInstance(entityManager, Parcela.class).add(Restriccion.IGUAL("estado", Estado.ACTIVO))
				.add(Restriccion.MENOR("cast(nroRegistro as integer)", 50000).SIN_PROCESAR_ENTIDADES())
				.setProyeccion(Proyeccion.PROP("MAX(cast(nroRegistro as integer))").SIN_PROCESAR_ENTIDADES());

		Integer numeroRegistro = locCriterio.uniqueResult();
		if(numeroRegistro == null) {
			numeroRegistro = 0;
		}
		numeroRegistro++;

		return numeroRegistro.longValue();
	}

	private void validarAsociacionesParcela(Parcela pParcela) {
		List<AsociacionParcela> locListaAsociacionesPersistidas = new ArrayList<AsociacionParcela>();

		for(AsociacionParcela cadaAsociacion : pParcela.getListaAsociacionParcela()) {
			if(cadaAsociacion.getIdAsociacionParcela() != -1) {
				locListaAsociacionesPersistidas.add(cadaAsociacion);
			}
		}

		Criterio locCriterio = Criterio.getInstance(this.entityManager, AsociacionParcela.class).add(Restriccion.IGUAL("parcela", pParcela))
				.add(Restriccion.NOT(Restriccion.EN("e", locListaAsociacionesPersistidas)));
		locCriterio.setModoDebug(true);

		for(Object cadaObj : locCriterio.list()) {
			AsociacionParcela cadaAsociacion = (AsociacionParcela) cadaObj;
			entityManager.remove(cadaAsociacion);
		}
	}

	public List<AuxIdEntidad> findListaAuxIdParcela(String cadena) {
		Criterio locCriterio = Criterio.getInstance(entityManager, Parcela.class);

		locCriterio.crearAlias("tituloPropiedad.listaRegistrosPropietarios.persona", "cadaPersona");
		locCriterio.crearAlias("nomenclaturaCatastral", "locNomenclatura");

		locCriterio.add(Restriccion.IGUAL("estado", Parcela.Estado.ACTIVO));
		locCriterio.add(Restriccion.ILIKE("locNomenclatura.nroParcela||' '||cadaPersona.apellido||' '||cadaPersona.nombre", cadena).SIN_PROCESAR_ENTIDADES());

		locCriterio.setProyeccion(Proyeccion.NEW(AuxIdEntidad.class, "e.idParcela", "cadaPersona.apellido||', '||cadaPersona.nombre||' ['||locNomenclatura.nroParcela||']'")
				.SIN_PROCESAR_ENTIDADES());

		locCriterio.setModoDebug(true);

		List<AuxIdEntidad> locListaParcelas = locCriterio.list();

		return locListaParcelas;
	}

	private void validarPropietarioObligacion(Parcela pParcela) {

		RegistroPropietario locRegPropActual = pParcela.getTituloPropiedad().getRegistroPropietarioEncargadoObligaciones();

		TituloPropiedad locTituloPropPersistido = Criterio.getInstance(entityManager, TituloPropiedad.class)
				.add(Restriccion.IGUAL("idTituloPropiedad", pParcela.getTituloPropiedad().getIdTituloPropiedad())).uniqueResult();
		RegistroPropietario locRegPropPersistido = locTituloPropPersistido.getRegistroPropietarioEncargadoObligaciones();

		if(!locRegPropActual.equals(locRegPropPersistido)) {
			List<ObligacionRfr> locListaObligaciones = Criterio.getInstance(entityManager, DocHabEspecializadoRfr.class).add(Restriccion.IGUAL("parcela", pParcela))
					.setProyeccion(Proyeccion.PROP("obligacion")).list();

			for(ObligacionRfr cadaObligacion : locListaObligaciones) {
				cadaObligacion.setPersona(locRegPropActual.getPersona());
			}
		}

	}
}