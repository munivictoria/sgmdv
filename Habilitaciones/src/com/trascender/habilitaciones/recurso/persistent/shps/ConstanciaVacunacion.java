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
@Table(name = "CONSTANCIA_VACUNACION")
public class ConstanciaVacunacion implements Serializable{
	
	public static final long serialVersionUID = -50569679320234860L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="gen_id_constancia_vacunacion")
	@SequenceGenerator(name = "gen_id_constancia_vacunacion", sequenceName = "gen_id_constancia_vacunacion", allocationSize = 1)
	@Column(name = "ID_CONSTANCIA_VACUNACION")
	private long idConstanciaVacunacion=-1;
	
	@Column(name = "FECHA_VACUNACION", nullable = false)
	private Date fechaVacunacion;
	
	private String vacuna;
	
	@Column(name = "FECHA_VALIDEZ", nullable = false)
	private Date fechaValidez;
	
	@ManyToOne
	@JoinColumn(name = "ID_LIBRETA_SANITARIA", nullable = false)
	private LibretaSanitaria libretaSanitaria;
	
	
	public LibretaSanitaria getLibretaSanitaria() {
		return libretaSanitaria;
	}
	public void setLibretaSanitaria(LibretaSanitaria libretaSanitaria) {
		this.libretaSanitaria = libretaSanitaria;
	}
	
	public Date getFechaVacunacion() {
		return fechaVacunacion;
	}
	public void setFechaVacunacion(Date fechaVacunacion) {
		this.fechaVacunacion = fechaVacunacion;
	}
	
	public Date getFechaValidez() {
		return fechaValidez;
	}
	public void setFechaValidez(Date fechaValidez) {
		this.fechaValidez = fechaValidez;
	}
	
	public long getIdConstanciaVacunacion() {
		return idConstanciaVacunacion;
	}
	public void setIdConstanciaVacunacion(long idConstanciaVacunacion) {
		this.idConstanciaVacunacion = idConstanciaVacunacion;
	}
	
	public String getVacuna() {
		return vacuna;
	}
	public void setVacuna(String vacuna) {
		this.vacuna = vacuna;
	}
	
	@Override
	public int hashCode() {
		if (this.getIdConstanciaVacunacion()!=-1){
			final int PRIME = 31;
			int result = 1;
			result = PRIME * result + (int) (idConstanciaVacunacion ^ (idConstanciaVacunacion >>> 32));
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
		final ConstanciaVacunacion other = (ConstanciaVacunacion) obj;
		if (idConstanciaVacunacion != other.idConstanciaVacunacion)
			return false;
		return true;
	}
	
}
