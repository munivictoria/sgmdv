package com.trascender.saic.recurso.transients.pagoFacil;

public class ChequeCMC7PF {
	
	private Integer idBanco;
	private Integer idSucursalBanco;
	private Integer idPlazaEmisora;
	private Integer numeroCheque;
	private Integer numeroCuentaBanco;
	
	public Integer getIdBanco() {
		return idBanco;
	}
	public void setIdBanco(Integer idBanco) {
		this.idBanco = idBanco;
	}
	public Integer getIdSucursalBanco() {
		return idSucursalBanco;
	}
	public void setIdSucursalBanco(Integer idSucursalBanco) {
		this.idSucursalBanco = idSucursalBanco;
	}
	public Integer getIdPlazaEmisora() {
		return idPlazaEmisora;
	}
	public void setIdPlazaEmisora(Integer idPlazaEmisora) {
		this.idPlazaEmisora = idPlazaEmisora;
	}
	public Integer getNumeroCheque() {
		return numeroCheque;
	}
	public void setNumeroCheque(Integer numeroCheque) {
		this.numeroCheque = numeroCheque;
	}
	public Integer getNumeroCuentaBanco() {
		return numeroCuentaBanco;
	}
	public void setNumeroCuentaBanco(Integer numeroCuentaBanco) {
		this.numeroCuentaBanco = numeroCuentaBanco;
	}
	@Override
	public String toString() {
		return "ChequeCMC7PF [idBanco=" + idBanco + ", idSucursalBanco="
				+ idSucursalBanco + ", idPlazaEmisora=" + idPlazaEmisora
				+ ", numeroCheque=" + numeroCheque + ", numeroCuentaBanco="
				+ numeroCuentaBanco + "]";
	}
	
	

}
