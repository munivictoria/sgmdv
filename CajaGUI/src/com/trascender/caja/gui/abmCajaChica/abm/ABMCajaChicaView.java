package com.trascender.caja.gui.abmCajaChica.abm;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.trascender.caja.gui.abmCajaChica.CajaChicaABMModel;
import com.trascender.caja.gui.recursos.MessagesCaja;
import com.trascender.gui.framework.abmStandard.ABMView;
import com.trascender.gui.framework.component.TFormattedTextFieldImporte;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.Util;

public abstract class ABMCajaChicaView extends ABMView {

	private static final long serialVersionUID = 5020581628601245047L;
	
	private CajaChicaABMModel abmModel;
	
	private JLabel lblNombre;
	private JTextField tfNombre;
	private JLabel lblImporteReposicion;
	private TFormattedTextFieldImporte tfImporteReposicion;
	
	private final String NOMBRE_RECURSO = "ABMCajaChica";
	
	public ABMCajaChicaView(JDialog owner) {
		super(owner);
		this.init();
	}
	
	public ABMCajaChicaView(JFrame owner) {
		super(owner);
		this.init();
	}
	
	private void init() {
		int numFila = -1;
		
		numFila++;
		this.lblNombre = new TLabel();
		this.lblNombre.setText(MessagesCaja.getString(NOMBRE_RECURSO + ".lblNombre"));
		this.lblNombre.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblNombre);
		
		this.tfNombre = new JTextField();
		this.tfNombre.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlCuerpo().add(this.tfNombre);
		
		numFila++;
		this.lblImporteReposicion = new TLabel();
		this.lblImporteReposicion.setText(MessagesCaja.getString(this.NOMBRE_RECURSO+".lblImporteReposicion"));
		this.lblImporteReposicion.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblImporteReposicion);
		
		this.tfImporteReposicion = new TFormattedTextFieldImporte();
		this.tfImporteReposicion.setHorizontalAlignment(JTextField.RIGHT);
		this.tfImporteReposicion.setBounds(Util.getBoundsColumnaInputTextFieldImporte(numFila));
		this.getPnlCuerpo().add(this.tfImporteReposicion);
		
		setTamanioPosicionVentana(numFila + 1);
	}
	
	@Override
	public void setTamanioPosicionVentana(int pCantidadFilasComponentes) {
		this.setSize(Util.getTamanioVentanaABM(pCantidadFilasComponentes));
		this.setLocationRelativeTo(null);		
	}

	public CajaChicaABMModel getAbmModel() {
		return abmModel;
	}

	public void setAbmModel(CajaChicaABMModel abmModel) {
		this.abmModel = abmModel;
	}

	public TFormattedTextFieldImporte getTfImporteReposicion() {
		return tfImporteReposicion;
	}

	public void setTfImporteReposicion(TFormattedTextFieldImporte tfImporteReposicion) {
		this.tfImporteReposicion = tfImporteReposicion;
	}

	public JTextField getTfNombre() {
		return tfNombre;
	}

	public void setTfNombre(JTextField tfNombre) {
		this.tfNombre = tfNombre;
	}

	public void setLblImporteReposicion(JLabel lblImporteReposicion) {
		this.lblImporteReposicion = lblImporteReposicion;
	}

	public void setLblNombre(JLabel lblNombre) {
		this.lblNombre = lblNombre;
	}

	public JLabel getLblImporteReposicion() {
		return lblImporteReposicion;
	}

	public JLabel getLblNombre() {
		return lblNombre;
	}

}
