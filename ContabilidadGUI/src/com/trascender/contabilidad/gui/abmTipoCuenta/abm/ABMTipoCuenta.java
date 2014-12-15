package com.trascender.contabilidad.gui.abmTipoCuenta.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

import com.trascender.contabilidad.gui.abmTipoCuenta.AdminTipoCuenta;
import com.trascender.contabilidad.gui.abmTipoCuenta.TipoCuentaABMModel;
import com.trascender.contabilidad.gui.abmTipoCuenta.TipoCuentaExcluidosTableModel;
import com.trascender.contabilidad.recurso.persistent.TipoCuenta;
import com.trascender.contabilidad.recurso.persistent.TipoCuenta.Abreviatura;
import com.trascender.contabilidad.recurso.persistent.TipoCuenta.Opera;
import com.trascender.framework.util.Util;
import com.trascender.gui.framework.abmStandard.ABMController;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.model.TDefaultComboBoxModel;
import com.trascender.gui.framework.util.Conversor;
import com.trascender.gui.framework.util.Validador;

public abstract class ABMTipoCuenta extends ABMController<TipoCuenta> {
	
	public abstract ABMTipoCuentaView getView();
	public abstract TipoCuentaABMModel getAbmModel();
	public abstract TipoCuentaExcluidosTableModel getTableModelTipoCuenta();
	
	@Override
	protected void init() {
		super.init();
		this.setModels();
		this.setListeners();
		
		this.getView().getPnlBtnTablaTipoCuenta().getBtnModificar().setVisible(false);
		this.getView().getPnlBtnTablaTipoCuenta().getBtnQuitarTodos().setVisible(false);
	}
	
	private void setModels() {
		this.getView().setAbmModel(this.getAbmModel());
		this.getView().getCbAbreviatura().setModel(new TDefaultComboBoxModel(Abreviatura.values()));
		this.getView().getCbOperaAsientos().setModel(new TDefaultComboBoxModel(Opera.values()));
		this.getView().getCbOperaMovimientosCaja().setModel(new TDefaultComboBoxModel(Opera.values()));
		this.getView().setTableModelTipoCuentaExcluidos(this.getTableModelTipoCuenta());
	}

	private void setListeners() {
		this.getView().getPnlBtnTablaTipoCuenta().getBtnAgregar().addActionListener(new BtnAgregarTipoCuentaListener(this));
		this.getView().getPnlBtnTablaTipoCuenta().getBtnEliminar().addActionListener(new BtnQuitarTipoCuentaListener(this));
	}
	
	@Override
	public void actualizarABMModel() {
		TipoCuentaABMModel locModel = this.getView().getAbmModel();
		
		locModel.setNombre(Conversor.getNullSiVacio(this.getView().getTfNombre().getText()));
		
		Abreviatura locAbreviatura = null;
		Object locAbreviaturaSelected = this.getView().getCbAbreviatura().getSelectedItem();
		if (locAbreviaturaSelected != null) locAbreviatura = Abreviatura.valueOf(Util.getEnumNameFromString(locAbreviaturaSelected.toString()));
		locModel.setAbreviatura(locAbreviatura);
		
		Opera locOperaAsientos = null;
		Object locOperaAsientosSelected = this.getView().getCbOperaAsientos().getSelectedItem();
		if (locOperaAsientosSelected != null) locOperaAsientos = Opera.valueOf(Util.getEnumNameFromString(locOperaAsientosSelected.toString()));
		locModel.setOperaAsientos(locOperaAsientos);
		
		Opera locOperaMovimientosCaja = null;
		Object locOperaMovimientosCajaSelected = this.getView().getCbOperaMovimientosCaja().getSelectedItem();
		if (locOperaMovimientosCajaSelected != null) locOperaMovimientosCaja = Opera.valueOf(Util.getEnumNameFromString(locOperaMovimientosCajaSelected.toString()));
		locModel.setOperaMovimientosCaja(locOperaMovimientosCaja);
		
		locModel.setListaTipoCuentaExcluidos(this.getView().getTableModelTipoCuentaExcluidos().getListaObjetos());
		
		locModel.fireActualizarDatos();
	}

	@Override
	public void actualizarView() {
		this.getView().getTfNombre().setText(this.getAbmModel().getNombre());
		this.getView().getCbAbreviatura().setSelectedItem(this.getAbmModel().getAbreviatura());
		this.getView().getCbOperaAsientos().setSelectedItem(this.getAbmModel().getOperaAsientos());
		this.getView().getCbOperaMovimientosCaja().setSelectedItem(this.getAbmModel().getOperaMovimientosCaja());
		
		if (this.getAbmModel().getListaTipoCuentaExcluidos() != null) {
			this.getTableModelTipoCuenta().setListaObjetos(this.getAbmModel().getListaTipoCuentaExcluidos());
			
//			this.getTableModelTipoCuenta().clearTable();
//			this.getTableModelTipoCuenta().addRows(this.getAbmModel().getListaTipoCuentaExcluidos());
		}
	}
	
	@Override
	public boolean validarDatos() {
		boolean validacionOK = true;
		
		List<Object> attNulos = new ArrayList<Object>();
		List<JLabel> lblNulos = new ArrayList<JLabel>();
		
		attNulos.add(this.getView().getTfNombre().getText());
		lblNulos.add(this.getView().getLblNombre());
		
		attNulos.add(Conversor.getVacioSiNull(this.getView().getCbAbreviatura().getSelectedItem()));
		lblNulos.add(this.getView().getLblAbreviatura());
		
		attNulos.add(Conversor.getVacioSiNull(this.getView().getCbOperaAsientos().getSelectedItem()));
		lblNulos.add(this.getView().getLblOperaAsientos());
		
		attNulos.add(Conversor.getVacioSiNull(this.getView().getCbOperaMovimientosCaja().getSelectedItem()));
		lblNulos.add(this.getView().getLblOperaMovimientosCaja());
		
		List<String> listaErrores = new ArrayList<String>();
		try {
			listaErrores.addAll(Validador.validarNulos(attNulos, lblNulos));
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
	
	void agregarTablaTipoCuenta() throws Exception {
		AdminTipoCuenta adminTipoCuenta = new AdminTipoCuenta(this.getView());
		TipoCuenta locTipoCuenta = adminTipoCuenta.openSelect();
		if (locTipoCuenta != null) {
			this.getTableModelTipoCuenta().addRow(locTipoCuenta);
			actualizarABMModel();
			this.actualizarView();
		}
	}
	
	void quitarTablaTipoCuenta() throws Exception {
		int fila = this.getView().getPnlTablaTipoCuenta().getTblDatos().getSelectedRow();
		this.getTableModelTipoCuenta().deleteRow(fila);
	}
}

class BtnAgregarTipoCuentaListener implements ActionListener {

	private ABMTipoCuenta controller;

	public BtnAgregarTipoCuentaListener(ABMTipoCuenta controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.agregarTablaTipoCuenta();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnQuitarTipoCuentaListener implements ActionListener {

	private ABMTipoCuenta controller;
	
	public BtnQuitarTipoCuentaListener(ABMTipoCuenta controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.quitarTablaTipoCuenta();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}