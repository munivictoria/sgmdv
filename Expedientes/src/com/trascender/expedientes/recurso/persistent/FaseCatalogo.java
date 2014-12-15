
package com.trascender.expedientes.recurso.persistent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import com.trascender.expedientes.enums.EstadoPlantilla;
import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;

@Entity
@Table(name = "EXP_FASECATALOGO")
public class FaseCatalogo implements Serializable {

	public static final long serialVersionUID = 1275627737083850837L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_id_exp_faseCatalogo")
	@SequenceGenerator(name = "gen_id_exp_faseCatalogo", sequenceName = "gen_id_exp_faseCatalogo", allocationSize = 1)
	@Column(name = "ID_FASECATALOGO")
	private long idFaseCatalogo = -1l;

	@Column(name = "NOMBRE", nullable = false)
	private String nombre;

	@Enumerated(EnumType.STRING)
	private EstadoPlantilla estado = EstadoPlantilla.ACTIVO;

	@ManyToMany
	@JoinTable(name = "EXP_RELA_FASECATALOGO_TRAMITECATALOGO", joinColumns = @JoinColumn(name = "ID_FASECATALOGO"), inverseJoinColumns = @JoinColumn(name = "ID_TRAMITECATALOGO"))
	private List<TramiteCatalogo> listaTramitesCatalogos = new ArrayList<TramiteCatalogo>();

	@Where(clause = "id_recurso = " + serialVersionUID)
	@OneToMany(mappedBy = "idEntidad", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
	private List<AtributoDinamico<?>> listaAtributosDinamicos = new ArrayList<AtributoDinamico<?>>();

	@ManyToMany
	@JoinTable(name = "EXP_RELA_FASECATALOGO_FASEESPECIAL", joinColumns = @JoinColumn(name = "ID_FASECATALOGO"), inverseJoinColumns = @JoinColumn(name = "ID_FASEESPECIAL"))
	private List<FaseCatalogo> listaFasesEspeciales = new ArrayList<FaseCatalogo>();

	public long getIdFaseCatalogo() {
		return idFaseCatalogo;
	}

	public void setIdFaseCatalogo(long idFaseCatalogo) {
		this.idFaseCatalogo = idFaseCatalogo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public EstadoPlantilla getEstado() {
		return estado;
	}

	public void setEstado(EstadoPlantilla estado) {
		this.estado = estado;
	}

	public List<TramiteCatalogo> getListaTramitesCatalogos() {
		return listaTramitesCatalogos;
	}

	public void setListaTramitesCatalogos(List<TramiteCatalogo> listaTramitesCatalogos) {
		this.listaTramitesCatalogos = listaTramitesCatalogos;
	}

	public void addAtributoDinamico(AtributoDinamico<?> pAtributoDinamico) {
		pAtributoDinamico.setIdEntidad(this.idFaseCatalogo);
		this.listaAtributosDinamicos.add(pAtributoDinamico);
	}

	public List<AtributoDinamico<?>> getListaAtributosDinamicos() {
		return listaAtributosDinamicos;
	}

	public void setListaAtributosDinamicos(List<AtributoDinamico<?>> pListaAtributosDinamicos) {
		this.listaAtributosDinamicos.clear();
		for(AtributoDinamico<?> cadaAtributo : pListaAtributosDinamicos) {
			if(cadaAtributo.getValor() != null) {
				this.addAtributoDinamico(cadaAtributo);
			}
		}
	}

	@Override
	public String toString() {
		return this.nombre;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		final FaseCatalogo other = (FaseCatalogo) obj;
		if(idFaseCatalogo != other.idFaseCatalogo)
			return false;
		return true;
	}

	public List<FaseCatalogo> getListaFasesEspeciales() {
		return listaFasesEspeciales;
	}

	public void setListaFasesEspeciales(List<FaseCatalogo> listaFasesEspeciales) {
		this.listaFasesEspeciales = listaFasesEspeciales;
	}
}