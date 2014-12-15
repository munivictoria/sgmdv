package com.trascender.contabilidad.gui.abmAsociacionCuentaArticulo.abmEliminar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JLabel;

import com.trascender.contabilidad.gui.abmAsociacionCuentaArticulo.AsociacionCuentaArticuloABMModel;
import com.trascender.contabilidad.gui.abmCuenta.AdminCuenta;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.contabilidad.recurso.persistent.CuentaArticulo;
import com.trascender.framework.recurso.transients.Periodo;
import com.trascender.gui.framework.abmStandard.ABMController;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Conversor;
import com.trascender.gui.framework.util.Validador;

public abstract class ABMAsociacionCuentaArticuloEliminar extends ABMController<CuentaArticulo>{
	
	public abstract ABMAsociacionCuentaArticuloEliminarView getView();
	public abstract AsociacionCuentaArticuloABMModel getAbmModel();
	public abstract BajaArticuloABMModel getAbmModelBajaArticulo();
	
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
		this.getView().getPnlBtnSeleccionCuentaIngreso().getBtnSeleccionar().addActionListener(new BtnSeleccionarCuentaIngresoListener(this));
		this.getView().getPnlBtnSeleccionCuentaIngreso().getBtnLimpiar().addActionListener(new BtnLimpiarCuentaIngresoListener(this));
		
		this.getView().getPnlBtnSeleccionCuentaEgreso().getBtnSeleccionar().addActionListener(new BtnSeleccionarCuentaEgresoListener(this));
		this.getView().getPnlBtnSeleccionCuentaEgreso().getBtnLimpiar().addActionListener(new BtnLimpiarCuentaEgresoListener(this));
	}
	
	void seleccionarCuentaIngreso() throws Exception {
		//Solo selecciona cuentas que admitan asientos
		AdminCuenta adminCuenta = new AdminCuenta(this.getView(),true);
		Cuenta locCuenta = adminCuenta.openSelect();
		
		if (locCuenta != null) {
			Cuenta cuenta = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().getCuentaByID(locCuenta.getIdCuenta());
			ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().validarCuenta(cuenta);
			this.getAbmModelBajaArticulo().setCuentaIngreso(cuenta);
			this.actualizarABMModel();
			this.actualizarView();
		}
	}
	
	void limpiarCuentaIngreso() {
		this.getAbmModelBajaArticulo().setCuentaIngreso(null);
		this.actualizarABMModel();
		this.actualizarView();
	}
	
	void seleccionarCuentaEgreso() throws Exception {
		//Solo selecciona cuentas que admitan asientos
		AdminCuenta adminCuenta = new AdminCuenta(this.getView(),true);
		Cuenta locCuenta = adminCuenta.openSelect();
		
		if (locCuenta != null) {
			Cuenta cuenta = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().getCuentaByID(locCuenta.getIdCuenta());
			ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().validarCuenta(cuenta);
			this.getAbmModelBajaArticulo().setCuentaEgreso(cuenta);
			this.actualizarABMModel();
			this.actualizarView();
		}
	}
	
	void limpiarCuentaEgreso() {
		this.getAbmModelBajaArticulo().setCuentaEgreso(null);
		this.actualizarABMModel();
		this.actualizarView();
	}
	
	@Override
	public void actualizarABMModel() {
		String comentario = Conversor.getNullSiVacio(this.getView().getTaComentario().getText());
		this.getAbmModelBajaArticulo().setCuentaArticulo(this.getAbmModel().getObjetoABM());
		this.getAbmModelBajaArticulo().getObjetoABM().setFecha(Conversor.getDate(this.getView().getTfFecha().getText()));
		this.getAbmModelBajaArticulo().setComentario(comentario);
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
		
		this.getView().getTfArticulo().setText(Conversor.getVacioSiNull(this.getAbmModel().getArticulo()));
		this.getView().getTfCuenta().setText(Conversor.getVacioSiNull(this.getAbmModel().getCuenta()));
		
		this.getView().getTfCuentaEgreso().setText(Conversor.getVacioSiNull(this.getAbmModelBajaArticulo().getCuentaEgreso()));
		this.getView().getTfCuentaIngreso().setText(Conversor.getVacioSiNull(this.getAbmModelBajaArticulo().getCuentaIngreso()));
		this.getView().getTaComentario().setText(Conversor.getVacioSiNull(this.getAbmModelBajaArticulo().getComentario()));
		this.getView().getTfFecha().setValue(Conversor.getString(this.getAbmModelBajaArticulo().getFecha()));
	}
	
	@Override
	public boolean validarDatos() {
		boolean validacionOK = true;
		
		List<Object> attNulos = new ArrayList<Object>();
		List<JLabel> lblNuLos = new ArrayList<JLabel>();
		
		attNulos.add(this.getView().getTfCuentaEgreso().getText());
		lblNuLos.add(this.getView().getLblCuentaEgreso());
		attNulos.add(this.getView().getTfCuentaIngreso().getText());
		lblNuLos.add(this.getView().getLblCuentaIngreso());
		
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

class BtnSeleccionarCuentaIngresoListener implements ActionListener {

	private ABMAsociacionCuentaArticuloEliminar controller;
	
	public BtnSeleccionarCuentaIngresoListener(ABMAsociacionCuentaArticuloEliminar controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.seleccionarCuentaIngreso();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}

class BtnLimpiarCuentaIngresoListener implements ActionListener{

	private ABMAsociacionCuentaArticuloEliminar controller;
	
	public BtnLimpiarCuentaIngresoListener(ABMAsociacionCuentaArticuloEliminar controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.limpiarCuentaIngreso();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}

class BtnSeleccionarCuentaEgresoListener implements ActionListener {

	private ABMAsociacionCuentaArticuloEliminar controller;
	
	public BtnSeleccionarCuentaEgresoListener(ABMAsociacionCuentaArticuloEliminar controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.seleccionarCuentaEgreso();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}

class BtnLimpiarCuentaEgresoListener implements ActionListener{

	private ABMAsociacionCuentaArticuloEliminar controller;
	
	public BtnLimpiarCuentaEgresoListener(ABMAsociacionCuentaArticuloEliminar controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.limpiarCuentaEgreso();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}
