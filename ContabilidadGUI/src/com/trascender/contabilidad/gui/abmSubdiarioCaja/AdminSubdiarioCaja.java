package com.trascender.contabilidad.gui.abmSubdiarioCaja;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.contabilidad.gui.abmSubdiarioCaja.abm.AgregarSubdiarioCaja;
import com.trascender.contabilidad.gui.abmSubdiarioCaja.abm.ModificarSubdiarioCaja;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.SubdiarioCaja;
import com.trascender.gui.framework.abmStandard.AdminController;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.model.TDefaultComboBoxModel;
import com.trascender.gui.framework.util.Conversor;

public class AdminSubdiarioCaja extends AdminController<SubdiarioCaja> {
	
	private AdminSubdiarioCajaView view;
	private SubdiarioCajaBusquedaModel busquedaModel = new SubdiarioCajaBusquedaModel();
	private SubdiarioCajaTableModel tableModel = new SubdiarioCajaTableModel();
	
	public AdminSubdiarioCaja(JFrame owner) throws Exception {
		this.view = new AdminSubdiarioCajaView(owner);
		this.init();
	}
	
	public AdminSubdiarioCaja(JDialog owner) throws Exception {
		this.view = new AdminSubdiarioCajaView(owner);
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		
		this.getView().getPnlPie().getBtnEliminar().setEnabled(false);
		
		this.setModels();
		this.setListeners();
	}
	
	private void setModels() {
		this.getView().setBusquedaModel(this.getBusquedaModel());
		this.getView().setTableModel(this.getTableModel());
		
		this.getView().getCbTipo().setModel(new TDefaultComboBoxModel(SubdiarioCaja.Tipo.values()));
	}
	
	private void setListeners() {
		this.getView().getPnlBotonesBusqueda().getBtnBuscar().addActionListener(new BtnBuscarListener(this));
		
		this.getView().getPnlPie().getBtnAgregar().addActionListener(new BtnAgregarListener(this));
		this.getView().getPnlPie().getBtnModificar().addActionListener(new BtnModificarListener(this));
	}
	
	@Override
	protected void actualizarBusquedaModel() {
		this.getBusquedaModel().setFechaCreacionDesde(Conversor.getDate(this.getView().getFtfFechaCreacionDesde().getText()));
		this.getBusquedaModel().setFechaCreacionHasta(Conversor.getDate(this.getView().getFtfFechaCreacionHasta().getText()));
		
		Object locTipo = this.getView().getCbTipo().getSelectedItem();
		if (locTipo != null) this.getBusquedaModel().setTipo((SubdiarioCaja.Tipo)locTipo);
		else this.getBusquedaModel().setTipo(null);
		
		this.getBusquedaModel().fireActualizarDatos();
	}

	@Override
	protected void actualizarBusquedaView() {
		this.getView().getFtfFechaCreacionDesde().setValue(Conversor.getString(this.getBusquedaModel().getFechaCreacionDesde()));
		this.getView().getFtfFechaCreacionHasta().setValue(Conversor.getString(this.getBusquedaModel().getFechaCreacionHasta()));
		this.getView().getCbTipo().setSelectedItem(this.getBusquedaModel().getTipo());
	}

	@Override
	protected SubdiarioCajaBusquedaModel getBusquedaModel() {
		return this.busquedaModel;
	}

	@Override
	protected SubdiarioCajaTableModel getTableModel() {
		return this.tableModel;
	}

	@Override
	protected AdminSubdiarioCajaView getView() {
		return this.view;
	}
	
	void buscar() {
		final AdminSubdiarioCaja controller = this;
		Thread locThread = new Thread(new Runnable(){
			public void run() {
				try {
					controller.actualizarBusquedaModel();
					controller.getView().iniBusqueda();
					List<SubdiarioCaja> locLista = controller.getBusquedaModel().buscar();
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
	
	void openAgregarSubdiarioCaja() throws Exception {
		AgregarSubdiarioCaja agregarSubdiarioCaja = new AgregarSubdiarioCaja(this.getView());
		agregarSubdiarioCaja.open();
		if (agregarSubdiarioCaja.isOperacionRealizada()) {
			this.getTableModel().addRow(agregarSubdiarioCaja.getAbmModel().getObjetoABM());
		}
	}
	
	void openModificarSubdiarioCaja() throws Exception {
		SubdiarioCaja locSubdiarioCaja = this.getSelectedRow();
		
		if (locSubdiarioCaja != null) {
			ModificarSubdiarioCaja modificarSubdiarioCaja = new ModificarSubdiarioCaja(this.getView());
			SubdiarioCaja subdiarioCaja = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().getSubdiarioCajaByID(locSubdiarioCaja.getIdSubdiarioCaja());
			modificarSubdiarioCaja.getAbmModel().setObjetoABM(subdiarioCaja);
			modificarSubdiarioCaja.actualizarView();
			modificarSubdiarioCaja.open();
			
			if (modificarSubdiarioCaja.isOperacionRealizada()) {
				this.getTableModel().updateRow(modificarSubdiarioCaja.getAbmModel().getObjetoABM());
			}
		}
	}
	
}


class BtnBuscarListener implements ActionListener {
	private AdminSubdiarioCaja controller;
	public BtnBuscarListener(AdminSubdiarioCaja controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		this.controller.buscar();
	}
}


class BtnAgregarListener implements ActionListener{
	private AdminSubdiarioCaja controller;
	public BtnAgregarListener(AdminSubdiarioCaja controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openAgregarSubdiarioCaja();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnModificarListener implements ActionListener{
	private AdminSubdiarioCaja controller;
	public BtnModificarListener(AdminSubdiarioCaja controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openModificarSubdiarioCaja();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}
