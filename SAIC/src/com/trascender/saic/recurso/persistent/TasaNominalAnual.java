package com.trascender.saic.recurso.persistent;

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

@Entity
@Table(name = "TASA_NOMINAL_ANUAL")
public class TasaNominalAnual implements Serializable{

	private static final long serialVersionUID = 116845673703731499L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="gen_id_tasa_nominal_anual")
	@SequenceGenerator(name = "gen_id_tasa_nominal_anual", sequenceName = "gen_id_tasa_nominal_anual", allocationSize = 1)
	@Column(name = "ID_TASA_NOMINAL_ANUAL")
	private long idTasaNominalAnual = -1;
	
	@Column(name = "CUOTAS_HASTA")
	private Integer cuotasHasta;
	
	private Double interes;
	
	@ManyToOne
	@JoinColumn(name = "id_plantilla_plan_de_pago")
	private PlantillaPlanDePago plantilla;

	public Integer getCuotasHasta() {
		return cuotasHasta;
	}

	public void setCuotasHasta(Integer cuotasHasta) {
		this.cuotasHasta = cuotasHasta;
	}

	public Double getInteres() {
		return interes;
	}

	public void setInteres(Double interes) {
		this.interes = interes;
	}

	public PlantillaPlanDePago getPlantilla() {
		return plantilla;
	}

	public void setPlantilla(PlantillaPlanDePago plantilla) {
		this.plantilla = plantilla;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ (int) (idTasaNominalAnual ^ (idTasaNominalAnual >>> 32));
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
		TasaNominalAnual other = (TasaNominalAnual) obj;
		if (idTasaNominalAnual != other.idTasaNominalAnual)
			return false;
		return true;
	}
	
}
