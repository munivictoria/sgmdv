package com.trascender.contabilidad.gui.abmAsociacionCuentaLineaOrdenCompra.abm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.trascender.contabilidad.gui.abmAsociacionCuentaLineaOrdenCompra.AsociacionCuentaLineaOrdenCompraABMModel;
import com.trascender.contabilidad.gui.abmLineaOrdenCompra.LineaOrdenCompraTableModel;
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

public abstract class ABMAsociacionCuentaLineaOrdenCompraView extends ABMView {
	
	private static final long serialVersionUID = 7985774538789211160L;
	
	private LineaOrdenCompraTableModel tableModel;
	private AsociacionCuentaLineaOrdenCompraABMModel abmModel;
	
	private JLabel  lblOrdenCompra;
	private JTextField tfOrdenCompra;
	private PnlABMAtributos pnlAtributos;
	private PnlTabla pnlTabla;
	private PnlVerticalBotones pnlBtnTabla;
	
	private final String NOMBRE_RECURSO = "ABMAsociacionCuentaLineaOrdenCompra";
	
	public ABMAsociacionCuentaLineaOrdenCompraView(JDialog pDialog) {
		super(pDialog);
		this.init();
	}

	public ABMAsociacionCuentaLineaOrdenCompraView(JFrame pFrame) {
		super(pFrame);
		this.init();
	}
	
	private void init() {
		
		this.pnlAtributos = new PnlABMAtributos();
		this.pnlTabla = new PnlTabla();
		this.pnlBtnTabla = new PnlVerticalBotones();

		int numFila= -1;
		
		numFila++;
		this.lblOrdenCompra = new TLabel();
		this.lblOrdenCompra.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblOrdenCompra"));
		this.lblOrdenCompra.setHorizontalAlignment(JLabel.LEFT);
		this.lblOrdenCompra.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlAtributos().add(this.lblOrdenCompra);
		
		numFila++;
		this.tfOrdenCompra = new JTextField();
		this.tfOrdenCompra.setEditable(false);
		this.tfOrdenCompra.setBounds(new Rectangle(ConstantesPosicion.COLUMN_LBL_POS_INI_X, 
				ConstantesPosicion.COLUMN_LBL_POS_INI_Y+numFila*ConstantesSeparacion.INCREMENTO_Y, 
				ConstantesTamanio.TF_WIDTH * 2, 
				ConstantesTamanio.TF_HEIGHT));
		this.getPnlAtributos().add(this.tfOrdenCompra);
		
		numFila++;
		
		this.pnlAtributos.setPreferredSize(new Dimension(
				ConstantesPosicion.COLUMN_LBL_POS_INI_X*2+ConstantesTamanio.TF_WIDTH+ConstantesPosicion.COLUMN_INPUT_POS_INI_X, 
				ConstantesSeparacion.INCREMENTO_Y+ConstantesTamanio.TF_HEIGHT*numFila+ConstantesPosicion.COLUMN_LBL_POS_INI_Y));
		
		this.getPnlCuerpo().setLayout(new BorderLayout());
		this.getPnlCuerpo().add(this.pnlAtributos, BorderLayout.NORTH);
		this.getPnlCuerpo().add(this.pnlTabla, BorderLayout.CENTER);
		this.getPnlCuerpo().add(this.pnlBtnTabla, BorderLayout.EAST);
		
		this.setTextoBtnAsociar();
		this.setTamanioPosicionVentana(numFila + 1);
		
	}

	@Override
	public void setTamanioPosicionVentana(int pCantidadFilasComponentes) {
		this.setSize(new Dimension(700,600));
		this.setLocationRelativeTo(null);
	}

	public void setTextoBtnAsociar() {
		this.getPnlBtnTabla().getBtnAgregar().setText(MessagesContabilidad.getString("ABMAsociacionCuentaLineaOrdenCompra.bntAceptarTexto"));
		this.getPnlBtnTabla().getBtnAgregar().setMnemonic(MessagesContabilidad.getChar(("ABMAsociacionCuentaLineaOrdenCompra.bntAceptarTextoMnemonic")));
		this.getPnlBtnTabla().getBtnAgregar().setToolTipText(MessagesContabilidad.getString("ABMAsociacionCuentaLineaOrdenCompra.bntAceptarTextoToolTip"));
	}
	
	public AsociacionCuentaLineaOrdenCompraABMModel getAbmModel() {
		return abmModel;
	}

	public void setAbmModel(AsociacionCuentaLineaOrdenCompraABMModel abmModel) {
		this.abmModel = abmModel;
	}

	public LineaOrdenCompraTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(LineaOrdenCompraTableModel tableModel) {
		this.tableModel = tableModel;
		this.getPnlTabla().getTblDatos().setModel(this.tableModel);
	}

	public PnlVerticalBotones getPnlBtnTabla() {
		return pnlBtnTabla;
	}

	public PnlTabla getPnlTabla() {
		return pnlTabla;
	}

	public PnlABMAtributos getPnlAtributos() {
		return pnlAtributos;
	}

	public JTextField getTfOrdenCompra() {
		return tfOrdenCompra;
	}

	public void setTfOrdenCompra(JTextField tfOrdenCompra) {
		this.tfOrdenCompra = tfOrdenCompra;
	}
}
