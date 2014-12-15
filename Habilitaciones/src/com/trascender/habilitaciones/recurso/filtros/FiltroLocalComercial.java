package com.trascender.habilitaciones.recurso.filtros;

import java.util.List;

import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.habilitaciones.recurso.persistent.shps.DocumentoSHPS;
import com.trascender.habilitaciones.recurso.persistent.shps.LocalComercial;

public class FiltroLocalComercial extends FiltroAbstracto<LocalComercial>{
	private static final long serialVersionUID = 8088215966225576299L;
	
	private DocumentoSHPS documentoEspecializado;
	private String numeroInscripcion;
	private Parcela parcela; 
	private Boolean activo;
	private List<Long> listaIdParcelas;
	
	public DocumentoSHPS getDocumentoEspecializado() {
		return documentoEspecializado;
	}
	public void setDocumentoEspecializado(DocumentoSHPS documentoEspecializado) {
		this.documentoEspecializado = documentoEspecializado;
	}
	public String getNumeroInscripcion() {
		return numeroInscripcion;
	}
	public void setNumeroInscripcion(String numeroInscripcion) {
		this.numeroInscripcion = numeroInscripcion;
	}
	public Parcela getParcela() {
		return parcela;
	}
	public void setParcela(Parcela parcela) {
		this.parcela = parcela;
	}
	public Boolean getActivo() {
		return activo;
	}
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	public List<Long> getListaIdParcelas() {
		return listaIdParcelas;
	}
	public void setListaIdParcelas(List<Long> listaIdParcelas) {
		this.listaIdParcelas = listaIdParcelas;
	}

}
