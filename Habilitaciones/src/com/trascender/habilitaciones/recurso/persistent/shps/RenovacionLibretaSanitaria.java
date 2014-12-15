package com.trascender.habilitaciones.recurso.persistent.shps;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "RENOVACION_LIBRETA_SANITARIA")
public class RenovacionLibretaSanitaria implements Serializable {

	public static final long serialVersionUID = 113809881826483996L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="gen_id_renovacion_libreta_san")
	@SequenceGenerator(name = "gen_id_renovacion_libreta_san", sequenceName = "gen_id_renovacion_libreta_san", allocationSize = 1)
	@Column(name = "ID_RENOVACION_LIBRETA_SANITARIA")
	private long idRenovacionLibretaSanitaria=-1;
	
	@Column(name = "FECHA_DESDE")
	private Date fechaDesde;
	
	@Column(name = "FECHA_VIGENCIA")
	private Date fechaVigencia;
	
	@ManyToOne
	@JoinColumn(name = "ID_LIBRETA_SANITARIA")
	private LibretaSanitaria libretaSanitaria;

	public LibretaSanitaria getLibretaSanitaria() {
		return libretaSanitaria;
	}
	public void setLibretaSanitaria(LibretaSanitaria libretaSanitaria) {
		this.libretaSanitaria = libretaSanitaria;
	}
	public Date getFechaDesde() {
		return fechaDesde;
	}
	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	public Date getFechaVigencia() {
		return fechaVigencia;
	}
	public void setFechaVigencia(Date fechaVigencia) {
		this.fechaVigencia = fechaVigencia;
	}
	
	public long getIdRenovacionLibretaSanitaria() {
		return idRenovacionLibretaSanitaria;
	}
	public void setIdRenovacionLibretaSanitaria(long idRenovacionLibretaSanitaria) {
		this.idRenovacionLibretaSanitaria = idRenovacionLibretaSanitaria;
	}
	
	@Override
	public int hashCode() {
		if (this.getIdRenovacionLibretaSanitaria()==-1) return super.hashCode();
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + (int) (idRenovacionLibretaSanitaria ^ (idRenovacionLibretaSanitaria >>> 32));
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
		final RenovacionLibretaSanitaria other = (RenovacionLibretaSanitaria) obj;
		if (idRenovacionLibretaSanitaria != other.idRenovacionLibretaSanitaria)
			return false;
		return true;
	}
}
