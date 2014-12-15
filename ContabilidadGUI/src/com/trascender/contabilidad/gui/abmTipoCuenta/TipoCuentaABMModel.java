package com.trascender.contabilidad.gui.abmTipoCuenta;

import java.util.List;

import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.TipoCuenta;
import com.trascender.contabilidad.recurso.persistent.TipoCuenta.Abreviatura;
import com.trascender.contabilidad.recurso.persistent.TipoCuenta.Opera;
import com.trascender.gui.framework.model.TAbstractABMModel;

public class TipoCuentaABMModel extends TAbstractABMModel<TipoCuenta> {
	
	@Override
	public void agregar() throws Exception {
		this.objetoABM = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().addTipoCuenta(this.getObjetoABM());
	}
	
	@Override
	public void modificar() throws Exception {
		this.objetoABM = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().updateTipoCuenta(this.getObjetoABM());
	}
	
	@Override
	public void eliminar() throws Exception {
		ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().deleteTipoCuenta(this.getObjetoABM());
	}

	public Abreviatura getAbreviatura() {
		return this.getObjetoABM().getAbreviatura();
	}
	public void setAbreviatura(Abreviatura abreviatura) {
		this.getObjetoABM().setAbreviatura(abreviatura);
	}

	public String getNombre() {
		return this.getObjetoABM().getNombre();
	}
	public void setNombre(String nombre) {
		this.getObjetoABM().setNombre(nombre);
	}

	public Opera getOperaAsientos() {
		return this.getObjetoABM().getOperaAsientos();
	}
	public void setOperaAsientos(Opera operaAsientos) {
		this.getObjetoABM().setOperaAsientos(operaAsientos);
	}

	public Opera getOperaMovimientosCaja() {
		return this.getObjetoABM().getOperaMovimientosCaja();
	}
	public void setOperaMovimientosCaja(Opera operaMovimientosCaja) {
		this.getObjetoABM().setOperaMovimientosCaja(operaMovimientosCaja);
	}
	public List<TipoCuenta> getListaTipoCuentaExcluidos() {
		return this.getObjetoABM().getListaTipoCuentaExcluidos();
	}
	public void setListaTipoCuentaExcluidos(List<TipoCuenta> pLista) {
		this.getObjetoABM().setListaTipoCuentaExcluidos(pLista);
	}
}
