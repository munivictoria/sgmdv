package com.trascender.contabilidad.gui.abmLibroDiario.abm;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.trascender.contabilidad.gui.abmLibroDiario.LibroDiarioABMModel;
import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.abmStandard.ABMView;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.Util;

public abstract class ABMLibroDiarioView extends ABMView {
	
	private static final long serialVersionUID = 5899247111205539127L;

	private LibroDiarioABMModel abmModel;
	
	private JLabel lblCodigoAutorizacion;
	private JTextField tfCodigoAutorizacion;
	private JLabel lblNumero;
	private JTextField tfNumero;
	private JLabel lblCantidadFolios;
	private JTextField tfCantidadFolios;
	
	private final String NOMBRE_RECURSO = "ABMLibroDiario";
	
	
	public ABMLibroDiarioView(JFrame owner) {
		super(owner);
		this.init();
	}
	
	public ABMLibroDiarioView(JDialog owner) {
		super(owner);
		this.init();
	}
	
	private void init() {
		int numFila = -1;
		
		numFila++;
		this.lblCodigoAutorizacion = new TLabel();
		this.lblCodigoAutorizacion.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblCodigoAutorizacion"));
		this.lblCodigoAutorizacion.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblCodigoAutorizacion);
		
		this.tfCodigoAutorizacion = new JTextField();
		this.tfCodigoAutorizacion.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlCuerpo().add(this.tfCodigoAutorizacion);
		
		numFila++;
		this.lblNumero = new TLabel();
		this.lblNumero.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblNumero"));
		this.lblNumero.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblNumero);
		
		this.tfNumero = new JTextField();
		this.tfNumero.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlCuerpo().add(this.tfNumero);
		
		numFila++;
		this.lblCantidadFolios = new TLabel();
		this.lblCantidadFolios.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblCantidadFolios"));
		this.lblCantidadFolios.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblCantidadFolios);
		
		this.tfCantidadFolios = new JTextField();
		this.tfCantidadFolios.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlCuerpo().add(this.tfCantidadFolios);
		
		this.setTamanioPosicionVentana(numFila + 1);
	}
	
	@Override
	public void setTamanioPosicionVentana(int pCantidadFilasComponentes) {
		this.setSize(Util.getTamanioVentanaABM(pCantidadFilasComponentes));
		this.setLocationRelativeTo(null);
	}

	public LibroDiarioABMModel getAbmModel() {
		return abmModel;
	}

	public void setAbmModel(LibroDiarioABMModel abmModel) {
		this.abmModel = abmModel;
	}

	public JLabel getLblCantidadFolios() {
		return lblCantidadFolios;
	}

	public JLabel getLblCodigoAutorizacion() {
		return lblCodigoAutorizacion;
	}

	public JLabel getLblNumero() {
		return lblNumero;
	}

	public JTextField getTfCantidadFolios() {
		return tfCantidadFolios;
	}

	public JTextField getTfCodigoAutorizacion() {
		return tfCodigoAutorizacion;
	}

	public JTextField getTfNumero() {
		return tfNumero;
	}

}
