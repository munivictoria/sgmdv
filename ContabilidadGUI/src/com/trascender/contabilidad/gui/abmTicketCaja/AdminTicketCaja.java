package com.trascender.contabilidad.gui.abmTicketCaja;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.trascender.contabilidad.recurso.persistent.TicketCaja;
import com.trascender.contabilidad.recurso.persistent.TicketCaja.Estado;
import com.trascender.gui.framework.abmStandard.AdminController;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Conversor;
import com.trascender.gui.framework.util.Validador;

public class AdminTicketCaja extends AdminController<TicketCaja> {
	
	private AdminTicketcajaView view;
	private TicketCajaBusquedaModel busquedaModel = new TicketCajaBusquedaModel();
	private TicketCajaTableModel tableModel = new TicketCajaTableModel();
	
	public AdminTicketCaja(JDialog owner) throws Exception {
		this.view = new AdminTicketcajaView(owner);
		this.init();
	}
	
	public AdminTicketCaja(JFrame owner) throws Exception {
		this.view = new AdminTicketcajaView(owner);
		this.init();
	}

	@Override
	protected void init() {
		super.init();
		this.setModels();
		this.setListeners();
		
		this.getView().getPnlPie().getBtnAgregar().setEnabled(false);
		this.getView().getPnlPie().getBtnModificar().setEnabled(false);
		this.getView().getPnlPie().getBtnEliminar().setEnabled(false);
	}
	
	private void setModels() {
		this.getView().setBusquedaModel(this.busquedaModel);
		this.getView().setTableModel(this.tableModel);
	}
	
	private void setListeners() {
//		this.getView().getPnlBotonesSeleccionUsuario().getBtnSeleccionar().addActionListener(new BtnSeleccionarUsuarioListener(this));
//		this.getView().getPnlBotonesSeleccionUsuario().getBtnLimpiar().addActionListener(new BtnLimpiarUsuarioListener(this));
//		
//		this.getView().getPnlBotonesSeleccionCaja().getBtnSeleccionar().addActionListener(new BtnSeleccionarCajaListener(this));
//		this.getView().getPnlBotonesSeleccionCaja().getBtnLimpiar().addActionListener(new BtnLimpiarCajaListener(this));
		
		this.getView().getPnlBotonesBusqueda().getBtnBuscar().addActionListener(new BtnBuscarListener(this));
		
		this.getView().getPnlPie().getBtnSeleccionar().addActionListener(new BtnSeleccionarListener(this));
	}
	
	@Override
	protected void actualizarBusquedaModel() {
		TicketCajaBusquedaModel locModel = this.getBusquedaModel();
		
		locModel.setFechaDesde(Conversor.getDate(this.getView().getFtfFechaDesde().getText()));
		locModel.setFechaHasta(Conversor.getDate(this.getView().getFtfFechaHasta().getText()));
		locModel.setNumero(Conversor.getInteger(this.getView().getTfNumero().getText()));
				
		locModel.fireActualizarDatos();		
	}

	@Override
	protected void actualizarBusquedaView() {
		this.getView().getFtfFechaDesde().setValue(Conversor.getString(this.getBusquedaModel().getFechaDesde()));
		this.getView().getFtfFechaHasta().setValue(Conversor.getString(this.getBusquedaModel().getFechaHasta()));
		this.getView().getTfNumero().setText(Conversor.getVacioSiNull(this.getBusquedaModel().getNumero()));
		this.getView().getTfUsuario().setText(Conversor.getVacioSiNull(this.getBusquedaModel().getUsuario()));
		this.getView().getTfCaja().setText(Conversor.getVacioSiNull(this.getBusquedaModel().getCaja()));
	}

	@Override
	protected TicketCajaBusquedaModel getBusquedaModel() {
		return this.busquedaModel;
	}

	@Override
	protected TicketCajaTableModel getTableModel() {
		return this.tableModel;
	}

	@Override
	protected AdminTicketcajaView getView() {
		return this.view;
	}
	
	@Override
	public boolean validarDatos() {
		boolean validacionOK = true;
		
		List<String> attFecha = new ArrayList<String>();
		List<JLabel> lblFecha = new ArrayList<JLabel>();
		
		attFecha.add(this.getView().getFtfFechaDesde().getText());
		lblFecha.add(this.getView().getLblFechaDesdeHasta());
		
		attFecha.add(this.getView().getFtfFechaHasta().getText());
		lblFecha.add(this.getView().getLblFechaDesdeHasta());
		
		
		List<String> listaErrores = new ArrayList<String>();
		try {
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
	
	void buscar() throws Exception {
		final AdminTicketCaja controller = this;
		Thread locThread = new Thread(new Runnable(){
			public void run() {
				try {
					controller.actualizarBusquedaModel();
					controller.getView().iniBusqueda();
					List<TicketCaja> locLista = controller.getBusquedaModel().buscar();
					try {
						if (locLista != null && !locLista.isEmpty()) {
							controller.getTableModel().setListaObjetos(locLista);
						}else {
							controller.getTableModel().clearTable();
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
	public TicketCaja getSelectedRow() {
		TicketCaja objetoSeleccionado = null;
		
		if (this.getView() != null && this.getView().getPnlTabla().getTblDatos() != null) {
			int selectedRow = this.getView().getPnlTabla().getTblDatos().getSelectedRow();
			if (selectedRow > -1) {
				objetoSeleccionado = this.getTableModel().getRow(selectedRow);
				if (objetoSeleccionado.getEstado()!= Estado.ACTIVO) {
					AppManager.getInstance().showInformationMsg(this.getView(), "El ticket seleccionado no es un Ticket Activo");
					objetoSeleccionado = null;
				} 
			}
		}
		return objetoSeleccionado;
	}
	
	

}

class BtnBuscarListener implements ActionListener {
	private AdminTicketCaja controller;
	
	public BtnBuscarListener(AdminTicketCaja controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
//			if (this.controller.validarDatos()) {
				this.controller.buscar();
//			}
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}


class BtnSeleccionarListener implements ActionListener {
	private AdminTicketCaja controller;
	
	public BtnSeleccionarListener(AdminTicketCaja controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
//			if (this.controller.validarDatos()) {
				//this.controller.getSelectedRow();
//			}
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}


