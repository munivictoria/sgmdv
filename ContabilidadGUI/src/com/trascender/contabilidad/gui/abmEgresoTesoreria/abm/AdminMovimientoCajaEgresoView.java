package com.trascender.contabilidad.gui.abmEgresoTesoreria.abm;

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

public class AdminMovimientoCajaEgresoView extends AdminView {

	private static final long serialVersionUID = -2643417836269210365L;
	
	private MovimientoCajaEgresoBusquedaModel busquedaModel;
	private MovimientoCajaEgresoTableModel tableModel;

	private JLabel lblFechaDesde;
	private JFormattedTextField tfFechaDesde;
	private JLabel lblFechaHasta;
	private JFormattedTextField tfFechaHasta;
	
	private final String NOMBRE_RECURSO ="ABMEgresoTesoreria";
	private MaskFormatter formatter;
	
	public AdminMovimientoCajaEgresoView(JFrame owner) {
		super(owner);
		this.init();
		this.setVisibilidadBotones(false);
	}
	
	public AdminMovimientoCajaEgresoView(JDialog owner) {
		super(owner);
		this.init();
		this.setVisibilidadBotones(false);
	}
	
	private void setVisibilidadBotones(boolean b) {
		this.getPnlPie().getBtnSeleccionar().setEnabled(b);
		this.getPnlPie().getBtnAgregar().setVisible(b);
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

	
	public MovimientoCajaEgresoBusquedaModel getBusquedaModel() {
		return busquedaModel;
	}

	public void setBusquedaModel(MovimientoCajaEgresoBusquedaModel busquedaModel) {
		this.busquedaModel = busquedaModel;
	}

	public MovimientoCajaEgresoTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(MovimientoCajaEgresoTableModel tableModel) {
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


