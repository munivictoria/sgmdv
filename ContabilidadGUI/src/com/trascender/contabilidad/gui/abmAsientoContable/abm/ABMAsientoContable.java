package com.trascender.contabilidad.gui.abmAsientoContable.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTable;

import com.trascender.contabilidad.gui.abmAsientoContable.AsientoContableABMModel;
import com.trascender.contabilidad.gui.abmLibroDiario.AdminLibroDiario;
import com.trascender.contabilidad.gui.abmLineaAsientoContable.LineaAsientoContableTableModel;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.AsientoContable;
import com.trascender.contabilidad.recurso.persistent.FolioLibroDiario;
import com.trascender.contabilidad.recurso.persistent.LibroDiario;
import com.trascender.contabilidad.recurso.persistent.LineaAsientoContable;
import com.trascender.contabilidad.recurso.persistent.SubdiarioCaja;
import com.trascender.contabilidad.system.interfaces.SystemAdministracionConsultaContable;
import com.trascender.gui.framework.abmStandard.ABMController;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.model.TDefaultComboBoxModel;
import com.trascender.gui.framework.recursos.Messages;
import com.trascender.gui.framework.util.Conversor;
import com.trascender.gui.framework.util.Validador;

public abstract class ABMAsientoContable extends ABMController<AsientoContable> {
	
	public abstract ABMAsientoContableView getView();
	public abstract AsientoContableABMModel getAbmModel();
	public abstract LineaAsientoContableTableModel getTableModel();
	
	@Override
	protected void init() {
		super.init();
		this.setModels();
		this.setListeners();
		this.setCommonProperties();
		
		this.getView().getPnlTabla().getTblDatos().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		this.getView().getPnlTabla().getTblDatos().getColumnModel().getColumn(0).setPreferredWidth(310);
		this.getView().getPnlTabla().getTblDatos().getColumnModel().getColumn(1).setPreferredWidth(140);
		this.getView().getPnlTabla().getTblDatos().getColumnModel().getColumn(2).setPreferredWidth(140);
		this.getView().getCbFolioLibroDiario().setEnabled(false);
	}
	
	private void setModels() {
		this.getView().setAbmModel(this.getAbmModel());
		this.getView().getPnlTabla().getTblDatos().setModel(this.getTableModel());
		this.getView().getCbTipoSubdiarioCaja().setModel(new TDefaultComboBoxModel(SubdiarioCaja.Tipo.values()));
	}
	
	private void setListeners() {
		this.getView().getPnlBotonesSeleccionLibroDiario().getBtnSeleccionar().addActionListener(new BtnSeleccionarLibroDiarioListener(this));
		this.getView().getPnlBotonesSeleccionLibroDiario().getBtnLimpiar().addActionListener(new BtnLimpiarLibroDiarioListener(this));

		this.getView().getBtnGenerarAsientoContable().addActionListener(new BtnGenerarAsientoContableListener(this));
		this.getView().getBtnCargarAsientoContable().addActionListener(new BtnCargarAsientoContableListener(this));
	}
	
	private void setCommonProperties() {
		this.getView().getPnlBtnTabla().getBtnEliminar().setVisible(false);
		this.getView().getPnlBtnTabla().getBtnModificar().setVisible(false);
		this.getView().getPnlBtnTabla().getBtnQuitarTodos().setVisible(false);
		
		this.getView().getPnlBtnTabla().getBtnAgregar().setText(Messages.getString("Application.btnQuitar"));
		this.getView().getPnlBtnTabla().getBtnAgregar().setMnemonic(Messages.getString("Application.btnQuitarMnemonic").charAt(0));
	}
	
	public boolean validarDatosGenerarAsientoContable() {
		boolean validacionOK = true;
		
		List<Object> attNulos = new ArrayList<Object>();
		List<JLabel> lblNulos = new ArrayList<JLabel>();
		
		attNulos.add(this.getView().getFtfFecha().getText());
		lblNulos.add(this.getView().getLblFecha());
		
		attNulos.add(this.getView().getCbTipoSubdiarioCaja().getSelectedItem());
		lblNulos.add(this.getView().getLblTipoSubdiarioCaja());
		
		List<String> listaErrores = new ArrayList<String>();
		
		try {
			listaErrores.addAll(Validador.validarNulos(attNulos, lblNulos));
			listaErrores.addAll(Validador.validarFechaNoMayorALaActual(this.getView().getFtfFecha().getText(), this.getView().getLblFecha()));
			
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
	
	private boolean validarDatosAgregarAsientoContable() {
		boolean validacionOK = true;
		
		List<Object> attNulos = new ArrayList<Object>();
		List<JLabel> lblNulos = new ArrayList<JLabel>();
		
		attNulos.add(this.getView().getFtfFecha().getText());
		lblNulos.add(this.getView().getLblFecha());
		
		List<String> listaErrores = new ArrayList<String>();
		
		try {
			listaErrores.addAll(Validador.validarNulos(attNulos, lblNulos));
			listaErrores.addAll(Validador.validarFechaNoMayorALaActual(this.getView().getFtfFecha().getText(), this.getView().getLblFecha()));
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
	
	@Override
	public boolean validarDatos() {
		boolean validacionOK = true;
		
		List<Object> attNulos = new ArrayList<Object>();
		List<JLabel> lblNulos = new ArrayList<JLabel>();
		
		attNulos.add(this.getView().getTfNumeroAsiento().getText());
		lblNulos.add(this.getView().getLblNumeroAsiento());
		
		attNulos.add(this.getView().getTfLibroDiario().getText());
		lblNulos.add(this.getView().getLblLibroDiario());
		
		attNulos.add(this.getView().getCbFolioLibroDiario().getSelectedItem());
		lblNulos.add(this.getView().getLblFolioLibroDiario());
		
		attNulos.add(this.getView().getFtfFecha().getText());
		lblNulos.add(this.getView().getLblFecha());
		
		List<String> listaErrores = new ArrayList<String>();
		
		try {
			listaErrores.addAll(Validador.validarNulos(attNulos, lblNulos));
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
	
	@Override
	protected void actualizarABMModel() {
		this.getAbmModel().setNumeroAsiento(Conversor.getInteger(this.getView().getTfNumeroAsiento().getText()));
		
		Object locFolio = this.getView().getCbFolioLibroDiario().getSelectedItem();
		if (locFolio != null) this.getAbmModel().setFolioLibroDiario((FolioLibroDiario)locFolio);
		else this.getAbmModel().setFolioLibroDiario(null);
		
		this.getAbmModel().setObservaciones(Conversor.getNullSiVacio(this.getView().getTaObservaciones().getText()));
		
		this.getAbmModel().setFecha(Conversor.getDate(this.getView().getFtfFecha().getText()));
		
		Object locTipoSubdiario = this.getView().getCbTipoSubdiarioCaja().getSelectedItem();
		if (locTipoSubdiario != null) this.getAbmModel().setTipoSubdiarioCaja((SubdiarioCaja.Tipo)locTipoSubdiario);
		else this.getAbmModel().setTipoSubdiarioCaja(null);
	}
	
	@Override
	public void actualizarView() { 
		this.getView().getTfNumeroAsiento().setText(Conversor.getVacioSiNull(this.getAbmModel().getNumeroAsiento()));
		this.getView().getTfLibroDiario().setText(Conversor.getVacioSiNull(this.getAbmModel().getLibroDiario()));
		
		this.getView().getCbFolioLibroDiario().setSelectedItem(this.getAbmModel().getFolioLibroDiario());
		this.getView().getTaObservaciones().setText(Conversor.getVacioSiNull(this.getAbmModel().getObservaciones()));
		this.getView().getFtfFecha().setValue(Conversor.getString(this.getAbmModel().getFecha()));
		this.getView().getCbTipoSubdiarioCaja().setSelectedItem(this.getAbmModel().getTipoSubdiarioCaja());
		
		this.getView().getPnlTabla().getTblDatos().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		this.getView().getPnlTabla().getTblDatos().getColumnModel().getColumn(0).setPreferredWidth(315);
		this.getView().getPnlTabla().getTblDatos().getColumnModel().getColumn(1).setPreferredWidth(140);
		this.getView().getPnlTabla().getTblDatos().getColumnModel().getColumn(2).setPreferredWidth(140);
	}
	
	@SuppressWarnings("unchecked")
	void seleccionarLibroDiario() throws Exception {
		AdminLibroDiario adminLibroDiario = new AdminLibroDiario(this.getView());
		LibroDiario locLibroDiario = adminLibroDiario.openSelect();
		if (locLibroDiario != null) {
			this.getAbmModel().setLibroDiario(locLibroDiario);
			SystemAdministracionConsultaContable locSystem = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable();
			List<FolioLibroDiario> locListaFolios = locSystem.findListaFolioLibroDiario(null, locLibroDiario);
			Collections.sort(locListaFolios, new Comparator<FolioLibroDiario>() {
				public int compare(FolioLibroDiario o1, FolioLibroDiario o2) {
					return o1.toString().compareToIgnoreCase(o2.toString());
				}
			});
			this.getView().getCbFolioLibroDiario().setModel(new TDefaultComboBoxModel(locListaFolios.toArray()));
			this.getView().getCbFolioLibroDiario().setEnabled(true);
			this.actualizarABMModel();
			this.actualizarView();
		}
	}
	
	public void calcularResultados(){
		this.getView().getPnlResultados().getLblTotalDebe().setText(this.TotalAsientoContableDebe());
		this.getView().getPnlResultados().getLblTotalHaber().setText(this.TotalAsientoContableHaber());
	}
	
	void limpiarLibroDiario() throws Exception {
		this.getAbmModel().setLibroDiario(null);
		this.getAbmModel().setFolioLibroDiario(null);
		this.getView().getCbFolioLibroDiario().setSelectedItem(null);
		this.getView().getCbFolioLibroDiario().setEnabled(false);
		this.actualizarABMModel();
		this.actualizarView();
	}
	
	void generarAsientoContable() throws Exception {
		System.out.println("------------------------------> " + this.getView().getCbTipoSubdiarioCaja().getSelectedItem());
		if(this.getView().getCbTipoSubdiarioCaja().getSelectedItem().equals(SubdiarioCaja.Tipo.PRESUPUESTARIO)){
			throw new Exception("Para generar el asiento debe seleccionar un tipo diferente a Presupuestario");
		}
		
		this.getTableModel().clearTable();
		
		if (this.validarDatosGenerarAsientoContable()) {
			this.actualizarABMModel();
			
			if (this.getAbmModel().getTipoSubdiarioCaja() != null) {
				if (this.getAbmModel().getTipoSubdiarioCaja().equals(SubdiarioCaja.Tipo.INGRESO)) {
					this.getView().getPnlAyudaDevengamiento().setVisible(true);
				}else {
					this.getView().getPnlAyudaDevengamiento().setVisible(false);
				}
			}
				
			this.getAbmModel().generarAsientoContable();
			this.getTableModel().addRows(this.getAbmModel().getObjetoABM().getLineasAsientoContable());
			//this.actualizarView();
			
			this.getView().getPnlTabla().getTblDatos().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			this.getView().getPnlTabla().getTblDatos().getColumnModel().getColumn(0).setPreferredWidth(315);
			this.getView().getPnlTabla().getTblDatos().getColumnModel().getColumn(1).setPreferredWidth(140);
			this.getView().getPnlTabla().getTblDatos().getColumnModel().getColumn(2).setPreferredWidth(140);
			this.calcularResultados();
		}
	}
	
	
	/**
	 * Este método abre la ventana de cargar asiento contable.
	 * @throws Exception
	 */
	void cargarAsientoContable() throws Exception {
		if (this.validarDatosAgregarAsientoContable()) {
			this.actualizarABMModel();
			CargarAsientoContable cargarAsientoContable = new CargarAsientoContable(this.getView(), this.getAbmModel());
			cargarAsientoContable.open();
			if (cargarAsientoContable.isOperacionRealizada()) {
				this.getTableModel().clearTable();
				this.getTableModel().addRows(this.getAbmModel().getObjetoABM().getLineasAsientoContable());
				//this.actualizarView();
			}
			this.getView().getPnlTabla().getTblDatos().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			this.getView().getPnlTabla().getTblDatos().getColumnModel().getColumn(0).setPreferredWidth(315);
			this.getView().getPnlTabla().getTblDatos().getColumnModel().getColumn(1).setPreferredWidth(140);
			this.getView().getPnlTabla().getTblDatos().getColumnModel().getColumn(2).setPreferredWidth(140);
			this.calcularResultados();
		}
	}

	private String TotalAsientoContableDebe(){
		Double locTotal = new Double(0);
		for(LineaAsientoContable cadaLineaAsientoContable: this.getAbmModel().getLineaAsientoContable()){
		
			if(cadaLineaAsientoContable.getImporteDebe() != null){
				locTotal+=cadaLineaAsientoContable.getImporteDebe();
			}
		}
		
		NumberFormat dispFormat = NumberFormat.getNumberInstance();
		dispFormat.setMaximumFractionDigits(2);
		dispFormat.setMinimumFractionDigits(2);
		dispFormat.setGroupingUsed(true);
		
		return dispFormat.format(locTotal);
	} 

	private String TotalAsientoContableHaber(){
		Double locTotal = new Double(0);
				
		for(LineaAsientoContable cadaLineaAsientoContable: this.getAbmModel().getLineaAsientoContable()){
		
			if(cadaLineaAsientoContable.getImporteHaber() != null){
				locTotal+=cadaLineaAsientoContable.getImporteHaber();
			}
		}

		NumberFormat dispFormat = NumberFormat.getNumberInstance();
		dispFormat.setMaximumFractionDigits(2);
		dispFormat.setMinimumFractionDigits(2);
		dispFormat.setGroupingUsed(true);
		
		return dispFormat.format(locTotal);
	} 
}

/**
 * 
 * @author marina
 *
 */
class BtnSeleccionarLibroDiarioListener implements ActionListener {
	private ABMAsientoContable controller;
	public BtnSeleccionarLibroDiarioListener(ABMAsientoContable controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.seleccionarLibroDiario();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}


class BtnLimpiarLibroDiarioListener implements ActionListener {
	private ABMAsientoContable controller;
	public BtnLimpiarLibroDiarioListener(ABMAsientoContable controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.limpiarLibroDiario();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnGenerarAsientoContableListener implements ActionListener {
	private ABMAsientoContable controller;
	public BtnGenerarAsientoContableListener(ABMAsientoContable controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.generarAsientoContable();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnCargarAsientoContableListener implements ActionListener {
	private ABMAsientoContable controller;

	public BtnCargarAsientoContableListener(ABMAsientoContable controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.cargarAsientoContable();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}