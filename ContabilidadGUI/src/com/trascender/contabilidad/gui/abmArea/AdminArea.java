package com.trascender.contabilidad.gui.abmArea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.framework.recurso.persistent.Area;
import com.trascender.gui.framework.abmStandard.AdminController;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Conversor;

public class AdminArea extends AdminController<Area> {

	private AreaBusquedaModel busquedaModel = new AreaBusquedaModel();
	private AreaTableModel tableModel;
	private AdminAreaView view;

	public AdminArea(JDialog owner) {
		try {
			this.tableModel = new AreaTableModel();
			this.view = new AdminAreaView(owner);
			this.init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public AdminArea(JFrame owner) {
		try {
			this.tableModel = new AreaTableModel();
			this.view = new AdminAreaView(owner);
			this.init();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		AreaBusquedaModel locModel = this.getBusquedaModel();
		
		locModel.setNombre(Conversor.getNullSiVacio(this.getView().getTfNombre().getText()));
		
		locModel.fireActualizarDatos();
	}

	@Override
	protected void actualizarBusquedaView() {
		this.getView().getTfNombre().setText(Conversor.getVacioSiNull(this.getBusquedaModel().getNombre()));
	}

	@Override
	protected AreaBusquedaModel getBusquedaModel() {
		return this.busquedaModel;
	}

	@Override
	protected AreaTableModel getTableModel() {
		return this.tableModel;
	}

	@Override
	protected AdminAreaView getView() {
		return this.view;
	}
	
	public void buscar() throws Exception {
		final AdminArea controller = this;
		Thread locThread = new Thread(new Runnable(){
			public void run() {
				try {
					controller.actualizarBusquedaModel();
					controller.getView().iniBusqueda();
					List<Area> locLista = controller.getBusquedaModel().buscar();
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
	private AdminArea controller;
	
	public BtnBuscarListener(AdminArea controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.buscar();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}
