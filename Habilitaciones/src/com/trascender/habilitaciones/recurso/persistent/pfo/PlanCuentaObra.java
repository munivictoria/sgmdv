package com.trascender.habilitaciones.recurso.persistent.pfo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.trascender.framework.util.Periodicidad;


/**
 * 
 * @author Mariano Lusardi 
 * @hibernate.class table = "PLAN_CUENTA_OBRA"
 * 
 */

@Entity
@Table(name = "PLAN_CUENTA_OBRA")
public class PlanCuentaObra implements Serializable{

	
	public enum SistemaCalculo{
		DIRECTO
		//,FRANCES
	}
	/**
	 * 
	 */
	public static final long serialVersionUID = -754749405523494614L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="gen_id_plan_cuenta_obra")
	@SequenceGenerator(name = "gen_id_plan_cuenta_obra", sequenceName = "gen_id_plan_cuenta_obra", allocationSize = 1)
	@Column(name = "ID_PLAN_CUENTA_OBRA")
	private long idPlanCuentaPorObra=-1;
	
	private String nombre;
	
	@Column(name = "CANTIDAD_CUOTAS")
	private Integer cantidadCuotas;
	
	@Column(name = "TASA_ANUAL")
	private Double tasaAnual;
	
	@Column(name = "TASA_FIJA")
	private boolean tasaFija;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "SISTEMA_CALCULO_INTERES")
	private SistemaCalculo sistemaCalculoInteres;
	
	@Enumerated(EnumType.STRING)
	private Periodicidad periodicidad;
	
	
	public Integer getCantidadCuotas() {
		return cantidadCuotas;
	}
	public void setCantidadCuotas(Integer cantidadCuotas) {
		this.cantidadCuotas = cantidadCuotas;
	}
	
	public Periodicidad getPeriodicidad() {
		return periodicidad;
	}
	public void setPeriodicidad(Periodicidad periodicidad) {
		this.periodicidad = periodicidad;
	}
	
	public Double getTasaAnual() {
		return tasaAnual;
	}
	public void setTasaAnual(Double tasaAnual) {
		this.tasaAnual = tasaAnual;
	}
	
	public boolean isTasaFija() {
		return tasaFija;
	}
	public void setTasaFija(boolean tasaFija) {
		this.tasaFija = tasaFija;
	}
	
	public long getIdPlanCuentaPorObra() {
		return idPlanCuentaPorObra;
	}
	public void setIdPlanCuentaPorObra(long idPlanCuentaPorObra) {
		this.idPlanCuentaPorObra = idPlanCuentaPorObra;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Override
	public int hashCode() {
		if (this.getIdPlanCuentaPorObra()==-1){
			return super.hashCode();
		}
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + (int) (idPlanCuentaPorObra ^ (idPlanCuentaPorObra >>> 32));
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
		final PlanCuentaObra other = (PlanCuentaObra) obj;
		if (idPlanCuentaPorObra != other.idPlanCuentaPorObra)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return (this.getNombre()!=null)?this.getNombre()+"("+this.getCantidadCuotas()+")":"";
	}
	
	
	/**
	 * 
	 * @hibernate.property column = "SISTEMA_CALCULO_INTERES" type = "com.trascender.habilitaciones.util.enumerations.SistemaCalculoPlanCuentaObra"
	 */
	public SistemaCalculo getSistemaCalculoInteres() {
		return sistemaCalculoInteres;
	}
	public void setSistemaCalculoInteres(SistemaCalculo sistemaCalculoInteres) {
		this.sistemaCalculoInteres = sistemaCalculoInteres;
	}
}
