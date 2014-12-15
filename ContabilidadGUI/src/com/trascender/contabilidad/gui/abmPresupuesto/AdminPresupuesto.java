package com.trascender.contabilidad.gui.abmPresupuesto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.trascender.contabilidad.gui.abmPresupuesto.abm.AgregarPresupuesto;
import com.trascender.contabilidad.gui.abmPresupuesto.abm.ConsultarPresupuesto;
import com.trascender.contabilidad.gui.abmPresupuesto.abm.EliminarPresupuesto;
import com.trascender.contabilidad.gui.abmPresupuesto.abm.ModificarPresupuesto;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.Presupuesto;
import com.trascender.contabilidad.recurso.persistent.Presupuesto.Estado;
import com.trascender.contabilidad.recurso.persistent.Presupuesto.Tipo;
import com.trascender.framework.util.Util;
import com.trascender.gui.framework.abmStandard.AdminController;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.model.TDefaultComboBoxModel;
import com.trascender.gui.framework.util.Conversor;
import com.trascender.gui.framework.util.Validador;

public class AdminPresupuesto extends AdminController<Presupuesto> {

	private AdminPresupuestoView view;
	private PresupuestoBusquedaModel busquedaModel = new PresupuestoBusquedaModel();
	private PresupuestoTableModel tableModel = new PresupuestoTableModel();
	
	public AdminPresupuesto(JDialog owner)throws Exception {
		this.view = new AdminPresupuestoView(owner);
		this.init();
	}
	
	public AdminPresupuesto(JFrame owner) throws Exception {
		this.view = new AdminPresupuestoView(owner);
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
		
		this.getView().getCbTipoPresupuesto().setModel(new TDefaultComboBoxModel(Presupuesto.Tipo.values()));
		this.getView().getCbEstado().setModel(new TDefaultComboBoxModel(Presupuesto.Estado.values()));
	}

	private void setListeners() {
		this.getView().getPnlBotonesBusqueda().getBtnBuscar().addActionListener(new BtnBuscarListener(this));
		
		this.getView().getPnlPie().getBtnAgregar().addActionListener(new BtnAgregarListener(this));
		this.getView().getPnlPie().getBtnModificar().addActionListener(new BtnModificarListener(this));
		this.getView().getPnlPie().getBtnEliminar().addActionListener(new BtnEliminarListener(this));
		this.getView().getPnlPie().getBtnConsultar().addActionListener(new BtnConsultarListener(this));
	}
	
	@Override
	protected void actualizarBusquedaModel() {
		PresupuestoBusquedaModel locModelo = this.getView().getBusquedaModel();
		
		Tipo locTipoPresupuesto = null; 
		Object locTipoPresupuestoSelecionado = this.getView().getCbTipoPresupuesto().getSelectedItem();
		if (locTipoPresupuestoSelecionado != null) locTipoPresupuesto = Tipo.valueOf(Util.getEnumNameFromString(locTipoPresupuestoSelecionado.toString()));
		locModelo.setTipoPresupuesto(locTipoPresupuesto);
		
		locModelo.setFechaDesde(Conversor.getDate(this.getView().getFtfFechaDesde().getText()));
		
		locModelo.setFechaHasta(Conversor.getDate(this.getView().getFtfFechaHasta().getText()));
		
		Estado locEstado = null;
		Object locEstadoSelecionado = this.getView().getCbEstado().getSelectedItem();
		if (locEstadoSelecionado != null) locEstado = Estado.valueOf(Util.getEnumNameFromString(locEstadoSelecionado.toString()));
		locModelo.setEstado(locEstado);
		
		locModelo.fireActualizarDatos();
	}

	@Override
	protected void actualizarBusquedaView() {
		this.getView().getCbTipoPresupuesto().setSelectedItem(this.getBusquedaModel().getTipoPresupuesto());
		this.getView().getFtfFechaDesde().setValue(Conversor.getString(this.getBusquedaModel().getFechaDesde()));
		this.getView().getFtfFechaHasta().setValue(Conversor.getString(this.getBusquedaModel().getFechaHasta()));
		this.getView().getCbEstado().setSelectedItem(this.getBusquedaModel().getEstado());
	}

	@Override
	protected PresupuestoBusquedaModel getBusquedaModel() {
		return this.busquedaModel;
	}

	@Override
	protected PresupuestoTableModel getTableModel() {
		return this.tableModel;
	}

	@Override
	protected AdminPresupuestoView getView() {
		return this.view;
	}
	
	@Override
	public boolean validarDatos() {
		boolean validacionOK = true;
		
		List<String> attFechas = new ArrayList<String>();
		List<JLabel> lblFechas = new ArrayList<JLabel>();
		
		attFechas.add(this.getView().getFtfFechaDesde().getText());
		lblFechas.add(this.getView().getLblFecha());
		
		attFechas.add(this.getView().getFtfFechaHasta().getText());
		lblFechas.add(this.getView().getLblFecha());
		
		List<String> listaErrores = new ArrayList<String>();
		
		try {
			listaErrores.addAll(Validador.validarFechas(attFechas, lblFechas));
		} catch (GuiException ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.getView(), ex.getMessage());
		}
		
		if (!listaErrores.isEmpty()) {
			validacionOK = false;
		}
		
		return validacionOK;
	}
	
	void buscar() {
		final AdminPresupuesto controller = this; 
		Thread locThread = new Thread(new Runnable() {
			public void run() {
				try {
					controller.actualizarBusquedaModel();
					controller.getView().iniBusqueda();
					List<Presupuesto> locLista = controller.getBusquedaModel().buscar();
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
	
	void openAgregarPresupuesto() throws Exception {
		AgregarPresupuesto agregarPresupuesto = new AgregarPresupuesto(this.getView());
		agregarPresupuesto.open();
		if (agregarPresupuesto.isOperacionRealizada()) {
			//this.getTableModel().addRow(agregarPresupuesto.getAbmModel().getObjetoABM());
			this.buscar();
		}
	}
	
	void openModificarPresupuesto() throws Exception {
		Presupuesto locPresupuesto = this.getSelectedRow();
		if (locPresupuesto != null) {
			locPresupuesto = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().getPresupuestoByID(locPresupuesto.getIdPresupuesto());
			ModificarPresupuesto modificarPresupuesto = new ModificarPresupuesto(this.getView());
			modificarPresupuesto.getAbmModel().setObjetoABM(locPresupuesto);
			
			//modificarPresupuesto.setTableModel();
			modificarPresupuesto.instanciarTableModel(locPresupuesto.getTipo());
			
			modificarPresupuesto.actualizarView();
			modificarPresupuesto.open();
			if (modificarPresupuesto.isOperacionRealizada()) {
				//this.getTableModel().updateRow(presupuesto);
				this.buscar();
			}
		}
	}
	
	void openEliminarPresupuesto() throws Exception {
		Presupuesto locPresupuesto = this.getSelectedRow();
		if (locPresupuesto != null) {
			locPresupuesto = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().getPresupuestoByID(locPresupuesto.getIdPresupuesto());
			EliminarPresupuesto eliminarPresupuesto = new EliminarPresupuesto(this.getView());
			eliminarPresupuesto.getAbmModel().setObjetoABM(locPresupuesto);
			eliminarPresupuesto.instanciarTableModel(locPresupuesto.getTipo());
			eliminarPresupuesto.actualizarView();
			eliminarPresupuesto.open();
			if (eliminarPresupuesto.isOperacionRealizada()) {
				this.getTableModel().deleteRow(locPresupuesto);
			}
		}
	}
	
	void openConsultarPresupuesto() throws Exception {
		Presupuesto locPresupuesto = this.getSelectedRow();
		if(locPresupuesto != null) {
			locPresupuesto = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().getPresupuestoByID(locPresupuesto.getIdPresupuesto()); 
			ConsultarPresupuesto consultarPresupuesto = new ConsultarPresupuesto(this.getView()); 
			consultarPresupuesto.getAbmModel().setObjetoABM(locPresupuesto);
			consultarPresupuesto.instanciarTableModel(locPresupuesto.getTipo());
			consultarPresupuesto.CalcularResultados(locPresupuesto.getTipo());
			consultarPresupuesto.actualizarView();
			consultarPresupuesto.open();
		}
	
	}

}

class BtnBuscarListener implements ActionListener {

	private AdminPresupuesto controller;
	
	public BtnBuscarListener(AdminPresupuesto controller) {
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

class BtnAgregarListener implements ActionListener{
	
	private AdminPresupuesto controller;

	public BtnAgregarListener(AdminPresupuesto controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openAgregarPresupuesto();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnModificarListener implements ActionListener {
	
	private AdminPresupuesto controller;

	public BtnModificarListener(AdminPresupuesto controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openModificarPresupuesto();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnEliminarListener implements ActionListener {
	
	private AdminPresupuesto controller;

	public BtnEliminarListener(AdminPresupuesto controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openEliminarPresupuesto();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnConsultarListener implements ActionListener {
	
	private AdminPresupuesto controller;

	public BtnConsultarListener(AdminPresupuesto controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openConsultarPresupuesto();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}
