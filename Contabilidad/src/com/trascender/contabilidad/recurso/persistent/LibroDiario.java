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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "LIBRO_DIARIO")
public class LibroDiario implements Serializable{

	public static final long serialVersionUID = 3258929364385558103L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_libro_diario")
	@SequenceGenerator(name = "gen_id_libro_diario", sequenceName = "gen_id_libro_diario",allocationSize = 1)
	@Column(name="ID_LIBRO_DIARIO")
	private long idLibroDiario = -1;
	
	@Column(name = "CODIGO_AUTORIZACION")
	private String codigoAutorizacion;
	private String numero;
	//Relacion con otros objetos
	
	@OneToMany(mappedBy = "libroDiario", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<FolioLibroDiario> foliosLibroDiario = new HashSet<FolioLibroDiario>();
	
	public String getCodigoAutorizacion() {
		return codigoAutorizacion;
	}
	public void setCodigoAutorizacion(String codigoAutorizacion) {
		this.codigoAutorizacion = codigoAutorizacion;
	}
	public Set<FolioLibroDiario> getFoliosLibroDiario() {
		return foliosLibroDiario;
	}
	public void setFoliosLibroDiario(Set<FolioLibroDiario> foliosLibroDiario) {
		this.foliosLibroDiario = foliosLibroDiario;
	}
	public long getIdLibroDiario() {
		return idLibroDiario;
	}
	public void setIdLibroDiario(long idLibroDiario) {
		this.idLibroDiario = idLibroDiario;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	@Override
	public String toString(){
		return this.numero +" ("+this.codigoAutorizacion+")";
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		if (this.idLibroDiario == -1){
			return super.hashCode();
		}
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + (int) (idLibroDiario ^ (idLibroDiario >>> 32));
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
		final LibroDiario other = (LibroDiario) obj;
		if (idLibroDiario != other.idLibroDiario) {
			return false;
		}
		return true;
	}
	
	

}
