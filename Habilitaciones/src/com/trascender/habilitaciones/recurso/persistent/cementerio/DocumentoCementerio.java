package com.trascender.habilitaciones.recurso.persistent.cementerio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PostPersist;

import org.hibernate.annotations.Where;

import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
import com.trascender.framework.util.LogAuditoria;
import com.trascender.framework.util.Util;
import com.trascender.habilitaciones.recurso.persistent.AsocRegAlicuota;
import com.trascender.habilitaciones.recurso.persistent.DocHabilitanteEspecializado;

@Entity
@DiscriminatorValue(value="CMT")
public class DocumentoCementerio extends DocHabilitanteEspecializado{

	public static final long serialVersionUID = 3886426769569924460L;
	
	@Where(clause = "id_recurso = " + serialVersionUID)
	@OneToMany(mappedBy = "idEntidad", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
	private List<AtributoDinamico<?>> listaAtributosDinamicos = new ArrayList<AtributoDinamico<?>>();
	
	@OrderBy(value="fecha")
	@Where(clause = "id_recurso = " + serialVersionUID)
	@OneToMany(mappedBy = "idEntidad", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<LogAuditoria> listaLogsAuditoria = new ArrayList<LogAuditoria>();
	
	@Column(name = "NUMERO_CUENTA")
	private Integer numeroCuenta;
	
	public Integer getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(Integer numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

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
		for (AtributoDinamico<?> cadaAtributo : pListaAtributosDinamicos){
			if (cadaAtributo.getValor() != null){
				this.addAtributoDinamico(cadaAtributo);
			}
		}
	}
	
	public void addParcelaCementerio(ParcelaCementerio pParcelaCementerio) {
		this.getListaAsocRegAlicuota().add(pParcelaCementerio);
		pParcelaCementerio.setDocHabilitanteEspecializado(this);
	}
	
	@PostPersist
	public void postPersist(){
		for (AtributoDinamico<?> cadaAtributo : getListaAtributosDinamicos()){
			cadaAtributo.setIdEntidad(getIdDocHabilitanteEspecializado());
		}
	}
	
	public long getSerialVersionUID() {
		return serialVersionUID;
	}
	
	public List<LogAuditoria> getListaLogsAuditoria() {
		return listaLogsAuditoria;
	}

	public void setListaLogsAuditoria(List<LogAuditoria> pListaLogsAuditoria) {
		this.listaLogsAuditoria = pListaLogsAuditoria;
	}
	
	@Override
	public String toString() {
		String retorno = Util.returnToString(this, serialVersionUID);
		if(retorno != null) {
			return retorno;
		}
		
		StringBuilder locStringBuilder=new StringBuilder();

		locStringBuilder.append("[CEMENTERIO]");

		for(AsocRegAlicuota cadaAsoc : this.getListaAsocRegAlicuota()){
			locStringBuilder.append(" " + cadaAsoc);
		}
		return locStringBuilder.toString();
	}
}
