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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "SECCION_CIIU")
public class SeccionCiiu implements Serializable{

	public static final long serialVersionUID = 2230257458796982963L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_seccion_ciiu")
	@SequenceGenerator(name = "gen_id_seccion_ciiu", sequenceName = "gen_id_seccion_ciiu",allocationSize = 1)
	@Column(name="ID_SECCION_CIIU")
	private long idSeccionCiiu = -1;

	private String codigo;
	
	private String nombre;
	
	@OneToMany(mappedBy = "seccionCiiu", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<GrupoCiiu> listaGruposCiiu = new ArrayList<GrupoCiiu>();

	public SeccionCiiu(){
		this.listaGruposCiiu = new ArrayList<GrupoCiiu>();
	}
	
	public long getIdSeccionCiiu() {
		return idSeccionCiiu;
	}

	public void setIdSeccionCiiu(long pIdSeccionCiiu) {
		idSeccionCiiu = pIdSeccionCiiu;
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

	public List<GrupoCiiu> getListaGruposCiiu() {
		return listaGruposCiiu;
	}

	public void setListaGruposCiiu(List<GrupoCiiu> pListaGruposCiiu) {
		listaGruposCiiu = pListaGruposCiiu;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ (int) (idSeccionCiiu ^ (idSeccionCiiu >>> 32));
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
		SeccionCiiu other = (SeccionCiiu) obj;
		if (idSeccionCiiu != other.idSeccionCiiu)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.nombre+ " [" + this.codigo + "]";
	}
	
	
	
	
}
