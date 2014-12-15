package com.trascender.contabilidad.gui.abmPlanDeCuenta;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;

import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.contabilidad.recurso.persistent.PlanDeCuenta;

public class NodoPlanCuenta extends DefaultMutableTreeNode{
	
	private static final long serialVersionUID = -6334917825409654346L;
	
	private PlanDeCuenta planDeCuenta;
	
	public NodoPlanCuenta(PlanDeCuenta pPlanDeCuenta){
		this.planDeCuenta = pPlanDeCuenta;
	}
	
	public void insert(NodoCuenta newChild, int childIndex) {
		super.insert(newChild, childIndex);
		this.add(newChild.getCuenta());
	}
	
	public void add(Cuenta cuenta) {
		if (!this.planDeCuenta.getListaCuentas().contains(cuenta)){
			this.planDeCuenta.getListaCuentas().add(cuenta);
			cuenta.setPlanDeCuenta(this.planDeCuenta);
		}
	}
	
	public void add(NodoCuenta newChild) {
		if (!this.planDeCuenta.getListaCuentas().contains(newChild.getCuenta())){
			super.add((MutableTreeNode)newChild);
			this.add(newChild.getCuenta());
		}
	}
	
	@Override
	public String toString() {
		// ariel:
		//return this.planDeCuenta.toString();
		return "Lista de Cuentas";
	}
	
	@Override
	public PlanDeCuenta getUserObject() {
		return this.planDeCuenta;
	}
	
	@Override
	public boolean isRoot() {
		return true;
	}
}