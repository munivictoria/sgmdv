package com.trascender.contabilidad.gui.abmBalance;

import java.text.ParseException;

import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.abmStandard.AdminView;
import com.trascender.gui.framework.abmStandard.PnlBotonesSeleccion;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.Util;

public class AdminBalanceView extends AdminView {

	private static final long serialVersionUID = 5011078781064412403L;
	
	private BalanceBusquedaModel busquedaModel;
	private BalanceTableModel tableModel;
	
	private JLabel lblNombre;
	private JTextField tfNombre;
	private JLabel lblFecha;
	private JFormattedTextField ftfFechaDesde;
	private JFormattedTextField ftfFechaHasta;
	private JLabel lblTipoBalance;
	private JTextField tfTipoBalance;
	private PnlBotonesSeleccion pnlBotonesSeleccionTipoBalance;
	
	private final String NOMBRE_RECURSO = "AdminBalance";
	
	private MaskFormatter formatter;
	
	public AdminBalanceView(JFrame owner) {
		super(owner);
		this.init();
	}
	
	public AdminBalanceView(JDialog owner) {
		super(owner);
		this.init();
	}
	
	private void init() {
		int numFila = -1;
		
		numFila++;
		this.lblNombre = new TLabel();
		this.lblNombre.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblNombre"));
		this.lblNombre.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblNombre);
		
		this.tfNombre = new JTextField();
		this.tfNombre.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlBusqueda().add(this.tfNombre);
		
		numFila++;
		this.lblFecha = new TLabel();
		this.lblFecha.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblFecha"));
		this.lblFecha.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblFecha);
		
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
		this.lblTipoBalance = new TLabel();
		this.lblTipoBalance.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblTipoBalance"));
		this.lblTipoBalance.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblTipoBalance);
		
		this.tfTipoBalance = new JTextField();
		this.tfTipoBalance.setEditable(false);
		this.tfTipoBalance.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlBusqueda().add(this.tfTipoBalance);
		
		this.pnlBotonesSeleccionTipoBalance = new PnlBotonesSeleccion();
		this.pnlBotonesSeleccionTipoBalance.setBounds(Util.getBoundsColumnaSeleccion(numFila));
		this.getPnlBusqueda().add(this.pnlBotonesSeleccionTipoBalance);
		
		numFila++;
		this.setPosicionPanelBotonesBusqueda(numFila);
		this.getPnlBusqueda().add(this.getPnlBotonesBusqueda());
		
		this.setTituloVentana();
		this.setDescripcionVentana();
		this.setTamanioPanelBusqueda(numFila + 1);
		this.setTamanioPosicionVentana();
	}
	
	@Override
	public void setDescripcionVentana() {
		this.getPnlCabecera().getLblDescripcion().setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".descripcion"));
	}

	@Override
	public void setTituloVentana() {
		String titulo = MessagesContabilidad.getString(this.NOMBRE_RECURSO+".titulo");
		this.setTitle(titulo);
		this.getPnlCabecera().getLblTitulo().setText(titulo);
	}

	public BalanceBusquedaModel getBusquedaModel() {
		return busquedaModel;
	}

	public void setBusquedaModel(BalanceBusquedaModel busquedaModel) {
		this.busquedaModel = busquedaModel;
	}

	public BalanceTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(BalanceTableModel tableModel) {
		this.tableModel = tableModel;
		this.getPnlTabla().getTblDatos().setModel(tableModel);
	}

	public JFormattedTextField getFtfFechaDesde() {
		return ftfFechaDesde;
	}

	public JFormattedTextField getFtfFechaHasta() {
		return ftfFechaHasta;
	}

	public JLabel getLblFecha() {
		return lblFecha;
	}

	public JLabel getLblNombre() {
		return lblNombre;
	}

	public JLabel getLblTipoBalance() {
		return lblTipoBalance;
	}

	public PnlBotonesSeleccion getPnlBotonesSeleccionTipoBalance() {
		return pnlBotonesSeleccionTipoBalance;
	}

	public JTextField getTfNombre() {
		return tfNombre;
	}

	public JTextField getTfTipoBalance() {
		return tfTipoBalance;
	}

}
