package com.trascender.saic.recurso.transients.pagoFacil;


public class DetalleInstrumentoPF {
	
	public static final Integer recordCode = 7;
	
	private String currencyCode = "";
	private Character payInstrument;
	private String codeBarPayment = "";
	private Double amount = 0.0d;
	
	private ChequeCMC7PF chequeCMC7;

	/**
	 * Sino no es del tipo chere retorna null.
	 * @return
	 */
	public ChequeCMC7PF getChequeCMC7() {
		if((payInstrument.equals('c') || payInstrument.equals('C')) &&(codeBarPayment != null)){
			ChequeCMC7PF locChequeCMC7 = new ChequeCMC7PF();
			locChequeCMC7.setIdBanco(Integer.valueOf(this.codeBarPayment.substring(0, 3)));
			locChequeCMC7.setIdSucursalBanco(Integer.valueOf(this.codeBarPayment.substring(3, 6)));
			locChequeCMC7.setIdPlazaEmisora(Integer.valueOf(this.codeBarPayment.substring(6, 10)));
			locChequeCMC7.setNumeroCheque(Integer.valueOf(this.codeBarPayment.substring(10, 18)));
			locChequeCMC7.setNumeroCuentaBanco(Integer.valueOf(this.codeBarPayment.substring(18, 29)));
			
			return locChequeCMC7;
		}
		return null;
		
	}
	public void setChequeCMC7(ChequeCMC7PF chequeCMC7) {
		this.chequeCMC7 = chequeCMC7;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public Character getPayInstrument() {
		return payInstrument;
	}
	public void setPayInstrument(Character payInstrument) {
		this.payInstrument = payInstrument;
	}
	public String getCodeBarPayment() {
		return codeBarPayment;
	}
	public void setCodeBarPayment(String codeBarPayment) {
		this.codeBarPayment = codeBarPayment;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	
	
	
}
