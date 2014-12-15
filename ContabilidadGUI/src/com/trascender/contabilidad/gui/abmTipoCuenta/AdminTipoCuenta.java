package com.trascender.contabilidad.gui.abmTipoCuenta;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.contabilidad.gui.abmTipoCuenta.abm.AgregarTipoCuenta;
import com.trascender.contabilidad.gui.abmTipoCuenta.abm.ConsultarTipoCuenta;
import com.trascender.contabilidad.gui.abmTipoCuenta.abm.EliminarTipoCuenta;
import com.trascender.contabilidad.gui.abmTipoCuenta.abm.ModificarTipoCuenta;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.TipoCuenta;
import com.trascender.contabilidad.recurso.persistent.TipoCuenta.Abreviatura;
import com.trascender.contabilidad.recurso.persistent.TipoCuenta.Opera;
import com.trascender.gui.framework.abmStandard.AdminController;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.model.TDefaultComboBoxModel;

public class AdminTipoCuenta extends AdminController<TipoCuenta> {
	
	private AdminTipoCuentaView view;
	private TipoCuentaTableModel tableModel = new TipoCuentaTableModel();
	private TipoCuentaBusquedaModel busquedaModel = new TipoCuentaBusquedaModel();
	
	public AdminTipoCuenta(JFrame owner) throws Exception {
		this.view = new AdminTipoCuentaView(owner);
		this.init();
	}
	
	public AdminTipoCuenta(JDialog owner) throws Exception {
		this.view = new AdminTipoCuentaView(owner);
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
		
		this.getView().getCbAbreviatura().setModel(new TDefaultComboBoxModel(Abreviatura.values()));
		this.getView().getCbOperaAsientos().setModel(new TDefaultComboBoxModel(Opera.values()));
		this.getView().getCbOperaMovimientosCaja().setModel(new TDefaultComboBoxModel(Opera.values()));
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
		TipoCuentaBusquedaModel locModel = this.getView().getBusquedaModel();
		
		locModel.setNombre(this.getView().getTfNombre().getText());
		
		Object locAbreviatura = this.getView().getCbAbreviatura().getSelectedItem();
		if (locAbreviatura != null) locModel.setAbreviatura((Abreviatura)locAbreviatura);
		else locModel.setAbreviatura(null);
		
		Object locOperaAsientos = this.getView().getCbOperaAsientos().getSelectedItem();
		if (locOperaAsientos != null) locModel.setOperaAsientos((Opera)locOperaAsientos);
		else locModel.setOperaAsientos(null);
		
		Object locOperaMovimientosCaja = this.getView().getCbOperaMovimientosCaja().getSelectedItem();
		if (locOperaMovimientosCaja != null) locModel.setOperaMovimientosCaja((Opera)locOperaMovimientosCaja);
		else locModel.setOperaMovimientosCaja(null);
		
		locModel.fireActualizarDatos();
	}
	
	@Override
	protected void actualizarBusquedaView() {
		this.getView().getTfNombre().setText(this.getBusquedaModel().getNombre());
		this.getView().getCbAbreviatura().setSelectedItem(this.getBusquedaModel().getAbreviatura());
		this.getView().getCbOperaAsientos().setSelectedItem(this.getBusquedaModel().getOperaAsientos());
		this.getView().getCbOperaMovimientosCaja().setSelectedItem(this.getBusquedaModel().getOperaMovimientosCaja());
	}
	
	protected AdminTipoCuentaView getView() {
		return view;
	}
	
	protected TipoCuentaBusquedaModel getBusquedaModel() {
		return busquedaModel;
	}
	
	protected TipoCuentaTableModel getTableModel() {
		return tableModel;
	}
	
	
	void buscar() {
		final AdminTipoCuenta controller = this;
		Thread locThread = new Thread(new Runnable(){
			public void run() {
				try {
					controller.actualizarBusquedaModel();
					controller.getView().iniBusqueda();
					List<TipoCuenta> locLista = controller.getBusquedaModel().buscar();
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
	
	void openAgregarTipoCuenta() throws Exception {
		AgregarTipoCuenta agregarTipoCuenta = new AgregarTipoCuenta(this.getView());
		agregarTipoCuenta.getAbmModel().setObjetoABM(new TipoCuenta());
		agregarTipoCuenta.open();
		if (agregarTipoCuenta.isOperacionRealizada()) {
//			this.getTableModel().addRow(agregarTipoCuenta.getAbmModel().getObjetoABM());
			this.buscar();
		}
	}
	
	void openModificarTipoCuenta() throws Exception {
		TipoCuenta locTipoCuenta = this.getSelectedRow();
		if (locTipoCuenta != null) {
			TipoCuenta tipoCuenta = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().getTipoCuentaByID(locTipoCuenta.getIdTipoCuenta());
			ModificarTipoCuenta modificarTipoCuenta = new ModificarTipoCuenta(this.getView());
			modificarTipoCuenta.getAbmModel().setObjetoABM(tipoCuenta);
			modificarTipoCuenta.actualizarView();
			modificarTipoCuenta.open();
			if (modificarTipoCuenta.isOperacionRealizada()) {
//				this.getTableModel().updateRow(locTipoCuenta);
				this.buscar();
			}
		}
	}
	
	void openEliminarTipoCuenta() throws Exception {
		TipoCuenta locTipoCuenta = this.getSelectedRow();
		if (locTipoCuenta != null) {
			TipoCuenta tipoCuenta = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().getTipoCuentaByID(locTipoCuenta.getIdTipoCuenta());
			EliminarTipoCuenta eliminarTipoCuenta = new EliminarTipoCuenta(this.getView());
			eliminarTipoCuenta.getAbmModel().setObjetoABM(tipoCuenta);
			eliminarTipoCuenta.actualizarView();
			eliminarTipoCuenta.open();
			if (eliminarTipoCuenta.isOperacionRealizada()){
				this.getTableModel().deleteRow(locTipoCuenta);
			}
		}
	}
	
	void openConsultarTipoCuenta() throws Exception {
		TipoCuenta locTipoCuenta = this.getSelectedRow();
		if (locTipoCuenta != null) {
			TipoCuenta tipoCuenta = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().getTipoCuentaByID(locTipoCuenta.getIdTipoCuenta());
			ConsultarTipoCuenta consultarTipoCuenta = new ConsultarTipoCuenta(this.getView());
			consultarTipoCuenta.getAbmModel().setObjetoABM(tipoCuenta);
			consultarTipoCuenta.actualizarView();
			consultarTipoCuenta.open();
			if (consultarTipoCuenta.isOperacionRealizada()){
				this.getTableModel().deleteRow(locTipoCuenta);
			}
		}
	}
	
}


class BtnBuscarListener implements ActionListener {
	private AdminTipoCuenta controller;
	public BtnBuscarListener(AdminTipoCuenta controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		this.controller.buscar();
	}
}


class BtnAgregarListener implements ActionListener {
	private AdminTipoCuenta controller;
	public BtnAgregarListener(AdminTipoCuenta controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openAgregarTipoCuenta();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}


class BtnModificarListener implements ActionListener {
	private AdminTipoCuenta controller;
	public BtnModificarListener(AdminTipoCuenta controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openModificarTipoCuenta();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}


class BtnEliminarListener implements ActionListener {
	private AdminTipoCuenta controller;
	public BtnEliminarListener(AdminTipoCuenta controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openEliminarTipoCuenta();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnConsultarListener implements ActionListener {
	private AdminTipoCuenta controller;
	public BtnConsultarListener(AdminTipoCuenta controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openConsultarTipoCuenta();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

