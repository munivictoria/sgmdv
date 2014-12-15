
package com.trascender.framework.util;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.trascender.criterio.clases.Criterio;
import ar.trascender.criterio.clases.Orden;
import ar.trascender.criterio.clases.Proyeccion;
import ar.trascender.util.ReflectionUtils;

import com.trascender.framework.recurso.persistent.ConfiguracionAtributoTabla;
import com.trascender.framework.recurso.persistent.ConfiguracionRecurso;
import com.trascender.framework.recurso.persistent.ConjuntoAtributoTabla;

public abstract class FiltroAbstracto<T> implements Serializable {

	public static final int ASC = 55654647;
	public static final int DESC = 54347354;

	private Integer numeroPagina;
	private Integer cantidadPorPagina;

	private Long cantidadFilasTotales = 0l;

	private Map<String, Integer> mapaOrden = new HashMap<String, Integer>();

	private List<T> listaResultados;

	private List<BusquedaPorLog> listaBusquedaPorLogs = new ArrayList<BusquedaPorLog>();

	private boolean buscarPorLogs = false;

	private ConfiguracionRecurso configuracionRecurso;

	public Long getCantidadFilasTotales() {
		return cantidadFilasTotales;
	}

	public void setCantidadFilasTotales(Long pCantidadFilasTotales) {
		cantidadFilasTotales = pCantidadFilasTotales;
	}

	public List<T> getListaResultados() {
		return listaResultados;
	}

	public void setListaResultados(List<T> pListaResultados) {
		listaResultados = pListaResultados;
	}

	public Integer getNumeroPagina() {
		return numeroPagina;
	}

	public void setNumeroPagina(Integer pNumeroPagina) {
		numeroPagina = pNumeroPagina;
	}

	public Integer getCantidadPorPagina() {
		return cantidadPorPagina;
	}

	public void setCantidadPorPagina(Integer pCantidadPorPagina) {
		cantidadPorPagina = pCantidadPorPagina;
	}

	public Map<String, Integer> getMapaOrden() {
		return mapaOrden;
	}

	public void setMapaOrden(Map<String, Integer> pMapaOrden) {
		mapaOrden = pMapaOrden;
	}

	public List<BusquedaPorLog> getListaBusquedaPorLogs() {
		return listaBusquedaPorLogs;
	}

	public void setListaBusquedaPorLogs(List<BusquedaPorLog> pListaBusquedaPorLogs) {
		listaBusquedaPorLogs = pListaBusquedaPorLogs;
	}

	public ConfiguracionRecurso getConfiguracionRecurso() {
		return configuracionRecurso;
	}

	public void setConfiguracionRecurso(ConfiguracionRecurso pConfiguracionRecurso) {
		configuracionRecurso = pConfiguracionRecurso;
	}

	public void procesarYListar(final Criterio pCriterio) {
		Proyeccion locProyeccion = pCriterio.getProyeccion();

		// Seteo la proyeccion Count para saber la cantidad de resultados totales, sin paginar.
		pCriterio.setProyeccion(Proyeccion.PROP("COUNT (DISTINCT e)").SIN_PROCESAR_ENTIDADES());
		Long locCantidadFilasTotales = pCriterio.uniqueResult();
		this.setCantidadFilasTotales(locCantidadFilasTotales);

		// Remuevo la proyeccion COUNT
		pCriterio.setProyeccion(locProyeccion);

		// Seteo las ordenes correspondientes
		this.addOrdenes(pCriterio);
		// Añado la paginacion correspondiente, si aplica.
		this.addPaginacion(pCriterio);

		this.listaResultados = pCriterio.list();

		try {
			refrescarPropiedadesConfRecurso(this.listaResultados);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void refrescarPropiedadesConfRecurso(List<T> pLista) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {
		if(this.configuracionRecurso != null && !configuracionRecurso.getListaConjuntoAtributosTabla().isEmpty() && !pLista.isEmpty()) {
			T objetoEjemplo = pLista.get(0);
			Class claseEjemplo = objetoEjemplo.getClass();
			for(ConjuntoAtributoTabla cadaConjunto : configuracionRecurso.getListaConjuntoAtributosTabla()) {
				List<Method> listaGeters = new ArrayList<Method>();
				for(ConfiguracionAtributoTabla cadaConf : cadaConjunto.getListaAtributosTabla()) {
					listaGeters.add(ReflectionUtils.getGeter(claseEjemplo, cadaConf.getNombreAtributo()));
				}

				for(Method cadaMethod : listaGeters) {
					for(T cadaT : pLista) {
						Object objeto = cadaMethod.invoke(cadaT);
						if (objeto != null) objeto.toString();
					}
				}
			}
		}
	}

	public void addOrdenes(Criterio pCriterio) {
		for(String cadaLlave : mapaOrden.keySet()) {
			int orden = mapaOrden.get(cadaLlave);
			if(orden == ASC) {
				pCriterio.add(Orden.ASC(cadaLlave));
			} else {
				pCriterio.add(Orden.DESC(cadaLlave));
			}
		}
	}

	public void addPaginacion(Criterio pCriterio) {
		if(cantidadPorPagina != null && numeroPagina != null) {
			pCriterio.setFirstResult(cantidadPorPagina * (numeroPagina - 1));
			pCriterio.setMaxResults(cantidadPorPagina);
		}
	}

	public boolean isBuscarPorLogs() {
		return buscarPorLogs;
	}

	public void setBuscarPorLogs(boolean pBuscarPorLogs) {
		buscarPorLogs = pBuscarPorLogs;
	}

	public Date getPrimerDiaAnioActual() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.MONTH, 0);

		return calendar.getTime();
	}
}
