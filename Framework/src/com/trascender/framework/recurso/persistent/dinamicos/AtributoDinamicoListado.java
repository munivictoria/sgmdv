package com.trascender.framework.recurso.persistent.dinamicos;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue(value = "LISTADO")
public class AtributoDinamicoListado extends AtributoDinamico<OpcionAtributoDinamicoListado>{
	
	private static final long serialVersionUID = -8041772777337552163L;
	
	@ManyToOne
	@JoinColumn(name = "ID_OPCION")
	private OpcionAtributoDinamicoListado valorOpcion;

	@Override
	public OpcionAtributoDinamicoListado getValor() {
		return valorOpcion;
	}

	@Override
	public void setValor(OpcionAtributoDinamicoListado pValor) {
		this.valorOpcion = pValor;
	}

	@Override
	public void setValorString(String pValor) throws Exception {
		this.valorOpcion = plantilla.getOpcionPorValor(pValor);
	}

	@Override
	public String getValorString() {
		if (valorOpcion != null){
			return valorOpcion.getValor();
		}
		return null;
	}

	@Override
	public String getNombreAtributoValor() {
		return "valorOpcion";
	}

}
