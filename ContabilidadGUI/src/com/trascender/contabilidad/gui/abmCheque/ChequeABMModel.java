package com.trascender.contabilidad.gui.abmCheque;

import java.util.Date;

import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.Cheque;
import com.trascender.contabilidad.recurso.persistent.CuentaBancaria;
import com.trascender.gui.framework.model.TAbstractABMModel;

public class ChequeABMModel extends TAbstractABMModel<Cheque>{

	@Override
	public void agregar() throws Exception {
		this.objetoABM = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionEgresos().addCheque(this.getObjetoABM());
	}

	@Override
	public void eliminar() throws Exception {
		ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionEgresos().deleteCheque(this.getObjetoABM());
	}

	@Override
	public void modificar() throws Exception {
		this.objetoABM = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionEgresos().updateCheque(this.getObjetoABM());
	}

	public Date getFechaEmision() {
		return this.objetoABM.getFechaEmision();
	}

	public void setFechaEmision(Date fechaEmision) {
		this.objetoABM.setFechaEmision(fechaEmision);
	}

	public Date getFechaPago() {
		return this.objetoABM.getFechaPago();
	}

	public void setFechaPago(Date fechaPago) {
		this.objetoABM.setFechaPago(fechaPago);
	}

	public Double getImporte() {
		return this.objetoABM.getImporte();
	}

	public void setImporte(Double imorte) {
		this.objetoABM.setImporte(imorte);
	}

	public String getNumeroCheque() {
		return this.objetoABM.getNumeroCheque();
	}

	public void setNumeroCheque(String numeroCheque) {
		this.objetoABM.setNumeroCheque(numeroCheque);
	}
	
	public Boolean isPostdatado() {
		return this.objetoABM.isPostdatado();
	}

	public void setPostdatado(Boolean postdatado) {
		this.objetoABM.setPostdatado(postdatado);
	}
	
	public CuentaBancaria getCuentaBancaria() {
		return this.objetoABM.getCuentaBancaria();
	}

	public void setCuentaBancaria(CuentaBancaria cuentaBancaria) {
		this.objetoABM.setCuentaBancaria(cuentaBancaria);
	}
}
