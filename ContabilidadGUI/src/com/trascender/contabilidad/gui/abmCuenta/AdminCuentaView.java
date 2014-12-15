package com.trascender.contabilidad.gui.abmCuenta;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.abmStandard.AdminView;
import com.trascender.gui.framework.abmStandard.PnlBotonesSeleccion;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.Util;

public class AdminCuentaView extends AdminView {
	
	private static final long serialVersionUID = 5393088222475928804L;
	
	private CuentaBusquedaModel busquedaModel;
	private CuentaTableModel tableModel;
	
	private JLabel lblCodigoImputacion;
	private JTextField tfCodigoImputacion;
	private JLabel lblNombre;
	private JTextField tfNombre;
	private JLabel lblAbreviatura;
	private JTextField tfAbreviatura;
	private JLabel lblTipoCuenta;
	private JTextField tfTipoCuenta;
	private PnlBotonesSeleccion pnlBotonesSeleccionTipoCuenta;
	private JLabel lblPlanDeCuenta;
	private JTextField tfPlanDeCuenta;
	private PnlBotonesSeleccion pnlBotonesSeleccionPlanDeCuenta;
	private JLabel lblArea;
	private JComboBox cbArea;
	
	private final String NOMBRE_RECURSO = "AdminCuenta";
	
	public AdminCuentaView(JFrame owner) {
		super(owner);
		this.init();
	}
	
	public AdminCuentaView(JDialog owner) {
		super(owner);
		this.init();
	}
	
	private void init() {
		int numFila = -1;
		
		numFila++;
		this.lblCodigoImputacion = new TLabel();
		this.lblCodigoImputacion.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblCodigoImputacion"));
		this.lblCodigoImputacion.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblCodigoImputacion);
		
		this.tfCodigoImputacion = new JTextField();
		this.tfCodigoImputacion.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlBusqueda().add(this.tfCodigoImputacion);
		
		numFila++;
		this.lblNombre = new TLabel();
		this.lblNombre.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblNombre"));
		this.lblNombre.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblNombre);
		
		this.tfNombre = new JTextField();
		this.tfNombre.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlBusqueda().add(this.tfNombre);
		
		numFila++;
		this.lblAbreviatura = new TLabel();
		this.lblAbreviatura.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblAbreviatura"));
		this.lblAbreviatura.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblAbreviatura);
		
		this.tfAbreviatura = new JTextField();
		this.tfAbreviatura.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlBusqueda().add(this.tfAbreviatura);
		
		numFila++;
		this.lblTipoCuenta = new TLabel();
		this.lblTipoCuenta.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblTipoCuenta"));
		this.lblTipoCuenta.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblTipoCuenta);
		
		this.tfTipoCuenta = new JTextField();
		this.tfTipoCuenta.setEditable(false);
		this.tfTipoCuenta.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlBusqueda().add(this.tfTipoCuenta);
		
		this.pnlBotonesSeleccionTipoCuenta = new PnlBotonesSeleccion();
		this.pnlBotonesSeleccionTipoCuenta.setBounds(Util.getBoundsColumnaSeleccion(numFila));
		this.getPnlBusqueda().add(this.pnlBotonesSeleccionTipoCuenta);
		
		numFila++;
		this.lblPlanDeCuenta = new TLabel();
		this.lblPlanDeCuenta.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblPlanDeCuenta"));
		this.lblPlanDeCuenta.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblPlanDeCuenta);
		
		this.tfPlanDeCuenta = new JTextField();
		this.tfPlanDeCuenta.setEditable(false);
		this.tfPlanDeCuenta.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlBusqueda().add(this.tfPlanDeCuenta);
		
		this.pnlBotonesSeleccionPlanDeCuenta = new PnlBotonesSeleccion();
		this.pnlBotonesSeleccionPlanDeCuenta.setBounds(Util.getBoundsColumnaSeleccion(numFila));
		this.getPnlBusqueda().add(this.pnlBotonesSeleccionPlanDeCuenta);
		
		numFila++;
		this.lblArea = new TLabel();
		this.lblArea.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblArea"));
		this.lblArea.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblArea);
		
		this.cbArea = new JComboBox();
		this.cbArea.setBounds(Util.getBoundsColumnaInputComboBox(numFila));
		this.getPnlBusqueda().add(this.cbArea);
		
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
		this.getPnlCabecera().getLblDescripcion().setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".descripcion"));
	}

	@Override
	public void setTituloVentana() {
		String titulo = MessagesContabilidad.getString(this.NOMBRE_RECURSO+".titulo");
		this.setTitle(titulo);
		this.getPnlCabecera().getLblTitulo().setText(titulo);
	}

	public CuentaBusquedaModel getBusquedaModel() {
		return busquedaModel;
	}

	public void setBusquedaModel(CuentaBusquedaModel busquedaModel) {
		this.busquedaModel = busquedaModel;
	}

	public CuentaTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(CuentaTableModel tableModel) {
		this.tableModel = tableModel;
		this.getPnlTabla().getTblDatos().setModel(tableModel);
	}

	public JLabel getLblAbreviatura() {
		return lblAbreviatura;
	}

	public JLabel getLblCodigoImputacion() {
		return lblCodigoImputacion;
	}

	public JLabel getLblNombre() {
		return lblNombre;
	}

	public JLabel getLblPlanDeCuenta() {
		return lblPlanDeCuenta;
	}

	public JLabel getLblTipoCuenta() {
		return lblTipoCuenta;
	}

	public PnlBotonesSeleccion getPnlBotonesSeleccionPlanDeCuenta() {
		return pnlBotonesSeleccionPlanDeCuenta;
	}

	public PnlBotonesSeleccion getPnlBotonesSeleccionTipoCuenta() {
		return pnlBotonesSeleccionTipoCuenta;
	}

	public JTextField getTfAbreviatura() {
		return tfAbreviatura;
	}

	public JTextField getTfCodigoImputacion() {
		return tfCodigoImputacion;
	}

	public JTextField getTfNombre() {
		return tfNombre;
	}

	public JTextField getTfPlanDeCuenta() {
		return tfPlanDeCuenta;
	}

	public JTextField getTfTipoCuenta() {
		return tfTipoCuenta;
	}

	public JLabel getLblArea() {
		return lblArea;
	}

	public JComboBox getCbArea() {
		return cbArea;
	}
	
}
