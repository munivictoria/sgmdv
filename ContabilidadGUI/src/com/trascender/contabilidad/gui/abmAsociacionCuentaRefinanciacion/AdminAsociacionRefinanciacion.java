package com.trascender.contabilidad.gui.abmAsociacionCuentaRefinanciacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.trascender.contabilidad.gui.abmAsociacionCuentaRefinanciacion.abm.AgregarAsociacionRefinanciacion;
import com.trascender.contabilidad.gui.abmAsociacionCuentaRefinanciacion.abm.ConsultarAsociacionRefinanciacion;
import com.trascender.contabilidad.gui.abmAsociacionCuentaRefinanciacion.abm.EliminarAsociacionRefinanciacion;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.AsociacionRefinanciacion;
import com.trascender.contabilidad.recurso.persistent.ParametroAsociacion;
import com.trascender.framework.util.Periodicidad;
import com.trascender.gui.framework.abmStandard.AdminController;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Conversor;
import com.trascender.gui.framework.util.Validador;

public class AdminAsociacionRefinanciacion extends AdminController<AsociacionRefinanciacion> {

	private AdminAsociacionRefinanciacionView view;
	private AsociacionRefinanciacionBusquedaModel busquedaModel = new AsociacionRefinanciacionBusquedaModel();
	private AsociacionRefinanciacionTableModel tableModel = new AsociacionRefinanciacionTableModel();
	
	public AdminAsociacionRefinanciacion(JFrame owner) throws Exception {
		this.view = new AdminAsociacionRefinanciacionView(owner);
		this.init();
	}
	
	public AdminAsociacionRefinanciacion(JDialog owner) throws Exception {
		this.view = new AdminAsociacionRefinanciacionView(owner);
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
		this.getView().getPnlTabla().getTblDatos().setModel(this.getTableModel());
	}
	
	private void setListeners() {
		this.getView().getPnlBotonesBusqueda().getBtnBuscar().addActionListener(new BtnBuscarListener(this));
		
		this.getView().getPnlPie().getBtnAgregar().addActionListener(new BtnAgregarListener(this));
		this.getView().getPnlPie().getBtnEliminar().addActionListener(new BtnEliminarListener(this));
		this.getView().getPnlPie().getBtnConsultar().addActionListener(new BtnConsultarListener(this));
	}

	@Override
	protected void actualizarBusquedaModel() {
		this.getBusquedaModel().fireActualizarDatos();
	}

	@Override
	protected void actualizarBusquedaView() {
	}

	@Override
	protected AdminAsociacionRefinanciacionView getView() {
		return this.view;
	}

	void buscar() {
		if (this.validarDatos()) {
			final AdminAsociacionRefinanciacion controller = this;
			Thread locThread = new Thread(new Runnable() {
				public void run() {
					try {
						controller.actualizarBusquedaModel();
						controller.getView().iniBusqueda();
						List<AsociacionRefinanciacion> locAsociacionRefinanciacion = controller.getBusquedaModel().buscar();
						controller.getTableModel().setListaObjetos(locAsociacionRefinanciacion);
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
	
	void openAgregarAsocioacionCuentaRefinanciacion() throws Exception {
		AgregarAsociacionRefinanciacion agregarAsociacionRefinanciacion = new AgregarAsociacionRefinanciacion(this.getView());
		agregarAsociacionRefinanciacion.open();
		if (agregarAsociacionRefinanciacion.isOperacionRealizada()) {
			this.buscar();
		}
	}
	
	void openConsultarAsociacionRefinanciacion() throws Exception {
		AsociacionRefinanciacion locAsociacionRefinanciacion = this.getSelectedRow();
		if (locAsociacionRefinanciacion != null) {
			locAsociacionRefinanciacion = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().getAsociacionRefinanciacionByID(locAsociacionRefinanciacion.getIdAsociacionRefinanciacion());
			ConsultarAsociacionRefinanciacion consultarAsociacionRefinanciacion = new ConsultarAsociacionRefinanciacion(this.getView());
			consultarAsociacionRefinanciacion.getAbmModel().setObjetoABM(locAsociacionRefinanciacion);
			consultarAsociacionRefinanciacion.updateView();
			List<ParametroAsociacion> locListaParametros = new ArrayList<ParametroAsociacion>();
//			for (LineaAsociacionRefinanciacion unaLinea : locAsociacionRefinanciacion.getLineaAsociacionRefinanciacion()) {
//				locListaParametros.add(unaLinea.getParametroAsociacion());
//			}
			consultarAsociacionRefinanciacion.getAbmModel().setListaParametrosAsociacion(locListaParametros);
			consultarAsociacionRefinanciacion.actualizarView();
			consultarAsociacionRefinanciacion.open();
			if (consultarAsociacionRefinanciacion.isOperacionRealizada() && this.getTableModel().getRowCount() > 0) {
				this.buscar();
			}
		}
	}
	
	void openEliminarAsociacionRefinanciacion() throws Exception {
		AsociacionRefinanciacion locAsociacionRefinanciacion = this.getSelectedRow();
		if (locAsociacionRefinanciacion != null) {
			locAsociacionRefinanciacion = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().getAsociacionRefinanciacionByID(locAsociacionRefinanciacion.getIdAsociacionRefinanciacion());
			EliminarAsociacionRefinanciacion eliminarAsociacionRefinanciacion = new EliminarAsociacionRefinanciacion(this.getView());
			eliminarAsociacionRefinanciacion.getAbmModel().setObjetoABM(locAsociacionRefinanciacion);
			eliminarAsociacionRefinanciacion.updateView();
			List<ParametroAsociacion> locListaParametros = new ArrayList<ParametroAsociacion>();
			eliminarAsociacionRefinanciacion.getAbmModel().setListaParametrosAsociacion(locListaParametros);
//			
			eliminarAsociacionRefinanciacion.actualizarView();
			eliminarAsociacionRefinanciacion.open();
			if (eliminarAsociacionRefinanciacion.isOperacionRealizada() && this.getTableModel().getRowCount() > 0) {
				this.buscar();
			}
		}
	}

	@Override
	public AsociacionRefinanciacionBusquedaModel getBusquedaModel() {
		return this.busquedaModel;
	}

	@Override
	public AsociacionRefinanciacionTableModel getTableModel() {
		return this.tableModel;
	}
}


class BtnBuscarListener implements ActionListener {
	private AdminAsociacionRefinanciacion controller;
	public BtnBuscarListener(AdminAsociacionRefinanciacion controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		this.controller.buscar();
	}
}

class BtnAgregarListener implements ActionListener {
	private AdminAsociacionRefinanciacion controller;
	public BtnAgregarListener(AdminAsociacionRefinanciacion controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openAgregarAsocioacionCuentaRefinanciacion();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnConsultarListener implements ActionListener{
	
	private AdminAsociacionRefinanciacion controller;

	public BtnConsultarListener(AdminAsociacionRefinanciacion controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openConsultarAsociacionRefinanciacion();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnEliminarListener implements ActionListener {
	private AdminAsociacionRefinanciacion controller;
	
	public BtnEliminarListener(AdminAsociacionRefinanciacion controller) {
		this.controller = controller;
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openEliminarAsociacionRefinanciacion();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}