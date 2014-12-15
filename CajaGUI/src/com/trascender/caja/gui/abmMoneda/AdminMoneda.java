package com.trascender.caja.gui.abmMoneda;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.caja.gui.abmMoneda.abm.AgregarMoneda;
import com.trascender.caja.gui.abmMoneda.abm.EliminarMoneda;
import com.trascender.caja.gui.abmMoneda.abm.ModificarMoneda;
import com.trascender.caja.gui.main.CajaGUI;
import com.trascender.contabilidad.recurso.persistent.Moneda;
import com.trascender.gui.framework.abmStandard.AdminController;
import com.trascender.gui.framework.main.AppManager;

public class AdminMoneda extends AdminController<Moneda> {
	
	private AdminMonedaView view;
	private MonedaBusquedaModel busquedaModel = new MonedaBusquedaModel();
	private MonedaTableModel tableModel = new MonedaTableModel();
	
	public AdminMoneda(JFrame owner) throws Exception {
		this.view = new AdminMonedaView(owner);
		this.init();
	}
	
	public AdminMoneda(JDialog owner) throws Exception {
		this.view = new AdminMonedaView(owner);
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
		MonedaBusquedaModel locModel = this.getView().getBusquedaModel();
		
		locModel.setNombre(this.getView().getTfNombre().getText());
		
		locModel.fireActualizarDatos();
	}

	@Override
	protected void actualizarBusquedaView() {
		this.getView().getTfNombre().setText(this.getBusquedaModel().getNombre());	
	}

	@Override
	protected MonedaBusquedaModel getBusquedaModel() {
		return busquedaModel;
	}

	@Override
	protected MonedaTableModel getTableModel() {
		return tableModel;
	}

	@Override
	protected AdminMonedaView getView() {
		return view;
	}

	void buscar() {
		final AdminMoneda controller = this;
		Thread locThread = new Thread(new Runnable(){
			public void run() {
				try {
					controller.actualizarBusquedaModel();
					controller.getView().iniBusqueda();
					List<Moneda> locLista = controller.getBusquedaModel().buscar();
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
	
	void openAgregarMoneda() throws Exception {
		AgregarMoneda agregarMoneda = new AgregarMoneda(this.getView());
		agregarMoneda.open();
		if (agregarMoneda.isOperacionRealizada()) {
			this.buscar();
		}
	}
	
	void openModificarMoneda() throws Exception {
		Moneda locMoneda = this.getSelectedRow();
		if (locMoneda != null) {
			locMoneda = CajaGUI.getInstance().getAdminSystemsCaja().getSystemAdministracionPlanillaDiaria().findMonedaByID(locMoneda.getIdTipoMoneda());
			ModificarMoneda locModificarMoneda = new ModificarMoneda(this.getView());
			locModificarMoneda.getAbmModel().setObjetoABM(locMoneda);
			locModificarMoneda.actualizarView();
			locModificarMoneda.open();
			if (locModificarMoneda.isOperacionRealizada()) {
				this.getTableModel().updateRow(locMoneda);
			}
		}
	}
	
	void openEliminarMoneda() throws Exception {
		Moneda locMoneda = this.getSelectedRow();
		if (locMoneda != null) {
			locMoneda = CajaGUI.getInstance().getAdminSystemsCaja().getSystemAdministracionPlanillaDiaria().findMonedaByID(locMoneda.getIdTipoMoneda());
			EliminarMoneda locEliminarMoneda = new EliminarMoneda(this.getView());
			locEliminarMoneda.getAbmModel().setObjetoABM(locMoneda);
			locEliminarMoneda.actualizarView();
			locEliminarMoneda.open();
			if (locEliminarMoneda.isOperacionRealizada()) {
				this.getTableModel().deleteRow(locMoneda);
			}
		}
	}
	
}

class BtnBuscarListener implements ActionListener {
	private AdminMoneda controller;
	public BtnBuscarListener(AdminMoneda controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		this.controller.buscar();
	}
}

class BtnAgregarListener implements ActionListener {
	private AdminMoneda controller;
	public BtnAgregarListener(AdminMoneda controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openAgregarMoneda();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnModificarListener implements ActionListener {
	private AdminMoneda controller;
	public BtnModificarListener(AdminMoneda controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openModificarMoneda();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnEliminarListener implements ActionListener {
	private AdminMoneda controller;
	public BtnEliminarListener(AdminMoneda controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openEliminarMoneda();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}