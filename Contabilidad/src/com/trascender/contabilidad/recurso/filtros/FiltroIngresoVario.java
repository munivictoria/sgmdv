package com.trascender.contabilidad.recurso.filtros;

import java.util.Date;
import java.util.List;

import com.trascender.contabilidad.recurso.persistent.ConceptoIngresoVario;
import com.trascender.contabilidad.recurso.persistent.IngresoVario;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.saic.recurso.persistent.RegistroDeuda;
import com.trascender.saic.recurso.persistent.RegistroDeuda.EstadoRegistroDeuda;

public class FiltroIngresoVario extends FiltroAbstracto<IngresoVario>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2690564415976820215L;

	private Persona persona;
	private ConceptoIngresoVario conceptoIngresoVario;
	private RegistroDeuda.EstadoRegistroDeuda estado;
	private List<Long> listaIdPersonas;
	private Date fechaDesde, fechaHasta;
	
	public FiltroIngresoVario() {
	}
	
	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public FiltroIngresoVario(Persona persona,
			ConceptoIngresoVario conceptoIngresoVario, EstadoRegistroDeuda estado) {
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

	public RegistroDeuda.EstadoRegistroDeuda getEstado() {
		return estado;
	}

	public void setEstado(RegistroDeuda.EstadoRegistroDeuda estado) {
		this.estado = estado;
	}

	public List<Long> getListaIdPersonas() {
		return listaIdPersonas;
	}

	public void setListaIdPersonas(List<Long> listaIdPersonas) {
		this.listaIdPersonas = listaIdPersonas;
	}
	
}
