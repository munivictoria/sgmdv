package com.trascender.contabilidad.gui.abmRetencion.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.trascender.contabilidad.gui.abmOrdenPago.OrdenPagoBusquedaModel;
import com.trascender.contabilidad.gui.abmOrdenPago.OrdenPagoTableModel;
import com.trascender.contabilidad.gui.abmRetencion.LineaRetencionTableModel;
import com.trascender.contabilidad.gui.abmRetencion.RetencionABMModel;
import com.trascender.contabilidad.recurso.persistent.ComprobanteRetencion;
import com.trascender.gui.framework.main.AppManager;

public class EliminarRetencion extends ABMRetencion {

	private EliminarRetencionView view;
	private RetencionABMModel abmModel = new RetencionABMModel();
	private OrdenPagoTableModel ordenPagoTableModel = new OrdenPagoTableModel();
	private OrdenPagoBusquedaModel ordenPagoBusquedaModel= new OrdenPagoBusquedaModel();
	private LineaRetencionTableModel lineaRetencionTableModel = new LineaRetencionTableModel();
	
	public EliminarRetencion(JDialog owner, ComprobanteRetencion retencion) throws Exception {
		this.view = new EliminarRetencionView(owner);
		this.abmModel.setObjetoABM(retencion);
		
		//this.actualizarABMModel();
		this.actualizarView();
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		this.setListeners();
		this.disabledAll();
		this.editableAll(); 
	}
	
	private void disabledAll() {
		this.getView().getPnlBotonesSeleccionProveedor().setVisible(false);
		this.getView().getPnlBtnTablaParametroRetencion().setVisible(false);
		this.getView().getPnlPie().getBtnImprimir().setVisible(false);
		this.getView().getBtnBuscarOrdenesDePagos().setVisible(false);
	}
	
	private void editableAll() {
		this.getView().getTfAnio().setEditable(false);
		this.getView().getTfMes().setEditable(false);
		this.getView().getTfProveedor().setEditable(false);
	}
	
	private void setListeners() {
		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnEliminarListener(this));
//		this.getView().getPnlPie().getBtnImprimir().addActionListener(new BtnImprimirListener(this));
	}
	
	@Override
	public RetencionABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public LineaRetencionTableModel getLineaRetencionTableModel() {
		return this.lineaRetencionTableModel;
	}

	@Override
	public OrdenPagoBusquedaModel getOrdenPagoBusquedaModel() {
		return this.ordenPagoBusquedaModel;
	}

	@Override
	public OrdenPagoTableModel getOrdenPagoTableModel() {
		return this.ordenPagoTableModel;
	}

	@Override
	public ABMRetencionView getView() {
		return this.view;
	}

	void eliminarComprobanteRetencion() throws Exception {
		this.getAbmModel().eliminar();
		this.setOperacionRealizada(true);
		this.close();
	}
	
}

class BtnEliminarListener implements ActionListener {

	private EliminarRetencion controller;
	
	public BtnEliminarListener(EliminarRetencion controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.eliminarComprobanteRetencion();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}

