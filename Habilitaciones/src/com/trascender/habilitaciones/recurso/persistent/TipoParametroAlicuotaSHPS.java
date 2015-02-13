package com.trascender.habilitaciones.recurso.persistent;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.trascender.habilitaciones.recurso.persistent.shps.DocumentoSHPS;

@Entity
@DiscriminatorValue("ALICUOTA_SHPS")
public class TipoParametroAlicuotaSHPS extends TipoParametroAlicuota{

	private static final long serialVersionUID = -8967553521284942697L;

	@Enumerated(EnumType.STRING)
	@Column(name = "ATRIBUTO")
	private AtributoAlicuotaSHPS atributoAlicuotaSHPS;

	public enum AtributoAlicuotaSHPS{
		ALICUOTA_SHPS, PAGO_MINIMO, ES_ALICUOTA_PORCENTUAL,
		IMPORTE_DECLARADO, ES_RUBRO_PRINCIPAL, PRESENTO_DECLARACION, ES_DECLARACION_MAS_ALTA,
		ES_MINIMO_MAS_ALTO;
		@Override
		public String toString() {
			return com.trascender.framework.util.Util.capitalizeEnumName(super.toString());
		}
	}

	@Override
	public Object getValor(AsocRegAlicuota pAsocRegAlicuota) {
		if (pAsocRegAlicuota.getDocHabilitanteEspecializado() instanceof DocumentoSHPS){
			switch (this.atributoAlicuotaSHPS){
			case ALICUOTA_SHPS: return pAsocRegAlicuota.getRegistroAlicuota().getValor().doubleValue();
			case ES_ALICUOTA_PORCENTUAL: return pAsocRegAlicuota.getRegistroAlicuota().isPorcentual()?1d:0d;
			case IMPORTE_DECLARADO: {
				try{
					DocumentoSHPS locDocumento = (DocumentoSHPS) pAsocRegAlicuota.getDocHabilitanteEspecializado();
					LineaDeclaracionJuradaSHPS locLinea = locDocumento.getLineaDDJJSHPS(getCuotaLiquidacion(), pAsocRegAlicuota.getRegistroAlicuota());
					return locLinea != null ? locLinea.getImporte().doubleValue() : 0d;
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
			case PAGO_MINIMO: return pAsocRegAlicuota.getRegistroAlicuota().getMinimo() != null ? 
					pAsocRegAlicuota.getRegistroAlicuota().getMinimo().doubleValue()
					: 0D;
			case ES_DECLARACION_MAS_ALTA:{
				List<RegistroValuado> locListaRegistrosValuados = 
						pAsocRegAlicuota.getDocHabilitanteEspecializado().getListaRegistrosValuados(this.getCuotaLiquidacion());
				//Habria que lanzar excepcion?
				if (locListaRegistrosValuados.isEmpty()) return 0d;
				java.util.Collections.sort(locListaRegistrosValuados, new Comparator<RegistroValuado>() {
					public int compare(RegistroValuado o1,	RegistroValuado o2) {
						if (o1.getMontoImponible().equals(o2.getMontoImponible())) return 0;
						else if (o1.getMontoImponible() > o2.getMontoImponible()) return 1;
						else return -1;
					}
				});
				return locListaRegistrosValuados.get(0).getRegAlicuota() .equals(pAsocRegAlicuota.getRegistroAlicuota()) ? 1d : 0d;
			}
			case ES_MINIMO_MAS_ALTO:{
				List<RegAlicuota> locListaAlicuotas = new ArrayList<RegAlicuota>(pAsocRegAlicuota.getDocHabilitanteEspecializado().getListaRegAlicuotas());
				java.util.Collections.sort(locListaAlicuotas, new Comparator<RegAlicuota>() {
					public int compare(RegAlicuota o1, RegAlicuota o2) {
						return o1.getMinimo().compareTo(o2.getMinimo());
					}
				});
				return locListaAlicuotas.get(0).equals(pAsocRegAlicuota.getRegistroAlicuota()) ? 1d : 0d;
			}
			case PRESENTO_DECLARACION : return pAsocRegAlicuota.getDocHabilitanteEspecializado().getRegistroValuado(this.getCuotaLiquidacion(), pAsocRegAlicuota.getRegistroAlicuota()) == null ? 0d:1d;
			case ES_RUBRO_PRINCIPAL : return ((DocumentoSHPS) pAsocRegAlicuota.getDocHabilitanteEspecializado()).getRubroPrincipal().equals(pAsocRegAlicuota.getRegistroAlicuota()) ? 1d:0d;
			}
			return 0d;
		} else {
			throw new IllegalArgumentException("El documento habilitante especializado debe ser del tipo DocumentoSHPS");
		}
	}

	public AtributoAlicuotaSHPS getAtributoAlicuotaSHPS() {
		return atributoAlicuotaSHPS;
	}

	public void setAtributoAlicuotaSHPS(AtributoAlicuotaSHPS atributoAlicuotaSHPS) {
		this.atributoAlicuotaSHPS = atributoAlicuotaSHPS;
	}

	@Override
	public void setNombreAtributo(String pNombreAtributo) {
		this.atributoAlicuotaSHPS = AtributoAlicuotaSHPS.valueOf(pNombreAtributo);
	}

}
