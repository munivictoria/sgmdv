package com.trascender.contabilidad.gui.abmLineaOrdenPago.abm;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.trascender.contabilidad.gui.abmLineaOrdenPago.LineaOrdenPagoABMModel;
import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.abmStandard.ABMView;
import com.trascender.gui.framework.abmStandard.PnlBotonesSeleccion;
import com.trascender.gui.framework.component.TFormattedTextFieldImporte;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.Util;

public abstract class ABMLineaOrdenPagoView extends ABMView {

	private static final long serialVersionUID = 5513255615940373091L;

	private LineaOrdenPagoABMModel abmModel;
	
	private JLabel lblOrdenCompra;
	private JTextField tfOrdenCompra;
	private PnlBotonesSeleccion pnlBotonesSeleccionOrdenCompra;
	private JLabel lblImporte;
	private TFormattedTextFieldImporte tfImporte;
	
	private final String NOMBRE_RECURSO = "ABMLineaOrdenPago";
	
	public ABMLineaOrdenPagoView(JFrame owner) {
		super(owner);
		this.init();
	}
	
	public ABMLineaOrdenPagoView(JDialog owner) {
		super(owner);
		this.init();
	}
	
	private void init() {
		int numFila = -1;
		
		numFila++;
		this.lblOrdenCompra = new TLabel();
		this.lblOrdenCompra.setText(MessagesContabilidad.getString(NOMBRE_RECURSO+".lblOrdenCompra"));
		this.lblOrdenCompra.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblOrdenCompra);
		
		this.tfOrdenCompra = new JTextField();
		this.tfOrdenCompra.setEditable(false);
		this.tfOrdenCompra.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlCuerpo().add(this.tfOrdenCompra);
		
		this.pnlBotonesSeleccionOrdenCompra = new PnlBotonesSeleccion();
		this.pnlBotonesSeleccionOrdenCompra.setBounds(Util.getBoundsColumnaSeleccion(numFila));
		this.getPnlCuerpo().add(this.pnlBotonesSeleccionOrdenCompra);
		
		numFila++;
		this.lblImporte = new TLabel();
		this.lblImporte.setText(MessagesContabilidad.getString(NOMBRE_RECURSO+".lblImporte"));
		this.lblImporte.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblImporte);
	
		this.tfImporte = new TFormattedTextFieldImporte();
		this.tfImporte.setEditable(false);
		this.tfImporte.setBounds(Util.getBoundsColumnaInputTextFieldImporte(numFila));
		this.getPnlCuerpo().add(this.tfImporte);
		
		this.setTamanioPosicionVentana(numFila + 1);
	}
	
	@Override
	public void setTamanioPosicionVentana(int pCantidadFilasComponentes) {
		this.setSize(Util.getTamanioVentanaABMSeleccion(pCantidadFilasComponentes));
		this.setLocationRelativeTo(null);
	}

	public LineaOrdenPagoABMModel getAbmModel() {
		return abmModel;
	}

	public void setAbmModel(LineaOrdenPagoABMModel abmModel) {
		this.abmModel = abmModel;
	}

	public JLabel getLblImporte() {
		return lblImporte;
	}

	public JLabel getLblOrdenCompra() {
		return lblOrdenCompra;
	}

	public PnlBotonesSeleccion getPnlBotonesSeleccionOrdenCompra() {
		return pnlBotonesSeleccionOrdenCompra;
	}

	public TFormattedTextFieldImporte getTfImporte() {
		return tfImporte;
	}

	public JTextField getTfOrdenCompra() {
		return tfOrdenCompra;
	}

}
