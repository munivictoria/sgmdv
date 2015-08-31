/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

package muni.expedientes.panels;

import muni.expedientes.tables.TableAreas;
import muni.expedientes.tables.TableUsuarios;
import muni.expedientes.tables.TableUsuariosExtensores;

public class PanelResponsableNodo extends PanelResponsable {

	private TableAreasResponsablesNodo tableAreas = new TableAreasResponsablesNodo();
	private TableUsuariosResponsableNodo tableUsuarios = new TableUsuariosResponsableNodo();
	private TableUsuariosExtensores tableUsuariosExtensores = new TableUsuariosExtensores();

	public TableUsuariosExtensores getTableUsuariosExtensores() {
		return tableUsuariosExtensores;
	}

	public void setTableUsuariosExtensores(TableUsuariosExtensores tableUsuariosExtensores) {
		this.tableUsuariosExtensores = tableUsuariosExtensores;
	}

	public TableAreas getTableAreas() {
		return tableAreas;
	}

	public void setTableAreas(TableAreasResponsablesNodo tableAreas) {
		this.tableAreas = tableAreas;
	}

	public TableUsuarios getTableUsuarios() {
		return tableUsuarios;
	}

	public void setTableUsuarios(TableUsuariosResponsableNodo tableUsuarios) {
		this.tableUsuarios = tableUsuarios;
	}

}