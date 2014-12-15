package com.trascender.caja.gui.cobro.abm.pagoTicketDeposito;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.caja.gui.cobro.PagoTicketDepositoABMModel;
import com.trascender.contabilidad.recurso.persistent.PagoTicketDeposito;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.model.TAbstractABMModel;

public class AgregarPagoTicketDeposito extends ABMPagoTicketDeposito{
	
	private AgregarPagoTicketDepositoView view;
	private PagoTicketDepositoABMModel abmModel = new PagoTicketDepositoABMModel();
	
	public AgregarPagoTicketDeposito(JFrame owner) throws Exception {
		this.view = new AgregarPagoTicketDepositoView(owner);
		this.abmModel.setObjetoABM(new PagoTicketDeposito());
		this.init();
	}
	
	public AgregarPagoTicketDeposito(JDialog owner) throws Exception {
		this.view = new AgregarPagoTicketDepositoView(owner);
		this.abmModel.setObjetoABM(new PagoTicketDeposito());
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		this.setListeners();
	}
	
	private void setListeners() {
		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnAgregarPagoTicketDeposito(this));
	}
	
	public void agregarPagoTicketDeposito(){
		if (this.validarDatos()) {
			this.actualizarABMModel();
			this.setOperacionRealizada(true);
			this.close();
		}
	}

	@Override
	public ABMPagoTicketDepositoView getView() {
		return this.view;
	}

	@Override
	public PagoTicketDepositoABMModel getABMModel() {
		return this.abmModel;
	}

	@Override
	protected TAbstractABMModel getAbmModel() {
		return this.abmModel;
	}
	
	class BtnAgregarPagoTicketDeposito implements ActionListener{
		private AgregarPagoTicketDeposito controller;
		public BtnAgregarPagoTicketDeposito(AgregarPagoTicketDeposito controller) {
			this.controller = controller;
		}
		public void actionPerformed(ActionEvent e) {
			try {
				this.controller.agregarPagoTicketDeposito();
			} catch (Exception ex) {
				ex.printStackTrace();
				AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
			}
		}
	}
	
}
