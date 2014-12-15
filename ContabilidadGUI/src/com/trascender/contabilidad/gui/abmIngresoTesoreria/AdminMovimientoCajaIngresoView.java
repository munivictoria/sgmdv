package com.trascender.contabilidad.gui.abmIngresoTesoreria;

import java.awt.Color;
import java.text.ParseException;

import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.text.MaskFormatter;

import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.abmStandard.AdminView;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.Constantes;
import com.trascender.gui.framework.util.Util;

public class AdminMovimientoCajaIngresoView extends AdminView {

	private static final long serialVersionUID = -2643417836269210365L;
	
	private MovimientoCajaIngresoBusquedaModel busquedaModel;
	private MovimientoCajaIngresoTableModel tableModel;

	private JLabel lblFechaDesde;
	private JFormattedTextField tfFechaDesde;
	private JLabel lblFechaHasta;
	private JFormattedTextField tfFechaHasta;
	
	private final String NOMBRE_RECURSO ="ABMIngresoTesoreria";
	private MaskFormatter formatter;
	
	public AdminMovimientoCajaIngresoView(JFrame owner) {
		super(owner);
		this.init();
		this.setVisibilidadBotones(false);
	}
	
	public AdminMovimientoCajaIngresoView(JDialog owner) {
		super(owner);
		this.init();
		this.setVisibilidadBotones(false);
	}
	
	private void setVisibilidadBotones(boolean b) {
		this.getPnlPie().getBtnSeleccionar().setEnabled(b);
		this.getPnlPie().getBtnModificar().setVisible(b);
	}

	private void init() {
		int numFila = -1;
		
		numFila++;
		this.lblFechaDesde = new TLabel();
		this.lblFechaDesde.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblFechaDesde"));
		this.lblFechaDesde.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblFechaDesde);
		
		try {
			this.formatter = new MaskFormatter("##/##/####");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		this.tfFechaDesde = new JFormattedTextField(this.formatter);
		this.tfFechaDesde.setBounds(Util.getBoundsColumnaInputTextFieldFechaDesde(numFila));
		this.getPnlBusqueda().add(this.tfFechaDesde);
	
		numFila++;
		this.lblFechaHasta = new TLabel();
		this.lblFechaHasta.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblFechaHasta"));
		this.lblFechaHasta.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblFechaHasta);
		
		try {
			this.formatter = new MaskFormatter("##/##/####");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		this.tfFechaHasta = new JFormattedTextField(this.formatter);
		this.tfFechaHasta.setBounds(Util.getBoundsColumnaInputTextFieldFechaDesde(numFila));
		this.getPnlBusqueda().add(this.tfFechaHasta);
		
		numFila++;
		this.setPosicionPanelBotonesBusqueda(numFila);
		this.getPnlBusqueda().add(this.getPnlBotonesBusqueda());
		
		this.setTituloVentana();
		this.setDescripcionVentana();
		this.setColorFondo();
		this.setTextoBtnAceptar();
		this.setTextoBtnEliminar();
		this.setTextoBtnCerrar();
		this.setTamanioPanelBusqueda(numFila+1);
		this.setTamanioPosicionVentana();
		
	}	
	

	public JLabel getLblFechaDesde() {
		return lblFechaDesde;
	}

	public JLabel getLblFechaHasta() {
		return lblFechaHasta;
	}

	public JFormattedTextField getTfFechaDesde() {
		return tfFechaDesde;
	}

	public JFormattedTextField getTfFechaHasta() {
		return tfFechaHasta;
	}

	
	public MovimientoCajaIngresoBusquedaModel getBusquedaModel() {
		return busquedaModel;
	}

	public void setBusquedaModel(MovimientoCajaIngresoBusquedaModel busquedaModel) {
		this.busquedaModel = busquedaModel;
	}

	public MovimientoCajaIngresoTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(MovimientoCajaIngresoTableModel tableModel) {
		this.tableModel = tableModel;
		this.getPnlTabla().getTblDatos().setModel(tableModel);
	}

	public void setColorFondo() {
		Color color = Constantes.COLOR_CONSULTAR;
		this.getPnlCabecera().setBackground(color);
		this.getPnlPie().setBackground(color);
	}

	public void setDescripcionVentana() {
		this.getPnlCabecera().getLblDescripcion().setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".descripcion"));
	}

	public void setTextoBtnAceptar() {
		this.getPnlPie().getBtnAgregar().setText("A archivo");
		this.getPnlPie().getBtnAgregar().setToolTipText("Genera el archivo para exportar los ingresos a otro sistema contable");
	}
	
	public void setTextoBtnEliminar(){
		this.getPnlPie().getBtnEliminar().setText(MessagesContabilidad.getString("Application.btnImprimir"));
		this.getPnlPie().getBtnEliminar().setMnemonic(MessagesContabilidad.getChar("Application.btnImprimirMnemonic"));
		this.getPnlPie().getBtnEliminar().setToolTipText(MessagesContabilidad.getString("Application.btnImprimirToolTip"));
	}

	public void setTextoBtnCerrar() {
    	this.getPnlPie().getBtnCerrar().setText(MessagesContabilidad.getString("Application.btnCerrar"));
		this.getPnlPie().getBtnCerrar().setMnemonic(MessagesContabilidad.getChar("Application.btnCerrarMnemonic"));
		this.getPnlPie().getBtnCerrar().setToolTipText(MessagesContabilidad.getString("Application.btnCerrarToolTip"));
    }

	public void setTituloVentana() {
		String locTitulo = MessagesContabilidad.getString(NOMBRE_RECURSO + ".titulo");
		this.setTitle(locTitulo);
		this.getPnlCabecera().getLblTitulo().setText(locTitulo);
	}
	
}


