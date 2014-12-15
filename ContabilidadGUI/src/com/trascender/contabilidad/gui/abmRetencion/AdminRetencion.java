package com.trascender.contabilidad.gui.abmRetencion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.trascender.compras.recurso.persistent.suministros.Proveedor;
import com.trascender.contabilidad.gui.abmProveedor.AdminProveedor;
import com.trascender.contabilidad.gui.abmRetencion.abm.AgregarRetencion;
import com.trascender.contabilidad.gui.abmRetencion.abm.EliminarRetencion;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.ComprobanteRetencion;
import com.trascender.framework.recurso.transients.Periodo;
import com.trascender.framework.util.Periodicidad;
import com.trascender.gui.framework.abmStandard.AdminController;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Conversor;
import com.trascender.gui.framework.util.Validador;

public class AdminRetencion extends AdminController<ComprobanteRetencion> {

	private RetencionBusquedaModel busquedaModel = new RetencionBusquedaModel();
	private RetencionTableModel tableModel = new RetencionTableModel();
	private AdminRetencionView view;
	
	public AdminRetencion(JFrame owner) throws Exception {
		this.view = new AdminRetencionView(owner);
		this.init();
	}
	
	public AdminRetencion(JDialog owner) throws Exception {
		this.view = new AdminRetencionView(owner);
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		
		this.getView().getPnlPie().getBtnModificar().setEnabled(false);
		
		this.setModels();
		this.setListeners();
	}
	
	private void setModels() {
		this.getView().setBusquedaModel(this.getBusquedaModel());
		this.getView().setTableModel(this.getTableModel());
	}
	
	private void setListeners() {
		this.getView().getPnlBotonesSeleccionProveedor().getBtnSeleccionar().addActionListener(new BtnSeleccionarProveedorListener(this));
		this.getView().getPnlBotonesSeleccionProveedor().getBtnLimpiar().addActionListener(new BtnLimpiarProveedorListener(this));
		
		this.getView().getPnlBotonesBusqueda().getBtnBuscar().addActionListener(new BtnBuscarListener(this));
		
		this.getView().getPnlPie().getBtnAgregar().addActionListener(new BtnAgregarRetencionListener(this));
		this.getView().getPnlPie().getBtnEliminar().addActionListener(new BtnEliminarRetencionListener(this));
	}
	
	@Override
	protected void actualizarBusquedaModel() {
		RetencionBusquedaModel locModel = this.getBusquedaModel();
		if (this.getView().getTfMes().getText() != null && !this.getView().getTfMes().getText().equals("")&& 
			this.getView().getTfAnio().getText() != null && !this.getView().getTfAnio().getText().equals("")) {
			try {
				String fecha = "01/" + this.getView().getTfMes().getText() + "/" + this.getView().getTfAnio().getText();
		        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		        Date fechaFin = sdf.parse(fecha); 
				Calendar calendario= Calendar.getInstance();
				calendario.setTime(fechaFin);
				
				Periodo locPeriodo = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemRegistroValuado()
				.getPeriodo(Periodicidad.MENSUAL, Conversor.getInteger(this.getView().getTfMes().getText()), 
						Conversor.getInteger(this.getView().getTfAnio().getText()));
				
				locModel.setPeriodo(locPeriodo);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
			locModel.fireActualizarDatos();
		}
		
	
	}

	@Override
	protected void actualizarBusquedaView() {
		
		if (this.getBusquedaModel().getProveedor() != null) {
			this.getView().getTfProveedor().setText(Conversor.getVacioSiNull(this.getBusquedaModel().getProveedor()));
		} else {
			this.getView().getTfProveedor().setText("");
		}
		
		
		if (this.getBusquedaModel().getPeriodo() != null) {
			Calendar pFecha = this.getBusquedaModel().getPeriodo().getFechaInicio();
			this.getView().getTfMes().setText(Integer.toString(pFecha.get(Calendar.MONTH)+1));
			this.getView().getTfAnio().setText(Integer.toString(pFecha.get(Calendar.YEAR)));
		} else {
			this.getView().getTfMes().setText("");
			this.getView().getTfAnio().setText("");
		}
		
	}
	
	@Override
	public boolean validarDatos() {
		boolean validacionOK = true;
		
		
		List<Object> attNulos = new ArrayList<Object>();
		List<JLabel> lblNulos = new ArrayList<JLabel>();
		
		List<String> attMes = new ArrayList<String>();
		List<JLabel> lblMes = new ArrayList<JLabel>();
		
		List<Object> attEnteros = new ArrayList<Object>();
		List<JLabel> lblEnteros = new ArrayList<JLabel>();
		
		List<Object> attAnio = new ArrayList<Object>();
		List<JLabel> lblAnio = new ArrayList<JLabel>();
		List<Integer> longAnio = new ArrayList<Integer>();
		
		attEnteros.add(this.getView().getTfMes().getText());
		lblEnteros.add(this.getView().getLblMes());
		
		attEnteros.add(this.getView().getTfAnio().getText());
		lblEnteros.add(this.getView().getLblAnio());
		
		attNulos.add(this.getView().getTfProveedor().getText());
		lblNulos.add(this.getView().getLblProveedor());
		
		attNulos.add(this.getView().getTfMes().getText());
		lblNulos.add(this.getView().getLblMes());
		
		attNulos.add(this.getView().getTfAnio().getText());
		lblNulos.add(this.getView().getLblAnio());
		
		attMes.add(this.getView().getTfMes().getText());
		lblMes.add(this.getView().getLblMes());
		
		attAnio.add(this.getView().getTfAnio().getText());
		lblAnio.add(this.getView().getLblAnio());
		longAnio.add(4);	
		
		List<String> listaErrores = new ArrayList<String>();
		try {
			listaErrores.addAll(Validador.validarEnteros(attEnteros, lblEnteros));
			listaErrores.addAll(Validador.validarNulos(attNulos, lblNulos));
			listaErrores.addAll(Validador.validarMes(attMes, lblMes));
			listaErrores.addAll(Validador.validarLongitudExacta(attAnio, lblAnio, longAnio));
		}
		catch (GuiException ex) {
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

	@Override
	protected RetencionBusquedaModel getBusquedaModel() {
		return this.busquedaModel;
	}

	@Override
	protected RetencionTableModel getTableModel() {
		return this.tableModel;
	}

	@Override
	protected AdminRetencionView getView() {
		return this.view;
	}
	
	void seleccionarProveedor() throws Exception {
		AdminProveedor adminProveedor = new AdminProveedor(this.getView());
		Proveedor locProveedor = adminProveedor.openSelect();
		
		if (locProveedor != null) {
			this.getBusquedaModel().setProveedor(locProveedor);
			this.actualizarBusquedaModel();
			this.actualizarBusquedaView();
		}		
	}
	
	void limpiarProveedor() {
		this.getBusquedaModel().setProveedor(null);
		this.actualizarBusquedaModel();
		this.actualizarBusquedaView();
	}
	
	public void buscar() throws Exception {
		final AdminRetencion controller = this;
		Thread locThread = new Thread(new Runnable(){
			public void run() {
				try {
					controller.actualizarBusquedaModel();
					controller.getView().iniBusqueda();
					List<ComprobanteRetencion> locLista = controller.getBusquedaModel().buscar();
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
	
	void openAgregarRetencion() throws Exception {
		AgregarRetencion agregarRetencion= new AgregarRetencion(this.getView());
		agregarRetencion.open();
		if (agregarRetencion.isOperacionRealizada()) {
			ComprobanteRetencion comprobanteRetencion = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionEgresos().getComprobanteRetencionByID(agregarRetencion.getAbmModel().getObjetoABM().getIdComprobanteRetencion());
			this.getTableModel().addRow(comprobanteRetencion);
			
		}
	}
	
	void openEliminarRetencion() throws Exception {
		ComprobanteRetencion locComprobanteRetencion = this.getSelectedRow();
		
		if (locComprobanteRetencion != null) {
			ComprobanteRetencion comprobanteRetencion = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionEgresos().getComprobanteRetencionByID(locComprobanteRetencion.getIdComprobanteRetencion());
			
			EliminarRetencion eliminarRetencion= new EliminarRetencion(this.getView(), comprobanteRetencion);
			eliminarRetencion.getAbmModel().setProveedor(comprobanteRetencion.getOrdenPago().getProveedor());
			
//			System.out.println(" ID de R : " + comprobanteRetencion.getIdComprobanteRetencion());
//			System.out.println(" O de P : " + comprobanteRetencion.getOrdenPago().getIdDocumentoEgreso());
			
			eliminarRetencion.actualizarView();
			
			eliminarRetencion.getOrdenPagoBusquedaModel().setProveedor(comprobanteRetencion.getOrdenPago().getProveedor());
			eliminarRetencion.getOrdenPagoBusquedaModel().setFechaEmisionDesde(comprobanteRetencion.getPeriodo().getFechaInicio().getTime());
			eliminarRetencion.getOrdenPagoBusquedaModel().setFechaEmisionHasta(comprobanteRetencion.getPeriodo().getFechaFin().getTime());
			
			eliminarRetencion.actualizarABMModel();
			eliminarRetencion.buscarOrdenesDePago();
			eliminarRetencion.open();
			if (eliminarRetencion.isOperacionRealizada()) {
				this.getTableModel().deleteRow(locComprobanteRetencion);
			}
		}
		
	}

}

class BtnSeleccionarProveedorListener implements ActionListener {
	private AdminRetencion controller;
	
	public BtnSeleccionarProveedorListener(AdminRetencion controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.seleccionarProveedor();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnLimpiarProveedorListener implements ActionListener {
	private AdminRetencion controller;
	
	public BtnLimpiarProveedorListener(AdminRetencion controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.limpiarProveedor();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnBuscarListener implements ActionListener {
	private AdminRetencion controller;
	
	public BtnBuscarListener(AdminRetencion controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if (this.controller.validarDatos()) {
				this.controller.buscar();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}

class BtnAgregarRetencionListener implements ActionListener {
	private AdminRetencion controller;
	
	public BtnAgregarRetencionListener(AdminRetencion controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openAgregarRetencion();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}

class BtnEliminarRetencionListener implements ActionListener {
	private AdminRetencion controller;
	
	public BtnEliminarRetencionListener(AdminRetencion controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openEliminarRetencion();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}
