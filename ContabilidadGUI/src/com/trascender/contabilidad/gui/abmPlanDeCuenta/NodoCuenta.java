package com.trascender.contabilidad.gui.abmPlanDeCuenta;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;

import com.trascender.contabilidad.recurso.persistent.Cuenta;

public class NodoCuenta extends DefaultMutableTreeNode {
	
	private static final long serialVersionUID = -558079134316254059L;
	
	private Cuenta cuenta;
	
	public NodoCuenta(Cuenta pNodo) {
		this.cuenta = pNodo;
	}
	
	public Cuenta getCuenta() {
		return this.cuenta;
	}
	
	public void setCuenta(Cuenta pCuenta) {
		this.cuenta = pCuenta;
	}
	
	/**
	 * 
	 * @param pParent
	 */
	public void setParent(NodoCuenta pParent) {
		super.setParent(pParent);
		pParent.add(this.cuenta);
	}
	
	/**
	 * Agrega una cuenta como hija de la cuenta actual
	 * @param pCuenta cuenta hija a agregar
	 */
	public void add(Cuenta pCuenta){
		if (!this.cuenta.getCuentasHijos().contains(pCuenta)){
			this.cuenta.getCuentasHijos().add(pCuenta);
			pCuenta.setCuentaPadre(this.cuenta);
			pCuenta.setPlanDeCuenta(this.cuenta.getPlanDeCuenta());
		}
	}
	
	/**
	 * 
	 * @param pNodoPlanCuenta
	 */
	public void setParent(NodoPlanCuenta pNodoPlanCuenta){
		super.setParent(pNodoPlanCuenta);
		this.cuenta.setCuentaPadre(null);
		// ariel: if
		if (pNodoPlanCuenta != null) {
			this.cuenta.setPlanDeCuenta(pNodoPlanCuenta.getUserObject());
			pNodoPlanCuenta.add(this.cuenta);
		}
	}
	
	public void add(NodoCuenta newChild) {
		Cuenta locCuenta = newChild.getCuenta();
		this.add(locCuenta);
		super.add((MutableTreeNode)newChild);		
	}
	
	@Override
	public void setParent(MutableTreeNode newParent) {
		if (newParent instanceof NodoCuenta){
			this.setParent((NodoCuenta)newParent);
		}
		else{
			this.setParent((NodoPlanCuenta)newParent);
		}
	}
	
	@Override
	public String toString() {
		return this.cuenta.toString();
	}
	
	@Override
	public Cuenta getUserObject() {
		return this.cuenta;
	}
}