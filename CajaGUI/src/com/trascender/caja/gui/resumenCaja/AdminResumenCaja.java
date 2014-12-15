package com.trascender.caja.gui.resumenCaja;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.caja.gui.abmCaja.AdminCaja;
import com.trascender.caja.gui.exception.CajaGUIException;
import com.trascender.caja.gui.impresion.ImpresoraSerieTicketCaja;
import com.trascender.caja.gui.impresion.ImpresoraSpoolTicketCaja;
import com.trascender.caja.gui.impresion.Imprimible;
import com.trascender.caja.gui.main.CajaGUI;
import com.trascender.contabilidad.recurso.persistent.Caja;
import com.trascender.contabilidad.recurso.persistent.CajaChica;
import com.trascender.contabilidad.recurso.persistent.DetalleTicketCaja;
import com.trascender.contabilidad.recurso.persistent.TicketCaja;
import com.trascender.contabilidad.recurso.persistent.TicketCancelado;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Permiso;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.framework.recurso.persistent.PersonaJuridica;
import com.trascender.gui.framework.abmStandard.AdminController;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.recursos.Messages;
import com.trascender.gui.framework.util.Conversor;

public class AdminResumenCaja extends AdminController<TicketCaja> {

	private AdminResumenCajaView view;
	private ResumenCajaTableModel tableModel = new ResumenCajaTableModel();
	private ResumenCajaBusquedaModel busquedaModel = new ResumenCajaBusquedaModel();
	
	public AdminResumenCaja(JDialog owner) throws Exception {
		this.view = new AdminResumenCajaView(owner);
		this.init();
	}
	
	public AdminResumenCaja(JFrame owner) throws Exception {
		this.view = new AdminResumenCajaView(owner);
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		this.setModels();
		this.setListeners();
		this.setPropiedadesBotones();
		this.setFecha();
		this.setPermisosCaja();
	}
	
	public void setPermisosCaja(){
		try{
			Permiso locPermiso = AppManager.getInstance().getPermiso(TicketCaja.serialVersionUID);
			if (locPermiso == null || !locPermiso.isAudith()) {
				this.getBusquedaModel().setCaja(CajaGUI.getInstance().getCaja());
				this.actualizarBusquedaView();
				this.getView().getPnlBotonesSeleccionCaja().setVisible(false);
				this.getView().getTfFecha().setEditable(false);
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	private void setPropiedadesBotones() {
		this.getView().getPnlPie().getBtnAgregar().setVisible(false);
		
		this.getView().getPnlPie().getBtnModificar().setText(Messages.getString("Application.btnReimprimir"));
		this.getView().getPnlPie().getBtnModificar().setMnemonic(Messages.getString("Application.btnReimprimirMnemonic").charAt(0));
		this.getView().getPnlPie().getBtnModificar().setToolTipText(Messages.getString("Application.btnReimprimirToolTip"));
		
		this.getView().getPnlPie().getBtnEliminar().setText(Messages.getString("Application.btnAnular"));
		this.getView().getPnlPie().getBtnEliminar().setMnemonic(Messages.getString("Application.btnAnularMnemonic").charAt(0));
		this.getView().getPnlPie().getBtnEliminar().setToolTipText(Messages.getString("Application.btnAnularToolTip"));
	}
	
	private void setFecha() {
		this.getView().getTfFecha().setText(Conversor.getString(Calendar.getInstance().getTime()));
	}

	private void setModels() {
		this.getView().setBusquedaModel(this.getBusquedaModel());
		this.getView().setTableModel(this.getTableModel());
	}
	
	private void setListeners() {
		
		this.getView().getPnlBotonesSeleccionCaja().getBtnSeleccionar().addActionListener(new BtnSeleccionarCajaListener(this));
		this.getView().getPnlBotonesSeleccionCaja().getBtnLimpiar().addActionListener(new BtnLimpiarCajaListener(this));
		
		this.getView().getPnlBotonesBusqueda().getBtnBuscar().addActionListener(new BtnBuscarListener(this));
		
		this.getView().getPnlPie().getBtnEliminar().addActionListener(new BtnAnularListener(this));
		this.getView().getPnlPie().getBtnModificar().addActionListener(new BtnrReimprimirTicketListener(this));
	}
	
	@Override
	protected void actualizarBusquedaModel() {
		ResumenCajaBusquedaModel locModel = this.getBusquedaModel();
		
		String stringFecha = this.getView().getTfFecha().getText();
		if (stringFecha != null && !stringFecha.trim().isEmpty()) {
			Date locFecha = Conversor.getDate(stringFecha);
			locModel.setFechaDesde(locFecha);
			locModel.setFechaHasta(locFecha);
		}
		
		locModel.fireActualizarDatos();
	}

	@Override
	protected void actualizarBusquedaView() {
		//this.getView().getTfFecha().setValue(this.getBusquedaModel().getFechaDesde());
		this.getView().getTfCaja().setText(Conversor.getVacioSiNull(this.getBusquedaModel().getCaja()));
	}

	@Override
	protected ResumenCajaBusquedaModel getBusquedaModel() {
		return busquedaModel;
	}

	@Override
	protected ResumenCajaTableModel getTableModel() {
		return tableModel;
	}

	@Override
	protected AdminResumenCajaView getView() {
		return view;
	}

	void buscar() {
		final AdminResumenCaja controller = this;
		Thread locThread = new Thread(new Runnable(){
			public void run() {
				try {
					controller.actualizarBusquedaModel();
					controller.getView().iniBusqueda();
					List<TicketCaja> locLista = controller.getBusquedaModel().buscar();
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
	
	void seleccionarCaja() throws Exception {
		AdminCaja adminCaja = new AdminCaja(this.getView());
		this.actualizarBusquedaModel();
		Caja locCaja = adminCaja.openSelect();
		if (locCaja != null) {
			this.busquedaModel.setCaja(locCaja);
			this.actualizarBusquedaView();
		}
	}
	
	void limpiarCaja() {
		this.getBusquedaModel().setCaja(null);
		this.actualizarBusquedaModel();
		this.actualizarBusquedaView();
	}
	//mines: ANULAR
	protected void anularTicket() throws Exception {
		TicketCaja locTicketCaja = this.getSelectedRow();
		if (locTicketCaja != null) {
			Boolean locMensaje = AppManager.getInstance().showConfirmMsg(this.getView(), "¿Desea anular el Ticket seleccionado?");
			if (locMensaje) {
				locTicketCaja = CajaGUI.getInstance().getAdminSystemsCaja().getSystemAdministracionIngresos().findTicketCajaByID(locTicketCaja.getIdTicketCaja());
				
				CajaGUI.getInstance().getAdminSystemsCaja().getSystemAdministracionIngresos().cancelarTicketCaja(locTicketCaja);
				
				//Imprime el ticket con la leyenda de ANULADO
				Persona locPersona = null;
				for (DetalleTicketCaja locDetalle : locTicketCaja.getDetalles()) {
					locPersona = locDetalle.getDeuda().getPersona();
				}
				try{
					Imprimible locImprimible = Imprimible.getInstance(locTicketCaja, locPersona);
					if (locImprimible.isValido()){
						locImprimible.imprimirAnulacion();
//						locImprimible.imprimirAnulacion();
					}
				} catch(Exception ex){
					ex.printStackTrace();
					AppManager.getInstance().showErrorMsg(this.getView(), "Ha ocurrido un error al intentar imprimir. Comuníquese con el administrador.");
				}
				this.buscar();
			}
		}
	}

	public void ReimprimirTicket() throws RemoteException, TrascenderException  {
		TicketCaja locTicketCaja = this.getSelectedRow();
		if(locTicketCaja != null){
			locTicketCaja = CajaGUI.getInstance().getAdminSystemsCaja().getSystemAdministracionIngresos().findTicketCajaByID(locTicketCaja.getIdTicketCaja());
			if (locTicketCaja.getEstado().equals(TicketCaja.Estado.ACTIVO)) {
				
				Persona locPersona = null;
				
				for (DetalleTicketCaja locDetalle : locTicketCaja.getDetalles()) {
					locPersona = locDetalle.getDeuda().getPersona();
				}
				try{
					Imprimible locImprimible = Imprimible.getInstance(locTicketCaja, locPersona);
					if (locImprimible.isValido()){
						locImprimible.reimprimir();
//						locImprimible.imprimir();
					}
				}
				catch(Exception e){
					e.printStackTrace();
					AppManager.getInstance().showErrorMsg(this.getView(), "Ha ocurrido un error al intentar imprimir. Comuníquese con el administrador.");
				}
			} else { // Reeimpresion ticket anulado
				Persona locPersona = null;
				TicketCancelado tC = CajaGUI.getInstance().getAdminSystemsCaja().getSystemAdministracionIngresos().getTicketCanceladoPorTicketCaja(locTicketCaja);
				locPersona = CajaGUI.getInstance().getAdminSystemsCaja().getSystemAdministracionIngresos().getPersonaPorDeuda(tC.getIdDeuda());
				for (DetalleTicketCaja unDetalle : locTicketCaja.getDetalles()) {
					unDetalle.setDeuda(CajaGUI.getInstance().getAdminSystemsCaja().getSystemAdministracionIngresos().getDeudaByID(tC.getIdDeuda()));
				}
				try{
					Imprimible locImprimible = Imprimible.getInstance(locTicketCaja, locPersona);
					if (locImprimible.isValido()){
						locImprimible.imprimirAnulacion();
//						locImprimible.imprimirAnulacion();
					}
				} catch(Exception ex){
						ex.printStackTrace();
						AppManager.getInstance().showErrorMsg(this.getView(), "Ha ocurrido un error al intentar imprimir. Comuníquese con el administrador.");
				}
			}
		}
	}
}

class BtnBuscarListener implements ActionListener {
	private AdminResumenCaja controller;
	public BtnBuscarListener(AdminResumenCaja controller) {
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

class BtnSeleccionarCajaListener implements ActionListener {
	private AdminResumenCaja controller;
	public BtnSeleccionarCajaListener(AdminResumenCaja controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.seleccionarCaja();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnLimpiarCajaListener implements ActionListener {
	private AdminResumenCaja controller;
	public BtnLimpiarCajaListener(AdminResumenCaja controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.limpiarCaja();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}
//	ANULAR:
class BtnAnularListener implements ActionListener {
	private AdminResumenCaja controller;
	public BtnAnularListener(AdminResumenCaja controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.anularTicket();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnrReimprimirTicketListener implements ActionListener {
	private AdminResumenCaja controller;
	
	public BtnrReimprimirTicketListener(AdminResumenCaja controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.ReimprimirTicket();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}