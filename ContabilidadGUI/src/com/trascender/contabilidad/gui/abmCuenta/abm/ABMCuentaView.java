package com.trascender.contabilidad.gui.abmCuenta.abm;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.trascender.contabilidad.gui.abmCuenta.CuentaABMModel;
import com.trascender.contabilidad.gui.abmCuenta.TipoCuentaTableModel;
import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.abmStandard.ABMView;
import com.trascender.gui.framework.abmStandard.PnlABMAtributos;
import com.trascender.gui.framework.abmStandard.PnlTabla;
import com.trascender.gui.framework.abmStandard.PnlVerticalBotones;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.ConstantesPosicion;
import com.trascender.gui.framework.util.ConstantesSeparacion;
import com.trascender.gui.framework.util.ConstantesTamanio;
import com.trascender.gui.framework.util.Util;

public abstract class ABMCuentaView extends ABMView {
	
	private static final long serialVersionUID = 798268371265231187L;

	private CuentaABMModel abmModel;
	
	private JLabel lblCuentaPadre;
	private JTextField tfCuentaPadre;
	private JLabel lblCodigoImputacion;
	private JTextField tfCodigoImputacion;
	private JLabel lblNombre;
	private JTextField tfNombre;
	private JLabel lblAbreviatura;
	private JTextField tfAbreviatura;
//	private JLabel lblTipoCuenta;
//	private JTextField tfTipoCuenta;
//	private PnlBotonesSeleccion pnlBotonesSeleccionTipoCuenta;
	private JLabel lblArea;
	private JComboBox cbArea;
	private PnlTabla pnlTablaTipoCuenta;
	private JPanel pnlTablas;
	private PnlVerticalBotones pnlBtnTablaTipoCuenta;
	private PnlABMAtributos pnlAtributos;
	private JPanel pnlContenedorTipoCuenta;
	private JLabel lblTablaTipoCuenta;
	private TipoCuentaTableModel tableModelTipoCuenta;
	
	
	private final String NOMBRE_RECURSO = "ABMCuenta";
	
	
	public ABMCuentaView(JFrame pFrame) {
		super(pFrame);
		this.init();
	}
	
	public ABMCuentaView(JDialog pDialog) {
		super(pDialog);
		this.init();
	}
	
	
	private void init() {
		this.getPnlCuerpo().setLayout(new BorderLayout());
		
		int numFila = -1;
		
		this.pnlAtributos = new PnlABMAtributos();
		this.pnlTablaTipoCuenta = new PnlTabla();
		this.pnlTablas = new JPanel();
		this.pnlTablas.setLayout(new BoxLayout(this.pnlTablas, BoxLayout.Y_AXIS));
		
		numFila++;
		this.lblCuentaPadre = new TLabel();
		this.lblCuentaPadre.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO + ".lblCuentaPadre"));
		this.lblCuentaPadre.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.pnlAtributos.add(this.lblCuentaPadre);
		
		this.tfCuentaPadre = new JTextField();
		this.tfCuentaPadre.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.tfCuentaPadre.setEditable(false);
		this.pnlAtributos.add(this.tfCuentaPadre);
		
		numFila++;
		this.lblCodigoImputacion = new TLabel();
		this.lblCodigoImputacion.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblCodigoImputacion"));
		this.lblCodigoImputacion.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.pnlAtributos.add(this.lblCodigoImputacion);
		
		this.tfCodigoImputacion = new JTextField();
		this.tfCodigoImputacion.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.pnlAtributos.add(this.tfCodigoImputacion);
		
		numFila++;
		this.lblNombre = new TLabel();
		this.lblNombre.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblNombre"));
		this.lblNombre.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.pnlAtributos.add(this.lblNombre);
		
		this.tfNombre = new JTextField();
		this.tfNombre.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.pnlAtributos.add(this.tfNombre);
		
		numFila++;
		this.lblAbreviatura = new TLabel();
		this.lblAbreviatura.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblAbreviatura"));
		this.lblAbreviatura.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.pnlAtributos.add(this.lblAbreviatura);
		
		this.tfAbreviatura = new JTextField();
		this.tfAbreviatura.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.pnlAtributos.add(this.tfAbreviatura);
		
		numFila++;
		this.lblArea = new TLabel();
		this.lblArea.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblArea"));
		this.lblArea.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.pnlAtributos.add(this.lblArea);
		
		this.cbArea = new JComboBox();
		this.cbArea.setBounds(Util.getBoundsColumnaInputComboBox(numFila));
		this.pnlAtributos.add(this.cbArea);
		
		this.pnlContenedorTipoCuenta = new JPanel();
		this.pnlContenedorTipoCuenta.setLayout(new BorderLayout());
		this.lblTablaTipoCuenta = new JLabel();
		this.lblTablaTipoCuenta.setText("Tipos de Cuenta");
		this.pnlTablaTipoCuenta = new PnlTabla();
		this.pnlTablaTipoCuenta.setPreferredSize(new Dimension(100,100));
		
		this.pnlBtnTablaTipoCuenta = new PnlVerticalBotones();
		this.getPnlContenedorTipoCuenta().add(this.lblTablaTipoCuenta, BorderLayout.NORTH);
		this.getPnlContenedorTipoCuenta().add(this.pnlBtnTablaTipoCuenta, BorderLayout.EAST);
		this.getPnlContenedorTipoCuenta().add(this.pnlTablaTipoCuenta, BorderLayout.CENTER);
		
		this.getPnlTablas().add(this.getPnlContenedorTipoCuenta());
		
		this.pnlAtributos.setPreferredSize(new Dimension(
				ConstantesPosicion.COLUMN_LBL_POS_INI_X*2+ConstantesTamanio.TF_WIDTH+ConstantesPosicion.COLUMN_INPUT_POS_INI_X, 
				ConstantesSeparacion.INCREMENTO_Y*2+ConstantesTamanio.CBX_HEIGHT*numFila+ConstantesPosicion.COLUMN_LBL_POS_INI_Y));
		
		this.getPnlCuerpo().add(this.pnlAtributos, BorderLayout.NORTH);
		this.getPnlCuerpo().add(this.getPnlTablas(), BorderLayout.CENTER);
		
		this.setTamanioPosicionVentana(numFila + 1);
	}
	
	@Override
	public void setTamanioPosicionVentana(int pCantidadFilasComponentes) {
		this.setSize(new Dimension(
				ConstantesPosicion.COLUMN_LBL_POS_INI_X * 2 +
				ConstantesPosicion.COLUMN_INPUT_POS_INI_X +
				ConstantesTamanio.TF_WIDTH +
				ConstantesSeparacion.SEPARADOR_HORIZONTAL + 
				ConstantesTamanio.PNL_BOTONES_SELECCION_WIDTH,600));
		this.setLocationRelativeTo(null);
	}

	public JTextField getTfAbreviatura() {
		return tfAbreviatura;
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

	public JTextField getTfCodigoImputacion() {
		return tfCodigoImputacion;
	}

	public JTextField getTfNombre() {
		return tfNombre;
	}

	public CuentaABMModel getAbmModel() {
		return abmModel;
	}

	public void setAbmModel(CuentaABMModel abmModel) {
		this.abmModel = abmModel;
	}

	public JTextField getTfCuentaPadre() {
		return tfCuentaPadre;
	}

	public JLabel getLblCuentaPadre() {
		return lblCuentaPadre;
	}

	public JLabel getLblArea() {
		return lblArea;
	}

	public JComboBox getCbArea() {
		return cbArea;
	}
	
	public TipoCuentaTableModel getTableModelTipoCuenta() {
		return tableModelTipoCuenta;
	}

	public void setTableModelTipoCuenta(TipoCuentaTableModel tableModelTipoCuenta) {
		this.getPnlTablaTipoCuenta().getTblDatos().setModel(tableModelTipoCuenta);
		this.tableModelTipoCuenta = tableModelTipoCuenta;
	}

	public PnlTabla getPnlTablaTipoCuenta() {
		return pnlTablaTipoCuenta;
	}

	public JPanel getPnlTablas() {
		return pnlTablas;
	}

	public PnlVerticalBotones getPnlBtnTablaTipoCuenta() {
		return pnlBtnTablaTipoCuenta;
	}

	public PnlABMAtributos getPnlAtributos() {
		return pnlAtributos;
	}

	public JPanel getPnlContenedorTipoCuenta() {
		return pnlContenedorTipoCuenta;
	}

	public JLabel getLblTablaTipoCuenta() {
		return lblTablaTipoCuenta;
	}
	
}
