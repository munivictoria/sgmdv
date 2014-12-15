package com.trascender.contabilidad.gui.abmOrdenPago.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.contabilidad.gui.abmLineaOrdenPago.LineaOrdenPagoTableModel;
import com.trascender.contabilidad.gui.abmOrdenPago.ChequeOrdenPagoTableModel;
import com.trascender.contabilidad.gui.abmOrdenPago.DebitoTableModel;
import com.trascender.contabilidad.gui.abmOrdenPago.OrdenPagoABMModel;
import com.trascender.gui.framework.main.AppManager;

public class EliminarOrdenPago extends ABMOrdenPago {
	
	private EliminarOrdenPagoView view;
	private OrdenPagoABMModel abmModel = new OrdenPagoABMModel();
	private LineaOrdenPagoTableModel facturaTableModel = new LineaOrdenPagoTableModel();
	private ChequeOrdenPagoTableModel chequeTableModel = new ChequeOrdenPagoTableModel();
	private DebitoTableModel debitoTableModel = new DebitoTableModel();
	
	public EliminarOrdenPago(JDialog owner) throws Exception{
		this.view = new EliminarOrdenPagoView(owner);
		this.init();
	}
	
	public EliminarOrdenPago(JFrame owner) throws Exception{
		this.view = new EliminarOrdenPagoView(owner);
		this.init();
	}
	
	protected void init() {
		super.init();
		this.setListeners();
		this.disabledAll();
		this.visibleAll();
		this.editableAll(); 
	}

	private void disabledAll() {
		
	}

	private void visibleAll() {
		this.getView().getPnlBtnSeleccionProveedor().setVisible(false);
		this.getView().getPnlBtnSeleccionRetencion().setVisible(false);
		this.getView().getPnlBotonesTablaFactura().setVisible(false);
		this.getView().getPnlBotonesTblCheque().setVisible(false);
		this.getView().getPnlBotonesTblDebito().setVisible(false);
	}
	
	private void editableAll() {
		this.getView().getTfFechaEmision().setEditable(false);
		this.getView().getTfFechaPago().setEditable(false);
		this.getView().getTfProveedor().setEditable(false);
		this.getView().getTfRetencion().setEditable(false);
	}
	
	private void setListeners() {
		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnEliminarListener(this));	
	}
	
	void eliminarOrdenPago() throws Exception {
		if (this.validarDatos()) {
			if (this.getAbmModel().getRetencion() != null) {
				if (AppManager.getInstance().showConfirmMsg(getView(), "Los datos del Comprobante de Retención, " +
					"asociado a la Orden de Pago que está eliminando, también serán eliminados.")) {
					this.actualizarABMModel();
					this.getAbmModel().eliminar();
					this.setOperacionRealizada(true);
					this.close();
				}
			} else {
				this.actualizarABMModel();
				this.getAbmModel().eliminar();
				this.setOperacionRealizada(true);
				this.close();
			}
			
			
		}
	}
	
	@Override
	public OrdenPagoABMModel getAbmModel() {
		return abmModel;
	}

	@Override
	public LineaOrdenPagoTableModel getFacturaTableModel() {
		return this.facturaTableModel;
	}
	

	@Override
	public DebitoTableModel getDebitoTableModel() {
		return this.debitoTableModel;
	}
	
	@Override
	public ABMOrdenPagoView getView() {
		return view;
	}

	@Override
	public ChequeOrdenPagoTableModel getChequeTableModel() {
		return this.chequeTableModel;
	}
}

class BtnEliminarListener implements ActionListener {
	private EliminarOrdenPago controller;
	public BtnEliminarListener(EliminarOrdenPago controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			if (this.controller.getView().getPnlTablaFactura().getTblDatos().isEditing()) {
				this.controller.getView().getPnlTablaFactura().getTblDatos().getCellEditor().stopCellEditing();
			}
			this.controller.eliminarOrdenPago();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(),ex.getMessage());
		}
	}
}
