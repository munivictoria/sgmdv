package com.trascender.contabilidad.gui.abmSolicitudSuministros;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.compras.recurso.persistent.suministros.SolicitudSuministro;
import com.trascender.gui.framework.abmStandard.AdminController;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.model.TDefaultComboBoxModel;
import com.trascender.gui.framework.util.Conversor;

public class AdminSolicitudSuministro extends AdminController<SolicitudSuministro> {

	private SolicitudSuministroBusquedaModel busquedaModel = new SolicitudSuministroBusquedaModel();
	private SolicitudSuministroTableModel tableModel;
	private AdminSolicitudSuministroView view;
	
	public AdminSolicitudSuministro(JDialog owner) {
		try {
			this.tableModel = new SolicitudSuministroTableModel();
			this.view = new AdminSolicitudSuministroView(owner);
			this.init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public AdminSolicitudSuministro(JFrame owner) {
		try {
			this.tableModel = new SolicitudSuministroTableModel();
			this.view = new AdminSolicitudSuministroView(owner);
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
		this.getView().getPnlBotonesSeleccionProducto().getBtnSeleccionar().addActionListener(new BtnSeleccionarProductoListener(this));
		this.getView().getPnlBotonesSeleccionProducto().getBtnLimpiar().addActionListener(new BtnLimpiarProductoListener(this));
		
		this.getView().getPnlBotonesSeleccionArea().getBtnSeleccionar().addActionListener(new BtnSeleccionarAreaListener(this));
		this.getView().getPnlBotonesSeleccionArea().getBtnLimpiar().addActionListener(new BtnLimpiarAreaListener(this));
		
		this.getView().getPnlBotonesBusqueda().getBtnBuscar().addActionListener(new BtnBuscarListener(this));
	}
	
	@Override
	protected void actualizarBusquedaModel() {
		SolicitudSuministroBusquedaModel locModel = this.getBusquedaModel();
		
		Object locEstado = this.getView().getCbEstado().getSelectedItem();
		
		locModel.fireActualizarDatos();
	}

	@Override
	protected void actualizarBusquedaView() {
		this.getView().getTfproducto().setText(Conversor.getVacioSiNull(this.getBusquedaModel().getBienAsociado()));
		this.getView().getTfArea().setText(Conversor.getVacioSiNull(this.getBusquedaModel().getArea()));
	}
	
	public void buscar() throws Exception {
		final AdminSolicitudSuministro controller = this;
		Thread locThread = new Thread(new Runnable(){
			public void run() {
				try {
					controller.actualizarBusquedaModel();
					controller.getView().iniBusqueda();
					List<SolicitudSuministro> locLista = controller.getBusquedaModel().buscar();
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

	@Override
	protected SolicitudSuministroBusquedaModel getBusquedaModel() {
		return this.busquedaModel;
	}

	@Override
	protected SolicitudSuministroTableModel getTableModel() {
		return this.tableModel;
	}

	@Override
	protected AdminSolicitudSuministroView getView() {
		return this.view;
	}
	
}

class BtnSeleccionarProductoListener implements ActionListener {
	AdminSolicitudSuministro controller;
	
	public BtnSeleccionarProductoListener(AdminSolicitudSuministro controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}
	
}

class BtnLimpiarProductoListener implements ActionListener {
	AdminSolicitudSuministro controller;
	
	public BtnLimpiarProductoListener(AdminSolicitudSuministro controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}

class BtnSeleccionarAreaListener implements ActionListener {
	AdminSolicitudSuministro controller;
	
	public BtnSeleccionarAreaListener(AdminSolicitudSuministro controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}

class BtnLimpiarAreaListener implements ActionListener {
	AdminSolicitudSuministro controller;
	
	public BtnLimpiarAreaListener(AdminSolicitudSuministro controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}

class BtnBuscarListener implements ActionListener {
	AdminSolicitudSuministro controller;
	
	public BtnBuscarListener(AdminSolicitudSuministro controller) {
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