package com.trascender.catastro.recurso.persistent;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.util.AuditoriaIndirecta;
import com.trascender.framework.util.EntidadTrascender;
import com.trascender.framework.util.Util;

@Entity
@Table(name = "FIRMANTE_PLANO_CONSTRUCCION")
public class FirmantePlanoConstruccion implements Serializable, AuditoriaIndirecta{

	private static final long serialVersionUID = 8079486497181126379L;
	
	public enum Cargo{
		PROYECTISTA,
		CALCULISTA,
		DIRECTOR_DE_OBRA,
		RELEVADOR,
		CONSTRUCTOR;

		@Override
		public String toString() {
			return Util.capitalizeEnumName(this.name());
		}
	}
	
	@Id
	@SequenceGenerator(allocationSize = 1, name="GEN_ID_FIRMANTE_PLANO_CONSTRUCCION", sequenceName="GEN_ID_FIRMANTE_PLANO_CONSTRUCCION")
	@GeneratedValue(generator="GEN_ID_FIRMANTE_PLANO_CONSTRUCCION", strategy=GenerationType.SEQUENCE)
	@Column(name="ID_FIRMANTE_PLANO_CONSTRUCCION")
	private long idFirmantePlanoConstruccion = -1;
	
	@ManyToOne
	@JoinColumn(name="ID_PLANO_CONSTRUCCION",nullable=false)
	private PlanoConstruccion planoConstruccion;
	
	@ManyToOne
	@JoinColumn(name="ID_PERSONA",nullable=false)
	private Persona persona;
	
	@Column(name = "FECHA_FIRMA")
	private Date fechaFirma;

	@Enumerated(EnumType.STRING)
	private Cargo cargo;
	
	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Date getFechaFirma() {
		return fechaFirma;
	}

	public void setFechaFirma(Date fechaFirma) {
		this.fechaFirma = fechaFirma;
	}

	public long getIdFirmantePlanoConstruccion() {
		return idFirmantePlanoConstruccion;
	}

	public void setIdFirmantePlanoConstruccion(long idFirmantePlanoConstruccion) {
		this.idFirmantePlanoConstruccion = idFirmantePlanoConstruccion;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public PlanoConstruccion getPlanoConstruccion() {
		return planoConstruccion;
	}

	public void setPlanoConstruccion(PlanoConstruccion planoConstruccion) {
		this.planoConstruccion = planoConstruccion;
	}
	
	@Override
	public String toString() {
		return "FirmantePlanoConstruccion [persona=" + persona + ", cargo="
				+ cargo + "]";
	}

	public EntidadTrascender getEntidadTrascender() {
		return this.planoConstruccion;
	}

	public String getNombrePropiedad() {
		return "Firmante ["+persona+"]";
	}

	public boolean concatenaNombre() {
		return true;
	}

}
