package com.trascender.contabilidad.gui.abmCuentaBancaria;

import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.abmStandard.AdminView;
import com.trascender.gui.framework.abmStandard.PnlBotonesSeleccion;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.Util;

public class AdminCuentaBancariaView extends AdminView {

	private static final long serialVersionUID = 5513255615940373091L;

	private CuentaBancariaBusquedaModel busquedaModel;
	private CuentaBancariaTableModel tableModel;
	
	private JLabel lblTipoCuenta;
	private JTextField tfTipoCuenta;
	private JLabel lblNumero;
	private JTextField tfNumero;
	private JLabel lblPropia;
	private JCheckBox chkPropia;
	private JLabel lblBanco;
	private JTextField tfBanco;
	private PnlBotonesSeleccion pnlBotonesSeleccion;
	
	private final String NOMBRE_RECURSO = "AdminCuentaBancaria";
	
	public AdminCuentaBancariaView(JFrame owner) {
		super(owner);
		this.init();
	}

	public AdminCuentaBancariaView(JDialog owner) {
		super(owner);
		this.init();
	}
	
	private void init() {
		int numFila = -1;
		
		numFila++;
		this.lblTipoCuenta = new TLabel();
		this.lblTipoCuenta.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblTipoCuenta"));
		this.lblTipoCuenta.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblTipoCuenta);
		
		this.tfTipoCuenta = new JTextField();
		this.tfTipoCuenta.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlBusqueda().add(this.tfTipoCuenta);

		numFila++;
		this.lblNumero = new TLabel();
		this.lblNumero.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblNumero"));
		this.lblNumero.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblNumero);
		
		this.tfNumero = new JTextField();
		this.tfNumero.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlBusqueda().add(this.tfNumero);
		
		numFila++;
		this.lblPropia = new TLabel();
		this.lblPropia.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblPropia"));
		this.lblPropia.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblPropia);
		
		this.chkPropia = new JCheckBox();
		this.chkPropia.setBounds(Util.getBoundsColumnaCheckBox(numFila));
		this.getPnlBusqueda().add(this.chkPropia);
		
		numFila++;
		this.lblBanco = new TLabel();
		this.lblBanco.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblBanco"));
		this.lblBanco.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblBanco);
		
		this.tfBanco = new JTextField();
		this.tfBanco.setEditable(false);
		this.tfBanco.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlBusqueda().add(this.tfBanco);
		
		this.pnlBotonesSeleccion = new PnlBotonesSeleccion();
		this.pnlBotonesSeleccion.setBounds(Util.getBoundsColumnaSeleccion(numFila));
		this.getPnlBusqueda().add(this.pnlBotonesSeleccion);
		
		// PANEL CON BOTONES BUSCAR+REINICIAR
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
		this.getPnlCabecera().getLblDescripcion().setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO + ".descripcion"));
		
	}

	@Override
	public void setTituloVentana() {
		String titulo = MessagesContabilidad.getString(this.NOMBRE_RECURSO + ".titulo");
		this.setTitle(titulo);
		this.getPnlCabecera().getLblTitulo().setText(titulo);
	}
	public CuentaBancariaBusquedaModel getBusquedaModel() {
		return busquedaModel;
	}

	public void setBusquedaModel(CuentaBancariaBusquedaModel busquedaModel) {
		this.busquedaModel = busquedaModel;
	}

	public CuentaBancariaTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(CuentaBancariaTableModel tableModel) {
		this.tableModel = tableModel;
		this.getPnlTabla().getTblDatos().setModel(tableModel);
	}

	public JCheckBox getChkPropia() {
		return chkPropia;
	}

	public JLabel getLblBanco() {
		return lblBanco;
	}

	public JLabel getLblNumero() {
		return lblNumero;
	}

	public JLabel getLblPropia() {
		return lblPropia;
	}

	public JLabel getLblTipoCuenta() {
		return lblTipoCuenta;
	}

	public PnlBotonesSeleccion getPnlBotonesSeleccion() {
		return pnlBotonesSeleccion;
	}

	public JTextField getTfBanco() {
		return tfBanco;
	}

	public JTextField getTfNumero() {
		return tfNumero;
	}

	public JTextField getTfTipoCuenta() {
		return tfTipoCuenta;
	}

}
