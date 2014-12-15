package com.trascender.saic.recurso.persistent.auditoriaTributaria;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.framework.recurso.persistent.PersonaJuridica;
import com.trascender.framework.util.Util;
/**
 * La intimación de pago es un requerimiento formal dirigido a un deudor para que cumpla su obligación.
 * @author jsantacruz
 */
@Entity
public class Intimacion implements Serializable{

	public static final long serialVersionUID = -1226486087468072161L;

	/**
	 * Estados posibles { PRESCRIPTA, VIGENTE, ENVIADA }
	 * @author jsantacruz
	 */
	
	public enum EstadoIntimacion {
		PRESCRIPTA,
		PRESCRIPTA_SIN_FECHA_RECEPCION,
		VIGENTE,
		ENVIADA;
		
		@Override
		public String toString() {
			return Util.capitalizeEnumName(this.name());
		}
	}
	
	@Id
	@SequenceGenerator(name="gen_id_intimacion", sequenceName="gen_id_intimacion")
	@GeneratedValue(generator="gen_id_intimacion", strategy=GenerationType.SEQUENCE)
	@Column(name="ID_INTIMACION")
	private long idIntimacion = -1;
	
	@ManyToOne
	@JoinColumn(name="ID_PERSONA")
	private Persona contribuyente;
	
	@Column(name="FECHA_EMISION")
	private Date fechaEmision;
	
	@Column(name="FECHA_RECEPCION")
	private Date fechaRecepcion;
	
	@Column(name="PERIODO_VALIDEZ")
	private Integer periodoValidez;
	
	private String causa = "";
	
	private String observaciones = "";
	
	/**
	 * La fecha de recepcion y el periodo de validez son necesarios.
	 * Si la fecha de recepcion es nula, el vencimiento se calcula sobre la fecha de emision
	 * @return Un string con el estado
	 */
	public EstadoIntimacion getEstado() {
		Calendar locFecha = Calendar.getInstance();
		
		if(this.getFechaRecepcion() != null && this.getPeriodoValidez() != null){
			locFecha.setTime(this.getFechaRecepcion());
			locFecha.add(Calendar.DAY_OF_MONTH, this.getPeriodoValidez());
			
			if(locFecha.getTime().before(Calendar.getInstance().getTime())){
				return EstadoIntimacion.PRESCRIPTA;
			}else{
				return EstadoIntimacion.VIGENTE;
			}
		} else if(this.getFechaRecepcion() == null && this.getPeriodoValidez() != null){
			locFecha.setTime(this.getFechaEmision());
			locFecha.add(Calendar.DAY_OF_MONTH, this.getPeriodoValidez());
			
			if(locFecha.getTime().before(Calendar.getInstance().getTime())){
				return EstadoIntimacion.PRESCRIPTA_SIN_FECHA_RECEPCION;
			}else{
				return EstadoIntimacion.VIGENTE;
			}
		}
		
		return EstadoIntimacion.ENVIADA;
	}

	public long getIdIntimacion() {
		return idIntimacion;
	}
	public void setIdIntimacion(long idIntimacion) {
		this.idIntimacion = idIntimacion;
	}
	public Persona getContribuyente() {
		return contribuyente;
	}
	public void setContribuyente(Persona contribuyente) {
		this.contribuyente = contribuyente;
	}
	public Date getFechaEmision() {
		return fechaEmision;
	}
	public void setFechaEmision(Date fecha_Emision) {
		this.fechaEmision = fecha_Emision;
	}
	public Date getFechaRecepcion() {
		return fechaRecepcion;
	}
	public void setFechaRecepcion(Date fecha_recepcion) {
		if (fecha_recepcion != null) {
			try{
				if(this.getFechaEmision() != null && !this.getFechaEmision().after(fecha_recepcion)){
					this.fechaRecepcion = fecha_recepcion;
				}else{
					throw new Exception("La fecha de recepcion debe ser mayor a la fecha de emisión.");
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public Integer getPeriodoValidez() {
		return periodoValidez;
	}
	public void setPeriodoValidez(Integer periodoValidez) {
		if(periodoValidez >= 1)
		this.periodoValidez = periodoValidez;
	}
	public String getCausa() {
		return causa;
	}
	public void setCausa(String causa) {
		this.causa = causa;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idIntimacion ^ (idIntimacion >>> 32));
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
		Intimacion other = (Intimacion) obj;
		if (idIntimacion != other.idIntimacion)
			return false;
		return true;
	}

	@Override
	public String toString() {
		
		StringBuilder locToString = new StringBuilder();
		if(this.getContribuyente() instanceof PersonaFisica){
			locToString.append(((PersonaFisica) this.getContribuyente()).toString());
		}else if(this.getContribuyente() instanceof PersonaJuridica){
			locToString.append(((PersonaJuridica) this.getContribuyente()).toString());
		}
		locToString.append(" - Estado:"+ this.getEstado());
		
		if(this.getFechaRecepcion() != null){
			locToString.append(" - Fecha Recepcion:"+Util.getString(this.getFechaRecepcion(), "dd/MM/yyyy"));
		}else{
			locToString.append(" - Fecha Emision:"+Util.getString(this.getFechaEmision(), "dd/MM/yyyy"));
		}
		return locToString.toString();
	}
	
}
