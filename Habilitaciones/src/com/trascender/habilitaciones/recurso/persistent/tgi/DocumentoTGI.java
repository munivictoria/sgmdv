
package com.trascender.habilitaciones.recurso.persistent.tgi;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PostPersist;

import org.hibernate.annotations.Where;

import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
import com.trascender.framework.util.LogAuditoria;
import com.trascender.framework.util.Util;
import com.trascender.framework.util.anotations.NoAuditable;
import com.trascender.habilitaciones.recurso.persistent.DocHabilitanteEspecializado;

/**
 * 
 * @author Mariano Lusardi
 * @hibernate.subclass discriminator-value = "TGI"
 */

@Entity
@DiscriminatorValue(value = "TGI")
public class DocumentoTGI extends DocHabilitanteEspecializado {

	public static final String DISCRIMINATOR_VALUE = "TGI";

	public static final long serialVersionUID = 7360945272398009769L;

	@Where(clause = "id_recurso = " + serialVersionUID)
	@OneToMany(mappedBy = "idEntidad", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
	private final List<AtributoDinamico<?>> listaAtributosDinamicos = new ArrayList<AtributoDinamico<?>>();

	@NoAuditable
	@OrderBy(value = "fecha")
	@Where(clause = "id_recurso = " + serialVersionUID)
	@OneToMany(mappedBy = "idEntidad", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
	private List<LogAuditoria> listaLogsAuditoria = new ArrayList<LogAuditoria>();

	@Override
	public void addAtributoDinamico(AtributoDinamico<?> pAtributoDinamico) {
		pAtributoDinamico.setIdEntidad(this.getIdDocHabilitanteEspecializado());
		this.listaAtributosDinamicos.add(pAtributoDinamico);
	}

	@Override
	public List<AtributoDinamico<?>> getListaAtributosDinamicos() {
		return listaAtributosDinamicos;
	}

	@Override
	public void setListaAtributosDinamicos(List<AtributoDinamico<?>> pListaAtributosDinamicos) {
		this.listaAtributosDinamicos.clear();
		for(AtributoDinamico<?> cadaAtributo : pListaAtributosDinamicos) {
			if(cadaAtributo.getValor() != null) {
				this.addAtributoDinamico(cadaAtributo);
			}
		}
	}

	@Override
	public List<LogAuditoria> getListaLogsAuditoria() {
		return listaLogsAuditoria;
	}

	@Override
	public void setListaLogsAuditoria(List<LogAuditoria> pListaLogsAuditoria) {
		this.listaLogsAuditoria = pListaLogsAuditoria;
	}

	@Override
	public String toString() {
		String retorno = Util.returnToString(this, serialVersionUID);
		if(retorno != null) {
			return retorno;
		}
		
		return "[TGI]"
				+ ((this.getParcela() != null 
				&& this.getParcela().getNomenclaturaCatastral() != null 
				&& this.getParcela().getNomenclaturaCatastral().getNroParcela() != null)
				? (" Parcela: " + this.getParcela().getNomenclaturaCatastral().getNroParcela())
				: "");
	}

	@Override
	@PostPersist
	public void postPersist() {
		for(AtributoDinamico<?> cadaAtributo : getListaAtributosDinamicos()) {
			cadaAtributo.setIdEntidad(getIdDocHabilitanteEspecializado());
		}
	}

	public long getSerialVersionUID() {
		return serialVersionUID;
	}
}
