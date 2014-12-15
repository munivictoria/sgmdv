package com.trascender.contabilidad.gui.abmTipoBalance.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

import com.trascender.contabilidad.gui.abmCuenta.AdminCuenta;
import com.trascender.contabilidad.gui.abmCuenta.CuentaTableModel;
import com.trascender.contabilidad.gui.abmTipoBalance.TipoBalanceABMModel;
import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.contabilidad.recurso.persistent.TipoBalance;
import com.trascender.gui.framework.abmStandard.ABMController;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Conversor;
import com.trascender.gui.framework.util.Validador;

public abstract class ABMTipoBalance extends ABMController<TipoBalance> {

	private CuentaTableModel tableModel;

	public abstract ABMTipoBalanceView getView();
	public abstract TipoBalanceABMModel getAbmModel();
	
	@Override
	protected void init() {
		super.init();
		this.setModels();
		this.Listeners();
		this.setCommonProperties();
	}
	
	private void Listeners() {
		this.getView().getPnlBtnTabla().getBtnAgregar().addActionListener(new BtnAgregarCuentaListener(this));
		this.getView().getPnlBtnTabla().getBtnEliminar().addActionListener(new BtnQuitarListener(this));
		this.getView().getPnlBtnTabla().getBtnQuitarTodos().addActionListener(new BtnQuitarTodosListener(this));
	}
	private void setCommonProperties() {
		this.getView().getPnlBtnTabla().getBtnModificar().setVisible(false);
	}

	private void setModels() {
		this.getView().setAbmModel(this.getAbmModel());
		this.getView().setTableModel(this.getTableModel());
		this.getView().getPnlSpTabla().getTblDatos().setModel(this.getTableModel());		
	}
	
	@Override
	public void actualizarABMModel() {
		TipoBalanceABMModel locModel = this.getAbmModel();
		
		locModel.setNombre(this.getView().getTfNombre().getText());
		locModel.setFechaCreacion(Conversor.getDate(this.getView().getTfFechaCreacion().getText()));
		
		List<Cuenta> locLista = this.getTableModel().getListaObjetos();
		if (this.getAbmModel().getListaDeCuentas() != null) {
			this.getAbmModel().getListaDeCuentas().clear();
			this.getAbmModel().getListaDeCuentas().addAll(locLista);
		}
		locModel.fireActualizarDatos();
	}

	@Override
	public void actualizarView() {
		this.getView().getTfNombre().setText(this.getAbmModel().getNombre());
		this.getView().getTfFechaCreacion().setValue(Conversor.getString(this.getAbmModel().getFechaCreacion()));
		if (this.getAbmModel().getListaDeCuentas() != null) {
			this.getTableModel().setListaObjetos(new ArrayList<Cuenta>(this.getAbmModel().getListaDeCuentas()));
		}
	}
	
	public CuentaTableModel getTableModel() {
		return tableModel;
	}
	
	@Override
	public boolean validarDatos() {
		boolean validacionOK = true;
		
		List<Object> attNulos = new ArrayList<Object>();
		List<JLabel> lblNulos = new ArrayList<JLabel>();
		
		List<Integer> attMin = new ArrayList<Integer>();
		List<JLabel> lblMin = new ArrayList<JLabel>();
		List<Integer> cantMin = new ArrayList<Integer>();
		
		List<String> attFechas = new ArrayList<String>();
		List<JLabel> lblFechas = new ArrayList<JLabel>();
		
		attNulos.add(this.getView().getTfNombre().getText());
		lblNulos.add(this.getView().getLblNombre());
		attNulos.add(this.getView().getTfFechaCreacion().getText());
		lblNulos.add(this.getView().getLblFechaCreacion());
		
		attMin.add(this.getView().getPnlSpTabla().getTblDatos().getRowCount());
		lblMin.add(new JLabel("La Lista de Cuentas"));
		cantMin.add(1);
		
		attFechas.add(this.getView().getTfFechaCreacion().getText());
		lblFechas.add(this.getView().getLblFechaCreacion());
		
		List<String> listaErrores = new ArrayList<String>();
		try {
			listaErrores.addAll(Validador.validarNulos(attNulos, lblNulos));
			listaErrores.addAll(Validador.validarCantidadMinima(attMin, lblMin, cantMin));
			listaErrores.addAll(Validador.validarFechas(attFechas, lblFechas));
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
	
	void agregarTablaCuentas() throws Exception {
		AdminCuenta adminCuenta = new AdminCuenta(this.getView(), true);
		List<Cuenta> locListaCuentas = adminCuenta.openSelectMultiple();
		if (locListaCuentas != null) {
			this.getTableModel().addRows(locListaCuentas);
			actualizarABMModel();
			this.actualizarView();
		}
	}
	
	void quitarTablaCuentas() throws Exception {
		int fila = this.getView().getPnlSpTabla().getTblDatos().getSelectedRow();
		this.getTableModel().deleteRow(fila);
	}
	
	void quitarTodosTablaCuentas() throws Exception {
		if (!this.getTableModel().getListaObjetos().isEmpty()) {
			if (AppManager.getInstance().showConfirmMsg(this.getView(), "¿Desea quitar todas las Cuentas?")) {
				this.getTableModel().clearTable();
			}
		}
	}
}

class BtnAgregarCuentaListener implements ActionListener {

	private ABMTipoBalance controller;

	public BtnAgregarCuentaListener(ABMTipoBalance controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.agregarTablaCuentas();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnQuitarListener implements ActionListener {

	private ABMTipoBalance controller;
	
	public BtnQuitarListener(ABMTipoBalance controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.quitarTablaCuentas();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}

class BtnQuitarTodosListener implements ActionListener {
	private ABMTipoBalance controller;

	public BtnQuitarTodosListener(ABMTipoBalance controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.quitarTodosTablaCuentas();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}