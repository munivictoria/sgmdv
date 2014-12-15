package com.trascender.habilitaciones.recurso.persistent;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.trascender.habilitaciones.util.UtilTipoParametroDinamico;

@Entity
@DiscriminatorValue("ALICUOTA_OSP_DINA")
public class TipoParametroAlicuotaOSPDinamico extends TipoParametroAlicuota{
	private static final long serialVersionUID = -4973642884140537869L;

	@Override
	public Object getValor(AsocRegAlicuota pAsocRegAlicuota) {
		return UtilTipoParametroDinamico.getValor(pAsocRegAlicuota.getRegistroAlicuota().getListaAtributosDinamicos(), this.getNombreVariable());
	}

	@Override
	public void setNombreAtributo(String pNombreAtributo) {
	}

}
