/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

package com.trascender.expedientes.recurso.persistent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.trascender.expedientes.exception.ExpedientesExceptions;
import com.trascender.framework.recurso.persistent.DiaFeriado;
import com.trascender.framework.recurso.persistent.Usuario;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "EXP_NODOEXPEDIENTE")
public abstract class NodoExpediente implements Serializable {

	private static final long serialVersionUID = -6528883936032403887L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_id_exp_nodoexpediente")
	@SequenceGenerator(name = "gen_id_exp_nodoexpediente", sequenceName = "gen_id_exp_nodoexpediente", allocationSize = 1)
	@Column(name = "ID_NODOEXPEDIENTE")
	protected long idNodoExpediente = -1;

	@OneToMany(mappedBy = "nodoExpediente", cascade = CascadeType.ALL)
	protected List<Hito> listaHitos = new ArrayList<Hito>();

	@ManyToOne
	@JoinColumn(name = "ID_NODOPROCEDIMIENTO")
	protected NodoProcedimiento nodoProcedimiento;

	@ManyToOne
	@JoinColumn(name = "ID_NODOPADRE")
	protected NodoExpediente nodoPadre;

	@Column(name = "ORDEN")
	protected Integer orden;

	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
	@JoinColumn(name = "ID_PLAZO")
	protected Plazo plazo;

	@OneToMany(mappedBy = "nodoPadre", cascade = CascadeType.ALL, orphanRemoval = true)
	protected List<NodoExpediente> listaNodosExpedientes = new ArrayList<NodoExpediente>();

	@Column(name = "FECHA_INICIO")
	protected Date fechaInicio;

	@Column(name = "FECHA_FIN")
	protected Date fechaFin;

	public Plazo getPlazo() {
		return plazo;
	}

	public void setPlazo(Plazo plazo) {
		this.plazo = plazo;
	}

	public long getIdNodoExpediente() {
		return idNodoExpediente;
	}

	public void setIdNodoExpediente(long idNodoExpediente) {
		this.idNodoExpediente = idNodoExpediente;
	}

	public List<Hito> getListaHitos() {
		try {
			Collections.sort(listaHitos, comparatorHito);
		} catch(Exception e) {
			e.printStackTrace();
		}

		return listaHitos;
	}

	public void setListaHitos(List<Hito> listaHitos) {
		this.listaHitos = listaHitos;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public NodoProcedimiento getNodoProcedimiento() {
		return nodoProcedimiento;
	}

	public void setNodoProcedimiento(NodoProcedimiento nodoProcedimiento) {
		this.nodoProcedimiento = nodoProcedimiento;
	}

	public NodoExpediente getNodoPadre() {
		return nodoPadre;
	}

	public void setNodoPadre(NodoExpediente nodoPadre) {
		this.nodoPadre = nodoPadre;
	}

	public List<NodoExpediente> getListaNodosExpedientes() {
		return listaNodosExpedientes;
	}

	public void setListaNodosExpedientes(List<NodoExpediente> listaNodosExpedientes) {
		this.listaNodosExpedientes = listaNodosExpedientes;
	}

	public Integer getOrden() {
		return orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}

	public void addHito(String pDescripcion, String pComentario, Usuario pUsuario) {
		Hito h = new Hito();
		h.nombre = this.getClass().getName();
		h.setDescripcion(pDescripcion);
		h.setComentario(pComentario);
		h.setFecha(new Date());
		h.setNodoExpediente(this);
		h.setUsuario(pUsuario);
		this.listaHitos.add(h);
	}

	private static Comparator<Hito> comparatorHito = new Comparator<Hito>() {

		@Override
		public int compare(Hito h1, Hito h2) {
			return h2.getFecha().compareTo(h1.getFecha());
		}
	};

	public List<Hito> listarHitos() {
		List<Hito> lista = new ArrayList<Hito>();
		recopilarHitos(this, lista);
		Collections.sort(lista, comparatorHito);
		return lista;
	}

	/** 
	 * trae los hitos suyos y de todos los ascendentes 
	 */
	private void recopilarHitos(NodoExpediente pNe, List<Hito> pHitos) {
		pHitos.addAll(pNe.getListaHitos());
		for(NodoExpediente locNe : pNe.getListaNodosExpedientes()) {
			recopilarHitos(locNe, pHitos);
		}
	}

	public boolean esResponsableDeNodos(Usuario pUsuario) {
		return esResponsableDeNodos(this, pUsuario);
	}

	public boolean esResponsableDirectoOPadre(Usuario pUsuario) {
		Responsable locResponsable = this.getNodoProcedimiento().getResponsable();
		if(locResponsable != null && locResponsable.esResponsable(pUsuario)) {
			return true;
		} else if(this.nodoPadre != null) {
			return nodoPadre.esResponsableDirectoOPadre(pUsuario);
		} else {
			return false;
		}
	}

	private Boolean esResponsableDeNodos(NodoExpediente pNodo, Usuario pUsuario) {
		Responsable r = pNodo.getNodoProcedimiento().getResponsable();
		Boolean soyResponsable = r.soyResponsable(pUsuario);
		if(r != null && soyResponsable != null) {
			return soyResponsable;
		}
		for(NodoExpediente nodo : pNodo.getListaNodosExpedientes()) {
			Boolean esResponsableDeNodos = esResponsableDeNodos(nodo, pUsuario);
			if(esResponsableDeNodos != null) {
				return esResponsableDeNodos;
			}
		}
		
		return null;
	}

	public class Permiso {
		
		Boolean abuelo;
		Boolean padre;
		Boolean hijo;

	}

	@Transient
	public Map<NodoExpediente, Boolean[]> getMapPermisos(Usuario pUsuario) {
		Map<NodoExpediente, Boolean[]> map = new HashMap<NodoExpediente, Boolean[]>();
		armapMapaPermisos(pUsuario, map, this);
		
		return map;
	}

	public List<Boolean> getPermisos(Usuario pUsuario) {
		List<Boolean> list = new ArrayList<Boolean>();
		armarArrayPermisos(this, pUsuario, list);
		
		return list;
	}

	private void armarArrayPermisos(NodoExpediente nodo, Usuario pUsuario, List<Boolean> bArray) {
		Responsable r = nodo.getNodoProcedimiento().getResponsable();
		bArray.add(r != null ? r.soyResponsable(pUsuario) : null);
		if(nodo.getNodoPadre() != null) {
			armarArrayPermisos(nodo.getNodoPadre(), pUsuario, bArray);
		}
	}

	private void armapMapaPermisos(Usuario pUsuario, Map<NodoExpediente, Boolean[]> map, NodoExpediente pNodoE) {
		List<Boolean> list = new ArrayList<Boolean>();
		armarArrayPermisos(pNodoE, pUsuario, list);
		Boolean[] barr = new Boolean[list.size()];
		for(int i = 0; i < list.size(); i++) {
			barr[i] = list.get(i);
		}
		map.put(pNodoE, barr);

		for(NodoExpediente ne : pNodoE.getListaNodosExpedientes()) {
			armapMapaPermisos(pUsuario, map, ne);
		}
	}

	public void crearPlazo() {
		if(this.nodoProcedimiento.getPlazo() != null) {
			this.plazo = new Plazo(this.nodoProcedimiento);
		}
		for(NodoExpediente cadaNodoHijo : this.listaNodosExpedientes) {
			cadaNodoHijo.crearPlazo();
		}
	}

	public void anularPlazo() {
		this.plazo = null;
		for(NodoExpediente cadaNodoHijo : this.listaNodosExpedientes) {
			cadaNodoHijo.anularPlazo();
		}
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		final NodoExpediente other = (NodoExpediente) obj;
		
		// Si algunas de los objetos son nuevos, comparamos por el Procedimiento asociado.
		if(this.idNodoExpediente == -1 || other.idNodoExpediente == -1)
			return this.nodoProcedimiento.equals(other.nodoProcedimiento);
		if(idNodoExpediente != other.idNodoExpediente)
			return false;
		
		return true;
	}

	/**
	 * @return objeto que sirve de plantilla al nodo puede ser un <strong> Procedimiento </strong> (para expedientes) o <strong>Cat\341logo </strong> (para
	 *         fases y tr�mites)
	 */
	public abstract Object getPlantilla();

	/**
	 * @param diasFeriados
	 *            : lista de feriados cargados en el sistema.
	 * @return <strong>true</strong> en caso de que encuentre alg�n nodo vencido en cualquier direcci�n de la jerarqu�a (ascendientes o descendientes).
	 */
	public abstract boolean tieneVencimientos(List<DiaFeriado> diasFeriados);

	/**
	 * @param diasFeriados
	 *            : lista de feriados cargados en el sistema.
	 * @return <strong>true</strong> en caso de que plazo.fechaFin sea menor a la fecha actual.
	 */
	public boolean isVencido(List<DiaFeriado> diasFeriados) {
		boolean retorno = false;
		if(this.plazo != null) {
			retorno = plazo.getDatosCalculados(diasFeriados).isVencido();
		}
		
		return retorno;
	}

	public void agregarExtension(Integer dias, Usuario pUsuario, String pComentario, List<DiaFeriado> pListaFeriados) throws ExpedientesExceptions {
		if(this.plazo != null) {
			this.plazo.validarNuevaExtension(dias, pUsuario, this.nodoProcedimiento);
			Plazo locPlazoNuevo = this.plazo.getExtension(pUsuario, dias, pListaFeriados);
			this.plazo = locPlazoNuevo;
		}
		this.addHito("Se agregó extensión de " + dias + " dias a " + this.getPlantilla().toString(), pComentario, pUsuario);
	}

}