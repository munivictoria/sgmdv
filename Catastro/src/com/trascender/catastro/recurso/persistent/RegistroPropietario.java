package com.trascender.catastro.recurso.persistent;

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

import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.util.AuditoriaIndirecta;
import com.trascender.framework.util.EntidadTrascender;

@Entity
@Table(name = "REGISTRO_PROPIETARIO")
public class RegistroPropietario implements Serializable, AuditoriaIndirecta {
	
	public static final long serialVersionUID = 4997643080258134102L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_id_registro_propietario")
	@SequenceGenerator(name ="gen_id_registro_propietario", sequenceName = "gen_id_registro_propietario", allocationSize = 1)
	@Column(name = "ID_REGISTRO_PROPIETARIO")
	private long idRegistroPropietario= -1;
	
	private String descripcion;
	
	@ManyToOne
	@JoinColumn(name = "ID_TITULO_PROPIEDAD")
	private TituloPropiedad tituloPropiedad;
	
	/**
	 * Tipo titulo = 1 para registro propietario de parcelas.
	 * 				 2 para RP Automotores
	 * 				 0 para Null
	 */
	@Column(name="TIPO_TITULO")
	private Integer tipoTitulo;
	
	@ManyToOne
	@JoinColumn(name = "ID_PERSONA")
	private Persona persona;
	
	@Column(name = "PORCENTAJE")
	private Float porcentaje;
	
	@Column(name = "ENCARGADO_DE_OBLIGACIONES")
	private Boolean encargadoDeObligaciones;
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public long getIdRegistroPropietario() {
		return idRegistroPropietario;
	}

	public void setIdRegistroPropietario(long idRegistroPropietario) {
		this.idRegistroPropietario = idRegistroPropietario;
	}

	public TituloPropiedad getTituloPropiedad() {
		return tituloPropiedad;
	}
	
	public Float getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(Float porcentaje) {
		this.porcentaje = porcentaje;
	}

	public Boolean getEncargadoDeObligaciones() {
		return encargadoDeObligaciones;
	}

	public void setEncargadoDeObligaciones(Boolean encargadoDeObligaciones) {
		this.encargadoDeObligaciones = encargadoDeObligaciones;
	}

	/**
	 * Tambien inicializa el tipoTitulo automatico
	 * @param tituloPropiedad
	 */
	public void setTituloPropiedad(TituloPropiedad tituloPropiedad) {
		this.tituloPropiedad = tituloPropiedad;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	/**
	 * Tipo titulo = 1 para registro propietario de parcelas.
	 * 				 2 para RP Automotores
	 * 				 0 para Null
	 */
	public Integer getTipoTitulo() {
		return tipoTitulo;
	}

	/**
	 * Tipo titulo = 1 para registro propietario de parcelas.
	 * 				 2 para RP Automotores
	 * 				 0 para Null
	 */
	public void setTipoTitulo(Integer tipoTitulo) {
		this.tipoTitulo = tipoTitulo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ (int) (idRegistroPropietario ^ (idRegistroPropietario >>> 32));
		return result;
	}

	@Override
	public String toString() {
		return this.getPersona().toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RegistroPropietario other = (RegistroPropietario) obj;
		if (idRegistroPropietario != other.idRegistroPropietario)
			return false;
		return true;
	}

	public EntidadTrascender getEntidadTrascender() {
		return tituloPropiedad;
	}

	public String getNombrePropiedad() {
		return "Propietario ["+persona.getDenominacion()+"]";
	}

	public boolean concatenaNombre() {
		return true;
	}

}
