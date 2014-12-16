package com.trascender.contabilidad.gui.abmBien;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.compras.recurso.persistent.suministros.Bien;
import com.trascender.gui.framework.abmStandard.AdminController;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Conversor;

public class AdminBien extends AdminController<Bien> {
	
	private AdminBienView view;
	private BienBusquedaModel busquedaModel = new BienBusquedaModel();
	private BienTableModel tableModel = new BienTableModel();
	
	public AdminBien(JFrame owner) throws Exception {
		this.view = new AdminBienView(owner);
		this.init();
	}
	
	public AdminBien(JDialog owner) throws Exception {
		this.view = new AdminBienView(owner);
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
		this.getView().getPnlBotonesSeleccionGrupoBien().getBtnSeleccionar().addActionListener(new BtnSeleccionarGrupoBienListener(this));
		this.getView().getPnlBotonesSeleccionGrupoBien().getBtnLimpiar().addActionListener(new BtnLimpiarGrupoBienListener(this));
		
		this.getView().getPnlBotonesBusqueda().getBtnBuscar().addActionListener(new BtnBuscarListener(this));
	}
	
	@Override
	protected void actualizarBusquedaModel() {
		BienBusquedaModel locModel = this.getBusquedaModel();
		
		locModel.setNombre(Conversor.getNullSiVacio(this.getView().getTfNombre().getText()));
		
		locModel.fireActualizarDatos();
	}

	@Override
	protected void actualizarBusquedaView() {
		this.getView().getTfNombre().setText(Conversor.getVacioSiNull(this.getBusquedaModel().getNombre()));
		this.getView().getTfGrupoBien().setText(Conversor.getVacioSiNull(this.getBusquedaModel().getGrupoBien()));
	}

	@Override
	protected BienBusquedaModel getBusquedaModel() {
		return this.busquedaModel;
	}

	@Override
	protected BienTableModel getTableModel() {
		return this.tableModel;
	}

	@Override
	protected AdminBienView getView() {
		return this.view;
	}
	
	void buscar() throws Exception {
		final AdminBien controller = this;
		Thread locThread = new Thread(new Runnable(){
			public void run() {
				try {
					controller.actualizarBusquedaModel();
					controller.getView().iniBusqueda();
					List<Bien> locLista = controller.getBusquedaModel().buscar();
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
	
	void limpiarGrupoBien() throws Exception {
		this.getBusquedaModel().setGrupoBien(null);
		this.actualizarBusquedaModel();
		this.actualizarBusquedaView();
	}
	
}


class BtnBuscarListener implements ActionListener {
	private AdminBien controller;
	public BtnBuscarListener(AdminBien controller) {
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


class BtnSeleccionarGrupoBienListener implements ActionListener {
	private AdminBien controller;
	public BtnSeleccionarGrupoBienListener(AdminBien controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
//			this.controller.seleccionarGrupoBien();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}


class BtnLimpiarGrupoBienListener implements ActionListener {
	private AdminBien controller;
	public BtnLimpiarGrupoBienListener(AdminBien controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.limpiarGrupoBien();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}
