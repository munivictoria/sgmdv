/**
 * 
 */
package com.trascender.compras.recurso.persistent.suministros;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.trascender.framework.recurso.persistent.Usuario;

@Entity
@Table(name = "USUARIO_AUTORIZADOR")
@Inheritance(strategy = InheritanceType.JOINED)
public class UsuarioAutorizador implements Serializable {

	private static final long serialVersionUID = -3886009533552468836L;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_usuario_autorizador")
	@SequenceGenerator(name = "gen_id_usuario_autorizador", sequenceName = "gen_id_usuario_autorizador",allocationSize = 1)
	@Column(name="ID_USUARIO_AUTORIZADOR")
	protected long idUsuarioAutorizador=-1;
	
	@ManyToOne
	@JoinColumn(name = "ID_USUARIO")
	protected Usuario usuario;
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario idUsuario) {
		this.usuario = idUsuario;
	}
	
	@Override
	public String toString() {	
		return this.usuario.getUser();
	}
	
	public long getIdUsuarioAutorizador() {
		return idUsuarioAutorizador;
	}
	
	public void setIdUsuarioAutorizador(long idUsuarioAutorizador) {
		this.idUsuarioAutorizador = idUsuarioAutorizador;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ (int) (idUsuarioAutorizador ^ (idUsuarioAutorizador >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioAutorizador other = (UsuarioAutorizador) obj;
		if (idUsuarioAutorizador != other.idUsuarioAutorizador)
			return false;
		return true;
	}
}