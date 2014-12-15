package com.trascender.contabilidad.gui.abmCuentaBancaria;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.contabilidad.gui.abmBanco.AdminBanco;
import com.trascender.contabilidad.gui.abmCuentaBancaria.abm.AgregarCuentaBancaria;
import com.trascender.contabilidad.gui.abmCuentaBancaria.abm.EliminarCuentaBancaria;
import com.trascender.contabilidad.gui.abmCuentaBancaria.abm.ModificarCuentaBancaria;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.Banco;
import com.trascender.contabilidad.recurso.persistent.CuentaBancaria;
import com.trascender.gui.framework.abmStandard.AdminController;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Conversor;

public class AdminCuentaBancaria extends AdminController<CuentaBancaria>{

	private AdminCuentaBancariaView view;
	private CuentaBancariaBusquedaModel busquedaModel = new CuentaBancariaBusquedaModel();
	private CuentaBancariaTableModel tableModel = new CuentaBancariaTableModel();

	public AdminCuentaBancaria(JDialog owner) throws Exception {
		this.view = new AdminCuentaBancariaView(owner);
		this.init();
	}

	public AdminCuentaBancaria(JFrame owner) throws Exception {
		this.view = new AdminCuentaBancariaView(owner);
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
		this.getView().getPnlPie().getBtnAgregar().addActionListener(new BtnAgregarListener(this));
		this.getView().getPnlPie().getBtnModificar().addActionListener(new BtnModificarListener(this));
		this.getView().getPnlPie().getBtnEliminar().addActionListener(new BtnEliminarListener(this));

		this.getView().getPnlBotonesSeleccion().getBtnSeleccionar().addActionListener(new BtnSeleccionarBancoListener(this));
		this.getView().getPnlBotonesSeleccion().getBtnLimpiar().addActionListener(new BtnLimpiarBancoListener(this));
	}

	@Override
	protected void actualizarBusquedaModel() {
		CuentaBancariaBusquedaModel locModel = this.getBusquedaModel();

		locModel.setNumero(Conversor.getNullSiVacio(this.getView().getTfNumero().getText()));
		locModel.setTipoCuenta(Conversor.getNullSiVacio(this.getView().getTfTipoCuenta().getText()));
		locModel.setPropia(this.getView().getChkPropia().isSelected());

		locModel.fireActualizarDatos();
	}

	@Override
	public void actualizarBusquedaView() {
		this.getView().getTfNumero().setText(Conversor.getVacioSiNull(this.getBusquedaModel().getNumero()));
		this.getView().getTfTipoCuenta().setText(Conversor.getVacioSiNull(this.getBusquedaModel().getTipoCuenta()));
		this.getView().getTfBanco().setText(Conversor.getVacioSiNull(this.getBusquedaModel().getBanco()));
		//this.getView().getChkPropia().setSelected((this.getBusquedaModel().isPropia()==null)?false:this.getBusquedaModel().isPropia());
		this.getView().getChkPropia().setSelected(this.getBusquedaModel().isPropia());
	}

	@Override
	protected CuentaBancariaBusquedaModel getBusquedaModel() {
		return this.busquedaModel;
	}

	@Override
	protected CuentaBancariaTableModel getTableModel() {
		return this.tableModel;
	}

	@Override
	protected AdminCuentaBancariaView getView() {
		return this.view;
	}



	void seleccionarBanco() throws Exception { 
		AdminBanco adminBanco = new AdminBanco(this.getView());
		Banco locBanco = adminBanco.openSelect();

		if (locBanco != null) {
			Banco banco = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConciliacionBancaria().getBancoByID(locBanco.getIdBanco());
			this.getBusquedaModel().setBanco(banco);
			this.actualizarBusquedaModel();
			this.actualizarBusquedaView();
		}
	}


	void limpiarBanco() {
		this.getBusquedaModel().setBanco(null);
		this.actualizarBusquedaModel();
		this.actualizarBusquedaView();
	}


	void buscar() {
		final AdminCuentaBancaria controller = this;
		Thread locThread = new Thread(new Runnable() {

			public void run()  {
				try {
					controller.actualizarBusquedaModel();
					controller.getView().iniBusqueda();
					List<CuentaBancaria> locLista = controller.getBusquedaModel().buscar();
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

	protected void openAgregarCuentaBancaria() throws Exception {
		AgregarCuentaBancaria agregarCuentaBancaria = new AgregarCuentaBancaria(this.getView());
		agregarCuentaBancaria.open();
		if (agregarCuentaBancaria.isOperacionRealizada()) {
//			this.getTableModel().addRow(agregarCuentaBancaria.getAbmModel().getObjetoABM());
			this.buscar();
		}
	}

	protected void openModificarCuentaBancaria() throws Exception {
		CuentaBancaria locCuentaBancaria = this.getSelectedRow();
		if (locCuentaBancaria != null) {
			locCuentaBancaria = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConciliacionBancaria().getCuentaBancariaByID(locCuentaBancaria.getIdCuentaBancaria());
			ModificarCuentaBancaria locModificarCuentaBancaria = new ModificarCuentaBancaria(this.getView());
			locModificarCuentaBancaria.getAbmModel().setObjetoABM(locCuentaBancaria);
			locModificarCuentaBancaria.actualizarView();
			locModificarCuentaBancaria.open();
			if (locModificarCuentaBancaria.isOperacionRealizada()) {
//				this.getTableModel().updateRow(locCuentaBancaria);
				this.buscar();
			}
		}
	}

	protected void openEliminarCuentaBancaria() throws Exception {
		CuentaBancaria locCuentaBancaria = this.getSelectedRow();
		if (locCuentaBancaria != null) {
			locCuentaBancaria = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConciliacionBancaria().getCuentaBancariaByID(locCuentaBancaria.getIdCuentaBancaria());;
			EliminarCuentaBancaria eliminarCuentaBancaria = new EliminarCuentaBancaria(this.getView());
			eliminarCuentaBancaria.getAbmModel().setObjetoABM(locCuentaBancaria);
			eliminarCuentaBancaria.actualizarView();
			eliminarCuentaBancaria.open();
			if (eliminarCuentaBancaria.isOperacionRealizada()) {
				this.getTableModel().deleteRow(locCuentaBancaria);
			}
		}
	}

}

class BtnBuscarListener implements ActionListener {

	private AdminCuentaBancaria controller;

	public BtnBuscarListener(AdminCuentaBancaria controller) {
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

class BtnAgregarListener implements ActionListener {

	private AdminCuentaBancaria controller;

	public BtnAgregarListener(AdminCuentaBancaria controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openAgregarCuentaBancaria();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}

}

class BtnSeleccionarBancoListener implements ActionListener {

	private AdminCuentaBancaria controller;

	public BtnSeleccionarBancoListener(AdminCuentaBancaria controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.seleccionarBanco();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}

}

class BtnLimpiarBancoListener implements ActionListener {

	private AdminCuentaBancaria controller;

	public BtnLimpiarBancoListener(AdminCuentaBancaria controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.limpiarBanco();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnModificarListener implements ActionListener {

	private AdminCuentaBancaria controller;

	public BtnModificarListener(AdminCuentaBancaria controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openModificarCuentaBancaria();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnEliminarListener implements ActionListener {

	private AdminCuentaBancaria controller;

	public BtnEliminarListener(AdminCuentaBancaria controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openEliminarCuentaBancaria();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}