package com.trascender.saic.recurso.transients;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.trascender.saic.recurso.persistent.LiquidacionTasa;
import com.trascender.saic.recurso.persistent.RegistroDeuda;

/**
 * Utilizada para generar estadisticas sobre la cobrabilidad de las Obras
 * @author ferna
 *
 */
public class LineaEstadisticaObra implements Serializable{
	private static final long serialVersionUID = -8594406340890068372L;

	private int numeroPeriodo;
	private List<LiquidacionTasa> listaLiquidacionesTasa = new ArrayList<LiquidacionTasa>();
	private int cantidadACobrar = 0;
	private double totalACobrar = 0d;
	private int cantidadCobrados = 0;
	private double totalCobrado = 0d;
	private int cantidadNoCobrados = 0;
	private double totalNoCobrados = 0d;
	
	public void addLiquidacionTasa(LiquidacionTasa pLiquidacionTasa){
		this.listaLiquidacionesTasa.add(pLiquidacionTasa);
		this.numeroPeriodo = pLiquidacionTasa.getCuotaLiquidacion().getNumero();
		this.cantidadACobrar++;
		this.totalACobrar += pLiquidacionTasa.getValor();
		if (pLiquidacionTasa.getEstado().equals(RegistroDeuda.EstadoRegistroDeuda.PAGADA)){
			cantidadCobrados++;
			totalCobrado += pLiquidacionTasa.getValor();
		} else {
			cantidadNoCobrados++;
			totalNoCobrados += pLiquidacionTasa.getValor();
		}
	}

	public int getNumeroPeriodo() {
		return numeroPeriodo;
	}

	public void setNumeroPeriodo(int numeroPeriodo) {
		this.numeroPeriodo = numeroPeriodo;
	}

	public int getCantidadACobrar() {
		return cantidadACobrar;
	}

	public void setCantidadACobrar(int cantidadACobrar) {
		this.cantidadACobrar = cantidadACobrar;
	}

	public double getTotalACobrar() {
		return totalACobrar;
	}

	public void setTotalACobrar(double totalACobrar) {
		this.totalACobrar = totalACobrar;
	}

	public int getCantidadCobrados() {
		return cantidadCobrados;
	}

	public void setCantidadCobrados(int cantidadCobrados) {
		this.cantidadCobrados = cantidadCobrados;
	}

	public double getTotalCobrado() {
		return totalCobrado;
	}

	public void setTotalCobrado(double totalCobrado) {
		this.totalCobrado = totalCobrado;
	}

	public int getCantidadNoCobrados() {
		return cantidadNoCobrados;
	}

	public void setCantidadNoCobrados(int cantidadNoCobrados) {
		this.cantidadNoCobrados = cantidadNoCobrados;
	}

	public double getTotalNoCobrados() {
		return totalNoCobrados;
	}

	public void setTotalNoCobrados(double totalNoCobrados) {
		this.totalNoCobrados = totalNoCobrados;
	}
	
}
