package com.trascender.contabilidad.gui.abmOrdenPago.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.contabilidad.gui.abmLineaOrdenPago.LineaOrdenPagoTableModel;
import com.trascender.contabilidad.gui.abmOrdenPago.ChequeOrdenPagoTableModel;
import com.trascender.contabilidad.gui.abmOrdenPago.DebitoTableModel;
import com.trascender.contabilidad.gui.abmOrdenPago.OrdenPagoABMModel;
import com.trascender.contabilidad.recurso.persistent.OrdenPago;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Conversor;

public class AgregarOrdenPago extends ABMOrdenPago {

	private AgregarOrdenPagoView view;
	private OrdenPagoABMModel abmModel = new OrdenPagoABMModel();
	private LineaOrdenPagoTableModel facturaTableModel = new LineaOrdenPagoTableModel();
	private ChequeOrdenPagoTableModel chequeTableModel = new ChequeOrdenPagoTableModel();
	private DebitoTableModel debitoTableModel = new DebitoTableModel();
	
	public AgregarOrdenPago(JFrame owner) throws Exception {
		this.view = new AgregarOrdenPagoView(owner);
		this.abmModel.setObjetoABM(new OrdenPago());
		this.init();
	}
	
	public AgregarOrdenPago(JDialog owner) throws Exception {
		this.view = new AgregarOrdenPagoView(owner);
		this.abmModel.setObjetoABM(new OrdenPago());
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		
		this.habilitarBotonesTablaOrdenCompra(false);
	//	this.getView().getTfFechaEmision().setText(Conversor.getString(Calendar.getInstance().getTime()));
		this.getView().getLblRetencion().setVisible(false);
		this.getView().getTfRetencion().setVisible(false);
		this.getView().getPnlBtnSeleccionRetencion().setVisible(false);
		
		this.setListeners();
	}
	
	private void setListeners() {
		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnAgregarOrdenPagoListener(this));
	}
	
	@Override
	public OrdenPagoABMModel getAbmModel() {
		return this.abmModel;
	}


	@Override
	public AgregarOrdenPagoView getView() {
		return this.view;
	}
	
	void agregarOrdenPago() throws Exception {
		Date fPago = Conversor.getDate(this.getView().getTfFechaPago().getText());
		Date fEmi = Conversor.getDate(this.getView().getTfFechaEmision().getText());

		if (this.validarDatos()) {
			if (fPago.compareTo(fEmi) > 0 || fPago.compareTo(fEmi) == 0) {
				this.actualizarABMModel();
				this.getAbmModel().agregar();
				this.setOperacionRealizada(true);
				this.close();
			}
			else {
				AppManager.getInstance().showErrorMsg(this.getView(), "La Fecha de Pago no puede ser anterior a la Fecha de Emisión.");
			}
		}
		
	}

	@Override
	public ChequeOrdenPagoTableModel getChequeTableModel() {
		return this.chequeTableModel;
	}
	
	@Override
	public DebitoTableModel getDebitoTableModel() {
		return this.debitoTableModel;
	}

	@Override
	public LineaOrdenPagoTableModel getFacturaTableModel() {
		return this.facturaTableModel;
	}
	
}

class BtnAgregarOrdenPagoListener implements ActionListener {

	private AgregarOrdenPago controller;
	
	public BtnAgregarOrdenPagoListener(AgregarOrdenPago controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.agregarOrdenPago();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
	
}