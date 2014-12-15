package com.trascender.contabilidad.gui.abmArticulo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.compras.recurso.persistent.inventario.Articulo;
import com.trascender.compras.recurso.persistent.inventario.Articulo.EstadoContable;
import com.trascender.contabilidad.gui.abmArea.AdminArea;
import com.trascender.framework.recurso.persistent.Area;
import com.trascender.gui.framework.abmStandard.AdminController;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.model.TDefaultComboBoxModel;
import com.trascender.gui.framework.util.Conversor;

public class AdminArticulo extends AdminController<Articulo>{
	
	private AdminArticuloView view;
	private ArticuloBusquedaModel busquedaModel = new ArticuloBusquedaModel();
	private ArticuloTableModel tableModel;
	
	
	public AdminArticulo(JFrame owner){
		this.view = new AdminArticuloView(owner);
		try{
			tableModel = new ArticuloTableModel();
		} catch (Exception e){
			e.printStackTrace();
		}
		this.init();
	}
	
	public AdminArticulo(JDialog owner){
		this.view = new AdminArticuloView(owner);
		try{
			tableModel = new ArticuloTableModel();
		} catch (Exception e){
			e.printStackTrace();
		}
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
		
		this.getView().getCbEstadoContable().setModel(new TDefaultComboBoxModel(EstadoContable.values()));
	}
	
	private void setListeners() {
		this.getView().getPnlBotonesSeleccionArea().getBtnSeleccionar().addActionListener(new BtnSeleccionarAreaListener(this));
		this.getView().getPnlBotonesSeleccionArea().getBtnLimpiar().addActionListener(new BtnLimpiarAreaListener(this));
		
		this.getView().getPnlBotonesBusqueda().getBtnBuscar().addActionListener(new BtnBuscarListener(this));
	}

	@Override
	protected AdminArticuloView getView() {
		return this.view;
	}

	@Override
	protected ArticuloTableModel getTableModel() {
		return this.tableModel;
	}

	@Override
	protected ArticuloBusquedaModel getBusquedaModel() {
		return this.busquedaModel;
	}

	@Override
	protected void actualizarBusquedaModel() {
		ArticuloBusquedaModel locModel = this.getView().getBusquedaModel();
		
		locModel.setNombre(Conversor.getNullSiVacio(this.getView().getTfNombre().getText()));
		locModel.setCodigo(Conversor.getNullSiVacio(this.getView().getTfCodigo().getText()));
		
		Object locEstadoContable = this.getView().getCbEstadoContable().getSelectedItem();
		if (locEstadoContable != null) locModel.setEstadoContable((EstadoContable)locEstadoContable);
		else locModel.setEstadoContable(null);
		
		locModel.fireActualizarDatos();
	}

	@Override
	protected void actualizarBusquedaView() {
		this.getView().getTfNombre().setText(this.getBusquedaModel().getNombre());
		this.getView().getTfCodigo().setText(this.getBusquedaModel().getCodigo());
		this.getView().getCbEstadoContable().setSelectedItem(this.getBusquedaModel().getEstadoContable());
		this.getView().getTfArea().setText(Conversor.getVacioSiNull(this.getBusquedaModel().getArea()));
	}
	
	void buscar() {
		if (this.validarDatos()) {
			final AdminArticulo controller = this;
			Thread locThread = new Thread(new Runnable() {
				public void run() {
					try {
						controller.actualizarBusquedaModel();
						controller.getView().iniBusqueda();
						List<Articulo> locListaArticulo = controller.getBusquedaModel().buscar();
						controller.getTableModel().setListaObjetos(locListaArticulo);
					} catch (Exception ex) {
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
	
	void seleccionarArea() throws Exception {
		AdminArea adminArea = new AdminArea(this.getView());
		Area locArea = adminArea.openSelect();
		
		if (locArea != null) {
			this.getBusquedaModel().setArea(locArea);
			this.actualizarBusquedaModel();
			this.actualizarBusquedaView();
		}
	}
	
	void limpiarArea() throws Exception {
		this.getBusquedaModel().setArea(null);
		this.actualizarBusquedaModel();
		this.actualizarBusquedaView();
	}
	
	class BtnSeleccionarAreaListener implements ActionListener {
		AdminArticulo controller;
		
		public BtnSeleccionarAreaListener(
				AdminArticulo controller) {
			this.controller = controller;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				this.controller.seleccionarArea();
			} catch (Exception ex) {
				ex.printStackTrace();
				AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
			}
		}
		
	}

	class BtnLimpiarAreaListener implements ActionListener {
		AdminArticulo controller;
		
		public BtnLimpiarAreaListener(
				AdminArticulo controller) {
			this.controller = controller;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				this.controller.limpiarArea();
			} catch (Exception ex) {
				ex.printStackTrace();
				AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
			}
		}
	}
	
	class BtnBuscarListener implements ActionListener{
		AdminArticulo controller;
		
		public BtnBuscarListener(AdminArticulo controller){
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
	

}
