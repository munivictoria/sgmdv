package com.trascender.gui.framework.principal;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import com.trascender.gui.framework.main.AppManager;

public class MenuSistema {

	private Component owner;
	private JMenu menuSistema;
	private JMenuItem miConectar;
	private JMenuItem miDesconectar;
	private JMenuItem miSalir;
	private JMenu menuLookAndFeel;
	
	
	public MenuSistema(Component owner) {
		this.owner = owner;
		this.init();
		this.setListeners();
	}
	
	private void init() {
		menuSistema = new JMenu();
		menuSistema.setText("Sistema");
		menuSistema.setMnemonic(KeyEvent.VK_S);
		
		miConectar = new JMenuItem();
		miConectar.setText("Conectar");
		miConectar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_HOME, 8));
		menuSistema.add(miConectar);
		
		miDesconectar = new JMenuItem();
		miDesconectar.setText("Desconectar");
		miDesconectar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_END, 8));
		menuSistema.add(miDesconectar);
		
		menuSistema.addSeparator();
		
		menuLookAndFeel= new MenuLookAndFeel();
		menuLookAndFeel.setText("Aspecto");
		menuSistema.add(menuLookAndFeel);
		
		menuSistema.addSeparator();
		
		miSalir = new JMenuItem();
		miSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, 8));
		miSalir.setText("Salir");
		menuSistema.add(miSalir);
	}
	
	public JMenu getMenu() {
		return menuSistema;
	}

	private void setListeners() {
		this.miSalir.addActionListener(new MISalirListener(this.owner));
	}

	public JMenuItem getMiConectar() {
		return miConectar;
	}

	public void setMiConectar(JMenuItem miConectar) {
		this.miConectar = miConectar;
	}

	public JMenuItem getMiDesconectar() {
		return miDesconectar;
	}

	public void setMiDesconectar(JMenuItem miDesconectar) {
		this.miDesconectar = miDesconectar;
	}

	public JMenuItem getMiSalir() {
		return miSalir;
	}

	public void setMiSalir(JMenuItem miSalir) {
		this.miSalir = miSalir;
	}

}


class MISalirListener implements ActionListener {
	private Component owner;
	public MISalirListener(Component owner) {
		this.owner = owner;
	}
	public void actionPerformed(ActionEvent e) {
		if (AppManager.getInstance().salir((JFrame)this.owner)) {
			System.exit(0);
		}
	}
}
