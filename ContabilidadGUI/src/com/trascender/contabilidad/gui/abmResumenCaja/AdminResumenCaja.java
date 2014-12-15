package com.trascender.contabilidad.gui.abmResumenCaja;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.contabilidad.recurso.persistent.TicketCaja;
import com.trascender.contabilidad.recurso.persistent.Caja.Estado;
import com.trascender.gui.framework.abmStandard.AdminController;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.model.TDefaultComboBoxModel;
import com.trascender.gui.framework.util.Conversor;

public class AdminResumenCaja extends AdminController<TicketCaja> {

	private AdminResumenCajaView view;
	private ResumenCajaTableModel tableModel = new ResumenCajaTableModel();
	private ResumenCajaBusquedaModel busquedaModel = new ResumenCajaBusquedaModel();
	
	public AdminResumenCaja(JDialog owner) throws Exception {
		this.view = new AdminResumenCajaView(owner);
		this.init();
	}
	
	public AdminResumenCaja(JFrame owner) throws Exception {
		this.view = new AdminResumenCajaView(owner);
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		this.setModels();
		this.setListeners();
		this.setPropiedadesBotones();
	}
	
	private void setPropiedadesBotones() {
		this.getView().getPnlPie().getBtnAgregar().setVisible(false);
		this.getView().getPnlPie().getBtnModificar().setVisible(false);
		this.getView().getPnlPie().getBtnEliminar().setText(MessagesContabilidad.getString("Application.btnImprimir"));
		this.getView().getPnlPie().getBtnEliminar().setMnemonic(MessagesContabilidad.getString("Application.btnImprimirMnemonic").charAt(0));
		this.getView().getPnlPie().getBtnEliminar().setToolTipText(MessagesContabilidad.getString("Application.btnImprimirToolTip"));
		this.getView().getPnlPie().getBtnEliminar().setEnabled(false);
	}
		
	private void setModels() {
		this.getView().getCbEstado().setModel(new TDefaultComboBoxModel(Estado.values()));
		this.getView().setBusquedaModel(this.getBusquedaModel());
		this.getView().setTableModel(this.getTableModel());
	}
	
	private void setListeners() {
		this.getView().getPnlBotonesSeleccionCajero().getBtnSeleccionar().addActionListener(new BtnSeleccionarCajeroListener(this));
		this.getView().getPnlBotonesSeleccionCajero().getBtnLimpiar().addActionListener(new BtnLimpiarCajeroListener(this));
		
		this.getView().getPnlBotonesSeleccionCaja().getBtnSeleccionar().addActionListener(new BtnSeleccionarCajaListener(this));
		this.getView().getPnlBotonesSeleccionCaja().getBtnLimpiar().addActionListener(new BtnLimpiarCajaListener(this));
		
		this.getView().getPnlBotonesBusqueda().getBtnBuscar().addActionListener(new BtnBuscarListener(this));
	}
	
	@Override
	protected void actualizarBusquedaModel() {
		ResumenCajaBusquedaModel locModel = this.getBusquedaModel();
		
		locModel.fireActualizarDatos();
	}

	@Override
	protected void actualizarBusquedaView() {
		//this.getView().getTfFecha().setValue(this.getBusquedaModel().getFechaDesde());
		this.getView().getTfCaja().setText(Conversor.getVacioSiNull(this.getBusquedaModel().getCaja()));
	}

	@Override
	protected ResumenCajaBusquedaModel getBusquedaModel() {
		return busquedaModel;
	}

	@Override
	protected ResumenCajaTableModel getTableModel() {
		return tableModel;
	}

	@Override
	protected AdminResumenCajaView getView() {
		return view;
	}

	void buscar() {
		final AdminResumenCaja controller = this;
		Thread locThread = new Thread(new Runnable(){
			public void run() {
				try {
					controller.actualizarBusquedaModel();
					controller.getView().iniBusqueda();
					List<TicketCaja> locLista = controller.getBusquedaModel().buscar();
					controller.getTableModel().setListaObjetos(locLista);
				}
				catch (Exception ex) {
					ex.printStackTrace();
					AppManager.getInstance().showErrorMsg(controller.getView(), ex.getMessage());
				}
				finally {
					controller.getView().finBusqueda();
				}
			}
		});
		locThread.start();
	}
	
	void seleccionarCajero() throws Exception {
		
	}
	
	void limpiarCajero() {
//		this.getBusquedaModel().setCa)(null);
//		this.actualizarBusquedaModel();
//		this.actualizarBusquedaView();
	}
	
	void seleccionarCaja() throws Exception {
//		AdminCaja adminCaja = new AdminCaja(this.getView());
//		this.actualizarBusquedaModel();
//		Caja locCaja = adminCaja.openSelect();
//		if (locCaja != null) {
//			this.busquedaModel.setCaja(locCaja);
//			this.actualizarBusquedaView();
//		}
	}
	
	void limpiarCaja() {
		this.getBusquedaModel().setCaja(null);
		this.actualizarBusquedaModel();
		this.actualizarBusquedaView();
	}

}

class BtnBuscarListener implements ActionListener {
	private AdminResumenCaja controller;
	public BtnBuscarListener(AdminResumenCaja controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.buscar();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnSeleccionarCajeroListener implements ActionListener {
	private AdminResumenCaja controller;
	
	public BtnSeleccionarCajeroListener(AdminResumenCaja controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.seleccionarCajero();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnLimpiarCajeroListener implements ActionListener {
	private AdminResumenCaja controller;
	
	public BtnLimpiarCajeroListener(AdminResumenCaja controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.limpiarCajero();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}

class BtnSeleccionarCajaListener implements ActionListener {
	private AdminResumenCaja controller;
	public BtnSeleccionarCajaListener(AdminResumenCaja controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.seleccionarCaja();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnLimpiarCajaListener implements ActionListener {
	private AdminResumenCaja controller;
	public BtnLimpiarCajaListener(AdminResumenCaja controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.limpiarCaja();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}
