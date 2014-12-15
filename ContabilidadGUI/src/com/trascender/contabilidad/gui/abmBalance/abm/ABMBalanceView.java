package com.trascender.contabilidad.gui.abmBalance.abm;

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

import com.trascender.contabilidad.gui.abmBalance.BalanceABMModel;
import com.trascender.contabilidad.gui.abmBalance.CuentaHistoricaBalanceTableModel;
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

public abstract class ABMBalanceView extends ABMView {
	
	private static final long serialVersionUID = -6873942152071955286L;
	
	private BalanceABMModel abmModel;
	private CuentaHistoricaBalanceTableModel tableModel;
	
	private PnlABMAtributos pnlAtributos;
	private PnlTabla pnlTabla;

	private JLabel lblNombre;
	private JTextField tfNombre;
	private JLabel lblFecha;
	private JFormattedTextField ftfFecha;
	private JLabel lblTipoBalance;
	private JTextField tfTipoBalance;
	private PnlBotonesSeleccion pnlBotonesSeleccionTipoBalance;
	
	private JButton btnGenerarBalance;
	private MaskFormatter formatter;
	private final String NOMBRE_RECURSO = "ABMBalance";
	
	public ABMBalanceView(JFrame owner) {
		super(owner);
		this.init();
	}
	
	public ABMBalanceView(JDialog owner) {
		super(owner);
		this.init();
	}
	
	private void init() {
		this.getPnlCuerpo().setLayout(new BorderLayout());
		
		this.pnlAtributos = new PnlABMAtributos();
		this.pnlTabla = new PnlTabla();
		
		int numFila = -1;
		
		numFila++;
		this.lblNombre = new TLabel();
		this.lblNombre.setText(MessagesContabilidad.getString(NOMBRE_RECURSO+".lblNombre"));
		this.lblNombre.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.pnlAtributos.add(this.lblNombre);
		
		this.tfNombre = new JTextField();
		this.tfNombre.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.pnlAtributos.add(this.tfNombre);
		
		numFila++;
		this.lblFecha = new TLabel();
		this.lblFecha.setText(MessagesContabilidad.getString(NOMBRE_RECURSO+".lblFecha"));
		this.lblFecha.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.pnlAtributos.add(this.lblFecha);
	
		try {
			this.formatter = new MaskFormatter("##/##/####");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.ftfFecha = new JFormattedTextField(this.formatter);
		this.ftfFecha.setBounds(Util.getBoundsColumnaInputTextFieldFechaDesde(numFila));
		this.pnlAtributos.add(this.ftfFecha);
		
		numFila++;
		this.lblTipoBalance = new TLabel();
		this.lblTipoBalance.setText(MessagesContabilidad.getString(NOMBRE_RECURSO+".lblTipoBalance"));
		this.lblTipoBalance.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.pnlAtributos.add(this.lblTipoBalance);
		
		this.tfTipoBalance = new JTextField();
		this.tfTipoBalance.setEditable(false);
		this.tfTipoBalance.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.pnlAtributos.add(this.tfTipoBalance);
		
		this.pnlBotonesSeleccionTipoBalance = new PnlBotonesSeleccion();
		this.pnlBotonesSeleccionTipoBalance.setBounds(Util.getBoundsColumnaSeleccion(numFila));
		this.pnlAtributos.add(this.pnlBotonesSeleccionTipoBalance);
		
		numFila++;
		this.btnGenerarBalance = new JButton();
		this.btnGenerarBalance.setText("Generar");
		this.btnGenerarBalance.setBounds(
				ConstantesPosicion.COLUMN_INPUT_POS_INI_X,
				ConstantesPosicion.COLUMN_INPUT_POS_INI_Y+numFila*ConstantesSeparacion.INCREMENTO_Y,
				ConstantesTamanio.BTN_WIDTH,
				ConstantesTamanio.BTN_HEIGHT);
		this.pnlAtributos.add(this.btnGenerarBalance);
		
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

	public BalanceABMModel getAbmModel() {
		return abmModel;
	}

	public void setAbmModel(BalanceABMModel abmModel) {
		this.abmModel = abmModel;
	}

	public CuentaHistoricaBalanceTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(CuentaHistoricaBalanceTableModel tableModel) {
		this.tableModel = tableModel;
		this.getPnlTabla().getTblDatos().setModel(tableModel);
	}

	public JButton getBtnGenerarBalance() {
		return btnGenerarBalance;
	}

	public JFormattedTextField getFtfFecha() {
		return ftfFecha;
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

	public PnlABMAtributos getPnlAtributos() {
		return pnlAtributos;
	}

	public PnlBotonesSeleccion getPnlBotonesSeleccionTipoBalance() {
		return pnlBotonesSeleccionTipoBalance;
	}

	public PnlTabla getPnlTabla() {
		return pnlTabla;
	}

	public JTextField getTfNombre() {
		return tfNombre;
	}

	public JTextField getTfTipoBalance() {
		return tfTipoBalance;
	}
}
