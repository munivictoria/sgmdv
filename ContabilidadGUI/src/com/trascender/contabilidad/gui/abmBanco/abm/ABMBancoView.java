package com.trascender.contabilidad.gui.abmBanco.abm;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.trascender.contabilidad.gui.abmBanco.BancoABMModel;
import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.abmStandard.ABMView;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.Util;

public abstract class ABMBancoView extends ABMView {
	
	private static final long serialVersionUID = -486623717321421113L;

	private BancoABMModel abmModel;
	
	private JLabel lblNombre;
	private JTextField tfNombre;
	private JLabel lblSucursal;
	private JTextField tfSucursal;
	
	private static final String NOMBRE_RECURSO = "ABMBanco";
	
	public ABMBancoView(JFrame owner) {
		super(owner);
		this.init();
	}
	
	public ABMBancoView(JDialog owner) {
		super(owner);
		this.init();
	}
	
	private void init() {
		int numFila = -1;
		
		numFila++;
		this.lblNombre = new TLabel();
		this.lblNombre.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblNombre"));
		this.lblNombre.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblNombre);
		
		this.tfNombre = new JTextField();
		this.tfNombre.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlCuerpo().add(this.tfNombre);
		
		numFila++;
		this.lblSucursal = new TLabel();
		this.lblSucursal.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblSucursal"));
		this.lblSucursal.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblSucursal);
		
		this.tfSucursal = new JTextField();
		this.tfSucursal.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlCuerpo().add(this.tfSucursal);
		
		this.setTamanioPosicionVentana(numFila + 1);
	}
	
	@Override
	public void setTamanioPosicionVentana(int pCantidadFilasComponentes) {
		this.setSize(Util.getTamanioVentanaABM(pCantidadFilasComponentes));
		this.setLocationRelativeTo(null);		
	}

	public BancoABMModel getAbmModel() {
		return abmModel;
	}

	public void setAbmModel(BancoABMModel abmModel) {
		this.abmModel = abmModel;
	}

	public JLabel getLblNombre() {
		return lblNombre;
	}

	public JLabel getLblSucursal() {
		return lblSucursal;
	}

	public JTextField getTfNombre() {
		return tfNombre;
	}

	public JTextField getTfSucursal() {
		return tfSucursal;
	}
}
