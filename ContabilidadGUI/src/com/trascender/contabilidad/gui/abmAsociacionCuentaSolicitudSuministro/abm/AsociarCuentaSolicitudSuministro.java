package com.trascender.contabilidad.gui.abmAsociacionCuentaSolicitudSuministro.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.contabilidad.gui.abmAsociacionCuentaSolicitudSuministro.AsociacionCuentaSolicitudSuministroABMModel;
import com.trascender.contabilidad.gui.abmAsociacionCuentaSolicitudSuministro.FirmaPermisoTableModel;
import com.trascender.contabilidad.gui.abmAsociacionCuentaSolicitudSuministro.LineaSolicitudSuministroTableModel;
import com.trascender.contabilidad.gui.abmCuenta.AdminCuenta;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.framework.recurso.persistent.referencia.CuentaRfr;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Conversor;

public class AsociarCuentaSolicitudSuministro extends ABMAsociacionCuentaSolicitudSuministro {

	private AsociacionCuentaSolicitudSuministroABMModel abmModel = new AsociacionCuentaSolicitudSuministroABMModel();
	private AsociarCuentaSolicitudSuministroView view;
	private FirmaPermisoTableModel firmaPermisoTableModel = new FirmaPermisoTableModel();
	private LineaSolicitudSuministroTableModel lineaSolicitudSuministroTableModel = new LineaSolicitudSuministroTableModel();
	
	public AsociarCuentaSolicitudSuministro(JDialog owner) throws Exception {
		this.view = new AsociarCuentaSolicitudSuministroView(owner);
		this.init();
	}
	
	public AsociarCuentaSolicitudSuministro(JFrame owner) throws Exception {
		this.view = new AsociarCuentaSolicitudSuministroView(owner);
		this.init();
	}
	
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
		
		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnAceptarListener(this));
	}
	
	@Override
	protected void actualizarABMModel() {
		this.getAbmModel().fireActualizarDatos();
	}

	@Override
	public void actualizarView() {
		this.getView().getTfSolicitudSuministro().setText(Conversor.getVacioSiNull(this.getAbmModel().getObjetoABM()));
//		this.getView().getTfCantidad().setText(Conversor.getVacioSiNull(this.getAbmModel().getObjetoABM().getCantidad()));
//		this.getView().getTfValor().setText(Conversor.getVacioSiNull(this.getAbmModel().getObjetoABM().getValorEstimado()));
		this.getView().getTfUsuario().setText(Conversor.getVacioSiNull(this.getAbmModel().getObjetoABM().getUsuario()));
//		this.getView().getTfCuenta().setText(Conversor.getVacioSiNull(this.getAbmModel().getCuentaRfr()));
	}

	@Override
	public AsociacionCuentaSolicitudSuministroABMModel getAbmModel() {
		return this.abmModel;
	}
		
	void seleccionarCuenta() throws Exception {
		AdminCuenta adminCuenta = new AdminCuenta(this.getView(), true);
		Cuenta locCuenta = adminCuenta.openSelect();
		
		if (locCuenta != null) {
			Cuenta cuenta = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().getCuentaByID(locCuenta.getIdCuenta());
			ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().validarCuenta(cuenta);
			CuentaRfr cuentaRfr = new CuentaRfr();
			cuentaRfr.setIdCuenta(cuenta.getIdCuenta());
			cuentaRfr.setNombre(cuenta.getNombre());
			cuentaRfr.setAbreviatura(cuenta.getAbreviatura());
			cuentaRfr.setCodigoImputacion(cuenta.getCodigoImputacion());
//			this.getAbmModel().setCuentaRfr(cuentaRfr);
			
			this.actualizarABMModel();
			this.actualizarView();
		}
	}
	
	void limpiarCuenta() {
//		this.getAbmModel().setCuentaRfr(null);
		this.actualizarABMModel();
		this.actualizarView();
	}

	void aceptarAsociacion() throws Exception {
		if (validarDatos()) {
			this.actualizarABMModel();
			this.getAbmModel().agregar();
			this.setOperacionRealizada(true);
			this.close();
		}
	}

	@Override
	public ABMAsociacionCuentaSolicitudSuministroView getView() {
		return this.getView();
	}

	@Override
	public FirmaPermisoTableModel getFirmaPermisoTableModel() {
		return this.firmaPermisoTableModel;
	}

	@Override
	public LineaSolicitudSuministroTableModel getLineaSolicitudSuministroTableModel() {
		return this.lineaSolicitudSuministroTableModel;
	}

}

class BtnSeleccionarCuentaListener1 implements ActionListener {

	private AsociarCuentaSolicitudSuministro controller;
		
	public BtnSeleccionarCuentaListener1(
			AsociarCuentaSolicitudSuministro controller) {
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

class BtnLimpiarCuentaListener1 implements ActionListener{

	private AsociarCuentaSolicitudSuministro controller;
	
	public BtnLimpiarCuentaListener1(AsociarCuentaSolicitudSuministro controller) {
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

class BtnAceptarListener implements ActionListener {
	private AsociarCuentaSolicitudSuministro controller;
	
	public BtnAceptarListener(AsociarCuentaSolicitudSuministro controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.aceptarAsociacion();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}

}

