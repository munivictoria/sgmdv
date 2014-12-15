package com.trascender.contabilidad.gui.abmCuentaBancaria;

import java.util.List;

import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.Banco;
import com.trascender.contabilidad.recurso.persistent.CuentaBancaria;
import com.trascender.contabilidad.system.interfaces.SystemAdministracionConciliacionBancaria;
import com.trascender.gui.framework.model.TAbstractBusquedaModel;

public class CuentaBancariaBusquedaModel extends TAbstractBusquedaModel<CuentaBancaria> {
	
	private String tipoCuenta;
	private String numero;
	private boolean propia;
	private Banco banco;

	@SuppressWarnings("unchecked")
	@Override
	public List<CuentaBancaria> buscar() throws Exception {
		SystemAdministracionConciliacionBancaria locSystem = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConciliacionBancaria();
		List<CuentaBancaria> listaCuentaBancaria = locSystem.findListaCuentaBancaria(this.getTipoCuenta(), this.getNumero(), this.isPropia(), this.getBanco());
		return listaCuentaBancaria;
	}

	@Override
	public void reiniciar() {
		this.setTipoCuenta(null);
		this.setNumero(null);
		this.setPropia(false);
		this.setBanco(null);
		
		this.fireActualizarDatos();
	}

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public boolean isPropia() {
		return propia;
	}

	public void setPropia(boolean propia) {
		this.propia = propia;
	}


}
