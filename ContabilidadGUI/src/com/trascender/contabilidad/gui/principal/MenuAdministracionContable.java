package com.trascender.contabilidad.gui.principal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.trascender.contabilidad.gui.abmAsientoContable.AdminAsientoContable;
import com.trascender.contabilidad.gui.abmAsociacionCuentaArticulo.AdminAsociacionCuentaArticulo;
import com.trascender.contabilidad.gui.abmAsociacionCuentaConceptoIngresoVario.AdminAsociacionCuentaConceptoIngresoVario;
import com.trascender.contabilidad.gui.abmAsociacionCuentaInteresRecargo.AdminAsociacionCuentaInteresRecargo;
import com.trascender.contabilidad.gui.abmAsociacionCuentaModificador.AdminAsociacionCuentaModificador;
import com.trascender.contabilidad.gui.abmAsociacionCuentaRefinanciacion.AdminAsociacionRefinanciacion;
import com.trascender.contabilidad.gui.abmAsociacionCuentaSolicitudSuministro.AdminAsociacionCuentaSolicitudSuministro;
import com.trascender.contabilidad.gui.abmAsociacionCuentaTipoTasa.AdminAsociacionCuentaTipoTasa;
import com.trascender.contabilidad.gui.abmBalance.AdminBalance;
import com.trascender.contabilidad.gui.abmFactura.AdminFactura;
import com.trascender.contabilidad.gui.abmImportarPlanDeCuenta.AdminImportarPlanDeCuenta;
import com.trascender.contabilidad.gui.abmLibroDiario.AdminLibroDiario;
import com.trascender.contabilidad.gui.abmMayor.AdminMayor;
import com.trascender.contabilidad.gui.abmPlanDeCuenta.AdminPlanDeCuenta;
import com.trascender.contabilidad.gui.abmPresupuesto.AdminPresupuesto;
import com.trascender.contabilidad.gui.abmSubdiarioCaja.AdminSubdiarioCaja;
import com.trascender.contabilidad.gui.abmTipoBalance.AdminTipoBalance;
import com.trascender.contabilidad.gui.abmTipoCuenta.AdminTipoCuenta;
import com.trascender.gui.framework.main.AppManager;

public class MenuAdministracionContable extends JMenu {
	
	private static final long serialVersionUID = -6458584919129531119L;
	
	private JFrame owner;
	
	private JMenuItem miAdminTipoCuenta;
	private JMenuItem miAdminTipoBalance;
	private JMenuItem miAdminPlanDeCuenta;
	
	private JMenuItem miGenerarBalances;
	private JMenuItem miGenerarSubdiarioCaja;
	private JMenuItem miGenerarMayores;
	
	private JMenuItem miAdminLibroDiario;
	private JMenuItem miAdminPresupuesto;
	private JMenuItem miAdminAsientoContable;
	
	private JMenuItem miAdminAsociacionCuentaTipoTasa;
	private JMenuItem miAdminAsociacionCuentaModificador;
	private JMenuItem miAdminAsociacionCuentaInteresRecargo;
	private JMenuItem miAdminAsociacionCuentaRefinanciacion;
	private JMenuItem miAdminAsociacionCuentaSolicitudSuministro;
	private JMenuItem miAdminAsociacionCuentaConceptoIngresoVario;
	private JMenuItem miAdminAsociacionCuentaLineaFactura;
	private JMenuItem miAdminAsociacionCuentaArticulo;
	
	private JMenuItem miAdminImportarPlanDeCuenta;
	
	public MenuAdministracionContable(JFrame owner) {
		super();
		this.owner = owner;
		this.init();
		this.setListeners();
	}
	
	private void init() {
		this.setText("Admin. Contable");
		this.setMnemonic(KeyEvent.VK_C);
		
		this.miAdminPlanDeCuenta = new JMenuItem();
		this.miAdminPlanDeCuenta.setText("Planes de Cuenta");
		this.miAdminPlanDeCuenta.setMnemonic(KeyEvent.VK_C);
		//this.miAdminPlanDeCuenta.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0));
		this.add(this.miAdminPlanDeCuenta);
		
		this.miAdminTipoBalance = new JMenuItem();
		this.miAdminTipoBalance.setText("Plantillas de Balances");
		this.miAdminTipoBalance.setMnemonic(KeyEvent.VK_N);
		//this.miAdminTipoBalance.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0));
		this.add(this.miAdminTipoBalance);
		
		this.miAdminTipoCuenta = new JMenuItem();
		this.miAdminTipoCuenta.setText("Tipos de Cuenta");
		this.miAdminTipoCuenta.setMnemonic(KeyEvent.VK_T);
		//this.miAdminTipoCuenta.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
		this.add(this.miAdminTipoCuenta);
		
		this.addSeparator();
		
		this.miGenerarBalances = new JMenuItem();
		this.miGenerarBalances.setText("Generar Balances");
		this.miGenerarBalances.setMnemonic(KeyEvent.VK_B);
		this.add(this.miGenerarBalances);
		
		this.miGenerarMayores = new JMenuItem();
		this.miGenerarMayores.setText("Generar Mayores");
		this.miGenerarMayores.setMnemonic(KeyEvent.VK_M);
		this.add(this.miGenerarMayores);
		
		this.miGenerarSubdiarioCaja = new JMenuItem();
		this.miGenerarSubdiarioCaja.setText("Generar Subdiario de Caja");
		this.miGenerarSubdiarioCaja.setMnemonic(KeyEvent.VK_S);
		this.add(this.miGenerarSubdiarioCaja);
		
		this.addSeparator();
		
		this.miAdminLibroDiario = new JMenuItem();
		this.miAdminLibroDiario.setText("Libros Diarios");
		this.miAdminLibroDiario.setMnemonic(KeyEvent.VK_L);
		//this.miAdminLibroDiario.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F6, 0));
		this.add(this.miAdminLibroDiario);
		
		this.miAdminPresupuesto = new JMenuItem();
		this.miAdminPresupuesto.setText("Presupuestos");
		this.miAdminPresupuesto.setMnemonic(KeyEvent.VK_P);
		//this.miAdminPresupuesto.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F7, 0));
		this.add(this.miAdminPresupuesto);
		
		this.miAdminAsientoContable = new JMenuItem();
		this.miAdminAsientoContable.setText("Asientos Contables");
		this.miAdminAsientoContable.setMnemonic(KeyEvent.VK_A);
		//this.miAdminAsientoContable.setAccelerator(KeyStroke.getKeyStroke(KeyEvent., 0));
		this.add(this.miAdminAsientoContable);
		
		this.addSeparator();
		
		this.miAdminAsociacionCuentaTipoTasa = new JMenuItem();
		this.miAdminAsociacionCuentaTipoTasa.setText("Asociaciones Cuenta/Fórmula de Cálculo");
		//this.miAdminPlanCuenta.setMnemonic(KeyEvent.);
		//this.miAdminAsociacionCuentaTipoTasa.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F9, 0));
		this.add(this.miAdminAsociacionCuentaTipoTasa);
		
		this.miAdminAsociacionCuentaModificador =  new JMenuItem();
		this.miAdminAsociacionCuentaModificador.setText("Asociaciones Cuenta/Modificador");
		//this.miAdminAsociacionCuentaModificador.setMnemonic(KeyEvent.);
		//this.miAdminAsociacionCuentaModificador.setAccelerator(null);
		this.add(this.miAdminAsociacionCuentaModificador);
		
		this.miAdminAsociacionCuentaInteresRecargo = new JMenuItem();
		this.miAdminAsociacionCuentaInteresRecargo.setText("Asociaciones Cuenta/Interes-Recargo");
		this.add(this.miAdminAsociacionCuentaInteresRecargo);
		
		this.miAdminAsociacionCuentaRefinanciacion = new JMenuItem();
		this.miAdminAsociacionCuentaRefinanciacion.setText("Asociaciones Cuenta/Refinanciación");
		//this.miAdminAsociacionCuentaLineaOrdenCompra.setMnemonic(KeyEvent.);
		//this.miAdminAsociacionCuentaLineaOrdenCompra.setAccelerator(null);
		this.add(this.miAdminAsociacionCuentaRefinanciacion);
		
		this.miAdminAsociacionCuentaSolicitudSuministro = new JMenuItem();
		this.miAdminAsociacionCuentaSolicitudSuministro.setText("Asociaciones Cuenta/Solicitud de Suministro");
		//this.miAdminAsociacionCuentaLineaOrdenCompra.setMnemonic(KeyEvent.);
		//this.miAdminAsociacionCuentaLineaOrdenCompra.setAccelerator(null);
		this.add(this.miAdminAsociacionCuentaSolicitudSuministro);
		
		this.miAdminAsociacionCuentaConceptoIngresoVario = new JMenuItem();
		this.miAdminAsociacionCuentaConceptoIngresoVario.setText("Asociaciones Cuenta/Concepto Ingreso Vario");
		//this.miAdminAsociacionCuentaLineaOrdenCompra.setMnemonic(KeyEvent.);
		//this.miAdminAsociacionCuentaLineaOrdenCompra.setAccelerator(null);
		this.add(this.miAdminAsociacionCuentaConceptoIngresoVario);
		
		this.miAdminAsociacionCuentaLineaFactura = new JMenuItem();
		this.miAdminAsociacionCuentaLineaFactura.setText("Asociaciones Cuenta/Líneas de Factura");
		//this.miAdminAsociacionCuentaLineaOrdenCompra.setMnemonic(KeyEvent.);
		//this.miAdminAsociacionCuentaLineaOrdenCompra.setAccelerator(null);
		this.add(this.miAdminAsociacionCuentaLineaFactura);
		
		this.miAdminAsociacionCuentaArticulo = new JMenuItem();
		this.miAdminAsociacionCuentaArticulo.setText("Asociaciones Cuenta/Articulos");
		this.add(this.miAdminAsociacionCuentaArticulo);
		
		this.addSeparator();
		
		this.miAdminImportarPlanDeCuenta = new JMenuItem();
		this.miAdminImportarPlanDeCuenta.setText("Importar Plan de Cuenta");
		//this.miAdminImportarPlanDeCuenta.setMnemonic(KeyEvent.VK_C);
		//this.miAdminImportarPlanDeCuenta.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0));
		this.add(this.miAdminImportarPlanDeCuenta);
		
	}
	
	public void setListeners() {
		this.miAdminTipoCuenta.addActionListener(new AdminTipoCuentaListener(this.owner));
		this.miAdminTipoBalance.addActionListener(new AdminTipoBalanceListener(this.owner));
		this.miAdminPlanDeCuenta.addActionListener(new AdminPlanDeCuentaListener(this.owner));
		
		this.miGenerarBalances.addActionListener(new GenerarBalancesListener(this.owner));
		this.miGenerarSubdiarioCaja.addActionListener(new GenerarSubdiarioCajaListener(this.owner));
		this.miGenerarMayores.addActionListener(new GenerarMayoresListener(this.owner));
		
		this.miAdminLibroDiario.addActionListener(new AdminLibroDiarioListener(this.owner));
		this.miAdminPresupuesto.addActionListener(new AdminPresupuestoListener(this.owner));
		this.miAdminAsientoContable.addActionListener(new AdminAsientoContableListener(this.owner));
		
		this.miAdminAsociacionCuentaTipoTasa.addActionListener(new AdminAsociacionCuentaTipoTasaListener(this.owner));
		this.miAdminAsociacionCuentaModificador.addActionListener(new AdminAsociacionCuentaModificadorListener(this.owner));
		this.miAdminAsociacionCuentaInteresRecargo.addActionListener(new AdminAsociacionCuentaInteresRecargoListener(this.owner));
		this.miAdminAsociacionCuentaRefinanciacion.addActionListener(new AdminAsociacionCuentaRefinanciacionListener(this.owner));
		this.miAdminAsociacionCuentaSolicitudSuministro.addActionListener(new AdminAsociacionCuentaSolicitudSuministroListener(this.owner));
		this.miAdminAsociacionCuentaConceptoIngresoVario.addActionListener(new AdminAsociacionCuentaConceptoIngresoVarioListener(this.owner));
		this.miAdminAsociacionCuentaLineaFactura.addActionListener(new AdminAsociacionCuentaLineaFacturaListener(this.owner));
		this.miAdminAsociacionCuentaArticulo.addActionListener(new AdminAsociacionCuentaArticuloListener(this.owner));
		
		this.miAdminImportarPlanDeCuenta.addActionListener(new AdminImportarPlanDeCuentaListener(this.owner));
	}

	public JMenuItem getMiAdminAsientoContable() {
		return miAdminAsientoContable;
	}

	public JMenuItem getMiAdminAsociacionCuentaRefinanciacion() {
		return miAdminAsociacionCuentaRefinanciacion;
	}

	public JMenuItem getMiAdminAsociacionCuentaModificador() {
		return miAdminAsociacionCuentaModificador;
	}
	
	public JMenuItem getMiAdminAsociacionCuentaInteresRecargo(){
		return miAdminAsociacionCuentaInteresRecargo;
	}

	public JMenuItem getMiAdminAsociacionCuentaSolicitudSuministro() {
		return miAdminAsociacionCuentaSolicitudSuministro;
	}

	public JMenuItem getMiAdminAsociacionCuentaTipoTasa() {
		return miAdminAsociacionCuentaTipoTasa;
	}

	public JMenuItem getMiAdminLibroDiario() {
		return miAdminLibroDiario;
	}

	public JMenuItem getMiAdminPlanDeCuenta() {
		return miAdminPlanDeCuenta;
	}

	public JMenuItem getMiAdminPresupuesto() {
		return miAdminPresupuesto;
	}

	public JMenuItem getMiAdminTipoBalance() {
		return miAdminTipoBalance;
	}

	public JMenuItem getMiAdminTipoCuenta() {
		return miAdminTipoCuenta;
	}

	public JMenuItem getMiGenerarBalances() {
		return miGenerarBalances;
	}

	public JMenuItem getMiGenerarMayores() {
		return miGenerarMayores;
	}

	public JMenuItem getMiGenerarSubdiarioCaja() {
		return miGenerarSubdiarioCaja;
	}

	public JMenuItem getMiAdminAsociacionCuentaConceptoIngresoVario() {
		return miAdminAsociacionCuentaConceptoIngresoVario;
	}

	public JMenuItem getMiAdminImportarPlanDeCuenta() {
		return miAdminImportarPlanDeCuenta;
	}

	public JMenuItem getMiAdminAsociacionCuentaLineaFactura() {
		return miAdminAsociacionCuentaLineaFactura;
	}

	public JMenuItem getMiAdminAsociacionCuentaArticulo() {
		return miAdminAsociacionCuentaArticulo;
	}

}

class AdminTipoCuentaListener implements ActionListener {
	private JFrame owner;
	public AdminTipoCuentaListener(JFrame owner) {
		this.owner = owner;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			AdminTipoCuenta adminTipoCuenta = new AdminTipoCuenta(this.owner);
			adminTipoCuenta.open();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.owner, ex.getMessage());
		}
		
	}
}

class AdminPlanDeCuentaListener implements ActionListener{
	private JFrame owner;
	
	public AdminPlanDeCuentaListener(JFrame owner){
		this.owner = owner;
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			AdminPlanDeCuenta adminPlanDeCuenta = new AdminPlanDeCuenta(this.owner);
			adminPlanDeCuenta.open();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.owner, ex.getMessage());
		}
	}
	
}

class AdminTipoBalanceListener implements ActionListener {
	private JFrame owner;
	
	public AdminTipoBalanceListener(JFrame owner) {
		this.owner = owner;
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			AdminTipoBalance adminTipoBalance = new AdminTipoBalance(this.owner);
			adminTipoBalance.open();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.owner, ex.getMessage());
		}
	}
	
}

class AdminLibroDiarioListener implements ActionListener {
	private JFrame owner;
	
	public AdminLibroDiarioListener(JFrame owner) {
		this.owner = owner;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			AdminLibroDiario adminLibroDiario = new AdminLibroDiario(this.owner);
			adminLibroDiario.open();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.owner, ex.getMessage());
		}
	}
}

class AdminPresupuestoListener implements ActionListener {
	private JFrame owner;
	
	public AdminPresupuestoListener(JFrame owner) {
		this.owner = owner;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			AdminPresupuesto adminPresupuesto = new AdminPresupuesto(this.owner);
			adminPresupuesto.open();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.owner, ex.getMessage());
		}
	}
}

class AdminAsociacionCuentaTipoTasaListener implements ActionListener {
	private JFrame owner;
	
	public AdminAsociacionCuentaTipoTasaListener(JFrame owner) {
		this.owner = owner;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			AdminAsociacionCuentaTipoTasa adminTipoTasaCuenta = new AdminAsociacionCuentaTipoTasa(this.owner);
			adminTipoTasaCuenta.open();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.owner, ex.getMessage());
		}
	}
}

class AdminAsociacionCuentaModificadorListener implements ActionListener {
	private JFrame owner;
	
	public AdminAsociacionCuentaModificadorListener(JFrame owner) {
		this.owner = owner;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			AdminAsociacionCuentaModificador adminAsociacionCuentaModificador = new AdminAsociacionCuentaModificador(this.owner);
			adminAsociacionCuentaModificador.open();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.owner, ex.getMessage());
		}
	}
}

class AdminAsociacionCuentaInteresRecargoListener implements ActionListener {
	private JFrame owner;
	
	public AdminAsociacionCuentaInteresRecargoListener(JFrame owner) {
		this.owner = owner;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			AdminAsociacionCuentaInteresRecargo adminAsociacionCuentaInteresRecargo = new AdminAsociacionCuentaInteresRecargo (this.owner);
			adminAsociacionCuentaInteresRecargo.open();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.owner, ex.getMessage());
		}
	}
}


class AdminAsociacionCuentaRefinanciacionListener implements ActionListener {
	private JFrame owner;
	
	public AdminAsociacionCuentaRefinanciacionListener(JFrame owner) {
		this.owner = owner;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			AdminAsociacionRefinanciacion  adminAsociacionCuentaRefinanciacion = new AdminAsociacionRefinanciacion(this.owner);
			adminAsociacionCuentaRefinanciacion.open();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.owner, ex.getMessage());
		}
	}
}

class AdminAsociacionCuentaSolicitudSuministroListener implements ActionListener {
	private JFrame owner;

	public AdminAsociacionCuentaSolicitudSuministroListener(JFrame owner) {
		this.owner = owner;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			AdminAsociacionCuentaSolicitudSuministro adminAsociacionCuentaSolicitudSuministro = new AdminAsociacionCuentaSolicitudSuministro(this.owner);
			adminAsociacionCuentaSolicitudSuministro.open();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.owner, ex.getMessage());
		}
		
	}
	
}

class GenerarBalancesListener implements ActionListener {
	private JFrame owner;
	
	public GenerarBalancesListener(JFrame owner) {
		this.owner = owner;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			AdminBalance adminBalance = new AdminBalance(this.owner);
			adminBalance.open();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.owner, ex.getMessage());
		}
	}
}

class GenerarSubdiarioCajaListener implements ActionListener {
	private JFrame owner;
	
	public GenerarSubdiarioCajaListener(JFrame owner) {
		this.owner = owner;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			AdminSubdiarioCaja adminSubdiarioCaja = new AdminSubdiarioCaja(this.owner);
			adminSubdiarioCaja.open();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.owner, ex.getMessage());
		}
	}
}


class GenerarMayoresListener implements ActionListener {
	private JFrame owner;
	
	public GenerarMayoresListener(JFrame owner) {
		this.owner = owner;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			AdminMayor adminMayor = new AdminMayor(this.owner);
			adminMayor.open();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.owner, ex.getMessage());
		}
	}
}


class AdminAsientoContableListener implements ActionListener {
	private JFrame owner;
	
	public AdminAsientoContableListener(JFrame owner) {
		this.owner = owner;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			AdminAsientoContable adminAsientoContable = new AdminAsientoContable(this.owner);
			adminAsientoContable.open();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.owner, ex.getMessage());
		}
	}
}

class AdminAsociacionCuentaConceptoIngresoVarioListener implements ActionListener {
	private JFrame owner;
	
	public AdminAsociacionCuentaConceptoIngresoVarioListener(
			JFrame owner) {
		this.owner = owner;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			AdminAsociacionCuentaConceptoIngresoVario adminAsociacionCuentaConceptoIngresoVario = new AdminAsociacionCuentaConceptoIngresoVario(this.owner);
			adminAsociacionCuentaConceptoIngresoVario.open();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.owner, ex.getMessage());
		}
	}
	
}

class AdminImportarPlanDeCuentaListener implements ActionListener {
	private JFrame owner;

	public AdminImportarPlanDeCuentaListener(JFrame owner) {
		this.owner = owner;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			AdminImportarPlanDeCuenta adminImportarPlanDeCuenta = new AdminImportarPlanDeCuenta(this.owner);
			adminImportarPlanDeCuenta.open();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.owner, ex.getMessage());
		}
	}
	
}

class AdminAsociacionCuentaArticuloListener implements ActionListener{
	private JFrame owner;
	
	public AdminAsociacionCuentaArticuloListener(JFrame owner){
		this.owner = owner;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try{
			AdminAsociacionCuentaArticulo adminAsociacionCuentaArticulo = new AdminAsociacionCuentaArticulo(this.owner);
			adminAsociacionCuentaArticulo.open();
		} catch (Exception ex){
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(owner, ex.getMessage());
		}
	}
}

class AdminAsociacionCuentaLineaFacturaListener implements ActionListener {
	private JFrame owner;
	
	public AdminAsociacionCuentaLineaFacturaListener(JFrame owner) {
		this.owner = owner;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			AdminFactura adminFactura = new AdminFactura(this.owner);
			adminFactura.open();
			
//			AdminAsociacionCuentaLineaFactura adminAsociacionCuentaLineaFactura = new AdminAsociacionCuentaLineaFactura(this.owner);
//			adminAsociacionCuentaLineaFactura.open();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.owner, ex.getMessage());
		}
	}
	
}
