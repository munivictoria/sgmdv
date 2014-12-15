package com.trascender.contabilidad.gui.abmGrupoBien;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.compras.recurso.persistent.suministros.GrupoBien;
import com.trascender.gui.framework.abmStandard.AdminController;
import com.trascender.gui.framework.main.AppManager;

public class AdminGrupoBien extends AdminController<GrupoBien> {
	
	private AdminGrupoBienView view;
	private GrupoBienBusquedaModel busquedaModel = new GrupoBienBusquedaModel();
	private GrupoBienTableModel tableModel = new GrupoBienTableModel();
	
	public AdminGrupoBien(JFrame owner) throws Exception {
		this.view = new AdminGrupoBienView(owner);
		this.init();
	}

	public AdminGrupoBien(JDialog owner) throws Exception {
		this.view = new AdminGrupoBienView(owner);
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
		this.getBusquedaModel().fireActualizarDatos();
	}

	@Override
	protected void actualizarBusquedaView() {
		
	}

	@Override
	protected GrupoBienBusquedaModel getBusquedaModel() {
		return this.busquedaModel;
	}

	@Override
	protected GrupoBienTableModel getTableModel() {
		return this.tableModel;
	}

	@Override
	protected AdminGrupoBienView getView() {
		return this.view;
	}
	
	void buscar() throws Exception {
		final AdminGrupoBien controller = this;
		Thread locThread = new Thread(new Runnable(){
			public void run() {
				try {
					controller.actualizarBusquedaModel();
					controller.getView().iniBusqueda();
					List<GrupoBien> locLista = controller.getBusquedaModel().buscar();
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
	private AdminGrupoBien controller;
	public BtnBuscarListener(AdminGrupoBien controller) {
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
