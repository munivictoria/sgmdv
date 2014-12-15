package com.trascender.contabilidad.gui.abmAsociacionCuentaLineaOrdenCompra.abm;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.abmStandard.ABMView;
import com.trascender.gui.framework.abmStandard.PnlBotonesSeleccion;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.Constantes;
import com.trascender.gui.framework.util.ConstantesPosicion;
import com.trascender.gui.framework.util.ConstantesSeparacion;
import com.trascender.gui.framework.util.ConstantesTamanio;
import com.trascender.gui.framework.util.Util;

public class AsociarCuentaLineaOrdenCompraView extends ABMView {

	private static final long serialVersionUID = 5513255615940373091L;

	private AsociarCuentaLineaOrdenCompraModel abmModel;
	
	private JLabel lblOrdenCompra;
	private JTextField tfOrdenCompra;
	private JLabel lblCuenta;
	private JTextField tfCuenta;
	private PnlBotonesSeleccion pnlBotonesSeleccionCuenta;
	
	private final String NOMBRE_RECURSO = "AsociarCuentaLineaOrdenCompra";
	
	public AsociarCuentaLineaOrdenCompraView(JDialog owner) {
		super(owner);
		this.init();
	}
	
	public AsociarCuentaLineaOrdenCompraView(JFrame owner) {
		super(owner);
		this.init();
	}
	
	private void init() {
		int numFila= -1;
		
		numFila++;
		this.lblOrdenCompra = new TLabel();
		this.lblOrdenCompra.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblOrdenCompra"));
		this.lblOrdenCompra.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblOrdenCompra);
		
		this.tfOrdenCompra = new JTextField();
		this.tfOrdenCompra.setEditable(false);
		this.tfOrdenCompra.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlCuerpo().add(this.tfOrdenCompra);

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

	@Override
	public void setTextoBtnAceptar() {
		
	}

	@Override
	public void setTituloVentana() {
		String titulo = MessagesContabilidad.getString(this.NOMBRE_RECURSO+".titulo");
		this.setTitle(titulo);
		this.getPnlCabecera().getLblTitulo().setText(titulo);
	}

	public AsociarCuentaLineaOrdenCompraModel getAbmModel() {
		return abmModel;
	}

	public void setAbmModel(AsociarCuentaLineaOrdenCompraModel abmModel) {
		this.abmModel = abmModel;
	}

	public JLabel getLblCuenta() {
		return lblCuenta;
	}

	public JLabel getLblOrdenCompra() {
		return lblOrdenCompra;
	}

	public JTextField getTfCuenta() {
		return tfCuenta;
	}

	public JTextField getTfOrdenCompra() {
		return tfOrdenCompra;
	}

	public PnlBotonesSeleccion getPnlBotonesSeleccionCuenta() {
		return pnlBotonesSeleccionCuenta;
	}

}
