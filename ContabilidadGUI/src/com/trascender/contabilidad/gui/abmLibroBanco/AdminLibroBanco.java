package com.trascender.contabilidad.gui.abmLibroBanco;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.contabilidad.gui.abmCuentaBancaria.AdminCuentaBancaria;
import com.trascender.contabilidad.gui.abmLibroBanco.abm.AgregarLibroBanco;
import com.trascender.contabilidad.gui.abmLibroBanco.abm.ConsultarLibroBanco;
import com.trascender.contabilidad.gui.abmLibroBanco.abm.EliminarLibroBanco;
import com.trascender.contabilidad.gui.abmLibroBanco.abm.ModificarLibroBanco;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.CuentaBancaria;
import com.trascender.contabilidad.recurso.persistent.LibroBanco;
import com.trascender.gui.framework.abmStandard.AdminController;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Conversor;

public class AdminLibroBanco extends AdminController<LibroBanco>{

	private AdminLibroBancoView view;
	private LibroBancoBusquedaModel busquedaModel = new LibroBancoBusquedaModel();
	private LibroBancoTableModel tableModel = new LibroBancoTableModel();
	
	
	public AdminLibroBanco(JDialog owner) throws Exception {
		this.view = new AdminLibroBancoView(owner);
		this.init();
	}
	
	public AdminLibroBanco(JFrame owner) throws Exception {
		 this.view = new AdminLibroBancoView(owner);
		 this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		this.setModels();
		this.setListeners();
	}

	private void setModels() {
		this.view.setBusquedaModel(this.busquedaModel);
		this.view.getPnlTabla().getTblDatos().setModel(this.tableModel);		
	}

	private void setListeners() {
		this.getView().getPnlBotonesBusqueda().getBtnBuscar().addActionListener(new BtnBuscarListener(this));
		
		this.getView().getPnlBotonesSeleccion().getBtnSeleccionar().addActionListener(new BtnSeleccionarCuentaBancariaListener(this));
		this.getView().getPnlBotonesSeleccion().getBtnLimpiar().addActionListener(new BtnLimpiarCuentaBancariaListener(this));
		
		this.getView().getPnlPie().getBtnAgregar().addActionListener(new BtnAgregarListener(this));
		this.getView().getPnlPie().getBtnModificar().addActionListener(new BtnModificarListener(this));
		this.getView().getPnlPie().getBtnEliminar().addActionListener(new BtnEliminarListener(this));
		this.getView().getPnlPie().getBtnConsultar().addActionListener(new BtnConsultarListener(this));
	}
	
	
	@Override
	protected void actualizarBusquedaModel() {
		LibroBancoBusquedaModel locModel = this.getBusquedaModel();
		
		locModel.setNombre(Conversor.getNullSiVacio(this.getView().getTfNombre().getText()));
		
		locModel.fireActualizarDatos();
	}

	@Override
	protected void actualizarBusquedaView() {
		this.getView().getTfNombre().setText(Conversor.getVacioSiNull(this.getBusquedaModel().getNombre()));
		this.getView().getTfCuentaBancaria().setText(Conversor.getVacioSiNull(this.getBusquedaModel().getCuentaBancaria()));
	}

	@Override
	protected LibroBancoBusquedaModel getBusquedaModel() {
		return this.busquedaModel;
	}

	@Override
	protected LibroBancoTableModel getTableModel() {
		return this.tableModel;
	}

	@Override
	protected AdminLibroBancoView getView() {
		return this.view;
	}

	void buscar() {
		final AdminLibroBanco controller = this;
		Thread locThread = new Thread(new Runnable() {

			public void run()  {
				try {
					controller.actualizarBusquedaModel();
					controller.getView().iniBusqueda();
					List<LibroBanco> locLista = controller.getBusquedaModel().buscar();
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
	
	void seleccionarCuentaBancaria() throws Exception { 
		AdminCuentaBancaria adminCuentaBancaria = new AdminCuentaBancaria(this.getView());
		CuentaBancaria locCuentaBancaria = adminCuentaBancaria.openSelect();
		
		if (locCuentaBancaria!= null) {
			CuentaBancaria cuentaBancaria= ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConciliacionBancaria().getCuentaBancariaByID(locCuentaBancaria.getIdCuentaBancaria());
			this.getBusquedaModel().setCuentaBancaria(cuentaBancaria);
			this.actualizarBusquedaModel();
			this.actualizarBusquedaView();
		}
	}
	
	void limpiarCuentaBancaria() {
		this.getBusquedaModel().setCuentaBancaria(null);
		this.actualizarBusquedaModel();
		this.actualizarBusquedaView();
	}
	
	protected void openConsultarLibroBanco() throws Exception {
		LibroBanco locLibroBanco = this.getSelectedRow();
		if (locLibroBanco != null) {
			locLibroBanco = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConciliacionBancaria().getLibroBancoByID(locLibroBanco.getIdLibroBanco());
			ConsultarLibroBanco consultarLibroBanco= new ConsultarLibroBanco(this.getView());
			consultarLibroBanco.getAbmModel().setObjetoABM(locLibroBanco);
			consultarLibroBanco.actualizarView();
			consultarLibroBanco.open();
			if (consultarLibroBanco.isOperacionRealizada()) {
				this.getTableModel().deleteRow(locLibroBanco);
			}
		}
	}
	
	protected void openAgregarLibroBanco() throws Exception {
		AgregarLibroBanco agregarLibroBanco = new AgregarLibroBanco(this.getView());
		agregarLibroBanco.open();
		if (agregarLibroBanco.isOperacionRealizada()) {
			this.getTableModel().addRow(agregarLibroBanco.getAbmModel().getObjetoABM() );
		}
	}
	
	protected void openModificarLibroBanco() throws Exception {
		LibroBanco locLibroBanco= this.getSelectedRow();
		if (locLibroBanco != null) {
			locLibroBanco = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConciliacionBancaria().getLibroBancoByID(locLibroBanco.getIdLibroBanco());
			ModificarLibroBanco locModificarLibroBanco = new ModificarLibroBanco(this.getView());
			locModificarLibroBanco.getAbmModel().setObjetoABM(locLibroBanco);
			locModificarLibroBanco.actualizarView();
			
			//ArrayList<LineaLibroBanco> listaObjetos = locLibroBanco.getLineasLibroBanco();
//			locModificarLibroBanco.getAbmTableModel().setListaObjetos(listaObjetos);
			
			locModificarLibroBanco.open();
			if (locModificarLibroBanco.isOperacionRealizada()) {
				this.getTableModel().updateRow(locLibroBanco);
			}
		}
	}
	
	protected void openEliminarLibroBanco() throws Exception {
		LibroBanco locLibroBanco = this.getSelectedRow();
		if (locLibroBanco != null) {
			locLibroBanco = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConciliacionBancaria().getLibroBancoByID(locLibroBanco.getIdLibroBanco());
			EliminarLibroBanco eliminarLibroBanco= new EliminarLibroBanco(this.getView());
			eliminarLibroBanco.getAbmModel().setObjetoABM(locLibroBanco);
			eliminarLibroBanco.actualizarView();
			eliminarLibroBanco.open();
			if (eliminarLibroBanco.isOperacionRealizada()) {
				this.getTableModel().deleteRow(locLibroBanco);
			}
		}
	}
}

class BtnBuscarListener implements ActionListener {

	private AdminLibroBanco controller;
	
	public BtnBuscarListener(AdminLibroBanco controller) {
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

class BtnSeleccionarCuentaBancariaListener implements ActionListener {

	private AdminLibroBanco  controller;
	
	public BtnSeleccionarCuentaBancariaListener(AdminLibroBanco controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.seleccionarCuentaBancaria();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}

class BtnLimpiarCuentaBancariaListener implements ActionListener {

	private AdminLibroBanco controller;
	
	public BtnLimpiarCuentaBancariaListener(AdminLibroBanco controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.limpiarCuentaBancaria();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnConsultarListener implements ActionListener {

	private AdminLibroBanco controller;
	
	public BtnConsultarListener(AdminLibroBanco controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openConsultarLibroBanco();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnAgregarListener implements ActionListener {

	private AdminLibroBanco controller;
	
	public BtnAgregarListener(AdminLibroBanco controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openAgregarLibroBanco();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}

class BtnModificarListener implements ActionListener {

	private AdminLibroBanco controller;
	
	public BtnModificarListener(AdminLibroBanco controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openModificarLibroBanco();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnEliminarListener implements ActionListener {

	private AdminLibroBanco controller;
	
	public BtnEliminarListener(AdminLibroBanco controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openEliminarLibroBanco();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}