/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

package com.trascender.expedientes.recurso.persistent;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "EXP_PARAMETRO_VALUADO_REPORTE")
public class ParametroValuadoReporte implements Serializable {

	private static final long serialVersionUID = -1926461288246051324L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_id_parametro_valuado_rp")
	@SequenceGenerator(name = "gen_id_parametro_valuado_rp", sequenceName = "gen_id_parametro_valuado_rp", allocationSize = 1)
	@Column(name = "ID_PARAMETRO_VALUADO_RP")
	private long idParametroValuadoReporte = -1;

	private String nombre;

	@ManyToOne
	@JoinColumn(name = "ID_VERSION_EJECUCION_RP")
	private VersionEjecucionReporte versionEjecucionReporte;

	@Column(name = "VALOR_CADENA")
	private String valorCadena;

	@Column(name = "VALOR_SELECCION")
	private String[] valorSeleccion;

	@Column(name = "VALOR_ENTERO")
	private Long valorEntero;

	@Column(name = "VALOR_DECIMAL")
	private Double valorDecimal;

	@Column(name = "VALOR_FECHA")
	private Date valorFecha;

	@Column(name = "VALOR_BOOLEANO")
	private Boolean valorBooleano;

	public long getIdParametroValuadoReporte() {
		return idParametroValuadoReporte;
	}

	public void setIdParametroValuadoReporte(long idParametroValuadoReporte) {
		this.idParametroValuadoReporte = idParametroValuadoReporte;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public VersionEjecucionReporte getVersionEjecucionReporte() {
		return versionEjecucionReporte;
	}

	public void setVersionEjecucionReporte(VersionEjecucionReporte versionEjecucionReporte) {
		this.versionEjecucionReporte = versionEjecucionReporte;
	}

	public String getValorCadena() {
		return valorCadena;
	}

	public void setValorCadena(String valorCadena) {
		this.valorCadena = valorCadena;
	}

	public String[] getValorSeleccion() {
		return valorSeleccion;
	}
	
	public String getValorSeleccionString() {
		String cadena = "";
		for(int i = 0; i < valorSeleccion.length; i++) {
			cadena += valorSeleccion[i] + " ";
		}
		
		return cadena.trim();
	}

	public void setValorSeleccion(String[] valorSeleccion) {
		this.valorSeleccion = valorSeleccion;
	}

	public Long getValorEntero() {
		return valorEntero;
	}

	public void setValorEntero(Long valorEntero) {
		this.valorEntero = valorEntero;
	}

	public Double getValorDecimal() {
		return valorDecimal;
	}

	public void setValorDecimal(Double valorDecimal) {
		this.valorDecimal = valorDecimal;
	}

	public Date getValorFecha() {
		return valorFecha;
	}

	public void setValorFecha(Date valorFecha) {
		this.valorFecha = valorFecha;
	}

	public Object getValor() {
		if(valorCadena != null)
			return valorCadena;
		if(valorSeleccion != null)
			return valorSeleccion;
		if(valorEntero != null)
			return valorEntero;
		if(valorDecimal != null)
			return valorDecimal;
		if(valorFecha != null)
			return valorFecha;
		else
			return valorBooleano;
	}

	public Boolean getValorBooleano() {
		return valorBooleano;
	}

	public void setValorBooleano(Boolean valorBooleano) {
		this.valorBooleano = valorBooleano;
	}

}