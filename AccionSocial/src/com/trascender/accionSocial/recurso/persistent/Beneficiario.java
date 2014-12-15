package com.trascender.accionSocial.recurso.persistent;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.util.Util;

@Entity
@Table(name="BENEFICIARIO")
public class Beneficiario implements Serializable{

	/**
	 *Vinculos posibles: PADRE, MADRE, HIJO, HIJA, ESPOSO, ESPOSA, PAREJA  
	 */
	public enum Vinculo {
		PADRE, MADRE, HIJO, HIJA, ESPOSO, ESPOSA, PAREJA;
		
		public String toString() {
			return Util.capitalizeEnumName(this.name());
		};
	}
	
	/**
	 * Instruccion posible: NINGUNA, PRIMARIA, SECUNDARIA, UNIVERSITARIA, POSTGRADO
	 */
	public enum Instruccion {
		NINGUNA, PRIMARIA, SECUNDARIA, UNIVERSITARIA, POSTGRADO;
		
		public String toString() {
			return Util.capitalizeEnumName(this.name());
		};
	}
	
	public static final long serialVersionUID = -2722107780606781387L;

	@Id
	@GeneratedValue(generator="gen_id_beneficiario", strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(allocationSize = 1,name="gen_id_beneficiario", sequenceName="gen_id_beneficiario")
	@Column(name="ID_BENEFICIARIO")
	private long idBeneficiario = -1;
	
	
	private String ocupacion;
	private Double ingresos;
	private String salud;
	
	@Enumerated(EnumType.STRING)
	private Vinculo vinculo;
	
	@Enumerated(EnumType.STRING)
	private Instruccion instruccion;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ID_PERSONA")
	private Persona persona;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ID_OBRA_SOCIAL")
	private ObraSocial obraSocial;
	

	public long getIdBeneficiario() {
		return idBeneficiario;
	}

	public void setIdBeneficiario(long idBeneficiario) {
		this.idBeneficiario = idBeneficiario;
	}

	public String getOcupacion() {
		return ocupacion;
	}

	public void setOcupacion(String ocupacion) {
		this.ocupacion = ocupacion;
	}

	public Double getIngresos() {
		return ingresos;
	}

	public void setIngresos(Double ingresos) {
		this.ingresos = ingresos;
	}

	public String getSalud() {
		return salud;
	}

	public void setSalud(String salud) {
		this.salud = salud;
	}

	public Vinculo getVinculo() {
		return vinculo;
	}

	public void setVinculo(Vinculo vinculo) {
		this.vinculo = vinculo;
	}

	public Instruccion getInstruccion() {
		return instruccion;
	}

	public void setInstruccion(Instruccion instruccion) {
		this.instruccion = instruccion;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public ObraSocial getObraSocial() {
		return obraSocial;
	}

	public void setObraSocial(ObraSocial obraSocial) {
		this.obraSocial = obraSocial;
	}
	
	public String toString(){
		return persona.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ (int) (idBeneficiario ^ (idBeneficiario >>> 32));
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
		Beneficiario other = (Beneficiario) obj;
		if (idBeneficiario != other.idBeneficiario)
			return false;
		return true;
	}
	
}
