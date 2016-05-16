
package com.trascender.contabilidad.business.ejb;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.nfunk.jep.JEP;

import ar.trascender.criterio.clases.Criterio;
import ar.trascender.criterio.clases.Orden;
import ar.trascender.criterio.clases.Proyeccion;
import ar.trascender.criterio.clases.Restriccion;

import com.trascender.contabilidad.business.interfaces.BusinessIngresoVarioLocal;
import com.trascender.contabilidad.exception.TrascenderContabilidadException;
import com.trascender.contabilidad.recurso.filtros.FiltroConceptoIngresoVario;
import com.trascender.contabilidad.recurso.filtros.FiltroIngresoVario;
import com.trascender.contabilidad.recurso.persistent.ConceptoIngresoVario;
import com.trascender.contabilidad.recurso.persistent.ImputacionIngresoVario;
import com.trascender.contabilidad.recurso.persistent.IngresoVario;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Rol;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.recurso.transients.AtributoConsultable;
import com.trascender.framework.recurso.transients.AtributoConsultable.Tipo;
import com.trascender.framework.recurso.transients.AuxIdEntidad;
import com.trascender.framework.recurso.transients.Grupo;
import com.trascender.framework.recurso.transients.Recurso;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.framework.util.Util;
import com.trascender.habilitaciones.business.interfaces.BusinessTipoTasaLocal;
import com.trascender.habilitaciones.recurso.persistent.ConceptoPorMora;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.TipoParametro;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroConstante;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroVencimiento;
import com.trascender.habilitaciones.recurso.persistent.TipoTasa;
import com.trascender.habilitaciones.recurso.persistent.enums.TipoParametroInteres;
import com.trascender.habilitaciones.recurso.persistent.tipoParametroGrilla.TipoParametroGrilla;
import com.trascender.habilitaciones.util.GrillaFunction;
import com.trascender.habilitaciones.util.MotorFormulas;
import com.trascender.saic.recurso.persistent.ParametroValuado;
import com.trascender.saic.recurso.persistent.ParametroValuadoDouble;
import com.trascender.saic.recurso.persistent.ParametroValuadoString;
import com.trascender.saic.recurso.persistent.RegistroDeuda;
import com.trascender.saic.recurso.persistent.RegistroDeuda.EstadoRegistroDeuda;

@Stateless(name = "ejb/BusinessIngresoVarioLocal")
public class BusinessIngresoVarioBean implements BusinessIngresoVarioLocal {
	static {
		Grupo grupoRecursos = new Grupo();
		grupoRecursos.setId(BusinessIngresoVarioBean.serialVersionUID);
		grupoRecursos.setNombre(BusinessIngresoVarioBean.NOMBRE);

		Recurso ingresoVario = new Recurso();
		ingresoVario.setIdRecurso(IngresoVario.serialVersionUID);
		ingresoVario.setNombre("Ingresos Varios");
		ingresoVario.setAtributosConsultables("Número", "numero", 
				"Persona", "persona", 
				"Concepto", "conceptoIngresoVario", AtributoConsultable.Tipo.TEXTO_LARGO, 
				"Monto", "monto", Tipo.MONTO, 
				"Fecha Emisión", "fechaEmision", Tipo.FECHA, 
				"Estado", "estado");
		ingresoVario.setClase(IngresoVario.class);
		grupoRecursos.getListaRecursos().add(ingresoVario);

		Recurso conceptoIngresoVario = new Recurso();
		conceptoIngresoVario.setIdRecurso(ConceptoIngresoVario.serialVersionUID);
		conceptoIngresoVario.setNombre("Conceptos Ingresos Varios");
		conceptoIngresoVario.setAtributosConsultables("Nombre", "nombre", "Valor por Defecto", "valorPorDefecto");
		conceptoIngresoVario.setClase(ConceptoIngresoVario.class);
		grupoRecursos.getListaRecursos().add(conceptoIngresoVario);

		SecurityMgr.getInstance().addGrupo(grupoRecursos);
	}

	private static final long serialVersionUID = 5513255615940373091L;
	private static final String NOMBRE = "CAJ|Adm. Ingresos Varios";

	@PersistenceContext
	private EntityManager entity;
	private Date fechaActualizacionRefinanciacion;
	
	@EJB
	private BusinessTipoTasaLocal businessTipoTasa;
	private JEP jep;

	public BusinessIngresoVarioBean() {
	}

	public void ejbActivate() throws EJBException, RemoteException {

	}

	public void ejbPassivate() throws EJBException, RemoteException {

	}

	public void ejbRemove() throws EJBException, RemoteException {
	}

	public void setSessionContext(SessionContext ctx) throws EJBException, RemoteException {

	}

	public void ejbCreate() throws CreateException {
	}

	// Concepto Ingreso Vario
	public ConceptoIngresoVario addConceptoIngresoVario(ConceptoIngresoVario pConceptoIngresoVario) throws Exception {
		this.validarConceptoIngresoVario(pConceptoIngresoVario);
		entity.persist(pConceptoIngresoVario);
		return pConceptoIngresoVario;
	}

	public ConceptoIngresoVario updateConceptoIngresoVario(ConceptoIngresoVario pConceptoIngresoVario) throws java.lang.Exception {
		this.validarConceptoIngresoVario(pConceptoIngresoVario);
		entity.merge(pConceptoIngresoVario);
		return pConceptoIngresoVario;
	}

	private void validarConceptoIngresoVario(ConceptoIngresoVario pConceptoIngresoVario) throws TrascenderException {
		Criterio locCriterio = Criterio.getInstance(entity, ConceptoIngresoVario.class)
				.add(Restriccion.DISTINTO("idConceptoIngresoVario", pConceptoIngresoVario.getIdConceptoIngresoVario()))
				.add(Restriccion.LIKE("nombre", pConceptoIngresoVario.getNombre(), false)).setProyeccion(Proyeccion.COUNT());

		Long cantidad = locCriterio.uniqueResult();
		if(cantidad > 0) {
			throw new TrascenderContabilidadException(516);
		}
		pConceptoIngresoVario.calcularValorPorDefecto();
	}

	public void deleteConceptoIngresoVario(com.trascender.contabilidad.recurso.persistent.ConceptoIngresoVario pConceptoIngresoVario) throws java.lang.Exception {
		this.validarEliminarConceptoIngresoVario(pConceptoIngresoVario);
		entity.remove(entity.merge(pConceptoIngresoVario));
	}

	private void validarEliminarConceptoIngresoVario(ConceptoIngresoVario pConceptoIngresoVario) throws TrascenderContabilidadException {
		long locCantidadDepen = Criterio.getInstance(this.entity, IngresoVario.class).add(Restriccion.IGUAL("conceptoIngresoVario", pConceptoIngresoVario))
				.setProyeccion(Proyeccion.COUNT()).uniqueResult();

		if(locCantidadDepen > 0) {
			throw new TrascenderContabilidadException(515);
		}

	}

	public com.trascender.contabilidad.recurso.persistent.ConceptoIngresoVario getConceptoIngresoVarioByID(Long pIdConceptoIngresoVario) throws java.lang.Exception {
		ConceptoIngresoVario locConceptoIngresoVario = entity.find(ConceptoIngresoVario.class, pIdConceptoIngresoVario);
		locConceptoIngresoVario.getListaRelaConceptoIngresoVarioCuenta().size();
		locConceptoIngresoVario.getListaIngresoVario().size();
		locConceptoIngresoVario.getListaRoles().size();
		locConceptoIngresoVario.getListaUsuarios().size();
		return locConceptoIngresoVario;
	}
	
	public List<AuxIdEntidad> findListaAuxIdConceptoIngresoVario(String cadena, Usuario pUsuario) {
		Criterio locCriterio = Criterio.getInstance(entity, ConceptoIngresoVario.class);
		locCriterio.add(Restriccion.ILIKE("nombre", cadena));
		
		if (!pUsuario.getUser().equals("root")) {
			Restriccion[] arregloRestricciones = new Restriccion[pUsuario.getListaRoles().size() + 1];
			int i = 0;
			for(Iterator<Rol> locIterador = pUsuario.getListaRoles().iterator(); locIterador.hasNext();) {
				Rol cadaRol = locIterador.next();
				arregloRestricciones[i] = Restriccion.IGUAL("cadaRol", cadaRol);
				i++;
			}
			arregloRestricciones[i] = Restriccion.IGUAL("cadaUsuario", pUsuario);
	
			locCriterio.add(Restriccion.OR(arregloRestricciones));
			locCriterio.crearAliasLeft("listaRoles", "cadaRol");
			locCriterio.crearAliasLeft("listaUsuarios", "cadaUsuario");
		}
		locCriterio.setProyeccion(Proyeccion.NEW(AuxIdEntidad.class, "idConceptoIngresoVario", "nombre"));
		locCriterio.setModoDebug(true);
		List<AuxIdEntidad> locListaResultado = locCriterio.list();
		return locListaResultado;
	}

	public com.trascender.contabilidad.recurso.persistent.IngresoVario addIngresoVario(com.trascender.contabilidad.recurso.persistent.IngresoVario pIngresoVario)
			throws java.lang.Exception {
		this.validarImputaciones(pIngresoVario);
		Integer locNumero = Criterio.getInstance(entity, IngresoVario.class).add(Orden.DESC("numero")).setMaxResults(1).setProyeccion(Proyeccion.PROP("numero")).uniqueResult();
		if(locNumero == null) {
			locNumero = new Integer(1);
		}
		pIngresoVario.setNumero(locNumero + 1);
		Obligacion locObligacion = pIngresoVario.getDocGeneradorDeuda().getObligacion();
		locObligacion.setNombre("Ingreso vario");
		locObligacion = entity.merge(locObligacion);
		pIngresoVario.getDocGeneradorDeuda().setObligacion(locObligacion);
		entity.persist(pIngresoVario);
		return pIngresoVario;
	}

	public com.trascender.contabilidad.recurso.persistent.IngresoVario updateIngresoVario(com.trascender.contabilidad.recurso.persistent.IngresoVario pIngresoVario)
			throws java.lang.Exception {
		this.validarImputaciones(pIngresoVario);
		entity.merge(pIngresoVario);
		return pIngresoVario;
	}

	public com.trascender.contabilidad.recurso.persistent.IngresoVario getIngresoVarioByID(Long pIdIngresoVario) throws java.lang.Exception {
		
		Criterio locCriterio = Criterio.getInstance(entity, IngresoVario.class)
//				.add(Restriccion.IGUAL("idRegistroDeuda", pIdIngresoVario))
				.add(Restriccion.ID(pIdIngresoVario))
				.crearFetchAlias("docGeneradorDeuda.obligacion.persona", "persona")
				.setModoDebug(true);
		IngresoVario locIngresoVario = locCriterio.uniqueResult();
		if(locIngresoVario != null) {
			locIngresoVario.getConceptoIngresoVario().getListaRelaConceptoIngresoVarioCuenta().size();
			locIngresoVario.getFechaEmision();
			locIngresoVario.getValor();
			locIngresoVario.getNombre();
			locIngresoVario.getPersona();
			locIngresoVario.getPersona().toString();
			locIngresoVario.getListaImputacionIngresos().size();
		}
		return locIngresoVario;
	}

	public void deleteIngresoVario(IngresoVario pIngresoVario) throws Exception {
		this.validarEliminarIngresoVario(pIngresoVario);
		pIngresoVario.setEstado(EstadoRegistroDeuda.ANULADA);
		this.entity.merge(pIngresoVario);
	}

	/**
	 * Valida que el IngresoVario no sea nulo <br>
	 * y que no permita borrar uno que no este en estado creado.
	 * 
	 * @param pIngresoVario
	 * @throws Exception
	 */
	private void validarEliminarIngresoVario(IngresoVario pIngresoVario) throws Exception {
		if(pIngresoVario == null) {
			throw new TrascenderContabilidadException(42);
		}

		if(!pIngresoVario.getEstado().equals(EstadoRegistroDeuda.VIGENTE)) {
			throw new TrascenderContabilidadException(41);
		}

	}

	private void validarImputaciones(IngresoVario pIngresoVario) {
		for(ImputacionIngresoVario cadaImputacion : pIngresoVario.getListaImputacionIngresos()) {
			cadaImputacion.setIngresoVario(pIngresoVario);
		}
	}

	public FiltroIngresoVario findListaIngresoVario(FiltroIngresoVario pFiltro) throws java.lang.Exception {
		
		pFiltro.getMapaOrden().put("numero", FiltroAbstracto.DESC);
		
		Criterio locCriterio = Criterio.getInstance(entity, IngresoVario.class)
			.add(Restriccion.IGUAL("conceptoIngresoVario", pFiltro.getConceptoIngresoVario()))
			.add(Restriccion.IGUAL("estado", pFiltro.getEstado()))
			.add(Restriccion.IGUAL("numero", pFiltro.getNumero()))
			.add(Restriccion.MAYOR("fechaEmision", pFiltro.getFechaDesde()))
			.add(Restriccion.MENOR("fechaEmision", pFiltro.getFechaHasta()));

		if(pFiltro.getPersona() != null) {
			locCriterio.add(Restriccion.IGUAL("docGeneradorDeuda.obligacion.persona", pFiltro.getPersona()));
		} else {
			locCriterio.add(Restriccion.EN("docGeneradorDeuda.obligacion.persona.idPersona", pFiltro.getListaIdPersonas()));
		}

		pFiltro.procesarYListar(locCriterio);

		for(IngresoVario cadaIngresoVario : pFiltro.getListaResultados()) {
			cadaIngresoVario.getPersona().toString();
		}
		return pFiltro;
	}

	public FiltroConceptoIngresoVario findListaConceptoIngresoVario(FiltroConceptoIngresoVario pFiltro) throws java.lang.Exception {
		Criterio locCriterio = Criterio.getInstance(entity, ConceptoIngresoVario.class).add(Restriccion.ILIKE("nombre", pFiltro.getNombre()));

		pFiltro.procesarYListar(locCriterio);

		for(ConceptoIngresoVario cadaConcepto : pFiltro.getListaResultados()) {
			cadaConcepto.getListaRelaConceptoIngresoVarioCuenta().size();
			cadaConcepto.getListaIngresoVario().size();
			cadaConcepto.getListaRoles().size();
			cadaConcepto.getListaUsuarios().size();
		}

		return pFiltro;
	}

	public List<ConceptoIngresoVario> findListaConceptoIngresoVarioPorUsuario(Usuario pUsuario) throws java.lang.Exception {
		Criterio locCriterio = Criterio.getInstance(entity, ConceptoIngresoVario.class);

		Restriccion[] arregloRestricciones = new Restriccion[pUsuario.getListaRoles().size() + 1];

		int i = 0;
		for(Iterator<Rol> locIterador = pUsuario.getListaRoles().iterator(); locIterador.hasNext();) {
			Rol cadaRol = locIterador.next();
			arregloRestricciones[i] = Restriccion.IGUAL("cadaRol", cadaRol);
			i++;
		}

		arregloRestricciones[i] = Restriccion.IGUAL("cadaUsuario", pUsuario);

		locCriterio.add(Restriccion.OR(arregloRestricciones));
		locCriterio.crearAliasLeft("listaRoles", "cadaRol");
		locCriterio.crearAliasLeft("listaUsuarios", "cadaUsuario");
		locCriterio.setModoDebug(true);

		List<ConceptoIngresoVario> listaResultado = locCriterio.list();
		for(ConceptoIngresoVario cadaConcepto : listaResultado) {
			cadaConcepto.getListaRelaConceptoIngresoVarioCuenta().size();
			cadaConcepto.getListaIngresoVario().size();
			cadaConcepto.getListaRoles().size();
			cadaConcepto.getListaUsuarios().size();
		}

		return listaResultado;
	}
	
	public void actualizarDeudaIngresoVario(IngresoVario pIngresoVario, Date pFecha, boolean pAplicarInteres) throws Exception {
		fechaActualizacionRefinanciacion = pFecha;
		Double locValorInteresAnterior = pIngresoVario.getInteres();

		boolean estaVencida = Util.isFechaAfterNoTima(pFecha, pIngresoVario.getFechaVencimientoOriginal());

		// Calculo interés solo cuando el ingreso vario esta vencido y el parametro asi lo indique
		if(estaVencida && pAplicarInteres) {
			this.jep = MotorFormulas.initializeJEP();

			pIngresoVario = getIngresoVarioByID(pIngresoVario.getIdRegistroDeuda());

			pIngresoVario.setInteres(0d);
			TipoTasa locTipoTasa = pIngresoVario.getConceptoIngresoVario().getTipoTasa();

			// Calculamos y agregamos los parametros de vencimiento, también reemplazamos los viejos en la liquidacion.
			List<ParametroValuado> listaParametrosVencimientosActualizados = this.getListaParametrosVencimientosActualizados(pIngresoVario);
			for(ParametroValuado cadaParametroValuado : listaParametrosVencimientosActualizados) {
				this.jep.addVariable(cadaParametroValuado.getNombreParametro(), cadaParametroValuado.getValorParametro());
			}

			/*
			 * Agregamos los Parametros Grilla que podria haber en la Tipo Tasa. Esto provaca el recalculo de algunas cosas, que es algo que no queremos hacer
			 * aqui, pero solo seria para Intereses y Recargos, y eso está bien.
			 */
			for(TipoParametro cadaTipoParametro : locTipoTasa.getListaParametros()) {
				if(cadaTipoParametro instanceof TipoParametroGrilla) {
					TipoParametroGrilla locTipoParametroGrilla = businessTipoTasa.getTipoParametroGrillaPorId(cadaTipoParametro.getIdTipoParametro());

					GrillaFunction cadaFunction = new GrillaFunction(TipoParametroGrilla.getGrillaDeCache(locTipoParametroGrilla));
					jep.addFunction(cadaTipoParametro.getNombreVariable(), cadaFunction);
				} else if(cadaTipoParametro instanceof TipoParametroConstante) {
					TipoParametroConstante tpc = (TipoParametroConstante) cadaTipoParametro;
					jep.addVariable(cadaTipoParametro.getNombreVariable(), tpc.getValor());
				}
			}

			this.setInteres(pIngresoVario);
		} else if(pAplicarInteres) {
			pIngresoVario.setInteres(locValorInteresAnterior);
		} else {
			pIngresoVario.setInteres(0D);
		}

		pIngresoVario.setEstado(RegistroDeuda.EstadoRegistroDeuda.VIGENTE);
		pIngresoVario.setFechaVencimiento(pFecha);

		entity.merge(pIngresoVario);
	}

	/**
	 * Calcula el interés y el recargo por mora, necesita que estén seteados los valores del jep
	 */
	private void setInteres(IngresoVario pIngresoVario) {
		TipoTasa locFormula = pIngresoVario.getConceptoIngresoVario().getTipoTasa();
		ConceptoPorMora locInteres = locFormula.getInteres();

		Double locValorInteres = this.calcularConceptoPorMora(locInteres);
		jep.addVariable(TipoParametroInteres.IMPORTE_INTERES.getNombreVariable(), locValorInteres);

		pIngresoVario.setInteres(locValorInteres);
	}

	private Double calcularConceptoPorMora(ConceptoPorMora pConceptoPorMora) {
		Double valorRetorno = 0d;
		if(pConceptoPorMora != null) {
			jep.parseExpression(pConceptoPorMora.getFormula());
			if(jep.getErrorInfo() != null) {
				System.err.println(jep.getErrorInfo());
				valorRetorno = 0d;
			} else {
				Double valor = jep.getValue();
				valorRetorno = Util.redondear(valor.doubleValue(), 2);
			}
		}

		return valorRetorno;
	}

	/**
	 * @return la lista actualizada de parámetros valuados
	 */
	private List<ParametroValuado> getListaParametrosVencimientosActualizados(IngresoVario pIngresoVario) throws Exception {
		List<ParametroValuado> locListaNuevosParametrosVencimiento = new ArrayList<ParametroValuado>();

		locListaNuevosParametrosVencimiento.add(this.getParametroImporteVencimiento(pIngresoVario));
		locListaNuevosParametrosVencimiento.add(this.getParametroDiasDesdeVencimiento(pIngresoVario));
		locListaNuevosParametrosVencimiento.add(this.getParametroMesesDesdeVencimiento(pIngresoVario));
		locListaNuevosParametrosVencimiento.add(this.getParametroFechaActualizacionDeuda());
		locListaNuevosParametrosVencimiento.add(this.getParametroFechaVencimiento(pIngresoVario));

		return locListaNuevosParametrosVencimiento;
	}

	private ParametroValuado getParametroImporteVencimiento(IngresoVario pIngresoVario) {
		ParametroValuadoDouble locParametroValuado = new ParametroValuadoDouble();
		locParametroValuado.setValorParametro(pIngresoVario.getMonto());
		locParametroValuado.setNombreParametro(Util.getEnumNameFromString(TipoParametroVencimiento.TipoAtributoVencimiento.IMPORTE_PRIMER_VENCIMIENTO.toString()));

		return locParametroValuado;
	}

	private ParametroValuado getParametroDiasDesdeVencimiento(IngresoVario pIngresoVario) throws Exception {
		ParametroValuadoDouble locParametroValuado = new ParametroValuadoDouble();
		locParametroValuado.setNombreParametro(Util.getEnumNameFromString(TipoParametroVencimiento.TipoAtributoVencimiento.DIAS_DESDE_PRIMER_VENCIMIENTO.toString()));

		if(pIngresoVario.getFechaVencimiento().after(pIngresoVario.getFechaVencimientoOriginal())) {
			locParametroValuado.setValorParametro(Integer.valueOf(Util.getDiasDiferencia(pIngresoVario.getFechaVencimientoOriginal(), pIngresoVario.getFechaVencimiento()))
					.doubleValue());
		} else {
			locParametroValuado.setValorParametro(0D);
		}

		return locParametroValuado;
	}

	private ParametroValuado getParametroMesesDesdeVencimiento(IngresoVario pIngresoVario) throws Exception {
		ParametroValuadoDouble locParametroValuado = new ParametroValuadoDouble();
		locParametroValuado.setNombreParametro(Util.getEnumNameFromString(TipoParametroVencimiento.TipoAtributoVencimiento.MESES_DESDE_PRIMER_VENCIMIENTO.toString()));

		if(pIngresoVario.getFechaVencimiento().after(pIngresoVario.getFechaVencimientoOriginal())) {
			locParametroValuado.setValorParametro(Integer.valueOf(Util.getMesesDiferencia(pIngresoVario.getFechaVencimientoOriginal(), pIngresoVario.getFechaVencimiento()))
					.doubleValue());
		} else {
			locParametroValuado.setValorParametro(0D);
		}

		return locParametroValuado;
	}

	private ParametroValuado getParametroFechaActualizacionDeuda() throws Exception {
		ParametroValuadoString locParametroValuado = new ParametroValuadoString();
		locParametroValuado.setValorParametro(new SimpleDateFormat("dd/MM/yyyy").format(fechaActualizacionRefinanciacion));
		locParametroValuado.setNombreParametro(Util.getEnumNameFromString(TipoParametroVencimiento.TipoAtributoVencimiento.FECHA_ACTUALIZACION_DEUDA.toString()));

		return locParametroValuado;
	}

	private ParametroValuado getParametroFechaVencimiento(IngresoVario pIngresoVario) {
		ParametroValuadoString locParametroValuado = new ParametroValuadoString();
		locParametroValuado.setNombreParametro(Util.getEnumNameFromString(TipoParametroVencimiento.TipoAtributoVencimiento.FECHA_VENCIMIENTO_CUOTA.toString()));
		locParametroValuado.setValorParametro(new SimpleDateFormat("dd/MM/yyyy").format(pIngresoVario.getFechaVencimientoOriginal()));

		return locParametroValuado;
	}
}

