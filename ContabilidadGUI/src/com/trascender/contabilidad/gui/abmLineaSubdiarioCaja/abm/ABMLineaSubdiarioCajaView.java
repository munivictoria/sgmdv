package com.trascender.contabilidad.gui.abmLineaSubdiarioCaja.abm;

import java.text.ParseException;

import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import com.trascender.contabilidad.gui.abmLineaSubdiarioCaja.LineaSubdiarioCajaABMModel;
import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.abmStandard.ABMView;
import com.trascender.gui.framework.abmStandard.PnlBotonesSeleccion;
import com.trascender.gui.framework.component.TFormattedTextFieldImporte;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.Util;

public abstract class ABMLineaSubdiarioCajaView extends ABMView {

	private static final long serialVersionUID = -655506593741941713L;

	private LineaSubdiarioCajaABMModel abmModel;
	
	private JLabel lblCuenta;
	private JTextField tfCuenta;
	private PnlBotonesSeleccion pnlBotonesSeleccionCuenta;
	private JLabel lblDia;
	private JTextField tfDia;
	private JLabel lblImporte;
	private TFormattedTextFieldImporte tfImporte;
	
	private final String NOMBRE_RECURSO = "ABMLineaSubdiarioCaja";
	private MaskFormatter formatter;
	
	public ABMLineaSubdiarioCajaView(JFrame owner) {
		super(owner);
		this.init();
	}
	
	public ABMLineaSubdiarioCajaView(JDialog owner) {
		super(owner);
		this.init();
	}

	private void init() {
		int numFila = -1;

		numFila++;
		this.lblDia = new TLabel();
		this.lblDia.setText(MessagesContabilidad.getString(NOMBRE_RECURSO+".lblDia"));
		this.lblDia.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblDia);
	
		try {
			this.formatter = new MaskFormatter("##");
			formatter.setPlaceholderCharacter('0');
		} catch (ParseException e) {
			e.printStackTrace();
		}

		this.tfDia = new JFormattedTextField(this.formatter);
		this.tfDia.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlCuerpo().add(this.tfDia);

		numFila++;
		this.lblCuenta = new TLabel();
		this.lblCuenta.setText(MessagesContabilidad.getString(NOMBRE_RECURSO+".lblCuenta"));
		this.lblCuenta.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblCuenta);
		
		this.tfCuenta = new JTextField();
		this.tfCuenta.setEditable(false);
		this.tfCuenta.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlCuerpo().add(this.tfCuenta);
		
		this.pnlBotonesSeleccionCuenta = new PnlBotonesSeleccion();
		this.pnlBotonesSeleccionCuenta.setBounds(Util.getBoundsColumnaSeleccion(numFila));
		this.getPnlCuerpo().add(this.pnlBotonesSeleccionCuenta);
		
		numFila++;
		this.lblImporte = new TLabel();
		this.lblImporte.setText(MessagesContabilidad.getString(NOMBRE_RECURSO+".lblImporte"));
		this.lblImporte.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblImporte);
	
		this.tfImporte = new TFormattedTextFieldImporte();
		this.tfImporte.setBounds(Util.getBoundsColumnaInputTextFieldImporte(numFila));
		this.getPnlCuerpo().add(this.tfImporte);
		
		this.setTamanioPosicionVentana(numFila + 1);
	}
	
	@Override
	public void setTamanioPosicionVentana(int pCantidadFilasComponentes) {
		this.setSize(Util.getTamanioVentanaABMSeleccion(pCantidadFilasComponentes));
		this.setLocationRelativeTo(null);
	}

	public LineaSubdiarioCajaABMModel getAbmModel() {
		return abmModel;
	}

	public void setAbmModel(LineaSubdiarioCajaABMModel abmModel) {
		this.abmModel = abmModel;
	}

	public JLabel getLblCuenta() {
		return lblCuenta;
	}

	public JLabel getLblDia() {
		return lblDia;
	}

	public JLabel getLblImporte() {
		return lblImporte;
	}

	public PnlBotonesSeleccion getPnlBotonesSeleccionCuenta() {
		return pnlBotonesSeleccionCuenta;
	}

	public JTextField getTfCuenta() {
		return tfCuenta;
	}

	public JTextField getTfDia() {
		return tfDia;
	}

	public TFormattedTextFieldImporte getTfImporte() {
		return tfImporte;
	}

}
