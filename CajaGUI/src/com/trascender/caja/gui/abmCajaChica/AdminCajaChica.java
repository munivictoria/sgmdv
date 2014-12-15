package com.trascender.caja.gui.abmCajaChica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.caja.gui.abmCajaChica.abm.AgregarCajaChica;
import com.trascender.caja.gui.abmCajaChica.abm.EliminarCajaChica;
import com.trascender.caja.gui.abmCajaChica.abm.ModificarCajaChica;
import com.trascender.caja.gui.main.CajaGUI;
import com.trascender.contabilidad.recurso.persistent.CajaChica;
import com.trascender.gui.framework.abmStandard.AdminController;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Conversor;

public class AdminCajaChica extends AdminController<CajaChica> {

	private AdminCajaChicaView view;
	private CajaChicaTableModel tableModel = new CajaChicaTableModel();
	private CajaChicaBusquedaModel busquedaModel = new CajaChicaBusquedaModel();
	
	public AdminCajaChica(JDialog owner) throws Exception {
		this.view = new AdminCajaChicaView(owner);
		this.init();
	}
	
	public AdminCajaChica(JFrame owner) throws Exception {
		this.view = new AdminCajaChicaView(owner);
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
		CajaChicaBusquedaModel locModel = this.getView().getBusquedaModel();
		
		locModel.setNombre(Conversor.getNullSiVacio(this.getView().getTfNombre().getText().trim()));
		
		locModel.fireActualizarDatos();
	}

	@Override
	protected void actualizarBusquedaView() {
		this.getView().getTfNombre().setText(this.getBusquedaModel().getNombre());
	}

	@Override
	protected CajaChicaBusquedaModel getBusquedaModel() {
		return busquedaModel;
	}

	@Override
	protected CajaChicaTableModel getTableModel() {
		return tableModel;
	}

	@Override
	protected AdminCajaChicaView getView() {
		return view;
	}
	
	void buscar() {
		final AdminCajaChica controller = this;
		Thread locThread = new Thread(new Runnable() {
			public void run() {
				try {
					controller.actualizarBusquedaModel();
					controller.getView().iniBusqueda();
					List<CajaChica> locLista = controller.getBusquedaModel().buscar();
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
	
	protected void openAgregarCajaChica() throws Exception {
		AgregarCajaChica agregarCajaChica = new AgregarCajaChica(this.getView());
		agregarCajaChica.open();
		if (agregarCajaChica.isOperacionRealizada()) {
			this.buscar();
		}
	}
	
	protected void openModificarCajaChica() throws Exception {
		CajaChica locCajaChica = this.getSelectedRow();
		if (locCajaChica != null) {
			locCajaChica = CajaGUI.getInstance().getAdminSystemsCaja().getSystemAdministracionCajaChica().findCajaChicaByID(locCajaChica.getIdCajaChica());
			ModificarCajaChica locModificarCajaChica = new ModificarCajaChica(this.getView());
			locModificarCajaChica.getAbmModel().setObjetoABM(locCajaChica);
			locModificarCajaChica.actualizarView();
			locModificarCajaChica.open();
			if (locModificarCajaChica.isOperacionRealizada()) {
				this.getTableModel().updateRow(locCajaChica);
			}
		}
	}
	
	protected void openEliminarCajaChica() throws Exception {
		CajaChica locCajaChica = this.getSelectedRow();
		if (locCajaChica != null) {
			locCajaChica = CajaGUI.getInstance().getAdminSystemsCaja().getSystemAdministracionCajaChica().findCajaChicaByID(locCajaChica.getIdCajaChica());
			EliminarCajaChica locEliminarCajaChica = new EliminarCajaChica(this.getView());
			locEliminarCajaChica.getAbmModel().setObjetoABM(locCajaChica);
			locEliminarCajaChica.actualizarView();
			locEliminarCajaChica.open();
			if (locEliminarCajaChica.isOperacionRealizada()) {
				this.getTableModel().deleteRow(locCajaChica);
			}
		}
	}
}

class BtnBuscarListener implements ActionListener {
	private AdminCajaChica controller;
	public BtnBuscarListener(AdminCajaChica controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
			this.controller.buscar();
	}
}

class BtnAgregarListener implements ActionListener {
	private AdminCajaChica controller;
	public BtnAgregarListener(AdminCajaChica controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openAgregarCajaChica();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnModificarListener implements ActionListener {
	private AdminCajaChica controller;
	public BtnModificarListener(AdminCajaChica controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openModificarCajaChica();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnEliminarListener implements ActionListener {
	private AdminCajaChica controller;
	public BtnEliminarListener(AdminCajaChica controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openEliminarCajaChica();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}