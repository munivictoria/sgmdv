package com.trascender.contabilidad.gui.abmPersonaFisica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.framework.recurso.persistent.PersonaFisica.TipoDocumento;
import com.trascender.gui.framework.abmStandard.AdminController;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.model.TDefaultComboBoxModel;
import com.trascender.gui.framework.util.Conversor;

public class AdminPersonaFisica extends AdminController<PersonaFisica> {
	
	private AdminPersonaFisicaView view;
	private PersonaFisicaBusquedaModel busquedaModel = new PersonaFisicaBusquedaModel();
	private PersonaFisicaTableModel tableModel = new PersonaFisicaTableModel();
	
	public AdminPersonaFisica(JFrame owner) throws Exception {
		this.view = new AdminPersonaFisicaView(owner);
		this.init();
	}
	
	public AdminPersonaFisica(JDialog owner) throws Exception {
		this.view = new AdminPersonaFisicaView(owner);
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

		this.getView().getCbTipoDocumento().setModel(new TDefaultComboBoxModel(TipoDocumento.values()));
	}
	
	private void setListeners() {
		this.getView().getPnlBotonesBusqueda().getBtnBuscar().addActionListener(new BtnBuscarListener(this));
	}
	
	@Override
	protected void actualizarBusquedaModel() {
		PersonaFisicaBusquedaModel locModel = this.getBusquedaModel();
		locModel.setCuil(Conversor.getNullSiVacio(this.getView().getTfCuil().getText()));
		
		Object locTipoDocumento = this.getView().getCbTipoDocumento().getSelectedItem();
		if (locTipoDocumento != null) locModel.setTipoDocumento((TipoDocumento)locTipoDocumento);
		else locModel.setTipoDocumento(null);
		
		locModel.setNumeroDocumento(Conversor.getNullSiVacio(this.getView().getTfNumeroDocumento().getText()));
		locModel.setApellido(Conversor.getNullSiVacio(this.getView().getTfApellido().getText()));
		locModel.setNombre(Conversor.getNullSiVacio(this.getView().getTfNombre().getText()));
		
		locModel.fireActualizarDatos();
	}

	@Override
	protected void actualizarBusquedaView() {
		this.getView().getTfCuil().setText(Conversor.getVacioSiNull(this.getBusquedaModel().getCuil()));
		this.getView().getCbTipoDocumento().setSelectedItem(this.getBusquedaModel().getTipoDocumento());
		this.getView().getTfNumeroDocumento().setText(Conversor.getVacioSiNull(this.getBusquedaModel().getNumeroDocumento()));
		this.getView().getTfApellido().setText(Conversor.getVacioSiNull(this.getBusquedaModel().getApellido()));
		this.getView().getTfNombre().setText(Conversor.getVacioSiNull(this.getBusquedaModel().getNombre()));
	}

	@Override
	protected PersonaFisicaBusquedaModel getBusquedaModel() {
		return this.busquedaModel;
	}

	@Override
	protected PersonaFisicaTableModel getTableModel() {
		return this.tableModel;
	}

	@Override
	protected AdminPersonaFisicaView getView() {
		return this.view;
	}
	
	void buscar() throws Exception {
		final AdminPersonaFisica controller = this;
		Thread locThread = new Thread(new Runnable(){
			public void run() {
				try {
					controller.actualizarBusquedaModel();
					controller.getView().iniBusqueda();
					List<PersonaFisica> locLista = controller.getBusquedaModel().buscar();
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
	private AdminPersonaFisica controller;
	public BtnBuscarListener(AdminPersonaFisica controller) {
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
