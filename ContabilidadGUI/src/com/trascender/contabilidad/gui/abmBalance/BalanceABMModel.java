package com.trascender.contabilidad.gui.abmBalance;

import java.util.Date;
import java.util.Set;

import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.Balance;
import com.trascender.contabilidad.recurso.persistent.CuentaHistoricaBalance;
import com.trascender.contabilidad.recurso.persistent.TipoBalance;
import com.trascender.contabilidad.system.interfaces.SystemAdministracionConsultaContable;
import com.trascender.gui.framework.model.TAbstractABMModel;

public class BalanceABMModel extends TAbstractABMModel<Balance> {
	
	public void generarBalance() throws Exception {
		SystemAdministracionConsultaContable locSystem = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable();
		this.objetoABM = locSystem.generarBalance(this.getTipoBalance());
	}
	
	@Override
	public void agregar() throws Exception {
		SystemAdministracionConsultaContable locSystem = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable();
		this.objetoABM = locSystem.addBalance(this.objetoABM);
	}

	@Override
	public void eliminar() throws Exception {
		SystemAdministracionConsultaContable locSystem = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable();
		locSystem.deleteBalance(this.objetoABM);
	}

	@Override
	public void modificar() throws Exception {
		
	}

	public TipoBalance getTipoBalance() {
		return this.objetoABM.getTipoBalance();
	}

	public void setTipoBalance(TipoBalance tipoBalance) {
		this.objetoABM.setTipoBalance(tipoBalance);
	}

	public Date getFecha() {
		return this.objetoABM.getFecha();
	}

	public void setFecha(Date fecha) {
		this.objetoABM.setFecha(fecha);
	}

	public Set<CuentaHistoricaBalance> getListaCuentaHistoricoBalance() {
		return this.objetoABM.getListaCuentaHistoricoBalance();
	}

	public void setListaCuentaHistoricoBalance(
			Set<CuentaHistoricaBalance> listaCuentaHistoricoBalance) {
		this.objetoABM.setListaCuentaHistoricoBalance(listaCuentaHistoricoBalance);
	}

	public String getNombre() {
		return this.objetoABM.getNombre();
	}

	public void setNombre(String nombre) {
		this.objetoABM.setNombre(nombre);
	}

}
