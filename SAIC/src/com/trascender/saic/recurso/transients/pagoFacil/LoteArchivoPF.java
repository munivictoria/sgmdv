package com.trascender.saic.recurso.transients.pagoFacil;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class LoteArchivoPF {

	public static final Integer recordCode = 3;
	
	private Date createDate;
	private Integer batchNumber;
	private String description;
	
	private Set<RegistroDetallePF> listaRegistroPagos = new HashSet<RegistroDetallePF>();

	private ColaLotePF colaLote = new ColaLotePF();
	
	public void addRegistroDetallePF(RegistroDetallePF pRegistroDetallePF){
		this.listaRegistroPagos.add(pRegistroDetallePF);
	}
	
	public ColaLotePF getColaLote() {
		return colaLote;
	}

	public void setColaLote(ColaLotePF colaLote) {
		this.colaLote = colaLote;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public Integer getRecordCode() {
		return recordCode;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(Integer batchNumber) {
		this.batchNumber = batchNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<RegistroDetallePF> getListaRegistroPagos() {
		return listaRegistroPagos;
	}

	public void setListaRegistroPagos(Set<RegistroDetallePF> listaRegistroPagos) {
		this.listaRegistroPagos = listaRegistroPagos;
	}
	
	
}
