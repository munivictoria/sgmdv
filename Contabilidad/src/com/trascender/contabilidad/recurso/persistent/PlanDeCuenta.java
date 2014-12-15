/**
 * 
 */
package com.trascender.contabilidad.recurso.persistent;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "PLAN_DE_CUENTA")
public class PlanDeCuenta implements Serializable{

	public static final long serialVersionUID = 5051388374354410809L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_plan_de_cuenta")
	@SequenceGenerator(name = "gen_id_plan_de_cuenta", sequenceName = "gen_id_plan_de_cuenta",allocationSize = 1)
	@Column(name="ID_PLAN_DE_CUENTA")
	private long idPlanDeCuenta = -1;
	
	@Column(name = "NOMBRE")
	private String descripcion;
	
	@Column(name = "FECHA_ALTA")
	@Temporal(TemporalType.DATE)
	private Date fechaAlta;
	
	@OneToMany(mappedBy = "planDeCuenta", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Cuenta> listaCuentas = new HashSet<Cuenta>();
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public long getIdPlanDeCuenta() {
		return idPlanDeCuenta;
	}

	public void setIdPlanDeCuenta(long idPlanDeCuenta) {
		this.idPlanDeCuenta = idPlanDeCuenta;
	}

	public Set<Cuenta> getListaCuentas() {
		return listaCuentas;
	}

	public void setListaCuentas(Set<Cuenta> listaCuentas) {
		this.listaCuentas = listaCuentas;
	}
	
	public Set<Cuenta> getListaCuentasSinPadres(){
		Set<Cuenta> locListaCuentas = new HashSet<Cuenta>();
		for (Cuenta cadaCuenta : this.listaCuentas){
			if (cadaCuenta.getCuentaPadre() == null){
				locListaCuentas.add(cadaCuenta);
			}
		}
		return locListaCuentas;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		if (this.idPlanDeCuenta == -1){
			return super.hashCode();
		}
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + (int) (idPlanDeCuenta ^ (idPlanDeCuenta >>> 32));
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final PlanDeCuenta other = (PlanDeCuenta) obj;
		if (idPlanDeCuenta != other.idPlanDeCuenta) {
			return false;
		}
		return true;
	}
	
	@Override
	public String toString(){
		DateFormat locDateFormat = DateFormat.getDateInstance(DateFormat.SHORT);
		String linea = (this.descripcion!=null)?this.descripcion:"";
		if (this.fechaAlta!=null){
			linea+=" ("+locDateFormat.format(this.fechaAlta)+")";
		}
		
		return linea;
	}
	
}
