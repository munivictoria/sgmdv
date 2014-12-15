package com.trascender.contabilidad.gui.principal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.trascender.contabilidad.gui.abmBanco.AdminBanco;
import com.trascender.contabilidad.gui.abmBoletaDeposito.AdminBoletaDeposito;
import com.trascender.contabilidad.gui.abmCheque.AdminCheque;
import com.trascender.contabilidad.gui.abmCuentaBancaria.AdminCuentaBancaria;
import com.trascender.contabilidad.gui.abmDebitoBancario.AdminDebitoBancario;
import com.trascender.contabilidad.gui.abmLibroBanco.AdminLibroBanco;
import com.trascender.contabilidad.gui.abmOrdenPago.AdminOrdenPago;
import com.trascender.contabilidad.gui.abmOrdenPagoDevolucion.AdminOrdenPagoDevolucion;
import com.trascender.contabilidad.gui.abmResumenCaja.AdminResumenCaja;
import com.trascender.contabilidad.gui.abmRetencion.AdminRetencion;
import com.trascender.gui.framework.main.AppManager;

public class MenuAdministracionBancaria extends JMenu {

	private static final long serialVersionUID = -5873310065622527277L;
	
	private JFrame owner;
	
	private JMenuItem miAdminBanco;
	private JMenuItem miAdminCuentaBancaria;
	private JMenuItem miAdminLibroBanco;
	private JMenuItem miAdminCheque;
	private JMenuItem miAdminDebitoBancario;
	private JMenuItem miAdminBoletaDeposito;
	private JMenuItem miAdminComprobanteRetencion;
	private JMenuItem miAdminOrdenPago;
	private JMenuItem miAdminDevolucion;
	//private JMenuItem miAdminResumenCajas;
	
	public MenuAdministracionBancaria(JFrame owner) {
		super();
		this.owner = owner;
		this.init(); 
		this.setListeners();
	}

	private void init() {
		this.setText("Admin. Bancaria");
		this.setMnemonic(KeyEvent.VK_B);
		
		this.miAdminBanco = new JMenuItem();
		this.miAdminBanco.setText("Bancos");
		this.add(this.miAdminBanco);
		
		this.miAdminCheque = new JMenuItem();
		this.miAdminCheque.setText("Cheques");
		this.add(this.miAdminCheque);
		
		this.miAdminDebitoBancario = new JMenuItem();
		this.miAdminDebitoBancario.setText("D\u00E9bito Bancario");
		this.add(this.miAdminDebitoBancario);
		
		this.miAdminCuentaBancaria = new JMenuItem();
		this.miAdminCuentaBancaria.setText("Cuentas Bancarias");
		this.add(this.miAdminCuentaBancaria);
		
		this.miAdminLibroBanco = new JMenuItem();
		this.miAdminLibroBanco.setText("Libro Banco");
		this.add(this.miAdminLibroBanco);
		
		this.addSeparator();
		
		this.miAdminBoletaDeposito = new JMenuItem();
		this.miAdminBoletaDeposito.setText("Boletas de Depósito");
		this.add(this.miAdminBoletaDeposito);
		
		this.miAdminComprobanteRetencion = new JMenuItem();
		this.miAdminComprobanteRetencion.setText("Comprobantes de Retención");
		this.add(this.miAdminComprobanteRetencion);
		
		this.miAdminOrdenPago = new JMenuItem();
		this.miAdminOrdenPago.setText("Ordenes de Pago");
		this.add(this.miAdminOrdenPago);
		
		this.miAdminDevolucion = new JMenuItem();
		this.miAdminDevolucion.setText("Devolución");
		this.add(this.miAdminDevolucion);
		
//		this.addSeparator();
//		
//		this.miAdminResumenCajas = new JMenuItem();
//		this.miAdminResumenCajas.setText("Resumen de Caja");
//		this.add(this.miAdminResumenCajas);
	}
	
	private void setListeners() {
		this.miAdminBanco.addActionListener(new AdminBancoListener(this.owner));
		this.miAdminCuentaBancaria.addActionListener(new AdminCuentaBancariaListener(this.owner));
		this.miAdminLibroBanco.addActionListener(new AdminLibroBancoListener(this.owner));
		
		this.miAdminCheque.addActionListener(new AdminChequeListener(this.owner));
		this.miAdminDebitoBancario.addActionListener(new AdminDebitoBancarioListener(this.owner));
		this.miAdminBoletaDeposito.addActionListener(new AdminBoletaDepositoListener(this.owner));
		this.miAdminComprobanteRetencion.addActionListener(new AdminComprobanteRetencionoListener(this.owner));
		this.miAdminOrdenPago.addActionListener(new AdminOrdenPagoListener(this.owner));
		this.miAdminDevolucion.addActionListener(new AdminDevolucionListener(this.owner));
//		this.miAdminResumenCajas.addActionListener(new AdminResumenCajaListener(this.owner));
	}

	public JMenuItem getMiAdminBanco() {
		return miAdminBanco;
	}

	public JMenuItem getMiAdminBoletaDeposito() {
		return miAdminBoletaDeposito;
	}

	public JMenuItem getMiAdminCheque() {
		return miAdminCheque;
	}

	public JMenuItem getMiAdminDebitoBancario() {
		return miAdminDebitoBancario;
	}

	public JMenuItem getMiAdminComprobanteRetencion() {
		return miAdminComprobanteRetencion;
	}

	public JMenuItem getMiAdminCuentaBancaria() {
		return miAdminCuentaBancaria;
	}

	public JMenuItem getMiAdminLibroBanco() {
		return miAdminLibroBanco;
	}

	public JMenuItem getMiAdminOrdenPago() {
		return miAdminOrdenPago;
	}

	public JMenuItem getMiAdminDevolucion() {
		return miAdminDevolucion;
	}
	
//	public JMenuItem getMiAdminRetencion() {
//		return miAdminRetencion;
//	}
	
//	public JMenuItem getMiAdminResumenCajas() {
//		return miAdminResumenCajas;
//	}
	
}

class AdminBancoListener implements ActionListener {
	private JFrame owner;
	public AdminBancoListener(JFrame owner) {
		this.owner = owner;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			AdminBanco adminBanco = new AdminBanco(this.owner);
			adminBanco.open();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg((JFrame)owner, ex.getMessage());
		}
	}
}

class AdminCuentaBancariaListener implements ActionListener {
	private JFrame owner;
	
	public AdminCuentaBancariaListener (JFrame owner)  {
		this.owner = owner;
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			AdminCuentaBancaria adminCuentaBancaria= new AdminCuentaBancaria(this.owner);
			adminCuentaBancaria.open();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg((JFrame)owner, ex.getMessage());
		}
	}
}

class AdminLibroBancoListener implements ActionListener {
	private JFrame owner;
	
	public AdminLibroBancoListener (JFrame owner)  {
		this.owner = owner;
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			AdminLibroBanco adminLibroBanco = new AdminLibroBanco(this.owner);
			adminLibroBanco.open();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg((JFrame)owner, ex.getMessage());
		}
	}
}

class AdminChequeListener implements ActionListener {
	private JFrame owner;
	
	public AdminChequeListener (JFrame owner)  {
		this.owner = owner;
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			AdminCheque adminCheque = new AdminCheque(this.owner);
			adminCheque.open();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg((JFrame)owner, ex.getMessage());
		}
	}
}

class AdminDebitoBancarioListener implements ActionListener {
	private JFrame owner;
	public AdminDebitoBancarioListener(JFrame owner) {
		this.owner = owner;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			AdminDebitoBancario adminDebitoBancario = new AdminDebitoBancario(this.owner);
			adminDebitoBancario.open();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg((JFrame)owner, ex.getMessage());
		}
	}
}

class AdminBoletaDepositoListener implements ActionListener {
	private JFrame owner;
	
	public AdminBoletaDepositoListener (JFrame owner)  {
		this.owner = owner;
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			AdminBoletaDeposito adminBoletaDeposito = new AdminBoletaDeposito(this.owner);
			adminBoletaDeposito.open();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg((JFrame)owner, ex.getMessage());
		}
	}
}

class AdminComprobanteRetencionoListener implements ActionListener {
	private JFrame owner;
	
	public AdminComprobanteRetencionoListener (JFrame owner)  {
		this.owner = owner;
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			AdminRetencion adminRetencion = new AdminRetencion(this.owner);
			adminRetencion.open();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg((JFrame)owner, ex.getMessage());
		}
	}
}

class AdminOrdenPagoListener implements ActionListener {
	private JFrame owner;
	
	public AdminOrdenPagoListener (JFrame owner)  {
		this.owner = owner;
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			AdminOrdenPago adminOrdenPago = new AdminOrdenPago(this.owner);
			adminOrdenPago.open();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg((JFrame)owner, ex.getMessage());
		}
	}
}

class AdminDevolucionListener implements ActionListener {
	private JFrame owner;
	
	public AdminDevolucionListener (JFrame owner)  {
		this.owner = owner;
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			AdminOrdenPagoDevolucion adminOrdenPagoDevolucion = new AdminOrdenPagoDevolucion(this.owner);
			adminOrdenPagoDevolucion.open();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg((JFrame)owner, ex.getMessage());
		}
	}
}

class AdminRetencionListener implements ActionListener {
	private JFrame owner;
	
	public AdminRetencionListener (JFrame owner)  {
		this.owner = owner;
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			AdminRetencion adminRetencion = new AdminRetencion(this.owner);
			adminRetencion.open();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg((JFrame)owner, ex.getMessage());
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
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg((JFrame)owner, ex.getMessage());
		}
	}
}
