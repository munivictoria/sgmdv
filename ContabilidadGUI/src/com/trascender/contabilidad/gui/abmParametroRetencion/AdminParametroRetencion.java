package com.trascender.contabilidad.gui.abmParametroRetencion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.contabilidad.gui.abmParametroRetencion.abm.AgregarParametroRetencion;
import com.trascender.contabilidad.gui.abmParametroRetencion.abm.EliminarParametroRetencion;
import com.trascender.contabilidad.gui.abmParametroRetencion.abm.ModificarParametroRetencion;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.ParametroRetencion;
import com.trascender.gui.framework.abmStandard.AdminController;
import com.trascender.gui.framework.main.AppManager;

public class AdminParametroRetencion extends AdminController<ParametroRetencion> {

	private AdminParametroRetencionView view;
	private ParametroRetencionBusquedaModel busquedaModel = new ParametroRetencionBusquedaModel();
	private ParametroRetencionTableModel tableModel = new ParametroRetencionTableModel();
	
	public AdminParametroRetencion(JFrame owner) throws Exception {
		this.view = new AdminParametroRetencionView(owner);
		this.init();
	}
	
	public AdminParametroRetencion(JDialog owner) throws Exception {
		this.view = new AdminParametroRetencionView(owner);
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
//		this.getView().getPnlBotonesSeleccion().getBtnSeleccionar().addActionListener(new BtnSeleccionarProveedorListener(this));
//		this.getView().getPnlBotonesSeleccion().getBtnLimpiar().addActionListener(new BtnLimpiarProveedorListener(this));
//		
//		this.getView().getPnlBotonesBusqueda().getBtnBuscar().addActionListener(new BtnBuscarListener(this));
//		this.getView().getPnlBotonesBusqueda().getBtnReiniciar().addActionListener(new BtnReiniciarListener(this));
//
		this.getView().getPnlPie().getBtnAgregar().addActionListener(new BtnAgregarListener(this));
		this.getView().getPnlPie().getBtnModificar().addActionListener(new BtnModificarListener(this));
		this.getView().getPnlPie().getBtnEliminar().addActionListener(new BtnEliminarListener(this));
//		this.getView().getPnlPie().getBtnConsultar().addActionListener(new BtnConsultarListener(this));
	}
	
	@Override
	protected void actualizarBusquedaModel() {
		ParametroRetencionBusquedaModel locModel = this.getBusquedaModel();
		
		locModel.fireActualizarDatos();
	}

	@Override
	protected void actualizarBusquedaView() {
		
	}

	@Override
	protected ParametroRetencionBusquedaModel getBusquedaModel() {
		return this.busquedaModel;
	}

	@Override
	protected ParametroRetencionTableModel getTableModel() {
		return this.tableModel;
	}

	@Override
	protected AdminParametroRetencionView getView() {
		return this.view;
	}
	
	void buscar() throws Exception {
		final AdminParametroRetencion controller = this;
		Thread locThread = new Thread(new Runnable(){
			public void run() {
				try {
					controller.actualizarBusquedaModel();
					controller.getView().iniBusqueda();
					List<ParametroRetencion> locLista = controller.getBusquedaModel().buscar();
					controller.getTableModel().setListaObjetos(locLista);
//					if (!locLista.isEmpty()) {
//						controller.habilitarBtnConsultar(true);
//					}
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
	
	void openAgregarParametroRetencion() throws Exception {
		AgregarParametroRetencion agregarParametroRetencion = new AgregarParametroRetencion(this.getView());
		agregarParametroRetencion.open();
		if (agregarParametroRetencion.isOperacionRealizada()) {
			this.getTableModel().addRow(agregarParametroRetencion.getAbmModel().getObjetoABM());
			this.buscar();
		}
	}

	void openModificarParametroRetencion() throws Exception {
		ParametroRetencion locParametroRetencion = this.getSelectedRow();
		if(locParametroRetencion != null) {
			locParametroRetencion = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionEgresos().getParametroRetencionByID(locParametroRetencion.getIdParametroRetencion());
//			locParametroRetencion.getLineasParametroRetencion().toString();
			ModificarParametroRetencion modificarParametroRetencion = new ModificarParametroRetencion(this.getView());
			modificarParametroRetencion.getAbmModel().setObjetoABM(locParametroRetencion);
			modificarParametroRetencion.actualizarView();
			modificarParametroRetencion.open();
			
			this.buscar();
		}
	}
	
	void openEliminarParametroRetencion() throws Exception {
		ParametroRetencion locParametroRetencion = this.getSelectedRow();
		if(locParametroRetencion != null) {
			locParametroRetencion = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionEgresos().getParametroRetencionByID(locParametroRetencion.getIdParametroRetencion());
			EliminarParametroRetencion eliminarParametroRetencion = new EliminarParametroRetencion(this.getView());
			eliminarParametroRetencion.getAbmModel().setObjetoABM(locParametroRetencion);
//			eliminarParametroRetencion.getAbmModel().setListaLineasParametroRetencion(locParametroRetencion.getLineasParametroRetencion());
			eliminarParametroRetencion.actualizarView();
			eliminarParametroRetencion.open();
			this.buscar();
		}
	}

}

class BtnAgregarListener implements ActionListener{
	
	private AdminParametroRetencion controller;

	public BtnAgregarListener(AdminParametroRetencion controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openAgregarParametroRetencion();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnModificarListener implements ActionListener{
	
	private AdminParametroRetencion controller;

	public BtnModificarListener(AdminParametroRetencion controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openModificarParametroRetencion();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnEliminarListener implements ActionListener{
	
	private AdminParametroRetencion controller;

	public BtnEliminarListener(AdminParametroRetencion controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openEliminarParametroRetencion();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}
