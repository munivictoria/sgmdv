package com.trascender.framework.business.interfaces;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.Local;
import javax.persistence.Query;

import ar.trascender.criterio.clases.Criterio;
import ar.trascender.criterio.clases.Restriccion;

import com.trascender.framework.recurso.filtros.FiltroConfiguracionRecurso;
import com.trascender.framework.recurso.filtros.FiltroPlantillaAtributosDinamicos;
import com.trascender.framework.recurso.filtros.FiltroProcesoDB;
import com.trascender.framework.recurso.filtros.FiltroReportesJasper;
import com.trascender.framework.recurso.persistent.ConfiguracionAccesosDirectos;
import com.trascender.framework.recurso.persistent.ConfiguracionRecurso;
import com.trascender.framework.recurso.persistent.ParametroSistema;
import com.trascender.framework.recurso.persistent.ProcesoDB;
import com.trascender.framework.recurso.persistent.ReportesJasper;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
import com.trascender.framework.recurso.persistent.dinamicos.PlantillaAtributoDinamico;
import com.trascender.framework.recurso.persistent.validacionDinamica.ComponenteValidacion;

@Local
public interface BusinessParametroLocal {

	public final static String JNDI_NAME = "ejb/BusinessParametro";

	public ParametroSistema getParametroPorId(long pIdParametro)throws java.lang.Exception;

	public ParametroSistema getParametroPorCodigo(String pCodigo) throws java.lang.Exception;

	public ParametroSistema getParametroPorNombre(String nombre) throws java.lang.Exception;

	public void addParametro(ParametroSistema pParametro) throws java.lang.Exception;

	public void updateParametro(ParametroSistema pParametro) throws java.lang.Exception;

	public void deleteParametro(ParametroSistema pParametro) throws java.lang.Exception;

	public ConfiguracionRecurso getConfiguracionRecursoPorId(Long pIdConfiguracion) throws Exception;

	public ConfiguracionRecurso getConfiguracionRecursoPorNombre(String pNombre) throws Exception;

	public ConfiguracionRecurso getConfiguracionPorRecurso(Long pIdRecurso) throws Exception;

	public void addConfiguracionRecurso(ConfiguracionRecurso pConfiguracion) throws java.lang.Exception;

	public void updateConfiguracionRecurso(ConfiguracionRecurso pConfiguracion) throws java.lang.Exception;

	public void deleteConfiguracionRecurso(ConfiguracionRecurso pConfiguracion) throws java.lang.Exception;

	public PlantillaAtributoDinamico addPlantillaAtributoDinamico(PlantillaAtributoDinamico pPlantilla) throws Exception;
	public PlantillaAtributoDinamico updatePlantillaAtributoDinamico(PlantillaAtributoDinamico pPlantilla) throws Exception;
	public void deletePlantillaAtributoDinamico(PlantillaAtributoDinamico pPlantilla) throws Exception;
	public FiltroPlantillaAtributosDinamicos findListaPlantillaAtritbutosDinamicos(FiltroPlantillaAtributosDinamicos filtro) throws Exception;
	public PlantillaAtributoDinamico getPlantillaAtributoDinamicoPorId(long pIdPlantilla) throws java.lang.Exception;
	/**
	 * Devuelve un listado de ID's de entidades que tengan Atributos Dinamicos con valores iguales
	 * a la lista recibida como parametro. Ideal para usar como subconsulta.
	 * @return El listado de ID's correspondientes,  
	 * NULL si ningun AtributoDinamico fue valido (no se deberia incluir la subconsulta)
	 * 	
	 */
	public Set<Long> findListaIdsEntidad(long pIdRecurso, List<AtributoDinamico<?>> pListadoAtributosDinamicos);

	public void addValidacionARecurso(ComponenteValidacion pComponente, Long pIdRecurso) throws Exception;

	public FiltroConfiguracionRecurso findListaConfiguracionRecurso(FiltroConfiguracionRecurso pFiltro);

	public List<String> getListaNombresPropiedadesLogs(long pSerialVersionUID);
	public Map<String, Usuario> getMapaUsuariosLogs(long pSerialVersionUID);

	public ReportesJasper addReporteJasper(ReportesJasper pReporteJasper) throws Exception;
	public ReportesJasper updateReporteJasper(ReportesJasper pReporteJasper) throws Exception;
	public ReportesJasper getReporteJasperPorId(long pIdReporte) throws java.lang.Exception;
	public FiltroReportesJasper findListaReportesJasper(FiltroReportesJasper filtro) throws Exception;
	
	public FiltroProcesoDB findListaProcesosDB(FiltroProcesoDB pFiltro);
	
	public String ejecutarProcesoDB(Long idProceso, String parametros);
	
	public ConfiguracionAccesosDirectos getConfiguracionPorUsuario(Long idUsuario);

	public void addAccesoDirecto(Long pIdRecurso, Usuario pUsuario);
}
