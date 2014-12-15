package com.trascender.contabilidad.gui.abmMayor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.contabilidad.gui.abmCuenta.AdminCuenta;
import com.trascender.contabilidad.gui.abmMayor.abm.AgregarMayor;
import com.trascender.contabilidad.gui.abmMayor.abm.ConsultarMayor;
import com.trascender.contabilidad.gui.abmMayor.abm.EliminarMayor;
import com.trascender.contabilidad.gui.abmMayor.abm.ModificarMayor;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.contabilidad.recurso.persistent.LineaMayor;
import com.trascender.contabilidad.recurso.persistent.Mayor;
import com.trascender.gui.framework.abmStandard.AdminController;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Conversor;

public class AdminMayor extends AdminController<Mayor> {

	private AdminMayorView view;

	private MayorBusquedaModel busquedaModel = new MayorBusquedaModel();

	private MayorTableModel tableModel = new MayorTableModel();

	public AdminMayor(JFrame owner) throws Exception {
		this.view = new AdminMayorView(owner);
		this.init();
	}

	public AdminMayor(JDialog owner) throws Exception {
		this.view = new AdminMayorView(owner);
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
		
		this.getView().getPnlBotonesSeleccion().getBtnSeleccionar().addActionListener(new BtnSeleccionarCuentaListener(this));
		this.getView().getPnlBotonesSeleccion().getBtnLimpiar().addActionListener(new BtnLimpiarCuentaListener(this));
		
		this.getView().getPnlPie().getBtnAgregar().addActionListener(new BtnAgregarListener(this));
		this.getView().getPnlPie().getBtnModificar().addActionListener(new BtnModificarListener(this));
		this.getView().getPnlPie().getBtnEliminar().addActionListener(new BtnEliminarListener(this));
		this.getView().getPnlPie().getBtnConsultar().addActionListener(new BtnConsultarListener(this));
	}

	@Override
	protected void actualizarBusquedaModel() {
		MayorBusquedaModel locModel = this.getBusquedaModel();

		locModel.fireActualizarDatos();
	}

	@Override
	protected void actualizarBusquedaView() {
		this.getView().getTfCuenta().setText(
				Conversor.getVacioSiNull(this.getBusquedaModel().getCuenta()));
	}

	@Override
	protected MayorBusquedaModel getBusquedaModel() {
		return this.busquedaModel;
	}

	@Override
	protected MayorTableModel getTableModel() {
		return this.tableModel;
	}

	@Override
	protected AdminMayorView getView() {
		return this.view;
	}

	void seleccionarCuenta() throws Exception { 
		AdminCuenta adminCuenta = new AdminCuenta(this.getView());
		Cuenta locCuenta = adminCuenta.openSelect();
		
		if (locCuenta != null) {
			Cuenta cuenta = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().getCuentaByID(locCuenta.getIdCuenta());
			this.getBusquedaModel().setCuenta(cuenta);
			this.actualizarBusquedaModel();
			this.actualizarBusquedaView();
		}
	}
	
	
	void limpiarCuenta() {
		this.getBusquedaModel().setCuenta(null);
		this.actualizarBusquedaModel();
		this.actualizarBusquedaView();
	}
	
	void buscar() {
		final AdminMayor controller = this;
		Thread locThread = new Thread(new Runnable() {
			public void run() {
				try {
					controller.actualizarBusquedaModel();
					controller.getView().iniBusqueda();
					List<Mayor> locLista = controller.getBusquedaModel()
							.buscar();
					controller.getTableModel().setListaObjetos(locLista);
				} catch (Exception ex) {
					ex.printStackTrace();
					AppManager.getInstance().showErrorMsg(controller.getView(),
							ex.getMessage());
				}
				finally {
					controller.getView().finBusqueda();
				}
			}
		});
		locThread.start();
	}

	void openAgregarMayor() throws Exception {
		AgregarMayor agregarMayor = new AgregarMayor(this.getView());
		agregarMayor.open();
		if (agregarMayor.isOperacionRealizada()) {
			this.getTableModel().addRow(agregarMayor.getAbmModel().getObjetoABM());
		}
	}

	void openModificarMayor() throws Exception {
		Mayor locMayor= this.getSelectedRow();
		if (locMayor != null) {
			locMayor = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().getMayorByID(locMayor.getIdMayor());
			ModificarMayor locModificarMayor = new ModificarMayor(this.getView());
			locModificarMayor.getAbmModel().setObjetoABM(locMayor);
			locModificarMayor.getTableModel().addRows(locMayor.getListaLineaMayor());
			ArrayList<LineaMayor> listaLineaMayor = new ArrayList<LineaMayor>(locMayor.getListaLineaMayor());
			Date fechaGeneracion = listaLineaMayor.get(0).getFechaGeneracion();
			Calendar calendario= Calendar.getInstance();
			calendario.setTime(fechaGeneracion);
			
			locModificarMayor.actualizarView(); 
			locModificarMayor.getView().getTfAnio().setText(Conversor.getString(calendario.get(Calendar.YEAR)));
			locModificarMayor.getView().getTfMes().setText(Conversor.getString(Conversor.getDia((calendario.get(Calendar.MONTH)+1))));
			locModificarMayor.open();
			if (locModificarMayor.isOperacionRealizada()) {
				this.getTableModel().updateRow(locMayor);
			}
		}
	}
	
	void openEliminarMayor() throws Exception {
		Mayor locMayor = this.getSelectedRow();
		
		if (locMayor != null) {
			locMayor = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().getMayorByID(locMayor.getIdMayor());
			EliminarMayor locEliminarMayor = new EliminarMayor(this.getView());
			locEliminarMayor.getAbmModel().setObjetoABM(locMayor);
			locEliminarMayor.getTableModel().addRows(locMayor.getListaLineaMayor());
			locEliminarMayor.actualizarView();
			ArrayList<LineaMayor> listaLineaMayor = new ArrayList<LineaMayor>(locMayor.getListaLineaMayor());
			Date fechaGeneracion = listaLineaMayor.get(0).getFechaGeneracion();
			Calendar calendario= Calendar.getInstance();
			calendario.setTime(fechaGeneracion);
			locEliminarMayor.getView().getTfAnio().setText(Conversor.getString(calendario.get(Calendar.YEAR)));
			locEliminarMayor.getView().getTfMes().setText(Conversor.getString(Conversor.getDia((calendario.get(Calendar.MONTH)+1))));
			locEliminarMayor.open();
			
			if (locEliminarMayor.isOperacionRealizada()) {
				this.getTableModel().deleteRow(locMayor);
			}
		}
	}

	public void openConsultarMayor() throws Exception {
		Mayor locMayor = this.getSelectedRow();
		
		if (locMayor != null) {
			locMayor = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().getMayorByID(locMayor.getIdMayor());
			ConsultarMayor locConsultarMayor = new ConsultarMayor(this.getView());
			locConsultarMayor.getAbmModel().setObjetoABM(locMayor);
			locConsultarMayor.getTableModel().addRows(locMayor.getListaLineaMayor());
			locConsultarMayor.actualizarView();
			ArrayList<LineaMayor> listaLineaMayor = new ArrayList<LineaMayor>(locMayor.getListaLineaMayor());
			Date fechaGeneracion = listaLineaMayor.get(0).getFechaGeneracion();
			Calendar calendario= Calendar.getInstance();
			calendario.setTime(fechaGeneracion);
			locConsultarMayor.getView().getTfAnio().setText(Conversor.getString(calendario.get(Calendar.YEAR)));
			locConsultarMayor.getView().getTfMes().setText(Conversor.getString(Conversor.getDia((calendario.get(Calendar.MONTH)+1))));
			locConsultarMayor.open();
			
			if (locConsultarMayor.isOperacionRealizada()) {
				this.getTableModel().deleteRow(locMayor);
			}
		}
	}
}

class BtnBuscarListener implements ActionListener {
	private AdminMayor controller;

	public BtnBuscarListener(AdminMayor controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		this.controller.buscar();
	}
}

class BtnSeleccionarCuentaListener implements ActionListener {

	private AdminMayor controller;
	
	public BtnSeleccionarCuentaListener(AdminMayor controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.seleccionarCuenta();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}

class BtnLimpiarCuentaListener implements ActionListener {

	private AdminMayor controller;
	
	public BtnLimpiarCuentaListener(AdminMayor controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.limpiarCuenta();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnAgregarListener implements ActionListener {

	private AdminMayor controller;

	public BtnAgregarListener(AdminMayor controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openAgregarMayor();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(),
					ex.getMessage());
		}
	}
}


class BtnModificarListener implements ActionListener {

	private AdminMayor controller;
	
	public BtnModificarListener(AdminMayor controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openModificarMayor();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnEliminarListener implements ActionListener {

	private AdminMayor controller;
	
	public BtnEliminarListener(AdminMayor controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openEliminarMayor();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnConsultarListener implements ActionListener {

	private AdminMayor controller;
	
	public BtnConsultarListener(AdminMayor controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openConsultarMayor();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}