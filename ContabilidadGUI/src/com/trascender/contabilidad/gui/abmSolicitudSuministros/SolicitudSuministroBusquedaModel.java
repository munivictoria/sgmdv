package com.trascender.contabilidad.gui.abmSolicitudSuministros;

import java.util.List;

import com.trascender.compras.recurso.filtros.FiltroSolicitudSuministro;
import com.trascender.compras.recurso.persistent.suministros.Bien;
import com.trascender.compras.recurso.persistent.suministros.SolicitudSuministro;
import com.trascender.compras.recurso.persistent.suministros.SolicitudSuministro.Estado;
import com.trascender.compras.system.interfaces.SystemAdministracionSolicitudSuministro;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.framework.recurso.persistent.Area;
import com.trascender.gui.framework.model.TAbstractBusquedaModel;

public class SolicitudSuministroBusquedaModel extends TAbstractBusquedaModel<SolicitudSuministro> {

	private Area area;
	private Bien bienAsociado;
	private Estado estado;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SolicitudSuministro> buscar() throws Exception {
		SystemAdministracionSolicitudSuministro locSystem = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionSolicitudSuministro();
		FiltroSolicitudSuministro locFiltro = new FiltroSolicitudSuministro();
		locFiltro.setArea(this.getArea());
		locFiltro.setBien(this.getBienAsociado());
		locFiltro.setEstado(this.getEstado());
		List<SolicitudSuministro> locLista = locSystem.findListadoSolicitudSuministro(locFiltro).getListaResultados();
		return locLista;
	}

	@Override
	public void reiniciar() {
		this.setArea(null);
		this.setBienAsociado(null);
		this.setEstado(null);
		
		fireActualizarDatos();
	}
	
	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public Bien getBienAsociado() {
		return bienAsociado;
	}

	public void setBienAsociado(Bien bienAsociado) {
		this.bienAsociado = bienAsociado;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

}
