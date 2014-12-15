package com.trascender.catastro.recurso.persistent;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.trascender.framework.util.Util;

@Entity
@Table(name = "DECLARACION_JURADA")
public class DeclaracionJurada implements Serializable{

	/**
	 * 
	 */
	public static final long serialVersionUID = 632544967109239661L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_id_declaracion_jurada")
	@SequenceGenerator(name ="gen_id_declaracion_jurada", sequenceName = "gen_id_declaracion_jurada", allocationSize = 1)
	@Column(name = "ID_DECLARACION_JURADA")
	private long idDeclaracionJurada=-1;
	
	private String numero;
	
	@Column(name = "FECHA_INSCRIPCION")
	private Date fechaInscripcion;
	
	public Date getFechaInscripcion() {
		return fechaInscripcion;
	}

	public void setFechaInscripcion(Date fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}

	public long getIdDeclaracionJurada() {
		return idDeclaracionJurada;
	}

	public void setIdDeclaracionJurada(long idDeclaracionJurada) {
		this.idDeclaracionJurada = idDeclaracionJurada;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	@Override
	public String toString() {
		return this.getNumero() + " - " + Util.getString(this.getFechaInscripcion());
	}
}
