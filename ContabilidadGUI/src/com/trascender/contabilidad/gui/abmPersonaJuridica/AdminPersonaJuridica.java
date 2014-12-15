package com.trascender.contabilidad.gui.abmPersonaJuridica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.contabilidad.gui.abmPersonaFisica.AdminPersonaFisica;
import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.framework.recurso.persistent.PersonaJuridica;
import com.trascender.gui.framework.abmStandard.AdminController;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Conversor;

public class AdminPersonaJuridica extends AdminController<PersonaJuridica> {
	
	private AdminPersonaJuridicaView view;
	private PersonaJuridicaBusquedaModel busquedaModel = new PersonaJuridicaBusquedaModel();
	private PersonaJuridicaTableModel tableModel = new PersonaJuridicaTableModel();
	
	
	public AdminPersonaJuridica(JFrame owner) throws Exception {
		this.view = new AdminPersonaJuridicaView(owner);
		this.init();
	}
	
	public AdminPersonaJuridica(JDialog owner) throws Exception {
		this.view = new AdminPersonaJuridicaView(owner);
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		this.setModels();
		this.setListeners();
		this.setCommonProperties();
	}
	
	private void setCommonProperties() {
		this.getView().getTfTitular().setEditable(false);
		
		this.getView().getPnlPie().getBtnAgregar().setEnabled(false);
		this.getView().getPnlPie().getBtnModificar().setEnabled(false);
		this.getView().getPnlPie().getBtnEliminar().setEnabled(false);
	}
	
	private void setModels() {
		this.getView().setBusquedaModel(this.getBusquedaModel());
		this.getView().setTableModel(this.getTableModel());
	}
	
	private void setListeners() {
		this.getView().getPnlBotonesBusqueda().getBtnBuscar().addActionListener(new BtnBuscarListener(this));
		
		this.getView().getPnlBotonesSeleccion().getBtnSeleccionar().addActionListener(new BtnSeleccionarTitularListener(this));
		this.getView().getPnlBotonesSeleccion().getBtnLimpiar().addActionListener(new BtnLimpiarTitularListener(this));
	}

	@Override
	protected void actualizarBusquedaModel() {
		PersonaJuridicaBusquedaModel locModel = this.getBusquedaModel();
		
		locModel.setCuim(Conversor.getNullSiVacio(this.getView().getTfCuit().getText()));
		locModel.setRazonSocial(Conversor.getNullSiVacio(this.getView().getTfRazonSocial().getText()));
		
		locModel.fireActualizarDatos();
	}

	@Override
	protected void actualizarBusquedaView() {
		this.getView().getTfCuit().setText(Conversor.getVacioSiNull(this.getBusquedaModel().getCuim()));
		this.getView().getTfRazonSocial().setText(Conversor.getVacioSiNull(this.getBusquedaModel().getRazonSocial()));
		this.getView().getTfTitular().setText(Conversor.getVacioSiNull(this.getBusquedaModel().getTitular()));
	}

	@Override
	protected PersonaJuridicaBusquedaModel getBusquedaModel() {
		return this.busquedaModel;
	}

	@Override
	protected PersonaJuridicaTableModel getTableModel() {
		return this.tableModel;
	}

	@Override
	protected AdminPersonaJuridicaView getView() {
		return this.view;
	}
	
	void buscar() throws Exception {
		final AdminPersonaJuridica controller = this;
		Thread locThread = new Thread(new Runnable() {

			public void run() {
				try {
					controller.actualizarBusquedaModel();
					controller.getView().iniBusqueda();
					List<PersonaJuridica> locLista = controller.getBusquedaModel().buscar();
					controller.getTableModel().setListaObjetos(locLista);
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

	void seleccionarTitular() throws Exception {
		AdminPersonaFisica adminPersonaFisica = new AdminPersonaFisica(this.getView());
		this.actualizarBusquedaModel();
		PersonaFisica locPersonaFisica = adminPersonaFisica.openSelect();
	
		if (locPersonaFisica != null) {
			this.getBusquedaModel().setTitular(locPersonaFisica);
			this.actualizarBusquedaView();
		}
	}
	
	void limpiarTitular() {
		this.getBusquedaModel().setTitular(null);
		this.actualizarBusquedaModel();
		this.actualizarBusquedaView();
	}
	
}

class BtnBuscarListener implements ActionListener {

	private AdminPersonaJuridica controller;
		
	public BtnBuscarListener(AdminPersonaJuridica controller) {
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

class BtnSeleccionarTitularListener implements ActionListener {

	private AdminPersonaJuridica controller;
	
	public BtnSeleccionarTitularListener(AdminPersonaJuridica controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.seleccionarTitular();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnLimpiarTitularListener implements ActionListener {

	private AdminPersonaJuridica controller;
	
	public BtnLimpiarTitularListener(AdminPersonaJuridica controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.limpiarTitular();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}