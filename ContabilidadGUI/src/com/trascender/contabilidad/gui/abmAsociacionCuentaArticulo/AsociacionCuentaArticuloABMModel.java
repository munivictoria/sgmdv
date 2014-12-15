package com.trascender.contabilidad.gui.abmAsociacionCuentaArticulo;

import com.trascender.compras.recurso.persistent.inventario.Articulo;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.contabilidad.recurso.persistent.CuentaArticulo;
import com.trascender.framework.recurso.transients.Periodo;
import com.trascender.gui.framework.model.TAbstractABMModel;

public class AsociacionCuentaArticuloABMModel extends TAbstractABMModel<CuentaArticulo>{
	
	@Override
	public void agregar() throws Exception {
		this.objetoABM = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().addCuentaArticulo(this.getObjetoABM());
	}

	@Override
	public void modificar() throws Exception {
		this.objetoABM = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().updateCuentaArticulo(this.getObjetoABM());
	}

	@Override
	public void eliminar() throws Exception {

	}
	
	public void setCuenta(Cuenta pCuenta){
		this.objetoABM.setCuenta(pCuenta);
	}
	
	public Cuenta getCuenta(){
		return this.objetoABM.getCuenta();
	}
	
	public void setPeriodo(Periodo pPeriodo){
		this.objetoABM.setPeriodo(pPeriodo);
	}
	
	public Periodo getPeriodo(){
		return this.objetoABM.getPeriodo();
	}
	
	public Articulo getArticulo(){
		return this.objetoABM.getArticulo();
	}
	
	public void setArticulo(Articulo pArticulo){
		this.objetoABM.setArticulo(pArticulo);
	}

}
