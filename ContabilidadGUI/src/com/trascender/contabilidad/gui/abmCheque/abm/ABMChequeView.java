package com.trascender.contabilidad.gui.abmCheque.abm;

import java.awt.Dimension;
import java.text.ParseException;

import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import com.trascender.contabilidad.gui.abmCheque.ChequeABMModel;
import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.abmStandard.ABMView;
import com.trascender.gui.framework.abmStandard.PnlBotonesSeleccion;
import com.trascender.gui.framework.component.TFormattedTextFieldImporte;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.ConstantesPosicion;
import com.trascender.gui.framework.util.ConstantesSeparacion;
import com.trascender.gui.framework.util.ConstantesTamanio;
import com.trascender.gui.framework.util.Util;

public abstract class ABMChequeView extends ABMView{

	private static final long serialVersionUID = -7943816795207296753L;

	private ChequeABMModel abmModel;
	
	private JLabel lblNumeroCheque;
	private JTextField tfNumeroCheque;
	private JLabel lblFechaEmision;
	private JFormattedTextField tfFechaEmision;
	private JLabel lblFechaPago;
	private JFormattedTextField tfFechaPago;
	private JLabel lblImporte;
	private TFormattedTextFieldImporte tfImporte;
	private JLabel lblPostdatado;
	private JCheckBox chkPostdatado;
	private JLabel lblCuentaBancaria;
	private JTextField tfCuentaBancaria;
	private PnlBotonesSeleccion pnlBotonesSeleccion;

	private final String NOMBRE_RECURSO ="ABMCheque";
	private MaskFormatter formatter;
	
	public ABMChequeView(JFrame owner) {
		super(owner);
		this.init();
	}

	public ABMChequeView(JDialog owner) {
		super(owner);
		this.init();
	}
	
	private void init() {
		int numFila = -1;
		
		numFila++;
		this.lblNumeroCheque = new TLabel();
		this.lblNumeroCheque.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblNumeroCheque"));
		this.lblNumeroCheque.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblNumeroCheque);
		
		this.tfNumeroCheque = new JTextField();
		this.tfNumeroCheque.setBounds(Util.getBoundsColumnaInputTextFieldImporte(numFila));
		this.getPnlCuerpo().add(this.tfNumeroCheque);
		
		numFila++;
		this.lblFechaEmision = new TLabel();
		this.lblFechaEmision.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblFechaEmision"));
		this.lblFechaEmision.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblFechaEmision);
		
		try {
			this.formatter = new MaskFormatter("##/##/####");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		this.tfFechaEmision = new JFormattedTextField(this.formatter);
		this.tfFechaEmision.setBounds(Util.getBoundsColumnaInputTextFieldFechaDesde(numFila));
		this.getPnlCuerpo().add(this.tfFechaEmision);
		
		numFila++;
		this.lblFechaPago = new TLabel();
		this.lblFechaPago.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblFechaPago"));
		this.lblFechaPago.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblFechaPago);
		
		this.tfFechaPago = new JFormattedTextField(this.formatter);
		this.tfFechaPago.setBounds(Util.getBoundsColumnaInputTextFieldFechaDesde(numFila));
		this.getPnlCuerpo().add(this.tfFechaPago);
		
		numFila++;
		this.lblImporte = new TLabel();
		this.lblImporte.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblImporte"));
		this.lblImporte.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblImporte);
		
		this.tfImporte = new TFormattedTextFieldImporte();
		//this.tfImporte.setHorizontalAlignment(JTextField.RIGHT);
		this.tfImporte.setBounds(Util.getBoundsColumnaInputTextFieldImporte(numFila));
		this.getPnlCuerpo().add(this.tfImporte);
		
		numFila++;
		this.lblPostdatado= new TLabel();
		this.lblPostdatado.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblPostdatado"));
		this.lblPostdatado.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblPostdatado);
		
		this.chkPostdatado = new JCheckBox();
		this.chkPostdatado.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlCuerpo().add(this.chkPostdatado);
		
		numFila++;
		this.lblCuentaBancaria = new TLabel();
		this.lblCuentaBancaria.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblCuentaBancaria"));
		this.lblCuentaBancaria.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblCuentaBancaria);
		
		this.tfCuentaBancaria = new JTextField();
		this.tfCuentaBancaria.setEditable(false);
		this.tfCuentaBancaria.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlCuerpo().add(this.tfCuentaBancaria);
		
		this.pnlBotonesSeleccion = new PnlBotonesSeleccion();
		this.pnlBotonesSeleccion.setBounds(Util.getBoundsColumnaSeleccion(numFila));
		this.getPnlCuerpo().add(this.pnlBotonesSeleccion);
		
		setTamanioPosicionVentana(numFila + 1);
	}	
	
	@Override
	public void setTamanioPosicionVentana(int pCantidadFilasComponentes) {
		this.setSize(new Dimension(
				ConstantesPosicion.COLUMN_LBL_POS_INI_X*2+
				ConstantesPosicion.COLUMN_INPUT_POS_INI_X+
				ConstantesTamanio.TF_WIDTH+
				ConstantesSeparacion.SEPARADOR_HORIZONTAL+
				ConstantesTamanio.PNL_BOTONES_SELECCION_WIDTH,
				
				ConstantesTamanio.PNL_CABECERA_HEIGHT+ConstantesTamanio.PNL_PIE_HEIGHT+pCantidadFilasComponentes*
				ConstantesSeparacion.INCREMENTO_Y+ConstantesPosicion.COLUMN_LBL_POS_INI_Y));
		this.setLocationRelativeTo(null);
		
	}
	
	public ChequeABMModel getAbmModel() {
		return abmModel;
	}
	public void setAbmModel(ChequeABMModel abmModel) {
		this.abmModel = abmModel;
	}
	public JCheckBox getChkPostdatado() {
		return chkPostdatado;
	}
	public JLabel getLblFechaEmision() {
		return lblFechaEmision;
	}
	public JLabel getLblFechaPago() {
		return lblFechaPago;
	}
	public JLabel getLblImporte() {
		return lblImporte;
	}
	public JLabel getLblNumeroCheque() {
		return lblNumeroCheque;
	}
	public JLabel getLblPostdatado() {
		return lblPostdatado;
	}
	public JFormattedTextField getTfFechaEmision() {
		return tfFechaEmision;
	}
	public JFormattedTextField getTfFechaPago() {
		return tfFechaPago;
	}
	public TFormattedTextFieldImporte getTfImporte() {
		return tfImporte;
	}
	public JTextField getTfNumeroCheque() {
		return tfNumeroCheque;
	}

	public PnlBotonesSeleccion getPnlBotonesSeleccion() {
		return pnlBotonesSeleccion;
	}

	public void setPnlBotonesSeleccion(PnlBotonesSeleccion pnlBotonesSeleccion) {
		this.pnlBotonesSeleccion = pnlBotonesSeleccion;
	}

	public JLabel getLblCuentaBancaria() {
		return lblCuentaBancaria;
	}

	public JTextField getTfCuentaBancaria() {
		return tfCuentaBancaria;
	}
}
