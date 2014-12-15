package com.trascender.contabilidad.gui.abmLibroBanco;

import java.util.Date;

import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.CuentaBancaria;
import com.trascender.contabilidad.recurso.persistent.LibroBanco;
import com.trascender.gui.framework.model.TAbstractABMModel;

public class LibroBancoABMModel extends TAbstractABMModel<LibroBanco> {

	Date fechaGeneracion;
	
	public void generar() throws Exception {
		this.objetoABM = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConciliacionBancaria().generarLibroBancoDiario(this.getObjetoABM(), this.getFechaGeneracion());
	}
	
	@Override
	public void agregar() throws Exception {
		this.objetoABM = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConciliacionBancaria().addLibroBanco(this.getObjetoABM());
	}

	@Override
	public void eliminar() throws Exception {
		ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConciliacionBancaria().deleteLibroBanco(this.getObjetoABM());
	}

	@Override
	public void modificar() throws Exception {
		this.objetoABM = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConciliacionBancaria().updateLibroBanco(this.getObjetoABM());
	}

	public CuentaBancaria getCuentaBancaria() {
		return this.objetoABM.getCuentaBancaria();
	}

	public void setCuentaBancaria(CuentaBancaria cuentaBancaria) {
		this.objetoABM.setCuentaBancaria(cuentaBancaria);
	}

	public String getNombre() {
		return this.objetoABM.getNombre();
	}

	public void setNombre(String nombre) {
		this.objetoABM.setNombre(nombre);
	}

	public Date getFechaGeneracion() {
		return fechaGeneracion;
	}

	public void setFechaGeneracion(Date fechaGeneracion) {
		this.fechaGeneracion = fechaGeneracion;
	}
	
}
