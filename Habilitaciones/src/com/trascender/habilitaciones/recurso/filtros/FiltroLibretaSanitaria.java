package com.trascender.habilitaciones.recurso.filtros;

import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.habilitaciones.recurso.persistent.shps.LibretaSanitaria;

public class FiltroLibretaSanitaria extends FiltroAbstracto<LibretaSanitaria>{
	private static final long serialVersionUID = -7006697944426564967L;
	
	private PersonaFisica personaFisica; 
	private Integer numeroLibretaSanitaria; 
	private boolean estado = true;
	
	public PersonaFisica getPersonaFisica() {
		return personaFisica;
	}
	public void setPersonaFisica(PersonaFisica personaFisica) {
		this.personaFisica = personaFisica;
	}
	public Integer getNumeroLibretaSanitaria() {
		return numeroLibretaSanitaria;
	}
	public void setNumeroLibretaSanitaria(Integer numeroLibretaSanitaria) {
		this.numeroLibretaSanitaria = numeroLibretaSanitaria;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
}
