package com.trascender.contabilidad.gui.abmTipoCuenta.abm;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.trascender.contabilidad.gui.abmReporteContable.ParametroReporteContableTableModel;
import com.trascender.contabilidad.gui.abmTipoCuenta.TipoCuentaABMModel;
import com.trascender.contabilidad.gui.abmTipoCuenta.TipoCuentaExcluidosTableModel;
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

public abstract class ABMTipoCuentaView extends ABMView {
	
	private static final long serialVersionUID = -3144396131035715147L;

	private TipoCuentaABMModel abmModel;
	
	private JLabel lblNombre;
	private JTextField tfNombre;
	private JLabel lblAbreviatura;
	private JComboBox cbAbreviatura;
	private JLabel lblOperaAsientos;
	private JComboBox cbOperaAsientos;
	private JLabel lblOperaMovimientosCaja;
	private JComboBox cbOperaMovimientosCaja;
	private PnlTabla pnlTablaTipoCuenta;
	private JPanel pnlTablas;
	private PnlVerticalBotones pnlBtnTablaTipoCuenta;
	private PnlABMAtributos pnlAtributos;
	private JPanel pnlContenedorTipoCuenta;
	private JLabel lblTablaTipoCuenta;
	private TipoCuentaExcluidosTableModel tableModelTipoCuentaExcluidos;
	
	private final String NOMBRE_RECURSO = "ABMTipoCuenta";
	
	
	public ABMTipoCuentaView(JFrame pFrame) {
		super(pFrame);
		this.init();
	}
	
	public ABMTipoCuentaView(JDialog pDialog) {
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
		
		this.cbAbreviatura = new JComboBox();
		this.cbAbreviatura.setBounds(Util.getBoundsColumnaInputComboBox(numFila));
		this.pnlAtributos.add(this.cbAbreviatura);
		
		numFila++;
		this.lblOperaAsientos = new TLabel();
		this.lblOperaAsientos.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblOperaAsientos"));
		this.lblOperaAsientos.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.pnlAtributos.add(this.lblOperaAsientos);
		
		this.cbOperaAsientos = new JComboBox();
		this.cbOperaAsientos.setBounds(Util.getBoundsColumnaInputComboBox(numFila));
		this.pnlAtributos.add(this.cbOperaAsientos);
		
		numFila++;
		this.lblOperaMovimientosCaja = new TLabel();
		this.lblOperaMovimientosCaja.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblOperaMovimientosCaja"));
		this.lblOperaMovimientosCaja.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.pnlAtributos.add(this.lblOperaMovimientosCaja);
		
		this.cbOperaMovimientosCaja = new JComboBox();
		this.cbOperaMovimientosCaja.setBounds(Util.getBoundsColumnaInputComboBox(numFila));
		this.pnlAtributos.add(this.cbOperaMovimientosCaja);
		
		this.pnlContenedorTipoCuenta = new JPanel();
		this.pnlContenedorTipoCuenta.setLayout(new BorderLayout());
		this.lblTablaTipoCuenta = new JLabel();
		this.lblTablaTipoCuenta.setText("Tipos de Cuentas a los que excluye");
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
//		this.setSize(Util.getTamanioVentanaABM(pCantidadFilasComponentes));
		this.setSize(new Dimension(
				ConstantesPosicion.COLUMN_LBL_POS_INI_X * 2 +
				ConstantesPosicion.COLUMN_INPUT_POS_INI_X +
				ConstantesTamanio.TF_WIDTH +
				ConstantesSeparacion.SEPARADOR_HORIZONTAL + 
				ConstantesTamanio.PNL_BOTONES_SELECCION_WIDTH,600));
		this.setLocationRelativeTo(null);
	}
	
	public JComboBox getCbAbreviatura() {
		return cbAbreviatura;
	}

	public JComboBox getCbOperaAsientos() {
		return cbOperaAsientos;
	}

	public JComboBox getCbOperaMovimientosCaja() {
		return cbOperaMovimientosCaja;
	}

	public JLabel getLblAbreviatura() {
		return lblAbreviatura;
	}

	public JLabel getLblNombre() {
		return lblNombre;
	}

	public JLabel getLblOperaAsientos() {
		return lblOperaAsientos;
	}

	public JLabel getLblOperaMovimientosCaja() {
		return lblOperaMovimientosCaja;
	}

	public JTextField getTfNombre() {
		return tfNombre;
	}

	public TipoCuentaABMModel getAbmModel() {
		return abmModel;
	}

	public void setAbmModel(TipoCuentaABMModel abmModel) {
		this.abmModel = abmModel;
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

	public TipoCuentaExcluidosTableModel getTableModelTipoCuentaExcluidos() {
		return tableModelTipoCuentaExcluidos;
	}

	public void setTableModelTipoCuentaExcluidos(TipoCuentaExcluidosTableModel tableModelTipoCuentaExcluidos) {
		this.tableModelTipoCuentaExcluidos = tableModelTipoCuentaExcluidos;
		this.getPnlTablaTipoCuenta().getTblDatos().setModel(tableModelTipoCuentaExcluidos);
	}
}
