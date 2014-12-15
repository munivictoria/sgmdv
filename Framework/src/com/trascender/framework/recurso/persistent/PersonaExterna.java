package com.trascender.framework.recurso.persistent;

import java.util.List;

import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;

/**
 * @deprecated ESTA CLASE QUEDA EN ESDADO deprecated POR QUE NO TIENE USO ALGUNO
 * SIQUIERA TIENE UNA TABLA EN LA BASE
 * @author jsantacruz
 */
public class PersonaExterna extends Persona {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7481157175102798613L;
	private long idPersonaExterna=-1;
	private String nombre;
	
	public long getIdPersonaExterna() {
		return idPersonaExterna;
	}
	
	public void setIdPersonaExterna(long pIdPersonaExterna) {
		idPersonaExterna = pIdPersonaExterna;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String pNombre) {
		nombre = pNombre;
	}

	@Override
	public void addAtributoDinamico(AtributoDinamico<?> pAtributoDinamico) {
	}

	@Override
	public List<AtributoDinamico<?>> getListaAtributosDinamicos() {
		return null;
	}

	@Override
	public void setListaAtributosDinamicos(
			List<AtributoDinamico<?>> pListaAtributosDinamicos) {
	}

	@Override
	public String getToStringCompleto() {
		return this.nombre;
	}

	@Override
	public String getDenominacion() {
		return nombre;
	}
	
}
