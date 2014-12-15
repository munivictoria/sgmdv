package com.trascender.contabilidad.recurso.persistent;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.trascender.framework.recurso.persistent.Persona;

@Entity
@Table(name = "ORDEN_PAGO_DEVOLUCION")
public class OrdenPagoDevolucion extends DocumentoEgreso {

	public static final long serialVersionUID = 6217998467406918111L;
	
	@ManyToOne
	@JoinColumn(name = "ID_PERSONA")
	private Persona persona;
	
	@OneToMany(mappedBy = "ordenPago", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<LineaOrdenPagoDevolucion> lineaOrdenPagoDev = new HashSet<LineaOrdenPagoDevolucion>();
	
	
	public Persona getPersona() {
		return persona;
	}
	
	public void setPersona(Persona persona) {
		this.persona = persona;
		
	}

	public Set<LineaOrdenPagoDevolucion> getLineaOrdenPagoDev() {
		return lineaOrdenPagoDev;
	}

	public void setLineaOrdenPagoDev(Set<LineaOrdenPagoDevolucion> lineaOrdenPagoDev) {
		this.lineaOrdenPagoDev = lineaOrdenPagoDev;
	}
}
