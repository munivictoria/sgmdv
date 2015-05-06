
package com.trascender.saic.business.interfaces;

import java.io.File;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.ejb.Local;

import org.nfunk.jep.JEP;

import com.trascender.catastro.recurso.persistent.Calle;
import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.habilitaciones.recurso.filtros.FiltroObligacionSHPS;
import com.trascender.habilitaciones.recurso.persistent.CuotaLiquidacion;
import com.trascender.habilitaciones.recurso.persistent.DocHabilitanteEspecializado;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.RegAlicuota;
import com.trascender.habilitaciones.recurso.persistent.TipoObligacion;
import com.trascender.habilitaciones.recurso.persistent.TipoParametro;
import com.trascender.habilitaciones.recurso.persistent.TipoTasa;
import com.trascender.habilitaciones.recurso.persistent.TipoVencimiento;
import com.trascender.habilitaciones.recurso.persistent.cementerio.ParcelaCementerio;
import com.trascender.habilitaciones.recurso.persistent.cementerio.TipoSepultura;
import com.trascender.habilitaciones.recurso.persistent.osp.ServicioOSP;
import com.trascender.habilitaciones.recurso.persistent.pfo.Obra;
import com.trascender.habilitaciones.recurso.persistent.transito.Vehiculo;
import com.trascender.saic.exception.ResultadoLiquidacion;
import com.trascender.saic.recurso.filtros.FiltroCobroExterno;
import com.trascender.saic.recurso.filtros.FiltroLiquidacionArrendamiento;
import com.trascender.saic.recurso.filtros.FiltroLiquidacionAutomotor;
import com.trascender.saic.recurso.filtros.FiltroLiquidacionCementerio;
import com.trascender.saic.recurso.filtros.FiltroLiquidacionOSP;
import com.trascender.saic.recurso.filtros.FiltroLiquidacionPFO;
import com.trascender.saic.recurso.filtros.FiltroLiquidacionSHPS;
import com.trascender.saic.recurso.filtros.FiltroLiquidacionTGI;
import com.trascender.saic.recurso.filtros.FiltroLiquidacionTasaMenor;
import com.trascender.saic.recurso.filtros.FiltroLogLiquidacion;
import com.trascender.saic.recurso.persistent.CobroExterno;
import com.trascender.saic.recurso.persistent.CobroExterno.EntidadRecaudadora;
import com.trascender.saic.recurso.persistent.Condonacion;
import com.trascender.saic.recurso.persistent.EstadoCuentaTemporal;
import com.trascender.saic.recurso.persistent.LiquidacionTasa;
import com.trascender.saic.recurso.persistent.LogLiquidacion;
import com.trascender.saic.recurso.persistent.ParametroValuado;
import com.trascender.saic.recurso.persistent.RegistroCancelacion;
import com.trascender.saic.recurso.persistent.RegistroDeuda;
import com.trascender.saic.recurso.persistent.RegistroDeuda.EstadoRegistroDeuda;
import com.trascender.saic.recurso.persistent.Tasa;
import com.trascender.saic.recurso.persistent.Vencimiento;
import com.trascender.saic.recurso.transients.HistorialPagosTasas;

@Local
public interface BusinessLiquidacionTasaLocal {
	public static final String JNDI_NAME = "BusinessLiquidacionTasaLocal/local";

	// public void ejecutarProcedimientoActualizacionDeudaOSP(Persona pPersona, Parcela pParcela) throws Exception;
	//
	// public void ejecutarProcedimientoActualizacionDeudaPFO(Persona pPersona, Parcela pParcela) throws Exception;
	//
	// public void ejecutarProcedimientoActualizacionDeudaSHPS(Persona pPersona, Parcela pParcela) throws Exception;
	//
	// public void ejecutarProcedimientoActualizacionDeudaTGI(Persona pPersona, Parcela pParcela) throws Exception;

	// public List<EstadoCuentaTemporalTGI> getListaEstadoCuentasTGI();
	// public List<EstadoCuentaTemporalOSP> getListaEstadoCuentasOSP();
	// public List<EstadoCuentaTemporalSHPS> getListaEstadoCuentasSHPS();
	// public LinkedHashMap<Integer, EstadoCuentaTemporalPFO> getListaEstadoCuentasPFO();

	public void ejecutarProcedimientoActualizacionDeuda(Persona pPersona, Parcela pParcela, List<LiquidacionTasa> listaLiquidacionesExcluir) throws Exception;
	
	public void ejecutarProcedimientoActualizacionDeuda(Persona pPersona, Parcela pParcela) throws Exception;

	public List<EstadoCuentaTemporal> getListaEstadoCuentasTemporales();

	public HistorialPagosTasas getHistorialPagos(Obligacion pObligacion, int pAnios);

	/**
	 * Liquida uina obligación para un período y utilizando la fecha de liquidación pasada por parámetro
	 * 
	 * @param pObligacion
	 * @param pPeriodo
	 * @param pFechaLiquidacion
	 * @param pNumeroCuota
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List liquidarObligacion(Obligacion pObligacion, CuotaLiquidacion pCuota, Calendar pFechaLiquidacion, TipoTasa pTipoTasa) throws Exception;

	/**
	 * Retorna el listado de modificadores de liquidación aplicados
	 * 
	 * @param pJep
	 * @param locLiquidacionTasa
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Set calcularValoresModificadoresLiquidacion(com.trascender.saic.recurso.persistent.LiquidacionTasa pLiquidacionTasa, java.util.Calendar pFechaAplicacion, JEP pJep);

	/**
	 * Liquida la tasa general inmobiliaria para un período, esta liquidación representa la liquidación normal la misma es liquidada a la fecha del primer
	 * vencimiento
	 * 
	 * @param pPeriodo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ResultadoLiquidacion liquidarTgi(CuotaLiquidacion[] pCuota, Persona pPersona, Parcela pParcela, Boolean pIgnorarPlan) throws Exception;

	public void generarLiquidacionPruebaTGI(CuotaLiquidacion pCuota, Persona pPersona, Parcela pParcela) throws Exception;

	/**
	 * Realiza las liquidaciones de tasa OSP
	 * 
	 * @param pServicio
	 * @param pCalle
	 * @param pPeriodo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List generarLiquidacionPruebaOSP(ServicioOSP pServicio, Calle pCalle, CuotaLiquidacion pCuota, Persona pPersona, Parcela pParcela) throws Exception;

	@SuppressWarnings("unchecked")
	public List generarLiquidacionPruebaPFO(Persona pPersona, Obra pObra, Calle pCalle, CuotaLiquidacion pCuota) throws Exception;

	@SuppressWarnings("unchecked")
	public List generarLiquidacionPruebaSHPS(Persona pPersona, CuotaLiquidacion pCuota, FiltroObligacionSHPS pFiltro) throws Exception;

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
	 */
	@SuppressWarnings("unchecked")
	public List findListaLiquidaciones(CuotaLiquidacion pCuota, TipoTasa pTipoTasa, Persona pPersona, EstadoRegistroDeuda pEstadoRegistroDeuda) throws Exception;

	/**
	 * Realiza las liquidaciones de la tasa OSP
	 * 
	 * @param pPeriodo
	 * @param pServicio
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ResultadoLiquidacion liquidarOSP(ServicioOSP pServicio, Calle pCalle, CuotaLiquidacion[] pCuota, Persona pPersona, Parcela pParcela, Boolean pIgnorarPlan) throws Exception;

	/**
	 * Recupera una liquidación por número de identificación
	 * 
	 * @param pId
	 * @return
	 * @throws Exception
	 */
	public LiquidacionTasa getLiquidacionTasaPorId(long pId) throws Exception;

	public LiquidacionTasa getLiquidacionTasaCompleta(LiquidacionTasa pLiquidacionTasa);

	/**
	 * Recupera un listado de liquidaciones de TGI
	 * 
	 * @param pPeriodo
	 * @param pPersona
	 * @param pParcela
	 * @param pPeriodicidad
	 * @param pNumeroCuota
	 * @return
	 * @throws Excepiton
	 */
	@SuppressWarnings("unchecked")
	public FiltroLiquidacionTGI findListaLiquidacionesTGI(FiltroLiquidacionTGI pFiltro) throws Exception;

	/**
	 * Crea un nuevo registro de cancelación del tipo Condonacion
	 * 
	 * @param pRegistroDeuda
	 * @throws Exception
	 */
	public Condonacion condonarDeuda(RegistroDeuda pRegistroDeuda, String pCausa) throws Exception;

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
	 * @param pTipoDeuda
	 * @param pCuentaSubcuenta
	 * @return
	 * @throws TrascenderException
	 */
	@SuppressWarnings("unchecked")
	public FiltroLiquidacionOSP findListaLiquidacionesOSP(FiltroLiquidacionOSP pFiltro) throws Exception;

	/**
	 * Liquida todas las obliga ciones de SHPS cuyos registros de alícuota sean fijos
	 * 
	 * @param pPeriodo
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ResultadoLiquidacion liquidarSHPS(Persona pPersona, CuotaLiquidacion[] pCuota, FiltroObligacionSHPS pFiltro, Boolean pIgnorarPlan) throws Exception;

	public RegistroDeuda getRegistroDeudaPorId(Long pId) throws Exception;

	@SuppressWarnings("unchecked")
	public ResultadoLiquidacion liquidarPFO(Persona pPersona, Obra pObra, Calle pCalle, CuotaLiquidacion[] pCuota) throws Exception;

	public ResultadoLiquidacion liquidarTasaMenor(Persona pPersona, Parcela pParcela, TipoObligacion lTipoObligacionTipoTasa, CuotaLiquidacion[] pCuotas, Boolean pIgnorarPlan)
			throws Exception;

	public FiltroLiquidacionTasaMenor findListaLiquidacionesTasaMenor(FiltroLiquidacionTasaMenor pFiltro);

	@SuppressWarnings("unchecked")
	public FiltroLiquidacionPFO findListaLiquidacionesPFO(FiltroLiquidacionPFO pFiltro) throws Exception;

	@SuppressWarnings("unchecked")
	public FiltroLiquidacionSHPS findListaLiquidacionesSHPS(FiltroLiquidacionSHPS pFiltro) throws Exception;

	/**
	 * Recupera el vencimiento actual de un registro de deuda
	 * 
	 * @param pRegistroDeuda
	 * @return
	 * @throws java.lang.Exception
	 */
	public Vencimiento getVencimientoActualPorRegistroDeuda(RegistroDeuda pRegistroDeuda) throws Exception;

	public void anularRegistrosDeudaSinCancelar(Obligacion pObligacion) throws Exception;

	/**
	 * Obtiene un listado de las liquidaciones de TGI tomando las obligaciones del mísmo período sin el que es pasado por parámetro
	 * 
	 * @param pRegistroDeuda
	 *            registro de deuda asociado
	 */
	@SuppressWarnings("unchecked")
	public List getListaLiquidacionesTGI(RegistroDeuda pRegistroDeuda) throws Exception;

	public boolean isLiquidacionTGISaldada(RegistroDeuda pRegistroDeuda) throws Exception;

	@SuppressWarnings("unchecked")
	public List getListaLiquidacionesSHPS(Persona pPersona, RegAlicuota pRubro, CuotaLiquidacion pCuota) throws Exception;

	public Calendar getFechaVencimiento(final Calendar pFechaDesde, TipoVencimiento pTipoVencimiento) throws Exception;

	public void setLlave(long pLlave);

	/**
	 * Crea un tipo de parámetro valuado a partir del tipo de parámetro. pero debe haberse ejecutado primero el setTipoParametro
	 * 
	 * @param pTipoParametro
	 * @param pNumeroCuota
	 * @param pIdRegistroDeuda
	 * @return
	 * @throws Exception
	 */
	public ParametroValuado getParametroValuadoFromTipoParametro(DocHabilitanteEspecializado pDocumentoHabilitanteEspecializado, TipoParametro pTipoParametro, CuotaLiquidacion pCuota,
			Integer pNumeroCuota, long pIdRegistroDeuda) throws Exception;

	public void desactivarTasasTGIRestantes(RegistroDeuda cadaRegistroDeuda, Tasa locTasa) throws Exception;

	public boolean comprobarObra(Obra pObra) throws Exception;

	public Vencimiento[] calcularVencimientos(LiquidacionTasa locLiquidacionTasa, Calendar pFechaInicioPeriodo) throws Exception;

	public void eliminarLiquidacionesFisicamente(List<LiquidacionTasa> pListaLiquidacion, String comentario);

	public void addRegistroCancelacionManual(List<LiquidacionTasa> pListaLiquidacion, String comentario, Usuario pUsuario);

	public void updateLiquidacionTasa(List<LiquidacionTasa> pListaLiquidaciones, String comentario) throws Exception;

	public void marcarImpaga(List<LiquidacionTasa> pListaLiquidacion, String comentario);

	public FiltroLiquidacionAutomotor findListaLiquidacionesAutomotor(FiltroLiquidacionAutomotor pFiltro) throws Exception;

	public FiltroLiquidacionCementerio findListaLiquidacionesCementerio(FiltroLiquidacionCementerio pFiltro) throws Exception;

	public List findListaDocsGeneradoresDeuda(Obligacion pObligacion);

	public ResultadoLiquidacion liquidarAutomotor(Vehiculo pVehiculo, Persona pPersona, CuotaLiquidacion pCuota) throws Exception;

	public ResultadoLiquidacion liquidarCementerio(ParcelaCementerio pParcelaCementerio, CuotaLiquidacion pCuota, Persona pPersona, TipoSepultura pTipoSepultura) throws Exception;

	public RegistroDeuda volverAtrasDeuda(RegistroCancelacion pRegistroCancelacion);
	
	public FiltroCobroExterno findListaCobroExterno(FiltroCobroExterno pFiltro);
	
	public CobroExterno getCobroExternoById(Long pIdCobroExterno) throws Exception;
	
	public void procesarArchivoCobroExterno(File pArchivo, EntidadRecaudadora pEntidadRecaudadora) throws Exception;
	
	public String getCodigoClientePagoFacil(Long pIdLiquidacionTasa);
	
	public void generarLogLiquidacion(LiquidacionTasa pLiquidacionTasa, Usuario pUsuario, LogLiquidacion.Evento pEvento, String comentario);
	
	public List<LogLiquidacion> getListaLogLiquidacion(FiltroLogLiquidacion pFiltro) throws Exception;

	public LogLiquidacion getLogLiquidacionesPorId(long pId) throws Exception;
	
	public FiltroLogLiquidacion findListaLogLiquidacion(FiltroLogLiquidacion pFiltro);
	
	public FiltroLiquidacionArrendamiento findListaLiquidacionesArrendamiento(FiltroLiquidacionArrendamiento pFiltro) throws Exception;
	
	public ResultadoLiquidacion liquidarArrendamiento(CuotaLiquidacion[] pCuotas, Persona pPersona, Parcela pParcela, Boolean pIgnorarPlan) throws Exception;
}
