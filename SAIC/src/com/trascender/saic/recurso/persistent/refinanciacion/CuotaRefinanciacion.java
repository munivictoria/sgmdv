package com.trascender.saic.recurso.persistent.refinanciacion;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.transients.Periodo;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.framework.util.Util;
import com.trascender.habilitaciones.recurso.persistent.CuotaLiquidacion;
import com.trascender.saic.recurso.persistent.RegistroDeuda;

@Entity
@Table(name = "CUOTA_REFINANCIAC")
@PrimaryKeyJoinColumn(name = "ID_REGISTRO_DEUDA")
public class CuotaRefinanciacion extends RegistroDeuda{


	private static final long serialVersionUID = 8876910760401312881L;
	
	@Column(name = "NUMERO_CUOTA")
	private Integer numeroCuota;
	
	@Column(name = "FECHA_VENCIMIENTO")
	private Date fechaVencimiento;

	@Column(name = "VALOR_CUOTA")
	private Double valorCuota;
	
	private Double interes;
	
	@Column(name = "SALDO_CAPITAL")
	private Double saldoCapital; 
	
	public CuotaRefinanciacion() {
		this.setTipoDeuda(TipoDeuda.REFINANCIACION);
	}
	
	@Transient
	@Override
	public Double getValor(){
		try{
			Double total = this.getValorCuota();
			Date locFechaActual = Calendar.getInstance().getTime(); 
			
			if (locFechaActual.after(this.getFechaVencimiento())){
				int cantidadDias = Util.getDiasDiferencia(this.getFechaVencimiento(),locFechaActual);
				total += cantidadDias*((DocumentoRefinanciacion)this.getDocGeneradorDeuda()).getInteresDiario();
			}
			
			return total;
		}
		catch(TrascenderException e){
			e.printStackTrace();
			return 0d;
		}
	}


	public Integer getNumeroCuota() {
		return numeroCuota;
	}


	public void setNumeroCuota(Integer numeroCuota) {
		this.numeroCuota = numeroCuota;
	}


	@Override
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}


	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}


	public Double getValorCuota() {
		return valorCuota;
	}


	public void setValorCuota(Double valorCuota) {
		this.valorCuota = valorCuota;
	}

	
	@Override
	public Double getInteres() {
		return interes;
	}


	public void setInteres(Double interes) {
		this.interes = interes;
	}


	public Double getSaldoCapital() {
		return saldoCapital;
	}


	public void setSaldoCapital(Double saldoCapital) {
		this.saldoCapital = saldoCapital;
	}

	public String getNombre() {
		return //this.getStringPeriodoLiquidado(); 
		this.toString();
	}


	public Persona getPersona() {
		return this.getDocGeneradorDeuda().getObligacion().getPersona();
	}


	@Override
	public String toString() {
		DocumentoRefinanciacion locDocumentoRefinanciacion = (DocumentoRefinanciacion) this.getDocGeneradorDeuda();
		return "Refinanciación Nº: "+locDocumentoRefinanciacion.getNumeroRefinanciacion() + " Nº Cuota: "+this.getNumeroCuota();
	}
	
	@Transient
	@Override
	public Double getMultas() {
		return 0d;
	}
	
	@Transient
	@Override
	public Double getRecargo() {
		DocumentoRefinanciacion locDocumentoRefinanciacion = (DocumentoRefinanciacion)this.getDocGeneradorDeuda();
		Double interesDiario = locDocumentoRefinanciacion.getInteresDiario();
		return interesDiario*this.getCantidadDiasDesdeVencimiento().floatValue()*this.getValor();
	}


	private Integer getCantidadDiasDesdeVencimiento() {
		try{
			return Util.getDiasDiferencia(SecurityMgr.getInstance().getFechaActual().getTime(),this.getFechaVencimiento());	
		}
		catch(Exception e){
			try{
				return -1*Util.getDiasDiferencia(this.getFechaVencimiento(), SecurityMgr.getInstance().getFechaActual().getTime());
				
			}
			catch(Exception ex){
				return 0;
			}
		}
	}

}
