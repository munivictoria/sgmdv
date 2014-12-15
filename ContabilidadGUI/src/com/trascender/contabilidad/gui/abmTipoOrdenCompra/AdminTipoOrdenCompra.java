package com.trascender.contabilidad.gui.abmTipoOrdenCompra;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.compras.recurso.persistent.suministros.TipoOrdenCompra;
import com.trascender.gui.framework.abmStandard.AdminController;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Conversor;

public class AdminTipoOrdenCompra extends AdminController<TipoOrdenCompra> {
	
	private AdminTipoOrdenCompraView view;
	private TipoOrdenCompraBusquedaModel busquedaModel = new TipoOrdenCompraBusquedaModel();
	private TipoOrdenCompraTableModel tableModel = new TipoOrdenCompraTableModel();
	
	public AdminTipoOrdenCompra(JFrame owner) throws Exception {
		this.view = new AdminTipoOrdenCompraView(owner);
		this.init();
	}
	
	public AdminTipoOrdenCompra(JDialog owner) throws Exception {
		this.view = new AdminTipoOrdenCompraView(owner);
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		
		this.getView().getPnlPie().getBtnAgregar().setEnabled(false);
		this.getView().getPnlPie().getBtnModificar().setEnabled(false);
		this.getView().getPnlPie().getBtnEliminar().setEnabled(false);
		
		this.setModels();
		this.setListeners();
	}
	
	private void setModels() {
		this.getView().setBusquedaModel(this.getBusquedaModel());
		this.getView().setTableModel(this.getTableModel());
	}
	
	private void setListeners() {
		this.getView().getPnlBotonesBusqueda().getBtnBuscar().addActionListener(new BtnBuscarListener(this));
	}
	
	@Override
	protected void actualizarBusquedaModel() {
		TipoOrdenCompraBusquedaModel locModel = this.getBusquedaModel();
		
		locModel.setNombre(Conversor.getNullSiVacio(this.getView().getTfNombre().getText()));
		
		locModel.fireActualizarDatos();
	}

	@Override
	protected void actualizarBusquedaView() {
		this.getView().getTfNombre().setText(Conversor.getVacioSiNull(this.getBusquedaModel().getNombre()));		
	}

	@Override
	protected TipoOrdenCompraBusquedaModel getBusquedaModel() {
		return this.busquedaModel;
	}

	@Override
	protected TipoOrdenCompraTableModel getTableModel() {
		return this.tableModel;
	}

	@Override
	protected AdminTipoOrdenCompraView getView() {
		return this.view;
	}

	void buscar() throws Exception {
		final AdminTipoOrdenCompra controller = this;
		Thread locThread = new Thread(new Runnable(){
			public void run() {
				try {
					controller.actualizarBusquedaModel();
					controller.getView().iniBusqueda();
					List<TipoOrdenCompra> locLista = controller.getBusquedaModel().buscar();
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
	
}

class BtnBuscarListener implements ActionListener {
	private AdminTipoOrdenCompra controller;
	public BtnBuscarListener(AdminTipoOrdenCompra controller) {
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
