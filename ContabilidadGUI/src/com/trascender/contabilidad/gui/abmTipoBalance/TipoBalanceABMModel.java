package com.trascender.contabilidad.gui.abmTipoBalance;

import java.util.Date;
import java.util.Set;

import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.contabilidad.recurso.persistent.TipoBalance;
import com.trascender.gui.framework.model.TAbstractABMModel;
import com.trascender.gui.framework.util.Conversor;

public class TipoBalanceABMModel extends TAbstractABMModel<TipoBalance>{

	TipoBalance loc = new TipoBalance();
	
	private String nombre;
	private Date fechaCreacion;
	private Set<Cuenta> listaDeCuentas; 
	

	@Override
	public void agregar() throws Exception {
		this.objetoABM = new TipoBalance();
		this.objetoABM.setNombre(this.getNombre());
		this.objetoABM.setFecha(this.getFechaCreacion());
		this.objetoABM.setListaCuentas(this.getListaDeCuentas());

		this.objetoABM = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().addTipoBalance(this.getObjetoABM());
	}

	@Override
	public void eliminar() throws Exception {
		ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().deleteTipoBalance(this.getObjetoABM());
	}

	@Override
	public void modificar() throws Exception {
		ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().updateTipoBalance(this.getObjetoABM());
	}
	
	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = Conversor.getNullSiVacio(nombre);
	}

	public Set<Cuenta> getListaDeCuentas() {
		return listaDeCuentas;
	}

	public void setListaDeCuentas(Set<Cuenta> listaDeCuentas) {
		this.listaDeCuentas = listaDeCuentas;
	}

	@Override
	public TipoBalance getObjetoABM() {
		if(objetoABM != null){
			this.objetoABM.setNombre(this.getNombre());
			this.objetoABM.setFecha(this.getFechaCreacion());
			this.objetoABM.setListaCuentas(this.getListaDeCuentas());
		}
		return objetoABM;
	}
	
	@Override
	public void setObjetoABM(TipoBalance objetoABM) {
		this.objetoABM = objetoABM;
		if(objetoABM != null){
			this.setNombre(this.objetoABM.getNombre());
			this.setFechaCreacion(this.objetoABM.getFecha());
			this.setListaDeCuentas(this.objetoABM.getListaCuentas());
		}
	}
}
