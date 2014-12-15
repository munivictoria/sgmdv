package com.trascender.caja.gui.abmMoneda;

import com.trascender.caja.gui.main.CajaGUI;
import com.trascender.contabilidad.recurso.persistent.Moneda;
import com.trascender.contabilidad.recurso.persistent.Moneda.Tipo;
import com.trascender.gui.framework.model.TAbstractABMModel;

public class MonedaABMModel extends TAbstractABMModel<Moneda> {

	@Override
	public void agregar() throws Exception {
		this.objetoABM = CajaGUI.getInstance().getAdminSystemsCaja().getSystemAdministracionPlanillaDiaria().addMoneda(this.getObjetoABM());
	}

	@Override
	public void eliminar() throws Exception {
		CajaGUI.getInstance().getAdminSystemsCaja().getSystemAdministracionPlanillaDiaria().deleteMoneda(this.getObjetoABM());
	}

	@Override
	public void modificar() throws Exception {
		this.objetoABM = CajaGUI.getInstance().getAdminSystemsCaja().getSystemAdministracionPlanillaDiaria().updateMoneda(this.getObjetoABM());
	}

	public String getNombre() {
		return this.objetoABM.getNombre();
	}

	public void setNombre(String nombre) {
		this.objetoABM.setNombre(nombre);
	}

	public Tipo getTipo() {
		return this.objetoABM.getTipo();
	}

	public void setTipo(Tipo tipo) {
		this.objetoABM.setTipo(tipo);
	}

}
