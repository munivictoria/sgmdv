package com.trascender.habilitaciones.recurso.persistent.cementerio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PostPersist;
import javax.persistence.Transient;

import org.hibernate.annotations.Where;

import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
import com.trascender.framework.util.EntidadTrascender;
import com.trascender.framework.util.LogAuditoria;
import com.trascender.framework.util.anotations.NoAuditable;
import com.trascender.habilitaciones.recurso.persistent.AsocRegAlicuota;

@Entity
@DiscriminatorValue(value="PARCELA_CEMENTERIO")
public class ParcelaCementerio extends AsocRegAlicuota implements Serializable, EntidadTrascender{
	
	public static final long serialVersionUID = -6216706651688728880L;

	private Double superficie;
	
	@OneToMany(mappedBy="parcelaCementerio", cascade = CascadeType.ALL, orphanRemoval=true)
	private List<Difunto> listaDifuntos = new ArrayList<Difunto>();
	
	@NoAuditable
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name = "ID_TITULO_PROPIEDAD")
	private Concesion concesion;
	
	@Where(clause = "id_recurso = " + serialVersionUID)
	@OneToMany(mappedBy = "idEntidad", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
	private List<AtributoDinamico<?>> listaAtributosDinamicos = new ArrayList<AtributoDinamico<?>>();
	
	@OrderBy(value="fecha")
	@Where(clause = "id_recurso = " + serialVersionUID)
	@OneToMany(mappedBy = "idEntidad", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<LogAuditoria> listaLogsAuditoria = new ArrayList<LogAuditoria>();
	
	public Double getSuperficie() {
		return superficie;
	}

	public void setSuperficie(Double superficie) {
		this.superficie = superficie;
	}

	public List<Difunto> getListaDifuntos() {
		return listaDifuntos;
	}

	public void setListaDifuntos(List<Difunto> listaDifuntos) {
		this.listaDifuntos = listaDifuntos;
	}
	
	public Concesion getConcesion() {
		return concesion;
	}

	public void setConcesion(Concesion concesion) {
		this.concesion = concesion;
	}

	public void addAtributoDinamico(AtributoDinamico<?> pAtributoDinamico){
		pAtributoDinamico.setIdEntidad(this.getIdAsocRegAlicuota());
		this.listaAtributosDinamicos.add(pAtributoDinamico);
	}
	
	public List<AtributoDinamico<?>> getListaAtributosDinamicos() {
		return listaAtributosDinamicos;
	}
	
	public void setListaAtributosDinamicos(List<AtributoDinamico<?>> pListaAtributosDinamicos) {
		this.listaAtributosDinamicos.clear();
		for (AtributoDinamico<?> cadaAtributo : pListaAtributosDinamicos){
			if (cadaAtributo.getValor() != null){
				this.addAtributoDinamico(cadaAtributo);
			}
		}
	}
	
	@Override
	public String toString() {
		String locRetorno = "Parcela Cementerio ";
		
		if(this.getSuperficie() != null) {
			locRetorno += "Superficie " + this.getSuperficie();
		}
		
		if (concesion != null){
			if(concesion.getListaRegistrosPropietarios() != null && concesion.getListaRegistrosPropietarios().iterator().hasNext()){
				locRetorno += " ["+concesion.getListaRegistrosPropietarios().iterator().next().getPersona().getDenominacion()+"]";
			}
		}
		return locRetorno;
	}
	
	@PostPersist
	public void postPersist(){
		for (AtributoDinamico<?> cadaAtributo : getListaAtributosDinamicos()){
			cadaAtributo.setIdEntidad(getIdAsocRegAlicuota());
		}
	}
	
	//*********************************************************************************************************************************************************************************/
	// AUDITORIA

	@Transient
	private long llaveUsuarioAuditoria;
	@Transient
	private String comentarioAuditoria;

	public List<LogAuditoria> getListaLogsAuditoria() {
		return listaLogsAuditoria;
	}

	public void setListaLogsAuditoria(List<LogAuditoria> pListaLogsAuditoria) {
		this.listaLogsAuditoria = pListaLogsAuditoria;
	}

	public long getLlaveUsuarioAuditoria() {
		return llaveUsuarioAuditoria;
	}

	public void setLlaveUsuarioAuditoria(long llaveUsuarioAuditoria) {
		this.llaveUsuarioAuditoria = llaveUsuarioAuditoria;
		if(this.getConcesion() != null) {
			this.getConcesion().setLlaveUsuarioAuditoria(llaveUsuarioAuditoria);
		}
	}

	public String getComentarioAuditoria() {
		return comentarioAuditoria;
	}

	public void setComentarioAuditoria(String comentarioAuditoria) {
		this.comentarioAuditoria = comentarioAuditoria;
		if(this.getConcesion() != null) {
			this.getConcesion().setComentarioAuditoria(comentarioAuditoria);
		}
	}

	public long getIdEntidad() {
		return this.getIdAsocRegAlicuota();
	}

	public long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getNombrePropiedadId() {
		return "idAsocRegAlicuota";
	}

	public boolean isAuditable() {
		return true;
	}
}
