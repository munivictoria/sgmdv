package com.trascender.contabilidad.gui.abmAsociacionCuentaModificador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.trascender.contabilidad.gui.abmAsociacionCuentaModificador.abm.AgregarAsociacionCuentaModificador;
import com.trascender.contabilidad.gui.abmAsociacionCuentaModificador.abm.EliminarAsociacionCuentaModificador;
import com.trascender.contabilidad.gui.abmAsociacionCuentaModificador.abm.ModificarAsociacionCuentaModificador;
import com.trascender.contabilidad.gui.abmCuenta.AdminCuenta;
import com.trascender.contabilidad.gui.abmTipoModificador.AdminTipoModificador;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.contabilidad.recurso.persistent.CuentaModificador;
import com.trascender.gui.framework.abmStandard.AdminController;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Conversor;
import com.trascender.gui.framework.util.Validador;
import com.trascender.habilitaciones.recurso.persistent.TipoModificador;

public class AdminAsociacionCuentaModificador extends AdminController<CuentaModificador> {

	private final AdminAsociacionCuentaModificadorView view;
	private final AsociacionCuentaModificadorBusquedaModel busquedaModel = new AsociacionCuentaModificadorBusquedaModel();
	private final AsociacionCuentaModificadorTableModel tableModel = new AsociacionCuentaModificadorTableModel();

	public AdminAsociacionCuentaModificador(JFrame owner) throws Exception {
		this.view = new AdminAsociacionCuentaModificadorView(owner);
		this.init();
	}

	public AdminAsociacionCuentaModificador(JDialog owner) throws Exception {
		this.view = new AdminAsociacionCuentaModificadorView(owner);
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
		this.getView().getPnlBotonesSeleccionTipoModificador().getBtnSeleccionar().addActionListener(new BtnSeleccionarTipoModificadorListener(this));
		this.getView().getPnlBotonesSeleccionTipoModificador().getBtnLimpiar().addActionListener(new BtnLimpiarTipoModificadorListener(this));
		this.getView().getPnlBotonesSeleccionCuenta().getBtnSeleccionar().addActionListener(new BtnSeleccionarCuentaListener(this));
		this.getView().getPnlBotonesSeleccionCuenta().getBtnLimpiar().addActionListener(new BtnLimpiarCuentaListener(this));

		this.getView().getPnlBotonesBusqueda().getBtnBuscar().addActionListener(new BtnBuscarListener(this));

		this.getView().getPnlPie().getBtnAgregar().addActionListener(new BtnAgregarListener(this));
		this.getView().getPnlPie().getBtnModificar().addActionListener(new BtnModificarListener(this));
		this.getView().getPnlPie().getBtnEliminar().addActionListener(new BtnEliminarListener(this));
	}

	@Override
	protected void actualizarBusquedaModel() {
		this.getBusquedaModel().fireActualizarDatos();
	}

	@Override
	protected void actualizarBusquedaView() {
		this.getView().getTfTipoModificador().setText(Conversor.getVacioSiNull(this.getBusquedaModel().getTipoModificador()));
		this.getView().getTfCuenta().setText(Conversor.getVacioSiNull(this.getBusquedaModel().getCuenta()));
	}

	@Override
	protected AsociacionCuentaModificadorBusquedaModel getBusquedaModel() {
		return this.busquedaModel;
	}

	@Override
	protected AsociacionCuentaModificadorTableModel getTableModel() {
		return this.tableModel;
	}

	@Override
	protected AdminAsociacionCuentaModificadorView getView() {
		return this.view;
	}

	void buscar() {
		if (this.validarDatos()) {
			final AdminAsociacionCuentaModificador controller = this;
			Thread locThread = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						controller.actualizarBusquedaModel();
						controller.getView().iniBusqueda();
						List<CuentaModificador> locCuentaTipoTasa = controller.getBusquedaModel().buscar();
						controller.getTableModel().setListaObjetos(locCuentaTipoTasa);
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
	}

	@Override
	public boolean validarDatos() {
		boolean validacionOK = true;
		
		return validacionOK;
	}

	void seleccionarTipoModificador() throws Exception {
		AdminTipoModificador adminTipoModificador = new AdminTipoModificador(this.getView());
		this.actualizarBusquedaModel();
		TipoModificador locTipoModificador = adminTipoModificador.openSelect();
		if (locTipoModificador != null) {
			this.getBusquedaModel().setTipoModificador(locTipoModificador);
			this.actualizarBusquedaView();
		}
	}

	void limpiarTipoModificador() throws Exception {
		this.getBusquedaModel().setTipoModificador(null);
		this.actualizarBusquedaModel();
		this.actualizarBusquedaView();
	}

	void seleccionarCuenta() throws Exception {
		AdminCuenta adminCuenta = new AdminCuenta(this.getView());
		Cuenta locCuenta = adminCuenta.openSelect();

		if (locCuenta != null) {
			this.getBusquedaModel().setCuenta(locCuenta);
			this.actualizarBusquedaModel();
			this.actualizarBusquedaView();
		}
	}

	void limpiarCuenta() throws Exception {
		this.getBusquedaModel().setCuenta(null);
		this.actualizarBusquedaModel();
		this.actualizarBusquedaView();
	}


	void openAgregarAsocioacionCuentaModificador() throws Exception {
		AgregarAsociacionCuentaModificador agregarAsociacionCuentaModificador = new AgregarAsociacionCuentaModificador(this.getView());
		agregarAsociacionCuentaModificador.open();
		if (agregarAsociacionCuentaModificador.isOperacionRealizada() && this.getTableModel().getRowCount() > 0) {
			this.buscar();
		}
	}

	void openModificarAsocioacionCuentaModificador() throws Exception {
		CuentaModificador locCuentaModificador = this.getSelectedRow();
		if (locCuentaModificador != null) {
			locCuentaModificador = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().getCuentaModificadorByID(locCuentaModificador.getIdAsociacionCuenta());
			ModificarAsociacionCuentaModificador modificarAsociacionCuentaModificador = new ModificarAsociacionCuentaModificador(this.getView());
			modificarAsociacionCuentaModificador.getAbmModel().setObjetoABM(locCuentaModificador);
			modificarAsociacionCuentaModificador.actualizarView();
			modificarAsociacionCuentaModificador.open();
			if (modificarAsociacionCuentaModificador.isOperacionRealizada() && this.getTableModel().getRowCount() > 0) {
				this.buscar();
			}
		}
	}

	void openEliminarAsociacionCuentaModificador() throws Exception {
		CuentaModificador locCuentaModificador = this.getSelectedRow();
		if (locCuentaModificador != null) {
			locCuentaModificador = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().getCuentaModificadorByID(locCuentaModificador.getIdAsociacionCuenta());
			EliminarAsociacionCuentaModificador mliminarAsociacionCuentaModificador = new EliminarAsociacionCuentaModificador(this.getView());
			mliminarAsociacionCuentaModificador.getAbmModel().setObjetoABM(locCuentaModificador);
			mliminarAsociacionCuentaModificador.actualizarView();
			mliminarAsociacionCuentaModificador.open();
			if (mliminarAsociacionCuentaModificador.isOperacionRealizada() && this.getTableModel().getRowCount() > 0) {
				this.buscar();
			}
		}
	}

}


class BtnBuscarListener implements ActionListener {
	private final AdminAsociacionCuentaModificador controller;
	public BtnBuscarListener(AdminAsociacionCuentaModificador controller) {
		this.controller = controller;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		this.controller.buscar();
	}
}

class BtnSeleccionarTipoModificadorListener implements ActionListener {
	private final AdminAsociacionCuentaModificador controller;
	public BtnSeleccionarTipoModificadorListener(AdminAsociacionCuentaModificador controller) {
		this.controller = controller;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.seleccionarTipoModificador();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnLimpiarTipoModificadorListener implements ActionListener {
	private final AdminAsociacionCuentaModificador controller;
	public BtnLimpiarTipoModificadorListener(AdminAsociacionCuentaModificador controller) {
		this.controller = controller;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.limpiarTipoModificador();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnSeleccionarCuentaListener implements ActionListener {
	private final AdminAsociacionCuentaModificador controller;
	public BtnSeleccionarCuentaListener(AdminAsociacionCuentaModificador controller) {
		this.controller = controller;
	}
	@Override
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
	private final AdminAsociacionCuentaModificador controller;
	public BtnLimpiarCuentaListener(AdminAsociacionCuentaModificador controller) {
		this.controller = controller;
	}
	@Override
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
	private final AdminAsociacionCuentaModificador controller;
	public BtnAgregarListener(AdminAsociacionCuentaModificador controller) {
		this.controller = controller;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openAgregarAsocioacionCuentaModificador();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnModificarListener implements ActionListener {
	private final AdminAsociacionCuentaModificador controller;
	public BtnModificarListener(AdminAsociacionCuentaModificador controller) {
		this.controller = controller;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openModificarAsocioacionCuentaModificador();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnEliminarListener implements ActionListener {
	private final AdminAsociacionCuentaModificador controller;
	public BtnEliminarListener(AdminAsociacionCuentaModificador controller) {
		this.controller = controller;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openEliminarAsociacionCuentaModificador();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}
