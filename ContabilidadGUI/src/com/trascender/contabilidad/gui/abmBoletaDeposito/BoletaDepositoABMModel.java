package com.trascender.contabilidad.gui.abmBoletaDeposito;

import java.util.Date;

import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.BoletaDeposito;
import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.contabilidad.recurso.persistent.CuentaBancaria;
import com.trascender.gui.framework.model.TAbstractABMModel;

public class BoletaDepositoABMModel extends TAbstractABMModel<BoletaDeposito>{
	private Cuenta cuentaAfectada;
	
	@Override
	public void agregar() throws Exception {
		System.out.println("cuenta: " + this.cuentaAfectada);
		this.objetoABM = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionEgresos().addBoletaDeposito(this.getObjetoABM(), this.getCuentaAfectada());
	}

	@Override
	public void eliminar() throws Exception {
		
		ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionEgresos().deleteBoletaDeposito(this.getObjetoABM());
	}

	@Override
	public void modificar() throws Exception {
		this.objetoABM = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionEgresos().updateBoletaDeposito(this.getObjetoABM(), this.getCuentaAfectada());
	}

	public CuentaBancaria getCuentaBancaria() {
		return this.objetoABM.getCuentaBancaria();
	}

	public void setCuentaBancaria(CuentaBancaria cuentaBancaria) {
		this.objetoABM.setCuentaBancaria(cuentaBancaria);
	}

	public Date getFecha() {
		return this.objetoABM.getFecha();
	}

	public void setFecha(Date fecha) {
		this.objetoABM.setFecha(fecha);
	}

	public Double getImporte() {
		return this.objetoABM.getImporte();
	}

	public void setImporte(Double importe) {
		this.objetoABM.setImporte(importe);
	}

	public String getNumeroBoleta() {
		return this.objetoABM.getNumeroBoleta();
	}

	public void setNumeroBoleta(String numeroBoleta) {
		this.objetoABM.setNumeroBoleta(numeroBoleta);
	}

	public String getObservaciones() {
		return this.objetoABM.getObservaciones();
	}

	public void setObservaciones(String observaciones) {
		this.objetoABM.setObservaciones(observaciones);
	}

	public Cuenta getCuentaAfectada() {
		return cuentaAfectada;
	}
	
	public void setCuentaAfectada(Cuenta cuenta) {
		this.cuentaAfectada = cuenta;
	}
}
