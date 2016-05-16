package com.trascender.contabilidad.recurso.persistent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.util.Util;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.saic.recurso.interfaces.Pagable;
import com.trascender.saic.recurso.persistent.DocGeneradorDeuda;
import com.trascender.saic.recurso.persistent.RegistroDeuda;
import com.trascender.saic.recurso.persistent.RegistroDeuda.EstadoRegistroDeuda;

@Entity
@Table(name = "INGRESO_VARIO")
@PrimaryKeyJoinColumn(name = "ID_REGISTRO_DEUDA")
public class IngresoVario extends RegistroDeuda implements Serializable, Pagable {

	public static final long serialVersionUID = -3670711348690088473L;

	@Column(name = "VALOR")
	private Double valor = 0D;

	@Column(name = "FECHA_EMISION")
	private Date fechaEmision;
	
	@Column(name = "FECHA_VENCIMIENTO")
	private Date fechaVencimiento;
	
	@Column(name = "FECHA_VENCIMIENTO_ORIGINAL")
	private Date fechaVencimientoOriginal;
	
	private Integer numero = 0;
	
	private Double interes = 0D;

	@ManyToOne
	@JoinColumn(name = "ID_CONCEPTO_INGRESO_VARIO")
	private ConceptoIngresoVario conceptoIngresoVario;

	private String observaciones;

	@OneToMany(mappedBy = "ingresoVario", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ImputacionIngresoVario> listaImputacionIngresos = new ArrayList<ImputacionIngresoVario>();
	
	public IngresoVario() {
		Obligacion locObligacion = new Obligacion();
		locObligacion.setEstado(Obligacion.Estado.CREADO);
		DocGeneradorDeuda locDocumento = new DocGeneradorDeuda();
		locDocumento.setCantidadRegDeuda(1);
		locDocumento.setTipoDocGeneradorDeuda(DocGeneradorDeuda.TipoDocGeneradorDeuda.INGRESO_VARIO);
		locDocumento.setObligacion(locObligacion);
		this.setDocGeneradorDeuda(locDocumento);
		this.setEstado(EstadoRegistroDeuda.VIGENTE);
		this.setEstadoAnterior(EstadoRegistroDeuda.VIGENTE);
	}

	public List<ImputacionIngresoVario> getListaImputacionIngresos() {
		return listaImputacionIngresos;
	}

	public void setListaImputacionIngresos(List<ImputacionIngresoVario> listaImputacionIngresos) {
		this.listaImputacionIngresos = listaImputacionIngresos;
	}

	public ConceptoIngresoVario getConceptoIngresoVario() {
		return conceptoIngresoVario;
	}

	public void setConceptoIngresoVario(ConceptoIngresoVario conceptoIngresoVario) {
		this.conceptoIngresoVario = conceptoIngresoVario;
	}

	@Override
	public Date getFechaEmision() {
		return fechaEmision;
	}

	@Override
	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	@Override
	public Persona getPersona() {
		return getDocGeneradorDeuda().getObligacion().getPersona();
	}

	public void setPersona(Persona persona) {
		this.getDocGeneradorDeuda().getObligacion().setPersona(persona);
	}

	@Override
	public String getNombre() {
		return this.getConceptoIngresoVario().getNombre() + " - " + getObservaciones();
	}

	public Double getValor() {
		return this.valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		String linea = this.getPersona().toString() + " - " + this.conceptoIngresoVario.getNombre() + " - $" + this.valor;
		return linea;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	@Override
	public Long getId() {
		return this.getIdRegistroDeuda();
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	@Override
	public Double getMonto() {
		return this.getValor() + getInteres();
	}

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public Date getFechaVencimientoOriginal() {
		return fechaVencimientoOriginal;
	}

	public void setFechaVencimientoOriginal(Date fechaVencimientoOriginal) {
		this.fechaVencimientoOriginal = fechaVencimientoOriginal;
	}

	public Double getInteres() {
		return interes;
	}

	public void setInteres(Double interes) {
		this.interes = interes;
	}

	@Override
	public EstadoRegistroDeuda getEstado() {
		try {
			if(this.estado.equals(EstadoRegistroDeuda.ANULADA) || this.estado.equals(EstadoRegistroDeuda.CANCELADA) || this.estado.equals(EstadoRegistroDeuda.VENCIDA)
					|| this.estado.equals(EstadoRegistroDeuda.VIGENTE)) {
				if(this.getRegistroCancelacion() == null) {// si no tiene reg de cancelacion, es vigente
					estado = EstadoRegistroDeuda.VIGENTE;
				}

				if(estado.equals(EstadoRegistroDeuda.VIGENTE)) {
					Date locFechaActual = Util.getFechaActualFormatoSimple();

					if(this.getFechaVencimientoOriginal() != null) {// si es vigente y tiene una fecha de vencimiento, comprueba si no esta venciada
						Date locFechaVencimiento = this.getFechaVencimiento();
						estado = ((locFechaVencimiento.after(locFechaActual) || locFechaActual.getTime() == locFechaVencimiento.getTime()) ? EstadoRegistroDeuda.VIGENTE
								: EstadoRegistroDeuda.VENCIDA);
					} else {// si no tiene una fecha de vencimiento, el estado es vencido
						estado = EstadoRegistroDeuda.VENCIDA;
					}
				}
				// si el estado de la obligacion asociada es anulada, el registro es anulado.
				if(this.getDocGeneradorDeuda() != null 
						&& this.getDocGeneradorDeuda().getObligacion().getEstado().equals(Obligacion.Estado.ANULADO)
						&& this.estado != EstadoRegistroDeuda.PAGADA) {
					estado = EstadoRegistroDeuda.ANULADA;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return estado;
	}
	
	
	
}