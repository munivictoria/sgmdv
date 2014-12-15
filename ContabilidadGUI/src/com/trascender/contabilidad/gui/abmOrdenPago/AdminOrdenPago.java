package com.trascender.contabilidad.gui.abmOrdenPago;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.trascender.compras.recurso.persistent.suministros.Proveedor;
import com.trascender.contabilidad.gui.abmOrdenPago.abm.AgregarOrdenPago;
import com.trascender.contabilidad.gui.abmOrdenPago.abm.ConsultarOrdenPago;
import com.trascender.contabilidad.gui.abmOrdenPago.abm.EliminarOrdenPago;
import com.trascender.contabilidad.gui.abmOrdenPago.abm.ModificarOrdenPago;
import com.trascender.contabilidad.gui.abmProveedor.AdminProveedor;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.OrdenPago;
import com.trascender.contabilidad.recurso.persistent.DocumentoEgreso.Estado;
import com.trascender.gui.framework.abmStandard.AdminController;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.model.TDefaultComboBoxModel;
import com.trascender.gui.framework.util.Conversor;
import com.trascender.gui.framework.util.Validador;

public class AdminOrdenPago extends AdminController<OrdenPago> {

	private AdminOrdenPagoView view;
	private OrdenPagoBusquedaModel busquedaModel= new OrdenPagoBusquedaModel();
	private OrdenPagoTableModel tableModel = new OrdenPagoTableModel();
	
	
	public AdminOrdenPago(JFrame owner) throws Exception {
		this.view = new AdminOrdenPagoView(owner);
		this.init();
	}
	
	public AdminOrdenPago(JDialog owner) throws Exception {
		this.view = new AdminOrdenPagoView(owner);
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		this.setModels();
		this.setListeners();
		this.habilitarBtnConsultar(false);
	}
	
	private void setModels() {
		this.getView().setBusquedaModel(this.busquedaModel);
		this.getView().setTableModel(this.tableModel);
		this.getView().getCbEstado().setModel(new TDefaultComboBoxModel(Estado.values()));
	}
	
	private void setListeners() {
		this.getView().getPnlBotonesSeleccion().getBtnSeleccionar().addActionListener(new BtnSeleccionarProveedorListener(this));
		this.getView().getPnlBotonesSeleccion().getBtnLimpiar().addActionListener(new BtnLimpiarProveedorListener(this));
		
		this.getView().getPnlBotonesBusqueda().getBtnBuscar().addActionListener(new BtnBuscarListener(this));
		this.getView().getPnlBotonesBusqueda().getBtnReiniciar().addActionListener(new BtnReiniciarListener(this));

		this.getView().getPnlPie().getBtnAgregar().addActionListener(new BtnAgregarListener(this));
		this.getView().getPnlPie().getBtnModificar().addActionListener(new BtnModificarListener(this));
		this.getView().getPnlPie().getBtnEliminar().addActionListener(new BtnEliminarListener(this));
		this.getView().getPnlPie().getBtnConsultar().addActionListener(new BtnConsultarListener(this));
	}
	
	@Override
	protected void actualizarBusquedaModel() {
		OrdenPagoBusquedaModel locModel = this.getBusquedaModel();
		
		locModel.setFechaEmisionDesde(Conversor.getDate(this.getView().getTfFechaEmisionDesde().getText()));
		locModel.setFechaEmisionHasta(Conversor.getDate(this.getView().getTfFechaEmisionHasta().getText()));
		locModel.setFechaPagoDesde(Conversor.getDate(this.getView().getTfFechaPagoDesde().getText()));
		locModel.setFechaPagoHasta(Conversor.getDate(this.getView().getTfFechaPagoHasta().getText()));
		locModel.setImporteDesde(Conversor.getDouble(this.getView().getTfImporteDesde().getValue()));
		locModel.setImporteHasta(Conversor.getDouble(this.getView().getTfImporteHasta().getValue()));

		Object locEstado = this.getView().getCbEstado().getSelectedItem();
		if (locEstado != null) locModel.setEstado((Estado)locEstado); 
		else locModel.setEstado(null);
		
		locModel.fireActualizarDatos();		
	}

	@Override
	protected void actualizarBusquedaView() {
		this.getView().getTfFechaEmisionDesde().setValue(Conversor.getString(this.getBusquedaModel().getFechaEmisionDesde()));
		this.getView().getTfFechaEmisionHasta().setValue(Conversor.getString(this.getBusquedaModel().getFechaEmisionHasta()));
		this.getView().getTfFechaPagoDesde().setValue(Conversor.getString(this.getBusquedaModel().getFechaPagoDesde()));
		this.getView().getTfFechaPagoHasta().setValue(Conversor.getString(this.getBusquedaModel().getFechaPagoHasta()));
		this.getView().getTfImporteDesde().setValue(this.getBusquedaModel().getImporteDesde());
		this.getView().getTfImporteHasta().setValue(this.getBusquedaModel().getImporteHasta());
		this.getView().getTfProveedor().setText(Conversor.getVacioSiNull(this.getBusquedaModel().getProveedor()));
		this.getView().getTfProveedor().setCaretPosition(0);
		
		this.getView().getCbEstado().setSelectedItem(this.getBusquedaModel().getEstado());
	}
	
	void habilitarBtnConsultar(boolean habilitar) {
		this.getView().getPnlPie().getBtnConsultar().setEnabled(habilitar);
	}
	
	
	
	@Override
	public boolean validarDatos() {
		boolean validacionOK = true;
		
		List<Object> attEnteros = new ArrayList<Object>();
		List<JLabel> lblEnteros = new ArrayList<JLabel>();
		
		attEnteros.add(this.getView().getTfImporteDesde().getText());
		lblEnteros.add(this.getView().getLblImporteDesde());
		
		attEnteros.add(this.getView().getTfImporteHasta().getText());
		lblEnteros.add(this.getView().getLblImporteHasta());
		
		List<String> attFecha = new ArrayList<String>();
		List<JLabel> lblFecha = new ArrayList<JLabel>();
		
		attFecha.add(this.getView().getTfFechaEmisionDesde().getText());
		lblFecha.add(this.getView().getLblFechaEmision());
		
		attFecha.add(this.getView().getTfFechaEmisionHasta().getText());
		lblFecha.add(this.getView().getLblFechaEmision());
		
		attFecha.add(this.getView().getTfFechaPagoDesde().getText());
		lblFecha.add(this.getView().getLblFechaPago());
		
		attFecha.add(this.getView().getTfFechaPagoHasta().getText());
		lblFecha.add(this.getView().getLblFechaPago());
		
		List<String> listaErrores = new ArrayList<String>();
		try {
			listaErrores.addAll(Validador.validarEnteros(attEnteros, lblEnteros));
			listaErrores.addAll(Validador.validarFechas(attFecha, lblFecha));
		}
		catch (GuiException ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.getView(), ex.getMessage());
			return false;
		}
		
		if (!listaErrores.isEmpty()) {
			validacionOK = false;
			this.getAbmErrores().getView().setListaErrores(listaErrores);
			this.getAbmErrores().open();
		}
		
		return validacionOK;
	}
	
	void seleccionarOrdenPago() throws Exception {
		AdminProveedor adminProveedor = new AdminProveedor(this.getView());
		this.actualizarBusquedaModel();
		Proveedor locProveedor = adminProveedor.openSelect();
		
		if (locProveedor != null) {
			this.getBusquedaModel().setProveedor(locProveedor);
			this.actualizarBusquedaView();
		}
	}
	
	void limpiarOrdenPago() throws Exception {
		this.getBusquedaModel().setProveedor(null);
		this.actualizarBusquedaModel();
		this.actualizarBusquedaView();
	}
	
	void buscar() throws Exception {
		final AdminOrdenPago controller = this;
		Thread locThread = new Thread(new Runnable(){
			public void run() {
				try {
					controller.actualizarBusquedaModel();
					controller.getView().iniBusqueda();
					List<OrdenPago> locLista = controller.getBusquedaModel().buscar();
					try {
						if (!locLista.isEmpty()) {
						controller.getTableModel().setListaObjetos(locLista);
						controller.habilitarBtnConsultar(true);
					}
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
				catch (Exception ex) {
					ex.printStackTrace();
					AppManager.getInstance().showErrorMsg(controller.getView(), ex.getMessage());
				}
				finally{
					controller.getView().finBusqueda();
				}
			}
		});
		locThread.start();
	}
	
	@Override
	protected void setBtnReiniciarListener() {
		super.setBtnReiniciarListener();
		this.getView().getPnlPie().getBtnConsultar().setEnabled(false);
	}
	
	void openAgregarOrdenPago() throws Exception {
		AgregarOrdenPago agregarOrdenPago = new AgregarOrdenPago(this.getView());
		agregarOrdenPago.open();
		if (agregarOrdenPago.isOperacionRealizada()) {
			this.getTableModel().addRow(agregarOrdenPago.getAbmModel().getObjetoABM());
			this.buscar();
		}
	}

	void openModificarOrdenPago() throws Exception {
		OrdenPago locOrdenPago = this.getSelectedRow();
		if(locOrdenPago != null) {
			locOrdenPago = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionEgresos().getOrdenPagoByID(locOrdenPago.getIdDocumentoEgreso());
			locOrdenPago.getLineasOrdenPago().toString();
			ModificarOrdenPago modificarOrdenPago = new ModificarOrdenPago(this.getView());
			modificarOrdenPago.getAbmModel().setObjetoABM(locOrdenPago);
			modificarOrdenPago.actualizarView();
			modificarOrdenPago.open();
			
			this.buscar();
		}
	}
	
	void openEliminarOrdenPago() throws Exception {
		OrdenPago locOrdenPago = this.getSelectedRow();
		if(locOrdenPago != null) {
			locOrdenPago = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionEgresos().getOrdenPagoByID(locOrdenPago.getIdDocumentoEgreso());
			EliminarOrdenPago eliminarOrdenPago = new EliminarOrdenPago(this.getView());
			eliminarOrdenPago.getAbmModel().setObjetoABM(locOrdenPago);
//			eliminarOrdenPago.getAbmModel().setListaLineasOrdenPago(locOrdenPago.getLineasOrdenPago());
			eliminarOrdenPago.actualizarView();
			eliminarOrdenPago.open();
			this.buscar();
		}
	}
	
	void openConsultarOrdenPago() throws Exception {
		boolean habilitarBtnConfirmar;
		if ((this.getView().getCbEstado().getSelectedIndex() != -1)) {
			if ((this.getView().getCbEstado().getSelectedItem().equals(OrdenPago.Estado.CANCELADA)) ||
					(this.getView().getCbEstado().getSelectedItem().equals(OrdenPago.Estado.CONFIRMADA))) {
				habilitarBtnConfirmar = false; 
			} else {
				habilitarBtnConfirmar = true;
			}
		}else {
			habilitarBtnConfirmar = true;
		}
		
		
		OrdenPago locOrdenPago = this.getSelectedRow();
		if(locOrdenPago != null) {
			locOrdenPago = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionEgresos().getOrdenPagoByID(locOrdenPago.getIdDocumentoEgreso());
			ConsultarOrdenPago consultarOrdenPago = new ConsultarOrdenPago(this.getView(), habilitarBtnConfirmar);
			consultarOrdenPago.getAbmModel().setObjetoABM(locOrdenPago);
			consultarOrdenPago.getAbmModel().setListaLineasOrdenPago(locOrdenPago.getLineasOrdenPago());
			consultarOrdenPago.actualizarView();
			consultarOrdenPago.open();
		}
		//this.buscar();
	}
	
	@Override
	protected OrdenPagoBusquedaModel getBusquedaModel() {
		return this.busquedaModel;
	}

	@Override
	protected OrdenPagoTableModel getTableModel() {
		return this.tableModel;
	}

	@Override
	protected AdminOrdenPagoView getView() {
		return this.view;
	}
}

class BtnSeleccionarProveedorListener implements ActionListener {
	private AdminOrdenPago controller;
	
	public BtnSeleccionarProveedorListener(AdminOrdenPago controller) {
		this.controller = controller;
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.seleccionarOrdenPago();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnLimpiarProveedorListener implements ActionListener {
	private AdminOrdenPago controller;
	
	public BtnLimpiarProveedorListener(AdminOrdenPago controller) {
		this.controller = controller;
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.limpiarOrdenPago();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnBuscarListener implements ActionListener {
	private AdminOrdenPago controller;
	
	public BtnBuscarListener(AdminOrdenPago controller) {
		this.controller = controller;
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.buscar();
			
			if ((this.controller.getView().getCbEstado().getSelectedIndex() != -1)) {
				if (this.controller.getView().getCbEstado().getSelectedItem().equals(OrdenPago.Estado.CREADA)) {
					this.controller.getView().getPnlPie().getBtnConsultar().setEnabled(true);
					this.controller.getView().getPnlPie().getBtnAgregar().setEnabled(true);
					this.controller.getView().getPnlPie().getBtnModificar().setEnabled(true);
					this.controller.getView().getPnlPie().getBtnEliminar().setEnabled(true);
				}
				if (this.controller.getView().getCbEstado().getSelectedItem().equals(OrdenPago.Estado.CONFIRMADA)) {
					this.controller.getView().getPnlPie().getBtnConsultar().setEnabled(true);
					this.controller.getView().getPnlPie().getBtnAgregar().setEnabled(true);
					this.controller.getView().getPnlPie().getBtnModificar().setEnabled(false);
					this.controller.getView().getPnlPie().getBtnEliminar().setEnabled(false);
				}
				if (this.controller.getView().getCbEstado().getSelectedItem().equals(OrdenPago.Estado.CANCELADA)) {
					this.controller.getView().getPnlPie().getBtnConsultar().setEnabled(true);
					this.controller.getView().getPnlPie().getBtnAgregar().setEnabled(true);
					this.controller.getView().getPnlPie().getBtnModificar().setEnabled(false);
					this.controller.getView().getPnlPie().getBtnEliminar().setEnabled(false);
				}
			}
			
		}
		catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnReiniciarListener implements ActionListener {
	private AdminOrdenPago controller;

	public BtnReiniciarListener(AdminOrdenPago controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.setBtnReiniciarListener();
			
		}
		catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnAgregarListener implements ActionListener{
	
	private AdminOrdenPago controller;

	public BtnAgregarListener(AdminOrdenPago controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openAgregarOrdenPago();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnModificarListener implements ActionListener{
	
	private AdminOrdenPago controller;

	public BtnModificarListener(AdminOrdenPago controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openModificarOrdenPago();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnEliminarListener implements ActionListener{
	
	private AdminOrdenPago controller;

	public BtnEliminarListener(AdminOrdenPago controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openEliminarOrdenPago();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnConsultarListener implements ActionListener{
	
	private AdminOrdenPago controller;

	public BtnConsultarListener(AdminOrdenPago controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openConsultarOrdenPago();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}
