package com.trascender.contabilidad.gui.abmTipoModificador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.trascender.contabilidad.gui.abmTipoTasa.AdminTipoTasa;
import com.trascender.gui.framework.abmStandard.AdminController;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Conversor;
import com.trascender.gui.framework.util.Validador;
import com.trascender.habilitaciones.recurso.persistent.TipoModificador;
import com.trascender.habilitaciones.recurso.persistent.TipoTasa;

public class AdminTipoModificador extends AdminController<TipoModificador> {

	private AdminTipoModificadorView view;
	private TipoModificadorBusquedaModel busquedaModel = new TipoModificadorBusquedaModel();
	private TipoModificadorTableModel tableModel = new TipoModificadorTableModel();

	public AdminTipoModificador(JFrame owner) throws Exception {
		this.view = new AdminTipoModificadorView(owner);
		this.init();
	}

	public AdminTipoModificador(JDialog owner) throws Exception {
		this.view = new AdminTipoModificadorView(owner);
		this.init();
	}

	@Override
	protected void init() {
		super.init();

		this.getView().getPnlPie().getBtnAgregar().setEnabled(false);
		this.getView().getPnlPie().getBtnModificar().setEnabled(false);
		this.getView().getPnlPie().getBtnEliminar().setEnabled(false);

		this.setModels();
		this.setListeners();
	}

	private void setModels() {
		this.getView().setTableModel(this.getTableModel());
		this.getView().setBusquedaModel(this.getBusquedaModel());
	}

	private void setListeners() {
		this.getView().getPnlBotonesSeleccionTipoTasa().getBtnSeleccionar().addActionListener(new BtnSeleccionarTipoTasaListener(this));
		this.getView().getPnlBotonesSeleccionTipoTasa().getBtnLimpiar().addActionListener(new BtnLimpiarTipoTasaListener(this));

		this.getView().getPnlBotonesBusqueda().getBtnBuscar().addActionListener(new BtnBuscarListener(this));
	}

	@Override
	protected void actualizarBusquedaModel() {
		this.getBusquedaModel().fireActualizarDatos();
	}

	@Override
	protected void actualizarBusquedaView() {
		this.getView().getTfTipoTasa().setText(Conversor.getVacioSiNull(this.getBusquedaModel().getTipoTasa()));
	}

	@Override
	protected TipoModificadorBusquedaModel getBusquedaModel() {
		return this.busquedaModel;
	}

	@Override
	protected TipoModificadorTableModel getTableModel() {
		return this.tableModel;
	}

	@Override
	protected AdminTipoModificadorView getView() {
		return this.view;
	}

	@Override
	public boolean validarDatos() {
		boolean validacionOK = true;
		
		List<Object> attNulos = new ArrayList<Object>();
		List<JLabel> lblNulos = new ArrayList<JLabel>();
		
		attNulos.add(this.getView().getTfTipoTasa().getText());
		lblNulos.add(this.getView().getLblTipoTasa());
		
		List<String> listaErrores = new ArrayList<String>();
		try {
			listaErrores.addAll(Validador.validarNulos(attNulos, lblNulos));
		}
		catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.getView(), ex.getMessage());
			return false;
		}
		
		if (!listaErrores.isEmpty()) {
			validacionOK = false;
			this.mostrarErroresValidacion(listaErrores);
		}
		
		return validacionOK;
	}
	
	void seleccionarTipoTasa() throws Exception {
		AdminTipoTasa adminTipoTasa = new AdminTipoTasa(this.getView());
		this.actualizarBusquedaModel();
		TipoTasa locTipoTasa = adminTipoTasa.openSelect();

		if (locTipoTasa != null) {
			this.getBusquedaModel().setTipoTasa(locTipoTasa);
			this.actualizarBusquedaView();
		}
	}

	void limpiarTipoTasa() throws Exception {
		this.getBusquedaModel().setTipoTasa(null);
		this.actualizarBusquedaModel();
		this.actualizarBusquedaView();
	}

	void buscar() {
		if (this.validarDatos()) {
			final AdminTipoModificador controller = this;
			Thread locThread = new Thread(new Runnable() {
				public void run() {
					try {
						controller.actualizarBusquedaModel();
						controller.getView().iniBusqueda();
						List<TipoModificador> locTipoModificador = controller.getBusquedaModel().buscar();
						controller.getTableModel().setListaObjetos(locTipoModificador);
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
	private AdminTipoModificador controller;
	public BtnBuscarListener(AdminTipoModificador controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		this.controller.buscar();
	}
}

class BtnSeleccionarTipoTasaListener implements ActionListener {
	private AdminTipoModificador controller;
	public BtnSeleccionarTipoTasaListener(AdminTipoModificador controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.seleccionarTipoTasa();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnLimpiarTipoTasaListener implements ActionListener {
	private AdminTipoModificador controller;
	public BtnLimpiarTipoTasaListener(AdminTipoModificador controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.limpiarTipoTasa();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

