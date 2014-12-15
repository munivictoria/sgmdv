package com.trascender.gui.framework.abmStandard;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

import com.trascender.gui.framework.recursos.Messages;
import com.trascender.gui.framework.util.ConstantesTamanio;
import com.trascender.gui.framework.util.Util;

public class PnlVerticalBotones extends JPanel{

	private static final long serialVersionUID = 5513255615940373091L;
	 
	private JButton btnAgregar;
	private JButton btnModificar;
	private JButton btnQuitar;
	private JButton btnQuitarTodos;
	
	private JButton[] botonesAdicionales;
	
	public PnlVerticalBotones() {
		this.init();
	}
	
	public PnlVerticalBotones(JButton... pBotonesAdicionales){
		this.botonesAdicionales = pBotonesAdicionales;
		this.init();
	}

	private void init() {
		
		this.setPreferredSize(new Dimension(ConstantesTamanio.BTN_WIDTH + 24, 0));
		
		Border bordePnlBotonesArbol = BorderFactory.createCompoundBorder(
				BorderFactory.createMatteBorder(1, 0, 0, 0, Color.GRAY), 
				BorderFactory.createEmptyBorder(120, 120, 120, 120));
		this.setBorder(bordePnlBotonesArbol);
		
		this.setLayout(null);
		
		int numFila = -1;
		
		numFila++;
		this.btnAgregar = new JButton();
		this.btnAgregar.setBounds(Util.getBoundsBotonesPnlVertical(numFila));
		this.btnAgregar.setText(Messages.getString("Application.btnAgregar"));
		this.btnAgregar.setMnemonic(Messages.getString("Application.btnAgregarMnemonic").charAt(0));
		this.add(btnAgregar);
		
		numFila++;
		this.btnModificar = new JButton();
		this.btnModificar.setBounds(Util.getBoundsBotonesPnlVertical(numFila));
		this.btnModificar.setText(Messages.getString("Application.btnModificar"));
		this.btnModificar.setMnemonic(Messages.getString("Application.btnModificarMnemonic").charAt(0));
		this.add(btnModificar);
		
		if (this.botonesAdicionales != null) {
			for (JButton cadaBoton : this.botonesAdicionales){
				numFila++;
				cadaBoton.setBounds(Util.getBoundsBotonesPnlVertical(numFila));
				this.add(cadaBoton);	
			}
		}
		
		numFila++;
		
		numFila++;
		this.btnQuitar = new JButton();
		this.btnQuitar.setBounds(Util.getBoundsBotonesPnlVertical(numFila));
		this.btnQuitar.setText(Messages.getString("Application.btnQuitar"));
		this.btnQuitar.setMnemonic(Messages.getString("Application.btnQuitarMnemonic").charAt(0));
		this.add(btnQuitar);
		
		numFila++;
		this.btnQuitarTodos = new JButton();
		this.btnQuitarTodos.setBounds(Util.getBoundsBotonesPnlVertical(numFila));
		this.btnQuitarTodos.setText(Messages.getString("Application.btnQuitarTodos"));
		this.btnQuitarTodos.setMnemonic(Messages.getString("Application.btnQuitarTodosMnemonic").charAt(0));
		this.add(this.btnQuitarTodos);
	}

	
	public JButton getBtnAgregar() {
		return btnAgregar;
	}

	public JButton getBtnEliminar() {
		return btnQuitar;
	}

	public JButton getBtnModificar() {
		return btnModificar;
	}

	public JButton getBtnQuitarTodos() {
		return btnQuitarTodos;
	}
}
