package com.trascender.contabilidad.gui.abmDocumentoRefinanciacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.contabilidad.gui.abmPersonaFisica.AdminPersonaFisica;
import com.trascender.contabilidad.gui.abmPersonaJuridica.AdminPersonaJuridica;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.framework.recurso.persistent.PersonaJuridica;
import com.trascender.gui.framework.abmStandard.AdminController;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Conversor;
import com.trascender.saic.recurso.persistent.refinanciacion.DocumentoRefinanciacion;

public class AdminDocumentoRefinanciacion extends AdminController<DocumentoRefinanciacion>{

	private AdminDocumentoRefinanciacionView view;
	private DocumentoRefinanciacionBusquedaModel busquedaModel = new DocumentoRefinanciacionBusquedaModel();
	private DocumentoRefinanciacionTableModel tableModel = new DocumentoRefinanciacionTableModel();
	
	public AdminDocumentoRefinanciacion(JFrame owner) throws Exception {
		this.view = new AdminDocumentoRefinanciacionView(owner);
		this.init();
	}
	
	public AdminDocumentoRefinanciacion(JDialog owner) throws Exception {
		this.view = new AdminDocumentoRefinanciacionView(owner);
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		this.setModels();
		this.setListeners();
		
		this.getView().getPnlPie().getBtnAgregar().setEnabled(false);
		this.getView().getPnlPie().getBtnModificar().setEnabled(false);
		this.getView().getPnlPie().getBtnEliminar().setEnabled(false);
	}
	
	private void setModels() {
		this.view.setBusquedaModel(this.busquedaModel);
		this.view.getPnlTabla().getTblDatos().setModel(this.tableModel);
	}

	private void setListeners() {
		this.getView().getPnlBotonesBusqueda().getBtnBuscar().addActionListener(new BtnBuscarListener(this));
		
//		this.getView().getPnlPie().getBtnAgregar().addActionListener(new BtnAgregarListener(this));
//		this.getView().getPnlPie().getBtnModificar().addActionListener(new BtnModificarListener(this));
//		this.getView().getPnlPie().getBtnEliminar().addActionListener(new BtnEliminarListener(this));

		this.getView().getPnlBotonesSeleccionPersona().getBtnSeleccionarPersonaFisica().addActionListener(new BtnAgregarPersonaFisicaListener(this));
		this.getView().getPnlBotonesSeleccionPersona().getBtnSeleccionarPersonaJuridica().addActionListener(new BtnAgregarPersonaJuridicaListener(this));
		this.getView().getPnlBotonesSeleccionPersona().getBtnLimpiar().addActionListener(new BtnLimpiarPersonaListener(this));
	}
	
	@Override
	protected void actualizarBusquedaModel() {
		DocumentoRefinanciacionBusquedaModel locModel = this.getBusquedaModel();

		locModel.setNumeroRefinanciacion(Conversor.getInteger(this.getView().getTfNumeroRefinanciacion().getText()));

		locModel.fireActualizarDatos();
	}

	@Override
	protected void actualizarBusquedaView() {
		this.getView().getTfNumeroRefinanciacion().setText(Conversor.getVacioSiNull(this.getBusquedaModel().getNumeroRefinanciacion()));
		this.getView().getTfContribuyente().setText(Conversor.getVacioSiNull(this.getBusquedaModel().getPersona()));
	}

	@Override
	protected DocumentoRefinanciacionBusquedaModel getBusquedaModel() {
		return this.busquedaModel;
	}

	@Override
	protected DocumentoRefinanciacionTableModel getTableModel() {
		return this.tableModel;
	}

	@Override
	protected AdminDocumentoRefinanciacionView getView() {
		return this.view;
	}
	
	void seleccionarPersonaFisica() throws Exception { 
		AdminPersonaFisica adminPersonaFisica = new AdminPersonaFisica(this.getView());
		PersonaFisica locPersonaFisica= adminPersonaFisica.openSelect();
		
		if (locPersonaFisica != null) {
			PersonaFisica personaFisica= AppManager.getInstance().getAdminSystems().getSystemPersonaFisica().getPersonaFisicaPorId(locPersonaFisica.getIdPersona());
			this.getBusquedaModel().setPersona(personaFisica);
			this.actualizarBusquedaModel();
			this.actualizarBusquedaView();
		}
	}
	
	void seleccionarPersonaJuridica() throws Exception { 
		AdminPersonaJuridica adminPersonaJuridica = new AdminPersonaJuridica(this.getView());
		PersonaJuridica locPersonaJuridica= adminPersonaJuridica.openSelect();
		
		if (locPersonaJuridica != null) {
			PersonaJuridica personaJuridica = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemPersonaJuridica().getPersonaJuridicaPorId(locPersonaJuridica.getIdPersonaJuridica());
			this.getBusquedaModel().setPersona(personaJuridica);
			this.actualizarBusquedaModel();
			this.actualizarBusquedaView();
		}
	}
	
	void limpiarPersona() {
		this.getBusquedaModel().setPersona(null);
		this.actualizarBusquedaModel();
		this.actualizarBusquedaView();
	}
	
	void buscar() {
		final AdminDocumentoRefinanciacion controller = this;
		Thread locThread = new Thread(new Runnable() {

			public void run()  {
				try {
					controller.actualizarBusquedaModel();
					controller.getView().iniBusqueda();
					List<DocumentoRefinanciacion> locLista = controller.getBusquedaModel().buscar();
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

}

class BtnAgregarPersonaFisicaListener implements ActionListener {

	private AdminDocumentoRefinanciacion controller;
	
	public BtnAgregarPersonaFisicaListener(AdminDocumentoRefinanciacion controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.seleccionarPersonaFisica();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}

class BtnAgregarPersonaJuridicaListener implements ActionListener {

	private AdminDocumentoRefinanciacion controller;
	
	public BtnAgregarPersonaJuridicaListener(AdminDocumentoRefinanciacion controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.seleccionarPersonaJuridica();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}

class BtnLimpiarPersonaListener implements ActionListener {

	private AdminDocumentoRefinanciacion controller;
	
	public BtnLimpiarPersonaListener(AdminDocumentoRefinanciacion controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.limpiarPersona();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}

class BtnBuscarListener implements ActionListener {

	private AdminDocumentoRefinanciacion controller;

	public BtnBuscarListener(AdminDocumentoRefinanciacion controller) {
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

//class BtnAgregarListener implements ActionListener {
//
//	private AdminDocumentoRefinanciacion controller;
//
//	public BtnAgregarListener(AdminDocumentoRefinanciacion controller) {
//		this.controller = controller;
//	}
//
//	public void actionPerformed(ActionEvent e) {
//		try {
//			this.controller.openAgregarCuentaBancaria();
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
//		}
//	}
//
//}
//
//class BtnModificarListener implements ActionListener {
//
//	private AdminDocumentoRefinanciacion controller;
//
//	public BtnModificarListener(AdminDocumentoRefinanciacion controller) {
//		this.controller = controller;
//	}
//
//	public void actionPerformed(ActionEvent e) {
//		try {
//			this.controller.openModificarCuentaBancaria();
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
//		}
//	}
//}
//
//class BtnEliminarListener implements ActionListener {
//
//	private AdminDocumentoRefinanciacion controller;
//
//	public BtnEliminarListener(AdminDocumentoRefinanciacion controller) {
//		this.controller = controller;
//	}
//
//	public void actionPerformed(ActionEvent e) {
//		try {
//			this.controller.openEliminarCuentaBancaria();
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
//		}
//	}
//}

