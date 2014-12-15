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
@Table(name = "LINEA_SUBDIARIO_CAJA")
public class LineaSubdiarioCaja implements Serializable{

	/**
	 * 
	 */
	public static final long serialVersionUID = -8023145077343363860L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_linea_subdiario_caja")
	@SequenceGenerator(name = "gen_id_linea_subdiario_caja", sequenceName = "gen_id_linea_subdiario_caja",allocationSize = 1)
	@Column(name="ID_LINEA_SUBDIARIO_CAJA")
	private long idLineaSubdiarioCaja =-1;
	private Integer dia;
	private Double importe = new Double(0);
	//Relacion con otros objetos
	
	@ManyToOne
	@JoinColumn(name = "ID_CUENTA")
	private Cuenta cuenta;
	
	@ManyToOne
	@JoinColumn(name = "ID_SUBDIARIO_CAJA")
	private SubdiarioCaja subdiarioCaja;
	
	public Integer getDia() {
		return dia;
	}
	public void setDia(Integer dia) {
		this.dia = dia;
	}
	public long getIdLineaSubdiarioCaja() {
		return idLineaSubdiarioCaja;
	}
	public void setIdLineaSubdiarioCaja(long idLineaSubdiarioCaja) {
		this.idLineaSubdiarioCaja = idLineaSubdiarioCaja;
	}
	public Double getImporte() {
		return importe;
	}
	public void setImporte(Double importe) {
		this.importe = importe;
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
		if (this.idLineaSubdiarioCaja==-1){
			return super.hashCode();
		}
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + (int) (idLineaSubdiarioCaja ^ (idLineaSubdiarioCaja >>> 32));
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
		final LineaSubdiarioCaja other = (LineaSubdiarioCaja) obj;
		if (idLineaSubdiarioCaja != other.idLineaSubdiarioCaja) {
			return false;
		}
		return true;
	}
	public SubdiarioCaja getSubdiarioCaja() {
		return subdiarioCaja;
	}
	public void setSubdiarioCaja(SubdiarioCaja subdiarioCaja) {
		this.subdiarioCaja = subdiarioCaja;
	}
}
