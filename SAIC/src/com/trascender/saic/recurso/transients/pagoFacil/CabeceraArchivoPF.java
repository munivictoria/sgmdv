package com.trascender.saic.recurso.transients.pagoFacil;

import java.util.Date;

public class CabeceraArchivoPF {
	
	public static final Integer recordCode = 1;
	
	private Date createDate; 
	private String originName; 
	private Integer clientNumber;
	private String clientName;
	
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getOriginName() {
		return originName;
	}
	public void setOriginName(String originName) {
		this.originName = originName;
	}
	public Integer getClientNumber() {
		return clientNumber;
	}
	public void setClientNumber(Integer clientNumber) {
		this.clientNumber = clientNumber;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	
	
}
