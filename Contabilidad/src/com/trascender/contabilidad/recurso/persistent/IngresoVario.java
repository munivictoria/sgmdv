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
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.saic.recurso.interfaces.Pagable;
import com.trascender.saic.recurso.persistent.DocGeneradorDeuda;
import com.trascender.saic.recurso.persistent.RegistroDeuda;

@Entity
@Table(name = "INGRESO_VARIO")
@PrimaryKeyJoinColumn(name = "ID_REGISTRO_DEUDA")
public class IngresoVario extends RegistroDeuda implements Serializable, Pagable {

	public static final long serialVersionUID = -3670711348690088473L;

//	@Id
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_id_ingreso_vario")
//	@SequenceGenerator(name = "gen_id_ingreso_vario", sequenceName = "gen_id_ingreso_vario", allocationSize = 1)
//	@Column(name = "ID_INGRESO_VARIO")
//	private long idIngresoVario = -1;

	@Column(name = "VALOR")
	private Double valor = 0D;

	@Column(name = "FECHA_EMISION")
	private Date fechaEmision;
	private Integer numero = 0;

	// Relacion con otros objetos

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

	@Override
	public EstadoRegistroDeuda getEstado() {
		return estado;
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
		return this.toString();
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

//	@Override
//	public int hashCode() {
//		if(this.idIngresoVario == -1) {
//			return super.hashCode();
//		}
//		final int PRIME = 31;
//		int result = 1;
//		result = PRIME * result + (int) (idIngresoVario ^ (idIngresoVario >>> 32));
//		return result;
//	}

//	@Override
//	public boolean equals(Object obj) {
//		if(this == obj) {
//			return true;
//		}
//		if(obj == null) {
//			return false;
//		}
//		if(getClass() != obj.getClass()) {
//			return false;
//		}
//		final IngresoVario other = (IngresoVario) obj;
//		if(idIngresoVario != other.idIngresoVario) {
//			return false;
//		}
//		return true;
//	}
	
	

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
		return this.getValor();
	}

}