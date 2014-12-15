package com.trascender.contabilidad.gui.abmProveedor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.compras.recurso.persistent.suministros.Bien;
import com.trascender.compras.recurso.persistent.suministros.GrupoProveedor;
import com.trascender.compras.recurso.persistent.suministros.Proveedor;
import com.trascender.contabilidad.gui.abmBien.AdminBien;
import com.trascender.contabilidad.gui.abmGrupoProveedor.AdminGrupoProveedor;
import com.trascender.gui.framework.abmStandard.AdminController;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.model.TDefaultComboBoxModel;
import com.trascender.gui.framework.util.Conversor;

public class AdminProveedor extends AdminController<Proveedor> {
	
	private AdminProveedorView view;
	private ProveedorBusquedaModel busquedaModel = new ProveedorBusquedaModel();
	private ProveedorTableModel tableModel = new ProveedorTableModel();
	
	public AdminProveedor(JFrame owner) throws Exception {
		this.view = new AdminProveedorView(owner);
		this.init();
	}
	
	public AdminProveedor(JDialog owner) throws Exception {
		this.view = new AdminProveedorView(owner);
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
		this.getView().getCbEstado().setModel(new TDefaultComboBoxModel(Proveedor.Estado.values()));
	}
	
	private void setListeners() {
		this.getView().getPnlBotonesBusqueda().getBtnBuscar().addActionListener(new BtnBuscarListener(this));
	}
	
	@Override
	protected void actualizarBusquedaModel() {
		ProveedorBusquedaModel locModel = this.getBusquedaModel();
		
		locModel.setRazonSocial(Conversor.getNullSiVacio(this.getView().getTfRazonSocial().getText()));
		locModel.setCodigo(Conversor.getNullSiVacio(this.getView().getTfCodigo().getText()));
		
		Object locEstado = this.getView().getCbEstado().getSelectedItem();
		if (locEstado != null) locModel.setEstado((Proveedor.Estado)locEstado);
		else locModel.setEstado(null);
		
		locModel.fireActualizarDatos();
	}

	@Override
	protected void actualizarBusquedaView() {
		this.getView().getTfRazonSocial().setText(Conversor.getVacioSiNull(this.getBusquedaModel().getRazonSocial()));
		this.getView().getTfCodigo().setText(Conversor.getVacioSiNull(this.getBusquedaModel().getCodigo()));
		this.getView().getCbEstado().setSelectedItem(this.getBusquedaModel().getEstado());
	}

	@Override
	protected ProveedorBusquedaModel getBusquedaModel() {
		return this.busquedaModel;
	}

	@Override
	protected ProveedorTableModel getTableModel() {
		return this.tableModel;
	}

	@Override
	protected AdminProveedorView getView() {
		return this.view;
	}
	
	void buscar() throws Exception {
		final AdminProveedor controller = this;
		Thread locThread = new Thread(new Runnable(){
			public void run() {
				try {
					controller.actualizarBusquedaModel();
					controller.getView().iniBusqueda();
					List<Proveedor> locLista = controller.getBusquedaModel().buscar();
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
	private AdminProveedor controller;
	public BtnBuscarListener(AdminProveedor controller) {
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