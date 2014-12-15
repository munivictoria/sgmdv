package com.trascender.caja.gui.abmCajaChica;

import java.util.List;

import com.trascender.caja.gui.main.CajaGUI;
import com.trascender.contabilidad.recurso.persistent.CajaChica;
import com.trascender.contabilidad.system.interfaces.SystemAdministracionCajaChica;
import com.trascender.gui.framework.model.TAbstractBusquedaModel;

public class CajaChicaBusquedaModel extends TAbstractBusquedaModel<CajaChica> {

	private String Nombre;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CajaChica> buscar() throws Exception {
		SystemAdministracionCajaChica locSystem = CajaGUI.getInstance().getAdminSystemsCaja().getSystemAdministracionCajaChica();
		List<CajaChica> locLista = locSystem.findListaCajaChica(this.getNombre());
		return locLista;
	}

	@Override
	public void reiniciar() {
		this.setNombre(null);
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

}
