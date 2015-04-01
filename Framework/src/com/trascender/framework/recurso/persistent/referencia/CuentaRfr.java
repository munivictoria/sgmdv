package com.trascender.framework.recurso.persistent.referencia;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name = "CUENTA")
public class CuentaRfr implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID_CUENTA")
	private long idCuenta = -1;
	
	@Column(name = "CODIGO_IMPUTACION")
	private String codigoImputacion;
	private String nombre;
	private String abreviatura;
	
	@ManyToOne
	@JoinColumn(name = "ID_CUENTA_PADRE")
	private CuentaRfr cuentaPadre;
	
	@Column(name = "CODIGO_IMPUTACION_COMPLETO")
	private String codigoImputacionCompleto;
	
	public String getCodigoImputacionCompleto() {
		return codigoImputacionCompleto;
	}
	public void setCodigoImputacionCompleto(String codigoImputacionCompleto) {
		this.codigoImputacionCompleto = codigoImputacionCompleto;
	}
	public CuentaRfr getCuentaPadre() {
		return cuentaPadre;
	}
	public void setCuentaPadre(CuentaRfr cuentaPadre) {
		this.cuentaPadre = cuentaPadre;
	}
	public String getAbreviatura() {
		return abreviatura;
	}
	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	public String getCodigoImputacion() {
		return codigoImputacion;
	}
	public void setCodigoImputacion(String codigoImputacion) {
		this.codigoImputacion = codigoImputacion;
	}
	
	public long getIdCuenta() {
		return idCuenta;
	}
	public void setIdCuenta(long idCuenta) {
		this.idCuenta = idCuenta;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idCuenta ^ (idCuenta >>> 32));
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
		CuentaRfr other = (CuentaRfr) obj;
		if (idCuenta != other.idCuenta)
			return false;
		return true;
	}
	@Override
	public String toString(){
		if (this == null) {
			return "";
		}
		String locAbreviatura = "";
		if (this.abreviatura != null){
			locAbreviatura = " ("+this.abreviatura+")";
		} 
		String locCodigoImputacion ="";
		if (this.codigoImputacionCompleto!= null){
			locCodigoImputacion = this.codigoImputacionCompleto;
		}
		String cadena = locCodigoImputacion+" "+this.nombre+locAbreviatura;
		return cadena; 
	}
}
