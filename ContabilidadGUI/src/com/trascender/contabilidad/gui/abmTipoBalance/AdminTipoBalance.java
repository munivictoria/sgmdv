package com.trascender.contabilidad.gui.abmTipoBalance;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Set;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.contabilidad.gui.abmTipoBalance.abm.AgregarTipoBalance;
import com.trascender.contabilidad.gui.abmTipoBalance.abm.ConsultarTipoBalance;
import com.trascender.contabilidad.gui.abmTipoBalance.abm.EliminarTipoBalance;
import com.trascender.contabilidad.gui.abmTipoBalance.abm.ModificarTipoBalance;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.contabilidad.recurso.persistent.TipoBalance;
import com.trascender.gui.framework.abmStandard.AdminController;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Conversor;

public class AdminTipoBalance extends AdminController<TipoBalance> {

	private AdminTipoBalanceView view;
	private TipoBalanceBusquedaModel busquedaModel = new TipoBalanceBusquedaModel();
	private TipoBalanceTableModel tableModel = new TipoBalanceTableModel();
	
	public AdminTipoBalance(JDialog owner) throws Exception {
		this.view = new AdminTipoBalanceView(owner);
		this.init();
	}
	
	public AdminTipoBalance(JFrame owner) throws Exception {
		this.view = new AdminTipoBalanceView(owner);
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		this.setModels();
		this.setListeners();
	}
	
	private void setModels(){
		this.getView().setBusquedaModel(this.getBusquedaModel());
		this.getView().setTableModel(this.getTableModel());
		this.getView().getPnlTabla().getTblDatos().setModel(this.getTableModel());
	}
	
	private void setListeners(){
		this.view.getPnlBotonesBusqueda().getBtnBuscar().addActionListener(new BtnBuscarListener(this));
		
		this.view.getPnlPie().getBtnAgregar().addActionListener(new BtnAgregarListener(this));
		this.view.getPnlPie().getBtnModificar().addActionListener(new BtnModificarListener(this, view));
		this.view.getPnlPie().getBtnEliminar().addActionListener(new BtnEliminarListener(this));
		this.view.getPnlPie().getBtnConsultar().addActionListener(new BtnConsultarListener(this));
	}
	
	@Override
	protected void actualizarBusquedaModel() {
		TipoBalanceBusquedaModel locModel = this.getView().getBusquedaModel();
		locModel.setNombre(Conversor.getVacioSiNull(this.getView().getTfNombre().getText()));
		
		if (this.getView().getTfFechaDesde() != null){
			locModel.setFechaDesde(Conversor.getDate(this.getView().getTfFechaDesde().getText()));
		}
		
		if (this.getView().getTfFechaHasta() != null) {
			locModel.setFechaHasta(Conversor.getDate(this.getView().getTfFechaHasta().getText()));
		}
		
		locModel.fireActualizarDatos();
	}
	
	@Override
	protected void actualizarBusquedaView() {
		this.getView().getTfNombre().setText(Conversor.getNullSiVacio(this.getBusquedaModel().getNombre()));
		this.getView().getTfFechaDesde().setValue(Conversor.getString(this.getBusquedaModel().getFechaDesde()));
		this.getView().getTfFechaHasta().setValue(Conversor.getString(this.getBusquedaModel().getFechaHasta()));
	}

	@Override
	protected TipoBalanceTableModel getTableModel() {
		return tableModel;
	}

	@Override
	protected AdminTipoBalanceView getView() {
		return view;
	}

	protected TipoBalanceBusquedaModel getBusquedaModel() {
		return busquedaModel;
	}
	
	void buscar() throws Exception {
		Thread locThread = new Thread(new Runnable(){
			public void run() {
				try {
					actualizarBusquedaModel();
					getView().iniBusqueda();
					List<TipoBalance> locLista = getBusquedaModel().buscar();
					getTableModel().setListaObjetos(locLista);
				} catch (Exception ex) {
					ex.printStackTrace();
					AppManager.getInstance().showErrorMsg(getView(), ex.getMessage());
				}
				finally {
					getView().finBusqueda();
				}
			}
		});
		locThread.start();
	}
	
	
	void openAgregarTipoBalance() throws Exception {
		AgregarTipoBalance agregarTipoBalance = new AgregarTipoBalance(this.getView());
		agregarTipoBalance.open();
		if (agregarTipoBalance.isOperacionRealizada()) {
			//this.getTableModel().addRow(agregarTipoBalance.getAbmModel().getObjetoABM());
			this.buscar();
		}
	}

	void openModificarTipoBalance() throws Exception {
		TipoBalance locTipoBalance = this.getSelectedRow();
		if(locTipoBalance != null) {
			locTipoBalance = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().getTipoBalanceByID(locTipoBalance.getIdTipoBalance());
			ModificarTipoBalance modificarTipoBalance = new ModificarTipoBalance(this.getView());
			modificarTipoBalance.getAbmModel().setObjetoABM(locTipoBalance);
			Set<Cuenta> locListaCuenta = locTipoBalance.getListaCuentas();
			
			modificarTipoBalance.getAbmModel().setListaDeCuentas(locListaCuenta);
			
			modificarTipoBalance.actualizarView();
			modificarTipoBalance.open();
			if (modificarTipoBalance.isOperacionRealizada()) {
				this.buscar();
			}
		}
	}

	void openEliminarTipoBalance() throws Exception {
		TipoBalance locTipoBalance = this.getSelectedRow();
		if(locTipoBalance != null) {
			locTipoBalance = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().getTipoBalanceByID(locTipoBalance.getIdTipoBalance());
			EliminarTipoBalance eliminarTipoBalance = new EliminarTipoBalance(this.getView());
			eliminarTipoBalance.getAbmModel().setObjetoABM(locTipoBalance);
			Set<Cuenta> locListaCuenta = locTipoBalance.getListaCuentas();
			
			eliminarTipoBalance.getAbmModel().setListaDeCuentas(locListaCuenta);
			
			eliminarTipoBalance.actualizarView();
			eliminarTipoBalance.open();
			if (eliminarTipoBalance.isOperacionRealizada()) {
				this.buscar();
			}
		}
	}
	
	void openConsultarTipoBalance() throws Exception {
		TipoBalance locTipoBalance = this.getSelectedRow();
		if (locTipoBalance != null) {
			locTipoBalance = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().getTipoBalanceByID(locTipoBalance.getIdTipoBalance());
			ConsultarTipoBalance consultarTipoBalance = new ConsultarTipoBalance(this.getView());
			consultarTipoBalance.getAbmModel().setObjetoABM(locTipoBalance);
			Set<Cuenta> locListaCuenta = locTipoBalance.getListaCuentas();
		
			consultarTipoBalance.getAbmModel().setListaDeCuentas(locListaCuenta);
			consultarTipoBalance.actualizarView();
			consultarTipoBalance.open();
			if (consultarTipoBalance.isOperacionRealizada()) {
				this.getTableModel().deleteRow(locTipoBalance);
			}
		}
	}
}

class BtnBuscarListener implements ActionListener {
	
	private AdminTipoBalance controller;

	public BtnBuscarListener(AdminTipoBalance controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.buscar();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(controller.getView(), ex.getMessage());
		}
	}
}

class BtnAgregarListener implements ActionListener {

	private AdminTipoBalance controller;
	
	public BtnAgregarListener(AdminTipoBalance controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openAgregarTipoBalance();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnModificarListener implements ActionListener {
	
	private AdminTipoBalance controller;

	public BtnModificarListener(AdminTipoBalance controller, AdminTipoBalanceView view) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openModificarTipoBalance();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnEliminarListener implements ActionListener {
	
	private AdminTipoBalance controller;

	public BtnEliminarListener(AdminTipoBalance controller) {
		this.controller = controller;
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openEliminarTipoBalance();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnConsultarListener implements ActionListener {
	
	private AdminTipoBalance controller;

	public BtnConsultarListener(AdminTipoBalance controller) {
		this.controller = controller;
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openConsultarTipoBalance();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

