package com.trascender.contabilidad.gui.abmOrdenPagoDevolucion.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.contabilidad.gui.abmOrdenPago.ChequeOrdenPagoTableModel;
import com.trascender.contabilidad.gui.abmOrdenPago.DebitoTableModel;
import com.trascender.contabilidad.gui.abmOrdenPagoDevolucion.LineaOrdenPagoDevolucionTableModel;
import com.trascender.contabilidad.gui.abmOrdenPagoDevolucion.OrdenPagoDevolucionABMModel;
import com.trascender.contabilidad.recurso.persistent.OrdenPagoDevolucion;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Conversor;

public class AgregarOrdenPagoDevolucion extends ABMOrdenPagoDevolucion {

	private AgregarOrdenPagoDevolucionView view;
	private LineaOrdenPagoDevolucionTableModel lineaOPDTableModel = new LineaOrdenPagoDevolucionTableModel();
	private OrdenPagoDevolucionABMModel abmModel = new OrdenPagoDevolucionABMModel();
	private ChequeOrdenPagoTableModel chequeTableModel = new ChequeOrdenPagoTableModel();
	private DebitoTableModel debitoTableModel = new DebitoTableModel();
	
	public AgregarOrdenPagoDevolucion(JFrame owner) throws Exception {
		this.view = new AgregarOrdenPagoDevolucionView(owner);
		this.abmModel.setObjetoABM(new OrdenPagoDevolucion());
		this.init();
	}
	
	public AgregarOrdenPagoDevolucion(JDialog owner) throws Exception {
		this.view = new AgregarOrdenPagoDevolucionView(owner);
		this.abmModel.setObjetoABM(new OrdenPagoDevolucion());
		this.init();
	}
	
	
	@Override
	protected void init() {
		super.init();
		this.setListeners();
	}
	
	private void setListeners() {
		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnAgregarOrdenPagoListener(this));
	}
	
	@Override
	public OrdenPagoDevolucionABMModel getAbmModel() {
		return this.abmModel;
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
	public LineaOrdenPagoDevolucionTableModel getLineaOrdenPagoDevTableModel() {
		return lineaOPDTableModel;
	}

	@Override
	public AgregarOrdenPagoDevolucionView getView() {
		return this.view;
	}
	
	void agregarOrdenPagoDevolucion() throws Exception {
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

	

}

class BtnAgregarOrdenPagoListener implements ActionListener {

	private AgregarOrdenPagoDevolucion controller;
	
	public BtnAgregarOrdenPagoListener(AgregarOrdenPagoDevolucion controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.agregarOrdenPagoDevolucion();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
	
}

