package com.trascender.contabilidad.gui.abmPlanDeCuenta;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.contabilidad.gui.abmPlanDeCuenta.abm.AgregarPlanDeCuenta;
import com.trascender.contabilidad.gui.abmPlanDeCuenta.abm.EliminarPlanDeCuenta;
import com.trascender.contabilidad.gui.abmPlanDeCuenta.abm.ModificarPlanDeCuenta;
import com.trascender.contabilidad.gui.abmPlanDeCuenta.abmConsultar.ConsultarPlanDeCuenta;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.PlanDeCuenta;
import com.trascender.gui.framework.abmStandard.AdminController;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Conversor;

public class AdminPlanDeCuenta extends AdminController<PlanDeCuenta>{

	private AdminPlanDeCuentaView view;
	private PlanDeCuentaTableModel tableModel = new PlanDeCuentaTableModel();
	private PlanDeCuentaBusquedaModel busquedaModel = new PlanDeCuentaBusquedaModel();
	
	public AdminPlanDeCuenta (JFrame owner) throws Exception{
		this.view = new AdminPlanDeCuentaView(owner);
		this.init();
	}

	public AdminPlanDeCuenta (JDialog owner) throws Exception{
		this.view = new AdminPlanDeCuentaView(owner);
		this.init();
	}

	@Override
	protected void init() {
		super.init();
		this.setModels();
		this.setLIsteners();
		this.setBtnConsultarVisible();
	}
	
	private void setBtnConsultarVisible() {
		this.view.getPnlPie().getBtnConsultar().setVisible(true);
	}

	private void setModels() {
		this.view.setBusquedaModel(this.busquedaModel);
		this.view.getPnlTabla().getTblDatos().setModel(this.tableModel);
	}
	
	private void setLIsteners() {
		this.view.getPnlBotonesBusqueda().getBtnBuscar().addActionListener(new BtnBuscarListener(this));
		
		this.view.getPnlPie().getBtnAgregar().addActionListener(new BtnAgregarListener(this));
		this.view.getPnlPie().getBtnModificar().addActionListener(new BtnModificarListener(this));
		this.view.getPnlPie().getBtnEliminar().addActionListener(new BtnEliminarListener(this));
		this.view.getPnlPie().getBtnConsultar().addActionListener(new BtnConsultarListener(this));
	}


	@Override
	protected void actualizarBusquedaModel() {
		PlanDeCuentaBusquedaModel locModel = this.getView().getBusquedaModel();
		
		locModel.setDescripcion(Conversor.getNullSiVacio(this.getView().getTfDescripcion().getText()));
		locModel.setFechaDesde(Conversor.getDate(this.getView().getTfFechaDesde().getText()));
		locModel.setFechaHasta(Conversor.getDate(this.getView().getTfFechaHasta().getText()));
		
		locModel.fireActualizarDatos();
	}

	@Override
	protected void actualizarBusquedaView() {
		this.getView().getTfDescripcion().setText(this.getBusquedaModel().getDescripcion());
		this.getView().getTfFechaDesde().setValue(Conversor.getString(this.getBusquedaModel().getFechaDesde()));
		this.getView().getTfFechaHasta().setValue(Conversor.getString(this.getBusquedaModel().getFechaHasta()));
	}
	
	void openAgregarPlanDeCuenta() throws Exception {
		AgregarPlanDeCuenta agregarPlanDeCuenta = new AgregarPlanDeCuenta(this.getView());
		agregarPlanDeCuenta.open();
		if (agregarPlanDeCuenta.isOperacionRealizada()) {
			this.getTableModel().addRow(agregarPlanDeCuenta.getAbmModel().getObjetoABM());
			
		}
	}
	
	void openModificarPlanDeCuenta() throws Exception {
		PlanDeCuenta locPlanDeCuenta = this.getSelectedRow();
		if (locPlanDeCuenta != null) {
			PlanDeCuenta planDeCuenta = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().getPlanDeCuentaByID(locPlanDeCuenta.getIdPlanDeCuenta());
			ModificarPlanDeCuenta modificarPlanDeCuenta = new ModificarPlanDeCuenta(this.getView(),planDeCuenta);
			
			modificarPlanDeCuenta.actualizarView();
			
			modificarPlanDeCuenta.actualizarArbol();
			//agregado por jose con la nueva lista
			modificarPlanDeCuenta.getAbmModel().setTmpCuentas(modificarPlanDeCuenta.getAbmModel().getObjetoABM().getListaCuentas());
			System.out.println(modificarPlanDeCuenta.getAbmModel().getTmpCuentas());
			//*-----------------------
			modificarPlanDeCuenta.open();
			if (modificarPlanDeCuenta.isOperacionRealizada()) {
				this.getTableModel().updateRow(locPlanDeCuenta);
			}
			this.buscar();
		}
	}
	
	void openEliminarPlanDeCuenta() throws Exception {
		PlanDeCuenta locPlanDeCuenta = this.getSelectedRow();
		if (locPlanDeCuenta != null) {
			PlanDeCuenta planDeCuenta = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().getPlanDeCuentaByID(locPlanDeCuenta.getIdPlanDeCuenta());
			
			System.out.println(planDeCuenta.toString());
			System.out.println(planDeCuenta.getListaCuentas());
			
			EliminarPlanDeCuenta eliminarPlanDeCuenta = new EliminarPlanDeCuenta(this.getView(),planDeCuenta);
			
			eliminarPlanDeCuenta.actualizarView();
			
			eliminarPlanDeCuenta.actualizarArbol();
			eliminarPlanDeCuenta.open();
			if (eliminarPlanDeCuenta.isOperacionRealizada()) {
				this.getTableModel().deleteRow(locPlanDeCuenta);
			}
			this.buscar();
		}
	}
	
	void openConsultarPlanDeCuenta() throws Exception {
		PlanDeCuenta locPlanDeCuenta = this.getSelectedRow();
		if (locPlanDeCuenta != null) {
			PlanDeCuenta planDeCuenta = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().getPlanDeCuentaByID(locPlanDeCuenta.getIdPlanDeCuenta());
			ConsultarPlanDeCuenta consultarPlanDeCuenta = new ConsultarPlanDeCuenta(this.getView(),planDeCuenta);
			consultarPlanDeCuenta.instanciarTableModel();
			consultarPlanDeCuenta.open();
		}
	}
	
	@Override
	protected PlanDeCuentaTableModel getTableModel() {
		return tableModel;
	}

	@Override
	protected AdminPlanDeCuentaView getView() {
		return view;
	}
	
	protected PlanDeCuentaBusquedaModel getBusquedaModel() {
		return busquedaModel;
	}
	
	void buscar() throws Exception {
		final AdminPlanDeCuenta controller = this;
		Thread LocThread= new Thread(new Runnable(){
			public void run() {
				try {
					controller.actualizarBusquedaModel();
					controller.getView().iniBusqueda();
					List<PlanDeCuenta> locLista = controller.getBusquedaModel().buscar();
					controller.getTableModel().setListaObjetos(locLista);
				} catch (Exception ex) {
					ex.printStackTrace();
					AppManager.getInstance().showErrorMsg(view, ex.getMessage());
				}
				finally {
					controller.getView().finBusqueda();
				}
			}
		});
		LocThread.start();
	}
}

class BtnBuscarListener implements ActionListener{

	private AdminPlanDeCuenta controller;
	
	public BtnBuscarListener(AdminPlanDeCuenta controller){
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

class BtnAgregarListener implements ActionListener{
	
	private AdminPlanDeCuenta controller;
	
	public BtnAgregarListener(AdminPlanDeCuenta controller) {
		this.controller = controller;
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openAgregarPlanDeCuenta();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}

class BtnModificarListener implements ActionListener{

	private AdminPlanDeCuenta controller;

	public BtnModificarListener(AdminPlanDeCuenta controller) {
		this.controller = controller;
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openModificarPlanDeCuenta();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}

class BtnEliminarListener implements ActionListener{

	private AdminPlanDeCuenta controller;

	public BtnEliminarListener(AdminPlanDeCuenta controller) {
		this.controller = controller;
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openEliminarPlanDeCuenta();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnConsultarListener implements ActionListener{

	private AdminPlanDeCuenta controller;

	public BtnConsultarListener(AdminPlanDeCuenta controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openConsultarPlanDeCuenta();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}


