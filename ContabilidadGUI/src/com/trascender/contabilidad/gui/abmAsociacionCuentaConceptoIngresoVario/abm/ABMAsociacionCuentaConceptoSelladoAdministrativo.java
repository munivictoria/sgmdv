package com.trascender.contabilidad.gui.abmAsociacionCuentaConceptoIngresoVario.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JLabel;

import com.trascender.contabilidad.gui.abmAsociacionCuentaConceptoIngresoVario.AsociacionCuentaConceptoSelladoAdministrativoABMModel;
import com.trascender.contabilidad.gui.abmConceptoIngresoVario.AdminConceptoIngresoVario;
import com.trascender.contabilidad.gui.abmCuenta.AdminCuenta;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.ConceptoIngresoVario;
import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.contabilidad.recurso.persistent.CuentaConceptoIngresoVario;
import com.trascender.framework.recurso.transients.Periodo;
import com.trascender.framework.util.Periodicidad;
import com.trascender.gui.framework.abmStandard.ABMController;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Conversor;
import com.trascender.gui.framework.util.Validador;

public abstract class ABMAsociacionCuentaConceptoSelladoAdministrativo extends ABMController<CuentaConceptoIngresoVario> {

	public abstract AsociacionCuentaConceptoSelladoAdministrativoABMModel getAbmModel();
	public abstract ABMAsociacionCuentaConceptoSelladoAdministrativoView getView();
	
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
		
		this.getView().getPnlBotonesSeleccionConceptoSelladoAdministrativo().getBtnSeleccionar().addActionListener(new BtnSeleccionarConceptoSelladoAdministrativoListener(this));
		this.getView().getPnlBotonesSeleccionConceptoSelladoAdministrativo().getBtnLimpiar().addActionListener(new BtnLimpiarConceptoSelladoAdministrativoListener(this));
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
		
		this.getView().getTfCuenta().setText(Conversor.getVacioSiNull(this.getAbmModel().getCuenta()));
		this.getView().getTfConceptoSelladoAdministrativo().setText(Conversor.getVacioSiNull(this.getAbmModel().getConceptoSelladoAdministrativo()));
	}
	
	@Override
	public boolean validarDatos() {
		boolean validacionOK = true;
		
		List<Object> attNulos = new ArrayList<Object>();
		List<JLabel> lblNulos = new ArrayList<JLabel>();
		
		attNulos.add(this.getView().getTfAnioPeriodo().getText());
		lblNulos.add(this.getView().getLblAnioPeriodo());
		attNulos.add(this.getView().getTfConceptoSelladoAdministrativo().getText());
		lblNulos.add(this.getView().getLblConceptoSelladoAdministrativo());
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
	
	void seleccionarConceptoSelladoAdministrativo() throws Exception {
		AdminConceptoIngresoVario adminConceptoSelladoAdministrativo = new AdminConceptoIngresoVario(this.getView());
		ConceptoIngresoVario locConceptoSelladoAdministrativo = adminConceptoSelladoAdministrativo.openSelect();
		
		if (locConceptoSelladoAdministrativo != null) {
			this.getAbmModel().setConceptoSelladoAdministrativo(locConceptoSelladoAdministrativo);
			this.actualizarABMModel();
			this.actualizarView();
		}
	}
	
	void limpiarConceptoSelladoAdministrativo() throws Exception {
		this.getAbmModel().setConceptoSelladoAdministrativo(null);
		this.actualizarABMModel();
		this.actualizarView();
	}
}

class BtnSeleccionarCuentaListener implements ActionListener {
	ABMAsociacionCuentaConceptoSelladoAdministrativo controller;
	
	public BtnSeleccionarCuentaListener(
			ABMAsociacionCuentaConceptoSelladoAdministrativo controller) {
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

class BtnLimpiarCuentaListener implements ActionListener {
	ABMAsociacionCuentaConceptoSelladoAdministrativo controller;

	public BtnLimpiarCuentaListener(
			ABMAsociacionCuentaConceptoSelladoAdministrativo controller) {
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

class BtnSeleccionarConceptoSelladoAdministrativoListener implements ActionListener {
	ABMAsociacionCuentaConceptoSelladoAdministrativo controller;

	public BtnSeleccionarConceptoSelladoAdministrativoListener(
			ABMAsociacionCuentaConceptoSelladoAdministrativo controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.seleccionarConceptoSelladoAdministrativo();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}

class BtnLimpiarConceptoSelladoAdministrativoListener implements ActionListener {
	public BtnLimpiarConceptoSelladoAdministrativoListener(
			ABMAsociacionCuentaConceptoSelladoAdministrativo controller) {
		this.controller = controller;
	}

	ABMAsociacionCuentaConceptoSelladoAdministrativo controller;

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.limpiarConceptoSelladoAdministrativo();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}