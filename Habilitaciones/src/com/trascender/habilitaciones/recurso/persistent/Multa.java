package com.trascender.habilitaciones.recurso.persistent;

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
@Table(name = "MULTA")
public class Multa implements Serializable {

	/**
	 * 
	 */
	public static final long serialVersionUID = 6185231385072674456L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="gen_id_multa")
	@SequenceGenerator(name = "gen_id_multa", sequenceName = "gen_id_multa", allocationSize = 1)
	@Column(name = "ID_MULTA")
	private long idMulta=-1;
	
	@ManyToOne
	@JoinColumn(name = "ID_DOC_HAB_ESPECIALIZADO")
	private DocHabilitanteEspecializado docHabilitanteEspecializado;
	
	@Column(name = "FECHA_MULTA")
	private Date fecha;
	
	@Column(name = "DETALLE_MULTA")
	private String detalle;
	
	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public DocHabilitanteEspecializado getDocHabilitanteEspecializado() {
		return docHabilitanteEspecializado;
	}

	public void setDocHabilitanteEspecializado(
			DocHabilitanteEspecializado docHabilitanteEspecializado) {
		this.docHabilitanteEspecializado = docHabilitanteEspecializado;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public long getIdMulta() {
		return idMulta;
	}

	public void setIdMulta(long idMulta) {
		this.idMulta = idMulta;
	}
		
}
