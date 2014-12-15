package com.trascender.contabilidad.gui.abmFolioLibroDiario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.contabilidad.gui.abmLibroDiario.AdminLibroDiario;
import com.trascender.contabilidad.recurso.persistent.FolioLibroDiario;
import com.trascender.contabilidad.recurso.persistent.LibroDiario;
import com.trascender.gui.framework.abmStandard.AdminController;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Conversor;

@SuppressWarnings("unchecked")
public class AdminFolioLibroDiario extends AdminController {
	
	private AdminFolioLibroDiarioView view;
	private FolioLibroDiarioBusquedaModel busquedaModel = new FolioLibroDiarioBusquedaModel();
	private FolioLibroDiarioTableModel tableModel = new FolioLibroDiarioTableModel();
	
	public AdminFolioLibroDiario(JFrame owner) throws Exception {
		this.view = new AdminFolioLibroDiarioView(owner);
		this.init();
	}
	
	public AdminFolioLibroDiario (JDialog owner) throws Exception {
		this.view = new AdminFolioLibroDiarioView(owner);
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
		this.view.setBusquedaModel(this.busquedaModel);
		this.view.getPnlTabla().getTblDatos().setModel(this.tableModel);
	}
	
	private void setListeners() {
		this.getView().getPnlBotonesSeleccionLibroDiario().getBtnSeleccionar().addActionListener(new BtnSeleccionarLibroDiarioListener(this));
		this.getView().getPnlBotonesSeleccionLibroDiario().getBtnLimpiar().addActionListener(new BtnLimpiarLibroDiarioListener(this));
	
		this.getView().getPnlBotonesBusqueda().getBtnBuscar().addActionListener(new BtnBuscarListener(this));
	}
	
	@Override
	protected void actualizarBusquedaModel() {
		FolioLibroDiarioBusquedaModel locModel = this.getBusquedaModel();
		locModel.setNumero(this.view.getTfNumero().getText());
		locModel.fireActualizarDatos();
	}

	@Override
	protected void actualizarBusquedaView() {
		this.getView().getTfNumero().setText(this.getBusquedaModel().getNumero());
		this.getView().getTfLibroDiario().setText(Conversor.getVacioSiNull(this.getBusquedaModel().getLibroDiario()));
	}

	@Override
	protected FolioLibroDiarioBusquedaModel getBusquedaModel() {
		return this.busquedaModel;
	}

	@Override
	protected FolioLibroDiarioTableModel getTableModel() {
		return this.tableModel;
	}

	@Override
	protected AdminFolioLibroDiarioView getView() {
		return this.view;
	}

	void seleccionarLibroDiario() throws Exception {
		AdminLibroDiario adminLibroDiario = new AdminLibroDiario((JDialog)this.view);
		this.actualizarBusquedaModel();
		LibroDiario locLibroDiario = adminLibroDiario.openSelect();
		if (locLibroDiario != null) {
			this.busquedaModel.setLibroDiario(locLibroDiario);
			this.actualizarBusquedaView();
		}
	}
	
	void limpiarLibroDiario() throws Exception {
		this.busquedaModel.setLibroDiario(null);
		this.actualizarBusquedaModel();
		this.actualizarBusquedaView();
	}
	
	void buscar() throws Exception {
		final AdminFolioLibroDiario controller = this;
		Thread locThread = new Thread(new Runnable() {
			public void run() {
				try {
					controller.actualizarBusquedaModel();
					controller.getView().iniBusqueda();
					List<FolioLibroDiario> locLista = controller.getBusquedaModel().buscar();
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

class BtnSeleccionarLibroDiarioListener implements ActionListener {
	
	private AdminFolioLibroDiario controller;
	
	public BtnSeleccionarLibroDiarioListener(AdminFolioLibroDiario controller) {
		this.controller = controller;
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			controller.seleccionarLibroDiario();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnLimpiarLibroDiarioListener implements ActionListener {
	
	private AdminFolioLibroDiario controller;
	
	public BtnLimpiarLibroDiarioListener(AdminFolioLibroDiario controller) {
		this.controller = controller;
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			controller.limpiarLibroDiario();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnBuscarListener implements ActionListener {
	
	private AdminFolioLibroDiario controller;
	
	public BtnBuscarListener(AdminFolioLibroDiario controller) {
		this.controller = controller;
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.buscar();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(controller.getView(), ex.getMessage());
		}
	}
}

