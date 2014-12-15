package com.trascender.contabilidad.gui.abmAsociacionCuentaLineaOrdenCompra.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.compras.recurso.persistent.suministros.LineaOrdenCompra;
import com.trascender.contabilidad.gui.abmAsociacionCuentaLineaOrdenCompra.AsociacionCuentaLineaOrdenCompraABMModel;
import com.trascender.contabilidad.gui.abmLineaOrdenCompra.LineaOrdenCompraTableModel;
import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.main.AppManager;

public class AgregarAsociacionCuentaLineaOrdenCompra extends ABMAsociacionCuentaLineaOrdenCompra {

	private AgregarAsociacionCuentaLineaOrdenCompraView view;
	private AsociacionCuentaLineaOrdenCompraABMModel abmModel = new AsociacionCuentaLineaOrdenCompraABMModel();
	private LineaOrdenCompraTableModel tableModel = new LineaOrdenCompraTableModel();
	
	public AgregarAsociacionCuentaLineaOrdenCompra(JDialog owner) throws Exception {
		this.view = new AgregarAsociacionCuentaLineaOrdenCompraView(owner);
		
		this.init();
	}
	
	public AgregarAsociacionCuentaLineaOrdenCompra(JFrame owner) throws Exception {
		this.view = new AgregarAsociacionCuentaLineaOrdenCompraView(owner);
		
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		this.setModels();
		this.setListeners();
	}
	
	private void setModels() {
		this.getView().setAbmModel(this.getAbmModel());
		this.getView().setTableModel(this.getTableModel());
	}
	
	private void setListeners() {
		this.getView().getPnlBtnTabla().getBtnAgregar().addActionListener(new BtnAsociarListener(this));
		
		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnGuardarAsociacionListener(this));
	}
	
	public void setTextoBotonAsociar() {
		this.getView().getPnlBtnTabla().getBtnAgregar().setText(MessagesContabilidad.getString("ABMAsociacionCuentaLineaOrdenCompra.bntAceptarTexto"));
		this.getView().getPnlBtnTabla().getBtnAgregar().setMnemonic(MessagesContabilidad.getChar(("ABMAsociacionCuentaLineaOrdenCompra.bntAceptarTextoMnemonic")));
		this.getView().getPnlBtnTabla().getBtnAgregar().setToolTipText(MessagesContabilidad.getString("ABMAsociacionCuentaLineaOrdenCompra.bntAceptarTextoToolTip"));
	}
	
	@Override
	public AsociacionCuentaLineaOrdenCompraABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public AgregarAsociacionCuentaLineaOrdenCompraView getView() {
		return this.view;
	}
	
	@Override
	public LineaOrdenCompraTableModel getTableModel() {
		return this.tableModel;
	}

	
	void asociarCuentaLineaOrdenCompra() throws Exception {

		LineaOrdenCompra locLineaOrdenCompra = this.getSelectedRow();
		
		if (locLineaOrdenCompra != null) {
//			LineaOrdenCompra lineaOrdenCompra  = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionOrdenCompra().findOrdenCompraByID(locLineaOrdenCompra .getIdLineaOrdenCompra());

			AsociarCuentaLineaOrdenCompra asociarCuentaLineaOrdenCompra = new AsociarCuentaLineaOrdenCompra(this.getView());

			asociarCuentaLineaOrdenCompra.getAbmModel().setObjetoABM(locLineaOrdenCompra);

			asociarCuentaLineaOrdenCompra.actualizarView(); 
			asociarCuentaLineaOrdenCompra.open();

			if (asociarCuentaLineaOrdenCompra.isOperacionRealizada()) {
				locLineaOrdenCompra.setCuentaRfr(asociarCuentaLineaOrdenCompra.getAbmModel().getCuentaRfr());
				this.getTableModel().updateRow(locLineaOrdenCompra );
			}
		}
	}
		
	void guardarAsociacionCuentaLineaOrdenCompra() throws Exception {
		if (validarDatos()) {
			this.actualizarABMModel();
			this.getAbmModel().modificar();
			this.setOperacionRealizada(true);
			this.close();
		}
	}

}

class BtnAsociarListener implements ActionListener {

	private AgregarAsociacionCuentaLineaOrdenCompra controller;
	
	public BtnAsociarListener(AgregarAsociacionCuentaLineaOrdenCompra controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.asociarCuentaLineaOrdenCompra();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}

class BtnGuardarAsociacionListener implements ActionListener {

	private AgregarAsociacionCuentaLineaOrdenCompra controller;
	
	public BtnGuardarAsociacionListener(AgregarAsociacionCuentaLineaOrdenCompra controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.guardarAsociacionCuentaLineaOrdenCompra();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}
