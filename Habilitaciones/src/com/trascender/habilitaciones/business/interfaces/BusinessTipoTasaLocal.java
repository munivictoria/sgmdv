package com.trascender.habilitaciones.business.interfaces;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import ar.trascender.criterio.clases.Criterio;
import ar.trascender.criterio.clases.Restriccion;

import com.trascender.catastro.recurso.persistent.Zonificacion;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.habilitaciones.recurso.filtros.FiltroPlan;
import com.trascender.habilitaciones.recurso.filtros.FiltroTipoParametroConstante;
import com.trascender.habilitaciones.recurso.filtros.FiltroTipoParametroGrilla;
import com.trascender.habilitaciones.recurso.filtros.FiltroTipoParametroGrupoZona;
import com.trascender.habilitaciones.recurso.filtros.FiltroTipoTasa;
import com.trascender.habilitaciones.recurso.persistent.Plan;
import com.trascender.habilitaciones.recurso.persistent.TipoObligacion;
import com.trascender.habilitaciones.recurso.persistent.TipoParametro;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroAlicuota;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroConstante;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroDinamico;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroGrupoZona;
import com.trascender.habilitaciones.recurso.persistent.TipoTasa;
import com.trascender.habilitaciones.recurso.persistent.tipoParametroGrilla.TipoParametroGrilla;
import com.trascender.habilitaciones.recurso.persistent.tipoParametroGrilla.TipoParametroGrillaFila;

/**
 * Local interface for BusinessTipoTasa.
 */
@Local
public interface BusinessTipoTasaLocal {

	public static final String JNDI_NAME = "ejb/BusinessTipoTasaLocal/local";


	/**
	 * Agrega un tipoTasa
	 * @param pTipoTasa
	 * @return 
	 * @throws Exception
	 */
	public TipoTasa addTipoTasa( TipoTasa pTipoTasa ) throws Exception;

	/**
	 * Actualiza los datos de un tipo de tasa
	 * @param pTipoTasa
	 * @return 
	 * @throws Exception
	 */
	public TipoTasa updateTipoTasa(TipoTasa pTipoTasa ) throws Exception;

	/**
	 * Dá de baja un tipo de parámetro
	 * @param pTipoParametro
	 * @throws Exception
	 */
	public void deleteTipoParametro( TipoParametro pTipoParametro ) throws Exception;

	public void deleteFisicamenteTipoTasa( TipoTasa pTipoTasa ) throws Exception;

	public void deleteTipoTasa( TipoTasa pTipoTasa ) throws Exception;

	/**
	 * Recupera el estado anterior de un tipo de tasa
	 * @param pTipoTasa
	 * @return 
	 * @throws Exception
	 */
	public TipoTasa restoreTipoTasa( TipoTasa pTipoTasa ) throws Exception;

	/**
	 * Recupera el listado de tipos de tasa
	 * @param pNombre
	 * @param pEstado
	 * @return 
	 * @throws Exception
	 */
	public FiltroTipoTasa findListaTipoTasa( FiltroTipoTasa pFiltro) throws Exception;

	/**
	 * Valida la expresión matemática del atributo formula del tipo de tasa pasado por parámetro
	 * @param pTipoTasa
	 * @throws Exception
	 */
	public void validarFormula(TipoTasa pTipoTasa ) throws Exception;

	/**
	 * Obtiene el listado de parámetros parcelarios
	 * @return 
	 * @throws Exception
	 */
	public List getListaParametrosParcelarios(  ) throws Exception;

	/**
	 * Obtiene el listado de parámetros parcelarios
	 * @return 
	 * @throws Exception
	 */
	public List getListaParametrosTGI(  ) throws Exception;

	public TipoParametroConstante addTipoParametroConstante( TipoParametroConstante pTipoParametroConstante ) throws Exception;

	/**
	 * Agrega un grupo de zonas para utilizarlo como parámetro
	 * @param pTipoParametroGrupoZona
	 * @return 
	 * @throws Exception
	 */
	public TipoParametroGrupoZona addTipoParametroGrupoZona( TipoParametroGrupoZona pTipoParametroGrupoZona ) throws Exception;

	public TipoParametroGrupoZona updateTipoParametroGrupoZona( TipoParametroGrupoZona pTipoParametroGrupoZona ) throws Exception;

	/**
	 * Recupera el listado de tipos de parámetros de un grupo zona
	 * @return 
	 * @throws Exception
	 */
	public FiltroTipoParametroGrupoZona findListaTipoParametroGrupoZona(FiltroTipoParametroGrupoZona pFiltro) throws Exception;

	/**
	 * Recupera un tipo de parámetro de grupo de zonas por id (con todos los atributos)
	 * @param pId
	 * @return 
	 * @throws Exception
	 */
	public TipoParametroGrupoZona getTipoParametroGrupoZonaPorId( long pId ) throws Exception;

	/**
	 * Recupera el tipo de parámetro constante por id
	 * @param pId
	 * @return 
	 * @throws Exception
	 */
	public TipoParametroConstante getTipoParametroConstantePorId( long pId ) throws Exception;

	/**
	 * Recupera el listado de parámetros cosntantes
	 * @param pNombre
	 * @return 
	 * @throws Exception
	 */
	public FiltroTipoParametroConstante findListaParametrosConstantes(FiltroTipoParametroConstante pFiltro) throws Exception;

	public List getListaParametrosObra(  ) throws Exception;

	/**
	 * Recupera el listado de atributos de personas
	 * @return 
	 * @throws Exception
	 */
	public List getListaParametrosPersonas(  ) throws Exception;

	/**
	 * Recupera un tipo de tasa con todos los datos asociados
	 * @param pId
	 * @return 
	 * @throws Exception
	 */
	public TipoTasa getTipoTasaPorId( long pId ) throws Exception;

	/**
	 * Recupera el listado de parámetros de OSP y agrega los que se hayan agregado
	 * @return 
	 * @throws Exception
	 */
	public List getListaParametrosOSP(  ) throws Exception;
	
	public List<TipoParametroAlicuota> getListaParametrosAlicuotaOSP();

	/**
	 * Crea un nuevo tipo de parámetro constante
	 * @param pTipoParametro
	 * @return 
	 * @throws Exception
	 */
	public TipoParametroConstante updateTipoParametroConstante(TipoParametroConstante pTipoParametroConstante ) throws Exception;

	/**
	 * Recupera la listad e parámetros de SHPS
	 * @return 
	 * @throws Exception
	 */
	public List getListaParametrosSHPS() throws Exception;
	public List<TipoParametroAlicuota> getListaParametrosAlicuotaSHPS();

	public TipoTasa getTipoTasa(TipoObligacion pTipoObligacion, 
			TipoTasa.Estado pEstadoTipoTasa,
			Integer pCantidadCuotas,
			Plan pPlan) throws Exception;

	/**
	 * Recupera el listado de atributos de personas
	 * @return 
	 * @throws Exception
	 */
	public List getListaParametrosVencimiento(  ) throws Exception;

	/**
	 * Recupera el listado de atributos de personas
	 * @return 
	 * @throws Exception
	 */
	public List getListaParametrosPFO(  ) throws Exception;

	public List<TipoParametroDinamico> getListaParametrosDinamicos(TipoObligacion pTipoObligacion) throws TrascenderException;

	/**
	 * Obtiene el listado de parámetros de cementerio
	 * @return 
	 * @throws Exception
	 */
	public List<TipoParametroAlicuota> getListaParametrosCementerio() throws Exception;

	public List<TipoParametroAlicuota> getListaParametrosParcelaCementerio() throws Exception;

	public List<TipoParametroAlicuota> getListaParametrosTipoSepultura() throws Exception;
	
	public List getListaParametrosAutomotor() throws Exception;
	
	public List getListaParametrosVehiculo() throws Exception;

	public void activarTipoTasa( TipoTasa pTipoTasa, String pComentario, long pLlave ) throws Exception;

	/**
	 * Retorna el valor de la tasa sin modificadores de ningún tipo
	 * @param pTipoTasa
	 * @param pListaValores
	 * @return 
	 * @throws Exception
	 */
	public Double calcularTasa( TipoTasa pTipoTasa, Map pListaValores ) throws Exception;

	public Double calcularTasaAlicuota(TipoTasa pTipoTasa, Map pListaValores) throws Exception;

	public Map calcularModificadoresSobreTasa(TipoTasa pTipoTasa, Map pValores ) throws Exception;

	public Map calcularModificadoresSobreSubtotal( TipoTasa pTipoTasa, Map pValores ) throws Exception;

	public Map calcularVencimientos( Date pFechaLiquidacion, Date pFechaCobro, TipoTasa pTipoTasa, Map pValores ) throws Exception;

	public Map<String, Double> calcularIntereses(Date fechaLiquidacion, Date fechaCobro, TipoTasa tipoTasa, Map<String, Double> valores) throws Exception;

	public void addPlan(Plan pPlan) throws TrascenderException;

	public void updatePlan(Plan pPlan) throws TrascenderException;

	public void deletePlan(Plan pPlan);

	public FiltroPlan findListaPlan(FiltroPlan pFiltro);
	public Plan getPlanPorId(long pId) throws Exception;
	
	public List getListaParametrosDeuda() throws Exception;
	
	public void addTipoParametroGrilla(TipoParametroGrilla pTipoParametro) throws TrascenderException;
	
	public void updateTipoParametroGrilla(TipoParametroGrilla pTipoParametro) throws TrascenderException;
	
	public void deleteTipoParametroGrilla(TipoParametroGrilla pTipoParamatro);
	
	public FiltroTipoParametroGrilla findListaTipoParametroGrilla(FiltroTipoParametroGrilla pFiltro);
	
	public TipoParametroGrilla getTipoParametroGrillaPorId(Long pId);
}
