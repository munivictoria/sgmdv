package com.trascender.caja.gui.abmMovimientoCajaChica.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

import com.trascender.caja.gui.abmCajaChica.AdminCajaChica;
import com.trascender.caja.gui.abmConceptoMovimientoCajaChica.AdminConceptoMovimientoCajaChica;
import com.trascender.caja.gui.abmMovimientoCajaChica.MovimientoCajaChicaABMModel;
import com.trascender.caja.gui.main.CajaGUI;
import com.trascender.contabilidad.recurso.persistent.CajaChica;
import com.trascender.contabilidad.recurso.persistent.ConceptoMovimientoCajaChica;
import com.trascender.contabilidad.recurso.persistent.MovimientoCajaChica;
import com.trascender.gui.framework.abmStandard.ABMController;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Conversor;
import com.trascender.gui.framework.util.Validador;

public abstract class ABMMovimientoCajaChica extends ABMController<MovimientoCajaChica> {

	public abstract ABMMovimientoCajaChicaView getView();
	public abstract MovimientoCajaChicaABMModel getAbmModel();
	
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
		this.getView().getPnlBotonesSeleccionCajaChica().getBtnSeleccionar().addActionListener(new BtnSeleccionarCajaChicaListener(this));
		this.getView().getPnlBotonesSeleccionCajaChica().getBtnLimpiar().addActionListener(new BtnLimpiarCajaChicaListener(this));
		
		this.getView().getPnlBotonesSeleccionConcepto().getBtnSeleccionar().addActionListener(new BtnSeleccionarConceptoListener(this));
		this.getView().getPnlBotonesSeleccionConcepto().getBtnLimpiar().addActionListener(new BtnLimpiarConceptoListener(this));
	}
	
	@Override
	public void actualizarABMModel() {
		MovimientoCajaChicaABMModel locModel = this.getAbmModel();
		
		Double importe;
		if ((this.getView().getTfImporte().getText()).equals("")) {
			importe = new Double("0,00");
		} else {
			importe = Conversor.getDouble(this.getView().getTfImporte().getValue());
		}
	
		locModel.setImporte(importe);
		
		locModel.fireActualizarDatos();
	}
	
	@Override
	public void actualizarView() {
		this.getView().getTfCajaChica().setText(Conversor.getVacioSiNull(this.getAbmModel().getCajaChica()));
		this.getView().getTfConcepto().setText(Conversor.getVacioSiNull(this.getAbmModel().getConceptoMovimiento()));
		this.getView().getTfImporte().setValue(this.getAbmModel().getImporte());
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
		
		List<Float> attMayorCero = new ArrayList<Float>();
		List<JLabel> lblMayorCero = new ArrayList<JLabel>();
		
		attNulos.add(this.getView().getTfCajaChica().getText());
		lblNulos.add(this.getView().getLblCajaChica());
		
		attNulos.add(this.getView().getTfConcepto().getText());
		lblNulos.add(this.getView().getLblConcepto());
		
		attNulos.add(this.getView().getTfImporte().getValue());
		lblNulos.add(this.getView().getLblImporte());
		
		attDecimales.add(this.getView().getTfImporte().getValue());
		lblDecimales.add(this.getView().getLblImporte());
		
		attPositivo.add(this.getView().getTfImporte().getValue().toString());
		lblPositivo.add(this.getView().getLblImporte());	
		
		attMayorCero.add(Conversor.getFloat(this.getView().getTfImporte().getValue().toString()));
		lblMayorCero.add(this.getView().getLblImporte());
		
		List<String> listaErrores = new ArrayList<String>();
		try {
			listaErrores.addAll(Validador.validarNulos(attNulos, lblNulos));
			listaErrores.addAll(Validador.validarDecimales(attDecimales, lblDecimales));
			listaErrores.addAll(Validador.validarPositivos(attPositivo, lblPositivo));
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

	void seleccionarCajaChica() throws Exception { 
		AdminCajaChica adminCajaChica = new AdminCajaChica(this.getView());
		CajaChica locCajaChica = adminCajaChica.openSelect();
		
		if (locCajaChica != null) {
			CajaChica cajaChica = CajaGUI.getInstance().getAdminSystemsCaja().getSystemAdministracionCajaChica().findCajaChicaByID(locCajaChica.getIdCajaChica());
			this.getAbmModel().setCajaChica(cajaChica);
			this.actualizarABMModel();
			this.actualizarView();
			
		}
	}
	
	void limpiarCuentaBancaria() {
		this.getAbmModel().setCajaChica(null);
		this.actualizarABMModel();
		this.actualizarView();
	}
	
	void seleccionarConcepto() throws Exception {
		AdminConceptoMovimientoCajaChica adminConceptoMovimientoCajaChica = new AdminConceptoMovimientoCajaChica(this.getView());
		ConceptoMovimientoCajaChica locConceptoMovimientoCajaChica = adminConceptoMovimientoCajaChica.openSelect();
		
		if (locConceptoMovimientoCajaChica != null) {
			ConceptoMovimientoCajaChica conceptoMovimientoCajaChica = CajaGUI.getInstance().getAdminSystemsCaja().getSystemAdministracionCajaChica().findConceptoMovimientoCajaChicaByID(locConceptoMovimientoCajaChica.getIdConceptoMovimientoCajaChica());
			this.getAbmModel().setConceptoMovimiento(conceptoMovimientoCajaChica);
			this.actualizarABMModel();
			this.actualizarView();
		}
	}
	
	void limpiarConcepto(){
		this.getAbmModel().setConceptoMovimiento(null);
		this.actualizarABMModel();
		this.actualizarView();
	}
}

class BtnSeleccionarCajaChicaListener implements ActionListener {
	private ABMMovimientoCajaChica controller;
	public BtnSeleccionarCajaChicaListener(ABMMovimientoCajaChica controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.seleccionarCajaChica();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnLimpiarCajaChicaListener implements ActionListener {
	private ABMMovimientoCajaChica controller;
	public BtnLimpiarCajaChicaListener(ABMMovimientoCajaChica controller) {
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

class BtnSeleccionarConceptoListener implements ActionListener {
	private ABMMovimientoCajaChica controller;
	public BtnSeleccionarConceptoListener(ABMMovimientoCajaChica controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.seleccionarConcepto();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnLimpiarConceptoListener implements ActionListener {
	private ABMMovimientoCajaChica controller;
	public BtnLimpiarConceptoListener(ABMMovimientoCajaChica controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.limpiarConcepto();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}
