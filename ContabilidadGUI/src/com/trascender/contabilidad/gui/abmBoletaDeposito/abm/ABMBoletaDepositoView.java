package com.trascender.contabilidad.gui.abmBoletaDeposito.abm;

import java.awt.Dimension;
import java.text.ParseException;

import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import com.trascender.contabilidad.gui.abmBoletaDeposito.BoletaDepositoABMModel;
import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.abmStandard.ABMView;
import com.trascender.gui.framework.abmStandard.PnlBotonesSeleccion;
import com.trascender.gui.framework.component.TFormattedTextFieldImporte;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.ConstantesPosicion;
import com.trascender.gui.framework.util.ConstantesSeparacion;
import com.trascender.gui.framework.util.ConstantesTamanio;
import com.trascender.gui.framework.util.Util;

public abstract class ABMBoletaDepositoView extends ABMView {

	private static final long serialVersionUID = -2265493803856944080L;

	private BoletaDepositoABMModel abmModel;
	
	private JLabel lblNumeroBoleta;
	private JTextField tfNumeroBoleta;
	private JLabel lblImporte;
	private TFormattedTextFieldImporte tfImporte;
	private JLabel lblFecha;
	private JFormattedTextField tfFecha;
	private JLabel lblCuentaBancaria;
	private JTextField tfCuentaBancaria;
	private JLabel lblCuentaAfectada;
	private JTextField tfCuentaAfectada;
	private JLabel lblObservaciones;
	private JTextArea taObservaciones;
	
	private MaskFormatter formatter;
	private PnlBotonesSeleccion pnlBtnSeleccionCuentaBancaria;
	private PnlBotonesSeleccion pnlBtnSeleccionCuentaAfectada;


private static final String NOMBRE_RECURSO = "ABMBoletaDeposito";
	
	public ABMBoletaDepositoView(JFrame owner) {
		super(owner);
		this.init();
	}

	public ABMBoletaDepositoView(JDialog owner) {
		super(owner);
		this.init();
	}
	
	private void init() {
		int numFila = -1;
		
		numFila++;
		this.lblNumeroBoleta = new TLabel();
		this.lblNumeroBoleta.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblNumeroBoleta"));
		this.lblNumeroBoleta.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblNumeroBoleta);
		
		this.tfNumeroBoleta = new JTextField();
		this.tfNumeroBoleta.setBounds(Util.getBoundsColumnaInputTextFieldImporte(numFila));
		this.getPnlCuerpo().add(this.tfNumeroBoleta);
		
		numFila++;
		this.lblImporte = new TLabel();
		this.lblImporte.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblImporte"));
		this.lblImporte.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblImporte);
		
		this.tfImporte = new TFormattedTextFieldImporte();
		this.tfImporte.setHorizontalAlignment(JTextField.RIGHT);
		this.tfImporte.setBounds(Util.getBoundsColumnaInputTextFieldImporte(numFila));
		this.getPnlCuerpo().add(this.tfImporte);
		
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
		this.lblCuentaBancaria = new TLabel();
		this.lblCuentaBancaria.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblCuentaBancaria"));
		this.lblCuentaBancaria.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblCuentaBancaria);
		
		this.tfCuentaBancaria = new JTextField();
		this.tfCuentaBancaria.setEditable(false);
		this.tfCuentaBancaria.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlCuerpo().add(this.tfCuentaBancaria);
		
		this.pnlBtnSeleccionCuentaBancaria = new PnlBotonesSeleccion();
		this.pnlBtnSeleccionCuentaBancaria.setBounds(Util.getBoundsColumnaSeleccion(numFila));
		this.getPnlCuerpo().add(this.pnlBtnSeleccionCuentaBancaria);
		
		numFila++;
		this.lblCuentaAfectada = new TLabel();
		this.lblCuentaAfectada.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblCuentaAfectada"));
		this.lblCuentaAfectada.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblCuentaAfectada);
		
		this.tfCuentaAfectada = new JTextField();
		this.tfCuentaAfectada.setEditable(false);
		this.tfCuentaAfectada.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlCuerpo().add(this.tfCuentaAfectada);
		
		this.pnlBtnSeleccionCuentaAfectada = new PnlBotonesSeleccion();
		this.pnlBtnSeleccionCuentaAfectada.setBounds(Util.getBoundsColumnaSeleccion(numFila));
		this.getPnlCuerpo().add(this.pnlBtnSeleccionCuentaAfectada);
		
		numFila++;
		this.lblObservaciones = new TLabel();
		this.lblObservaciones.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblObservaciones"));
		this.lblObservaciones.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblObservaciones);
		
		
		this.taObservaciones = new JTextArea();
		this.taObservaciones.setLineWrap(true);
		this.taObservaciones.setWrapStyleWord(true);
		JScrollPane spObservaciones = new JScrollPane(this.taObservaciones);
		spObservaciones.setBounds(Util.getBoundsColumnaInputTextArea(numFila));
		this.getPnlCuerpo().add(spObservaciones);
		
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
				
				ConstantesTamanio.PNL_CABECERA_HEIGHT+
				ConstantesTamanio.PNL_PIE_HEIGHT+
				pCantidadFilasComponentes*ConstantesSeparacion.INCREMENTO_Y+
				ConstantesTamanio.TA_HEIGHT));
		this.setLocationRelativeTo(null);
		
	}
	
	public BoletaDepositoABMModel getAbmModel() {
		return abmModel;
	}

	public JLabel getLblCuentaBancaria() {
		return lblCuentaBancaria;
	}

	public JLabel getLblFecha() {
		return lblFecha;
	}

	public JLabel getLblImporte() {
		return lblImporte;
	}

	public JLabel getLblNumeroBoleta() {
		return lblNumeroBoleta;
	}

	public JLabel getLblObservaciones() {
		return lblObservaciones;
	}

	public PnlBotonesSeleccion getPnlBtnSeleccionCuentaAfectada() {
		return pnlBtnSeleccionCuentaAfectada;
	}

	public PnlBotonesSeleccion getPnlBtnSeleccionCuentaBancaria() {
		return pnlBtnSeleccionCuentaBancaria;
	}

	public JTextField getTfCuentaBancaria() {
		return tfCuentaBancaria;
	}

	public JFormattedTextField getTfFecha() {
		return tfFecha;
	}

	public TFormattedTextFieldImporte getTfImporte() {
		return tfImporte;
	}

	public JTextField getTfNumeroBoleta() {
		return tfNumeroBoleta;
	}

	public JTextArea getTaObservaciones() {
		return taObservaciones;
	}

	public void setAbmModel(BoletaDepositoABMModel abmModel) {
		this.abmModel = abmModel;
	}

	public JLabel getLblCuentaAfectada() {
		return lblCuentaAfectada;
	}

	public JTextField getTfCuentaAfectada() {
		return tfCuentaAfectada;
	}
	
}
