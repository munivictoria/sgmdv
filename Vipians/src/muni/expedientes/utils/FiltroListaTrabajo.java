package muni.expedientes.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.context.FacesContext;

import muni.CommunicationExpedientesBean;
import muni.SessionBean1;

import com.trascender.expedientes.recurso.persistent.Expediente;
import com.trascender.expedientes.recurso.persistent.FaseCatalogo;
import com.trascender.expedientes.recurso.persistent.NodoExpediente;
import com.trascender.expedientes.recurso.persistent.Procedimiento;
import com.trascender.expedientes.recurso.persistent.Tramite;
import com.trascender.expedientes.recurso.persistent.TramiteCatalogo;
import com.trascender.framework.recurso.persistent.DiaFeriado;
import com.trascender.framework.recurso.persistent.Usuario;

public class FiltroListaTrabajo {

	public List<NodoExpediente> listaExpediente = new ArrayList<NodoExpediente>();
	private List<NodoExpediente> listaResultado = new ArrayList<NodoExpediente>();

	public List<Procedimiento> listaSeleccionProcedimiento = new ArrayList<Procedimiento>();
	public List<FaseCatalogo> listaSeleccionFaseCatalogo = new ArrayList<FaseCatalogo>();
	public List<TramiteCatalogo> listaSeleccionTramiteCatalogo = new ArrayList<TramiteCatalogo>();

	public Map<Object, List<NodoExpediente>> mapProcedimiento = new HashMap<Object, List<NodoExpediente>>();
	public Map<Object, List<NodoExpediente>> mapFaseCatalogo = new HashMap<Object, List<NodoExpediente>>();
	public Map<Object, List<NodoExpediente>> mapTramiteCatalogo = new HashMap<Object, List<NodoExpediente>>();
	public boolean soloVencidos = false;
	public List<DiaFeriado> listaFeriados = new ArrayList<DiaFeriado>();
	private Usuario usuario = getSessionBean1().getUsuario();
	public Object lastSelected = null;

	public List<NodoExpediente> actualizarListaResultado() {
		listaResultado.clear();
		Set<NodoExpediente> set = new HashSet<NodoExpediente>();
		if (!listaSeleccionTramiteCatalogo.isEmpty()) {
			for (TramiteCatalogo tc : listaSeleccionTramiteCatalogo) {
				List<NodoExpediente> listNE = (mapTramiteCatalogo
						.get(MapUtils.getKey(mapTramiteCatalogo, tc)));
				for (NodoExpediente tramite : listNE) {
					set.add(tramite.getNodoPadre().getNodoPadre());
				}
			}
		} else {
			for (List<NodoExpediente> listNE : mapTramiteCatalogo.values()) {
				for (NodoExpediente tramite : listNE) {
					set.add(tramite.getNodoPadre().getNodoPadre());
				}
			}

		}
		listaResultado.addAll(set);
		return listaResultado;
	}

	public List<NodoExpediente> getListaResultado() {
		return listaResultado;
	}

	public void setListaResultado(List<NodoExpediente> listaResultado) {
		this.listaResultado = listaResultado;
	}

	public Map<Object, List<NodoExpediente>> getMapProcedimiento() {
		return mapProcedimiento;
	}

	public void setMapProcedimiento(Map<Object, List<NodoExpediente>> mapProcedimiento) {
		this.mapProcedimiento = mapProcedimiento;
	}

	public Map<Object, List<NodoExpediente>> getMapFaseCatalogo() {
		return mapFaseCatalogo;
	}

	public void setMapFaseCatalogo(Map<Object, List<NodoExpediente>> mapFaseCatalogo) {
		this.mapFaseCatalogo = mapFaseCatalogo;
	}

	public Map<Object, List<NodoExpediente>> getMapTramiteCatalogo() {
		return mapTramiteCatalogo;
	}

	public void setMapTramiteCatalogo(Map<Object, List<NodoExpediente>> mapTramiteCatalogo) {
		this.mapTramiteCatalogo = mapTramiteCatalogo;
	}

	public void actualizarMapProcedimiento() {
		mapProcedimiento.clear();
		armarMap(listaExpediente, mapProcedimiento);
	}

	public void inicializarMapas() {
		mapProcedimiento.clear();
		mapFaseCatalogo.clear();
		mapTramiteCatalogo.clear();
		armarMap(listaExpediente, mapProcedimiento);
		armarMap(getListaFaseActiva(listaExpediente), mapFaseCatalogo);
		for (Object fc : mapFaseCatalogo.keySet()) {
			armarMapTramites(fc);
		}

	}

	private List<NodoExpediente> getListaFaseActiva(List<NodoExpediente> lista) {
		List<NodoExpediente> list = new ArrayList<NodoExpediente>();
		for (NodoExpediente ne : lista) {
			Expediente e = (Expediente) ne;
			list.add(e.getFaseActual());
		}
		return list;
	}

	


	private void armarMap(List<NodoExpediente> lista, Map<Object, List<NodoExpediente>> map) {
		for (NodoExpediente ne : lista) {
			Object key = ne.getPlantilla();
			if (soloVencidos && !ne.tieneVencimientos(listaFeriados)) continue;
			if (ne instanceof Tramite) {
				//No agregamos los tramites cuyo estado representa cerrado
				Tramite locTramite = (Tramite) ne;
				if (locTramite.isCerrado()) continue;
				//TODO Preguntar y filtrar por Tramites no trabajados.
			}
			MapUtils.put(map, ne, key);
		}
	}

	
	public void actualizarMapasSegunSeleccionProcedimiento(List<Procedimiento> listaSeleccionada) {
		if (listaSeleccionada != null && !listaSeleccionada.isEmpty()) {
			mapFaseCatalogo.clear();
			mapTramiteCatalogo.clear();
			for (Procedimiento procedimiento : listaSeleccionada) {
				armarMap(getListaFaseActiva(mapProcedimiento.get(MapUtils.getKey(mapProcedimiento,
						procedimiento))), mapFaseCatalogo);
			}
			for (Object fc : mapFaseCatalogo.keySet()) {
				armarMapTramites(fc);
			}
		}
	}

	public void actualizarMapasSegunSeleccionFaseCatalogo(List<FaseCatalogo> listaSeleccionada) {
		if (listaSeleccionada != null && !listaSeleccionada.isEmpty()) {
			mapTramiteCatalogo.clear();
			for (FaseCatalogo fc : listaSeleccionada) {
				armarMapTramites(fc);
			}
		}
	}

	private List<NodoExpediente> filtrarTramitesResponsables(List<NodoExpediente> lista) {
		List<NodoExpediente> locLista = new ArrayList<NodoExpediente>();
		for (NodoExpediente t : lista) {
			boolean ok = false;
			for (Boolean bool : t.getPermisos(usuario)) {
				if (bool != null) {
					ok = true;
					break;
				}
			}
			if (ok) {
				locLista.add(t);
			}
		}
		return locLista;
	}

	private void armarMapTramites(Object fc) {
		List<NodoExpediente> listaFase = mapFaseCatalogo.get(MapUtils.getKey(mapFaseCatalogo, fc));
		for (NodoExpediente fase : listaFase) {
			armarMap(filtrarTramitesResponsables(fase.getListaNodosExpedientes()),
					mapTramiteCatalogo);
		}
	}

	private CommunicationExpedientesBean getCommunicationExpedienteBean() {
		return (CommunicationExpedientesBean) this.getSessionBean("CommunicationExpedientesBean");
	}

	public Object getSessionBean(String pBeanName) {
		return FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get(pBeanName);
	}

	public List<DiaFeriado> getListaFeriados(Date from) {
		try {
			listaFeriados = getCommunicationExpedienteBean().getRemoteSystemExpedientes()
					.getDiasFeriadosEntre(from, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaFeriados;
	}

	private SessionBean1 getSessionBean1() {
		return (SessionBean1) getSessionBean("SessionBean1");
	}

}
