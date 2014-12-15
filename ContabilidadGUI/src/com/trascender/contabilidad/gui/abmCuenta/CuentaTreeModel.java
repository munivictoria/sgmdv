package com.trascender.contabilidad.gui.abmCuenta;

import javax.swing.tree.DefaultMutableTreeNode;

import com.trascender.contabilidad.gui.abmPlanDeCuenta.NodoCuenta;
import com.trascender.contabilidad.gui.abmPlanDeCuenta.NodoPlanCuenta;
import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.contabilidad.recurso.persistent.PlanDeCuenta;
import com.trascender.gui.framework.model.TAbstractTreeModel;

public class CuentaTreeModel extends TAbstractTreeModel<Cuenta> {
	
	private static final long serialVersionUID = -3080635352885924589L;
	
	
	/**
	 * Crea la estructura de arbol a partir de un plan de cuenta
	 * @param root
	 */
	public CuentaTreeModel(PlanDeCuenta root) {
		super(new NodoPlanCuenta(root));
		for (Cuenta locCuenta: root.getListaCuentasSinPadres()){
			this.addCuenta(this.getRoot(), locCuenta);
		}
	}
	
	
	/**
	 * Agrega recursivamente las cuentas
	 * @param pNodoPadre
	 * @param pCuenta
	 */
	private void addCuenta(DefaultMutableTreeNode pNodoPadre, Cuenta pCuenta) {
		NodoCuenta locNodoCuenta = this.getNewNodo(pCuenta);
		pNodoPadre.add(locNodoCuenta);
		for (Cuenta cadaCuenta:pCuenta.getCuentasHijos()){
			this.addCuenta(locNodoCuenta, cadaCuenta);
		}
	}
	
	
	@Override
	public NodoCuenta getNewNodo(Cuenta pObjeto) {
		return new NodoCuenta(pObjeto);
	}
	
	@Override
	public NodoPlanCuenta getRoot() {
		return (NodoPlanCuenta)super.getRoot();
	}
}
