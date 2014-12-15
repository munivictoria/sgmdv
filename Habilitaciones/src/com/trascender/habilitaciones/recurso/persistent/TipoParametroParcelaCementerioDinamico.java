package com.trascender.habilitaciones.recurso.persistent;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamicoBooleano;
import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamicoDecimal;
import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamicoEntero;
import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamicoListado;
import com.trascender.framework.recurso.persistent.dinamicos.PlantillaAtributoDinamico;
import com.trascender.habilitaciones.recurso.persistent.cementerio.ParcelaCementerio;


@Entity
@DiscriminatorValue(value = "PARCELA_CEM_DINA")
public class TipoParametroParcelaCementerioDinamico extends TipoParametroAlicuota {
	
	private static final long serialVersionUID = 5399624631112322149L;

	@Column(name = "ATRIBUTO")
	private String nombre;
	
	public TipoParametroParcelaCementerioDinamico(){
	}
	
	public TipoParametroParcelaCementerioDinamico(PlantillaAtributoDinamico pPlantilla){
		this(pPlantilla.getNombre());
	}
	
	public TipoParametroParcelaCementerioDinamico(String pNombre){
		super();
		this.nombre = pNombre;
		setNombreVariable(pNombre);
	}
	
	@Override
	public Object getValor(AsocRegAlicuota pAsocRegALicuota){
		Object valor = null;
		AtributoDinamico<?> locAtributo = this.getAtributoPorNombre(pAsocRegALicuota);
		if (locAtributo != null){
			valor = getValor(locAtributo);
		}
		return valor;
	}
	
	private Object getValor(AtributoDinamico<?> pAtributo){
		Object locValor = null;
		if (pAtributo instanceof AtributoDinamicoDecimal){
			AtributoDinamicoDecimal locAtributoDecimal = (AtributoDinamicoDecimal) pAtributo;
			locValor = locAtributoDecimal.getValor();
		} else if (pAtributo instanceof AtributoDinamicoEntero){
			AtributoDinamicoEntero locAtributoEntero = (AtributoDinamicoEntero) pAtributo;
			locValor = locAtributoEntero.getValor().doubleValue();
		} else if (pAtributo instanceof AtributoDinamicoBooleano){
			AtributoDinamicoBooleano locAtributoDinamicoBooleano = (AtributoDinamicoBooleano) pAtributo;
			locValor = locAtributoDinamicoBooleano.getValor() ? 1D : 0D;
		} else if (pAtributo instanceof AtributoDinamicoListado){
			AtributoDinamicoListado locAtributoDinamicoListado = (AtributoDinamicoListado) pAtributo;
			locValor = formatearString(locAtributoDinamicoListado.getValor().getValor());
		}
		return locValor;
	}
	
	private AtributoDinamico<?> getAtributoPorNombre(AsocRegAlicuota pAsocRegALicuota){
		ParcelaCementerio locParcela = (ParcelaCementerio) pAsocRegALicuota;
		for (AtributoDinamico<?> cadaAtributo : locParcela.getListaAtributosDinamicos()){
			if (formatearString(cadaAtributo.getNombre()).equals(this.nombre)){
				return cadaAtributo;
			}
		}
		return null;
	}
	
	private String formatearString(final String pString){
		try {
			return pString.replace(" ", "_").toUpperCase();
		} catch (Exception e){
			return "";
		}
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public void setNombreAtributo(String pNombreAtributo) {
		
	}
}
