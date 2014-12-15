package com.trascender.contabilidad.recurso.persistent;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "PARAMETRO_RETENCION")
public class ParametroRetencion implements Serializable {

	public static final long serialVersionUID = 277723878664902637L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_parametro_retencion")
	@SequenceGenerator(name = "gen_id_parametro_retencion", sequenceName = "gen_id_parametro_retencion",allocationSize = 1)
	@Column(name="ID_PARAMETRO_RETENCION")
	private long idParametroRetencion = -1;
	private String nombre;
	
	@Column(name = "IMPORTE_MINIMO")
	private Double importeMinimo = new Double(0);
	
	private Double porcentaje;
	
	@Column(name = "DEDUCIR_IVA")
	private Boolean deducirIVA;
	
	private Double alicuota;
	
	public long getIdParametroRetencion() {
		return idParametroRetencion;
	}
	
	public void setIdParametroRetencion(long idParametroRetencion) {
		this.idParametroRetencion = idParametroRetencion;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Double getImporteMinimo() {
		return importeMinimo;
	}
	
	public void setImporteMinimo(Double importeMinimo) {
		this.importeMinimo = importeMinimo;
	}
	
	public Double getPorcentaje() {
		return porcentaje;
	}
	
	public void setPorcentaje(Double porcentaje) {
		this.porcentaje = porcentaje;
	}
	
	public Boolean isDeducirIVA() {
		return deducirIVA;
	}
	
	public void setDeducirIVA(Boolean deducirIVA) {
		this.deducirIVA = deducirIVA;
	}
	
	public Double getAlicuota() {
		return alicuota;
	}

	public void setAlicuota(Double alicuota) {
		this.alicuota = alicuota;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idParametroRetencion ^ (idParametroRetencion >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ParametroRetencion other = (ParametroRetencion) obj;
		if (idParametroRetencion != other.idParametroRetencion) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "ParametroRetencion [" + nombre + ", importeMinimo="
				+ importeMinimo + ", porcentaje=" + porcentaje + ", alicuota="
				+ alicuota + ", deducirIVA=" + deducirIVA + "]";
	}
}
