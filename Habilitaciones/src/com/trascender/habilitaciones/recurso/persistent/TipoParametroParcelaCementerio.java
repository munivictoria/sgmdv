package com.trascender.habilitaciones.recurso.persistent;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.trascender.habilitaciones.recurso.persistent.cementerio.DocumentoCementerio;
import com.trascender.habilitaciones.recurso.persistent.cementerio.ParcelaCementerio;


@Entity
@DiscriminatorValue("PARCELA_CEMENTERIO")
public class TipoParametroParcelaCementerio extends TipoParametroAlicuota{
	public static final long serialVersionUID = 5796546489601251956L;

	@Enumerated(EnumType.STRING)
	@Column(name = "ATRIBUTO")
	private AtributoParcelaCementerio atributoParcelaCementerio;
	
	public enum AtributoParcelaCementerio{
		SUPERFICIE;
		@Override
		public String toString() {
			return com.trascender.framework.util.Util.capitalizeEnumName(super.toString());
		}
	}

	@Override
	public Object getValor(AsocRegAlicuota pAsocRegAlicuota) {
		if (pAsocRegAlicuota instanceof ParcelaCementerio){
			switch (this.atributoParcelaCementerio){
				case SUPERFICIE: return ((ParcelaCementerio)pAsocRegAlicuota).getSuperficie();
				
			}
			return 0d;
		} else {
			throw new IllegalArgumentException("" /* Aca mostrar un msj */);
		}
	}

	public AtributoParcelaCementerio getAtributoParcelaCementerio() {
		return atributoParcelaCementerio;
	}

	public void setAtributoParcelaCementerio(
			AtributoParcelaCementerio atributoParcelaCementerio) {
		this.atributoParcelaCementerio = atributoParcelaCementerio;
	}
	
	@Override
	public void setNombreAtributo(String pNombreAtributo) {
		this.atributoParcelaCementerio = AtributoParcelaCementerio.valueOf(pNombreAtributo);
	}
}
