package com.trascender.compras.recurso.filtros;

import com.trascender.compras.recurso.persistent.suministros.UsuarioAutorizadorOrdenCompra;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.util.FiltroAbstracto;

public class FiltroUsuarioFirmante extends FiltroAbstracto<UsuarioAutorizadorOrdenCompra>{

	private static final long serialVersionUID = 8112438111401589880L;
	private Usuario usuario = new Usuario();
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
