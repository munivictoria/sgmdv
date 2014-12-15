package com.trascender.habilitaciones.recurso.persistent;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "PLANTILLA_DOC_HAB_COMPUESTO")
@PrimaryKeyJoinColumn(name = "ID_PLANTILLA_DOC_HABILITANTE")
public class PlantillaDocHabCompuesto extends PlantillaDocHabilitante{

	public static final long serialVersionUID = 316427361745309643L;
	
	private String descripcion;
	
	@OneToMany(mappedBy = "padre", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<PlantillaDocHabilitante> hijos=new HashSet<PlantillaDocHabilitante>();

	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public Set<PlantillaDocHabilitante> getHijos() {
		return hijos;
	}
	public void setHijos(Set<PlantillaDocHabilitante> hijos) {
		this.hijos = hijos;
	}
	
	
}
