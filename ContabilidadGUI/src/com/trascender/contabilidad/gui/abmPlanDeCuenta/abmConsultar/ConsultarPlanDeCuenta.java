package com.trascender.contabilidad.gui.abmPlanDeCuenta.abmConsultar;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JDialog;

import com.trascender.contabilidad.gui.abmPlanDeCuenta.PlanDeCuentaABMModel;
import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.contabilidad.recurso.persistent.PlanDeCuenta;

public class ConsultarPlanDeCuenta extends ABMPlanDeCuentaConsultar {

	private PlanDeCuentaTableModelConsultar tableModel = new PlanDeCuentaTableModelConsultar();
	private PlanDeCuentaABMModel abmModel = new PlanDeCuentaABMModel();
	private ConsultarPlanDeCuentaView view;
	private ArrayList<Cuenta> listaCuentasFinal = new ArrayList<Cuenta>();

	public ConsultarPlanDeCuenta(JDialog owner, PlanDeCuenta pPlanDeCuenta)
			throws Exception {
		this.view = new ConsultarPlanDeCuentaView(owner);
		this.getAbmModel().setObjetoABM(pPlanDeCuenta);
		this.actualizarABMModel();
		this.actualizarView();
		this.init();
	}

	@Override
	protected void init() {
		super.init();
		this.setVisibleBtonAceptar();
		this.setTextBtnCancelar();
	}

	@Override
	public PlanDeCuentaABMModel getAbmModel() {
		return this.abmModel;
	}

	protected PlanDeCuentaTableModelConsultar getTableModel() {
		return this.tableModel;
	}

	@Override
	protected ConsultarPlanDeCuentaView getView() {
		return this.view;
	}

	private void setVisibleBtonAceptar() {
		this.getView().getPnlPie().getBtnAceptar().setVisible(false);
	}

	public void setTextBtnCancelar() {
		this.getView().getPnlPie().getBtnCancelar().setText(
				MessagesContabilidad.getString("Application.btnCerrar"));
		this.getView().getPnlPie().getBtnCancelar().setMnemonic(
				MessagesContabilidad.getChar("Application.btnCerrarMnemonic"));
		this.getView().getPnlPie().getBtnCancelar().setToolTipText(
				MessagesContabilidad.getString("Application.btnCerrarToolTip"));
	}

	@Override
	public void actualizarView() {

	}

	@Override
	public void actualizarABMModel() {
//		ArrayList<Cuenta> listaCuentas = new ArrayList<Cuenta>();
//		try {
//			Set<Cuenta> cuentasPadres = this.getAbmModel().getObjetoABM().getListaCuentas();
//			Iterator<Cuenta> iterator = cuentasPadres.iterator();
//			while (iterator.hasNext()) {
//				Cuenta cuentasHijos = (Cuenta) iterator.next();
//				try {
//					listaCuentasFinal.add(cuentasHijos);
//					listaCuentas = this.obtengoListadoRecursivo(cuentasHijos.getCuentasHijos());
//				} catch (Exception e) {
//				}
//			}
//			this.getTableModel().setListaObjetos(listaCuentasFinal);
//		} catch (Exception e) {
//		}
		this.getTableModel().setListaObjetos(new ArrayList<Cuenta>(this.getAbmModel().getObjetoABM().getListaCuentas()));
	}

	public ArrayList<Cuenta> obtengoListadoRecursivo(Set<Cuenta> cuentaX) {
		ArrayList<Cuenta> listaCuentas = new ArrayList<Cuenta>();
		ArrayList<Cuenta> lCuentas = new ArrayList<Cuenta>();

		Iterator<Cuenta> iterator = cuentaX.iterator();
		while (iterator.hasNext()) {
			Cuenta cuentasHijosDeX = (Cuenta) iterator.next();
			listaCuentasFinal.add(cuentasHijosDeX);
			Set<Cuenta> hijos = cuentasHijosDeX.getCuentasHijos();
			if (!hijos.isEmpty()) {
				lCuentas = obtengoListadoRecursivo(hijos);
			}
		}
		return listaCuentasFinal;
	}

	public void instanciarTableModel() {
		this.getView().setTableModel(this.tableModel);
	}

}
