package com.trascender.contabilidad.gui.abmCuenta.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

import com.trascender.contabilidad.gui.abmCuenta.CuentaABMModel;
import com.trascender.contabilidad.gui.abmCuenta.TipoCuentaTableModel;
import com.trascender.contabilidad.gui.abmTipoCuenta.AdminTipoCuenta;
import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.contabilidad.recurso.persistent.TipoCuenta;
import com.trascender.framework.recurso.persistent.Area;
import com.trascender.gui.framework.abmStandard.ABMController;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.model.TDefaultComboBoxModel;
import com.trascender.gui.framework.util.Conversor;
import com.trascender.gui.framework.util.Validador;

public abstract class ABMCuenta extends ABMController<Cuenta> {
	
	public abstract ABMCuentaView getView();
	public abstract CuentaABMModel getAbmModel();
	public abstract TipoCuentaTableModel getTableModelTipoCuenta();
	
	@Override
	protected void init() {
		super.init();
		this.setModels();
		this.setListeners();
		
		this.getView().getPnlBtnTablaTipoCuenta().getBtnModificar().setVisible(false);
		this.getView().getPnlBtnTablaTipoCuenta().getBtnQuitarTodos().setVisible(false);
	}
	
	private void setModels() {
		this.getView().setAbmModel(this.getAbmModel());
		
		List<Area> locListaAreas = this.getAbmModel().getListaAreas();
		this.getView().getCbArea().setModel(new TDefaultComboBoxModel(locListaAreas.toArray()));
		this.getView().setTableModelTipoCuenta(this.getTableModelTipoCuenta());
	}
	
	private void setListeners() {
		this.getView().getPnlBtnTablaTipoCuenta().getBtnAgregar().addActionListener(new BtnAgregarTipoCuentaListener(this));
		this.getView().getPnlBtnTablaTipoCuenta().getBtnEliminar().addActionListener(new BtnQuitarTipoCuentaListener(this));
	}
	
	@Override
	public void actualizarABMModel() {
		CuentaABMModel locModel = this.getAbmModel();
		
		locModel.setCodigoImputacion(Conversor.getNullSiVacio(this.getView().getTfCodigoImputacion().getText()));
		locModel.setNombre(Conversor.getNullSiVacio(this.getView().getTfNombre().getText()));
		locModel.setAbreviatura(Conversor.getNullSiVacio(this.getView().getTfAbreviatura().getText()));
		locModel.armarCodigoImputacionCompleto();
		locModel.setArea((Area)this.getView().getCbArea().getSelectedItem());
		
		locModel.setListaTipoCuenta(this.getView().getTableModelTipoCuenta().getListaObjetos());
		
		locModel.fireActualizarDatos();
	}
	
	@Override
	public void actualizarView() {
		Cuenta locCuenta = this.getAbmModel().getObjetoABM();
		
		String cuentaPadre = "";

		if (locCuenta.getCuentaPadre() == null){
			//cuentaPadre = Conversor.getVacioSiNull(locCuenta.getPlanDeCuenta());
		}
		else{
			cuentaPadre = locCuenta.getCuentaPadre().toString();
		}
		
		this.getView().getTfCuentaPadre().setText(cuentaPadre);
		this.getView().getTfCodigoImputacion().setText(this.getAbmModel().getCodigoImputacion());
		this.getView().getTfNombre().setText(this.getAbmModel().getNombre());
		this.getView().getTfAbreviatura().setText(this.getAbmModel().getAbreviatura());
		this.getView().getCbArea().setSelectedItem(this.getAbmModel().getArea());
		
		if (this.getAbmModel().getListaTipoCuenta() != null) {
			this.getTableModelTipoCuenta().setListaObjetos(this.getAbmModel().getListaTipoCuenta());
		}
	}
	
	@Override
	public boolean validarDatos() {
		boolean validacionOK = true;
		
		List<Object> attNulos = new ArrayList<Object>();
		List<JLabel> lblNulos = new ArrayList<JLabel>();
		
		attNulos.add(this.getView().getTfCodigoImputacion().getText());
		lblNulos.add(this.getView().getLblCodigoImputacion());
		
		attNulos.add(this.getView().getTfNombre().getText());
		lblNulos.add(this.getView().getLblNombre());
		
//		attNulos.add(Conversor.getVacioSiNull(this.getAbmModel().getTipoCuenta()));									<---------- hacer algo aca
//		lblNulos.add(this.getView().getLblTipoCuenta());
		
		List<String> listaErrores = new ArrayList<String>();
		try {
			listaErrores.addAll(Validador.validarNulos(attNulos, lblNulos));
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
	
	void agregarTipoCuenta() throws Exception {
		AdminTipoCuenta adminTipoCuenta = new AdminTipoCuenta(this.getView());
		TipoCuenta locTipoCuenta = adminTipoCuenta.openSelect();
		if (locTipoCuenta != null) {
			this.getTableModelTipoCuenta().addRow(locTipoCuenta);
			actualizarABMModel();
			this.actualizarView();
		}
	}
	
	void quitarTipoCuenta() throws Exception {
		int fila = this.getView().getPnlTablaTipoCuenta().getTblDatos().getSelectedRow();
		this.getTableModelTipoCuenta().deleteRow(fila);
	}
	
}

class BtnAgregarTipoCuentaListener implements ActionListener {

	private ABMCuenta controller;

	public BtnAgregarTipoCuentaListener(ABMCuenta controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.agregarTipoCuenta();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnQuitarTipoCuentaListener implements ActionListener {

	private ABMCuenta controller;
	
	public BtnQuitarTipoCuentaListener(ABMCuenta controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.quitarTipoCuenta();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
