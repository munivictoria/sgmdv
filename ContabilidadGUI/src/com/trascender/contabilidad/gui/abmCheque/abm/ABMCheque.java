package com.trascender.contabilidad.gui.abmCheque.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

import com.trascender.contabilidad.gui.abmCheque.ChequeABMModel;
import com.trascender.contabilidad.gui.abmCuentaBancaria.AdminCuentaBancaria;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.Cheque;
import com.trascender.contabilidad.recurso.persistent.CuentaBancaria;
import com.trascender.gui.framework.abmStandard.ABMController;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Conversor;
import com.trascender.gui.framework.util.Validador;

public abstract class ABMCheque extends ABMController<Cheque>{

	public abstract ABMChequeView getView();
	public abstract ChequeABMModel getAbmModel();
	
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
		this.getView().getPnlBotonesSeleccion().getBtnSeleccionar().addActionListener(new BtnSeleccionarCuentaBancariaListener(this));
		this.getView().getPnlBotonesSeleccion().getBtnLimpiar().addActionListener(new BtnLimpiarCuentaBancariaListener(this));
	}
	
	@Override
	public void actualizarABMModel() {
		ChequeABMModel locModel = this.getAbmModel();
		
		locModel.setNumeroCheque(Conversor.getNullSiVacio(this.getView().getTfNumeroCheque().getText()));
		locModel.setFechaEmision(Conversor.getDate(this.getView().getTfFechaEmision().getText()));
		locModel.setFechaPago(Conversor.getDate(this.getView().getTfFechaPago().getText()));
		locModel.setImporte(Conversor.getDouble(this.getView().getTfImporte().getValue().toString()));
		locModel.setPostdatado(this.getView().getChkPostdatado().isSelected());
		
		locModel.fireActualizarDatos();		
	}

	@Override
	public void actualizarView() {
		this.getView().getTfNumeroCheque().setText(Conversor.getVacioSiNull(this.getAbmModel().getNumeroCheque()));
		this.getView().getTfFechaEmision().setValue(Conversor.getString(this.getAbmModel().getFechaEmision()));
		this.getView().getTfFechaPago().setValue(Conversor.getString(this.getAbmModel().getFechaPago()));
		this.getView().getTfImporte().setValue(this.getAbmModel().getImporte());
		this.getView().getChkPostdatado().setSelected(this.getAbmModel().isPostdatado());
		this.getView().getTfCuentaBancaria().setText(Conversor.getVacioSiNull(this.getAbmModel().getCuentaBancaria()));
	}
	
	@Override
	public boolean validarDatos() {
		boolean validacionOK = true;
		
		
		List<Object> attNulos = new ArrayList<Object>();
		List<JLabel> lblNulos = new ArrayList<JLabel>();
		
		List<String> attFecha = new ArrayList<String>();
		List<JLabel> lblFecha = new ArrayList<JLabel>();
		
		List<Object> attDecimales = new ArrayList<Object>();
		List<JLabel> lblDecimales = new ArrayList<JLabel>();
		
		List<String> attPositivo = new ArrayList<String>();
		List<JLabel> lblPositivo = new ArrayList<JLabel>();
		
		List<Object> attEnteros = new ArrayList<Object>();
		List<JLabel> lblEnteros = new ArrayList<JLabel>();
		
		attEnteros.add(this.getView().getTfNumeroCheque().getText());
		lblEnteros.add(this.getView().getLblNumeroCheque());
		
		attNulos.add(this.getView().getTfNumeroCheque().getText());
		lblNulos.add(this.getView().getLblNumeroCheque());
		
		attNulos.add(this.getView().getTfFechaEmision().getText());
		lblNulos.add(this.getView().getLblFechaEmision());
		
		attNulos.add(this.getView().getTfFechaPago().getText());
		lblNulos.add(this.getView().getLblFechaPago());
		
		attNulos.add(this.getView().getTfImporte().getValue());
		lblNulos.add(this.getView().getLblImporte());
		
		attNulos.add(this.getView().getTfCuentaBancaria().getText());
		lblNulos.add(this.getView().getLblCuentaBancaria());
		
		attFecha.add(this.getView().getTfFechaEmision().getText());
		lblFecha.add(this.getView().getLblFechaEmision());
		
		attFecha.add(this.getView().getTfFechaPago().getText());
		lblFecha.add(this.getView().getLblFechaPago());
		
		if (this.getView().getTfImporte().getValue() instanceof Double) {
			
			
		};
		
		attDecimales.add(this.getView().getTfImporte().getValue());
		lblDecimales.add(this.getView().getLblImporte());
		
		attPositivo.add(this.getView().getTfImporte().getValue().toString());
		lblPositivo.add(this.getView().getLblImporte());	
		
		List<String> listaErrores = new ArrayList<String>();
		try {
			listaErrores.addAll(Validador.validarEnteros(attEnteros, lblEnteros));
			listaErrores.addAll(Validador.validarNulos(attNulos, lblNulos));
			listaErrores.addAll(Validador.validarFechas(attFecha, lblFecha));
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

	void seleccionarCuentaBancaria() throws Exception { 
		AdminCuentaBancaria adminCuentaBancaria = new AdminCuentaBancaria(this.getView());
		CuentaBancaria locCuentaBancaria = adminCuentaBancaria.openSelect();
		
		if (locCuentaBancaria != null) {
			CuentaBancaria cuentaBancaria = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConciliacionBancaria().getCuentaBancariaByID(locCuentaBancaria.getIdCuentaBancaria());
			this.getAbmModel().setCuentaBancaria(cuentaBancaria);
			this.actualizarABMModel();
			this.actualizarView();
		}
	}
	
	void limpiarCuentaBancaria() {
		this.getAbmModel().setCuentaBancaria(null);
		this.actualizarABMModel();
		this.actualizarView();
	}
}

class BtnSeleccionarCuentaBancariaListener implements ActionListener {

	private ABMCheque controller;
	
	public BtnSeleccionarCuentaBancariaListener(ABMCheque controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.seleccionarCuentaBancaria();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnLimpiarCuentaBancariaListener implements ActionListener {

	private ABMCheque controller;
	
	public BtnLimpiarCuentaBancariaListener(ABMCheque controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.limpiarCuentaBancaria();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}