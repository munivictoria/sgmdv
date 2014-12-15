package com.trascender.gui.framework.abmStandard;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ABMErroresView extends ABMView {
	
	private static final long serialVersionUID = -6910933339815987945L;
	
	private JScrollPane spErrores;
	private JTextArea taErrores;
	
	private List<String> listaErrores;
	
	public ABMErroresView(JDialog owner) {
		super(owner);
		this.setModal(false);
		this.init();
	}
	
	private void init() {
		this.getPnlCabecera().setPreferredSize(new Dimension(400, 40));
		
		this.getPnlPie().getBtnCancelar().setText("Cerrar");
		this.getPnlPie().getBtnCancelar().setToolTipText("Cerrar y volver");
		
		this.spErrores = new JScrollPane();
		this.spErrores.setAutoscrolls(true);
		this.taErrores = new JTextArea();
		this.taErrores.setLineWrap(true);
		this.taErrores.setWrapStyleWord(true);
		this.taErrores.setEditable(false);
		this.spErrores.setViewportView(this.taErrores);
		
		this.getPnlCuerpo().setLayout(new BorderLayout());
		this.getPnlCuerpo().add(this.spErrores, BorderLayout.CENTER);
		
		this.setTamanioPosicionVentana(0);
		this.setColorFondo();
		this.setTituloVentana();
		this.setDescripcionVentana();
		this.setTextoBtnAceptar();
	}
	
	@Override
	public void setColorFondo() {
		Color bg = Color.LIGHT_GRAY;
		this.getPnlCabecera().setBackground(bg);
		this.getPnlPie().setBackground(bg);
	}

	@Override
	public void setDescripcionVentana() {
		
	}

	@Override
	public void setTamanioPosicionVentana(int pCantidadFilasComponentes) {
		this.setSize(new Dimension(400,400));
		this.setLocation(10, 10);
	}

	@Override
	public void setTextoBtnAceptar() {
		this.getPnlPie().getBtnAceptar().setEnabled(false);
		this.getPnlPie().getBtnAceptar().setVisible(false);
	}

	@Override
	public void setTituloVentana() {
		this.setTitle("Error");
		this.getPnlCabecera().getLblTitulo().setText("Error de Validación");
	}

	public List<String> getListaErrores() {
		return listaErrores;
	}

	public void setListaErrores(List<String> listaErrores) {
		this.listaErrores = listaErrores;
		if (this.listaErrores != null && !this.listaErrores.isEmpty()) {
			String mensaje = "";
			for (int i = 0; i < this.listaErrores.size(); i++) {
				mensaje += this.listaErrores.get(i) + "\n";
			}
			taErrores.setText(mensaje);
		}
	}

	public JScrollPane getSpErrores() {
		return spErrores;
	}

	public JTextArea getTaErrores() {
		return taErrores;
	}

}
