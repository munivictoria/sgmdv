package com.trascender.contabilidad.gui.abmUsuario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.gui.framework.abmStandard.AdminController;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Conversor;

public class AdminUsuario extends AdminController<Usuario> {
	private UsuarioBusquedaModel busquedaModel = new UsuarioBusquedaModel();
	private UsuarioTableModel tableModel;
	private AdminUsuarioView view;

	public AdminUsuario(JDialog owner) {
		try {
			this.tableModel = new UsuarioTableModel();
			this.view = new AdminUsuarioView(owner);
			this.init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public AdminUsuario(JFrame owner) {
		try {
			this.tableModel = new UsuarioTableModel();
			this.view = new AdminUsuarioView(owner);
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
		UsuarioBusquedaModel locModel = this.getBusquedaModel();
		
		locModel.setNombre(Conversor.getNullSiVacio(this.getView().getTfNombre().getText()));
		
		locModel.fireActualizarDatos();
	}

	@Override
	protected void actualizarBusquedaView() {
		this.getView().getTfNombre().setText(Conversor.getVacioSiNull(this.getBusquedaModel().getNombre()));
	}

	@Override
	protected UsuarioBusquedaModel getBusquedaModel() {
		return this.busquedaModel;
	}

	@Override
	protected UsuarioTableModel getTableModel() {
		return this.tableModel;
	}

	@Override
	protected AdminUsuarioView getView() {
		return this.view;
	}
	
	public void buscar() throws Exception {
		final AdminUsuario controller = this;
		Thread locThread = new Thread(new Runnable(){
			public void run() {
				try {
					controller.actualizarBusquedaModel();
					controller.getView().iniBusqueda();
					List<Usuario> locLista = controller.getBusquedaModel().buscar();
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
	private AdminUsuario controller;
	
	public BtnBuscarListener(AdminUsuario controller) {
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
