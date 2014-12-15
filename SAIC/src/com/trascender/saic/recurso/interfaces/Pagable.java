package com.trascender.saic.recurso.interfaces;

import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.saic.recurso.persistent.RegistroCancelacion;

public interface Pagable {
	public Long getId();
	public Persona getPersona();
	public Double getMonto();
	public String getNombre();
	public void setRegistroCancelacion(RegistroCancelacion pRegistroCancelacion);
	public RegistroCancelacion getRegistroCancelacion();
}
