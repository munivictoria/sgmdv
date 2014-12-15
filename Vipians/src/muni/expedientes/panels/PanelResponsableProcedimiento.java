package muni.expedientes.panels;

import com.sun.rave.web.ui.component.HiddenField;
import com.sun.rave.web.ui.component.Label;

import muni.expedientes.tables.TableAreas;
import muni.expedientes.tables.TableAreasResponsablesProcedimiento;
import muni.expedientes.tables.TableUsuarios;
import muni.expedientes.tables.TableUsuariosExtensores;
import muni.expedientes.tables.TableUsuariosResponsablesProcedimiento;

public class PanelResponsableProcedimiento extends PanelResponsable {
	

	 private HiddenField hResponsable = new HiddenField();
	 private Label lblResponsable = new Label();
	 public HiddenField gethResponsable() {
		return hResponsable;
	}
	public void sethResponsable(HiddenField hResponsable) {
		this.hResponsable = hResponsable;
	}
	public Label getLblResponsable() {
		return lblResponsable;
	}
	public void setLblResponsable(Label lblResponsable) {
		this.lblResponsable = lblResponsable;
	}
	private TableAreasResponsablesProcedimiento tableAreas   = new TableAreasResponsablesProcedimiento();
	 private TableUsuariosResponsablesProcedimiento tableUsuarios = new TableUsuariosResponsablesProcedimiento();
	 private TableUsuariosExtensores tableUsuariosExtensores = new TableUsuariosExtensores();
	 
	 
	public TableUsuariosExtensores getTableUsuariosExtensores() {
		return tableUsuariosExtensores;
	}
	 
	public void setTableUsuariosExtensores(
			TableUsuariosExtensores tableUsuariosExtensores) {
		this.tableUsuariosExtensores = tableUsuariosExtensores;
	}
	public TableAreas getTableAreas() {
		return tableAreas;
	}
	public void setTableAreas(TableAreas tableAreas) {
		this.tableAreas = (TableAreasResponsablesProcedimiento) tableAreas;
	}
	public TableUsuarios getTableUsuarios() {
		return tableUsuarios;
	}
	public void setTableUsuarios(TableUsuarios tableUsuarios) {
		this.tableUsuarios = (TableUsuariosResponsablesProcedimiento) tableUsuarios;
	}
	
	 
	 

	 
	 
	 
	 
}
