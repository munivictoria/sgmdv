package com.trascender.contabilidad.gui.abmAsientoContable;

import java.text.ParseException;

import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.text.MaskFormatter;

import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.abmStandard.AdminView;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.Util;

public class AdminAsientoContableView extends AdminView {

	private static final long serialVersionUID = 7951248440279552301L;
	
	private AsientoContableBusquedaModel busquedaModel;
	private AsientoContableTableModel tableModel;
	
	private JLabel lblNumeroAsiento;
	private JTextField tfNumeroAsiento;
	private JLabel lblFechaDesdeHasta;
	private JFormattedTextField ftfFechaDesde;
	private JFormattedTextField ftfFechaHasta;
	
	private MaskFormatter formatter;
	private final String NOMBRE_RECURSO = "AdminAsientoContable";
	
	public AdminAsientoContableView(JFrame owner) {
		super(owner);
		this.init();
	}
	
	public AdminAsientoContableView(JDialog owner) {
		super(owner);
		this.init();
	}
	
	private void init() {
		int numFila = -1;
		
		numFila++;
		this.lblNumeroAsiento = new TLabel();
		this.lblNumeroAsiento.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblNumeroAsiento"));
		this.lblNumeroAsiento.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblNumeroAsiento);
		
		this.tfNumeroAsiento = new JTextField();
		this.tfNumeroAsiento.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlBusqueda().add(this.tfNumeroAsiento);
		
		numFila++;
		this.lblFechaDesdeHasta = new TLabel();
		this.lblFechaDesdeHasta.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblFechaDesdeHasta"));
		this.lblFechaDesdeHasta.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblFechaDesdeHasta);
		
		try {
			this.formatter = new MaskFormatter("##/##/####");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.ftfFechaDesde = new JFormattedTextField(this.formatter);
		this.ftfFechaDesde.setBounds(Util.getBoundsColumnaInputTextFieldFechaDesde(numFila));
		this.getPnlBusqueda().add(this.ftfFechaDesde);
		
		this.ftfFechaHasta = new JFormattedTextField(this.formatter);
		this.ftfFechaHasta.setBounds(Util.getBoundsColumnaInputTextFieldFechaHasta(numFila));
		this.getPnlBusqueda().add(this.ftfFechaHasta);
		
		numFila++;
		this.setPosicionPanelBotonesBusqueda(numFila);
		this.getPnlBusqueda().add(this.getPnlBotonesBusqueda());
		
		this.getPnlTabla().getTblDatos().setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
		
		this.setTituloVentana();
		this.setDescripcionVentana();
		this.setTamanioPanelBusqueda(numFila + 1);
		this.setTamanioPosicionVentana();
	}
	
	@Override
	public void setDescripcionVentana() {
		this.getPnlCabecera().getLblDescripcion().setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".descripcion"));
	}

	@Override
	public void setTituloVentana() {
		String locTitulo= MessagesContabilidad.getString(NOMBRE_RECURSO + ".titulo");
		this.setTitle(locTitulo);
		this.getPnlCabecera().getLblTitulo().setText(locTitulo);
	}

	public AsientoContableBusquedaModel getBusquedaModel() {
		return busquedaModel;
	}

	public void setBusquedaModel(AsientoContableBusquedaModel busquedaModel) {
		this.busquedaModel = busquedaModel;
	}

	public AsientoContableTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(AsientoContableTableModel tableModel) {
		this.tableModel = tableModel;
		this.getPnlTabla().getTblDatos().setModel(tableModel);
	}

	public JFormattedTextField getFtfFechaDesde() {
		return ftfFechaDesde;
	}

	public JFormattedTextField getFtfFechaHasta() {
		return ftfFechaHasta;
	}

	public JLabel getLblFechaDesdeHasta() {
		return lblFechaDesdeHasta;
	}

	public JLabel getLblNumeroAsiento() {
		return lblNumeroAsiento;
	}

	public JTextField getTfNumeroAsiento() {
		return tfNumeroAsiento;
	}
	
	

}
