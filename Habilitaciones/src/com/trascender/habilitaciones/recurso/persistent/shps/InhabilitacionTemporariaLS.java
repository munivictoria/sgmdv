package com.trascender.habilitaciones.recurso.persistent.shps;

import java.io.Serializable;
import java.util.Date;

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
@Table(name = "INHAB_TEMP_LIBRETA_SANITARIA")
public class InhabilitacionTemporariaLS implements Serializable {
	/**
	 * 
	 */
	public static final long serialVersionUID = -8721866993355838846L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="gen_id_inhab_libreta_sanitaria")
	@SequenceGenerator(name = "gen_id_inhab_libreta_sanitaria", sequenceName = "gen_id_inhab_libreta_sanitaria", allocationSize = 1)
	@Column(name = "ID_INHAB_TEMP_LIBRETA_SANITARIA")
	private long idInhabilitacionTemporariaLS=-1;
	
	private String diagnostico;
	
	@Column(name = "PRUEBA_CONFIRMATORIA")
	private String pruebaConfirmatoria;
	
	@Column(name = "FECHA_INHABILITACION")
	private Date fechaInhabilitacion;
	
	@Column(name = "FECHA_REINTEGRO")
	private Date fechaReintegro;
	
	@ManyToOne
	@JoinColumn(name = "ID_LIBRETA_SANITARIA", nullable = false)
	private LibretaSanitaria libretaSanitaria;

	public LibretaSanitaria getLibretaSanitaria() {
		return libretaSanitaria;
	}
	public void setLibretaSanitaria(LibretaSanitaria libretaSanitaria) {
		this.libretaSanitaria = libretaSanitaria;
	}
	public String getDiagnostico() {
		return diagnostico;
	}
	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}
	
	public Date getFechaReintegro() {
		return fechaReintegro;
	}
	public void setFechaReintegro(Date fechaReintegro) {
		this.fechaReintegro = fechaReintegro;
	}
	public long getIdInhabilitacionTemporariaLS() {
		return idInhabilitacionTemporariaLS;
	}
	public void setIdInhabilitacionTemporariaLS(long idInhabilitacionTemporariaLS) {
		this.idInhabilitacionTemporariaLS = idInhabilitacionTemporariaLS;
	}
	
	public String getPruebaConfirmatoria() {
		return pruebaConfirmatoria;
	}
	public void setPruebaConfirmatoria(String pruebaConfirmatoria) {
		this.pruebaConfirmatoria = pruebaConfirmatoria;
	}
	
	public Date getFechaInhabilitacion() {
		return fechaInhabilitacion;
	}
	public void setFechaInhabilitacion(Date fechaIhabilitacion) {
		this.fechaInhabilitacion = fechaIhabilitacion;
	}
	
	
	@Override
	public int hashCode() {
		if (this.getIdInhabilitacionTemporariaLS()!=-1){
			final int PRIME = 31;
			int result = 1;
			result = PRIME * result + (int) (idInhabilitacionTemporariaLS ^ (idInhabilitacionTemporariaLS >>> 32));
			return result;
		}
		else{
			return super.hashCode();
		}
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final InhabilitacionTemporariaLS other = (InhabilitacionTemporariaLS) obj;
		if (idInhabilitacionTemporariaLS != other.idInhabilitacionTemporariaLS)
			return false;
		return true;
	}
	
}
