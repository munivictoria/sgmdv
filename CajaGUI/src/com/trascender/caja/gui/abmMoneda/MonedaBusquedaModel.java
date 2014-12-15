package com.trascender.caja.gui.abmMoneda;

import java.util.List;

import com.trascender.caja.gui.main.CajaGUI;
import com.trascender.contabilidad.recurso.persistent.Moneda;
import com.trascender.contabilidad.system.interfaces.SystemAdministracionPlanillaDiaria;
import com.trascender.gui.framework.model.TAbstractBusquedaModel;
import com.trascender.gui.framework.util.Conversor;

public class MonedaBusquedaModel extends TAbstractBusquedaModel<Moneda> {
	
	private String Nombre;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Moneda> buscar() throws Exception {
		SystemAdministracionPlanillaDiaria locSystem = CajaGUI.getInstance().getAdminSystemsCaja().getSystemAdministracionPlanillaDiaria();
		List<Moneda> locLista = locSystem.findListaMoneda(this.getNombre());
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
		Nombre = Conversor.getNullSiVacio(nombre);
	}
}
