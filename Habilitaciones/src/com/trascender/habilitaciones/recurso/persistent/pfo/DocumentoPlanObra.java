package com.trascender.habilitaciones.recurso.persistent.pfo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.Where;

import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
import com.trascender.framework.util.LogAuditoria;
import com.trascender.framework.util.Util;
import com.trascender.habilitaciones.recurso.persistent.DocHabilitanteEspecializado;

@Entity
@DiscriminatorValue(value = "PO")
public class DocumentoPlanObra extends DocHabilitanteEspecializado{

	public static final String DISCRIMINATOR_VALUE="PO";
	
	public static final long serialVersionUID = 7605345840513026280L;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_OBRA", nullable = false)
	private Obra obra;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PLAN_CUENTA_OBRA", nullable = false)
	private PlanCuentaObra planCuentaObra;
	
	@OneToMany(mappedBy = "documentoPlanObra", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<CuadraAfectada> listaCuadrasAfectadas=new HashSet<CuadraAfectada>();
	
	@Where(clause = "id_recurso = " + serialVersionUID)
	@OneToMany(mappedBy = "idEntidad", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
	private List<AtributoDinamico<?>> listaAtributosDinamicos = new ArrayList<AtributoDinamico<?>>();
	
	@OrderBy(value="fecha")
	@Where(clause = "id_recurso = " + serialVersionUID)
	@OneToMany(mappedBy = "idEntidad", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
	private List<LogAuditoria> listaLogsAuditoria = new ArrayList<LogAuditoria>();
	
	public void addAtributoDinamico(AtributoDinamico<?> pAtributoDinamico){
		pAtributoDinamico.setIdEntidad(this.getIdDocHabilitanteEspecializado());
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
	
	public Set<CuadraAfectada> getListaCuadrasAfectadas() {
		return listaCuadrasAfectadas;
	}
	public void setListaCuadrasAfectadas(Set<CuadraAfectada> listaCuadrasAfectadas) {
		this.listaCuadrasAfectadas = listaCuadrasAfectadas;
	}
	
	public Obra getObra() {
		return obra;
	}
	public void setObra(Obra obra) {
		this.obra = obra;
	}
	
	public PlanCuentaObra getPlanCuentaObra() {
		return planCuentaObra;
	}
	public void setPlanCuentaObra(PlanCuentaObra planCuentaObra) {
		this.planCuentaObra = planCuentaObra;
	}
	
	@Override
	public String toString() {
		String retorno = Util.returnToString(this, serialVersionUID);
		if(retorno != null) {
			return retorno;
		}
		
		StringBuilder locString=new StringBuilder();
	
		locString.append("[PFO]");
		
		if (this.getObra()!=null){
			locString.append(" | Obra: "+this.getObra().toString()+" - ");
		}
		if (this.getParcela()!=null){
			locString.append(" | Parcela: "+this.getParcela().toString()+" - ");
		}
		if (this.getPlanCuentaObra()!=null){
			locString.append(" | Plan de cuenta: "+this.getPlanCuentaObra().toString());
		}
		return locString.toString();
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
}
