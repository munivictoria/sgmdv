/**
 * 
 */
package com.trascender.catastro.recurso.persistent;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author danilo
 *
 */
@Entity
@Table(name= "ESTADISTICAS_INDEC")
public class EstadisticasIndec implements Serializable{

	public static final long serialVersionUID = 3592985120191209477L;
	
	@Id
	@SequenceGenerator(allocationSize = 1, name="GEN_ID_ESTADISTICAS_INDEC", sequenceName="GEN_ID_ESTADISTICAS_INDEC")
	@GeneratedValue(generator="GEN_ID_ESTADISTICAS_INDEC", strategy=GenerationType.SEQUENCE)
	@Column(name="ID_ESTADISTICAS_INDEC")
	private long idEstadisticasIndec = -1;
	
	@Column(name="CANTIDAD_VIVIENDAS")
	private Integer cantidadViviendas;
	
	@Column(name="CANTIDAD_HABITACIONES")
	private Integer cantidadHabitaciones;
	
	@Column(name="CANTIDAD_BANIOS")
	private Integer cantidadBanios;
	
	@Column(name="CANTIDAD_COCINAS")
	private Integer cantidadCocinas;
	
	@Column(name="CANTIDAD_GARAGES")
	private Integer cantidadGarages;
	
	@Column(name="LOCALES_COMERCIALES")
	private Integer localesComerciales;
	
	@Column(name="OTROS")
	private Integer otros;

	public long getIdEstadisticasIndec() {
		return idEstadisticasIndec;
	}

	public void setIdEstadisticasIndec(long idEstadisticasIndec) {
		this.idEstadisticasIndec = idEstadisticasIndec;
	}

	public Integer getCantidadViviendas() {
		return cantidadViviendas;
	}

	public void setCantidadViviendas(Integer cantidadViviendas) {
		this.cantidadViviendas = cantidadViviendas;
	}

	public Integer getCantidadHabitaciones() {
		return cantidadHabitaciones;
	}

	public void setCantidadHabitaciones(Integer cantidadHabitaciones) {
		this.cantidadHabitaciones = cantidadHabitaciones;
	}

	public Integer getCantidadBanios() {
		return cantidadBanios;
	}

	public void setCantidadBanios(Integer cantidadBanios) {
		this.cantidadBanios = cantidadBanios;
	}

	public Integer getCantidadCocinas() {
		return cantidadCocinas;
	}

	public void setCantidadCocinas(Integer cantidadCocinas) {
		this.cantidadCocinas = cantidadCocinas;
	}

	public Integer getCantidadGarages() {
		return cantidadGarages;
	}

	public void setCantidadGarages(Integer cantidadGarages) {
		this.cantidadGarages = cantidadGarages;
	}

	public Integer getLocalesComerciales() {
		return localesComerciales;
	}

	public void setLocalesComerciales(Integer localesComerciales) {
		this.localesComerciales = localesComerciales;
	}

	public Integer getOtros() {
		return otros;
	}

	public void setOtros(Integer otros) {
		this.otros = otros;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		if(cantidadViviendas != null) {
			sb.append("Viviendas: ").append(cantidadViviendas).append(" - ");
		}
		if(cantidadHabitaciones != null) {
			sb.append("Habitaciones: ").append(cantidadHabitaciones).append(" - ");
		}
		if(cantidadBanios != null) {
			sb.append("Baños: ").append(cantidadBanios).append(" - ");
		}
		if(cantidadCocinas != null) {
			sb.append("Cocinas: ").append(cantidadCocinas).append(" - ");
		}
		if(cantidadGarages != null) {
			sb.append("Garages: ").append(cantidadGarages).append(" - ");
		}
		if(localesComerciales != null) {
			sb.append("Comerciales: ").append(localesComerciales).append(" - ");
		}
		if(otros != null) {
			sb.append("Otros: ").append(otros).append(" - ");
		}
		if (cantidadBanios == null && cantidadCocinas == null && cantidadGarages == null && otros == null &&
			cantidadHabitaciones == null && cantidadViviendas == null && localesComerciales == null) {
			sb.append("No contiene estadisticas indec asociadas");
		}
		return  sb.toString();
	}
}
