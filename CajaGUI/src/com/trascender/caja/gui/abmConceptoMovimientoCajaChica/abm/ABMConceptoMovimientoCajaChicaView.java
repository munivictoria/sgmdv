package com.trascender.caja.gui.abmConceptoMovimientoCajaChica.abm;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.trascender.caja.gui.abmConceptoMovimientoCajaChica.ConceptoMovimientoCajaChicaABMModel;
import com.trascender.caja.gui.recursos.MessagesCaja;
import com.trascender.gui.framework.abmStandard.ABMView;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.Util;

public abstract class ABMConceptoMovimientoCajaChicaView extends ABMView {

	private static final long serialVersionUID = 7593867598008484979L;

	private ConceptoMovimientoCajaChicaABMModel abmModel;
	
	private JLabel lblNombre;
	private JTextField tfNombre;
	private JLabel lblDescripcion;
	private JTextField tfDescripcion;
	private JLabel lblTipo;
	private JComboBox cbTipo;
	
	private final String NOMBRE_RECURSO = "ABMConceptoMovimientoCajaChica";
	
	public ABMConceptoMovimientoCajaChicaView(JDialog owner) {
		super(owner);
		this.init();
	}
	
	public ABMConceptoMovimientoCajaChicaView(JFrame owner) {
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
		this.lblDescripcion = new TLabel();
		this.lblDescripcion.setText(MessagesCaja.getString(this.NOMBRE_RECURSO+".lblDescripcion"));
		this.lblDescripcion.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblDescripcion);
		
		this.tfDescripcion = new JTextField();
		this.tfDescripcion.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlCuerpo().add(this.tfDescripcion);
		
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
		
	public ConceptoMovimientoCajaChicaABMModel getAbmModel() {
		return abmModel;
	}

	public void setAbmModel(ConceptoMovimientoCajaChicaABMModel abmModel) {
		this.abmModel = abmModel;
	}

	public JLabel getLblDescripcion() {
		return lblDescripcion;
	}

	public void setLblDescripcion(JLabel lblDescripcion) {
		this.lblDescripcion = lblDescripcion;
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

	public JTextField getTfDescripcion() {
		return tfDescripcion;
	}

	public void setTfDescripcion(JTextField tfDescripcion) {
		this.tfDescripcion = tfDescripcion;
	}

	public JTextField getTfNombre() {
		return tfNombre;
	}

	public void setTfNombre(JTextField tfNombre) {
		this.tfNombre = tfNombre;
	}

	public JComboBox getCbTipo() {
		return cbTipo;
	}

	public void setCbTipo(JComboBox cbTipo) {
		this.cbTipo = cbTipo;
	}

	
}
