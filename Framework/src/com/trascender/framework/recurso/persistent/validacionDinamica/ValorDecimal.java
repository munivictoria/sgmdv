package com.trascender.framework.recurso.persistent.validacionDinamica;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.trascender.framework.recurso.persistent.validacionDinamica.ComponenteValidacion.Operadores;

@Entity
@DiscriminatorValue(value="DECIMAL")
public class ValorDecimal extends ValorValidacion{

	public static final long serialVersionUID = 6778152178126352637L;

	@Column(name="VALOR_DECIMAL")
	private Double valor;
	
	@Override
	public Double getValor() {
		return this.valor;
	}

	@Override
	public boolean comparar(Object pObjeto) {
		return this.comparar(pObjeto, null);
		
	}
	
	public boolean comparar(Object pObjeto, Operadores pTipoOperacion) {
		if(pTipoOperacion == null){
			return this.valor.equals(pObjeto);
		}
		
		switch (pTipoOperacion) {
			case IGUAL: if(this.valor.equals(pObjeto)) return true; break;
			case MAYOR: if(this.valor > (Double) pObjeto) return true; break;
			case MAYOR_IGUAL: if(this.valor >= (Double) pObjeto) return true; break;
			case MENOR: if(this.valor < (Double) pObjeto) return true; break;
			case MENOR_IGUAL: if(this.valor <= (Double) pObjeto) return true; break;
			case DISTINTO: if(this.valor != (Double) pObjeto) return true; break;
		}
		return false;
	}

	public void setValor(Object pValor) {
		valor = (Double) pValor;
	}
	
	

}
