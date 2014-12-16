package com.trascender.saic.recurso.persistent.refinanciacion;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.framework.util.Util;
import com.trascender.habilitaciones.recurso.persistent.shps.DocumentoSHPS;
import com.trascender.saic.recurso.persistent.DocGeneradorDeuda;
import com.trascender.saic.recurso.persistent.RegCancelacionPorRefinanciacion;
import com.trascender.saic.recurso.persistent.RegistroDeuda;

@Entity
@Table(name = "DOCUMENTO_REFINANCIACION")
@PrimaryKeyJoinColumn(name = "ID_DOC_GENERADOR_DEUDA")
public class DocumentoRefinanciacion extends DocGeneradorDeuda {

	/**
	 * Estados posibles de una refinanciación
	 */
	public enum EstadoRefinanciacion {
		TERMINADA, CADUCADA, PENDIENTE;

		@Override
		public String toString() {
			return Util.capitalizeEnumName(super.toString());
		}
	}

	/**
	 * 
	 */
	public static final long serialVersionUID = -3236503559570668549L;

	@Column(name = "NUMERO_REFINANCIACION")
	private Integer numeroRefinanciacion;

	@Column(name = "ESTADO_REFINANCIACION")
	@Enumerated(EnumType.STRING)
	private EstadoRefinanciacion estadoRefinanciacion = EstadoRefinanciacion.PENDIENTE;

	@Column(name = "SALDO_A_FAVOR")
	private Double saldoAFavor = 0d;

	@Column(name = "CANT_DIAS_CAIDA_REF")
	private Integer cantidadDiasCadaRefinanciacion = 30;

	@Column(name = "CANT_CUOTAS_CAIDA_REF")
	private Integer cantidadCuotasCadaRefinanciacion = 5;

	@Column(name = "INTERES_DIARIO")
	private Double interesDiario = 0.05d;

	@OneToOne(mappedBy = "documentoRefinanciacion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private RegCancelacionPorRefinanciacion regCancelacionPorRefinanciacion;

	@Column(name = "TASA_NOMINAL_ANUAL")
	private Double tasaNominalAnual;

	private Double capital;

	@Column(name = "CUOTAS_POR_ANIO")
	private Integer cuotasPorAnio = 12;

	@Column(name = "CANTIDAD_CUOTAS")
	private Integer cantidadCuotas;

	@Column(name = "DIA_VENCIMIENTO")
	private Integer diaVencimiento;

	@Column(name = "ANIO_INICIO_REFINANCIACION")
	private Integer anioInicioRefinanciacion;

	@Column(name = "MES_INICIO_REFINANCIACION")
	private Integer mesInicioRefinanciacion;

	@Transient
	private Double multa = 0d;
	@Transient
	private Double recargo = 0d;
	@Transient
	private Double entrega = 0d;
	@Transient
	private Double montoMinimoPorCuota = 0d;

	/**
	 * El capital es el monto a refinanciar
	 * 
	 * @return
	 */
	public Double getCapital() {
		if (this.capital == null) {

			if (this.getRegCancelacionPorRefinanciacion() != null) {

				this.setCapital(this.getRegCancelacionPorRefinanciacion()
						.getCapitalAPagar()
						- this.getRegCancelacionPorRefinanciacion()
								.getTotalCondonado() - this.getSaldoAFavor());
			}
		}
		return capital;
	}

	/**
	 * Recupera el total que ha de pagar
	 * 
	 * @return
	 */
	@Transient
	public Double getTotalAPagar() {
		double total = 0d;
		for (RegistroDeuda cadaRegistroDeuda : this.getListaRegistrosDeuda()) {
			total += cadaRegistroDeuda.getMonto();
		}
		total += this.multa + this.recargo;
		total -= this.entrega;

		return total;
	}

	public void setCapital(Double capital) {
		this.capital = capital;
	}

	public Integer getCuotasPorAnio() {
		return cuotasPorAnio;
	}

	public void setCuotasPorAnio(Integer cuotasPorAnio) {
		this.cuotasPorAnio = cuotasPorAnio;
	}

	public Integer getDiaVencimiento() {
		return diaVencimiento;
	}

	public void setDiaVencimiento(Integer diaVencimiento) {
		this.diaVencimiento = diaVencimiento;
	}

	public RegCancelacionPorRefinanciacion getRegCancelacionPorRefinanciacion() {
		return regCancelacionPorRefinanciacion;
	}

	public void setRegCancelacionPorRefinanciacion(
			RegCancelacionPorRefinanciacion regCancelacionPorRefinanciacion) {
		this.regCancelacionPorRefinanciacion = regCancelacionPorRefinanciacion;
	}

	public DocumentoRefinanciacion() {

		this.setTipoDocGeneradorDeuda(DocGeneradorDeuda.TipoDocGeneradorDeuda.REFINANCIACION);

	}

	public Integer getNumeroRefinanciacion() {
		return numeroRefinanciacion;
	}

	public void setNumeroRefinanciacion(Integer numeroRefinanciacion) {
		this.numeroRefinanciacion = numeroRefinanciacion;
	}

	public EstadoRefinanciacion getEstadoRefinanciacion()
			throws TrascenderException {
		int cantidadRegistrosDeudaImpagos = 0;
		int cantidadRegistrosDeudaCaducos = 0;
		int cantidadRegistrosDeudaVencidos = 0;
		for (RegistroDeuda locRegistroDeuda : this.getListaRegistrosDeuda()) {
			if (locRegistroDeuda.getRegistroCancelacion() == null
					|| !locRegistroDeuda.getEstado().equals(
							RegistroDeuda.EstadoRegistroDeuda.PAGADA)) {
				cantidadRegistrosDeudaImpagos++;
				CuotaRefinanciacion locCuotaRefinanciacion = (CuotaRefinanciacion) locRegistroDeuda;

				Date locFechaActual = SecurityMgr.getInstance()
						.getFechaActual().getTime();
				if (locFechaActual.after(locCuotaRefinanciacion
						.getFechaVencimiento())) {
					cantidadRegistrosDeudaVencidos++;
					int diasDiferencia = Util.getDiasDiferencia(
							locCuotaRefinanciacion.getFechaVencimiento(),
							locFechaActual);
					if (diasDiferencia >= this
							.getCantidadDiasCadaRefinanciacion())
						cantidadRegistrosDeudaCaducos++;
				}
			}
		}

		// if
		// ((cantidadRegistrosDeudaImpagos==0)&&(this.estadoRefinanciacion!=EstadoRefinanciacion.TERMINADA)){
		// &&(this.estadoRefinanciacion!=EstadoRefinanciacion.CADUCADA)
		if (cantidadRegistrosDeudaImpagos == 0) {
			this.setEstadoRefinanciacion(EstadoRefinanciacion.TERMINADA);
		} else if ((cantidadRegistrosDeudaCaducos >= this
				.getCantidadCuotasCadaRefinanciacion())) {
			this.setEstadoRefinanciacion(EstadoRefinanciacion.CADUCADA);
		} else if (this.estadoRefinanciacion != EstadoRefinanciacion.PENDIENTE) {
			this.setEstadoRefinanciacion(EstadoRefinanciacion.PENDIENTE);
		}

		return estadoRefinanciacion;
	}

	public void setEstadoRefinanciacion(
			EstadoRefinanciacion estadoRefinanciacion) {
		this.estadoRefinanciacion = estadoRefinanciacion;
	}

	public Double getSaldoAFavor() {
		return saldoAFavor;
	}

	public void setSaldoAFavor(Double saldoAFavor) {
		this.saldoAFavor = saldoAFavor;
	}

	public Integer getCantidadCuotas() {
		return cantidadCuotas;
	}

	public void setCantidadCuotas(Integer cantidadCuotas) {
		this.cantidadCuotas = cantidadCuotas;
	}

	public Integer getCantidadDiasCadaRefinanciacion() {
		return cantidadDiasCadaRefinanciacion;
	}

	public void setCantidadDiasCadaRefinanciacion(
			Integer cantidadDiasCadaRefinanciacion) {
		this.cantidadDiasCadaRefinanciacion = cantidadDiasCadaRefinanciacion;
	}

	public Integer getCantidadCuotasCadaRefinanciacion() {
		return cantidadCuotasCadaRefinanciacion;
	}

	public void setCantidadCuotasCadaRefinanciacion(
			Integer cantidadCuotasCadaRefinanciacion) {
		this.cantidadCuotasCadaRefinanciacion = cantidadCuotasCadaRefinanciacion;
	}

	public Double getInteresDiario() {
		return interesDiario;
	}

	public void setInteresDiario(Double interesDiario) {
		this.interesDiario = interesDiario;
	}

	public Integer getAnioInicioRefinanciacion() {
		return anioInicioRefinanciacion;
	}

	public void setAnioInicioRefinanciacion(Integer anioInicioRefinanciacion) {
		this.anioInicioRefinanciacion = anioInicioRefinanciacion;
	}

	public Integer getMesInicioRefinanciacion() {
		return mesInicioRefinanciacion;
	}

	public void setMesInicioRefinanciacion(Integer mesInicioRefinanciacion) {
		this.mesInicioRefinanciacion = mesInicioRefinanciacion;
	}

	public Double getTasaNominalAnual() {
		return tasaNominalAnual;
	}

	public void setTasaNominalAnual(Double tasaNominalAnual) {
		this.tasaNominalAnual = tasaNominalAnual;
	}

	/**
	 * @hibernate.property column="MULTA"
	 * @return
	 */
	public Double getMulta() {
		return multa;
	}

	public void setMulta(Double multa) {
		this.multa = multa;
	}

	/**
	 * @hibernate.property column="RECARGO"
	 * @return
	 */
	public Double getRecargo() {
		return recargo;
	}

	public void setRecargo(Double recargo) {
		this.recargo = recargo;
	}

	/**
	 * @hibernate.property column="ENTREGA"
	 * @return
	 */
	public Double getEntrega() {
		return entrega;
	}

	public void setEntrega(Double entrega) {
		this.entrega = entrega;
	}

	public Double getMontoMinimoPorCuota() {
		if (montoMinimoPorCuota != null && montoMinimoPorCuota == 0d) {
			this.montoMinimoPorCuota = this.getTotalAPagar()
					/ this.getCantidadCuotas();
		}
		return montoMinimoPorCuota;
	}

	public void setMontoMinimoPorCuota(Double montoMinimoPorCuota) {
		this.montoMinimoPorCuota = montoMinimoPorCuota;
	}

	public String getStringMesAnioInicio() {
		return this.mesInicioRefinanciacion + "/"
				+ this.anioInicioRefinanciacion;
	}

	public Persona getStringPersona() {
		return this.getObligacion().getPersona();
	}

	public String getStringInmuebles() {
		String locInmuebles = "";
		for (RegistroDeuda cadaRegistroDeuda : this
				.getRegCancelacionPorRefinanciacion().getListaRegistrosDeuda()) {
			// Es un inmueble si no pertenece a un comercio y se agrega si
			// todavía no está en el String.
			if (!(cadaRegistroDeuda.getDocGeneradorDeuda().getObligacion()
					.getDocumentoEspecializado() instanceof DocumentoSHPS)
					&& !locInmuebles.contains(cadaRegistroDeuda
							.getDocGeneradorDeuda().getObligacion()
							.getDocumentoEspecializado().getDomicilio()
							.toString())) {
				locInmuebles += " "
						+ cadaRegistroDeuda.getDocGeneradorDeuda()
								.getObligacion().getDocumentoEspecializado()
								.getDomicilio().toString() + "\n";
			}
		}
		return locInmuebles;
	}

	public String getStringComercios() {
		String locComercios = "";
		for (RegistroDeuda cadaRegistroDeuda : this
				.getRegCancelacionPorRefinanciacion().getListaRegistrosDeuda()) {
			// Es un inmueble si no pertenece a un comercio y se agrega si
			// todavía no está en el String.
			if ((cadaRegistroDeuda.getDocGeneradorDeuda().getObligacion()
					.getDocumentoEspecializado() instanceof DocumentoSHPS)
					&& !locComercios.contains(cadaRegistroDeuda
							.getDocGeneradorDeuda().getObligacion()
							.getDocumentoEspecializado().getDomicilio()
							.toString())) {
				locComercios += " "
						+ cadaRegistroDeuda.getDocGeneradorDeuda()
								.getObligacion().getDocumentoEspecializado()
								.getDomicilio().toString() + "\n";
			}
		}
		return locComercios;
	}

	public String getStringNombreRefinanciacion() {
		Calendar locCalendar = Calendar.getInstance();
		locCalendar.setTime(this.getObligacion().getDocumentoEspecializado()
				.getFechaCreacion());
		return " "
				+ "Refinanciación "
				+ locCalendar.get(Calendar.YEAR)
				/*+ " - "
				+ this.getRegCancelacionPorRefinanciacion()
						.getDigestoMunicipal().toString()*/;
	}

	public String getStringEstadoRefinanciacion() throws TrascenderException {
		return this.getEstadoRefinanciacion().toString();
	}

}
