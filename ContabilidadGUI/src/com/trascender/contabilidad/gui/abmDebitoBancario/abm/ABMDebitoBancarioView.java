package com.trascender.contabilidad.gui.abmDebitoBancario.abm;

import java.awt.Dimension;
import java.text.ParseException;

import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import com.trascender.contabilidad.gui.abmDebitoBancario.DebitoBancarioABMModel;
import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.abmStandard.ABMView;
import com.trascender.gui.framework.abmStandard.PnlBotonesSeleccion;
import com.trascender.gui.framework.component.TFormattedTextFieldImporte;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.ConstantesPosicion;
import com.trascender.gui.framework.util.ConstantesSeparacion;
import com.trascender.gui.framework.util.ConstantesTamanio;
import com.trascender.gui.framework.util.Util;

public abstract class ABMDebitoBancarioView extends ABMView{

	private static final long serialVersionUID = -9070939622133293744L;
	
	private DebitoBancarioABMModel abmModel;

	private JLabel lblFecha;
	private JFormattedTextField tfFecha;
	private JLabel lblImporte;
	private TFormattedTextFieldImporte tfImporte;
	private JLabel lblCuentaBancaria;
	private JTextField tfCuentaBancaria;
	private PnlBotonesSeleccion pnlBotonesSeleccion;
	
	private final String NOMBRE_RECURSO ="ABMDebitoBancario";
	private MaskFormatter formatter;
	
	public ABMDebitoBancarioView(JFrame owner) {
		super(owner);
		this.init();
	}

	public ABMDebitoBancarioView(JDialog owner) {
		super(owner);
		this.init();
	}
	
	private void init() {
		int numFila = -1;
		
		numFila++;
		this.lblFecha = new TLabel();
		this.lblFecha.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblFecha"));
		this.lblFecha.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblFecha);
		
		try {
			this.formatter = new MaskFormatter("##/##/####");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		this.tfFecha = new JFormattedTextField(this.formatter);
		this.tfFecha.setBounds(Util.getBoundsColumnaInputTextFieldFechaDesde(numFila));
		this.getPnlCuerpo().add(this.tfFecha);
		
		numFila++;
		this.lblImporte = new TLabel();
		this.lblImporte.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblImporte"));
		this.lblImporte.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblImporte);

		this.tfImporte = new TFormattedTextFieldImporte();
//		this.tfImporte.setHorizontalAlignment(JTextField.RIGHT);
		this.tfImporte.setBounds(Util.getBoundsColumnaInputTextFieldImporte(numFila));
		this.getPnlCuerpo().add(this.tfImporte);

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
		
		this.tfImporte.setHorizontalAlignment(JTextField.RIGHT);
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

	public DebitoBancarioABMModel getAbmModel() {
		return abmModel;
	}
	
	public void setAbmModel(DebitoBancarioABMModel abmModel) {
		this.abmModel = abmModel;
	}
	
	public JLabel getLblFecha() {
		return lblFecha;
	}
	
	public JLabel getLblImporte() {
		return lblImporte;
	}
	
	public JFormattedTextField getTfFecha() {
		return tfFecha;
	}
	
	public TFormattedTextFieldImporte getTfImporte() {
		return tfImporte;
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
