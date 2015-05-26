	package com.trascender.saic.recurso.persistent;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.trascender.framework.recurso.persistent.DigestoMunicipal;
import com.trascender.saic.recurso.persistent.refinanciacion.CuotaRefinanciacion;
import com.trascender.saic.recurso.persistent.refinanciacion.DocumentoRefinanciacion;
import com.trascender.saic.recurso.transients.LiquidacionTasaAgrupada;

/**
 * Este registro de cancelación obtiene los datos de los registros de deuda
 * que refinancia, no calcula datos de la refinanciación
 * @author Mariano Lusardi
 *
 */
@Entity
@Table(name = "REG_CANC_POR_REF")
@PrimaryKeyJoinColumn(name = "ID_REGISTRO_CANCELACION")
public class RegCancelacionPorRefinanciacion extends RegistroCancelacion{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5004726042156629837L;
	
	
	//Cambio a Lazy por consulta muy larga. Fernando
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "registroCancelacion")
	private Set<RegistroDeuda> listaRegistrosDeuda=new HashSet<RegistroDeuda>();
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_DIGESTO_MUNICIPAL", nullable = true)
	private DigestoMunicipal digestoMunicipal;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_DOC_GENERADOR_DEUDA", nullable = true)
	private DocumentoRefinanciacion documentoRefinanciacion;
	
	@Column(name = "IMPORTE_CONDONADO")
	private Double importeCondonado;
	
	@Column(name = "INTERES_CONDONADO")
	private Double interesCondonado;
	
	@Column(name = "RECARGO_CONDONADO")
	private Double recargoCondonado;
	
	@Column(name = "MULTA_CONDONADA")
	private Double multaCondonada;

	/**
	 * 
	 * @return la suma de los intereses de todos los registros de deuda que sean liquidaciones
	 */
	public Double getInteres(){
		Double interes= 0d;
		for (RegistroDeuda locRegistroDeuda: this.listaRegistrosDeuda){
			if (locRegistroDeuda instanceof LiquidacionTasa ){
				LiquidacionTasa locLiquidacionTasa = (LiquidacionTasa)locRegistroDeuda;
				interes+=locLiquidacionTasa.getInteres();
			}
		}
		return interes;
	}
	
	
	/**
	 * 
	 * @return retorna el total de los recargos de las liquidaciones
	 */
	public Double getRecargoTotal(){
		Double recargo = 0d;
		for (RegistroDeuda locRegistroDeuda: this.listaRegistrosDeuda){
			if (locRegistroDeuda instanceof LiquidacionTasa){
				LiquidacionTasa locLiquidacionTasa = (LiquidacionTasa)locRegistroDeuda;
				recargo += locLiquidacionTasa.getRecargo();
			}
		}
		return recargo;
	}
	
	
	/**
	 * Obtiene todos los valores de las multas 
	 * @return
	 */
	public Double getMultasTotal(){
		double multa = 0;
		for (RegistroDeuda locRegistroDeuda: this.listaRegistrosDeuda){
			if (locRegistroDeuda instanceof LiquidacionTasa){
				LiquidacionTasa locLiquidacionTasa = (LiquidacionTasa)locRegistroDeuda;
				multa += locLiquidacionTasa.getMontoMultas();
			}
		}
		return multa;
	}
	
	/**
	 * @return la sumatoria de todos los montos de los registros de deuda con sus modificadores
	 * pero sin considerar las multas aplicadas, ni los intereses y recargos
	 */
	public Double getImporteTotal(){
		Double montoTotal = 0d;
		for (RegistroDeuda locRegistroDeuda: this.listaRegistrosDeuda){
			if (locRegistroDeuda instanceof LiquidacionTasa){
				LiquidacionTasa locLiquidacionTasa = (LiquidacionTasa)locRegistroDeuda;
				montoTotal+=locLiquidacionTasa.getValorTotal();
			}else if (locRegistroDeuda instanceof CuotaRefinanciacion){
				CuotaRefinanciacion cadaCuota = (CuotaRefinanciacion) locRegistroDeuda;
				montoTotal += cadaCuota.getValor();
			} else {
				montoTotal += locRegistroDeuda.getMonto();
			}
		}
		return montoTotal;
	}
	

	/**
	 * Es el total de lo que el contribuyente debería pagar en todas las liquidaciones
	 * @return
	 */
	public Double getCapitalAPagar(){
		Double capital =0d;
		for (RegistroDeuda locRegistroDeuda: this.listaRegistrosDeuda){
			capital += locRegistroDeuda.getMonto();
		}
		return capital;
	}
	
	/**
	 * recupera el total condonado
	 * @return
	 */
	public Double getTotalCondonado(){
		Double locCondonado =  this.getImporteCondonado() +
				this.getInteresCondonado() +
				this.getRecargoCondonado() +
				this.getMultaCondonada();
		System.out.println("__________________condonado ? ="+locCondonado);
		return locCondonado;
		
	}
	
	
	
	
	public Set<RegistroDeuda> getListaRegistrosDeuda() {
		return listaRegistrosDeuda;
	}
	public void setListaRegistrosDeuda(Set<RegistroDeuda> listaRegistrosDeuda) {
		this.listaRegistrosDeuda = listaRegistrosDeuda;
	}
	
	public void addListaRegistrosDeuda(Set<RegistroDeuda> listaRegistros) {
		//Si es LiquidacionTasaAgrupada, agrego las Liquidaciones que la componen directamente:
		for (RegistroDeuda cadaRegistro : listaRegistros) {
			if (cadaRegistro instanceof LiquidacionTasaAgrupada) {
				LiquidacionTasaAgrupada cadaAgrupada = (LiquidacionTasaAgrupada) cadaRegistro;
				this.listaRegistrosDeuda.addAll(cadaAgrupada.getListaLiquidacionesTasa());
			} else {
				this.listaRegistrosDeuda.add(cadaRegistro);
			}
		}
	}

	
	
	/**
	 * @return la cantidad de registros de deuda a refinanciar
	 */
	public int getCantidadRegistrosDeuda(){
		return this.getListaRegistrosDeuda().size();
	}
	
	public DigestoMunicipal getDigestoMunicipal() {
		return digestoMunicipal;
	}
	public void setDigestoMunicipal(DigestoMunicipal digestoMunicipal) {
		this.digestoMunicipal = digestoMunicipal;
	}
	
	public Double getImporteCondonado() {
		return importeCondonado;
	}
	public void setImporteCondonado(Double importeCondonado) {
		this.importeCondonado = importeCondonado;
	}

	public Double getInteresCondonado() {
		return interesCondonado;
	}
	public void setInteresCondonado(Double interesCondonado) {
		this.interesCondonado = interesCondonado;
	}


	public Double getRecargoCondonado() {
		return recargoCondonado;
	}
	public void setRecargoCondonado(Double recargoCondonado) {
		this.recargoCondonado = recargoCondonado;
	}

	public Double getMultaCondonada() {
		return multaCondonada;
	}
	public void setMultaCondonada(Double multaCondonada) {
		this.multaCondonada = multaCondonada;
	}


	public DocumentoRefinanciacion getDocumentoRefinanciacion() {
		return documentoRefinanciacion;
	}
	public void setDocumentoRefinanciacion(DocumentoRefinanciacion documentoRefinanciacion) {
		this.documentoRefinanciacion = documentoRefinanciacion;
	}

}
