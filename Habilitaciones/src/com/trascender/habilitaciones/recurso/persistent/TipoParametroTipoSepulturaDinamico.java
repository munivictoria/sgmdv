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
import com.trascender.habilitaciones.recurso.persistent.cementerio.TipoSepultura;

@Entity
@DiscriminatorValue(value = "TIPO_SEPULTURA_DINA")
public class TipoParametroTipoSepulturaDinamico extends TipoParametroAlicuota{

	private static final long serialVersionUID = -1075121491375375162L;

	@Column(name = "ATRIBUTO")
	private String nombre;
	
	public TipoParametroTipoSepulturaDinamico(){
	}
	
	public TipoParametroTipoSepulturaDinamico(PlantillaAtributoDinamico pPlantilla){
		this(pPlantilla.getNombre());
	}
	
	public TipoParametroTipoSepulturaDinamico(String pNombre){
		super();
		this.nombre = pNombre;
		setNombreVariable(pNombre);
	}
	
	@Override
	public Object getValor(AsocRegAlicuota pAsocRegALicuota){
		Object valor = new Double(0);
		AtributoDinamico<?> locAtributo = this.getAtributoPorNombre(pAsocRegALicuota.getRegistroAlicuota());
		if (locAtributo != null){
			valor = getValor(locAtributo);
		}
		return valor;
	}
	
	private Object getValor(AtributoDinamico<?> pAtributo){
		Object locValor = new Double(0);
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
	
	private AtributoDinamico<?> getAtributoPorNombre(RegAlicuota pRegALicuota){
		TipoSepultura locTipoSepultura = (TipoSepultura) pRegALicuota;
		for (AtributoDinamico<?> cadaAtributo : locTipoSepultura.getListaAtributosDinamicos()){
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
