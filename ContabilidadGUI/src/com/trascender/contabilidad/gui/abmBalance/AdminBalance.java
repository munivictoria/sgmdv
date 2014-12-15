package com.trascender.contabilidad.gui.abmBalance;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.contabilidad.gui.abmBalance.abm.AgregarBalance;
import com.trascender.contabilidad.gui.abmBalance.abm.ConsultarBalance;
import com.trascender.contabilidad.gui.abmBalance.abm.EliminarBalance;
import com.trascender.contabilidad.gui.abmTipoBalance.AdminTipoBalance;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.Balance;
import com.trascender.contabilidad.recurso.persistent.TipoBalance;
import com.trascender.gui.framework.abmStandard.AdminController;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Conversor;

public class AdminBalance extends AdminController<Balance> {
	
	private AdminBalanceView view;
	private BalanceBusquedaModel busquedaModel = new BalanceBusquedaModel();
	private BalanceTableModel tableModel = new BalanceTableModel();
	
	public AdminBalance(JFrame owner) throws Exception {
		this.view = new AdminBalanceView(owner);
		this.init();
	}
	
	public AdminBalance(JDialog owner) throws Exception {
		this.view = new AdminBalanceView(owner);
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		
		this.setModels();
		this.setListeners();
		this.setBtnConsultarVisible();
		
		this.getView().getPnlPie().getBtnModificar().setEnabled(false);
		//this.getView().getPnlPie().getBtnEliminar().setEnabled(false);
	}
	
	private void setBtnConsultarVisible() {
		this.view.getPnlPie().getBtnConsultar().setVisible(true);
	}
	
	private void setModels() {
		this.getView().setBusquedaModel(this.getBusquedaModel());
		this.getView().setTableModel(this.getTableModel());
		this.getView().getPnlTabla().getTblDatos().setModel(this.getTableModel());
	}
	
	private void setListeners() {
		this.getView().getPnlBotonesSeleccionTipoBalance().getBtnSeleccionar().addActionListener(new BtnSeleccionarTipoBalanceListener(this));
		this.getView().getPnlBotonesSeleccionTipoBalance().getBtnLimpiar().addActionListener(new BtnLimpiarTipoBalanceListener(this));
		
		this.getView().getPnlBotonesBusqueda().getBtnBuscar().addActionListener(new BtnBuscarListener(this));
		
		this.view.getPnlPie().getBtnConsultar().addActionListener(new BtnConsultarListener(this));
		this.getView().getPnlPie().getBtnAgregar().addActionListener(new BtnAgregarListener(this));
		this.getView().getPnlPie().getBtnEliminar().addActionListener(new BtnEliminarListener(this));
	}
	
	@Override
	protected void actualizarBusquedaModel() {
		BalanceBusquedaModel locModel = this.getBusquedaModel();
		locModel.setNombre(Conversor.getNullSiVacio(this.getView().getTfNombre().getText()));
		locModel.setFechaDesde(Conversor.getDate(this.getView().getFtfFechaDesde().getText()));
		locModel.setFechaHasta(Conversor.getDate(this.getView().getFtfFechaHasta().getText()));
		locModel.fireActualizarDatos();
	}

	@Override
	protected void actualizarBusquedaView() {
		this.getView().getTfNombre().setText(Conversor.getVacioSiNull(this.getBusquedaModel().getNombre()));
		this.getView().getFtfFechaDesde().setValue(Conversor.getString(this.getBusquedaModel().getFechaDesde()));
		this.getView().getFtfFechaHasta().setValue(Conversor.getString(this.getBusquedaModel().getFechaHasta()));
		this.getView().getTfTipoBalance().setText(Conversor.getVacioSiNull(this.getBusquedaModel().getTipoBalance()));
	}

	@Override
	protected BalanceBusquedaModel getBusquedaModel() {
		return this.busquedaModel;
	}

	@Override
	protected BalanceTableModel getTableModel() {
		return this.tableModel;
	}

	@Override
	protected AdminBalanceView getView() {
		return this.view;
	}
	
	void buscar() {
		final AdminBalance controller = this;
		Thread locThread = new Thread(new Runnable(){
			public void run() {
				try {
					controller.actualizarBusquedaModel();
					controller.getView().iniBusqueda();
					List<Balance> locLista = controller.getBusquedaModel().buscar();
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
	
	void openConsultarBalance() throws Exception {
		Balance locBalance = this.getSelectedRow();
		if (locBalance != null) {
			Balance balance = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().getBalanceByID(locBalance.getIdBalance());
			ConsultarBalance consultarBalance = new ConsultarBalance(this.getView(), balance);
			
			consultarBalance.instanciarTableModel();
			consultarBalance.getAbmModel().setObjetoABM(balance);
			consultarBalance.actualizarView();
			consultarBalance.open();
		}
	}
	
	void openAgregarBalance() throws Exception {
		AgregarBalance agregarBalance = new AgregarBalance(this.getView());
		agregarBalance.open();
		if (agregarBalance.isOperacionRealizada()) {
			this.buscar();
		}
	}
	
	void openModificarBalance() throws Exception {
		
	}
	
	void openEliminarBalance() throws Exception {
		Balance locBalance = this.getSelectedRow();
		if (locBalance != null) {
			locBalance = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().getBalanceByID(locBalance.getIdBalance());
			EliminarBalance locEliminarBalance = new EliminarBalance(this.getView());
			locEliminarBalance.getAbmModel().setObjetoABM(locBalance);
			locEliminarBalance.actualizarView();
			locEliminarBalance.open();
			if (locEliminarBalance.isOperacionRealizada()) {
				this.getTableModel().deleteRow(locBalance);
			}
		}
	}
	
	void seleccionarTipoBalance() throws Exception {
		AdminTipoBalance adminTipoBalance = new AdminTipoBalance(this.getView());
		TipoBalance locTipoBalance = adminTipoBalance.openSelect();
		if (locTipoBalance != null) {
			this.getBusquedaModel().setTipoBalance(locTipoBalance);
			this.actualizarBusquedaModel();
			this.actualizarBusquedaView();
		}
	}
	
	void limpiarTipoBalance() throws Exception {
		this.getBusquedaModel().setTipoBalance(null);
		this.actualizarBusquedaModel();
		this.actualizarBusquedaView();
	}
	
}


class BtnBuscarListener implements ActionListener {
	private AdminBalance controller;
	public BtnBuscarListener(AdminBalance controller) {
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

class BtnSeleccionarTipoBalanceListener implements ActionListener {
	private AdminBalance controller;
	public BtnSeleccionarTipoBalanceListener(AdminBalance controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.seleccionarTipoBalance();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnLimpiarTipoBalanceListener implements ActionListener {
	private AdminBalance controller;
	public BtnLimpiarTipoBalanceListener(AdminBalance controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.limpiarTipoBalance();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnConsultarListener implements ActionListener {
	private AdminBalance controller;
	
	public BtnConsultarListener(AdminBalance controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openConsultarBalance();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}

class BtnAgregarListener implements ActionListener{
	
	private AdminBalance controller;

	public BtnAgregarListener(AdminBalance controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openAgregarBalance();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnEliminarListener implements ActionListener {
	
	private AdminBalance controller;

	public BtnEliminarListener(AdminBalance controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openEliminarBalance();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}
