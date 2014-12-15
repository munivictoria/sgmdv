package com.trascender.contabilidad.gui.abmAsociacionCuentaArticulo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;

import com.trascender.compras.recurso.persistent.inventario.Articulo;
import com.trascender.contabilidad.gui.abmArticulo.AdminArticulo;
import com.trascender.contabilidad.gui.abmAsociacionCuentaArticulo.abm.AgregarAsociacionCuentaArticulo;
import com.trascender.contabilidad.gui.abmAsociacionCuentaArticulo.abm.ModificarAsociacionCuentaArticulo;
import com.trascender.contabilidad.gui.abmAsociacionCuentaArticulo.abmEliminar.EliminarAsociacionCuentaArticulo;
import com.trascender.contabilidad.gui.abmAsociacionCuentaTipoTasa.AdminAsociacionCuentaTipoTasa;
import com.trascender.contabilidad.gui.abmAsociacionCuentaTipoTasa.abm.AgregarAsociacionCuentaTipoTasa;
import com.trascender.contabilidad.gui.abmAsociacionCuentaTipoTasa.abm.ModificarAsociacionCuentaTipoTasa;
import com.trascender.contabilidad.gui.abmCuenta.AdminCuenta;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.BajaArticulo;
import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.contabilidad.recurso.persistent.CuentaArticulo;
import com.trascender.contabilidad.recurso.persistent.CuentaTipoTasa;
import com.trascender.gui.framework.abmStandard.AdminController;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Conversor;
import com.trascender.gui.framework.util.Validador;

public class AdminAsociacionCuentaArticulo extends AdminController<CuentaArticulo>{
	
	private AdminAsociacionCuentaArticuloView view;
	
	private AsociacionCuentaArticuloTableModel tableModel;
	private AsociacionCuentaArticuloBusquedaModel busquedaModel = new AsociacionCuentaArticuloBusquedaModel();
	
	public AdminAsociacionCuentaArticulo(JFrame frame){
		this.view = new AdminAsociacionCuentaArticuloView(frame);
		try{
			tableModel = new AsociacionCuentaArticuloTableModel();
		} catch (Exception e){
			e.printStackTrace();
		}
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		this.setModels();
		this.setListeners();
		this.getView().getPnlPie().getBtnEliminar().setText("Dar de baja");
	}
	
	private void setModels() {
		this.getView().setBusquedaModel(this.getBusquedaModel());
		this.getView().setTableModel(this.getTableModel());
	}
	
	private void setListeners() {
		this.getView().getPnlBotonesSeleccionArticulo().getBtnSeleccionar().addActionListener(new BtnSeleccionarArticuloListener(this));
		this.getView().getPnlBotonesSeleccionArticulo().getBtnLimpiar().addActionListener(new BtnLimpiarArticuloListener(this));
		this.getView().getPnlBotonesSeleccionCuenta().getBtnSeleccionar().addActionListener(new BtnSeleccionarCuentaListener(this));
		this.getView().getPnlBotonesSeleccionCuenta().getBtnLimpiar().addActionListener(new BtnLimpiarCuentaListener(this));
		
		this.getView().getPnlBotonesBusqueda().getBtnBuscar().addActionListener(new BtnBuscarListener(this));
		
		this.getView().getPnlPie().getBtnAgregar().addActionListener(new BtnAgregarListener(this));
		this.getView().getPnlPie().getBtnModificar().addActionListener(new BtnModificarListener(this));
		this.getView().getPnlPie().getBtnEliminar().addActionListener(new BtnEliminarListener(this));
	}

	@Override
	protected AdminAsociacionCuentaArticuloView getView() {
		return this.view;
	}

	@Override
	protected AsociacionCuentaArticuloTableModel getTableModel() {
		return this.tableModel;
	}

	@Override
	protected AsociacionCuentaArticuloBusquedaModel getBusquedaModel() {
		return this.busquedaModel;
	}

	@Override
	protected void actualizarBusquedaModel() {
		Integer locAnio = Conversor.getInteger(this.getView().getTfAnioPeriodo().getText());
		this.getBusquedaModel().setAnio(locAnio);
		this.getBusquedaModel().fireActualizarDatos();
		
	}

	@Override
	protected void actualizarBusquedaView() {
		this.getView().getTfArticulo().setText(Conversor.getVacioSiNull(this.getBusquedaModel().getArticulo()));
		this.getView().getTfCuenta().setText(Conversor.getVacioSiNull(this.getBusquedaModel().getCuenta()));
	}
	
	void buscar() {
		if (this.validarDatos()) {
			final AdminAsociacionCuentaArticulo controller = this;
			Thread locThread = new Thread(new Runnable() {
				public void run() {
					try {
						controller.actualizarBusquedaModel();
						controller.getView().iniBusqueda();
						List<CuentaArticulo> locCuentaArticulo = controller.getBusquedaModel().buscar();
						controller.getTableModel().setListaObjetos(locCuentaArticulo);
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
	
	@Override
	public boolean validarDatos() {
		boolean validacionOK = true;
		
		List<Object> attAnio = new ArrayList<Object>();
		List<JLabel> lblAnio = new ArrayList<JLabel>();
		List<Integer> longAnio = new ArrayList<Integer>();
		
		attAnio.add(this.getView().getTfAnioPeriodo().getText());
		lblAnio.add(this.getView().getLblAnioPeriodo());
		longAnio.add(4);
		
		List<String> listaErrores = new ArrayList<String>();
		try {
			listaErrores.addAll(Validador.validarEnteros(attAnio, lblAnio));
			listaErrores.addAll(Validador.validarLongitudExacta(attAnio, lblAnio, longAnio));
		} catch (GuiException ex) {
			ex.printStackTrace();
			return false;
		}
		
		if (!listaErrores.isEmpty()) {
			validacionOK = false;
			this.mostrarErroresValidacion(listaErrores);
		}
		return validacionOK;
	}
	
	void openAgregarAsocioacionCuentaArticulo() throws Exception {
		AgregarAsociacionCuentaArticulo agregarAsociacionCuentaTipoArticulo = new AgregarAsociacionCuentaArticulo(this.getView());
		agregarAsociacionCuentaTipoArticulo.open();
		if (agregarAsociacionCuentaTipoArticulo.isOperacionRealizada() && this.getTableModel().getRowCount() > 0){
			this.buscar();
		}
	}
	
	void openModificarAsociacionCuentaArticulo() throws Exception {
		CuentaArticulo locCuentaArticulo = this.getSelectedRow();
		if (locCuentaArticulo != null){
			locCuentaArticulo = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().getCuentaArticuloPorId(locCuentaArticulo.getIdAsociacionCuenta());
			ModificarAsociacionCuentaArticulo modificarCuentaArticulo = new ModificarAsociacionCuentaArticulo(this.getView());
			modificarCuentaArticulo.getAbmModel().setObjetoABM(locCuentaArticulo);
			modificarCuentaArticulo.actualizarView();
			modificarCuentaArticulo.open();
			if (modificarCuentaArticulo.isOperacionRealizada() && this.getTableModel().getRowCount() > 0){
				this.buscar();
			}
		}
	}
	
	void openEliminarAsociacionCuentaArticulo() throws Exception {
		CuentaArticulo locCuentaArticulo = this.getSelectedRow();
		if (locCuentaArticulo != null){
			locCuentaArticulo = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().getCuentaArticuloPorId(locCuentaArticulo.getIdAsociacionCuenta());
			EliminarAsociacionCuentaArticulo eliminarAsociacion = new EliminarAsociacionCuentaArticulo(getView());
			eliminarAsociacion.getAbmModel().setObjetoABM(locCuentaArticulo);
			if (locCuentaArticulo.getBajaArticulo() != null){
				eliminarAsociacion.getAbmModelBajaArticulo().setObjetoABM(locCuentaArticulo.getBajaArticulo());
			} else {
				eliminarAsociacion.getAbmModelBajaArticulo().setObjetoABM(new BajaArticulo());
			}
			eliminarAsociacion.actualizarView();
			eliminarAsociacion.open();
			if (eliminarAsociacion.isOperacionRealizada() && this.getTableModel().getRowCount() > 0){
				this.buscar();
			}
		}
	}
	
	void seleccionarCuenta() throws Exception {
		AdminCuenta adminCuenta = new AdminCuenta(this.getView());
		Cuenta locCuenta = adminCuenta.openSelect();
		
		if (locCuenta != null) {
			this.getBusquedaModel().setCuenta(locCuenta);
			this.actualizarBusquedaModel();
			this.actualizarBusquedaView();
		}
	}
	
	void limpiarCuenta() throws Exception {
		this.getBusquedaModel().setCuenta(null);
		this.actualizarBusquedaModel();
		this.actualizarBusquedaView();
	}
	
	void seleccionarArticulo() throws Exception {
		AdminArticulo adminArticulo = new AdminArticulo(this.getView());
		Articulo locArticulo = adminArticulo.openSelect();
		
		if (locArticulo != null) {
			this.getBusquedaModel().setArticulo(locArticulo);
			this.actualizarBusquedaModel();
			this.actualizarBusquedaView();
		}
	}
	
	void limpiarArticulo() throws Exception{
		this.getBusquedaModel().setArticulo(null);
		this.actualizarBusquedaModel();
		this.actualizarBusquedaView();
	}
	
}
	
	class BtnSeleccionarCuentaListener implements ActionListener {
		AdminAsociacionCuentaArticulo controller;
		
		public BtnSeleccionarCuentaListener(
				AdminAsociacionCuentaArticulo controller) {
			this.controller = controller;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				this.controller.seleccionarCuenta();
			} catch (Exception ex) {
				ex.printStackTrace();
				AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
			}
		}
		
	}

	class BtnLimpiarCuentaListener implements ActionListener {
		AdminAsociacionCuentaArticulo controller;
		
		public BtnLimpiarCuentaListener(
				AdminAsociacionCuentaArticulo controller) {
			this.controller = controller;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				this.controller.limpiarCuenta();
			} catch (Exception ex) {
				ex.printStackTrace();
				AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
			}
		}
		
	}
	
	class BtnSeleccionarArticuloListener implements ActionListener {
		AdminAsociacionCuentaArticulo controller;
		
		public BtnSeleccionarArticuloListener(
				AdminAsociacionCuentaArticulo controller) {
			this.controller = controller;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				this.controller.seleccionarArticulo();
			} catch (Exception ex) {
				ex.printStackTrace();
				AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
			}
		}
	}

	class BtnLimpiarArticuloListener implements ActionListener {
		AdminAsociacionCuentaArticulo controller;
		
		public BtnLimpiarArticuloListener(
				AdminAsociacionCuentaArticulo controller) {
			this.controller = controller;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				this.controller.limpiarArticulo();
			} catch (Exception ex) {
				ex.printStackTrace();
				AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
			}
		}
	}
	
	class BtnBuscarListener implements ActionListener {
		AdminAsociacionCuentaArticulo controller;
		
		public BtnBuscarListener(
				AdminAsociacionCuentaArticulo controller) {
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
	
	class BtnAgregarListener implements ActionListener {
		private AdminAsociacionCuentaArticulo controller;
		public BtnAgregarListener(AdminAsociacionCuentaArticulo controller) {
			this.controller = controller;
		}
		public void actionPerformed(ActionEvent e) {
			try {
				this.controller.openAgregarAsocioacionCuentaArticulo();
			} catch (Exception ex) {
				ex.printStackTrace();
				AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
			}
		}
	}

	class BtnModificarListener implements ActionListener {
		private AdminAsociacionCuentaArticulo controller;
		public BtnModificarListener(AdminAsociacionCuentaArticulo controller) {
			this.controller = controller;
		}
		public void actionPerformed(ActionEvent e) {
			try {
				this.controller.openModificarAsociacionCuentaArticulo();
			} catch (Exception ex) {
				ex.printStackTrace();
				AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
			}
		}
	}
	
	class BtnEliminarListener implements ActionListener {
		private AdminAsociacionCuentaArticulo controller;
		public BtnEliminarListener(AdminAsociacionCuentaArticulo controller) {
			this.controller = controller;
		}
		public void actionPerformed(ActionEvent e) {
			try {
				this.controller.openEliminarAsociacionCuentaArticulo();
			} catch (Exception ex) {
				ex.printStackTrace();
				AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
			}
		}
	}
