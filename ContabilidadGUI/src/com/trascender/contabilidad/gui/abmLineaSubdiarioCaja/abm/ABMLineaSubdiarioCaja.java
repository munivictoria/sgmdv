package com.trascender.contabilidad.gui.abmLineaSubdiarioCaja.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

import com.trascender.contabilidad.gui.abmCuenta.AdminCuenta;
import com.trascender.contabilidad.gui.abmLineaSubdiarioCaja.LineaSubdiarioCajaABMModel;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.contabilidad.recurso.persistent.LineaSubdiarioCaja;
import com.trascender.gui.framework.abmStandard.ABMController;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Conversor;
import com.trascender.gui.framework.util.Validador;

public abstract class ABMLineaSubdiarioCaja extends ABMController<LineaSubdiarioCaja> {

	@Override
	public abstract LineaSubdiarioCajaABMModel getAbmModel();
	public abstract ABMLineaSubdiarioCajaView getView();
	
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
	protected void actualizarABMModel() {
		this.getAbmModel().setDia(Conversor.getInteger(this.getView().getTfDia().getText()));
		this.getAbmModel().setImporte(Conversor.getDouble(this.getView().getTfImporte().getValue().toString()));
		
		this.getAbmModel().fireActualizarDatos();
	}
	
	@Override
	public void actualizarView() {
		this.getView().getTfCuenta().setText(Conversor.getVacioSiNull(this.getAbmModel().getCuenta()));
		this.getView().getTfDia().setText(Conversor.getDia(Conversor.getString(this.getAbmModel().getDia())));
		this.getView().getTfImporte().setValue(this.getAbmModel().getImporte());
	}
	
	@Override
	public boolean validarDatos() {
		boolean validacionOK = true;
		
		List<Object> attNulos = new ArrayList<Object>();
		List<JLabel> lblNulos = new ArrayList<JLabel>();
		
		List<Object> attEnteros = new ArrayList<Object>();
		List<JLabel> lblEnteros = new ArrayList<JLabel>();
		
		List<Object> attDia = new ArrayList<Object>();
		List<JLabel> lblDia = new ArrayList<JLabel>();
		List<Integer> longDia = new ArrayList<Integer>();
		
		List<Object> attDecimales = new ArrayList<Object>();
		List<JLabel> lblDecimales = new ArrayList<JLabel>();
		
		List<Float> attMayorCero = new ArrayList<Float>();
		List<JLabel> lblMayorCero = new ArrayList<JLabel>();
		
		attDia.add(this.getView().getTfDia().getText());
		lblDia.add(this.getView().getLblDia());
		longDia.add(2);

		attNulos.add(this.getView().getTfCuenta().getText());
		lblNulos.add(this.getView().getLblCuenta());
		
		attNulos.add(this.getView().getTfDia().getText());
		lblNulos.add(this.getView().getLblDia());
		
		attNulos.add(this.getView().getTfImporte().getValue());
		lblNulos.add(this.getView().getLblImporte());
		
		attDecimales.add(this.getView().getTfImporte().getValue());
		lblDecimales.add(this.getView().getLblImporte());
		
		attMayorCero.add(Conversor.getFloat(this.getView().getTfImporte().getValue().toString()));
		lblMayorCero.add(this.getView().getLblImporte());
		
		List<String> listaErrores = new ArrayList<String>();
		try {
			listaErrores.addAll(Validador.validarNulos(attNulos, lblNulos));
			listaErrores.addAll(Validador.validarLongitudExacta(attDia, lblDia, longDia));
			listaErrores.addAll(Validador.validarEnteros(attEnteros, lblEnteros));
			listaErrores.addAll(Validador.validarDecimales(attDecimales, lblDecimales));
			listaErrores.addAll(Validador.validarSiEsCero(attMayorCero, lblMayorCero));
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
	
	public boolean validarDia(String fecha) {
		boolean validacionOK = true;
		
		List<String> attFechas = new ArrayList<String>();
		List<JLabel> lblFechas = new ArrayList<JLabel>();
		
		attFechas.add(fecha);
		lblFechas.add(this.getView().getLblDia());
		
		List<String> listaErrores = new ArrayList<String>();
		try {
			listaErrores.addAll(Validador.validarDia(attFechas, lblFechas));
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
			this.getAbmModel().setCuenta(cuenta);
			this.actualizarABMModel();
			this.actualizarView();
		}
	}
	
	void limpiarCuenta() {
		this.getAbmModel().setCuenta(null);
		this.actualizarABMModel();
		this.actualizarView();
	}

}


class BtnSeleccionarCuentaListener implements ActionListener {
	private ABMLineaSubdiarioCaja controller;
	public BtnSeleccionarCuentaListener(ABMLineaSubdiarioCaja controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.seleccionarCuenta();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnLimpiarCuentaListener implements ActionListener{
	private ABMLineaSubdiarioCaja controller;
	public BtnLimpiarCuentaListener(ABMLineaSubdiarioCaja controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.limpiarCuenta();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}
