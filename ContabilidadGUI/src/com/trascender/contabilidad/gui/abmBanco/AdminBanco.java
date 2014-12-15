package com.trascender.contabilidad.gui.abmBanco;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.contabilidad.gui.abmBanco.abm.AgregarBanco;
import com.trascender.contabilidad.gui.abmBanco.abm.ConsultarBanco;
import com.trascender.contabilidad.gui.abmBanco.abm.EliminarBanco;
import com.trascender.contabilidad.gui.abmBanco.abm.ModificarBanco;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.Banco;
import com.trascender.gui.framework.abmStandard.AdminController;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Conversor;

public class AdminBanco extends AdminController<Banco> {

	private AdminBancoView view;
	private BancoBusquedaModel busquedaModel = new BancoBusquedaModel();
	private BancoTableModel tableModel = new BancoTableModel();
	
	public AdminBanco(JDialog owner) throws Exception{
		this.view = new AdminBancoView(owner);
		this.init();
	}
	
	public AdminBanco(JFrame owner) throws Exception{
		this.view = new AdminBancoView(owner);
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		this.setModels();
		this.setListeners();
	}
	
	private void setModels() {
		this.getView().setTableModel(this.getTableModel());
		this.getView().setBusquedaModel(this.getBusquedaModel());
	}
	
	private void setListeners() {
		this.getView().getPnlBotonesBusqueda().getBtnBuscar().addActionListener(new BtnBuscarListener(this));
		
		this.getView().getPnlPie().getBtnAgregar().addActionListener(new BtnAgregarListener(this));
		this.getView().getPnlPie().getBtnModificar().addActionListener(new BtnModificarListener(this));
		this.getView().getPnlPie().getBtnEliminar().addActionListener(new BtnEliminarListener(this));
		this.getView().getPnlPie().getBtnConsultar().addActionListener(new BtnConsultarListener(this));
	}
	
	@Override
	protected void actualizarBusquedaModel() {
		BancoBusquedaModel locModel = this.getView().getBusquedaModel();
		
		locModel.setNombre(Conversor.getNullSiVacio(this.getView().getTfNombre().getText()));
		locModel.setSucursal(Conversor.getNullSiVacio(this.getView().getTfSucursal().getText()));
		
		locModel.setNombre(this.getView().getTfNombre().getText());
		locModel.setSucursal(this.getView().getTfSucursal().getText());
		
		locModel.fireActualizarDatos();
	}

	@Override
	protected void actualizarBusquedaView() {
		this.getView().getTfNombre().setText(Conversor.getVacioSiNull(this.getBusquedaModel().getNombre()));
		this.getView().getTfSucursal().setText(Conversor.getVacioSiNull(this.getBusquedaModel().getSucursal()));
	}

	@Override
	protected BancoBusquedaModel getBusquedaModel() {
		return this.busquedaModel;
	}

	@Override
	protected BancoTableModel getTableModel() {
		return this.tableModel;
	}

	@Override
	protected AdminBancoView getView() {
		return this.view;
	}

	void buscar() throws Exception {
		final AdminBanco controller = this;
		Thread locThread = new Thread(new Runnable(){
			public void run() {
				try {
					controller.actualizarBusquedaModel();
					controller.getView().iniBusqueda();
					List<Banco> locLista = controller.getBusquedaModel().buscar();
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
	
	protected void openAgregarBanco() throws Exception {
		AgregarBanco agregarBanco = new AgregarBanco(this.getView());
		agregarBanco.open();
		if (agregarBanco.isOperacionRealizada()) {
			this.getTableModel().addRow(agregarBanco.getAbmModel().getObjetoABM());
		}
	}

	protected void openModificarBanco() throws Exception {
		Banco locBanco = this.getSelectedRow();
		if (locBanco != null) {
			locBanco = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConciliacionBancaria().getBancoByID(locBanco.getIdBanco());
			ModificarBanco locModificarBanco= new ModificarBanco(this.getView());
			locModificarBanco.getAbmModel().setObjetoABM(locBanco);
			locModificarBanco.actualizarView();
			locModificarBanco.open();
			if (locModificarBanco.isOperacionRealizada()) {
				this.getTableModel().updateRow(locBanco);
			}
		}
	}
	
	protected void openEliminarBanco() throws Exception {
		Banco locBanco = this.getSelectedRow();
		if (locBanco != null) {
			locBanco = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConciliacionBancaria().getBancoByID(locBanco.getIdBanco());
			EliminarBanco locEliminarBanco = new EliminarBanco(this.getView());
			locEliminarBanco.getAbmModel().setObjetoABM(locBanco);
			locEliminarBanco.actualizarView();
			locEliminarBanco.open();
			if (locEliminarBanco.isOperacionRealizada()) {
				this.getTableModel().deleteRow(locBanco);
			}
		}
	}
	
	protected void openConsultarBanco() throws Exception {
		Banco locBanco = this.getSelectedRow();
		if (locBanco != null) {
			locBanco = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConciliacionBancaria().getBancoByID(locBanco.getIdBanco());
			ConsultarBanco locConsultarBanco = new ConsultarBanco(this.getView());
			locConsultarBanco.getAbmModel().setObjetoABM(locBanco);
			locConsultarBanco.actualizarView();
			locConsultarBanco.open();
			if (locConsultarBanco.isOperacionRealizada()) {
				this.getTableModel().deleteRow(locBanco);
			}
		}
	}

}


class BtnBuscarListener implements ActionListener {
	private AdminBanco controller;
	public BtnBuscarListener(AdminBanco controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.buscar();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnAgregarListener implements ActionListener {

	private AdminBanco controller;
	
	public BtnAgregarListener(AdminBanco controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openAgregarBanco();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}

class BtnModificarListener implements ActionListener {

	private AdminBanco controller;
	
	public BtnModificarListener(AdminBanco controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openModificarBanco();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}

class BtnEliminarListener implements ActionListener {

	private AdminBanco controller;
	
	public BtnEliminarListener(AdminBanco controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openEliminarBanco();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}

class BtnConsultarListener implements ActionListener {

	private AdminBanco controller;
	
	public BtnConsultarListener(AdminBanco controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openConsultarBanco();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}
