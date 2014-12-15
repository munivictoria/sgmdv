package com.trascender.expedientes.recurso.persistent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Where;

import com.trascender.expedientes.recurso.persistent.Responsabilidad.Accion;
import com.trascender.framework.recurso.persistent.Area;
import com.trascender.framework.recurso.persistent.Usuario;

@Entity
@Table(name = "EXP_RESPONSABLE")
public class Responsable implements Serializable {

	public static final long serialVersionUID = 6641305459325684893L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_id_exp_responsable")
	@SequenceGenerator(name = "gen_id_exp_responsable", sequenceName = "gen_id_exp_responsable",
			allocationSize = 1)
	@Column(name = "ID_FASE")
	private Long idResponsable = -1l;
	
	private String nombre;
	
	@Where(clause = "tipo = 'usuario'")
	@OneToMany(mappedBy = "responsable", cascade = CascadeType.ALL, orphanRemoval = true, targetEntity = Responsabilidad.class)
	private List<UsuarioResponsable> listaUsuariosResponsables = new ArrayList<UsuarioResponsable>();
	
	@Where(clause = "tipo = 'area'")
	@OneToMany(mappedBy = "responsable", cascade = CascadeType.ALL, orphanRemoval = true, targetEntity = Responsabilidad.class)
	private List<AreaResponsable> listaAreasResponsables = new ArrayList<AreaResponsable>();
	
	@OneToMany(mappedBy = "responsable", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<UsuarioExtensor> listaUsuariosExtensores = new ArrayList<UsuarioExtensor>();
	
	public List<UsuarioExtensor> getListaUsuariosExtensores() {
		return listaUsuariosExtensores;
	}

	public void setListaUsuariosExtensores(
			List<UsuarioExtensor> listaUsuariosExtensores) {
		this.listaUsuariosExtensores = listaUsuariosExtensores;
	}

	public List<UsuarioResponsable> getListaUsuariosResponsables() {
		return listaUsuariosResponsables;
	}

	public void setListaUsuariosResponsables(
			List<UsuarioResponsable> listaUsuariosResponsables) {
		this.listaUsuariosResponsables = listaUsuariosResponsables;
		setearResponsables();
	}

	public List<AreaResponsable> getListaAreasResponsables() {
		return listaAreasResponsables;
	}

	public void setListaAreasResponsables(
			List<AreaResponsable> listaAreasResponsables) {
		this.listaAreasResponsables = listaAreasResponsables;
		setearResponsables();
	}
	
	private void setearResponsables(){
		for (AreaResponsable cadaArea : this.listaAreasResponsables) {
			cadaArea.setResponsable(this);
		}
		for (UsuarioResponsable cadaUsuario : this.listaUsuariosResponsables) {
			cadaUsuario.setResponsable(this);
		}
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getIdResponsable() {
		return idResponsable;
	}

	public void setIdResponsable(Long idResponsable) {
		this.idResponsable = idResponsable;
	}

	@Override
	@Transient
	public String toString() {
		String text = "";
		for (AreaResponsable a : listaAreasResponsables) {
			text += a.getArea().getNombre();
			if (listaAreasResponsables.indexOf(a) < listaAreasResponsables.size() - 1) {
				text += "-";
			}
		}
		return text;
	}
	
	public List<Usuario> getUsuarios(){
		List<Usuario> listaResultados = new ArrayList<Usuario>();
		for (UsuarioResponsable cadaUsuario : this.listaUsuariosResponsables) {
			listaResultados.add(cadaUsuario.getUsuario());
		}
		return listaResultados;
	}
	
	public List<Area> getAreas(){
		List<Area> listaResultados = new ArrayList<Area>();
		for (AreaResponsable cadaArea : this.listaAreasResponsables) {
			listaResultados.add(cadaArea.getArea());
		}
		return listaResultados;
	}

	public boolean esResponsable(Usuario pUsuario) {
		return tieneResponsabilidad(pUsuario, Accion.RESPONSABLE);
	}
	
	public boolean esSupervisor(Usuario usuario) {
		return tieneResponsabilidad(usuario, Accion.SUPERVISOR);
	}
	
	private boolean tieneResponsabilidad(Usuario pUsuario, Accion pAccion) {
		boolean esResponsable = false;
		for (Iterator<UsuarioResponsable> itUsuario = this.listaUsuariosResponsables.iterator(); itUsuario.hasNext() && !esResponsable;) {
			UsuarioResponsable cadaUsuarioResponsable = itUsuario.next();
			esResponsable = cadaUsuarioResponsable.getUsuario().equals(pUsuario) 
					&& cadaUsuarioResponsable.getResponsabilidad() == pAccion;
		}
		if (!esResponsable) {
			for (Iterator<AreaResponsable> itArea = this.listaAreasResponsables.iterator(); itArea.hasNext() && !esResponsable;) {
				AreaResponsable cadaAreaResponsable = itArea.next();
				for (Iterator<Area> itAreaUsuario = pUsuario.getListaAreas().iterator(); itAreaUsuario.hasNext() && !esResponsable;) {
					esResponsable = cadaAreaResponsable.getArea().equals(itAreaUsuario.next()) 
							&& cadaAreaResponsable.getResponsabilidad() == pAccion;
				}
			}
		}
		return esResponsable;
	}
	
	public Boolean soyResponsable(Usuario pUsuario) {
		Boolean retorno = null;

		if (esSupervisor(pUsuario)) {
			retorno = false;
		}
		if (esResponsable(pUsuario)) {
			retorno = true;
		}

		return retorno;
	}

}
