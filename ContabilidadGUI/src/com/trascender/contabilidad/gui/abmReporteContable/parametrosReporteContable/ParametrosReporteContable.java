package com.trascender.contabilidad.gui.abmReporteContable.parametrosReporteContable;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.swing.JRViewer;
import ar.trascender.criterio.clases.Criterio;
import ar.trascender.criterio.clases.Restriccion;

import com.trascender.compras.recurso.persistent.suministros.LiquidacionCompra;
import com.trascender.compras.reporte.dataSource.LiquidacionCompraDS;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.gui.recursos.RecursosContabilidad;
import com.trascender.contabilidad.recurso.persistent.ParametroReporteContable;
import com.trascender.contabilidad.recurso.persistent.ParametroReporteContable.Tipo;
import com.trascender.contabilidad.recurso.persistent.ReporteContable;
import com.trascender.contabilidad.system.interfaces.SystemReportesContabilidad;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.ReportesJasper;
import com.trascender.gui.framework.abmStandard.ABMErrores;
import com.trascender.gui.framework.abmStandard.ABMView;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.recursos.Messages;
import com.trascender.gui.framework.util.Constantes;
import com.trascender.gui.framework.util.ConstantesPosicion;
import com.trascender.gui.framework.util.ConstantesSeparacion;
import com.trascender.gui.framework.util.ConstantesTamanio;
import com.trascender.gui.framework.util.Conversor;
import com.trascender.gui.framework.util.Util;
import com.trascender.gui.framework.util.Validador;

public class ParametrosReporteContable extends ABMView{

	private static final long serialVersionUID = -181495561954809770L;
	
	private ReporteContable reporteContable;
	
	private Map<ParametroReporteContable, Object> locMapaParametros = new HashMap<ParametroReporteContable, Object>();
	private Map<ParametroReporteContable, JLabel> locMapaLabels = new HashMap<ParametroReporteContable, JLabel>();
	
	public ParametrosReporteContable() {
		super();
		this.init();
	}


	public ParametrosReporteContable(JFrame pFrame, ReporteContable pReporteContable) {
		super(pFrame);
		this.reporteContable = pReporteContable;
		this.init();
	}
	
	
	private void init() {
		this.setImagenCabecera();
		this.setColorFondo();
		this.setDescripcionVentana();
		this.setTextoBtnAceptar();
		this.setTituloVentana();
		
		int numFila = -1;
		
		for(ParametroReporteContable cadaParametro : this.reporteContable.getListaParametroReporte()){
			numFila++;
			JLabel label = new JLabel();
			label.setText(cadaParametro.getNombre());
			label.setBounds(Util.getBoundsColumnaLabel(numFila));
			this.getPnlCuerpo().add(label);
			
			locMapaLabels.put(cadaParametro, label);
			
			PnlSeleccion panel = null;
			if(cadaParametro.getTipo().equals(ParametroReporteContable.Tipo.PLANTILLA_BALANCE)){
				 panel = new PnlSeleccionPlantillaBalance(this);
				 this.armarPanel(panel, numFila, cadaParametro);
				
			} else if(cadaParametro.getTipo().equals(ParametroReporteContable.Tipo.CUENTA)){
				panel = new PnlSeleccionCuenta(this);
				this.armarPanel(panel, numFila, cadaParametro);
			} else if(cadaParametro.getTipo().equals(ParametroReporteContable.Tipo.ÁREA)){
				PnlSeleccionArea locPanelArea = new PnlSeleccionArea(this);
				locPanelArea.getComboBox().setBounds(Util.getBoundsColumnaInputTextField(numFila));
				this.getPnlCuerpo().add(locPanelArea.getComboBox());
				this.locMapaParametros.put(cadaParametro, locPanelArea);
				
			} else if(cadaParametro.getTipo().equals(ParametroReporteContable.Tipo.SECRETARÍA)){
				PnlSeleccionSecretaria locPanelSecretaria = new PnlSeleccionSecretaria(this);
				locPanelSecretaria.getComboBox().setBounds(Util.getBoundsColumnaInputTextField(numFila));
				this.getPnlCuerpo().add(locPanelSecretaria.getComboBox());
				this.locMapaParametros.put(cadaParametro, locPanelSecretaria);
			} else{
				JTextField textField = new JTextField();
				if(cadaParametro.getTipo().equals(ParametroReporteContable.Tipo.FECHA)){
					try {
						MaskFormatter formatter = new MaskFormatter("##/##/####");
						textField = new JFormattedTextField(formatter);
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				
				textField.setBounds(Util.getBoundsColumnaInputTextField(numFila));
				
				this.getPnlCuerpo().add(textField);
				this.locMapaParametros.put(cadaParametro, textField);
			}
		}
		
		this.setTamanioPosicionVentana(numFila + 1);
		this.getPnlPie().getBtnAceptar().addActionListener(new BtnVisualizarListener(this));
		this.getPnlPie().getBtnCancelar().addActionListener(new BtnCancelarListener(this));
	}

	private void armarPanel(PnlSeleccion pPanel, int pNumFila, ParametroReporteContable pParametro){
		pPanel.getTextField().setBounds(Util.getBoundsColumnaInputTextField(pNumFila));
		pPanel.getTextField().setEditable(false);
		this.getPnlCuerpo().add(pPanel.getTextField());
		
		pPanel.getPanelBotones().setBounds(Util.getBoundsColumnaSeleccion(pNumFila));
		this.getPnlCuerpo().add(pPanel.getPanelBotones());
		this.locMapaParametros.put(pParametro, pPanel);
	}
	
	@Override
	public void setTituloVentana() {
		this.setTitle("Parámetros del Reporte");
		this.getPnlCabecera().getLblTitulo().setText(this.reporteContable.getNombre());
	}


	@Override
	public void setDescripcionVentana() {
	}


	@Override
	public void setColorFondo() {
		Color color = Constantes.COLOR_REPORTE;
		this.getPnlCabecera().setBackground(color);
		this.getPnlPie().setBackground(color);
	}


	@Override
	public void setTextoBtnAceptar() {
		this.getPnlPie().getBtnAceptar().setText("Visualizar");
		this.getPnlPie().getBtnAceptar().setMnemonic('V');
	}


	@Override
	public void setTamanioPosicionVentana(int pCantidadFilasComponentes) {
		this.setSize(new Dimension(
				ConstantesPosicion.COLUMN_LBL_POS_INI_X*12+ConstantesTamanio.TF_WIDTH+ConstantesPosicion.COLUMN_INPUT_POS_INI_X, 
				ConstantesTamanio.PNL_CABECERA_HEIGHT+ConstantesTamanio.PNL_PIE_HEIGHT+pCantidadFilasComponentes*
				ConstantesSeparacion.INCREMENTO_Y+ConstantesPosicion.COLUMN_LBL_POS_INI_Y));
		this.setLocationRelativeTo(null);
	}
	
	public void abrirVisualzarReporte(){
		Map<String, Object> locMapaResultado = new HashMap<String, Object>();
		
		for(ParametroReporteContable cadaParametro : this.locMapaParametros.keySet()){
			Object locComponente = this.locMapaParametros.get(cadaParametro);
			if(locComponente instanceof JTextField){
				JTextField locTf = (JTextField) locComponente;
				if(locTf.getText() != null && !locTf.getText().equals("")){
					
					if(cadaParametro.getTipo().equals(ParametroReporteContable.Tipo.FECHA)){
						JFormattedTextField locFormatedTextField = (JFormattedTextField) locTf;
						try {
							locMapaResultado.put(cadaParametro.getNombre(), Conversor.getDate(locFormatedTextField.getText()));
						} catch (Exception e1) {
							e1.printStackTrace();
							AppManager.getInstance().showErrorMsg((this), e1.getMessage());
						}
					} else if(cadaParametro.getTipo().equals(ParametroReporteContable.Tipo.NUMÉRICO)) {
						try{
							Double locValor = Double.parseDouble(locTf.getText());
							locMapaResultado.put(cadaParametro.getNombre(), locValor);
						} catch (Exception e1) {
							e1.printStackTrace();
							AppManager.getInstance().showErrorMsg((this), e1.getMessage());
						}
					} else {
						locMapaResultado.put(cadaParametro.getNombre(), locTf.getText());
					}
				}
			} else if (locComponente instanceof PnlSeleccion){
					PnlSeleccion locPnl = (PnlSeleccion) this.locMapaParametros.get(cadaParametro);
					JTextField locTf = locPnl.getTextField();
					if(locTf.getText() != null && !locTf.getText().equals("")){
						locMapaResultado.put(cadaParametro.getNombre(), locPnl.getIdObjetoSeleccionado());
					}
			} else if (locComponente instanceof PnlSeleccionArea){
				PnlSeleccionArea locPnl = (PnlSeleccionArea) this.locMapaParametros.get(cadaParametro);
				JComboBox locCb = locPnl.getComboBox();
				if(locCb.getSelectedItem() != null){
					locMapaResultado.put(cadaParametro.getNombre(), locPnl.getIdAreaSeleccionada());
				}
			} else if (locComponente instanceof PnlSeleccionSecretaria){
				PnlSeleccionSecretaria locPnl = (PnlSeleccionSecretaria) this.locMapaParametros.get(cadaParametro);
				JComboBox locCb = locPnl.getComboBox();
				if(locCb.getSelectedItem() != null){
					locMapaResultado.put(cadaParametro.getNombre(), locPnl.getIdSecretariaSeleccionada());
				}
			}
		}

		SystemReportesContabilidad locSystem;
		try {
			locSystem = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemReportesContabilidad();
			JasperPrint locJasperPrint = locSystem.getReporteContable(reporteContable, locMapaResultado);
			this.dispose();
			this.mostrarVistaPreviaReporte(locJasperPrint, this.reporteContable.getNombre());
		} catch (Exception e1){
			e1.printStackTrace();
			AppManager.getInstance().showErrorMsg((this), e1.getMessage());
		}
	}
	
	public void setImagenCabecera() {
		Icon i = AppManager.getInstance().getAdminRecursos().getIcon(RecursosContabilidad.IMG_CAB_REPORTES);
		this.getPnlCabecera().getLblImagen().setIcon(i);
	}
	
	public boolean validarDatos() {
		boolean validacionOK = true;
		
		List<Object> attNulos = new ArrayList<Object>();
		List<JLabel> lblNulos = new ArrayList<JLabel>();
		
		List<String> attFechas = new ArrayList<String>();
		List<JLabel> lblFechas = new ArrayList<JLabel>();
		
		List<Object> attNumericos = new ArrayList<Object>();
		List<JLabel> lblNumericos = new ArrayList<JLabel>();
		
		for(ParametroReporteContable cadaParametro : this.locMapaParametros.keySet()){
			if(cadaParametro.isRequerido()){
				Tipo locTipo = cadaParametro.getTipo();
				if(locTipo.equals(ParametroReporteContable.Tipo.PLANTILLA_BALANCE) || locTipo.equals(ParametroReporteContable.Tipo.CUENTA)){
					JTextField locTf = ((PnlSeleccion)this.locMapaParametros.get(cadaParametro)).getTextField();
					attNulos.add(locTf.getText());
					lblNulos.add(this.locMapaLabels.get(cadaParametro));
				} else if(locTipo.equals(ParametroReporteContable.Tipo.ÁREA)){
					JComboBox locCb = ((PnlSeleccionArea)this.locMapaParametros.get(cadaParametro)).getComboBox();
					attNulos.add(locCb.getSelectedItem());
					lblNulos.add(this.locMapaLabels.get(cadaParametro));
				} else if(locTipo.equals(ParametroReporteContable.Tipo.SECRETARÍA)){
					JComboBox locCb = ((PnlSeleccionSecretaria)this.locMapaParametros.get(cadaParametro)).getComboBox();
					attNulos.add(locCb.getSelectedItem());
					lblNulos.add(this.locMapaLabels.get(cadaParametro));
				} else{
					attNulos.add(((JTextField)this.locMapaParametros.get(cadaParametro)).getText());
					lblNulos.add(this.locMapaLabels.get(cadaParametro));
					
					if(cadaParametro.getTipo().equals(ParametroReporteContable.Tipo.FECHA)){
						attFechas.add(((JTextField)this.locMapaParametros.get(cadaParametro)).getText());
						lblFechas.add(this.locMapaLabels.get(cadaParametro));
					}
				}
			}
			if(cadaParametro.getTipo().equals(ParametroReporteContable.Tipo.NUMÉRICO)){
				attNumericos.add(((JTextField)this.locMapaParametros.get(cadaParametro)).getText());
				lblNumericos.add(this.locMapaLabels.get(cadaParametro));
			}
		}
		
		List<String> listaErrores = new ArrayList<String>();
		
		try {
			listaErrores.addAll(Validador.validarNulos(attNulos, lblNulos));
			listaErrores.addAll(Validador.validarFechas(attFechas, lblFechas));
			listaErrores.addAll(Validador.validarDecimales(attNumericos, lblNumericos));
		} catch (GuiException ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this, ex.getMessage());
		}
		
		if (!listaErrores.isEmpty()) {
			validacionOK = false;
			this.mostrarErroresValidacion(listaErrores);
		}
		
		return validacionOK;
	}
	
	public void mostrarErroresValidacion(List<String> listaErrores) {
		if (Messages.getBoolean("Application.mostrarPantallaErroresABM")) {
			ABMErrores locVentanaErrores = new ABMErrores(this);
			locVentanaErrores.getView().setListaErrores(listaErrores);
			locVentanaErrores.open();
		}
	}
	
	class BtnVisualizarListener implements ActionListener {

		private ParametrosReporteContable locVentana;
		
		public BtnVisualizarListener(ParametrosReporteContable pVentana){
			locVentana = pVentana;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if (this.locVentana.validarDatos()) {
				locVentana.abrirVisualzarReporte();
			}
		}
		
	}
	
	class BtnCancelarListener implements ActionListener {
		
		private ParametrosReporteContable locVentana;
		
		public BtnCancelarListener(ParametrosReporteContable pVentana){
			locVentana = pVentana;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			locVentana.dispose();
		}
	}
}
