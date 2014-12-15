package com.trascender.contabilidad.gui.abmAsociacionCuentaInteresRecargo.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

import com.trascender.contabilidad.gui.abmAsociacionCuentaInteresRecargo.AsociacionCuentaInteresRecargoABMModel;
import com.trascender.contabilidad.gui.abmConceptoPorMora.AdminConceptoPorMora;
import com.trascender.contabilidad.gui.abmCuenta.AdminCuenta;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.contabilidad.recurso.persistent.CuentaInteresRecargo;
import com.trascender.gui.framework.abmStandard.ABMController;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Conversor;
import com.trascender.gui.framework.util.Validador;
import com.trascender.habilitaciones.recurso.persistent.ConceptoPorMora;

public abstract class ABMAsociacionCuentaInteresRecargo extends ABMController<CuentaInteresRecargo> {

	@Override
	public abstract ABMAsociacionCuentaInteresRecargoView getView();
	@Override
	public abstract AsociacionCuentaInteresRecargoABMModel getAbmModel();

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

		this.getView().getPnlBtnSeleccionInteresRecargo().getBtnSeleccionar().addActionListener(new BtnSeleccionarConceptoPorMoraListener(this));
		this.getView().getPnlBtnSeleccionInteresRecargo().getBtnLimpiar().addActionListener(new BtnLimpiarConceptoPorMoraListener(this));

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
		this.getView().getTfInteresRecargo().setText(Conversor.getVacioSiNull(this.getAbmModel().getConceptoPorMora()));
		this.getView().getTfCuentaAtrasada().setText(Conversor.getVacioSiNull(this.getAbmModel().getCuentaAtrasada()));
	}

	@Override
	public boolean validarDatos() {
		boolean validacionOK = true;

		List<Object> attNulos = new ArrayList<Object>();
		List<JLabel> lblNulos = new ArrayList<JLabel>();

		attNulos.add(this.getView().getTfInteresRecargo().getText());
		lblNulos.add(this.getView().getLblInteresRecargo());
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

	void seleccionarConceptoPorMora() throws Exception {
		AdminConceptoPorMora adminConceptoPorMora = new AdminConceptoPorMora(this.getView());
		ConceptoPorMora locConceptoPorMora = adminConceptoPorMora.openSelect();

		if (locConceptoPorMora != null) {
			this.getAbmModel().setConceptoPorMora(locConceptoPorMora);
			this.actualizarABMModel();
			this.actualizarView();
		}
	}

	void limpiarConceptoPorMora() throws Exception {
		this.getAbmModel().setConceptoPorMora(null);
		this.actualizarABMModel();
		this.actualizarView();
	}

}

class BtnSeleccionarCuentaListener implements ActionListener {

	private final ABMAsociacionCuentaInteresRecargo controller;

	public BtnSeleccionarCuentaListener(ABMAsociacionCuentaInteresRecargo controller) {
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

	private final ABMAsociacionCuentaInteresRecargo controller;

	public BtnLimpiarCuentaListener(ABMAsociacionCuentaInteresRecargo controller) {
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

	private final ABMAsociacionCuentaInteresRecargo controller;

	public BtnSeleccionarCuentaAtrasadaListener(ABMAsociacionCuentaInteresRecargo controller) {
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

	private final ABMAsociacionCuentaInteresRecargo controller;

	public BtnLimpiarCuentaAtrasadaListener(ABMAsociacionCuentaInteresRecargo controller) {
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

class BtnSeleccionarConceptoPorMoraListener implements ActionListener {

	private final ABMAsociacionCuentaInteresRecargo controller;

	public BtnSeleccionarConceptoPorMoraListener(ABMAsociacionCuentaInteresRecargo controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.seleccionarConceptoPorMora();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}

}

class BtnLimpiarConceptoPorMoraListener implements ActionListener {

	private final ABMAsociacionCuentaInteresRecargo controller;

	public BtnLimpiarConceptoPorMoraListener(ABMAsociacionCuentaInteresRecargo controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.limpiarConceptoPorMora();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}

}
