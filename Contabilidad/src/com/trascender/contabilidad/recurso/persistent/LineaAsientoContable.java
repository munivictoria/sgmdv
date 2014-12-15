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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "LINEA_ASIENTO_CONTABLE")
public class LineaAsientoContable implements Serializable{

	/**
	 * 
	 */
	public static final long serialVersionUID = -8066721717457503654L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_linea_asiento_contable")
	@SequenceGenerator(name = "gen_id_linea_asiento_contable", sequenceName = "gen_id_linea_asiento_contable",allocationSize = 1)
	@Column(name="ID_LINEA_ASIENTO_CONTABLE")
	private long idLineaAsientoContable = -1;
	
	@Column(name = "IMPORTE_DEBE")
	private Double importeDebe = new Double(0);
	
	@Column(name = "IMPORTE_HABER")
	private Double importeHaber = new Double(0);
	
	//Relacion con otros objetos
	
	@ManyToOne
	@JoinColumn(name = "ID_CUENTA")
	private Cuenta cuenta;
	
	@ManyToOne
	@JoinColumn(name = "ID_ASIENTO_CONTABLE")
	private AsientoContable asientoContable;
	
	
	public Cuenta getCuenta() {
		return cuenta;
	}
	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}
	public long getIdLineaAsientoContable() {
		return idLineaAsientoContable;
	}
	public void setIdLineaAsientoContable(long idLineaAsientoContable) {
		this.idLineaAsientoContable = idLineaAsientoContable;
	}
	
	public void setAsientoContable(AsientoContable asientoContable) {
		this.asientoContable = asientoContable;
	}
	public AsientoContable getAsientoContable() {
		return asientoContable;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		if (this.idLineaAsientoContable == -1){
			return super.hashCode();
		}
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + (int) (idLineaAsientoContable ^ (idLineaAsientoContable >>> 32));
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
		final LineaAsientoContable other = (LineaAsientoContable) obj;
		if (idLineaAsientoContable != other.idLineaAsientoContable) {
			return false;
		}
		return true;
	}
	public Double getImporteDebe() {
		return importeDebe;
	}
	public void setImporteDebe(Double importeDebe) {
		this.importeDebe = importeDebe;
	}
	public Double getImporteHaber() {
		return importeHaber;
	}
	public void setImporteHaber(Double importeHaber) {
		this.importeHaber = importeHaber;
	}
	@Override
	public String toString(){
		return this.cuenta+" Debe: "+this.importeDebe+". Haber:"+this.importeHaber;
	}
}
