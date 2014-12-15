package com.trascender.contabilidad.gui.abmSubdiarioCaja.abm;

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

import com.trascender.contabilidad.gui.abmLineaSubdiarioCaja.LineaSubdiarioCajaTableModel;
import com.trascender.contabilidad.gui.abmSubdiarioCaja.SubdiarioCajaABMModel;
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

public abstract class ABMSubdiarioCajaView extends ABMView {

	private static final long serialVersionUID = 3940032375116221430L;
	
	private SubdiarioCajaABMModel abmModel;
	private LineaSubdiarioCajaTableModel tableModel;
	
	private PnlABMAtributos pnlAtributos;
	private PnlTabla pnlTabla;
	private PnlVerticalBotones pnlBotonesTabla;
	
	private JLabel lblFechaCreacion;
	private JFormattedTextField ftfFechaCreacion;
	private JLabel lblPlanDeCuenta;
	private JTextField tfPlanDeCuenta;
	private PnlBotonesSeleccion pnlBotonesSeleccionPlanDeCuenta;
	private JLabel lblTipo;
	private JComboBox cbTipo;
	
	private JButton btnGenerarSubdiario;
	
	private final String NOMBRE_RECURSO = "ABMSubdiarioCaja";
	private MaskFormatter formatter;

	
	public ABMSubdiarioCajaView(JFrame owner) {
		super(owner);
		this.init();
	}
	
	public ABMSubdiarioCajaView(JDialog owner) {
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
		this.lblFechaCreacion = new TLabel();
		this.lblFechaCreacion.setText(MessagesContabilidad.getString(NOMBRE_RECURSO+".lblFechaCreacion"));
		this.lblFechaCreacion.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.pnlAtributos.add(this.lblFechaCreacion);
	
		try {
			this.formatter = new MaskFormatter("##/##/####");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.ftfFechaCreacion = new JFormattedTextField(this.formatter);
		this.ftfFechaCreacion.setBounds(Util.getBoundsColumnaInputTextFieldFechaDesde(numFila));
		this.pnlAtributos.add(this.ftfFechaCreacion);
		
		numFila++;
		this.lblTipo = new TLabel();
		this.lblTipo.setText(MessagesContabilidad.getString(NOMBRE_RECURSO+".lblTipo"));
		this.lblTipo.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.pnlAtributos.add(this.lblTipo);
		
		this.cbTipo = new JComboBox();
		this.cbTipo.setBounds(Util.getBoundsColumnaInputComboBox(numFila));
		this.pnlAtributos.add(this.cbTipo);

		numFila++;
		this.lblPlanDeCuenta = new TLabel();
		this.lblPlanDeCuenta.setText(MessagesContabilidad.getString(NOMBRE_RECURSO+".lblPlanDeCuenta"));
		this.lblPlanDeCuenta.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.pnlAtributos.add(this.lblPlanDeCuenta);
		
		this.tfPlanDeCuenta = new JTextField();
		this.tfPlanDeCuenta.setEditable(false);
		this.tfPlanDeCuenta.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.pnlAtributos.add(this.tfPlanDeCuenta);
		
		this.pnlBotonesSeleccionPlanDeCuenta = new PnlBotonesSeleccion();
		this.pnlBotonesSeleccionPlanDeCuenta.setBounds(Util.getBoundsColumnaSeleccion(numFila));
		this.pnlAtributos.add(this.pnlBotonesSeleccionPlanDeCuenta);
		
		numFila++;
		this.btnGenerarSubdiario = new JButton();
		this.btnGenerarSubdiario.setText("Generar");
		this.btnGenerarSubdiario.setBounds(
				ConstantesPosicion.COLUMN_INPUT_POS_INI_X,
				ConstantesPosicion.COLUMN_INPUT_POS_INI_Y+numFila*ConstantesSeparacion.INCREMENTO_Y,
				ConstantesTamanio.BTN_WIDTH,
				ConstantesTamanio.BTN_HEIGHT);
		this.pnlAtributos.add(this.btnGenerarSubdiario);
		
		this.pnlAtributos.setPreferredSize(new Dimension(
				ConstantesPosicion.COLUMN_LBL_POS_INI_X*2+ConstantesTamanio.TF_WIDTH+ConstantesPosicion.COLUMN_INPUT_POS_INI_X, 
				ConstantesSeparacion.INCREMENTO_Y*2+ConstantesTamanio.CBX_HEIGHT*numFila+ConstantesPosicion.COLUMN_LBL_POS_INI_Y));
		
		this.getPnlCuerpo().add(this.pnlAtributos, BorderLayout.NORTH);
		this.getPnlCuerpo().add(this.pnlTabla, BorderLayout.CENTER);
		this.getPnlCuerpo().add(this.pnlBotonesTabla, BorderLayout.EAST);
		
		this.setTamanioPosicionVentana(numFila + 1);
		
	}
	
	@Override
	public void setTamanioPosicionVentana(int pCantidadFilasComponentes) {
		this.setSize(new Dimension(
//				ConstantesPosicion.COLUMN_LBL_POS_INI_X+ConstantesTamanio.TF_WIDTH+ConstantesSeparacion.INCREMENTO_Y+
//				ConstantesPosicion.COLUMN_INPUT_POS_INI_X +ConstantesTamanio.PNL_BOTONES_SELECCION_WIDTH
				700
				,600));
		this.setLocationRelativeTo(null);
	}

	public SubdiarioCajaABMModel getAbmModel() {
		return abmModel;
	}

	public void setAbmModel(SubdiarioCajaABMModel abmModel) {
		this.abmModel = abmModel;
	}

	public LineaSubdiarioCajaTableModel getTableModel() {
		return tableModel;
	}
	
	public void setTableModel(LineaSubdiarioCajaTableModel tableModel) {
		this.tableModel = tableModel;
		this.getPnlTabla().getTblDatos().setModel(tableModel);
	}

	public JComboBox getCbTipo() {
		return cbTipo;
	}

	public JFormattedTextField getFtfFechaCreacion() {
		return ftfFechaCreacion;
	}

	public JLabel getLblFechaCreacion() {
		return lblFechaCreacion;
	}

	public JLabel getLblPlanDeCuenta() {
		return lblPlanDeCuenta;
	}

	public JLabel getLblTipo() {
		return lblTipo;
	}

	public PnlABMAtributos getPnlAtributos() {
		return pnlAtributos;
	}

	public PnlBotonesSeleccion getPnlBotonesSeleccionPlanDeCuenta() {
		return pnlBotonesSeleccionPlanDeCuenta;
	}

	public PnlTabla getPnlTabla() {
		return pnlTabla;
	}

	public JTextField getTfPlanDeCuenta() {
		return tfPlanDeCuenta;
	}

	public PnlVerticalBotones getPnlBotonesTabla() {
		return pnlBotonesTabla;
	}

	public JButton getBtnGenerarSubdiario() {
		return btnGenerarSubdiario;
	}

}
