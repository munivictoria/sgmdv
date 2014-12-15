package com.trascender.contabilidad.gui.abmCuenta;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.contabilidad.gui.abmPlanDeCuenta.AdminPlanDeCuenta;
import com.trascender.contabilidad.gui.abmTipoCuenta.AdminTipoCuenta;
import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.contabilidad.recurso.persistent.PlanDeCuenta;
import com.trascender.contabilidad.recurso.persistent.TipoCuenta;
import com.trascender.framework.recurso.persistent.Area;
import com.trascender.gui.framework.abmStandard.AdminController;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.model.TDefaultComboBoxModel;
import com.trascender.gui.framework.util.Conversor;

public class AdminCuenta extends AdminController<Cuenta> {
	
	private AdminCuentaView view;
	private CuentaTableModel tableModel = new CuentaTableModel();
	private CuentaBusquedaModel busquedaModel = new CuentaBusquedaModel();
	
	private ArrayList<Cuenta> listaCuentasFinal = new ArrayList<Cuenta>();
	
	public AdminCuenta(JFrame owner) throws Exception {
		this.view = new AdminCuentaView(owner);
		this.init();
	}
	
	public AdminCuenta(JDialog owner) throws Exception {
		this.view = new AdminCuentaView(owner);
		this.init();
	}
	
	
	/**
	 * Este constructor se utiliza cuando se necesita que solo se muestren las cuentas que requieran asientos contables
	 * @param owner
	 * @param pCuentasAceptanAsientos
	 * @throws Exception
	 */
	public AdminCuenta(JDialog owner, boolean pCuentasAceptanAsientos) throws Exception{
		this(owner);
		this.busquedaModel.setAceptaAsientosContables(pCuentasAceptanAsientos);
	}
	
	@Override
	protected void init() {
		super.init();
		this.setCommonProperties();
		this.setModels();
		this.setListeners();
	}
	
	private void setCommonProperties() {
		this.getView().getPnlPie().getBtnAgregar().setEnabled(false);
		this.getView().getPnlPie().getBtnModificar().setEnabled(false);
		this.getView().getPnlPie().getBtnEliminar().setEnabled(false);
	}
	
	private void setModels() {
		this.getView().setBusquedaModel(this.getBusquedaModel());
		this.getView().setTableModel(this.getTableModel());
		
		List<Area> locListaAreas = this.busquedaModel.getListaAreas();
		this.getView().getCbArea().setModel(new TDefaultComboBoxModel(locListaAreas.toArray()));
	}
	
	private void setListeners() {
		this.getView().getPnlBotonesSeleccionTipoCuenta().getBtnSeleccionar().addActionListener(new BtnSeleccionarTipoCuentaListener(this));
		this.getView().getPnlBotonesSeleccionTipoCuenta().getBtnLimpiar().addActionListener(new BtnLimpiarTipoCuentaListener(this));
		this.getView().getPnlBotonesSeleccionPlanDeCuenta().getBtnSeleccionar().addActionListener(new BtnSeleccionarPlanDeCuentaListener(this));
		this.getView().getPnlBotonesSeleccionPlanDeCuenta().getBtnLimpiar().addActionListener(new BtnLimpiarPlanDeCuentaListener(this));
		
		this.getView().getPnlBotonesBusqueda().getBtnBuscar().addActionListener(new BtnBuscarListener(this));
	}
	
	@Override
	protected void actualizarBusquedaModel() {
		CuentaBusquedaModel locModel = this.getBusquedaModel();
		locModel.setCodigoImputacion(this.getView().getTfCodigoImputacion().getText());
		locModel.setNombre(this.getView().getTfNombre().getText());
		locModel.setAbreviatura(this.getView().getTfAbreviatura().getText());
		locModel.setArea((Area)this.getView().getCbArea().getSelectedItem());
		locModel.fireActualizarDatos();
	}
	
	public List<Cuenta> ordenarCuentas(ArrayList<Cuenta> listaCuentas) {
		Collections.sort(listaCuentas, new Comparator<Cuenta>(){
			public int compare(Cuenta o1, Cuenta o2) {
				return (o1.getCodigoImputacionCompleto()!=null?o1.getCodigoImputacionCompleto():"").compareTo(o2.getCodigoImputacionCompleto()!=null?o2.getCodigoImputacionCompleto():"");
			}
		});
		
		return listaCuentas;
	}
	
	public void instanciarTableModel() {
		this.getView().setTableModel(this.tableModel);
	}
	
	@Override
	protected void actualizarBusquedaView() {
		this.getView().getTfCodigoImputacion().setText(Conversor.getVacioSiNull(this.getBusquedaModel().getCodigoImputacion()));
		this.getView().getTfNombre().setText(Conversor.getVacioSiNull(this.getBusquedaModel().getNombre()));
		this.getView().getTfAbreviatura().setText(Conversor.getVacioSiNull(this.getBusquedaModel().getAbreviatura()));
		this.getView().getTfTipoCuenta().setText(Conversor.getVacioSiNull(this.getBusquedaModel().getTipoCuenta()));
		this.getView().getTfPlanDeCuenta().setText(Conversor.getVacioSiNull(this.getBusquedaModel().getPlanDeCuenta()));
		this.getView().getCbArea().setSelectedItem(this.getBusquedaModel().getArea());
	}

	@Override
	protected CuentaBusquedaModel getBusquedaModel() {
		return this.busquedaModel;
	}
	
	@Override
	protected CuentaTableModel getTableModel() {
		return this.tableModel;
	}
	
	@Override
	protected AdminCuentaView getView() {
		return this.view;
	}
	
	void buscar() throws Exception {
		final AdminCuenta controller = this;
		Thread locThread = new Thread(new Runnable(){
			public void run() {
				try {
					controller.actualizarBusquedaModel();
					controller.getView().iniBusqueda();
					List<Cuenta> locLista = controller.getBusquedaModel().buscar();
					controller.getTableModel().setListaObjetos(controller.ordenarCuentas((ArrayList<Cuenta>) locLista));
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
	
	void openAgregarCuenta() throws Exception {
		// No se agregan cuentas individualmente. Solo desde PlanDeCuenta. 
	}
	
	void openModificarCuenta() throws Exception {
		// No se modifican cuentas individualmente. Solo desde PlanDeCuenta.
	}
	
	void openEliminarCuenta() throws Exception {
		// No se eliminan cuentas individualmente. Solo desde PlanDeCuenta.
	}
	
	void seleccionarTipoCuenta() throws Exception {
		AdminTipoCuenta adminTipoCuenta = new AdminTipoCuenta(this.getView());
		this.actualizarBusquedaModel();
		TipoCuenta locTipoCuenta = adminTipoCuenta.openSelect();
		if (locTipoCuenta != null) {
			this.getBusquedaModel().setTipoCuenta(locTipoCuenta);
			this.actualizarBusquedaView();
		}
	}
	
	void limpiarTipoCuenta() throws Exception {
		this.getBusquedaModel().setTipoCuenta(null);
		this.actualizarBusquedaModel();
		this.actualizarBusquedaView();
	}
	
	void selecccionarPlanDeCuenta() throws Exception {
		AdminPlanDeCuenta adminPlanDeCuenta = new AdminPlanDeCuenta(this.getView());
		this.actualizarBusquedaModel();
		PlanDeCuenta locPlanDeCuenta = adminPlanDeCuenta.openSelect();
		if (locPlanDeCuenta != null) {
			this.getBusquedaModel().setPlanDeCuenta(locPlanDeCuenta);
			this.actualizarBusquedaView();
		}
	}
	
	void limpiarPlanDeCuenta() throws Exception {
		this.getBusquedaModel().setPlanDeCuenta(null);
		this.actualizarBusquedaModel();
		this.actualizarBusquedaView();
	}
}

class BtnBuscarListener implements ActionListener {
	private AdminCuenta controller;
	public BtnBuscarListener(AdminCuenta controller) {
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

class BtnSeleccionarTipoCuentaListener implements ActionListener {
	private AdminCuenta controller;
	public BtnSeleccionarTipoCuentaListener(AdminCuenta controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.seleccionarTipoCuenta();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnLimpiarTipoCuentaListener implements ActionListener {
	private AdminCuenta controller;
	public BtnLimpiarTipoCuentaListener(AdminCuenta controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.limpiarTipoCuenta();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnSeleccionarPlanDeCuentaListener implements ActionListener {
	private AdminCuenta controller;
	public BtnSeleccionarPlanDeCuentaListener(AdminCuenta controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.selecccionarPlanDeCuenta();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}

class BtnLimpiarPlanDeCuentaListener implements ActionListener {
	private AdminCuenta controller;
	public BtnLimpiarPlanDeCuentaListener(AdminCuenta controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.limpiarPlanDeCuenta();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}
