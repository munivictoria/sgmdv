package com.trascender.contabilidad.gui.abmCuentaBancaria;

import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.Banco;
import com.trascender.contabilidad.recurso.persistent.CuentaBancaria;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.gui.framework.model.TAbstractABMModel;

public class CuentaBancariaAbmModel extends TAbstractABMModel<CuentaBancaria> {
	
	@Override
	public void agregar() throws Exception {
		this.objetoABM = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConciliacionBancaria().addCuentaBancaria(this.getObjetoABM());
	}

	@Override
	public void eliminar() throws Exception {
		ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConciliacionBancaria().deleteCuentaBancaria(this.getObjetoABM());
	}

	@Override
	public void modificar() throws Exception {
		this.objetoABM = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConciliacionBancaria().updateCuentaBancaria(this.getObjetoABM());
	}

	public Banco getBanco() {
		return this.objetoABM.getBanco();
	}

	public void setBanco(Banco banco) {
		this.objetoABM.setBanco(banco);
	}

	public String getNumero() {
		return this.objetoABM.getNumero();
	}

	public void setNumero(String numero) {
		this.objetoABM.setNumero(numero);
	}

	public Boolean isPropia() {
		return this.objetoABM.isPropia();
	}

	public void setPropia(Boolean propia) {
		this.objetoABM.setPropia(propia);
	}

	public String getTipoCuenta() {
		return this.objetoABM.getTipoCuenta();
	}

	public void setTipoCuenta(String tipoCuenta) {
		this.objetoABM.setTipoCuenta(tipoCuenta);
	}

	public Persona getTitularCuentaBancaria() {
		return this.objetoABM.getTitularCuentaBancaria();
	}

	public void setTitularCuentaBancaria(Persona titularCuentaBancaria) {
		this.objetoABM.setTitularCuentaBancaria(titularCuentaBancaria);
	}

}
