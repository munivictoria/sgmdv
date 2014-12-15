package com.trascender.habilitaciones.util;

import java.text.SimpleDateFormat;
import java.util.List;

import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamicoBooleano;
import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamicoDecimal;
import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamicoEntero;
import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamicoFecha;
import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamicoListado;

public class UtilTipoParametroDinamico {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	private static String formatearString(final String pString){
		return pString.replace(" ", "_").toUpperCase();
	}
	
	private static AtributoDinamico<?> getAtributoPorNombre(List<AtributoDinamico<?>> pLista, String pNombreVariable){
		for (AtributoDinamico<?> cadaAtributo : pLista){
			if (formatearString(cadaAtributo.getNombre()).equals(pNombreVariable)){
				return cadaAtributo;
			}
		}
		return null;
	}
	
	public static Object getValor(List<AtributoDinamico<?>> pLista, String pNombreVariable){
		Object locValor = new Double(0);
		AtributoDinamico<?> locAtributo = getAtributoPorNombre(pLista, pNombreVariable);
		if (locAtributo != null) {
			if (locAtributo instanceof AtributoDinamicoDecimal){
				AtributoDinamicoDecimal locAtributoDecimal = (AtributoDinamicoDecimal) locAtributo;
				locValor = locAtributoDecimal.getValor();
			} else if (locAtributo instanceof AtributoDinamicoEntero){
				AtributoDinamicoEntero locAtributoEntero = (AtributoDinamicoEntero) locAtributo;
				locValor = locAtributoEntero.getValor().doubleValue();
			} else if (locAtributo instanceof AtributoDinamicoBooleano){
				AtributoDinamicoBooleano locAtributoDinamicoBooleano = (AtributoDinamicoBooleano) locAtributo;
				locValor = locAtributoDinamicoBooleano.getValor() ? 1D : 0D;
			} else if (locAtributo instanceof AtributoDinamicoListado){
				AtributoDinamicoListado locAtributoDinamicoListado = (AtributoDinamicoListado) locAtributo;
				locValor = formatearString(locAtributoDinamicoListado.getValor().getValor());
			} else if (locAtributo instanceof AtributoDinamicoFecha) {
				AtributoDinamicoFecha locAtributoFecha = (AtributoDinamicoFecha) locAtributo;
				locValor = sdf.format(locAtributoFecha.getValor());
			}
		}
		return locValor;
	}

}
