package com.trascender.habilitaciones.recurso.persistent;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "PLANTILLA_DOC_HABILITANTE")
@Inheritance(strategy = InheritanceType.JOINED)
public class PlantillaDocHabilitante implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="gen_id_plantilla_doc_hab")
	@SequenceGenerator(name = "gen_id_plantilla_doc_hab", sequenceName = "gen_id_plantilla_doc_hab", allocationSize = 1)
	@Column(name = "ID_PLANTILLA_DOC_HABILITANTE")
	private long idPlantillaDocHabilitante=-1;
	
//	@ManyToOne(cascade=CascadeType.ALL) //FIXME ver si es necesaria la cascada o no
	@ManyToOne
	@JoinColumn(name = "ID_PLANTILLA_OBLIGACION", nullable = false)
	private PlantillaObligacion raiz;
	
	@ManyToOne
	@JoinColumn(name = "ID_PADRE")
	private PlantillaDocHabCompuesto padre;
	
	@Column(nullable = false)
	private String nombre;
	
	public long getIdPlantillaDocHabilitante() {
		return idPlantillaDocHabilitante;
	}
	public void setIdPlantillaDocHabilitante(long idPlantillaDocHabilitante) {
		this.idPlantillaDocHabilitante = idPlantillaDocHabilitante;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public PlantillaDocHabCompuesto getPadre() {
		return padre;
	}
	public void setPadre(PlantillaDocHabCompuesto padre) {
		if (!padre.getHijos().contains(this)){
			padre.getHijos().add(this);
		}
		this.padre = padre;
	}
	
	public PlantillaObligacion getRaiz() {
		return raiz;
	}
	public void setRaiz(PlantillaObligacion pRaiz) {
		this.raiz = pRaiz;
	}
	
	@Override
	public int hashCode() {
		if (this.getIdPlantillaDocHabilitante()==-1){
			return super.hashCode();
		}
		else{
			return (this.getClass().getName()+this.getIdPlantillaDocHabilitante()).hashCode();		
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this){
			return true;
		}
		if (!(obj instanceof PlantillaDocHabilitante)){
			return false;
		}
		PlantillaDocHabilitante locPlantilla=(PlantillaDocHabilitante)obj;
		if (this.getNombre()!=null){
			return this.getNombre().equals(locPlantilla.getNombre());
		}
		else {
			return (locPlantilla.getNombre()!=null);
		}
	}
	@Override
	public String toString() {
		return this.getNombre();
	}
}
