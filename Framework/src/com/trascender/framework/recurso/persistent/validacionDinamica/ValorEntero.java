package com.trascender.framework.recurso.persistent.validacionDinamica;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.trascender.framework.recurso.persistent.validacionDinamica.ComponenteValidacion.Operadores;

@Entity
@DiscriminatorValue(value="ENTERO")
public class ValorEntero extends ValorValidacion{

	private static final long serialVersionUID = 6472636928893832798L;

	@Column(name="VALOR_ENTERO")
	private Long valor;
	
	@Override
	public Long getValor() {
		return this.valor;
	}
	
	@Override
	public void setValor(Object pValor) {
		this.valor=(Long) pValor;
		
	}
	public void setValor(Long pValor) {
		valor = pValor;
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
			case MAYOR: if(this.valor > (Long) pObjeto) return true; break;
			case MAYOR_IGUAL: if(this.valor >= (Long) pObjeto) return true; break;
			case MENOR: if(this.valor < (Long) pObjeto) return true; break;
			case MENOR_IGUAL: if(this.valor <= (Long) pObjeto) return true; break;
			case DISTINTO: if(this.valor != (Long) pObjeto) return true; break;
		}
		return false;
		
	}


}
