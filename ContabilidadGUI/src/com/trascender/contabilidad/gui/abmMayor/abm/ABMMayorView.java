package com.trascender.contabilidad.gui.abmMayor.abm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import com.trascender.contabilidad.gui.abmMayor.MayorABMModel;
import com.trascender.contabilidad.gui.abmMayor.MayorTableModel;
import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.abmStandard.ABMView;
import com.trascender.gui.framework.abmStandard.PnlABMAtributos;
import com.trascender.gui.framework.abmStandard.PnlBotonesSeleccion;
import com.trascender.gui.framework.abmStandard.PnlTabla;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.ConstantesPosicion;
import com.trascender.gui.framework.util.ConstantesSeparacion;
import com.trascender.gui.framework.util.ConstantesTamanio;
import com.trascender.gui.framework.util.Util;

public abstract class ABMMayorView extends ABMView {

	private static final long serialVersionUID = 8310525708483052811L;
	private MayorABMModel abmModel;
	private MayorTableModel tableModel;
	
	private PnlABMAtributos pnlAtributos;
	private PnlTabla pnlTabla;
	
	private JLabel lblMes;
	private JTextField tfMes;
	private JLabel lblAnio;
	private JTextField tfAnio;
	private JLabel lblCuenta;
	private JTextField tfCuenta;
	private JLabel lblTipo;
	private JComboBox cbTipo;
	private PnlBotonesSeleccion pnlBotonesSeleccionCuenta;
	private MaskFormatter formatter;
	
	private JButton btnGenerarMayor;
	
	private static final String NOMBRE_RECURSO = "ABMMayor";

	public ABMMayorView(JFrame owner) {
		super(owner);
		this.init();
	}

	public ABMMayorView(JDialog owner) {
		super(owner);
		this.init();
	}

	private void init() {
		this.getPnlCuerpo().setLayout(new BorderLayout());

		this.pnlAtributos = new PnlABMAtributos();
		this.pnlTabla = new PnlTabla();

		int numFila = -1;

		numFila++;
		this.lblMes = new TLabel();
		this.lblMes.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblMes"));
		this.lblMes.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlAtributos().add(this.lblMes);

		try {
			this.formatter = new MaskFormatter("##");
			formatter.setPlaceholderCharacter('0');
		} catch (ParseException e) {
			e.printStackTrace();
		}

		
		this.tfMes = new JFormattedTextField(this.formatter);
		this.tfMes.setBounds(Util.getBoundsColumnaInputTextFieldFechaDesde(numFila));
		this.getPnlAtributos().add(this.tfMes);

		numFila++;
		this.lblAnio = new TLabel();
		this.lblAnio.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblAnio"));
		this.lblAnio.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlAtributos().add(this.lblAnio);

		this.tfAnio = new JTextField();
		this.tfAnio.setBounds(Util.getBoundsColumnaInputTextFieldFechaDesde(numFila));
		this.getPnlAtributos().add(this.tfAnio);

		numFila++;
		this.lblCuenta = new TLabel();
		this.lblCuenta.setText(MessagesContabilidad.getString(NOMBRE_RECURSO
				+ ".lblCuenta"));
		this.lblCuenta.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlAtributos().add(this.lblCuenta);

		this.tfCuenta = new JTextField();
		this.tfCuenta.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlAtributos().add(this.tfCuenta);

		this.pnlBotonesSeleccionCuenta = new PnlBotonesSeleccion();
		this.pnlBotonesSeleccionCuenta.setBounds(Util
				.getBoundsColumnaSeleccion(numFila));
		this.getPnlAtributos().add(this.pnlBotonesSeleccionCuenta);
		
		numFila++;
		this.lblTipo = new TLabel();
		this.lblTipo.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblTipo"));
		this.lblTipo.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlAtributos().add(this.lblTipo);

		this.cbTipo = new JComboBox();
		this.cbTipo.setBounds(Util.getBoundsColumnaInputComboBox(numFila));
		this.getPnlAtributos().add(this.cbTipo);
		
		numFila++;
		this.btnGenerarMayor = new JButton();
		this.btnGenerarMayor.setText("Generar");
		this.btnGenerarMayor.setBounds(
				ConstantesPosicion.COLUMN_INPUT_POS_INI_X,
				ConstantesPosicion.COLUMN_INPUT_POS_INI_Y+numFila*ConstantesSeparacion.INCREMENTO_Y,
				ConstantesTamanio.BTN_WIDTH,
				ConstantesTamanio.BTN_HEIGHT);
		this.pnlAtributos.add(this.btnGenerarMayor);
		
		this.pnlAtributos.setPreferredSize(new Dimension(
				ConstantesPosicion.COLUMN_LBL_POS_INI_X*2+ConstantesTamanio.TF_WIDTH+ConstantesPosicion.COLUMN_INPUT_POS_INI_X, 
				ConstantesSeparacion.INCREMENTO_Y*2+ConstantesTamanio.CBX_HEIGHT*numFila+ConstantesPosicion.COLUMN_LBL_POS_INI_Y));
		
		this.getPnlCuerpo().add(this.pnlAtributos, BorderLayout.NORTH);
		this.getPnlCuerpo().add(this.pnlTabla, BorderLayout.CENTER);
		
		this.setTamanioPosicionVentana(numFila + 1);
		
	}
	
	@Override
	public void setTamanioPosicionVentana(int pCantidadFilasComponentes) {
		this.setSize(new Dimension(
				ConstantesPosicion.COLUMN_LBL_POS_INI_X+ConstantesTamanio.TF_WIDTH+ConstantesSeparacion.INCREMENTO_Y+
				ConstantesPosicion.COLUMN_INPUT_POS_INI_X +ConstantesTamanio.PNL_BOTONES_SELECCION_WIDTH,600));
		this.setLocationRelativeTo(null);
	}


	public MayorABMModel getAbmModel() {
		return abmModel;
	}

	public void setAbmModel(MayorABMModel abmModel) {
		this.abmModel = abmModel;
	}

	public MayorTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(MayorTableModel tableModel) {
		this.tableModel = tableModel;
		this.getPnlTabla().getTblDatos().setModel(tableModel);
	}

	public JLabel getLblAnio() {
		return lblAnio;
	}

	public JLabel getLblMes() {
		return lblMes;
	}

	public JTextField getTfAnio() {
		return tfAnio;
	}

	public JTextField getTfMes() {
		return tfMes;
	}

	public JLabel getLblCuenta() {
		return lblCuenta;
	}

	public JLabel getLblFecha() {
		return lblMes;
	}

	public PnlABMAtributos getPnlAtributos() {
		return pnlAtributos;
	}

	public PnlBotonesSeleccion getPnlBotonesSeleccionCuenta() {
		return pnlBotonesSeleccionCuenta;
	}

	public PnlTabla getPnlTabla() {
		return pnlTabla;
	}

	public JTextField getTfCuenta() {
		return tfCuenta;
	}

	public JButton getBtnGenerarMayor() {
		return btnGenerarMayor;
	}

	public JComboBox getCbTipo() {
		return cbTipo;
	}

	public JLabel getLblTipo() {
		return lblTipo;
	}
	
}
