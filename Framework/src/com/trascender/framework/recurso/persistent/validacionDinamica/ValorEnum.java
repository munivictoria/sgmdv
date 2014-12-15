package com.trascender.framework.recurso.persistent.validacionDinamica;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.trascender.framework.recurso.persistent.validacionDinamica.ComponenteValidacion.Operadores;

@Entity
@DiscriminatorValue(value="ENUMERATE")
public class ValorEnum extends ValorValidacion{

	private static final long serialVersionUID = -9217969361484007389L;

	@Column(name="VALOR_ENUMERATE")
	private String valor;
	
	@Override
	public String getValor() {
		return (String) valor;
	}

	@Override
	public void setValor(Object pValor) {
		if(pValor != null){
			String valor = (String) pValor;
			valor = valor.toUpperCase();
		}
		this.valor = (String) pValor;
	}

	@Override
	public boolean comparar(Object pObjeto) {
		return this.comparar(pObjeto, Operadores.IGUAL);
	}

	@Override
	public boolean comparar(Object pObjeto, Operadores pTipoOperacion) {
		System.out.println(this.valor + " - " + pObjeto.toString().toUpperCase());
		switch (pTipoOperacion) {
			case IGUAL: return this.valor.toUpperCase().equals(pObjeto.toString().toUpperCase());
			case DISTINTO: return !this.comparar(pObjeto);
		default: return false;
		}
	}

}
