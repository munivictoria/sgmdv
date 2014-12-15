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
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Conversor;

public class ModificarOrdenPago extends ABMOrdenPago{
	
	private ModificarOrdenPagoView view;
	private OrdenPagoABMModel abmModel = new OrdenPagoABMModel();
	private LineaOrdenPagoTableModel facturaTableModel = new LineaOrdenPagoTableModel();
	private ChequeOrdenPagoTableModel chequeTableModel = new ChequeOrdenPagoTableModel();
	private DebitoTableModel debitoTableModel = new DebitoTableModel();
	
	public ModificarOrdenPago(JDialog owner) throws Exception{
		this.view = new ModificarOrdenPagoView(owner);
		this.init();
	}
	
	public ModificarOrdenPago(JFrame owner) throws Exception{
		this.view = new ModificarOrdenPagoView(owner);
		this.init();
	}
	
	protected void init() {
		super.init();
		this.setListeners();
		this.disabledAll();
		this.visibleAll();
		this.editableAll(); 
		this.habilitarBotonesTablaOrdenCompra(true);
	}

	private void disabledAll() {
		
	}

	private void visibleAll() {
		
	}
	
	private void editableAll() {
	
	}
	
	private void setListeners() {
		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnModificarOrdenPagoListener(this));
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
	public ABMOrdenPagoView getView() {
		return view;
	}
	
	@Override
	public DebitoTableModel getDebitoTableModel() {
		return this.debitoTableModel;
	}
	
	@Override
	public ChequeOrdenPagoTableModel getChequeTableModel() {
		return this.chequeTableModel;
	}

	
	void modificarOrdenPago() throws Exception {
		Date fPago = Conversor.getDate(this.getView().getTfFechaPago().getText());
		Date fEmi = Conversor.getDate(this.getView().getTfFechaEmision().getText());

		if (this.validarDatos()) {
			if (this.getAbmModel().getRetencion() != null) {
				if (AppManager.getInstance().showConfirmMsg(this.getView(), "El Comprobante de Retención, asociado a la Orden de Pago que está modificando, será eliminado.\n " +
				"Deberá generar nuevamente el Comprobante de retención para dicha Orden de Pago.")) {
					if (fPago.compareTo(fEmi) > 0 || fPago.compareTo(fEmi) == 0) {
						this.actualizarABMModel();
						this.getAbmModel().setRetencion(null);
						this.getAbmModel().modificar();
						this.setOperacionRealizada(true);
			
						this.close();
					}
					else {
						AppManager.getInstance().showErrorMsg(this.getView(), "La Fecha de Pago no puede ser anterior a la Fecha de Emisión.");
					}
				}
			} else {
				
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
}

class BtnModificarOrdenPagoListener implements ActionListener {
	private ModificarOrdenPago controller;
	public BtnModificarOrdenPagoListener(ModificarOrdenPago controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			if (this.controller.getView().getPnlTablaFactura().getTblDatos().isEditing()) {
				this.controller.getView().getPnlTablaFactura().getTblDatos().getCellEditor().stopCellEditing();
			}
			this.controller.modificarOrdenPago();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(),ex.getMessage());
		}
	}
}
