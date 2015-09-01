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
@Table(name = "CONDONACION_REGISTRO_DEUDA")
public class CondonacionRegistroDeuda implements Serializable{
	private static final long serialVersionUID = 5392816168970689156L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="gen_id_condonacion_registro_deuda")
	@SequenceGenerator(name = "gen_id_condonacion_registro_deuda", sequenceName = "gen_id_condonacion_registro_deuda", allocationSize = 1)
	@Column(name = "ID_CONDONACION_REGISTRO_DEUDA")
	private long idCondonacionRegistroDeuda = -1;
	
	@ManyToOne
	@JoinColumn(name = "ID_REG_CANCELACION_POR_REF")
	private RegCancelacionPorRefinanciacion regCancelacionPorRefinanciacion;
	
	@ManyToOne
	@JoinColumn(name = "ID_REGISTRO_DEUDA")
	private RegistroDeuda registroDeuda;
	
	private Double porcentaje;

	public long getIdCondonacionRegistroDeuda() {
		return idCondonacionRegistroDeuda;
	}

	public void setIdCondonacionRegistroDeuda(long idCondonacionRegistroDeuda) {
		this.idCondonacionRegistroDeuda = idCondonacionRegistroDeuda;
	}

	public RegCancelacionPorRefinanciacion getRegCancelacionPorRefinanciacion() {
		return regCancelacionPorRefinanciacion;
	}

	public void setRegCancelacionPorRefinanciacion(
			RegCancelacionPorRefinanciacion regCancelacionPorRefinanciacion) {
		this.regCancelacionPorRefinanciacion = regCancelacionPorRefinanciacion;
	}

	public RegistroDeuda getRegistroDeuda() {
		return registroDeuda;
	}

	public void setRegistroDeuda(RegistroDeuda registroDeuda) {
		this.registroDeuda = registroDeuda;
	}

	public Double getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(Double porcentaje) {
		this.porcentaje = porcentaje;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ (int) (idCondonacionRegistroDeuda ^ (idCondonacionRegistroDeuda >>> 32));
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
		CondonacionRegistroDeuda other = (CondonacionRegistroDeuda) obj;
		if (this.idCondonacionRegistroDeuda == -1 && other.idCondonacionRegistroDeuda == -1) 
			return this == other;
		if (idCondonacionRegistroDeuda != other.idCondonacionRegistroDeuda)
			return false;
		return true;
	}
	

}
