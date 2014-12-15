package com.trascender.contabilidad.gui.abmAsociacionCuentaArticulo.abmEliminar;

import java.util.Date;

import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.BajaArticulo;
import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.contabilidad.recurso.persistent.CuentaArticulo;
import com.trascender.gui.framework.model.TAbstractABMModel;

public class BajaArticuloABMModel extends TAbstractABMModel<BajaArticulo>{

	@Override
	public void agregar() throws Exception {
		if (this.objetoABM.getIdBajaArticulo() == -1){
			this.objetoABM = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().addBajaArticulo(getObjetoABM());
		}
	}

	@Override
	public void modificar() throws Exception {
	}

	@Override
	public void eliminar() throws Exception {
	}
	
	public void setFecha(Date pFecha){
		this.objetoABM.setFecha(pFecha);
	}
	
	public Date getFecha(){
		return this.objetoABM.getFecha();
	}
	
	public void setComentario(String pComentario){
		this.objetoABM.setComentario(pComentario);
	}
	
	public String getComentario(){
		return this.objetoABM.getComentario();
	}
	
	public void setCuentaIngreso(Cuenta pCuenta){
		this.objetoABM.setCuentaIngreso(pCuenta);
	}
	
	public Cuenta getCuentaIngreso(){
		return this.objetoABM.getCuentaIngreso();
	}
	
	public void setCuentaEgreso(Cuenta pCuenta){
		this.objetoABM.setCuentaEgreso(pCuenta);
	}
	
	public Cuenta getCuentaEgreso(){
		return this.objetoABM.getCuentaEgreso();
	}
	
	public void setCuentaArticulo(CuentaArticulo pCuentaArticulo){
		this.objetoABM.setCuentaArticulo(pCuentaArticulo);
	}
	
	public CuentaArticulo getCuentaArticulo(){
		return objetoABM.getCuentaArticulo();
	}

}
