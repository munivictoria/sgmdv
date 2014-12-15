package com.trascender.habilitaciones.recurso.persistent;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;


/**
 * 
 * @author Mariano Lusardi. Que hiciste, Mariano?
 * @hibernate.subclass discriminator-value = "DDJJ_SHPS"
 */
@Entity
@DiscriminatorValue("DDJJ_SHPS")
public class DeclaracionJuradaSHPS extends RegistroValuado {

	/**
	 * 
	 */
	public static final long serialVersionUID = -2973178579360113122L;
	
	@Column(name="DESCUENTO_POR_RETENCIONES")
	private Double descuentoPorRetenciones;
	
	@OneToMany(mappedBy = "declaracionJuradaSHPS", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<LineaDeclaracionJuradaSHPS> listaLineasDDJJSHPS = new ArrayList<LineaDeclaracionJuradaSHPS>();

	public List<LineaDeclaracionJuradaSHPS> getListaLineasDDJJSHPS() {
		return listaLineasDDJJSHPS;
	}

	public void setListaLineasDDJJSHPS(List<LineaDeclaracionJuradaSHPS> listaLineasDDJJSHPS) {
		this.listaLineasDDJJSHPS = listaLineasDDJJSHPS;
	}

	public Double getDescuentoPorRetenciones() {
		return descuentoPorRetenciones;
	}

	public void setDescuentoPorRetenciones(Double descuentoPorRetenciones) {
		this.descuentoPorRetenciones = descuentoPorRetenciones;
	}
	
	public String toString(){
		StringBuilder locStringBuilder = new StringBuilder();
		DocHabilitanteEspecializado locDocHabilitanteEspecializado = this.getDocHabilitanteEspecializado();
		locStringBuilder.append((locDocHabilitanteEspecializado!=null)?locDocHabilitanteEspecializado.toString():"[SHPS]" );
		
		if (this.getFecha()!=null){
			DateFormat locDateFormat = DateFormat.getDateInstance(DateFormat.SHORT);
			locStringBuilder.append(" Fecha: "+locDateFormat.format(this.getFecha()));
		}
		
		if (this.getCuotaLiquidacion() != null){
			locStringBuilder.append(" Período: " + this.getCuotaLiquidacion().toString());
		}
		locStringBuilder.append(" Monto : " + this.getMontoImponible());
		
		return locStringBuilder.toString();    
		
	}
	
	public String getPeriodoString() {
		return this.getCuotaLiquidacion().getPeriodo().getCalendario().getAnio().toString() + ", " + this.getCuotaLiquidacion().getPeriodo().getNombre();
	}	
	
	public void calcularMontoImponible() {
		Double locMonto = 0d;
		for(LineaDeclaracionJuradaSHPS cadaLinea : this.getListaLineasDDJJSHPS()) {
			locMonto += cadaLinea.getImporte();
		}
		this.setMontoImponible(locMonto);
	}
}