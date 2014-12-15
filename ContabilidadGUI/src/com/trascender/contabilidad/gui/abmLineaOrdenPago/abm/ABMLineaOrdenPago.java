package com.trascender.contabilidad.gui.abmLineaOrdenPago.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

import com.trascender.compras.recurso.persistent.suministros.Factura;
import com.trascender.contabilidad.gui.abmFactura.AdminFactura;
import com.trascender.contabilidad.gui.abmLineaOrdenPago.LineaOrdenPagoABMModel;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.LineaOrdenPago;
import com.trascender.gui.framework.abmStandard.ABMController;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Conversor;
import com.trascender.gui.framework.util.Validador;

public abstract class ABMLineaOrdenPago extends ABMController<LineaOrdenPago> {

	public abstract ABMLineaOrdenPagoView getView();
	public abstract LineaOrdenPagoABMModel getAbmModel();
	
	@Override
	protected void init() {
		super.init();
		this.setModels();
		this.setListeners();
	}
	
	private void setModels() {
		this.getView().setAbmModel(this.getAbmModel());
	}
	
	private void setListeners() {
		this.getView().getPnlBotonesSeleccionOrdenCompra().getBtnSeleccionar().addActionListener(new BtnSeleccionarOrdenCompraListener(this));
		this.getView().getPnlBotonesSeleccionOrdenCompra().getBtnLimpiar().addActionListener(new BtnLimpiarOrdenCompraListener(this));
	}
	
	@Override
	protected void actualizarABMModel() {
		this.getAbmModel().setImporte(Conversor.getDouble(this.getView().getTfImporte().getValue().toString()));
		this.getAbmModel().fireActualizarDatos();
	}

	@Override
	public void actualizarView() {
		this.getView().getTfOrdenCompra().setText(Conversor.getString(this.getAbmModel().getFactura()));
		this.getView().getTfImporte().setValue(this.getAbmModel().getImporte());
	}
	
	@Override
	public boolean validarDatos() {
		boolean validacionOK = true;
		
		List<Object> attNulos = new ArrayList<Object>();
		List<JLabel> lblNulos = new ArrayList<JLabel>();
		
		attNulos.add(this.getView().getTfOrdenCompra().getText());
		lblNulos.add(this.getView().getLblOrdenCompra());
		
		attNulos.add(this.getView().getTfImporte().getValue());
		lblNulos.add(this.getView().getLblImporte());
		
		List<String> listaErrores = new ArrayList<String>();
		try {
			listaErrores.addAll(Validador.validarNulos(attNulos, lblNulos));
		}
		catch (GuiException ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.getView(), ex.getMessage());
			return false;
		}
		
		if (!listaErrores.isEmpty()) {
			validacionOK = false;
			this.mostrarErroresValidacion(listaErrores);
		}
		
		return validacionOK;
	}

	void seleccionarOrdenCompra() throws Exception {
		AdminFactura adminFactura = new AdminFactura(this.getView());
		Factura locFactura = adminFactura.openSelect();
		
		if (locFactura != null) {
			Factura factura = ContabilidadGUI.getInstance().getAdminSystemsCompras().getSystemAdministracionFactura().getFacturaPorId(locFactura.getIdFactura());
			this.getAbmModel().setFactura(factura);
			this.actualizarABMModel();
			this.getAbmModel().setImporte(factura.getTotal());
			this.actualizarView();
		}
	}
	
	void limpiarOrdenCompra() {
		this.getAbmModel().setFactura(null);
		this.getAbmModel().setImporte(null);
		this.actualizarABMModel();
		this.actualizarView();
	}

}

class BtnSeleccionarOrdenCompraListener implements ActionListener {
	private ABMLineaOrdenPago controller;
	
	public BtnSeleccionarOrdenCompraListener(ABMLineaOrdenPago controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.seleccionarOrdenCompra();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnLimpiarOrdenCompraListener implements ActionListener{
	private ABMLineaOrdenPago controller;
	
	public BtnLimpiarOrdenCompraListener(ABMLineaOrdenPago controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.limpiarOrdenCompra();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}
