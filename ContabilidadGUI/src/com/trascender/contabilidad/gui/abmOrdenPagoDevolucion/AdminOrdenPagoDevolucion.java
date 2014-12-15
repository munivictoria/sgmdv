package com.trascender.contabilidad.gui.abmOrdenPagoDevolucion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;

import com.trascender.contabilidad.gui.abmOrdenPagoDevolucion.abm.AgregarOrdenPagoDevolucion;
import com.trascender.contabilidad.gui.abmOrdenPagoDevolucion.abm.ConsultarOrdenPagoDevolucion;
import com.trascender.contabilidad.gui.abmOrdenPagoDevolucion.abm.EliminarOrdenPagoDevolucion;
import com.trascender.contabilidad.gui.abmOrdenPagoDevolucion.abm.ModificarOrdenPagoDevolucion;
import com.trascender.contabilidad.gui.abmPersonaFisica.AdminPersonaFisica;
import com.trascender.contabilidad.gui.abmPersonaJuridica.AdminPersonaJuridica;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.OrdenPagoDevolucion;
import com.trascender.contabilidad.recurso.persistent.DocumentoEgreso.Estado;
import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.framework.recurso.persistent.PersonaJuridica;
import com.trascender.gui.framework.abmStandard.AdminController;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.model.TDefaultComboBoxModel;
import com.trascender.gui.framework.util.Conversor;
import com.trascender.gui.framework.util.Validador;

public class AdminOrdenPagoDevolucion extends AdminController<OrdenPagoDevolucion> {

	private AdminOrdenPagoDevolucionView view;
	private OrdenPagoDevolucionBusquedaModel busquedaModel= new OrdenPagoDevolucionBusquedaModel();
	private OrdenPagoDevolucionTableModel tableModel = new OrdenPagoDevolucionTableModel();
	
	public AdminOrdenPagoDevolucion(JFrame owner) throws Exception {
		this.view = new AdminOrdenPagoDevolucionView(owner);
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		this.setModels();
		this.setListeners();
		this.habilitarBtnConsultar(true);
		
	}
	
	private void setModels() {
		this.getView().setBusquedaModel(this.busquedaModel);
		this.getView().setTableModel(this.tableModel);
		this.getView().getCbEstado().setModel(new TDefaultComboBoxModel(Estado.values()));
	}
	
	private void setListeners() {
		this.getView().getPnlBotonesSeleccionPersona().getBtnSeleccionarPersonaFisica().addActionListener(new BtnSeleccionarPersonaFisicaListener(this));
		this.getView().getPnlBotonesSeleccionPersona().getBtnSeleccionarPersonaJuridica().addActionListener(new BtnSeleccionarPersonaJuridicaListener(this));
		this.getView().getPnlBotonesSeleccionPersona().getBtnLimpiar().addActionListener(new BtnLimpiarPersonaListener(this));
		
		this.getView().getPnlBotonesBusqueda().getBtnBuscar().addActionListener(new BtnBuscarListener(this));
		this.getView().getPnlBotonesBusqueda().getBtnReiniciar().addActionListener(new BtnReiniciarListener(this));

		this.getView().getPnlPie().getBtnAgregar().addActionListener(new BtnAgregarListener(this));
		this.getView().getPnlPie().getBtnModificar().addActionListener(new BtnModificarListener(this));
		this.getView().getPnlPie().getBtnEliminar().addActionListener(new BtnEliminarListener(this));
		this.getView().getPnlPie().getBtnConsultar().addActionListener(new BtnConsultarListener(this));
	}
	
	@Override
	protected void actualizarBusquedaModel() {
		OrdenPagoDevolucionBusquedaModel locModel = this.getBusquedaModel();
		
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
		this.getView().getTfPersona().setText(Conversor.getVacioSiNull(this.getBusquedaModel().getPersona()));
		this.getView().getTfPersona().setCaretPosition(0);
		
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
	
	void seleccionarPersonaFisica() throws Exception { 
		AdminPersonaFisica adminPersonaFisica = new AdminPersonaFisica(this.getView());
		PersonaFisica locPersonaFisica= adminPersonaFisica.openSelect();
		
		if (locPersonaFisica != null) {
			PersonaFisica personaFisica= AppManager.getInstance().getAdminSystems().getSystemPersonaFisica().getPersonaFisicaPorId(locPersonaFisica.getIdPersona());
			this.getBusquedaModel().setPersona(personaFisica);
			this.actualizarBusquedaModel();
			this.actualizarBusquedaView();
		}
	}
	
	void seleccionarPersonaJuridica() throws Exception { 
		AdminPersonaJuridica adminPersonaJuridica = new AdminPersonaJuridica(this.getView());
		PersonaJuridica locPersonaJuridica= adminPersonaJuridica.openSelect();
		
		if (locPersonaJuridica != null) {
			PersonaJuridica personaJuridica = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemPersonaJuridica().getPersonaJuridicaPorId(locPersonaJuridica.getIdPersonaJuridica());
			this.getBusquedaModel().setPersona(personaJuridica);
			this.actualizarBusquedaModel();
			this.actualizarBusquedaView();
		}
	}
	
	void limpiarPersona() {
		this.getBusquedaModel().setPersona(null);
		this.actualizarBusquedaModel();
		this.actualizarBusquedaView();
	}
	
	void buscar() throws Exception {
		final AdminOrdenPagoDevolucion controller = this;
		Thread locThread = new Thread(new Runnable(){
			public void run() {
				try {
					controller.actualizarBusquedaModel();
					controller.getView().iniBusqueda();
					List<OrdenPagoDevolucion> locLista = controller.getBusquedaModel().buscar();
					try {
						if (locLista != null && !locLista.isEmpty()) {
							controller.getTableModel().setListaObjetos(locLista);
							//controller.habilitarBtnConsultar(true);
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
	protected OrdenPagoDevolucionBusquedaModel getBusquedaModel() {
		return this.busquedaModel;
	}

	@Override
	protected OrdenPagoDevolucionTableModel getTableModel() {
		return this.tableModel;
	}

	@Override
	protected AdminOrdenPagoDevolucionView getView() {
		return this.view;
	}
	
	@Override
	protected void setBtnReiniciarListener() {
		super.setBtnReiniciarListener();
		this.getView().getPnlPie().getBtnConsultar().setEnabled(false);
	}
	
	void openAgregarOrdenPagoDevolucion() throws Exception {
		AgregarOrdenPagoDevolucion agregarOrdenPagoDev = new AgregarOrdenPagoDevolucion(this.getView());
		agregarOrdenPagoDev.open();
		if (agregarOrdenPagoDev.isOperacionRealizada()) {
			this.getTableModel().addRow(agregarOrdenPagoDev.getAbmModel().getObjetoABM());
			this.buscar();
		}
	}

	void openModificarOrdenPagoDevolucion() throws Exception {
		OrdenPagoDevolucion locOrdenPagoDev = this.getSelectedRow();
		if(locOrdenPagoDev != null) {
			locOrdenPagoDev = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionEgresos().getOrdenPagoDevolucionByID(locOrdenPagoDev.getIdDocumentoEgreso());
			locOrdenPagoDev.getLineaOrdenPagoDev().toString();
			ModificarOrdenPagoDevolucion modificarOrdenPagoDev = new ModificarOrdenPagoDevolucion(this.getView());
			modificarOrdenPagoDev.getAbmModel().setObjetoABM(locOrdenPagoDev);
			modificarOrdenPagoDev.actualizarView();
			modificarOrdenPagoDev.open();
			this.buscar();
		}
	}
	
	void openEliminarOrdenPagoDevolucion() throws Exception {
		OrdenPagoDevolucion locOrdenPagoDev = this.getSelectedRow();
		if(locOrdenPagoDev != null) {
			locOrdenPagoDev = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionEgresos().getOrdenPagoDevolucionByID(locOrdenPagoDev.getIdDocumentoEgreso());
			EliminarOrdenPagoDevolucion eliminarOrdenPagoDev = new EliminarOrdenPagoDevolucion(this.getView());
			eliminarOrdenPagoDev.getAbmModel().setObjetoABM(locOrdenPagoDev);
//			eliminarOrdenPago.getAbmModel().setListaLineasOrdenPago(locOrdenPago.getLineasOrdenPago());
			eliminarOrdenPagoDev.actualizarView();
			eliminarOrdenPagoDev.open();
			this.buscar();
		}
	}
	
	void openConsultarOrdenPagoDevolucion() throws Exception {
//		boolean habilitarBtnConfirmar;
//		if ((this.getView().getCbEstado().getSelectedIndex() != -1)) {
//			if ((this.getView().getCbEstado().getSelectedItem().equals(OrdenPago.Estado.CANCELADA)) ||
//					(this.getView().getCbEstado().getSelectedItem().equals(OrdenPago.Estado.CONFIRMADA))) {
//				habilitarBtnConfirmar = false; 
//			} else {
//				habilitarBtnConfirmar = true;
//			}
//		}else {
//			habilitarBtnConfirmar = true;
//		}
//		
//		
		OrdenPagoDevolucion locOrdenPagoDev = this.getSelectedRow();
		if(locOrdenPagoDev != null) {
			locOrdenPagoDev = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionEgresos().getOrdenPagoDevolucionByID(locOrdenPagoDev.getIdDocumentoEgreso());
			ConsultarOrdenPagoDevolucion consultarOrdenPagoDev = new ConsultarOrdenPagoDevolucion(this.getView());
			consultarOrdenPagoDev.getAbmModel().setObjetoABM(locOrdenPagoDev);
			consultarOrdenPagoDev.getAbmModel().setListaLineasOrdenPagoDev(locOrdenPagoDev.getLineaOrdenPagoDev());
			consultarOrdenPagoDev.actualizarView();
			consultarOrdenPagoDev.open();
		}
//		//this.buscar();
	}

}

class BtnSeleccionarPersonaFisicaListener implements ActionListener {

	private AdminOrdenPagoDevolucion controller;
	
	public BtnSeleccionarPersonaFisicaListener(AdminOrdenPagoDevolucion controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.seleccionarPersonaFisica();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}

class BtnSeleccionarPersonaJuridicaListener implements ActionListener {

	private AdminOrdenPagoDevolucion controller;
	
	public BtnSeleccionarPersonaJuridicaListener(AdminOrdenPagoDevolucion controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.seleccionarPersonaJuridica();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}

class BtnLimpiarPersonaListener implements ActionListener {

	private AdminOrdenPagoDevolucion controller;
	
	public BtnLimpiarPersonaListener(AdminOrdenPagoDevolucion controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.limpiarPersona();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}

class BtnBuscarListener implements ActionListener {
	private AdminOrdenPagoDevolucion controller;
	
	public BtnBuscarListener(AdminOrdenPagoDevolucion controller) {
		this.controller = controller;
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.buscar();
			
//			if ((this.controller.getView().getCbEstado().getSelectedIndex() != -1)) {
//				if (this.controller.getView().getCbEstado().getSelectedItem().equals(OrdenPago.Estado.CREADA)) {
//					this.controller.getView().getPnlPie().getBtnConsultar().setEnabled(true);
//					this.controller.getView().getPnlPie().getBtnAgregar().setEnabled(true);
//					this.controller.getView().getPnlPie().getBtnModificar().setEnabled(true);
//					this.controller.getView().getPnlPie().getBtnEliminar().setEnabled(true);
//				}
//				if (this.controller.getView().getCbEstado().getSelectedItem().equals(OrdenPago.Estado.CONFIRMADA)) {
//					this.controller.getView().getPnlPie().getBtnConsultar().setEnabled(true);
//					this.controller.getView().getPnlPie().getBtnAgregar().setEnabled(true);
//					this.controller.getView().getPnlPie().getBtnModificar().setEnabled(false);
//					this.controller.getView().getPnlPie().getBtnEliminar().setEnabled(false);
//				}
//				if (this.controller.getView().getCbEstado().getSelectedItem().equals(OrdenPago.Estado.CANCELADA)) {
//					this.controller.getView().getPnlPie().getBtnConsultar().setEnabled(true);
//					this.controller.getView().getPnlPie().getBtnAgregar().setEnabled(true);
//					this.controller.getView().getPnlPie().getBtnModificar().setEnabled(false);
//					this.controller.getView().getPnlPie().getBtnEliminar().setEnabled(false);
//				}
//			}
			
		}
		catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnReiniciarListener implements ActionListener {
	private AdminOrdenPagoDevolucion controller;

	public BtnReiniciarListener(AdminOrdenPagoDevolucion controller) {
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
	
	private AdminOrdenPagoDevolucion controller;

	public BtnAgregarListener(AdminOrdenPagoDevolucion controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openAgregarOrdenPagoDevolucion();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnModificarListener implements ActionListener{
	
	private AdminOrdenPagoDevolucion controller;

	public BtnModificarListener(AdminOrdenPagoDevolucion controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openModificarOrdenPagoDevolucion();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnEliminarListener implements ActionListener{
	
	private AdminOrdenPagoDevolucion controller;

	public BtnEliminarListener(AdminOrdenPagoDevolucion controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openEliminarOrdenPagoDevolucion();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnConsultarListener implements ActionListener{
	
	private AdminOrdenPagoDevolucion controller;

	public BtnConsultarListener(AdminOrdenPagoDevolucion controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openConsultarOrdenPagoDevolucion();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}
