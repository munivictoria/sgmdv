package com.trascender.contabilidad.gui.abmOrdenPagoDevolucion.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.contabilidad.gui.abmOrdenPago.ChequeOrdenPagoTableModel;
import com.trascender.contabilidad.gui.abmOrdenPago.DebitoTableModel;
import com.trascender.contabilidad.gui.abmOrdenPagoDevolucion.LineaOrdenPagoDevolucionTableModel;
import com.trascender.contabilidad.gui.abmOrdenPagoDevolucion.OrdenPagoDevolucionABMModel;
import com.trascender.gui.framework.main.AppManager;

public class EliminarOrdenPagoDevolucion extends ABMOrdenPagoDevolucion {

	private EliminarOrdenPagoDevolucionView view;
	private LineaOrdenPagoDevolucionTableModel lineaOPDTableModel = new LineaOrdenPagoDevolucionTableModel();
	private OrdenPagoDevolucionABMModel abmModel = new OrdenPagoDevolucionABMModel();
	private ChequeOrdenPagoTableModel chequeTableModel = new ChequeOrdenPagoTableModel();
	private DebitoTableModel debitoTableModel = new DebitoTableModel();

	public EliminarOrdenPagoDevolucion(JDialog owner) throws Exception {
		this.view = new EliminarOrdenPagoDevolucionView(owner);
		this.init();
	}
	
	public EliminarOrdenPagoDevolucion(JFrame owner) throws Exception {
		this.view = new EliminarOrdenPagoDevolucionView(owner);
		this.init();
	}
	
	protected void init() {
		super.init();
		this.setListeners();
		this.disabledAll();
		this.visibleAll(false);
		this.editableAll(false); 
	}

	private void disabledAll() {
		
	}
	
	private void visibleAll(Boolean bandera) {
		this.getView().getPnlBtnSeleccionPersona().setVisible(bandera);
		this.getView().getPnlBotonesTblLineaOrdenPagoDev().setVisible(bandera);
		this.getView().getPnlBotonesTblCheque().setVisible(bandera);
		this.getView().getPnlBotonesTblDebito().setVisible(bandera);
	}
	
	private void editableAll(Boolean bandera) {
		this.getView().getTfFechaEmision().setEditable(bandera);
		this.getView().getTfFechaPago().setEditable(bandera);
		this.getView().getTfPersona().setEditable(bandera);
	}
	
	private void setListeners() {
		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnEliminarListener(this));	
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
	public ABMOrdenPagoDevolucionView getView() {
		return this.view;
	}
	
	void eliminarOrdenPagoDevolucion() throws Exception {
		if (this.validarDatos()) {
			this.actualizarABMModel();
			this.getAbmModel().eliminar();
			this.setOperacionRealizada(true);
			this.close();
		}
	}

}

class BtnEliminarListener implements ActionListener {
	private EliminarOrdenPagoDevolucion controller;
	
	public BtnEliminarListener(EliminarOrdenPagoDevolucion controller) {
		this.controller = controller;
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			if (this.controller.getView().getPnlTablaLineaOrdenPagoDev().getTblDatos().isEditing()) {
				this.controller.getView().getPnlTablaLineaOrdenPagoDev().getTblDatos().getCellEditor().stopCellEditing();
			}
			this.controller.eliminarOrdenPagoDevolucion();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(),ex.getMessage());
		}
	}
}
