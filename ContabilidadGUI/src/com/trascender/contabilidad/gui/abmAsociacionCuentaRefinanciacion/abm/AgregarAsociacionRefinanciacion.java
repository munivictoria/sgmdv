package com.trascender.contabilidad.gui.abmAsociacionCuentaRefinanciacion.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.trascender.contabilidad.gui.abmAsociacionCuentaRefinanciacion.AsociacionRefinanciacionABMModel;
import com.trascender.contabilidad.gui.abmAsociacionCuentaRefinanciacion.AsociacionRefinanciacionBusquedaModel;
import com.trascender.contabilidad.gui.abmAsociacionCuentaRefinanciacion.ParametroAsociacionTableModel;
import com.trascender.contabilidad.gui.abmAsociacionCuentaRefinanciacion.RefinanciacionTableModel;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.contabilidad.recurso.persistent.AsociacionRefinanciacion;
import com.trascender.gui.framework.main.AppManager;

public class AgregarAsociacionRefinanciacion extends ABMAsociacionRefinanciacion {

	private AgregarAsociacionRefinanciacionView view;
	private AsociacionRefinanciacionABMModel abmModel = new AsociacionRefinanciacionABMModel();
	private RefinanciacionTableModel refinanciacionTableModel = new RefinanciacionTableModel();
	private ParametroAsociacionTableModel parametroAsociacionTableModel = new ParametroAsociacionTableModel();
	
	public AgregarAsociacionRefinanciacion(JDialog owner) throws Exception {
		this.view = new AgregarAsociacionRefinanciacionView(owner);
		this.getAbmModel().setObjetoABM(new AsociacionRefinanciacion());
		this.init();
	}

	@Override
	protected void init() {
		super.init();
		this.setModels();
		this.getView().getPnlBtnTablaParametroAsociacion().getBtnModificar().setText(MessagesContabilidad.getString("Application.btnQuitar"));
		this.getView().getPnlBtnTablaParametroAsociacion().getBtnModificar().setMnemonic(MessagesContabilidad.getChar("Application.btnQuitarMnemonic"));
		this.getView().getPnlBtnTablaParametroAsociacion().getBtnModificar().setToolTipText(MessagesContabilidad.getString("Application.btnQuitarToolTip"));
		
		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnAgregarListener(this));
		this.disabledAll();
	}
	
	private void disabledAll() {
		this.getView().getPnlTablas().remove(this.getView().getPnlContenedorRefinanciaciones());
	}
	
	private void setModels() {
		this.getView().setParametroAsociacionTableModel(this.parametroAsociacionTableModel);
	}
	
	@Override
	public ABMAsociacionRefinanciacionView getView() {
		return this.view;
	}

	void agregarAsociacionRefinanciacion() throws Exception {
		if (this.validarDatos()) {
			if (this.getAbmModel().getListaParametrosAsociacion().isEmpty()) {
				ContabilidadGUI.getInstance().showInformationMsg(this.getView(), "No se puede guardar la asociación si parámetros.");
			}else {
				this.actualizarABMModel();
				this.getAbmModel().agregar();
				this.setOperacionRealizada(true);
				this.close();
			}
		}
		
	}

	@Override
	public ParametroAsociacionTableModel getParametroAsociacionTableModel() {
		return this.parametroAsociacionTableModel;
	}

	@Override
	public AsociacionRefinanciacionBusquedaModel getRefinanciacionBusquedaModel() {
		return null;
	}

	@Override
	public RefinanciacionTableModel getRefinanciacionTableModel() {
		return refinanciacionTableModel;
	}

	@Override
	public AsociacionRefinanciacionABMModel getAbmModel() {
		return this.abmModel;
	}
}

class BtnAgregarListener implements ActionListener {
	private AgregarAsociacionRefinanciacion controller;
	public BtnAgregarListener(AgregarAsociacionRefinanciacion controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.agregarAsociacionRefinanciacion();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(),ex.getMessage());
		}
	}
}