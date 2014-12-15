package com.trascender.habilitaciones.recurso.persistent;

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

@Entity
@Table(name = "REGISTRO_EXENCION_OBLIGACION")
public class RegistroExencionObligacion implements Serializable{

	public static final long serialVersionUID = 7704600893603092704L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="gen_id_reg_exencion_ob")
	@SequenceGenerator(name = "gen_id_reg_exencion_ob", sequenceName = "gen_id_reg_exencion_ob", allocationSize = 1)
	@Column(name = "ID_REGISTRO_EXENCION_OBLIGACION")
	private long idRegistroExencionObligacion=-1;
	
	@ManyToOne
	@JoinColumn(name = "ID_OBLIGACION")
	private Obligacion obligacion;
	
	@Column(name = "REFERENCIA_NOTA_HCD")
	private String referenciaNotaHCD;
	
	@ManyToOne
	@JoinColumn(name = "ID_EXENCION")
	private ExencionObligacion exencionObligacion;
	
	public long getIdRegistroExencionObligacion() {
		return idRegistroExencionObligacion;
	}
	public void setIdRegistroExencionObligacion(long pIdRegistroExencionObligacion) {
		this.idRegistroExencionObligacion = pIdRegistroExencionObligacion;
	}
	
	public Obligacion getObligacion() {
		return obligacion;
	}	
	public void setObligacion(Obligacion pObligacion) {
		this.obligacion = pObligacion;
	}

	public String getReferenciaNotaHCD() {
		return referenciaNotaHCD;
	}
	public void setReferenciaNotaHCD(String pReferenciaNotaHCD) {
		this.referenciaNotaHCD = pReferenciaNotaHCD;
	}
	
	public ExencionObligacion getExencionObligacion(){
		return exencionObligacion;
	}
	public void setExencionObligacion(ExencionObligacion pExencionObligacion){
		this.exencionObligacion = pExencionObligacion;
	}
	
	public String getStringRegistroExencion(){
		String locStringExencion = "Prueba";
		try{
			locStringExencion = this.exencionObligacion.toString() + " - Referencia Nota HCD ["+this.referenciaNotaHCD+"]";
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return locStringExencion;
	}
	
	@Override
	public String toString(){
		return obligacion.toString()+" "+referenciaNotaHCD;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((exencionObligacion == null) ? 0 : exencionObligacion
						.hashCode());
		result = prime * result
				+ ((obligacion == null) ? 0 : obligacion.hashCode());
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
		RegistroExencionObligacion other = (RegistroExencionObligacion) obj;
		if (exencionObligacion == null) {
			if (other.exencionObligacion != null)
				return false;
		} else if (!exencionObligacion.equals(other.exencionObligacion))
			return false;
		if (obligacion == null) {
			if (other.obligacion != null)
				return false;
		} else if (!obligacion.equals(other.obligacion))
			return false;
		return true;
	}
		
}
