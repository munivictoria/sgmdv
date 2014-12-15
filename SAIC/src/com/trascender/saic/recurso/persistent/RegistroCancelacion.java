package com.trascender.saic.recurso.persistent;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.trascender.framework.util.Periodicidad;
import com.trascender.saic.recurso.interfaces.Pagable;


/**
 * @hibernate.class table = "REGISTRO_CANCELACION"
 */

@Entity
@Table(name = "REGISTRO_CANCELACION")
@Inheritance(strategy = InheritanceType.JOINED)
public class RegistroCancelacion implements Serializable{

	public static final long serialVersionUID = -7943976786049492205L;
	
	@Id
	@SequenceGenerator(name="gen_id_registro_cancelacion",allocationSize = 1, sequenceName = "gen_id_registro_cancelacion")
	@GeneratedValue(generator = "gen_id_registro_cancelacion", strategy = GenerationType.SEQUENCE)
	@Column(name="ID_REGISTRO_CANCELACION")
	private long idRegistroCancelacion=-1;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_CANCELACION", nullable=false)
	private Date fechaCancelacion;
	
	@Enumerated(EnumType.STRING)
	@Column(name="FORMA_DE_PAGO")
	private Periodicidad formaDePago;
	
	@Transient
	private Pagable deuda;
	
	
	public RegistroCancelacion(){
		this.fechaCancelacion = Calendar.getInstance().getTime();
	}
	/**
	 * 
	 * @hibernate.property column = "FECHA_CANCELACION" not-null = "true" 
	 */
	public Date getFechaCancelacion() {
		return fechaCancelacion;
	}
	public void setFechaCancelacion(Date pFechaCancelacion) {
		fechaCancelacion = pFechaCancelacion;
	}
	
	
	
	public Periodicidad getFormaDePago() {
		return formaDePago;
	}
	public void setFormaDePago(Periodicidad formaDePago) {
		this.formaDePago = formaDePago;
	}
	/**
	 * 
	 * @hibernate.id  column = "ID_REGISTRO_CANCELACION" unsaved-value = "-1" generator-class = "increment" 
	 */
	public long getIdRegistroCancelacion() {
		return idRegistroCancelacion;
	}
	public void setIdRegistroCancelacion(long pIdRegistroCancelacion) {
		idRegistroCancelacion = pIdRegistroCancelacion;
	}
	@Override
	public int hashCode() {
		if (this.idRegistroCancelacion==-1) return super.hashCode();
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + (int) (idRegistroCancelacion ^ (idRegistroCancelacion >>> 32));
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
		final RegistroCancelacion other = (RegistroCancelacion) obj;
		if (idRegistroCancelacion != other.idRegistroCancelacion)
			return false;
		return true;
	}

	
	public Pagable getDeuda() {
		return deuda;
	}
	public void setDeuda(Pagable deuda) {
		this.deuda = deuda;
	}
}
