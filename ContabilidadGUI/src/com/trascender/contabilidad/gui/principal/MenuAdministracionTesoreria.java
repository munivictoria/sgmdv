package com.trascender.contabilidad.gui.principal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.trascender.contabilidad.gui.abmEgresoTesoreria.abm.AdminMovimientoCajaEgreso;
import com.trascender.contabilidad.gui.abmIngresoTesoreria.AdminMovimientoCajaIngreso;
import com.trascender.gui.framework.main.AppManager;

public class MenuAdministracionTesoreria extends JMenu {

	private static final long serialVersionUID = -1165923033928266843L;
	
	private JFrame owner;
	
	private JMenuItem miAdminEgresoTesoreria;
	private JMenuItem miAdminIngresoTesoreria;
	
	public MenuAdministracionTesoreria(JFrame owner) {
		super();
		this.owner = owner;
		this.init();
		this.setListeners();
	}

	private void init() {
		this.setText("Admin. Tesoreria");
		this.setMnemonic(KeyEvent.VK_T);
		
		this.miAdminEgresoTesoreria = new JMenuItem();
		this.miAdminEgresoTesoreria.setText("Egreso Tesorería");
		this.miAdminEgresoTesoreria.setMnemonic(KeyEvent.VK_E);
		//this.miAdminPlanDeCuenta.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0));
		this.add(this.miAdminEgresoTesoreria);
		
		this.miAdminIngresoTesoreria = new JMenuItem();
		this.miAdminIngresoTesoreria.setText("Ingreso Tesorería");
		this.miAdminIngresoTesoreria.setMnemonic(KeyEvent.VK_I);
		//this.miAdminTipoBalance.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0));
		this.add(this.miAdminIngresoTesoreria);
	}
	
	private void setListeners() {
		this.miAdminEgresoTesoreria.addActionListener(new AdminEgresoTesoreriaListener(this.owner));
		this.miAdminIngresoTesoreria.addActionListener(new AdminIngresoTesoreriaListener(this.owner));
	}

	public JMenuItem getMiAdminEgresoTesoreria() {
		return miAdminEgresoTesoreria;
	}

	public JMenuItem getMiAdminIngresoTesoreria() {
		return miAdminIngresoTesoreria;
	}
	
}

class AdminEgresoTesoreriaListener implements ActionListener {
	private JFrame owner;
	
	public AdminEgresoTesoreriaListener(JFrame owner) {
		this.owner = owner;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			AdminMovimientoCajaEgreso adminEgresoTesoreria = new AdminMovimientoCajaEgreso(this.owner);
			adminEgresoTesoreria.getView().setVisible(true);
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.owner, ex.getMessage());
		}
	}
	
}

class AdminIngresoTesoreriaListener implements ActionListener {
	private JFrame owner;

	public AdminIngresoTesoreriaListener(JFrame owner) {
		this.owner = owner;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			AdminMovimientoCajaIngreso adminIngresoTesoreria = new AdminMovimientoCajaIngreso(this.owner);
			adminIngresoTesoreria.open();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.owner, ex.getMessage());
		}
	}
	
}


