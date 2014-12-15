package com.trascender.contabilidad.gui.abmDebitoBancario;

import java.util.Date;

import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.CuentaBancaria;
import com.trascender.contabilidad.recurso.persistent.Debito;
import com.trascender.gui.framework.model.TAbstractABMModel;

public class DebitoBancarioABMModel extends TAbstractABMModel<Debito>{

	@Override
	public void agregar() throws Exception {
		this.objetoABM = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionEgresos().addDebito(this.getObjetoABM());
	}

	@Override
	public void eliminar() throws Exception {
		ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionEgresos().deleteDebito(this.getObjetoABM());
	}

	@Override
	public void modificar() throws Exception {
		this.objetoABM = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionEgresos().updateDebito(this.getObjetoABM());
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

	public void setImporte(Double imorte) {
		this.objetoABM.setImporte(imorte);
	}
	
	public CuentaBancaria getCuentaBancaria() {
		return this.objetoABM.getCuentaBancaria();
	}

	public void setCuentaBancaria(CuentaBancaria pCuentaBancaria) {
		this.objetoABM.setCuentaBancaria(pCuentaBancaria);
	}
}
