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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "FOLIO_LIBRO_DIARIO")
public class FolioLibroDiario implements Serializable{

	/**
	 * 
	 */
	public static final long serialVersionUID = -6131302690919059289L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_folio_libro_diario")
	@SequenceGenerator(name = "gen_id_folio_libro_diario", sequenceName = "gen_id_folio_libro_diario",allocationSize = 1)
	@Column(name="ID_FOLIO_LIBRO_DIARIO")
	private long idFolioLibroDiario =-1;
	private String numero;
	//Relacion con otros objetos
	
	@ManyToOne
	@JoinColumn(name = "ID_LIBRO_DIARIO")
	private LibroDiario libroDiario;
	
	@OneToMany(mappedBy = "folioLibroDiario", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<AsientoContable> asientosContables = new HashSet<AsientoContable>();
	
//	@ManyToMany
//	@JoinTable(name = "RELA_LIN_MAY_LIB_DIAR", joinColumns=@JoinColumn(name = "ID_FOLIO_LIBRO_DIARIO"), inverseJoinColumns=@JoinColumn(name = "ID_LINEA_MAYOR"))
	@OneToMany(mappedBy = "folioLibroDiario")
	private Set<LineaMayor> lineaMayor = new HashSet<LineaMayor>();
	

	public long getIdFolioLibroDiario() {
		return idFolioLibroDiario;
	}

	public void setIdFolioLibroDiario(long idFolioLibroDiario) {
		this.idFolioLibroDiario = idFolioLibroDiario;
	}

	public LibroDiario getLibroDiario() {
		return libroDiario;
	}

	public void setLibroDiario(LibroDiario libroDiario) {
		this.libroDiario = libroDiario;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Set<AsientoContable> getAsientosContables() {
		return asientosContables;
	}

	public void setAsientosContables(Set<AsientoContable> asientosContables) {
		this.asientosContables = asientosContables;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		if (this.idFolioLibroDiario == -1){
			return super.hashCode();
		}
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + (int) (idFolioLibroDiario ^ (idFolioLibroDiario >>> 32));
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
		final FolioLibroDiario other = (FolioLibroDiario) obj;
		if (idFolioLibroDiario != other.idFolioLibroDiario) {
			return false;
		}
		return true;
	}

	public Set<LineaMayor> getLineaMayor() {
		return lineaMayor;
	}

	public void setLineaMayor(Set<LineaMayor> lineaMayor) {
		this.lineaMayor = lineaMayor;
	}
	
	@Override
	public String toString(){
		return "Folio "+ this.numero;
	}
	
}
