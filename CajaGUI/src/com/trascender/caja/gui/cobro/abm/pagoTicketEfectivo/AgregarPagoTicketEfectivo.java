package com.trascender.caja.gui.cobro.abm.pagoTicketEfectivo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.caja.gui.cobro.PagoTicketEfectivoABMModel;
import com.trascender.contabilidad.recurso.persistent.PagoTicketEfectivo;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.model.TAbstractABMModel;

public class AgregarPagoTicketEfectivo extends ABMPagoTicketEfectivo{
	
	private AgregarPagoTicketEfectivoView view;
	private PagoTicketEfectivoABMModel abmModel = new PagoTicketEfectivoABMModel();
	
	public AgregarPagoTicketEfectivo(JFrame owner) throws Exception {
		this.view = new AgregarPagoTicketEfectivoView(owner);
		this.abmModel.setObjetoABM(new PagoTicketEfectivo());
		this.init();
	}
	
	public AgregarPagoTicketEfectivo(JDialog owner) throws Exception {
		this.view = new AgregarPagoTicketEfectivoView(owner);
		this.abmModel.setObjetoABM(new PagoTicketEfectivo());
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		this.setListeners();
	}
	
	private void setListeners() {
		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnAgregarPagoTicketEfectivo(this));
	}
	
	public void agregarPagoTicketEfectivo(){
		if (this.validarDatos()) {
			this.actualizarABMModel();
			this.setOperacionRealizada(true);
			this.close();
		}
	}

	@Override
	public ABMPagoTicketEfectivoView getView() {
		return this.view;
	}

	@Override
	public PagoTicketEfectivoABMModel getABMModel() {
		return this.abmModel;
	}

	@Override
	protected TAbstractABMModel getAbmModel() {
		return this.abmModel;
	}
	
	class BtnAgregarPagoTicketEfectivo implements ActionListener{
		private AgregarPagoTicketEfectivo controller;
		public BtnAgregarPagoTicketEfectivo(AgregarPagoTicketEfectivo controller) {
			this.controller = controller;
		}
		public void actionPerformed(ActionEvent e) {
			try {
				this.controller.agregarPagoTicketEfectivo();
			} catch (Exception ex) {
				ex.printStackTrace();
				AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
			}
		}
	}
	
}
