package com.trascender.caja.gui.abmMovimientoCajaChica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.caja.gui.abmCajaChica.AdminCajaChica;
import com.trascender.caja.gui.abmConceptoMovimientoCajaChica.AdminConceptoMovimientoCajaChica;
import com.trascender.caja.gui.abmMovimientoCajaChica.abm.AgregarMovimientoCajaChica;
import com.trascender.caja.gui.abmMovimientoCajaChica.abm.EliminarMovimientoCajaChica;
import com.trascender.caja.gui.abmMovimientoCajaChica.abm.ModificarMovimientoCajaChica;
import com.trascender.caja.gui.main.CajaGUI;
import com.trascender.contabilidad.recurso.persistent.CajaChica;
import com.trascender.contabilidad.recurso.persistent.ConceptoMovimientoCajaChica;
import com.trascender.contabilidad.recurso.persistent.MovimientoCajaChica;
import com.trascender.gui.framework.abmStandard.AdminController;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Conversor;

public class AdminMovimientoCajaChica extends AdminController<MovimientoCajaChica> {

	private AdminMovimientoCajaChicaView view;
	private MovimientoCajaChicaTableModel tableModel = new MovimientoCajaChicaTableModel();
	private MovimientoCajaChicaBusquedaModel busquedaModel = new MovimientoCajaChicaBusquedaModel();
	
	public AdminMovimientoCajaChica(JDialog owner) throws Exception {
		this.view = new AdminMovimientoCajaChicaView(owner);
		this.init();
	}
	
	public AdminMovimientoCajaChica(JFrame owner) throws Exception {
		this.view = new AdminMovimientoCajaChicaView(owner);
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
		this.getView().getPnlBotonesSeleccionConcepto().getBtnSeleccionar().addActionListener(new BtnSeleccionarConceptoListener(this));
		this.getView().getPnlBotonesSeleccionConcepto().getBtnLimpiar().addActionListener(new BtnLimpiarConceptoListener(this));
		this.getView().getPnlBotonesSeleccionCajaChica().getBtnSeleccionar().addActionListener(new BtnSeleccionarCajaChicaListener(this));
		this.getView().getPnlBotonesSeleccionCajaChica().getBtnLimpiar().addActionListener(new BtnLimpiarCajaChicaListener(this));
		
		this.getView().getPnlBotonesBusqueda().getBtnBuscar().addActionListener(new BtnBuscarListener(this));
		
		this.getView().getPnlPie().getBtnAgregar().addActionListener(new BtnAgregarListener(this));
		this.getView().getPnlPie().getBtnModificar().addActionListener(new BtnModificarListener(this));
		this.getView().getPnlPie().getBtnEliminar().addActionListener(new BtnEliminarListener(this));
	}
	
	@Override
	protected void actualizarBusquedaModel() {
		MovimientoCajaChicaBusquedaModel locModel = this.getView().getBusquedaModel();
		
		locModel.setFechaDesde(Conversor.getDate(this.getView().getTfFechaDesde().getText()));
		locModel.setFechaHasta(Conversor.getDate(this.getView().getTfFechaHasta().getText()));
		
		locModel.fireActualizarDatos();
	}

	@Override
	protected void actualizarBusquedaView() {
		this.getView().getTfFechaDesde().setValue(Conversor.getString(this.getBusquedaModel().getFechaDesde()));
		this.getView().getTfFechaHasta().setValue(Conversor.getString(this.getBusquedaModel().getFechaHasta()));
		this.getView().getTfConcepto().setText(Conversor.getVacioSiNull(this.getBusquedaModel().getConceptoMovimiento()));
		this.getView().getTfCajaChica().setText(Conversor.getVacioSiNull(this.getBusquedaModel().getCajaChica()));
	}

	@Override
	protected MovimientoCajaChicaBusquedaModel getBusquedaModel() {
		return busquedaModel;
	}

	@Override
	protected MovimientoCajaChicaTableModel getTableModel() {
		return tableModel;
	}

	@Override
	protected AdminMovimientoCajaChicaView getView() {
		return view;
	}
	
	void buscar() throws Exception {
		final AdminMovimientoCajaChica controller = this;
		Thread locThread = new Thread(new Runnable(){
			public void run() {
				try {
					controller.actualizarBusquedaModel();
					controller.getView().iniBusqueda();
					List<MovimientoCajaChica> locLista = controller.getBusquedaModel().buscar();
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
	
	void seleccionarConcepto() throws Exception {
		AdminConceptoMovimientoCajaChica adminConceptoMovimientoCajaChica = new AdminConceptoMovimientoCajaChica(this.getView());
		this.actualizarBusquedaModel();
		ConceptoMovimientoCajaChica locConceptoMovimientoCajaChica = adminConceptoMovimientoCajaChica.openSelect();
		if (locConceptoMovimientoCajaChica != null) {
			this.getBusquedaModel().setConceptoMovimiento(locConceptoMovimientoCajaChica);
			this.actualizarBusquedaView();
		}
	}
	
	void limpiarConcepto() throws Exception {
		this.getBusquedaModel().setConceptoMovimiento(null);
		this.actualizarBusquedaModel();
		this.actualizarBusquedaView();
	}
	
	void seleccionarCajaChica() throws Exception {
		AdminCajaChica adminCajaChica = new AdminCajaChica(this.getView());
		this.actualizarBusquedaModel();
		CajaChica locCajaChica = adminCajaChica.openSelect();
		if (locCajaChica != null) {
			this.getBusquedaModel().setCajaChica(locCajaChica);
			this.actualizarBusquedaView();
		}
	}
	
	void limpiarCajaChica() throws Exception {
		this.getBusquedaModel().setCajaChica(null);
		this.actualizarBusquedaModel();
		this.actualizarBusquedaView();
	}
	
	protected void openAgregarMovimientoCajaChica() throws Exception {
		AgregarMovimientoCajaChica agregarMovimientoCajaChica = new AgregarMovimientoCajaChica(this.getView());
		agregarMovimientoCajaChica.open();
		if (agregarMovimientoCajaChica.isOperacionRealizada()) {
			this.buscar();
		}
	}
	
	protected void openModificarMovimientoCajaChica() throws Exception {
		MovimientoCajaChica locMovimientoCajaChica = this.getSelectedRow();
		if (locMovimientoCajaChica != null) {
			locMovimientoCajaChica = CajaGUI.getInstance().getAdminSystemsCaja().getSystemAdministracionCajaChica().findMovimientoCajaChicaByID(locMovimientoCajaChica.getIdMovimientoCajaChica());
			ModificarMovimientoCajaChica locModificarMovimientoCajaChica = new ModificarMovimientoCajaChica(this.getView());
			locModificarMovimientoCajaChica.getAbmModel().setObjetoABM(locMovimientoCajaChica);
			locModificarMovimientoCajaChica.actualizarView();
			locModificarMovimientoCajaChica.open();
			if (locModificarMovimientoCajaChica.isOperacionRealizada()) {
				this.getTableModel().updateRow(locMovimientoCajaChica);
			}
		}
	}
	
	protected void openEliminarMovimientoCajaChica() throws Exception {
		MovimientoCajaChica locMovimientoCajaChica = this.getSelectedRow();
		if (locMovimientoCajaChica != null) {
			locMovimientoCajaChica = CajaGUI.getInstance().getAdminSystemsCaja().getSystemAdministracionCajaChica().findMovimientoCajaChicaByID(locMovimientoCajaChica.getIdMovimientoCajaChica());
			EliminarMovimientoCajaChica locEliminarMovimientoCajaChica = new EliminarMovimientoCajaChica(this.getView());
			locEliminarMovimientoCajaChica.getAbmModel().setObjetoABM(locMovimientoCajaChica);
			locEliminarMovimientoCajaChica.actualizarView();
			locEliminarMovimientoCajaChica.open();
			if (locEliminarMovimientoCajaChica.isOperacionRealizada()) {
				this.getTableModel().deleteRow(locMovimientoCajaChica);
			}
		}
	}
	
}

class BtnBuscarListener implements ActionListener {
	private AdminMovimientoCajaChica controller;
	public BtnBuscarListener(AdminMovimientoCajaChica controller) {
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

class BtnSeleccionarConceptoListener implements ActionListener {
	private AdminMovimientoCajaChica controller;
	public BtnSeleccionarConceptoListener(AdminMovimientoCajaChica controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.seleccionarConcepto();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnLimpiarConceptoListener implements ActionListener {
	private AdminMovimientoCajaChica controller;
	public BtnLimpiarConceptoListener(AdminMovimientoCajaChica controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.limpiarConcepto();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}
	
class BtnSeleccionarCajaChicaListener implements ActionListener {
	private AdminMovimientoCajaChica controller;
	public BtnSeleccionarCajaChicaListener(AdminMovimientoCajaChica controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.seleccionarCajaChica();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnLimpiarCajaChicaListener implements ActionListener {
	private AdminMovimientoCajaChica controller;
	public BtnLimpiarCajaChicaListener(AdminMovimientoCajaChica controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.limpiarCajaChica();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnAgregarListener implements ActionListener {
	private AdminMovimientoCajaChica controller;
	public BtnAgregarListener(AdminMovimientoCajaChica controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openAgregarMovimientoCajaChica();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnModificarListener implements ActionListener {
	private AdminMovimientoCajaChica controller;
	public BtnModificarListener(AdminMovimientoCajaChica controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openModificarMovimientoCajaChica();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnEliminarListener implements ActionListener {
	private AdminMovimientoCajaChica controller;
	public BtnEliminarListener(AdminMovimientoCajaChica controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openEliminarMovimientoCajaChica();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

