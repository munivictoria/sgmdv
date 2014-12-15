package com.trascender.contabilidad.gui.abmImportarPlanDeCuenta;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.contabilidad.gui.abmImportarPlanDeCuenta.abm.ImportarPlanDeCuenta;
import com.trascender.contabilidad.gui.abmPlanDeCuenta.AdminPlanDeCuentaView;
import com.trascender.contabilidad.gui.abmPlanDeCuenta.PlanDeCuentaBusquedaModel;
import com.trascender.contabilidad.gui.abmPlanDeCuenta.PlanDeCuentaTableModel;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.contabilidad.recurso.persistent.PlanDeCuenta;
import com.trascender.gui.framework.abmStandard.AdminController;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Conversor;

public class AdminImportarPlanDeCuenta extends AdminController<PlanDeCuenta> {
	
	private AdminPlanDeCuentaView view;
	private PlanDeCuentaTableModel tableModel = new PlanDeCuentaTableModel();
	private PlanDeCuentaBusquedaModel busquedaModel = new PlanDeCuentaBusquedaModel();
	
	public AdminImportarPlanDeCuenta(JFrame owner) throws Exception {
		this.view = new AdminPlanDeCuentaView(owner);
		this.init();
	}
	
	public AdminImportarPlanDeCuenta(JDialog owner) throws Exception {
		this.view = new AdminPlanDeCuentaView(owner);
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		this.setModels();
		this.setLIsteners();
		this.setHabilitacionDeBotones();
		this.setTituloVentana();
	}

	private void setModels() {
		this.view.setBusquedaModel(this.busquedaModel);
		this.view.getPnlTabla().getTblDatos().setModel(this.tableModel);
	}
	
	private void setLIsteners() {
		this.view.getPnlBotonesBusqueda().getBtnBuscar().addActionListener(new BtnBuscarListener(this));
		
		this.view.getPnlPie().getBtnAgregar().addActionListener(new BtnAgregarListener(this));
	}
	
	private void setHabilitacionDeBotones() {
		this.view.getPnlPie().getBtnModificar().setEnabled(false);
		this.view.getPnlPie().getBtnEliminar().setEnabled(false);
	}
	
	public void setTituloVentana() {
		this.getView().getPnlCabecera().getLblTitulo().setText("Administración Importar Plan de Cuenta");
		this.getView().getPnlPie().getBtnAgregar().setText("Importar");
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
	
	void buscar() throws Exception {
		final AdminImportarPlanDeCuenta controller = this;
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

	void openImportarPlanDeCuenta() throws Exception {
		PlanDeCuenta locPlanDeCuenta = this.getSelectedRow();
		if (locPlanDeCuenta != null) {
			PlanDeCuenta planDeCuenta = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().getPlanDeCuentaByID(locPlanDeCuenta.getIdPlanDeCuenta());
			
			PlanDeCuenta planDeCuentaImportado = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().importarPlanDeCuenta(planDeCuenta);
			ImportarPlanDeCuenta importarPlanDeCuenta = new ImportarPlanDeCuenta(this.getView(), planDeCuentaImportado);
			importarPlanDeCuenta.instanciarTableModel();
			ArrayList<Cuenta>listaCuentasImportadas = new ArrayList<Cuenta>();
			listaCuentasImportadas.addAll(planDeCuentaImportado.getListaCuentas());
			importarPlanDeCuenta.getTableModel().setListaObjetos(listaCuentasImportadas);
			importarPlanDeCuenta.actualizarABMModel();
			importarPlanDeCuenta.open();
			if (importarPlanDeCuenta.isOperacionRealizada()) {
				this.getTableModel().addRow(importarPlanDeCuenta.getAbmModel().getObjetoABM());
			}
		}
	}
	
	@Override
	protected PlanDeCuentaBusquedaModel getBusquedaModel() {
		return this.busquedaModel;
	}

	@Override
	protected PlanDeCuentaTableModel getTableModel() {
		return this.tableModel;
	}

	@Override
	protected AdminPlanDeCuentaView getView() {
		return this.view;
	}

}

class BtnBuscarListener implements ActionListener{

	private AdminImportarPlanDeCuenta controller;
	
	public BtnBuscarListener(AdminImportarPlanDeCuenta controller) {
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
	
	private AdminImportarPlanDeCuenta controller;
	
	public BtnAgregarListener(AdminImportarPlanDeCuenta controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openImportarPlanDeCuenta();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}

