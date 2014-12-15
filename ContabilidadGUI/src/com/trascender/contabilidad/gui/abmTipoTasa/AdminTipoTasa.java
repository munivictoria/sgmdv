package com.trascender.contabilidad.gui.abmTipoTasa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.framework.util.Util;
import com.trascender.gui.framework.abmStandard.AdminController;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.model.TDefaultComboBoxModel;
import com.trascender.habilitaciones.recurso.persistent.TipoObligacion;
import com.trascender.habilitaciones.recurso.persistent.TipoTasa;

public class AdminTipoTasa extends AdminController<TipoTasa> {
	
	private AdminTipoTasaView view;
	private TipoTasaTableModel tableModel = new TipoTasaTableModel();
	private TipoTasaBusquedaModel busquedaModel = new TipoTasaBusquedaModel();
	
	public AdminTipoTasa(JFrame owner) throws Exception {
		this.view = new AdminTipoTasaView(owner);
		this.init();
	}
	
	public AdminTipoTasa(JDialog owner) throws Exception {
		this.view = new AdminTipoTasaView(owner);
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		this.setModels();
		this.setListeners();
		this.setCommonProperties();
	}
	
	private void setCommonProperties() {
		this.getView().getPnlPie().getBtnAgregar().setEnabled(false);
		this.getView().getPnlPie().getBtnModificar().setEnabled(false);
		this.getView().getPnlPie().getBtnEliminar().setEnabled(false);
	}
	
	private void setModels() {
		this.view.setBusquedaModel(this.busquedaModel);
		this.view.getPnlTabla().getTblDatos().setModel(this.tableModel);
		this.view.getCbTipoObligacion().setModel(new TDefaultComboBoxModel(busquedaModel.findTiposObliacion()));
	}
	
	private void setListeners() {
		this.getView().getPnlBotonesBusqueda().getBtnBuscar().addActionListener(new BtnBuscarListener(this));
	}
	
	@Override
	protected void actualizarBusquedaModel() {
		//TipoTasaBusquedaModel locModel = this.getView().getBusquedaModel();
		TipoTasaBusquedaModel locModel = this.getBusquedaModel();
		
		locModel.setNombre(this.getView().getTfNombre().getText());
		
		TipoObligacion locTipoObligacion = null;
		Object locTipoObligacionSelected = this.getView().getCbTipoObligacion().getSelectedItem();
		if (locTipoObligacionSelected != null) 
			locTipoObligacion = (TipoObligacion) locTipoObligacionSelected;
		
		locModel.setTipoObligacion(locTipoObligacion);
		
		locModel.fireActualizarDatos();
	}

	@Override
	protected void actualizarBusquedaView() {
		this.getView().getTfNombre().setText(this.getBusquedaModel().getNombre());
		this.getView().getCbTipoObligacion().setSelectedItem(this.getBusquedaModel().getTipoObligacion());
	}

	@Override
	protected TipoTasaBusquedaModel getBusquedaModel() {
		return busquedaModel;
	}

	@Override
	protected TipoTasaTableModel getTableModel() {
		return tableModel;
	}

	@Override
	protected AdminTipoTasaView getView() {
		return view;
	}

	void buscar() {
		final AdminTipoTasa controller = this;
		Thread locThread = new Thread(new Runnable() {
			public void run()  {
				try {
					controller.actualizarBusquedaModel();
					controller.getView().iniBusqueda();
					List<TipoTasa> locLista = controller.getBusquedaModel().buscar();
					controller.getTableModel().setListaObjetos(locLista);
					
//					controller.actualizarBusquedaModel();
//					controller.getView().iniBusqueda();
//					List<TipoTasa> locLista = controller.getBusquedaModel().buscar();
//					controller.getTableModel().setListaObjetos(locLista);
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

class BtnBuscarListener implements ActionListener {

	private AdminTipoTasa controller;
	
	public BtnBuscarListener(AdminTipoTasa controller) {
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