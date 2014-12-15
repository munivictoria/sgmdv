package com.trascender.contabilidad.gui.abmAsociacionCuentaSolicitudSuministro.abm;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.trascender.contabilidad.gui.abmAsociacionCuentaSolicitudSuministro.AsociacionCuentaSolicitudSuministroABMModel;
import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.abmStandard.ABMView;
import com.trascender.gui.framework.abmStandard.PnlBotonesSeleccion;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.Constantes;
import com.trascender.gui.framework.util.ConstantesPosicion;
import com.trascender.gui.framework.util.ConstantesSeparacion;
import com.trascender.gui.framework.util.ConstantesTamanio;
import com.trascender.gui.framework.util.Util;

public class AsociarCuentaSolicitudSuministroView extends ABMView {

	private static final long serialVersionUID = -1784388270769655194L;
	
	AsociacionCuentaSolicitudSuministroABMModel abmModel;

	private JLabel lblSolicitudSuministro;
	private JTextField tfSolicitudSuministro;
	private JLabel lblCantidad;
	private JTextField tfCantidad;
	private JLabel lblValor;
	private JTextField tfValor;
	private JLabel lblUsuario;
	private JTextField tfUsuario;
	private JLabel lblCuenta;
	private JTextField tfCuenta;
	private PnlBotonesSeleccion pnlBotonesSeleccionCuenta;
	
	private final String NOMBRE_RECURSO = "AsociarCuentaSolicitudSuministro";
	
	public AsociarCuentaSolicitudSuministroView(JDialog owner) {
		super(owner);
		this.init();
	}
	
	public AsociarCuentaSolicitudSuministroView(JFrame owner) {
		super(owner);
		this.init();
	}
	
	private void init() {
		int numFila= -1;
		
		numFila++;
		this.lblSolicitudSuministro = new TLabel();
		this.lblSolicitudSuministro.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblSolicitudSuministro"));
		this.lblSolicitudSuministro.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblSolicitudSuministro);
		
		this.tfSolicitudSuministro = new JTextField();
		this.tfSolicitudSuministro.setEditable(false);
		this.tfSolicitudSuministro.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlCuerpo().add(this.tfSolicitudSuministro);
		
		numFila++;
		this.lblCantidad = new TLabel();
		this.lblCantidad.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblCantidad"));
		this.lblCantidad.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblCantidad);
		
		this.tfCantidad = new JTextField();
		this.tfCantidad.setEditable(false);
		this.tfCantidad.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlCuerpo().add(this.tfCantidad);

		numFila++;
		this.lblValor = new TLabel();
		this.lblValor.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblValor"));
		this.lblValor.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblValor);
		
		this.tfValor = new JTextField();
		this.tfValor.setEditable(false);
		this.tfValor.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlCuerpo().add(this.tfValor);
		
		numFila++;
		this.lblUsuario = new TLabel();
		this.lblUsuario.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblUsuario"));
		this.lblUsuario.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblUsuario);
		
		this.tfUsuario = new JTextField();
		this.tfUsuario.setEditable(false);
		this.tfUsuario.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlCuerpo().add(this.tfUsuario);
		
		numFila++;
		this.lblCuenta = new TLabel();
		this.lblCuenta.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblCuenta"));
		this.lblCuenta.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblCuenta);
		
		this.tfCuenta = new JTextField();
		this.tfCuenta.setEditable(false);
		this.tfCuenta.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlCuerpo().add(this.tfCuenta);
		
		this.pnlBotonesSeleccionCuenta = new PnlBotonesSeleccion();
		this.pnlBotonesSeleccionCuenta.setBounds(Util.getBoundsColumnaSeleccion(numFila));
		this.getPnlCuerpo().add(this.pnlBotonesSeleccionCuenta);
		
		this.setTituloVentana();
		this.setDescripcionVentana();
		this.setColorFondo();
		this.setTamanioPosicionVentana(numFila+1);
	}
	
	@Override
	public void setColorFondo() {
		Color color = Constantes.COLOR_AGREGAR;
		this.getPnlPie().setBackground(color);
		this.getPnlCabecera().setBackground(color);
	}

	@Override
	public void setDescripcionVentana() {
		this.getPnlCabecera().getLblDescripcion().setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".descripcion"));
	}

	@Override
	public void setTamanioPosicionVentana(int cantidadFilasComponentes) {
		this.setSize(new Dimension(
				ConstantesPosicion.COLUMN_LBL_POS_INI_X*2+
				ConstantesPosicion.COLUMN_INPUT_POS_INI_X+
				ConstantesTamanio.TF_WIDTH+
				ConstantesSeparacion.SEPARADOR_HORIZONTAL+
				ConstantesTamanio.PNL_BOTONES_SELECCION_WIDTH,
				ConstantesTamanio.PNL_CABECERA_HEIGHT+ConstantesTamanio.PNL_PIE_HEIGHT+cantidadFilasComponentes*
				ConstantesSeparacion.INCREMENTO_Y+ConstantesPosicion.COLUMN_LBL_POS_INI_Y));
		this.setLocationRelativeTo(null);
	}

	@Override
	public void setTextoBtnAceptar() {
		
	}

	@Override
	public void setTituloVentana() {
		String titulo = MessagesContabilidad.getString(this.NOMBRE_RECURSO+".titulo");
		this.setTitle(titulo);
		this.getPnlCabecera().getLblTitulo().setText(titulo);
	}

	public AsociacionCuentaSolicitudSuministroABMModel getAbmModel() {
		return abmModel;
	}

	public void setAbmModel(AsociacionCuentaSolicitudSuministroABMModel abmModel) {
		this.abmModel = abmModel;
	}

	public JTextField getTfSolicitudSuministro() {
		return tfSolicitudSuministro;
	}

	public JTextField getTfCuenta() {
		return tfCuenta;
	}

	public JTextField getTfCantidad() {
		return tfCantidad;
	}

	public JTextField getTfValor() {
		return tfValor;
	}

	public JTextField getTfUsuario() {
		return tfUsuario;
	}

	public PnlBotonesSeleccion getPnlBotonesSeleccionCuenta() {
		return pnlBotonesSeleccionCuenta;
	}
	
}
