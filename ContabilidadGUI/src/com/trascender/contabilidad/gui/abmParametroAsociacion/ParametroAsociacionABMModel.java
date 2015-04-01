package com.trascender.contabilidad.gui.abmParametroAsociacion;

import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.gui.framework.model.TAbstractABMModel;
import com.trascender.saic.recurso.persistent.ParametroAsociacion;

public class ParametroAsociacionABMModel extends TAbstractABMModel<ParametroAsociacion> {

	@Override
	public void agregar() throws Exception {
		this.objetoABM = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().addParametroAsociacion(this.getObjetoABM());
	}

	@Override
	public void eliminar() throws Exception {
		ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().deleteParametroAsociacion(this.getObjetoABM());
	}

	@Override
	public void modificar() throws Exception {
		System.out.println("entro al modelo para update ");
		this.objetoABM = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().updateParametroAsociacion(this.getObjetoABM());
	}

	public Double getPorcentaje() {
		return this.objetoABM.getPorcentaje();
	}

	public void setPorcentaje(Double porcentaje) {
		this.objetoABM.setPorcentaje(porcentaje);
	}
}
