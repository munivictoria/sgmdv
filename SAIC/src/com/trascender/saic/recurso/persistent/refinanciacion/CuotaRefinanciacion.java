package com.trascender.saic.recurso.persistent.refinanciacion;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.framework.util.Util;
import com.trascender.saic.recurso.persistent.RegistroDeuda;

@Entity
@Table(name = "CUOTA_REFINANCIAC")
@PrimaryKeyJoinColumn(name = "ID_REGISTRO_DEUDA")
public class CuotaRefinanciacion extends RegistroDeuda {

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
	
	private Double recargo = 0d;

	public CuotaRefinanciacion() {
		this.setTipoDeuda(TipoDeuda.REFINANCIACION);
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

	@Override
	public String getNombre() {
		return this.toString();
	}

	@Override
	public Persona getPersona() {
		return this.getDocGeneradorDeuda().getObligacion().getPersona();
	}

	@Override
	public String toString() {
		DocumentoRefinanciacion locDocumentoRefinanciacion = (DocumentoRefinanciacion) this.getDocGeneradorDeuda();
		
		return "Plan Nº: " + locDocumentoRefinanciacion.getNumeroRefinanciacion() + " - Cuota " + this.getNumeroCuota();
	}
	
	/**
	 * @return obtiene el valor cuota + intereses + recargos (tiene que pagar todo)
	 */
	@Override
	public Double getMonto() {
		Double locMontoExencion = this.getMontoExencion();
		Double locMonto = this.getValorCuota() + this.getInteres() + this.getRecargo() + this.getMultas();
		
		return ((locMonto != null && locMonto > 0D) ? locMonto : 0D) - ((locMontoExencion != null && locMontoExencion > 0D) ? locMontoExencion : 0D);
	}

	@Transient
	@Override
	public Double getMultas() {
		return 0d;
	}

	public Double getRecargo() {
		return this.recargo;
	}
	
	public void setRecargo(Double pRecargo) {
		this.recargo = pRecargo;
	}
	
	public Double calcularRecargo() {
		DocumentoRefinanciacion locDocumentoRefinanciacion = (DocumentoRefinanciacion) this.getDocGeneradorDeuda();
		Double interesDiario = locDocumentoRefinanciacion.getInteresDiario();
		
		// Si la cantidad de dias es faltante (+) entonces no se aplican interes diarios...
		Integer cantidadDias = this.getCantidadDiasDesdeVencimiento();
		cantidadDias = (cantidadDias >= 0) ? 0 : cantidadDias;
		
		this.recargo = interesDiario * (cantidadDias * (-1)) * this.getValorCuota();
		
		return this.recargo;
	}

	private Integer getCantidadDiasDesdeVencimiento() {
		try {
			return Util.getDiasDiferencia(SecurityMgr.getInstance().getFechaActual().getTime(), this.getFechaVencimiento());
		} catch(Exception e) {
			try {
				return -1 * Util.getDiasDiferencia(this.getFechaVencimiento(), SecurityMgr.getInstance().getFechaActual().getTime());
			} catch(Exception ex) {
				return 0;
			}
		}
	}
	
}