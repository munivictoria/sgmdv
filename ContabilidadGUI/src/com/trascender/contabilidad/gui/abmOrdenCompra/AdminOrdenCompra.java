package com.trascender.contabilidad.gui.abmOrdenCompra;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.compras.recurso.persistent.suministros.OrdenCompra;
import com.trascender.compras.recurso.persistent.suministros.TipoOrdenCompra;
import com.trascender.contabilidad.gui.abmTipoOrdenCompra.AdminTipoOrdenCompra;
import com.trascender.gui.framework.abmStandard.AdminController;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.model.TDefaultComboBoxModel;
import com.trascender.gui.framework.util.Conversor;

public class AdminOrdenCompra extends AdminController<OrdenCompra> {

	private AdminOrdenCompraView view;
	private OrdenCompraBusquedaModel busquedaModel = new OrdenCompraBusquedaModel();
	private OrdenCompraTableModel tableModel = new OrdenCompraTableModel();
	
	public AdminOrdenCompra(JFrame owner) throws Exception {
		this.view = new AdminOrdenCompraView(owner);
		this.init();
	}
	
	public AdminOrdenCompra(JDialog owner) throws Exception {
		this.view = new AdminOrdenCompraView(owner);
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		
		this.getView().getPnlPie().getBtnAgregar().setEnabled(false);
		this.getView().getPnlPie().getBtnModificar().setEnabled(false);
		this.getView().getPnlPie().getBtnEliminar().setEnabled(false);
		
		this.getView().getPnlBotonesSeleccionProveedor().setVisible(false);
		
		this.setModels();
		this.setListeners();
	}
	
	private void setModels() {
		this.getView().setBusquedaModel(this.getBusquedaModel());
		this.getView().setTableModel(this.getTableModel());
		this.getView().getCbEstado().setModel(new TDefaultComboBoxModel(OrdenCompra.Estado.values()));
	}
	
	private void setListeners() {
		this.getView().getPnlBotonesSeleccionTipoOrdenCompra().getBtnSeleccionar().addActionListener(new BtnSeleccionarTipoOrdenCompraListener(this));
		this.getView().getPnlBotonesSeleccionTipoOrdenCompra().getBtnLimpiar().addActionListener(new BtnLimpiarTipoOrdenCompraListener(this));
		
//		this.getView().getPnlBotonesSeleccionProveedor().getBtnSeleccionar().addActionListener(new BtnSeleccionarProveedorListener(this));
//		this.getView().getPnlBotonesSeleccionProveedor().getBtnLimpiar().addActionListener(new BtnLimpiarProveedorListener(this));
		
		this.getView().getPnlBotonesBusqueda().getBtnBuscar().addActionListener(new BtnBuscarListener(this));
	}
	
	@Override
	protected void actualizarBusquedaModel() {
		OrdenCompraBusquedaModel locModel = this.getBusquedaModel();
		
		locModel.setFechaDesde(Conversor.getDate(this.getView().getTfFechaDesde().getText()));
		locModel.setFechaHasta(Conversor.getDate(this.getView().getTfFechaHasta().getText()));
		
		Object locEstado = this.getView().getCbEstado().getSelectedItem();
		if (locEstado != null) locModel.setEstado((OrdenCompra.Estado)locEstado);
		else locModel.setEstado(null);
		
		locModel.fireActualizarDatos();
	}

	@Override
	public void actualizarBusquedaView() {
		this.getView().getTfFechaDesde().setValue(Conversor.getString(this.getBusquedaModel().getFechaDesde()));
		this.getView().getTfFechaHasta().setValue(Conversor.getString(this.getBusquedaModel().getFechaHasta()));
		this.getView().getTfTipoOrdenCompra().setText(Conversor.getVacioSiNull(this.getBusquedaModel().getTipoOrdenCompra()));
		this.getView().getTfProveedor().setText(Conversor.getVacioSiNull(this.getBusquedaModel().getProveedor()));
		this.getView().getCbEstado().setSelectedItem(this.getBusquedaModel().getEstado());
	}

	@Override
	public OrdenCompraBusquedaModel getBusquedaModel() {
		return this.busquedaModel;
	}

	@Override
	protected OrdenCompraTableModel getTableModel() {
		return tableModel;
	}

	@Override
	protected AdminOrdenCompraView getView() {
		return this.view;
	}

	void buscar() throws Exception {
		final AdminOrdenCompra controller = this;
		Thread locThread = new Thread(new Runnable(){
			public void run() {
				try {
					controller.actualizarBusquedaModel();
					controller.getView().iniBusqueda();
					List<OrdenCompra> locLista = controller.getBusquedaModel().buscar();
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
	
	void seleccionarTipoOrdenCompra() throws Exception {
		AdminTipoOrdenCompra adminTipoOrdenCompra = new AdminTipoOrdenCompra(this.getView());
		this.actualizarBusquedaModel();
		TipoOrdenCompra locTipoOrdenCompra = adminTipoOrdenCompra.openSelect();
		if (locTipoOrdenCompra != null) {
			this.getBusquedaModel().setTipoOrdenCompra(locTipoOrdenCompra);
			this.actualizarBusquedaView();
		}
	}
	
	void limpiarTipoOrdenCompra() {
		this.getBusquedaModel().setTipoOrdenCompra(null);
		this.actualizarBusquedaModel();
		this.actualizarBusquedaView();
	}
	
//	void seleccionarProveedor() throws Exception {
//		AdminProveedor adminProveedor = new AdminProveedor(this.getView());
//		this.actualizarBusquedaModel();
//		Proveedor locProveedor = adminProveedor.openSelect();
//		if (locProveedor != null) {
//			this.getBusquedaModel().setProveedor(locProveedor);
//			this.actualizarBusquedaView();
//		}
//	}
//	
//	void limpiarProveedor() {
//		this.getBusquedaModel().setProveedor(null);
//		this.actualizarBusquedaModel();
//		this.actualizarBusquedaView();
//	}
	
}

class BtnSeleccionarTipoOrdenCompraListener implements ActionListener {
	private AdminOrdenCompra controller;
	public BtnSeleccionarTipoOrdenCompraListener(AdminOrdenCompra controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.seleccionarTipoOrdenCompra();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnLimpiarTipoOrdenCompraListener implements ActionListener {
	private AdminOrdenCompra controller;
	public BtnLimpiarTipoOrdenCompraListener(AdminOrdenCompra controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.limpiarTipoOrdenCompra();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

//class BtnSeleccionarProveedorListener implements ActionListener {
//	private AdminOrdenCompra controller;
//	public BtnSeleccionarProveedorListener(AdminOrdenCompra controller) {
//		this.controller = controller;
//	}
//	public void actionPerformed(ActionEvent e) {
//		try {
//			this.controller.seleccionarProveedor();
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
//		}
//	}
//}
//
//class BtnLimpiarProveedorListener implements ActionListener {
//	private AdminOrdenCompra controller;
//	public BtnLimpiarProveedorListener(AdminOrdenCompra controller) {
//		this.controller = controller;
//	}
//	public void actionPerformed(ActionEvent e) {
//		try {
//			this.controller.limpiarProveedor();
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
//		}
//	}
//}

class BtnBuscarListener implements ActionListener {
	private AdminOrdenCompra controller;
	public BtnBuscarListener(AdminOrdenCompra controller) {
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

