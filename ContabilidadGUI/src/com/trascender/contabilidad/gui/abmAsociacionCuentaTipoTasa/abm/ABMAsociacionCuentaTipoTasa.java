package com.trascender.contabilidad.gui.abmAsociacionCuentaTipoTasa.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

import com.trascender.contabilidad.gui.abmAsociacionCuentaTipoTasa.AsociacionCuentaTipoTasaABMModel;
import com.trascender.contabilidad.gui.abmCuenta.AdminCuenta;
import com.trascender.contabilidad.gui.abmTipoTasa.AdminTipoTasa;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.contabilidad.recurso.persistent.CuentaTipoTasa;
import com.trascender.gui.framework.abmStandard.ABMController;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Conversor;
import com.trascender.gui.framework.util.Validador;
import com.trascender.habilitaciones.recurso.persistent.TipoTasa;

public abstract class ABMAsociacionCuentaTipoTasa extends ABMController<CuentaTipoTasa> {

	@Override
	public abstract ABMAsociacionCuentaTipoTasaView getView();
	@Override
	public abstract AsociacionCuentaTipoTasaABMModel getAbmModel();


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

		this.getView().getPnlBtnSeleccionCuentaAtrasada().getBtnSeleccionar().addActionListener(new BtnSeleccionarCuentaAtrasadaListener(this));
		this.getView().getPnlBtnSeleccionCuentaAtrasada().getBtnLimpiar().addActionListener(new BtnLimpiarCuentaAtrasadaListener(this));

		this.getView().getPnlBtnSeleccionTipoTasa().getBtnSeleccionar().addActionListener(new BtnSeleccionarTipoTasaListener(this));
		this.getView().getPnlBtnSeleccionTipoTasa().getBtnLimpiar().addActionListener(new BtnLimpiarTipoTasaListener(this));
	}

	void seleccionarTipoTasa() throws Exception {
		AdminTipoTasa adminTipoTasa = new AdminTipoTasa(this.getView());		
		TipoTasa locTipoTasa = adminTipoTasa.openSelect();

		if (locTipoTasa != null) {
			TipoTasa tipoTasa = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemTipoTasa().getTipoTasaPorId(locTipoTasa.getIdTipoTasa());
			this.getAbmModel().setTipoTasa(tipoTasa);
			this.actualizarABMModel();
			this.actualizarView();
		}
	}

	void limpiarTipoTasa() {
		this.getAbmModel().setTipoTasa(null);
		this.actualizarABMModel();
		this.actualizarView();
	}


	/**
	 * Abre la ventana de seleccionar cuenta y trae el objeto cuenta
	 * @throws Exception
	 */
	void seleccionarCuenta() throws Exception {
		//Solo selecciona cuentas que admitan asientos
		AdminCuenta adminCuenta = new AdminCuenta(this.getView(),true);
		Cuenta locCuenta = adminCuenta.openSelect();

		if (locCuenta != null) {
			Cuenta cuenta = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().getCuentaByID(locCuenta.getIdCuenta());
			ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().validarCuenta(cuenta);
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

	@Override
	protected void actualizarABMModel() {
		this.getAbmModel().fireActualizarDatos();
	}

	@Override
	public void actualizarView() {
		this.getView().getTfTipoTasa().setText(Conversor.getVacioSiNull(this.getAbmModel().getTipoTasa()));
		this.getView().getTfCuenta().setText(Conversor.getVacioSiNull(this.getAbmModel().getCuenta()));
		this.getView().getTfCuentaAtrasada().setText(Conversor.getVacioSiNull(this.getAbmModel().getCuentaAtrasada()));
	}

	@Override
	public boolean validarDatos() {
		boolean validacionOK = true;

		List<Object> attNulos = new ArrayList<Object>();
		List<JLabel> lblNuLos = new ArrayList<JLabel>();

		attNulos.add(this.getView().getTfTipoTasa().getText());
		lblNuLos.add(this.getView().getLblTipoTasa());
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

}

class BtnSeleccionarCuentaListener implements ActionListener {

	private final ABMAsociacionCuentaTipoTasa controller;

	public BtnSeleccionarCuentaListener(ABMAsociacionCuentaTipoTasa controller) {
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

	private final ABMAsociacionCuentaTipoTasa controller;

	public BtnLimpiarCuentaListener(ABMAsociacionCuentaTipoTasa controller) {
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

	private final ABMAsociacionCuentaTipoTasa controller;

	public BtnSeleccionarCuentaAtrasadaListener(ABMAsociacionCuentaTipoTasa controller) {
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

	private final ABMAsociacionCuentaTipoTasa controller;

	public BtnLimpiarCuentaAtrasadaListener(ABMAsociacionCuentaTipoTasa controller) {
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

class BtnSeleccionarTipoTasaListener implements ActionListener {

	private final ABMAsociacionCuentaTipoTasa controller;

	public BtnSeleccionarTipoTasaListener(ABMAsociacionCuentaTipoTasa controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.seleccionarTipoTasa();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}

}

class BtnLimpiarTipoTasaListener implements ActionListener {

	private final ABMAsociacionCuentaTipoTasa controller;

	public BtnLimpiarTipoTasaListener(ABMAsociacionCuentaTipoTasa controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.limpiarTipoTasa();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}

}
