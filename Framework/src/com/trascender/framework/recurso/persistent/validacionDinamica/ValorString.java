package com.trascender.framework.recurso.persistent.validacionDinamica;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.trascender.framework.recurso.persistent.validacionDinamica.ComponenteValidacion.Operadores;

@Entity
@DiscriminatorValue(value="STRING")
public class ValorString extends ValorValidacion{

	public static final long serialVersionUID = -8452785780248754237L;
	
	@Column(name="VALOR_STRING")
	private String valor;
	
	@Override
	public String getValor() {
		return this.valor;
	}
	
	public void setValor(Object pValor) {
		valor = (String) pValor;
	}



	/**
	 * Compara el valor con un elemento pasado por parametro.
	 */
	@Override
	public boolean comparar(Object pObjeto) {
		return this.comprar(pObjeto, null, null);
	}
	
	public boolean comprar(Object pObjeto, Boolean alPrincipio, Boolean ignorarMayus){
		ignorarMayus = (ignorarMayus != null) && ignorarMayus;
		alPrincipio = (alPrincipio != null) && alPrincipio;
		
		if(alPrincipio){
			if (ignorarMayus) {
				if ((this.valor.toUpperCase()).startsWith(((String) pObjeto).toUpperCase())) {
					return true;
				}
			}

			if ((this.valor).startsWith((String) pObjeto)) {
				return true;
			}
		}
		
		if(ignorarMayus){
			if(this.valor.toUpperCase().equals(((String)pObjeto).toUpperCase())){
				return true;
			}
		}
		
		if(this.valor.equals(pObjeto)){
			return true;
		}
		
		return false;
		
	}

	@Override
	public boolean comparar(Object pObjeto, Operadores pTipoOperacion) {
		switch (pTipoOperacion) {
			case IGUAL: return this.comparar(pObjeto);
			case DISTINTO: return !this.comparar(pObjeto);
		default: return false;
		}
	}

}
