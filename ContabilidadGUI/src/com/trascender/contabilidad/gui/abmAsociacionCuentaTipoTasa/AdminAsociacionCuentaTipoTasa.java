package com.trascender.contabilidad.gui.abmAsociacionCuentaTipoTasa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.trascender.contabilidad.gui.abmAsociacionCuentaTipoTasa.abm.AgregarAsociacionCuentaTipoTasa;
import com.trascender.contabilidad.gui.abmAsociacionCuentaTipoTasa.abm.EliminarAsociacionCuentaTipoTasa;
import com.trascender.contabilidad.gui.abmAsociacionCuentaTipoTasa.abm.ModificarAsociacionCuentaTipoTasa;
import com.trascender.contabilidad.gui.abmCuenta.AdminCuenta;
import com.trascender.contabilidad.gui.abmTipoTasa.AdminTipoTasa;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.contabilidad.recurso.persistent.CuentaTipoTasa;
import com.trascender.gui.framework.abmStandard.AdminController;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Conversor;
import com.trascender.gui.framework.util.Validador;
import com.trascender.habilitaciones.recurso.persistent.TipoTasa;

public class AdminAsociacionCuentaTipoTasa extends AdminController<CuentaTipoTasa> {

	private final AdminAsociacionCuentaTipoTasaView view;
	private final AsociacionCuentaTipoTasaBusquedaModel busquedaModel = new AsociacionCuentaTipoTasaBusquedaModel();
	private final AsociacionCuentaTipoTasaTableModel tableModel = new AsociacionCuentaTipoTasaTableModel();

	public AdminAsociacionCuentaTipoTasa(JFrame owner) throws Exception {
		this.view = new AdminAsociacionCuentaTipoTasaView(owner);
		this.init();
	}

	public AdminAsociacionCuentaTipoTasa(JDialog owner) throws Exception {
		this.view = new AdminAsociacionCuentaTipoTasaView(owner);
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
		this.getView().getPnlBotonesSeleccionTipoTasa().getBtnSeleccionar().addActionListener(new BtnSeleccionarTipoTasaListener(this));
		this.getView().getPnlBotonesSeleccionTipoTasa().getBtnLimpiar().addActionListener(new BtnLimpiarTipoTasaListener(this));
		this.getView().getPnlBotonesSeleccionCuenta().getBtnSeleccionar().addActionListener(new BtnSeleccionarCuentaListener(this));
		this.getView().getPnlBotonesSeleccionCuenta().getBtnLimpiar().addActionListener(new BtnLimpiarCuentaListener(this));

		this.getView().getPnlBotonesBusqueda().getBtnBuscar().addActionListener(new BtnBuscarListener(this));

		this.getView().getPnlPie().getBtnAgregar().addActionListener(new BtnAgregarListener(this));
		this.getView().getPnlPie().getBtnModificar().addActionListener(new BtnModificarListener(this));
		this.getView().getPnlPie().getBtnEliminar().addActionListener(new BtnEliminarListener(this));
	}

	@Override
	protected void actualizarBusquedaModel() {
		AsociacionCuentaTipoTasaBusquedaModel locModel = this.getBusquedaModel();		
		locModel.fireActualizarDatos();
	}

	@Override
	protected void actualizarBusquedaView() {
		this.getView().getTfTipoTasa().setText(Conversor.getVacioSiNull(this.getBusquedaModel().getTipoTasa()));
		this.getView().getTfCuenta().setText(Conversor.getVacioSiNull(this.getBusquedaModel().getCuenta()));
	}

	@Override
	protected AsociacionCuentaTipoTasaBusquedaModel getBusquedaModel() {
		return this.busquedaModel;
	}

	@Override
	protected AsociacionCuentaTipoTasaTableModel getTableModel() {
		return this.tableModel;
	}

	@Override
	protected AdminAsociacionCuentaTipoTasaView getView() {
		return this.view;
	}

	@Override
	public boolean validarDatos() {
		boolean validacionOK = true;

		return validacionOK;
	}

	void openAgregarAsocioacionTipoTasaCuenta() throws Exception {
		AgregarAsociacionCuentaTipoTasa agregarAsociacionCuentaTipoTasa= new AgregarAsociacionCuentaTipoTasa(this.getView());
		agregarAsociacionCuentaTipoTasa.open();
		if (agregarAsociacionCuentaTipoTasa.isOperacionRealizada() && this.getTableModel().getRowCount() > 0) {
			this.buscar();
		}
	}

	void openModificarAsocioacionTipoTasaCuenta() throws Exception {
		CuentaTipoTasa locCuentaTipoTasa = this.getSelectedRow();
		if (locCuentaTipoTasa != null) {
			locCuentaTipoTasa = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().getCuentaTipoTasaByID(locCuentaTipoTasa.getIdAsociacionCuenta());
			ModificarAsociacionCuentaTipoTasa modificarAsociacionCuentaTipoTasa = new ModificarAsociacionCuentaTipoTasa(this.getView());
			modificarAsociacionCuentaTipoTasa.getAbmModel().setObjetoABM(locCuentaTipoTasa);
			modificarAsociacionCuentaTipoTasa.actualizarView();
			modificarAsociacionCuentaTipoTasa.open();
			if (modificarAsociacionCuentaTipoTasa.isOperacionRealizada() && this.getTableModel().getRowCount() > 0) {
				this.buscar();
			}
		}
	}

	void openEliminarAsociacionCuentaTipoTasa() throws Exception {
		CuentaTipoTasa locCuentaTipoTasa = this.getSelectedRow();
		if (locCuentaTipoTasa != null) {
			locCuentaTipoTasa = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().getCuentaTipoTasaByID(locCuentaTipoTasa.getIdAsociacionCuenta());
			EliminarAsociacionCuentaTipoTasa eliminarAsociacionCuentaTipoTasa = new EliminarAsociacionCuentaTipoTasa(this.getView());
			eliminarAsociacionCuentaTipoTasa.getAbmModel().setObjetoABM(locCuentaTipoTasa);
			eliminarAsociacionCuentaTipoTasa.actualizarView();
			eliminarAsociacionCuentaTipoTasa.open();
			if (eliminarAsociacionCuentaTipoTasa.isOperacionRealizada() && this.getTableModel().getRowCount() > 0) {
				this.buscar();
			}
		}
	}

	void buscar() throws Exception {
		if (this.validarDatos()) {
			final AdminAsociacionCuentaTipoTasa controller = this;
			Thread locThread = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						controller.actualizarBusquedaModel();
						controller.getView().iniBusqueda();
						List<CuentaTipoTasa> locCuentaTipoTasa = controller.getBusquedaModel().buscar();
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

	void seleccionarTipoTasa() throws Exception {
		AdminTipoTasa adminTipoTasa = new AdminTipoTasa(this.getView());
		this.actualizarBusquedaModel();
		TipoTasa locTipoTasa = adminTipoTasa.openSelect();

		if (locTipoTasa != null) {
			this.getBusquedaModel().setTipoTasa(locTipoTasa);
			this.actualizarBusquedaModel();
			this.actualizarBusquedaView();
		}
	}

	void limpiarTipoTasa() throws Exception {
		this.getBusquedaModel().setTipoTasa(null);
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
}

class BtnBuscarListener implements ActionListener {
	private final AdminAsociacionCuentaTipoTasa controller;
	public BtnBuscarListener(AdminAsociacionCuentaTipoTasa controller) {
		this.controller = controller;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.buscar();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnSeleccionarTipoTasaListener implements ActionListener {
	private final AdminAsociacionCuentaTipoTasa controller;
	public BtnSeleccionarTipoTasaListener(AdminAsociacionCuentaTipoTasa controller) {
		this.controller = controller;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.seleccionarTipoTasa();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnLimpiarTipoTasaListener implements ActionListener {
	private final AdminAsociacionCuentaTipoTasa controller;
	public BtnLimpiarTipoTasaListener(AdminAsociacionCuentaTipoTasa controller) {
		this.controller = controller;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.limpiarTipoTasa();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnSeleccionarCuentaListener implements ActionListener {
	private final AdminAsociacionCuentaTipoTasa controller;
	public BtnSeleccionarCuentaListener(AdminAsociacionCuentaTipoTasa controller) {
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
	private final AdminAsociacionCuentaTipoTasa controller;
	public BtnLimpiarCuentaListener(AdminAsociacionCuentaTipoTasa controller) {
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
	private final AdminAsociacionCuentaTipoTasa controller;
	public BtnAgregarListener(AdminAsociacionCuentaTipoTasa controller) {
		this.controller = controller;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openAgregarAsocioacionTipoTasaCuenta();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnModificarListener implements ActionListener {
	private final AdminAsociacionCuentaTipoTasa controller;
	public BtnModificarListener(AdminAsociacionCuentaTipoTasa controller) {
		this.controller = controller;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openModificarAsocioacionTipoTasaCuenta();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnEliminarListener implements ActionListener {
	private final AdminAsociacionCuentaTipoTasa controller;
	public BtnEliminarListener(AdminAsociacionCuentaTipoTasa controller) {
		this.controller = controller;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openEliminarAsociacionCuentaTipoTasa();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}
