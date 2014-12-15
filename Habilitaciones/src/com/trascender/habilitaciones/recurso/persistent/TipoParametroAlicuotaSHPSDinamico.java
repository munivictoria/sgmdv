package com.trascender.habilitaciones.recurso.persistent;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.trascender.habilitaciones.util.UtilTipoParametroDinamico;

@Entity
@DiscriminatorValue("ALICUOTA_SHPS_DINA")
public class TipoParametroAlicuotaSHPSDinamico extends TipoParametroAlicuota{
	private static final long serialVersionUID = 182163911578941080L;
	
	@Override
	public Object getValor(AsocRegAlicuota pAsocRegAlicuota) {
		return UtilTipoParametroDinamico.getValor(pAsocRegAlicuota.getRegistroAlicuota().getListaAtributosDinamicos(), this.getNombreVariable());
	}
	
	@Override
	public void setNombreAtributo(String pNombreAtributo) {
	}
}
