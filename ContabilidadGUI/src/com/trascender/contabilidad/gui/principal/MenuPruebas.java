package com.trascender.contabilidad.gui.principal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.trascender.contabilidad.gui.abmBien.AdminBien;
import com.trascender.contabilidad.gui.abmCuenta.AdminCuenta;
import com.trascender.contabilidad.gui.abmDigestoMunicipal.AdminDigestoMunicipal;
import com.trascender.contabilidad.gui.abmFolioLibroDiario.AdminFolioLibroDiario;
import com.trascender.contabilidad.gui.abmOrdenCompra.AdminOrdenCompra;
import com.trascender.contabilidad.gui.abmPersonaFisica.AdminPersonaFisica;
import com.trascender.contabilidad.gui.abmProveedor.AdminProveedor;
import com.trascender.contabilidad.gui.abmTipoModificador.AdminTipoModificador;
import com.trascender.contabilidad.gui.abmTipoOrdenCompra.AdminTipoOrdenCompra;
import com.trascender.gui.framework.main.AppManager;

public class MenuPruebas extends JMenu {

	private static final long serialVersionUID = -7369753227961996215L;
	
	private JFrame owner;
	
	private JMenuItem miAdminCuenta;
	private JMenuItem miAdminFolioLibroDiario;
	private JMenuItem miAdminDigestoMunicipal;
	private JMenuItem miAdminPersonaFisica;
	private JMenuItem miAdminGrupoProveedor;
	private JMenuItem miAdminGrupoBien;
	private JMenuItem miAdminBien;
	private JMenuItem miAdminProveedor;
	private JMenuItem miAdminTipoOrdenCompra;
	private JMenuItem miAdminOrdenCompra;
	private JMenuItem miAdminTipoModificador;
	
	public MenuPruebas(JFrame owner) {
		super();
		this.owner = owner;
		this.init();
		this.setListeners();
	}
	
	private void init() {
		this.setText("Admin. Pruebas");
		this.setMnemonic(KeyEvent.VK_P);
		
		this.miAdminCuenta = new JMenuItem();
		this.miAdminCuenta.setText("Administración de Cuentas");
		this.add(this.miAdminCuenta);
		
		this.miAdminFolioLibroDiario = new JMenuItem();
		this.miAdminFolioLibroDiario.setText("Administración de Folio Libros Diarios");
		this.add(this.miAdminFolioLibroDiario);
		
		this.miAdminDigestoMunicipal = new JMenuItem();
		this.miAdminDigestoMunicipal.setText("Administración de Dec. Ord y Res.");
		this.add(this.miAdminDigestoMunicipal);
		
		this.miAdminPersonaFisica = new JMenuItem();
		this.miAdminPersonaFisica.setText("Administración de Personas Físicas");
		this.add(this.miAdminPersonaFisica);
		
		this.miAdminGrupoProveedor = new JMenuItem();
		this.miAdminGrupoProveedor.setText("Administración de Grupos de Proveedores");
		this.add(this.miAdminGrupoProveedor);
		
		this.miAdminGrupoBien = new JMenuItem();
		this.miAdminGrupoBien.setText("Administración de Grupos de Productos");
		this.add(this.miAdminGrupoBien);
		
		this.miAdminBien = new JMenuItem();
		this.miAdminBien.setText("Administración de Productos");
		this.add(this.miAdminBien);
		
		this.miAdminProveedor = new JMenuItem();
		this.miAdminProveedor.setText("Administración de Proveedores");
		this.add(this.miAdminProveedor);
		
		this.miAdminTipoOrdenCompra = new JMenuItem();
		this.miAdminTipoOrdenCompra.setText("Administración de Tipos de Orden de Compra");
		this.add(this.miAdminTipoOrdenCompra);
		
		this.miAdminOrdenCompra = new JMenuItem();
		this.miAdminOrdenCompra.setText("Administración de Orden de Compra");
		this.add(this.miAdminOrdenCompra);
		
		this.miAdminTipoModificador = new JMenuItem();
		this.miAdminTipoModificador.setText("Administración de Modificadores de FdC");
		this.add(this.miAdminTipoModificador);
	}
	
	private void setListeners() {
		this.miAdminCuenta.addActionListener(new AdminCuentaListener(this.owner));
		this.miAdminFolioLibroDiario.addActionListener(new AdminFolioLibroDiarioListener(this.owner));
		this.miAdminDigestoMunicipal.addActionListener(new AdminDigestoMunicipalListener(this.owner));
		this.miAdminPersonaFisica.addActionListener(new AdminPersonaFisicaListener(this.owner));
		this.miAdminGrupoProveedor.addActionListener(new AdminGrupoProveedorListener(this.owner));
		this.miAdminGrupoBien.addActionListener(new AdminGrupoBienListener(this.owner));
		this.miAdminBien.addActionListener(new AdminBienListener(this.owner));
		this.miAdminProveedor.addActionListener(new AdminProveedorListener(this.owner));
		this.miAdminTipoOrdenCompra.addActionListener(new AdminTipoOrdenCompraListener(this.owner));
		this.miAdminOrdenCompra.addActionListener(new AdminOrdenCompraListener(this.owner));
		this.miAdminTipoModificador.addActionListener(new AdminTipoModificadorListener(this.owner));
	}
	
	public JMenuItem getMiAdminOrdenCompra() {
		return miAdminOrdenCompra;
	}

	public JMenuItem getMiAdminGrupoBien() {
		return miAdminGrupoBien;
	}

	public JMenuItem getMiAdminFolioLibroDiario() {
		return miAdminFolioLibroDiario;
	}

	public JMenuItem getMiAdminPersonaFisica() {
		return miAdminPersonaFisica;
	}

	public JMenuItem getMiAdminDigestoMunicipal() {
		return miAdminDigestoMunicipal;
	}
	
	public JMenuItem getMiAdminBien() {
		return miAdminBien;
	}

	public JMenuItem getMiAdminProveedor() {
		return miAdminProveedor;
	}

	public JMenuItem getMiAdminTipoModificador() {
		return miAdminTipoModificador;
	}

	public JMenuItem getMiAdminCuenta() {
		return miAdminCuenta;
	}

	public JMenuItem getMiAdminGrupoProveedor() {
		return miAdminGrupoProveedor;
	}

	public JMenuItem getMiAdminTipoOrdenCompra() {
		return miAdminTipoOrdenCompra;
	}
}

class AdminDigestoMunicipalListener implements ActionListener {
	private JFrame owner;
	
	public AdminDigestoMunicipalListener(JFrame owner) {
		this.owner = owner;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			AdminDigestoMunicipal adminDigestoMunicipal = new AdminDigestoMunicipal(this.owner);
//			adminDigestoMunicipal.open();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.owner, ex.getMessage());
		}
	}
}

class AdminCuentaListener implements ActionListener {
	private JFrame owner;
	public AdminCuentaListener(JFrame owner) {
		this.owner = owner;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			AdminCuenta adminCuenta = new AdminCuenta(this.owner);
			adminCuenta.open();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.owner, ex.getMessage());
		}
		
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

class AdminGrupoProveedorListener implements ActionListener {
	private JFrame owner;
	public AdminGrupoProveedorListener(JFrame owner) {
		this.owner = owner;
	}
	public void actionPerformed(ActionEvent e) {
		try {
//			AdminGrupoProveedor adminGrupoProveedor = new AdminGrupoProveedor(this.owner);
//			adminGrupoProveedor.open();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.owner, ex.getMessage());
		}
		
	}
}

class AdminGrupoBienListener implements ActionListener {
	private JFrame owner;
	public AdminGrupoBienListener(JFrame owner) {
		this.owner = owner;
	}
	public void actionPerformed(ActionEvent e) {
		try {
//			AdminGrupoBien adminGrupoBien = new AdminGrupoBien(this.owner);
//			adminGrupoBien.open();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.owner, ex.getMessage());
		}
		
	}
}

class AdminFolioLibroDiarioListener implements ActionListener {
	private JFrame owner;
	
	public AdminFolioLibroDiarioListener(JFrame owner) {
		this.owner = owner;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			AdminFolioLibroDiario adminFolioLibroDiario = new AdminFolioLibroDiario(this.owner);
			adminFolioLibroDiario.open();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.owner, ex.getMessage());
		}
	}
}

class AdminBienListener implements ActionListener {
	private JFrame owner;
	public AdminBienListener(JFrame owner) {
		this.owner = owner;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			AdminBien adminBien = new AdminBien(this.owner);
			adminBien.open();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.owner, ex.getMessage());
		}
		
	}
}


class AdminProveedorListener implements ActionListener {
	private JFrame owner;
	public AdminProveedorListener(JFrame owner) {
		this.owner = owner;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			AdminProveedor adminProveedor = new AdminProveedor(this.owner);
			adminProveedor.open();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.owner, ex.getMessage());
		}
		
	}
}

class AdminTipoOrdenCompraListener implements ActionListener {
	private JFrame owner;
	public AdminTipoOrdenCompraListener(JFrame owner) {
		this.owner = owner;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			AdminTipoOrdenCompra adminTipoOrdenCompra = new AdminTipoOrdenCompra (this.owner);
			adminTipoOrdenCompra.open();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.owner, ex.getMessage());
		}
	}
	
}

class AdminOrdenCompraListener implements ActionListener {
	private JFrame owner;
	public AdminOrdenCompraListener(JFrame owner) {
		this.owner = owner;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			AdminOrdenCompra adminOrdenCompra = new AdminOrdenCompra (this.owner);
			adminOrdenCompra.open();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.owner, ex.getMessage());
		}
	}
	
}

class AdminTipoModificadorListener implements ActionListener {
	private JFrame owner;
	public AdminTipoModificadorListener(JFrame owner) {
		this.owner = owner;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			AdminTipoModificador adminTipoModificador = new AdminTipoModificador(this.owner);
			adminTipoModificador.open();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.owner, ex.getMessage());
		}
	}
	
}