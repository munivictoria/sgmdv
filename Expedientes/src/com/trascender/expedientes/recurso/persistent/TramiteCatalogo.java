/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

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
@Table(name = "EXP_TRAMITECATALOGO")
public class TramiteCatalogo implements Serializable {

	public static final long serialVersionUID = -8010304888770896737L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_id_exp_tramiteCatalogo")
	@SequenceGenerator(name = "gen_id_exp_tramiteCatalogo", sequenceName = "gen_id_exp_tramiteCatalogo", allocationSize = 1)
	@Column(name = "ID_TRAMITECATALOGO")
	private long idTramiteCatalogo = -1l;

	@Column(name = "NOMBRE", nullable = false)
	private String nombre;

	@Column(name = "REQUERIDO_PARA_AVANZAR_DE_FASE")
	private boolean requeridoParaAvanzarDeFase = false;

	@Column(name = "REINICIAR_CON_FASE")
	private boolean reiniciarConFase = false;

	@Enumerated(EnumType.STRING)
	private EstadoPlantilla estado = EstadoPlantilla.ACTIVO;

	@ManyToMany
	@JoinTable(name = "EXP_RELA_TRAMITECATALOGO_DOCUMENTOCATALOGO", joinColumns = @JoinColumn(name = "ID_TRAMITECATALOGO"), inverseJoinColumns = @JoinColumn(name = "ID_DOCUMENTOCATALOGO"))
	private List<DocumentoCatalogo> listaDocumentosCatalogos = new ArrayList<DocumentoCatalogo>();

	@ManyToMany
	@JoinTable(name = "EXP_RELA_TRAMITECATALOGO_ESTADOTRAMITE", joinColumns = @JoinColumn(name = "ID_TRAMITECATALOGO"), inverseJoinColumns = @JoinColumn(name = "ID_ESTADOTRAMITE"))
	private List<EstadoTramite> listaEstadosTramite = new ArrayList<EstadoTramite>();

	@Where(clause = "id_recurso = " + serialVersionUID)
	@OneToMany(mappedBy = "idEntidad", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
	private List<AtributoDinamico<?>> listaAtributosDinamicos = new ArrayList<AtributoDinamico<?>>();

	public boolean isRequeridoParaAvanzarDeFase() {
		return requeridoParaAvanzarDeFase;
	}

	public void setRequeridoParaAvanzarDeFase(boolean requeridoParaAvanzarDeFase) {
		this.requeridoParaAvanzarDeFase = requeridoParaAvanzarDeFase;
	}

	public boolean isReiniciarConFase() {
		return reiniciarConFase;
	}

	public void setReiniciarConFase(boolean reiniciarConFase) {
		this.reiniciarConFase = reiniciarConFase;
	}

	public long getIdTramiteCatalogo() {
		return idTramiteCatalogo;
	}

	public void setIdTramiteCatalogo(long idTramiteCatalogo) {
		this.idTramiteCatalogo = idTramiteCatalogo;
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

	public List<DocumentoCatalogo> getListaDocumentosCatalogos() {
		return listaDocumentosCatalogos;
	}

	public void setListaDocumentosCatalogos(List<DocumentoCatalogo> listaDocumentosCatalogos) {
		this.listaDocumentosCatalogos = listaDocumentosCatalogos;
	}

	public List<EstadoTramite> getListaEstadosTramite() {
		return listaEstadosTramite;
	}

	public void setListaEstadosTramite(List<EstadoTramite> listaEstadosTramite) {
		this.listaEstadosTramite = listaEstadosTramite;
	}

	public void addAtributoDinamico(AtributoDinamico<?> pAtributoDinamico) {
		pAtributoDinamico.setIdEntidad(this.idTramiteCatalogo);
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
		final TramiteCatalogo other = (TramiteCatalogo) obj;
		if(idTramiteCatalogo != other.idTramiteCatalogo)
			return false;
		
		return true;
	}
	
}