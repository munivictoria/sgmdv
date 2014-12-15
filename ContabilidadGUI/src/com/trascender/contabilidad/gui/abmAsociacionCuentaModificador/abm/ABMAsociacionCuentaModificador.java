package com.trascender.contabilidad.gui.abmAsociacionCuentaModificador.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

import com.trascender.contabilidad.gui.abmAsociacionCuentaModificador.AsociacionCuentaModificadorABMModel;
import com.trascender.contabilidad.gui.abmCuenta.AdminCuenta;
import com.trascender.contabilidad.gui.abmTipoModificador.AdminTipoModificador;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.contabilidad.recurso.persistent.CuentaModificador;
import com.trascender.gui.framework.abmStandard.ABMController;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Conversor;
import com.trascender.gui.framework.util.Validador;
import com.trascender.habilitaciones.recurso.persistent.TipoModificador;

public abstract class ABMAsociacionCuentaModificador extends ABMController<CuentaModificador> {

	@Override
	public abstract ABMAsociacionCuentaModificadorView getView();
	@Override
	public abstract AsociacionCuentaModificadorABMModel getAbmModel();

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
		this.getView().getPnlBtnSeleccionCuenta().getBtnSeleccionar().addActionListener(new BtnSeleccionarCuentaListener(this));
		this.getView().getPnlBtnSeleccionCuenta().getBtnLimpiar().addActionListener(new BtnLimpiarCuentaListener(this));

		this.getView().getPnlBtnSeleccionTipoModificador().getBtnSeleccionar().addActionListener(new BtnSeleccionarTipoModificadorListener(this));
		this.getView().getPnlBtnSeleccionTipoModificador().getBtnLimpiar().addActionListener(new BtnLimpiarTipoModificadorListener(this));

		this.getView().getPnlBtnSeleccionCuentaAtrasada().getBtnSeleccionar().addActionListener(new BtnSeleccionarCuentaAtrasadaListener(this));
		this.getView().getPnlBtnSeleccionCuentaAtrasada().getBtnLimpiar().addActionListener(new BtnLimpiarCuentaAtrasadaListener(this));
	}

	@Override
	protected void actualizarABMModel() {
		this.getAbmModel().fireActualizarDatos();
	}

	@Override
	public void actualizarView() {
		this.getView().getTfCuenta().setText(Conversor.getVacioSiNull(this.getAbmModel().getCuenta()));
		this.getView().getTfTipoModificador().setText(Conversor.getVacioSiNull(this.getAbmModel().getTipoModificador()));
		this.getView().getTfCuentaAtrasada().setText(Conversor.getVacioSiNull(this.getAbmModel().getCuentaAtrasada()));
	}

	@Override
	public boolean validarDatos() {
		boolean validacionOK = true;

		List<Object> attNulos = new ArrayList<Object>();
		List<JLabel> lblNulos = new ArrayList<JLabel>();

		attNulos.add(this.getView().getTfTipoModificador().getText());
		lblNulos.add(this.getView().getLblTipoModificador());
		attNulos.add(this.getView().getTfCuenta().getText());
		lblNulos.add(this.getView().getLblCuenta());

		List<String> listaErrores = new ArrayList<String>();
		try {
			listaErrores.addAll(Validador.validarNulos(attNulos, lblNulos));
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
			this.getAbmModel().setCuenta(cuenta);
			this.actualizarABMModel();
			this.actualizarView();
		}
	}

	void limpiarCuenta() throws Exception {
		this.getAbmModel().setCuenta(null);
		this.actualizarABMModel();
		this.actualizarView();
	}

	void seleccionarCuentaAtrasada() throws Exception {
		//Solo selecciona cuentas que admitan asientos
		AdminCuenta adminCuenta = new AdminCuenta(this.getView(),true);
		Cuenta locCuenta = adminCuenta.openSelect();

		if (locCuenta != null) {
			Cuenta cuenta = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().getCuentaByID(locCuenta.getIdCuenta());
			ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().validarCuenta(cuenta);
			this.getAbmModel().setCuentaAtrasada(cuenta);
			this.actualizarABMModel();
			this.actualizarView();
		}
	}

	void limpiarCuentaAtrasada() {
		this.getAbmModel().setCuentaAtrasada(null);
		this.actualizarABMModel();
		this.actualizarView();
	}

	void seleccionarTipoModificador() throws Exception {
		AdminTipoModificador adminTipoModificador = new AdminTipoModificador(this.getView());
		TipoModificador locTipoModificador = adminTipoModificador.openSelect();

		if (locTipoModificador != null) {
			this.getAbmModel().setTipoModificador(locTipoModificador);
			this.actualizarABMModel();
			this.actualizarView();
		}
	}

	void limpiarTipoModificador() throws Exception {
		this.getAbmModel().setTipoModificador(null);
		this.actualizarABMModel();
		this.actualizarView();
	}

}

class BtnSeleccionarCuentaListener implements ActionListener {

	private final ABMAsociacionCuentaModificador controller;

	public BtnSeleccionarCuentaListener(ABMAsociacionCuentaModificador controller) {
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

class BtnLimpiarCuentaListener implements ActionListener{

	private final ABMAsociacionCuentaModificador controller;

	public BtnLimpiarCuentaListener(ABMAsociacionCuentaModificador controller) {
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

class BtnSeleccionarCuentaAtrasadaListener implements ActionListener {

	private final ABMAsociacionCuentaModificador controller;

	public BtnSeleccionarCuentaAtrasadaListener(ABMAsociacionCuentaModificador controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.seleccionarCuentaAtrasada();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}

}

class BtnLimpiarCuentaAtrasadaListener implements ActionListener{

	private final ABMAsociacionCuentaModificador controller;

	public BtnLimpiarCuentaAtrasadaListener(ABMAsociacionCuentaModificador controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.limpiarCuentaAtrasada();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}

}

class BtnSeleccionarTipoModificadorListener implements ActionListener {

	private final ABMAsociacionCuentaModificador controller;

	public BtnSeleccionarTipoModificadorListener(ABMAsociacionCuentaModificador controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.seleccionarTipoModificador();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}

}

class BtnLimpiarTipoModificadorListener implements ActionListener {

	private final ABMAsociacionCuentaModificador controller;

	public BtnLimpiarTipoModificadorListener(ABMAsociacionCuentaModificador controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.limpiarTipoModificador();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}

}
