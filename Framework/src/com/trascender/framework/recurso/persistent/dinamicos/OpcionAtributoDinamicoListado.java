package com.trascender.framework.recurso.persistent.dinamicos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.trascender.framework.util.AuditoriaIndirecta;
import com.trascender.framework.util.EntidadTrascender;

@Entity
@Table(name = "OPCION_ATRI_DINAM_LISTADO")
public class OpcionAtributoDinamicoListado implements Serializable, AuditoriaIndirecta{
	
	private static final long serialVersionUID = -2336146480688073935L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_opcion_atri_listado")
	@SequenceGenerator(name = "gen_id_opcion_atri_listado", sequenceName = "gen_id_opcion_atri_listado",allocationSize = 1)
	@Column(name="id_opcion_atri_listado")
	private long idOpcionAtriListado = -1;
	
	private String valor;
	
	@ManyToOne
	@JoinColumn(name = "ID_PLANTILLA_ATRIBUTO_DINAMICO")
	private PlantillaAtributoDinamico plantillaAtributoDinamico;
	
	public PlantillaAtributoDinamico getPlantillaAtributoDinamico() {
		return plantillaAtributoDinamico;
	}

	public void setPlantillaAtributoDinamico(
			PlantillaAtributoDinamico pPlantillaAtributoDinamico) {
		plantillaAtributoDinamico = pPlantillaAtributoDinamico;
	}

	public long getIdOpcionAtriListado() {
		return idOpcionAtriListado;
	}

	public void setIdOpcionAtriListado(long pIdOpcionAtriListado) {
		idOpcionAtriListado = pIdOpcionAtriListado;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String pValor) {
		valor = pValor;
	}
	
	@Override
	public String toString() {
		return valor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ (int) (idOpcionAtriListado ^ (idOpcionAtriListado >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		if(this.getIdOpcionAtriListado() == -1){
			return (super.equals(obj) && (System.identityHashCode(this) == System.identityHashCode(obj)));
		}
		OpcionAtributoDinamicoListado other = (OpcionAtributoDinamicoListado) obj;
		if (idOpcionAtriListado != other.idOpcionAtriListado)
			return false;
		return true;
	}

	public EntidadTrascender getEntidadTrascender() {
		return this.plantillaAtributoDinamico;
	}

	public String getNombrePropiedad() {
		return "Opción";
	}

	public boolean concatenaNombre() {
		return false;
	}
	
}
