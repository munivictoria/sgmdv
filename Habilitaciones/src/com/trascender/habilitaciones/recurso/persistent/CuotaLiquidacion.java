package com.trascender.habilitaciones.recurso.persistent;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.trascender.framework.recurso.transients.Calendario;
import com.trascender.framework.util.Periodicidad;
import com.trascender.framework.util.Util;

/**
 * @author jsantacruz
 *
 *Una Cuota liquidacion representa una porción de un período.
 *Es decir un periodo puede estar dividido en N cuotas.
 *
 */
@Entity
@Table(name = "CUOTA_LIQUIDACION")
public class CuotaLiquidacion implements Serializable, Comparable<CuotaLiquidacion>, Cloneable{

	public static final long serialVersionUID = 7771102129317399198L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_cuota_liquidacion")
	@SequenceGenerator(name = "gen_id_cuota_liquidacion", sequenceName = "gen_id_cuota_liquidacion",allocationSize = 1)
	@Column(name="ID_CUOTA_LIQUIDACION")
	private long idCuotaLiquidacion = -1;

	@ManyToOne
	@JoinColumn(name="ID_PERIODO")
	private PeriodoLiquidacion periodo;

	@ElementCollection(targetClass=Calendar.class)
	@CollectionTable(name="RELA_CUOTA_FECHA_VENCIMIENTO", 
	joinColumns=@JoinColumn(name="ID_CUOTA_LIQUIDACION"))
	@Column(name="FECHA_VENCIMIENTO")
	private List<Calendar> listaVencimientos = new LinkedList<Calendar>();

	private Integer numero;

	private String nombre;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public long getIdCuotaLiquidacion() {
		return idCuotaLiquidacion;
	}

	public void setIdCuotaLiquidacion(long idCuotaLiquidacion) {
		this.idCuotaLiquidacion = idCuotaLiquidacion;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public void addVencimiento(Calendar pFecha){
		this.listaVencimientos.add(pFecha);
	}

	public List<Calendar> getListaVencimientos() {
		if(this.listaVencimientos != null && !this.listaVencimientos.isEmpty()){
			Collections.sort(this.listaVencimientos, new Comparator<Calendar>() {

				//BEFORE ordena de Mas Grande a Mas chico.
				//AFTER ordena de Mas chico a Mas Grande.
				public int compare(Calendar o1, Calendar o2) {
					return o1.compareTo(o2);
				}
			});
		}
		return listaVencimientos;
	}

	public void setListaVencimientos(List<Calendar> listaVencimientos) {
		this.listaVencimientos = listaVencimientos;
	}

	public PeriodoLiquidacion getPeriodo() {
		return periodo;
	}

	public void setPeriodo(PeriodoLiquidacion periodo) {
		this.periodo = periodo;
	}

	@Transient
	public Periodicidad getPeriodicidad() {
		if(this.periodo != null){
			return this.periodo.getPeriodicidad();
		}

		return null;
	}

	public Periodicidad getPeriodicidadCalendario() {
		if(this.periodo != null && this.getPeriodo().getCalendario() != null){
			return this.periodo.getCalendario().getPeriodicidad();
		}

		return null;
	}

	@Transient
	public boolean isPrimeraCuota(){
		try{
			if(this.getNumero().equals(1) && this.getPeriodo().getNumero().equals(1)){
				return true;
			}
		}catch (NullPointerException e) {
			return false;
		}

		return false;
	}

	@Override
	public String toString() {
		String retorno = Util.returnToString(this, serialVersionUID);
		if(retorno != null) {
			return retorno;
		}
		
		return "Cuota " + numero;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ (int) (idCuotaLiquidacion ^ (idCuotaLiquidacion >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CuotaLiquidacion other = (CuotaLiquidacion) obj;
		if (idCuotaLiquidacion != other.idCuotaLiquidacion)
			return false;
		return true;
	}

	public int compareTo(CuotaLiquidacion o) {
		int intPeriodo = this.getPeriodo().compareTo(o.getPeriodo());
		if (intPeriodo != 0) return intPeriodo;
		return this.getNumero().compareTo(o.getNumero());
	}

	@Override
	public CuotaLiquidacion clone() throws CloneNotSupportedException {
		CuotaLiquidacion locCuota = (CuotaLiquidacion) super.clone();
		//		locCuota.getPeriodo().setIdPeriodo(-1l);
		locCuota.setIdCuotaLiquidacion(-1);
		return locCuota;
	}

	public Integer getAnio() {
		return this.getCalendario().getAnio();
	}

	public Calendario getCalendario() {
		return this.getPeriodo().getCalendario();
	}
	
	public Plan getPlan() {
		return ((CalendarioMunicipal) this.getCalendario()).getPlan();
	}
	
}
