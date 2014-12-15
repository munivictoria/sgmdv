package com.trascender.habilitaciones.recurso.persistent;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import com.trascender.framework.recurso.transients.Periodo;
import com.trascender.framework.util.Periodicidad;

@Entity
@DiscriminatorValue(value="PER_LIQ")
public class PeriodoLiquidacion extends Periodo{

	public static final long serialVersionUID = 1282044574902098721L;

	@Column(name = "PORCENTAJE")
	private double porcentajePeriodo;

	@Enumerated(EnumType.STRING)
	private Periodicidad periodicidad;

	@OrderBy(value="numero")
	@OneToMany(mappedBy="periodo", cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.EAGER)
	private Set<CuotaLiquidacion> listaCuotas = new HashSet<CuotaLiquidacion>();

	/**
	 * @param pCuota
	 * @return true si lo agrego.
	 */
	public boolean addCuotaLiquidacion(CuotaLiquidacion pCuota){
		pCuota.setPeriodo(this);
		return this.listaCuotas.add(pCuota);
	}


	public CuotaLiquidacion getCuotaLiquidacion(Integer pNumeroCuota) throws Exception{
		if(pNumeroCuota == null) return null;
		for(CuotaLiquidacion cadaCuota : this.listaCuotas){
			if(cadaCuota.getNumero().equals(pNumeroCuota)){
				return cadaCuota;
			}
		}

		return null;
	}

	public CuotaLiquidacion getCuotaLiquidacion(Calendar pFecha, Integer pNumeroCuota){
		for(CuotaLiquidacion cadaCuota : this.listaCuotas){
			boolean soloFecha = pNumeroCuota == null && pFecha != null;
			boolean soloNumero = pFecha == null && pNumeroCuota != null;
			//			if((soloFecha && soloNumero)){
			//				return null;
			//			}

			if(soloNumero && cadaCuota.getNumero().equals(pNumeroCuota)){
				return cadaCuota;
			}
		}

		return null;
	}

	public Periodicidad getPeriodicidad() {
		return periodicidad;
	}

	public void setPeriodicidad(Periodicidad periodicidad) {
		this.periodicidad = periodicidad;
	}

	public double getPorcentajePeriodo() {
		return porcentajePeriodo;
	}

	public void setPorcentajePeriodo(double porcentajePeriodo) {
		this.porcentajePeriodo = porcentajePeriodo;
	}

	public Set<CuotaLiquidacion> getListaCuotas() {
		return listaCuotas;
	}

	public void setListaCuotas(Set<CuotaLiquidacion> listaCuotas) {
		this.listaCuotas = listaCuotas;
	}

	public CuotaLiquidacion getPrimeraCuota() throws Exception{
		return this.getCuotaLiquidacion(1);
	}

	/**
	 * Devuelve la ultima cuota. <br>
	 * <li> Nota: devuelve la cuota con mayor numero agregada. No hay forma de saber cuantas deberian de haber<br>
	 * @return null si no existe
	 * @throws Exception
	 */
	public CuotaLiquidacion getUltimaCuota() throws Exception, NullPointerException{
		int ultimaCuota = 1;
		for(CuotaLiquidacion cadaCuotaLiquidacion : this.getListaCuotas()){
			if(cadaCuotaLiquidacion.getNumero().intValue() > ultimaCuota){
				ultimaCuota = cadaCuotaLiquidacion.getNumero().intValue();
			}
		}

		return this.getCuotaLiquidacion(ultimaCuota);
	}

	@Override
	public PeriodoLiquidacion clone() throws CloneNotSupportedException{
		PeriodoLiquidacion locPeriodo = (PeriodoLiquidacion) super.clone();
		Set<CuotaLiquidacion> locListaCuota = new HashSet<CuotaLiquidacion>();
		for (CuotaLiquidacion cadaCuota : locPeriodo.getListaCuotas()) {
			CuotaLiquidacion locCuota = cadaCuota.clone();
			locCuota.setPeriodo(locPeriodo);
			locListaCuota.add(locCuota);
		}
		locPeriodo.setListaCuotas(locListaCuota);
		return locPeriodo;
	}

}
