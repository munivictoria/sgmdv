package com.trascender.accionSocial.recurso.persistent;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
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

import com.trascender.framework.util.Util;

@Entity
@Table(name="SOLICITUD_BENEFICIO")
public class SolicitudBeneficio implements Serializable{

	/**
	 *Estados: ENTREGADA, NO_ENTREGADA, RECHAZADA
	 */
	public enum Estado{
		ENTREGADA, NO_ENTREGADA, RECHAZADA;
		
		public String toString() {
			return Util.capitalizeEnumName(this.name());
		};
	}
	
	public static final long serialVersionUID = -7404891399088896130L;
	
	@Id
	@GeneratedValue(generator="gen_id_solicitud_beneficio", strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(allocationSize=1, name="gen_id_solicitud_beneficio", sequenceName="gen_id_solicitud_beneficio")
	@Column(name="ID_SOLICITUD_BENEFICIO")
	private long idSolicitudBeneficio = -1;
	
	@Enumerated(EnumType.STRING)
	private Estado estado = Estado.NO_ENTREGADA;
	
	@Column(name="FECHA_ALTA")
	private Date fechaAlta = new Date();
	
	@Column(name="FECHA_FINALIZACION")
	private Date fechaFinalizacion;
	
	private Integer cantidad;
	private Double monto;
	
	@Column(name="COMENTARIO_FINALIZACION")
	private String comentarioFinalizacion;
	
	private String descripcion;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="ID_FICHA_SOCIAL")
	private FichaSocial fichaSocial;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ID_BENEFICIO")
	private Beneficio beneficio;
	
	
	public Beneficio getBeneficio() {
		return beneficio;
	}

	public void setBeneficio(Beneficio beneficio) {
		this.beneficio = beneficio;
	}

	public FichaSocial getFichaSocial() {
		return fichaSocial;
	}

	public void setFichaSocial(FichaSocial fichaSocial) {
		this.fichaSocial = fichaSocial;
	}

	public long getIdSolicitudBeneficio() {
		return idSolicitudBeneficio;
	}

	public void setIdSolicitudBeneficio(long idSolicitudBeneficio) {
		this.idSolicitudBeneficio = idSolicitudBeneficio;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Date getFechaFinalizacion() {
		return fechaFinalizacion;
	}

	public void setFechaFinalizacion(Date fechaFinalizacion) {
		this.fechaFinalizacion = fechaFinalizacion;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public String getComentarioFinalizacion() {
		return comentarioFinalizacion;
	}

	public void setComentarioFinalizacion(String comentarioFinalizacion) {
		this.comentarioFinalizacion = comentarioFinalizacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String toString(){
		return "Fecha: " + fechaAlta + ", Beneficio: "+beneficio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ (int) (idSolicitudBeneficio ^ (idSolicitudBeneficio >>> 32));
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
		SolicitudBeneficio other = (SolicitudBeneficio) obj;
		if (idSolicitudBeneficio != other.idSolicitudBeneficio)
			return false;
		return true;
	}
	
}
