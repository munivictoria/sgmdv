package com.trascender.contabilidad.gui.abmLibroBanco.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

import com.trascender.contabilidad.gui.abmCuentaBancaria.AdminCuentaBancaria;
import com.trascender.contabilidad.gui.abmLibroBanco.LibroBancoABMModel;
import com.trascender.contabilidad.gui.abmLibroBanco.LineasLibroBancoTableModel;
import com.trascender.contabilidad.gui.abmLineaLibroBanco.abm.AgregarLineaLibroBanco;
import com.trascender.contabilidad.gui.abmLineaLibroBanco.abm.ModificarLineaLibroBanco;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.CuentaBancaria;
import com.trascender.contabilidad.recurso.persistent.LibroBanco;
import com.trascender.contabilidad.recurso.persistent.LineaLibroBanco;
import com.trascender.gui.framework.abmStandard.ABMController;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Conversor;
import com.trascender.gui.framework.util.Validador;

public abstract class ABMLibroBanco extends ABMController<LibroBanco> {

	public abstract ABMLibroBancoView getView();
	public abstract LibroBancoABMModel getAbmModel();
	public abstract LineasLibroBancoTableModel getAbmTableModel();
	
	
	@Override
	protected void init() {
		super.init();
		this.setModels();
		this.setListeners();
		this.enableBotonesTabla(false);
	}
	
	private void setModels() {
		this.getView().setAbmModel(this.getAbmModel());
		this.getView().setAbmTableModel(this.getAbmTableModel());
	}
	
	private void setListeners() {
		this.getView().getPnlBotonesSeleccion().getBtnSeleccionar().addActionListener(new BtnSeleccionarCuentaBancariaListener(this));
		this.getView().getPnlBotonesSeleccion().getBtnLimpiar().addActionListener(new BtnLimpiarCuentaBancariaListener(this));
		
		this.getView().getBtnGenerarLibroBanco().addActionListener(new BtnGenerarLibroBancoListener(this));
		
		this.getView().getPnlBotonesTabla().getBtnAgregar().addActionListener(new BtnAgregarLineaLibroBancoListener(this));
		this.getView().getPnlBotonesTabla().getBtnModificar().addActionListener(new BtnModificarLineaLibroBancoListener(this));
		this.getView().getPnlBotonesTabla().getBtnEliminar().addActionListener(new BtnQuitarLineaLibroBancoListener(this));
		this.getView().getPnlBotonesTabla().getBtnQuitarTodos().addActionListener(new BtnQuitarTodosLineaLibroBancoListener(this));
	}
	
	@Override
	public void actualizarABMModel() {
		LibroBancoABMModel locModel = this.getAbmModel();
		
		locModel.setNombre(Conversor.getNullSiVacio(this.getView().getTfNombre().getText()));
		locModel.setFechaGeneracion(Conversor.getDate(this.getView().getTfFechaGeneracion().getText()));
		locModel.getObjetoABM().getLineasLibroBanco().clear();
		locModel.getObjetoABM().getLineasLibroBanco().addAll(this.getAbmTableModel().getListaObjetos());
		
		locModel.fireActualizarDatos();
	}

	@Override
	public void actualizarView() {
		this.getView().getTfNombre().setText(Conversor.getVacioSiNull(this.getAbmModel().getNombre()));
		this.getView().getTfCuentaBancaria().setText(Conversor.getVacioSiNull(this.getAbmModel().getCuentaBancaria()));
		this.getView().getTfFechaGeneracion().setValue(Conversor.getString(this.getAbmModel().getFechaGeneracion()));
		this.getAbmTableModel().clearTable();
		this.getAbmTableModel().setListaObjetos(this.getAbmModel().getObjetoABM().getLineasLibroBanco());
	}
	
	public LineaLibroBanco getSelectedRow() {
		
		LineaLibroBanco objetoSeleccionado = null;
		if (this.getView() != null && this.getView().getPnlTabla().getTblDatos() != null) {
			int selectedRow = this.getView().getPnlTabla().getTblDatos().getSelectedRow();
			if (selectedRow > -1) {
				objetoSeleccionado = this.getAbmTableModel().getRow(selectedRow);
			}
		}
		return objetoSeleccionado; 
	}
	
	
	/**
	 * Muestra una ventana de errores de validación agregándole los que son pasados por parámetro
	 * @param pListaErroresAgregados
	 * @return
	 */
	public boolean validarDatos(){
		boolean validacionOK = true;
		
		List<Object> attNulos = new ArrayList<Object>();
		List<JLabel> lblNulos = new ArrayList<JLabel>();
		
		List<Integer> attMin = new ArrayList<Integer>();
		List<JLabel> lblMin = new ArrayList<JLabel>();
		List<Integer> cantMin = new ArrayList<Integer>();
		
		attNulos.add(this.getView().getTfNombre().getText());
		lblNulos.add(this.getView().getLblNombre());
		
		attNulos.add(this.getView().getTfCuentaBancaria().getText());
		lblNulos.add(this.getView().getLblCuentaBancaria());
		
		attMin.add(this.getView().getPnlTabla().getTblDatos().getRowCount());
		lblMin.add(new JLabel("Las Lineas del Libro Banco"));
		cantMin.add(1);
		
		List<String> listaErrores = new ArrayList<String>();
		try {
			listaErrores.addAll(Validador.validarNulos(attNulos, lblNulos));
			listaErrores.addAll(Validador.validarCantidadMinima(attMin, lblMin, cantMin));
			listaErrores.addAll(Validador.validarFechaNoMayorALaActual(this.getView().getTfFechaGeneracion().getText(), this.getView().getLblFechaGeneracion()));
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
	
	protected void enableBotonesTabla(boolean flag) {
		this.getView().getPnlBotonesTabla().getBtnAgregar().setEnabled(flag);
		this.getView().getPnlBotonesTabla().getBtnModificar().setEnabled(flag);
		this.getView().getPnlBotonesTabla().getBtnEliminar().setEnabled(flag);
		this.getView().getPnlBotonesTabla().getBtnQuitarTodos().setEnabled(flag);
	}

	void seleccionarCuentaBancaria() throws Exception { 
		AdminCuentaBancaria adminCuentaBancaria= new AdminCuentaBancaria(this.getView());
		CuentaBancaria locCuentaBancaria= adminCuentaBancaria.openSelect();

		if (locCuentaBancaria!= null) {
			CuentaBancaria cuentaBancaria= ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConciliacionBancaria().getCuentaBancariaByID(locCuentaBancaria.getIdCuentaBancaria());
			this.getAbmModel().setCuentaBancaria(cuentaBancaria);
			this.actualizarABMModel();
			this.actualizarView();
		}
	}

	void limpiarCuentaBancaria() {
		this.getAbmModel().setCuentaBancaria(null);
		this.actualizarABMModel();
		this.actualizarView();
	}
	
	public void generarLibroBanco() throws Exception {
		if (this.validarDatosGeneraLibroBco()) {
			this.actualizarABMModel();
			this.getAbmModel().generar();
			this.getAbmTableModel().addRows(this.getAbmModel().getObjetoABM().getLineasLibroBanco());
			this.enableBotonesTabla(true);
			this.actualizarView();
		}
	}
	
	
	/**
	 * Valida la fecha del generar libro bco
	 * @return
	 */
	private boolean validarDatosGeneraLibroBco() {
		boolean validacionOK = true;
		
		List<Object> attNulos = new ArrayList<Object>();
		List<JLabel> lblNulos = new ArrayList<JLabel>();
		
		attNulos.add(this.getView().getTfNombre().getText());
		lblNulos.add(this.getView().getLblNombre());
		
		attNulos.add(this.getView().getTfCuentaBancaria().getText());
		lblNulos.add(this.getView().getLblCuentaBancaria());
		
		attNulos.add(this.getView().getTfFechaGeneracion().getValue());
		lblNulos.add(this.getView().getLblFechaGeneracion());
		
		List<String> listaErrores = new ArrayList<String>();
		try {
			listaErrores.addAll(Validador.validarNulos(attNulos, lblNulos));
			listaErrores.addAll(Validador.validarFechaNoMayorALaActual(this.getView().getTfFechaGeneracion().getText(), this.getView().getLblFechaGeneracion()));
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
	
	public void openAgregarLineaLibroBanco() throws Exception {
		AgregarLineaLibroBanco agregarLineaLibroBanco = new AgregarLineaLibroBanco(this.getView(),this.getAbmModel().getObjetoABM());
		agregarLineaLibroBanco.open();
		if (agregarLineaLibroBanco.isOperacionRealizada()) {
			LineaLibroBanco locLineaLibroBanco = agregarLineaLibroBanco.getAbmModel().getObjetoABM();
			locLineaLibroBanco.setLibroBanco(this.getAbmModel().getObjetoABM());
			this.getAbmTableModel().addRow(locLineaLibroBanco);
		}
	}
	
	public void openModificarLineaLibroBanco() throws Exception {
		LineaLibroBanco locLineaLibroBanco = this.getSelectedRow();
		
		if (locLineaLibroBanco != null) {
			ModificarLineaLibroBanco modificarLineaLibroBanco = new ModificarLineaLibroBanco(this.getView());
			modificarLineaLibroBanco.getAbmModel().setObjetoABM(locLineaLibroBanco);
			modificarLineaLibroBanco.actualizarView();
			modificarLineaLibroBanco.open();
			
 			if (modificarLineaLibroBanco.isOperacionRealizada()) {
 				this.getAbmModel().getObjetoABM().getLineasLibroBanco().remove(locLineaLibroBanco);
 				this.getAbmModel().getObjetoABM().getLineasLibroBanco().add(modificarLineaLibroBanco.getAbmModel().getObjetoABM());
 				this.getAbmTableModel().updateRow(locLineaLibroBanco);
			}
		}
	}
	

	public void openQuitarLineaLibroBanco() throws Exception {
		int fila = this.getView().getPnlTabla().getTblDatos().getSelectedRow();
		this.getAbmTableModel().deleteRow(fila);
	}

	public void openQuitarTodoLineaLibroBanco() throws Exception {
		if (!this.getAbmTableModel().getListaObjetos().isEmpty()) {
			if (AppManager.getInstance().showConfirmMsg(this.getView(), "¿Desea quitar todas las Líneas del Libro de Banco?")) {
				this.getAbmTableModel().clearTable();
			}
		}
	}

}

class BtnSeleccionarCuentaBancariaListener implements ActionListener {

	private ABMLibroBanco  controller;
	
	public BtnSeleccionarCuentaBancariaListener(ABMLibroBanco controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.seleccionarCuentaBancaria();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}

class BtnLimpiarCuentaBancariaListener implements ActionListener {

	private ABMLibroBanco controller;
	
	public BtnLimpiarCuentaBancariaListener(ABMLibroBanco controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.limpiarCuentaBancaria();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}

class BtnGenerarLibroBancoListener implements ActionListener {
	private ABMLibroBanco controller;
	
	public BtnGenerarLibroBancoListener(ABMLibroBanco controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.generarLibroBanco();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}

class BtnAgregarLineaLibroBancoListener implements ActionListener {
	private ABMLibroBanco controller;
	
	public BtnAgregarLineaLibroBancoListener(ABMLibroBanco controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openAgregarLineaLibroBanco();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}

class BtnModificarLineaLibroBancoListener implements ActionListener {
	private ABMLibroBanco controller;
	
	public BtnModificarLineaLibroBancoListener(ABMLibroBanco controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openModificarLineaLibroBanco();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}

class BtnQuitarLineaLibroBancoListener implements ActionListener {
	private ABMLibroBanco controller;
	
	public BtnQuitarLineaLibroBancoListener(ABMLibroBanco controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openQuitarLineaLibroBanco();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}

class BtnQuitarTodosLineaLibroBancoListener implements ActionListener {
	private ABMLibroBanco controller;
	
	public BtnQuitarTodosLineaLibroBancoListener(ABMLibroBanco controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openQuitarTodoLineaLibroBanco();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}

