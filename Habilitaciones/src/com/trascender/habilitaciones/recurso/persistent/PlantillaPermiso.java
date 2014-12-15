package com.trascender.habilitaciones.recurso.persistent;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.trascender.framework.recurso.persistent.Rol;


@Entity
@Table(name = "PLANTILLA_PERMISO_HAB")
@PrimaryKeyJoinColumn(name = "ID_PLANTILLA_DOC_HABILITANTE")
public class PlantillaPermiso extends PlantillaDocHabilitante {

	/**
	 * 
	 */
	public static final long serialVersionUID = -3371572398854094257L;
	
	@Column(name = "POSIBLE_AUTORIZADOR")
	private String posibleAutorizador;
	
	@ManyToOne
	@JoinColumn(name = "ID_ROL")
	private Rol rol;
	
	
	public String getPosibleAutorizador() {
		return posibleAutorizador;
	}
	public void setPosibleAutorizador(String posibleAutorizador) {
		this.posibleAutorizador = posibleAutorizador;
	}
	
	public Rol getRol() {
		return rol;
	}
	public void setRol(Rol rol) {
		this.rol = rol;
	}
	
}
