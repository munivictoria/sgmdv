package com.trascender.contabilidad.gui.abmAsociacionCuentaConceptoIngresoVario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.trascender.contabilidad.gui.abmAsociacionCuentaConceptoIngresoVario.abm.AgregarAsociacionCuentaConceptoIngresoVario;
import com.trascender.contabilidad.gui.abmAsociacionCuentaConceptoIngresoVario.abm.EliminarAsociacionCuentaConceptoIngresoVario;
import com.trascender.contabilidad.gui.abmAsociacionCuentaConceptoIngresoVario.abm.ModificarAsociacionCuentaConceptoIngresoVario;
import com.trascender.contabilidad.gui.abmConceptoIngresoVario.AdminConceptoIngresoVario;
import com.trascender.contabilidad.gui.abmCuenta.AdminCuenta;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.ConceptoIngresoVario;
import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.contabilidad.recurso.persistent.CuentaConceptoIngresoVario;
import com.trascender.framework.recurso.transients.Periodo;
import com.trascender.framework.util.Periodicidad;
import com.trascender.gui.framework.abmStandard.AdminController;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Conversor;
import com.trascender.gui.framework.util.Validador;

public class AdminAsociacionCuentaConceptoIngresoVario extends AdminController<CuentaConceptoIngresoVario> {
	
	private AsociacionCuentaConceptoIngresoVarioBusquedaModel busquedaModel = new AsociacionCuentaConceptoIngresoVarioBusquedaModel();
	private AsociacionCuentaConceptoIngresoVarioTableModel tableModel;
	private AdminAsociacionCuentaConceptoIngresoVarioView view;
	

	public AdminAsociacionCuentaConceptoIngresoVario(JDialog owner) {
		this.view = new AdminAsociacionCuentaConceptoIngresoVarioView(owner);
		try {
			this.tableModel = new AsociacionCuentaConceptoIngresoVarioTableModel();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.init();
	}
	
	public AdminAsociacionCuentaConceptoIngresoVario(JFrame owner) {
		this.view = new AdminAsociacionCuentaConceptoIngresoVarioView(owner);
		try {
			this.tableModel = new AsociacionCuentaConceptoIngresoVarioTableModel();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		this.getView().getPnlBotonesSeleccionConceptoIngresoVario().getBtnSeleccionar().addActionListener(new BtnSeleccionarConceptoIngresoVarioListener(this));
		this.getView().getPnlBotonesSeleccionConceptoIngresoVario().getBtnLimpiar().addActionListener(new BtnLimpiarIngresoVarioListener(this));
		this.getView().getPnlBotonesSeleccionCuenta().getBtnSeleccionar().addActionListener(new BtnSeleccionarCuentaListener(this));
		this.getView().getPnlBotonesSeleccionCuenta().getBtnLimpiar().addActionListener(new BtnLimpiarCuentaListener(this));
		
		this.getView().getPnlBotonesBusqueda().getBtnBuscar().addActionListener(new BtnBuscarListener(this));
		
		this.getView().getPnlPie().getBtnAgregar().addActionListener(new BtnAgregarListener(this));
		this.getView().getPnlPie().getBtnModificar().addActionListener(new BtnModificarListener(this));
		this.getView().getPnlPie().getBtnEliminar().addActionListener(new BtnEliminarListener(this));
	}
	
	@Override
	protected void actualizarBusquedaModel() {
		Integer locAnio = Conversor.getInteger(this.getView().getTfAnioPeriodo().getText());
		Periodo locPeriodo = null;
		if (locAnio != null) {
			try {
				locPeriodo = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemRegistroValuado().getPeriodo(Periodicidad.ANUAL, 1, locAnio);
			}
			catch (Exception ex) {
				ex.printStackTrace();
				AppManager.getInstance().showErrorMsg(this.getView(), ex.getMessage());
			}
		}
		this.getBusquedaModel().setPeriodo(locPeriodo);

		this.getBusquedaModel().fireActualizarDatos();
	}

	@Override
	protected void actualizarBusquedaView() {
		Periodo locPeriodo = this.getBusquedaModel().getPeriodo();
		String anio = "";
		if (locPeriodo != null) {
			anio = String.valueOf(locPeriodo.getFechaInicio().get(Calendar.YEAR));
		}
		this.getView().getTfAnioPeriodo().setText(anio);

		this.getView().getTfConceptoIngresoVario().setText(Conversor.getVacioSiNull(this.getBusquedaModel().getConceptoIngresoVario()));
		this.getView().getTfCuenta().setText(Conversor.getVacioSiNull(this.getBusquedaModel().getCuenta()));
	}

	@Override
	protected AsociacionCuentaConceptoIngresoVarioBusquedaModel getBusquedaModel() {
		return this.busquedaModel;
	}

	@Override
	protected AsociacionCuentaConceptoIngresoVarioTableModel getTableModel() {
		return this.tableModel;
	}

	@Override
	protected AdminAsociacionCuentaConceptoIngresoVarioView getView() {
		return this.view;
	}

	void buscar() {
		if (this.validarDatos()) {
			final AdminAsociacionCuentaConceptoIngresoVario controller = this;
			Thread locThread = new Thread(new Runnable() {
				public void run() {
					try {
						controller.actualizarBusquedaModel();
						controller.getView().iniBusqueda();
						List<CuentaConceptoIngresoVario> locCuentaConceptoIngresoVario = controller.getBusquedaModel().buscar();
						controller.getTableModel().setListaObjetos(locCuentaConceptoIngresoVario);
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
	
	void seleccionarConceptoIngresoVario() throws Exception {
		AdminConceptoIngresoVario adminConceptoIngresoVario = new AdminConceptoIngresoVario(this.getView());
		this.actualizarBusquedaModel();
		ConceptoIngresoVario locConceptoIngresoVario = adminConceptoIngresoVario.openSelect();
		if (locConceptoIngresoVario != null) {
			this.getBusquedaModel().setConceptoIngresoVario(locConceptoIngresoVario);
			this.actualizarBusquedaView();
		}
	}
	
	void limpiarConceptoIngresoVario() throws Exception {
		this.getBusquedaModel().setConceptoIngresoVario(null);
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
	
	@Override
	public boolean validarDatos() {
		boolean validacionOK = true;
		
		List<Object> attAnio = new ArrayList<Object>();
		List<JLabel> lblAnio = new ArrayList<JLabel>();
		List<Integer> longAnio = new ArrayList<Integer>();
		
		attAnio.add(this.getView().getTfAnioPeriodo().getText());
		lblAnio.add(this.getView().getLblAnioPeriodo());
		longAnio.add(4);
		
		List<String> listaErrores = new ArrayList<String>();
		try {
			listaErrores.addAll(Validador.validarEnteros(attAnio, lblAnio));
			listaErrores.addAll(Validador.validarLongitudExacta(attAnio, lblAnio, longAnio));
		} catch (GuiException ex) {
			ex.printStackTrace();
			return false;
		}
		
		if (!listaErrores.isEmpty()) {
			validacionOK = false;
			this.mostrarErroresValidacion(listaErrores);
		}
		
		return validacionOK;
	}
	
	public void agregarAsociacion() {
		
	}
	

	public void modificarAsociacion() {

	}

	public void eliminarAsociacion() {

	}
	
	void openAgregarAsocioacionCuentaConceptoIngresoVario() throws Exception {
		AgregarAsociacionCuentaConceptoIngresoVario agregarAsociacionCuentaConceptoIngresoVario = new AgregarAsociacionCuentaConceptoIngresoVario(this.getView());
		agregarAsociacionCuentaConceptoIngresoVario.open();
		if (agregarAsociacionCuentaConceptoIngresoVario.isOperacionRealizada() && this.getTableModel().getRowCount() > 0) {
			this.buscar();
		}
	}
	
	void openModificarAsocioacionCuentaConceptoIngresoVario() throws Exception {
		CuentaConceptoIngresoVario locCuentaConceptoIngresoVario = this.getSelectedRow();
		if (locCuentaConceptoIngresoVario != null) {
			locCuentaConceptoIngresoVario = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().getCuentaConceptoIngresoVarioByID(locCuentaConceptoIngresoVario.getIdAsociacionCuenta());
			ModificarAsociacionCuentaConceptoIngresoVario modificarAsociacionCuentaConceptoIngresoVario = new ModificarAsociacionCuentaConceptoIngresoVario(this.getView());
			modificarAsociacionCuentaConceptoIngresoVario.getAbmModel().setObjetoABM(locCuentaConceptoIngresoVario);
			modificarAsociacionCuentaConceptoIngresoVario.actualizarView();
			modificarAsociacionCuentaConceptoIngresoVario.open();
			if (modificarAsociacionCuentaConceptoIngresoVario.isOperacionRealizada() && this.getTableModel().getRowCount() > 0) {
				this.buscar();
			}
		}
	}
	
	void openEliminarAsociacionCuentaConceptoIngresoVario() throws Exception {
		CuentaConceptoIngresoVario locCuentaConceptoIngresoVario = this.getSelectedRow();
		if (locCuentaConceptoIngresoVario != null) {
			locCuentaConceptoIngresoVario = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().getCuentaConceptoIngresoVarioByID(locCuentaConceptoIngresoVario.getIdAsociacionCuenta());
			EliminarAsociacionCuentaConceptoIngresoVario eliminarAsociacionCuentaConceptoIngresoVario = new EliminarAsociacionCuentaConceptoIngresoVario(this.getView());
			eliminarAsociacionCuentaConceptoIngresoVario.getAbmModel().setObjetoABM(locCuentaConceptoIngresoVario);
			eliminarAsociacionCuentaConceptoIngresoVario.actualizarView();
			eliminarAsociacionCuentaConceptoIngresoVario.open();
			if (eliminarAsociacionCuentaConceptoIngresoVario.isOperacionRealizada() && this.getTableModel().getRowCount() > 0) {
				this.buscar();
			}
		}
	}
	
}

class BtnSeleccionarConceptoIngresoVarioListener implements ActionListener {
	AdminAsociacionCuentaConceptoIngresoVario controller;
	
	public BtnSeleccionarConceptoIngresoVarioListener(
			AdminAsociacionCuentaConceptoIngresoVario controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.seleccionarConceptoIngresoVario();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}

class BtnLimpiarIngresoVarioListener implements ActionListener {
	AdminAsociacionCuentaConceptoIngresoVario controller;
	
	public BtnLimpiarIngresoVarioListener(
			AdminAsociacionCuentaConceptoIngresoVario controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.limpiarConceptoIngresoVario();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}

class BtnSeleccionarCuentaListener implements ActionListener {
	AdminAsociacionCuentaConceptoIngresoVario controller;
	
	public BtnSeleccionarCuentaListener(
			AdminAsociacionCuentaConceptoIngresoVario controller) {
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
	AdminAsociacionCuentaConceptoIngresoVario controller;
	
	public BtnLimpiarCuentaListener(
			AdminAsociacionCuentaConceptoIngresoVario controller) {
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

class BtnBuscarListener implements ActionListener {
	AdminAsociacionCuentaConceptoIngresoVario controller;
	
	public BtnBuscarListener(
			AdminAsociacionCuentaConceptoIngresoVario controller) {
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

class BtnAgregarListener implements ActionListener {
	AdminAsociacionCuentaConceptoIngresoVario controller;

	public BtnAgregarListener(
			AdminAsociacionCuentaConceptoIngresoVario controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openAgregarAsocioacionCuentaConceptoIngresoVario();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnModificarListener implements ActionListener {
	AdminAsociacionCuentaConceptoIngresoVario controller;

	public BtnModificarListener(
			AdminAsociacionCuentaConceptoIngresoVario controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openModificarAsocioacionCuentaConceptoIngresoVario();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnEliminarListener implements ActionListener {
	AdminAsociacionCuentaConceptoIngresoVario controller;

	public BtnEliminarListener(
			AdminAsociacionCuentaConceptoIngresoVario controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openEliminarAsociacionCuentaConceptoIngresoVario();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

