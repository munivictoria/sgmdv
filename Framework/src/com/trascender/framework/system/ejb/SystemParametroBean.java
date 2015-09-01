
package com.trascender.framework.system.ejb;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import net.sf.jasperreports.engine.JasperPrint;

import com.trascender.framework.business.interfaces.BusinessParametroLocal;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.exception.TrascenderFrameworkException;
import com.trascender.framework.recurso.filtros.FiltroConfiguracionRecurso;
import com.trascender.framework.recurso.filtros.FiltroNumerador;
import com.trascender.framework.recurso.filtros.FiltroPlantillaAtributosDinamicos;
import com.trascender.framework.recurso.filtros.FiltroProcesoDB;
import com.trascender.framework.recurso.filtros.FiltroReporte;
import com.trascender.framework.recurso.filtros.FiltroReportesJasper;
import com.trascender.framework.recurso.persistent.ConfiguracionAccesosDirectos;
import com.trascender.framework.recurso.persistent.ConfiguracionRecurso;
import com.trascender.framework.recurso.persistent.Numerador;
import com.trascender.framework.recurso.persistent.ParametroSistema;
import com.trascender.framework.recurso.persistent.Permiso;
import com.trascender.framework.recurso.persistent.Permiso.Accion;
import com.trascender.framework.recurso.persistent.ProcesoDB;
import com.trascender.framework.recurso.persistent.ReportesJasper;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
import com.trascender.framework.recurso.persistent.dinamicos.PlantillaAtributoDinamico;
import com.trascender.framework.recurso.persistent.reporteDinamico.Reporte;
import com.trascender.framework.recurso.persistent.validacionDinamica.ComponenteValidacion;
import com.trascender.framework.recurso.transients.AtributoConsultable;
import com.trascender.framework.recurso.transients.Recurso;
import com.trascender.framework.system.interfaces.SystemParametro;
import com.trascender.framework.util.SecurityMgr;

@Stateful(name = "ejb/SystemParametro")
public class SystemParametroBean implements SystemParametro {

	@EJB
	private final BusinessParametroLocal parametroLocal = null;

	private long llave = 0;

	public ParametroSistema getParametroPorId(long pIdParametro) throws TrascenderException {
		try {
			return parametroLocal.getParametroPorId(pIdParametro);

		} catch(TrascenderFrameworkException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TrascenderFrameworkException(923);
		}
	}

	public ParametroSistema getParametroPorCodigo(String pCodigo) throws TrascenderException {

		try {
			return parametroLocal.getParametroPorCodigo(pCodigo);
		} catch(TrascenderFrameworkException e) {
			e.printStackTrace();
			throw e;

		} catch(Exception e) {
			e.printStackTrace();
			throw new TrascenderFrameworkException(923);
		}
	}

	public void addParametro(ParametroSistema pParametro) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, ConfiguracionRecurso.serialVersionUID, Accion.INSERT)) {
				try {
					parametroLocal.addParametro(pParametro);
				} catch(TrascenderException e) {
					e.printStackTrace();
					throw e;
				} catch(Exception locE) {
					locE.printStackTrace();
					throw new TrascenderFrameworkException(999);
				}
			} else {
				throw new TrascenderFrameworkException(805);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}

	}

	public void updateParametro(ParametroSistema pParametro) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, ConfiguracionRecurso.serialVersionUID, Accion.UPDATE)) {
				try {
					parametroLocal.updateParametro(pParametro);
				} catch(TrascenderException e) {
					e.printStackTrace();
					throw e;
				} catch(Exception locE) {
					locE.printStackTrace();
					throw new TrascenderFrameworkException(999);
				}
			} else {
				throw new TrascenderFrameworkException(805);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}

	}

	public void deleteParametro(ParametroSistema pParametro) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, ConfiguracionRecurso.serialVersionUID, Accion.DELETE)) {
				try {
					parametroLocal.deleteParametro(pParametro);
				} catch(TrascenderException e) {
					e.printStackTrace();
					throw e;
				} catch(Exception locE) {
					locE.printStackTrace();
					throw new TrascenderFrameworkException(999);
				}
			} else {
				throw new TrascenderFrameworkException(805);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public ParametroSistema getParametroPorNombre(String pNombre) throws TrascenderException {
		try {
			return parametroLocal.getParametroPorNombre(pNombre);
		} catch(TrascenderFrameworkException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TrascenderFrameworkException(922);
		}
	}

	public PlantillaAtributoDinamico addPlantillaAtributoDinamico(PlantillaAtributoDinamico pPlantilla) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, PlantillaAtributoDinamico.serialVersionUID, Accion.INSERT)) {
				return parametroLocal.addPlantillaAtributoDinamico(pPlantilla);
			} else {
				throw new TrascenderFrameworkException(805);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception locE) {
			locE.printStackTrace();
			throw new TrascenderFrameworkException(999);
		}

	}

	public PlantillaAtributoDinamico updatePlantillaAtributoDinamico(PlantillaAtributoDinamico pPlantilla) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, PlantillaAtributoDinamico.serialVersionUID, Accion.UPDATE)) {
				System.out.println("Tamanio de la lista en logica " + pPlantilla.getListaOpciones().size());
				return parametroLocal.updatePlantillaAtributoDinamico(pPlantilla);
			} else {
				throw new TrascenderFrameworkException(805);
			}
		} catch(TrascenderFrameworkException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TrascenderFrameworkException(941);
		}

	}

	public void deletePlantillaAtributoDinamico(PlantillaAtributoDinamico pPlantilla) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, PlantillaAtributoDinamico.serialVersionUID, Accion.DELETE)) {
				parametroLocal.deletePlantillaAtributoDinamico(pPlantilla);
			} else {
				throw new TrascenderFrameworkException(805);
			}
		} catch(TrascenderFrameworkException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TrascenderFrameworkException(942);
		}
	}

	public FiltroPlantillaAtributosDinamicos findListaPlantillaAtritbutosDinamicos(FiltroPlantillaAtributosDinamicos filtro) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, PlantillaAtributoDinamico.serialVersionUID, Accion.SELECT)) {
				return parametroLocal.findListaPlantillaAtritbutosDinamicos(filtro);
			} else {
				throw new TrascenderFrameworkException(805);
			}
		} catch(TrascenderFrameworkException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TrascenderFrameworkException(943);
		}
	}

	public FiltroReportesJasper findListaReportesJasper(FiltroReportesJasper filtro) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, FiltroReportesJasper.serialVersionUID, Accion.SELECT)) {
				return parametroLocal.findListaReportesJasper(filtro);
			} else {
				throw new TrascenderFrameworkException(805);
			}
		} catch(TrascenderFrameworkException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TrascenderFrameworkException(1990);
		}
	}

	public PlantillaAtributoDinamico getPlantillaAtributoDinamicoPorId(long pIdPlantilla) throws Exception {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, PlantillaAtributoDinamico.serialVersionUID, Accion.SELECT)) {
				return parametroLocal.getPlantillaAtributoDinamicoPorId(pIdPlantilla);
			} else {
				throw new TrascenderFrameworkException(805);
			}
		} catch(TrascenderFrameworkException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TrascenderFrameworkException(943);
		}
	}

	public ReportesJasper getReporteJasperPorId(long pIdReporte) throws Exception {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, ReportesJasper.serialVersionUID, Accion.SELECT)) {
				return parametroLocal.getReporteJasperPorId(pIdReporte);
			} else {
				throw new TrascenderFrameworkException(805);
			}
		} catch(TrascenderFrameworkException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TrascenderFrameworkException(943);
		}
	}

	// Esto esta mal.. hay que pornerlo en el Business
	public List<AtributoDinamico<?>> getAtributosPorRecurso(Long pRecurso, List<AtributoDinamico<?>> pListaActual, Boolean pBusqueda) throws TrascenderException {
		if(pListaActual == null) {
			pListaActual = new ArrayList<AtributoDinamico<?>>();
		}
		List<AtributoDinamico<?>> locListaResultado = new ArrayList<AtributoDinamico<?>>(pListaActual);
		FiltroPlantillaAtributosDinamicos filtro = new FiltroPlantillaAtributosDinamicos();
		filtro.setIdRecurso(pRecurso);
		filtro.setBusqueda(pBusqueda);
		// List<PlantillaAtributoDinamico> locListaPlantillas = this.findListaPlantillaAtritbutosDinamicos(filtro).getListaResultados();
		// No debo tener permisos de Seleccion de Atributos Dinamicos para hacer esta consulta.
		List<PlantillaAtributoDinamico> locListaPlantillas = null;
		try {
			locListaPlantillas = this.parametroLocal.findListaPlantillaAtritbutosDinamicos(filtro).getListaResultados();
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		for(PlantillaAtributoDinamico cadaPlantilla : locListaPlantillas) {
			boolean esta = false;
			for(AtributoDinamico<?> cadaAtributo : pListaActual) {
				if(cadaAtributo.getPlantilla().equals(cadaPlantilla)) {
					esta = true;
					break;
				}
			}
			if(!esta) {
				// No tiene un atributo para esa plantilla
				AtributoDinamico<?> locAtributo = cadaPlantilla.generarAtributoDinamico();
				locListaResultado.add(locAtributo);
			}
		}
		Collections.sort(locListaResultado);
		return locListaResultado;
	}

	public void addConfiguracionRecurso(ConfiguracionRecurso pConfiguracion) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, ConfiguracionRecurso.serialVersionUID, Accion.INSERT)) {
				this.parametroLocal.addConfiguracionRecurso(pConfiguracion);
			} else {
				throw new TrascenderFrameworkException(805);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception locE) {
			locE.printStackTrace();
			throw new TrascenderFrameworkException(999);
		}
	}

	public void updateConfiguracionRecurso(ConfiguracionRecurso pConfiguracion) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, ConfiguracionRecurso.serialVersionUID, Accion.UPDATE)) {
				this.parametroLocal.updateConfiguracionRecurso(pConfiguracion);
			} else {
				throw new TrascenderFrameworkException(805);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception locE) {
			locE.printStackTrace();
			throw new TrascenderFrameworkException(999);
		}
	}

	public void deleteConfiguracionRecurso(ConfiguracionRecurso pConfiguracion) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, ConfiguracionRecurso.serialVersionUID, Accion.DELETE)) {
				this.parametroLocal.deleteConfiguracionRecurso(pConfiguracion);
			} else {
				throw new TrascenderFrameworkException(805);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception locE) {
			locE.printStackTrace();
			throw new TrascenderFrameworkException(999);
		}
	}

	public ConfiguracionRecurso getConfiguracionRecursoPorId(Long pIdConfiguracion) throws TrascenderException {
		try {
			return this.parametroLocal.getConfiguracionRecursoPorId(pIdConfiguracion);
		} catch(Exception locE) {
			locE.printStackTrace();
			throw new TrascenderFrameworkException(9999);
		}

	}

	public ConfiguracionRecurso getConfiguracionRecursoPorNombre(String pNombre) throws TrascenderException {
		try {
			return this.parametroLocal.getConfiguracionRecursoPorNombre(pNombre);
		} catch(TrascenderException locE) {
			locE.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
			throw new TrascenderFrameworkException(9999);
		}
		return null;
	}

	public ConfiguracionRecurso getConfiguracionPorRecurso(Long pIdRecurso) throws TrascenderException {
		try {
			return this.parametroLocal.getConfiguracionPorRecurso(pIdRecurso);
		} catch(TrascenderException locE) {
			locE.printStackTrace();
		} catch(Exception locE) {
			locE.printStackTrace();
			throw new TrascenderFrameworkException(9999);
		}
		return null;
	}

	// Es temporal, lo correcto es buscar la configuracion recurso y despues los mostrables.
	public Set<AtributoConsultable> getAtributosConsultablePorRecurso(Long pIdRecurso) {
		Recurso locRecurso = (Recurso) SecurityMgr.getInstance().getRecursoBySerial(pIdRecurso);
		if(locRecurso == null) {
			System.out.println("recuso nulo, id: " + pIdRecurso);
		}

		return locRecurso.getAtributosConsultables();
	}

	public void addValidacionARecurso(ComponenteValidacion pComponente, Long pIdRecurso) throws Exception {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, ConfiguracionRecurso.serialVersionUID, Accion.INSERT)) {
				this.parametroLocal.addValidacionARecurso(pComponente, pIdRecurso);
			} else {
				throw new TrascenderFrameworkException(805);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception locE) {
			locE.printStackTrace();
			throw new TrascenderFrameworkException(999);
		}

	}

	public void setLlave(long pLlave) {
		this.llave = pLlave;
	}

	public FiltroConfiguracionRecurso findListaConfiguracionRecurso(FiltroConfiguracionRecurso pFiltro) throws Exception {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, ConfiguracionRecurso.serialVersionUID, Accion.SELECT)) {
				return parametroLocal.findListaConfiguracionRecurso(pFiltro);
			} else {
				throw new TrascenderFrameworkException(805);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception locE) {
			locE.printStackTrace();
			throw new TrascenderFrameworkException(999);
		}
	}

	/**
	 * Devuelve todas las configuracion Recurso de la base.
	 * 
	 * @return
	 */
	public List<ConfiguracionRecurso> getListaTodasConfiguracionRecurso() {
		try {
			return this.parametroLocal.findListaConfiguracionRecurso(new FiltroConfiguracionRecurso()).getListaResultados();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public byte[] getLogoMunicipalidad() {
		return SecurityMgr.getInstance().getMunicipalidad().getLogo();
	}

	public List<String> getListaNombresPropiedadesLogs(long pSerialVersionUID) {
		return this.parametroLocal.getListaNombresPropiedadesLogs(pSerialVersionUID);
	}

	public Map<String, Usuario> getMapaUsuariosLogs(long pSerialVersionUID) {
		return this.parametroLocal.getMapaUsuariosLogs(pSerialVersionUID);
	}

	public ReportesJasper addReporteJasper(ReportesJasper pReporteJasper) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, ReportesJasper.serialVersionUID, Accion.INSERT)) {
				return parametroLocal.addReporteJasper(pReporteJasper);
			} else {
				throw new TrascenderFrameworkException(805);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception locE) {
			locE.printStackTrace();
			throw new TrascenderFrameworkException(999);
		}
	}

	public ReportesJasper updateReporteJasper(ReportesJasper pReporteJasper) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, ReportesJasper.serialVersionUID, Accion.UPDATE)) {
				return parametroLocal.updateReporteJasper(pReporteJasper);
			} else {
				throw new TrascenderFrameworkException(805);
			}
		} catch(TrascenderFrameworkException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TrascenderFrameworkException(941);
		}

	}
	
	public FiltroProcesoDB findListaProcesosDB(FiltroProcesoDB pFiltro) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, ProcesoDB.serialVersionUID, Accion.SELECT)) {
				return parametroLocal.findListaProcesosDB(pFiltro);
			} else {
				throw new TrascenderFrameworkException(805);
			}
		} catch(Exception locE) {
			locE.printStackTrace();
			throw new TrascenderFrameworkException(999);
		}
	}
	
	public String ejecutarProcesoDB(Long idProceso, String parametros) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, ProcesoDB.serialVersionUID, Accion.UPDATE)) {
				return parametroLocal.ejecutarProcesoDB(idProceso, parametros);
			} else {
				throw new TrascenderFrameworkException(805);
			}
		} catch(Exception locE) {
			locE.printStackTrace();
			throw new TrascenderFrameworkException(999);
		}
	}

	public ConfiguracionAccesosDirectos getConfiguracionPorUsuario(
			Long pIdUsuario) throws TrascenderException {
		return parametroLocal.getConfiguracionPorUsuario(pIdUsuario);
	}

	public void addAccesoDirecto(Long pIdRecurso) throws TrascenderFrameworkException{
		Usuario locUsuario = SecurityMgr.getInstance().getUsuario(llave);
		parametroLocal.addAccesoDirecto(pIdRecurso, locUsuario);
	}
	
	@Override
	public void addReporte(Reporte pReporte) throws Exception {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, Reporte.serialVersionUID, Permiso.Accion.INSERT)) {
				this.parametroLocal.addReporte(pReporte);
			} else {
				throw new TrascenderFrameworkException(805);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TrascenderFrameworkException(960);
		}
	}

	@Override
	public Reporte updateReporte(Reporte pReporte) throws Exception {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, Reporte.serialVersionUID, Permiso.Accion.UPDATE)) {
				return this.parametroLocal.updateReporte(pReporte);
			} else {
				throw new TrascenderFrameworkException(805);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TrascenderFrameworkException(961);
		}
	}

	@Override
	public void deleteReporte(Reporte pReporte) throws Exception {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, Reporte.serialVersionUID, Permiso.Accion.DELETE)) {
				this.parametroLocal.deleteReporte(pReporte);
			} else {
				throw new TrascenderFrameworkException(805);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TrascenderFrameworkException(963);
		}
	}

	@Override
	public Reporte getReporteByID(Long pIdReporte) throws Exception {
		try {
			if(SecurityMgr.getInstance().getUsuario(llave) != null) {
				return this.parametroLocal.getReporteByID(pIdReporte);
			} else {
				throw new TrascenderFrameworkException(805);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TrascenderFrameworkException(964);
		}
	}

	@Override
	public FiltroReporte findListaReporte(FiltroReporte pFiltro) throws Exception {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, Reporte.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.parametroLocal.findListaReporte(pFiltro);
			} else {
				throw new TrascenderFrameworkException(805);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TrascenderFrameworkException(962);
		}
	}

	public List<Reporte> getListaMenuReporte(Usuario pUsuarioLogueado) {
		return this.parametroLocal.getListaMenuReporte(pUsuarioLogueado);
	}

	public JasperPrint getReporte(Reporte pReporte, Map<String, Object> pMapaParametros) throws Exception {
		Usuario locUsuario = SecurityMgr.getInstance().getUsuario(this.llave);
		pMapaParametros.put("P_USUARIO", locUsuario);
		pMapaParametros.put("P_ID_USUARIO", locUsuario.getIdUsuario());
		return this.parametroLocal.getReporte(pReporte, pMapaParametros);
	}

	public List<Reporte> getListaReportesPorUsuario(long idUsuario) throws Exception {
		return this.parametroLocal.getListaReportesPorUsuario(idUsuario);
	}
	
	public Numerador addNumerador(Numerador pNumerador) throws TrascenderFrameworkException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, Numerador.serialVersionUID, Permiso.Accion.INSERT)) {
				return this.parametroLocal.addNumerador(pNumerador);
			} else {
				throw new TrascenderFrameworkException(805);
			}
		} catch(TrascenderFrameworkException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TrascenderFrameworkException(520);
		}
	}

	public Numerador updateNumerador(Numerador pNumerador) throws TrascenderFrameworkException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, Numerador.serialVersionUID, Permiso.Accion.UPDATE)) {
				return this.parametroLocal.updateNumerador(pNumerador);
			} else {
				throw new TrascenderFrameworkException(805);
			}
		} catch(TrascenderFrameworkException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TrascenderFrameworkException(521);
		}
	}

	public void removeNumerador(Numerador pNumerador) throws TrascenderFrameworkException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, Numerador.serialVersionUID, Permiso.Accion.DELETE)) {
				this.parametroLocal.removeNumerador(pNumerador);
			} else {
				throw new TrascenderFrameworkException(805);
			}
		} catch(TrascenderFrameworkException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TrascenderFrameworkException(522);
		}
	}

	public FiltroNumerador findListaNumeradores(FiltroNumerador pFiltro) throws RemoteException, Exception {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, Numerador.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.parametroLocal.findListaNumeradores(pFiltro);
			} else
				throw new TrascenderFrameworkException(805);
		} catch(TrascenderFrameworkException e) {
			e.printStackTrace();
			throw new TrascenderFrameworkException(523);
		}
	}

	public Numerador getNumeradorPorId(long pIdNumerador) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, Numerador.serialVersionUID, Accion.SELECT)) {
				return this.parametroLocal.getNumeradorPorId(pIdNumerador);
			} else {
				throw new TrascenderFrameworkException(805);
			}
		} catch(TrascenderException locE) {
			locE.printStackTrace();
			throw new TrascenderFrameworkException(524);
		} catch(Exception locE2) {
			locE2.printStackTrace();
			throw new TrascenderFrameworkException(999);
		}
	}

}
