package com.trascender.contabilidad.gui.abmParametroRetencion;

import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.ParametroRetencion;
import com.trascender.gui.framework.model.TAbstractABMModel;

public class ParametroRetencionABMModel extends TAbstractABMModel<ParametroRetencion> {

	@Override
	public void agregar() throws Exception {
		this.objetoABM = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionEgresos().addParametroRetencion(this.getObjetoABM());
	}

	@Override
	public void eliminar() throws Exception {
		ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionEgresos().deleteParametroRetencion(this.getObjetoABM());
	}

	@Override
	public void modificar() throws Exception {
		this.objetoABM = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionEgresos().updateParametroRetencion(this.getObjetoABM());
	}

	public Double getAlicuota() {
		return this.objetoABM.getAlicuota();
	}

	public void setAlicuota(Double alicuota) {
		this.objetoABM.setAlicuota(alicuota);
	}

	public Boolean isDeducirIVA() {
		return this.objetoABM.isDeducirIVA();
	}

	public void setDeducirIVA(Boolean deducirIVA) {
		this.objetoABM.setDeducirIVA(deducirIVA);
	}

	public Double getImporteMinimo() {
		return this.objetoABM.getImporteMinimo();
	}

	public void setImporteMinimo(Double importeMinimo) {
		this.objetoABM.setImporteMinimo(importeMinimo);
	}

	public String getNombre() {
		return this.objetoABM.getNombre();
	}

	public void setNombre(String nombre) {
		this.objetoABM.setNombre(nombre);
	}

	public Double getPorcentaje() {
		return this.objetoABM.getPorcentaje();
	}

	public void setPorcentaje(Double porcentaje) {
		this.objetoABM.setPorcentaje(porcentaje);
	}

}
