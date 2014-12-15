package com.trascender.saic.recurso.filtros;

import com.trascender.habilitaciones.recurso.persistent.cementerio.ParcelaCementerio;
import com.trascender.habilitaciones.recurso.persistent.cementerio.TipoSepultura;

/**
 * 
 * @author nico
 *
 */
public class FiltroLiquidacionCementerio extends FiltroLiquidacionTasa {

	private static final long serialVersionUID = -8658770257471290191L;

	private ParcelaCementerio parcelaCementerio;
	private TipoSepultura tipoSepultura;
	
	public ParcelaCementerio getParcelaCementerio() {
		return parcelaCementerio;
	}
	
	public void setParcelaCementerio(ParcelaCementerio parcelaCementerio) {
		this.parcelaCementerio = parcelaCementerio;
	}
	
	public TipoSepultura getTipoSepultura() {
		return tipoSepultura;
	}
	
	public void setTipoSepultura(TipoSepultura tipoSepultura) {
		this.tipoSepultura = tipoSepultura;
	}
}
