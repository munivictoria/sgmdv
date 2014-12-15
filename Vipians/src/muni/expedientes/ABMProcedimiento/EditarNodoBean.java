
package muni.expedientes.ABMProcedimiento;

import muni.expedientes.tables.ABM_Table;

import com.trascender.presentacion.navegacion.ElementoPila;

public class EditarNodoBean extends ABM_Table {

	ABMProcedimiento abmProcedimiento = (ABMProcedimiento) getBean("expedientes$ABMProcedimiento$ABMProcedimiento");

	@Override
	public Long getIdPagina() {
		return abmProcedimiento.getIdPagina();
	}

	@Override
	public Long getIdSubSesion() {
		return abmProcedimiento.getIdSubSesion();
	}

	// tabla areas responsables
	public String btnAgregarArea_action() {
		abmProcedimiento.getElementosPila();
		abmProcedimiento.bean = "EditarNodoBean";
		abmProcedimiento.setElementosPila();
		return navegarParaSeleccionar("AdminArea");
	}

	public String btnQuitarArea_action() {
		return quitar_action(abmProcedimiento.getPanelEditNodo().getPanelResponsable().getTableAreas());
	}

	public String btnQuitarTodasAreas_action() {
		return quitarTodos_action(abmProcedimiento.getPanelEditNodo().getPanelResponsable().getTableAreas());
	}

	// tabla Usuarios responsables
	public String btnAgregarUsuario_action() {
		abmProcedimiento.getElementosPila();
		abmProcedimiento.bean = "EditarNodoBean";
		abmProcedimiento.usuarioSeleccion = "responsable";
		abmProcedimiento.setElementosPila();
		return navegarParaSeleccionar("AdminUsuario");
	}

	public String btnQuitarUsuario_action() {
		return quitar_action(abmProcedimiento.getPanelEditNodo().getPanelResponsable().getTableUsuarios());
	}

	public String btnQuitarTodosUsuarios_action() {
		return quitarTodos_action(abmProcedimiento.getPanelEditNodo().getPanelResponsable().getTableUsuarios());
	}
	
	// tabla Usuarios extensores responsables
	public String btnAgregarUsuarioExtensores_action() {
		abmProcedimiento.getElementosPila();
		abmProcedimiento.bean = "EditarNodoBean";
		abmProcedimiento.usuarioSeleccion = "extensor";
		abmProcedimiento.setElementosPila();
		return navegarParaSeleccionar("AdminUsuario");
	}

	public String btnQuitarUsuarioExtensores_action() {
		return quitar_action(abmProcedimiento.getPanelEditNodo().getPanelResponsable().getTableUsuariosExtensores());
	}

	public String btnQuitarTodosUsuariosExtensores_action() {
		return quitarTodos_action(abmProcedimiento.getPanelEditNodo().getPanelResponsable().getTableUsuariosExtensores());
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		abmProcedimiento.guardarEstadoObjetosUsados();
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
	}

	@Override
	protected String getCasoNavegacion() {
		return null;
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		return null;
	}

	@Override
	public void getElementosPila() {
	}

	@Override
	protected void setElementosPila() {
	}

	@Override
	public String getNombreBean() {
		return null;
	}

	@Override
	public long getSerialVersionUID() {
		return 0;
	}
}