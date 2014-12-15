package com.trascender.contabilidad.gui.abmConceptoIngresoVario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.contabilidad.recurso.persistent.ConceptoIngresoVario;
import com.trascender.gui.framework.abmStandard.AdminController;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Conversor;

public class AdminConceptoIngresoVario extends AdminController<ConceptoIngresoVario> {

	private ConceptoSelladoAdministrativoBusquedaModel busquedaModel = new ConceptoSelladoAdministrativoBusquedaModel();
	private ConceptoSelladoAdministrativoTableModel tableModel;
	private AdminConceptoSelladoAdministrativoView view;
	
	public AdminConceptoIngresoVario(JDialog owner) {
		try {
			tableModel = new ConceptoSelladoAdministrativoTableModel();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.view = new AdminConceptoSelladoAdministrativoView(owner);
		this.init();
	}
	
	public AdminConceptoIngresoVario(JFrame owner) {
		try {
			tableModel = new ConceptoSelladoAdministrativoTableModel();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.view = new AdminConceptoSelladoAdministrativoView(owner);
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		this.setModels();
		this.setListeners();
		this.setBotonInvisible();
	}
	
	private void setModels() {
		this.getView().setBusquedaModel(this.getBusquedaModel());
		this.getView().setTableModel(this.getTableModel());
	}
	
	private void setListeners() {
		this.getView().getPnlBotonesBusqueda().getBtnBuscar().addActionListener(new BtnBuscarListener(this));
	}
	
	private void setBotonInvisible() {
		this.getView().getPnlPie().getBtnAgregar().setEnabled(false);
		this.getView().getPnlPie().getBtnModificar().setEnabled(false);
		this.getView().getPnlPie().getBtnEliminar().setEnabled(false);
	}
	
	@Override
	protected void actualizarBusquedaModel() {
		ConceptoSelladoAdministrativoBusquedaModel locModel = this.getBusquedaModel();
		
		locModel.setNombre(Conversor.getNullSiVacio(this.getView().getTfNombre().getText()));
		
		locModel.fireActualizarDatos();
	}

	@Override
	protected void actualizarBusquedaView() {
		this.getView().getTfNombre().setText(Conversor.getVacioSiNull(this.getBusquedaModel().getNombre()));
	}

	@Override
	protected ConceptoSelladoAdministrativoBusquedaModel getBusquedaModel() {
		return this.busquedaModel;
	}

	@Override
	protected ConceptoSelladoAdministrativoTableModel getTableModel() {
		return this.tableModel;
	}

	@Override
	protected AdminConceptoSelladoAdministrativoView getView() {
		return this.view;
	}
	
	void buscar() throws Exception {
		if (validarDatos()) {
			final AdminConceptoIngresoVario controller = this;
			Thread locThread = new Thread(new Runnable() {

				public void run() {
					try {
						controller.actualizarBusquedaModel();
						controller.getView().iniBusqueda();
						List<ConceptoIngresoVario> locLista = controller.getBusquedaModel().buscar();
						controller.getTableModel().setListaObjetos(locLista);
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
	
}

class BtnBuscarListener implements ActionListener {
	AdminConceptoIngresoVario controller;
	
	public BtnBuscarListener(AdminConceptoIngresoVario controller) {
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
