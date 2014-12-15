package com.trascender.contabilidad.gui.abmTipoBalance.abm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.text.ParseException;

import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import com.trascender.contabilidad.gui.abmCuenta.CuentaTableModel;
import com.trascender.contabilidad.gui.abmTipoBalance.TipoBalanceABMModel;
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

public abstract class ABMTipoBalanceView extends ABMView {

	private static final long serialVersionUID = 5513255615940373091L;

	private CuentaTableModel tableModel;
	private TipoBalanceABMModel abmModel;
	
	private PnlABMAtributos pnlAtributos;
	private PnlTabla pnlSpTabla;
	private PnlVerticalBotones pnlBtnTabla;
	
	private JLabel lblNombre;
	private JTextField tfNombre;
	private JLabel lblFechaCreacion;
	private JFormattedTextField tfFechaCreacion;
	
	private final String NOMBRE_RECURSO = "ABMTipoBalance";
	private MaskFormatter formatter;
	
	
	public ABMTipoBalanceView(JDialog pDialog) {
		super(pDialog);
		this.init();
	}

	public ABMTipoBalanceView(JFrame pFrame) {
		super(pFrame);
		this.init();
	}
	
	private void init() {
		this.getPnlCuerpo().setLayout(new BorderLayout());
		
		this.pnlAtributos = new PnlABMAtributos();
		this.pnlSpTabla = new PnlTabla();
		this.pnlBtnTabla = new PnlVerticalBotones();
		
		int numFila = -1;
		
		numFila++;
		this.lblNombre = new TLabel();
		this.lblNombre.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO + ".lblNombre"));
		this.lblNombre.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.pnlAtributos.add(this.lblNombre);
		
		this.tfNombre = new JTextField();
		this.tfNombre.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.pnlAtributos.add(this.tfNombre);
		
		numFila++;
		this.lblFechaCreacion = new TLabel();
		this.lblFechaCreacion.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO + ".lblFechaCreacion"));
		this.lblFechaCreacion.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.pnlAtributos.add(this.lblFechaCreacion);
		
		try {
			this.formatter = new MaskFormatter("##/##/####");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.tfFechaCreacion = new JFormattedTextField(this.formatter);
		this.tfFechaCreacion.setBounds(Util.getBoundsColumnaInputTextFieldFechaDesde(numFila));
		this.pnlAtributos.add(this.tfFechaCreacion);
		
		this.pnlAtributos.setPreferredSize(new Dimension(
				ConstantesPosicion.COLUMN_LBL_POS_INI_X*2+ConstantesTamanio.TF_WIDTH+ConstantesPosicion.COLUMN_INPUT_POS_INI_X, 
				numFila*ConstantesSeparacion.INCREMENTO_Y+ConstantesTamanio.CBX_HEIGHT*numFila+ConstantesPosicion.COLUMN_LBL_POS_INI_Y*2));
		
		this.getPnlCuerpo().add(this.pnlAtributos, BorderLayout.NORTH);
		this.getPnlCuerpo().add(this.pnlSpTabla, BorderLayout.CENTER);
		this.getPnlCuerpo().add(this.pnlBtnTabla, BorderLayout.EAST);
		
		this.setTamanioPosicionVentana(numFila + 1);
		
	}

	@Override
	public void setTamanioPosicionVentana(int pCantidadFilasComponentes) {
		this.setSize(new Dimension(700,600));
		this.setLocationRelativeTo(null);
	}

	public JLabel getLblFechaCreacion() {
		return lblFechaCreacion;
	}

	public void setLblFechaCreacion(JLabel lblFechaCreacion) {
		this.lblFechaCreacion = lblFechaCreacion;
	}

	public JLabel getLblNombre() {
		return lblNombre;
	}

	public void setLblNombre(JLabel lblNombre) {
		this.lblNombre = lblNombre;
	}

	public PnlABMAtributos getPnlAtributos() {
		return pnlAtributos;
	}

	public void setPnlAtributos(PnlABMAtributos pnlAtributos) {
		this.pnlAtributos = pnlAtributos;
	}

	public PnlVerticalBotones getPnlBtnTabla() {
		return pnlBtnTabla;
	}

	public void setPnlBtnTabla(PnlVerticalBotones pnlBtnTabla) {
		this.pnlBtnTabla = pnlBtnTabla;
	}

	public PnlTabla getPnlSpTabla() {
		return pnlSpTabla;
	}

	public void setPnlSpTabla(PnlTabla pnlSpTabla) {
		this.pnlSpTabla = pnlSpTabla;
	}

	public JFormattedTextField getTfFechaCreacion() {
		return tfFechaCreacion;
	}

	public void setTfFechaCreacion(JFormattedTextField tfFechaCreacion) {
		this.tfFechaCreacion = tfFechaCreacion;
	}

	public JTextField getTfNombre() {
		return tfNombre;
	}

	public void setTfNombre(JTextField tfNombre) {
		this.tfNombre = tfNombre;
	}

	public CuentaTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(CuentaTableModel tableModel) {
		this.tableModel = tableModel;
	}

	public TipoBalanceABMModel getAbmModel() {
		return abmModel;
	}

	public void setAbmModel(TipoBalanceABMModel abmModel) {
		this.abmModel = abmModel;
	}
	
	
}
