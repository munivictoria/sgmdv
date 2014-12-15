package com.trascender.framework.recurso.persistent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
@Table(name = "GRUPO_CIIU")
public class GrupoCiiu implements Serializable{
	
	private static final long serialVersionUID = -1780983277348675919L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_grupo_ciiu")
	@SequenceGenerator(name = "gen_id_grupo_ciiu", sequenceName = "gen_id_grupo_ciiu",allocationSize = 1)
	@Column(name="ID_GRUPO_CIIU")
	private long idGrupoCiiu = -1;

	private String codigo;
	
	private String nombre;
	
	@ManyToOne
	@JoinColumn(name = "ID_SECCION_CIIU")
	private SeccionCiiu seccionCiiu;
	
	@OneToMany(mappedBy = "grupoCiiu", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CodigoCiiu> listaCodigosCiiu = new ArrayList<CodigoCiiu>();
	
	public GrupoCiiu(){
		this.listaCodigosCiiu = new ArrayList<CodigoCiiu>();
	}
	
	public long getIdGrupoCiiu() {
		return idGrupoCiiu;
	}
	public void setIdGrupoCiiu(long pIdGrupoCiiu) {
		idGrupoCiiu = pIdGrupoCiiu;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String pCodigo) {
		codigo = pCodigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String pNombre) {
		nombre = pNombre;
	}

	public SeccionCiiu getSeccionCiiu() {
		return seccionCiiu;
	}

	public void setSeccionCiiu(SeccionCiiu pSeccionCiiu) {
		seccionCiiu = pSeccionCiiu;
	}

	public List<CodigoCiiu> getListaCodigosCiiu() {
		return listaCodigosCiiu;
	}

	public void setListaCodigosCiiu(List<CodigoCiiu> pListaCodigosCiiu) {
		listaCodigosCiiu = pListaCodigosCiiu;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idGrupoCiiu ^ (idGrupoCiiu >>> 32));
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
		GrupoCiiu other = (GrupoCiiu) obj;
		if (idGrupoCiiu != other.idGrupoCiiu)
			return false;
		return true;
	}

	@Override
	public String toString() {		
		return this.nombre+ " [" + this.codigo + "]";
	}
	
}
