package com.trascender.framework.recurso.persistent.dinamicos;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "ENTERO")
public class AtributoDinamicoEntero extends AtributoDinamico<Long>{
	
	private static final long serialVersionUID = -1414785685770109142L;
	
	@Column(name = "VALOR_ENTERO")
	private Long valorEntero;

	@Override
	public void setValor(Long pValor) {
		valorEntero = pValor;
	}

	@Override
	public Long getValor() {
		return valorEntero;
	}

	@Override
	public void setValorString(String pValor) throws Exception{
		try{
			if (pValor != null && !pValor.trim().isEmpty()){
				this.valorEntero = Long.valueOf(pValor);
			} else {
				this.valorEntero = null;
			}
		} catch (NumberFormatException e){
			throw new Exception("Formato numerico no valido");
		}
	}

	@Override
	public String getValorString() {
		return this.valorEntero == null ? null : this.valorEntero.toString();
	}

	@Override
	public String getNombreAtributoValor() {
		return "valorEntero";
	}
	
}
