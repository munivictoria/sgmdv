package com.trascender.contabilidad.gui.abmOrdenPagoDevolucion.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

import com.trascender.contabilidad.gui.abmCheque.AdminCheque;
import com.trascender.contabilidad.gui.abmDebitoBancario.AdminDebitoBancario;
import com.trascender.contabilidad.gui.abmOrdenPago.ChequeOrdenPagoTableModel;
import com.trascender.contabilidad.gui.abmOrdenPago.DebitoTableModel;
import com.trascender.contabilidad.gui.abmOrdenPagoDevolucion.LineaOrdenPagoDevolucionTableModel;
import com.trascender.contabilidad.gui.abmOrdenPagoDevolucion.OrdenPagoDevolucionABMModel;
import com.trascender.contabilidad.gui.abmPersonaFisica.AdminPersonaFisica;
import com.trascender.contabilidad.gui.abmPersonaJuridica.AdminPersonaJuridica;
import com.trascender.contabilidad.gui.abmTicketCaja.AdminTicketCaja;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.Cheque;
import com.trascender.contabilidad.recurso.persistent.Debito;
import com.trascender.contabilidad.recurso.persistent.LineaOrdenPagoDevolucion;
import com.trascender.contabilidad.recurso.persistent.MovimientoBancario;
import com.trascender.contabilidad.recurso.persistent.OrdenPagoDevolucion;
import com.trascender.contabilidad.recurso.persistent.TicketCaja;
import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.framework.recurso.persistent.PersonaJuridica;
import com.trascender.gui.framework.abmStandard.ABMController;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Conversor;
import com.trascender.gui.framework.util.Validador;

public abstract class ABMOrdenPagoDevolucion extends ABMController<OrdenPagoDevolucion> {

	public abstract ABMOrdenPagoDevolucionView getView();
	public abstract OrdenPagoDevolucionABMModel getAbmModel();
	public abstract ChequeOrdenPagoTableModel getChequeTableModel();
	public abstract DebitoTableModel getDebitoTableModel();
	public abstract LineaOrdenPagoDevolucionTableModel getLineaOrdenPagoDevTableModel();

	@Override
	protected void init() {
		super.init();
		this.setModels();
		this.setListeners();
	}
	
	private void setModels() {
		this.getView().setAbmModel(this.getAbmModel());
		this.getView().getPnlTablaLineaOrdenPagoDev().getTblDatos().setModel(this.getLineaOrdenPagoDevTableModel());
		this.getView().getPnlTablaCheque().getTblDatos().setModel(this.getChequeTableModel());
		this.getView().getPnlTablaDebito().getTblDatos().setModel(this.getDebitoTableModel());
	}
	
	private void setListeners() {
		this.getView().getPnlBtnSeleccionPersona().getBtnSeleccionarPersonaFisica().addActionListener(new BtnSeleccionarPersonaFisicaListener(this));
		this.getView().getPnlBtnSeleccionPersona().getBtnSeleccionarPersonaJuridica().addActionListener(new BtnSeleccionarPersonaJuridicaListener(this));
		this.getView().getPnlBtnSeleccionPersona().getBtnLimpiar().addActionListener(new BtnLimpiarPersonaListener(this));
//		
		this.getView().getPnlBotonesTblLineaOrdenPagoDev().getBtnAgregar().addActionListener(new BtnSeleccionarLineaOPDListener(this));
		this.getView().getPnlBotonesTblLineaOrdenPagoDev().getBtnEliminar().addActionListener(new BtnQuitarLineaOPDListener(this));
		
		this.getView().getPnlBotonesTblCheque().getBtnAgregar().addActionListener(new BtnSeleccionarChequeListener(this));
		this.getView().getPnlBotonesTblCheque().getBtnEliminar().addActionListener(new BtnQuitarChequeListener(this));
		this.getView().getPnlBotonesTblCheque().getBtnQuitarTodos().addActionListener(new BtnQuitarTodosChequesListener(this));
			
		this.getView().getPnlBotonesTblDebito().getBtnAgregar().addActionListener(new BtnSeleccionarDebitoBancarioListener(this));
		this.getView().getPnlBotonesTblDebito().getBtnEliminar().addActionListener(new BtnQuitarDebitoListener(this));
		this.getView().getPnlBotonesTblDebito().getBtnQuitarTodos().addActionListener(new BtnQuitarTodosDebitosListener(this));
	}
	
	@Override
	protected void actualizarABMModel() {
		this.getAbmModel().setFechaEmision(Conversor.getDate(this.getView().getTfFechaEmision().getText()));
		this.getAbmModel().setFechaPago(Conversor.getDate(this.getView().getTfFechaPago().getText()));
		this.getAbmModel().fireActualizarDatos();
	}
	
	@Override
	public void actualizarView() {
		this.getView().getTfFechaEmision().setValue(Conversor.getString(this.getAbmModel().getFechaEmision()));
		this.getView().getTfFechaPago().setValue(Conversor.getString(this.getAbmModel().getFechaPago()));
		this.getView().getTfPersona().setText(Conversor.getVacioSiNull(this.getAbmModel().getPersona()));
		
		//Actualización de la tabla Linea orden pago devolucion
		if (this.getAbmModel().getListaLineasOrdenPagoDev() != null && this.getLineaOrdenPagoDevTableModel().getListaObjetos() != null) {
			
			List<LineaOrdenPagoDevolucion> locList = new ArrayList<LineaOrdenPagoDevolucion>(this.getAbmModel().getListaLineasOrdenPagoDev());
			this.getLineaOrdenPagoDevTableModel().clearTable();
			this.getLineaOrdenPagoDevTableModel().addRows(locList);
			
			try {
				//this.setTableCellEditor();
			}
			catch (Exception ex) {
				ex.printStackTrace();
				AppManager.getInstance().showErrorMsg(this.getView(), ex.getMessage());
			}
		}
		
		try {
			//Actualización de la tabla Movimiento Bancario
			if (this.getAbmModel().getMovimientoBancario() != null && this.getChequeTableModel()!= null) {
				List<Cheque> locListCheque = new ArrayList<Cheque>();
				List<Debito> locListDebito = new ArrayList<Debito>();
				
				this.getChequeTableModel().clearTable();
				this.getDebitoTableModel().clearTable();
				for (MovimientoBancario locMovimientoBancario : this.getAbmModel().getMovimientoBancario()) {
					if (locMovimientoBancario instanceof Cheque) {
						locListCheque.add((Cheque)locMovimientoBancario);
					//	this.getChequeTableModel().clearTable();
					} else {
						locListDebito.add((Debito)locMovimientoBancario);
				//		this.getDebitoTableModel().clearTable();
					}
				}
				this.getChequeTableModel().addRows(locListCheque);
				this.getDebitoTableModel().addRows(locListDebito);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean validarDatos() {
		boolean validacionOK = true;
		
		List<Object> attNulos = new ArrayList<Object>();
		List<JLabel> lblNulos = new ArrayList<JLabel>();
		
		List<Integer> attMin = new ArrayList<Integer>();
		List<JLabel> lblMin = new ArrayList<JLabel>();
		List<Integer> cantMin = new ArrayList<Integer>();
		
		List<String> attFechas = new ArrayList<String>();
		List<JLabel> lblFechas = new ArrayList<JLabel>();
		
		attNulos.add(this.getView().getTfFechaEmision().getText());
		lblNulos.add(this.getView().getLblFechaEmision());
		
		attNulos.add(this.getView().getTfFechaPago().getText());
		lblNulos.add(this.getView().getLblFechaPago());
		
		attNulos.add(this.getView().getTfPersona().getText());
		lblNulos.add(this.getView().getLblPersona());
		
		attMin.add(this.getView().getPnlTablaLineaOrdenPagoDev().getTblDatos().getRowCount());
		lblMin.add(new JLabel("La lista de Líneas de Ordenes de Pago por Devolución"));
		cantMin.add(1);
		
		attFechas.add(this.getView().getTfFechaEmision().getText());
		lblFechas.add(this.getView().getLblFechaEmision());
		
		attFechas.add(this.getView().getTfFechaPago().getText());
		lblFechas.add(this.getView().getLblFechaPago());
		
		List<String> listaErrores = new ArrayList<String>();
		try {
			listaErrores.addAll(Validador.validarNulos(attNulos, lblNulos));
			listaErrores.addAll(Validador.validarCantidadMinima(attMin, lblMin, cantMin));
			listaErrores.addAll(Validador.validarFechaNoMayorALaActual(this.getView().getTfFechaEmision().getText(), this.getView().getLblFechaEmision()));
			listaErrores.addAll(Validador.validarFechas(attFechas, lblFechas));
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
	
	void seleccionarPersonaFisica() throws Exception { 
		AdminPersonaFisica adminPersonaFisica = new AdminPersonaFisica(this.getView());
		PersonaFisica locPersonaFisica= adminPersonaFisica.openSelect();
		
		if (locPersonaFisica != null) {
			PersonaFisica personaFisica= AppManager.getInstance().getAdminSystems().getSystemPersonaFisica().getPersonaFisicaPorId(locPersonaFisica.getIdPersona());
			this.getAbmModel().setPersona(personaFisica);
			this.actualizarABMModel();
			this.actualizarView();
		}
	}
	
	void seleccionarPersonaJuridica() throws Exception { 
		AdminPersonaJuridica adminPersonaJuridica = new AdminPersonaJuridica(this.getView());
		PersonaJuridica locPersonaJuridica= adminPersonaJuridica.openSelect();
		
		if (locPersonaJuridica != null) {
			PersonaJuridica personaJuridica = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemPersonaJuridica().getPersonaJuridicaPorId(locPersonaJuridica.getIdPersonaJuridica());
			this.getAbmModel().setPersona(personaJuridica);
			this.actualizarABMModel();
			this.actualizarView();
		}
	}
	
	void limpiarPersona() {
		this.getAbmModel().setPersona(null);
		this.actualizarABMModel();
		this.actualizarView();
	}
	
	void seleccionarLineaOPD() throws Exception {
		AdminTicketCaja adminTicketCaja = new AdminTicketCaja(this.getView());
		TicketCaja locTicketCaja = adminTicketCaja.openSelect();
		if(locTicketCaja != null){
			locTicketCaja = ContabilidadGUI.getInstance().getAdminSystemsCaja().getSystemAdministracionIngresos().getTicketCajaPorNumero(locTicketCaja.getNumero());
			LineaOrdenPagoDevolucion locLOPD = new LineaOrdenPagoDevolucion();
			locLOPD.setTicketCaja(locTicketCaja);
			locLOPD.setImporte(locTicketCaja.getImporteTotal());
			this.getAbmModel().getListaLineasOrdenPagoDev().add(locLOPD);
			this.actualizarABMModel();
			this.actualizarView();
		}
	}
	
	void quitarLineaOPD() throws Exception{
		int locLineaOrdenPagoDev = this.getView().getPnlTablaLineaOrdenPagoDev().getTblDatos().getSelectedRow();
		
		if(locLineaOrdenPagoDev > -1){ 
			if (this.getLineaOrdenPagoDevTableModel()!= null) {
				LineaOrdenPagoDevolucion objetoSeleccionado = this.getLineaOrdenPagoDevTableModel().getRow(locLineaOrdenPagoDev);
				objetoSeleccionado = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionEgresos().getLineaOrdenPagoDevolucionByID(objetoSeleccionado.getIdLineaOrdenPagoDevolucion());
				this.getAbmModel().getListaLineasOrdenPagoDev().remove(objetoSeleccionado);
				this.actualizarABMModel();
				this.actualizarView();
			}
		}
	}
	
	void seleccionarCheque() throws Exception {
		AdminCheque adminCheque = new AdminCheque(this.getView());
		Cheque locCheque = adminCheque.openSelect();
		if(locCheque != null){
			locCheque = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionEgresos().getChequeByID(locCheque.getIdMovimientoBancario());
			this.getAbmModel().getMovimientoBancario().add(locCheque);
			this.actualizarABMModel();
			this.actualizarView();
		}
	}
	
	void seleccionarDebitoBancario() throws Exception {
		AdminDebitoBancario adminDebitoBancario = new AdminDebitoBancario(this.getView());
		Debito locDebitoBancario = adminDebitoBancario.openSelect();
		if (locDebitoBancario!= null) {
			locDebitoBancario = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionEgresos().getDebitoByID(locDebitoBancario.getIdMovimientoBancario());
				this.getAbmModel().getMovimientoBancario().add(locDebitoBancario);
				this.actualizarABMModel();
				this.actualizarView();
		}
	}
	
	void limpiarMovimientoBancario() throws Exception {
		this.getAbmModel().setMovimientoBancario(null);
		this.actualizarABMModel();
		this.actualizarView();
	}
	
	void quitarCheque() throws Exception{
		int locChequeSeleccionado = this.getView().getPnlTablaCheque().getTblDatos().getSelectedRow();
		
		if(locChequeSeleccionado > -1){ 
			if (this.getChequeTableModel()!= null) {
				Cheque objetoSeleccionado = this.getChequeTableModel().getRow(locChequeSeleccionado);
				objetoSeleccionado = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionEgresos().getChequeByID(objetoSeleccionado.getIdMovimientoBancario());
				this.getAbmModel().getMovimientoBancario().remove(objetoSeleccionado);
				this.actualizarABMModel();
				this.actualizarView();
			}
		}
	}
	
	void quitarDebito() throws Exception{
		int locDebitoSeleccionado = this.getView().getPnlTablaDebito().getTblDatos().getSelectedRow();
		
		if(locDebitoSeleccionado > -1){ 
			if (this.getDebitoTableModel()!= null) {
				Debito objetoSeleccionado = this.getDebitoTableModel().getRow(locDebitoSeleccionado);
				objetoSeleccionado = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionEgresos().getDebitoByID(objetoSeleccionado.getIdMovimientoBancario());
				this.getAbmModel().getMovimientoBancario().remove(objetoSeleccionado);
				this.actualizarABMModel();
				this.actualizarView();
			}
		}
	}
	
	void quitarTodosCheques() throws Exception{
		if (this.getAbmModel().getMovimientoBancario() != null && this.getAbmModel().getMovimientoBancario().size()>0) {
			List<MovimientoBancario> locListMovimientosBancarios = new ArrayList<MovimientoBancario>();
			for (MovimientoBancario locMovimientoBancario : this.getAbmModel().getMovimientoBancario()) {
				locListMovimientosBancarios.add(locMovimientoBancario);
			}
	
			for (MovimientoBancario locMovimientoBancario : locListMovimientosBancarios) {
				if (locMovimientoBancario instanceof Cheque){ 
					this.getAbmModel().getMovimientoBancario().remove(locMovimientoBancario);
				}
			}
			this.actualizarABMModel();
			this.actualizarView();
		}
	}
	
	void quitarTodosDebitos() throws Exception{
		if (this.getAbmModel().getMovimientoBancario() != null && this.getAbmModel().getMovimientoBancario().size()>0) {
			List<MovimientoBancario> locListMovimientosBancarios = new ArrayList<MovimientoBancario>();
			for (MovimientoBancario locMovimientoBancario : this.getAbmModel().getMovimientoBancario()) {
				locListMovimientosBancarios.add(locMovimientoBancario);
			}
	
			for (MovimientoBancario locMovimientoBancario : locListMovimientosBancarios) {
				if (locMovimientoBancario instanceof Debito){ 
					this.getAbmModel().getMovimientoBancario().remove(locMovimientoBancario);
				}
			}
			this.actualizarABMModel();
			this.actualizarView();
		}
	}
	
}

class BtnSeleccionarPersonaFisicaListener implements ActionListener {

	private ABMOrdenPagoDevolucion controller;
	
	public BtnSeleccionarPersonaFisicaListener(ABMOrdenPagoDevolucion controller) {
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

	private ABMOrdenPagoDevolucion controller;
	
	public BtnSeleccionarPersonaJuridicaListener(ABMOrdenPagoDevolucion controller) {
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

	private ABMOrdenPagoDevolucion controller;
	
	public BtnLimpiarPersonaListener(ABMOrdenPagoDevolucion controller) {
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

class BtnSeleccionarLineaOPDListener implements ActionListener {
	private ABMOrdenPagoDevolucion controller;
	
	public BtnSeleccionarLineaOPDListener(ABMOrdenPagoDevolucion controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.seleccionarLineaOPD();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnQuitarLineaOPDListener implements ActionListener {
	private ABMOrdenPagoDevolucion controller;
	
	public BtnQuitarLineaOPDListener(ABMOrdenPagoDevolucion controller) {
		this.controller = controller;
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.quitarLineaOPD();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnSeleccionarChequeListener implements ActionListener {
	private ABMOrdenPagoDevolucion controller;
	
	public BtnSeleccionarChequeListener(ABMOrdenPagoDevolucion controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.seleccionarCheque();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnSeleccionarDebitoBancarioListener implements ActionListener {
	private ABMOrdenPagoDevolucion controller;
	
	public BtnSeleccionarDebitoBancarioListener(ABMOrdenPagoDevolucion controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.seleccionarDebitoBancario();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnLimpiarMovimientoBancarioListener implements ActionListener {
	private ABMOrdenPagoDevolucion controller;
	
	public BtnLimpiarMovimientoBancarioListener(ABMOrdenPagoDevolucion controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.limpiarMovimientoBancario();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnQuitarChequeListener implements ActionListener {
	private ABMOrdenPagoDevolucion controller;
	
	public BtnQuitarChequeListener(ABMOrdenPagoDevolucion controller) {
		this.controller = controller;
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.quitarCheque();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnQuitarDebitoListener implements ActionListener {
	private ABMOrdenPagoDevolucion controller;
	
	public BtnQuitarDebitoListener(ABMOrdenPagoDevolucion controller) {
		this.controller = controller;
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.quitarDebito();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}


class BtnQuitarTodosChequesListener implements ActionListener {
	private ABMOrdenPagoDevolucion controller;
	
	public BtnQuitarTodosChequesListener(ABMOrdenPagoDevolucion controller) {
		this.controller = controller;
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.quitarTodosCheques();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnQuitarTodosDebitosListener implements ActionListener {
	private ABMOrdenPagoDevolucion controller;
	
	public BtnQuitarTodosDebitosListener(ABMOrdenPagoDevolucion controller) {
		this.controller = controller;
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.quitarTodosDebitos();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}


