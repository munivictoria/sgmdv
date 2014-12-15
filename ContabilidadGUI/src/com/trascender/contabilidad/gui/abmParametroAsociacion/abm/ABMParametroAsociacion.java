package com.trascender.contabilidad.gui.abmParametroAsociacion.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

import com.trascender.contabilidad.gui.abmCuenta.AdminCuenta;
import com.trascender.contabilidad.gui.abmParametroAsociacion.ParametroAsociacionABMModel;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.contabilidad.recurso.persistent.ParametroAsociacion;
import com.trascender.gui.framework.abmStandard.ABMController;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Conversor;
import com.trascender.gui.framework.util.Validador;

public abstract class ABMParametroAsociacion extends ABMController<ParametroAsociacion> {
	
	public abstract ParametroAsociacionABMModel getAbmModel();
	public abstract ABMParametroAsociacionView getView();
	
	@Override
	protected void init() {
		super.init();
		this.setModels();
		this.setListeners();
	}
	
	private void setModels() {
		this.getView().setAbmModel(this.getAbmModel());
	}
	
	private void setListeners() {
		this.getView().getPnlBotonesSeleccionCuenta().getBtnSeleccionar().addActionListener(new BtnSeleccionarCuentaListener(this));
		this.getView().getPnlBotonesSeleccionCuenta().getBtnLimpiar().addActionListener(new BtnLimpiarCuentaListener(this));
	}
	
	@Override
	public void actualizarView() {
		this.getView().getTfCuenta().setText(Conversor.getVacioSiNull(this.getAbmModel().getObjetoABM().getCuenta()));
		this.getView().getTfPorcentaje().setText(Conversor.getString(this.getAbmModel().getPorcentaje()));
	}
	
	@Override
	protected void actualizarABMModel() {
		ParametroAsociacionABMModel locModel = this.getAbmModel();
		locModel.setPorcentaje(Conversor.getDouble(this.getView().getTfPorcentaje().getText()));
		locModel.fireActualizarDatos();
	}
	
	@Override
	public boolean validarDatos() {
		boolean validacionOK = true;
		
		
		List<Object> attNulos = new ArrayList<Object>();
		List<JLabel> lblNulos = new ArrayList<JLabel>();
		
		List<Object> attDecimales = new ArrayList<Object>();
		List<JLabel> lblDecimales = new ArrayList<JLabel>();
		
		List<String> attPositivo = new ArrayList<String>();
		List<JLabel> lblPositivo = new ArrayList<JLabel>();
		
		List<Object> attEnteros = new ArrayList<Object>();
		List<JLabel> lblEnteros = new ArrayList<JLabel>();
		
		attNulos.add(this.getView().getTfPorcentaje().getText());
		lblNulos.add(this.getView().getLblPorcentaje());
		
		List<String> listaErrores = new ArrayList<String>();
		try {
			listaErrores.addAll(Validador.validarEnteros(attEnteros, lblEnteros));
			listaErrores.addAll(Validador.validarNulos(attNulos, lblNulos));
			listaErrores.addAll(Validador.validarDecimales(attDecimales, lblDecimales));
			listaErrores.addAll(Validador.validarPositivos(attPositivo, lblPositivo));
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
	
	void seleccionarCuenta() throws Exception {
		AdminCuenta adminCuenta = new AdminCuenta(this.getView(), true);
		Cuenta locCuenta = adminCuenta.openSelect();
		
		if (locCuenta != null) {
			Cuenta cuenta = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().getCuentaByID(locCuenta.getIdCuenta());
			ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().validarCuenta(cuenta);
			this.getAbmModel().getObjetoABM().setCuenta(cuenta);
			this.actualizarABMModel();
			this.actualizarView();
		}
	}
	
	void limpiarCuenta() throws Exception {
		this.getAbmModel().getObjetoABM().setCuenta(null);
		this.actualizarABMModel();
		this.actualizarView();
	}
}

class BtnSeleccionarCuentaListener implements ActionListener {
	ABMParametroAsociacion controller;
	
	public BtnSeleccionarCuentaListener(
			ABMParametroAsociacion controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.seleccionarCuenta();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnLimpiarCuentaListener implements ActionListener {
	ABMParametroAsociacion controller;

	public BtnLimpiarCuentaListener(
			ABMParametroAsociacion controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.limpiarCuenta();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}