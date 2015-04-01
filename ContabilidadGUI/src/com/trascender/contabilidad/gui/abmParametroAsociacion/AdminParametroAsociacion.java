package com.trascender.contabilidad.gui.abmParametroAsociacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.contabilidad.gui.abmParametroAsociacion.abm.AgregarParametroAsociacion;
import com.trascender.contabilidad.gui.abmParametroAsociacion.abm.EliminarParametroAsociacion;
import com.trascender.contabilidad.gui.abmParametroAsociacion.abm.ModificarParametroAsociacion;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.gui.framework.abmStandard.AdminController;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.saic.recurso.persistent.ParametroAsociacion;

public class AdminParametroAsociacion extends AdminController<ParametroAsociacion> {

	private AdminParametroAsociacionView view;
	private ParametroAsociacionBusquedaModel busquedaModel = new ParametroAsociacionBusquedaModel();
	private ParametroAsociacionTableModel tableModel = new ParametroAsociacionTableModel();
	
	public AdminParametroAsociacion(JFrame owner) throws Exception {
		this.view = new AdminParametroAsociacionView(owner);
		this.init();
	}
	
	public AdminParametroAsociacion(JDialog owner) throws Exception {
		this.view = new AdminParametroAsociacionView(owner);
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		this.setModels();
		this.setListeners();
		try {
			this.buscar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void setModels() {
		this.getView().setBusquedaModel(this.busquedaModel);
		this.getView().setTableModel(this.tableModel);
	}
	
	private void setListeners() {
		this.getView().getPnlPie().getBtnAgregar().addActionListener(new BtnAgregarListener(this));
		this.getView().getPnlPie().getBtnModificar().addActionListener(new BtnModificarListener(this));
		this.getView().getPnlPie().getBtnEliminar().addActionListener(new BtnEliminarListener(this));
//		this.getView().getPnlPie().getBtnConsultar().addActionListener(new BtnConsultarListener(this));
	}
	
	@Override
	protected void actualizarBusquedaModel() {
		ParametroAsociacionBusquedaModel locModel = this.getBusquedaModel();
		
		locModel.fireActualizarDatos();
	}

	@Override
	protected void actualizarBusquedaView() {
		
	}

	@Override
	protected ParametroAsociacionBusquedaModel getBusquedaModel() {
		return this.busquedaModel;
	}

	@Override
	protected ParametroAsociacionTableModel getTableModel() {
		return this.tableModel;
	}

	@Override
	protected AdminParametroAsociacionView getView() {
		return this.view;
	}
	
	void buscar() throws Exception {
		final AdminParametroAsociacion controller = this;
		Thread locThread = new Thread(new Runnable(){
			public void run() {
				try {
					controller.actualizarBusquedaModel();
					controller.getView().iniBusqueda();
					List<ParametroAsociacion> locLista = controller.getBusquedaModel().buscar();
					controller.getTableModel().setListaObjetos(locLista);
					if (!locLista.isEmpty()) {
						view.getPnlPie().getBtnSeleccionar().setEnabled(true);
					}
				}
				catch (Exception ex) {
					ex.printStackTrace();
					AppManager.getInstance().showErrorMsg(controller.getView(), ex.getMessage());
				}
				finally{
					controller.getView().finBusqueda();
				}
			}
		});
		locThread.start();
	}
	
	void openAgregarParametroAsociacion() throws Exception {
		AgregarParametroAsociacion agregarParametroAsociacion = new AgregarParametroAsociacion(this.getView());
		agregarParametroAsociacion.open();
		if (agregarParametroAsociacion.isOperacionRealizada()) {
			this.getTableModel().addRow(agregarParametroAsociacion.getAbmModel().getObjetoABM());
			this.buscar();
		}
	}

	void openModificarParametroAsociacion() throws Exception {
		ParametroAsociacion locParametroAsociacion = this.getSelectedRow();
		if(locParametroAsociacion != null) {
			locParametroAsociacion = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().getParametroAsociacionByID(locParametroAsociacion.getIdParametroAsociacion());
//			locParametroAsociacion.getLineasParametroRetencion().toString();
			ModificarParametroAsociacion modificarParametroAsociacion = new ModificarParametroAsociacion(this.getView());
			modificarParametroAsociacion.getAbmModel().setObjetoABM(locParametroAsociacion);
			modificarParametroAsociacion.actualizarView();
			modificarParametroAsociacion.open();
			
			this.buscar();
		}
	}
	
	void openEliminarParametroAsociacion() throws Exception {
		ParametroAsociacion locParametroAsociacion = this.getSelectedRow();
		if(locParametroAsociacion != null) {
			locParametroAsociacion = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().getParametroAsociacionByID(locParametroAsociacion.getIdParametroAsociacion());
			EliminarParametroAsociacion eliminarParametroAsociacion = new EliminarParametroAsociacion(this.getView());
			eliminarParametroAsociacion.getAbmModel().setObjetoABM(locParametroAsociacion);
//			eliminarParametroAsociacion.getAbmModel().setListaLineasParametroAsociacion(locParametroAsociacion.getLineasParametroAsociacion());
			eliminarParametroAsociacion.actualizarView();
			eliminarParametroAsociacion.open();
			this.buscar();
		}
	}
}

class BtnAgregarListener implements ActionListener{
	
	private AdminParametroAsociacion controller;

	public BtnAgregarListener(AdminParametroAsociacion controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openAgregarParametroAsociacion();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnModificarListener implements ActionListener{
	
	private AdminParametroAsociacion controller;

	public BtnModificarListener(AdminParametroAsociacion controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openModificarParametroAsociacion();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnEliminarListener implements ActionListener{
	
	private AdminParametroAsociacion controller;

	public BtnEliminarListener(AdminParametroAsociacion controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openEliminarParametroAsociacion();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}