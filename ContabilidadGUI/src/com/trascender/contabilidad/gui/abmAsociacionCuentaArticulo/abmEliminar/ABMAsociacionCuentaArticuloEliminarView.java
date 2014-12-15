package com.trascender.contabilidad.gui.abmAsociacionCuentaArticulo.abmEliminar;

import java.text.ParseException;

import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import com.trascender.contabilidad.gui.abmAsociacionCuentaArticulo.AsociacionCuentaArticuloABMModel;
import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.abmStandard.ABMView;
import com.trascender.gui.framework.abmStandard.PnlBotonesSeleccion;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.Util;

public abstract class ABMAsociacionCuentaArticuloEliminarView extends ABMView{

	private static final long serialVersionUID = -3792563385218790119L;
	
	private AsociacionCuentaArticuloABMModel abmModel;
	private BajaArticuloABMModel abmModelBajaArticulo;
	
	private JLabel lblAnioPeriodo;
	private JTextField tfAnioPeriodo;
	private JLabel lblArticulo;
	private JTextField tfArticulo;
	private JLabel lblCuenta;
	private JTextField tfCuenta;
	
	private JLabel lblFecha;
	private JFormattedTextField tfFecha;
	private JLabel lblComentario;
	private JTextArea taComentario;
	private JLabel lblCuentaIngreso;
	private JTextField tfCuentaIngreso;
	private PnlBotonesSeleccion pnlBtnSeleccionCuentaIngreso;
	
	private JLabel lblCuentaEgreso;
	private JTextField tfCuentaEgreso;
	private PnlBotonesSeleccion pnlBtnSeleccionCuentaEgreso;
	
	private MaskFormatter formatter;
	
	private static final String NOMBRE_RECURSO = "ABMAsociacionCuentaArticulo";
	
	
	public ABMAsociacionCuentaArticuloEliminarView(JFrame frame){
		super(frame);
		init();
	}
	
	public ABMAsociacionCuentaArticuloEliminarView(JDialog dialog){
		super(dialog);
		init();
	}
	
	private void init() {
		int numFila = -1;
		
		numFila++;
		this.lblAnioPeriodo = new TLabel();
		this.lblAnioPeriodo.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblAnioPeriodo"));
		this.lblAnioPeriodo.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblAnioPeriodo);
		
		this.tfAnioPeriodo = new JTextField();
		this.tfAnioPeriodo.setBounds(Util.getBoundsColumnaInputTextFieldFechaDesde(numFila));
		this.getPnlCuerpo().add(this.tfAnioPeriodo);
		
		numFila++;
		this.lblArticulo = new TLabel();
		this.lblArticulo.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblArticulo"));
		this.lblArticulo.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblArticulo);
		
		this.tfArticulo = new JTextField();
		this.tfArticulo.setEditable(false);
		this.tfArticulo.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlCuerpo().add(this.tfArticulo);
		
		numFila++;
		this.lblCuenta = new TLabel();
		this.lblCuenta.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblCuenta"));
		this.lblCuenta.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblCuenta);
		
		this.tfCuenta = new JTextField();
		this.tfCuenta.setEditable(false);
		this.tfCuenta.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlCuerpo().add(this.tfCuenta);
		
		numFila++;
		this.lblFecha = new TLabel();
		lblFecha.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblFecha"));
		lblFecha.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(lblFecha);
		
		try {
			this.formatter = new MaskFormatter("##/##/####");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.tfFecha = new JFormattedTextField(this.formatter);
		tfFecha.setBounds(Util.getBoundsColumnaInputTextFieldFechaDesde(numFila));
		this.getPnlCuerpo().add(this.tfFecha);
		
		numFila++;
		this.lblComentario = new TLabel();
		this.lblComentario.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblComentario"));
		this.lblComentario.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblComentario);
		
		this.taComentario = new JTextArea();
		this.taComentario.setLineWrap(true);
		this.taComentario.setWrapStyleWord(true);
		JScrollPane spObservaciones = new JScrollPane(this.taComentario);
		spObservaciones.setBounds(Util.getBoundsColumnaInputTextArea(numFila));
		this.getPnlCuerpo().add(spObservaciones);
		numFila++;
		
		numFila++;
		this.lblCuentaIngreso = new TLabel();
		this.lblCuentaIngreso.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblCuentaIngreso"));
		this.lblCuentaIngreso.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblCuentaIngreso);
		
		this.tfCuentaIngreso = new JTextField();
		this.tfCuentaIngreso.setEditable(false);
		this.tfCuentaIngreso.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlCuerpo().add(this.tfCuentaIngreso);
		
		this.pnlBtnSeleccionCuentaIngreso = new PnlBotonesSeleccion();
		this.pnlBtnSeleccionCuentaIngreso.setBounds(Util.getBoundsColumnaSeleccion(numFila));
		this.getPnlCuerpo().add(this.pnlBtnSeleccionCuentaIngreso);
		
		numFila++;
		this.lblCuentaEgreso = new TLabel();
		this.lblCuentaEgreso.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblCuentaEgreso"));
		this.lblCuentaEgreso.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblCuentaEgreso);
		
		this.tfCuentaEgreso = new JTextField();
		this.tfCuentaEgreso.setEditable(false);
		this.tfCuentaEgreso.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlCuerpo().add(this.tfCuentaEgreso);
		
		this.pnlBtnSeleccionCuentaEgreso = new PnlBotonesSeleccion();
		this.pnlBtnSeleccionCuentaEgreso.setBounds(Util.getBoundsColumnaSeleccion(numFila));
		this.getPnlCuerpo().add(this.pnlBtnSeleccionCuentaEgreso);
		
		this.setTamanioPosicionVentana(numFila + 1);
	}
	
	@Override
	public void setTamanioPosicionVentana(int pCantidadFilasComponentes) {
		this.setSize(Util.getTamanioVentanaABMSeleccion(pCantidadFilasComponentes));
		this.setLocationRelativeTo(null);
	}

	public AsociacionCuentaArticuloABMModel getAbmModel() {
		return abmModel;
	}

	public void setAbmModel(AsociacionCuentaArticuloABMModel abmModel) {
		this.abmModel = abmModel;
	}

	public JLabel getLblAnioPeriodo() {
		return lblAnioPeriodo;
	}

	public void setLblAnioPeriodo(JLabel lblAnioPeriodo) {
		this.lblAnioPeriodo = lblAnioPeriodo;
	}

	public JTextField getTfAnioPeriodo() {
		return tfAnioPeriodo;
	}

	public void setTfAnioPeriodo(JTextField tfAnioPeriodo) {
		this.tfAnioPeriodo = tfAnioPeriodo;
	}

	public JLabel getLblArticulo() {
		return lblArticulo;
	}

	public void setLblArticulo(JLabel lblArticulo) {
		this.lblArticulo = lblArticulo;
	}

	public JTextField getTfArticulo() {
		return tfArticulo;
	}

	public void setTfArticulo(JTextField tfArticulo) {
		this.tfArticulo = tfArticulo;
	}

	public JLabel getLblCuenta() {
		return lblCuenta;
	}

	public void setLblCuenta(JLabel lblCuenta) {
		this.lblCuenta = lblCuenta;
	}

	public JTextField getTfCuenta() {
		return tfCuenta;
	}

	public void setTfCuenta(JTextField tfCuenta) {
		this.tfCuenta = tfCuenta;
	}

	public BajaArticuloABMModel getAbmModelBajaArticulo() {
		return abmModelBajaArticulo;
	}

	public void setAbmModelBajaArticulo(BajaArticuloABMModel abmModelBajaArticulo) {
		this.abmModelBajaArticulo = abmModelBajaArticulo;
	}

	public PnlBotonesSeleccion getPnlBtnSeleccionCuentaIngreso() {
		return pnlBtnSeleccionCuentaIngreso;
	}

	public void setPnlBtnSeleccionCuentaIngreso(
			PnlBotonesSeleccion pnlBtnSeleccionCuentaIngreso) {
		this.pnlBtnSeleccionCuentaIngreso = pnlBtnSeleccionCuentaIngreso;
	}

	public PnlBotonesSeleccion getPnlBtnSeleccionCuentaEgreso() {
		return pnlBtnSeleccionCuentaEgreso;
	}

	public void setPnlBtnSeleccionCuentaEgreso(
			PnlBotonesSeleccion pnlBtnSeleccionCuentaEgreso) {
		this.pnlBtnSeleccionCuentaEgreso = pnlBtnSeleccionCuentaEgreso;
	}

	public JLabel getLblComentario() {
		return lblComentario;
	}

	public JTextArea getTaComentario() {
		return taComentario;
	}

	public JLabel getLblCuentaIngreso() {
		return lblCuentaIngreso;
	}

	public JTextField getTfCuentaIngreso() {
		return tfCuentaIngreso;
	}

	public JLabel getLblCuentaEgreso() {
		return lblCuentaEgreso;
	}

	public JTextField getTfCuentaEgreso() {
		return tfCuentaEgreso;
	}

	public JFormattedTextField getTfFecha() {
		return tfFecha;
	}

}
