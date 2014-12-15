/**
 * 
 */
package com.trascender.contabilidad.recurso.persistent;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "LINEA_PRESUPUESTO")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract  class LineaPresupuesto implements Serializable{

	public  static final long serialVersionUID = 5084577928943693018L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_linea_presupuesto")
	@SequenceGenerator(name = "gen_id_linea_presupuesto", sequenceName = "gen_id_linea_presupuesto",allocationSize = 1)
	@Column(name="ID_LINEA_PRESUPUESTO")
	private long idLineaPresupuesto = -1;
	//Relacion con otros objetos
	
	@ManyToOne
	@JoinColumn(name = "ID_PRESUPUESTO")
	private Presupuesto presupuesto;
	
	@ManyToOne
	@JoinColumn(name = "ID_CUENTA")
	private Cuenta cuenta;
	
	public long getIdLineaPresupuesto() {
		return idLineaPresupuesto;
	}

	public void setIdLineaPresupuesto(long idLineaPresupuesto) {
		this.idLineaPresupuesto = idLineaPresupuesto;
	}
	
	public Presupuesto getPresupuesto() {
		return presupuesto;
	}

	public void setPresupuesto(Presupuesto presupuesto) {
		this.presupuesto = presupuesto;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		if (this.idLineaPresupuesto == -1){
			return super.hashCode();
		}
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + (int) (idLineaPresupuesto ^ (idLineaPresupuesto >>> 32));
		result = PRIME * result + ((presupuesto == null) ? 0 : presupuesto.hashCode());
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
		final LineaPresupuesto other = (LineaPresupuesto) obj;
		if (idLineaPresupuesto != other.idLineaPresupuesto) {
			return false;
		}
		if (presupuesto == null) {
			if (other.presupuesto != null) {
				return false;
			}
		} else if (!presupuesto.equals(other.presupuesto)) {
			return false;
		}
		return true;
	}
	
	@Override
	public String toString(){
		return this.idLineaPresupuesto + " " + this.cuenta.toString() +
		" " + this.presupuesto.toString();
	}
	
}
