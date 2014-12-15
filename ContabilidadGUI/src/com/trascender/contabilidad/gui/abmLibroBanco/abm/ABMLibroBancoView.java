package com.trascender.contabilidad.gui.abmLibroBanco.abm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import com.trascender.contabilidad.gui.abmLibroBanco.LibroBancoABMModel;
import com.trascender.contabilidad.gui.abmLibroBanco.LineasLibroBancoTableModel;
import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.abmStandard.ABMView;
import com.trascender.gui.framework.abmStandard.PnlABMAtributos;
import com.trascender.gui.framework.abmStandard.PnlBotonesSeleccion;
import com.trascender.gui.framework.abmStandard.PnlTabla;
import com.trascender.gui.framework.abmStandard.PnlVerticalBotones;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.ConstantesPosicion;
import com.trascender.gui.framework.util.ConstantesSeparacion;
import com.trascender.gui.framework.util.ConstantesTamanio;
import com.trascender.gui.framework.util.Util;

public abstract class ABMLibroBancoView extends ABMView {

	private static final long serialVersionUID = 1L;

	private LibroBancoABMModel abmModel;
	private LineasLibroBancoTableModel abmTableModel;
	
	private PnlABMAtributos pnlAtributos;
	private PnlTabla pnlTabla;
	private PnlVerticalBotones pnlBotonesTabla;
	
	private JLabel lblNombre;
	private JTextField tfNombre;
	private JLabel lblCuentaBancaria;
	private JTextField tfCuentaBancaria;
	private PnlBotonesSeleccion pnlBotonesSeleccion;
	private JLabel lblFechaGeneracion;
	private JFormattedTextField tfFechaGeneracion;
	
	private JButton btnGenerarLibroBanco;
	
	private final String NOMBRE_RECURSO = "AdminLibroBanco";
	private MaskFormatter formatter;
	
	public ABMLibroBancoView(JFrame owner) {
		super(owner);
		this.init();
	}
	
	public ABMLibroBancoView(JDialog owner) {
		super(owner);
		this.init();
	}
	
	private void init() {
		this.getPnlCuerpo().setLayout(new BorderLayout());
		
		this.pnlAtributos = new PnlABMAtributos();
		this.pnlTabla = new PnlTabla();
		this.pnlBotonesTabla = new PnlVerticalBotones();
		
		int numFila = -1;

		numFila++;
		this.lblNombre = new TLabel();
		this.lblNombre.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblNombre"));
		this.lblNombre.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.pnlAtributos.add(this.lblNombre);

		this.tfNombre = new JTextField();
		this.tfNombre.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.pnlAtributos.add(this.tfNombre);
		
		numFila++;
		this.lblCuentaBancaria = new TLabel();
		this.lblCuentaBancaria.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblCuentaBancaria"));
		this.lblCuentaBancaria.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.pnlAtributos.add(this.lblCuentaBancaria);

		this.tfCuentaBancaria = new JTextField();
		this.tfCuentaBancaria.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.tfCuentaBancaria.setEditable(false);
		this.pnlAtributos.add(this.tfCuentaBancaria);
		
		this.pnlBotonesSeleccion = new PnlBotonesSeleccion();
		this.pnlBotonesSeleccion.setBounds(Util.getBoundsColumnaSeleccion(numFila));
		this.pnlAtributos.add(this.pnlBotonesSeleccion);
		
		numFila++;
		this.lblFechaGeneracion = new TLabel();
		this.lblFechaGeneracion.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblFechaGeneracion"));
		this.lblFechaGeneracion.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.pnlAtributos.add(this.lblFechaGeneracion);
		
		try {
			this.formatter = new MaskFormatter("##/##/####");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.tfFechaGeneracion = new JFormattedTextField(this.formatter);
		this.tfFechaGeneracion.setBounds(Util.getBoundsColumnaInputTextFieldFechaDesde(numFila));
		this.pnlAtributos.add(this.tfFechaGeneracion);
		
		numFila++;
		this.btnGenerarLibroBanco = new JButton();
		this.btnGenerarLibroBanco.setText("Generar");
		this.btnGenerarLibroBanco.setBounds(
				ConstantesPosicion.COLUMN_INPUT_POS_INI_X,
				ConstantesPosicion.COLUMN_INPUT_POS_INI_Y+numFila*ConstantesSeparacion.INCREMENTO_Y,
				ConstantesTamanio.BTN_WIDTH,
				ConstantesTamanio.BTN_HEIGHT);
		this.pnlAtributos.add(this.btnGenerarLibroBanco);
		
		this.pnlAtributos.setPreferredSize(new Dimension(
				ConstantesPosicion.COLUMN_LBL_POS_INI_X*2+ConstantesTamanio.TF_WIDTH+ConstantesPosicion.COLUMN_INPUT_POS_INI_X, 
				ConstantesSeparacion.INCREMENTO_Y*2+ConstantesTamanio.CBX_HEIGHT*numFila+ConstantesPosicion.COLUMN_LBL_POS_INI_Y));
		
		this.getPnlCuerpo().add(this.pnlAtributos, BorderLayout.NORTH);
		this.getPnlCuerpo().add(this.pnlTabla, BorderLayout.CENTER);
		this.getPnlCuerpo().add(this.pnlBotonesTabla, BorderLayout.EAST);
		
		setTamanioPosicionVentana(numFila + 1);
	}
	
	@Override
	public void setTamanioPosicionVentana(int pCantidadFilasComponentes) {
		this.setSize(new Dimension(700,600));
		this.setLocationRelativeTo(null);
		
	}

	public LibroBancoABMModel getAbmModel() {
		return abmModel;
	}

	public void setAbmModel(LibroBancoABMModel abmModel) {
		this.abmModel = abmModel;
	}

	public JLabel getLblCuentaBancaria() {
		return lblCuentaBancaria;
	}

	public JLabel getLblNombre() {
		return lblNombre;
	}

	public PnlBotonesSeleccion getPnlBotonesSeleccion() {
		return pnlBotonesSeleccion;
	}

	public JTextField getTfCuentaBancaria() {
		return tfCuentaBancaria;
	}

	public JTextField getTfNombre() {
		return tfNombre;
	}

	public LineasLibroBancoTableModel getAbmTableModel() {
		return abmTableModel;
	}

	public void setAbmTableModel(LineasLibroBancoTableModel abmTableModel) {
		this.abmTableModel = abmTableModel;
		this.getPnlTabla().getTblDatos().setModel(abmTableModel);
	}

	public PnlABMAtributos getPnlAtributos() {
		return pnlAtributos;
	}

	public PnlTabla getPnlTabla() {
		return pnlTabla;
	}

	public PnlVerticalBotones getPnlBotonesTabla() {
		return pnlBotonesTabla;
	}

	public JFormattedTextField getTfFechaGeneracion() {
		return tfFechaGeneracion;
	}

	public JButton getBtnGenerarLibroBanco() {
		return btnGenerarLibroBanco;
	}

	public JLabel getLblFechaGeneracion() {
		return lblFechaGeneracion;
	}
	
}
