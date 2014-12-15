package com.trascender.caja.gui.cobro.abm.pagoTicketCheque;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.caja.gui.cobro.PagoTicketChequeABMModel;
import com.trascender.caja.gui.cobro.PagoTicketChequeABMModel;
import com.trascender.contabilidad.recurso.persistent.PagoTicketCheque;
import com.trascender.contabilidad.recurso.persistent.PagoTicketCheque;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.model.TAbstractABMModel;

public class AgregarPagoTicketCheque extends ABMPagoTicketCheque{
	
	private AgregarPagoTicketChequeView view;
	private PagoTicketChequeABMModel abmModel = new PagoTicketChequeABMModel();
	
	public AgregarPagoTicketCheque(JFrame owner) throws Exception {
		this.view = new AgregarPagoTicketChequeView(owner);
		this.abmModel.setObjetoABM(new PagoTicketCheque());
		this.init();
	}
	
	public AgregarPagoTicketCheque(JDialog owner) throws Exception {
		this.view = new AgregarPagoTicketChequeView(owner);
		this.abmModel.setObjetoABM(new PagoTicketCheque());
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		this.setListeners();
	}
	
	private void setListeners() {
		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnAgregarPagoTicketCheque(this));
	}
	
	public void agregarPagoTicketCheque(){
		if (this.validarDatos()) {
			this.actualizarABMModel();
			this.setOperacionRealizada(true);
			this.close();
		}
	}

	@Override
	public ABMPagoTicketChequeView getView() {
		return this.view;
	}

	@Override
	public PagoTicketChequeABMModel getABMModel() {
		return this.abmModel;
	}

	@Override
	protected TAbstractABMModel getAbmModel() {
		return this.abmModel;
	}
	
	class BtnAgregarPagoTicketCheque implements ActionListener{
		private AgregarPagoTicketCheque controller;
		public BtnAgregarPagoTicketCheque(AgregarPagoTicketCheque controller) {
			this.controller = controller;
		}
		public void actionPerformed(ActionEvent e) {
			try {
				this.controller.agregarPagoTicketCheque();
			} catch (Exception ex) {
				ex.printStackTrace();
				AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
			}
		}
	}
	
}
