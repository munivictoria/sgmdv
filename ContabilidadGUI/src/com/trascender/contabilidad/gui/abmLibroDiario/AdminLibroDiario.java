package com.trascender.contabilidad.gui.abmLibroDiario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.contabilidad.gui.abmLibroDiario.abm.AgregarLibroDiario;
import com.trascender.contabilidad.gui.abmLibroDiario.abm.ConsultarLibroDiario;
import com.trascender.contabilidad.gui.abmLibroDiario.abm.EliminarLibroDiario;
import com.trascender.contabilidad.gui.abmLibroDiario.abm.ModificarLibroDiario;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.LibroDiario;
import com.trascender.gui.framework.abmStandard.AdminController;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Conversor;

public class AdminLibroDiario extends AdminController<LibroDiario> {
	
	private AdminLibroDiarioView view;
	private LibroDiarioBusquedaModel busquedaModel = new LibroDiarioBusquedaModel();
	private LibroDiarioTableModel tableModel = new LibroDiarioTableModel();
	
	public AdminLibroDiario(JFrame owner) throws Exception {
		this.view = new AdminLibroDiarioView(owner);
		this.init();
	}
	
	public AdminLibroDiario(JDialog owner) throws Exception {
		this.view = new AdminLibroDiarioView(owner);
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
		this.getView().getPnlTabla().getTblDatos().setModel(this.getTableModel());
	}
	
	private void setListeners() {
		this.getView().getPnlBotonesBusqueda().getBtnBuscar().addActionListener(new BtnBuscarListener(this));
		
		this.getView().getPnlPie().getBtnConsultar().addActionListener(new BtnConsultarListener(this));
		this.getView().getPnlPie().getBtnAgregar().addActionListener(new BtnAgregarListener(this));
		this.getView().getPnlPie().getBtnModificar().addActionListener(new BtnModificarListener(this));
		this.getView().getPnlPie().getBtnEliminar().addActionListener(new BtnEliminarListener(this));
	}
	
	@Override
	protected void actualizarBusquedaModel() {
		LibroDiarioBusquedaModel locModel = this.getBusquedaModel();
		locModel.setCodigoAutorizacion(this.getView().getTfCodigoAutorizacion().getText());
		locModel.setNumero(this.getView().getTfNumero().getText());
		locModel.fireActualizarDatos();
	}

	@Override
	protected void actualizarBusquedaView() {
		this.getView().getTfCodigoAutorizacion().setText(Conversor.getVacioSiNull(this.getBusquedaModel().getCodigoAutorizacion()));
		this.getView().getTfNumero().setText(Conversor.getVacioSiNull(this.getBusquedaModel().getNumero()));
	}

	@Override
	protected LibroDiarioBusquedaModel getBusquedaModel() {
		return this.busquedaModel;
	}

	@Override
	protected LibroDiarioTableModel getTableModel() {
		return this.tableModel;
	}

	@Override
	protected AdminLibroDiarioView getView() {
		return this.view;
	}
	
	void buscar() {
		final AdminLibroDiario controller = this;
		Thread locThread = new Thread(new Runnable() {
			public void run() {
				try {
					controller.actualizarBusquedaModel();
					controller.getView().iniBusqueda();
					List<LibroDiario> locLista = controller.getBusquedaModel().buscar();
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
	
	void openConsultarLibroDiario() throws Exception {
		LibroDiario locLibroDiario = this.getSelectedRow();
		if (locLibroDiario != null) {
			locLibroDiario = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().getLibroDiarioByID(locLibroDiario.getIdLibroDiario());
			ConsultarLibroDiario consultarLibroDiario = new ConsultarLibroDiario(this.getView());
			consultarLibroDiario.getAbmModel().setObjetoABM(locLibroDiario);
			consultarLibroDiario.actualizarView();
			consultarLibroDiario.open();
			if (consultarLibroDiario.isOperacionRealizada()) {
				this.getTableModel().deleteRow(locLibroDiario);
			}
		}
	}

	void openAgregarLibroDiario() throws Exception {
		AgregarLibroDiario agregarLibroDiario = new AgregarLibroDiario(this.getView());
		agregarLibroDiario.open();
		if (agregarLibroDiario.isOperacionRealizada()) {
//			this.getTableModel().addRow(agregarLibroDiario.getAbmModel().getObjetoABM());
			this.buscar();
		}
	}
	
	void openModificarLibroDiario() throws Exception {
		LibroDiario locLibroDiario = this.getSelectedRow();
		if (locLibroDiario != null) {
			locLibroDiario = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().getLibroDiarioByID(locLibroDiario.getIdLibroDiario());
			ModificarLibroDiario modificarLibroDiario = new ModificarLibroDiario(this.getView());
			modificarLibroDiario.getAbmModel().setObjetoABM(locLibroDiario);
			modificarLibroDiario.actualizarView();
			modificarLibroDiario.open();
			if (modificarLibroDiario.isOperacionRealizada()) {
//				this.getTableModel().updateRow(locLibroDiario);
				this.buscar();				
			}
		}
	}
	
	void openEliminarLibroDiario() throws Exception {
		LibroDiario locLibroDiario = this.getSelectedRow();
		if (locLibroDiario != null) {
			locLibroDiario = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().getLibroDiarioByID(locLibroDiario.getIdLibroDiario());
			EliminarLibroDiario eliminarLibroDiario = new EliminarLibroDiario(this.getView());
			eliminarLibroDiario.getAbmModel().setObjetoABM(locLibroDiario);
			eliminarLibroDiario.actualizarView();
			eliminarLibroDiario.open();
			if (eliminarLibroDiario.isOperacionRealizada()) {
				this.getTableModel().deleteRow(locLibroDiario);
			}
		}
	}
	
}

class BtnBuscarListener implements ActionListener {
	
	private AdminLibroDiario controller;
	
	public BtnBuscarListener(AdminLibroDiario controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		this.controller.buscar();
	}
	
}

class BtnConsultarListener implements ActionListener {
	
	private AdminLibroDiario controller;
	
	public BtnConsultarListener(AdminLibroDiario controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openConsultarLibroDiario();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnAgregarListener implements ActionListener {
	
	private AdminLibroDiario controller;
	
	public BtnAgregarListener(AdminLibroDiario controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openAgregarLibroDiario();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnModificarListener implements ActionListener {
	
	private AdminLibroDiario controller;
	
	public BtnModificarListener(AdminLibroDiario controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openModificarLibroDiario();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnEliminarListener implements ActionListener {
	
	private AdminLibroDiario controller;
	
	public BtnEliminarListener(AdminLibroDiario controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openEliminarLibroDiario();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}