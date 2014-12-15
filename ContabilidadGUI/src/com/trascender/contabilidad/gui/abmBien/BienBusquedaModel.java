package com.trascender.contabilidad.gui.abmBien;

import java.util.List;

import com.trascender.compras.recurso.filtros.FiltroBien;
import com.trascender.compras.recurso.persistent.suministros.Bien;
import com.trascender.compras.recurso.persistent.suministros.GrupoBien;
import com.trascender.compras.system.interfaces.SystemAdministracionBienes;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.gui.framework.model.TAbstractBusquedaModel;

public class BienBusquedaModel extends TAbstractBusquedaModel<Bien> {
	
	private GrupoBien grupoBien;
	private String nombre;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Bien> buscar() throws Exception {
		SystemAdministracionBienes locSystem = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionBienes();
		FiltroBien locFiltro = new FiltroBien();
		locFiltro.setNombre(this.getNombre());
		List<Bien> locLista = locSystem.findListadoBienes(locFiltro).getListaResultados();
		return locLista;
	}

	@Override
	public void reiniciar() {
		this.setGrupoBien(null);
		this.setNombre(null);
		
		this.fireActualizarDatos();
	}

	public GrupoBien getGrupoBien() {
		return grupoBien;
	}

	public void setGrupoBien(GrupoBien grupoBien) {
		this.grupoBien = grupoBien;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
