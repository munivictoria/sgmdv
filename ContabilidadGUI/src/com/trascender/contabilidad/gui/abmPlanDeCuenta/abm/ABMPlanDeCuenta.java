package com.trascender.contabilidad.gui.abmPlanDeCuenta.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import com.trascender.contabilidad.gui.abmCuenta.CuentaTreeModel;
import com.trascender.contabilidad.gui.abmCuenta.abm.AgregarCuenta;
import com.trascender.contabilidad.gui.abmCuenta.abm.ModificarCuenta;
import com.trascender.contabilidad.gui.abmPlanDeCuenta.PlanDeCuentaABMModel;
import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.contabilidad.recurso.persistent.PlanDeCuenta;
import com.trascender.gui.framework.abmStandard.ABMController;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Conversor;
import com.trascender.gui.framework.util.Validador;

public abstract class ABMPlanDeCuenta extends ABMController<PlanDeCuenta>{
	
	protected abstract ABMPlanDeCuentaView getView();
	protected abstract PlanDeCuentaABMModel getAbmModel();
	protected abstract CuentaTreeModel getTreeModel();
	protected ArrayList<Cuenta> cuentasBorradas = new ArrayList<Cuenta>();
	
	@Override
	protected void init() {
		super.init();
		this.setCommonProperties();
		this.setModels();
		this.setListeners();
		this.actualizarView();
	}
	
	private void setCommonProperties() {
		this.getView().getPnlVerticalBotonesArbol().getBtnEliminar().setText(MessagesContabilidad.getString("Application.btnEliminar"));
		this.getView().getPnlVerticalBotonesArbol().getBtnEliminar().setMnemonic(MessagesContabilidad.getChar("Application.btnEliminarMnemonic"));
	}
	
	private void setModels() {
		this.getView().setAbmModel(this.getAbmModel());
		this.getView().setTreeModel(this.getTreeModel());
	}
	
	private void setListeners(){
		
	}
	
	@Override
	protected void actualizarABMModel() {
		this.getAbmModel().getObjetoABM().setDescripcion(this.getView().getTfDescripcion().getText());
		this.getAbmModel().getObjetoABM().setFechaAlta(Conversor.getDate(this.getView().getTfFechaAlta().getText()));
		this.getAbmModel().fireActualizarDatos();
		this.getTreeModel().actualizarRaiz();
	}
	
	@Override
	public void actualizarView() {
		this.getView().getTfDescripcion().setText(this.getAbmModel().getDescripcion());
		this.getView().getTfFechaAlta().setValue(Conversor.getString(this.getAbmModel().getFechaAlta()));
	}
	
	@Override
	public boolean validarDatos() {
		boolean validacionOK = true;
		
		List<Object> attNulos = new ArrayList<Object>();
		List<JLabel> lblNulos = new ArrayList<JLabel>();
		
		List<String> attFechas = new ArrayList<String>();
		List<JLabel> lblFechas = new ArrayList<JLabel>();
		
		attNulos.add(this.getView().getTfDescripcion().getText());
		lblNulos.add(this.getView().getLblDescripcion());
		
		attNulos.add(this.getView().getTfFechaAlta().getText());
		lblNulos.add(this.getView().getLblFechaAlta());
		
		attFechas.add(this.getView().getTfFechaAlta().getText());
		lblFechas.add(this.getView().getLblFechaAlta());

		List<String> listaErrores = new ArrayList<String>();
		try {
			listaErrores.addAll(Validador.validarNulos(attNulos, lblNulos));
			listaErrores.addAll(Validador.validarFechas(attFechas, lblFechas));
			listaErrores.addAll(Validador.validarFechaNoMayorALaActual(this.getView().getTfFechaAlta().getText(), this.getView().getLblFechaAlta()));
			listaErrores.addAll(Validador.validarArbol(this.getView().getAbmModel().getObjetoABM().getListaCuentas()));
		}
		catch (GuiException ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.getView(), ex.getMessage());
			return false;
		}
		
		if (!listaErrores.isEmpty()) {
			validacionOK = false;
			this.mostrarErroresValidacion(listaErrores);
		}
		
		return validacionOK;
	}
	
	public void actualizarArbol() {
		this.getView().getPnlABMArbol().getTreeCuentas().setModel(this.getTreeModel());
		TreePath lastPath = this.getTreeModel().getUltimoNodoModificadoTreePath();
		if (lastPath != null) {
			this.getView().getPnlABMArbol().getTreeCuentas().makeVisible(lastPath);
			this.getView().getPnlABMArbol().getTreeCuentas().scrollPathToVisible(lastPath);
		}
	}
	
	void agregarNodo() throws Exception {
		TreePath rutaNodoSeleccionado = this.getView().getPnlABMArbol().getTreeCuentas().getSelectionPath();
		DefaultMutableTreeNode nodoSeleccionado = this.getTreeModel().getNodo(rutaNodoSeleccionado);
		
		AgregarCuenta agregarCuenta = new AgregarCuenta(this.getView());
		agregarCuenta.getAbmModel().setObjetoABM(new Cuenta());
		if (nodoSeleccionado != null) {
			Object locObject = nodoSeleccionado.getUserObject();
			agregarCuenta.getAbmModel().setPadre(locObject);
		}
		else{
			agregarCuenta.getAbmModel().setPadre(this.getTreeModel().getRoot().getUserObject());
		}
		agregarCuenta.actualizarView();
		agregarCuenta.open();
		
		if (agregarCuenta.isOperacionRealizada()) {
			this.getTreeModel().agregarNodo(rutaNodoSeleccionado, agregarCuenta.getAbmModel().getObjetoABM());
			this.actualizarArbol();
		}
	}
	
	void modificarNodo() throws Exception {
		TreePath rutaNodoSeleccionado = this.getView().getPnlABMArbol().getTreeCuentas().getSelectionPath();
		Cuenta locCuenta = this.getTreeModel().getObjeto(rutaNodoSeleccionado);

		if (locCuenta != null) {
			ModificarCuenta modificarCuenta = new ModificarCuenta(this.getView());
			modificarCuenta.getAbmModel().setObjetoABM(locCuenta);
			modificarCuenta.actualizarView();
			modificarCuenta.open();
			
			if (modificarCuenta.isOperacionRealizada()) {
				this.getTreeModel().modificarNodo(rutaNodoSeleccionado, modificarCuenta.getAbmModel().getObjetoABM());
				this.actualizarArbol();
			}
		}
	}
	
	void quitarNodo() throws Exception {
		TreePath rutaNodoSeleccionado = this.getView().getPnlABMArbol().getTreeCuentas().getSelectionPath();
		if (rutaNodoSeleccionado != null) {
			DefaultMutableTreeNode nodo = (DefaultMutableTreeNode)(rutaNodoSeleccionado.getLastPathComponent());
			if (nodo.getChildCount() == 0) {
					Cuenta locCuenta = this.getTreeModel().getObjeto(rutaNodoSeleccionado);
					this.cuentasBorradas.add(locCuenta);
					if (locCuenta != null) {
						this.getTreeModel().eliminarNodo(rutaNodoSeleccionado);
						this.getAbmModel().getObjetoABM().getListaCuentas().remove(locCuenta); 
						this.actualizarABMModel();
					}
			}else{
				AppManager.getInstance().showInformationMsg(this.getView(), "No se pueden eliminar cuentas que contengan otras cuentas asociadas.");
			}
		}
	}
	
	void quitarTodos() throws Exception {
		if (AppManager.getInstance().showConfirmMsg(this.getView(), "¿Desea quitar todas las Cuentas?")) {
			this.getTreeModel().clearTree();
			this.actualizarArbol();
			this.getAbmModel().getObjetoABM().getListaCuentas().clear();
			
		}
	}
	
	public ArrayList<Cuenta> getCuentasBorradas() {
		return cuentasBorradas;
	}
	
}

class BtnAgregarNodoListener implements ActionListener {
	private ABMPlanDeCuenta controller;
	public BtnAgregarNodoListener(ABMPlanDeCuenta controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e){
		try {
			this.controller.agregarNodo();
		}
		catch(Exception ex){
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnModificarNodoListener implements ActionListener {
	private ABMPlanDeCuenta controller;
	public BtnModificarNodoListener(ABMPlanDeCuenta controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.modificarNodo();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnQuitarNodoListener implements ActionListener {
	private ABMPlanDeCuenta controller;
	public BtnQuitarNodoListener(ABMPlanDeCuenta controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.quitarNodo();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnQuitarTodosListener implements ActionListener {
	private ABMPlanDeCuenta controller;
	public BtnQuitarTodosListener(ABMPlanDeCuenta controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.quitarTodos();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}
