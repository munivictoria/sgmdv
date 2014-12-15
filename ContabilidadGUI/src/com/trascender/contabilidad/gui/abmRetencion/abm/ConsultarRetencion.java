package com.trascender.contabilidad.gui.abmRetencion.abm;

import javax.swing.JDialog;

import com.trascender.contabilidad.gui.abmOrdenPago.OrdenPagoBusquedaModel;
import com.trascender.contabilidad.gui.abmOrdenPago.OrdenPagoTableModel;
import com.trascender.contabilidad.gui.abmParametroRetencion.ParametroRetencionTableModel;
import com.trascender.contabilidad.gui.abmRetencion.LineaRetencionTableModel;
import com.trascender.contabilidad.gui.abmRetencion.RetencionABMModel;
import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.contabilidad.recurso.persistent.ComprobanteRetencion;

public class ConsultarRetencion extends ABMRetencion {

	private ConsultarRetencionView view;
	private RetencionABMModel abmModel = new RetencionABMModel();
	private OrdenPagoTableModel ordenPagoTableModel = new OrdenPagoTableModel();
	private ParametroRetencionTableModel parametroRetencionTableModel = new ParametroRetencionTableModel();
	private OrdenPagoBusquedaModel ordenPagoBusquedaModel= new OrdenPagoBusquedaModel();
	private LineaRetencionTableModel lineaRetencionTableModel = new LineaRetencionTableModel();
	
	public ConsultarRetencion(JDialog owner, ComprobanteRetencion retencion) throws Exception {
		this.view = new ConsultarRetencionView(owner);
		this.abmModel.setObjetoABM(retencion);
		
//		this.actualizarView();
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		this.setTextoBtnCerrar();
		this.setListeners();
		this.disabledAll();
		this.editableAll(); 
	}
	
	private void disabledAll() {
		this.getView().getPnlPie().getBtnAceptar().setVisible(false);
		this.getView().getPnlBotonesSeleccionProveedor().setVisible(false);
		this.getView().getPnlBtnTablaParametroRetencion().setVisible(false);
		this.getView().getPnlPie().getBtnImprimir().setVisible(false);
	}
	
	private void editableAll() {
		this.getView().getTfAnio().setEditable(false);
		this.getView().getTfMes().setEditable(false);
		this.getView().getTfProveedor().setEditable(false);
	}
	
	private void setListeners() {
//		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnAceptarListeners(this));
//		this.getView().getPnlPie().getBtnImprimir().addActionListener(new BtnImprimirListener(this));
	}
	
	public void setTextoBtnCerrar() {
    	this.getView().getPnlPie().getBtnCancelar().setText(MessagesContabilidad.getString("Application.btnCerrar"));
		this.getView().getPnlPie().getBtnCancelar().setMnemonic(MessagesContabilidad.getChar("Application.btnCerrarMnemonic"));
		this.getView().getPnlPie().getBtnCancelar().setToolTipText(MessagesContabilidad.getString("Application.btnCerrarToolTip"));
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
	public ConsultarRetencionView getView() {
		return this.view;
	}

}

//class BtnAceptarListeners implements ActionListener {
//	private ConsultarRetencion controller;
//	
//	public BtnAceptarListeners(ConsultarRetencion controller) {
//		this.controller = controller;
//	}
//
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		try {
//			this.controller.confirmarOrdenPago();
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
//		}
//	}
//}
