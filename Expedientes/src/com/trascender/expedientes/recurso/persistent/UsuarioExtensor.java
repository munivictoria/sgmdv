/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

package com.trascender.expedientes.recurso.persistent;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.trascender.framework.recurso.persistent.Usuario;

@Entity
@Table(name = "EXP_USUARIO_EXTENSOR")
public class UsuarioExtensor implements Serializable {

	private static final long serialVersionUID = -2091681349743479182L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_id_usuario_extensor")
	@SequenceGenerator(name = "gen_id_usuario_extensor", sequenceName = "gen_id_usuario_extensor", allocationSize = 1)
	@Column(name = "ID_USUARIO_EXTENSOR")
	private long idUsuarioExtensor = -1;

	@ManyToOne
	@JoinColumn(name = "ID_RESPONSABLE")
	private Responsable responsable;

	@ManyToOne
	@JoinColumn(name = "ID_USUARIO")
	private Usuario usuario;

	@Column(name = "CANTIDAD_DIAS")
	private Integer cantidadDias = 1;

	public long getIdUsuarioExtensor() {
		return idUsuarioExtensor;
	}

	public void setIdUsuarioExtensor(long idUsuarioExtensor) {
		this.idUsuarioExtensor = idUsuarioExtensor;
	}

	public Responsable getResponsable() {
		return responsable;
	}

	public void setResponsable(Responsable responsable) {
		this.responsable = responsable;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Integer getCantidadDias() {
		return cantidadDias;
	}

	public void setCantidadDias(Integer cantidadDias) {
		this.cantidadDias = cantidadDias;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idUsuarioExtensor ^ (idUsuarioExtensor >>> 32));
		
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		UsuarioExtensor other = (UsuarioExtensor) obj;
		if(idUsuarioExtensor == -1 || other.idUsuarioExtensor == -1)
			return this.usuario.equals(other.usuario);
		if(idUsuarioExtensor != other.idUsuarioExtensor)
			return false;
		
		return true;
	}

}