package com.trascender.contabilidad.gui.abmPersonaJuridica;

import java.util.List;

import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.framework.recurso.filtros.FiltroPersonaJuridica;
import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.framework.recurso.persistent.PersonaJuridica;
import com.trascender.framework.system.interfaces.SystemPersonaJuridica;
import com.trascender.gui.framework.model.TAbstractBusquedaModel;

public class PersonaJuridicaBusquedaModel extends TAbstractBusquedaModel<PersonaJuridica> {
	
	private String cuim;
	private String razonSocial;
	private PersonaFisica titular;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PersonaJuridica> buscar() throws Exception {
		SystemPersonaJuridica locSystem = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemPersonaJuridica();
		FiltroPersonaJuridica locFiltro = new FiltroPersonaJuridica();
		locFiltro.setTitular(this.getTitular());
		locFiltro.setRazonSocial(this.getRazonSocial());
		locFiltro.setCuit(this.getCuim());
		List<PersonaJuridica> locLista = locSystem.findPersonaJuridica(locFiltro).getListaResultados();
		return locLista;
	}
	@Override
	public void reiniciar() {
		this.setCuim(null);
		this.setRazonSocial(null);
		this.setTitular(null);
		
		this.fireActualizarDatos();
	}
	public String getCuim() {
		return cuim;
	}
	public void setCuim(String cuim) {
		this.cuim = cuim;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public PersonaFisica getTitular() {
		return titular;
	}
	public void setTitular(PersonaFisica titular) {
		this.titular = titular;
	}

	
}
