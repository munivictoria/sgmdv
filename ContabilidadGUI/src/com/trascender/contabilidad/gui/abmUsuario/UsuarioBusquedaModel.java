package com.trascender.contabilidad.gui.abmUsuario;

import java.util.List;

import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.framework.recurso.filtros.FiltroUsuario;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.system.interfaces.SystemUsuario;
import com.trascender.gui.framework.model.TAbstractBusquedaModel;

public class UsuarioBusquedaModel extends TAbstractBusquedaModel<Usuario>{

	private String nombre;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> buscar() throws Exception {
		SystemUsuario locSystem = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemUsuario();
		locSystem.setLlave(ContabilidadGUI.getInstance().getLlaveUsuarioConectado());
		FiltroUsuario locFiltro = new FiltroUsuario();
		locFiltro.setUser(this.getNombre());
		List<Usuario> locLista = locSystem. findUsuario(locFiltro).getListaResultados();
		return locLista;
	}

	@Override
	public void reiniciar() {
		this.setNombre(null);
		
		this.fireActualizarDatos();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
