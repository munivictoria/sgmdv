package muni.expedientes.panels;

import java.util.ArrayList;

import muni.expedientes.tables.TableAreas;
import muni.expedientes.tables.TableUsuarios;
import muni.expedientes.tables.TableUsuariosExtensores;

import com.trascender.expedientes.recurso.persistent.AreaResponsable;
import com.trascender.expedientes.recurso.persistent.Responsable;
import com.trascender.expedientes.recurso.persistent.UsuarioExtensor;
import com.trascender.expedientes.recurso.persistent.UsuarioResponsable;
import com.trascender.framework.recurso.persistent.Area;
import com.trascender.framework.recurso.persistent.Usuario;

public abstract class PanelResponsable {

	public TableAreas getTableAreas() {
		return null;
	}

	public void setTableAreas(TableAreas tableAreas) {
	}

	public TableUsuarios getTableUsuarios() {
		return null;
	}

	public void setTableUsuarios(TableUsuarios tableUsuarios) {
	}
	
	public TableUsuariosExtensores getTableUsuariosExtensores() {
		return null;
	}

	public void setTableUsuariosExtensores(TableUsuariosExtensores tableUsuariosExtensores) {
	}

	public void _initTables() {
		getTableAreas()._init();
		getTableUsuarios()._init();
		getTableUsuariosExtensores()._init();
	}

	@SuppressWarnings("unchecked")
	public void guardarDatos(Responsable pResponsable) {
		if (pResponsable != null) {
			pResponsable.setListaAreasResponsables(getTableAreas().getList());
			pResponsable.setListaUsuariosResponsables(getTableUsuarios().getList());
			pResponsable.setListaUsuariosExtensores(getTableUsuariosExtensores().getList());
		} else if (emptyLists()) {
			pResponsable = new Responsable();
			pResponsable.setListaAreasResponsables(getTableAreas().getList());
			pResponsable.setListaUsuariosResponsables(getTableUsuarios().getList());
			pResponsable.setListaUsuariosExtensores(getTableUsuariosExtensores().getList());
		}
	}

	public void mostrarDatos(Responsable pResponsable) {
		if (pResponsable != null) {
			getTableAreas().setList(pResponsable.getListaAreasResponsables());
			getTableUsuarios().setList(pResponsable.getListaUsuariosResponsables());
			getTableUsuariosExtensores().setList(pResponsable.getListaUsuariosExtensores());
		} else{
			getTableAreas().setList(new ArrayList<AreaResponsable>());
			getTableUsuarios().setList(new ArrayList<UsuarioResponsable>());
			getTableUsuariosExtensores().setList(new ArrayList<UsuarioExtensor>());
		}
	}

	public boolean emptyLists() {
		return getTableAreas().getList().isEmpty() && getTableUsuarios().getList().isEmpty()
				&& getTableUsuariosExtensores().getList().isEmpty();
	}

	public void procesarObjetoSeleccion(Object pObject, Responsable pResponsable) {
		if (pObject instanceof Area) {
			Area nuevaArea = (Area) pObject;
			getTableAreas().addToList(pResponsable.getListaAreasResponsables(), nuevaArea);
		}
		if (pObject instanceof Usuario) {
			Usuario nuevoUsuario = (Usuario) pObject;
			getTableUsuarios().addToList(pResponsable.getListaUsuariosResponsables(), nuevoUsuario);
		}
		if (pObject instanceof UsuarioExtensor){
			UsuarioExtensor nuevoUsuarioExtensor = (UsuarioExtensor) pObject;
			getTableUsuariosExtensores().addToList(pResponsable.getListaUsuariosExtensores(), nuevoUsuarioExtensor);
		}
	}

}
