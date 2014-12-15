package com.trascender.contabilidad.gui.abmSubdiarioCaja.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

import com.trascender.contabilidad.gui.abmLineaSubdiarioCaja.LineaSubdiarioCajaTableModel;
import com.trascender.contabilidad.gui.abmLineaSubdiarioCaja.abm.AgregarLineaSubdiarioCaja;
import com.trascender.contabilidad.gui.abmLineaSubdiarioCaja.abm.ModificarLineaSubdiarioCaja;
import com.trascender.contabilidad.gui.abmPlanDeCuenta.AdminPlanDeCuenta;
import com.trascender.contabilidad.gui.abmSubdiarioCaja.SubdiarioCajaABMModel;
import com.trascender.contabilidad.recurso.persistent.LineaSubdiarioCaja;
import com.trascender.contabilidad.recurso.persistent.PlanDeCuenta;
import com.trascender.contabilidad.recurso.persistent.SubdiarioCaja;
import com.trascender.gui.framework.abmStandard.ABMController;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.model.TDefaultComboBoxModel;
import com.trascender.gui.framework.util.Conversor;
import com.trascender.gui.framework.util.Validador;

public abstract class ABMSubdiarioCaja extends ABMController<SubdiarioCaja> {
	
	public abstract ABMSubdiarioCajaView getView();
	public abstract SubdiarioCajaABMModel getAbmModel(); 
	public abstract LineaSubdiarioCajaTableModel getTableModel();
	
	@Override
	protected void init() {
		super.init();
		this.setCommonProperties();
		this.setModels();
		this.setListeners();
	}
	
	private void setCommonProperties() {
		this.getView().getPnlBotonesTabla().getBtnModificar().setVisible(false);
	}
	
	private void setModels() {
		this.getView().setAbmModel(this.getAbmModel());
		this.getView().setTableModel(this.getTableModel());
		
		this.getView().getCbTipo().setModel(new TDefaultComboBoxModel(SubdiarioCaja.Tipo.values()));
	}
	
	private void setListeners() {
		this.getView().getPnlBotonesSeleccionPlanDeCuenta().getBtnSeleccionar().addActionListener(new BtnSeleccionarPlanDeCuentaListener(this));
		this.getView().getPnlBotonesSeleccionPlanDeCuenta().getBtnLimpiar().addActionListener(new BtnLimpiarPlanDeCuentaListener(this));
		
		this.getView().getPnlBotonesTabla().getBtnAgregar().addActionListener(new BtnAgregarLineaSubdiarioCajaListener(this));
		this.getView().getPnlBotonesTabla().getBtnModificar().addActionListener(new BtnModificarLineaSubdiarioCajaListener(this));
		this.getView().getPnlBotonesTabla().getBtnEliminar().addActionListener(new BtnQuitarLineaSubdiarioCajaListener(this));
		this.getView().getPnlBotonesTabla().getBtnQuitarTodos().addActionListener(new BtnQuitarTodosLineaSubdiarioCajaListener(this));
	}
	
	@Override
	public void actualizarABMModel() {
		this.getAbmModel().setFechaCreacion(Conversor.getDate(this.getView().getFtfFechaCreacion().getText()));
		
		Object locTipo = this.getView().getCbTipo().getSelectedItem();
		if (locTipo != null) this.getAbmModel().setTipo((SubdiarioCaja.Tipo)locTipo);
		else this.getAbmModel().setTipo(null);
		
		List<LineaSubdiarioCaja> locLista = this.getTableModel().getListaObjetos();
		this.getAbmModel().getLineasSubdiarioCaja().clear();
		this.getAbmModel().getLineasSubdiarioCaja().addAll(locLista);
		
		this.getAbmModel().fireActualizarDatos();
	}
	
	@Override
	public void actualizarView() {
		this.getView().getFtfFechaCreacion().setValue(Conversor.getString(this.getAbmModel().getFechaCreacion()));
		this.getView().getTfPlanDeCuenta().setText(Conversor.getVacioSiNull(this.getAbmModel().getPlanDeCuenta()));
		this.getView().getCbTipo().setSelectedItem(this.getAbmModel().getTipo());
		this.getTableModel().setListaObjetos(new ArrayList<LineaSubdiarioCaja>(this.getAbmModel().getLineasSubdiarioCaja()));
	}
	
	public LineaSubdiarioCaja getSelectedRow() {
		LineaSubdiarioCaja objetoSeleccionado = null;
		if (this.getView() != null && this.getView().getPnlTabla().getTblDatos() != null) {
			int selectedRow = this.getView().getPnlTabla().getTblDatos().getSelectedRow();
			if (selectedRow > -1) {
				objetoSeleccionado = this.getTableModel().getRow(selectedRow);
			}
		}
		return objetoSeleccionado; 
	}

	public boolean validarDatosGenerarSubdiario() {
		boolean validacionOK = true;
		
		List<Object> attNulos = new ArrayList<Object>();
		List<JLabel> lblNulos = new ArrayList<JLabel>();
		
		List<String> attFechas = new ArrayList<String>();
		List<JLabel> lblFechas = new ArrayList<JLabel>();
		
		attNulos.add(this.getView().getFtfFechaCreacion().getText());
		lblNulos.add(this.getView().getLblFechaCreacion());
		
		attNulos.add(this.getView().getTfPlanDeCuenta().getText());
		lblNulos.add(this.getView().getLblPlanDeCuenta());
		
		attNulos.add(this.getView().getCbTipo().getSelectedItem());
		lblNulos.add(this.getView().getLblTipo());
		
		attFechas.add(this.getView().getFtfFechaCreacion().getText());
		lblFechas.add(this.getView().getLblFechaCreacion());
		
		List<String> listaErrores = new ArrayList<String>();
		
		try {
			listaErrores.addAll(Validador.validarNulos(attNulos, lblNulos));
			listaErrores.addAll(Validador.validarFechas(attFechas, lblFechas));
			listaErrores.addAll(Validador.validarFechaNoMayorALaActual(this.getView().getFtfFechaCreacion().getText(), this.getView().getLblFechaCreacion()));
		} catch (GuiException ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.getView(), ex.getMessage());
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
	
	@Override
	public boolean validarDatos() {
		boolean validacionOK = true;
		
		List<Integer> attMin = new ArrayList<Integer>();
		List<JLabel> lblMin = new ArrayList<JLabel>();
		List<Integer> cantMin = new ArrayList<Integer>();

		attMin.add(this.getView().getPnlTabla().getTblDatos().getRowCount());
		lblMin.add(new JLabel("La Lista de Líneas del Subdiario"));
		cantMin.add(1);
		
		List<String> listaErrores = new ArrayList<String>();
		try {
			listaErrores.addAll(Validador.validarCantidadMinima(attMin, lblMin, cantMin));
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
	
	void seleccionarPlanDeCuenta() throws Exception {
		AdminPlanDeCuenta adminPlanDeCuenta = new AdminPlanDeCuenta(this.getView());
		PlanDeCuenta locPlanDeCuenta = adminPlanDeCuenta.openSelect();
		
		if (locPlanDeCuenta != null) {
			this.getAbmModel().setPlanDeCuenta(locPlanDeCuenta);
			this.actualizarABMModel();
			this.actualizarView();
		}
	}
	
	void limpiarPlanDeCuenta() throws Exception {
		this.getAbmModel().setPlanDeCuenta(null);
		this.getTableModel().clearTable();
		this.actualizarABMModel();
		this.actualizarView();
	}
	
	void openAgregarLineaSubdiarioCaja() throws Exception {
		// DIA
//		Calendar c = Calendar.getInstance();
//		c.set(Integer.valueOf(partes[2]), Integer.valueOf(partes[1]), Integer.valueOf(partes[0]));
//			System.out.println("Dia ------> " + c.get(Calendar.DATE));
//			System.out.println("Mes ------> " + c.get(Calendar.MONTH));
//			System.out.println("Año ------> " + c.get(Calendar.YEAR));
			
		AgregarLineaSubdiarioCaja agregarLineaSubdiarioCaja = new AgregarLineaSubdiarioCaja(this.getView());
		
		String[] partes = this.getView().getFtfFechaCreacion().getText().split("/");
		agregarLineaSubdiarioCaja.getAbmModel().setMes(partes[1]);
		agregarLineaSubdiarioCaja.getAbmModel().setAnio(partes[2]);	

		agregarLineaSubdiarioCaja.open();
		
		if (agregarLineaSubdiarioCaja.isOperacionRealizada()) {
			LineaSubdiarioCaja locLineaSubdiarioCaja = agregarLineaSubdiarioCaja.getAbmModel().getObjetoABM();
			locLineaSubdiarioCaja.setSubdiarioCaja(this.getAbmModel().getObjetoABM());
			this.getTableModel().addRow(locLineaSubdiarioCaja);
		}
	}
	
	void openModificarLineaSubdiarioCaja() throws Exception {
		LineaSubdiarioCaja locLineaSubdiarioCaja = this.getSelectedRow();
		
		if (locLineaSubdiarioCaja != null) {
			ModificarLineaSubdiarioCaja modificarLineaSubdiarioCaja = new ModificarLineaSubdiarioCaja(this.getView());
			modificarLineaSubdiarioCaja.getAbmModel().setObjetoABM(locLineaSubdiarioCaja);
			modificarLineaSubdiarioCaja.actualizarView();
			modificarLineaSubdiarioCaja.open();
			
			if (modificarLineaSubdiarioCaja.isOperacionRealizada()) {
				this.getTableModel().updateRow(locLineaSubdiarioCaja);
			}
		}
	}

	void quitarLineaSubdiarioCaja() throws Exception {
		int fila = this.getView().getPnlTabla().getTblDatos().getSelectedRow();
		this.getTableModel().deleteRow(fila);
	}
	
	void quitarTodosLineaSubdiarioCaja() throws Exception {
		if (!this.getTableModel().getListaObjetos().isEmpty()) {
			if (AppManager.getInstance().showConfirmMsg(this.getView(), "¿Desea quitar todas las Líneas del Subdiario?")) {
				this.getTableModel().clearTable();
			}
		}
	}
	
}



class BtnSeleccionarPlanDeCuentaListener implements ActionListener {
	private ABMSubdiarioCaja controller;
	public BtnSeleccionarPlanDeCuentaListener(ABMSubdiarioCaja controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.seleccionarPlanDeCuenta();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnLimpiarPlanDeCuentaListener implements ActionListener {
	private ABMSubdiarioCaja controller;
	public BtnLimpiarPlanDeCuentaListener(ABMSubdiarioCaja controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.limpiarPlanDeCuenta();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnAgregarLineaSubdiarioCajaListener implements ActionListener {
	private ABMSubdiarioCaja controller;
	public BtnAgregarLineaSubdiarioCajaListener(ABMSubdiarioCaja controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openAgregarLineaSubdiarioCaja();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnModificarLineaSubdiarioCajaListener implements ActionListener {
	private ABMSubdiarioCaja controller;
	public BtnModificarLineaSubdiarioCajaListener(ABMSubdiarioCaja controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openModificarLineaSubdiarioCaja();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnQuitarLineaSubdiarioCajaListener implements ActionListener {
	private ABMSubdiarioCaja controller;
	public BtnQuitarLineaSubdiarioCajaListener(ABMSubdiarioCaja controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.quitarLineaSubdiarioCaja();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnQuitarTodosLineaSubdiarioCajaListener implements ActionListener {
	private ABMSubdiarioCaja controller;
	public BtnQuitarTodosLineaSubdiarioCajaListener(ABMSubdiarioCaja controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.quitarTodosLineaSubdiarioCaja();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}
