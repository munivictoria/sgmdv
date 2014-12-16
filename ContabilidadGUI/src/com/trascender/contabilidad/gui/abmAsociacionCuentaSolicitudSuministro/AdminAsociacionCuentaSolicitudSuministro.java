package com.trascender.contabilidad.gui.abmAsociacionCuentaSolicitudSuministro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.compras.recurso.persistent.suministros.Bien;
import com.trascender.compras.recurso.persistent.suministros.SolicitudSuministro;
import com.trascender.contabilidad.gui.abmArea.AdminArea;
import com.trascender.contabilidad.gui.abmAsociacionCuentaSolicitudSuministro.abm.AgregarAsociacionCuentaSolicitudSuministro;
import com.trascender.contabilidad.gui.abmAsociacionCuentaSolicitudSuministro.abm.ConsultarAsociacionCuentaSolicitudSuministro;
import com.trascender.contabilidad.gui.abmAsociacionCuentaSolicitudSuministro.abm.EliminarAsociacionCuentaSolicitudSuministro;
import com.trascender.contabilidad.gui.abmAsociacionCuentaSolicitudSuministro.abm.ModificarAsociacionCuentaSolicitudSuministro;
import com.trascender.contabilidad.gui.abmBien.AdminBien;
import com.trascender.contabilidad.gui.abmSolicitudSuministros.AdminSolicitudSuministroView;
import com.trascender.contabilidad.gui.abmSolicitudSuministros.SolicitudSuministroBusquedaModel;
import com.trascender.contabilidad.gui.abmSolicitudSuministros.SolicitudSuministroTableModel;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Area;
import com.trascender.gui.framework.abmStandard.AdminController;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.model.TDefaultComboBoxModel;
import com.trascender.gui.framework.util.Conversor;

public class AdminAsociacionCuentaSolicitudSuministro extends AdminController<SolicitudSuministro> {
	
	private final String NOMBRE_RECURSO = "AdminAsociacionCuentaSolicitudSuministro";

	private SolicitudSuministroBusquedaModel busquedaModel = new SolicitudSuministroBusquedaModel();
	private SolicitudSuministroTableModel tableModel;
	private AdminSolicitudSuministroView view;
	
	public AdminAsociacionCuentaSolicitudSuministro(JDialog owner) {
		try {
			this.tableModel = new SolicitudSuministroTableModel();
			this.view = new AdminSolicitudSuministroView(owner);
			this.init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public AdminAsociacionCuentaSolicitudSuministro(JFrame owner) {
		try {
			this.tableModel = new SolicitudSuministroTableModel();
			this.view = new AdminSolicitudSuministroView(owner);
			this.init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void init() {
		super.init();
		this.getView().getLblTitulo().setVisible(true);
		this.getView().getLblTitulo().setText("Seleccione la Solicitud de Suministros deseada para asociarla con una Cuenta.");
		this.getView().getPnlPie().getBtnModificar().setEnabled(false);
		this.getView().getPnlPie().getBtnEliminar().setEnabled(false);
		this.getView().getPnlPie().getBtnConsultar().setEnabled(false);
		this.setTituloVentana();
		this.setModels();
		this.setListeners();
	}
	
	public void setTituloVentana() {
		String locTitulo = MessagesContabilidad.getString(NOMBRE_RECURSO+".titulo");
		this.getView().setTitle(locTitulo);
		this.getView().getPnlCabecera().getLblTitulo().setText(locTitulo);
	}
	
	private void setModels() {
		this.getView().setBusquedaModel(this.getBusquedaModel());
		this.getView().setTableModel(this.getTableModel());
	}
	
	private void setListeners() {
		this.getView().getPnlBotonesSeleccionProducto().getBtnSeleccionar().addActionListener(new BtnSeleccionarProductoListener(this));
		this.getView().getPnlBotonesSeleccionProducto().getBtnLimpiar().addActionListener(new BtnLimpiarProductoListener(this));
		
		this.getView().getPnlBotonesSeleccionArea().getBtnSeleccionar().addActionListener(new BtnSeleccionarAreaListener(this));
		this.getView().getPnlBotonesSeleccionArea().getBtnLimpiar().addActionListener(new BtnLimpiarAreaListener(this));
		
		this.getView().getPnlBotonesBusqueda().getBtnBuscar().addActionListener(new BtnBuscarListener(this));
		this.getView().getPnlBotonesBusqueda().getBtnReiniciar().addActionListener(new BtnReiniciarListener(this));
		
		this.getView().getPnlPie().getBtnAgregar().addActionListener(new BtnAgregarListener(this));
		this.getView().getPnlPie().getBtnModificar().addActionListener(new BtnModificarListener(this));
		this.getView().getPnlPie().getBtnEliminar().addActionListener(new BtnEliminarListener(this));
		this.getView().getPnlPie().getBtnConsultar().addActionListener(new BtnConsultarListener(this));
	}

	@Override
	protected void actualizarBusquedaModel() {
		SolicitudSuministroBusquedaModel locModel = this.getBusquedaModel();
		
		Object locEstado = this.getView().getCbEstado().getSelectedItem();
		
		locModel.fireActualizarDatos();
	}

	@Override
	protected void actualizarBusquedaView() {
		this.getView().getTfproducto().setText(Conversor.getVacioSiNull(this.getBusquedaModel().getBienAsociado()));
		this.getView().getTfArea().setText(Conversor.getVacioSiNull(this.getBusquedaModel().getArea()));
		//this.getView().getCbEstado().setSelectedItem(SolicitudSuministro.Estado.CREADA);
	}
	
	public void buscar() throws Exception {
		final AdminAsociacionCuentaSolicitudSuministro controller = this;
		Thread locThread = new Thread(new Runnable(){
			public void run() {
				try {
					controller.actualizarBusquedaModel();
					controller.getView().iniBusqueda();
					List<SolicitudSuministro> locLista = controller.getBusquedaModel().buscar();
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
	
	@Override
	protected void setBtnReiniciarListener() {
		super.setBtnReiniciarListener();
	}
	
	public void agregarAsociacion() throws RemoteException, TrascenderException, Exception {
//		SolicitudSuministro locLineaSolicitudSuministro = this.getSelectedRow();
//		
//		if (locLineaSolicitudSuministro != null) {
//			SolicitudSuministro solicitudSuministro =  ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionSolicitudSuministro().findSolicitudSuministroByID(locLineaSolicitudSuministro.getIdSolicitudSuministro());
//			
//			AsociarCuentaSolicitudSuministro asociarCuentaLineaSolicitudSuministro = new AsociarCuentaSolicitudSuministro(this.getView());
//
//			asociarCuentaLineaSolicitudSuministro.getAbmModel().setObjetoABM(solicitudSuministro);
//
//			asociarCuentaLineaSolicitudSuministro.actualizarView(); 
//			asociarCuentaLineaSolicitudSuministro.open();
//
//			if (asociarCuentaLineaSolicitudSuministro.isOperacionRealizada()) {
//				solicitudSuministro.setCuentaRfr(asociarCuentaLineaSolicitudSuministro.getAbmModel().getCuentaRfr());
//				//this.getTableModel().updateRow(locLineaSolicitudSuministro);
//			}
//			this.buscar();
//		}
		
		SolicitudSuministro locLineaSolicitudSuministro = this.getSelectedRow();
		
		if (locLineaSolicitudSuministro != null) {
			SolicitudSuministro solicitudSuministro =  ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionSolicitudSuministro().findSolicitudSuministroByID(locLineaSolicitudSuministro.getIdSolicitudSuministro());
			
			AgregarAsociacionCuentaSolicitudSuministro agregarAsociacionCuentaLineaSolicitudSuministro = new AgregarAsociacionCuentaSolicitudSuministro(this.getView());

			agregarAsociacionCuentaLineaSolicitudSuministro.getAbmModel().setObjetoABM(solicitudSuministro);

			agregarAsociacionCuentaLineaSolicitudSuministro.actualizarView(); 
			agregarAsociacionCuentaLineaSolicitudSuministro.open();

//			if (agregarAsociacionCuentaLineaSolicitudSuministro.isOperacionRealizada()) {
//				solicitudSuministro.setCuentaRfr(agregarAsociacionCuentaLineaSolicitudSuministro.getAbmModel().getCuentaRfr());
//				//this.getTableModel().updateRow(locLineaSolicitudSuministro);
//				this.buscar();
//			}
			
		}
	}
	
	public void modificarAsociacion() throws RemoteException, TrascenderException, Exception {
		SolicitudSuministro locLineaSolicitudSuministro = this.getSelectedRow();
		
		if (locLineaSolicitudSuministro != null) {
			SolicitudSuministro solicitudSuministro =  ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionSolicitudSuministro().findSolicitudSuministroByID(locLineaSolicitudSuministro.getIdSolicitudSuministro());
			
			ModificarAsociacionCuentaSolicitudSuministro modificarAsociacionCuentaLineaSolicitudSuministro = new ModificarAsociacionCuentaSolicitudSuministro(this.getView());
			modificarAsociacionCuentaLineaSolicitudSuministro.getAbmModel().setObjetoABM(solicitudSuministro);
			modificarAsociacionCuentaLineaSolicitudSuministro.actualizarView();
			
//			Cuenta cta = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().getCuentaByID(solicitudSuministro.getCuentaRfr().getIdCuenta());
//			modificarAsociacionCuentaLineaSolicitudSuministro.getView().getTfCuenta().setText(cta.getCodigoImputacionCompleto() + " " + cta.getNombre());
			modificarAsociacionCuentaLineaSolicitudSuministro.open();

//			if (modificarAsociacionCuentaLineaSolicitudSuministro.isOperacionRealizada() && this.getTableModel().getRowCount() > 0) {
//				solicitudSuministro.setCuentaRfr(modificarAsociacionCuentaLineaSolicitudSuministro.getAbmModel().getCuentaRfr());
//				this.buscar();
//			}
			//this.buscar();
		}
	}
	
	public void eliminarAsociacion() throws RemoteException, TrascenderException, Exception {
		SolicitudSuministro locLineaSolicitudSuministro = this.getSelectedRow();
		
		if (locLineaSolicitudSuministro != null) {
			SolicitudSuministro solicitudSuministro =  ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionSolicitudSuministro().findSolicitudSuministroByID(locLineaSolicitudSuministro.getIdSolicitudSuministro());
			
			EliminarAsociacionCuentaSolicitudSuministro eliminarAsociacionCuentaLineaSolicitudSuministro = new EliminarAsociacionCuentaSolicitudSuministro(this.getView());
			eliminarAsociacionCuentaLineaSolicitudSuministro.getAbmModel().setObjetoABM(solicitudSuministro);
			eliminarAsociacionCuentaLineaSolicitudSuministro.actualizarView(); 
//			Cuenta cta = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().getCuentaByID(solicitudSuministro.getCuentaRfr().getIdCuenta());
//			eliminarAsociacionCuentaLineaSolicitudSuministro.getView().getTfCuenta().setText(cta.getCodigoImputacionCompleto() + " " + cta.getNombre());
			eliminarAsociacionCuentaLineaSolicitudSuministro.open();

//			if (eliminarAsociacionCuentaLineaSolicitudSuministro.isOperacionRealizada() && this.getTableModel().getRowCount() > 0) {
//				solicitudSuministro.setCuentaRfr(null);
//				this.buscar();
//			}
			//this.buscar();
		}
	}
	
	public void consultarAsociacion() throws RemoteException, TrascenderException, Exception {
		SolicitudSuministro locLineaSolicitudSuministro = this.getSelectedRow();
		
		if (locLineaSolicitudSuministro != null) {
			SolicitudSuministro solicitudSuministro =  ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionSolicitudSuministro().findSolicitudSuministroByID(locLineaSolicitudSuministro.getIdSolicitudSuministro());
			
			ConsultarAsociacionCuentaSolicitudSuministro consultarAsociacionCuentaLineaSolicitudSuministro = new ConsultarAsociacionCuentaSolicitudSuministro(this.getView());
			consultarAsociacionCuentaLineaSolicitudSuministro.getAbmModel().setObjetoABM(solicitudSuministro);
			consultarAsociacionCuentaLineaSolicitudSuministro.actualizarView(); 
//			if (solicitudSuministro.getCuentaRfr() != null) {	
//				Cuenta cta = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().getCuentaByID(solicitudSuministro.getCuentaRfr().getIdCuenta());
//			
//				consultarAsociacionCuentaLineaSolicitudSuministro.getView().getTfCuenta().setText(cta.getCodigoImputacionCompleto() + " " + cta.getNombre());
//			}
			
			//consultarAsociacionCuentaLineaSolicitudSuministro.getView().getTfCuenta().setText(cta.getCodigoImputacionCompleto() + " " + cta.getNombre());
			consultarAsociacionCuentaLineaSolicitudSuministro.open();

//			if (consultarAsociacionCuentaLineaSolicitudSuministro.isOperacionRealizada() && this.getTableModel().getRowCount() > 0) {
//				solicitudSuministro.setCuentaRfr(null);
//				this.buscar();
//			}
			//this.buscar();
		}
	}
	
	public void seleccionarBien() throws Exception {
		AdminBien adminBien = new AdminBien(this.getView());
		this.actualizarBusquedaModel();
		Bien locBien = adminBien.openSelect();
		if (locBien != null) {
			this.getBusquedaModel().setBienAsociado(locBien);
			this.actualizarBusquedaView();
		}
	}
	
	public void limpiarBien() throws Exception {
		this.getBusquedaModel().setBienAsociado(null);
		this.actualizarBusquedaModel();
		this.actualizarBusquedaView();
	}

	public void seleccionarArea() throws Exception {
		AdminArea adminArea = new AdminArea(this.getView());
		this.actualizarBusquedaModel();
		Area locArea = adminArea.openSelect();
		if (locArea != null) {
			this.getBusquedaModel().setArea(locArea);
			this.actualizarBusquedaView();
		}
	}
	
	public void limpiarArea() throws Exception {
		this.getBusquedaModel().setArea(null);
		this.actualizarBusquedaModel();
		this.actualizarBusquedaView();
	}
	
	
	@Override
	protected SolicitudSuministroBusquedaModel getBusquedaModel() {
		return this.busquedaModel;
	}
	
	@Override
	protected SolicitudSuministroTableModel getTableModel() {
		return this.tableModel;
	}

	@Override
	protected AdminSolicitudSuministroView getView() {
		return this.view;
	}

}

class BtnSeleccionarProductoListener implements ActionListener {
	AdminAsociacionCuentaSolicitudSuministro controller;
	
	public BtnSeleccionarProductoListener(
			AdminAsociacionCuentaSolicitudSuministro controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.seleccionarBien();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}

class BtnLimpiarProductoListener implements ActionListener {
	AdminAsociacionCuentaSolicitudSuministro controller;
		
	public BtnLimpiarProductoListener(
			AdminAsociacionCuentaSolicitudSuministro controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.limpiarBien();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}

class BtnSeleccionarAreaListener implements ActionListener {
	AdminAsociacionCuentaSolicitudSuministro controller;
		
	public BtnSeleccionarAreaListener(
			AdminAsociacionCuentaSolicitudSuministro controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.seleccionarArea();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}

class BtnLimpiarAreaListener implements ActionListener {
	AdminAsociacionCuentaSolicitudSuministro controller;
		
	public BtnLimpiarAreaListener(AdminAsociacionCuentaSolicitudSuministro controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.limpiarArea();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}

class BtnBuscarListener implements ActionListener {
	AdminAsociacionCuentaSolicitudSuministro controller;
		
	public BtnBuscarListener(AdminAsociacionCuentaSolicitudSuministro controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.buscar();
//			if (this.controller.getView().getCbEstado().getSelectedItem().equals(SolicitudSuministro.Estado.CREADA) 
//					|| this.controller.getView().getCbEstado().getSelectedItem().equals(SolicitudSuministro.Estado.ACEPTADA)) {
//				this.controller.getView().getPnlPie().getBtnAgregar().setEnabled(true);
//			}
//			else {
//				this.controller.getView().getPnlPie().getBtnAgregar().setEnabled(false);
//			}
			
//			if (this.controller.getView().getCbEstado().getSelectedItem().equals(SolicitudSuministro.Estado.ACEPTADA)) {
//				this.controller.getView().getPnlPie().getBtnModificar().setEnabled(true);
//				this.controller.getView().getPnlPie().getBtnEliminar().setEnabled(true);
//				this.controller.getView().getPnlPie().getBtnConsultar().setEnabled(true);
//				
//				this.controller.getView().getPnlPie().getBtnAgregar().setEnabled(false);
//			}
//			else {
//				this.controller.getView().getPnlPie().getBtnModificar().setEnabled(false);
//				this.controller.getView().getPnlPie().getBtnEliminar().setEnabled(false);
//				this.controller.getView().getPnlPie().getBtnConsultar().setEnabled(true);
//				this.controller.getView().getPnlPie().getBtnAgregar().setEnabled(true);
//			}
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
		
	}
	
}

class BtnReiniciarListener implements ActionListener {
	AdminAsociacionCuentaSolicitudSuministro controller;
		
	public BtnReiniciarListener(AdminAsociacionCuentaSolicitudSuministro controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.setBtnReiniciarListener();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
		
	}
	
}

class BtnAgregarListener implements ActionListener {
	AdminAsociacionCuentaSolicitudSuministro controller;
	
	public BtnAgregarListener(AdminAsociacionCuentaSolicitudSuministro controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.agregarAsociacion();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}

class BtnModificarListener implements ActionListener {
	AdminAsociacionCuentaSolicitudSuministro controller;
	
	public BtnModificarListener(
			AdminAsociacionCuentaSolicitudSuministro controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.modificarAsociacion();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}

class BtnEliminarListener implements ActionListener {
	AdminAsociacionCuentaSolicitudSuministro controller;
	
	public BtnEliminarListener(
			AdminAsociacionCuentaSolicitudSuministro controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.eliminarAsociacion();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}

class BtnConsultarListener implements ActionListener {
	AdminAsociacionCuentaSolicitudSuministro controller;

	public BtnConsultarListener(
			AdminAsociacionCuentaSolicitudSuministro controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.consultarAsociacion();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}
