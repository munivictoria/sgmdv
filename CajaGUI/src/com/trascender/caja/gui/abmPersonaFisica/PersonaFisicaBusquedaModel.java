package com.trascender.caja.gui.abmPersonaFisica;

import java.util.List;

import com.trascender.framework.recurso.filtros.FiltroPersonaFisica;
import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.framework.recurso.persistent.PersonaFisica.TipoDocumento;
import com.trascender.framework.system.interfaces.SystemPersonaFisica;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.model.TAbstractBusquedaModel;

public class PersonaFisicaBusquedaModel extends TAbstractBusquedaModel<PersonaFisica> {
	
	private String cuil;
	private TipoDocumento tipoDocumento;
	private String numeroDocumento;
	private String apellido;
	private String nombre;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PersonaFisica> buscar() throws Exception {
		SystemPersonaFisica locSystem = AppManager.getInstance().getAdminSystems().getSystemPersonaFisica();
		String tipoDoc = (this.getTipoDocumento() != null)?this.getTipoDocumento().toString():null;
		FiltroPersonaFisica locFiltro = new FiltroPersonaFisica();
		locFiltro.setCuil(this.getCuil());
		locFiltro.setNombre(this.getNombre());
		locFiltro.setApellido(this.getApellido());
		locFiltro.setTipoDocumento(this.getTipoDocumento());
		locFiltro.setNumeroDocumento(this.getNumeroDocumento());
		
		List<PersonaFisica> locLista = locSystem.findPersonaFisica(locFiltro).getListaResultados();
		return locLista;
	}

	@Override
	public void reiniciar() {
		this.setCuil(null);
		this.setTipoDocumento(null);
		this.setNumeroDocumento(null);
		this.setApellido(null);
		this.setNombre(null);
		
		this.fireActualizarDatos();
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCuil() {
		return cuil;
	}

	public void setCuil(String cuil) {
		this.cuil = cuil;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
}
