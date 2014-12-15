package com.trascender.contabilidad.gui.abmLineaLibroBanco.abm;

import java.text.ParseException;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.MaskFormatter;

import com.trascender.contabilidad.gui.abmLineaLibroBanco.LineaLibroBancoABMModel;
import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.abmStandard.ABMView;
import com.trascender.gui.framework.component.TFormattedTextFieldImporte;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.Util;

public abstract class ABMLineaLibroBancoView extends ABMView {

	private static final long serialVersionUID = -264833785644621955L;

	private LineaLibroBancoABMModel abmModel;
	
	private JLabel lblFechaGeneracion;
	private JFormattedTextField tfFechaGeneracion;
	private JLabel lblImporte;
	private TFormattedTextFieldImporte tfImporte;
	private JLabel lblConciliado;
	private JCheckBox chkConciliado;
	private JLabel lblTipo;
	private JComboBox cbTipo;
	private JLabel lblObservaciones;
	private JTextArea taObservaciones;
	
	private final String NOMBRE_RECURSO = "ABMLineaLibroBanco";
	private MaskFormatter formatter;

	public ABMLineaLibroBancoView(JDialog owner) {
		super(owner);
		this.init();
	}
	
	public ABMLineaLibroBancoView(JFrame owner) {
		super(owner);
		this.init();
	}
	
	private void init() {
		int numFila = -1;

		numFila++;
		this.lblFechaGeneracion = new TLabel();
		this.lblFechaGeneracion.setText(MessagesContabilidad.getString(NOMBRE_RECURSO+".lblFechaGeneracion"));
		this.lblFechaGeneracion.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblFechaGeneracion);
	
		try {
			this.formatter = new MaskFormatter("##/##/####");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		this.tfFechaGeneracion = new JFormattedTextField(this.formatter);
		this.tfFechaGeneracion.setBounds(Util.getBoundsColumnaInputTextFieldFechaDesde(numFila));
		this.getPnlCuerpo().add(this.tfFechaGeneracion);
		
		numFila++;
		this.lblImporte = new TLabel();
		this.lblImporte.setText(MessagesContabilidad.getString(NOMBRE_RECURSO+".lblImporte"));
		this.lblImporte.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblImporte);
	
		this.tfImporte = new TFormattedTextFieldImporte();
		this.tfImporte.setBounds(Util.getBoundsColumnaInputTextFieldImporte(numFila));
		this.getPnlCuerpo().add(this.tfImporte);

		numFila++;
		this.lblConciliado = new TLabel();
		this.lblConciliado.setText(MessagesContabilidad.getString(NOMBRE_RECURSO+".lblConciliado"));
		this.lblConciliado.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblConciliado);
		
		this.chkConciliado = new JCheckBox();
		this.chkConciliado.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlCuerpo().add(this.chkConciliado);
		
		numFila++;
		this.lblTipo = new TLabel();
		this.lblTipo.setText(MessagesContabilidad.getString(NOMBRE_RECURSO+".lblTipo"));
		this.lblTipo.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblTipo);
		
		this.cbTipo = new JComboBox();
		this.cbTipo.setBounds(Util.getBoundsColumnaInputComboBox(numFila));
		this.getPnlCuerpo().add(this.cbTipo);
		
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
		numFila++;
		
		this.setTamanioPosicionVentana(numFila + 1);
	}
	
	@Override
	public void setTamanioPosicionVentana(int cantidadFilasComponentes) {
		this.setSize(Util.getTamanioVentanaABM(cantidadFilasComponentes));
		this.setLocationRelativeTo(null);
	}

	public LineaLibroBancoABMModel getAbmModel() {
		return abmModel;
	}

	public void setAbmModel(LineaLibroBancoABMModel abmModel) {
		this.abmModel = abmModel;
	}

	public JLabel getLblFechaGeneracion() {
		return lblFechaGeneracion;
	}

	public void setLblFechaGeneracion(JLabel lblFechaGeneracion) {
		this.lblFechaGeneracion = lblFechaGeneracion;
	}

	public JFormattedTextField getTfFechaGeneracion() {
		return tfFechaGeneracion;
	}

	public void setTfFechaGeneracion(JFormattedTextField tfFechaGeneracion) {
		this.tfFechaGeneracion = tfFechaGeneracion;
	}

	public JLabel getLblImporte() {
		return lblImporte;
	}

	public void setLblImporte(JLabel lblImporte) {
		this.lblImporte = lblImporte;
	}

	public TFormattedTextFieldImporte getTfImporte() {
		return tfImporte;
	}

	public void setTfImporte(TFormattedTextFieldImporte tfImporte) {
		this.tfImporte = tfImporte;
	}

	public JLabel getLblTipo() {
		return lblTipo;
	}

	public void setLblTipo(JLabel lblTipo) {
		this.lblTipo = lblTipo;
	}

	public JTextArea getTaObservaciones() {
		return taObservaciones;
	}

	public void setTaObservaciones(JTextArea taObservaciones) {
		this.taObservaciones = taObservaciones;
	}

	public JComboBox getCbTipo() {
		return cbTipo;
	}

	public JLabel getLblObservaciones() {
		return lblObservaciones;
	}

	public JCheckBox getChkConciliado() {
		return chkConciliado;
	}
	
}
