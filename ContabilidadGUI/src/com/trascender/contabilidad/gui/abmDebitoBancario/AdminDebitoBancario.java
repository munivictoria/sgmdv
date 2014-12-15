package com.trascender.contabilidad.gui.abmDebitoBancario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.trascender.contabilidad.gui.abmDebitoBancario.abm.AgregarDebitoBancario;
import com.trascender.contabilidad.gui.abmDebitoBancario.abm.EliminarDebitoBancario;
import com.trascender.contabilidad.gui.abmDebitoBancario.abm.ModificarDebitoBancario;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.Debito;
import com.trascender.gui.framework.abmStandard.AdminController;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Conversor;
import com.trascender.gui.framework.util.Validador;


public class AdminDebitoBancario extends AdminController<Debito> {

	private AdminDebitoBancarioView view;
	private DebitoBancarioTableModel TableModel = new DebitoBancarioTableModel();
	private DebitoBancarioBusquedaModel busquedaModel = new DebitoBancarioBusquedaModel();
	
	public AdminDebitoBancario(JFrame owner) throws Exception{
		this.view = new AdminDebitoBancarioView(owner);
		this.init();
	}
	
	public AdminDebitoBancario(JDialog owner) throws Exception{
		this.view = new AdminDebitoBancarioView(owner);
		this.init();
	}
	
	
	protected void init(){
		super.init();
		this.setModels();
		this.setListeners();
	}
	
	private void setModels(){
		this.getView().setBusquedaModel(this.getBusquedaModel());
		this.getView().setTableModel(this.getTableModel());
	}
	

	private void setListeners(){
		this.getView().getPnlBotonesBusqueda().getBtnBuscar().addActionListener(new BtnBuscarListener(this));
		
		this.getView().getPnlPie().getBtnAgregar().addActionListener(new BtnAgregarListener(this));
		this.getView().getPnlPie().getBtnModificar().addActionListener(new BtnModificarListener(this));
		this.getView().getPnlPie().getBtnEliminar().addActionListener(new BtnEliminarListener(this));
	}
	
	@Override
	protected void actualizarBusquedaModel() {
		DebitoBancarioBusquedaModel locModel = this.getView().getBusquedaModel();
		locModel.setFechaEmisionDesde(Conversor.getDate(this.getView().getTfFechaEmisionDesde().getText()));
		locModel.setFechaEmisionHasta(Conversor.getDate(this.getView().getTfFechaEmisionHasta().getText()));
		if (this.getView().getTfImporteDesde().getText().equals("0,00") || this.getView().getTfImporteDesde().getText().equals("") ) {
			locModel.setImporteDesde(null);
		}else{
			locModel.setImporteDesde(Conversor.getDouble(this.getView().getTfImporteDesde().getValue()));
		}
		if (this.getView().getTfImporteHasta().getText().equals("0,00") || this.getView().getTfImporteHasta().getText().equals("0,00")) {
			locModel.setImporteHasta(null);
		}else {
			locModel.setImporteHasta(Conversor.getDouble(this.getView().getTfImporteHasta().getValue()));
		}
		
		locModel.fireActualizarDatos();		
	}

	@Override
	protected void actualizarBusquedaView() {
		this.getView().getTfFechaEmisionDesde().setValue(Conversor.getString(this.getBusquedaModel().getFechaEmisionDesde()));
		this.getView().getTfFechaEmisionHasta().setValue(Conversor.getString(this.getBusquedaModel().getFechaEmisionHasta()));
		this.getView().getTfImporteDesde().setValue(this.getBusquedaModel().getImporteDesde());
		this.getView().getTfImporteHasta().setValue(this.getBusquedaModel().getImporteHasta() );
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
	

	void buscar() throws Exception {
		final AdminDebitoBancario controller = this;
		Thread locThread = new Thread(new Runnable(){
			public void run() {
				try {
					controller.actualizarBusquedaModel();
					controller.getView().iniBusqueda();
					List<Debito> locLista = controller.getBusquedaModel().buscar();
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

	protected void openAgregarDebitoBancario() throws Exception {
		AgregarDebitoBancario agregarDebitoBancario = new AgregarDebitoBancario(this.getView());
		agregarDebitoBancario.open();
		if (agregarDebitoBancario.isOperacionRealizada()) {
			this.getTableModel().addRow(agregarDebitoBancario.getAbmModel().getObjetoABM());
		}
	}
	
	protected void openModificarDebitoBancario() throws Exception {
		Debito locDebitoBancario = this.getSelectedRow();
		if (locDebitoBancario != null) {
			locDebitoBancario = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionEgresos().getDebitoByID(locDebitoBancario.getIdMovimientoBancario());
			ModificarDebitoBancario locModificarDebitoBancario = new ModificarDebitoBancario(this.getView());
			locModificarDebitoBancario.getAbmModel().setObjetoABM(locDebitoBancario);
			locModificarDebitoBancario.actualizarView(); 
			locModificarDebitoBancario.open();
			if (locModificarDebitoBancario.isOperacionRealizada()) {
				this.getTableModel().updateRow(locDebitoBancario);
			}
		}
	}
	
	protected void openEliminarDebitoBancario() throws Exception {
		Debito locDebitoBancario = this.getSelectedRow();
		if (locDebitoBancario != null) {
			locDebitoBancario = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionEgresos().getDebitoByID(locDebitoBancario.getIdMovimientoBancario());
			EliminarDebitoBancario eliminarDebitoBancario = new EliminarDebitoBancario(this.getView());
			eliminarDebitoBancario.getAbmModel().setObjetoABM(locDebitoBancario);
			eliminarDebitoBancario.actualizarView(); 
			eliminarDebitoBancario.open();
			if (eliminarDebitoBancario.isOperacionRealizada()) {
				this.getTableModel().deleteRow(locDebitoBancario);
			}
		}
	}
	

	@Override
	protected DebitoBancarioBusquedaModel getBusquedaModel() {
		return this.busquedaModel;
	}

	@Override
	protected DebitoBancarioTableModel getTableModel() {
		return this.TableModel;
	}

	@Override
	protected AdminDebitoBancarioView getView() {
		return this.view;
	}
}

class BtnBuscarListener implements ActionListener {
	private AdminDebitoBancario controller;
	public BtnBuscarListener(AdminDebitoBancario controller) {
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

	private AdminDebitoBancario controller;
	
	public BtnAgregarListener(AdminDebitoBancario controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openAgregarDebitoBancario();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnModificarListener implements ActionListener {

	private AdminDebitoBancario controller;
	
	public BtnModificarListener(AdminDebitoBancario controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openModificarDebitoBancario();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnEliminarListener implements ActionListener {

	private AdminDebitoBancario controller;
	
	public BtnEliminarListener(AdminDebitoBancario controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openEliminarDebitoBancario();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}
