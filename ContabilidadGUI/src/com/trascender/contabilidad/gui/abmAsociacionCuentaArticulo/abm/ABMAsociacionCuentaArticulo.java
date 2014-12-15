package com.trascender.contabilidad.gui.abmAsociacionCuentaArticulo.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JLabel;

import com.trascender.compras.recurso.persistent.inventario.Articulo;
import com.trascender.contabilidad.gui.abmArticulo.AdminArticulo;
import com.trascender.contabilidad.gui.abmAsociacionCuentaArticulo.AsociacionCuentaArticuloABMModel;
import com.trascender.contabilidad.gui.abmCuenta.AdminCuenta;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.contabilidad.recurso.persistent.CuentaArticulo;
import com.trascender.framework.recurso.transients.Periodo;
import com.trascender.framework.util.Periodicidad;
import com.trascender.gui.framework.abmStandard.ABMController;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Conversor;
import com.trascender.gui.framework.util.Validador;

public abstract class ABMAsociacionCuentaArticulo extends ABMController<CuentaArticulo>{
	
	public abstract ABMAsociacionCuentaArticuloView getView();
	public abstract AsociacionCuentaArticuloABMModel getAbmModel();
	
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
		
		this.getView().getPnlBtnSeleccionArticulo().getBtnSeleccionar().addActionListener(new BtnSeleccionarArticuloListener(this));
		this.getView().getPnlBtnSeleccionArticulo().getBtnLimpiar().addActionListener(new BtnLimpiarArticuloListener(this));
	}
	
	void seleccionarArticulo() throws Exception {
		AdminArticulo adminArticulo = new AdminArticulo(this.getView());		
		Articulo locArticulo = adminArticulo.openSelect();
		
		if (locArticulo != null) {
			Articulo articulo = ContabilidadGUI.getInstance().getAdminSystemsCompras().getSystemStock().getArticuloPorId(locArticulo.getIdArticulo());
			this.getAbmModel().setArticulo(articulo);
			this.actualizarABMModel();
			this.actualizarView();
		}
	}

	void limpiarArticulo() {
		this.getAbmModel().setArticulo(null);
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

	@Override
	public void actualizarABMModel() {
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
		
		this.getView().getTfArticulo().setText(Conversor.getVacioSiNull(this.getAbmModel().getArticulo()));
		this.getView().getTfCuenta().setText(Conversor.getVacioSiNull(this.getAbmModel().getCuenta()));
	}
	
	@Override
	public boolean validarDatos() {
		boolean validacionOK = true;
		
		List<Object> attNulos = new ArrayList<Object>();
		List<JLabel> lblNuLos = new ArrayList<JLabel>();
		
		attNulos.add(this.getView().getTfAnioPeriodo().getText());
		lblNuLos.add(this.getView().getLblAnioPeriodo());
		attNulos.add(this.getView().getTfArticulo().getText());
		lblNuLos.add(this.getView().getLblArticulo());
		attNulos.add(this.getView().getTfCuenta().getText());
		lblNuLos.add(this.getView().getLblCuenta());

		List<Object> attAnio = new ArrayList<Object>();
		List<JLabel> lblAnio = new ArrayList<JLabel>();
		List<Integer> longAnio = new ArrayList<Integer>();
		
		attAnio.add(this.getView().getTfAnioPeriodo().getText());
		lblAnio.add(this.getView().getLblAnioPeriodo());
		longAnio.add(4);
		
		List<String> listaErrores = new ArrayList<String>();
		try {
			listaErrores.addAll(Validador.validarNulos(attNulos, lblNuLos));
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
	
	class BtnSeleccionarCuentaListener implements ActionListener {

		private ABMAsociacionCuentaArticulo controller;
		
		public BtnSeleccionarCuentaListener(ABMAsociacionCuentaArticulo controller) {
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

		private ABMAsociacionCuentaArticulo controller;
		
		public BtnLimpiarCuentaListener(ABMAsociacionCuentaArticulo controller) {
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
	
	class BtnSeleccionarArticuloListener implements ActionListener {

		private ABMAsociacionCuentaArticulo controller;
		
		public BtnSeleccionarArticuloListener(ABMAsociacionCuentaArticulo controller) {
			this.controller = controller;
		}

		public void actionPerformed(ActionEvent e) {
			try {
				this.controller.seleccionarArticulo();
			} catch (Exception ex) {
				ex.printStackTrace();
				AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
			}
		}
		
	}

	class BtnLimpiarArticuloListener implements ActionListener{

		private ABMAsociacionCuentaArticulo controller;
		
		public BtnLimpiarArticuloListener(ABMAsociacionCuentaArticulo controller) {
			this.controller = controller;
		}

		public void actionPerformed(ActionEvent e) {
			try {
				this.controller.limpiarArticulo();
			} catch (Exception ex) {
				ex.printStackTrace();
				AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
			}
		}
		
	}

}
