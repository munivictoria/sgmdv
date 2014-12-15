package com.trascender.saic.recurso.persistent;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "VENCIMIENTO")
public class Vencimiento implements Serializable, Cloneable, Comparable<Vencimiento>{

	public static final long serialVersionUID = -2943791248511875132L;
	
	@Id
	@SequenceGenerator(name = "gen_id_vencimiento", allocationSize = 1, sequenceName = "gen_id_vencimiento")
	@GeneratedValue(generator = "gen_id_vencimiento", strategy = GenerationType.SEQUENCE)
	@Column(name = "ID_VENCIMIENTO")
	private long idVencimiento=-1;
	
	@Column(name = "FECHA")
	@Temporal(TemporalType.DATE)
	private Date fecha;
	
	@Column(nullable = false)
	private Double valor;
	
	private String nombre;
	
	private Integer numero;
	
	public long getIdVencimiento() {
		return idVencimiento;
	}
	public void setIdVencimiento(long idVencimiento) {
		this.idVencimiento = idVencimiento;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
//		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
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
		Vencimiento other = (Vencimiento) obj;
		
		if(this.idVencimiento != other.getIdVencimiento()){
			return false;
		}else{
			if(System.identityHashCode(this) != System.identityHashCode(other)){
				return false;
			}
			
			return true;
		}
		
	}
	
	@Override
	public String toString() {
		return ((this.getNombre()==null)?"":this.nombre);
	}
	
	@Override
	public Vencimiento clone() throws CloneNotSupportedException{
		Vencimiento locVencimiento = (Vencimiento) super.clone();
		locVencimiento.setIdVencimiento(-1);
		return locVencimiento;
	}
	@Override
	public int compareTo(Vencimiento o) {
		return o.fecha.compareTo(this.fecha);
	}
	
}
