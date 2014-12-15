
package com.trascender.framework.system.ejb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import com.trascender.framework.business.interfaces.BusinessParametroLocal;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.exception.TrascenderFrameworkException;
import com.trascender.framework.recurso.filtros.FiltroConfiguracionRecurso;
import com.trascender.framework.recurso.filtros.FiltroPlantillaAtributosDinamicos;
import com.trascender.framework.recurso.filtros.FiltroReportesJasper;
import com.trascender.framework.recurso.persistent.ConfiguracionRecurso;
import com.trascender.framework.recurso.persistent.ParametroSistema;
import com.trascender.framework.recurso.persistent.Permiso.Accion;
import com.trascender.framework.recurso.persistent.ReportesJasper;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
import com.trascender.framework.recurso.persistent.dinamicos.PlantillaAtributoDinamico;
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

}
