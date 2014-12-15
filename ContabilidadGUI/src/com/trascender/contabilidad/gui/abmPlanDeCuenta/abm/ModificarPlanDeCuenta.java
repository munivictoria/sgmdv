package com.trascender.contabilidad.gui.abmPlanDeCuenta.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.contabilidad.gui.abmCuenta.CuentaTreeModel;
import com.trascender.contabilidad.gui.abmPlanDeCuenta.PlanDeCuentaABMModel;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.contabilidad.recurso.persistent.PlanDeCuenta;
import com.trascender.gui.framework.main.AppManager;

public class ModificarPlanDeCuenta extends ABMPlanDeCuenta {

	private ModificarPlanDeCuentaView view;
	private PlanDeCuentaABMModel abmModel = new PlanDeCuentaABMModel();
	private CuentaTreeModel treeModel;
	
	public ModificarPlanDeCuenta(JFrame owner, PlanDeCuenta pPlanDeCuenta) {
		this.view = new ModificarPlanDeCuentaView(owner);
		this.getAbmModel().setObjetoABM(pPlanDeCuenta);
		this.treeModel = new CuentaTreeModel(pPlanDeCuenta);
		this.init();
	}
	
	public ModificarPlanDeCuenta(JDialog owner, PlanDeCuenta pPlanCuenta) {
		this.view = new ModificarPlanDeCuentaView(owner);
		this.getAbmModel().setObjetoABM(pPlanCuenta);
		this.treeModel = new CuentaTreeModel(pPlanCuenta);
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		//this.getView().getPnlVerticalBotonesArbol().getBtnModificar().setVisible(false);
		this.getView().getPnlVerticalBotonesArbol().getBtnEliminar().setText(MessagesContabilidad.getString("Application.btnEliminar"));
		this.getView().getPnlVerticalBotonesArbol().getBtnEliminar().setMnemonic(MessagesContabilidad.getChar("Application.btnEliminarMnemonic"));
		this.getView().getPnlVerticalBotonesArbol().getBtnQuitarTodos().setVisible(false);
		this.setListener();
	}	
	
	private void setListener(){
		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnModificarListener(this));
		this.getView().getPnlVerticalBotonesArbol().getBtnAgregar().addActionListener(new BtnAgregarNodoListener(this));
		this.getView().getPnlVerticalBotonesArbol().getBtnModificar().addActionListener(new BtnModificarNodoListener(this));
		this.getView().getPnlVerticalBotonesArbol().getBtnEliminar().addActionListener(new BtnQuitarNodoListener(this));
		this.getView().getPnlVerticalBotonesArbol().getBtnQuitarTodos().addActionListener(new BtnQuitarTodosListener(this));
		this.getView().getPnlPie().getBtnCancelar().addActionListener(new BtnCancelarListener(this));
	}
	
	@Override
	public PlanDeCuentaABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public CuentaTreeModel getTreeModel() {
		return this.treeModel;
	}	

	@Override
	public ModificarPlanDeCuentaView getView() {
		return this.view;
	}
	
	void modificarPlanDeCuenta() throws Exception {
		if (this.validarDatos()) {
			this.actualizarABMModel();
//			Iterator<Cuenta> iterator = this.getCuentasBorradas().iterator();
//			while (iterator.hasNext()) {
//				Cuenta cuenta = (Cuenta) iterator.next();
//				try {
//					Cuenta cta = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().getCuentaByID(cuenta.getIdCuenta());
//					ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().deleteCuenta(cta);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
			
			this.getAbmModel().modificar();		
			this.setOperacionRealizada(true);
			this.close();
		}
	}
	
	public void btnCerrar() {
		this.getAbmModel().getObjetoABM().setListaCuentas(this.getAbmModel().getTmpCuentas());
	}
	
}


class BtnModificarListener implements ActionListener {
	private ModificarPlanDeCuenta controller;
	public BtnModificarListener(ModificarPlanDeCuenta controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.modificarPlanDeCuenta();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnCancelarListener implements ActionListener {
	private ModificarPlanDeCuenta controller;
	public BtnCancelarListener(ModificarPlanDeCuenta controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.btnCerrar();
		} catch (Exception ex) {
			
		}
	}
}