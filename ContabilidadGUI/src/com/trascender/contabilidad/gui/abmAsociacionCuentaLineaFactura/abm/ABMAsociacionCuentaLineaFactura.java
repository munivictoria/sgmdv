package com.trascender.contabilidad.gui.abmAsociacionCuentaLineaFactura.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JLabel;

import com.trascender.contabilidad.gui.abmAsociacionCuentaLineaFactura.AsociacionCuentaLineaFacturaABMModel;
import com.trascender.contabilidad.gui.abmCuenta.AdminCuenta;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.contabilidad.recurso.persistent.CuentaLineaFactura;
import com.trascender.framework.recurso.transients.Periodo;
import com.trascender.framework.util.Periodicidad;
import com.trascender.gui.framework.abmStandard.ABMController;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Conversor;
import com.trascender.gui.framework.util.Validador;

public abstract class ABMAsociacionCuentaLineaFactura extends ABMController<CuentaLineaFactura>{

	public abstract AsociacionCuentaLineaFacturaABMModel getAbmModel();
	public abstract ABMAsociacionCuentaLineaFacturaView getView();
	
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
	}
	
	@Override
	protected void actualizarABMModel() {
		Integer locAnio = Conversor.getInteger(this.getView().getTfAnioPeriodo().getText());
		Periodo locPeriodo = null;
		if (locAnio != null) {
			try {
				locPeriodo = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemRegistroValuado().getPeriodo(Periodicidad.ANUAL, 1, locAnio);
			}
			catch (Exception ex) {
				ex.printStackTrace();
				AppManager.getInstance().showErrorMsg(this.getView(), ex.getMessage());
			}
		}
		this.getAbmModel().setPeriodo(locPeriodo);
		
		this.getAbmModel().fireActualizarDatos();
	}
	
	@Override
	public void actualizarView() {
		Periodo locPeriodo = this.getAbmModel().getPeriodo();
		String anio = "";
		if (locPeriodo != null) {
			anio = String.valueOf(locPeriodo.getFechaInicio().get(Calendar.YEAR));
		}
		this.getView().getTfAnioPeriodo().setText(anio);
		this.getView().getTfLineaFactura().setText(Conversor.getVacioSiNull(this.getAbmModel().getLineaFactura()));
		this.getView().getTfCuenta().setText(Conversor.getVacioSiNull(this.getAbmModel().getCuenta()));
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
	
	@Override
	public boolean validarDatos() {
		boolean validacionOK = true;
		
		List<Object> attNulos = new ArrayList<Object>();
		List<JLabel> lblNulos = new ArrayList<JLabel>();
		
		attNulos.add(this.getView().getTfAnioPeriodo().getText());
		lblNulos.add(this.getView().getLblAnioPeriodo());
		attNulos.add(this.getView().getTfCuenta().getText());
		lblNulos.add(this.getView().getLblCuenta());
		
		List<Object> attAnio = new ArrayList<Object>();
		List<JLabel> lblAnio = new ArrayList<JLabel>();
		List<Integer> longAnio = new ArrayList<Integer>();
		
		attAnio.add(this.getView().getTfAnioPeriodo().getText());
		lblAnio.add(this.getView().getLblAnioPeriodo());
		longAnio.add(4);
		
		List<String> listaErrores = new ArrayList<String>();
		try {
			listaErrores.addAll(Validador.validarNulos(attNulos, lblNulos));
			listaErrores.addAll(Validador.validarEnteros(attAnio, lblAnio));
			listaErrores.addAll(Validador.validarLongitudExacta(attAnio, lblAnio, longAnio));
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

	private ABMAsociacionCuentaLineaFactura controller;
	
	public BtnSeleccionarCuentaListener(ABMAsociacionCuentaLineaFactura controller) {
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

	private ABMAsociacionCuentaLineaFactura controller;
	
	public BtnLimpiarCuentaListener(ABMAsociacionCuentaLineaFactura controller) {
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
