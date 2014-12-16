package com.trascender.contabilidad.gui.abmConceptoIngresoVario;

import java.util.List;

import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.filtros.FiltroConceptoIngresoVario;
import com.trascender.contabilidad.recurso.persistent.ConceptoIngresoVario;
import com.trascender.contabilidad.system.interfaces.SystemAdministracionIngresos;
import com.trascender.gui.framework.model.TAbstractBusquedaModel;

public class ConceptoSelladoAdministrativoBusquedaModel extends TAbstractBusquedaModel<ConceptoIngresoVario> {

	private String nombre;

	@Override
	public List<ConceptoIngresoVario> buscar() throws Exception {
		SystemAdministracionIngresos locSystem = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionIngresos();
		FiltroConceptoIngresoVario locFiltro = new FiltroConceptoIngresoVario();
		locFiltro.setNombre(this.getNombre());
		List<ConceptoIngresoVario> locLista = locSystem.findListaConceptoIngresoVario(locFiltro).getListaResultados();
		return locLista;
	}

	@Override
	public void reiniciar() {
		this.setNombre(null);
		
		this.fireActualizarDatos();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
