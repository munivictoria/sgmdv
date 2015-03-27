
package com.trascender.saic.business.ejb;

import java.io.File;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.ejb.CreateException;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Hibernate;
import org.jboss.ejb3.annotation.TransactionTimeout;
import org.nfunk.jep.JEP;
import org.nfunk.jep.Variable;

import ar.trascender.criterio.clases.Criterio;
import ar.trascender.criterio.clases.Orden;
import ar.trascender.criterio.clases.Proyeccion;
import ar.trascender.criterio.clases.Restriccion;
import ar.trascender.criterio.enums.TipoSubconsulta;

import com.trascender.catastro.business.interfaces.BusinessRegistroGeograficoLocal;
import com.trascender.catastro.business.interfaces.BusinessRegistroParcelarioLocal;
import com.trascender.catastro.business.interfaces.BusinessZonificacionLocal;
import com.trascender.catastro.recurso.persistent.Calle;
import com.trascender.catastro.recurso.persistent.Cuadra;
import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.catastro.recurso.persistent.ParcelaPorCuadra;
import com.trascender.catastro.recurso.rfr.DocHabEspecializadoRfr;
import com.trascender.framework.business.interfaces.BusinessMunicipalidadLocal;
import com.trascender.framework.business.interfaces.BusinessParametroLocal;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.exception.TrascenderFrameworkException;
import com.trascender.framework.recurso.filtros.FiltroDiaFeriado;
import com.trascender.framework.recurso.persistent.DiaFeriado;
import com.trascender.framework.recurso.persistent.ParametroSistemaString;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
import com.trascender.framework.recurso.transients.AtributoConsultable.Tipo;
import com.trascender.framework.recurso.transients.Grupo;
import com.trascender.framework.recurso.transients.Periodo;
import com.trascender.framework.recurso.transients.Recurso;
import com.trascender.framework.util.BusquedaPorLog;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.framework.util.Periodicidad;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.framework.util.Util;
import com.trascender.habilitaciones.business.interfaces.BusinessDocumentoOSPLocal;
import com.trascender.habilitaciones.business.interfaces.BusinessObligacionLocal;
import com.trascender.habilitaciones.business.interfaces.BusinessPeriodoLocal;
import com.trascender.habilitaciones.business.interfaces.BusinessTipoTasaLocal;
import com.trascender.habilitaciones.recurso.filtros.FiltroObligacionSHPS;
import com.trascender.habilitaciones.recurso.persistent.AsocRegAlicuota;
import com.trascender.habilitaciones.recurso.persistent.CalendarioMunicipal;
import com.trascender.habilitaciones.recurso.persistent.CuotaLiquidacion;
import com.trascender.habilitaciones.recurso.persistent.DocHabilitanteEspecializado;
import com.trascender.habilitaciones.recurso.persistent.Exencion.Estado;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.PeriodoLiquidacion;
import com.trascender.habilitaciones.recurso.persistent.RegAlicuota;
import com.trascender.habilitaciones.recurso.persistent.TipoModificador;
import com.trascender.habilitaciones.recurso.persistent.TipoObligacion;
import com.trascender.habilitaciones.recurso.persistent.TipoParametro;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroAlicuota;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroVencimiento;
import com.trascender.habilitaciones.recurso.persistent.TipoTasa;
import com.trascender.habilitaciones.recurso.persistent.TipoVencimiento;
import com.trascender.habilitaciones.recurso.persistent.VariableFormula;
import com.trascender.habilitaciones.recurso.persistent.VariableFormulaCompuesta;
import com.trascender.habilitaciones.recurso.persistent.VariableFormulaSimple;
import com.trascender.habilitaciones.recurso.persistent.arrendamiento.DocumentoArrendamiento;
import com.trascender.habilitaciones.recurso.persistent.cementerio.DocumentoCementerio;
import com.trascender.habilitaciones.recurso.persistent.cementerio.ParcelaCementerio;
import com.trascender.habilitaciones.recurso.persistent.cementerio.TipoSepultura;
import com.trascender.habilitaciones.recurso.persistent.osp.DocumentoOSP;
import com.trascender.habilitaciones.recurso.persistent.osp.ServicioOSP;
import com.trascender.habilitaciones.recurso.persistent.pfo.DocumentoPlanObra;
import com.trascender.habilitaciones.recurso.persistent.pfo.Obra;
import com.trascender.habilitaciones.recurso.persistent.shps.DocumentoSHPS;
import com.trascender.habilitaciones.recurso.persistent.tasaMenor.DocumentoTasaMenor;
import com.trascender.habilitaciones.recurso.persistent.tgi.DocumentoTGI;
import com.trascender.habilitaciones.recurso.persistent.tipoParametroGrilla.TipoParametroGrilla;
import com.trascender.habilitaciones.recurso.persistent.transito.DocumentoAutomotor;
import com.trascender.habilitaciones.recurso.persistent.transito.Vehiculo;
import com.trascender.habilitaciones.util.GrillaFunction;
import com.trascender.habilitaciones.util.MotorFormulas;
import com.trascender.saic.business.interfaces.BusinessEstadoCuentaContribuyenteLocal;
import com.trascender.saic.business.interfaces.BusinessExencionRegistroDeudaLocal;
import com.trascender.saic.business.interfaces.BusinessImpresionLocal;
import com.trascender.saic.business.interfaces.BusinessLiquidacionTasaLocal;
import com.trascender.saic.business.interfaces.BusinessReLiquidacionLocal;
import com.trascender.saic.business.interfaces.BusinessRegistroValuadoLocal;
import com.trascender.saic.exception.ResultadoLiquidacion;
import com.trascender.saic.exception.SaicException;
import com.trascender.saic.recurso.filtros.FiltroCobroExterno;
import com.trascender.saic.recurso.filtros.FiltroLiquidacionArrendamiento;
import com.trascender.saic.recurso.filtros.FiltroLiquidacionAutomotor;
import com.trascender.saic.recurso.filtros.FiltroLiquidacionCementerio;
import com.trascender.saic.recurso.filtros.FiltroLiquidacionOSP;
import com.trascender.saic.recurso.filtros.FiltroLiquidacionPFO;
import com.trascender.saic.recurso.filtros.FiltroLiquidacionSHPS;
import com.trascender.saic.recurso.filtros.FiltroLiquidacionTGI;
import com.trascender.saic.recurso.filtros.FiltroLiquidacionTasa;
import com.trascender.saic.recurso.filtros.FiltroLiquidacionTasaMenor;
import com.trascender.saic.recurso.filtros.FiltroLogLiquidacion;
import com.trascender.saic.recurso.persistent.AlicuotaLiquidada;
import com.trascender.saic.recurso.persistent.CobroExterno;
import com.trascender.saic.recurso.persistent.CobroExterno.EntidadRecaudadora;
import com.trascender.saic.recurso.persistent.Condonacion;
import com.trascender.saic.recurso.persistent.EstadoCuentaTemporal;
import com.trascender.saic.recurso.persistent.EstadoCuentaTemporalOSP;
import com.trascender.saic.recurso.persistent.EstadoCuentaTemporalPFO;
import com.trascender.saic.recurso.persistent.EstadoCuentaTemporalSHPS;
import com.trascender.saic.recurso.persistent.EstadoCuentaTemporalTGI;
import com.trascender.saic.recurso.persistent.ExencionRegistroDeuda;
import com.trascender.saic.recurso.persistent.LiquidacionTasa;
import com.trascender.saic.recurso.persistent.LogLiquidacion;
import com.trascender.saic.recurso.persistent.ModificadorLiquidacion;
import com.trascender.saic.recurso.persistent.ModificadorLiquidacionFormula;
import com.trascender.saic.recurso.persistent.ParametroValuado;
import com.trascender.saic.recurso.persistent.ParametroValuadoAlicuota;
import com.trascender.saic.recurso.persistent.ParametroValuadoDouble;
import com.trascender.saic.recurso.persistent.ParametroValuadoString;
import com.trascender.saic.recurso.persistent.RegistroCancelacion;
import com.trascender.saic.recurso.persistent.RegistroCancelacionManual;
import com.trascender.saic.recurso.persistent.RegistroDeuda;
import com.trascender.saic.recurso.persistent.RegistroDeuda.EstadoRegistroDeuda;
import com.trascender.saic.recurso.persistent.RegistroDeuda.TipoDeuda;
import com.trascender.saic.recurso.persistent.RegistroExencionRegistroDeuda;
import com.trascender.saic.recurso.persistent.Reliquidacion;
import com.trascender.saic.recurso.persistent.Tasa;
import com.trascender.saic.recurso.persistent.TasaTGI;
import com.trascender.saic.recurso.persistent.Vencimiento;
import com.trascender.saic.recurso.persistent.refinanciacion.DocumentoRefinanciacion;
import com.trascender.saic.recurso.references.LiquidacionTasaRef;
import com.trascender.saic.recurso.transients.HistorialPagosTasas;
import com.trascender.saic.recurso.transients.LineaHistorialPago;
import com.trascender.saic.recurso.transients.LiquidacionTasaAgrupada;
import com.trascender.saic.recurso.transients.pagoFacil.ArchivoTransmisionPagoFacil;
import com.trascender.saic.recurso.transients.pagoFacil.LoteArchivoPF;
import com.trascender.saic.recurso.transients.pagoFacil.RegistroDetallePF;
import com.trascender.saic.util.ValorBasicoTasaFuncion;
import com.trascender.saic.util.ValorModificadorFuncion;

@Stateless(name = "BusinessLiquidacionTasaLocal")
@TransactionTimeout(86400)
public class BusinessLiquidacionTasaBean implements BusinessLiquidacionTasaLocal {

	private static final long serialVersionUID = 1068157062836730679L;

	@PersistenceContext(name = "Vipians")
	private EntityManager entityManager;

	@EJB
	private BusinessParametroLocal businessParametro;

	private long llave;

	@SuppressWarnings("unused")
	private boolean rollbackOnly = true;

	@SuppressWarnings("unchecked")
	private List listaDiasFeriados;

	private JEP jep = MotorFormulas.initializeJEP();
	private double tiempoInicio;

	private LinkedHashMap<Integer, AlicuotaLiquidada> listaAlicuotasLiquidadas = new LinkedHashMap<Integer, AlicuotaLiquidada>();
	private final LinkedHashMap<Integer, EstadoCuentaTemporalPFO> listaEstadoCuentasPFO = new LinkedHashMap<Integer, EstadoCuentaTemporalPFO>();
	// private List<EstadoCuentaTemporalSHPS> listaEstadoCuentasSHPS;
	// private List<EstadoCuentaTemporalTGI> listaEstadoCuentasTGI;
	// private List<EstadoCuentaTemporalOSP> listaEstadoCuentasOSP;
	private List<EstadoCuentaTemporal> listaEstadoCuentasTemporales;

	private Set<Long> listaIdRegistroDeudaLiquidados = null;
	private Map<Long, Tasa> mapaTasasParaCuota = null;

	@Override
	public List<EstadoCuentaTemporal> getListaEstadoCuentasTemporales() {
		return listaEstadoCuentasTemporales;
	}

	// Map utilizado para agregar o actualizar Exenciones de Registros de Deuda al finalizar un proceso de liquidación.
	private LinkedHashMap<Integer, ExencionRegistroDeuda> listaExencionesRegistrosDeuda = new LinkedHashMap<Integer, ExencionRegistroDeuda>();

	private Boolean ignorarPlan = false;

	public Boolean getIgnorarPlan() {
		return ignorarPlan;
	}

	public void setIgnorarPlan(Boolean ignorarPlan) {
		this.ignorarPlan = ignorarPlan;
	}

	// Threads para controlar la concurrencia de cada liquidación.
	// private Thread threadLiquidacionTGI = new Thread();
	// private boolean isThreadTGIActivo = false;
	//
	// private Thread threadLiquidacionSHPS = new Thread();
	// private boolean isThreadSHPSActivo = false;
	//
	// private Thread threadLiquidacionPFO = new Thread();
	// private boolean isThreadPFOActivo = false;
	//
	// private Thread threadLiquidacionOSP = new Thread();
	// private boolean isThreadOSPActivo = false;
	//
	// private Thread threadLiquidacionTasaMenor = new Thread();
	// private boolean isThreadTasaMenorActivo = false;

	// LinkedHashMap<Integer, Vencimiento> listaVencimientos = new LinkedHashMap<Integer, Vencimiento>();
	// LinkedHashMap<Integer, ModificadorLiquidacion> listaModificadoresLiquidacion = new LinkedHashMap<Integer, ModificadorLiquidacion>();

	// private Thread threadLiquidacionPFO = new Thread();
	// private boolean isThreadPFOActivo = false;

	// private Thread threadLiquidacionOSP = new Thread();
	// private boolean isThreadOSPActivo = false;
	//
	// private Thread threadLiquidacionTasaMenor = new Thread();
	// private boolean isThreadTasaMenorActivo = false;
	//
	// LinkedHashMap<Integer, VencimientoLiquidacion> listaVencimientos = new LinkedHashMap<Integer, VencimientoLiquidacion>();
	// LinkedHashMap<Integer, ModificadorLiquidacion> listaModificadoresLiquidacion = new LinkedHashMap<Integer, ModificadorLiquidacion>();

	static {
		Grupo grupo = new Grupo();
		grupo.setId(serialVersionUID);
		grupo.setNombre("SAI|Adm. de Liquidaciones");

		Recurso locCobroExterno = new Recurso();
		locCobroExterno.setIdRecurso(CobroExterno.serialVersionUID);
		locCobroExterno.setNombre("Cobro Externo");
		locCobroExterno.setAtributosConsultables("Liquidación", "liquidacionTasa");
		locCobroExterno.setClase(CobroExterno.class);
		grupo.getListaRecursos().add(locCobroExterno);

		Recurso locLiquidacionTGI = new Recurso();
		locLiquidacionTGI.setIdRecurso(LiquidacionTasa.codigoTGI);
		locLiquidacionTGI.setNombre("Liquidación TGI");
		locLiquidacionTGI.setAtributosConsultables("Período", "stringPeriodoLiquidado", "Obligación", "stringObligacion", Tipo.TEXTO_LARGO, "Vencimiento", "fechaVencimiento",
				Tipo.FECHA, "Monto", "montoCalculado", Tipo.MONTO, "Tipo de Deuda", "tipoDeuda", "Estado", "estado");
		locLiquidacionTGI.setClase(LiquidacionTasa.class);
		grupo.getListaRecursos().add(locLiquidacionTGI);

		Recurso locLiquidacionSHPS = new Recurso();
		locLiquidacionSHPS.setIdRecurso(LiquidacionTasa.codigoSHPS);
		locLiquidacionSHPS.setNombre("Liquidación SHPS");
		locLiquidacionSHPS.setAtributosConsultables("Período", "stringPeriodoLiquidado", "Obligación", "stringPeriodoLiquidado", Tipo.TEXTO_LARGO, "Vencimiento", "fechaVencimiento",
				Tipo.FECHA, "Monto", "montoCalculado", Tipo.MONTO, "Tipo de Deuda", "tipoDeuda", "Estado", "estado");
		locLiquidacionSHPS.setClase(LiquidacionTasa.class);
		grupo.getListaRecursos().add(locLiquidacionSHPS);

		Recurso locLiquidacionOSP = new Recurso();
		locLiquidacionOSP.setIdRecurso(LiquidacionTasa.codigoOSP);
		locLiquidacionOSP.setNombre("Liquidación OSP");
		locLiquidacionOSP.setAtributosConsultables("Período", "stringPeriodoLiquidado", "Obligación", "stringObligacion", Tipo.TEXTO_LARGO, "Vencimiento", "fechaVencimiento",
				Tipo.FECHA, "Monto", "montoCalculado", Tipo.MONTO, "Tipo de Deuda", "tipoDeuda", "Estado", "estado");
		locLiquidacionOSP.setClase(LiquidacionTasa.class);
		grupo.getListaRecursos().add(locLiquidacionOSP);

		Recurso locLiquidacionPFO = new Recurso();
		locLiquidacionPFO.setIdRecurso(LiquidacionTasa.codigoPFO);
		locLiquidacionPFO.setNombre("Liquidación PFO");
		locLiquidacionPFO.setAtributosConsultables("Período", "stringPeriodoLiquidado", "Obligación", "stringObligacion", Tipo.TEXTO_LARGO, "Cuota Liquidada", "cuotaLiquidada",
				"Vencimiento", "fechaVencimiento", Tipo.FECHA, "Monto", "montoCalculado", Tipo.MONTO, "Tipo de Deuda", "tipoDeuda", "Estado", "estado");
		locLiquidacionPFO.setClase(LiquidacionTasa.class);
		grupo.getListaRecursos().add(locLiquidacionPFO);

		Recurso locLiquidacionCementerio = new Recurso();
		locLiquidacionCementerio.setIdRecurso(LiquidacionTasa.codigoCementerio);
		locLiquidacionCementerio.setNombre("Liquidación Cementerio");
		locLiquidacionCementerio.setAtributosConsultables("Período", "stringPeriodoLiquidado", "Obligación", "stringObligacion", Tipo.TEXTO_LARGO, "Cuota Liquidada", "cuotaLiquidada",
				"Vencimiento", "fechaVencimiento", Tipo.FECHA, "Monto", "montoCalculado", Tipo.MONTO, "Tipo de Deuda", "tipoDeuda", "Estado", "estado");
		locLiquidacionCementerio.setClase(LiquidacionTasa.class);
		grupo.getListaRecursos().add(locLiquidacionCementerio);

		Recurso locLiquidacionAutomotor = new Recurso();
		locLiquidacionAutomotor.setIdRecurso(LiquidacionTasa.codigoAutomotor);
		locLiquidacionAutomotor.setNombre("Liquidación Automotor");
		locLiquidacionAutomotor.setAtributosConsultables("Período", "stringPeriodoLiquidado", "Obligación", "stringObligacion", Tipo.TEXTO_LARGO, "Cuota Liquidada", "cuotaLiquidada",
				"Vencimiento", "fechaVencimiento", Tipo.FECHA, "Monto", "montoCalculado", Tipo.MONTO, "Tipo de Deuda", "tipoDeuda", "Estado", "estado");
		locLiquidacionAutomotor.setClase(LiquidacionTasa.class);
		grupo.getListaRecursos().add(locLiquidacionAutomotor);

		Recurso locLiquidacionTasaMenor = new Recurso();
		locLiquidacionTasaMenor.setIdRecurso(LiquidacionTasa.codigoTasaMenor);
		locLiquidacionTasaMenor.setNombre("Liquidación Tasa Menor");
		locLiquidacionTasaMenor.setAtributosConsultables("Período", "stringPeriodoLiquidado", "Obligación", "stringObligacion", Tipo.TEXTO_LARGO, "Cuota Liquidada", "cuotaLiquidada",
				"Vencimiento", "fechaVencimiento", Tipo.FECHA, "Monto", "montoCalculado", Tipo.MONTO, "Tipo de Deuda", "tipoDeuda", "Estado", "estado");
		locLiquidacionTasaMenor.setClase(LiquidacionTasa.class);
		grupo.getListaRecursos().add(locLiquidacionTasaMenor);
		
		Recurso locLiquidacionArrendamiento = new Recurso();
		locLiquidacionArrendamiento.setIdRecurso(LiquidacionTasa.codigoArrendamiento);
		locLiquidacionArrendamiento.setNombre("Liquidación Arrendamiento");
		locLiquidacionArrendamiento.setAtributosConsultables("Período", "stringPeriodoLiquidado", "Obligación", "stringObligacion", Tipo.TEXTO_LARGO, "Vencimiento", "fechaVencimiento",
				Tipo.FECHA, "Monto", "montoCalculado", Tipo.MONTO, "Tipo de Deuda", "tipoDeuda", "Estado", "estado");
		locLiquidacionArrendamiento.setClase(LiquidacionTasa.class);
		grupo.getListaRecursos().add(locLiquidacionArrendamiento);

		Recurso locRefinanciacion = new Recurso();
		locRefinanciacion.setIdRecurso(DocumentoRefinanciacion.serialVersionUID);
		locRefinanciacion.setNombre("Refinanciación");
		locRefinanciacion.setClase(DocumentoRefinanciacion.class);
		grupo.getListaRecursos().add(locRefinanciacion);

		Recurso locImpresion = new Recurso();
		locImpresion.setNombre("Imprimir Liquidaciones");
		locImpresion.setIdRecurso(LiquidacionTasa.codigoTasasUnificadas);
		locImpresion.setClase(LiquidacionTasa.class);
		grupo.getListaRecursos().add(locImpresion);

		Recurso locLogLiquidaciones = new Recurso();
		locLogLiquidaciones.setNombre("Log Liquidaciones");
		locLogLiquidaciones.setIdRecurso(LogLiquidacion.serialVersionUID);
		locLogLiquidaciones.setAtributosConsultables("Obligacion", "obligacion", Tipo.TEXTO_LARGO, "Usuario", "usuario", "Evento", "evento", "Cuota", "cuota", "Fecha y Hora", "fecha",
				Tipo.FECHA_HORA, "Monto Total", "montoTotalLiquidacion", Tipo.MONTO, "Intereses", "intereses", Tipo.MONTO, "Tipo Deuda", "tipoDeuda", "Comentario", "comentario",
				Tipo.TEXTO_LARGO);
		locLogLiquidaciones.setClase(LogLiquidacion.class);
		grupo.getListaRecursos().add(locLogLiquidaciones);

		SecurityMgr.getInstance().addGrupo(grupo);
	}

	@EJB
	private BusinessMunicipalidadLocal businessMunicipalidadLocal;
	@EJB
	private BusinessRegistroGeograficoLocal businessRegistroGeograficoLocal;
	@EJB
	private BusinessRegistroParcelarioLocal businessRegistroParcelarioLocal;
	@EJB
	private BusinessRegistroValuadoLocal businessRegistroValuadoLocal;
	@EJB
	private BusinessDocumentoOSPLocal businessDocumentoOSP;
	@EJB
	private BusinessEstadoCuentaContribuyenteLocal businessEstadoCuenta;
	@EJB
	private BusinessImpresionLocal businessImpresion;
	@EJB
	private BusinessZonificacionLocal businessZonificacion;
	@EJB
	private BusinessExencionRegistroDeudaLocal businessExencion;
	@EJB
	private BusinessObligacionLocal businessObligacion;
	@EJB
	private BusinessTipoTasaLocal businessTipoTasa;
	@EJB
	private BusinessPeriodoLocal businessPeriodo;
	@EJB
	private BusinessReLiquidacionLocal businessReliquidacion;

	private final Map<String, TipoParametro> tiposParametros = new HashMap<String, TipoParametro>();
	private List<TipoTasa> listaFormulasUtilizadas;
	private List<String> listaNombresParametros;
	private List<String> listaNombresModificadores;
	private List<String> listaNombresVencimientos;

	// private final com.trascender.saic.util.Lock lock = new Lock();

	public BusinessLiquidacionTasaBean() {
	}

	@Override
	public void setLlave(long pLlave) {
		this.llave = pLlave;
	}

	public void ejbActivate() throws EJBException, RemoteException {
	}

	/**
	 * 
	 * @throws CreateException
	 * @ejb.create-method view-type = "local"
	 */
	public void ejbCreate() throws CreateException {
	}

	public void ejbPassivate() throws EJBException, RemoteException {
	}

	public void ejbRemove() throws EJBException, RemoteException {
	}

	public void setSessionContext(SessionContext ctx) throws EJBException, RemoteException {
	}

	/**
	 * Liquida una obligación para un período, utilizando la fecha de liquidación pasada por parámetro
	 * 
	 * @param pObligacion
	 * @param pPeriodo
	 * @param pFechaLiquidacion
	 * @param pNumeroCuota
	 * @throws Exception
	 */
	@Override
	public List<LiquidacionTasa> liquidarObligacion(Obligacion pObligacion, CuotaLiquidacion pCuota, Calendar pFechaLiquidacion, TipoTasa pTipoTasa) throws Exception {
		// pCuota = entityManager.merge(pCuota);
		Integer pNumeroCuota = pCuota.getNumero();
		List<LiquidacionTasa> locListaRetorno = new ArrayList<LiquidacionTasa>();
		boolean isPeriodoLiquidado = this.isPeriodoLiquidado(pObligacion, pCuota);
		if(isPeriodoLiquidado) {
			throw new SaicException(56);
		}
		Tasa locTasa = this.getTasa(pObligacion, pCuota);
		long locTiempoParaLiquidar = System.currentTimeMillis();
		// pObligacion = this.businessObligacion.getObligacionPorId(pObligacion.getIdObligacion());
		// System.out.println("\n\ninicio la liquidación de la tasa = "+pObligacion+" para el período "+pPeriodo);

		DocHabilitanteEspecializado locDocHabilitanteEspecializado = pObligacion.getDocumentoEspecializado();

		// se controla que solo se liquide a una persona activa
		System.out.println("obligacion: " + pObligacion);
		System.out.println("persona: " + pObligacion.getPersona());
		System.out.println("estado: " + pObligacion.getPersona().getEstado());
		if(pObligacion.getPersona().getEstado().equals(Persona.Estado.ACTIVO)) {

			// seteo la cantidad de registros de deuda
			locTasa.setCantidadRegDeuda(locTasa.getCantidadRegDeuda() + 1);

			List<LiquidacionTasa> locListaLiquidacionesTasa = this.getLiquidacionTasaFromTasa(locTasa, pCuota, pNumeroCuota, pFechaLiquidacion, locDocHabilitanteEspecializado,
					pTipoTasa);

			// System.out.println("GENERE: "+ locListaLiquidacionesTasa.size() + " Liquidaciones Tasa.");
			Calendar locFechaInicioPeriodo = null;
			for(LiquidacionTasa locLiquidacionTasa : locListaLiquidacionesTasa) {

				// locLiquidacionTasa.setNumeroRegistroDeuda(((locTasa.getNroUtltimoRegistroDeudaLiquidado()!=null)?
				// locTasa.getNroUtltimoRegistroDeudaLiquidado():0)+1);
				// locTasa.getListaRegistrosDeuda().add(locLiquidacionTasa);

				// calculo los parámetros y los seteo excepto los de los vencimientos
				double tiempoInicial = System.currentTimeMillis();

				 //Para guardar los parametros grilla y calcularlos despues de los TipoParametro regulares.
				 List<TipoParametroGrilla> locListaParametrosGrilla = new ArrayList<TipoParametroGrilla>();

				for(TipoParametro cadaTipoParametro : pTipoTasa.getListaParametros()) {
					
					if (cadaTipoParametro instanceof TipoParametroGrilla) {
						locListaParametrosGrilla.add((TipoParametroGrilla)cadaTipoParametro);
						continue;
					}
					
					TipoParametro locTipoParametro = this.tiposParametros.get(cadaTipoParametro.getNombreVariable());
					
					if(locTipoParametro == null) {
						locTipoParametro = cadaTipoParametro;
					}
					ParametroValuado locParametroValuado = this.getParametroValuadoFromTipoParametro(locDocHabilitanteEspecializado, locTipoParametro, pCuota, pNumeroCuota,
							locLiquidacionTasa.getIdRegistroDeuda());
					locParametroValuado.setLiquidacionTasa(locLiquidacionTasa);

					Variable locVariable = jep.getVar(locParametroValuado.getNombreParametro());
					if(locVariable == null) {
						jep.addVariable(locParametroValuado.getNombreParametro(), locParametroValuado.getValorParametro());
					} else {
						jep.setVarValue(locParametroValuado.getNombreParametro(), locParametroValuado.getValorParametro());
					}
					locLiquidacionTasa.add(locParametroValuado);
				}
				
				//Procesar las grillas
				for (TipoParametroGrilla cadaTPGrilla : locListaParametrosGrilla) {
					GrillaFunction cadaFunction = new GrillaFunction(cadaTPGrilla);
					jep.addFunction(cadaTPGrilla.getNombreVariable(), cadaFunction);
				}

				System.out.println("Tiempo en obtener los parámetros = " + ((System.currentTimeMillis() - tiempoInicial) / 1000));

				// Calculo los valores Variables Formula Simple y los agrego como variables a la formula:
				for(VariableFormulaSimple cadaVariableSimple : pTipoTasa.getListaVariablesSimple()) {
					jep.parseExpression(cadaVariableSimple.getExpresion());
					cadaVariableSimple.setValor(jep.getValue());
					jep.addVariable(cadaVariableSimple.getNombre(), cadaVariableSimple.getValor());
				}
				
				//Luego de calcular las variables, pregunto por la condicion de no liquidacion.
				if (locLiquidacionTasa.getTipoTasa().getCondicionNoLiquidacion() != null
						&& !locLiquidacionTasa.getTipoTasa().getCondicionNoLiquidacion().trim().isEmpty()) {
					jep.parseExpression(locLiquidacionTasa.getTipoTasa().getCondicionNoLiquidacion());
					Double valorCondicionNoLiquidacion = jep.getValue();
					if (!valorCondicionNoLiquidacion.isNaN() 
							&& !valorCondicionNoLiquidacion.equals(0D)) {
						//No guardar esta liquidacion.
						continue;
					}
				}

				// calculo el valor de la tasa sin moficadores de ningún tipo

				tiempoInicial = System.currentTimeMillis();
				jep.parseExpression(locLiquidacionTasa.getTipoTasa().getFormula());

				// Por defecto el valor de la tasa es el valor dado por su fórmula
				locLiquidacionTasa.setValor(new Double(jep.getValue()).doubleValue());
				locLiquidacionTasa.setValor(Util.redondear(locLiquidacionTasa.getValor(), 2));
				System.out.println("Tiempo en parsear= " + ((System.currentTimeMillis() - tiempoInicial) / 1000));
				// Calcular los parametros de las alicuotas, setearlos y calcular los valores.
				if(locLiquidacionTasa.getTipoTasa().getFormulaRegAlicuota() != null && !locDocHabilitanteEspecializado.getListaRegAlicuotas().isEmpty()) {
					// Calculo de Variables Formulas Simples y compuestas:
					List<AlicuotaLiquidada> locListaAlicuotasLiquidadas = new ArrayList<AlicuotaLiquidada>();
					// for (RegAlicuota cadaRegAlicuota : locDocHabilitanteEspecializado.getListaRegAlicuotas()){
					for(AsocRegAlicuota cadaAsocRegAlicuota : locDocHabilitanteEspecializado.getListaAsocRegAlicuota()) {

						AlicuotaLiquidada locAlicuotaLiquidada = new AlicuotaLiquidada();
						locAlicuotaLiquidada.setRegAlicuota(cadaAsocRegAlicuota.getRegistroAlicuota());
						for(TipoParametroAlicuota cadaTipoParametroAlicuota : pTipoTasa.getListaParamatrosAlicuota()) {
							cadaTipoParametroAlicuota.setCuotaLiquidacion(pCuota);
							Object valor = cadaTipoParametroAlicuota.getValor(cadaAsocRegAlicuota);
							ParametroValuadoAlicuota locParametroValuadoAlicuota = ParametroValuadoAlicuota.getInstance(valor);
							locParametroValuadoAlicuota.setNombre(cadaTipoParametroAlicuota.getNombreVariable());
							jep.addVariable(locParametroValuadoAlicuota.getNombre(), locParametroValuadoAlicuota.getValorParametro());
							locAlicuotaLiquidada.addParametroValuado(locParametroValuadoAlicuota);
						}
						// Añado las Alicuotas Liquidadas a una lista auxiliar, para no volver a calcular los parametros nuevamente.
						locListaAlicuotasLiquidadas.add(locAlicuotaLiquidada);
						// Ya seteados todos los parametros, primero calculo las variables simples para que puedan ser utilizadas en las variables compuestas:
						for(VariableFormulaSimple cadaVariableSimple : pTipoTasa.getListaVariablesSimpleAlicuota()) {
							jep.parseExpression(cadaVariableSimple.getExpresion());
							cadaVariableSimple.setValor(jep.getValue());
							jep.addVariable(cadaVariableSimple.getNombre(), cadaVariableSimple.getValor());
						}
						// Ahora voy acumulando los evaluandos en las variables compuestas:
						for(VariableFormulaCompuesta cadaVariableCompuesta : pTipoTasa.getListaVariablesFormulaCompuesta()) {
							jep.parseExpression(cadaVariableCompuesta.getExpresion());
							cadaVariableCompuesta.getListaEvaluandos().add(jep.getValue());
						}
					}
					// Tengo las variables compuestas con sus evaluandos, las añado como variables:
					for(VariableFormulaCompuesta cadaVariableCompuesta : pTipoTasa.getListaVariablesFormulaCompuesta()) {
						jep.addVariable(cadaVariableCompuesta.getNombre(), cadaVariableCompuesta.getValor());
					}

					// Ya tengo las AlicutoasLiquidadas armadas desde la iteracion Inicial. Si la formula asi lo indica,
					// debo iterar sobre las mismas y calcular. Si indica no iterar, se calcula direcamente, probablemente
					// porque el usuario solo use las Variables simples y compuestas.
					if(pTipoTasa.isIteraSobreAlicuotas()) {
						for(AlicuotaLiquidada cadaAlicuotaLiquidada : locListaAlicuotasLiquidadas) {
							// Añado a la formula los parametros ya calculados
							for(ParametroValuadoAlicuota cadaParametroValuado : cadaAlicuotaLiquidada.getListaParametrosValuados()) {
								jep.addVariable(cadaParametroValuado.getNombre(), cadaParametroValuado.getValorParametro());
							}
							// Calculo y añado las variables simples.
							for(VariableFormulaSimple cadaVariableSimple : pTipoTasa.getListaVariablesSimpleAlicuota()) {
								jep.parseExpression(cadaVariableSimple.getExpresion());
								cadaVariableSimple.setValor(jep.getValue());
								jep.addVariable(cadaVariableSimple.getNombre(), cadaVariableSimple.getValor());
							}
							// Calculo el valor final de la Alicuota desde la formula.
							jep.parseExpression(pTipoTasa.getFormulaRegAlicuota());
							double valor = jep.getValue();
							cadaAlicuotaLiquidada.setValor(Util.redondear(valor, 2));
						}
					} else {
						// Como no itera, se suma el valor obtenido al valor inicial.
						jep.parseExpression(pTipoTasa.getFormulaRegAlicuota());
						double valor = locLiquidacionTasa.getValor() + (jep.getValue());
						locLiquidacionTasa.setValor(Util.redondear(valor, 2));
					}
					
					//Teniendo el valor de la Tasa, creo la funcion que puede ser usada por los modificadores.
					ValorBasicoTasaFuncion funcionValorTasa = new ValorBasicoTasaFuncion(locLiquidacionTasa);
					jep.addFunction(ValorBasicoTasaFuncion.VALOR_BASICO_TASA, funcionValorTasa);
					
					// Siempre debo añadir las Alicuotas liquidadas a la liquidacion, tengan valor o no, por si se reliquidan.
					for(AlicuotaLiquidada cadaAlicuotaLiquidada : locListaAlicuotasLiquidadas) {
						// Si tienen valor null, les seteo 0
						if(cadaAlicuotaLiquidada.getValor() == null) {
							cadaAlicuotaLiquidada.setValor(0D);
						}
						AlicuotaLiquidada locAlicuotaLiquidada = cadaAlicuotaLiquidada;
						if(this.listaAlicuotasLiquidadas.containsKey(cadaAlicuotaLiquidada.hashCode())) {
							locAlicuotaLiquidada = this.listaAlicuotasLiquidadas.get(cadaAlicuotaLiquidada.hashCode());
						} else {
							this.listaAlicuotasLiquidadas.put(cadaAlicuotaLiquidada.hashCode(), cadaAlicuotaLiquidada);
						}
						locLiquidacionTasa.addAlicuotaLiquidada(locAlicuotaLiquidada);
					}
				}

				// Armo la lista de modificadores de liquidacion
				// Calculo todo a la fecha de liquidación
				// tiempoInicial = System.currentTimeMillis();

				Set<ModificadorLiquidacion> locListaModificadoresLiquidacion = this.calcularValoresModificadoresLiquidacion(locLiquidacionTasa, pFechaLiquidacion, null);
				for(ModificadorLiquidacion locModificadorLiquidacion : locListaModificadoresLiquidacion) {
					// if(this.listaModificadoresLiquidacion.containsKey(locModificadorLiquidacion.hashCode())){
					// locModificadorLiquidacion = this.listaModificadoresLiquidacion.get(locModificadorLiquidacion.hashCode());
					// }
					// else{
					// this.listaModificadoresLiquidacion.put(locModificadorLiquidacion.hashCode(), locModificadorLiquidacion);
					// }
					locModificadorLiquidacion.setLiquidacionTasa(locLiquidacionTasa);

					locLiquidacionTasa.getListaModificadoresLiquidacion().add(locModificadorLiquidacion);
				}
				
				//Ya teniendo los modificadores, creo la funcion para poder llamar a sus valores
				ValorModificadorFuncion locFuncion = new ValorModificadorFuncion(locLiquidacionTasa);
				jep.addFunction(ValorModificadorFuncion.VALOR_MODIFICADOR, locFuncion);

				System.out.println("Tiempo en obtener modificadores liquidacion= " + ((System.currentTimeMillis() - tiempoInicial) / 1000));

				// Armo la lista de vencimientos
				tiempoInicial = System.currentTimeMillis();
				// if (locFechaInicioPeriodo!=null){
				// if (locTipoTasa.getPeriodicidadCuotas()!=null){
				// Periodicidad locPeriodicidad=locTipoTasa.getPeriodicidadCuotas();
				// locFechaInicioPeriodo.add(Calendar.YEAR,locPeriodicidad.getAños());
				// locFechaInicioPeriodo.add(Calendar.MONTH,locPeriodicidad.getMeses());
				// locFechaInicioPeriodo.add(Calendar.DAY_OF_MONTH, locPeriodicidad.getDias());
				// }
				// }
				// else {
				if(pCuota.getPeriodo().getFechaInicio() != null) {
					locFechaInicioPeriodo = (Calendar) pCuota.getPeriodo().getFechaInicio().clone();
				}
				// }
				
				
				/* Fernando, probando a crear los vencimientos directamente con intereses.
				Vencimiento[] locListaVencimientos = this.calcularVencimientos(locLiquidacionTasa, locFechaInicioPeriodo);
				for(Vencimiento locVencimiento : locListaVencimientos) {
					locLiquidacionTasa.getListaVencimientos().add(locVencimiento);
				}
				System.out.println("Tiempo en obtener vencimientos= " + ((System.currentTimeMillis() - tiempoInicial) / 1000));
				*/
				
				this.businessReliquidacion.setLlave(llave);
				locLiquidacionTasa = this.businessReliquidacion
						.calcularIntereses(locLiquidacionTasa, new Date(), true, false, false);
				
				// if(pNumeroCuota != null){
				// locLiquidacionTasa.setNumeroCuota(pCuota.getNumero());
				// }
				//
				// locLiquidacionTasa.setNumeroCuota(pCuota.getNumero());

				if(locLiquidacionTasa.getValor().isInfinite() || locLiquidacionTasa.getValor().isNaN()) {
					locLiquidacionTasa.setValor(0d);
					throw new SaicException(770);
				} else {
					locListaRetorno.add(locLiquidacionTasa);
				}

				/*
				 * Al Final de la liquidacion se busca si la obligacion posee alguna exencion para el periodo a liquidar de ser asi se liquida solo el
				 * porcentaje especificado si dicho porcentaje es 100 el monto de la liquidacion es 0
				 */
				// if(pObligacion.getListaRegistrosExencion() != null && !pObligacion.getListaRegistrosExencion().isEmpty()){
				/*
				 * levantarExcencionesObligaciones(); if(listaIdObligacionesConExcencion.contains(pObligacion.getIdObligacion()) &&
				 * pObligacion.getListaRegistrosExencion() != null && !pObligacion.getListaRegistrosExencion().isEmpty()) { boolean locIsExentoPeriodo = false;
				 * RegistroExencionObligacion locRegistroExencion = null; Iterator<RegistroExencionObligacion> locIterador =
				 * pObligacion.getListaRegistrosExencion().iterator(); while(locIterador.hasNext() && !locIsExentoPeriodo) { locRegistroExencion =
				 * locIterador.next(); if(locRegistroExencion.getExencionObligacion().getEstado().equals(Estado.VIGENTE)) { // Se controla si el periodo es
				 * igual o si se trata de una exencion anual y el año del periodo es igual al del parametro
				 * if(locRegistroExencion.getExencionObligacion().getCuotaLiquidacion().equals(pCuota) ||
				 * (locRegistroExencion.getExencionObligacion().getTipoExencion().equals(Tipo.ANIO) && locRegistroExencion.getExencionObligacion()
				 * .getCuotaLiquidacion().getPeriodo().getFechaInicio().get(Calendar.YEAR) == pCuota.getPeriodo().getFechaInicio().get(Calendar.YEAR))) {
				 * locIsExentoPeriodo = true; } } } System.out.println("Es periodo exento " + locIsExentoPeriodo);
				 * 
				 * if(locIsExentoPeriodo && locRegistroExencion != null) { ExencionRegistroDeuda locExencionRegistroDeuda = new ExencionRegistroDeuda();
				 * if(locRegistroExencion.getExencionObligacion().getDigestoMunicipal() != null) {
				 * locExencionRegistroDeuda.setDigestoMunicipal(locRegistroExencion.getExencionObligacion().getDigestoMunicipal()); }
				 * locExencionRegistroDeuda.setNombre("Exención creada Automáticamente en Fecha [" + Util.getStringFechaYHora(Calendar.getInstance().getTime())
				 * + "] para la Tasa [" + locLiquidacionTasa.getTipoTasa().getNombre() + "]");
				 * locExencionRegistroDeuda.setMotivo("Exención de Registros de Deuda creada Automáticamente duránte un proceso de Liquidación.");
				 * locExencionRegistroDeuda.setPorcentaje(locRegistroExencion.getExencionObligacion().getPorcentaje());
				 * locExencionRegistroDeuda.setTipoExencion(locRegistroExencion.getExencionObligacion().getTipoExencion());
				 * locExencionRegistroDeuda.setCuotaLiquidacion(locLiquidacionTasa.getCuotaLiquidacion()); //
				 * locExencionRegistroDeuda.setPeriodicidadCuotas(locLiquidacionTasa.getTipoTasa().getPeriodicidadCuotas());
				 * 
				 * try { if(locExencionRegistroDeuda != null && this.listaExencionesRegistrosDeuda.containsKey(locExencionRegistroDeuda.hashCode())) {
				 * locExencionRegistroDeuda = this.listaExencionesRegistrosDeuda.get(locExencionRegistroDeuda.hashCode()); } } catch(Exception e) { }
				 * 
				 * RegistroExencionRegistroDeuda locRegistroExencionRegistroDeuda = new RegistroExencionRegistroDeuda();
				 * locRegistroExencionRegistroDeuda.setExencionRegistroDeuda(locExencionRegistroDeuda);
				 * locRegistroExencionRegistroDeuda.setRegistroDeuda(locLiquidacionTasa); // Coloco la misma nota de referencia que tenía la obligación
				 * locRegistroExencionRegistroDeuda.setReferenciaNotaHCD(locRegistroExencion.getReferenciaNotaHCD());
				 * 
				 * locExencionRegistroDeuda.addRegistroDeudaExento(locRegistroExencionRegistroDeuda);
				 * 
				 * this.listaExencionesRegistrosDeuda.put(locExencionRegistroDeuda.hashCode(), locExencionRegistroDeuda); } }
				 */
				System.out.println("monto de la liquidacion " + locLiquidacionTasa.getValor());
				locLiquidacionTasa.recalcularMonto();
			}

			double locTiempo = System.currentTimeMillis();
			locTiempo -= locTiempoParaLiquidar;
			locTiempo = locTiempo / 1000;
			System.out.println("Finalizo la liquidación = [" + locTiempo + "]\n\n ");
		}
		System.out.println("Se generaron: " + locListaRetorno.size() + " LIQUIDACIONES TASA");
		System.out.println("|*|*|*|*   ------------------------------------------------------------------------------   *|*|*|*|");

		Usuario locUsuario = SecurityMgr.getInstance().getUsuario(this.llave);
		for(LiquidacionTasa cadaLiq : locListaRetorno) {
			generarLogLiquidacion(cadaLiq, locUsuario, LogLiquidacion.Evento.LIQUIDO, null);
		}

		return locListaRetorno;
	}

	private Set<Long> listaIdObligacionesConExcencion = null;

	@SuppressWarnings("unchecked")
	private void levantarExcencionesObligaciones() {
		if(listaIdObligacionesConExcencion == null) {
			listaIdObligacionesConExcencion = new HashSet(Criterio.getInstance(entityManager, Obligacion.class).setProyeccion(Proyeccion.PROP("idObligacion"))
					.add(Restriccion.NOT(Restriccion.ESTA_VACIO("listaRegistrosExencion"))).list());

			System.out.println("----------     Se levantaron " + listaIdObligacionesConExcencion.size() + " obligaciones con excencion.");
		}
	}

	/**
	 * 
	 * @param pObligacion
	 * @param pCuota
	 * @param pTasa
	 * @throws Exception
	 */
	private void validarPreLiquidacion(Obligacion pObligacion, CuotaLiquidacion pCuota) throws Exception {
		CalendarioMunicipal locCalendario = (CalendarioMunicipal) pCuota.getPeriodo().getCalendario();
		System.out.println("\t\t\t**************** Validando PRELIQUIDACION");
		// Verifico que el tipo obligacion de la obligacion coincida con el del calendario al que pertenece la cuota.
		if(!pObligacion.getDocumentoEspecializado().getTipoTasa().getPlan().equals(locCalendario.getPlan())) {
			throw new SaicException(46);
		}

		List<CuotaLiquidacion> locListaCuotasAnteriores = this.getCuotasAnteriores(pCuota);

		// en primera instancia tengo que ver si el/los periodos anteriores ya estan liquidados.
		// List<LiquidacionTasa> locListaLiquidacionesAnteriores = Criterio.getInstance(this.entityManager, LiquidacionTasa.class)
		for(CuotaLiquidacion cadaCuota : locListaCuotasAnteriores) {
			if(!this.isPeriodoLiquidado(pObligacion, cadaCuota)) {
				System.out.println("Al parecer esta queriendo liquidar una cuota adelantada a la que deberia.");
				throw new SaicException(43);
			}
		}

		// busco la primer liquidacion del primer periodo. Si esta pagada voy a saber la forma de pago
		// sino esta queriendo loquidar una tasa que no tiene seleccionada una forma de pago
		List<Long> locListaIds = Criterio.getInstance(this.entityManager, LiquidacionTasa.class).crearAlias("docGeneradorDeuda.obligacion", "locObligacion")
				.add(Restriccion.NOT(Restriccion.NULO("registroCancelacion")))
				// .add(Restriccion.IGUAL("registroCancelacion.periodicidad", pCuota.getPeriodo().getCalendario().getPeriodicidad()))
				.add(Restriccion.IGUAL("cuotaLiquidacion", ((PeriodoLiquidacion) pCuota.getPeriodo().getCalendario().findPeriodo(null, 1)).getCuotaLiquidacion(1)))
				// .add(Restriccion.IGUAL("cuotaLiquidacion.calendario.periodicidad", pCuota.getPeriodo().getCalendario().getPeriodicidad()))
				.add(Restriccion.IGUAL("locObligacion", pObligacion)).add(Orden.DESC("idRegistroDeuda")).setProyeccion(Proyeccion.PROP("idRegistroDeuda")).list();

		// System.out.println("\t\t\t Periodicidad del pago es: " + locLiquidacion.getRegistroCancelacion().getFormaDePago());

		if(locListaIds.isEmpty()) {
			throw new SaicException(44); // no se puede liquidar cuotas mayores a la primera si aun no se ha elegido una forma de pago
		} else {
			Periodicidad locPeriodicidad = Criterio.getInstance(this.entityManager, LiquidacionTasa.class).add(Restriccion.IGUAL("idRegistroDeuda", locListaIds.get(0)))
					.setProyeccion(Proyeccion.PROP("registroCancelacion.formaDePago")).uniqueResult();

			if(!locPeriodicidad.equals(pCuota.getPeriodo().getCalendario().getPeriodicidad())) {
				throw new SaicException(45); // significa que quiere liquidar en una periodicidad distinta a la elegida.
			}
		}
	}

	/**
	 * Recupera las cuotas anteriores de un periodo
	 * 
	 * @param pCuota
	 * @return
	 */
	private List<CuotaLiquidacion> getCuotasAnteriores(CuotaLiquidacion pCuota) {
		List<CuotaLiquidacion> locListaRetorno = new ArrayList<CuotaLiquidacion>();
		if(!this.entityManager.contains(pCuota)) {
			pCuota = this.entityManager.find(CuotaLiquidacion.class, pCuota.getIdCuotaLiquidacion());

			if(pCuota != null) {
				pCuota.toString();
				pCuota.getPeriodo().toString();
				pCuota.getPeriodo().getListaCuotas().toString();
				pCuota.getPeriodo().getCalendario().toString();
				pCuota.getPeriodo().getCalendario().getListaPeriodos();
			}
		}

		// Para saber las cuotas anteriores tengo q recorrer los periodos asociados al calendario.
		for(Periodo cadaPeriodo : pCuota.getPeriodo().getCalendario().getListaPeriodos()) {
			// si el numero del periodo es menor al periodo q pertenece mi cuota agrego todas las cuotas.
			// Sino recorro todas las cuotas y verifico por numero.
			if(cadaPeriodo.getNumero().intValue() == pCuota.getPeriodo().getNumero().intValue()) {
				for(CuotaLiquidacion cadaCuota : pCuota.getPeriodo().getListaCuotas()) {
					if(cadaCuota.getNumero().intValue() < pCuota.getNumero()) {
						locListaRetorno.add(cadaCuota);
					}
				}
				// Ej. puede ser que se este liquidando la cuota 2 del periodo 2.
				// entonces las n cuotas del periodo 1 tambien son cuotas anteriores.
			} else if(cadaPeriodo.getNumero().intValue() > pCuota.getPeriodo().getNumero().intValue()) {
				locListaRetorno.addAll(((PeriodoLiquidacion) cadaPeriodo).getListaCuotas());
			}
		}

		return locListaRetorno;
	}

	/**
	 * Retorna el listado de modificadores de liquidación aplicados
	 * 
	 * @param locLiquidacionTasa
	 * 
	 * @return
	 * @ejb.interface-method view-type = "local"
	 */
	@Override
	public Set<ModificadorLiquidacion> calcularValoresModificadoresLiquidacion(LiquidacionTasa pLiquidacionTasa, Calendar pFechaAplicacion, JEP pJep) {
		if(pJep != null) {
			this.jep = pJep;
		}

		TipoTasa locTipoTasa = pLiquidacionTasa.getTipoTasa();
		Calendar locFechaAplicacionModificadores = (Calendar) pFechaAplicacion.clone();
		Set<ModificadorLiquidacion> retorno = new HashSet<ModificadorLiquidacion>();

		// Separo aquellos que van de la tasa y los que van en el subtotal
		List<TipoModificador> listaModificadoresSobreTasa = new ArrayList<TipoModificador>();
		List<TipoModificador> listaModificadoresSobreSubTotal = new ArrayList<TipoModificador>();

		// //modif tasa eran
		// List<ModificadorLiquidacion> listaModificadoresLiquidacionSobreTasa=new ArrayList<ModificadorLiquidacion>();
		// List<ModificadorLiquidacion> listaModificadoresLiquidacionSobreSubTotal=new ArrayList<ModificadorLiquidacion>();

		// Obtengo la lista de tipos de modificador
		for(TipoModificador locTipoModificador : locTipoTasa.getListaModificadores()) {
			Calendar fechaDesde = this.getFechaDesdeModificador(pLiquidacionTasa.getCuotaLiquidacion().getPeriodo().getFechaInicio(), locTipoModificador);
			Calendar fechaHasta = this.getFechaHastaModificador(pLiquidacionTasa.getCuotaLiquidacion().getPeriodo().getFechaInicio(), locTipoModificador);
			// Verifico que el tipo de modificador sea aplicable según las fechas
			if(((fechaDesde != null && (fechaDesde.before(locFechaAplicacionModificadores) || fechaDesde.equals(locFechaAplicacionModificadores))) && (fechaHasta != null && (fechaHasta
					.after(locFechaAplicacionModificadores) || fechaHasta.equals(locFechaAplicacionModificadores))))
					|| ((locTipoModificador.getHastaDias().equals(0)) && (locTipoModificador.getHastaMeses().equals(0)) || ((locTipoModificador.getHastaDias() == null) && (locTipoModificador
							.getHastaMeses() == null)))) {
				boolean aplicable = true;

				if(aplicable) {
					if(locTipoModificador.isSobreSaldoNeto()) {
						listaModificadoresSobreTasa.add(locTipoModificador);
					} else {
						listaModificadoresSobreSubTotal.add(locTipoModificador);
					}
				}
			}
		}

		/**
		 * Dejo comentado esta parte porque estoy bastante seguro que nunca entra aca, pues la pLiquidacion es una liquidacion nueva y no contiene
		 * modificadores, por lo que el for nunca inicia.
		 */
		// Hago lo mismo con los modificadores para la tasa
		/*
		 * for (ModificadorLiquidacion locModificadorLiquidacion:
		 * pLiquidacionTasa.getListaModificadoresLiquidacion()){//((Tasa)pLiquidacionTasa.getDocGeneradorDeuda()).getListaModificadoresTasa()){ Calendar
		 * fechaDesde=this.getFechaDesde(pLiquidacionTasa.getCuotaLiquidacion().getFechaInicio(),
		 * locModificadorLiquidacion.getTipoModificador().getDesdeDias(),locModificadorLiquidacion.getTipoModificador().getDesdeMeses()); Calendar
		 * fechaHasta=this.getFechaHasta(pLiquidacionTasa.getCuotaLiquidacion().getFechaInicio(),
		 * locModificadorLiquidacion.getTipoModificador().getHastaDias(),locModificadorLiquidacion.getTipoModificador().getHastaMeses()); if ( (
		 * (fechaDesde.before(locFechaAplicacionModificadores)||fechaDesde.equals(locFechaAplicacionModificadores)) &&
		 * (fechaHasta.after(locFechaAplicacionModificadores)||fechaHasta.equals(locFechaAplicacionModificadores)) ) || (
		 * (locModificadorLiquidacion.getTipoModificador().getHastaDias().equals(0)) &&
		 * (locModificadorLiquidacion.getTipoModificador().getHastaMeses().equals(0)) || ((locModificadorLiquidacion.getTipoModificador().getHastaDias() == null
		 * ) && (locModificadorLiquidacion.getTipoModificador().getHastaMeses() == null)) ) ){
		 * 
		 * boolean aplicable=true; //Verifico que el modificador se aplicable según su condicional if
		 * (locModificadorLiquidacion.getTipoModificador().getCondicion()!=null){
		 * jep.parseExpression(locModificadorLiquidacion.getTipoModificador().getCondicion()); aplicable=(jep.getValue()==1); }
		 * 
		 * if (aplicable){ if (locModificadorLiquidacion.getTipoModificador().isSobreSaldoNeto()){
		 * listaModificadoresLiquidacionSobreTasa.add(locModificadorLiquidacion); } else{
		 * listaModificadoresLiquidacionSobreSubTotal.add(locModificadorLiquidacion); } } } }
		 */

		// en acumulador tasa guardo el total de la liquidación más los modificadores
		Double acumuladorTasa = pLiquidacionTasa.getValor();
		// Empiezo a recorrer los modificadores sobre la tasa que son aplicables
		// Primero los del tipo de tasa
		for(TipoModificador locTipoModificador : listaModificadoresSobreTasa) {

			Double valor = calcularValorTipoModificador(locTipoModificador);

			if(valor.equals(0d)) { // Si el valor del modificador es 0 significa q no es aplicable
				continue;
			}

			if(!locTipoModificador.isFijo()) {
				valor = pLiquidacionTasa.getValor() * valor / 100;
			}

			ModificadorLiquidacionFormula locModificadorLiquidacion = crearModificadorLiquidacionPorTipoModificador(locTipoModificador, pLiquidacionTasa, valor);
			retorno.add(locModificadorLiquidacion);

			acumuladorTasa += valor;
		}

		// Recorro los modificadores que se aplican sobre el subtotal
		for(TipoModificador locTipoModificador : listaModificadoresSobreSubTotal) {

			Double valor = calcularValorTipoModificador(locTipoModificador);

			if(valor.equals(0d)) { // Si el valor del modificador es 0 significa q no es aplicable
				continue;
			}
			if(!locTipoModificador.isFijo()) {
				valor = acumuladorTasa * valor / 100;
			}

			ModificadorLiquidacionFormula locModificadorLiquidacion = crearModificadorLiquidacionPorTipoModificador(locTipoModificador, pLiquidacionTasa, valor);
			retorno.add(locModificadorLiquidacion);

			// acumuladorTasa+=valorModificador; Esto generaba problemas cuando se aplicaba mas de un modificador sobre el subtotal
			// sucede debido a que el primero que se aplica lo hace bien, pero a partir del segundo el subtotal cambia y los valores de dichos modificadores
			// cambian tambien
		}
		return retorno;
	}

	private ModificadorLiquidacionFormula crearModificadorLiquidacionPorTipoModificador(TipoModificador pTipoModificador, LiquidacionTasa pLiquidacion, Double pValor) {
		ModificadorLiquidacionFormula locModificadorLiquidacion = new ModificadorLiquidacionFormula();
		locModificadorLiquidacion.setLiquidacionTasa(pLiquidacion);
		locModificadorLiquidacion.setNombre(pTipoModificador.getNombre());
		locModificadorLiquidacion.setTipoModificador(pTipoModificador);
		locModificadorLiquidacion.setValorModificador(Util.redondear(pValor, 2));

		return locModificadorLiquidacion;
	}

	private Double calcularValorTipoModificador(TipoModificador pTipoModificador) {
		// Calcular y agregar variables.
		for(VariableFormula cadaVariable : pTipoModificador.getListaVariables()) {
			jep.parseExpression(cadaVariable.getExpresion());
			Double locValor = jep.getValue();
			jep.addVariable(cadaVariable.getNombre(), locValor);
		}
		jep.parseExpression(pTipoModificador.getCondicion());
		Double valor = jep.getValue();
		valor = valor + 0d; // porque aveces puede devolver -0.0
		return valor;
	}

	/**
	 * Calcula los vencimientos
	 * 
	 * @param locLiquidacionTasa
	 * @param pListaTiposVencimientos
	 * @param pFechaInicioPeriodo
	 *            fecha de incio del período, (es necesaria para las cuotas)
	 * @return
	 */
	@Override
	public Vencimiento[] calcularVencimientos(LiquidacionTasa locLiquidacionTasa, Calendar pFechaInicioPeriodo) throws Exception {
		@SuppressWarnings("unused")
		DateFormat locDateFormat = DateFormat.getDateInstance(DateFormat.SHORT);

		TipoTasa locTipoTasa = locLiquidacionTasa.getTipoTasa();

		TipoVencimiento[] locListaTiposVencimiento = new TipoVencimiento[locTipoTasa.getListaVencimientos().size()];
		locTipoTasa.getListaVencimientos().toArray(locListaTiposVencimiento);

		Vencimiento[] retorno = new Vencimiento[locListaTiposVencimiento.length];
		this.ordenarArrayTiposVencimiento(locListaTiposVencimiento);

		for(int i = 0; i < locListaTiposVencimiento.length; i++) {
			// Verifico si tengo configurado un vencimiento estatico... Sino lo calculo.
			Date locFechaVencimiento = locLiquidacionTasa.getCuotaLiquidacion().getListaVencimientos().get(i).getTime();
			// locFechaVencimiento = this.getFechaVencimiento(pFechaInicioPeriodo, locListaTiposVencimiento[i]);

			Vencimiento locVencimiento = new Vencimiento();

			boolean aplicable = true;

			if(locListaTiposVencimiento[i].getCondicion() != null) {
				jep.parseExpression(locListaTiposVencimiento[i].getCondicion());
				double locResultadoCondicion = jep.getValue();
				if(locResultadoCondicion <= 0) {
					aplicable = false;
				}
			}

			if(aplicable) {
				locVencimiento.setFecha(locFechaVencimiento);
				locVencimiento.setNombre(locListaTiposVencimiento[i].getNombre());
				locVencimiento.setNumero(i);
				retorno[i] = locVencimiento;
				if(i == 0) {
					// Es el primero, que no tiene formula, no suma nada al monto de la liquidacion.
					locVencimiento.setValor(0D);
					this.setParametrosVencimiento(locLiquidacionTasa, locVencimiento);
				} else {
					String formulaVencimiento = locListaTiposVencimiento[i].getFormulaCalculo();
					if(formulaVencimiento == null) {
						formulaVencimiento = "0";
					}
					jep.parseExpression(formulaVencimiento);
					locVencimiento.setValor(jep.getValue());
				}

				// Si el valor del vencimiento es un número negativo
				if(locVencimiento.getValor() < 0) {
					locVencimiento.setValor(0d);
				} else {
					locVencimiento.setValor(Util.redondear(locVencimiento.getValor(), 2));
				}
			}
		}
		return retorno;
	}

	/**
	 * Setea los parámetros de los vencimientos (necesarios a partir del segundo vencimiento)
	 * 
	 * @param pLiquidacionTasa
	 * @param pPrimerVencimiento
	 * @throws Exception
	 */
	private void setParametrosVencimiento(LiquidacionTasa pLiquidacionTasa, Vencimiento pPrimerVencimiento) throws Exception {
		// Ahora tengo los valores de los vencimientos, por lo tanto puedo setear los parámetros correspondientes
		for(TipoParametro locTipoParametro : pLiquidacionTasa.getTipoTasa().getListaParametros()) {
			// fuerzo la recuperación de la clase del tipo de parámetro, porque sinó no me sirve la comparación
			if(Hibernate.getClass(locTipoParametro).equals(TipoParametroVencimiento.class)) {
				Calendar locFechaLiquidacion = Calendar.getInstance();
				locFechaLiquidacion.setTime(pLiquidacionTasa.getFechaEmision());

				Calendar locFechaPrimerVencimiento = Calendar.getInstance();
				locFechaPrimerVencimiento.setTime(pPrimerVencimiento.getFecha());
				// debo obtener la fecha del primer vencimiento directamente del tipo de vencimiento y la liquidacion
				Double locValorParametroValuado = new Double(0);

				switch(((TipoParametroVencimiento) locTipoParametro).getAtributoVencimiento()) {
					case IMPORTE_PRIMER_VENCIMIENTO:
						locValorParametroValuado = pLiquidacionTasa.getValorTotal().doubleValue();
						if(locValorParametroValuado == null)
							locValorParametroValuado = new Double(0);
						break;
					case MESES_DESDE_INICIO_PERIODO:
						if(locFechaLiquidacion.before(pLiquidacionTasa.getCuotaLiquidacion().getPeriodo().getFechaInicio())) {
							locValorParametroValuado = new Double(0);
						} else {
							locValorParametroValuado = new Double(Util.getMesesDiferencia(pLiquidacionTasa.getCuotaLiquidacion().getPeriodo().getFechaInicio(), locFechaLiquidacion));
						}
						break;
					case MESES_DESDE_PRIMER_VENCIMIENTO:
						if(locFechaLiquidacion.before(locFechaPrimerVencimiento)) {
							locValorParametroValuado = new Double(0);
						} else {
							locValorParametroValuado = new Double(Util.getMesesDiferencia(locFechaPrimerVencimiento, locFechaLiquidacion));
						}
						break;
					case DIAS_DESDE_INICIO_PERIODO:
						if(locFechaLiquidacion.before(pLiquidacionTasa.getCuotaLiquidacion().getPeriodo().getFechaInicio())) {
							locValorParametroValuado = new Double(0);
						} else {
							locValorParametroValuado = new Double(Util.getDiasDiferencia(pLiquidacionTasa.getCuotaLiquidacion().getPeriodo().getFechaInicio().getTime(),
									locFechaLiquidacion.getTime()));
						}
						break;
					case DIAS_DESDE_PRIMER_VENCIMIENTO:
						if(locFechaPrimerVencimiento.before(pLiquidacionTasa.getCuotaLiquidacion().getPeriodo().getFechaInicio())) {
							locValorParametroValuado = new Double(0);
						} else {
							locValorParametroValuado = new Double(Util.getDiasDiferencia(pLiquidacionTasa.getCuotaLiquidacion().getPeriodo().getFechaInicio().getTime(),
									locFechaPrimerVencimiento.getTime()));
						}
						break;
				}

				jep.addVariable(locTipoParametro.getNombreVariable(), locValorParametroValuado);

				ParametroValuadoDouble locParametro = new ParametroValuadoDouble();
				locParametro.setNombreParametro(locTipoParametro.getNombreVariable());
				locParametro.setValorParametro(locValorParametroValuado);
				locParametro.setLiquidacionTasa(pLiquidacionTasa);

				// if(this.listaParametrosValuados.containsKey(locParametro.hashCode())){
				// locParametro = (ParametroValuadoDouble) this.listaParametrosValuados.get(locParametro.hashCode());
				// }
				// else{
				// this.listaParametrosValuados.put(locParametro.hashCode(), locParametro);
				// }
				pLiquidacionTasa.getListaParametrosValuados().add(locParametro);
			}
		}
	}

	/**
	 * Verifica si el día pasado por parámetro es feriado
	 * 
	 * @param pFechaVencimiento
	 * @return
	 */
	private boolean isFeriado(Calendar pFechaVencimiento) throws Exception {
		boolean esFeriado = false;
		if(this.listaDiasFeriados == null) {
			this.listaDiasFeriados = this.businessMunicipalidadLocal.findListadoDiasFeriados(new FiltroDiaFeriado(null, pFechaVencimiento.get(Calendar.YEAR))).getListaResultados();
		}

		if(pFechaVencimiento.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
			esFeriado = true;
		} else if(pFechaVencimiento.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			esFeriado = true;
		}
		if(!esFeriado) {
			// La lista de días feriados viene ordenada de manera ascendente por fecha
			Iterator iterador = this.listaDiasFeriados.iterator();
			while((iterador.hasNext()) && (!esFeriado)) {
				DiaFeriado locDiaFeriado = (DiaFeriado) iterador.next();
				if(pFechaVencimiento.getTime().equals(locDiaFeriado.getFecha())) {
					esFeriado = true;
				}
			}
		}
		return esFeriado;
	}

	/**
	 * Ordena la lista de tipos de vencimientos por fecha
	 * 
	 * @param pListaTiposVencimientos
	 *            lista a ordenar
	 */
	private void ordenarArrayTiposVencimiento(TipoVencimiento[] pListaTiposVencimientos) {
		java.util.Arrays.sort(pListaTiposVencimientos, new Comparator<TipoVencimiento>() {
			@Override
			public int compare(TipoVencimiento o1, TipoVencimiento o2) {
				int valor1 = o1.getMeses() * 100 + o1.getDias();
				int valor2 = o2.getMeses() * 100 + o2.getDias();
				int resultado = valor1 - valor2;
				if(resultado != 0) {
					return resultado;
				} else {
					if(o1.getFormulaCalculo() == null) {
						return -1;
					} else {
						return 1;
					}
				}
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public void ejecutarProcedimientoActualizacionDeuda(Persona pPersona, Parcela pParcela) throws Exception {
		try {
			Query query;
			if(pParcela != null) {
				System.out.println("Entro a la parcela");
				query = this.entityManager.createNativeQuery("SELECT * FROM P_ESTADO_CUENTA_PARCELA(:param1)").setParameter("param1", pParcela.getIdParcela());
			} else if(pPersona != null) {
				System.out.println("Entro a la persona");
				query = this.entityManager.createNativeQuery("SELECT * FROM P_ESTADO_CUENTA_PERSONA(:param1)").setParameter("param1", pPersona.getIdPersona());
			} else {
				System.out.println("Entro al else");
				query = this.entityManager.createNativeQuery("SELECT * FROM P_ESTADO_CUENTA()");
			}
			System.out.println("Ejecutando procedure...");
			List<Object[]> lista = query.getResultList();

			System.out.println("Armando lista de estado cuenta temporal...");
			this.listaEstadoCuentasTemporales = armarListaEstadoCuentaTemporal(lista);
			System.out.println("Fin ejecución procedimiento actualización estado de cuentas. Preparando listado...");
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	// /**
	// * Ejectua el procedimiento almacenado de actualización de deudas TGI
	// * @param pParcela
	// * @param pPersona
	// *
	// */
	// @Override
	// public void ejecutarProcedimientoActualizacionDeudaTGI(Persona pPersona, Parcela pParcela) throws Exception{
	// try{
	// // lock.lock();
	// if(pParcela != null){
	// System.out.println("ENtro al parcela");
	//
	// @SuppressWarnings("rawtypes")
	// List lista = this.entityManager.createNativeQuery("SELECT * FROM P_ESTADO_CUENTA_PARCELA(:param1)")
	// .setParameter("param1", pParcela.getIdParcela())
	// .getResultList();
	// System.out.println(lista);
	//
	// } else if(pPersona != null){
	// System.out.println("ENtro al persona");
	// List retorno = this.entityManager.createNativeQuery("SELECT * FROM P_ESTADO_CUENTA_PERSONA(:param1)")
	// .setParameter("param1", pPersona.getIdPersona()).getResultList();
	// System.out.println("EL PROCEDURE DEVOLVIO ALGO: " + retorno);
	// }
	// else{
	// System.out.println("ENtro al else");
	// this.entityManager.createNativeQuery("SELECT * FROM P_ESTADO_CUENTA_TGI()")
	// .getResultList();
	// }
	//
	// System.out.println("Fin ejecución procedimiento actualización estado de cuentas. Preparando listado...");
	// List<EstadoCuentaTemporalTGI> locListaEstadoCuentas = this.businessEstadoCuenta.getListaEstadoCuentasTemporalTGI();
	//
	// listaEstadoCuentasTGI = new ArrayList<EstadoCuentaTemporalTGI>();
	// listaEstadoCuentasTGI.addAll(locListaEstadoCuentas);
	// }
	// catch(SQLException e){
	// e.printStackTrace();
	// throw e;
	// }finally{
	// // lock.unlock();
	// }
	//
	// }

	// /**
	// * Ejectua el procedimiento almacenado de actualización de deudas SHPS
	// * @param pParcela
	// * @param pPersona
	// *
	// */
	// @Override
	// public void ejecutarProcedimientoActualizacionDeudaSHPS(Persona pPersona, Parcela pParcela) throws Exception{
	// try{
	// // lock.lock();
	// if(pPersona != null){
	// this.entityManager.createNativeQuery("SELECT * FROM P_ESTADO_CUENTA_PERSONA_SHPS(:param1)")
	// .setParameter("param1", pPersona.getIdPersona())
	// .getResultList();
	// }
	// else if(pParcela != null){
	// this.entityManager.createNativeQuery("SELECT * FROMP_ESTADO_CUENTA_PARCELA_SHPS(:param1)")
	// .setParameter(1, pParcela.getIdParcela()).getResultList();
	// }
	// else{
	// this.entityManager.createNativeQuery("SELECT * FROM P_ESTADO_CUENTA_SHPS()").getResultList();
	// }
	//
	// System.out.println("Fin ejecución procedimiento actualización estado de cuentas. Preparando listado...");
	// List<EstadoCuentaTemporalSHPS> locListaEstadoCuentas = this.businessEstadoCuenta.getListaEstadoCuentasTemporalSHPS();
	//
	// listaEstadoCuentasSHPS = new ArrayList<EstadoCuentaTemporalSHPS>();
	// listaEstadoCuentasSHPS.addAll(locListaEstadoCuentas);
	// }
	// catch(SQLException e){
	// e.printStackTrace();
	// throw e;
	// }finally{
	// // lock.unlock();
	// }
	// }

	// /**
	// * Ejectua el procedimiento almacenado de actualización de deudas PFO
	// * @param pParcela
	// * @param pPersona
	// *
	// */
	// @Override
	// public void ejecutarProcedimientoActualizacionDeudaPFO(Persona pPersona, Parcela pParcela) throws Exception{
	//
	// try{
	// // lock.lock();
	// if(pPersona != null){
	// this.entityManager.createNativeQuery("SELECT * FROMP_ESTADO_CUENTA_PERSONA_PFO(:param1,:param2)")
	// .setParameter("param1", pPersona.getIdPersona())
	// .setParameter("param2", (pPersona instanceof PersonaFisica)?1:2)
	// .getResultList();
	// }
	// else if(pParcela != null){
	// this.entityManager.createNativeQuery("SELECT * FROM P_ESTADO_CUENTA_PARCELA_PFO(:param1)")
	// .setParameter("param1", pParcela.getIdParcela())
	// .getResultList();
	//
	// }
	// else{
	// this.entityManager.createNativeQuery("SELECT * FROM P_ESTADO_CUENTA_PFO()")
	// .getResultList();
	// }
	//
	// System.out.println("Fin ejecución procedimiento actualización estado de cuentas. Preparando listado...");
	// List<EstadoCuentaTemporalPFO> locListaEstadoCuentas = new ArrayList<EstadoCuentaTemporalPFO>();
	// locListaEstadoCuentas = this.businessEstadoCuenta.getListaEstadoCuentasTemporalPFO();
	//
	// listaEstadoCuentasPFO = new LinkedHashMap<Integer, EstadoCuentaTemporalPFO>();
	// if(!locListaEstadoCuentas.isEmpty()){
	// for(EstadoCuentaTemporalPFO cadaEstadoCuentaTemporal : locListaEstadoCuentas){
	// if(!listaEstadoCuentasPFO.containsKey(cadaEstadoCuentaTemporal.hashCode())){
	// // cadaEstadoCuentaTemporal.getParcela().toString();
	// // cadaEstadoCuentaTemporal.getPersona().toString();
	// listaEstadoCuentasPFO.put(cadaEstadoCuentaTemporal.hashCode(), cadaEstadoCuentaTemporal);
	// }
	// }
	// }
	// }
	// catch (SQLException e) {
	// e.printStackTrace();
	// throw e;
	// } catch (Exception e) {
	// e.printStackTrace();
	// throw e;
	// } finally {
	// // lock.unlock();
	// }
	//
	// }

	private List<EstadoCuentaTemporal> armarListaEstadoCuentaTemporal(List<Object[]> pLista) {
		List<EstadoCuentaTemporal> locListaEstadoCuentaTemporal = new ArrayList<EstadoCuentaTemporal>();
		for(Object[] cadaLinea : pLista) {
			EstadoCuentaTemporal cadaEstado = new EstadoCuentaTemporal();
			cadaEstado.setIdPersona(((BigDecimal) cadaLinea[0]).longValue());
			Object bdParcela = cadaLinea[1];
			if(bdParcela != null)
				cadaEstado.setIdParcela(((BigDecimal) bdParcela).longValue());
			cadaEstado.setTipoObligacion((String) cadaLinea[2]);
			cadaEstado.setIdRegistroDeuda(((BigDecimal) cadaLinea[3]).longValue());
			locListaEstadoCuentaTemporal.add(cadaEstado);
		}
		return locListaEstadoCuentaTemporal;
	}

	/**
	 * Ejectua el procedimiento almacenado de actualización de deudas OSP
	 * 
	 * @param pParcela
	 * @param pPersona
	 * 
	 */
	// @Override
	// public void ejecutarProcedimientoActualizacionDeudaOSP(Persona pPersona, Parcela pParcela) throws Exception{
	// try{
	// // lock.lock();
	// if(pParcela != null){
	// System.out.println("llamo parcela not null");
	// List<Object[]> locResult = this.entityManager.createNativeQuery("SELECT * FROM P_ESTADO_CUENTA_PARCELA(:param1)")
	// .setParameter("param1", pParcela.getIdParcela())
	// .getResultList();
	// listaEstadoCuentasTemporales = this.armarListaEstadoCuentaTemporal(locResult);
	//
	// } else if(pPersona != null){
	// System.out.println("LLamo persona not null");
	// List<Object[]> locResult = this.entityManager.createNativeQuery("SELECT * FROM P_ESTADO_CUENTA_PERSONA(:per)")
	// .setParameter("per", pPersona.getIdPersona())
	// .getResultList();
	// listaEstadoCuentasTemporales = this.armarListaEstadoCuentaTemporal(locResult);
	// }
	// else{
	// System.out.println("llamo else");
	// List<Object[]> locResult = this.entityManager.createNativeQuery("SELECT * FROM P_ESTADO_CUENTA_OSP()")
	// .getResultList();
	// listaEstadoCuentasTemporales = armarListaEstadoCuentaTemporal(locResult);
	// }
	//
	// System.out.println("Fin ejecución procedimiento actualización estado de cuentas. Preparando listado...");
	// // List<EstadoCuentaTemporalOSP> locListaEstadoCuentas = this.businessEstadoCuenta.getListaEstadoCuentasTemporalOSP();
	//
	//
	// // listaEstadoCuentasOSP.addAll(locListaEstadoCuentas);
	// }catch (Exception e) {
	// e.printStackTrace();
	// throw e;
	// } finally {
	// // lock.unlock();
	// }
	// }

	/**
	 * Calcula la fecha hasta a partir del inicio del período junto con los dias y meses
	 * 
	 * @param pFechaInicioPeriodo
	 * @param pDiasHasta
	 * @param pMesesHasta
	 * @return
	 */
	private Calendar getFechaHasta(final Calendar pFechaInicioPeriodo, Integer pDiasHasta, Integer pMesesHasta) {
		Calendar locCalendar = (Calendar) pFechaInicioPeriodo.clone();
		locCalendar.add(Calendar.MONTH, pMesesHasta);
		locCalendar.add(Calendar.DAY_OF_MONTH, pDiasHasta);
		return locCalendar;
	}

	private Calendar getFechaDesde(final Calendar pFechaInicioPeriodo, Integer pDiasDesde, Integer pMesesDesde) {
		Calendar locFechaLiquidacion = (Calendar) pFechaInicioPeriodo.clone();
		locFechaLiquidacion.add(Calendar.MONTH, pMesesDesde);
		locFechaLiquidacion.add(Calendar.DAY_OF_MONTH, pDiasDesde);
		return locFechaLiquidacion;
	}

	/**
	 * Calcula el valor de la fecha hasta la cual se aplica el modificador, partiendo del día del inicio del período
	 * 
	 * @param pFechaInicioPeriodo
	 * @param locModificador
	 * @return
	 */
	private Calendar getFechaHastaModificador(final Calendar pFechaInicioPeriodo, TipoModificador locModificador) {
		if(pFechaInicioPeriodo == null)
			return null;
		return this.getFechaHasta(pFechaInicioPeriodo, locModificador.getHastaDias(), locModificador.getHastaMeses());
	}

	/**
	 * Calcula el valor de la fecha desde la cual se aplica el modificador, partiendo del día del inicio del período
	 * 
	 * @param pFechaInicioPeriodo
	 * @param locModificador
	 * @return
	 */
	private Calendar getFechaDesdeModificador(final Calendar pFechaInicioPeriodo, TipoModificador locModificador) {
		if(pFechaInicioPeriodo == null)
			return null;
		return this.getFechaDesde(pFechaInicioPeriodo, locModificador.getDesdeDias(), locModificador.getDesdeMeses());
	}

	/**
	 * Retorna la fecha del venciento a partir de una fecha y el tipo de vencimiento
	 * 
	 * @param pFechaDesde
	 *            fecha desde la cual contar los meses y dias del vencimiento
	 * @param pTipoVencimiento
	 *            tipo de vencimiento
	 */
	@Override
	@SuppressWarnings("deprecation")
	public Calendar getFechaVencimiento(final Calendar pFechaDesde, TipoVencimiento pTipoVencimiento) throws Exception {
		// FIXME ver aca como poner la fecha de vencimiento desde el periodo
		Calendar fecha = (Calendar) pFechaDesde.clone();
		fecha.add(Calendar.MONTH, pTipoVencimiento.getMeses());
		fecha.add(Calendar.DAY_OF_MONTH, pTipoVencimiento.getDias());

		while(this.isFeriado(fecha) || fecha.getTime().getDay() == 0 || fecha.getTime().getDay() == 6) {
			fecha.add(Calendar.DAY_OF_MONTH, 1);
		}
		return fecha;
	}

	private TipoObligacion getTipoObligacionPorNombre(String pNombre) {
		return Criterio.getInstance(entityManager, TipoObligacion.class).add(Restriccion.IGUAL("nombre", pNombre)).uniqueResult();
	}

	/**
	 * Crea un tipo de parámetro valuado a partir del tipo de parámetro. pero debe haberse ejecutado primero el setTipoParametro
	 * 
	 * @param pTipoParametro
	 * @param pNumeroCuota
	 * @param pIdRegistroDeuda
	 * @return
	 * @throws Exception
	 */
	@Override
	public ParametroValuado getParametroValuadoFromTipoParametro(DocHabilitanteEspecializado pDocumentoHabilitanteEspecializado, TipoParametro pTipoParametro, CuotaLiquidacion pCuota,
			Integer pNumeroCuota, long pIdRegistroDeuda) throws Exception {
		// pTipoParametro.setPeriodoLiquidacion(pCuota.getPeriodo());
		pTipoParametro.setCuotaLiquidacion(pCuota);
		Object locValor = 0D;

		EstadoCuentaTemporal locEstadoCuentaTemporal = null;

		if(pTipoParametro.getNombreVariable().equals("BUEN_CONTRIBUYENTE")) {
			// se utiliza el idRegistroDeuda para evitar que al tratarse de recuperar las deudas no recupere esta precisamente,
			// este caso se da cuando queres reliquidar una deuda vencida, si no pedimos que no recupere esta la va a tomar como si fuera deuda
			// y no va a permitir calcular el descuento buen contribuyente

			// Para saber si es buen contribuyente solo me importa que la persona no este dentro de la tabla Estado_Cuenta_Temporal
			if(pDocumentoHabilitanteEspecializado instanceof DocumentoTGI) {
				locEstadoCuentaTemporal = new EstadoCuentaTemporalTGI();
			} else if(pDocumentoHabilitanteEspecializado instanceof DocumentoOSP) {
				locEstadoCuentaTemporal = new EstadoCuentaTemporalOSP();
			} else if(pDocumentoHabilitanteEspecializado instanceof DocumentoSHPS) {
				locEstadoCuentaTemporal = new EstadoCuentaTemporalSHPS();
			} else if(pDocumentoHabilitanteEspecializado instanceof DocumentoPlanObra) {
				locEstadoCuentaTemporal = new EstadoCuentaTemporalPFO();
			}
			locEstadoCuentaTemporal.setIdPersona(pDocumentoHabilitanteEspecializado.getObligacion().getPersona().getIdPersona());
			locEstadoCuentaTemporal.setTipoObligacion(null);
			locEstadoCuentaTemporal.setIdRegistroDeuda(pIdRegistroDeuda);
			// Por defecto se pone el valor en uno como si fuera buen contribuyente
			locValor = 1D;
		}

		if(pTipoParametro.getNombreVariable().equals("ES_ALGUN_PROPIETARIO_MOROSO_TGI")) {
			// Para tgi al poner solo la parcela puedo obtener si hay algun propietario que sea moroso
			locEstadoCuentaTemporal = new EstadoCuentaTemporalTGI();
			locEstadoCuentaTemporal.setIdParcela(pDocumentoHabilitanteEspecializado.getParcela().getIdParcela());
			locEstadoCuentaTemporal.setIdPersona(null);
			locEstadoCuentaTemporal.setTipoObligacion("TGI");
		} else if(pTipoParametro.getNombreVariable().equals("ES_ALGUN_PROPIETARIO_MOROSO_OSP")) {
			locEstadoCuentaTemporal = new EstadoCuentaTemporalOSP();
			locEstadoCuentaTemporal.setIdParcela(pDocumentoHabilitanteEspecializado.getParcela().getIdParcela());
			locEstadoCuentaTemporal.setIdPersona(null);
			locEstadoCuentaTemporal.setTipoObligacion("OSP");
		} else if(pTipoParametro.getNombreVariable().equals("ES_ALGUN_PROPIETARIO_MOROSO_PFO")) {
			locEstadoCuentaTemporal = new EstadoCuentaTemporalPFO();
			locEstadoCuentaTemporal.setIdParcela(pDocumentoHabilitanteEspecializado.getParcela().getIdParcela());
			locEstadoCuentaTemporal.setIdPersona(null);
			locEstadoCuentaTemporal.setTipoObligacion("PO");
		} else if(pTipoParametro.getNombreVariable().equals("ES_MOROSO_SHPS")) {
			locEstadoCuentaTemporal = new EstadoCuentaTemporalSHPS();
			locEstadoCuentaTemporal.setIdPersona(pDocumentoHabilitanteEspecializado.getObligacion().getPersona().getIdPersona());
			locEstadoCuentaTemporal.setTipoObligacion("SHPS");
		} else if(pTipoParametro.getNombreVariable().equals("ES_MOROSO_OSP")) {
			locEstadoCuentaTemporal = new EstadoCuentaTemporalOSP();
			locEstadoCuentaTemporal.setIdPersona(pDocumentoHabilitanteEspecializado.getObligacion().getPersona().getIdPersona());
			locEstadoCuentaTemporal.setTipoObligacion("OSP");
		}

		// 1 (verdadero) significa q no posee deuda, por tanto es buen contribuyente, 0 (false) que posee deuda
		if(locEstadoCuentaTemporal != null) {
			locEstadoCuentaTemporal.setIdRegistroDeuda(pIdRegistroDeuda);
			if(locEstadoCuentaTemporal instanceof EstadoCuentaTemporalTGI) {
				if(this.listaEstadoCuentasTemporales.contains(locEstadoCuentaTemporal)) {
					locValor = 1D;
				}
			} else if(locEstadoCuentaTemporal instanceof EstadoCuentaTemporalSHPS) {
				if(this.listaEstadoCuentasTemporales.contains(locEstadoCuentaTemporal)) {
					locValor = 1D;
				}
			} else if(locEstadoCuentaTemporal instanceof EstadoCuentaTemporalPFO) {
				if(this.listaEstadoCuentasPFO.containsKey(locEstadoCuentaTemporal.hashCode())) {
					locValor = 1D;
				}
			} else if(locEstadoCuentaTemporal instanceof EstadoCuentaTemporalOSP) {
				if(this.listaEstadoCuentasTemporales.contains(locEstadoCuentaTemporal)) {
					locValor = 1D;
				}
			}
		}

		try {
			if(locValor.equals(0D) && !pTipoParametro.getNombreVariable().equals("BUEN_CONTRIBUYENTE")) {
				locValor = pTipoParametro.getValor(pDocumentoHabilitanteEspecializado);
			}
		} catch(Exception e) {
			e.printStackTrace();
			locValor = 0d;
			// esto se agrego por el requerimiento de multa formal de shps, queda desactivado hasta proxima entrega
			// if(!(pDocumentoHabilitanteEspecializado instanceof DocumentoSHPS && pTipoParametro.getNombreVariable().contains("IMPORTE_DECLARADO"))){
			// throw new ValorParametroException(pDocumentoHabilitanteEspecializado.getObligacion(),pTipoParametro);
			// }
		}

		if(locValor == null)
			locValor = 0D;
		// Analizo el valor para saber si es String o Double, para instanciar el ParametroValuado correcto.
		if(locValor instanceof Double) {
			ParametroValuadoDouble locParametroValuado = new ParametroValuadoDouble();
			locParametroValuado.setNombreParametro(pTipoParametro.getNombreVariable());
			locParametroValuado.setValorParametro((Double) locValor);
			return locParametroValuado;
		} else {
			ParametroValuadoString locParametroValuado = new ParametroValuadoString();
			locParametroValuado.setNombreParametro(pTipoParametro.getNombreVariable());
			locParametroValuado.setValorParametro((String) locValor);
			return locParametroValuado;
		}
	}

	/**
	 * Retorna una Tasa para el año corriente, para una obligacion del anio dado por la cuota. Valida que no liquide para planes no correctos.
	 * 
	 * @param pObligacion
	 * @param pCuota
	 * @return
	 * @throws Exception
	 */
	private Tasa getTasa(Obligacion pObligacion, CuotaLiquidacion pCuota) throws Exception {
		Tasa locTasa = mapaTasasParaCuota.get(pObligacion.getIdObligacion());

		if(locTasa == null) {
			locTasa = new Tasa();
			locTasa.setObligacion(pObligacion);
			locTasa.setAnio(pCuota.getPeriodo().getCalendario().getAnio());
			locTasa.setCantidadRegDeuda(0);
		} else {
			if(!this.getIgnorarPlan()) {
				// Validamos que no este liquidando algo fuera del Plan elegido.
				// Si ya hay un plan elegido, la comparacion es sencilla.
				CalendarioMunicipal locCalendario = (CalendarioMunicipal) pCuota.getPeriodo().getCalendario();
				if(locTasa.getPlanElegido() != null && !locTasa.getPlanElegido().equals(locCalendario.getPlan())) {
					throw new SaicException(53);
				} else {
					// Si no hay plan elegido y esta liquidando un Periodo o Cuota superior al primero, validamos que sea solo del Plan por defecto.
					if(locTasa.getPlanElegido() == null && (pCuota.getNumero() > 1 || pCuota.getPeriodo().getNumero() > 1) && !locCalendario.getPlan().isPorDefecto()) {
						throw new SaicException(53);
					}
				}
			} else {
				// A la tasa debemos ponerle plan elegido en null y dejarlo liquidar
				locTasa.setPlanElegido(null);
				entityManager.merge(locTasa);
			}
		}
		return locTasa;
	}

	private void levantarTasasParaCuota(CuotaLiquidacion pCuota, List<? extends DocHabilitanteEspecializado> listaDocumentos) {
		Criterio locCriterio = Criterio.getInstance(entityManager, Tasa.class)
				.add(Restriccion.IGUAL("anio", pCuota.getPeriodo().getCalendario().getAnio()))
				.crearAlias("obligacion", "locObligacion")
				.setProyeccion(Proyeccion.PROP("locObligacion.idObligacion", "e"));
		
		//Si esta liquidando solo un documento, levantamos las tasas para ese unicamente.
		if (listaDocumentos.size() == 1) {
			Obligacion locObligacion = listaDocumentos.get(0).getObligacion();
			locCriterio.add(Restriccion.IGUAL("locObligacion", locObligacion));
		}
		
		List<Object[]> listaResultados = locCriterio.list();
		mapaTasasParaCuota = new HashMap<Long, Tasa>();
		for(Object[] cadaObjeto : listaResultados) {
			mapaTasasParaCuota.put((Long) cadaObjeto[0], (Tasa) cadaObjeto[1]);
		}
		System.out.println("----------     Se levantaron " + listaResultados.size() + " tasas.");
	}

	// /**
	// * Obtiene la tasa de una obligación, en caso que no exista, genera el tipo de tasa
	// * @param pObligacion obligación de la cual obtener el tipo de tasa
	// * @return
	// * @throws Exception
	// */
	// private Tasa getTasa(Obligacion pObligacion,CuotaLiquidacion pCuota,TipoTasa pTipoTasa) throws Exception {
	// DocHabilitanteEspecializado locDocHabilitanteEspecializado = pObligacion.getDocumentoEspecializado();
	// TipoTasa locTipoTasa = locDocHabilitanteEspecializado.getTipoTasa();
	// try{
	// Tasa locTasaActual = (Tasa)Criterio.getInstance(this.entityManager, Tasa.class)
	// .add(Restriccion.IGUAL("obligacion",pObligacion))
	// .add(Restriccion.NULO("fechaHasta"))
	// .setDistinct(true)
	// .uniqueResult();
	//
	// if (locTasaActual != null){
	// locTasaActual.toString();
	// if (!locTasaActual.getTipoTasa().equals(pTipoTasa)){
	// locTasaActual.setFechaHasta(Calendar.getInstance().getTime());
	// Tasa locTasaNueva = this.generarTasaNueva(pTipoTasa);
	// locTasaNueva.setDocGeneradorDeudaAnterior(locTasaActual);
	// locTasaNueva.setObligacion(pObligacion);
	// locTasaNueva.setUltimoPeriodo(pCuota);
	//
	// //Tengo que comparar a pata algunas cosas evitar exceptions de hibernate
	// CuotaLiquidacion locCuota= locTasaActual.getUltimoPeriodo();
	//
	// if (locCuota.getPeriodo().getIdPeriodo() != pCuota.getPeriodo().getIdPeriodo()){
	// //si la session no contiene a locPeriodo entonces lo asocio.
	// // if(!this.entityManager.contains(locCuota)){
	// // locCuota = (CuotaLiquidacion)this.entityManager.find(Periodo.class, locCuota.getIdPeriodo());
	// // }
	//
	// if (locTasaNueva.getUltimoPeriodo()==null){
	// locTasaNueva.setUltimoPeriodo(pCuota);
	// }
	// else if (pCuota.compareTo(locTasaActual.getUltimoPeriodo()) > 0){
	// locTasaNueva.setUltimoPeriodo(pCuota);
	// }
	// }
	//
	// // this.entityManager.detach(pCuota);
	// locTasaNueva = this.entityManager.merge(locTasaNueva);
	// return locTasaNueva;
	// }
	// else{
	// locTasaActual.setUltimoPeriodo(pCuota);
	// }
	// }
	// else{
	// locTasaActual = this.generarTasaNueva(pTipoTasa);
	// locTasaActual.setObligacion(pObligacion);
	// if (locTipoTasa==null){
	// locDocHabilitanteEspecializado.setTipoTasa(pTipoTasa);
	// }
	//
	// //No hay comprobación porque nunca hubo otro período
	//
	// locTasaActual.setUltimoPeriodo(pCuota);
	// this.entityManager.persist(locTasaActual);
	// this.entityManager.flush();
	// }
	// locTasaActual.toString();
	// for(RegistroDeuda cadaRegistro : locTasaActual.getListaRegistrosDeuda()){
	// cadaRegistro.toString();
	// }
	//
	// return locTasaActual;
	// }catch(NonUniqueResultException e){
	// //Este error solo debería tirarlo cuando se trata de liquidaciones PFO
	// System.out.println("Las cuotas de la obligación ya han sido liquidadas.");
	// e.printStackTrace();
	// }
	// return null;
	// }

	/**
	 * Genera un nuevo tipo de tasa a partir del tipo de tasa
	 * 
	 * @param pTipoTasa
	 * @return
	 */
	private Tasa generarTasaNueva(TipoTasa pTipoTasa) {
		Tasa locTasa = new Tasa();
		// locTasa.setTipoTasa(pTipoTasa);
		locTasa.setNombre(pTipoTasa.getNombre());
		locTasa.setCantidadRegDeuda(0);
		locTasa.setFechaDesde(Calendar.getInstance().getTime());
		locTasa.setNroUtltimoRegistroDeudaLiquidado(0);
		// locTasa.setPeriodicidadConfigurada(pTipoTasa.getPeriodicidad());
		// locTasa.setPeriodicidad(pTipoTasa.getPeriodicidad());
		return locTasa;
	}

	/**
	 * Recupera una liquidacion de una tasa a partir de un tipo de tasa, es necesario este método principalmente para tgi (parche horrible) porque hay varias
	 * fórmulas de cálculo para la misma liq
	 * 
	 * @param pTasa
	 * @return
	 * @throws Exception
	 */
	private List<LiquidacionTasa> getLiquidacionTasaFromTasa(Tasa pTasa, CuotaLiquidacion pCuota, Integer pNumeroCuota, Calendar pFechaLiquidacion,
			DocHabilitanteEspecializado pDocumento, TipoTasa pTipoTasa) throws Exception {
		List<LiquidacionTasa> listaLiquidacionesTasas = new ArrayList<LiquidacionTasa>();
		// primer periodo que se liquida
		// Significa que tengo que armar las cosas como si fueran varias liquidaciones distintas para el mísmo período
		LiquidacionTasa locLiquidacionTasa = new LiquidacionTasa();
		locLiquidacionTasa.setDocGeneradorDeuda(pTasa);
		locLiquidacionTasa.setFechaEmision(pFechaLiquidacion.getTime());
		locLiquidacionTasa.setTipoTasa(pTipoTasa);
		locLiquidacionTasa.setCuotaLiquidacion(pCuota);
		listaLiquidacionesTasas.add(locLiquidacionTasa);

		return listaLiquidacionesTasas;
	}

	/**
	 * Liquida la tasa general inmobiliaria para un período, esta liquidación representa la liquidación normal la misma es liquidada a la fecha del primer
	 * vencimiento
	 * 
	 * @param pPeriodo
	 * @return
	 * @ejb.interface-method view-type = "local"
	 */
	@Override
	public ResultadoLiquidacion liquidarTgi(CuotaLiquidacion[] pListaCuota, Persona pPersona, Parcela pParcela, Boolean pIgnorarPlan) throws Exception {
		this.rollbackOnly = false;
		List locListaLiquidacionesRetorno = new ArrayList();

		ResultadoLiquidacion locResultadoLiquidacion = new ResultadoLiquidacion();
			try {
				if(pListaCuota == null || pListaCuota.length < 1) {
					locResultadoLiquidacion.addMensaje(5);
					return locResultadoLiquidacion;
				}

				System.out.println("Preparando lista de Parámetros Valuados...");
				this.prepararListaParametroValuados();
				System.out.println("Actualizando Estado Cuentas...");
				this.ejecutarProcedimientoActualizacionDeuda(pPersona, pParcela);
				System.out.println("Preparando lista de Vencimientos...");
				this.prepararListaVencimientos();
				System.out.println("Preparando lista de Modificadores...");
				this.prepararListaModificadoresLiquidacion();
				System.out.println("Preparando lista de Exenciones...");
				this.prepararListaExencionesRegistrosDeuda();

				@SuppressWarnings("unused")
				double locTiempoInicio = System.currentTimeMillis();

				this.mostrarFechaYHoraInicioLiquidacion("");

				this.setIgnorarPlan(pIgnorarPlan);

				int acumuladorLiquidacionesTotales = 0;
				for(CuotaLiquidacion cadaCuota : pListaCuota) {
					try {
						locListaLiquidacionesRetorno = new ArrayList();
						System.out.println("LIQUIDANDO CUOTA: " + cadaCuota);

						locListaLiquidacionesRetorno.addAll(liquidarTGISinTransaccion(cadaCuota, pPersona, pParcela, locResultadoLiquidacion));

						acumuladorLiquidacionesTotales += locListaLiquidacionesRetorno.size();
						System.out.println("LOCLISTALIQUIDACIONESRETORNO IS EMPTY? = " + locListaLiquidacionesRetorno.isEmpty() + " MUST TO PERSISTE LIQUIDACIONES TASA.");
					} catch(Exception e) {
						// e.printStackTrace();
						locResultadoLiquidacion.getListaMensajes().add(e.getMessage());
						System.out.println("***** NO SE PUDO LIQUIDAR LA CUOTA: " + cadaCuota + " PORQUE HUBO UN ERROR DE POR MEDIO");
						// throw e;
					}

				}

				if(!locListaLiquidacionesRetorno.isEmpty()) {
					this.guardarLiquidacionesTasa(locListaLiquidacionesRetorno);
				}

				this.mostrarFechaYHoraFinLiquidacion("");
			} finally {
				System.gc();
			}
		locResultadoLiquidacion.setCantidadLiquidadas(locListaLiquidacionesRetorno.size());
		return locResultadoLiquidacion;
	}

	/**
	 * Anula las Liquidaciones no canceladas de la obligación, solo usar después de llamar al anular obligación
	 * 
	 * @ejb.interface-method view-type = "local"
	 * @param pObligacion
	 * @throws Exception
	 */
	@Override
	public void anularRegistrosDeudaSinCancelar(Obligacion pObligacion) throws Exception {
		try {
			List locListaRegistrosDeudasSincancelar = Criterio.getInstance(this.entityManager, RegistroDeuda.class).crearAlias("docGeneradorDeuda", "locDocGeneradorDeuda")
					.add(Restriccion.IGUAL("locDocGeneradorDeuda.obligacion", pObligacion)).list();

			for(Object cadaObject : locListaRegistrosDeudasSincancelar) {
				RegistroDeuda locRegistroDeuda = (RegistroDeuda) cadaObject;
				if(!locRegistroDeuda.getEstado().equals(EstadoRegistroDeuda.ANULADA) && !locRegistroDeuda.getEstado().equals(EstadoRegistroDeuda.CANCELADA)) {
					locRegistroDeuda.setEstado(EstadoRegistroDeuda.ANULADA);
					this.entityManager.merge(locRegistroDeuda);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("static-access")
	private void mostrarFechaYHoraFinLiquidacion(String pMensaje) {
		System.out.println("Fecha y Hora de Fin del Proceso de " + pMensaje + " Liquidaciones: " + Calendar.getInstance().getTime());
		double locTiempoFin = (System.currentTimeMillis() - tiempoInicio);
		double locHoras = 0;
		double locMinutos = 0;
		double locSegundos = 0;

		if(locTiempoFin > 3600000) {
			locHoras = locTiempoFin / 3600000;
			locTiempoFin -= (locHoras * 3600000);
		}

		if(locTiempoFin > 60000) {
			locMinutos = locTiempoFin / 60000;
			locTiempoFin -= (locMinutos * 60000);
		}

		if(locTiempoFin > 1000) {
			locSegundos = locTiempoFin / 1000;
		}
		double locRestoSegundos = locTiempoFin % 1000;

		NumberFormat locFormato = NumberFormat.getInstance();
		locFormato.setMaximumFractionDigits(2);

		System.out.println("Duración Total del Proceso de " + pMensaje + " Liquidaciones:");
		System.out.println(locFormato.getIntegerInstance().format(locHoras) + ":" + locFormato.getIntegerInstance().format(locMinutos) + ":"
				+ locFormato.getIntegerInstance().format(locSegundos) + "," + locFormato.getIntegerInstance().format(locRestoSegundos));
	}

	private void mostrarFechaYHoraInicioLiquidacion(String pMensaje) {
		System.out.println("Fecha y Hora de Inicio del Proceso " + pMensaje.trim() + " de Liquidaciones: " + Calendar.getInstance().getTime());
		tiempoInicio = System.currentTimeMillis();
	}

	private void prepararListaFormulas(TipoObligacion pTipoObligacion) {
		this.listaFormulasUtilizadas = Criterio.getInstance(this.entityManager, TipoTasa.class).add(Restriccion.IGUAL("estado", TipoTasa.Estado.ACTIVA))
				.add(Restriccion.IGUAL("tipoObligacion", pTipoObligacion)).list();

		this.listaNombresParametros = new ArrayList<String>();
		this.listaNombresModificadores = new ArrayList<String>();
		for(TipoTasa cadaTipoTasa : this.listaFormulasUtilizadas) {
			for(TipoParametro cadaTipoParametro : cadaTipoTasa.getListaParametros()) {
				this.listaNombresParametros.add(cadaTipoParametro.getNombreVariable());
			}

			for(TipoModificador cadaTipoModificador : cadaTipoTasa.getListaModificadores()) {
				this.listaNombresModificadores.add(cadaTipoModificador.getNombre());
			}

			for(TipoVencimiento cadaTipoVencimiento : cadaTipoTasa.getListaVencimientos()) {
				this.listaNombresVencimientos.add(cadaTipoVencimiento.getNombre());
			}
		}

	}

	private void prepararListaModificadoresLiquidacion() {
		// List<ModificadorLiquidacion> locListaModificadoresLiquidacion =
		// Criterio.getInstance(this.entityManager, ModificadorLiquidacion.class)
		// .list();
		//
		// this.listaModificadoresLiquidacion = new LinkedHashMap<Integer, ModificadorLiquidacion>();
		// for(ModificadorLiquidacion cadaModificadorLiquidacion : locListaModificadoresLiquidacion){
		// if(!this.listaModificadoresLiquidacion.containsKey(cadaModificadorLiquidacion.hashCode())){
		// this.listaModificadoresLiquidacion.put(cadaModificadorLiquidacion.hashCode(), cadaModificadorLiquidacion);
		// }
		// }
	}

	private void prepararListaVencimientos() {
		// List<VencimientoLiquidacion> locListaVencimientos =Criterio.getInstance(this.entityManager, VencimientoLiquidacion.class)
		// .list();
		//
		// this.listaVencimientos = new LinkedHashMap<Integer, VencimientoLiquidacion>();
		// for(VencimientoLiquidacion cadaVencimiento : locListaVencimientos){
		// if(!this.listaVencimientos.containsKey(cadaVencimiento.hashCode())){
		// this.listaVencimientos.put(cadaVencimiento.hashCode(), cadaVencimiento);
		// }
		// }
	}

	private void prepararListaParametroValuados() {
		// List<ParametroValuado> locListaParametrosValuados = Criterio.getInstance(this.entityManager, ParametroValuado.class)
		// .list();
		//
		// this.listaParametrosValuados = new LinkedHashMap<Integer, ParametroValuado>();
		// for (ParametroValuado cadaParametroValuado : locListaParametrosValuados) {
		// if (!this.listaParametrosValuados.containsKey(cadaParametroValuado
		// .hashCode())) {
		// this.listaParametrosValuados.put(
		// cadaParametroValuado.hashCode(), cadaParametroValuado);
		// }
		// }
	}

	private void prepararListaExencionesRegistrosDeuda() {
		// Mientras la exencion no esté terminada me sirve para agregarle nuevos registros de deuda exentos
		List<ExencionRegistroDeuda> locListaExencionesRegistrosDeuda = Criterio.getInstance(this.entityManager, ExencionRegistroDeuda.class)
				.add(Restriccion.DISTINTO("estado", Estado.TERMINADA)).list();

		this.listaExencionesRegistrosDeuda = new LinkedHashMap<Integer, ExencionRegistroDeuda>();
		for(ExencionRegistroDeuda cadaExencionRegistroDeuda : locListaExencionesRegistrosDeuda) {

			if(cadaExencionRegistroDeuda.getListaRegistrosExencion() != null && !cadaExencionRegistroDeuda.getListaRegistrosExencion().isEmpty()) {
				cadaExencionRegistroDeuda.getListaRegistrosExencion().toString();
			}

			if(!this.listaExencionesRegistrosDeuda.containsKey(cadaExencionRegistroDeuda.hashCode())) {
				this.listaExencionesRegistrosDeuda.put(cadaExencionRegistroDeuda.hashCode(), cadaExencionRegistroDeuda);
			}

		}
	}

	private java.util.List liquidarTGISinTransaccion(CuotaLiquidacion pCuota, Persona pPersona, Parcela pParcela, ResultadoLiquidacion pResultadoLiquidacion) throws Exception {
		if(!this.existeTipoTasa("TGI")) {
			throw new SaicException(21);
		}

		// this.cargarListaFormulas("TGI", pCuota);

		double locTiempoInicio = System.currentTimeMillis();
		this.prepararTiposParametros(pCuota);
		System.out.println("Tiempo en preparar los tipos de parámetros = " + ((System.currentTimeMillis() - locTiempoInicio) / 1000));

		locTiempoInicio = System.currentTimeMillis();
		Criterio locCriterio = Criterio.getInstance(entityManager, DocumentoTGI.class)
				.add(Restriccion.IGUAL("estado", DocHabilitanteEspecializado.Estado.ACTIVO))
				.setDistinct(true)
		// .add(Restriccion.MENOR("fechaInicioActividad",pCuota.getPeriodo().getFechaFin().getTime()))
				.add(Restriccion.IGUAL("obligacion.persona", pPersona)).add(Restriccion.IGUAL("parcela", pParcela));

		if(pPersona == null) {
			locCriterio.crearFetchAlias("obligacion.persona", "locPersona");
		}

		if(pParcela == null) {
			locCriterio.crearFetchAlias("parcela.listaRegistrosMejora", "mejoras");
			locCriterio.crearFetchAlias("parcela.listaParcelasPorCuadra", "parcelasPorCuadra");
		}

		List<DocumentoTGI> listado = locCriterio.list();
		System.out.println("Obligaciones encontradas: " + listado.size() + " **********************************************************");
		int total = listado.size();

		int procesadas = 0;
		int liquidadas = 0;

		System.out.println("tiempo en buscar las obligaciones = " + ((System.currentTimeMillis() - locTiempoInicio) / 1000));

		List<LiquidacionTasa> locListaLiquidacionesRetorno = new ArrayList<LiquidacionTasa>();

		CalendarioMunicipal locCalendarioMunicipal = (CalendarioMunicipal) pCuota.getPeriodo().getCalendario();

		listaFormulasUtilizadas = Criterio.getInstance(this.entityManager, TipoTasa.class).add(Restriccion.IGUAL("plan", locCalendarioMunicipal.getPlan()))
				.add(Restriccion.IGUAL("estado", TipoTasa.Estado.ACTIVA)).list();

		pCuota = entityManager.merge(pCuota);

		levantarTasasParaCuota(pCuota, listado);
		levantarLiquidacionesParaCuota(pCuota, listado);

		for(DocumentoTGI cadaDocumento : listado) {

			procesadas++;
			liquidadas++;

			// List<LiquidacionTasa> listaLiquidacionesTGIAux = null;
			try {
				double tiempoInicio = System.currentTimeMillis();
				// boolean isAlgunaTasaActiva = false;

				Obligacion locObligacion = cadaDocumento.getObligacion();

				// traigo las tasas correspondiente a cada obligacion
				// boolean locIsPeriodoLiquidado=this.isPeriodoLiquidado(locObligacion, pCuota);
				double tiempo = System.currentTimeMillis();
				System.out.println("Tiempo transcurrido para Recuperar Lista de Tasas TGI = " + ((tiempo - tiempoInicio) / 1000));

				// if(!locIsPeriodoLiquidado){
				// Tasa locTasa = this.getTasa(locObligacion, pCuota);
				locListaLiquidacionesRetorno.addAll(this.liquidarObligacion(locObligacion, pCuota, Calendar.getInstance(), listaFormulasUtilizadas.get(0)));

				// }
				double tiempoFinal = System.currentTimeMillis();

				double tiempoTranscurrido = tiempoFinal - tiempoInicio;

				System.out.println("Tiempo por liquidacion = " + (tiempoTranscurrido / 1000) + " seg");
			} catch(TrascenderException t) {
				liquidadas--;
				pResultadoLiquidacion.getListaMensajes().add(t.getMessage());
				pResultadoLiquidacion.getListaObligacionesNoLiquidadas().add(cadaDocumento.getObligacion());
			} catch(Exception e) {
				liquidadas--;
				e.printStackTrace();
				pResultadoLiquidacion.getListaMensajes().add(e.getMessage());
				pResultadoLiquidacion.getListaObligacionesNoLiquidadas().add(cadaDocumento.getObligacion());
			}

			// listaLiquidacionesTGIAux.clear();
			System.out.println("                  Se han procesado " + procesadas + "/" + total + " obligaciones");
			System.out.println("                  Se han liquidado " + liquidadas + "/" + total + " obligaciones");
		}
		listado.clear();
		System.gc();
		return locListaLiquidacionesRetorno;

	}

	// private void cargarListaFormulas(String pTipoObligacion, Periodo pPeriodo) throws Exception {
	// //recupero los tipo tasas
	// //Si es la cuota 1 traigo todas. sino la q necesito para liquidar
	// Criterio locCriterio = Criterio.getInstance(this.entityManager, TipoTasa.class)
	// .crearAlias("tipoObligacion.listaCalendarioMunicipal", "cadaCalendario")
	// .add(Restriccion.IGUAL("cadaCalendario.anio", pPeriodo.getFechaInicio().get(Calendar.YEAR)))
	// .add(Restriccion.IGUAL("estado",TipoTasa.Estado.ACTIVA))
	// .add(Restriccion.IGUAL("tipoObligacion.nombre", pTipoObligacion))
	// .setDistinct(true)
	// .setModoDebug(true);
	//
	// if(pPeriodo.getNumero() > 1){
	// locCriterio.add(Restriccion.IGUAL("periodicidad", pPeriodo.getCalendario().getPeriodicidad()));
	// }
	//
	// this.listaFormulasUtilizadas = locCriterio.list();
	//
	// if(this.listaFormulasUtilizadas == null || this.listaFormulasUtilizadas.isEmpty()){
	// throw new SaicException(45);
	// }
	// System.out.println("tasas para liquidar: "+ this.listaFormulasUtilizadas.size() + " - "+ this.listaFormulasUtilizadas);
	// los filtro, solo debo dejar aquellos que tengan
	// un calendario activo para el anio que se desea liquidar
	// Iterator<TipoTasa> locIterador = this.listaFormulasUtilizadas.iterator();

	// while(locIterador.hasNext()){
	// TipoTasa cadaTipoTasa = locIterador.next();
	// TipoObligacion cadaTipoObligacion = cadaTipoTasa.getTipoObligacion();
	// boolean remove = false;
	//
	// if(!cadaTipoTasa.getEstado().equals(TipoTasa.Estado.ACTIVA)){
	// locIterador.remove();
	// continue;
	// }
	// if(cadaTipoObligacion.getCalendarioActivo(cadaTipoTasa.getPeriodicidad(), pPeriodo.getFechaInicio().get(Calendar.YEAR))
	// == null){
	// remove = true;
	// }
	//
	// if(remove){
	// locIterador.remove();
	// }
	// }
	// }

	private boolean isTasaCancelada(Obligacion pObligacion, CuotaLiquidacion pCuota) {
		List locListaTasasCanceladas = Criterio.getInstance(this.entityManager, LiquidacionTasa.class).crearAlias("docGeneradorDeuda.obligacion", "locObligacion")
				.add(Restriccion.IGUAL("locObligacion", pObligacion)).add(Restriccion.IGUAL("cuotaLiquidacion.periodo.periodicidad", pCuota.getPeriodo().getPeriodicidad()))
				.add(Restriccion.NULO("registroCancelacion")).list();

		for(Object cadaObject : locListaTasasCanceladas) {
			LiquidacionTasa locLiquidacionTasa = (LiquidacionTasa) cadaObject;
			Tasa locTasa = (Tasa) locLiquidacionTasa.getDocGeneradorDeuda();
			if((locLiquidacionTasa.getCuotaLiquidacion().getPeriodo().getFechaInicio().get(Calendar.YEAR) == pCuota.getPeriodo().getFechaInicio().get(Calendar.YEAR))
					&& (locTasa.getEstado().equals(Tasa.Estado.INACTIVO))) {
				return true;
			}
		}
		return false;
	}

	private void guardarLiquidacionesTasa(List<LiquidacionTasa> pListaLiquidacionesRetorno) {
		try {
			// System.out.println("Guardando Parámetros Valuados...");
			// for(ParametroValuado cadaParametroValuado : this.listaParametrosValuados.values()){
			// if(cadaParametroValuado.getIdParametroValuado() == -1){
			// this.entityManager.persist(cadaParametroValuado);
			// }
			// }
			// this.entityManager.flush();
			// this.listaParametrosValuados.clear();
			// System.gc();
		} catch(Exception e) {
			System.out.println("Error al guardar los parametros valuados");
			e.printStackTrace();
		}
		try {
			System.out.println("Guardando las alicuotas liquidadas");
			for(AlicuotaLiquidada cadaAlicuotaLiquidada : this.listaAlicuotasLiquidadas.values()) {
				if(cadaAlicuotaLiquidada.getIdAlicuotaLiquidada() == -1) {
					this.entityManager.persist(cadaAlicuotaLiquidada);
				}
			}
			this.entityManager.flush();
			this.listaAlicuotasLiquidadas.clear();
		} catch(Exception e) {
			System.out.println("Error al guardar las alicuotas liquidadas");
			e.printStackTrace();
		}

		try {
			System.out.println("Guardando Modificadores de Liquidaciones...");
			// int locContadorModificadores = 0;
			//
			// for(ModificadorLiquidacion cadaModificadorLiquidacion : this.listaModificadoresLiquidacion.values()){
			// if(locContadorModificadores == 10000){
			// System.out.println(locContadorModificadores + " modificadores guardados o actualizados...");
			// this.entityManager.flush();
			// locContadorModificadores=0;
			// }
			// if(cadaModificadorLiquidacion.getIdModificadorLiquidacion() == -1){
			// this.entityManager.persist(cadaModificadorLiquidacion);
			// }
			// locContadorModificadores++;
			// }
			//
			// if(locContadorModificadores > 0){
			// System.out.println(locContadorModificadores + " modificadores guardados o actualizados...");
			// }
			//
			// this.entityManager.flush();
			// this.listaModificadoresLiquidacion.clear();
			// System.gc();
		} catch(Exception e) {
			System.out.println("Error al guardar los modificadores de liquidaciones");
			e.printStackTrace();
		}

		try {
			// System.out.println("Guardando Vencimientos...");
			// int locContadorVencimientos = 0;
			//
			// for(Vencimiento cadaVencimiento : this.listaVencimientos.values()){
			// if(locContadorVencimientos == 10000){
			// System.out.println(locContadorVencimientos + " vencimientos guardados o actualizados...");
			// this.entityManager.flush();
			// locContadorVencimientos=0;
			// }
			//
			// if(cadaVencimiento.getIdVencimiento() == -1){
			// this.entityManager.persist(cadaVencimiento);
			// }
			// locContadorVencimientos++;
			// }
			//
			// if(locContadorVencimientos > 0){
			// System.out.println(locContadorVencimientos + " vencimientos guardados o actualizados...");
			// }
			//
			// this.entityManager.flush();
			// this.listaVencimientos.clear();
			// System.gc();
		} catch(Exception e) {
			System.out.println("Error al guardar los vencimientos");
			e.printStackTrace();
		}

		try {
			System.out.println("Guardando Liquidaciones...");
			int locContadorLiquidaciones = 0;

			for(LiquidacionTasa cadaLiquidacionTasa : pListaLiquidacionesRetorno) {

				// if(!this.isPeriodoTGILiquidado(cadaLiquidacionTasa.getDocGeneradorDeuda().getObligacion(), cadaLiquidacionTasa.getCuotaLiquidacion())){
				if(locContadorLiquidaciones == 10000) {
					System.out.println(locContadorLiquidaciones + " liquidaciones guardadas...");
					this.entityManager.flush();
					locContadorLiquidaciones = 0;
				}
				cadaLiquidacionTasa.setRegistroExencionRegistroDeuda(null);
				try {
					this.entityManager.persist(cadaLiquidacionTasa);
					System.out.println("\tMUST TO BE PERSISTED CADA LIQUIDACION: " + cadaLiquidacionTasa + "\n " + locContadorLiquidaciones + "/" + pListaLiquidacionesRetorno.size());
				} catch(Exception e) {
					e.printStackTrace();
				}

				locContadorLiquidaciones++;
			}

			if(locContadorLiquidaciones > 0) {
				System.out.println(locContadorLiquidaciones + " liquidaciones guardadas...");
			}
			// System.gc();
		} catch(Exception e) {
			System.out.println("Error al guardar las Liquidaciones.");
			e.printStackTrace();
		}

		try {
			System.out.println("Guardando o Actualizando Exenciones...");
			int locContadorExenciones = 0;

			for(ExencionRegistroDeuda cadaExencionRegistroDeuda : this.listaExencionesRegistrosDeuda.values()) {

				if(locContadorExenciones == 10000) {
					System.out.println(locContadorExenciones + " exenciones guardadas o actualizadas...");
					locContadorExenciones = 0;
				}

				if(cadaExencionRegistroDeuda.getListaRegistrosExencion() != null && !cadaExencionRegistroDeuda.getListaRegistrosExencion().isEmpty()) {
					for(RegistroExencionRegistroDeuda cadaRegistroExencionRegistroDeuda : cadaExencionRegistroDeuda.getListaRegistrosExencion()) {
						cadaRegistroExencionRegistroDeuda.getRegistroDeuda().setRegistroExencionRegistroDeuda(cadaRegistroExencionRegistroDeuda);
						try {
							this.entityManager.merge(cadaRegistroExencionRegistroDeuda.getRegistroDeuda());
						} catch(Exception e) {
							e.printStackTrace();
						}
					}
				}

				this.entityManager.merge(cadaExencionRegistroDeuda);
				locContadorExenciones++;
			}

			if(locContadorExenciones > 0) {
				System.out.println(locContadorExenciones + " exenciones guardadas o actualizadas...");
			}
			// System.gc();
		} catch(Exception e) {
			System.out.println("Error al guardar las Exenciones.");
			e.printStackTrace();
		}

	}

	/**
	 * Prepara los tipos de parámetro para cada liquidacion
	 */
	private void prepararTiposParametros(CuotaLiquidacion pCuota) {
		// Date locFechaVencimientoCuota = (Date) pCuota.getListaVencimientos().get(0).getTime().clone();
		// Calendar locCalendar = Calendar.getInstance();
		// locCalendar.setTime(locFechaVencimientoCuota);
		// locCalendar.set(Calendar.HOUR_OF_DAY, 23);
		// locCalendar.set(Calendar.MINUTE, 59);
		// locCalendar.set(Calendar.SECOND, 59);
		//
		// Map<String, TipoParametro> mapaTiposParametro = new HashMap<String, TipoParametro>();
		// // Restriccion.OR(Restriccion.AND(Restriccion.NOT(Restriccion.NULO("fechaBaja")), /// Restriccion.IGUAL("activo",true)))
		// List<TipoParametro> listaResultadoParamConst = Criterio.getInstance(this.entityManager,
		// TipoParametroConstante.class).add(Restriccion.MENOR("fechaAlta", locCalendar.getTime()))
		// .add(Orden.DESC("fechaAlta")).add(Orden.ASC("nombreVariable")).list();
		//
		// List<TipoParametro> listaResultadoGrupoZona = Criterio.getInstance(this.entityManager,
		// TipoParametroGrupoZona.class).add(Restriccion.MENOR("fechaAlta", locCalendar.getTime()))
		// .add(Orden.DESC("fechaAlta")).add(Orden.ASC("nombreVariable")).list();
		//
		// List<TipoParametro> listaResultados = new ArrayList<TipoParametro>();
		// listaResultados.addAll(listaResultadoParamConst);
		// listaResultados.addAll(listaResultadoGrupoZona);
		//
		// for(TipoParametro cadaParametro : listaResultados) {
		// if(mapaTiposParametro.get(cadaParametro.getNombreVariable()) == null) {
		// mapaTiposParametro.put(cadaParametro.getNombreVariable(), cadaParametro);
		// }
		// }
		//
		// for(TipoParametro cadaTipoParametro : mapaTiposParametro.values()) {
		// cadaTipoParametro.setPeriodoLiquidacion(pCuota.getPeriodo());
		// this.tiposParametros.put(cadaTipoParametro.getNombreVariable(), cadaTipoParametro);
		// }
	}

	/**
	 * 
	 * @param pPeriodo
	 * @return
	 * @ejb.interface-method view-type = "local"
	 */
	@Override
	public void generarLiquidacionPruebaTGI(CuotaLiquidacion pCuota, Persona pPersona, Parcela pParcela) throws Exception {
		if(pCuota == null || pCuota.getPeriodo() == null) {
			throw new SaicException(20);
		}
		try {
			this.rollbackOnly = true;
			System.out.println("Preparando lista de Parámetros Valuados...");
			this.prepararListaParametroValuados();
			System.out.println("Actualizando Estado Cuentas...");
			this.ejecutarProcedimientoActualizacionDeuda(pPersona, pParcela);
			System.out.println("Preparando lista de Vencimientos...");
			this.prepararListaVencimientos();
			System.out.println("Preparando lista de Modificadores...");
			this.prepararListaModificadoresLiquidacion();

			this.mostrarFechaYHoraInicioLiquidacion("Prueba de");

			// List locListaLiquidacionesRetorno = liquidarTGISinTransaccion(pCuota, pPersona, pParcela);
			System.gc();

			this.businessImpresion.setEntityManager(this.entityManager);
			System.gc();

			this.mostrarFechaYHoraFinLiquidacion("Prueba de");

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Realiza las liquidaciones de prueba OSP
	 * 
	 * @param pServicio
	 * @param pCalle
	 * @param pPeriodo
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type="local"
	 */
	@Override
	public List generarLiquidacionPruebaOSP(ServicioOSP pServicio, Calle pCalle, CuotaLiquidacion pCuota, Persona pPersona, Parcela pParcela) throws Exception {

		try {

			System.out.println("Preparando lista de Parámetros Valuados...");
			this.prepararListaParametroValuados();
			System.out.println("Actualizando Estado Cuentas...");
			this.ejecutarProcedimientoActualizacionDeuda(null, null);
			System.out.println("Preparando lista de Vencimientos...");
			this.prepararListaVencimientos();
			System.out.println("Preparando lista de Modificadores...");
			this.prepararListaModificadoresLiquidacion();

			this.mostrarFechaYHoraInicioLiquidacion("Prueba de");

			this.setTipoTasaParaLiquidacionOSP(pServicio, pCalle);

			// List locListaLiquidacionesRetorno = this.generarLiquidacionOSP(pCuota, pCalle, pServicio, pPersona);

			this.businessImpresion.setEntityManager(this.entityManager);

			this.mostrarFechaYHoraFinLiquidacion("Prueba de");
			// return locListaLiquidacionesRetorno;
			return null;
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	/**
	 * 
	 * @param pPersona
	 * @param pObra
	 * @param pCalle
	 * @param pPeriodo
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public List generarLiquidacionPruebaPFO(Persona pPersona, Obra pObra, Calle pCalle, CuotaLiquidacion pCuota) throws Exception {
		try {
			if(pCuota == null) {
				throw new SaicException(20);
			}

			System.out.println("Preparando lista de Parámetros Valuados...");
			this.prepararListaParametroValuados();
			System.out.println("Actualizando Estado Cuentas...");
			this.ejecutarProcedimientoActualizacionDeuda(pPersona, null);
			System.out.println("Preparando lista de Vencimientos...");
			this.prepararListaVencimientos();
			System.out.println("Preparando lista de Modificadores...");
			this.prepararListaModificadoresLiquidacion();

			this.mostrarFechaYHoraInicioLiquidacion("Prueba de");

			List locListaLiquidacionesRetorno = this.generarLiquidacionPFO(pCuota, pObra, pCalle, pPersona);

			this.limpiarListasLiquidaciones();

			this.businessImpresion.setEntityManager(this.entityManager);
			// List<ReporteLiquidacionPFO> listadoReportePFO = this.businessImpresion.getListadoReportePFO(locListaLiquidacionesRetorno);

			// También limpio la lista de liquidaciones pero recién después de obtener el listado de reportes
			locListaLiquidacionesRetorno.clear();

			// this.guardarTablaTemporalPFO(listadoReportePFO);

			this.mostrarFechaYHoraFinLiquidacion("Prueba de");
			return locListaLiquidacionesRetorno;
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	private void limpiarListasLiquidaciones() {
		// this.listaParametrosValuados.clear();
		// this.listaEstadoCuentasTGI.clear();
		// this.listaModificadoresLiquidacion.clear();
		// this.listaVencimientos.clear();
		this.listaEstadoCuentasTemporales.clear();
	}

	/**
	 * @param pPersona
	 * @param pRubro
	 * @param pPeriodo
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	@Override
	public List generarLiquidacionPruebaSHPS(Persona pPersona, CuotaLiquidacion pCuota, FiltroObligacionSHPS pFiltro) throws Exception {
		if(pCuota == null) {
			throw new SaicException(20);
		}
		try {
			this.setTipoTasaParaLiquidacionSHPS(pPersona, pCuota, pFiltro);
			System.out.println("Preparando lista de Parámetros Valuados...");
			this.prepararListaParametroValuados();
			System.out.println("Preparando la lista de Parametros alicuotas");
			this.prepararListaAlicuotasLiquidadas();
			System.out.println("Actualizando Estado Cuentas...");
			if(pPersona != null) {
				this.ejecutarProcedimientoActualizacionDeuda(pPersona, null);
			}

			System.out.println("Preparando lista de Vencimientos...");
			this.prepararListaVencimientos();
			System.out.println("Preparando lista de Modificadores...");
			this.prepararListaModificadoresLiquidacion();

			this.mostrarFechaYHoraInicioLiquidacion("Prueba de");
			List locListaLiquidacionesRetorno = this.generarLiquidacionesSHPS(pPersona, pCuota, null , pFiltro);

			// this.businessImpresion.setEntityManager(this.entityManager);

			System.gc();

			this.mostrarFechaYHoraFinLiquidacion("Prueba de");

			return locListaLiquidacionesRetorno;
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 
	 * @param pPeriodo
	 * @param pCalle
	 * @param pServicio
	 * @return
	 * @throws Exception
	 */
	private List generarLiquidacionOSP(CuotaLiquidacion pCuota, List<DocumentoOSP> pListaDocumentos) throws Exception {
		// tiempo de inicio de la liquidación
		double locTiempoInicio = System.currentTimeMillis();
		// this.cargarListaFormulas("OYSP", pCuota);
		CalendarioMunicipal locCalendarioMunicipal = (CalendarioMunicipal) pCuota.getPeriodo().getCalendario();
		TipoTasa locTipoTasa = Criterio.getInstance(this.entityManager, TipoTasa.class).add(Restriccion.IGUAL("estado", TipoTasa.Estado.ACTIVA))
				.add(Restriccion.IGUAL("plan", locCalendarioMunicipal.getPlan())).uniqueResult();

		int total = pListaDocumentos.size();

		int procesadas = 0;
		int liquidadas = 0;

		System.out.println("Tiempo en buscar las obligaciones = " + ((System.currentTimeMillis() - locTiempoInicio) / 1000));
		System.out.println("Cantidad de obligaciones " + total);
		List<LiquidacionTasa> locListaLiquidacionesRetorno = new ArrayList<LiquidacionTasa>();

		this.prepararTiposParametros(pCuota);

		pCuota = entityManager.merge(pCuota);

		levantarTasasParaCuota(pCuota, pListaDocumentos);
		levantarLiquidacionesParaCuota(pCuota, pListaDocumentos);

		for(DocumentoOSP locDocumentoOSP : pListaDocumentos) {
			double tiempoInicio = System.currentTimeMillis();

			liquidadas++;
			procesadas++;

			// locDocumentoOSP.getObligacion().toString(); no!
			// boolean locIsPeriodoLiquidado=this.isPeriodoLiquidado(locDocumentoOSP.getObligacion(), pCuota);
			// if (!locIsPeriodoLiquidado){
			try {

				List<LiquidacionTasa> locListaLiquidacionesTasa = this.liquidarObligacion(locDocumentoOSP.getObligacion(), pCuota, pCuota.getPeriodo().getFechaInicio(), locTipoTasa);
				locListaLiquidacionesRetorno.addAll(locListaLiquidacionesTasa);
				/**
				 * TODO Ver esto, porque si se quieren liquidar otras periodicidades, estas cagado.
				 */
				// actualizo el estado de los valores de medidor a liquidados

				// for(LiquidacionTasa cadaLiquidacionTasa : locListaLiquidacionesTasa){

				// DocHabilitanteEspecializado locDocumentoEspecializado =
				// cadaLiquidacionTasa.getDocGeneradorDeuda().getObligacion().getDocumentoEspecializado();
				// for(RegistroValuado cadaRegistroValuado : locDocumentoEspecializado.getListaRegistrosValuados()){
				// if(cadaRegistroValuado.getPeriodo() == pPeriodo){
				// cadaRegistroValuado.setEstado(RegistroValuado.Estado.LIQUIDADO);
				// this.entityManager.merge(cadaRegistroValuado);
				// }
				// }
				// }
			} catch(TrascenderException t) {
				liquidadas--;
				System.out.println("          //*/*/*");
				System.out.println("ERROR: " + t.getMessage());
				System.out.println("          //*/*/*");
			} catch(Exception e) {
				liquidadas--;
				System.out.println(":::::: No se ha podido liquidar la obligacion.");
				e.printStackTrace();
				// locListaLiquidacionesRetorno.removeAll(listaLiquidacionesTGIAux);
			}

			double tiempoFinal = System.currentTimeMillis();
			double tiempoTranscurrido = tiempoFinal - tiempoInicio;
			System.out.println("Tiempo por liquidacion = " + (tiempoTranscurrido / 1000) + " seg");

			// listaLiquidacionesTGIAux.clear();
			System.out.println("                  Se han procesado " + procesadas + "/" + total + " obligaciones");
			System.out.println("                  Se han liquidado " + liquidadas + "/" + total + " obligaciones");
		}
		return locListaLiquidacionesRetorno;
	}
	
	private List generarLiquidacionArrendamiento(CuotaLiquidacion pCuota, List<DocumentoArrendamiento> pListaDocumentos) throws Exception {
		// tiempo de inicio de la liquidación
		double locTiempoInicio = System.currentTimeMillis();
		// this.cargarListaFormulas("OYSP", pCuota);
		CalendarioMunicipal locCalendarioMunicipal = (CalendarioMunicipal) pCuota.getPeriodo().getCalendario();
		TipoTasa locTipoTasa = Criterio.getInstance(this.entityManager, TipoTasa.class)
				.add(Restriccion.IGUAL("estado", TipoTasa.Estado.ACTIVA))
				.add(Restriccion.IGUAL("plan", locCalendarioMunicipal.getPlan())).uniqueResult();

		int total = pListaDocumentos.size();

		int procesadas = 0;
		int liquidadas = 0;

		System.out.println("Tiempo en buscar las obligaciones = " + ((System.currentTimeMillis() - locTiempoInicio) / 1000));
		System.out.println("Cantidad de obligaciones " + total);
		List<LiquidacionTasa> locListaLiquidacionesRetorno = new ArrayList<LiquidacionTasa>();

		this.prepararTiposParametros(pCuota);

		pCuota = entityManager.merge(pCuota);

		levantarTasasParaCuota(pCuota, pListaDocumentos);
		levantarLiquidacionesParaCuota(pCuota, pListaDocumentos);

		for(DocumentoArrendamiento locDocumentoArrendamiento : pListaDocumentos) {
			double tiempoInicio = System.currentTimeMillis();

			liquidadas++;
			procesadas++;

			try {

				List<LiquidacionTasa> locListaLiquidacionesTasa = this.liquidarObligacion(locDocumentoArrendamiento.getObligacion(), pCuota, pCuota.getPeriodo().getFechaInicio(), locTipoTasa);
				locListaLiquidacionesRetorno.addAll(locListaLiquidacionesTasa);
			} catch(TrascenderException t) {
				liquidadas--;
				System.out.println("          //*/*/*");
				System.out.println("ERROR: " + t.getMessage());
				System.out.println("          //*/*/*");
			} catch(Exception e) {
				liquidadas--;
				System.out.println(":::::: No se ha podido liquidar la obligacion.");
				e.printStackTrace();
			}

			double tiempoFinal = System.currentTimeMillis();
			double tiempoTranscurrido = tiempoFinal - tiempoInicio;
			System.out.println("Tiempo por liquidacion = " + (tiempoTranscurrido / 1000) + " seg");

			// listaLiquidacionesTGIAux.clear();
			System.out.println("                  Se han procesado " + procesadas + "/" + total + " obligaciones");
			System.out.println("                  Se han liquidado " + liquidadas + "/" + total + " obligaciones");
		}
		return locListaLiquidacionesRetorno;
	}

	private List<Parcela> getListaParcelasFromCalle(Calle pCalle) throws Exception {
		try {
			return Criterio.getInstance(this.entityManager, Parcela.class).crearAlias("domicilioParcelario.relacionCalle", "locRelaCalle")
					.add(Restriccion.IGUAL("locRelaCalle.idAbstractCalle", pCalle.getIdCalle())).list();
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * Recupera el número de período para una tasa (en caso de ser anual, con periodicidad anual, siempre retorna 1)
	 * 
	 * @param pTasaTGI
	 * @param pPeriodo
	 * @return
	 */
	@SuppressWarnings("unused")
	private Integer getNumeroPeriodo(TasaTGI pTasaTGI, Periodo pPeriodo) {
		// if ((pTasaTGI.getTipoTasa().getPeriodicidad().equals(Periodicidad.ANUAL)) &&
		// (pTasaTGI.getTipoTasa().getPeriodicidadCuotas().equals(Periodicidad.ANUAL))){
		// return 1;
		// }
		return pPeriodo.getNumero();
	}

	/**
	 * Verifica que un período no haya sido liquidado para el tipo de tasa
	 * 
	 * @param obligacion
	 * @param pTasaTGI
	 * @return
	 */
	@SuppressWarnings({"unused"})
	private boolean isPeriodoLiquidadoTGI(TasaTGI pTasaTGI, Periodo pPeriodo) throws Exception {
		Query locStatement = this.entityManager.createNativeQuery("SELECT FIRST 1 l.id_registro_deuda FROM liquidacion_tasa l " + "  INNER JOIN periodo p"
				+ "  	ON p.id_periodo = l.id_periodo" + "  INNER JOIN registro_deuda r" + "   ON r.id_registro_deuda = l.id_registro_deuda" + "  WHERE r.id_doc_generador_deuda = ? "
				+ " AND p.fecha_inicio = ?" + " AND p.periodicidad = ?");

		PeriodoLiquidacion locPeriodo = (PeriodoLiquidacion) pPeriodo;
		locStatement.setParameter(1, pTasaTGI.getIdDocGeneradorDeuda());
		Date locFechaPeriodo = pPeriodo.getFechaInicio().getTime();
		locStatement.setParameter(2, new java.sql.Date(locFechaPeriodo.getTime()));
		locStatement.setParameter(3, Util.getEnumNameFromString(locPeriodo.getCalendario().getPeriodicidad().toString().toUpperCase()));

		// Si hay al menos un resultado va a dar true
		List locResultado = locStatement.getResultList();// executeQuery();
		boolean esPeriodoLiqudado = !locResultado.isEmpty();

		System.out.println("::::::::::::::::::::::::: Período liquidado: " + ((esPeriodoLiqudado) ? "SI" : "NO"));
		return esPeriodoLiqudado;

	}

	/**
	 * Obtiene la lista de tasas para la obligación
	 * 
	 * @param pObligacion
	 *            obligación de la cual obtener el tipo de tasa
	 * @param pPeriodo
	 *            período al que hace referencia
	 * @return
	 */
	private List<Tasa> getListaTasas(Obligacion pObligacion, CuotaLiquidacion pCuota) throws Exception {
		List<Tasa> locListaTasasRetorno = new ArrayList<Tasa>();

		for(TipoTasa cadaTipoTasa : this.listaFormulasUtilizadas) {
			Tasa locNuevaTasa = new Tasa();
			locNuevaTasa.setFechaDesde(Calendar.getInstance().getTime());
			locNuevaTasa.setNombre(cadaTipoTasa.getNombre());
			locNuevaTasa.setCantidadRegDeuda(1);

			// locNuevaTasa.setPeriodicidad(cadaTipoTasa.getPeriodicidad());

			locNuevaTasa.setObligacion(pObligacion);
			locNuevaTasa.setUltimoPeriodo(pCuota);

			// locNuevaTasa.setPeriodicidadConfigurada(cadaTipoTasa.getPeriodicidadCuotas());
			// locNuevaTasa.setTipoTasa(cadaTipoTasa);

			// if(cadaTipoTasa.getPeriodicidad().equals(Periodicidad.ANUAL)
			// && cadaTipoTasa.getPeriodicidadCuotas().equals(Periodicidad.ANUAL)){
			// existeFormulaAnual = true;
			// }
			locListaTasasRetorno.add(locNuevaTasa);
		}

		// Si no existe una formula anual entonces dejo activas todas las tasas
		for(Tasa cadaTasaNueva : locListaTasasRetorno) {
			// if(!existeFormulaAnual){
			// cadaTasaTGINueva.setEstado(TasaTGI.Estado.ACTIVO);
			// }
			this.entityManager.persist(cadaTasaNueva);
		}

		this.entityManager.flush();
		System.out.println("Nuevas Tasas TGI creadas...");
		return locListaTasasRetorno;
	}

	// /**
	// * Trae la equivalencia de un período para el tipo de tasa actual (toma las periodicidades)
	// * @param pTipoTasa
	// * @param pPeriodo
	// * @return
	// */
	// private Periodo getEquivalenciaPeriodo(TipoTasa pTipoTasa, Periodo pPeriodo) throws Exception{
	// PeriodoLiquidacion locPeriodo = (PeriodoLiquidacion) pPeriodo;
	//
	// if (pTipoTasa.getPeriodicidad().equals(locPeriodo.getCalendario().getPeriodicidad())){
	// return pPeriodo;
	// }
	// this.entityManager.detach(pPeriodo);
	//
	// return this.businessPeriodo.getPeriodo(pPeriodo.getFechaInicio(),
	// pPeriodo.getNumero(),
	// pPeriodo.getFechaInicio().get(Calendar.YEAR),
	// pTipoTasa.getTipoObligacion().getCalendarioActivo(pTipoTasa.getPeriodicidad(),
	// pPeriodo.getFechaInicio().get(Calendar.YEAR)).getPeriodicidad(),
	// pTipoTasa.getTipoObligacion());
	//
	// }

	/**
	 * Verifica que exista al menos un tipo de tasa activa
	 * 
	 * @param pTipoDocHabilitante
	 * @return
	 */
	private boolean existeTipoTasa(String pTipoDocHabilitante) {
		return((Long) Criterio.getInstance(this.entityManager, TipoTasa.class).add(Restriccion.IGUAL("plan.tipoObligacion.nombre", pTipoDocHabilitante))
				.setProyeccion(Proyeccion.COUNT()).uniqueResult() > 0);
	}

	/**
	 * Obtiene la fecha del primer vencimiento de un período
	 * 
	 * @param pObligacion
	 * @param pPeriodo
	 * @return
	 */
	private Calendar getFechaPrimerVencimiento(Obligacion pObligacion, Periodo pPeriodo) throws Exception {
		TipoTasa locTipoTasa = pObligacion.getDocumentoEspecializado().getTipoTasa();
		TipoVencimiento[] locListaTiposVencimiento = new TipoVencimiento[locTipoTasa.getListaVencimientos().size()];
		locTipoTasa.getListaVencimientos().toArray(locListaTiposVencimiento);
		this.ordenarArrayTiposVencimiento(locListaTiposVencimiento);
		return this.getFechaVencimiento(pPeriodo.getFechaInicio(), locListaTiposVencimiento[0]);
	}

	/**
	 * Verifica si el período fué liquidado para una obligación
	 * 
	 * @param pObligacion
	 *            obligación asociada
	 * @param pPeriodo
	 *            periodo asociado
	 * @return true si el período fué liquidado
	 */
	private boolean isPeriodoLiquidado(Obligacion pObligacion, CuotaLiquidacion pCuota) {
		return listaIdRegistroDeudaLiquidados.contains(pObligacion.getIdObligacion());
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	private void levantarLiquidacionesParaCuota(CuotaLiquidacion pCuota, List<? extends DocHabilitanteEspecializado> listaDocumentos) {
		Criterio locCriterio = Criterio.getInstance(this.entityManager, LiquidacionTasa.class)
			.crearAlias("docGeneradorDeuda.obligacion", "locObligacion")
			.add(Restriccion.IGUAL("cuotaLiquidacion", pCuota))
			.add(Restriccion.DISTINTO("locObligacion.estado", Obligacion.Estado.ANULADO))
			.setProyeccion(Proyeccion.PROP("locObligacion.idObligacion"))
			.add(Orden.ASC("locObligacion.idObligacion"));
		
		//Si esta liquidando solo un documento, levantamos las liqudiaciones solo para ese documento
		if (listaDocumentos.size() == 1) {
			Obligacion locObligacion = listaDocumentos.get(0).getObligacion();
			locCriterio.add(Restriccion.IGUAL("locObligacion", locObligacion));
		}
				
		listaIdRegistroDeudaLiquidados = new HashSet(locCriterio.list());
		System.out.println("----------     Se levantaron " + listaIdRegistroDeudaLiquidados.size() + " registros de deuda liquidados.");
	}

	// private boolean isPeriodoTGILiquidado(Obligacion pObligacion,CuotaLiquidacion pCuota){
	// try{
	// Long locCantidad = (Long) Criterio.getInstance(this.entityManager, LiquidacionTasa.class)
	// .add(Restriccion.IGUAL("docGeneradorDeuda.obligacion", pObligacion))
	// .add(Restriccion.IGUAL("cuotaLiquidacion", pCuota))
	// .crearAlias("docGeneradorDeuda.obligacion","locObligacion")
	// .add(Restriccion.DISTINTO("locObligacion.estado", Obligacion.Estado.ANULADO))
	// .add(Restriccion.IGUAL("tipoTasa.periodicidad", pCuota.getCalendario().getPeriodicidad()))
	// .setProyeccion(Proyeccion.COUNT())
	// .uniqueResult();
	//
	// System.out.println("Verificacion isPeriodoTGILiquidado: ||||||||||||||||||"+ ((locCantidad > 0)?"TRUE":"FALSE"));
	//
	// if(locCantidad > 0) {
	// return true;
	// }
	// }catch (Exception e) {
	// e.printStackTrace();
	// }
	// return false;
	// }

	/**
	 * Obtiene el listado de las liquidaciones
	 * 
	 * @param pPeriodo
	 *            periodo de las liquidaciones
	 * @param pTipoTasa
	 *            tipo de tasa de las liquidaciones
	 * @param pPersona
	 *            persona a la que pertenece la persona
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	@Override
	public java.util.List findListaLiquidaciones(CuotaLiquidacion pCuota, com.trascender.habilitaciones.recurso.persistent.TipoTasa pTipoTasa,
			com.trascender.framework.recurso.persistent.Persona pPersona, EstadoRegistroDeuda pEstadoRegistroDeuda) throws Exception {
		try {
			Criterio crit = Criterio.getInstance(this.entityManager, LiquidacionTasa.class).crearAlias("docGenDeuda.obligacion", "locObligacion")
					.add(Restriccion.IGUAL("tipoDeuda", TipoDeuda.LIQUIDACION));
			if(pCuota != null) {
				crit.add(Restriccion.IGUAL("cuotaLiquidacion", pCuota)).add(Restriccion.IGUAL("periodo.periodicidad", pCuota.getPeriodo().getPeriodicidad()));
			}

			if(pTipoTasa != null) {
				crit.crearAlias("locObligacion.documentoEspecializado", "locDocEsp").add(Restriccion.IGUAL("locDocEsp.tipoTasa", pTipoTasa));
			}

			if(pPersona != null) {
				crit.add(Restriccion.IGUAL("locObligacion.persona", pPersona));
			}

			return crit.list();
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * Realiza las liquidaciones de la tasa OSP
	 * 
	 * @param pPeriodo
	 * @param pServicio
	 * @return
	 * @ejb.interface-method view-type = "local"
	 */
	@Override
	@SuppressWarnings({"unchecked"})
	public ResultadoLiquidacion liquidarOSP(ServicioOSP pServicio, Calle pCalle, CuotaLiquidacion[] pCuotas, Persona pPersona, Parcela pParcela, Boolean pIgnorarPlan) throws Exception {

		ResultadoLiquidacion locResultadoLiquidacion = new ResultadoLiquidacion();
			List locListaLiquidacionesRetorno = new ArrayList();
			try {
				System.out.println("Preparando lista de Parámetros Valuados...");
				this.prepararListaParametroValuados();
				System.out.println("Actualizando Estado Cuentas...");
				this.ejecutarProcedimientoActualizacionDeuda(pPersona, pParcela);
				System.out.println("Preparando lista de Vencimientos...");
				this.prepararListaVencimientos();
				System.out.println("Preparando lista de Modificadores...");
				this.prepararListaModificadoresLiquidacion();
				System.out.println("Preparando lista de Exenciones Vigentes...");
				this.prepararListaExencionesRegistrosDeuda();

				this.mostrarFechaYHoraInicioLiquidacion("");

				this.setIgnorarPlan(pIgnorarPlan);

				for(CuotaLiquidacion cadaCuota : pCuotas) {
					List<DocumentoOSP> locListaDocumentos = this.findListaDocumentosOspLiquidables(cadaCuota, pCalle, pServicio, pPersona, pParcela);
					locListaLiquidacionesRetorno.addAll(this.generarLiquidacionOSP(cadaCuota, locListaDocumentos));
				}

				if(!locListaLiquidacionesRetorno.isEmpty()) {
					this.guardarLiquidacionesTasa(locListaLiquidacionesRetorno);
				} else {
					System.out.println("No se han generado liquidaciones OSP.");
				}

				this.mostrarFechaYHoraFinLiquidacion("");
				locResultadoLiquidacion.setCantidadLiquidadas(locListaLiquidacionesRetorno.size());
				return locResultadoLiquidacion;
			} catch(Exception e) {
				e.printStackTrace();
				throw e;
			}
	}
	
	public ResultadoLiquidacion liquidarArrendamiento(CuotaLiquidacion[] pCuotas, Persona pPersona, Parcela pParcela, Boolean pIgnorarPlan) throws Exception {

		ResultadoLiquidacion locResultadoLiquidacion = new ResultadoLiquidacion();
			List locListaLiquidacionesRetorno = new ArrayList();
			try {
				System.out.println("Actualizando Estado Cuentas...");
				this.ejecutarProcedimientoActualizacionDeuda(pPersona, pParcela);
				this.prepararListaExencionesRegistrosDeuda();

				this.mostrarFechaYHoraInicioLiquidacion("");

				this.setIgnorarPlan(pIgnorarPlan);

				for(CuotaLiquidacion cadaCuota : pCuotas) {
					List<DocumentoArrendamiento> locListaDocumentos = this.findListaDocumentosArrendamientoLiquidables(cadaCuota, pPersona, pParcela);
					locListaLiquidacionesRetorno.addAll(this.generarLiquidacionArrendamiento(cadaCuota, locListaDocumentos));
				}

				if(!locListaLiquidacionesRetorno.isEmpty()) {
					this.guardarLiquidacionesTasa(locListaLiquidacionesRetorno);
				} else {
					System.out.println("No se han generado liquidaciones OSP.");
				}

				this.mostrarFechaYHoraFinLiquidacion("");
				locResultadoLiquidacion.setCantidadLiquidadas(locListaLiquidacionesRetorno.size());
				return locResultadoLiquidacion;
			} catch(Exception e) {
				e.printStackTrace();
				throw e;
			}
	}
	
	private List<DocumentoArrendamiento> findListaDocumentosArrendamientoLiquidables(CuotaLiquidacion pCuota, Persona pPersona, Parcela pParcela) throws Exception {

		Criterio locCriteriaObligaciones = Criterio.getInstance(this.entityManager, DocumentoArrendamiento.class)
				.setDistinct(true)
				.add(Restriccion.IGUAL("estado", DocHabilitanteEspecializado.Estado.ACTIVO))
				.add(Restriccion.IGUAL("obligacion.persona", pPersona))
				.add(Restriccion.IGUAL("parcela", pParcela));

		if(pPersona == null) {
			locCriteriaObligaciones.crearFetchAlias("obligacion.persona", "locPersona");
		}

		if(pParcela == null) {
			locCriteriaObligaciones.crearFetchAlias("parcela.listaRegistrosMejora", "mejoras");
			locCriteriaObligaciones.crearFetchAlias("parcela.listaParcelasPorCuadra", "parcelasPorCuadra");
		}

		return locCriteriaObligaciones.list();
	}

	private List<DocumentoOSP> findListaDocumentosOspLiquidables(CuotaLiquidacion pCuota, Calle pCalle, ServicioOSP pServicio, Persona pPersona, Parcela pParcela) throws Exception {

		Criterio locCriteriaObligaciones = Criterio.getInstance(this.entityManager, DocumentoOSP.class).setDistinct(true)

				.add(Restriccion.IGUAL("estado", DocHabilitanteEspecializado.Estado.ACTIVO)).add(Restriccion.IGUAL("obligacion.persona", pPersona))
				.add(Restriccion.IGUAL("parcela", pParcela));

		if(pPersona == null) {
			locCriteriaObligaciones.crearFetchAlias("obligacion.persona", "locPersona");
		}

		if(pParcela == null) {
			locCriteriaObligaciones.crearFetchAlias("parcela.listaRegistrosMejora", "mejoras");
			locCriteriaObligaciones.crearFetchAlias("parcela.listaParcelasPorCuadra", "parcelasPorCuadra");
		}

		if(pServicio != null) {
			locCriteriaObligaciones.crearAlias("listaRegAlicuotas", "cadaRegAlicuota").add(Restriccion.IGUAL("cadaRegAlicuota", pServicio));
		}

		if(pCalle != null) {
			List listaParcelas = this.getListaParcelasFromCalle(pCalle);
			locCriteriaObligaciones.add(Restriccion.EN("parcela", listaParcelas));
		}
		return locCriteriaObligaciones.list();
	}

	/**
	 * Liquida las obligaciones de Tasas Menores
	 * 
	 * @param pPersona
	 * @param pPlantilla
	 * @param pPeriodo
	 * @param pCalle
	 * @return
	 * @throws Exception
	 */
	@Override
	public ResultadoLiquidacion liquidarTasaMenor(Persona pPersona, Parcela pParcela, TipoObligacion pTipoObligacionTipoTasa, CuotaLiquidacion[] pCuotas, Boolean pIgnorarPlan)
			throws Exception {
		ResultadoLiquidacion locResultadoLiquidacion = new ResultadoLiquidacion();
		try {
			if(pCuotas == null) {
				// throw new SaicException(502);
				locResultadoLiquidacion.addMensaje(5);
				return locResultadoLiquidacion;
			}
			if(pTipoObligacionTipoTasa == null) {
				locResultadoLiquidacion.addMensaje(6);
				return locResultadoLiquidacion;
			}

			// while(isThreadTasaMenorActivo){
			// threadLiquidacionTasaMenor.sleep(2000);
			// System.out.println("::::::Se está ejecutando otro proceso de liquidaciones de Tasas Menores. Por favor, espere unos instantes...");
			// }
			//
			// threadLiquidacionTasaMenor = new Thread();
			// threadLiquidacionTasaMenor.run();
			// isThreadTasaMenorActivo= true;

			List<LiquidacionTasa> locListaLiquidacionesRetorno = new ArrayList<LiquidacionTasa>();
			try {
				System.gc();
				System.out.println("Preparando lista de Exenciones Vigentes...");
				this.prepararListaExencionesRegistrosDeuda();

				this.mostrarFechaYHoraInicioLiquidacion("");

				this.setIgnorarPlan(pIgnorarPlan);

				for(CuotaLiquidacion cadaCuota : pCuotas) {
					List<DocumentoTasaMenor> locListaDocumentos = this.findListaDocumentosTasaMenorLiquidables(pPersona, pParcela, pTipoObligacionTipoTasa);
					locListaLiquidacionesRetorno = this.generarLiquidacionTasaMenor(locListaDocumentos, cadaCuota);
				}

				if(!locListaLiquidacionesRetorno.isEmpty()) {
					this.guardarLiquidacionesTasa(locListaLiquidacionesRetorno);
				} else {
					System.out.println("No se han generado liquidaciones de Tasas Menores.");
				}

				this.mostrarFechaYHoraFinLiquidacion("");
				locResultadoLiquidacion.setCantidadLiquidadas(locListaLiquidacionesRetorno.size());
				return locResultadoLiquidacion;
			} catch(Exception e) {
				e.printStackTrace();
				throw e;
			}
		} finally {
			// this.isThreadTasaMenorActivo = false;
			// this.threadLiquidacionTasaMenor.interrupt();
			// this.threadLiquidacionTasaMenor = null;
		}
	}

	private List<LiquidacionTasa> generarLiquidacionTasaMenor(List<DocumentoTasaMenor> pListaDocumentos, CuotaLiquidacion pCuota) {
		// Para controles
		double locTiempoInicio = System.currentTimeMillis();

		CalendarioMunicipal locCalendarioMunicipal = (CalendarioMunicipal) pCuota.getPeriodo().getCalendario();
		TipoTasa locTipoTasa = Criterio.getInstance(this.entityManager, TipoTasa.class).add(Restriccion.IGUAL("estado", TipoTasa.Estado.ACTIVA))
				.add(Restriccion.IGUAL("plan", locCalendarioMunicipal.getPlan())).uniqueResult();

		int procesadas = 0;
		int liquidadas = 0;
		int total = pListaDocumentos.size();

		System.out.println("Tiempo en buscar las obligaciones = " + ((System.currentTimeMillis() - locTiempoInicio) / 1000));
		System.out.println("Cantidad de obligaciones " + total);

		List<LiquidacionTasa> locListaLiquidacionesRetorno = new ArrayList<LiquidacionTasa>();

		pCuota = entityManager.merge(pCuota);

		levantarTasasParaCuota(pCuota, pListaDocumentos);
		levantarLiquidacionesParaCuota(pCuota, pListaDocumentos);

		for(DocumentoTasaMenor cadaDocumento : pListaDocumentos) {
			boolean locEstaLiquidado = this.isPeriodoLiquidado(cadaDocumento.getObligacion(), pCuota);
			if(!locEstaLiquidado) {
				double tiempoInicio = System.currentTimeMillis();
				liquidadas++;
				procesadas++;

				try {
					List<LiquidacionTasa> locListaLiquidacionTasa = this.liquidarObligacion(cadaDocumento.getObligacion(), pCuota, pCuota.getPeriodo().getFechaInicio(), locTipoTasa);
					locListaLiquidacionesRetorno.addAll(locListaLiquidacionTasa);
				} catch(TrascenderException t) {
					liquidadas--;
					System.out.println("          //*/*/*");
					System.out.println("ERROR: " + t.getMessage());
					System.out.println("          //*/*/*");
				} catch(Exception e) {
					liquidadas--;
					System.out.println(":::::: No se ha podido liquidar la obligacion.");
					e.printStackTrace();
					// locListaLiquidacionesRetorno.removeAll(listaLiquidacionesTGIAux);
				}

				double tiempoFinal = System.currentTimeMillis();
				double tiempoTranscurrido = tiempoFinal - tiempoInicio;
				System.out.println("Tiempo por liquidacion = " + (tiempoTranscurrido / 1000) + " seg");

				// listaLiquidacionesTGIAux.clear();
				System.out.println("                  Se han procesado " + procesadas + "/" + total + " obligaciones");
				System.out.println("                  Se han liquidado " + liquidadas + "/" + total + " obligaciones");
			}
		}
		return locListaLiquidacionesRetorno;
	}

	private List<DocumentoTasaMenor> findListaDocumentosTasaMenorLiquidables(Persona pPersona, Parcela pParcela, TipoObligacion pTipoObligacionTasaMenor) throws Exception {
		Criterio locCriterio = Criterio.getInstance(entityManager, DocumentoTasaMenor.class).add(Restriccion.IGUAL("obligacion.persona", pPersona))
				.add(Restriccion.IGUAL("parcela", pParcela)).add(Restriccion.IGUAL("plantillaDocumentoTasaMenor.tipoObligacion", pTipoObligacionTasaMenor))
				.add(Restriccion.IGUAL("estado", DocHabilitanteEspecializado.Estado.ACTIVO));

		List<DocumentoTasaMenor> locListaDocumentos = locCriterio.list();

		return locListaDocumentos;
	}

	/**
	 * Obtiene el tipo de tasa de la obligación OSP
	 * 
	 * @param pObligacion
	 * @param pPeriodo
	 * @return
	 */
	private TipoTasa getTipoTasaOSP(Obligacion pObligacion, Periodo pPeriodo) throws SaicException {
		PeriodoLiquidacion locPeriodo = (PeriodoLiquidacion) pPeriodo;

		Criterio locCriterio = Criterio.getInstance(this.entityManager, TipoTasa.class).add(Restriccion.IGUAL("tipoObligacion.nombre", "OYSP"))
				.add(Restriccion.IGUAL("estado", TipoTasa.Estado.ACTIVA)).add(Restriccion.IGUAL("periodicidad", locPeriodo.getCalendario().getPeriodicidad()));

		List listaTiposTasas = locCriterio.list();

		if(listaTiposTasas.isEmpty()) {
			throw new SaicException(27);
		} else {
			return (TipoTasa) listaTiposTasas.get(0);
		}
	}

	/**
	 * Setea el tipo de tasa para las liquidaciones de OSP
	 * 
	 */
	@SuppressWarnings("unchecked")
	private void setTipoTasaParaLiquidacionOSP(ServicioOSP pServicioOSP, Calle pCalle) throws Exception {
		try {
			List locListaServiciosOSP = Criterio.getInstance(this.entityManager, ServicioOSP.class).add(Orden.ASC("idTipoAlicuota")).list();

			System.out.println("Hay " + locListaServiciosOSP.size() + " servicios registrados.");
			System.out.println("Actualizando documentos sin tasa...");

			TipoTasa locTipoTasa = Criterio.getInstance(this.entityManager, TipoTasa.class).add(Restriccion.IGUAL("periodicidad", pServicioOSP.getPeriodicidad()))
					.add(Restriccion.IGUAL("estado", TipoTasa.Estado.ACTIVA)).add(Restriccion.IGUAL("periodicidadCuotas", pServicioOSP.getPeriodicidad()))
					.add(Restriccion.IGUAL("tipoObligacion.nombre", "OYSP")).uniqueResult();

			if(locTipoTasa == null) {
				return;
			}

			List<Parcela> locListaParcelas = new ArrayList<Parcela>();
			if(pCalle != null) {
				locListaParcelas = this.getListaParcelasFromCalle(pCalle);
			}

			Criterio locCriterio = Criterio.getInstance(this.entityManager, DocumentoOSP.class).add(
					Restriccion.NOT(Restriccion.OR(Restriccion.IGUAL("obligacion.estado", Obligacion.Estado.ANULADO),
							Restriccion.IGUAL("obligacion.estado", Obligacion.Estado.TERMINADO))));

			if(pServicioOSP != null) {
				locCriterio.crearAlias("listaRegAlicuotas", "registroAlicuota");
				locCriterio.add(Restriccion.IGUAL("registroAlicuota.idTipoAlicuota", pServicioOSP.getIdTipoAlicuota()));
			}

			if(!locListaParcelas.isEmpty()) {
				locCriterio.add(Restriccion.EN("parcela", locListaParcelas));
			}

			locCriterio.add(Restriccion.OR(Restriccion.DISTINTO("tipoTasa", locTipoTasa), Restriccion.NULO("tipoTasa")));

			List locListaDocumentosOSP = locCriterio.list();
			System.out.println("SIZE");
			System.out.println(locListaDocumentosOSP.size());

			if(!locListaDocumentosOSP.isEmpty()) {

				if(locTipoTasa != null) {
					for(Object cadaDocumentoOSP : locListaDocumentosOSP) {
						DocumentoOSP locDocumentoOSP = (DocumentoOSP) cadaDocumentoOSP;
						if(locTipoTasa.getEstado().equals(TipoTasa.Estado.ACTIVA)) {
							boolean locCambio = false;
							if(locDocumentoOSP.getTipoTasa() != null && !locDocumentoOSP.getTipoTasa().equals(locTipoTasa)) {
								locDocumentoOSP.setTipoTasa(locTipoTasa);
								locCambio = true;
							} else {
								locDocumentoOSP.setTipoTasa(locTipoTasa);
								locCambio = true;
							}
							if(locCambio) {
								this.entityManager.merge(locDocumentoOSP);
							}
						}
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * Recupera una liquidación por número de identificación
	 * 
	 * @param pId
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	@Override
	public LiquidacionTasa getLiquidacionTasaPorId(long pId) throws Exception {
		LiquidacionTasa locLiquidacionTasa = (LiquidacionTasa) Criterio.getInstance(this.entityManager, LiquidacionTasa.class).add(Restriccion.IGUAL("idRegistroDeuda", pId))
				.uniqueResult();
		if(locLiquidacionTasa == null) {
			return null;
		}

		return this.getLiquidacionTasaCompleta(locLiquidacionTasa);
	}

	@Override
	public LiquidacionTasa getLiquidacionTasaCompleta(LiquidacionTasa pLiquidacionTasa) {

		for(Vencimiento locVencimiento : pLiquidacionTasa.getListaVencimientos()) {
			locVencimiento.toString();
		}

		for(ModificadorLiquidacion locModificadorLiquidacion : pLiquidacionTasa.getListaModificadoresLiquidacion()) {
			locModificadorLiquidacion.toString();
		}

		for(ParametroValuado locParametroValuado : pLiquidacionTasa.getListaParametrosValuados()) {
			locParametroValuado.toString();
		}

		for(AlicuotaLiquidada cadaAlicuotaLiquidada : pLiquidacionTasa.getListaAlicuotasLiquidadas()) {
			cadaAlicuotaLiquidada.toString();
			cadaAlicuotaLiquidada.getListaParametrosValuados().size();
		}

		pLiquidacionTasa.toString();
		pLiquidacionTasa.getDocGeneradorDeuda().toString();
		pLiquidacionTasa.getDocGeneradorDeuda().getObligacion().toString();
		pLiquidacionTasa.getTipoTasa().toString();
		pLiquidacionTasa.getTipoTasa().getListaModificadores().toString();
		pLiquidacionTasa.getTipoTasa().getListaParametros().toString();
		pLiquidacionTasa.getTipoTasa().getListaVencimientos().toString();
		pLiquidacionTasa.getCuotaLiquidacion().toString();
		if(pLiquidacionTasa.getRegistroCancelacion() != null) {
			pLiquidacionTasa.getRegistroCancelacion().toString();
			pLiquidacionTasa.getFechaCancelacion().toString();
		}
		return pLiquidacionTasa;
	}

	private ParametroSistemaString getParametroSistemaString(String pNombre) {
		return Criterio.getInstance(entityManager, ParametroSistemaString.class).add(Restriccion.IGUAL("nombre", pNombre)).setModoDebug(true).uniqueResult();
	}

	@Override
	public FiltroLiquidacionTGI findListaLiquidacionesTGI(FiltroLiquidacionTGI pFiltro) throws Exception {
		entityManager.clear();
		if((pFiltro.getPersona() == null) && (pFiltro.getCuota() == null) && (pFiltro.getParcela() == null)) {
			throw new SaicException(34);
		}

			Criterio crit = Criterio.getInstance(this.entityManager, LiquidacionTasa.class).setModoDebug(true).crearAlias("docGeneradorDeuda.obligacion", "locObligacion")
					.crearAlias("locObligacion.documentoEspecializado", "locDocEsp").add(Restriccion.JPQL("TYPE(locDocEsp) = " + DocumentoTGI.class.getSimpleName()));

			// AGREGO LOS pPARAMETERS
			crit.add(Restriccion.IGUAL("locObligacion.persona", pFiltro.getPersona()));
			crit.add(Restriccion.IGUAL("locDocEsp.parcela", pFiltro.getParcela()));
			crit.add(Restriccion.IGUAL("cuotaLiquidacion.periodo", pFiltro.getPeriodo()));
			crit.add(Restriccion.IGUAL("cuotaLiquidacion.periodo.calendario", pFiltro.getCalendario()));
			crit.add(Restriccion.IGUAL("cuotaLiquidacion.periodo.calendario.anio", pFiltro.getAnio()));
			crit.add(Restriccion.IGUAL("tipoDeuda", pFiltro.getTipoLiquidacion()));

			if(pFiltro.isNoCero()) {
				crit.add(Restriccion.DISTINTO("montoCalculado", 0D));
			}

			crit.setModoDebug(true);

			setRestriccionEstadoLiquidacion(pFiltro, crit);

			pFiltro.procesarYListar(crit);

			for(Object cadaObject : pFiltro.getListaResultados()) {
				LiquidacionTasa locLiquidacionTasa = (LiquidacionTasa) cadaObject;
				locLiquidacionTasa.toString();
				locLiquidacionTasa.getDocGeneradorDeuda().getObligacion().toString();
			}

		return pFiltro;
	}
	
	@Override
	public FiltroLiquidacionArrendamiento findListaLiquidacionesArrendamiento(FiltroLiquidacionArrendamiento pFiltro) throws Exception {
		entityManager.clear();
		if((pFiltro.getPersona() == null) && (pFiltro.getCuota() == null) && (pFiltro.getParcela() == null)) {
			throw new SaicException(34);
		}

			Criterio crit = Criterio.getInstance(this.entityManager, LiquidacionTasa.class)
					.setModoDebug(true).crearAlias("docGeneradorDeuda.obligacion", "locObligacion")
					.crearAlias("locObligacion.documentoEspecializado", "locDocEsp")
					.add(Restriccion.TYPE("locDocEsp", DocumentoArrendamiento.class));

			// AGREGO LOS pPARAMETERS
			crit.add(Restriccion.IGUAL("locObligacion.persona", pFiltro.getPersona()));
			crit.add(Restriccion.IGUAL("locDocEsp.parcela", pFiltro.getParcela()));
			crit.add(Restriccion.IGUAL("cuotaLiquidacion.periodo", pFiltro.getPeriodo()));
			crit.add(Restriccion.IGUAL("cuotaLiquidacion.periodo.calendario", pFiltro.getCalendario()));
			crit.add(Restriccion.IGUAL("cuotaLiquidacion.periodo.calendario.anio", pFiltro.getAnio()));
			crit.add(Restriccion.IGUAL("tipoDeuda", pFiltro.getTipoLiquidacion()));

			if(pFiltro.isNoCero()) {
				crit.add(Restriccion.DISTINTO("montoCalculado", 0D));
			}

			crit.setModoDebug(true);

			setRestriccionEstadoLiquidacion(pFiltro, crit);

			pFiltro.procesarYListar(crit);

			for(Object cadaObject : pFiltro.getListaResultados()) {
				LiquidacionTasa locLiquidacionTasa = (LiquidacionTasa) cadaObject;
				locLiquidacionTasa.toString();
				locLiquidacionTasa.getDocGeneradorDeuda().getObligacion().toString();
			}

		return pFiltro;
	}

	private FiltroLiquidacionTasa quitarNoCerosLiquidacionesTasa(FiltroLiquidacionTasa pFiltro) {
		for(Iterator<LiquidacionTasa> iterator = pFiltro.getListaResultados().iterator(); iterator.hasNext();) {
			LiquidacionTasa cadaLiquidacionTasa = iterator.next();
			// Quitamos no ceros
			if(pFiltro.isNoCero()) {
				if(cadaLiquidacionTasa.getMontoCalculado().equals(0D)) {
					iterator.remove();
					continue;
				}
			}
		}

		return pFiltro;
	}

	private CuotaLiquidacion getCuotaAnterior(CuotaLiquidacion pCuota) throws Exception {
		if(pCuota.getNumero() == 1 && pCuota.getPeriodo().getNumero() == 1) {
			return null;
		}

		boolean isCutaPeriodoAnterior = false;
		if(pCuota.getNumero() == 1 && pCuota.getPeriodo().getNumero() > 1) {
			isCutaPeriodoAnterior = true;
		}

		if(isCutaPeriodoAnterior) {
			// todo buscar la ultima cuota del primer periodo y retornar esa
			// return
		} else {
			return ((PeriodoLiquidacion) pCuota.getPeriodo().getCalendario().findPeriodo(null, pCuota.getPeriodo().getNumero())).getCuotaLiquidacion((pCuota.getNumero() - 1));
		}
		return null;
	}

	private List<Long> getListaSinReliquidacionesTGIVencidas(Periodo pPeriodo, List<Long> pListaIdObligacionesIgualFormaPago) {
		try {
			// Recupera los id de las ReliquidacionesTGI no canceladas para ese periodo
			List<Long> locListaIdsReliquidacionesTGI = Criterio.getInstance(this.entityManager, LiquidacionTasa.class).crearAlias("docGeneradorDeuda.obligacion", "locObligacion")
					.setProyeccion(Proyeccion.PROP("locObligacion.idObligacion")).setDistinct(true).crearAlias("locObligacion.documentoEspecializado", "locDocEsp")
					.add(Restriccion.NULO("registroCancelacion")).add(Restriccion.DISTINTO("locObligacion.estado", Obligacion.Estado.ANULADO))
					.add(Restriccion.IGUAL("tipoDeuda", RegistroDeuda.TipoDeuda.RELIQUIDACION)).add(Restriccion.IGUAL("estado", RegistroDeuda.EstadoRegistroDeuda.VENCIDA))
					.add(Restriccion.IGUAL("numeroCuota", 1)).add(Restriccion.JPQL("TYPE(locDocEsp) = " + DocumentoTGI.class.getSimpleName()))
					.add(Restriccion.IGUAL("periodo", pPeriodo)).list();

			for(Long cadaId : locListaIdsReliquidacionesTGI) {
				if(pListaIdObligacionesIgualFormaPago.contains(cadaId)) {
					pListaIdObligacionesIgualFormaPago.remove(cadaId);
				}
			}
			return pListaIdObligacionesIgualFormaPago;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Crea un nuevo registro de cancelación del tipo Condonacion
	 * 
	 * @param pRegistroDeuda
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	@Override
	public Condonacion condonarDeuda(RegistroDeuda pRegistroDeuda, String pCausa) throws Exception {
		if(pRegistroDeuda == null) {
			throw new SaicException(23);
		}

		try {
			if(pRegistroDeuda.getRegistroCancelacion() != null) {
				throw new SaicException(24);
			}

			Condonacion locCondonacion = new Condonacion();

			locCondonacion.setCausa(pCausa);
			locCondonacion.setFechaCancelacion(Calendar.getInstance().getTime());
			pRegistroDeuda.setRegistroCancelacion(locCondonacion);

			this.entityManager.persist(locCondonacion);
			this.entityManager.flush();
			this.entityManager.refresh(locCondonacion);
			Long locIdCondonacion = locCondonacion.getIdRegistroCancelacion();
			this.entityManager.merge(pRegistroDeuda);
			locCondonacion.setIdRegistroCancelacion(locIdCondonacion);
			return locCondonacion;
		} catch(Exception e) {
			throw e;
		}
	}

	@Override
	public FiltroLiquidacionTasaMenor findListaLiquidacionesTasaMenor(FiltroLiquidacionTasaMenor pFiltro) {

		ParametroSistemaString locParametroOmitirCeros = this.getParametroSistemaString("OMITIR_CEROS");
		if(locParametroOmitirCeros != null && locParametroOmitirCeros.getCadena().equals("SI")) {
			pFiltro.setNoCero(true);
		}

		Criterio locCriterio = Criterio.getInstance(this.entityManager, LiquidacionTasa.class).crearAlias("docGeneradorDeuda.obligacion", "locObligacion")
				.crearAlias("locObligacion.documentoEspecializado", "locDocEsp").add(Restriccion.JPQL("TYPE(locDocEsp) = DocumentoTasaMenor"));

		if(pFiltro.getParcela() != null) {
			locCriterio.crearAlias("locDocEsp.parcela", "locParcela");
			locCriterio.add(Restriccion.IGUAL("locParcela", pFiltro.getParcela()));
		}

		if(pFiltro.getPersona() != null) {
			locCriterio.add(Restriccion.IGUAL("locObligacion.persona", pFiltro.getPersona()));
		}
		if(pFiltro.getCuota() != null) {
			locCriterio.add(Restriccion.IGUAL("cuotaLiquidacion", pFiltro.getCuota()));
		}
		if(pFiltro.getPeriodo() != null) {
			locCriterio.add(Restriccion.IGUAL("cuotaLiquidacion.periodo", pFiltro.getPeriodo()));
		}
		if(pFiltro.getCalendario() != null) {
			locCriterio.add(Restriccion.IGUAL("cuotaLiquidacion.periodo.calendario", pFiltro.getCalendario()));
		}
		if(pFiltro.getAnio() != null) {
			locCriterio.add(Restriccion.IGUAL("cuotaLiquidacion.periodo.calendario.anio", pFiltro.getAnio()));
		}
		if(pFiltro.getTipoObligacionTasaMenor() != null) {
			locCriterio.add(Restriccion.IGUAL("tipoTasa.plan.tipoObligacion", pFiltro.getTipoObligacionTasaMenor()));
		}

		setRestriccionEstadoLiquidacion(pFiltro, locCriterio);

		pFiltro.procesarYListar(locCriterio);

		pFiltro = (FiltroLiquidacionTasaMenor) this.quitarNoCerosLiquidacionesTasa(pFiltro);

		for(LiquidacionTasa cadaLiquidacionTasa : pFiltro.getListaResultados()) {
			cadaLiquidacionTasa.toString();
			cadaLiquidacionTasa.getListaModificadoresLiquidacion().toString();
			cadaLiquidacionTasa.getMonto();
			cadaLiquidacionTasa.getCuotaLiquidada().toString();
			for(ModificadorLiquidacion cadaModificadorLiquidacion : cadaLiquidacionTasa.getListaModificadoresLiquidacion()) {
				cadaModificadorLiquidacion.toString();
			}

			cadaLiquidacionTasa.getListaParametrosValuados().toString();
			for(ParametroValuado cadaParametroValuado : cadaLiquidacionTasa.getListaParametrosValuados()) {
				cadaParametroValuado.toString();
			}

			cadaLiquidacionTasa.getListaVencimientos().toString();
			for(Vencimiento cadaVencimiento : cadaLiquidacionTasa.getListaVencimientos()) {
				cadaVencimiento.toString();
			}
			cadaLiquidacionTasa.getDocGeneradorDeuda().toString();
			cadaLiquidacionTasa.getDocGeneradorDeuda().getObligacion().toString();
			cadaLiquidacionTasa.getDocGeneradorDeuda().getObligacion().getPersona().toString();
		}

		return pFiltro;
	}

	/**
	 * Recupera el listado de liquidaciones de la osp
	 * 
	 * @param pServicioOSP
	 * @param pCalle
	 * @param pPeriodo
	 * @param pPersona
	 *            persona a la que pertenece la obligación
	 * @param pCuadra
	 *            cuadra a la que pertenece la obligacion
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	@Override
	@SuppressWarnings("unchecked")
	public FiltroLiquidacionOSP findListaLiquidacionesOSP(FiltroLiquidacionOSP pFiltro) throws Exception {
		if((pFiltro.getCalle() != null) && (pFiltro.getCuadra() != null)) {
			this.entityManager.refresh(pFiltro.getCalle());
			if(!pFiltro.getCalle().getListaCuadras().contains(pFiltro.getCuadra())) {
				throw new SaicException(31);
			}
		}

		if(pFiltro.getServicioOSP() != null) {
			if(pFiltro.getServicioMedido() != null) {
				if(!pFiltro.getServicioMedido().equals(pFiltro.getServicioOSP().isMedido())) {
					throw new SaicException(32);
				}
			}
		} else if(pFiltro.getPersona() == null && pFiltro.getCuota() == null) {
			throw new SaicException(33);
		}

		Criterio locCriterio = Criterio.getInstance(this.entityManager, LiquidacionTasa.class).crearAlias("docGeneradorDeuda.obligacion", "locObligacion")
				.crearAlias("locObligacion.documentoEspecializado", "locDocEsp").crearAlias("locDocEsp.parcela", "locParcela").add(Restriccion.JPQL("TYPE(locDocEsp) = 'OSP'"));

		// AGREGO LOS pPARAMETERS
		locCriterio.add(Restriccion.IGUAL("locObligacion.persona", pFiltro.getPersona())).add(Restriccion.IGUAL("locParcela", pFiltro.getParcela()));

		locCriterio.setModoDebug(true);

		if((pFiltro.getCuadra() != null) || (pFiltro.getCalle() != null)) {
			List listaIdsParcelas = null;
			if(pFiltro.getCuadra() != null) {
				Cuadra locCuadra = this.businessRegistroGeograficoLocal.getCuadraPorId(pFiltro.getCuadra().getIdCuadra());
				listaIdsParcelas = new ArrayList();
				for(ParcelaPorCuadra locParcelaPorCuadra : locCuadra.getListaParcelasPorCuadra()) {
					listaIdsParcelas.add(locParcelaPorCuadra.getParcela());
				}
			} else if(pFiltro.getCalle() != null) {
				listaIdsParcelas = Criterio.getInstance(this.entityManager, Parcela.class).crearAlias("domicilioParcelario.relacionCalle", "locRelaCalle")
						.add(Restriccion.IGUAL("locRelaCalle.idAbstractCalle", pFiltro.getCalle().getIdCalle())).detachedList();
			}
			locCriterio.add(Restriccion.EN("locDocEsp.parcela", listaIdsParcelas));
		}

		if(pFiltro.getServicioOSP() != null) {
			locCriterio.crearAlias("locDocEsp.listaAsocRegAlicuota.registroAlicuota", "cadaRegAlicuota").add(Restriccion.IGUAL("cadaRegAlicuota", pFiltro.getServicioOSP()));
		} else if(pFiltro.getServicioMedido() != null) {
			locCriterio.crearAlias("locDocEsp.listaAsocRegAlicuota.registroAlicuota", "cadaRegAlicuota").add(
					Restriccion.JPQL("cadaRegAlicuota SOME(SELECT s FROM ServicioOSP s WHERE s.medido = " + pFiltro.getServicioMedido().booleanValue() + ")"));
		}

		locCriterio.add(Restriccion.IGUAL("cuotaLiquidacion", pFiltro.getCuota())).add(Restriccion.IGUAL("cuotaLiquidacion.periodo", pFiltro.getPeriodo()))
				.add(Restriccion.IGUAL("cuotaLiquidacion.periodo.calendario", pFiltro.getCalendario()))
				.add(Restriccion.IGUAL("cuotaLiquidacion.periodo.calendario.anio", pFiltro.getAnio())).add(Restriccion.IGUAL("tipoDeuda", pFiltro.getTipoLiquidacion()));

		setRestriccionEstadoLiquidacion(pFiltro, locCriterio);

		if(pFiltro.isNoCero()) {
			locCriterio.add(Restriccion.DISTINTO("montoCalculado", 0D));
		}

		pFiltro.procesarYListar(locCriterio);

		for(LiquidacionTasa cadaLiquidacionTasa : pFiltro.getListaResultados()) {
			cadaLiquidacionTasa.toString();
			cadaLiquidacionTasa.getDocGeneradorDeuda().toString();
			cadaLiquidacionTasa.getDocGeneradorDeuda().getObligacion().toString();
			cadaLiquidacionTasa.getDocGeneradorDeuda().getObligacion().getPersona().toString();
			cadaLiquidacionTasa.getDocGeneradorDeuda().getObligacion().getDocumentoEspecializado().getDomicilio().toString();
		}

		entityManager.clear();

		return pFiltro;
	}

	private List<LiquidacionTasa> ordenarListaLiquidaciones(List<LiquidacionTasa> pListado) {

		LiquidacionTasa[] locListado = new LiquidacionTasa[pListado.size()];
		pListado.toArray(locListado);

		java.util.Arrays.sort(locListado, new Comparator<LiquidacionTasa>() {
			@Override
			public int compare(LiquidacionTasa o1, LiquidacionTasa o2) {
				int resultado = o1.getDocGeneradorDeuda().getObligacion().getDocumentoEspecializado().getDomicilio().getCalle().trim().toUpperCase()
						.compareTo(o2.getDocGeneradorDeuda().getObligacion().getDocumentoEspecializado().getDomicilio().getCalle().trim().toUpperCase());
				int resultadoCodPostal = o1.getDocGeneradorDeuda().getObligacion().getDocumentoEspecializado().getDomicilio().getLocalidad().getCodigoPostal()
						.compareTo(o2.getDocGeneradorDeuda().getObligacion().getDocumentoEspecializado().getDomicilio().getLocalidad().getCodigoPostal());
				if(resultado == 0) {
					try {
						Integer locNumeroO1 = Integer.valueOf(o1.getDocGeneradorDeuda().getObligacion().getDocumentoEspecializado().getDomicilio().getNumero().trim().toUpperCase());
						Integer locNumeroO2 = Integer.valueOf(o2.getDocGeneradorDeuda().getObligacion().getDocumentoEspecializado().getDomicilio().getNumero().trim().toUpperCase());
						resultado = locNumeroO1.compareTo(locNumeroO2);
					} catch(Exception e) {
						// Significa q no pudo castear un numero a Integer
						resultado = o1.getDocGeneradorDeuda().getObligacion().getDocumentoEspecializado().getDomicilio().getNumero().trim().toUpperCase()
								.compareTo(o2.getDocGeneradorDeuda().getObligacion().getDocumentoEspecializado().getDomicilio().getNumero().trim().toUpperCase());
					}
				}
				return resultadoCodPostal + resultado;
			}
		});

		pListado = new ArrayList<LiquidacionTasa>();
		for(LiquidacionTasa cadaLiquidacionTasa : locListado) {
			pListado.add(cadaLiquidacionTasa);
		}

		// System.gc();
		return pListado;

	}

	/**
	 * Liquida todas las obliga ciones de SHPS cuyos registros de alícuota sean fijos
	 * 
	 * @param pPeriodo
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	@Override
	@SuppressWarnings({"unchecked", "static-access"})
	public ResultadoLiquidacion liquidarSHPS(Persona pPersona, CuotaLiquidacion[] pCuota, FiltroObligacionSHPS pFiltro, Boolean pIgnorarPlan) throws Exception {
		List locListaLiquidacionesRetorno = new ArrayList();

		ResultadoLiquidacion locResultadoLiquidacion = new ResultadoLiquidacion();
		try {
			// while(isThreadSHPSActivo){
			// threadLiquidacionSHPS.sleep(2000);
			// System.out.println("::::::Se está ejecutando otro proceso de liquidaciones SHPS. Por favor, espere unos instantes...");
			// }
			//
			// threadLiquidacionSHPS = new Thread();
			// threadLiquidacionSHPS.run();
			// isThreadSHPSActivo = true;

			if(pCuota == null) {
				locResultadoLiquidacion.addMensaje(5);
				return locResultadoLiquidacion;
			}

			try {
				System.out.println("Preparando lista de Parámetros Valuados...");
				this.prepararListaParametroValuados();

				System.out.println("Preparando lista de Vencimientos...");
				this.prepararListaVencimientos();
				System.out.println("Preparando lista de Modificadores...");
				this.prepararListaModificadoresLiquidacion();
				System.out.println("Preparando lista de Exenciones Vigentes...");
				this.prepararListaExencionesRegistrosDeuda();
				System.out.println("Preparando lista de Alicuotas liquidadas");
				this.prepararListaAlicuotasLiquidadas();

				this.mostrarFechaYHoraInicioLiquidacion("");

				this.setIgnorarPlan(pIgnorarPlan);

				for(CuotaLiquidacion cadaCuota : pCuota) {
					CalendarioMunicipal locCalendario = (CalendarioMunicipal) 
							cadaCuota.getPeriodo().getCalendario();
					TipoTasa locTipoTasa = Criterio.getInstance(this.entityManager, TipoTasa.class)
							.add(Restriccion.IGUAL("estado", TipoTasa.Estado.ACTIVA))
							.add(Restriccion.IGUAL("plan", locCalendario.getPlan()))
							.uniqueResult();
					
					
					if (locTipoTasa.tieneParametrosDeduda()) {
						System.out.println("Actualizando Estado Cuentas...");
						this.ejecutarProcedimientoActualizacionDeuda(pPersona, null);
					}
					
					locListaLiquidacionesRetorno.addAll(this.generarLiquidacionesSHPS(pPersona, cadaCuota, locTipoTasa, pFiltro));
				}

				if(!locListaLiquidacionesRetorno.isEmpty()) {
					this.guardarLiquidacionesTasa(locListaLiquidacionesRetorno);
				}
				this.mostrarFechaYHoraFinLiquidacion("");

				locResultadoLiquidacion.setCantidadLiquidadas(locListaLiquidacionesRetorno.size());
				return locResultadoLiquidacion;
			} catch(Exception e) {
				e.printStackTrace();
				throw e;
			}
		} finally {
			// threadLiquidacionSHPS.interrupt();
			// threadLiquidacionSHPS = null;
			// isThreadSHPSActivo = false;
		}
	}

	private void prepararListaAlicuotasLiquidadas() {
		List<AlicuotaLiquidada> locListaAlicuotasLiquidadas = Criterio.getInstance(this.entityManager, AlicuotaLiquidada.class).list();

		this.listaAlicuotasLiquidadas = new LinkedHashMap<Integer, AlicuotaLiquidada>();
		for(AlicuotaLiquidada cadaAlicuotaLiquidada : locListaAlicuotasLiquidadas) {
			// if (!this.listaAlicuotasLiquidadas.containsKey(cadaAlicuotaLiquidada.hashCode())) {
			this.listaAlicuotasLiquidadas.put(cadaAlicuotaLiquidada.hashCode(), cadaAlicuotaLiquidada);
			// }
		}
	}

	private java.util.List<LiquidacionTasa> generarLiquidacionesSHPS(Persona pPersona, CuotaLiquidacion pCuota, TipoTasa pTipoTasa, FiltroObligacionSHPS pFiltro) throws Exception {

		// boolean isAlgunParametro=false;
		double locTiempoInicio = System.currentTimeMillis();

		List<DocumentoSHPS> locListaDocSHPS;

		Criterio crit = Criterio.getInstance(this.entityManager, DocumentoSHPS.class)
				.setDistinct(true)
				.add(Restriccion.IGUAL("estado", DocHabilitanteEspecializado.Estado.ACTIVO))
				.add(Restriccion.IGUAL("numeroInscripcion", pFiltro.getNumeroInscripcion()));

		if (pPersona == null) {
			crit.crearFetchAlias("obligacion.persona", "locPersona");
		}
		crit.add(Restriccion.IGUAL("obligacion.persona", pPersona));

		AtributoDinamico.addRestriccionesCriterio(crit, DocumentoSHPS.serialVersionUID, "idDocHabilitanteEspecializado", pFiltro.getListaAtributosDinamicos());

		locListaDocSHPS = crit.list();

		int total = locListaDocSHPS.size();

		System.out.println("tiempo en buscar las obligaciones = " + ((System.currentTimeMillis() - locTiempoInicio) / 1000));

		int procesadas = 0;
		int liquidadas = 0;

		List<LiquidacionTasa> locListaLiquidacionesRetorno = new ArrayList<LiquidacionTasa>();

		pCuota = entityManager.merge(pCuota);

		levantarTasasParaCuota(pCuota, locListaDocSHPS);
		levantarLiquidacionesParaCuota(pCuota, locListaDocSHPS);

		for(DocumentoSHPS cadaDocumento : locListaDocSHPS) {
			Obligacion locObligacion = cadaDocumento.getObligacion();
			boolean liquido = true;
//			if(cadaDocumento.getFechaCeseActividad() != null) {
//				if(cadaDocumento.getFechaCeseActividad().before(pCuota.getPeriodo().getFechaInicio().getTime())) {
//					liquido = false;
//				}
//			}

			procesadas++;

			if(liquido) {
				liquidadas++;
				if(pTipoTasa != null) {
					// if (!this.isPeriodoLiquidado(locObligacion,pCuota)){
					try {
						locListaLiquidacionesRetorno.addAll(this.liquidarObligacion(locObligacion, pCuota, Calendar.getInstance(), pTipoTasa));

						// locListaLiquidacionesRetorno.addAll(locListaLiquidacionesTasas);
						// for(LiquidacionTasa cadaLiquidacionTasa : locListaLiquidacionesTasas) {
						// // actualizo el estado de las declaraciones juradas a liquidadas
						// DocHabilitanteEspecializado locDocumentoEspecializado =
						// cadaLiquidacionTasa.getDocGeneradorDeuda().getObligacion().getDocumentoEspecializado();
						// for(RegistroValuado cadaRegistroValuado : locDocumentoEspecializado.getRegistroValuado(pCuota)) {
						// cadaRegistroValuado.setEstado(RegistroValuado.Estado.LIQUIDADO);
						// this.entityManager.merge(cadaRegistroValuado);
						// }
						// }
					} catch(TrascenderException t) {
						liquidadas--;
						System.out.println("          //*/*/*");
						System.out.println("ERROR: " + t.getMessage());
						System.out.println("          //*/*/*");
					} catch(Exception e) {
						liquidadas--;
						System.out.println(":::::: No se ha podido liquidar la obligacion.");
						e.printStackTrace();
						// locListaLiquidacionesRetorno.removeAll(listaLiquidacionesTGIAux);
					}

					double tiempoFinal = System.currentTimeMillis();
					double tiempoTranscurrido = tiempoFinal - tiempoInicio;
					System.out.println("Tiempo por liquidacion = " + (tiempoTranscurrido / 1000) + " seg");

					// listaLiquidacionesTGIAux.clear();
					System.out.println("                  Se han procesado " + procesadas + "/" + total + " obligaciones");
					System.out.println("                  Se han liquidado " + liquidadas + "/" + total + " obligaciones");

					// }
					// else{
					// System.out.println("::::::::No se pudo realizar la operación. La obligacion SHPS "+locObligacion.getIdObligacion() +
					// " ya ha sido liquidada.\n");
					// }
				} else {
					System.out.println(":::El Período que intenta liquidar no se corresponde con el de la fórmula asociada a la obligación.");
				}
			}
		}
		return locListaLiquidacionesRetorno;
	}

	/**
	 * Prepara el tipo de tasa para el documento SHPS asociado
	 * 
	 * @param pDeclaracionJuradaSHPS
	 * @param pRubro
	 * @param pPersona
	 * @param pPeriodo
	 * @param locDocumentoSHPS
	 *            documento cuyo tipo de tasa ha de ser seteado
	 */
	private void setTipoTasaParaLiquidacionSHPS(Persona pPersona, CuotaLiquidacion pCuota, FiltroObligacionSHPS pFiltroObligacion) {

		CalendarioMunicipal locCalendario = (CalendarioMunicipal) pCuota.getPeriodo().getCalendario();

		List<TipoTasa> listaTiposTasasSHPS = Criterio.getInstance(this.entityManager, TipoTasa.class).add(Restriccion.IGUAL("tipoObligacion.nombre", "SHPS"))
				.add(Restriccion.IGUAL("estado", TipoTasa.Estado.ACTIVA)).add(Restriccion.IGUAL("plan", locCalendario.getPlan())).list();

		try {
			for(TipoTasa cadaTipoTasa : listaTiposTasasSHPS) {
				List<DocumentoSHPS> locListaDocumentosSHPS = new ArrayList<DocumentoSHPS>();
				Criterio locCriterio = Criterio.getInstance(this.entityManager, DocumentoSHPS.class).add(Restriccion.IGUAL("estado", DocHabilitanteEspecializado.Estado.ACTIVO));

				AtributoDinamico.addRestriccionesCriterio(locCriterio, DocumentoSHPS.serialVersionUID, "idDocHabilitanteEspecializado", pFiltroObligacion.getListaAtributosDinamicos());

				locCriterio.crearAlias("listaRegAlicuotas", "cadaRegAlicuota")
				// .add(Restriccion.IGUAL("cadaRegAlicuota.periodicidad",cadaTipoTasa.getPeriodicidad()))
						.add(Restriccion.IGUAL("obligacion.persona", pPersona)).setModoDebug(true);
				locListaDocumentosSHPS = locCriterio.list();
				// }

				for(DocumentoSHPS cadaDocumentoSHPS : locListaDocumentosSHPS) {
					this.setTipoTasaSHPS(cadaDocumentoSHPS, cadaTipoTasa);
				}
			}
			this.entityManager.flush();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void setTipoTasaSHPS(DocumentoSHPS locDocumentoSHPS, TipoTasa locTipoTasa) {
		if((locDocumentoSHPS != null) && (locTipoTasa != null)) {
			if(locTipoTasa.getEstado().equals(TipoTasa.Estado.ACTIVA)) {
				boolean locCambio = false;
				if(locDocumentoSHPS.getTipoTasa() != null && !locDocumentoSHPS.getTipoTasa().equals(locTipoTasa)) {
					locDocumentoSHPS.setTipoTasa(locTipoTasa);
					locCambio = true;
				} else {
					locDocumentoSHPS.setTipoTasa(locTipoTasa);
					locCambio = true;
				}

				if(locCambio) {
					this.entityManager.merge(locDocumentoSHPS);
				}
			}
		}
	}

	/**
	 * 
	 * @param pId
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	@Override
	public RegistroDeuda getRegistroDeudaPorId(Long pId) throws Exception {
		if(pId == null) {
			throw new SaicException(51);
		}

		LiquidacionTasa locLiquidacionTasa = this.getLiquidacionTasaPorId(pId);
		if(locLiquidacionTasa == null) {
			try {
				RegistroDeuda locRegistroDeuda = (RegistroDeuda) Criterio.getInstance(this.entityManager, RegistroDeuda.class).add(Restriccion.IGUAL("idRegistroDeuda", pId))
						.uniqueResult();

				if(locRegistroDeuda != null) {
					locRegistroDeuda.toString();
					locRegistroDeuda.getDocGeneradorDeuda().toString();
					locRegistroDeuda.getDocGeneradorDeuda().getObligacion().toString();
					if(locRegistroDeuda.getRegistroCancelacion() != null) {
						locRegistroDeuda.getRegistroCancelacion().toString();
						locRegistroDeuda.getFechaCancelacion().toString();
					}
				} else {
					throw new SaicException(50);
				}
				return locRegistroDeuda;
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return locLiquidacionTasa;
	}

	/**
	 * 
	 * @param pPersona
	 * @param pObra
	 * @param pCalle
	 * @param pPeriodo
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	@Override
	@SuppressWarnings({"unchecked", "static-access"})
	public ResultadoLiquidacion liquidarPFO(com.trascender.framework.recurso.persistent.Persona pPersona, com.trascender.habilitaciones.recurso.persistent.pfo.Obra pObra,
			com.trascender.catastro.recurso.persistent.Calle pCalle, CuotaLiquidacion[] pCuotas) throws Exception {
		List locListaLiquidacionesRetorno = new ArrayList();

		ResultadoLiquidacion locResultadoLiquidacion = new ResultadoLiquidacion();

		try {
			// while(isThreadPFOActivo){
			// threadLiquidacionPFO.sleep(2000);
			// System.out.println("::::::Se está ejecutando otro proceso de liquidaciones PFO. Por favor, espere unos instantes...");
			// }
			//
			// threadLiquidacionPFO = new Thread();
			// threadLiquidacionPFO.run();
			// isThreadPFOActivo = true;

			try {
				if(pCuotas == null || pCuotas.length < 1) {
					locResultadoLiquidacion.addMensaje(5);
					return locResultadoLiquidacion;
				}

				System.out.println("Preparando lista de Parámetros Valuados...");
				this.prepararListaParametroValuados();
				System.out.println("Actualizando Estado Cuentas...");
				this.ejecutarProcedimientoActualizacionDeuda(pPersona, null);
				System.out.println("Preparando lista de Vencimientos...");
				this.prepararListaVencimientos();
				System.out.println("Preparando lista de Modificadores...");
				this.prepararListaModificadoresLiquidacion();
				System.out.println("Preparando lista de Exenciones Vigentes...");
				this.prepararListaExencionesRegistrosDeuda();

				this.mostrarFechaYHoraInicioLiquidacion("");

				for(CuotaLiquidacion cadaCuota : pCuotas) {
					locListaLiquidacionesRetorno = this.generarLiquidacionPFO(cadaCuota, pObra, pCalle, pPersona);

					if(!locListaLiquidacionesRetorno.isEmpty()) {
						this.guardarLiquidacionesTasa(locListaLiquidacionesRetorno);
					}
				}

				this.mostrarFechaYHoraFinLiquidacion("");

				locResultadoLiquidacion.setCantidadLiquidadas(locListaLiquidacionesRetorno.size());
				return locResultadoLiquidacion;
			} catch(Exception e) {
				e.printStackTrace();
				throw e;
			}
		} finally {
			// this.isThreadPFOActivo = false;
			// this.threadLiquidacionPFO.interrupt();
			// this.threadLiquidacionPFO = null;
		}
	}

	/**
	 * Recupera el tipo de tasa para la obligación de pfo
	 * 
	 * @param locObligacion
	 * @param pPeriodo
	 * @return
	 */
	private TipoTasa getTipoTasaPFO(Obligacion locObligacion, Periodo pPeriodo) throws Exception {
		DocumentoPlanObra locDocumentoPFO = (DocumentoPlanObra) locObligacion.getDocumentoEspecializado();
		PeriodoLiquidacion locPeriodo = (PeriodoLiquidacion) pPeriodo;
		Criterio locCriterio = Criterio.getInstance(this.entityManager, TipoTasa.class).add(Restriccion.IGUAL("tipoObligacion.nombre", "PLAN_FINANCIACION_OBRA"))
				.add(Restriccion.IGUAL("periodicidad", locPeriodo.getCalendario().getPeriodicidad()))
				.add(Restriccion.IGUAL("periodicidadCuotas", locDocumentoPFO.getPlanCuentaObra().getPeriodicidad())).add(Restriccion.IGUAL("estado", TipoTasa.Estado.ACTIVA));

		List locListadoTiposTasas = locCriterio.list();

		TipoTasa locTipoTasa = null;
		if(locListadoTiposTasas.size() >= 1) {
			locTipoTasa = (TipoTasa) locListadoTiposTasas.get(0);
		} else {
			throw new SaicException(28);
		}
		return locTipoTasa;
	}

	/**
	 * 
	 * @param pPersona
	 * @param pObra
	 * @param pCuadra
	 * @param pCalle
	 * @param pPeriodo
	 * @return
	 * @ejb.interface-method view-type = "local"
	 */
	@Override
	@SuppressWarnings("unchecked")
	public FiltroLiquidacionPFO findListaLiquidacionesPFO(FiltroLiquidacionPFO pFiltro) throws Exception {
		if(pFiltro.getCuota() == null) {
			if(pFiltro.getPersona() == null && pFiltro.getObra() == null) {
				throw new SaicException(35);
			}
		}

		if((pFiltro.getCuadra() != null) && (pFiltro.getCuadra() != null)) {
			if(!pFiltro.getCalle().getListaCuadras().contains(pFiltro.getCuadra())) {
				throw new SaicException(36);
			}
		}

		ParametroSistemaString locParametroOmitirCeros = this.getParametroSistemaString("OMITIR_CEROS");
		if(locParametroOmitirCeros != null && locParametroOmitirCeros.getCadena().equals("SI")) {
			pFiltro.setNoCero(true);
		}

		Criterio crit = Criterio.getInstance(this.entityManager, LiquidacionTasa.class).crearAlias("docGeneradorDeuda.obligacion.documentoEspecializado", "locDocEspecializado")
				.add(Restriccion.IGUAL("locDocEspecializado.class", "PO"));

		// Atributos principales de búsqueda (periodo, persona y obra)
		crit.add(Restriccion.IGUAL("docGeneradorDeuda.obligacion.persona", pFiltro.getPersona()));

		if(pFiltro.getCuota() != null) {
			crit.add(Restriccion.IGUAL("cuotaLiquidacion", pFiltro.getCuota()));
		}

		if(pFiltro.getPeriodo() != null) {
			crit.add(Restriccion.IGUAL("cuotaLiquidacion.periodo", pFiltro.getPeriodo()));
		}

		if(pFiltro.getCalendario() != null) {
			crit.add(Restriccion.IGUAL("cuotaLiquidacion.periodo.calendario", pFiltro.getCalendario()));
		}

		if((pFiltro.getObra() != null) || (pFiltro.getCalle() != null) || (pFiltro.getCuadra() != null)) {
			Criterio locSubConsulta = Criterio.getInstance(this.entityManager, DocumentoPlanObra.class).setProyeccion(Proyeccion.PROP("idDocHabilitanteEspecializado"));

			locSubConsulta.add(Restriccion.IGUAL("obra", pFiltro.getObra()));

			if(pFiltro.getCuadra() != null) {
				locSubConsulta.add(Restriccion.IGUAL("listaCuadrasAfectadas.cuadra", pFiltro.getCuadra()));
			} else if(pFiltro.getCalle() != null) {
				locSubConsulta.add(Restriccion.IGUAL("listaCuadrasAfectadas.cuadra.calle", pFiltro.getCalle()));
			}

			List locListadoAux = locSubConsulta.list();

			if(!locListadoAux.isEmpty()) {
				crit.add(Restriccion.EN("locDocEspecializado.idDocHabilitanteEspecializado", locListadoAux));
			} else {
				return new FiltroLiquidacionPFO();
			}
		}

		setRestriccionEstadoLiquidacion(pFiltro, crit);

		pFiltro.procesarYListar(crit);

		pFiltro = (FiltroLiquidacionPFO) this.quitarNoCerosLiquidacionesTasa(pFiltro);

		for(Object cadaObject : pFiltro.getListaResultados()) {
			LiquidacionTasa locLiquidacionTasa = (LiquidacionTasa) cadaObject;
			locLiquidacionTasa.toString();
			locLiquidacionTasa.getListaModificadoresLiquidacion().toString();
			for(ModificadorLiquidacion cadaModificadorLiquidacion : locLiquidacionTasa.getListaModificadoresLiquidacion()) {
				cadaModificadorLiquidacion.toString();
			}

			locLiquidacionTasa.getListaParametrosValuados().toString();
			for(ParametroValuado cadaParametroValuado : locLiquidacionTasa.getListaParametrosValuados()) {
				cadaParametroValuado.toString();
			}

			locLiquidacionTasa.getListaVencimientos().toString();
			for(Vencimiento cadaVencimiento : locLiquidacionTasa.getListaVencimientos()) {
				cadaVencimiento.toString();
			}
			locLiquidacionTasa.getDocGeneradorDeuda().toString();
			locLiquidacionTasa.getDocGeneradorDeuda().getObligacion().toString();
			locLiquidacionTasa.getDocGeneradorDeuda().getObligacion().getPersona().toString();

		}

		return pFiltro;
	}

	/**
	 * 
	 * @param pPersona
	 * @param pRubro
	 * @param pPeriodo
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	@Override
	@SuppressWarnings("unchecked")
	public FiltroLiquidacionSHPS findListaLiquidacionesSHPS(FiltroLiquidacionSHPS pFiltro) throws Exception {

		if((pFiltro.getPersona() == null) && (pFiltro.getCuota() == null)) {
			throw new SaicException(37);
		}
		
		Criterio locCritDocumento = Criterio.getInstance(this.entityManager, DocumentoSHPS.class)
				.add(Restriccion.IGUAL("obligacion.persona", pFiltro.getPersona()))
				.add(Restriccion.IGUAL("listaAsocRegAlicuota.registroAlicuota", pFiltro.getRubro()))
				.add(Restriccion.IGUAL("numeroInscripcion", pFiltro.getNroInscripcion()));
		
		AtributoDinamico.addRestriccionesCriterio(locCritDocumento, DocumentoSHPS.serialVersionUID, "idDocHabilitanteEspecializado", pFiltro.getListaAtributosDinamicos());

		Criterio crit = Criterio.getInstance(this.entityManager, LiquidacionTasa.class)
				.setModoDebug(true)
				.crearAlias("docGeneradorDeuda.obligacion.documentoEspecializado", "locDocEsp")
				.add(Restriccion.SUBCONSULTA("locDocEsp", TipoSubconsulta.SOME ,locCritDocumento))
				.add(Restriccion.IGUAL("cuotaLiquidacion", pFiltro.getCuota()))
				.add(Restriccion.IGUAL("cuotaLiquidacion.periodo", pFiltro.getPeriodo()))
				.add(Restriccion.IGUAL("cuotaLiquidacion.periodo.calendario", pFiltro.getCalendario()))
				.add(Restriccion.IGUAL("cuotaLiquidacion.periodo.calendario.anio", pFiltro.getAnio()))
				.setModoDebug(true)
				.add(Restriccion.IGUAL("tipoDeuda", pFiltro.getTipoLiquidacion()));

		if(pFiltro.isNoCero()) {
			crit.add(Restriccion.DISTINTO("montoCalculado", 0D));
		}

		setRestriccionEstadoLiquidacion(pFiltro, crit);

		pFiltro.procesarYListar(crit);

		for(Object cadaObject : pFiltro.getListaResultados()) {
			LiquidacionTasa locLiquidacionTasa = (LiquidacionTasa) cadaObject;
			locLiquidacionTasa.toString();
			locLiquidacionTasa.getStringObligacion();
//			locLiquidacionTasa.getListaModificadoresLiquidacion().toString();
//			locLiquidacionTasa.getListaModificadoresLiquidacion().size();
//			locLiquidacionTasa.getListaAlicuotasLiquidadas().size();
			locLiquidacionTasa.getListaVencimientos().toString();
		}

		return pFiltro;
	}

	private void setRestriccionEstadoLiquidacion(FiltroLiquidacionTasa pFiltro, Criterio pCriterio) {
		if(pFiltro.getEstadoLiquidacion() != null) {
			EstadoRegistroDeuda locEstado = pFiltro.getEstadoLiquidacion();
			if(locEstado.equals(EstadoRegistroDeuda.VENCIDA) || locEstado.equals(EstadoRegistroDeuda.VIGENTE)) {
				StringBuilder sb = new StringBuilder();
				sb.append("CURRENT_DATE ");
				sb.append(locEstado == EstadoRegistroDeuda.VENCIDA ? " > " : " <= ");
				sb.append("(SELECT MAX(fecha) FROM e.listaVencimientos)");

				pCriterio
				// .setDistinct(true)
				// .crearAlias("listaVencimientos", "cadaVencimiento")
						.add(Restriccion.JPQL(sb.toString())).add(
								Restriccion.OR(Restriccion.IGUAL("estado", EstadoRegistroDeuda.VENCIDA), Restriccion.IGUAL("estado", EstadoRegistroDeuda.VIGENTE)));
			} else if(pFiltro.getEstadoLiquidacion().equals(EstadoRegistroDeuda.ANULADA)) {
				pCriterio.add(Restriccion.IGUAL("locObligacion.estado", Obligacion.Estado.ANULADO)).add(Restriccion.NULO("registroCancelacion"));
			} else {
				pCriterio.add(Restriccion.IGUAL("estado", pFiltro.getEstadoLiquidacion()));
			}
		} else {
			pCriterio.add(Restriccion.OR(Restriccion.IGUAL("estado", EstadoRegistroDeuda.PAGADA), Restriccion.IGUAL("estado", EstadoRegistroDeuda.VENCIDA),
					Restriccion.IGUAL("estado", EstadoRegistroDeuda.VIGENTE)));
		}
	}

	/**
	 * Recupera el vencimiento actual de un registro de deuda
	 * 
	 * @ejb.interface-method view-type="local"
	 * @param pRegistroDeuda
	 * @return
	 * @throws java.lang.Exception
	 */
	@Override
	public com.trascender.saic.recurso.persistent.Vencimiento getVencimientoActualPorRegistroDeuda(com.trascender.saic.recurso.persistent.RegistroDeuda pRegistroDeuda)
			throws java.lang.Exception {
		this.entityManager.refresh(pRegistroDeuda);

		if(!(pRegistroDeuda instanceof LiquidacionTasa)) {
			throw new SaicException(38);
		}

		LiquidacionTasa locLiquidacion = (LiquidacionTasa) pRegistroDeuda;
		Vencimiento resultVencimientoActual = null;

		Date locFechaActual = SecurityMgr.getInstance().getFechaActual().getTime();
		locLiquidacion = this.getLiquidacionTasaPorId(locLiquidacion.getIdRegistroDeuda());

		// Obtengo el listado de vencimientos
		Vencimiento[] locListaVencimientos = new Vencimiento[locLiquidacion.getListaVencimientos().size()];
		locLiquidacion.getListaVencimientos().toArray(locListaVencimientos);

		int acumulador = 0;
		while((resultVencimientoActual == null) && (acumulador < locListaVencimientos.length)) {
			Vencimiento locVencimiento = locListaVencimientos[acumulador];
			if(locVencimiento.getFecha().compareTo(locFechaActual) >= 0) {
				resultVencimientoActual = locVencimiento;
			}
			acumulador++;
		}

		if(resultVencimientoActual != null) {
			Date locFechaVencimiento = resultVencimientoActual.getFecha();
			if((locFechaVencimiento.after(locFechaActual)) || (locFechaVencimiento.equals(locFechaActual))) {
				return resultVencimientoActual;
			}
		} else {
			Date locFechaPrimerVencimiento = locLiquidacion.getFechaPrimerVencimiento();
			if(locFechaPrimerVencimiento.after(locFechaActual) || locFechaPrimerVencimiento.equals(locFechaActual)) {
				return locLiquidacion.getListaVencimientos().first();
			}
		}
		throw new SaicException(39);
	}

	/**
	 * Obtiene un listado de las liquidaciones de TGI tomando las obligaciones del mísmo período sin el que es pasado por parámetro
	 * 
	 * @param pRegistroDeuda
	 *            registro de deuda asociado
	 * @ejb.interface-method view-type = "local"
	 */
	@Override
	@SuppressWarnings("unchecked")
	public java.util.List getListaLiquidacionesTGI(RegistroDeuda pRegistroDeuda) throws Exception {
		if(!Hibernate.getClass(pRegistroDeuda.getDocGeneradorDeuda()).equals(TasaTGI.class)) {
			throw new SaicException(52);
		}

		List listaLiquidacionesTasas = Criterio.getInstance(this.entityManager, LiquidacionTasa.class).crearAlias("docGeneradorDeuda", "locDocGeneradorDeuda")
				.add(Restriccion.IGUAL("locDocGeneradorDeuda.obligacion", pRegistroDeuda.getDocGeneradorDeuda().getObligacion())).add(Restriccion.NULO("registroCancelacion"))
				.add(Restriccion.NOT(Restriccion.IGUAL("idRegistroDeuda", pRegistroDeuda.getIdRegistroDeuda()))).list();

		List locListaLiquidacionesTasaRetorno = new ArrayList();

		for(Object cadaObject : listaLiquidacionesTasas) {
			LiquidacionTasa locLiquidacionTasa = (LiquidacionTasa) cadaObject;
			TasaTGI locTasaTGI = (TasaTGI) locLiquidacionTasa.getDocGeneradorDeuda();

			if((locLiquidacionTasa.getCuotaLiquidacion().getPeriodo().getFechaInicio().get(Calendar.YEAR) == pRegistroDeuda.getCuotaLiquidacion().getPeriodo().getFechaInicio()
					.get(Calendar.YEAR))
					&& !(locTasaTGI.getEstado().equals(TasaTGI.Estado.INACTIVO))) {
				locListaLiquidacionesTasaRetorno.add(locLiquidacionTasa);
			}
		}
		return locListaLiquidacionesTasaRetorno;
	}

	/**
	 * Si la liquidación TGI se pagó de alguna de las 3 formas la retorna true sino false
	 * 
	 * @param pRegistroDeuda
	 *            registro de deuda asociado
	 * @ejb.interface-method view-type = "local"
	 */
	@Override
	public boolean isLiquidacionTGISaldada(RegistroDeuda pRegistroDeuda) throws Exception {
		if(!Hibernate.getClass(pRegistroDeuda.getDocGeneradorDeuda()).equals(TasaTGI.class)) {
			throw new SaicException(52);
		}

		// recupero la lista de liquidaciones tasa para la obligacion
		List listaLiquidacionesTasas = Criterio.getInstance(this.entityManager, LiquidacionTasa.class).crearAlias("docGeneradorDeuda", "locDocGeneradorDeuda")
				.add(Restriccion.IGUAL("locDocGeneradorDeuda.obligacion", pRegistroDeuda.getDocGeneradorDeuda().getObligacion()))
				.add(Restriccion.NOT(Restriccion.IGUAL("idRegistroDeuda", pRegistroDeuda.getIdRegistroDeuda()))).list();

		boolean locDeudaSaldada = false;
		for(Object cadaObject : listaLiquidacionesTasas) {
			LiquidacionTasa locLiquidacionTasa = (LiquidacionTasa) cadaObject;
			// verifico que correspondan al periodo a saldar
			if(locLiquidacionTasa.getCuotaLiquidacion().getPeriodo().getFechaInicio().get(Calendar.YEAR) == pRegistroDeuda.getCuotaLiquidacion().getPeriodo().getFechaInicio()
					.get(Calendar.YEAR)) {
				// controlo si el registro de cancelación es distinto de null
				if(locLiquidacionTasa.getRegistroCancelacion() != null) {
					// si es distinto de null comparo el nro de cuota con la cantidad de cuotas de la tasa
					// y si coinciden es ya se pagó la última cuota
					if(locLiquidacionTasa.getNumeroCuota() == locLiquidacionTasa.getTipoTasa().getCantidadCuotas()) {
						locDeudaSaldada = true;
					}
				}
			}
		}
		// significa que aún no se saldo la deuda
		return locDeudaSaldada;
	}

	/**
	 * Genera la liquidación de la tasa PFO dentro del ambiente de una transacción
	 * 
	 * @param pPeriodo
	 * @param pObra
	 * @param pCalle
	 * @param pPersona
	 * @return
	 * @throws Exception
	 */
	private java.util.List generarLiquidacionPFO(CuotaLiquidacion pCuota, Obra pObra, Calle pCalle, Persona pPersona) throws Exception {
		boolean filtrarDocumentos = false;

		double locTiempoInicio = System.currentTimeMillis();
		Criterio locCriteriaDocumentos = Criterio.getInstance(this.entityManager, DocumentoPlanObra.class).setProyeccion(Proyeccion.PROP("idDocHabilitanteEspecializado"));

		if(pObra != null) {
			// this.entityManager.refresh(pObra);
			locCriteriaDocumentos.add(Restriccion.IGUAL("obra", pObra));
			filtrarDocumentos = true;
		}
		if(pCalle != null) {
			locCriteriaDocumentos.add(Restriccion.IGUAL("listaCuadrasAfectadas.cuadra.calle", pCalle));
			filtrarDocumentos = true;
		}

		if(pPersona != null) {
			locCriteriaDocumentos.add(Restriccion.IGUAL("obligacion.persona", pPersona));
			filtrarDocumentos = true;
		}

		Criterio locCriteriaObligacion = Criterio.getInstance(this.entityManager, Obligacion.class).add(Restriccion.IGUAL("documentoEspecializado.class", "PO"))
				.add(Restriccion.NOT(Restriccion.OR(Restriccion.IGUAL("estado", Obligacion.Estado.ANULADO), Restriccion.IGUAL("estado", Obligacion.Estado.TERMINADO))));

		if(filtrarDocumentos) {
			List locListaDocumentos = locCriteriaDocumentos.list();
			if(locListaDocumentos.isEmpty()) {
				return new ArrayList();
			}
			locCriteriaObligacion.add(Restriccion.EN("documentoEspecializado.idDocHabilitanteEspecializado", locListaDocumentos));
		}
		List locListadoObligaciones = locCriteriaObligacion.list();

		int total = locListadoObligaciones.size();
		System.out.println("tiempo en buscar las obligaciones = " + ((System.currentTimeMillis() - locTiempoInicio) / 1000));

		int liquidadas = 0;
		int procesadas = 0;

		boolean locLiquidado;
		int i;

		List<LiquidacionTasa> locListaLiquidacionesTasasRetorno = new ArrayList<LiquidacionTasa>();

		pCuota = entityManager.merge(pCuota);

//		levantarTasasParaCuota(pCuota);//TODO Liquidacion PFO, pfff....
//		levantarLiquidacionesParaCuota(pCuota);

		for(Object locObjObligacion : locListadoObligaciones) {
			liquidadas++;
			procesadas++;
			Obligacion locObligacion = (Obligacion) locObjObligacion;

			if(!this.isPeriodoLiquidado(locObligacion, pCuota)) {
				TipoTasa locTipoTasa = this.getTipoTasaPFO(locObligacion, pCuota.getPeriodo());
				DocumentoPlanObra locDocPlanObra = (DocumentoPlanObra) locObligacion.getDocumentoEspecializado();
				if(locTipoTasa != null) {
					i = 1;
					locLiquidado = false;
					while(i < (locDocPlanObra.getPlanCuentaObra().getCantidadCuotas() + 1) && locLiquidado != true) {
						System.out.println("Cuota Nº: " + i);
						try {
							// FIXME VER COMO LIQUIDAR LAS CUOTAS DE PFO

							List<LiquidacionTasa> locListaLiquidacionesTasas = this.liquidarObligacion(locObligacion, pCuota, pCuota.getPeriodo().getFechaInicio(), locTipoTasa);
							// Como no me genera bien el nro de cuota se lo agrego aca de momento.
							for(LiquidacionTasa cadaLiquidacionTasa : locListaLiquidacionesTasas) {
								cadaLiquidacionTasa.setNumeroCuota(i);
								// Por si no se seteo bien el periodo
								cadaLiquidacionTasa.setCuotaLiquidacion(pCuota);
							}
							locListaLiquidacionesTasasRetorno.addAll(locListaLiquidacionesTasas);
						} catch(SaicException e) {
							System.out.println("No se ha podido liquidar la obligación.");
							e.printStackTrace();
							throw e;
						}
						// FIXME SIGPER pCuota = (CuotaLiquidacion) this.businessPeriodo.getSiguientePeriodo(pCuota.getCalendario().getPeriodicidad(),
						// pCuota.getNumero(),
						// pCuota.getFechaInicio().get(Calendar.YEAR),
						// locTipoTasa.getTipoObligacion());
						i++;
					}
				}
			}
			System.out.println("se han procesado " + procesadas + "/" + total + " obligaciones");
			System.out.println("se han liquidado " + liquidadas + "/" + total + " obligaciones");
		}
		return locListaLiquidacionesTasasRetorno;
	}

	/**
	 * 
	 * @return
	 * @ejb.interface-method view-type = "local"
	 */
	@Override
	@SuppressWarnings({"unchecked"})
	public java.util.List<LiquidacionTasa> getListaLiquidacionesSHPS(Persona pPersona, RegAlicuota pRubro, CuotaLiquidacion pCuota) throws Exception {
		Criterio crit = Criterio.getInstance(this.entityManager, LiquidacionTasa.class)
		// .setFetchJoin("listaModificadoresLiquidacion", "listaVencimientos","listaParametrosValuados","periodo")
				.crearAlias("docGeneradorDeuda.obligacion", "locObligacion");

		// LOS pPARAMS
		crit.add(Restriccion.IGUAL("locObligacion.persona", pPersona))

		.crearAlias("locObligacion.documentoEspecializado", "locDocEsp").add(Restriccion.IGUAL("locDocEsp.registroAlicuota", pRubro))

		.add(Restriccion.IGUAL("cuotaLiquidacion", pCuota));

		List locLista = crit.list();
		for(Object cadaObject : locLista) {
			LiquidacionTasa locLiquidacionTasa = (LiquidacionTasa) cadaObject;
			locLiquidacionTasa.toString();
			locLiquidacionTasa.getListaModificadoresLiquidacion().toString();
			for(ModificadorLiquidacion cadaModificadorLiquidacion : locLiquidacionTasa.getListaModificadoresLiquidacion()) {
				cadaModificadorLiquidacion.toString();
			}
			locLiquidacionTasa.getListaParametrosValuados().toString();
			locLiquidacionTasa.getListaVencimientos().toString();
		}

		System.out.println("tamaño lista liquidaciones shps " + locLista.size());
		return locLista;
	}

	/**
	 * Método para desactivar las tasas tgi no canceladas, es usado en reliquidación y refinanciación
	 * 
	 * @param pRegistroDeuda
	 * @param pTasa
	 * @throws Exception
	 */
	@Override
	public void desactivarTasasTGIRestantes(RegistroDeuda pRegistroDeuda, Tasa pTasa) throws Exception {
		// List listaRegistrosDeuda = this.getListaLiquidacionesTGI(pRegistroDeuda);
		//
		// TasaTGI locTasaTGI = (TasaTGI) pTasa;
		//
		// this.entityManager.refresh(locTasaTGI);
		// TipoTasa tipoTasa = locTasaTGI.getTipoTasa();
		// RegistroDeuda locRegistroDeuda = null;
		//
		// //AL MOMENTO DE PAGAR LA SEGUNDA CUOTA , SI ES QUE TIENE, LAS DEMAS VAN A ESTAR CANCELADAS POR LO QUE NO VA A PASAR POR ESTE CODIGO.
		// //Pregunto si la cantidad de cuotas son distintos para no cancelar las demás cuotas de la liquidación
		// for (Object obj : listaRegistrosDeuda){
		// locRegistroDeuda = (RegistroDeuda) obj;
		// DocGeneradorDeuda locDocGeneradorDeuda = locRegistroDeuda.getDocGeneradorDeuda();
		// this.entityManager.detach(locDocGeneradorDeuda);
		// locDocGeneradorDeuda= (TasaTGI) this.entityManager.find(TasaTGI.class, locDocGeneradorDeuda.getIdDocGeneradorDeuda());
		// TasaTGI tasaTGI= (TasaTGI) locDocGeneradorDeuda;
		// TipoTasa locTipoTasa = tasaTGI.getTipoTasa();
		//
		// // if (locTipoTasa.getCantidadCuotas() == 1){
		// // //La desactivo solo si pertenece al mismo período, sino puede q yo cancela la primera bimestral y la segunda todavía no
		// // //y si no controlo me la desactivaría antes de tiempo
		// // if((locTipoTasa.getCantidadCuotas() != tipoTasa.getCantidadCuotas()) || (locTipoTasa.getPeriodicidad() != tipoTasa.getPeriodicidad()) ){
		// // tasaTGI.setEstado(TasaTGI.Estado.INACTIVO);
		// // }
		// // locRegistroDeuda.setDocGeneradorDeuda(locDocGeneradorDeuda);
		// // this.entityManager.merge(tasaTGI);
		// // }
		// // else {
		// // if((locTipoTasa.getCantidadCuotas() != tipoTasa.getCantidadCuotas()) || (locTipoTasa.getPeriodicidad() != tipoTasa.getPeriodicidad()) ){
		// // tasaTGI.setEstado(TasaTGI.Estado.INACTIVO);
		// // locRegistroDeuda.setDocGeneradorDeuda(locDocGeneradorDeuda);
		// // this.entityManager.merge(tasaTGI);
		// // }
		// // }
		// }
		// locTasaTGI.setEstado(TasaTGI.Estado.ACTIVO);
		// this.entityManager.merge(locTasaTGI);
	}

	/**
	 * metodo utilizado desde interfaz de habilitaciones para la comprobacion de obra, en caso de estar liquidada. Retorna True si no se han liquidado
	 * obligaciones - False si no se han liquidado
	 * 
	 * @param Obra
	 * */
	@Override
	@SuppressWarnings("unchecked")
	public boolean comprobarObra(com.trascender.habilitaciones.recurso.persistent.pfo.Obra pObra) { // Ver BUG 794
		boolean retorno = false;

		List<LiquidacionTasa> listadoLiquidaciones;

		try {
			FiltroLiquidacionPFO locFiltro = new FiltroLiquidacionPFO();
			locFiltro.setObra(pObra);

			listadoLiquidaciones = this.findListaLiquidacionesPFO(locFiltro).getListaResultados();
			System.out.println(" listado Liquidaciones es " + listadoLiquidaciones + " size " + listadoLiquidaciones.size());
			if(!listadoLiquidaciones.isEmpty()) {
				System.out.println(" entro por el verdadero... la lista no esta vacia ");
				retorno = false;
			} else
				retorno = true;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}

	@Override
	public HistorialPagosTasas getHistorialPagos(Obligacion pObligacion, int anios) {
		Calendar fechaHasta = Calendar.getInstance().getInstance();
		fechaHasta.set(Calendar.DAY_OF_MONTH, 31);
		fechaHasta.set(Calendar.MONTH, 11);
		Calendar fechaDesde = Calendar.getInstance().getInstance();
		fechaDesde.set(Calendar.DAY_OF_MONTH, 1);
		fechaDesde.set(Calendar.MONTH, 0);
		fechaDesde.set(Calendar.YEAR, fechaHasta.get(Calendar.YEAR) - (anios - 1));

		Query locQuery = entityManager
				.createQuery(
						"SELECT NEW com.trascender.saic.recurso.references.LiquidacionTasaRef (e.estado, "
								+ "CASE WHEN( e.registroCancelacion.idRegistroCancelacion IS NULL )THEN FALSE ELSE TRUE END, " + "e.fechaEmision, vencimientos) "
								+ "FROM LiquidacionTasa e JOIN e.listaVencimientos vencimientos WHERE " + "e.docGeneradorDeuda.obligacion.idObligacion = :idObligacion " + "AND "
								+ "e.fechaEmision >= :fechaDesde " + "AND " + "e.fechaEmision <= :fechaHasta " + "ORDER BY (e.fechaEmision) DESC")
				.setParameter("idObligacion", pObligacion.getIdObligacion()).setParameter("fechaDesde", fechaDesde.getTime()).setParameter("fechaHasta", fechaHasta.getTime());

		List<LiquidacionTasaRef> locListaresult = locQuery.getResultList();

		// años por cada linea que armo
		int anio = fechaHasta.get(Calendar.YEAR);

		// historialPagos para agregarles lineas
		HistorialPagosTasas locHistorialPagos = new HistorialPagosTasas();

		// recorro las liquidaciones y armo las lineas por cada año
		LineaHistorialPago locLineaPorAnio = new LineaHistorialPago(anio);
		for(LiquidacionTasaRef cadaliquidacionTasaRef : locListaresult) {

			// veo si la liquidacion corresponde al año por el que voy
			Calendar fechaEmisionCadaL = Calendar.getInstance();
			fechaEmisionCadaL.setTime(cadaliquidacionTasaRef.getFechaEmision());
			if(fechaEmisionCadaL.get(Calendar.YEAR) == anio) {
				locLineaPorAnio.addEstado(cadaliquidacionTasaRef.getEstadoChar());
			} else {// si son para el otro año agrego una linea nueva
				locHistorialPagos.addLineaHistorialAnual(locLineaPorAnio);
				anio--;
				locLineaPorAnio = new LineaHistorialPago(anio);
				locLineaPorAnio.addEstado(cadaliquidacionTasaRef.getEstadoChar());
			}
		}
		locHistorialPagos.addLineaHistorialAnual(locLineaPorAnio);

		return locHistorialPagos;
	}

	// @Override
	// public List<EstadoCuentaTemporalTGI> getListaEstadoCuentasTGI(){
	// return this.listaEstadoCuentasTGI;
	// }
	//
	// @Override
	// public List<EstadoCuentaTemporalOSP> getListaEstadoCuentasOSP(){
	// return this.listaEstadoCuentasOSP;
	// }
	//
	// @Override
	// public List<EstadoCuentaTemporalSHPS> getListaEstadoCuentasSHPS(){
	// return this.listaEstadoCuentasSHPS;
	// }

	// @Override
	// public LinkedHashMap<Integer, EstadoCuentaTemporalPFO> getListaEstadoCuentasPFO(){
	// return this.listaEstadoCuentasPFO;
	// }

	public LinkedHashMap<Integer, ExencionRegistroDeuda> getListaExencionesRegistrosDeuda() {
		return this.listaExencionesRegistrosDeuda;
	}

	@Override
	public void eliminarLiquidacionesFisicamente(List<LiquidacionTasa> pListaLiquidacion, String comentario) {
		if(pListaLiquidacion.get(0) instanceof LiquidacionTasaAgrupada) {
			List<LiquidacionTasaAgrupada> listaLiquidacionTasa = Util.castearLista(pListaLiquidacion);

			for(LiquidacionTasaAgrupada cadaLiqTasaAgrupada : listaLiquidacionTasa) {
				for(LiquidacionTasa cadaLiq : cadaLiqTasaAgrupada.getListaLiquidacionesTasa()) {
					if(cadaLiq.getTipoDeuda().equals(RegistroDeuda.TipoDeuda.RELIQUIDACION)) {
						// Si es una reliquidacion, debemos volver al estado anterior la liquidacion anterior.
						this.validarEliminadoReliquidacion(cadaLiq);
					}
					if(cadaLiq.getEstado().equals(RegistroDeuda.EstadoRegistroDeuda.RELIQUIDADA)) {

					}
					try {
						this.generarLogLiquidacion(cadaLiq, SecurityMgr.getInstance().getUsuario(llave), LogLiquidacion.Evento.ELIMINO, comentario);
					} catch(TrascenderFrameworkException e) {
						e.printStackTrace();
					}
					entityManager.remove(entityManager.merge(cadaLiq));
				}
			}
		} else {
			for(LiquidacionTasa cadaLiquidacion : pListaLiquidacion) {
				if(cadaLiquidacion.getTipoDeuda().equals(RegistroDeuda.TipoDeuda.RELIQUIDACION)) {
					// Si es una reliquidacion, debemos volver al estado anterior la liquidacion anterior.
					this.validarEliminadoReliquidacion(cadaLiquidacion);
				}
				if(cadaLiquidacion.getEstado().equals(RegistroDeuda.EstadoRegistroDeuda.RELIQUIDADA)) {

				}
				try {
					this.generarLogLiquidacion(cadaLiquidacion, SecurityMgr.getInstance().getUsuario(llave), LogLiquidacion.Evento.ELIMINO, comentario);
				} catch(TrascenderFrameworkException e) {
					e.printStackTrace();
				}
				entityManager.remove(entityManager.merge(cadaLiquidacion));
			}
		}
	}

	private void eliminarReliquidacionesRecursivas(LiquidacionTasa pLiquidacion) {
		// Al borrar solamente las reliquidacones, evitamos borrar la liquidacion inicial.
		if(pLiquidacion.getTipoDeuda().equals(RegistroDeuda.TipoDeuda.RELIQUIDACION)) {
			entityManager.remove(pLiquidacion);
		} else {

		}
	}

	private void validarEliminadoReliquidacion(LiquidacionTasa pLiquidacion) {
		Reliquidacion locReliquidacion = Criterio.getInstance(entityManager, Reliquidacion.class).add(Restriccion.IGUAL("liquidacionTasa", pLiquidacion)).uniqueResult();

		LiquidacionTasa locLiquidacion = Criterio.getInstance(entityManager, LiquidacionTasa.class).add(Restriccion.IGUAL("registroCancelacion", locReliquidacion)).uniqueResult();
		if(locLiquidacion != null) {
			locLiquidacion.setEstado(locLiquidacion.getEstadoAnterior());
			locLiquidacion.setRegistroCancelacion(null);
		}

		entityManager.remove(locReliquidacion);
	}

	@Override
	public void addRegistroCancelacionManual(List<LiquidacionTasa> pListaLiquidacion, String comentario, Usuario pUsuario) {
		RegistroCancelacionManual locRegistro = new RegistroCancelacionManual();
		locRegistro.setUsuario(pUsuario);
		locRegistro.setFechaCancelacion(new Date());
		locRegistro.setComentario(comentario);
		locRegistro = entityManager.merge(locRegistro);

		if(pListaLiquidacion.get(0) instanceof LiquidacionTasaAgrupada) {
			List<LiquidacionTasaAgrupada> listaLiquidacionTasa = Util.castearLista(pListaLiquidacion);

			for(LiquidacionTasaAgrupada cadaLiqTasaAgrupada : listaLiquidacionTasa) {
				for(LiquidacionTasa cadaLiq : cadaLiqTasaAgrupada.getListaLiquidacionesTasa()) {
					cadaLiq.setRegistroCancelacion(locRegistro);
					cadaLiq.setEstado(RegistroDeuda.EstadoRegistroDeuda.PAGADA);
					entityManager.merge(cadaLiq);
					this.generarLogLiquidacion(cadaLiq, pUsuario, LogLiquidacion.Evento.MARCO_PAGA, comentario);
				}
			}
		} else {
			for(LiquidacionTasa cadaLiquidacion : pListaLiquidacion) {
				cadaLiquidacion.setRegistroCancelacion(locRegistro);
				cadaLiquidacion.setEstado(RegistroDeuda.EstadoRegistroDeuda.PAGADA);
				entityManager.merge(cadaLiquidacion);
				this.generarLogLiquidacion(cadaLiquidacion, pUsuario, LogLiquidacion.Evento.MARCO_PAGA, comentario);
			}
		}
	}

	@Override
	public void updateLiquidacionTasa(List<LiquidacionTasa> pListaLiquidaciones, String comentario) throws Exception {
		// Debo clonar los modificadores y vencimientos para que no cambie los valores a otras liquidaciones
		for(LiquidacionTasa cadaLiquidacion : pListaLiquidaciones) {
			SortedSet<Vencimiento> locListaVencimientos = new TreeSet<Vencimiento>();
			for(Vencimiento cadaVencimiento : cadaLiquidacion.getListaVencimientos()) {
				locListaVencimientos.add(cadaVencimiento.clone());
			}
			cadaLiquidacion.getListaVencimientos().clear();
			cadaLiquidacion.setListaVencimientos(locListaVencimientos);
			Set<ModificadorLiquidacion> locListaModificadores = new HashSet<ModificadorLiquidacion>();
			for(ModificadorLiquidacion cadaModificador : cadaLiquidacion.getListaModificadoresLiquidacion()) {
				locListaModificadores.add(cadaModificador.clone());
			}
			cadaLiquidacion.setListaModificadoresLiquidacion(locListaModificadores);
			cadaLiquidacion.recalcularMonto();
			entityManager.merge(cadaLiquidacion);
			this.generarLogLiquidacion(cadaLiquidacion, SecurityMgr.getInstance().getUsuario(llave), LogLiquidacion.Evento.MODIFICO, comentario);
		}
	}

	@Override
	public void marcarImpaga(List<LiquidacionTasa> pListaLiquidacion, String comentario) {
		if(pListaLiquidacion.get(0) instanceof LiquidacionTasaAgrupada) {
			List<LiquidacionTasaAgrupada> listaLiquidacionTasa = Util.castearLista(pListaLiquidacion);

			for(LiquidacionTasaAgrupada cadaLiqTasaAgrupada : listaLiquidacionTasa) {
				for(LiquidacionTasa cadaLiq : cadaLiqTasaAgrupada.getListaLiquidacionesTasa()) {
					this.volverAtrasDeuda(cadaLiq.getRegistroCancelacion());
					cadaLiq.setEstado(EstadoRegistroDeuda.VIGENTE);
					cadaLiq.setRegistroCancelacion(null);
					entityManager.merge(cadaLiq);
					try {
						this.generarLogLiquidacion(cadaLiq, SecurityMgr.getInstance().getUsuario(llave), LogLiquidacion.Evento.MARCO_IMPAGA, comentario);
					} catch(TrascenderFrameworkException e) {
						e.printStackTrace();
					}
				}
			}
		} else {
			for(LiquidacionTasa cadaLiquidacion : pListaLiquidacion) {
				this.volverAtrasDeuda(cadaLiquidacion.getRegistroCancelacion());
				cadaLiquidacion.setEstado(EstadoRegistroDeuda.VIGENTE);
				cadaLiquidacion.setRegistroCancelacion(null);
				entityManager.merge(cadaLiquidacion);
				try {
					this.generarLogLiquidacion(cadaLiquidacion, SecurityMgr.getInstance().getUsuario(llave), LogLiquidacion.Evento.MARCO_IMPAGA, comentario);
				} catch(TrascenderFrameworkException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public FiltroLiquidacionAutomotor findListaLiquidacionesAutomotor(FiltroLiquidacionAutomotor pFiltro) throws Exception {
		if((pFiltro.getPersona() == null) && (pFiltro.getCuota() == null)) {
			throw new SaicException(37);
		}

		ParametroSistemaString locParametroOmitirCeros = this.getParametroSistemaString("OMITIR_CEROS");
		if(locParametroOmitirCeros != null && locParametroOmitirCeros.getCadena().equals("SI")) {
			pFiltro.setNoCero(true);
		}

		try {
			Criterio crit = Criterio.getInstance(this.entityManager, LiquidacionTasa.class).setModoDebug(true).crearAlias("docGeneradorDeuda.obligacion", "locObligacion")
					.crearAlias("locObligacion.documentoEspecializado", "locDocEsp").add(Restriccion.IGUAL("locDocEsp.class", "AUTOMOTOR"));
			// LOS pParam
			crit.add(Restriccion.IGUAL("locObligacion.persona", pFiltro.getPersona())).add(Restriccion.IGUAL("locDocEsp.vehiculo", pFiltro.getVehiculo()))
					.add(Restriccion.IGUAL("cuotaLiquidacion", pFiltro.getCuota())).add(Restriccion.IGUAL("cuotaLiquidacion.periodo", pFiltro.getPeriodo()))
					.add(Restriccion.IGUAL("cuotaLiquidacion.periodo.calendario", pFiltro.getCalendario()))
					.add(Restriccion.IGUAL("cuotaLiquidacion.periodo.calendario.anio", pFiltro.getAnio()));

			setRestriccionEstadoLiquidacion(pFiltro, crit);

			crit.add(Restriccion.IGUAL("tipoDeuda", pFiltro.getTipoLiquidacion()));

			pFiltro.procesarYListar(crit);

			pFiltro = (FiltroLiquidacionAutomotor) this.quitarNoCerosLiquidacionesTasa(pFiltro);

			for(Object cadaObject : pFiltro.getListaResultados()) {
				LiquidacionTasa locLiquidacionTasa = (LiquidacionTasa) cadaObject;
				locLiquidacionTasa.toString();
				locLiquidacionTasa.getStringObligacion();
				locLiquidacionTasa.getListaModificadoresLiquidacion().toString();
				locLiquidacionTasa.getListaModificadoresLiquidacion().size();
				locLiquidacionTasa.getListaAlicuotasLiquidadas().size();
				locLiquidacionTasa.getCuotaLiquidada();

				locLiquidacionTasa.getListaVencimientos().toString();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}

		return pFiltro;
	}

	@Override
	@SuppressWarnings("unchecked")
	public FiltroLiquidacionCementerio findListaLiquidacionesCementerio(FiltroLiquidacionCementerio pFiltro) throws Exception {
		if((pFiltro.getPersona() == null) && (pFiltro.getCuota() == null)) {
			throw new SaicException(37);
		}

		ParametroSistemaString locParametroOmitirCeros = this.getParametroSistemaString("OMITIR_CEROS");
		if(locParametroOmitirCeros != null && locParametroOmitirCeros.getCadena().equals("SI")) {
			pFiltro.setNoCero(true);
		}

		try {
			Criterio crit = Criterio.getInstance(this.entityManager, LiquidacionTasa.class).setModoDebug(true).crearAlias("docGeneradorDeuda.obligacion", "locObligacion")
					.crearAlias("locObligacion.documentoEspecializado", "locDocEsp").add(Restriccion.IGUAL("locDocEsp.class", "CMT"));

			// LOS pParam
			crit.add(Restriccion.IGUAL("locObligacion.persona", pFiltro.getPersona())).add(Restriccion.MIEMBRO_DE("locDocEsp.listaAsocRegAlicuota", pFiltro.getParcelaCementerio()))
					.add(Restriccion.IGUAL("locDocEsp.listaAsocRegAlicuota.registroAlicuota", pFiltro.getTipoSepultura()))
					.add(Restriccion.IGUAL("cuotaLiquidacion", pFiltro.getCuota())).add(Restriccion.IGUAL("cuotaLiquidacion.periodo", pFiltro.getPeriodo()))
					.add(Restriccion.IGUAL("cuotaLiquidacion.periodo.calendario", pFiltro.getCalendario()))
					.add(Restriccion.IGUAL("cuotaLiquidacion.periodo.calendario.anio", pFiltro.getAnio()));

			setRestriccionEstadoLiquidacion(pFiltro, crit);

			crit.add(Restriccion.IGUAL("tipoDeuda", pFiltro.getTipoLiquidacion()));

			pFiltro.procesarYListar(crit);

			pFiltro = (FiltroLiquidacionCementerio) this.quitarNoCerosLiquidacionesTasa(pFiltro);

			for(Object cadaObject : pFiltro.getListaResultados()) {
				LiquidacionTasa locLiquidacionTasa = (LiquidacionTasa) cadaObject;
				locLiquidacionTasa.toString();
				locLiquidacionTasa.getStringObligacion();
				locLiquidacionTasa.getListaModificadoresLiquidacion().size();
				locLiquidacionTasa.getCuotaLiquidada();
				locLiquidacionTasa.getListaAlicuotasLiquidadas().size();

				locLiquidacionTasa.getListaVencimientos().size();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}

		return pFiltro;
	}

	@Override
	public List findListaDocsGeneradoresDeuda(Obligacion pObligacion) {
		List locListaDocsGeneradoresDeuda = Criterio.getInstance(this.entityManager, Tasa.class).crearFetchAlias("planElegido", "locPlanElegido")
				.add(Restriccion.IGUAL("obligacion", pObligacion)).add(Orden.ASC("anio")).list();

		return locListaDocsGeneradoresDeuda;
	}

	@Override
	public ResultadoLiquidacion liquidarAutomotor(Vehiculo pVehiculo, Persona pPersona, CuotaLiquidacion pCuota) throws Exception {

		List<LiquidacionTasa> locListaLiquidacionesRetorno = new ArrayList<LiquidacionTasa>();

		ResultadoLiquidacion locResultadoLiquidacion = new ResultadoLiquidacion();

		CalendarioMunicipal locCalendarioMunicipal = (CalendarioMunicipal) pCuota.getPeriodo().getCalendario();
		TipoTasa locTipoTasa = Criterio.getInstance(this.entityManager, TipoTasa.class).add(Restriccion.IGUAL("plan", locCalendarioMunicipal.getPlan()))
				.add(Restriccion.IGUAL("estado", TipoTasa.Estado.ACTIVA)).uniqueResult();

		if(locTipoTasa == null) {
			locResultadoLiquidacion.addMensaje(4);
			return locResultadoLiquidacion;
		}

		Criterio locCriterio = Criterio.getInstance(entityManager, DocumentoAutomotor.class).add(Restriccion.IGUAL("estado", DocHabilitanteEspecializado.Estado.ACTIVO))
				.add(Restriccion.IGUAL("obligacion.persona", pPersona)).add(Restriccion.IGUAL("vehiculo", pVehiculo));

		if(pPersona == null) {
			locCriterio.crearFetchAlias("obligacion.persona", "locPersona");
		}

		if(pVehiculo == null) {
			locCriterio.crearFetchAlias("vehiculo", "locVehiculo");
		}

		pCuota = entityManager.merge(pCuota);
		
		List<DocumentoAutomotor> locListaDocumentos = locCriterio.list();

		levantarTasasParaCuota(pCuota, locListaDocumentos);
		levantarLiquidacionesParaCuota(pCuota, locListaDocumentos);

		for(DocumentoAutomotor cadaDocumento : locListaDocumentos) {

			try {
				locListaLiquidacionesRetorno.addAll(liquidarObligacion(cadaDocumento.getObligacion(), pCuota, Calendar.getInstance(), locTipoTasa));
			} catch(TrascenderException t) {
				System.out.println("          //*/*/*");
				System.out.println("ERROR: " + t.getMessage());
				locResultadoLiquidacion.getListaMensajes().add(t.getMessage());
				locResultadoLiquidacion.getListaObligacionesNoLiquidadas().add(cadaDocumento.getObligacion());
				System.out.println("          //*/*/*");
			} catch(Exception e) {
				System.out.println(":::::: No se ha podido liquidar la obligacion.");
				// e.printStackTrace();
				locResultadoLiquidacion.getListaMensajes().add(e.getMessage());
				locResultadoLiquidacion.getListaObligacionesNoLiquidadas().add(cadaDocumento.getObligacion());
			}
		}

		if(!locListaLiquidacionesRetorno.isEmpty()) {
			this.guardarLiquidacionesTasa(locListaLiquidacionesRetorno);
		}

		locResultadoLiquidacion.setCantidadLiquidadas(locListaLiquidacionesRetorno.size());
		return locResultadoLiquidacion;
	}

	@Override
	public ResultadoLiquidacion liquidarCementerio(ParcelaCementerio pParcelaCementerio, CuotaLiquidacion pCuota, Persona pPersona, TipoSepultura pTipoSepultura) throws Exception {

		List<LiquidacionTasa> locListaLiquidacionesRetorno = new ArrayList<LiquidacionTasa>();

		ResultadoLiquidacion locResultadoLiquidacion = new ResultadoLiquidacion();

		CalendarioMunicipal locCalendarioMunicipal = (CalendarioMunicipal) pCuota.getPeriodo().getCalendario();
		TipoTasa locTipoTasa = Criterio.getInstance(this.entityManager, TipoTasa.class).add(Restriccion.IGUAL("plan", locCalendarioMunicipal.getPlan()))
				.add(Restriccion.IGUAL("estado", TipoTasa.Estado.ACTIVA)).uniqueResult();

		if(locTipoTasa == null) {
			locResultadoLiquidacion.addMensaje(4);
			return locResultadoLiquidacion;
		}

		Criterio locCriterio = Criterio.getInstance(entityManager, DocumentoCementerio.class).add(Restriccion.IGUAL("estado", DocHabilitanteEspecializado.Estado.ACTIVO))
				.add(Restriccion.IGUAL("obligacion.persona", pPersona)).add(Restriccion.MIEMBRO_DE("listaAsocRegAlicuota", pParcelaCementerio))
				.add(Restriccion.IGUAL("listaAsocRegAlicuota.regAlicuota", pTipoSepultura));

		pCuota = entityManager.merge(pCuota);

		List<DocumentoCementerio> listaDocumentos = locCriterio.list();
		
		levantarTasasParaCuota(pCuota, listaDocumentos);
		levantarLiquidacionesParaCuota(pCuota, listaDocumentos);

		for(DocumentoCementerio cadaDocumento : listaDocumentos) {
			locListaLiquidacionesRetorno.addAll(liquidarObligacion(cadaDocumento.getObligacion(), pCuota, Calendar.getInstance(), locTipoTasa));
		}

		if(!locListaLiquidacionesRetorno.isEmpty()) {
			this.guardarLiquidacionesTasa(locListaLiquidacionesRetorno);
		}

		locResultadoLiquidacion.setCantidadLiquidadas(locListaLiquidacionesRetorno.size());
		return locResultadoLiquidacion;
	}

	/**
	 * Devuelve un Registro Deuda a el estado anterior de ser cobrado.
	 * 
	 * @param pRegistroCancelacion
	 */
	@Override
	@SuppressWarnings("unchecked")
	public RegistroDeuda volverAtrasDeuda(RegistroCancelacion pRegistroCancelacion) {

		List<RegistroDeuda> locListaRegistros = Criterio.getInstance(entityManager, RegistroDeuda.class).add(Restriccion.IGUAL("registroCancelacion", pRegistroCancelacion)).list();

		for(RegistroDeuda locRegistroDeuda : locListaRegistros) {
			if(locRegistroDeuda instanceof LiquidacionTasa) {
				Tasa locTasa = (Tasa) locRegistroDeuda.getDocGeneradorDeuda();
				locRegistroDeuda.setRegistroCancelacion(null);
				locRegistroDeuda.setEstado(RegistroDeuda.EstadoRegistroDeuda.VIGENTE);
				this.entityManager.merge(locRegistroDeuda);

				locTasa.setPlanElegido(null);
				entityManager.merge(locTasa);
				// Marcar como optables las los otros planes

				LiquidacionTasa locLiquidacion = (LiquidacionTasa) locRegistroDeuda;
				if(locLiquidacion.getCuotaLiquidacion().getPeriodo().getNumero().equals(1)) {
					List<LiquidacionTasa> locLista = Criterio.getInstance(entityManager, LiquidacionTasa.class).add(Restriccion.IGUAL("docGeneradorDeuda", locTasa))
							.add(Restriccion.IGUAL("estado", RegistroDeuda.EstadoRegistroDeuda.NO_OPTADA))
							.add(Restriccion.DISTINTO("tipoTasa.plan", locLiquidacion.getTipoTasa().getPlan())).list();
					for(LiquidacionTasa cadaLiquidacion : locLista) {
						cadaLiquidacion.setRegistroCancelacion(null);
						cadaLiquidacion.setEstado(RegistroDeuda.EstadoRegistroDeuda.VIGENTE);
					}
				}
			}
		}
		return locListaRegistros.isEmpty() ? null : locListaRegistros.get(0);
	}

	@Override
	public FiltroCobroExterno findListaCobroExterno(FiltroCobroExterno pFiltro) {
		Criterio locCriterio = Criterio.getInstance(entityManager, CobroExterno.class)
		/* .add(Restriccion.IGUAL("liquidacion", pFiltro.get)) */;

		BusquedaPorLog.addRestriccionesCriterio(locCriterio, CobroExterno.serialVersionUID, "idCobroExterno", pFiltro.getListaBusquedaPorLogs());

		pFiltro.procesarYListar(locCriterio);

		return pFiltro;
	}

	@Override
	public CobroExterno getCobroExternoById(Long pIdCobroExterno) throws Exception {
		CobroExterno locCobroExterno = entityManager.find(CobroExterno.class, pIdCobroExterno);

		return locCobroExterno;
	}

	@Override
	public void procesarArchivoCobroExterno(File pArchivo, EntidadRecaudadora pEntidadRecaudadora) throws Exception {
		if(pEntidadRecaudadora.equals(EntidadRecaudadora.PAGOFACIL)) {
			this.armarArchivoPagoFacil(pArchivo);
		}
	}

	private void armarArchivoPagoFacil(File pArchivo) throws Exception {
		ArchivoTransmisionPagoFacil locArchivoPF = new ArchivoTransmisionPagoFacil(pArchivo);
		System.out.println("--> " + locArchivoPF);
		for(LoteArchivoPF cadaLote : locArchivoPF.getListaLotes()) {
			if(cadaLote != null) {
				for(RegistroDetallePF cadaRegDetalle : cadaLote.getListaRegistroPagos()) {
					String locCodigoCliente = cadaRegDetalle.getDetalleTransaccion().getAccountNumber();

					// RegistroDeuda locRegistroDeuda = this.getRegistroDeudaPorId(idRegistroDeuda);

					System.out.println("-----------> el codigo cliente: " + locCodigoCliente);

					List<Object> locListaIdsResultado = this.leerCodigoClientePagoFacil(locCodigoCliente);

					System.out.println("<----------- el resultado de la lectura del codigo cliente: " + locListaIdsResultado);

					for(Object cadaObj : locListaIdsResultado) {
						RegistroDeuda locRegistroDeuda = this.getRegistroDeudaPorId(Long.parseLong(cadaObj.toString()));
						System.out.println(locRegistroDeuda);
						//TODO Armar el CobroExterno y persistirlo.
					}

					// System.out.println("-------> REGISTRO DEUDA CON ID " + locRegistroDeuda.getIdRegistroDeuda() + " : " + locRegistroDeuda);
				}
			}
		}
	}

	@Override
	public String getCodigoClientePagoFacil(Long pIdLiquidacionTasa) {
		return (String) this.entityManager.createNativeQuery("SELECT * FROM GENERAR_CODIGO_CLIENTE_PAGOFACIL(:param1)").setParameter("param1", pIdLiquidacionTasa).getSingleResult();
	}

	public ArrayList<Object> leerCodigoClientePagoFacil(String pCodigoCliente) {
		return (ArrayList<Object>) this.entityManager.createNativeQuery("SELECT * FROM LEER_CODIGO_CLIENTE_PAGOFACIL(:codCliente)").setParameter("codCliente", pCodigoCliente)
				.getResultList();
	}

	@Override
	public void generarLogLiquidacion(LiquidacionTasa pLiquidacionTasa, Usuario pUsuario, LogLiquidacion.Evento pEvento, String comentario) {
		LogLiquidacion locLogLiquidacion = new LogLiquidacion();

		locLogLiquidacion.setObligacion(pLiquidacionTasa.getDocGeneradorDeuda().getObligacion());
		locLogLiquidacion.setCuota(pLiquidacionTasa.getCuotaLiquidacion());
		locLogLiquidacion.setUsuario(pUsuario);
		locLogLiquidacion.setFecha(new Date());
		locLogLiquidacion.setMontoTotalLiquidacion(pLiquidacionTasa.getMonto());
		locLogLiquidacion.setEvento(pEvento);
		locLogLiquidacion.setComentario(comentario);
		locLogLiquidacion.setIntereses(pLiquidacionTasa.getInteres());
		locLogLiquidacion.setTipoDeuda(pLiquidacionTasa.getTipoDeuda());

		entityManager.merge(locLogLiquidacion);
	}

	@Override
	public List<LogLiquidacion> getListaLogLiquidacion(FiltroLogLiquidacion pFiltro) throws Exception {
		pFiltro.getMapaOrden().put("fecha", FiltroAbstracto.ASC);

		try {
			Criterio crit = Criterio.getInstance(this.entityManager, LogLiquidacion.class).add(Restriccion.IGUAL("obligacion", pFiltro.getObligacion()))
					.add(Restriccion.ILIKE("usuario.user", pFiltro.getUsuario())).add(Restriccion.IGUAL("evento", pFiltro.getEvento()))
					.add(Restriccion.MAYOR("fecha", pFiltro.getFechaDesdeInicioDia())).add(Restriccion.MENOR("fecha", pFiltro.getFechaHastaFinDia()));

			pFiltro.procesarYListar(crit);
		} catch(Exception e) {
			e.printStackTrace();
		}

		return pFiltro.getListaResultados();
	}

	@Override
	public LogLiquidacion getLogLiquidacionesPorId(long pId) throws Exception {
		LogLiquidacion locLogLiquidacion = (LogLiquidacion) Criterio.getInstance(this.entityManager, LogLiquidacion.class).add(Restriccion.IGUAL("idLogLiquidacion", pId))
				.uniqueResult();
		if(locLogLiquidacion == null) {
			return null;
		}

		locLogLiquidacion.getObligacion().toString();
		locLogLiquidacion.getUsuario().toString();

		return locLogLiquidacion;
	}

	@Override
	public FiltroLogLiquidacion findListaLogLiquidacion(FiltroLogLiquidacion pFiltro) {
		pFiltro.getMapaOrden().put("fecha", FiltroAbstracto.ASC);

		Criterio crit = Criterio.getInstance(this.entityManager, LogLiquidacion.class).add(Restriccion.ILIKE("usuario.user", pFiltro.getUsuario()))
				.add(Restriccion.IGUAL("evento", pFiltro.getEvento())).add(Restriccion.IGUAL("cuota.periodo.calendario.anio", pFiltro.getAnio()))
				.add(Restriccion.IGUAL("cuota.periodo.numero", pFiltro.getNroPeriodo())).add(Restriccion.IGUAL("cuota.numero", pFiltro.getNroCuota()))
				.add(Restriccion.MAYOR("fecha", pFiltro.getFechaDesdeInicioDia())).add(Restriccion.MENOR("fecha", pFiltro.getFechaHastaFinDia()));

		if(pFiltro.getTipoObligacion() != null) {
			String valor = "";
			if(pFiltro.getTipoObligacion().getNombre().equals("TGI")) {
				valor = "TGI";
			} else if(pFiltro.getTipoObligacion().getNombre().equals("SHPS")) {
				valor = "SHPS";
			} else if(pFiltro.getTipoObligacion().getNombre().equals("OYSP")) {
				valor = "OSP";
			} else if(pFiltro.getTipoObligacion().getNombre().equals("AUTOMOTOR")) {
				valor = "AUTOMOTOR";
			} else if(pFiltro.getTipoObligacion().getNombre().equals("CEMENTERIO")) {
				valor = "CMT";
			} else {
				valor = "MENOR";
				crit.add(Restriccion.IGUAL("obligacion.documentoEspecializado.plantillaDocumentoTasaMenor.nombre", pFiltro.getTipoObligacion().getNombre()));
			}
			crit.add(Restriccion.IGUAL("obligacion.documentoEspecializado.class", valor));
		}

		pFiltro.procesarYListar(crit);

		for(Object cadaObject : pFiltro.getListaResultados()) {
			LogLiquidacion locLogLiquidacion = (LogLiquidacion) cadaObject;
			locLogLiquidacion.toString();
			locLogLiquidacion.getObligacion().toString();
		}

		return pFiltro;
	}

}