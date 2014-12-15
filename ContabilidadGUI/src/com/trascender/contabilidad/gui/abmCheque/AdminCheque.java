package com.trascender.contabilidad.gui.abmCheque;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.trascender.contabilidad.gui.abmCheque.abm.AgregarCheque;
import com.trascender.contabilidad.gui.abmCheque.abm.ConsultarCheque;
import com.trascender.contabilidad.gui.abmCheque.abm.EliminarCheque;
import com.trascender.contabilidad.gui.abmCheque.abm.ModificarCheque;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.Cheque;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.gui.framework.abmStandard.AdminController;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Conversor;
import com.trascender.gui.framework.util.Validador;

public class AdminCheque extends AdminController<Cheque> {

	private AdminChequeView view;
	private ChequeBusquedaModel busquedaModel = new ChequeBusquedaModel();
	private ChequeTableModel TableModel = new ChequeTableModel();
	
	public AdminCheque(JFrame owner) throws Exception {
		this.view = new AdminChequeView(owner);
		this.init();
	}
	
	public AdminCheque(JDialog owner) throws Exception {
		this.view = new AdminChequeView(owner);
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		this.setModels();
		this.setListeners();
		this.setEnabled();
	}
	
	private void setEnabled() {
		this.getView().getPnlPie().getBtnConsultar().setVisible(true);
	}

	private void setModels() {
		this.getView().setBusquedaModel(this.getBusquedaModel());
		this.getView().setTableModel(this.getTableModel());
	}
	
	private void setListeners() {
		this.getView().getPnlBotonesBusqueda().getBtnBuscar().addActionListener(new BtnBuscarListener(this));
		
		this.getView().getPnlPie().getBtnAgregar().addActionListener(new BtnAgregarListener(this));
		this.getView().getPnlPie().getBtnModificar().addActionListener(new BtnModificarListener(this));
		this.getView().getPnlPie().getBtnEliminar().addActionListener(new BtnEliminarListener(this));
		this.getView().getPnlPie().getBtnConsultar().addActionListener(new BtnConsultarListener(this));
	}

	@Override
	protected void actualizarBusquedaModel() {
		ChequeBusquedaModel locModel = this.getView().getBusquedaModel();
		locModel.setNumeroCheque(Conversor.getNullSiVacio(this.getView().getTfNumeroCheque().getText()));
		
		locModel.setFechaEmisionDesde(Conversor.getDate(this.getView().getTfFechaEmisionDesde().getText()));
		locModel.setFechaEmisionHasta(Conversor.getDate(this.getView().getTfFechaEmisionHasta().getText()));
		locModel.setFechaPagoDesde(Conversor.getDate(this.getView().getTfFechaPagoDesde().getText()));
		locModel.setFechaPagoHasta(Conversor.getDate(this.getView().getTfFechaPagoHasta().getText()));
		locModel.setImporteDesde(Conversor.getDouble(this.getView().getTfImporteDesde().getValue()));
		locModel.setImporteHasta(Conversor.getDouble(this.getView().getTfImporteHasta().getValue()));
		
		locModel.fireActualizarDatos();		
	}

	@Override
	protected void actualizarBusquedaView() {
		this.getView().getTfNumeroCheque().setText(Conversor.getNullSiVacio(this.getBusquedaModel().getNumeroCheque()));
		this.getView().getTfFechaEmisionDesde().setValue(Conversor.getString(this.getBusquedaModel().getFechaEmisionDesde()));
		this.getView().getTfFechaEmisionHasta().setValue(Conversor.getString(this.getBusquedaModel().getFechaEmisionHasta()));
		this.getView().getTfFechaPagoDesde().setValue(Conversor.getString(this.getBusquedaModel().getFechaPagoDesde()));
		this.getView().getTfFechaPagoHasta().setValue(Conversor.getString(this.getBusquedaModel().getFechaPagoHasta()));
		this.getView().getTfImporteDesde().setValue(this.getBusquedaModel().getImporteDesde());
		this.getView().getTfImporteHasta().setValue(this.getBusquedaModel().getImporteHasta());
	}
	
	@Override
	public boolean validarDatos() {
		boolean validacionOK = true;
		
		List<String> attFecha = new ArrayList<String>();
		List<JLabel> lblFecha = new ArrayList<JLabel>();

		List<Object> attEnteros = new ArrayList<Object>();
		List<JLabel> lblEnteros = new ArrayList<JLabel>();
		
		if (this.getView().getTfFechaEmisionDesde().getValue() != null) {
			attFecha.add(this.getView().getTfFechaEmisionDesde().getText());
			lblFecha.add(this.getView().getLblFechaEmision());
		}
		
		if (this.getView().getTfFechaEmisionHasta().getValue() != null) {
			attFecha.add(this.getView().getTfFechaEmisionHasta().getText());
			lblFecha.add(this.getView().getLblFechaEmision());
		}
		
		if (this.getView().getTfFechaPagoDesde().getValue() != null) {
			attFecha.add(this.getView().getTfFechaPagoDesde().getText());
			lblFecha.add(this.getView().getLblFechaPago());
		}
		
		if (this.getView().getTfFechaPagoHasta().getValue() != null) {
			attFecha.add(this.getView().getTfFechaPagoHasta().getText());
			lblFecha.add(this.getView().getLblFechaPago());
		}
		
		if (this.getView().getTfNumeroCheque().getText()!= null && !this.getView().getTfNumeroCheque().getText().equals("")) {
			attEnteros.add(this.getView().getTfNumeroCheque().getText());
			lblEnteros.add(this.getView().getLblNumeroCheque());
		}
		
		
		List<String> listaErrores = new ArrayList<String>();
		
		try {
			listaErrores.addAll(Validador.validarFechas(attFecha, lblFecha));
			listaErrores.addAll(Validador.validarEnteros(attEnteros, lblEnteros));
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

	@Override
	protected ChequeBusquedaModel getBusquedaModel() {
		return this.busquedaModel;
	}

	@Override
	protected ChequeTableModel getTableModel() {
		return this.TableModel;
	}

	@Override
	protected AdminChequeView getView() {
		return this.view;
	}
	
	void buscar() throws Exception {
		final AdminCheque controller = this;
		Thread locThread = new Thread(new Runnable(){
			public void run() {
				try {
					controller.actualizarBusquedaModel();
					controller.getView().iniBusqueda();
					List<Cheque> locLista = controller.getBusquedaModel().buscar();
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
	
	protected void openAgregarCheque() throws Exception {
		AgregarCheque agregarCheque= new AgregarCheque(this.getView());
		agregarCheque.open();
		if (agregarCheque.isOperacionRealizada()) {
			this.getTableModel().addRow(agregarCheque.getAbmModel().getObjetoABM());
		}
	}
	
	protected void openModificarCheque() throws Exception {
		Cheque locCheque= this.getSelectedRow();
		if (locCheque != null) {
			locCheque = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionEgresos().getChequeByID(locCheque.getIdMovimientoBancario());
			ModificarCheque locModificarCheque = new ModificarCheque(this.getView());
			locModificarCheque.getAbmModel().setObjetoABM(locCheque);
			locModificarCheque.actualizarView(); 
			locModificarCheque.open();
			if (locModificarCheque.isOperacionRealizada()) {
				this.getTableModel().updateRow(locCheque);
			}
		}
	}
	
	protected void openEliminarCheque() throws Exception {
		Cheque locCheque = this.getSelectedRow();
		if (locCheque != null) {
			locCheque = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionEgresos().getChequeByID(locCheque.getIdMovimientoBancario());
			EliminarCheque eliminarCheque= new EliminarCheque(this.getView());
			eliminarCheque.getAbmModel().setObjetoABM(locCheque);
			eliminarCheque.actualizarView(); 
			eliminarCheque.open();
			if (eliminarCheque.isOperacionRealizada()) {
				this.getTableModel().deleteRow(locCheque);
			}
		}
	}

	public void openConsultarCheque() throws RemoteException, TrascenderException {
		Cheque locCheque = this.getSelectedRow();
		if (locCheque != null) {
			locCheque = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionEgresos().getChequeByID(locCheque.getIdMovimientoBancario());
			ConsultarCheque consultarCheque= new ConsultarCheque(this.getView());
			consultarCheque.getAbmModel().setObjetoABM(locCheque);
			consultarCheque.actualizarView(); 
			consultarCheque.open();
			if (consultarCheque.isOperacionRealizada()) {
				this.getTableModel().deleteRow(locCheque);
			}
		}		
	}	
}

class BtnBuscarListener implements ActionListener {
	private AdminCheque controller;
	public BtnBuscarListener(AdminCheque controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.buscar();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnAgregarListener implements ActionListener {

	private AdminCheque controller;
	
	public BtnAgregarListener(AdminCheque controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openAgregarCheque();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}

class BtnModificarListener implements ActionListener {

	private AdminCheque controller;
	
	public BtnModificarListener(AdminCheque controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openModificarCheque();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnEliminarListener implements ActionListener {

	private AdminCheque controller;
	
	public BtnEliminarListener(AdminCheque controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openEliminarCheque();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnConsultarListener implements ActionListener {

	private AdminCheque controller;
	
	public BtnConsultarListener(AdminCheque controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openConsultarCheque();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}