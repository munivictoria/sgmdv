package com.trascender.contabilidad.gui.abmDigestoMunicipal;

import java.util.List;

import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.framework.recurso.filtros.FiltroDigestoMunicipal;
import com.trascender.framework.recurso.persistent.DigestoMunicipal;
import com.trascender.framework.system.interfaces.SystemMunicipalidad;
import com.trascender.gui.framework.model.TAbstractBusquedaModel;

public class DigestoMunicipalBusquedaModel extends TAbstractBusquedaModel<DigestoMunicipal> {
	
	private String nombre;
	private DigestoMunicipal.Tipo tipo;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DigestoMunicipal> buscar() throws Exception {
		SystemMunicipalidad locSystem = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemMunicipalidad();
		FiltroDigestoMunicipal locFiltro = new FiltroDigestoMunicipal();
		locFiltro.setDescripcion(this.getNombre());
		locFiltro.setTipo(this.getTipo());
		List<DigestoMunicipal> locLista = locSystem.findListaDigestosMunicipales(locFiltro).getListaResultados();
		return locLista;
	}
	
	@Override
	public void reiniciar() {
		this.setNombre(null);
		this.setTipo(null);
		
		this.fireActualizarDatos();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public DigestoMunicipal.Tipo getTipo() {
		return tipo;
	}

	public void setTipo(DigestoMunicipal.Tipo tipo) {
		this.tipo = tipo;
	}

}
