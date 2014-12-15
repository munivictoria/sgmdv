package com.trascender.caja.gui.principal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import com.trascender.caja.gui.abmPersonaFisica.AdminPersonaFisica;
import com.trascender.caja.gui.cobro.Cobro;
import com.trascender.caja.gui.resumenActualCaja.AdminResumenActualCaja;
import com.trascender.caja.gui.resumenCaja.AdminResumenCaja;
import com.trascender.gui.framework.main.AppManager;

public class MenuAdministracionCobro extends JMenu {

	private static final long serialVersionUID = -4176043071346099311L;

	private JFrame owner;
	
	private JMenuItem miAdminCobro;
	private JMenuItem miAdminResumenCaja;
	private JMenuItem miAdminResumenActualCaja;
	
	private JMenuItem miAdminPersonFisicaPrueba;
	
	
	public MenuAdministracionCobro (JFrame owner) {
		super();
		this.owner = owner;
		this.init();
		this.setListeners();
	}

	private void init() {
		this.setText("Admin. Cobro");
		this.setMnemonic(KeyEvent.VK_O);
		
		this.miAdminPersonFisicaPrueba = new JMenuItem();
		this.miAdminPersonFisicaPrueba.setText("Administracion de Personas Fisicas PRUEBA");
		//this.add(this.miAdminPersonFisicaPrueba);
		
		this.miAdminCobro = new JMenuItem();
		this.miAdminCobro.setText("Cobros");
		//setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_HOME, 8))
		this.miAdminCobro.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));
		this.miAdminCobro.setMnemonic('C');
		this.add(this.miAdminCobro);
		
		this.miAdminResumenCaja = new JMenuItem();
		this.miAdminResumenCaja.setText("Resumen Diario de Caja");
		this.miAdminResumenCaja.setMnemonic('R');
		this.add(this.miAdminResumenCaja);
		
		this.miAdminResumenActualCaja = new JMenuItem();
		this.miAdminResumenActualCaja.setText("Resumen Actual de Caja");
		this.miAdminResumenActualCaja.setMnemonic('E');
		this.add(this.miAdminResumenActualCaja);
	}
	
	private void setListeners() {
		this.miAdminPersonFisicaPrueba.addActionListener(new AdminPersonaFisicaListener(this.owner));
		this.miAdminCobro.addActionListener(new AdminCobroListener(this.owner));
		this.miAdminResumenCaja.addActionListener(new AdminResumenCajaListener(this.owner));
		this.miAdminResumenActualCaja.addActionListener(new AdminResumenActualCajaListener(this.owner));
	}

	public JMenuItem getMiAdminPersonFisicaPrueba() {
		return miAdminPersonFisicaPrueba;
	}

	public void setMiAdminPersonFisicaPrueba(JMenuItem miAdminPersonFisicaPrueba) {
		this.miAdminPersonFisicaPrueba = miAdminPersonFisicaPrueba;
	}

	public JMenuItem getMiAdminCobro() {
		return miAdminCobro;
	}

	public void setMiAdminCobro(JMenuItem miAdminCobro) {
		this.miAdminCobro = miAdminCobro;
	}

	public JMenuItem getMiAdminResumenCaja() {
		return miAdminResumenCaja;
	}

	public void setMiAdminResumenCaja(JMenuItem miAdminResumenCaja) {
		this.miAdminResumenCaja = miAdminResumenCaja;
	}

	public JMenuItem getMiAdminResumenActualCaja() {
		return miAdminResumenActualCaja;
	}

	public void setMiAdminResumenActualCaja(JMenuItem miAdminResumenActualCaja) {
		this.miAdminResumenActualCaja = miAdminResumenActualCaja;
	}
	
}

class AdminPersonaFisicaListener implements ActionListener {
	private JFrame owner;
	public AdminPersonaFisicaListener(JFrame owner) {
		this.owner = owner;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			AdminPersonaFisica adminPersonaFisica = new AdminPersonaFisica(this.owner);
			adminPersonaFisica.open();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.owner, ex.getMessage());
		}
		
	}
}

class AdminCobroListener implements ActionListener {
	private JFrame owner;
	public AdminCobroListener(JFrame owner) {
		this.owner = owner;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			Cobro Cobro = new Cobro(this.owner);
			Cobro.open();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.owner, ex.getMessage());
		}
		
	}
}

class AdminResumenCajaListener implements ActionListener {
	private JFrame owner;
	public AdminResumenCajaListener(JFrame owner) {
		this.owner = owner;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			AdminResumenCaja adminResumenCaja = new AdminResumenCaja(this.owner);
			adminResumenCaja.open();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.owner, ex.getMessage());
		}
		
	}
}

class AdminResumenActualCajaListener implements ActionListener {
	private JFrame owner;
	
	public AdminResumenActualCajaListener(JFrame owner) {
		this.owner = owner;
	}

	public void actionPerformed(ActionEvent arg0) {
		try {
			AdminResumenActualCaja adminResumenActualCaja = new AdminResumenActualCaja(this.owner);
			adminResumenActualCaja.open();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.owner, ex.getMessage());
		}
	}
}