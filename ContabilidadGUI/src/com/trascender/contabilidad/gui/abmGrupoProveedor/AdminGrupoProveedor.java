package com.trascender.contabilidad.gui.abmGrupoProveedor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.compras.recurso.persistent.suministros.GrupoProveedor;
import com.trascender.gui.framework.abmStandard.AdminController;
import com.trascender.gui.framework.main.AppManager;

public class AdminGrupoProveedor extends AdminController<GrupoProveedor> {
	
	private AdminGrupoProveedorView view;
	private GrupoProveedorBusquedaModel busquedaModel = new GrupoProveedorBusquedaModel();
	private GrupoProveedorTableModel tableModel = new GrupoProveedorTableModel();
	
	public AdminGrupoProveedor(JFrame owner) throws Exception {
		this.view = new AdminGrupoProveedorView(owner);
		this.init();
	}
	
	public AdminGrupoProveedor(JDialog owner) throws Exception {
		this.view = new AdminGrupoProveedorView(owner);
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
		
	}

	@Override
	protected void actualizarBusquedaView() {
		this.getBusquedaModel().fireActualizarDatos();
	}

	@Override
	protected GrupoProveedorBusquedaModel getBusquedaModel() {
		return this.busquedaModel;
	}

	@Override
	protected GrupoProveedorTableModel getTableModel() {
		return this.tableModel;
	}

	@Override
	protected AdminGrupoProveedorView getView() {
		return this.view;
	}
	
	void buscar() throws Exception {
		final AdminGrupoProveedor controller = this;
		Thread locThread = new Thread(new Runnable(){
			public void run() {
				try {
					controller.actualizarBusquedaModel();
					controller.getView().iniBusqueda();
					List<GrupoProveedor> locLista = controller.getBusquedaModel().buscar();
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
	private AdminGrupoProveedor controller;
	public BtnBuscarListener(AdminGrupoProveedor controller) {
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
