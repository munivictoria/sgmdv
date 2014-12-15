package com.trascender.contabilidad.gui.abmAsociacionCuentaInteresRecargo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.trascender.contabilidad.gui.abmAsociacionCuentaInteresRecargo.abm.AgregarAsociacionCuentaInteresRecargo;
import com.trascender.contabilidad.gui.abmAsociacionCuentaInteresRecargo.abm.EliminarAsociacionCuentaInteresRecargo;
import com.trascender.contabilidad.gui.abmAsociacionCuentaInteresRecargo.abm.ModificarAsociacionCuentaInteresRecargo;
import com.trascender.contabilidad.gui.abmConceptoPorMora.AdminConceptoPorMora;
import com.trascender.contabilidad.gui.abmCuenta.AdminCuenta;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.contabilidad.recurso.persistent.CuentaInteresRecargo;
import com.trascender.gui.framework.abmStandard.AdminController;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Conversor;
import com.trascender.gui.framework.util.Validador;
import com.trascender.habilitaciones.recurso.persistent.ConceptoPorMora;

public class AdminAsociacionCuentaInteresRecargo extends AdminController<CuentaInteresRecargo> {

	private final AdminAsociacionCuentaInteresRecargoView view;
	private final AsociacionCuentaInteresRecargoBusquedaModel busquedaModel = new AsociacionCuentaInteresRecargoBusquedaModel();
	private final AsociacionCuentaInteresRecargoTableModel tableModel = new AsociacionCuentaInteresRecargoTableModel();

	public AdminAsociacionCuentaInteresRecargo(JFrame owner) throws Exception {
		this.view = new AdminAsociacionCuentaInteresRecargoView(owner);
		this.init();
	}

	public AdminAsociacionCuentaInteresRecargo(JDialog owner) throws Exception {
		this.view = new AdminAsociacionCuentaInteresRecargoView(owner);
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
		this.getView().getPnlBotonesSeleccionConceptoPorMora().getBtnSeleccionar().addActionListener(new BtnSeleccionarConceptoPorMoraListener(this));
		this.getView().getPnlBotonesSeleccionConceptoPorMora().getBtnLimpiar().addActionListener(new BtnLimpiarConceptoPorMoraListener(this));
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
		this.getView().getTfConceptoPorMora().setText(Conversor.getVacioSiNull(this.getBusquedaModel().getConceptoPorMora()));
		this.getView().getTfCuenta().setText(Conversor.getVacioSiNull(this.getBusquedaModel().getCuenta()));
	}

	@Override
	protected AsociacionCuentaInteresRecargoBusquedaModel getBusquedaModel() {
		return this.busquedaModel;
	}

	@Override
	protected AsociacionCuentaInteresRecargoTableModel getTableModel() {
		return this.tableModel;
	}

	@Override
	protected AdminAsociacionCuentaInteresRecargoView getView() {
		return this.view;
	}

	void buscar() {
		if (this.validarDatos()) {
			final AdminAsociacionCuentaInteresRecargo controller = this;
			Thread locThread = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						controller.actualizarBusquedaModel();
						controller.getView().iniBusqueda();
						List<CuentaInteresRecargo> locListaAsociaciones = controller.getBusquedaModel().buscar();
						controller.getTableModel().setListaObjetos(locListaAsociaciones);
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

	void seleccionarConceptoPorMora() throws Exception {
		AdminConceptoPorMora adminConceptoPorMora = new AdminConceptoPorMora(this.getView());
		this.actualizarBusquedaModel();
		ConceptoPorMora locConcepto = adminConceptoPorMora.openSelect();
		if (locConcepto != null) {
			this.getBusquedaModel().setConceptoPorMora(locConcepto);
			this.actualizarBusquedaView();
		}
	}

	void limpiarConceptoPorMora() throws Exception {
		this.getBusquedaModel().setConceptoPorMora(null);
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


	void openAgregarAsocioacionCuentaInteresRecargo() throws Exception {
		AgregarAsociacionCuentaInteresRecargo agregarAsociacionCuentaInteresRecargo = new AgregarAsociacionCuentaInteresRecargo(this.getView());
		agregarAsociacionCuentaInteresRecargo.open();
		if (agregarAsociacionCuentaInteresRecargo.isOperacionRealizada() && this.getTableModel().getRowCount() > 0) {
			this.buscar();
		}
	}

	void openModificarAsocioacionCuentaInteresRecargo() throws Exception {
		CuentaInteresRecargo locAsociacionCuenta = this.getSelectedRow();
		if (locAsociacionCuenta != null) {
			locAsociacionCuenta = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().getCuentaInteresRecargoByID((locAsociacionCuenta.getIdAsociacionCuenta()));
			ModificarAsociacionCuentaInteresRecargo modificarAsociacionCuentaInteresRecargo = new ModificarAsociacionCuentaInteresRecargo(this.getView());
			modificarAsociacionCuentaInteresRecargo.getAbmModel().setObjetoABM(locAsociacionCuenta);
			modificarAsociacionCuentaInteresRecargo.actualizarView();
			modificarAsociacionCuentaInteresRecargo.open();
			if (modificarAsociacionCuentaInteresRecargo.isOperacionRealizada() && this.getTableModel().getRowCount() > 0) {
				this.buscar();
			}
		}
	}

	void openEliminarAsociacionCuentaInteresRecargo() throws Exception {
		CuentaInteresRecargo locAsociacion = this.getSelectedRow();
		if (locAsociacion != null) {
			locAsociacion = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().getCuentaInteresRecargoByID((locAsociacion.getIdAsociacionCuenta()));
			EliminarAsociacionCuentaInteresRecargo mliminarAsociacionCuentaInteresRecargo = new EliminarAsociacionCuentaInteresRecargo(this.getView());
			mliminarAsociacionCuentaInteresRecargo.getAbmModel().setObjetoABM(locAsociacion);
			mliminarAsociacionCuentaInteresRecargo.actualizarView();
			mliminarAsociacionCuentaInteresRecargo.open();
			if (mliminarAsociacionCuentaInteresRecargo.isOperacionRealizada() && this.getTableModel().getRowCount() > 0) {
				this.buscar();
			}
		}
	}

}


class BtnBuscarListener implements ActionListener {
	private final AdminAsociacionCuentaInteresRecargo controller;
	public BtnBuscarListener(AdminAsociacionCuentaInteresRecargo controller) {
		this.controller = controller;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		this.controller.buscar();
	}
}

class BtnSeleccionarConceptoPorMoraListener implements ActionListener {
	private final AdminAsociacionCuentaInteresRecargo controller;
	public BtnSeleccionarConceptoPorMoraListener(AdminAsociacionCuentaInteresRecargo controller) {
		this.controller = controller;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.seleccionarConceptoPorMora();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnLimpiarConceptoPorMoraListener implements ActionListener {
	private final AdminAsociacionCuentaInteresRecargo controller;
	public BtnLimpiarConceptoPorMoraListener(AdminAsociacionCuentaInteresRecargo controller) {
		this.controller = controller;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.limpiarConceptoPorMora();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnSeleccionarCuentaListener implements ActionListener {
	private final AdminAsociacionCuentaInteresRecargo controller;
	public BtnSeleccionarCuentaListener(AdminAsociacionCuentaInteresRecargo controller) {
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
	private final AdminAsociacionCuentaInteresRecargo controller;
	public BtnLimpiarCuentaListener(AdminAsociacionCuentaInteresRecargo controller) {
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
	private final AdminAsociacionCuentaInteresRecargo controller;
	public BtnAgregarListener(AdminAsociacionCuentaInteresRecargo controller) {
		this.controller = controller;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openAgregarAsocioacionCuentaInteresRecargo();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnModificarListener implements ActionListener {
	private final AdminAsociacionCuentaInteresRecargo controller;
	public BtnModificarListener(AdminAsociacionCuentaInteresRecargo controller) {
		this.controller = controller;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openModificarAsocioacionCuentaInteresRecargo();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnEliminarListener implements ActionListener {
	private final AdminAsociacionCuentaInteresRecargo controller;
	public BtnEliminarListener(AdminAsociacionCuentaInteresRecargo controller) {
		this.controller = controller;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openEliminarAsociacionCuentaInteresRecargo();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}
