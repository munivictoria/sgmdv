
package com.trascender.habilitaciones.business.ejb;

import static com.trascender.habilitaciones.recurso.persistent.TipoParametroVencimiento.TipoAtributoVencimiento.DIAS_DESDE_INICIO_PERIODO;
import static com.trascender.habilitaciones.recurso.persistent.TipoParametroVencimiento.TipoAtributoVencimiento.DIAS_DESDE_PRIMER_VENCIMIENTO;
import static com.trascender.habilitaciones.recurso.persistent.TipoParametroVencimiento.TipoAtributoVencimiento.IMPORTE_PRIMER_VENCIMIENTO;
import static com.trascender.habilitaciones.recurso.persistent.TipoParametroVencimiento.TipoAtributoVencimiento.MESES_DESDE_INICIO_PERIODO;
import static com.trascender.habilitaciones.recurso.persistent.TipoParametroVencimiento.TipoAtributoVencimiento.MESES_DESDE_PRIMER_VENCIMIENTO;

import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.ejb.CreateException;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.nfunk.jep.JEP;
import org.nfunk.jep.ParseException;

import ar.trascender.criterio.clases.Criterio;
import ar.trascender.criterio.clases.Proyeccion;
import ar.trascender.criterio.clases.Restriccion;
import ar.trascender.criterio.enums.Posicion;

import com.trascender.catastro.business.interfaces.BusinessZonificacionLocal;
import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.catastro.recurso.persistent.Zona;
import com.trascender.catastro.recurso.persistent.Zonificacion;
import com.trascender.framework.business.interfaces.BusinessCalendarioLocal;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.exception.TrascenderFrameworkException;
import com.trascender.framework.recurso.persistent.FirmaPermiso;
import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.framework.recurso.persistent.PersonaJuridica;
import com.trascender.framework.recurso.persistent.dinamicos.PlantillaAtributoDinamico;
import com.trascender.framework.recurso.transients.AtributoConsultable;
import com.trascender.framework.recurso.transients.Grupo;
import com.trascender.framework.recurso.transients.Periodo;
import com.trascender.framework.recurso.transients.Recurso;
import com.trascender.framework.system.interfaces.SystemUsuario;
import com.trascender.framework.util.BusquedaPorLog;
import com.trascender.framework.util.EntidadTrascender;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.framework.util.TrascenderEnverListener;
import com.trascender.framework.util.Util;
import com.trascender.habilitaciones.business.interfaces.BusinessTipoTasaLocal;
import com.trascender.habilitaciones.exception.HabilitacionesErrorFormulaException;
import com.trascender.habilitaciones.exception.HabilitacionesException;
import com.trascender.habilitaciones.recurso.filtros.FiltroPlan;
import com.trascender.habilitaciones.recurso.filtros.FiltroTipoParametroConstante;
import com.trascender.habilitaciones.recurso.filtros.FiltroTipoParametroGrilla;
import com.trascender.habilitaciones.recurso.filtros.FiltroTipoParametroGrupoZona;
import com.trascender.habilitaciones.recurso.filtros.FiltroTipoTasa;
import com.trascender.habilitaciones.recurso.persistent.ConceptoPorMora;
import com.trascender.habilitaciones.recurso.persistent.CuotaLiquidacion;
import com.trascender.habilitaciones.recurso.persistent.LineaTipoParametroConstante;
import com.trascender.habilitaciones.recurso.persistent.PermisoTipoTasa;
import com.trascender.habilitaciones.recurso.persistent.Plan;
import com.trascender.habilitaciones.recurso.persistent.RegAlicuota;
import com.trascender.habilitaciones.recurso.persistent.TipoModificador;
import com.trascender.habilitaciones.recurso.persistent.TipoObligacion;
import com.trascender.habilitaciones.recurso.persistent.TipoParametro;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroAlicuota;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroAlicuotaOSP;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroAlicuotaOSPDinamico;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroAlicuotaSHPS;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroAlicuotaSHPS.AtributoAlicuotaSHPS;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroAlicuotaSHPSDinamico;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroAutomotor;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroAutomotorDinamico;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroCementerio;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroCementerioDinamico;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroConstante;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroDeuda;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroDinamico;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroGrupoZona;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroMarcaDinamico;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroModeloDinamico;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroOSP;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroObra;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroPFO;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroParcelaCementerio;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroParcelaCementerio.AtributoParcelaCementerio;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroParcelaCementerioDinamico;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroParcelario;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroParcelarioDinamico;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroPersona;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroPersonaFisicaDinamico;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroPersonaJuridicaDinamico;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroSHPS;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroTGI;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroTipoSepultura;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroTipoSepultura.AtributoTipoSepultura;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroTipoSepulturaDinamico;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroTipoVehiculoDinamico;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroVehiculo;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroVehiculoDinamico;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroVencimiento;
import com.trascender.habilitaciones.recurso.persistent.TipoTasa;
import com.trascender.habilitaciones.recurso.persistent.TipoTasa.Estado;
import com.trascender.habilitaciones.recurso.persistent.TipoVencimiento;
import com.trascender.habilitaciones.recurso.persistent.VariableFormula;
import com.trascender.habilitaciones.recurso.persistent.VariableFormulaCompuesta;
import com.trascender.habilitaciones.recurso.persistent.VariableFormulaSimple;
import com.trascender.habilitaciones.recurso.persistent.cementerio.DocumentoCementerio;
import com.trascender.habilitaciones.recurso.persistent.cementerio.ParcelaCementerio;
import com.trascender.habilitaciones.recurso.persistent.cementerio.TipoSepultura;
import com.trascender.habilitaciones.recurso.persistent.enums.TipoParametroInteres;
import com.trascender.habilitaciones.recurso.persistent.osp.DocumentoOSP;
import com.trascender.habilitaciones.recurso.persistent.osp.ServicioOSP;
import com.trascender.habilitaciones.recurso.persistent.pfo.DocumentoPlanObra;
import com.trascender.habilitaciones.recurso.persistent.shps.DocumentoSHPS;
import com.trascender.habilitaciones.recurso.persistent.shps.Rubro;
import com.trascender.habilitaciones.recurso.persistent.tasaMenor.PlantillaDocumentoTasaMenor;
import com.trascender.habilitaciones.recurso.persistent.tgi.DocumentoTGI;
import com.trascender.habilitaciones.recurso.persistent.tipoParametroGrilla.TipoParametroGrilla;
import com.trascender.habilitaciones.recurso.persistent.tipoParametroGrilla.TipoParametroGrillaFila;
import com.trascender.habilitaciones.recurso.persistent.tipoParametroGrilla.TipoParametroGrillaFilaColumna;
import com.trascender.habilitaciones.recurso.persistent.tipoParametroGrilla.TipoParametroGrillaVariable;
import com.trascender.habilitaciones.recurso.persistent.transito.DocumentoAutomotor;
import com.trascender.habilitaciones.recurso.persistent.transito.Marca;
import com.trascender.habilitaciones.recurso.persistent.transito.Modelo;
import com.trascender.habilitaciones.recurso.persistent.transito.TipoVehiculo;
import com.trascender.habilitaciones.recurso.persistent.transito.Vehiculo;
import com.trascender.habilitaciones.util.FuncionBoba;
import com.trascender.habilitaciones.util.GrillaFunction;
import com.trascender.habilitaciones.util.MotorFormulas;

@Stateless(name = "ejb/BusinessTipoTasaLocal")
public class BusinessTipoTasaBean implements BusinessTipoTasaLocal {
	private Double locValor = 0d;
	private JEP jep;

	@EJB
	private BusinessZonificacionLocal businessZonificacion;

	@EJB
	private BusinessCalendarioLocal businessCalendario;

	@PersistenceContext(name = "Vipians")
	private EntityManager entityManager;

	/*
	 * los getListaParametrosAlgo son redundantes, pero a falta de tiempo no existe la posibilidad de mejorarlos, (dejado para otra version)
	 */
	private static final long serialVersionUID = -503058017218512453L;
	static {
		Grupo grupo = new Grupo();
		grupo.setId(serialVersionUID);
		grupo.setNombre("HAB|Adm. de Fórmulas de Cálculo");

		Recurso tipoModificador = new Recurso();
		tipoModificador.setIdRecurso(TipoModificador.serialVersionUID);
		tipoModificador.setNombre("Tipo de Modificador");
		tipoModificador.setClase(TipoModificador.class);
		grupo.getListaRecursos().add(tipoModificador);

		Recurso tipoTasa = new Recurso();
		tipoTasa.setIdRecurso(TipoTasa.serialVersionUID);
		tipoTasa.setNombre("Fórmula de Cálculo");
		tipoTasa.setAtributosConsultables("Nombre", "nombre", "Tipo de Obligación", "nombreTipoObligacion", "Plan", "plan", "Cuotas Limitadas", "fija", "Estado", "estado");
		tipoTasa.setClase(TipoTasa.class);
		grupo.getListaRecursos().add(tipoTasa);

		Recurso tipoParametroConstante = new Recurso();
		tipoParametroConstante.setIdRecurso(TipoParametroConstante.serialVersionUID);
		tipoParametroConstante.setNombre("Parámetro Constante");
		tipoParametroConstante.setAtributosConsultables("Nombre", "nombre", "Nombre de Variable", "nombreVariable", "Valor", "valor", "Tipo", "tipoValor");
		tipoParametroConstante.setClase(TipoParametroConstante.class);
		grupo.getListaRecursos().add(tipoParametroConstante);

		Recurso tipoParametroGrilla = new Recurso();
		tipoParametroGrilla.setIdRecurso(TipoParametroGrilla.serialVersionUID);
		tipoParametroGrilla.setNombre("Tipo Parametro Grilla");
		tipoParametroGrilla.setAtributosConsultables("Nombre", "nombreVariable");
		tipoParametroGrilla.setClase(TipoParametroGrilla.class);
		grupo.getListaRecursos().add(tipoParametroGrilla);

		Recurso tipoParametroParcelario = new Recurso();
		tipoParametroParcelario.setIdRecurso(TipoParametroParcelario.serialVersionUID);
		tipoParametroParcelario.setNombre("Parámetro Parcelario");
		tipoParametroParcelario.setClase(TipoParametroParcelario.class);
		grupo.getListaRecursos().add(tipoParametroParcelario);

		Recurso tipoParametroObra = new Recurso();
		tipoParametroObra.setIdRecurso(TipoParametroObra.serialVersionUID);
		tipoParametroObra.setNombre("Parámetro de Obra");
		tipoParametroObra.setClase(TipoParametroObra.class);
		grupo.getListaRecursos().add(tipoParametroObra);

		Recurso tipoParametroGrupoZona = new Recurso();
		tipoParametroGrupoZona.setIdRecurso(TipoParametroGrupoZona.serialVersionUID);
		tipoParametroGrupoZona.setNombre("Grupo de Zonas");
		tipoParametroGrupoZona.setAtributosConsultables("Nombre", "nombreGrupoZona", "Nombre de Variable", "nombreVariable", "Zonificacion", "zonificacion");
		tipoParametroGrupoZona.setClase(TipoParametroGrupoZona.class);
		grupo.getListaRecursos().add(tipoParametroGrupoZona);

		Recurso tipoParametroPersonas = new Recurso();
		tipoParametroPersonas.setIdRecurso(TipoParametroPersona.serialVersionUID);
		tipoParametroPersonas.setNombre("Parámetro de Personas");
		tipoParametroPersonas.setClase(TipoParametroPersona.class);
		grupo.getListaRecursos().add(tipoParametroPersonas);

		Recurso tipoParametroOSP = new Recurso();
		tipoParametroOSP.setIdRecurso(TipoParametroOSP.serialVersionUID);
		tipoParametroOSP.setNombre("Parámetro de OySP");
		tipoParametroOSP.setClase(TipoParametroOSP.class);
		grupo.getListaRecursos().add(tipoParametroOSP);

		Recurso tipoParametroSHPS = new Recurso();
		tipoParametroSHPS.setIdRecurso(TipoParametroSHPS.serialVersionUID);
		tipoParametroSHPS.setNombre("Parámetro de SHPS");
		tipoParametroSHPS.setClase(TipoParametroSHPS.class);
		grupo.getListaRecursos().add(tipoParametroSHPS);

		Recurso tipoParametroPFO = new Recurso();
		tipoParametroPFO.setIdRecurso(TipoParametroPFO.serialVersionUID);
		tipoParametroPFO.setNombre("Parámetro de PFO");
		tipoParametroPFO.setClase(TipoParametroPFO.class);
		grupo.getListaRecursos().add(tipoParametroPFO);

		Recurso tipoParametroTGI = new Recurso();
		tipoParametroTGI.setIdRecurso(TipoParametroTGI.serialVersionUID);
		tipoParametroTGI.setNombre("Parámetro de TGI");
		tipoParametroTGI.setClase(TipoParametroTGI.class);
		grupo.getListaRecursos().add(tipoParametroTGI);
		
		Recurso tipoParametroDeuda = new Recurso();
		tipoParametroDeuda.setIdRecurso(TipoParametroDeuda.serialVersionUID);
		tipoParametroDeuda.setNombre("Parámetros Deuda");
		tipoParametroDeuda.setClase(TipoParametroDeuda.class);
		grupo.getListaRecursos().add(tipoParametroDeuda);

		Recurso tipoParametroVencimiento = new Recurso();
		tipoParametroVencimiento.setIdRecurso(TipoParametroVencimiento.serialVersionUID);
		tipoParametroVencimiento.setNombre("Parámetro de Vencimiento");
		tipoParametroVencimiento.setClase(TipoParametroVencimiento.class);
		grupo.getListaRecursos().add(tipoParametroVencimiento);

		Recurso permisoTipoTasa = new Recurso();
		permisoTipoTasa.setIdRecurso(PermisoTipoTasa.serialVersionUID);
		permisoTipoTasa.setNombre("Permiso de Activación de Fórmulas de cálculo");
		permisoTipoTasa.setClase(PermisoTipoTasa.class);
		grupo.getListaRecursos().add(permisoTipoTasa);
		
		Recurso cuotaLiquidacion = new Recurso();
		cuotaLiquidacion.setIdRecurso(CuotaLiquidacion.serialVersionUID);
		cuotaLiquidacion.setNombre("Cuota Liquidación");
		cuotaLiquidacion.setClase(CuotaLiquidacion.class);
		grupo.getListaRecursos().add(cuotaLiquidacion);

		Recurso plan = new Recurso();
		plan.setIdRecurso(Plan.serialVersionUID);
		plan.setNombre("Planes");
		plan.setAtributosConsultables("Nombre", "nombre", "Tipo Obligacion", "tipoObligacion");
		plan.addAtributoConsultable(new AtributoConsultable("porDefecto", "Por defecto", AtributoConsultable.Tipo.BOOLEANO));
		plan.setClase(Plan.class);
		grupo.getListaRecursos().add(plan);

		SecurityMgr.getInstance().addGrupo(grupo);
	}

	public BusinessTipoTasaBean() {
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
	 * Método para obtener el motor de fórmulas JEP
	 */
	private JEP getMotorFormulas() {
		if(this.jep == null) {
			this.jep = MotorFormulas.initializeJEP();
		}
		return this.jep;

	}

	/**
	 * Agrega un tipoTasa
	 * 
	 * @param pTipoTasa
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public TipoTasa addTipoTasa(TipoTasa pTipoTasa) throws Exception {
		TipoTasa locTipoTasaNueva = this.addNewTipoTasa(pTipoTasa);

		return locTipoTasaNueva;
	}

	/**
	 * Agrega un nuevo tipo de tasa
	 * 
	 * @param pTipoTasa
	 * @return
	 */
	private TipoTasa addNewTipoTasa(TipoTasa pTipoTasa) throws Exception {
		if(!pTipoTasa.getEstado().equals(TipoTasa.Estado.EN_ESPERA)) {
			throw new HabilitacionesException(114);
		}

		pTipoTasa.setIdTipoTasa(-1);
		this.verificarTipoTasa(pTipoTasa);
		for(TipoVencimiento locTipoVencimiento : pTipoTasa.getListaVencimientos()) {
			locTipoVencimiento.setTipoTasa(pTipoTasa);
		}

		for(TipoModificador locTipoModificador : pTipoTasa.getListaModificadores()) {
			locTipoModificador.setTipoTasa(pTipoTasa);
		}

		TrascenderEnverListener.setValoresEnAuditoriaBean(pTipoTasa);
		this.entityManager.persist(pTipoTasa);
		this.entityManager.flush();
		this.entityManager.refresh(pTipoTasa);

		return pTipoTasa;
	}

	/**
	 * Actualiza los datos de un tipo de tasa
	 * 
	 * @param pTipoTasa
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public TipoTasa updateTipoTasa(TipoTasa pTipoTasa) throws Exception {
		/*
		 * if (!pTipoTasa.getEstado().equals(TipoTasa.Estado.EN_ESPERA)){ throw new HabilitacionesException(113); }
		 */
		this.verificarTipoTasa(pTipoTasa);

		// if(pTipoTasa.getInteres() != null){
		// this.entityManager.merge(pTipoTasa.getInteres());
		// }
		//
		// if(pTipoTasa.getRecargo() != null ){
		// this.entityManager.merge(pTipoTasa.getRecargo());
		// }

		// HASTA ACÁ
		TrascenderEnverListener.setValoresEnAuditoriaBean(pTipoTasa);
		pTipoTasa = this.entityManager.merge(pTipoTasa);
		entityManager.flush();
		return pTipoTasa;
	}

	/**
	 * Verifica que el tipo de tasa tenga los parámetos requeridos correctamente ingresados y llama a la validación de la fórmula
	 * 
	 * @param pTipoTasa
	 * @throws Exception
	 */
	private void verificarTipoTasa(TipoTasa pTipoTasa) throws Exception {
		this.validarFormula(pTipoTasa);
		if(pTipoTasa.getFechaCreacion() == null) {
			pTipoTasa.setFechaCreacion(Calendar.getInstance().getTime());
		}
		if(pTipoTasa.getFechaVigenciaDesde() == null) {
			pTipoTasa.setFechaVigenciaDesde(Calendar.getInstance().getTime());
		}

		for(TipoModificador locTipoModificador : pTipoTasa.getListaModificadores()) {
			locTipoModificador.setTipoTasa(pTipoTasa);
		}
		for(TipoVencimiento locTipoVencimiento : pTipoTasa.getListaVencimientos()) {
			locTipoVencimiento.setTipoTasa(pTipoTasa);
		}
		pTipoTasa.juntarVariables();
		// Tipo de vencimiento por defecto
		if(pTipoTasa.getListaVencimientos().isEmpty()) {
			TipoVencimiento locTipoVencimiento = new TipoVencimiento();
			locTipoVencimiento.setDias(0);
			locTipoVencimiento.setMeses(0);
			locTipoVencimiento.setNombre("Primer Vencimiento");
			locTipoVencimiento.setTipoTasa(pTipoTasa);
			pTipoTasa.getListaVencimientos().add(locTipoVencimiento);
		}

	}

	/**
	 * Dá de baja un tipo de parámetro
	 * 
	 * @param pTipoParametro
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public void deleteTipoParametro(TipoParametro pTipoParametro) throws Exception {
		// recupero el estado en que se encontraba en la bd
		pTipoParametro = this.entityManager.find(TipoParametro.class, pTipoParametro.getIdTipoParametro());

		pTipoParametro.setActivo(false);
		pTipoParametro.setFechaBaja(Calendar.getInstance().getTime());

		this.entityManager.merge(pTipoParametro);
	}

	/**
	 * 
	 * @param pTipoTasa
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public void deleteFisicamenteTipoTasa(TipoTasa pTipoTasa) throws Exception {
		pTipoTasa = this.entityManager.getReference(TipoTasa.class, pTipoTasa.getIdTipoTasa());
		this.entityManager.remove(pTipoTasa);
	}

	/**
	 * 
	 * @param pTipoTasa
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public void deleteTipoTasa(TipoTasa pTipoTasa) throws Exception {
		if(!pTipoTasa.getEstado().equals(Estado.EN_ESPERA)) {
			throw new HabilitacionesException(124);
		}

		pTipoTasa.setFechaBaja(Calendar.getInstance().getTime());
		pTipoTasa.setEstado(com.trascender.habilitaciones.recurso.persistent.TipoTasa.Estado.INACTIVA);
		// pTipoTasa.activar();
		TrascenderEnverListener.setValoresEnAuditoriaBean(pTipoTasa);
		this.entityManager.merge(pTipoTasa);
		this.entityManager.flush();
	}

	/**
	 * Recupera el estado anterior de un tipo de tasa
	 * 
	 * @param pTipoTasa
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public TipoTasa restoreTipoTasa(TipoTasa pTipoTasa) throws Exception {
		pTipoTasa.activar();
		pTipoTasa.setFechaBaja(null);
		return pTipoTasa;
	}

	/**
	 * Recupera el listado de tipos de tasa
	 * 
	 * @param pNombre
	 * @param pEstado
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public FiltroTipoTasa findListaTipoTasa(FiltroTipoTasa pFiltro) throws Exception {

		Criterio locCriterio = Criterio.getInstance(this.entityManager, TipoTasa.class).setModoDebug(true).add(Restriccion.ILIKE("nombre", pFiltro.getNombre()))
				.add(Restriccion.IGUAL("plan.tipoObligacion", pFiltro.getTipoObligacion())).add(Restriccion.IGUAL("periodicidad", pFiltro.getPeriodicidad()))
				.add(Restriccion.IGUAL("estado", pFiltro.getEstado()));

		BusquedaPorLog.addRestriccionesCriterio(locCriterio, TipoTasa.serialVersionUID, "idTipoBien", pFiltro.getListaBusquedaPorLogs());

		pFiltro.procesarYListar(locCriterio);
		for(TipoTasa cadaTipoTasa : pFiltro.getListaResultados()) {
			cadaTipoTasa.toString();
			for(TipoParametro cadaParam : cadaTipoTasa.getListaParametros()) {
				cadaParam.toString();
			}
		}
		return pFiltro;
	}

	/**
	 * Valida la expresión matemática del atributo formula del tipo de tasa pasado por parámetro
	 * 
	 * @param pTipoTasa
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public void validarFormula(TipoTasa pTipoTasa) throws Exception {
		JEP jep = this.getMotorFormulas();

		List<TipoParametro> locListaTiposParametrosSistema = Criterio.getInstance(this.entityManager, TipoParametro.class).detachedList();

		for(TipoParametro cadaTipoParametro : locListaTiposParametrosSistema) {
			boolean locExiste = false;
			Iterator<TipoParametro> locIterador = pTipoTasa.getListaParametros().iterator();
			while(locIterador.hasNext() && !locExiste) {
				TipoParametro locTipoParametro = locIterador.next();
				if(cadaTipoParametro.getNombreVariable().equals(locTipoParametro.getNombreVariable())) {
					locExiste = true;
				}
			}

			if(pTipoTasa.getFormula().contains(cadaTipoParametro.getNombreVariable()) && !locExiste) {
				throw new HabilitacionesErrorFormulaException(cadaTipoParametro);
			}
		}

		// Variables
		for(VariableFormulaSimple cadaVariable : pTipoTasa.getListaVariablesSimple()) {
			jep.parseExpression(cadaVariable.getExpresion());
			cadaVariable.setValor(jep.getValue());
			jep.addVariable(cadaVariable.getNombre(), cadaVariable.getValor());
		}

		List<TipoParametroVencimiento> listaParametrosVencimientos = null;
		for(TipoParametro locTipoParametro : pTipoTasa.getListaParametros()) {
			if(locTipoParametro instanceof TipoParametroVencimiento) {
				if(listaParametrosVencimientos == null) {
					listaParametrosVencimientos = new ArrayList<TipoParametroVencimiento>();
				}
				listaParametrosVencimientos.add((TipoParametroVencimiento) locTipoParametro);
			} else if (locTipoParametro instanceof TipoParametroGrilla) {
				TipoParametroGrilla locGrilla = (TipoParametroGrilla) locTipoParametro;
				jep.addFunction(locGrilla.getNombreVariable(), new GrillaFunction(locGrilla));
			} else {
				jep.addVariable(locTipoParametro.getNombreVariable(), 0d);
			}
		}

		jep.parseExpression(pTipoTasa.getFormula());
		jep.getValueAsObject();
		String locErrorInfo = jep.getErrorInfo();
		System.err.println(locErrorInfo);
		if(locErrorInfo != null) {
			throw new HabilitacionesException(111);
		}

		// VALÍDO LOS MODIFICADORES AHORA
		for(TipoModificador locModificador : pTipoTasa.getListaModificadores()) {

			// Variables
			for(VariableFormula cadaVariable : locModificador.getListaVariables()) {
				jep.parseExpression(cadaVariable.getExpresion());
				jep.addVariable(cadaVariable.getNombre(), jep.getValue());
			}

			jep.parseExpression(locModificador.getCondicion());
			jep.getValue();
			String locErrorInfoModificador = jep.getErrorInfo();
			System.err.println(locErrorInfoModificador);
			if(locErrorInfoModificador != null) {
				locErrorInfoModificador = locModificador.getNombre() + ". " + locErrorInfoModificador;
				throw new HabilitacionesException(115, locErrorInfoModificador);
			}
		}

		if(listaParametrosVencimientos != null) {
			for(TipoParametro locTipoParametro : listaParametrosVencimientos) {
				jep.addVariable(locTipoParametro.getNombreVariable(), 0d);
			}
		}

		for(TipoVencimiento locTipoVencimiento : pTipoTasa.getListaVencimientos()) {
			if(locTipoVencimiento.getFormulaCalculo() != null && !locTipoVencimiento.getFormulaCalculo().equals("")) {
				System.out.println("formula de cadaTipoVencimiento: " + locTipoVencimiento.getFormulaCalculo());
				jep.parseExpression(locTipoVencimiento.getFormulaCalculo());
				jep.getValue();
				String locErrorInfoVenc = jep.getErrorInfo();
				if(locErrorInfoVenc != null) {
					System.out.println("error jep: " + locErrorInfoVenc);
					throw new HabilitacionesException(116);
				}
			}
		}

		// Valido el interés y el recargo
		jep.addVariable(TipoParametroInteres.IMPORTE_INTERES.getNombreVariable(), 0f);
		//Se agrega la funcion VALOR_MODIFICADOR del modulo SAIC con una funcion boba.
		jep.addFunction("VALOR_MODIFICADOR", new FuncionBoba());
		if(pTipoTasa.getInteres() != null && pTipoTasa.getInteres().getFormula() != null) {
			jep.parseExpression(pTipoTasa.getInteres().getFormula());
			jep.getValue();
			locErrorInfo = jep.getErrorInfo();
			if(locErrorInfo != null) {
				throw new HabilitacionesException(122);
			}
		}

		if(pTipoTasa.getRecargo() != null && pTipoTasa.getRecargo().getFormula() != null) {
			jep.parseExpression(pTipoTasa.getRecargo().getFormula());
			jep.getValue();
			locErrorInfo = jep.getErrorInfo();
			if(locErrorInfo != null) {
				throw new HabilitacionesException(123);
			}
		}
	}

	/**
	 * Obtiene el listado de parámetros parcelarios
	 * 
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	@SuppressWarnings("unchecked")
	public List getListaParametrosParcelarios() throws Exception {
		List locListaRetorno = this.getListaTipoParametro(TipoParametroParcelario.class, TipoParametroParcelario.AtributoParcela.class);
		locListaRetorno.addAll(getListaParametrosDinamicos(Parcela.serialVersionUID, TipoParametroParcelarioDinamico.class, null));
		return locListaRetorno;
	}

	/**
	 * Obtiene el listado de parámetros parcelarios
	 * 
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	@SuppressWarnings("unchecked")
	public List getListaParametrosTGI() throws Exception {

		List locListaRetorno = this.getListaTipoParametro(TipoParametroTGI.class, TipoParametroTGI.TipoAtributoTGI.class);
		locListaRetorno.addAll(getListaParametrosDinamicos(DocumentoTGI.serialVersionUID, TipoParametroDinamico.class, null));

		return locListaRetorno;
	}

	// /**
	// * Recupera un parámetro parcelario según un atributo parcela (utilizado para dar de alta los parámetros parcelarios)
	// * @param pTipoAtributoTGI
	// * @return
	// */
	// private TipoParametroTGI getParametroTGIFromTipoAtributoTGI(TipoParametroTGI.TipoAtributoTGI pTipoAtributoTGI){
	// TipoParametroTGI locTipoParametro=new TipoParametroTGI();
	// locTipoParametro.setActivo(true);
	// locTipoParametro.setAtributoTGI(pTipoAtributoTGI);
	// locTipoParametro.setFechaAlta(Calendar.getInstance().getTime());
	// if (pTipoAtributoTGI.equals(TipoParametroTGI.TipoAtributoTGI.ES_ALGUN_PROPIETARIO_MOROSO_TGI)){
	// locTipoParametro.setBooleano(true);
	// }
	// locTipoParametro.setNombreVariable(Util.getEnumNameFromString(pTipoAtributoTGI.toString()));
	// return locTipoParametro;
	// }
	//
	// /**
	// * Recupera un parámetro parcelario según un atributo parcela (utilizado para dar de alta los parámetros parcelarios)
	// * @param pAtributoParcela
	// * @return
	// */
	// private TipoParametroParcelario getParametroParcelarioFromAtributoParcela(TipoParametroParcelario.AtributoParcela pAtributoParcela){
	// TipoParametroParcelario locTipoParametro=new TipoParametroParcelario();
	// locTipoParametro.setActivo(true);
	// locTipoParametro.setAtributoParcela(pAtributoParcela);
	// locTipoParametro.setFechaAlta(Calendar.getInstance().getTime());
	// if ((pAtributoParcela.equals(TipoParametroParcelario.AtributoParcela.ES_BALDIO))
	// ||(pAtributoParcela.equals(TipoParametroParcelario.AtributoParcela.ES_RADIO_CENTRICO))
	// || (pAtributoParcela.equals(TipoParametroParcelario.AtributoParcela.EN_LOTEO))
	// || (pAtributoParcela.equals(TipoParametroParcelario.AtributoParcela.EN_POSESION))
	// || (pAtributoParcela.equals(TipoParametroParcelario.AtributoParcela.ES_EXENTO_DE_RECARGO_POR_BALDIO))
	// || (pAtributoParcela.equals(TipoParametroParcelario.AtributoParcela.ES_PROPIEDAD_HORIZONTAL))
	// || (pAtributoParcela.equals(TipoParametroParcelario.AtributoParcela.ES_PROPIETARIOS_JUBILADOS))){
	// locTipoParametro.setBooleano(true);
	// }
	//
	// locTipoParametro.setNombreVariable(Util.getEnumNameFromString(pAtributoParcela.toString()));
	// return locTipoParametro;
	// }
	/**
	 * 
	 * @param pTipoParametroConstante
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public TipoParametroConstante addTipoParametroConstante(TipoParametroConstante pTipoParametroConstante) throws Exception {
		pTipoParametroConstante.setActivo(true);
		pTipoParametroConstante.setFechaAlta(Calendar.getInstance().getTime());

		pTipoParametroConstante.getListaLineas().size();

		this.verificarTipoParametro(pTipoParametroConstante);
		TrascenderEnverListener.setValoresEnAuditoriaBean(pTipoParametroConstante);
		this.entityManager.persist(pTipoParametroConstante);
		this.entityManager.flush();

		return pTipoParametroConstante;
	}

	/**
	 * Verifica que el nombre de variable sea único (para evitar problemas)
	 * 
	 * @param pTipoParametro
	 * @throws Exception
	 */
	private void verificarTipoParametro(TipoParametro pTipoParametro) throws Exception {
		if(pTipoParametro == null) {
			throw new HabilitacionesException(530);
		}

		if(pTipoParametro.getNombreVariable() == null || pTipoParametro.equals("")) {
			throw new HabilitacionesException(531);
		}

		if(pTipoParametro instanceof TipoParametroGrupoZona) {
			TipoParametroGrupoZona locTipoParametroGrupoZona = (TipoParametroGrupoZona) pTipoParametro;
			if(locTipoParametroGrupoZona.getZonificacion() == null) {
				throw new HabilitacionesException(528);
			}

			this.verificarTPGrupoZona((TipoParametroGrupoZona) pTipoParametro);
		}

		Criterio locCriterio = Criterio.getInstance(this.entityManager, TipoParametro.class).add(Restriccion.IGUAL("nombreVariable", pTipoParametro.getNombreVariable()))
				.add(Restriccion.DISTINTO("idTipoParametro", pTipoParametro.getIdTipoParametro())).add(Restriccion.IGUAL("activo", true)).setProyeccion(Proyeccion.COUNT());

		if((Long) locCriterio.uniqueResult() > 0) {
			throw new HabilitacionesException(110);
		}

		if(pTipoParametro instanceof TipoParametroConstante) {
			for(LineaTipoParametroConstante cadaLinea : ((TipoParametroConstante) pTipoParametro).getListaLineas()) {
				cadaLinea.setTipoParametroConstante((TipoParametroConstante) pTipoParametro);
			}
		}
	}

	/**
	 * Verifica si cambio la zonificacion para limpia la lista de zonas asociadas.
	 * 
	 * @param pTipoParametroGrupoZona
	 * @throws Exception
	 */
	private void verificarTPGrupoZona(TipoParametroGrupoZona pTipoParametroGrupoZona) throws Exception {
		// verifico si cambio la zonificacion para limpiar la lista de zonas asociadas
		if(((Long) Criterio.getInstance(this.entityManager, TipoParametroGrupoZona.class)
				.add(Restriccion.NOT(Restriccion.IGUAL("zonificacion", pTipoParametroGrupoZona.getZonificacion())))
				.add(Restriccion.IGUAL("idTipoParametro", pTipoParametroGrupoZona.getIdTipoParametro())).add(Restriccion.IGUAL("activo", false)).setProyeccion(Proyeccion.COUNT())
				.uniqueResult()) > 0) {
			pTipoParametroGrupoZona.getListaZonas().clear();
		}

	}

	/**
	 * Recupera el nombre del atributo constante desde la base de datos (para verificar su hubo algún cambio)
	 * 
	 * @param pTipoParametroConstante
	 * @return
	 * @throws Exception
	 */
	private String getNombreVariableParametro(TipoParametro pTipoParametro) throws Exception {
		if((pTipoParametro != null) && (pTipoParametro.getIdTipoParametro() != -1)) {
			return Criterio.getInstance(this.entityManager, TipoParametro.class).setProyeccion(Proyeccion.PROP("nombreVariable"))
					.add(Restriccion.IGUAL("idTipoParametro", pTipoParametro.getIdTipoParametro())).toString();
		} else {
			return null;
		}
	}

	/**
	 * Agrega un grupo de zonas para utilizarlo como parámetro
	 * 
	 * @param pTipoParametroGrupoZona
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public TipoParametroGrupoZona addTipoParametroGrupoZona(TipoParametroGrupoZona pTipoParametroGrupoZona) throws Exception {
		this.verificarTipoParametro(pTipoParametroGrupoZona);
		this.filtrarGrupoZona(pTipoParametroGrupoZona);

		pTipoParametroGrupoZona.setFechaAlta(Calendar.getInstance().getTime());
		TrascenderEnverListener.setValoresEnAuditoriaBean(pTipoParametroGrupoZona);
		this.entityManager.merge(pTipoParametroGrupoZona);
		this.entityManager.flush();
		pTipoParametroGrupoZona.getListaZonas().toString();

		return pTipoParametroGrupoZona;

	}

	/**
	 * Elimina las zonas con valor = 0 de un grupo zona
	 * 
	 * @param pTipoParametroGrupoZona
	 * @throws Exception
	 */
	private void filtrarGrupoZona(TipoParametroGrupoZona pTipoParametroGrupoZona) throws Exception {
		Set<Zona> locListaZonas = new HashSet<Zona>();

		if((pTipoParametroGrupoZona.getListaZonas() != null) && (!pTipoParametroGrupoZona.getListaZonas().isEmpty())) {
			for(Zona locZona : pTipoParametroGrupoZona.getListaZonas().keySet()) {
				if((pTipoParametroGrupoZona.getListaZonas().get(locZona) != null) && (pTipoParametroGrupoZona.getListaZonas().get(locZona).equals(0F))) {
					locListaZonas.add(locZona);
				}
			}
		}

		Zonificacion locZonificacion = Criterio.getInstance(entityManager, Zonificacion.class)
				.add(Restriccion.IGUAL("idZonificacion", pTipoParametroGrupoZona.getZonificacion().getIdZonificacion())).uniqueResult();

		for(Zona cadaZona : pTipoParametroGrupoZona.getListaZonas().keySet()) {
			if(!locZonificacion.getListaZonas().contains(cadaZona)) {
				if(!locListaZonas.contains(cadaZona)) {
					locListaZonas.add(cadaZona);
				}
			}
		}

		// ELIMINO TODOS LOS QUE ENCUENTRO
		for(Zona locZona : locListaZonas) {
			if(pTipoParametroGrupoZona.getListaZonas().containsKey(locZona)) {
				pTipoParametroGrupoZona.getListaZonas().remove(locZona);
			}
		}
		this.entityManager.flush();

	}

	/**
	 * 
	 * @param pTipoParametroGrupoZona
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public TipoParametroGrupoZona updateTipoParametroGrupoZona(TipoParametroGrupoZona pTipoParametroGrupoZona) throws Exception {
		return (TipoParametroGrupoZona) this.updateTipoParametro(pTipoParametroGrupoZona);
	}

	/**
	 * Recupera el listado de tipos de parámetros de un grupo zona
	 * 
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public FiltroTipoParametroGrupoZona findListaTipoParametroGrupoZona(FiltroTipoParametroGrupoZona pFiltro) throws Exception {
		this.entityManager.clear();
		Criterio locCriterio = Criterio.getInstance(this.entityManager, TipoParametroGrupoZona.class)
				.add(Restriccion.LIKE("nombreGrupoZona", pFiltro.getNombre(), true, Posicion.AL_PRINCIPIO)).add(Restriccion.IGUAL("zonificacion", pFiltro.getZonificacion()))
				.add(Restriccion.IGUAL("activo", new Boolean(true)));

		BusquedaPorLog.addRestriccionesCriterio(locCriterio, TipoParametroGrupoZona.serialVersionUID, "idTipoParametro", pFiltro.getListaBusquedaPorLogs());

		pFiltro.procesarYListar(locCriterio);

		for(Object cadaObject : pFiltro.getListaResultados()) {
			TipoParametroGrupoZona cadaTipo = (TipoParametroGrupoZona) cadaObject;
			for(Zona cadaZona : cadaTipo.getListaZonas().keySet()) {
				cadaZona.toString();
			}
		}
		return pFiltro;
	}

	/**
	 * Recupera un tipo de parámetro de grupo de zonas por id (con todos los atributos)
	 * 
	 * @param pId
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public TipoParametroGrupoZona getTipoParametroGrupoZonaPorId(long pId) throws Exception {
		TipoParametroGrupoZona locTipoParametroGrupoZona = this.entityManager.find(TipoParametroGrupoZona.class, pId);

		for(Zona locZona : locTipoParametroGrupoZona.getListaZonas().keySet()) {
			locZona.toString();
		}

		locTipoParametroGrupoZona.toString();
		locTipoParametroGrupoZona.getZonificacion().toString();
		locTipoParametroGrupoZona.getZonificacion().getListaZonas().toString();
		locTipoParametroGrupoZona.getListaLogsAuditoria().size();

		return locTipoParametroGrupoZona;
	}

	/**
	 * Recupera el tipo de parámetro constante por id
	 * 
	 * @param pId
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public TipoParametroConstante getTipoParametroConstantePorId(long pId) throws Exception {
		TipoParametroConstante locTipoParametroConstante = this.entityManager.find(TipoParametroConstante.class, pId);

		locTipoParametroConstante.toString();
		locTipoParametroConstante.getListaLogsAuditoria().size();
		locTipoParametroConstante.getListaLineas().size();
		return locTipoParametroConstante;
	}

	/**
	 * Recupera el listado de parámetros cosntantes
	 * 
	 * @param pNombre
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public FiltroTipoParametroConstante findListaParametrosConstantes(FiltroTipoParametroConstante pFiltro) throws Exception {
		Criterio locCriterio = Criterio.getInstance(this.entityManager, TipoParametroConstante.class).add(Restriccion.ILIKE("nombre", pFiltro.getNombre()))
				.add(Restriccion.IGUAL("activo", new Boolean(true)));
		BusquedaPorLog.addRestriccionesCriterio(locCriterio, TipoParametroConstante.serialVersionUID, "idTipoParametro", pFiltro.getListaBusquedaPorLogs());
		pFiltro.procesarYListar(locCriterio);
		return pFiltro;
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	@SuppressWarnings("unchecked")
	public List getListaParametrosObra() throws Exception {
		// List locListaRetorno = Criterio.getInstance(this.entityManager, TipoParametroObra.class).list();

		List locListaRetorno = this.getListaTipoParametro(TipoParametroObra.class, TipoParametroObra.TipoAtributo.class);
		locListaRetorno.addAll(getListaParametrosDinamicos(DocumentoPlanObra.serialVersionUID, TipoParametroDinamico.class, null));

		return locListaRetorno;
	}

	/**
	 * Recupera el listado de atributos de personas
	 * 
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	@SuppressWarnings("unchecked")
	public List getListaParametrosPersonas() throws Exception {

		List locListaRetorno = this.getListaTipoParametro(TipoParametroPersona.class, TipoParametroPersona.AtributoPersona.class);
		locListaRetorno.addAll(getListaParametrosDinamicos(PersonaFisica.serialVersionUID, TipoParametroPersonaFisicaDinamico.class, null));
		locListaRetorno.addAll(getListaParametrosDinamicos(PersonaJuridica.serialVersionUID, TipoParametroPersonaJuridicaDinamico.class, null));

		return locListaRetorno;
	}

	/**
	 * Recupera un tipo de parámetro de personas a partir de la enumeración correspondiente
	 * 
	 * @param pAtributoPersona
	 * @return
	 */
	// private TipoParametroPersona getTipoParametroPersonaFromAtributoPersona(AtributoPersona pAtributoPersona) {
	// TipoParametroPersona locTipoParametro=new TipoParametroPersona();
	// locTipoParametro.setAtributoPersona(pAtributoPersona);
	// locTipoParametro.setBooleano(pAtributoPersona==TipoParametroPersona.AtributoPersona.ES_JUBILADO);
	// locTipoParametro.setActivo(true);
	// locTipoParametro.setFechaAlta(Calendar.getInstance().getTime());
	// locTipoParametro.setNombreVariable(pAtributoPersona.name());
	// return locTipoParametro;
	// }

	/**
	 * Obtiene un parametro TipoParametroObra a partir del TipoAtributo parasdo
	 * 
	 * @param pTipoAtributo
	 * @return
	 * @throws Exception
	 */
	// private TipoParametroObra getParametroObraFromTipoParametroObra(TipoParametroObra.TipoAtributo pTipoAtributo) throws Exception{
	// TipoParametroObra locTipoParametroObra=new TipoParametroObra();
	// locTipoParametroObra.setNombreVariable(Util.getEnumNameFromString(pTipoAtributo.toString()));
	// locTipoParametroObra.setFechaAlta(Calendar.getInstance().getTime());
	// locTipoParametroObra.setTipoAtributoObra(pTipoAtributo);
	// this.verificarTipoParametro(locTipoParametroObra);
	// return locTipoParametroObra;
	// }

	/**
	 * Recupera un tipo de tasa con todos los datos asociados
	 * 
	 * @param pId
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public TipoTasa getTipoTasaPorId(long pId) throws Exception {

		TipoTasa locTipoTasa = (TipoTasa) Criterio.getInstance(this.entityManager, TipoTasa.class).add(Restriccion.IGUAL("idTipoTasa", pId)).uniqueResult();

		for(TipoParametro cadaTipoParametro : locTipoTasa.getListaParametros()) {
			cadaTipoParametro.toString();
		}

		for(TipoModificador cadaTipoModificador : locTipoTasa.getListaModificadores()) {
			cadaTipoModificador.toString();
			cadaTipoModificador.getListaVariables().size();
		}

		for(TipoVencimiento cadaTipoVencimiento : locTipoTasa.getListaVencimientos()) {
			cadaTipoVencimiento.toString();
		}

		for(TipoParametroAlicuota cadaTipoParametro : locTipoTasa.getListaParamatrosAlicuota()) {
			cadaTipoParametro.toString();
		}

		for(VariableFormula cadaVariable : locTipoTasa.getListaVariables()) {
			cadaVariable.toString();
		}

		locTipoTasa.getFormula();
		locTipoTasa.getFormulaRegAlicuota();
		locTipoTasa.getListaLogsAuditoria().size();
		return locTipoTasa;
	}

	/**
	 * Recupera el listado de parámetros de OSP y agrega los que se hayan agregado
	 * 
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	@SuppressWarnings("unchecked")
	public List getListaParametrosOSP() throws Exception {
		List locListaRetorno = this.getListaTipoParametro(TipoParametroOSP.class, TipoParametroOSP.TipoAtributoOSP.class);
		locListaRetorno.addAll(getListaParametrosDinamicos(DocumentoOSP.serialVersionUID, TipoParametroDinamico.class, null));

		return locListaRetorno;
	}

	/**
	 * Recupera un tipo de parámetro de OSP a partir de un Tipo de atributo de OSP (enumeración)
	 * 
	 * @param pTipoAtributoOSP
	 * @return
	 * @throws Exception
	 * @throws NamingException
	 */
	// private TipoParametroOSP getTipoParametroOSPFromTipoAtributoOSP(TipoAtributoOSP pTipoAtributoOSP) throws NamingException, Exception{
	// TipoParametroOSP locTipoParametroOSP=new TipoParametroOSP();
	// locTipoParametroOSP.setTipoAtributoOSP(pTipoAtributoOSP);
	// locTipoParametroOSP.setNombreVariable(pTipoAtributoOSP.name());
	// if (pTipoAtributoOSP.equals(TipoParametroOSP.TipoAtributoOSP.ES_MEDIDO)){
	// locTipoParametroOSP.setBooleano(true);
	// }
	// else{
	// locTipoParametroOSP.setBooleano(false);
	// }
	// locTipoParametroOSP.setFechaAlta(Calendar.getInstance().getTime());
	// locTipoParametroOSP.setActivo(true);
	//
	// return locTipoParametroOSP;
	// }

	// ------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Actualiza los datos de un tipo de tasa dentro del ambiente de una sesion pasada por parámetro
	 * 
	 * @param pSession
	 * @param pTipoTasa
	 * @return
	 * @throws Exception
	 * 
	 */
	private TipoTasa updateTipoTasa$(TipoTasa pTipoTasa) throws Exception {
		this.entityManager.refresh(pTipoTasa);
		TipoTasa locTipoTasa = pTipoTasa.clone();
		this.entityManager.detach(pTipoTasa);

		this.verificarTipoTasa(locTipoTasa);
		locTipoTasa.setTipoTasaAnterior(pTipoTasa);
		locTipoTasa = this.entityManager.merge(locTipoTasa);

		for(TipoModificador locTipoModificador : locTipoTasa.getListaModificadores()) {
			locTipoModificador.setTipoTasa(locTipoTasa);
			this.entityManager.merge(locTipoModificador);
		}

		this.entityManager.flush();

		this.deleteTipoTasa$(pTipoTasa);

		for(TipoModificador locTipoModificador : locTipoTasa.getListaModificadores()) {
			locTipoModificador.toString();
		}
		for(TipoParametro locTipoParametro : locTipoTasa.getListaParametros()) {
			locTipoParametro.toString();
		}

		this.entityManager.detach(locTipoTasa);

		return locTipoTasa;

	}

	/**
	 * Elimina un tipo de tasa dentro del ambiente de una sesión pasada por parámetro
	 * 
	 * @param pSession
	 * @param pTipoTasa
	 */
	private void deleteTipoTasa$(TipoTasa pTipoTasa) {
		if(pTipoTasa.getEstado().equals(TipoTasa.Estado.ACTIVA)) {
			// Recupero el estado anterior del tipo de tasa
			pTipoTasa = this.entityManager.find(TipoTasa.class, pTipoTasa.getIdTipoTasa());
			pTipoTasa.setFechaBaja(Calendar.getInstance().getTime());
			if(pTipoTasa.getFechaVigenciaHasta() != null) {
				pTipoTasa.setFechaVigenciaHasta(Calendar.getInstance().getTime());
			}
			pTipoTasa.desactivar();
			this.entityManager.merge(pTipoTasa);
		} else if(pTipoTasa.getEstado().equals(TipoTasa.Estado.EN_ESPERA)) {
			pTipoTasa.setFechaBaja(Calendar.getInstance().getTime());
			if(pTipoTasa.getFechaVigenciaHasta() != null) {
				pTipoTasa.setFechaVigenciaHasta(Calendar.getInstance().getTime());
			}
			pTipoTasa.desactivarTasaEnEspera();
			System.out.println(pTipoTasa.getEstado());
			this.entityManager.merge(pTipoTasa);
		}
	}

	/**
	 * Elimina un tipo de parámetro dentro del ambiente de una sesion
	 * 
	 * @param pSession
	 * @param pTipoParametro
	 */
	private void deleteTipoParametro$(TipoParametro pTipoParametro) {
		if(pTipoParametro instanceof EntidadTrascender) {
			TrascenderEnverListener.setValoresEnAuditoriaBean((EntidadTrascender) pTipoParametro);
		}
		pTipoParametro = this.entityManager.find(TipoParametro.class, pTipoParametro.getIdTipoParametro());
		pTipoParametro.setActivo(false);
		pTipoParametro.setFechaBaja(Calendar.getInstance().getTime());
		this.entityManager.merge(pTipoParametro);
	}

	/**
	 * Crea un nuevo tipo de parámetro constante
	 * 
	 * @param pTipoParametro
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public TipoParametroConstante updateTipoParametroConstante(TipoParametroConstante pTipoParametroConstante) throws Exception {
		TrascenderEnverListener.setValoresEnAuditoriaBean(pTipoParametroConstante);
		// return (TipoParametroConstante)this.updateTipoParametro(pTipoParametroConstante);ç
		this.verificarTipoParametro(pTipoParametroConstante);
		return this.entityManager.merge(pTipoParametroConstante);
	}

	/**
	 * Actualiza los datos de un tipo de parámetro manteniendo el histórico
	 * 
	 * @param pTipoParametro
	 * @return
	 * @throws Exception
	 */
	private TipoParametro updateTipoParametro(TipoParametro pTipoParametro) throws Exception {

		String locNombreVariableAtributoViejo = this.getNombreVariableParametro(pTipoParametro);
		TipoParametro locTipoParametroNuevo = pTipoParametro.clone();
		locTipoParametroNuevo.setActivo(true);
		locTipoParametroNuevo.setFechaBaja(null);

		if(pTipoParametro instanceof EntidadTrascender) {
			((EntidadTrascender) locTipoParametroNuevo).setComentarioAuditoria(((EntidadTrascender) pTipoParametro).getComentarioAuditoria());
			((EntidadTrascender) locTipoParametroNuevo).setLlaveUsuarioAuditoria(((EntidadTrascender) pTipoParametro).getLlaveUsuarioAuditoria());
		}

		// se da de baja el viejo y se crea uno nuevo
		this.deleteTipoParametro$(pTipoParametro);
		this.entityManager.flush();

		this.verificarTipoParametro(pTipoParametro);
		// hago un save para que creo uno nuevo (no importa el id)

		if(pTipoParametro instanceof EntidadTrascender) {
			TrascenderEnverListener.setValoresEnAuditoriaBean((EntidadTrascender) locTipoParametroNuevo);
		}
		this.entityManager.merge(locTipoParametroNuevo);
		this.entityManager.flush();

		// FIXME DESCOMENTAR Y ARREGLAR
		// List locListaTasas=Criterio.getInstance(this.entityManager, TipoTasa.class)
		// .crearAlias("listaParametros", "cadaParam")
		// .add(Restriccion.IGUAL("cadaParam", pTipoParametro))
		// .list();
		//
		//
		// //Actualiza todos los tipos de tasas donde estaba asociado este parámetro constante
		// for (Object cadaObject : locListaTasas ){
		// TipoTasa cadaTipoTasa=(TipoTasa)cadaObject;
		// cadaTipoTasa.getListaParametros().remove(pTipoParametro);
		// cadaTipoTasa.getListaParametros().add(locTipoParametroNuevo);
		// if (locNombreVariableAtributoViejo!=null
		// && !locNombreVariableAtributoViejo.equals(locTipoParametroNuevo.getNombreVariable())
		// && cadaTipoTasa.getFormula() != null){
		// cadaTipoTasa.setFormula(this.reemplazarFormula(cadaTipoTasa.getFormula(), locNombreVariableAtributoViejo,locTipoParametroNuevo.getNombreVariable()));
		// }
		// this.updateTipoTasa$(cadaTipoTasa); //this.updateTipoTasa(sess, locTipoTasa);
		// }

		return locTipoParametroNuevo;
	}

	/**
	 * Reemplaza los nombres de variables de la fórmula por un nuevo nombre de variable
	 * 
	 * @param pFormula
	 *            formula que hay que reemplazar
	 * @param pParametroViejo
	 *            nombre de variable viejo
	 * @param pParametroNuevo
	 *            nombre de variable nuevo
	 * @return
	 */
	private String reemplazarFormula(String pFormula, String pParametroViejo, String pParametroNuevo) {

		int locIndiceOcurrenciaAnterior = 0;
		int locIndiceOcurrencia = 0;
		StringBuilder locResultado = new StringBuilder();

		while(locIndiceOcurrencia != -1) {
			locIndiceOcurrencia = pFormula.indexOf(pParametroViejo, locIndiceOcurrenciaAnterior);
			// Significa que debe terminar de agregar lo que falta nomás
			if(locIndiceOcurrencia == -1) {
				locResultado.append(pFormula.substring(locIndiceOcurrenciaAnterior, pFormula.length()));

			} else {
				// agrego todo lo que se encuentra entre medio del la ocurrencia anterior y esta
				locResultado.append(pFormula.substring(locIndiceOcurrenciaAnterior, locIndiceOcurrencia));

				// verifico que el fin de la cadena no sea una letra
				if(pFormula.length() > (locIndiceOcurrencia + pParametroViejo.length())) {
					if(String.valueOf(pFormula.charAt(locIndiceOcurrencia + pParametroViejo.length())).matches("[\\W]")) {

						// verifico el principio
						if(locIndiceOcurrencia > 0) {
							if(String.valueOf(pFormula.charAt(locIndiceOcurrencia - 1)).matches("[\\W]")) {
								locResultado.append(pParametroNuevo);
							} else {
								// si el principio es una letra dejo lo que ya estaba
								locResultado.append(pFormula.substring(locIndiceOcurrencia, locIndiceOcurrencia + pParametroViejo.length()));
							}
						} else {
							// si el principio es=0 significa que comienza con el nombre, por lo tanto hay que cambiarlo
							locResultado.append(pParametroNuevo);
						}
					} else {
						// sino termina tengo que verificar que no sea el último

						locResultado.append(pFormula.substring(locIndiceOcurrencia, locIndiceOcurrencia + pParametroViejo.length()));
					}
				} else {

					// verifico el principio
					if(String.valueOf(pFormula.charAt(locIndiceOcurrencia - 1)).matches("[\\W]")) {
						locResultado.append(pParametroNuevo);
					} else {
						// si el principio es una letra dejo lo que ya estaba
						locResultado.append(pFormula.substring(locIndiceOcurrencia, locIndiceOcurrencia + pParametroViejo.length()));
					}
				}
				locIndiceOcurrenciaAnterior = locIndiceOcurrencia + pParametroViejo.length();
			}
		}
		return locResultado.toString();
	}

	/**
	 * Recupera la listad e parámetros de SHPS
	 * 
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	@SuppressWarnings("unchecked")
	public List getListaParametrosSHPS() throws Exception {
		List locListaRetorno = this.getListaTipoParametro(TipoParametroSHPS.class, TipoParametroSHPS.AtributoSHPS.class);
		locListaRetorno.addAll(getListaParametrosDinamicos(DocumentoSHPS.serialVersionUID, TipoParametroDinamico.class, null));

		return locListaRetorno;
	}

	public List<TipoParametroAlicuota> getListaParametrosAlicuotaSHPS() {
		List<TipoParametroAlicuota> locListaResultado = new ArrayList<TipoParametroAlicuota>();

		// List<TipoParametroAlicuotaSHPS> locListaParametrosAlicuota = Criterio.getInstance(entityManager, TipoParametroAlicuotaSHPS.class).list();
		// forPrincipal:
		// for (AtributoAlicuotaSHPS cadaAtributo : TipoParametroAlicuotaSHPS.AtributoAlicuotaSHPS.values()){
		// for (TipoParametroAlicuotaSHPS cadaTipoParametro : locListaParametrosAlicuota){
		// if (cadaTipoParametro.getAtributoAlicuotaSHPS().equals(cadaAtributo)){
		// continue forPrincipal;
		// }
		// }
		// TipoParametroAlicuotaSHPS locTipoParametro = this.getTipoParametroAlicuotaFromAlicuotaSHPS(cadaAtributo);
		// entityManager.persist(locTipoParametro);
		// locListaParametrosAlicuota.add(locTipoParametro);
		// }
		//
		// locListaResultado.addAll(locListaParametrosAlicuota);
		// locListaResultado.addAll(this.getListaParametrosDinamicosAlicuota());
		locListaResultado.addAll(this.getListaTipoParametroAlicuota(TipoParametroAlicuotaSHPS.class, TipoParametroAlicuotaSHPS.AtributoAlicuotaSHPS.class));
		locListaResultado.addAll(this.getListaParametrosDinamicosAlicuota(Rubro.serialVersionUID, TipoParametroAlicuotaSHPSDinamico.class));

		return locListaResultado;
	}

	public List<TipoParametroAlicuota> getListaParametrosAlicuotaOSP() {
		List<TipoParametroAlicuota> locListaResultado = new ArrayList<TipoParametroAlicuota>();
		locListaResultado.addAll(this.getListaTipoParametroAlicuota(TipoParametroAlicuotaOSP.class, TipoParametroOSP.TipoAtributoOSP.class));
		locListaResultado.addAll(this.getListaParametrosDinamicosAlicuota(ServicioOSP.serialVersionUID, TipoParametroAlicuotaOSPDinamico.class));
		return locListaResultado;
	}

	// Genera los Parametros para los atributos dinamicos anexados a las Alicuotas SHPS.
	private List<TipoParametroAlicuotaSHPSDinamico> getListaParametrosDinamicosAlicuota() {
		Criterio locCriterio = Criterio.getInstance(entityManager, PlantillaAtributoDinamico.class).add(Restriccion.IGUAL("idRecurso", RegAlicuota.serialVersionUID))
				.add(Restriccion.DISTINTO("tipo", PlantillaAtributoDinamico.Tipo.FECHA)).add(Restriccion.DISTINTO("tipo", PlantillaAtributoDinamico.Tipo.CADENA))
				.setProyeccion(Proyeccion.PROP("nombre"));

		List<String> locListaNombreAtributos = locCriterio.list();
		locListaNombreAtributos = this.procesarNombres(locListaNombreAtributos, null);

		List<TipoParametroAlicuotaSHPSDinamico> locListaResultado = new ArrayList<TipoParametroAlicuotaSHPSDinamico>();

		if(!locListaNombreAtributos.isEmpty()) {

			locListaResultado = Criterio.getInstance(entityManager, TipoParametroAlicuotaSHPSDinamico.class).add(Restriccion.EN("nombreVariable", locListaNombreAtributos)).list();

			// Se persisten nuevos Parametros en caso de no existir
			for(String cadaNombre : locListaNombreAtributos) {
				boolean existe = false;
				Iterator<TipoParametroAlicuotaSHPSDinamico> it = locListaResultado.iterator();
				while(it.hasNext() && !existe) {
					TipoParametroAlicuotaSHPSDinamico cadaParametroDinamico = it.next();
					existe = cadaParametroDinamico.getNombreVariable().equals(cadaNombre);
				}
				if(!existe) {
					locListaResultado.add(persistirNuevoParametroAlicuotaSHPSDinamico(cadaNombre));
				}
			}
		}
		return locListaResultado;

	}

	private TipoParametroAlicuotaSHPS getTipoParametroAlicuotaFromAlicuotaSHPS(AtributoAlicuotaSHPS pAtributoAlicuotaSHPS) {
		TipoParametroAlicuotaSHPS locTipoParametro = new TipoParametroAlicuotaSHPS();
		locTipoParametro.setAtributoAlicuotaSHPS(pAtributoAlicuotaSHPS);
		locTipoParametro.setNombreVariable(pAtributoAlicuotaSHPS.name());
		return locTipoParametro;
	}

	/**
	 * Recupera un tipo de parámetro de SHPS a partir de un AtributoSHPS
	 * 
	 * @param pTipoAtributoSHPS
	 * @return TipoParametroSHPS
	 * @throws CreateException
	 * @throws NamingException
	 * @throws Exception
	 **/
	// private TipoParametroSHPS getTipoParametroSHPSFromTipoAtributoSHPS(AtributoSHPS pTipoAtributoSHPS)
	// throws NamingException, CreateException{
	// TipoParametroSHPS locTipoParametroSHPS=new TipoParametroSHPS();
	// locTipoParametroSHPS.setAtributoSHPS(pTipoAtributoSHPS);
	// locTipoParametroSHPS.setNombreVariable(pTipoAtributoSHPS.name());
	// locTipoParametroSHPS.setFechaAlta(Calendar.getInstance().getTime());
	// locTipoParametroSHPS.setActivo(true);
	//
	// return locTipoParametroSHPS;
	// }

	/**
	 * @param pPeriodicidad
	 * @param pTipoObligacion
	 * @param pEstadoTipoTasa
	 *            [ACTIVA, EN_ESPERA] no acpeta el estado INACTIVA porque puede haber varios
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public TipoTasa getTipoTasa(TipoObligacion pTipoObligacion, TipoTasa.Estado pEstadoTipoTasa, Integer pCantidadCuotas, Plan pPlan) throws Exception {

		if(pEstadoTipoTasa.equals(TipoTasa.Estado.INACTIVA)) {
			throw new HabilitacionesException(112);
		}

		Criterio locCriterio = Criterio.getInstance(this.entityManager, TipoTasa.class).add(Restriccion.IGUAL("plan.tipoObligacion", pTipoObligacion))
				.add(Restriccion.IGUAL("plan", pPlan)).add(Restriccion.IGUAL("estado", pEstadoTipoTasa));

		TipoTasa tipoTasaResultado = (TipoTasa) locCriterio.uniqueResult();

		if(tipoTasaResultado != null) {
			for(TipoModificador cadaTipo : tipoTasaResultado.getListaModificadores()) {
				cadaTipo.toString();
			}

			for(TipoParametro cadaTipo : tipoTasaResultado.getListaParametros()) {
				cadaTipo.toString();
			}

			for(TipoVencimiento cadaTipo : tipoTasaResultado.getListaVencimientos()) {
				cadaTipo.toString();
			}
		}
		return tipoTasaResultado;
	}

	/**
	 * Recupera el listado de atributos de personas
	 * 
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	@SuppressWarnings("unchecked")
	public List getListaParametrosVencimiento() throws Exception {
		List locListaRetorno = this.getListaTipoParametro(TipoParametroVencimiento.class, TipoParametroVencimiento.TipoAtributoVencimiento.class);
		return locListaRetorno;
	}

	/**
	 * Recupera los atributos dinamicos asociados a una Tasa Menor y a sus registros valuados.
	 * 
	 * @param pPlantilla
	 * @return
	 */
	private List<String> getListaNombresAtributos(PlantillaDocumentoTasaMenor pPlantilla) {
		Criterio locCriterio = Criterio.getInstance(entityManager, PlantillaDocumentoTasaMenor.class)
				.add(Restriccion.IGUAL("idDocumentoTasaMenor", pPlantilla.getIdDocumentoTasaMenor())).crearAlias("listaPlantillasAtributos", "cadaPlantilla")
				.add(Restriccion.DISTINTO("cadaPlantilla.tipo", PlantillaAtributoDinamico.Tipo.FECHA))
				.add(Restriccion.DISTINTO("cadaPlantilla.tipo", PlantillaAtributoDinamico.Tipo.CADENA)).setProyeccion(Proyeccion.PROP("cadaPlantilla.nombre"));

		List<String> locListaResultados = locCriterio.list();

		locCriterio = Criterio.getInstance(entityManager, PlantillaDocumentoTasaMenor.class).add(Restriccion.IGUAL("idDocumentoTasaMenor", pPlantilla.getIdDocumentoTasaMenor()))
				.crearAlias("listaPlantillasRegistroValuado", "cadaPlantillaRegValuado").add(Restriccion.DISTINTO("cadaPlantillaRegValuado.tipo", PlantillaAtributoDinamico.Tipo.FECHA))
				.add(Restriccion.DISTINTO("cadaPlantillaRegValuado.tipo", PlantillaAtributoDinamico.Tipo.CADENA)).setProyeccion(Proyeccion.PROP("cadaPlantillaRegValuado.nombre"));

		List<String> locListaNombresValuados = locCriterio.list();
		locListaResultados.addAll(locListaNombresValuados);
		return locListaResultados;
	}

	/**
	 * Recupera los nombres de los atributos dinamicos asociados a las Tasas clasicas de Vipians.
	 * 
	 * @param pTipoObligacion
	 * @return
	 */
	private List<String> getListaNombresAtributos(TipoObligacion pTipoObligacion) {
		Criterio locCriterio = Criterio.getInstance(entityManager, PlantillaAtributoDinamico.class).add(Restriccion.DISTINTO("tipo", PlantillaAtributoDinamico.Tipo.FECHA))
				.add(Restriccion.DISTINTO("tipo", PlantillaAtributoDinamico.Tipo.CADENA)).setProyeccion(Proyeccion.PROP("nombre")).setModoDebug(true);

		if(pTipoObligacion.getNombre().equals("TGI")) {
			locCriterio.add(Restriccion.IGUAL("idRecurso", DocumentoTGI.serialVersionUID));
		} else if(pTipoObligacion.getNombre().equals("OYSP")) {
			locCriterio.add(Restriccion.IGUAL("idRecurso", DocumentoOSP.serialVersionUID));
		} else if(pTipoObligacion.getNombre().equals("PLAN_FINANCIACION_OBRA")) {
			locCriterio.add(Restriccion.IGUAL("idRecurso", DocumentoPlanObra.serialVersionUID));
		} else if(pTipoObligacion.getNombre().equals("SHPS")) {
			locCriterio.add(Restriccion.IGUAL("idRecurso", DocumentoSHPS.serialVersionUID));
		}
		return locCriterio.list();
	}

	public List<TipoParametroDinamico> getListaParametrosDinamicos(TipoObligacion pTipoObligacion) throws TrascenderException {
		if(pTipoObligacion == null) {
			throw new HabilitacionesException(860);
		}
		List<String> locListaNombresAtributosDocumentos;
		// El criterio de busqueda de una Tasa Menor es distinto del resto
		PlantillaDocumentoTasaMenor locPlantillaDocumento = Criterio.getInstance(entityManager, PlantillaDocumentoTasaMenor.class)
				.add(Restriccion.IGUAL("tipoObligacion", pTipoObligacion)).uniqueResult();
		System.out.println("Es tasa menor??");
		System.out.println(locPlantillaDocumento != null);
		if(locPlantillaDocumento != null) {
			locListaNombresAtributosDocumentos = this.getListaNombresAtributos(locPlantillaDocumento);
		} else {
			locListaNombresAtributosDocumentos = this.getListaNombresAtributos(pTipoObligacion);
		}

		locListaNombresAtributosDocumentos = this.procesarNombres(locListaNombresAtributosDocumentos, null);

		List<TipoParametroDinamico> locListaResultado = new ArrayList<TipoParametroDinamico>();

		if(!locListaNombresAtributosDocumentos.isEmpty()) {

			locListaResultado = Criterio.getInstance(entityManager, TipoParametroDinamico.class).add(Restriccion.EN("nombreVariable", locListaNombresAtributosDocumentos)).list();

			// Se persisten nuevos Parametros en caso de no existir
			for(String cadaNombre : locListaNombresAtributosDocumentos) {
				boolean existe = false;
				Iterator<TipoParametroDinamico> it = locListaResultado.iterator();
				while(it.hasNext() && !existe) {
					TipoParametroDinamico cadaParametroDinamico = it.next();
					existe = cadaParametroDinamico.getNombre().equals(cadaNombre);
				}
				if(!existe) {
					locListaResultado.add(persistirNuevoParametroDinamico(cadaNombre));
				}
			}
		}
		return locListaResultado;
	}

	/**
	 * Persiste y devuelve un nuevo TipoParametroDinamico con el nombre dado.
	 * 
	 * @param pNombre
	 * @return
	 */
	private TipoParametroDinamico persistirNuevoParametroDinamico(String pNombre) {
		System.out.println("Se persiste: " + pNombre);
		TipoParametroDinamico locTipoParametroDinamico = new TipoParametroDinamico(pNombre);
		entityManager.persist(locTipoParametroDinamico);
		return locTipoParametroDinamico;
	}

	private TipoParametroAlicuotaSHPSDinamico persistirNuevoParametroAlicuotaSHPSDinamico(String pNombre) {
		TipoParametroAlicuotaSHPSDinamico locTipoParametro = new TipoParametroAlicuotaSHPSDinamico();
		locTipoParametro.setNombreVariable(pNombre);
		entityManager.persist(locTipoParametro);
		return locTipoParametro;
	}

	/**
	 * Transforma strings en nombres de variables, reemplazando espacios por guiones bajos y capitalizando
	 * 
	 * @param pListaNombres
	 */
	private List<String> procesarNombres(List<String> pListaNombres, String pSufijo) {
		List<String> locListaResultado = new ArrayList<String>();
		for(String cadaString : pListaNombres) {
			String locNombre = cadaString.toUpperCase().replace(" ", "_");
			if(pSufijo != null)
				locNombre += "_" + pSufijo;
			locListaResultado.add(locNombre);
		}
		return locListaResultado;
	}

	/**
	 * Recupera un tipo de parámetro de SHPS a partir de un AtributoSHPS
	 * 
	 * @param pTipoAtributoVencimiento
	 * @return TipoParametroSHPS
	 */
	// private TipoParametroVencimiento getTipoParametroVencimientoFromTipoAtributoVencimiento(TipoParametroVencimiento.TipoAtributoVencimiento
	// pTipoAtributoVencimiento){
	// TipoParametroVencimiento locTipoParametroPFO=new TipoParametroVencimiento();
	// locTipoParametroPFO.setAtributoVencimiento(pTipoAtributoVencimiento);
	// locTipoParametroPFO.setNombreVariable(pTipoAtributoVencimiento.name());
	// locTipoParametroPFO.setFechaAlta(Calendar.getInstance().getTime());
	// locTipoParametroPFO.setActivo(true);
	// return locTipoParametroPFO;
	// }

	/**
	 * Recupera el listado de atributos de personas
	 * 
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	@SuppressWarnings("unchecked")
	public List getListaParametrosPFO() throws Exception {
		List locListaRetorno = this.getListaTipoParametro(TipoParametroPFO.class, TipoParametroPFO.TipoAtributoPFO.class);
		locListaRetorno.addAll(getListaParametrosDinamicos(DocumentoPlanObra.serialVersionUID, TipoParametroDinamico.class, null));

		return locListaRetorno;
	}

	/**
	 * Recupera un tipo de parámetro de SHPS a partir de un AtributoSHPS
	 * 
	 * @param pTipoAtributoPFO
	 * @return TipoParametroSHPS
	 */
	// private TipoParametroPFO getTipoParametroPFOFromTipoAtributoPFO(TipoParametroPFO.TipoAtributoPFO pTipoAtributoPFO){
	// TipoParametroPFO locTipoParametroPFO=new TipoParametroPFO();
	// locTipoParametroPFO.setAtributoPFO(pTipoAtributoPFO);
	// locTipoParametroPFO.setNombreVariable(pTipoAtributoPFO.name());
	// locTipoParametroPFO.setFechaAlta(Calendar.getInstance().getTime());
	// locTipoParametroPFO.setActivo(true);
	// if (locTipoParametroPFO.getAtributoPFO().equals(TipoParametroPFO.TipoAtributoPFO.ES_ALGUN_PROPIETARIO_MOROSO_PFO)){
	// locTipoParametroPFO.setBooleano(true);
	// }
	// else{
	// locTipoParametroPFO.setBooleano(false);
	// }
	// return locTipoParametroPFO;
	// }

	public List<TipoParametroAlicuota> getListaParametrosTipoSepultura() throws Exception {
		List<TipoParametroAlicuota> locListaResultado = new ArrayList<TipoParametroAlicuota>();

		List<TipoParametroTipoSepultura> locListaParametrosTipoSepultura = Criterio.getInstance(entityManager, TipoParametroTipoSepultura.class).list();
		forPrincipal: for(AtributoTipoSepultura cadaAtributo : TipoParametroTipoSepultura.AtributoTipoSepultura.values()) {
			for(TipoParametroTipoSepultura cadaTipoParametro : locListaParametrosTipoSepultura) {
				if(cadaTipoParametro.getAtributoTipoSepultura().equals(cadaAtributo)) {
					continue forPrincipal;
				}
			}
			TipoParametroTipoSepultura locTipoParametro = this.getTipoParametroAlicuotaFromTipoSepultura(cadaAtributo);
			entityManager.persist(locTipoParametro);
			locListaParametrosTipoSepultura.add(locTipoParametro);
		}

		locListaResultado.addAll(locListaParametrosTipoSepultura);
		locListaResultado.addAll(getListaParametrosDinamicosAlicuota(TipoSepultura.serialVersionUID, TipoParametroTipoSepulturaDinamico.class));

		return locListaResultado;
	}

	public List getListaParametrosCementerio() throws Exception {
		List<TipoParametro> locListaRetorno = new ArrayList<TipoParametro>();

		locListaRetorno = Criterio.getInstance(this.entityManager, TipoParametroCementerio.class).list();

		if(locListaRetorno.size() != TipoParametroCementerio.AtributoCementerio.values().length) {
			if(locListaRetorno.isEmpty()) {
				for(TipoParametroCementerio.AtributoCementerio locAtributoCementerio : TipoParametroCementerio.AtributoCementerio.values()) {
					TipoParametroCementerio locTipoParametro = this.getParametroCementerioFromTipoAtributoCementerio(locAtributoCementerio);
					this.entityManager.persist(locTipoParametro);
					locListaRetorno.add(locTipoParametro);
				}
			} else {
				for(TipoParametroCementerio.AtributoCementerio locAtributoCementerio : TipoParametroCementerio.AtributoCementerio.values()) {
					boolean existe = false;
					Iterator locIterador = locListaRetorno.iterator();
					TipoParametroCementerio locParametroCementerio = null;

					while((locIterador.hasNext()) && (!existe)) {
						locParametroCementerio = (TipoParametroCementerio) locIterador.next();
						existe = (locAtributoCementerio.equals(locParametroCementerio.getAtributoCementerio()));
					}

					if(!existe) {
						locParametroCementerio = this.getParametroCementerioFromTipoAtributoCementerio(locAtributoCementerio);
						this.entityManager.persist(locParametroCementerio);
						locListaRetorno.add(locParametroCementerio);
					}
				}
			}
		}

		locListaRetorno.addAll(getListaParametrosDinamicos(DocumentoCementerio.serialVersionUID, TipoParametroCementerioDinamico.class, null));

		return locListaRetorno;
	}

	public List<TipoParametroAlicuota> getListaParametrosParcelaCementerio() throws Exception {
		List<TipoParametroAlicuota> locListaResultado = new ArrayList<TipoParametroAlicuota>();

		List<TipoParametroParcelaCementerio> locListaParametrosParcelaCementerio = Criterio.getInstance(entityManager, TipoParametroParcelaCementerio.class).list();
		forPrincipal: for(AtributoParcelaCementerio cadaAtributo : TipoParametroParcelaCementerio.AtributoParcelaCementerio.values()) {
			for(TipoParametroParcelaCementerio cadaTipoParametro : locListaParametrosParcelaCementerio) {
				if(cadaTipoParametro.getAtributoParcelaCementerio().equals(cadaAtributo)) {
					continue forPrincipal;
				}
			}
			TipoParametroParcelaCementerio locTipoParametro = this.getTipoParametroAlicuotaFromParcelaCementerio(cadaAtributo);
			entityManager.persist(locTipoParametro);
			locListaParametrosParcelaCementerio.add(locTipoParametro);
		}

		locListaResultado.addAll(locListaParametrosParcelaCementerio);
		locListaResultado.addAll(this.getListaParametrosDinamicosAlicuota(ParcelaCementerio.serialVersionUID, TipoParametroParcelaCementerioDinamico.class));

		return locListaResultado;
	}

	private TipoParametroParcelaCementerio getTipoParametroAlicuotaFromParcelaCementerio(AtributoParcelaCementerio pAtributoParcelaCementerio) {
		TipoParametroParcelaCementerio locTipoParametro = new TipoParametroParcelaCementerio();
		locTipoParametro.setAtributoParcelaCementerio(pAtributoParcelaCementerio);
		locTipoParametro.setNombreVariable(pAtributoParcelaCementerio.name());
		return locTipoParametro;
	}

	private TipoParametroTipoSepultura getTipoParametroAlicuotaFromTipoSepultura(AtributoTipoSepultura pAtributoTipoSepultura) {
		TipoParametroTipoSepultura locTipoParametro = new TipoParametroTipoSepultura();
		locTipoParametro.setAtributoTipoSepultura(pAtributoTipoSepultura);
		locTipoParametro.setNombreVariable(pAtributoTipoSepultura.name());
		return locTipoParametro;
	}

	private <T extends TipoParametro> List<T> getListaParametrosDinamicos(long pIdRecurso, Class<T> pClase, String pSufijo) throws Exception {
		Criterio locCriterio = Criterio.getInstance(entityManager, PlantillaAtributoDinamico.class).add(Restriccion.IGUAL("idRecurso", pIdRecurso))
				.add(Restriccion.DISTINTO("tipo", PlantillaAtributoDinamico.Tipo.FECHA)).add(Restriccion.DISTINTO("tipo", PlantillaAtributoDinamico.Tipo.CADENA))
				.setProyeccion(Proyeccion.PROP("nombre"));

		List<String> locListaNombreAtributos = locCriterio.list();
		locListaNombreAtributos = this.procesarNombres(locListaNombreAtributos, pSufijo);

		List<T> locListaResultado = new ArrayList<T>();

		if(!locListaNombreAtributos.isEmpty()) {

			locListaResultado = Criterio.getInstance(entityManager, pClase).add(Restriccion.EN("nombreVariable", locListaNombreAtributos)).list();

			// Se persisten nuevos Parametros en caso de no existir
			for(String cadaNombre : locListaNombreAtributos) {
				boolean existe = false;
				Iterator<T> it = locListaResultado.iterator();
				while(it.hasNext() && !existe) {
					T cadaParametroDinamico = it.next();
					existe = cadaParametroDinamico.getNombreVariable().equals(cadaNombre);
				}
				if(!existe) {
					locListaResultado.add(persistirNuevoParametroDinamico(pClase, cadaNombre));
				}
			}
		}
		return locListaResultado;
	}

	private <T extends TipoParametro> T persistirNuevoParametroDinamico(Class<T> pClase, String pNombre) throws Exception {
		T pInstance = pClase.newInstance();
		pClase.getMethod("setNombreVariable", String.class).invoke(pInstance, pNombre);
		entityManager.persist(pInstance);
		return pInstance;
	}

	private <T extends TipoParametroAlicuota> List<T> getListaParametrosDinamicosAlicuota(long pIdRecurso, Class<T> pClase) {
		Criterio locCriterio = Criterio.getInstance(entityManager, PlantillaAtributoDinamico.class).add(Restriccion.IGUAL("idRecurso", pIdRecurso))
				.add(Restriccion.DISTINTO("tipo", PlantillaAtributoDinamico.Tipo.FECHA)).add(Restriccion.DISTINTO("tipo", PlantillaAtributoDinamico.Tipo.CADENA))
				.setProyeccion(Proyeccion.PROP("nombre"));

		List<String> locListaNombreAtributos = locCriterio.list();
		locListaNombreAtributos = this.procesarNombres(locListaNombreAtributos, null);

		List<T> locListaResultado = new ArrayList<T>();

		if(!locListaNombreAtributos.isEmpty()) {

			locListaResultado = Criterio.getInstance(entityManager, pClase).add(Restriccion.EN("nombreVariable", locListaNombreAtributos)).list();

			// Se persisten nuevos Parametros en caso de no existir
			for(String cadaNombre : locListaNombreAtributos) {
				boolean existe = false;
				Iterator<T> it = locListaResultado.iterator();
				while(it.hasNext() && !existe) {
					T cadaParametroDinamico = it.next();
					existe = cadaParametroDinamico.getNombreVariable().equals(cadaNombre);
				}
				if(!existe) {
					try {
						locListaResultado.add(persistirNuevoParametroDinamicoAlicuota(pClase, cadaNombre));
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return locListaResultado;
	}

	private <T extends TipoParametroAlicuota> T persistirNuevoParametroDinamicoAlicuota(Class<T> pClase, String pNombre) throws Exception {
		T pInstance = pClase.newInstance();
		pClase.getMethod("setNombreVariable", String.class).invoke(pInstance, pNombre);
		entityManager.persist(pInstance);
		return pInstance;
	}

	private TipoParametroCementerio getParametroCementerioFromTipoAtributoCementerio(TipoParametroCementerio.AtributoCementerio pTipoAtributoCementerio) {
		TipoParametroCementerio locTipoParametro = new TipoParametroCementerio();
		locTipoParametro.setActivo(true);
		locTipoParametro.setFechaAlta(Calendar.getInstance().getTime());
		locTipoParametro.setNombreVariable(Util.getEnumNameFromString(pTipoAtributoCementerio.toString()));
		return locTipoParametro;
	}

	private <T extends TipoParametro> List<T> getListaTipoParametro(Class<T> pClase, Class<? extends Enum> claseEnum) {
		List<T> locListaRetorno = Criterio.getInstance(this.entityManager, pClase).list();

		if(locListaRetorno.size() != claseEnum.getEnumConstants().length) {
			if(locListaRetorno.isEmpty()) {
				for(Enum locAtributo : claseEnum.getEnumConstants()) {
					T locTipoParametro = this.getParametroFromAtributo(pClase, locAtributo);
					this.entityManager.persist(locTipoParametro);
					locListaRetorno.add(locTipoParametro);
				}
			} else {
				for(Enum locAtributo : claseEnum.getEnumConstants()) {
					boolean existe = false;
					Iterator<T> locIterador = locListaRetorno.iterator();
					T locParametro = null;

					while((locIterador.hasNext()) && (!existe)) {
						locParametro = locIterador.next();
						existe = (locAtributo.name().equals(locParametro.getNombreVariable()));
					}

					if(!existe) {
						locParametro = this.getParametroFromAtributo(pClase, locAtributo);
						this.entityManager.persist(locParametro);
						locListaRetorno.add(locParametro);
					}
				}
			}
		}

		return locListaRetorno;
	}

	private <T extends TipoParametroAlicuota> List<T> getListaTipoParametroAlicuota(Class<T> pClase, Class<? extends Enum> claseEnum) {
		List<T> locListaRetorno = Criterio.getInstance(this.entityManager, pClase).list();

		if(locListaRetorno.size() != claseEnum.getEnumConstants().length) {
			if(locListaRetorno.isEmpty()) {
				for(Enum locAtributo : claseEnum.getEnumConstants()) {
					T locTipoParametro = this.getParametroAlicuotaFromAtributo(pClase, locAtributo);
					this.entityManager.persist(locTipoParametro);
					locListaRetorno.add(locTipoParametro);
				}
			} else {
				for(Enum locAtributo : claseEnum.getEnumConstants()) {
					boolean existe = false;
					Iterator<T> locIterador = locListaRetorno.iterator();
					T locParametro = null;

					while((locIterador.hasNext()) && (!existe)) {
						locParametro = locIterador.next();
						existe = (locAtributo.name().equals(locParametro.getNombreVariable()));
					}

					if(!existe) {
						locParametro = this.getParametroAlicuotaFromAtributo(pClase, locAtributo);
						this.entityManager.persist(locParametro);
						locListaRetorno.add(locParametro);
					}
				}
			}
		}

		return locListaRetorno;
	}

	public List getListaParametrosAutomotor() throws Exception {
		List<TipoParametro> locListaRetorno = new ArrayList<TipoParametro>();

		locListaRetorno.addAll(this.getListaTipoParametro(TipoParametroAutomotor.class, TipoParametroAutomotor.AtributoAutomotor.class));
		locListaRetorno.addAll(getListaParametrosDinamicos(DocumentoAutomotor.serialVersionUID, TipoParametroAutomotorDinamico.class, null));

		return locListaRetorno;
	}

	public List getListaParametrosVehiculo() throws Exception {
		List<TipoParametro> locListaRetorno = new ArrayList<TipoParametro>();

		locListaRetorno.addAll(this.getListaTipoParametro(TipoParametroVehiculo.class, TipoParametroVehiculo.AtributoVehiculo.class));
		locListaRetorno.addAll(getListaParametrosDinamicos(Vehiculo.serialVersionUID, TipoParametroVehiculoDinamico.class, "VEHICULO"));

		locListaRetorno.addAll(getListaParametrosDinamicos(Marca.serialVersionUID, TipoParametroMarcaDinamico.class, "MARCA"));
		locListaRetorno.addAll(getListaParametrosDinamicos(Modelo.serialVersionUID, TipoParametroModeloDinamico.class, "MODELO"));
		locListaRetorno.addAll(getListaParametrosDinamicos(TipoVehiculo.serialVersionUID, TipoParametroTipoVehiculoDinamico.class, "TIPO_VEHICULO"));

		return locListaRetorno;
	}

	public List getListaParametrosDeuda() throws Exception {
		List<TipoParametro> locListaRetorno = new ArrayList<TipoParametro>();

		locListaRetorno.addAll(this.getListaTipoParametro(TipoParametroDeuda.class, TipoParametroDeuda.AtributoDeuda.class));

		return locListaRetorno;
	}

	private <T extends TipoParametro> T getParametroFromAtributo(Class<T> pClase, Enum pAtributo) {
		T locTipoParametro = null;
		try {
			locTipoParametro = pClase.newInstance();
			locTipoParametro.setActivo(true);
			locTipoParametro.setFechaAlta(Calendar.getInstance().getTime());
			locTipoParametro.setNombreVariable(Util.getEnumNameFromString(pAtributo.name()));
			locTipoParametro.setNombreAtributo(locTipoParametro.getNombreVariable());
		} catch(InstantiationException e) {
			e.printStackTrace();
		} catch(IllegalAccessException e) {
			e.printStackTrace();
		}

		return locTipoParametro;
	}

	private <T extends TipoParametroAlicuota> T getParametroAlicuotaFromAtributo(Class<T> pClase, Enum pAtributo) {
		T locTipoParametro = null;
		try {
			locTipoParametro = pClase.newInstance();
			locTipoParametro.setNombreVariable(Util.getEnumNameFromString(pAtributo.name()));
			locTipoParametro.setNombreAtributo(locTipoParametro.getNombreVariable());
		} catch(InstantiationException e) {
			e.printStackTrace();
		} catch(IllegalAccessException e) {
			e.printStackTrace();
		}

		return locTipoParametro;
	}

	/**
	 * 
	 * @param pTipoTasa
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public void activarTipoTasa(TipoTasa pTipoTasa, String pComentario, long pLlave) throws Exception {

		TipoTasa locTipoTasaVieja = this.getTipoTasa(pTipoTasa.getPlan().getTipoObligacion(), TipoTasa.Estado.ACTIVA, pTipoTasa.getCantidadCuotas(), pTipoTasa.getPlan());

		// PermisoTipoTasa locPermisoTipoTasa=pTipoTasa.getPermisoTipoTasa();

		// if (locPermisoTipoTasa==null){
		// locPermisoTipoTasa=new PermisoTipoTasa();
		// }

		SystemUsuario locUsuario = (SystemUsuario) (new InitialContext()).lookup(SystemUsuario.JNDI_NAME);
		locUsuario.setLlave(pLlave);

		FirmaPermiso locFirma = locUsuario.firmar(pComentario);

		// locPermisoTipoTasa.setFirma(locFirma);
		// pTipoTasa.setPermisoTipoTasa(locPermisoTipoTasa);
		pTipoTasa.setTipoTasaAnterior(locTipoTasaVieja);

		if(!pTipoTasa.activar()) {
			throw new HabilitacionesException(120);
		}

		// locPermisoTipoTasa.setTipoTasa(pTipoTasa);

		// this.entityManager.persist(locPermisoTipoTasa);
		this.entityManager.merge(pTipoTasa);

		if(locTipoTasaVieja != null) {
			locTipoTasaVieja.setFechaBaja(Calendar.getInstance().getTime());
			if(locTipoTasaVieja.getFechaVigenciaHasta() == null)
				locTipoTasaVieja.setFechaVigenciaHasta(Calendar.getInstance().getTime());
			this.entityManager.merge(locTipoTasaVieja);

			// List locListado = Criterio.getInstance(this.entityManager, DocHabilitanteEspecializado.class)
			// .add(Restriccion.IGUAL("tipoTasa",locTipoTasaVieja))
			// .list();
			//
			// for (Object cadaObjeto : locListado){
			// DocHabilitanteEspecializado cadaDocHabEspecializado = (DocHabilitanteEspecializado)cadaObjeto;
			// cadaDocHabEspecializado.setTipoTasa(pTipoTasa);
			// this.entityManager.merge(cadaDocHabEspecializado);
			// }
		}
	}

	public Double calcularTasaAlicuota(TipoTasa pTipoTasa, Map pListaValores) throws Exception {
		jep = this.getMotorFormulas();
		for(TipoParametro locTipoParametro : pTipoTasa.getListaParametros()) {
			if(locTipoParametro instanceof TipoParametroConstante) {
				jep.addVariable(locTipoParametro.getNombreVariable(), locTipoParametro.getValor(null));
			} else {
				Object valor = pListaValores.get(locTipoParametro.getNombreVariable());
				if(valor != null) {
					if(locTipoParametro.isBooleano()) {
						if(valor instanceof Double && ((Double) valor) > 0) {
							valor = 1d;
						}
					}
					jep.addVariable(locTipoParametro.getNombreVariable(), valor);
				} else {
					pListaValores.put(locTipoParametro.getNombreVariable(), 0d);
					jep.addVariable(locTipoParametro.getNombreVariable(), 0d);
				}
			}
		}
		// Variables simples no alicuotas
		for(VariableFormulaSimple cadaVariableSimple : pTipoTasa.getListaVariablesSimple()) {
			jep.parseExpression(cadaVariableSimple.getExpresion());
			cadaVariableSimple.setValor(jep.getValue());
			jep.addVariable(cadaVariableSimple.getNombre(), cadaVariableSimple.getValor());
		}
		// Parametros de las Alicuotas
		for(TipoParametroAlicuota cadaTipoParamatroAlicuota : pTipoTasa.getListaParamatrosAlicuota()) {
			jep.addVariable(cadaTipoParamatroAlicuota.getNombreVariable(), pListaValores.get(cadaTipoParamatroAlicuota.getNombreVariable()));
		}
		// Variables simples de Alicuotas
		for(VariableFormulaSimple cadaVariableSimple : pTipoTasa.getListaVariablesSimpleAlicuota()) {
			jep.parseExpression(cadaVariableSimple.getExpresion());
			cadaVariableSimple.setValor(jep.getValue());
			jep.addVariable(cadaVariableSimple.getNombre(), cadaVariableSimple.getValor());
		}
		// Variables compuestas
		for(VariableFormulaCompuesta cadaVariable : pTipoTasa.getListaVariablesFormulaCompuesta()) {
			jep.parseExpression(cadaVariable.getExpresion());
			jep.addVariable(cadaVariable.getNombre(), jep.getValue());
		}
		jep.parseExpression(pTipoTasa.getFormulaRegAlicuota());
		if(jep.hasError()) {
			System.out.println("ERROR EN LA FORMULA DE ALICUOTA:\n" + jep.getErrorInfo());
			throw new HabilitacionesException(111);
		}
		return jep.getValue();
	}

	/**
	 * Retorna el valor de la tasa sin modificadores de ningún tipo
	 * 
	 * @param pTipoTasa
	 * @param pListaValores
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public Double calcularTasa(TipoTasa pTipoTasa, java.util.Map pListaValores) throws Exception {

		jep = this.getMotorFormulas();
		this.validarFormula(pTipoTasa);

		for(TipoParametro locTipoParametro : pTipoTasa.getListaParametros()) {
			if(locTipoParametro instanceof TipoParametroConstante) {
				jep.addVariable(locTipoParametro.getNombreVariable(), locTipoParametro.getValor(null));
			} else if (locTipoParametro instanceof TipoParametroGrilla) {
				TipoParametroGrilla locGrilla = (TipoParametroGrilla) locTipoParametro;
				GrillaFunction locFunction = new GrillaFunction(locGrilla);
				jep.addFunction(locGrilla.getNombreVariable(), locFunction);
			} else {
				Object valor = pListaValores.get(locTipoParametro.getNombreVariable());
				if(valor != null) {
					if(locTipoParametro.isBooleano()) {
						if(valor instanceof Double && ((Double) valor) > 0) {
							valor = 1d;
						}
					}
					jep.addVariable(locTipoParametro.getNombreVariable(), valor);
				} else {
					// o 0f o null
					pListaValores.put(locTipoParametro.getNombreVariable(), 0d);
					jep.addVariable(locTipoParametro.getNombreVariable(), 0d);
				}
			}
		}
		for(VariableFormulaSimple cadaVariableSimple : pTipoTasa.getListaVariablesSimple()) {
			jep.parseExpression(cadaVariableSimple.getExpresion());
			cadaVariableSimple.setValor(jep.getValue());
			jep.addVariable(cadaVariableSimple.getNombre(), cadaVariableSimple.getValor());
		}
		jep.parseExpression(pTipoTasa.getFormula());
		Double locValor = new Double(jep.getValue());
		String locError = jep.getErrorInfo();
		if(locError != null) {
			System.out.println("Error en el parseo de fórmula. " + locError);
			throw new HabilitacionesException(117);
		}
		return locValor;
	}

	/**
	 * 
	 * @param pTipoTasa
	 * @param pValores
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public java.util.Map<String, Double> calcularModificadoresSobreTasa(com.trascender.habilitaciones.recurso.persistent.TipoTasa pTipoTasa, Map pValores) throws Exception {
		this.locValor = 0d;
		this.calcularValorTipoTasaSinModificadores(pTipoTasa, pValores);
		jep = this.getMotorFormulas();

		Map<String, Double> locRetorno = new HashMap<String, Double>();
		List<TipoModificador> listaModificadoresSobreTasa = new ArrayList<TipoModificador>();

		// primero separo los 2 tipos de parámetros
		for(TipoModificador locModificador : pTipoTasa.getListaModificadores()) {
			if(locModificador.isSobreSaldoNeto()) {
				listaModificadoresSobreTasa.add(locModificador);
			}
		}

		locRetorno.putAll(this.getValoresListado(listaModificadoresSobreTasa, jep));

		return locRetorno;
	}

	private void calcularValorTipoTasaSinModificadores(TipoTasa pTipoTasa, Map<String, Double> pValores) throws Exception {
		JEP jep = this.getMotorFormulas();
		// seteo los parámetros

		for(TipoParametro locTipoParametro : pTipoTasa.getListaParametros()) {
			if(locTipoParametro instanceof TipoParametroConstante) {
				jep.addVariable(locTipoParametro.getNombreVariable(), locTipoParametro.getValor(null));
			} else {
				jep.addVariable(locTipoParametro.getNombreVariable(), pValores.get(locTipoParametro.getNombreVariable()));
			}
		}

		if((this.locValor == null) || (this.locValor == 0)) {
			jep.parseExpression(pTipoTasa.getFormula());
			this.locValor = new Double(jep.getValue());
		}
	}

	private Map<String, Double> getValoresListado(List<TipoModificador> pListaModificadores, JEP jep) throws Exception {
		Double valorTotalTipoTasa = this.locValor;
		Map<String, Double> locRetorno = new HashMap<String, Double>();
		jep = this.getMotorFormulas();

		for(TipoModificador locModificador : pListaModificadores) {
			System.out.println("modificador: " + locModificador.getNombre());
			Double valor = 0d;
			if(locModificador.getCondicion() != null) {
				//VariablesFormula de cada Modificador
				for(VariableFormula cadaVariable : locModificador.getListaVariables()) {
					jep.parseExpression(cadaVariable.getExpresion());
					Double locValor = jep.getValue();
					jep.addVariable(cadaVariable.getNombre(), locValor);
				}
				jep.parseExpression(locModificador.getCondicion());

				Double locCondicion = new Double(jep.getValue());
				locCondicion = locCondicion + 0d;
				if(jep.getErrorInfo() != null) {
					throw new Exception(jep.getErrorInfo());
				}

				System.out.println("formula y condicion " + locModificador.getCondicion() + " - " + locCondicion);
				if(locCondicion != 0.0d) {
					valor = this.calcularModificadorTipoTasa(locModificador, locCondicion);
				} else {
					valor = 0d;
				}
			} else {
				valor = this.calcularModificadorTipoTasa(locModificador, 0d);

			}
			locRetorno.put(locModificador.getNombre() + ((locModificador.isFijo()) ? ("[" + valor + "]") : "[" + valor + "%]"), valor);

			valorTotalTipoTasa += valor;

			System.out.println("valor modificador: " + valor);
			System.out.println("valor tipoTasa: " + valorTotalTipoTasa);

		}
		this.locValor = valorTotalTipoTasa;
		return locRetorno;
	}

	/**
	 * Calcula el tipo de modificador para el tipo de tasa según corresponda si es porcentual o fijo
	 * 
	 * @param pTipoModificador
	 * @return
	 */
	private Double calcularModificadorTipoTasa(TipoModificador pTipoModificador, Double pValor) {
		if(pTipoModificador.isFijo()) {
			return pValor;
		} else {
			return locValor * pValor / 100;
		}
	}

	/**
	 * 
	 * @param pTipoTasa
	 * @param valores
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public java.util.Map<String, Double> calcularModificadoresSobreSubtotal(com.trascender.habilitaciones.recurso.persistent.TipoTasa pTipoTasa, java.util.Map pValores)
			throws Exception {
		this.locValor = 0d;

		// JEP jep=this.calcularValorTipoTasaSinModificadores(pTipoTasa,pValores);
		this.calcularValorTipoTasaSinModificadores(pTipoTasa, pValores);
		jep = this.getMotorFormulas();

		List<TipoModificador> listaModificadoresSobreTasa = new ArrayList<TipoModificador>();
		List<TipoModificador> listaModificadoresSobreTotal = new ArrayList<TipoModificador>();
		// primero separo los 2 tipos de parámetros
		for(TipoModificador locModificador : pTipoTasa.getListaModificadores()) {
			if(locModificador.isSobreSaldoNeto()) {
				listaModificadoresSobreTasa.add(locModificador);
			} else {
				listaModificadoresSobreTotal.add(locModificador);
			}
		}

		// Esto obtiene todos los valores sobre el tipo de tasa
		this.getValoresListado(listaModificadoresSobreTasa, jep);
		return this.getValoresListado(listaModificadoresSobreTotal, jep);
	}

	/**
	 * 
	 * @param pFechaLiquidacion
	 *            la fecha de liquidación, en caso de ser nula se toma la fecha actual
	 * @param pFechaCobro
	 *            fecha de cobro de la tasa, en caso de ser nula se toma la actual
	 * @param pTipoTasa
	 * @param pValores
	 * @param valores
	 *            Map<String, Double> donde la clave representa el nombre del parámetro.
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public java.util.Map<java.util.Date, Double> calcularVencimientos(Date pFechaLiquidacion, Date pFechaCobro, TipoTasa pTipoTasa, Map pValores) throws Exception {
		String error;
		if(pFechaCobro == null) {
			pFechaCobro = Calendar.getInstance().getTime();
		}
		if(pFechaLiquidacion == null) {
			pFechaLiquidacion = Calendar.getInstance().getTime();
		}

		// Map<Date, Double> locRetorno=new HashMap<Date,Double>();
		Map<Date, Double> locRetorno = new TreeMap<Date, Double>();

		// WRAPEO A CALENDAR LOS DATE
		Calendar locFechaCobro = Calendar.getInstance();
		locFechaCobro.setTime(pFechaCobro);

		Calendar locFechaLiquidacion = Calendar.getInstance();
		locFechaLiquidacion.setTime(pFechaLiquidacion);

		// OBTENGO EL PERÍODO
		Periodo locPeriodo = this.businessCalendario.getPeriodo(locFechaLiquidacion, null, locFechaLiquidacion.get(Calendar.YEAR), null, null);

		jep = this.getMotorFormulas();
		for(Object cadaObject : pValores.keySet()) { // String locValor
			jep.addVariable((String) cadaObject, pValores.get(cadaObject)); // Valor, pValores.get(locValor)
		}

		for(TipoParametro cadaTipoParametro : pTipoTasa.getListaParametros()) {
			if(cadaTipoParametro instanceof TipoParametroConstante) {
				jep.addVariable(cadaTipoParametro.getNombreVariable(), cadaTipoParametro.getValor(null));
			}
		}

		jep.parseExpression(pTipoTasa.getFormula());
		Double valorTasa = Double.valueOf(jep.getValue()).doubleValue();

		error = jep.getErrorInfo();

		TipoVencimiento[] locTipoVencimientos = new TipoVencimiento[pTipoTasa.getListaVencimientos().size()];

		pTipoTasa.getListaVencimientos().toArray(locTipoVencimientos);

		Calendar locFechaPrimerVencimiento = this.getFechaVencimiento(pFechaLiquidacion, locTipoVencimientos[0]);

		// Recorro los típos de parametros y les seteo los de vencimientos
		for(TipoParametro locTipoParametro : pTipoTasa.getListaParametros()) {
			if(locTipoParametro instanceof TipoParametroVencimiento) {
				System.out.println(locTipoParametro.getNombreVariable());
				switch(((TipoParametroVencimiento) locTipoParametro).getAtributoVencimiento()) {
					case IMPORTE_PRIMER_VENCIMIENTO:
						jep.addVariable(locTipoParametro.getNombreVariable(), valorTasa);
						System.out.println("Import primer vencim- Valor tasa" + valorTasa);
						break;
					case MESES_DESDE_INICIO_PERIODO:
						jep.addVariable(locTipoParametro.getNombreVariable(), this.getMesesDiferencia(locPeriodo.getFechaInicio(), locFechaCobro));
						System.out.println("meses desde inicio periodo- " + this.getMesesDiferencia(locPeriodo.getFechaInicio(), locFechaCobro));
						break;
					case MESES_DESDE_PRIMER_VENCIMIENTO:
						if(locFechaCobro.before(locFechaPrimerVencimiento)) {
							jep.addVariable(locTipoParametro.getNombreVariable(), 0);
							System.out.println("meses desde primer vencim 0");
						} else {
							jep.addVariable(locTipoParametro.getNombreVariable(), this.getMesesDiferencia(locFechaPrimerVencimiento, locFechaCobro));
							System.out.println("meses desde primer vencim- " + this.getMesesDiferencia(locFechaPrimerVencimiento, locFechaCobro));
						}
						break;
					case DIAS_DESDE_INICIO_PERIODO:
						jep.addVariable(locTipoParametro.getNombreVariable(), this.getDiasDiferencia(locPeriodo.getFechaInicio(), locFechaCobro));
						System.out.println("dias desde inicio periodo- " + this.getDiasDiferencia(locPeriodo.getFechaInicio(), locFechaCobro));
						break;
					case DIAS_DESDE_PRIMER_VENCIMIENTO:
						if(locFechaCobro.before(locFechaPrimerVencimiento)) {
							jep.addVariable(locTipoParametro.getNombreVariable(), 0);
							System.out.println("dias desde primer vencimiento 0- ");
						} else {
							jep.addVariable(locTipoParametro.getNombreVariable(), this.getDiasDiferencia(locFechaPrimerVencimiento, locFechaCobro));
							System.out.println("dias desde primer vencimiento - " + this.getDiasDiferencia(locFechaPrimerVencimiento, locFechaCobro));
						}
						break;

				}
			}
		}

		// Ordeno el array por fecha
		java.util.Arrays.sort(locTipoVencimientos, new Comparator<TipoVencimiento>() {
			public int compare(TipoVencimiento o1, TipoVencimiento o2) {
				int valor1 = o1.getMeses() * 100 + o1.getDias();
				int valor2 = o2.getMeses() * 100 + o2.getDias();
				return valor1 - valor2;
			}
		});

		for(TipoVencimiento locVencimiento : locTipoVencimientos) {
			Double resultado = 0d;
			Calendar locFechaVencimiento = Calendar.getInstance();

			if(locVencimiento.getNombre().equals("Vencimiento al Cobro")) {
				locFechaVencimiento = locFechaCobro;

			} else {
				locFechaVencimiento = this.getFechaVencimiento(locFechaLiquidacion.getTime(), locVencimiento);
			}

			boolean aplicable = true;
			if(locVencimiento.getCondicion() != null) {
				jep.parseExpression(locVencimiento.getCondicion());
				double locResultadoCondicion = jep.getValue();
				if(locResultadoCondicion <= 0) {
					aplicable = false;
				}
				error = jep.getErrorInfo();
			}

			System.out.println("modificador aplicable " + aplicable);
			if(aplicable) {
				// El valor de la tasa es el de la fórmula, en caso contrario es el del vencimiento
				if(locVencimiento != locTipoVencimientos[0]) {
					if(locVencimiento.getFormulaCalculo() != null) {
						jep.parseExpression(locVencimiento.getFormulaCalculo());
					} else {
						jep.parseExpression(pTipoTasa.getFormula());
					}
					valorTasa = new Double(jep.getValue()).doubleValue();
				}
				// ahora aplico todos los modificadores sobre la tasa
				List<TipoModificador> listaModificadoresSobreSubTotal = new ArrayList<TipoModificador>();
				List<TipoModificador> listaModificadoresSobreSaldoNeto = new ArrayList<TipoModificador>();
				for(TipoModificador cadaTipoModificador : pTipoTasa.getListaModificadores()) {
					boolean esModificadorAplicable = true;

					Calendar locFechaModificador = Calendar.getInstance();
					locFechaModificador = this.getFechaDesdeModificador(locFechaLiquidacion, cadaTipoModificador);

					if(cadaTipoModificador.getDesdeDias() > 0 || cadaTipoModificador.getDesdeMeses() > 0) {
						esModificadorAplicable = esModificadorAplicable && (locFechaVencimiento.after(locFechaModificador) || (locFechaVencimiento.equals(locFechaModificador)));
					}

					locFechaModificador = Calendar.getInstance();
					locFechaModificador = this.getFechaHastaModificador(locFechaLiquidacion, cadaTipoModificador);

					if(cadaTipoModificador.getHastaDias() > 0 || cadaTipoModificador.getHastaMeses() > 0) {
						esModificadorAplicable = esModificadorAplicable && (locFechaVencimiento.before(locFechaModificador) || (locFechaVencimiento.equals(locFechaModificador)));
					}

					// if (esModificadorAplicable){
					// if (locModificador.isSobreSaldoNeto()){
					// if (locModificador.isFijo()){
					// sumaModificadoresSobreTasa+=locValor;
					// }
					// else{
					// sumaModificadoresSobreTasa+=locValor*valorTasa/100;
					// }
					// }
					// else{
					// listaModificadoresSobreSubTotal.add(locModificador);
					// }
					// }

					if(esModificadorAplicable) {
						if(cadaTipoModificador.isSobreSaldoNeto()) {
							listaModificadoresSobreSaldoNeto.add(cadaTipoModificador);
						} else {
							listaModificadoresSobreSubTotal.add(cadaTipoModificador);
						}
					}
				}

				Double sumaModificadoresSobreTasa = 0d;
				for(TipoModificador cadaTipoModificador : listaModificadoresSobreSaldoNeto) {
					if(cadaTipoModificador.getCondicion() != null) {
						double locValor = 0;
						jep.parseExpression(cadaTipoModificador.getCondicion());
						locValor = jep.getValue();
						if(locValor == 0)
							continue;

						if(cadaTipoModificador.isFijo()) {
							sumaModificadoresSobreTasa += locValor;
						} else {
							sumaModificadoresSobreTasa += locValor * valorTasa / 100;
						}

					}
				}

				error = jep.getErrorInfo();
				resultado = valorTasa + sumaModificadoresSobreTasa;
				valorTasa = resultado;
				System.out.println("resultado " + valorTasa + " suma modificadores " + sumaModificadoresSobreTasa);
				for(TipoModificador cadaTipoModificador : listaModificadoresSobreSubTotal) {

					if(cadaTipoModificador.getCondicion() != null) {
						double locValor = 0;
						jep.parseExpression(cadaTipoModificador.getCondicion());
						locValor = jep.getValue();
						if(locValor == 0)
							continue;

						if(cadaTipoModificador.isFijo()) {
							resultado += locValor;
						} else {
							resultado += valorTasa * locValor / 100;
						}
					}
				}

				if(locVencimiento == locTipoVencimientos[0]) {
					for(TipoParametro locTipoParametro : pTipoTasa.getListaParametros()) {
						if(locTipoParametro instanceof TipoParametroVencimiento) {
							TipoParametroVencimiento locTipoParametroVencimiento = (TipoParametroVencimiento) locTipoParametro;
							if(locTipoParametroVencimiento.getAtributoVencimiento().equals(TipoParametroVencimiento.TipoAtributoVencimiento.IMPORTE_PRIMER_VENCIMIENTO)) {
								jep.setVarValue(locTipoParametroVencimiento.getNombreVariable(), resultado);
							}
						}
					}

				}

			} else {
				resultado = 0d;
			}

			if(error != null) {
				throw new Exception(error);
			}

			locRetorno.put(locFechaVencimiento.getTime(), resultado);

		}

		return locRetorno;
	}

	/**
	 * Calcula el valor de la fecha hasta la cual se aplica el modificador, partiendo del día de la liquidación
	 * 
	 * @param locFechaLiquidacion
	 * @param locModificador
	 * @return
	 */
	private Calendar getFechaHastaModificador(final Calendar locFechaLiquidacion, TipoModificador locModificador) {

		Calendar locCalendar = (Calendar) locFechaLiquidacion.clone();
		locCalendar.add(Calendar.MONTH, locModificador.getHastaMeses());
		locCalendar.add(Calendar.DAY_OF_MONTH, locModificador.getHastaDias());

		return locCalendar;
	}

	/**
	 * Calcula el valor de la fecha desde la cual se aplica el modificador, partiendo del día de la liquidación
	 * 
	 * @param locFechaLiquidacion
	 * @param locModificador
	 * @return
	 */

	private Calendar getFechaDesdeModificador(final Calendar locFechaLiquidacion, TipoModificador locModificador) {

		Calendar locCalendar = (Calendar) locFechaLiquidacion.clone();
		locCalendar.add(Calendar.MONTH, locModificador.getDesdeMeses());
		locCalendar.add(Calendar.DAY_OF_MONTH, locModificador.getDesdeDias());

		return locCalendar;
	}

	/**
	 * 
	 * Recupera la cantidad de dias de diferencia entre 2 fechas (solo
	 * 
	 * @param pFecha1
	 * @param pFecha2
	 * @return
	 */

	private int getDiasDiferencia(final Calendar pFecha1, final Calendar pFecha2) throws Exception {
		if(pFecha2.before(pFecha1)) {
			throw new HabilitacionesException(118);
		}
		int difAnios = pFecha2.get(Calendar.YEAR) - pFecha1.get(Calendar.YEAR);
		int difMeses = pFecha2.get(Calendar.MONTH) - pFecha1.get(Calendar.MONTH);
		int difDias = pFecha2.get(Calendar.DAY_OF_MONTH) - pFecha1.get(Calendar.DAY_OF_MONTH);

		Calendar locCalendario = (Calendar) pFecha1.clone();

		if(difAnios == 0) {
			return(pFecha2.get(Calendar.DAY_OF_YEAR) - pFecha1.get(Calendar.DAY_OF_YEAR));
		}

		if(difAnios > 0) {
			for(int i = 0; i < difAnios; i++) {
				locCalendario.add(Calendar.YEAR, 1);
				difDias += locCalendario.getActualMaximum(Calendar.DAY_OF_YEAR);
			}
		}

		return difDias;

	}

	/**
	 * Calcula los meses de diferencia entre dos fechas
	 * 
	 * @param pFecha1
	 *            fecha inferior
	 * @param pFecha2
	 *            fecha superior
	 * @return
	 */
	private int getMesesDiferencia(Calendar pFecha1, Calendar pFecha2) throws Exception {
		if(pFecha2.before(pFecha1)) {
			throw new HabilitacionesException(118);
		}
		int difAnios = pFecha2.get(Calendar.YEAR) - pFecha1.get(Calendar.YEAR);
		int difMeses = pFecha2.get(Calendar.MONTH) - pFecha1.get(Calendar.MONTH);
		int difDias = pFecha2.get(Calendar.DAY_OF_MONTH) - pFecha1.get(Calendar.DAY_OF_MONTH);

		if(difDias < 0) {
			difMeses--;
		}

		if(difAnios == 0) {
			return difMeses;
		}

		difMeses += difAnios * 12;

		return difMeses;
	}

	/**
	 * 
	 * @param pFechaLiquidacion
	 * @param pFechaCobro
	 * @param pTipoTasa
	 * @param pValores
	 * @return
	 * @throws Exception
	 */
	public Map<String, Double> calcularIntereses(Date pFechaLiquidacion, Date pFechaCobro, TipoTasa pTipoTasa, Map<String, Double> pValores) throws Exception {
		Map<Date, Double> vencimientos = this.calcularVencimientos(pFechaLiquidacion, pFechaCobro, pTipoTasa, pValores);

		// Obtengo la última fecha de vencimiento
		Date fechaUltimoVencimiento = null;
		for(Date cadaVencimiento : vencimientos.keySet()) {
			if((fechaUltimoVencimiento == null) || (fechaUltimoVencimiento.before(cadaVencimiento))) {
				fechaUltimoVencimiento = cadaVencimiento;
			}
		}

		// Significa que hay vencimientos
		if(pFechaCobro.after(fechaUltimoVencimiento)) {
			Map<String, Double> salida = new HashMap<String, Double>();

			Double importeVencimiento = vencimientos.get(fechaUltimoVencimiento);

			Calendar pFechaLiq = Calendar.getInstance();
			pFechaLiq.setTime(pFechaLiquidacion);

			Periodo periodo = this.businessCalendario.getPeriodo(pFechaLiq, null, pFechaLiq.get(Calendar.YEAR), null, null);
			this.setParametros(pValores);
			this.setParametrosVencimiento(periodo.getFechaInicio().getTime(), fechaUltimoVencimiento, pFechaCobro, importeVencimiento);

			Double valorInteres = this.calcularConceptoPorMora(pTipoTasa.getInteres()).doubleValue();
			jep.addVariable(TipoParametroInteres.IMPORTE_INTERES.getNombreVariable(), valorInteres);
			Double valorRecargo = this.calcularConceptoPorMora(pTipoTasa.getRecargo()).doubleValue();

			DateFormat locDateFormat = SimpleDateFormat.getDateInstance(DateFormat.SHORT);
			String textoInteres = "Interés al día " + locDateFormat.format(pFechaCobro);
			String textRecargo = "Recargo al día " + locDateFormat.format(pFechaCobro);

			salida.put(textoInteres, valorInteres);
			salida.put(textRecargo, valorRecargo);
			return salida;
		}
		return null;
	}

	/**
	 * Setea los valores al motor de jep
	 * 
	 * @param valores
	 */
	private void setParametros(Map<String, Double> valores) {
		JEP jep = this.getMotorFormulas();
		for(String cadaParametro : valores.keySet()) {
			jep.addVariable(cadaParametro, valores.get(cadaParametro));
		}
	}

	/**
	 * Calcula el valor del concepto por mora pasado por parámetro. Se basa en que las variables utilizadas ya se encuentran registradas
	 * 
	 * @param concepto
	 *            por mora
	 * @return
	 * @throws ParseException
	 */
	private Double calcularConceptoPorMora(ConceptoPorMora pInteres) throws ParseException {
		if(pInteres != null) {
			this.jep.parseExpression(pInteres.getFormula());
			Double locValor = jep.getValue();
			if(locValor < 0) {
				locValor = 0d;
			}
			return locValor;
		}
		return 0d;
	}

	/**
	 * Setea en JEP los parámetros de vencimiento
	 * 
	 * @param pFechaUltimoVencimiento
	 * @param pFechaCobro
	 * @param importeVencimiento
	 * @throws Exception
	 */
	private void setParametrosVencimiento(Date pFechaInicioPeriodo, Date pFechaUltimoVencimiento, Date pFechaCobro, Double importeVencimiento) throws Exception {
		JEP jep = this.getMotorFormulas();
		int mesesDesdeVencimiento = 0;
		int diasDesdeVencimiento = 0;
		int mesesDesdeInicioPeriodo = 0;
		int diasDesdeInicioPeriodo = 0;
		try {
			mesesDesdeVencimiento = Util.getMesesDiferencia(pFechaUltimoVencimiento, pFechaCobro);
			diasDesdeVencimiento = Util.getDiasDiferencia(pFechaUltimoVencimiento, pFechaCobro);

			Calendar fechaInicioPeriodo = Calendar.getInstance();
			fechaInicioPeriodo.setTime(pFechaInicioPeriodo);

			mesesDesdeInicioPeriodo = Util.getMesesDiferencia(pFechaInicioPeriodo, pFechaCobro);
			diasDesdeInicioPeriodo = Util.getDiasDiferencia(pFechaInicioPeriodo, pFechaCobro);

		} catch(TrascenderFrameworkException e) {
			if(e.getCodeTrascenderException() != 1500) {
				throw e;
			}
		} finally {
			// Seteo los valores a las variables
			jep.addVariable(IMPORTE_PRIMER_VENCIMIENTO.getNombreVariable(), importeVencimiento.doubleValue());
			jep.addVariable(MESES_DESDE_PRIMER_VENCIMIENTO.getNombreVariable(), mesesDesdeVencimiento);
			jep.addVariable(DIAS_DESDE_PRIMER_VENCIMIENTO.getNombreVariable(), diasDesdeVencimiento);
			jep.addVariable(MESES_DESDE_INICIO_PERIODO.getNombreVariable(), mesesDesdeInicioPeriodo);
			jep.addVariable(DIAS_DESDE_INICIO_PERIODO.getNombreVariable(), diasDesdeInicioPeriodo);
		}

	}

	/**
	 * Retorna la fecha del vencimiento a partir de una fecha y el tipo de vencimiento
	 * 
	 * @param pFechaDesde
	 *            fecha desde la cual contar los meses y dias del vencimiento
	 * @param pTipoVencimiento
	 *            tipo de vencimiento
	 */
	private Calendar getFechaVencimiento(Date pFechaDesde, TipoVencimiento pTipoVencimiento) {
		Calendar fecha = Calendar.getInstance();
		fecha.setTime(pFechaDesde);
		fecha.add(Calendar.DAY_OF_MONTH, pTipoVencimiento.getDias());
		fecha.add(Calendar.MONTH, pTipoVencimiento.getMeses());
		return fecha;
	}

	public void addPlan(Plan pPlan) throws TrascenderException {
		validarPlan(pPlan);
		TrascenderEnverListener.setValoresEnAuditoriaBean(pPlan);
		entityManager.persist(pPlan);
		entityManager.flush();
	}

	public void updatePlan(Plan pPlan) throws TrascenderException {
		validarPlan(pPlan);
		TrascenderEnverListener.setValoresEnAuditoriaBean(pPlan);
		entityManager.merge(pPlan);
		entityManager.flush();
	}

	public void deletePlan(Plan pPlan) {
		TrascenderEnverListener.setValoresEnAuditoriaBean(pPlan);
		entityManager.remove(entityManager.merge(pPlan));
		entityManager.flush();
	}

	public FiltroPlan findListaPlan(FiltroPlan pFiltro) {
		Criterio locCriterio = Criterio.getInstance(entityManager, Plan.class).add(Restriccion.IGUAL("nombre", pFiltro.getNombre()))
				.add(Restriccion.IGUAL("tipoObligacion", pFiltro.getTipoObligacion()));
		BusquedaPorLog.addRestriccionesCriterio(locCriterio, Plan.serialVersionUID, "idPlan", pFiltro.getListaBusquedaPorLogs());
		pFiltro.procesarYListar(locCriterio);
		return pFiltro;
	}

	private void validarPlan(Plan pPlan) throws TrascenderException {
		if(pPlan.isPorDefecto()) {
			Criterio locCriterio = Criterio.getInstance(entityManager, Plan.class).add(Restriccion.IGUAL("tipoObligacion", pPlan.getTipoObligacion()))
					.add(Restriccion.IGUAL("porDefecto", true)).add(Restriccion.DISTINTO("idPlan", pPlan.getIdPlan())).setProyeccion(Proyeccion.COUNT());
			long cantidad = locCriterio.uniqueResult();
			if(cantidad > 0) {
				throw new HabilitacionesException(125);
			}
		}
	}

	public Plan getPlanPorId(long pId) throws Exception {
		Plan locPlan = (Plan) Criterio.getInstance(this.entityManager, Plan.class).add(Restriccion.IGUAL("idPlan", pId)).uniqueResult();
		if(locPlan != null) {
			locPlan.getListaLogsAuditoria().size();
		}
		return locPlan;
	}

	public void addTipoParametroGrilla(TipoParametroGrilla pTipoParametro) throws TrascenderException {
		this.validarTipoParametroGrilla(pTipoParametro);
		this.entityManager.merge(pTipoParametro);
	}

	private void validarTipoParametroGrilla(TipoParametroGrilla pTipoParametro) throws TrascenderException {
		Criterio locCriterio = Criterio.getInstance(entityManager, TipoParametroGrilla.class).add(Restriccion.LIKE("nombreVariable", pTipoParametro.getNombreVariable(), false))
				.add(Restriccion.DISTINTO("idTipoParametro", pTipoParametro.getIdTipoParametro())).setProyeccion(Proyeccion.COUNT());

		Long cantidad = locCriterio.uniqueResult();

		if(cantidad > 0) {
			throw new HabilitacionesException(536);
		}

		for(TipoParametroGrillaVariable cadaVariable : pTipoParametro.getListaVariables()) {
			cadaVariable.setGrilla(pTipoParametro);
		}
		for(TipoParametroGrillaFila cadaFila : pTipoParametro.getFilas()) {
			cadaFila.setGrilla(pTipoParametro);
			for(TipoParametroGrillaFilaColumna cadaColumna : cadaFila.getColumnas()) {
				cadaColumna.setFila(cadaFila);
			}
		}
	}

	public void updateTipoParametroGrilla(TipoParametroGrilla pTipoParametro) throws TrascenderException {
		this.validarTipoParametroGrilla(pTipoParametro);
		this.entityManager.merge(pTipoParametro);
	}

	public void deleteTipoParametroGrilla(TipoParametroGrilla pTipoParametro) {
		entityManager.remove(entityManager.merge(pTipoParametro));
		entityManager.flush();
	}

	public FiltroTipoParametroGrilla findListaTipoParametroGrilla(FiltroTipoParametroGrilla pFiltro) {
		Criterio locCriterio = Criterio.getInstance(entityManager, TipoParametroGrilla.class).add(Restriccion.ILIKE("nombreVariable", pFiltro.getNombre()));

		pFiltro.procesarYListar(locCriterio);
		return pFiltro;
	}

	public TipoParametroGrilla getTipoParametroGrillaPorId(Long pId) {
		TipoParametroGrilla locGrilla = entityManager.find(TipoParametroGrilla.class, pId);

		if(locGrilla != null) {
			for(TipoParametroGrillaFila cadaFila : locGrilla.getFilas()) {
				cadaFila.getColumnas().toString();
			}
			locGrilla.getListaVariables().toString();
		}

		return locGrilla;
	}

}