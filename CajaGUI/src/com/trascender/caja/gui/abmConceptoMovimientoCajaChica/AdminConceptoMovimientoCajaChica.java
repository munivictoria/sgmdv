package com.trascender.caja.gui.abmConceptoMovimientoCajaChica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.caja.gui.abmConceptoMovimientoCajaChica.abm.AgregarConceptoMovimientoCajaChica;
import com.trascender.caja.gui.abmConceptoMovimientoCajaChica.abm.EliminarConceptoMovimientoCajaChica;
import com.trascender.caja.gui.abmConceptoMovimientoCajaChica.abm.ModificarConceptoMovimientoCajaChica;
import com.trascender.caja.gui.main.CajaGUI;
import com.trascender.contabilidad.recurso.persistent.ConceptoMovimientoCajaChica;
import com.trascender.gui.framework.abmStandard.AdminController;
import com.trascender.gui.framework.main.AppManager;

public class AdminConceptoMovimientoCajaChica extends AdminController<ConceptoMovimientoCajaChica> {
	
	private AdminConceptoMovimientoCajaChicaView view;
	private ConceptoMoviminetoCajaChicaBusquedaModel busquedaModel = new ConceptoMoviminetoCajaChicaBusquedaModel();
	private ConceptoMovimientoCajaChicaTableModel tableModel = new ConceptoMovimientoCajaChicaTableModel();
	
	public AdminConceptoMovimientoCajaChica(JFrame owner) throws Exception {
		this.view = new AdminConceptoMovimientoCajaChicaView(owner);
		this.init();
	}

	public AdminConceptoMovimientoCajaChica(JDialog owner) throws Exception {
		this.view = new AdminConceptoMovimientoCajaChicaView(owner);
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		this.setModels();
		this.setListeners();
	}
	
	private void setModels() {
		this.getView().setBusquedaModel(this.getBusquedaModel());
		this.getView().setTableModel(this.getTableModel());
	}
	
	private void setListeners() {
		this.getView().getPnlBotonesBusqueda().getBtnBuscar().addActionListener(new BtnBuscarListener(this));
		this.getView().getPnlPie().getBtnAgregar().addActionListener(new BtnAgregarListener(this));
		this.getView().getPnlPie().getBtnModificar().addActionListener(new BtnModificarListener(this));
		this.getView().getPnlPie().getBtnEliminar().addActionListener(new BtnEliminarListener(this));
	}
	
	@Override
	protected void actualizarBusquedaModel() {
		ConceptoMoviminetoCajaChicaBusquedaModel locModel = this.getView().getBusquedaModel();
		
		locModel.setNombre(this.getView().getTfNombre().getText());
		
		locModel.fireActualizarDatos();
	}

	@Override
	protected void actualizarBusquedaView() {
		this.getView().getTfNombre().setText(this.getBusquedaModel().getNombre());
	}

	@Override
	protected ConceptoMoviminetoCajaChicaBusquedaModel getBusquedaModel() {
		return busquedaModel;
	}

	@Override
	protected ConceptoMovimientoCajaChicaTableModel getTableModel() {
		return tableModel;
	}

	@Override
	protected AdminConceptoMovimientoCajaChicaView getView() {
		return view;
	}

	void buscar() {
		final AdminConceptoMovimientoCajaChica controller = this;
		Thread locThread = new Thread(new Runnable(){
			public void run() {
				try {
					controller.actualizarBusquedaModel();
					controller.getView().iniBusqueda();
					List<ConceptoMovimientoCajaChica> locLista = controller.getBusquedaModel().buscar();
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
	
	protected void openAgregarConceptoMovimientoCajaChica() throws Exception {
		AgregarConceptoMovimientoCajaChica agregarConceptoMovimientoCajaChica = new AgregarConceptoMovimientoCajaChica(this.getView());
		agregarConceptoMovimientoCajaChica.open();
		if (agregarConceptoMovimientoCajaChica.isOperacionRealizada()) {
			this.buscar();
		}
	}
	
	protected void openModificarConceptoMovimientoCajaChica() throws Exception {
		ConceptoMovimientoCajaChica locConceptoMovimientoCajaChica = this.getSelectedRow();
		if (locConceptoMovimientoCajaChica != null) {
			locConceptoMovimientoCajaChica = CajaGUI.getInstance().getAdminSystemsCaja().getSystemAdministracionCajaChica().findConceptoMovimientoCajaChicaByID(locConceptoMovimientoCajaChica.getIdConceptoMovimientoCajaChica());
			ModificarConceptoMovimientoCajaChica locModificarConceptoMovimientoCajaChica = new ModificarConceptoMovimientoCajaChica(this.getView());
			locModificarConceptoMovimientoCajaChica.getAbmModel().setObjetoABM(locConceptoMovimientoCajaChica);
			locModificarConceptoMovimientoCajaChica.actualizarView();
			locModificarConceptoMovimientoCajaChica.open();
			if (locModificarConceptoMovimientoCajaChica.isOperacionRealizada()) {
				this.getTableModel().updateRow(locConceptoMovimientoCajaChica);
			}
		}
	}
	
	protected void openEliminarConceptoMovimientoCajaChica() throws Exception {
		ConceptoMovimientoCajaChica locConceptoMovimientoCajaChica = this.getSelectedRow();
		if (locConceptoMovimientoCajaChica != null) {
			locConceptoMovimientoCajaChica = CajaGUI.getInstance().getAdminSystemsCaja().getSystemAdministracionCajaChica().findConceptoMovimientoCajaChicaByID(locConceptoMovimientoCajaChica.getIdConceptoMovimientoCajaChica());
			EliminarConceptoMovimientoCajaChica locEliminarConceptoMovimientoCajaChica = new EliminarConceptoMovimientoCajaChica(this.getView());
			locEliminarConceptoMovimientoCajaChica.getAbmModel().setObjetoABM(locConceptoMovimientoCajaChica);
			locEliminarConceptoMovimientoCajaChica.actualizarView();
			locEliminarConceptoMovimientoCajaChica.open();
			if (locEliminarConceptoMovimientoCajaChica.isOperacionRealizada()) {
				this.getTableModel().deleteRow(locConceptoMovimientoCajaChica);
			}
		}
	}
}

class BtnBuscarListener implements ActionListener {
	private AdminConceptoMovimientoCajaChica controller;
	public BtnBuscarListener(AdminConceptoMovimientoCajaChica controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		this.controller.buscar();
	}
}

class BtnAgregarListener implements ActionListener {
	private AdminConceptoMovimientoCajaChica controller;
	public BtnAgregarListener(AdminConceptoMovimientoCajaChica controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openAgregarConceptoMovimientoCajaChica();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnModificarListener implements ActionListener {
	private AdminConceptoMovimientoCajaChica controller;
	public BtnModificarListener(AdminConceptoMovimientoCajaChica controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openModificarConceptoMovimientoCajaChica();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnEliminarListener implements ActionListener {
	private AdminConceptoMovimientoCajaChica controller;
	public BtnEliminarListener(AdminConceptoMovimientoCajaChica controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openEliminarConceptoMovimientoCajaChica();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}
