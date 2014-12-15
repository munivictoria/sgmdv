package com.trascender.caja.gui.cobro.abm.pagoTicketCompensacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.caja.gui.cobro.PagoTicketCompensacionABMModel;
import com.trascender.contabilidad.recurso.persistent.PagoTicketCompensacion;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.model.TAbstractABMModel;

public class AgregarPagoTicketCompensacion extends ABMPagoTicketCompensacion{
	
	private AgregarPagoTicketCompensacionView view;
	private PagoTicketCompensacionABMModel abmModel = new PagoTicketCompensacionABMModel();
	
	public AgregarPagoTicketCompensacion(JFrame owner) throws Exception {
		this.view = new AgregarPagoTicketCompensacionView(owner);
		this.abmModel.setObjetoABM(new PagoTicketCompensacion());
		this.init();
	}
	
	public AgregarPagoTicketCompensacion(JDialog owner) throws Exception {
		this.view = new AgregarPagoTicketCompensacionView(owner);
		this.abmModel.setObjetoABM(new PagoTicketCompensacion());
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		this.setListeners();
	}
	
	private void setListeners() {
		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnAgregarPagoTicketCompensacion(this));
	}
	
	public void agregarPagoTicketCompensacion(){
		if (this.validarDatos()) {
			this.actualizarABMModel();
			this.setOperacionRealizada(true);
			this.close();
		}
	}

	@Override
	public ABMPagoTicketCompensacionView getView() {
		return this.view;
	}

	@Override
	public PagoTicketCompensacionABMModel getABMModel() {
		return this.abmModel;
	}

	@Override
	protected TAbstractABMModel getAbmModel() {
		return this.abmModel;
	}
	
	class BtnAgregarPagoTicketCompensacion implements ActionListener{
		private AgregarPagoTicketCompensacion controller;
		public BtnAgregarPagoTicketCompensacion(AgregarPagoTicketCompensacion controller) {
			this.controller = controller;
		}
		public void actionPerformed(ActionEvent e) {
			try {
				this.controller.agregarPagoTicketCompensacion();
			} catch (Exception ex) {
				ex.printStackTrace();
				AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
			}
		}
	}
	
}
