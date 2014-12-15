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
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Conversor;

public class ModificarOrdenPagoDevolucion extends ABMOrdenPagoDevolucion {

	private ModificarOrdenPagoDevolucionView view;
	private LineaOrdenPagoDevolucionTableModel lineaOPDTableModel = new LineaOrdenPagoDevolucionTableModel();
	private OrdenPagoDevolucionABMModel abmModel = new OrdenPagoDevolucionABMModel();
	private ChequeOrdenPagoTableModel chequeTableModel = new ChequeOrdenPagoTableModel();
	private DebitoTableModel debitoTableModel = new DebitoTableModel();
	
	public ModificarOrdenPagoDevolucion(JDialog owner) throws Exception {
		this.view = new ModificarOrdenPagoDevolucionView(owner);
		this.init();
	}
	
	public ModificarOrdenPagoDevolucion(JFrame owner) throws Exception {
		this.view = new ModificarOrdenPagoDevolucionView(owner);
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		this.setListeners();
//		this.habilitarBotonesTablaOrdenCompra(true);
	}
	
	private void setListeners() {
		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnModificarOrdenPagoDevListener(this));
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
		return this.lineaOPDTableModel;
	}

	@Override
	public ModificarOrdenPagoDevolucionView getView() {
		return this.view;
	}
	
	void modificarOrdenPagoDevolucion() throws Exception {
		Date fPago = Conversor.getDate(this.getView().getTfFechaPago().getText());
		Date fEmi = Conversor.getDate(this.getView().getTfFechaEmision().getText());

		if (this.validarDatos()) {
			if (fPago.compareTo(fEmi) > 0 || fPago.compareTo(fEmi) == 0) {
				this.actualizarABMModel();
				this.getAbmModel().modificar();
				this.setOperacionRealizada(true);
				
				this.close();
			}
			else {
				AppManager.getInstance().showErrorMsg(this.getView(), "La Fecha de Pago no puede ser anterior a la Fecha de Emisión.");
			}
		}
	}

}

class BtnModificarOrdenPagoDevListener implements ActionListener {
	private ModificarOrdenPagoDevolucion controller;
	
	public BtnModificarOrdenPagoDevListener(ModificarOrdenPagoDevolucion controller) {
		this.controller = controller;
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			if (this.controller.getView().getPnlTablaLineaOrdenPagoDev().getTblDatos().isEditing()) {
				this.controller.getView().getPnlTablaLineaOrdenPagoDev().getTblDatos().getCellEditor().stopCellEditing();
			}
			this.controller.modificarOrdenPagoDevolucion();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(),ex.getMessage());
		}
	}
}


