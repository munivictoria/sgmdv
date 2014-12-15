package com.trascender.caja.gui.abmCajaChica;

import com.trascender.caja.gui.main.CajaGUI;
import com.trascender.contabilidad.recurso.persistent.CajaChica;
import com.trascender.gui.framework.model.TAbstractABMModel;

public class CajaChicaABMModel extends TAbstractABMModel<CajaChica> {
	
	@Override
	public void agregar() throws Exception {
		this.objetoABM.setUsuario(CajaGUI.getInstance().getUsuario());
		this.objetoABM = CajaGUI.getInstance().getAdminSystemsCaja().getSystemAdministracionCajaChica().addCajaChica(this.objetoABM);
	}

	@Override
	public void eliminar() throws Exception {
		CajaGUI.getInstance().getAdminSystemsCaja().getSystemAdministracionCajaChica().deleteCajaChica(this.objetoABM);
	}

	@Override
	public void modificar() throws Exception {
		this.objetoABM = CajaGUI.getInstance().getAdminSystemsCaja().getSystemAdministracionCajaChica().updateCajaChica(this.objetoABM);
	}
	
	
	public String getNombre() {
		return this.objetoABM.getNombre();
	}
	
	public void setNombre(String nombre) {
		this.objetoABM.setNombre(nombre);
	}

	public Double getImporteReposicion() {
		return this.objetoABM.getImporteReposicion();
	}

	public void setImporteReposicion(Double importeReposicion) {
		this.objetoABM.setImporteReposicion(importeReposicion);
	}

}
