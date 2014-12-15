package com.trascender.caja.gui.abmConceptoMovimientoCajaChica;

import java.util.List;

import com.trascender.caja.gui.main.CajaGUI;
import com.trascender.contabilidad.recurso.persistent.ConceptoMovimientoCajaChica;
import com.trascender.contabilidad.system.interfaces.SystemAdministracionCajaChica;
import com.trascender.gui.framework.model.TAbstractBusquedaModel;
import com.trascender.gui.framework.util.Conversor;

public class ConceptoMoviminetoCajaChicaBusquedaModel extends TAbstractBusquedaModel<ConceptoMovimientoCajaChica> {

	private String nombre;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ConceptoMovimientoCajaChica> buscar() throws Exception {
		SystemAdministracionCajaChica locSystem = CajaGUI.getInstance().getAdminSystemsCaja().getSystemAdministracionCajaChica();
		List<ConceptoMovimientoCajaChica> locLista = locSystem.findListaConceptosCajaChica(this.getNombre());
		return locLista;
	}

	@Override
	public void reiniciar() {
		this.setNombre(null);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = Conversor.getNullSiVacio(nombre);
	}
}
