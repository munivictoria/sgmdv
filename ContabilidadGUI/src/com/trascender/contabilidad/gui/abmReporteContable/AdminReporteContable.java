package com.trascender.contabilidad.gui.abmReporteContable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.contabilidad.gui.abmReporteContable.abm.AgregarReporteContable;
import com.trascender.contabilidad.gui.abmReporteContable.abm.ConsultarReporteContable;
import com.trascender.contabilidad.gui.abmReporteContable.abm.EliminarReporteContable;
import com.trascender.contabilidad.gui.abmReporteContable.abm.ModificarReporteContable;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.ReporteContable;
import com.trascender.gui.framework.abmStandard.AdminController;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Conversor;

public class AdminReporteContable extends AdminController<ReporteContable>{

	private AdminReporteContableView view;
	private ReporteContableBusquedaModel busquedaModel = new ReporteContableBusquedaModel();
	private ReporteContableTableModel tableModel = new ReporteContableTableModel();
	
	public AdminReporteContable(JFrame owner) throws Exception {
		this.view = new AdminReporteContableView(owner);
		this.init();
	}
	
	public AdminReporteContable(JDialog owner) throws Exception {
		this.view = new AdminReporteContableView(owner);
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		this.setModels();
		this.setListeners();
	}
	
	private void setModels() {
		this.getView().setTableModel(this.getTableModel());
		this.getView().setBusquedaModel(this.getBusquedaModel());
	}
	
	private void setListeners() {
		this.getView().getPnlBotonesBusqueda().getBtnBuscar().addActionListener(new BtnBuscarListener(this));
		
		this.getView().getPnlPie().getBtnAgregar().addActionListener(new BtnAgregarListener(this));
		this.getView().getPnlPie().getBtnModificar().addActionListener(new BtnModificarListener(this));
		this.getView().getPnlPie().getBtnEliminar().addActionListener(new BtnEliminarListener(this));
		this.getView().getPnlPie().getBtnConsultar().addActionListener(new BtnConsultarListener(this));
	}
	
	@Override
	public AdminReporteContableView getView() {
		return view;
	}

	@Override
	protected ReporteContableTableModel getTableModel() {
		return this.tableModel;
	}

	@Override
	protected ReporteContableBusquedaModel getBusquedaModel() {
		return this.busquedaModel;
	}

	@Override
	protected void actualizarBusquedaModel() {
		ReporteContableBusquedaModel locModel = this.getView().getBusquedaModel();
		locModel.setNombre(Conversor.getNullSiVacio(this.getView().getTfNombre().getText()));
		
		locModel.fireActualizarDatos();		
	}

	@Override
	protected void actualizarBusquedaView() {
		this.getView().getTfNombre().setText(Conversor.getVacioSiNull(this.getBusquedaModel().getNombre()));
	}
	
	void buscar() throws Exception {
		final AdminReporteContable controller = this;
		Thread locThread = new Thread(new Runnable(){
			public void run() {
				try {
					controller.actualizarBusquedaModel();
					controller.getView().iniBusqueda();
					List<ReporteContable> locLista = controller.getBusquedaModel().buscar();
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
	
	protected void openAgregarReporteContable() throws Exception {
		AgregarReporteContable agregarReporteContable = new AgregarReporteContable(this.getView());
		agregarReporteContable.open();
		if (agregarReporteContable.isOperacionRealizada()) {
			this.getTableModel().addRow(agregarReporteContable.getAbmModel().getObjetoABM());
			
			List<ReporteContable> locListaReportes = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemReportesContabilidad().getListaMenuReporteContable(AppManager.getInstance().getUsuario());
			ContabilidadGUI.getInstance().getMainController().getView().getMenuReportes().armarItemsMenuReportes(locListaReportes);
		}
	}
	
	protected void openModificarReporteContable() throws Exception {
		ReporteContable locReporteContable = this.getSelectedRow();
		if (locReporteContable != null) {
			ReporteContable reporteContable = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemReportesContabilidad().getReporteContableByID(locReporteContable.getIdReporteContable());
			ModificarReporteContable modificarReporteContable = new ModificarReporteContable(this.getView());
			modificarReporteContable.getAbmModel().setObjetoABM(reporteContable);
			modificarReporteContable.getTableModelParametro().addRows(modificarReporteContable.getAbmModel().getObjetoABM().getListaParametroReporte());
			modificarReporteContable.getTableModelUsuario().addRows(modificarReporteContable.getAbmModel().getObjetoABM().getListaUsuario());
			modificarReporteContable.actualizarView();
			modificarReporteContable.open();
			if (modificarReporteContable.isOperacionRealizada()) {
				this.getTableModel().updateRow(locReporteContable);
				this.buscar();
				
				ContabilidadGUI.getInstance().habilitarMenuesSegunPermisos();
			}
		}
	}
	
	protected void openEliminarReporteContable() throws Exception {
		ReporteContable locReporteContable = this.getSelectedRow();
		if (locReporteContable != null) {
			ReporteContable reporteContable = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemReportesContabilidad().getReporteContableByID(locReporteContable.getIdReporteContable());
			EliminarReporteContable eliminarReporteContable = new EliminarReporteContable(this.getView());
			eliminarReporteContable.getAbmModel().setObjetoABM(reporteContable);
			eliminarReporteContable.getTableModelParametro().addRows(eliminarReporteContable.getAbmModel().getObjetoABM().getListaParametroReporte());
			eliminarReporteContable.getTableModelUsuario().addRows(eliminarReporteContable.getAbmModel().getObjetoABM().getListaUsuario());
			eliminarReporteContable.actualizarView();
			eliminarReporteContable.open();
			if (eliminarReporteContable.isOperacionRealizada()) {
				this.getTableModel().updateRow(locReporteContable);
				this.buscar();
				
				ContabilidadGUI.getInstance().habilitarMenuesSegunPermisos();
			}
		}
	}
	
	void openConsultarReporteContable() throws Exception {
		ReporteContable locReporte = this.getSelectedRow();
		if (locReporte != null) {
			ReporteContable reporte = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemReportesContabilidad().getReporteContableByID(locReporte.getIdReporteContable());
			ConsultarReporteContable consultarReporte = new ConsultarReporteContable(this.getView(), reporte);
			
			consultarReporte.instanciarTableModel();
			consultarReporte.getAbmModel().setObjetoABM(reporte);
			consultarReporte.actualizarView();
			consultarReporte.open();
		}
	}
	
	class BtnBuscarListener implements ActionListener {
		private AdminReporteContable controller;
		public BtnBuscarListener(AdminReporteContable controller) {
			this.controller = controller;
		}
		public void actionPerformed(ActionEvent e) {
			try {
				this.controller.buscar();
			}
			catch (Exception ex) {
				ex.printStackTrace();
				AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
			}
		}
	}
	
	class BtnAgregarListener implements ActionListener {

		private AdminReporteContable controller;
		
		public BtnAgregarListener(AdminReporteContable controller) {
			this.controller = controller;
		}

		public void actionPerformed(ActionEvent e) {
			try {
				this.controller.openAgregarReporteContable();
			} catch (Exception ex) {
				ex.printStackTrace();
				AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
			}
		}
	}
	
	class BtnModificarListener implements ActionListener {

		private AdminReporteContable controller;
		
		public BtnModificarListener(AdminReporteContable controller) {
			this.controller = controller;
		}

		public void actionPerformed(ActionEvent e) {
			try {
				this.controller.openModificarReporteContable();
			} catch (Exception ex) {
				ex.printStackTrace();
				AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
			}
		}
	}
	
	class BtnEliminarListener implements ActionListener {

		private AdminReporteContable controller;
		
		public BtnEliminarListener(AdminReporteContable controller) {
			this.controller = controller;
		}

		public void actionPerformed(ActionEvent e) {
			try {
				this.controller.openEliminarReporteContable();
			} catch (Exception ex) {
				ex.printStackTrace();
				AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
			}
		}
	}
	
	class BtnConsultarListener implements ActionListener {

		private AdminReporteContable controller;
		
		public BtnConsultarListener(AdminReporteContable controller) {
			this.controller = controller;
		}

		public void actionPerformed(ActionEvent e) {
			try {
				this.controller.openConsultarReporteContable();
			} catch (Exception ex) {
				ex.printStackTrace();
				AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
			}
		}
	}
}
