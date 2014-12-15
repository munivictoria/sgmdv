package com.trascender.contabilidad.gui.abmDigestoMunicipal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.framework.recurso.persistent.DigestoMunicipal;
import com.trascender.framework.util.Util;
import com.trascender.gui.framework.abmStandard.AdminController;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.model.TDefaultComboBoxModel;
import com.trascender.gui.framework.util.Conversor;

public class AdminDigestoMunicipal extends AdminController<DigestoMunicipal> {
	
	private AdminDigestoMunicipalView view;
	private DigestoMunicipalBusquedaModel busquedaModel = new DigestoMunicipalBusquedaModel();
	private DigestoMunicipalTableModel tableModel = new DigestoMunicipalTableModel();
	
	public AdminDigestoMunicipal(JFrame owner) throws Exception {
		this.view = new AdminDigestoMunicipalView(owner);
		this.init();
	}
	
	public AdminDigestoMunicipal(JDialog owner) throws Exception {
		this.view = new AdminDigestoMunicipalView(owner);
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
		
		this.getView().getCbTipo().setModel(new TDefaultComboBoxModel(DigestoMunicipal.Tipo.values()));
	}
	
	private void setListeners() {
		this.getView().getPnlBotonesBusqueda().getBtnBuscar().addActionListener(new BtnBuscarListener(this));
	}
	
	@Override
	protected void actualizarBusquedaModel() {
		DigestoMunicipalBusquedaModel locModel = this.getBusquedaModel();
		
		locModel.setNombre(Conversor.getNullSiVacio(this.getView().getTfNombre().getText()));
		
		DigestoMunicipal.Tipo locTipo = null;
		Object locTipoSelected = this.getView().getCbTipo().getSelectedItem();
		if (locTipoSelected != null) locTipo = DigestoMunicipal.Tipo.valueOf(Util.getEnumNameFromString(locTipoSelected.toString()));
		locModel.setTipo(locTipo);
		
		locModel.fireActualizarDatos();
	}

	@Override
	protected void actualizarBusquedaView() {
		this.getView().getTfNombre().setText(this.getBusquedaModel().getNombre());
		this.getView().getCbTipo().setSelectedItem(this.getBusquedaModel().getTipo());
	}

	@Override
	protected DigestoMunicipalBusquedaModel getBusquedaModel() {
		return this.busquedaModel;
	}

	@Override
	protected DigestoMunicipalTableModel getTableModel() {
		return this.tableModel;
	}

	@Override
	protected AdminDigestoMunicipalView getView() {
		return this.view;
	}
	
	void buscar() throws Exception {
		final AdminDigestoMunicipal controller = this;
		Thread locThread = new Thread(new Runnable(){
			public void run() {
				try {
					controller.actualizarBusquedaModel();
					controller.getView().iniBusqueda();
					List<DigestoMunicipal> locLista = controller.getBusquedaModel().buscar();
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
	private AdminDigestoMunicipal controller;
	public BtnBuscarListener(AdminDigestoMunicipal controller) {
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
