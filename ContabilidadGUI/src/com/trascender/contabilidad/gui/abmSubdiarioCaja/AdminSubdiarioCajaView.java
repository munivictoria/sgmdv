package com.trascender.contabilidad.gui.abmSubdiarioCaja;

import java.text.ParseException;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.text.MaskFormatter;

import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.abmStandard.AdminView;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.Util;

public class AdminSubdiarioCajaView extends AdminView {

	private static final long serialVersionUID = -4800973723406897636L;
	
	private SubdiarioCajaBusquedaModel busquedaModel;
	private SubdiarioCajaTableModel tableModel;
	
	private JLabel lblFechaCreacionDesde;
	private JFormattedTextField ftfFechaCreacionDesde;
	private JLabel lblFechaCreacionHasta;
	private JFormattedTextField ftfFechaCreacionHasta;
	private JLabel lblTipo;
	private JComboBox cbTipo;
	
	private static final String NOMBRE_RECURSO = "AdminSubdiarioCaja";
	private MaskFormatter formatter;
	
	public AdminSubdiarioCajaView(JFrame owner) {
		super(owner);
		this.init();
	}
	
	public AdminSubdiarioCajaView(JDialog owner) {
		super(owner);
		this.init();
	}
	
	private void init() {
		int numFila = -1;
		
		numFila++;
		this.lblFechaCreacionDesde = new TLabel();
		this.lblFechaCreacionDesde.setText(MessagesContabilidad.getString(NOMBRE_RECURSO+".lblFechaCreacionDesde"));
		this.lblFechaCreacionDesde.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblFechaCreacionDesde);
		
		try {
			this.formatter = new MaskFormatter("##/##/####");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.ftfFechaCreacionDesde = new JFormattedTextField(this.formatter);
		this.ftfFechaCreacionDesde.setBounds(Util.getBoundsColumnaInputTextFieldFechaDesde(numFila));
		this.getPnlBusqueda().add(this.ftfFechaCreacionDesde);
		
		numFila++;
		this.lblFechaCreacionHasta = new TLabel();
		this.lblFechaCreacionHasta.setText(MessagesContabilidad.getString(NOMBRE_RECURSO+".lblFechaCreacionHasta"));
		this.lblFechaCreacionHasta.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblFechaCreacionHasta);
		
		this.ftfFechaCreacionHasta = new JFormattedTextField(this.formatter);
		this.ftfFechaCreacionHasta.setBounds(Util.getBoundsColumnaInputTextFieldFechaDesde(numFila));
		this.getPnlBusqueda().add(this.ftfFechaCreacionHasta);
		
		numFila++;
		this.lblTipo = new TLabel();
		this.lblTipo.setText(MessagesContabilidad.getString(NOMBRE_RECURSO+".lblTipo"));
		this.lblTipo.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblTipo);
		
		this.cbTipo = new JComboBox();
		this.cbTipo.setBounds(Util.getBoundsColumnaInputComboBox(numFila));
		this.getPnlBusqueda().add(this.cbTipo);
		
		numFila++;
		this.setPosicionPanelBotonesBusqueda(numFila);
		this.getPnlBusqueda().add(this.getPnlBotonesBusqueda());
		
		this.setTituloVentana();
		this.setDescripcionVentana();
		this.setTamanioPanelBusqueda(numFila+1);
		this.setTamanioPosicionVentana();
	}
	
	@Override
	public void setDescripcionVentana() {
		this.getPnlCabecera().getLblDescripcion().setText(MessagesContabilidad.getString(NOMBRE_RECURSO+".descripcion"));
	}

	@Override
	public void setTituloVentana() {
		String locTitulo = MessagesContabilidad.getString(NOMBRE_RECURSO+".titulo");
		this.setTitle(locTitulo);
		this.getPnlCabecera().getLblTitulo().setText(locTitulo);
	}

	public SubdiarioCajaBusquedaModel getBusquedaModel() {
		return busquedaModel;
	}

	public void setBusquedaModel(SubdiarioCajaBusquedaModel busquedaModel) {
		this.busquedaModel = busquedaModel;
	}

	public SubdiarioCajaTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(SubdiarioCajaTableModel tableModel) {
		this.tableModel = tableModel;
		this.getPnlTabla().getTblDatos().setModel(tableModel);
	}

	public JComboBox getCbTipo() {
		return cbTipo;
	}

	public JFormattedTextField getFtfFechaCreacionDesde() {
		return ftfFechaCreacionDesde;
	}

	public JFormattedTextField getFtfFechaCreacionHasta() {
		return ftfFechaCreacionHasta;
	}

	public JLabel getLblFechaCreacionDesde() {
		return lblFechaCreacionDesde;
	}

	public JLabel getLblFechaCreacionHasta() {
		return lblFechaCreacionHasta;
	}

	public JLabel getLblTipo() {
		return lblTipo;
	}
	
}
