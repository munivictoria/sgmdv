package com.trascender.contabilidad.gui.principal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import com.trascender.contabilidad.gui.ayuda.AcercaDe;
import com.trascender.gui.framework.main.AppManager;

public class MenuAyuda extends JMenu {
	
	private static final long serialVersionUID = -9083789241336904785L;
	
	private JFrame owner;
	
	private JMenuItem miContenido;
	private JMenuItem miAcercaDe;
	
	public MenuAyuda(JFrame owner) {
		this.owner = owner;
		this.init();
		this.setListeners();
	}
	
	private void init() {
		this.setText("Ayuda");
		this.setMnemonic(KeyEvent.VK_U);
		
		miContenido = new JMenuItem();
		miContenido.setMnemonic(KeyEvent.VK_O);
		miContenido.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		miContenido.setText("Contenido");
		this.add(miContenido);
		
		this.addSeparator();
		
		miAcercaDe = new JMenuItem();
		miAcercaDe.setMnemonic(KeyEvent.VK_C);
		miAcercaDe.setText("Acerca de Vipians");
		this.add(miAcercaDe);
	}
	
	private void setListeners() {
//		this.miContenido.addActionListener(new MenuAyudaActionListener(this.owner));
		this.miAcercaDe.addActionListener(new MenuAcercaDeListener(this.owner));
	}
}

class MenuAyudaActionListener implements ActionListener {
	private JFrame owner;
	public MenuAyudaActionListener(JFrame owner) {
		this.owner = owner;
	}
	
	public void actionPerformed(ActionEvent e) {
		AppManager.getInstance().showInformationMsg(this.owner, "Menu Ayuda: no implementado.");
	}
}

class MenuAcercaDeListener implements ActionListener {
	private JFrame owner;
	public MenuAcercaDeListener(JFrame owner) {
		this.owner = owner;
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			AcercaDe acercaDe = new AcercaDe(this.owner);
			acercaDe.open();			
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.owner, ex.getMessage());
		}
	}
}