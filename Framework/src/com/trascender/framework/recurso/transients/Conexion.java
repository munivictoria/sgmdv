package com.trascender.framework.recurso.transients;

import java.util.Date;

import com.trascender.framework.recurso.persistent.Usuario;
public class Conexion {
	
	private Usuario usuario;
	private long llave;
	private String remoteAddress;
	private String remoteHost;
	private String remotePort;
	private Date fechaHora;

	
	public Date getFechaHora() {
		return fechaHora;
	}
	public void setFechaHora(Date pFechaHora) {
		fechaHora = pFechaHora;
	}
	public String getRemoteAddress() {
		return remoteAddress;
	}
	public void setRemoteAddress(String pRemoteAddress) {
		remoteAddress = pRemoteAddress;
	}
	public String getRemoteHost() {
		return remoteHost;
	}
	public void setRemoteHost(String pRemoteHost) {
		remoteHost = pRemoteHost;
	}
	public String getRemotePort() {
		return remotePort;
	}
	public void setRemotePort(String pRemotePort) {
		remotePort = pRemotePort;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario pUsuario) {
		usuario = pUsuario;
	}
	
	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		final Conexion other = (Conexion) obj;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}
	public long getLlave() {
		return llave;
	}
	public void setLlave(long pLlave) {
		llave = pLlave;
	} 
	
}
