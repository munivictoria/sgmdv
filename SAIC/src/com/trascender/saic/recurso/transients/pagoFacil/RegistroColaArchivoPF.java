package com.trascender.saic.recurso.transients.pagoFacil;

import java.util.Date;

public class RegistroColaArchivoPF {
	
	public static final Integer recordCode = 9;
	
	private Date createDate;
	private Integer totalBatches;
	private Integer filePaymentCount;
	private Double filePaymentAmount;
	private Integer fileCount;
	
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Integer getTotalBatches() {
		return totalBatches;
	}
	public void setTotalBatches(Integer totalBatches) {
		this.totalBatches = totalBatches;
	}
	public Integer getFilePaymentCount() {
		return filePaymentCount;
	}
	public void setFilePaymentCount(Integer filePaymentCount) {
		this.filePaymentCount = filePaymentCount;
	}
	public Double getFilePaymentAmount() {
		return filePaymentAmount;
	}
	public void setFilePaymentAmount(Double filePaymentAmount) {
		this.filePaymentAmount = filePaymentAmount;
	}
	public Integer getFileCount() {
		return fileCount;
	}
	public void setFileCount(Integer fileCount) {
		this.fileCount = fileCount;
	}
	
	
	

}
