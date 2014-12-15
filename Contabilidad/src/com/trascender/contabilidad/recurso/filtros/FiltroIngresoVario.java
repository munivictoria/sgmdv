package com.trascender.contabilidad.recurso.filtros;

import java.util.List;

import com.trascender.contabilidad.recurso.persistent.ConceptoIngresoVario;
import com.trascender.contabilidad.recurso.persistent.IngresoVario;
import com.trascender.contabilidad.recurso.persistent.IngresoVario.Estado;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.util.FiltroAbstracto;

public class FiltroIngresoVario extends FiltroAbstracto<IngresoVario>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2690564415976820215L;

	private Persona persona;
	private ConceptoIngresoVario conceptoIngresoVario;
	private IngresoVario.Estado estado;
	private List<Long> listaIdPersonas;
	
	public FiltroIngresoVario() {
	}

	public FiltroIngresoVario(Persona persona,
			ConceptoIngresoVario conceptoIngresoVario, Estado estado) {
		super();
		this.persona = persona;
		this.conceptoIngresoVario = conceptoIngresoVario;
		this.estado = estado;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public ConceptoIngresoVario getConceptoIngresoVario() {
		return conceptoIngresoVario;
	}

	public void setConceptoIngresoVario(ConceptoIngresoVario conceptoIngresoVario) {
		this.conceptoIngresoVario = conceptoIngresoVario;
	}

	public IngresoVario.Estado getEstado() {
		return estado;
	}

	public void setEstado(IngresoVario.Estado estado) {
		this.estado = estado;
	}

	public List<Long> getListaIdPersonas() {
		return listaIdPersonas;
	}

	public void setListaIdPersonas(List<Long> listaIdPersonas) {
		this.listaIdPersonas = listaIdPersonas;
	}
	
}
