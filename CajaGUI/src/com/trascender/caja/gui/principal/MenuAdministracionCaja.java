package com.trascender.caja.gui.principal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.trascender.caja.gui.abmCaja.AdminCaja;
import com.trascender.caja.gui.abmCajaChica.AdminCajaChica;
import com.trascender.caja.gui.abmConceptoMovimientoCajaChica.AdminConceptoMovimientoCajaChica;
import com.trascender.caja.gui.abmMoneda.AdminMoneda;
import com.trascender.caja.gui.abmMovimientoCajaChica.AdminMovimientoCajaChica;
import com.trascender.gui.framework.main.AppManager;

public class MenuAdministracionCaja extends JMenu {

	private static final long serialVersionUID = 1270243909563104734L;

	private JFrame owner;
	
	private JMenuItem miAdminCaja;
	private JMenuItem miAdminCajaChica;
	private JMenuItem miAdminConceptoMovimientoCajaChica;
	private JMenuItem miAdminMovimientoCajaChica;
	private JMenuItem miAdminMoneda;
	
	public MenuAdministracionCaja(JFrame owner) {
		super();
		this.owner = owner;
		this.init();
		this.setListeners();
	}

	private void init() {
		this.setText("Admin. Caja");
		this.setMnemonic(KeyEvent.VK_C);
		
		this.miAdminCaja = new JMenuItem();
		this.miAdminCaja.setText("Administracion de Cajas");
		this.miAdminCaja.setMnemonic(KeyEvent.VK_C);
		this.add(this.miAdminCaja);
				
		this.miAdminCajaChica = new JMenuItem();
		this.miAdminCajaChica.setText("Administracion de Cajas Chicas");
		this.miAdminCajaChica.setMnemonic(KeyEvent.VK_A);
		this.add(this.miAdminCajaChica);
		
		this.miAdminConceptoMovimientoCajaChica = new JMenuItem();
		this.miAdminConceptoMovimientoCajaChica.setText("Administracion de Conceptos de Movimientos de Cajas Chicas");
		this.miAdminConceptoMovimientoCajaChica.setMnemonic(KeyEvent.VK_O);
		this.add(this.miAdminConceptoMovimientoCajaChica);
		
		this.miAdminMovimientoCajaChica = new JMenuItem();
		this.miAdminMovimientoCajaChica.setText("Administracion de Movimientos de Cajas Chicas");
		this.miAdminMovimientoCajaChica.setMnemonic(KeyEvent.VK_M);
		this.add(this.miAdminMovimientoCajaChica);
		
		this.miAdminMoneda = new JMenuItem();
		this.miAdminMoneda.setText("Administracion de Tipos de Monedas");
		this.miAdminMoneda.setMnemonic(KeyEvent.VK_N);
		this.add(this.miAdminMoneda);
		
	}
	
	private void setListeners() {
		this.miAdminCaja.addActionListener(new AdminCajaListener(this.owner));
		this.miAdminCajaChica.addActionListener(new AdminCajaChicasListener(this.owner));
		this.miAdminConceptoMovimientoCajaChica.addActionListener(new AdminConceptoMovimientoCajaChicaListener(this.owner));
		this.miAdminMovimientoCajaChica.addActionListener(new AdminMovimientoCajaChicaListener(this.owner));
		this.miAdminMoneda.addActionListener(new AdminMonedaListener(this.owner));
	}

	public JMenuItem getMiAdminCaja() {
		return miAdminCaja;
	}

	public JMenuItem getMiAdminCajaChica() {
		return miAdminCajaChica;
	}

	public JMenuItem getMiAdminConceptoMovimientoCajaChica() {
		return miAdminConceptoMovimientoCajaChica;
	}

	public JMenuItem getMiAdminMovimientoCajaChica() {
		return miAdminMovimientoCajaChica;
	}

	public JMenuItem getMiAdminMoneda() {
		return miAdminMoneda;
	}

	public JFrame getOwner() {
		return owner;
	}
}

class AdminCajaListener implements ActionListener {
	private JFrame owner;
	public AdminCajaListener(JFrame owner) {
		this.owner = owner;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			AdminCaja adminCaja = new AdminCaja(this.owner);
			adminCaja.open();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.owner, ex.getMessage());
		}
		
	}
}

class AdminCajaChicasListener implements ActionListener {
	private JFrame owner;
	public AdminCajaChicasListener(JFrame owner) {
		this.owner = owner;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			AdminCajaChica adminCajaChica = new AdminCajaChica(this.owner);
			adminCajaChica.open();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.owner, ex.getMessage());
		}
		
	}
}

class AdminConceptoMovimientoCajaChicaListener implements ActionListener {
	private JFrame owner;
	public AdminConceptoMovimientoCajaChicaListener(JFrame owner) {
		this.owner = owner;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			AdminConceptoMovimientoCajaChica adminConceptoMovimientoCajaChica = new AdminConceptoMovimientoCajaChica(this.owner);
			adminConceptoMovimientoCajaChica.open();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.owner, ex.getMessage());
		}
		
	}
}

class AdminMovimientoCajaChicaListener implements ActionListener {
	private JFrame owner;
	public AdminMovimientoCajaChicaListener(JFrame owner) {
		this.owner = owner;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			AdminMovimientoCajaChica adminMovimientoCajaChica = new AdminMovimientoCajaChica(this.owner);
			adminMovimientoCajaChica.open();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.owner, ex.getMessage());
		}
	}
}

class AdminMonedaListener implements ActionListener {
	private JFrame owner;
	public AdminMonedaListener(JFrame owner) {
		this.owner = owner;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			AdminMoneda adminMoneda = new AdminMoneda(this.owner);
			adminMoneda.open();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.owner, ex.getMessage());
		}
	}
	
}
