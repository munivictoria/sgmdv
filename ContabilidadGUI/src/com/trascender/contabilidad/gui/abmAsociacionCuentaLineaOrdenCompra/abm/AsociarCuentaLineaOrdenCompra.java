package com.trascender.contabilidad.gui.abmAsociacionCuentaLineaOrdenCompra.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.trascender.compras.recurso.persistent.suministros.LineaOrdenCompra;
import com.trascender.contabilidad.gui.abmCuenta.AdminCuenta;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.framework.recurso.persistent.referencia.CuentaRfr;
import com.trascender.gui.framework.abmStandard.ABMController;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Conversor;
import com.trascender.gui.framework.util.Validador;

public class AsociarCuentaLineaOrdenCompra extends ABMController<LineaOrdenCompra> {

	private  AsociarCuentaLineaOrdenCompraView view;
	private  AsociarCuentaLineaOrdenCompraModel abmModel = new AsociarCuentaLineaOrdenCompraModel();
	
	public AsociarCuentaLineaOrdenCompra(JFrame owner) {
		this.view = new AsociarCuentaLineaOrdenCompraView(owner);
		this.init();
	}
	
	public AsociarCuentaLineaOrdenCompra(JDialog owner) {
		this.view = new AsociarCuentaLineaOrdenCompraView(owner);
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
	protected void actualizarView() {
		this.getView().getTfOrdenCompra().setText(Conversor.getVacioSiNull(this.getAbmModel().getObjetoABM()));
		this.getView().getTfCuenta().setText(Conversor.getVacioSiNull(this.getAbmModel().getCuentaRfr()));
	}
	
	@Override
	public boolean validarDatos() {
		boolean validacionOK = true;
		
		List<Object> attNulos = new ArrayList<Object>();
		List<JLabel> lblNuLos = new ArrayList<JLabel>();
		
		attNulos.add(this.getView().getTfOrdenCompra().getText());
		lblNuLos.add(this.getView().getLblOrdenCompra());
		
		attNulos.add(this.getView().getTfCuenta().getText());
		lblNuLos.add(this.getView().getLblCuenta());
		
		List<String> listaErrores = new ArrayList<String>();
		
		try {
			listaErrores.addAll(Validador.validarNulos(attNulos, lblNuLos));
		} catch (GuiException ex) {
			ex.printStackTrace();
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
			CuentaRfr cuentaRef = new CuentaRfr();
			cuentaRef.setAbreviatura(cuenta.getAbreviatura());
			cuentaRef.setCodigoImputacion(cuenta.getCodigoImputacion());
			cuentaRef.setIdCuenta(cuenta.getIdCuenta());
			cuentaRef.setNombre(cuenta.getNombre());
			this.getAbmModel().setCuentaRfr(cuentaRef);
			
			this.actualizarABMModel();
			this.actualizarView();
		}
	}
	
	void limpiarCuenta() {
		this.getAbmModel().setCuentaRfr(null);
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
	protected AsociarCuentaLineaOrdenCompraModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	protected AsociarCuentaLineaOrdenCompraView getView() {
		return this.view;
	}
	
}

class BtnSeleccionarCuentaListener implements ActionListener {

	private AsociarCuentaLineaOrdenCompra controller;
	
	public BtnSeleccionarCuentaListener(AsociarCuentaLineaOrdenCompra controller) {
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

	private AsociarCuentaLineaOrdenCompra controller;
	
	public BtnLimpiarCuentaListener(AsociarCuentaLineaOrdenCompra controller) {
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
	private AsociarCuentaLineaOrdenCompra controller;
	
	public BtnAceptarListener(AsociarCuentaLineaOrdenCompra controller) {
		super();
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