package com.trascender.framework.system.interfaces;



import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.Remote;

import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.filtros.FiltroConfiguracionRecurso;
import com.trascender.framework.recurso.filtros.FiltroPlantillaAtributosDinamicos;
import com.trascender.framework.recurso.filtros.FiltroReportesJasper;
import com.trascender.framework.recurso.persistent.ConfiguracionRecurso;
import com.trascender.framework.recurso.persistent.ParametroSistema;
import com.trascender.framework.recurso.persistent.ReportesJasper;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
import com.trascender.framework.recurso.persistent.dinamicos.PlantillaAtributoDinamico;
import com.trascender.framework.recurso.persistent.validacionDinamica.ComponenteValidacion;
import com.trascender.framework.recurso.transients.AtributoConsultable;
@Remote
public interface SystemParametro {

	public static final String JNDI_NAME = "ejb/SystemParametro/remote";

	public ParametroSistema getParametroPorId(long pId) 
			throws com.trascender.framework.exception.TrascenderException;

	public ParametroSistema getParametroPorCodigo(String pCodigo)
			throws com.trascender.framework.exception.TrascenderException;

	public ParametroSistema getParametroPorNombre(String nombre)
			throws com.trascender.framework.exception.TrascenderException;

	public void addParametro(ParametroSistema pParametro)
			throws com.trascender.framework.exception.TrascenderException;

	public void updateParametro(ParametroSistema pParametro)
			throws com.trascender.framework.exception.TrascenderException;

	public void deleteParametro(ParametroSistema pParametro)
			throws com.trascender.framework.exception.TrascenderException;

	public void addConfiguracionRecurso(ConfiguracionRecurso pConfiguracion)
			throws com.trascender.framework.exception.TrascenderException;

	public void updateConfiguracionRecurso(ConfiguracionRecurso pConfiguracion)
			throws com.trascender.framework.exception.TrascenderException;

	public void deleteConfiguracionRecurso(ConfiguracionRecurso pConfiguracion)
			throws com.trascender.framework.exception.TrascenderException;

	public ConfiguracionRecurso getConfiguracionRecursoPorId(Long pIdConfiguracion)
			throws com.trascender.framework.exception.TrascenderException;

	public ConfiguracionRecurso getConfiguracionRecursoPorNombre(String pNombre) 
			throws com.trascender.framework.exception.TrascenderException;

	public ConfiguracionRecurso getConfiguracionPorRecurso(Long pIdRecurso)
			throws com.trascender.framework.exception.TrascenderException;

	public PlantillaAtributoDinamico addPlantillaAtributoDinamico(PlantillaAtributoDinamico pPlantilla) throws TrascenderException;
	public PlantillaAtributoDinamico updatePlantillaAtributoDinamico(PlantillaAtributoDinamico pPlantilla) throws TrascenderException;
	public void deletePlantillaAtributoDinamico(PlantillaAtributoDinamico pPlantilla) throws TrascenderException;
	public FiltroPlantillaAtributosDinamicos findListaPlantillaAtritbutosDinamicos(FiltroPlantillaAtributosDinamicos filtro) throws TrascenderException;
	public List<AtributoDinamico<?>> getAtributosPorRecurso(Long pRecurso, List<AtributoDinamico<?>> pListaActual, Boolean pBusqueda) throws TrascenderException;
	public PlantillaAtributoDinamico getPlantillaAtributoDinamicoPorId(long pIdPlantilla) throws java.lang.Exception;

	public Set<AtributoConsultable> getAtributosConsultablePorRecurso(Long pIdRecurso);
	public void addValidacionARecurso(ComponenteValidacion pComponente, Long pIdRecurso) throws Exception;
	public List<ConfiguracionRecurso> getListaTodasConfiguracionRecurso() throws TrascenderException;
	public FiltroConfiguracionRecurso findListaConfiguracionRecurso(FiltroConfiguracionRecurso pFiltro) throws Exception;

	public byte[] getLogoMunicipalidad();

	public void setLlave(long pLlave);

	public List<String> getListaNombresPropiedadesLogs(long pSerialVersionUID);
	public Map<String, Usuario> getMapaUsuariosLogs(long pSerialVersionUID);
	
	public ReportesJasper addReporteJasper(ReportesJasper pReporteJasper) throws TrascenderException;
	public ReportesJasper updateReporteJasper(ReportesJasper pReporteJasper) throws TrascenderException;
	public ReportesJasper getReporteJasperPorId(long pIdReporte) throws java.lang.Exception;
	public FiltroReportesJasper findListaReportesJasper(FiltroReportesJasper filtro) throws TrascenderException;
}
