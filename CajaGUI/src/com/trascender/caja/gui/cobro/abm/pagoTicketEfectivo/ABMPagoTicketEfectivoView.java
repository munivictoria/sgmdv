package com.trascender.caja.gui.cobro.abm.pagoTicketEfectivo;

import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.trascender.caja.gui.cobro.PagoTicketEfectivoABMModel;
import com.trascender.caja.gui.recursos.MessagesCaja;
import com.trascender.gui.framework.abmStandard.ABMView;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.Constantes;
import com.trascender.gui.framework.util.Util;

public abstract class ABMPagoTicketEfectivoView extends ABMView{
	
	private PagoTicketEfectivoABMModel abmModel;
	
	private TLabel lblMonto;
	private TLabel lblComentario;
	
	private JTextField tfMonto;
	private JTextArea tfComentario;
	
	public ABMPagoTicketEfectivoView(JDialog owner) {
		super(owner);
		this.init();
	}
	
	public ABMPagoTicketEfectivoView(JFrame owner) {
		super(owner);
		this.init();
	}
	
	private void init() {
		int numFila = -1;
		
		numFila++;
		this.lblMonto = new TLabel();
		this.lblMonto.setText("Monto");
		this.lblMonto.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblMonto);
		
		this.tfMonto = new JTextField();
		this.tfMonto.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlCuerpo().add(this.tfMonto);
		
		numFila++;
		this.lblComentario = new TLabel();
		this.lblComentario.setText("Comentario");
		this.lblComentario.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblComentario);
		
		this.tfComentario = new JTextArea();
		this.tfComentario.setLineWrap(true);
		this.tfComentario.setWrapStyleWord(true);
		JScrollPane spComentario = new JScrollPane(this.tfComentario);
		spComentario.setBounds(Util.getBoundsColumnaInputTextArea(numFila));
		this.getPnlCuerpo().add(spComentario);
		
		numFila++;
		
		setTamanioPosicionVentana(numFila + 1);		
		
		this.setColorFondo();
		this.setTituloVentana();
		this.setDescripcionVentana();
		this.setTextoBtnAceptar();
	}
	
	@Override
	public void setTamanioPosicionVentana(int pCantidadFilasComponentes) {
		this.setSize(Util.getTamanioVentanaABM(pCantidadFilasComponentes));
		this.setLocationRelativeTo(null);		
	}

	@Override
	public void setColorFondo() {
		Color color = Constantes.COLOR_AGREGAR;
		this.getPnlCabecera().setBackground(color);
		this.getPnlPie().setBackground(color);
	}

	@Override
	public void setTextoBtnAceptar() {
		this.getPnlPie().getBtnAceptar().setText(MessagesCaja.getString("Application.btnAgregarTexto"));
		this.getPnlPie().getBtnAceptar().setMnemonic(MessagesCaja.getChar("Application.btnAgregarTextoMnemonic"));
		this.getPnlPie().getBtnAceptar().setToolTipText(MessagesCaja.getString("Application.btnAgregarTextoToolTip"));
	}

	public JTextField getTfMonto() {
		return tfMonto;
	}

	public JTextArea getTfComentario() {
		return tfComentario;
	}

	public PagoTicketEfectivoABMModel getAbmModel() {
		return abmModel;
	}

	public void setAbmModel(PagoTicketEfectivoABMModel abmModel) {
		this.abmModel = abmModel;
	}

	public TLabel getLblMonto() {
		return lblMonto;
	}

	public TLabel getLblComentario() {
		return lblComentario;
	}
	
}
