package com.trascender.saic.recurso.transients.pagoFacil;

import java.util.Date;

public class DetalleRegistroPF {
	
	public static final Integer recordCode = 5;
	
	private Integer recordSequence;
	private Integer transactionCode;
	private Date workDate;
	private Date transferDate;
	private String accountNumber;
	private String currencyCode;
	private Double amount;
	private String terminalId;
	private Date paymentDate;
	private Date paymentTime;
	private Integer secuenciaTransaccion;
	
	public Integer getRecordSequence() {
		return recordSequence;
	}
	public void setRecordSequence(Integer recordSequence) {
		this.recordSequence = recordSequence;
	}
	public Integer getTransactionCode() {
		return transactionCode;
	}
	public void setTransactionCode(Integer transactionCode) {
		this.transactionCode = transactionCode;
	}
	public Date getWorkDate() {
		return workDate;
	}
	public void setWorkDate(Date workDate) {
		this.workDate = workDate;
	}
	public Date getTransferDate() {
		return transferDate;
	}
	public void setTransferDate(Date transferDate) {
		this.transferDate = transferDate;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getTerminalId() {
		return terminalId;
	}
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	
	public Integer getSecuenciaTransaccion() {
		return secuenciaTransaccion;
	}
	public void setSecuenciaTransaccion(Integer secuenciaTransaccion) {
		this.secuenciaTransaccion = secuenciaTransaccion;
	}
	@Override
	public String toString() {
		return "DetalleRegistroPF [recordSequence=" + recordSequence
				+ ", transactionCode=" + transactionCode + ", workDate="
				+ workDate + ", transferDate=" + transferDate
				+ ", accountNumber=" + accountNumber + ", currencyCode="
				+ currencyCode + ", amount=" + amount + ", terminalId="
				+ terminalId + ", paymentDate=" + paymentDate
				+ ", paymentTime=" + paymentTime + ", secuenciaTransaccion="
				+ secuenciaTransaccion + "]";
	}
	
	
}
