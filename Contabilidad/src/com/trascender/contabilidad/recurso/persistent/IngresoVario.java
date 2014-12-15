package com.trascender.contabilidad.recurso.persistent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.saic.recurso.interfaces.Pagable;
import com.trascender.saic.recurso.persistent.RegistroCancelacion;

@Entity
@Table(name = "INGRESO_VARIO")
public class IngresoVario implements Serializable, Pagable{

	public static final long serialVersionUID = -3670711348690088473L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_ingreso_vario")
	@SequenceGenerator(name = "gen_id_ingreso_vario", sequenceName = "gen_id_ingreso_vario",allocationSize = 1)
	@Column(name="ID_INGRESO_VARIO")
	private long idIngresoVario= -1;
	
	@Column(name = "VALOR")
	private Double valor = 0D;
	
	@Column(name = "FECHA_EMISION")
	private Date fechaEmision;
	private Integer numero = 0;
	public enum Estado {CREADO, ANULADO, PAGADO;
		
		@Override
		public String toString(){
			return com.trascender.framework.util.Util.capitalizeEnumName(super.toString());
		}
	}
	
	@Enumerated(EnumType.STRING)
	private Estado estado = Estado.CREADO;
	//Relacion con otros objetos
	
	@ManyToOne
	@JoinColumn(name = "ID_CONCEPTO_INGRESO_VARIO")
	private ConceptoIngresoVario conceptoIngresoVario;
	
	@ManyToOne
	@JoinColumn(name = "ID_PERSONA")
	private Persona persona;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_REGISTRO_CANCELACION")
	private RegistroCancelacion registroCancelacion;
	
	private String observaciones;
	
	@OneToMany(mappedBy = "ingresoVario", cascade = CascadeType.ALL, orphanRemoval=true)
	private List<ImputacionIngresoVario> listaImputacionIngresos = new ArrayList<ImputacionIngresoVario>();
	
	
	public List<ImputacionIngresoVario> getListaImputacionIngresos() {
		return listaImputacionIngresos;
	}

	public void setListaImputacionIngresos(
			List<ImputacionIngresoVario> listaImputacionIngresos) {
		this.listaImputacionIngresos = listaImputacionIngresos;
	}

	public RegistroCancelacion getRegistroCancelacion() {
		return registroCancelacion;
	}

	public void setRegistroCancelacion(RegistroCancelacion registroCancelacion) {
		this.registroCancelacion = registroCancelacion;
	}

	public long getIdIngresoVario() {
		return idIngresoVario;
	}

	public void setIdIngresoVario(long idIngresoVario) {
		this.idIngresoVario = idIngresoVario;
	}

	public ConceptoIngresoVario getConceptoIngresoVario() {
		return conceptoIngresoVario;
	}

	public void setConceptoIngresoVario(ConceptoIngresoVario conceptoIngresoVario) {
		this.conceptoIngresoVario = conceptoIngresoVario;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Date getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
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
	public String toString(){
		String linea = this.persona.toString() + " - "+ this.conceptoIngresoVario.getNombre() +" - $"+ this.valor;
		return linea; 
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		if (this.idIngresoVario == -1){
			return super.hashCode();
		}
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + (int) (idIngresoVario ^ (idIngresoVario >>> 32));
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final IngresoVario other = (IngresoVario) obj;
		if (idIngresoVario != other.idIngresoVario) {
			return false;
		}
		return true;
	}
	
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	@Override
	public Long getId() {
		return this.idIngresoVario;
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
