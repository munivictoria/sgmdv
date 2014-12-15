/**
 * 
 */
package com.trascender.contabilidad.recurso.persistent;

import java.io.Serializable;
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

@Entity
@Table(name = "BANCO")
public class Banco implements Serializable{

	public static final long serialVersionUID = -5411641271539925210L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_banco")
	@SequenceGenerator(name = "gen_id_banco", sequenceName = "gen_id_banco",allocationSize = 1)
	@Column(name="ID_BANCO")
	private long idBanco = -1;
	private String nombre;
	private String sucursal;
	//Relacion con otros objetos
	
	@OneToMany(mappedBy = "banco", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<CuentaBancaria> cuentasBancarias;

	public Set<CuentaBancaria> getCuentasBancarias() {
		return cuentasBancarias;
	}
	public void setCuentasBancarias(Set<CuentaBancaria> cuentasBancarias) {
		this.cuentasBancarias = cuentasBancarias;
	}
	public long getIdBanco() {
		return idBanco;
	}
	public void setIdBanco(long idBanco) {
		this.idBanco = idBanco;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getSucursal() {
		return sucursal;
	}
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}
	
	@Override
	public String toString(){
		String linea = this.nombre;;
		
		if (this.sucursal != null){
			linea += " - "+this.sucursal;
		}
		return linea;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		if (this.idBanco == -1){
			return super.hashCode();
		}
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + (int) (idBanco ^ (idBanco >>> 32));
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
		final Banco other = (Banco) obj;
		if (idBanco != other.idBanco) {
			return false;
		}
		return true;
	}
	
}
