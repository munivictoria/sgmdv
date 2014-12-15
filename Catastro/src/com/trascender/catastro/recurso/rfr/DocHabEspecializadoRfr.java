package com.trascender.catastro.recurso.rfr;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.framework.recurso.persistent.referencia.ObligacionRfr;

@Entity
@Table(name="DOC_HAB_ESPECIALIZADO")
@DiscriminatorColumn(name="TIPO_DOC_HAB_ESPECIALIZADO")
public class DocHabEspecializadoRfr {

	public enum Estado{ACTIVO,INACTIVO;
	
		@Override
		public String toString() {
			return com.trascender.framework.util.Util.capitalizeEnumName(super.toString());
		}
	};
	
	@Id
	@Column(name="ID_DOC_HAB_ESPECIALIZADO")
	private long idDocHabEspecializado = -1L;
	
	@ManyToOne
	@JoinColumn(name="ID_OBLIGACION")
	private ObligacionRfr obligacion;
	
	private Estado estado = Estado.ACTIVO;
	
	@Column(name="FECHA_CREACION")
	private Date fechaCreacion = Calendar.getInstance().getTime();

	@ManyToOne(optional=false)
	@JoinColumn(name="ID_PARCELA", nullable=false)
	private Parcela parcela;
	
	
	@Transient
	private Long idServicioOSP;
	
	public long getIdDocHabEspecializado() {
		return idDocHabEspecializado;
	}

	public void setIdDocHabEspecializado(long idDocHabEspecializado) {
		this.idDocHabEspecializado = idDocHabEspecializado;
	}

	public ObligacionRfr getObligacion() {
		return obligacion;
	}

	public void setObligacion(ObligacionRfr obligacion) {
		this.obligacion = obligacion;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Parcela getParcela() {
		return parcela;
	}

	public void setParcela(Parcela parcela) {
		this.parcela = parcela;
	}

	public Long getIdServicioOSP() {
		return idServicioOSP;
	}

	public void setIdServicioOSP(Long idServicioOSP) {
		this.idServicioOSP = idServicioOSP;
	}
	
	
	
	
}
