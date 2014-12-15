package com.trascender.habilitaciones.recurso.persistent;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import javax.persistence.Entity;

import com.trascender.framework.recurso.persistent.FirmaPermiso;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.util.Util;

/**
 * Retención hace referencia a un saldo a favor que obtiene un contribuyente luego
 * de haberse efectuado una liquidación shps cuyo monto en la DDJJ habia sido mal declarado.
 * @author Maximiliano Fontanini
 * @since 1.1
 */
//
//@Entity
//@Table(name = "RETENCION")
public class Retencion implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6981137915710255736L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="gen_id_retencion")
	@SequenceGenerator(name = "gen_id_retencion", sequenceName = "gen_id_retencion", allocationSize = 1)
	@Column(name = "ID_RETENCION")
	private long idRetencion=-1;
	private Date fecha;
	
	@ManyToOne
	@JoinColumn(name = "ID_PERSONA")
	private Persona persona;
	
	@Transient
	private DeclaracionJuradaSHPS declaracionJuradaSHPS;
	
	
	private FirmaPermiso firmaPermiso;
	private double saldoInicial=0f;
	private double saldoRestante=0f;
	
	public enum Estado{
		NO_SALDADO,SALDADO;
		
		@Override
		public String toString(){
			return Util.capitalizeEnumName(super.toString());
		}
	}

	public long getIdRetencion() {
		return idRetencion;
	}

	public void setIdRetencion(long idRetencion) {
		this.idRetencion = idRetencion;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public DeclaracionJuradaSHPS getDeclaracionJuradaSHPS() {
		return declaracionJuradaSHPS;
	}

	public void setDeclaracionJuradaSHPS(DeclaracionJuradaSHPS declaracionJuradaSHPS) {
		this.declaracionJuradaSHPS = declaracionJuradaSHPS;
	}

	public FirmaPermiso getFirmaPermiso() {
		return firmaPermiso;
	}

	public void setFirmaPermiso(FirmaPermiso firmaPermiso) {
		this.firmaPermiso = firmaPermiso;
	}

	public double getSaldoInicial() {
		return saldoInicial;
	}

	public void setSaldoInicial(double saldoInicial) {
		this.saldoInicial = saldoInicial;
	}

	public double getSaldoRestante() {
		return saldoRestante;
	}

	public void setSaldoRestante(double saldoRestante) {
		this.saldoRestante = saldoRestante;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((declaracionJuradaSHPS == null) ? 0 : declaracionJuradaSHPS
						.hashCode());
		result = prime * result + (int) (idRetencion ^ (idRetencion >>> 32));
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
		Retencion other = (Retencion) obj;
		if (declaracionJuradaSHPS == null) {
			if (other.declaracionJuradaSHPS != null)
				return false;
		} else if (!declaracionJuradaSHPS.equals(other.declaracionJuradaSHPS))
			return false;
		if (idRetencion != other.idRetencion)
			return false;
		return true;
	}
	
	public String toString(){
		return "DDJJ: "+this.declaracionJuradaSHPS.toString() + " Saldo Inicial" + this.saldoInicial
		+ " Saldo Restante: " + this.saldoRestante; 
	} 
	
	
	
	
	

}
