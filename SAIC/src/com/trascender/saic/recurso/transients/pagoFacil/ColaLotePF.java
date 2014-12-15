package com.trascender.saic.recurso.transients.pagoFacil;

import java.util.Date;

public class ColaLotePF {

	public static final Integer recordCode = 8;
	
	private Date createDate;
	private Integer batchNumber;
	private Integer batchPaymentCount;
	private Double batchPaymentMount = 0.0d;
	private Integer batchCount;
	
	public Date getCreateDate() {
		return createDate;
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
	public Integer getBatchPaymentCount() {
		return batchPaymentCount;
	}
	public void setBatchPaymentCount(Integer batchPaymentCount) {
		this.batchPaymentCount = batchPaymentCount;
	}
	public Double getBatchPaymentMount() {
		return batchPaymentMount;
	}
	public void setBatchPaymentMount(Double batchPaymentMount) {
		this.batchPaymentMount = batchPaymentMount;
	}
	public Integer getBatchCount() {
		return batchCount;
	}
	public void setBatchCount(Integer batchCount) {
		this.batchCount = batchCount;
	}
	
}
