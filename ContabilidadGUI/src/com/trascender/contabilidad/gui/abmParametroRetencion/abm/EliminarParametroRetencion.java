package com.trascender.contabilidad.gui.abmParametroRetencion.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.trascender.contabilidad.gui.abmParametroRetencion.ParametroRetencionABMModel;
import com.trascender.gui.framework.main.AppManager;

public class EliminarParametroRetencion extends ABMParametroRetencion {

	private EliminarParametroRetencionView view;
	private ParametroRetencionABMModel abmModel = new ParametroRetencionABMModel();
	
	public EliminarParametroRetencion(JDialog owner) {
		this.view = new EliminarParametroRetencionView(owner);
		this.init();
	}
	@Override
	protected void init() {
		super.init();
		this.setListeners();
		this.disabledAll();
	}
	
	private void disabledAll() {
		this.getView().getTfNombre().setEditable(false);
		this.getView().getTfAlicuota().setEditable(false);
		this.getView().getTfImporteMinimo().setEditable(false);
		this.getView().getTfPorcentaje().setEditable(false);
		this.getView().getChDeducirIVA().setEnabled(false);
	}

	private void setListeners() {
		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnEliminarListener(this));
	}
	
	@Override
	public ParametroRetencionABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public EliminarParametroRetencionView getView() {
		return this.view;
	}
	
	void eliminarParametroRertencion() throws Exception {
		this.getAbmModel().eliminar();
		this.setOperacionRealizada(true);
		this.close();
	}

}

class BtnEliminarListener implements ActionListener {

	private EliminarParametroRetencion controller;
	
	public BtnEliminarListener(EliminarParametroRetencion controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.eliminarParametroRertencion();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}

