/**
 * 
 */
package com.trascender.contabilidad.recurso.persistent;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "LIBRO_BANCO")
public class LibroBanco implements Serializable{

	public  static final long serialVersionUID = -8277391294071941973L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_libro_banco")
	@SequenceGenerator(name = "gen_id_libro_banco", sequenceName = "gen_id_libro_banco",allocationSize = 1)
	@Column(name="ID_LIBRO_BANCO")
	private long idLibroBanco =-1;
	private String nombre;
	
	@Enumerated(EnumType.STRING)
	private Estado estado;
	public enum Estado{ACTIVO, INACTIVO;
	@Override
	public String toString(){
			return com.trascender.framework.util.Util.capitalizeEnumName(super.toString());
	   }
	}
	//Relacion con otros objetos
	
	@OneToMany(mappedBy = "libroBanco", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<LineaLibroBanco> lineasLibroBanco = new HashSet<LineaLibroBanco>();
	
	@ManyToOne
	@JoinColumn(name = "ID_CUENTA_BANCARIA")
	private CuentaBancaria cuentaBancaria;
	public long getIdLibroBanco() {
		return idLibroBanco;
	}

	public void setIdLibroBanco(long idLibroBanco) {
		this.idLibroBanco = idLibroBanco;
	}

	public Set<LineaLibroBanco> getLineasLibroBanco() {
		return lineasLibroBanco;
	}

	public void setLineasLibroBanco(Set<LineaLibroBanco> lineasLibroBanco) {
		this.lineasLibroBanco = lineasLibroBanco;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public CuentaBancaria getCuentaBancaria() {
		return cuentaBancaria;
	}

	public void setCuentaBancaria(CuentaBancaria cuentaBancaria) {
		this.cuentaBancaria = cuentaBancaria;
	}

	public Estado getEstado(){
		return estado;
	}

	public void setEstado(Estado estado){
		this.estado = estado;
	}
	@Override
	public String toString(){
		return this.nombre;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		if (this.idLibroBanco == -1){
			return super.hashCode();
		}
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + (int) (idLibroBanco ^ (idLibroBanco >>> 32));
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
		final LibroBanco other = (LibroBanco) obj;
		if (idLibroBanco != other.idLibroBanco) {
			return false;
		}
		return true;
	}
	
}
