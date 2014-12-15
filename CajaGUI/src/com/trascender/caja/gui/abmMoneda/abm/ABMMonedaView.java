package com.trascender.caja.gui.abmMoneda.abm;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.trascender.caja.gui.abmMoneda.MonedaABMModel;
import com.trascender.caja.gui.recursos.MessagesCaja;
import com.trascender.gui.framework.abmStandard.ABMView;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.Util;

public abstract class ABMMonedaView extends ABMView{

	private static final long serialVersionUID = 4434569151450736213L;

	private MonedaABMModel abmModel;
	
	private JLabel lblNombre;
	private JTextField tfNombre;
	private JLabel lblTipo;
	private JComboBox cbTipo;
	
	private final String NOMBRE_RECURSO = "ABMMoneda";
	
	public ABMMonedaView(JDialog owner) {
		super(owner);
		this.init();
	}
	
	public ABMMonedaView(JFrame owner) {
		super(owner);
		this.init();
	}
	
	private void init() {
		int numFila = -1;
		
		numFila++;
		this.lblNombre = new TLabel();
		this.lblNombre.setText(MessagesCaja.getString(this.NOMBRE_RECURSO+".lblNombre"));
		this.lblNombre.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblNombre);
		
		this.tfNombre = new JTextField();
		this.tfNombre.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlCuerpo().add(this.tfNombre);
		
		numFila++;
		this.lblTipo = new TLabel();
		this.lblTipo.setText(MessagesCaja.getString(this.NOMBRE_RECURSO+".lblTipo"));
		this.lblTipo.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblTipo);
		
		this.cbTipo = new JComboBox();
		this.cbTipo.setBounds(Util.getBoundsColumnaInputComboBox(numFila));
		this.getPnlCuerpo().add(this.cbTipo);
		
		setTamanioPosicionVentana(numFila + 1);
	}

	@Override
	public void setTamanioPosicionVentana(int pCantidadFilasComponentes) {
		this.setSize(Util.getTamanioVentanaABM(pCantidadFilasComponentes));
		this.setLocationRelativeTo(null);		
	}
	
	public JComboBox getCbTipo() {
		return cbTipo;
	}
	public void setCbTipo(JComboBox cbTipo) {
		this.cbTipo = cbTipo;
	}
	public JLabel getLblNombre() {
		return lblNombre;
	}
	public void setLblNombre(JLabel lblNombre) {
		this.lblNombre = lblNombre;
	}
	public JLabel getLblTipo() {
		return lblTipo;
	}
	public void setLblTipo(JLabel lblTipo) {
		this.lblTipo = lblTipo;
	}
	public JTextField getTfNombre() {
		return tfNombre;
	}
	public void setTfNombre(JTextField tfNombre) {
		this.tfNombre = tfNombre;
	}

	public MonedaABMModel getAbmModel() {
		return abmModel;
	}

	public void setAbmModel(MonedaABMModel abmModel) {
		this.abmModel = abmModel;
	}
}
