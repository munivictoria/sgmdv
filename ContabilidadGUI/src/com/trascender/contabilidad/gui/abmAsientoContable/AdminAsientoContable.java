package com.trascender.contabilidad.gui.abmAsientoContable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.contabilidad.gui.abmAsientoContable.abm.AgregarAsientoContable;
import com.trascender.contabilidad.gui.abmAsientoContable.abm.EliminarAsientoContable;
import com.trascender.contabilidad.gui.abmAsientoContable.abm.ModificarAsientoContable;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.AsientoContable;
import com.trascender.contabilidad.recurso.persistent.FolioLibroDiario;
import com.trascender.gui.framework.abmStandard.AdminController;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Conversor;

public class AdminAsientoContable extends AdminController<AsientoContable> {

	private AdminAsientoContableView view;
	private AsientoContableBusquedaModel busquedaModel = new AsientoContableBusquedaModel();
	private AsientoContableTableModel tableModel = new AsientoContableTableModel();
	
	public AdminAsientoContable(JFrame owner) throws Exception {
		this.view = new AdminAsientoContableView(owner);
		this.init();
	}
	
	public AdminAsientoContable(JDialog owner) throws Exception {
		this.view = new AdminAsientoContableView(owner);
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		this.setModels();
		this.setListeners();
//		this.getView().getPnlTabla().getTblDatos().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	private void setModels() {
		this.getView().setBusquedaModel(this.getBusquedaModel());
		this.getView().setTableModel(this.getTableModel());
	}
	
	private void setListeners() {
		this.getView().getPnlBotonesBusqueda().getBtnBuscar().addActionListener(new BtnBuscarListener(this));
		
		this.getView().getPnlPie().getBtnAgregar().addActionListener(new BtnAgregarListener(this));
		this.getView().getPnlPie().getBtnModificar().addActionListener(new BtnModificarListener(this));
		this.getView().getPnlPie().getBtnEliminar().addActionListener(new BtnEliminarListener(this));
	}
	
	@Override
	protected void actualizarBusquedaModel() {
		this.getBusquedaModel().setNumeroAsiento(Conversor.getInteger(this.getView().getTfNumeroAsiento().getText()));
		this.getBusquedaModel().setFechaDesde(Conversor.getDate(this.getView().getFtfFechaDesde().getText()));
		this.getBusquedaModel().setFechaHasta(Conversor.getDate(this.getView().getFtfFechaHasta().getText()));
		
		this.getBusquedaModel().fireActualizarDatos();
	}

	@Override
	protected void actualizarBusquedaView() {
		this.getView().getTfNumeroAsiento().setText(Conversor.getVacioSiNull(this.getBusquedaModel().getNumeroAsiento()));
		this.getView().getFtfFechaDesde().setValue(Conversor.getString(this.getBusquedaModel().getFechaDesde()));
		this.getView().getFtfFechaHasta().setValue(Conversor.getString(this.getBusquedaModel().getFechaHasta()));
	}
	
	@Override
	protected AsientoContableBusquedaModel getBusquedaModel() {
		return this.busquedaModel;
	}

	@Override
	protected AsientoContableTableModel getTableModel() {
		return this.tableModel;
	}

	@Override
	protected AdminAsientoContableView getView() {
		return this.view;
	}

	void buscar() {
		final AdminAsientoContable controller = this;
		Thread locThread = new Thread(new Runnable(){
			public void run() {
				try {
					controller.actualizarBusquedaModel();
					controller.getView().iniBusqueda();
					List<AsientoContable> locLista = controller.getBusquedaModel().buscar();
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
	
	void openAgregarAsientoContable() throws Exception {
		AgregarAsientoContable agregarAsientoContable = new AgregarAsientoContable(this.getView());
		agregarAsientoContable.open();
		if (agregarAsientoContable.isOperacionRealizada()) {
			this.getTableModel().addRow(agregarAsientoContable.getAbmModel().getObjetoABM());
			this.buscar();
		}
	}
	
	@SuppressWarnings("unchecked")
	void openModificarAsientoContable() throws Exception {
		AsientoContable locAsientoContable = this.getSelectedRow();
		if (locAsientoContable != null) {
			AsientoContable asientoContable = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().getAsientoContableByID(locAsientoContable.getIdAsientoContable());
			List<FolioLibroDiario> listaFolios = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().findListaFolioLibroDiario(null,asientoContable.getFolioLibroDiario().getLibroDiario());
			ModificarAsientoContable modificarAsientoContable = new ModificarAsientoContable(this.getView());
			modificarAsientoContable.getAbmModel().setLibroDiario(asientoContable.getFolioLibroDiario().getLibroDiario());
			modificarAsientoContable.getView().getCbFolioLibroDiario().setModel(new DefaultComboBoxModel(listaFolios.toArray()));
			modificarAsientoContable.getAbmModel().setObjetoABM(asientoContable);
			modificarAsientoContable.getTableModel().addRows(modificarAsientoContable.getAbmModel().getObjetoABM().getLineasAsientoContable());
			modificarAsientoContable.calcularResultados();
			modificarAsientoContable.actualizarView();
			modificarAsientoContable.open();
			if (modificarAsientoContable.isOperacionRealizada()) {
				this.getTableModel().updateRow(locAsientoContable);
				this.buscar();
			}
		}
	}
	
	void openEliminarAsientoContable() throws Exception {
		AsientoContable locAsientoContable = this.getSelectedRow();
		if (locAsientoContable != null) {
			AsientoContable asientoContable = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().getAsientoContableByID(locAsientoContable.getIdAsientoContable());
			List<FolioLibroDiario> listaFolios = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().findListaFolioLibroDiario(null,asientoContable.getFolioLibroDiario().getLibroDiario());
			EliminarAsientoContable eliminarAsientoContable = new EliminarAsientoContable(this.getView());
			eliminarAsientoContable.getAbmModel().setLibroDiario(asientoContable.getFolioLibroDiario().getLibroDiario());
			eliminarAsientoContable.getView().getCbFolioLibroDiario().setModel(new DefaultComboBoxModel(listaFolios.toArray()));
			eliminarAsientoContable.getAbmModel().setObjetoABM(asientoContable);
			eliminarAsientoContable.getTableModel().addRows(eliminarAsientoContable.getAbmModel().getObjetoABM().getLineasAsientoContable());
			eliminarAsientoContable.calcularResultados();
			eliminarAsientoContable.actualizarView();
			eliminarAsientoContable.open();
			if (eliminarAsientoContable.isOperacionRealizada()) {
				this.getTableModel().updateRow(locAsientoContable);
				this.buscar();
			}
		}
	}
	
}


class BtnBuscarListener implements ActionListener {
	private AdminAsientoContable controller;
	public BtnBuscarListener(AdminAsientoContable controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		this.controller.buscar();
	}
}


class BtnAgregarListener implements ActionListener {
	private AdminAsientoContable controller;
	public BtnAgregarListener(AdminAsientoContable controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openAgregarAsientoContable();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnModificarListener implements ActionListener {
	private AdminAsientoContable controller;
	public BtnModificarListener(AdminAsientoContable controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openModificarAsientoContable();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}


class BtnEliminarListener implements ActionListener {
	private AdminAsientoContable controller;
	public BtnEliminarListener(AdminAsientoContable controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openEliminarAsientoContable();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}
