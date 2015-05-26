package com.trascender.saic.recurso.persistent;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.trascender.framework.util.Periodicidad;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;

@Entity
@Table(name = "DOC_GENERADOR_DEUDA")
@Inheritance (strategy = InheritanceType.JOINED)
public class DocGeneradorDeuda implements Serializable{

	public enum TipoDocGeneradorDeuda{SELLADO,TASA,MIGRACION,REFINANCIACION,INGRESO_VARIO}

	private static final long serialVersionUID = -8706846311834524781L;

	@Id
	@Column(name = "ID_DOC_GENERADOR_DEUDA")
	@SequenceGenerator(allocationSize = 1, name = "gen_id_doc_generador_deuda", sequenceName = "gen_id_doc_generador_deuda")
	@GeneratedValue(generator = "gen_id_doc_generador_deuda", strategy = GenerationType.SEQUENCE)
	private long idDocGeneradorDeuda=-1;

	@Transient
	private String nombre;

	private String descripcion;

	//	@Column(name = "FECHA_DESDE")
	@Transient
	private Date fechaDesde;

	@Transient
	private Date fechaHasta;

	@Column(name = "CANT_REGISTROS_DEUDA")
	private Integer cantidadRegDeuda;

	@Transient
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "ID_DOC_GENERADOR_DEUDA_ANT")
	private DocGeneradorDeuda docGeneradorDeudaAnterior;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_OBLIGACION", nullable = false)
	private Obligacion obligacion;

	@Enumerated(EnumType.STRING)
	@Column(name = "PERIODICIDAD")
	private Periodicidad periodicidad;

	@Column(name = "ULT_NRO_REG_DEUDA_LIQUIDADO")
	private Integer nroUtltimoRegistroDeudaLiquidado;

	//Paso esto a Lazy, no creo que se se necesite cargar, Fernando
	@OneToMany(fetch=FetchType.LAZY, mappedBy = "docGeneradorDeuda", cascade = CascadeType.DETACH)
	private Set<RegistroDeuda> listaRegistrosDeuda=new HashSet<RegistroDeuda>();

	@Enumerated(EnumType.STRING)
	@Column(name = "TIPO")
	private TipoDocGeneradorDeuda tipoDocGeneradorDeuda = TipoDocGeneradorDeuda.TASA;


	public TipoDocGeneradorDeuda getTipoDocGeneradorDeuda() {
		if (tipoDocGeneradorDeuda==null){
			this.tipoDocGeneradorDeuda=TipoDocGeneradorDeuda.TASA;
		}
		return tipoDocGeneradorDeuda;
	}
	public void setTipoDocGeneradorDeuda(TipoDocGeneradorDeuda tipoDocGeneradorDeuda) {
		this.tipoDocGeneradorDeuda = tipoDocGeneradorDeuda;
	}

	public DocGeneradorDeuda getDocGeneradorDeudaAnterior() {
		return docGeneradorDeudaAnterior;
	}
	public void setDocGeneradorDeudaAnterior(
			DocGeneradorDeuda docGeneradorDeudaAnterior) {
		this.docGeneradorDeudaAnterior = docGeneradorDeudaAnterior;
	}


	public Integer getCantidadRegDeuda() {
		return cantidadRegDeuda;
	}
	public void setCantidadRegDeuda(Integer pCantidadRegDeuda) {
		cantidadRegDeuda = pCantidadRegDeuda;
	}

	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String pDescripcion) {
		descripcion = pDescripcion;
	}


	public Date getFechaDesde() {
		return fechaDesde;
	}
	public void setFechaDesde(Date pFechaDesde) {
		fechaDesde = pFechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}
	public void setFechaHasta(Date pFechaHasta) {
		fechaHasta = pFechaHasta;
	}


	public long getIdDocGeneradorDeuda() {
		return idDocGeneradorDeuda;
	}
	public void setIdDocGeneradorDeuda(long pIdDocGeneradorDeuda) {
		idDocGeneradorDeuda = pIdDocGeneradorDeuda;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String pNombre) {
		nombre = pNombre;
	}

	@Override
	public String toString() {	
		return (this.nombre==null)?"":this.nombre;
	}


	public Obligacion getObligacion() {
		return obligacion;
	}
	public void setObligacion(Obligacion obligacion) {
		this.obligacion = obligacion;
	}


	public Set<RegistroDeuda> getListaRegistrosDeuda() {
		return listaRegistrosDeuda;
	}
	public void setListaRegistrosDeuda(Set<RegistroDeuda> listaRegistrosDeuda) {
		this.listaRegistrosDeuda = listaRegistrosDeuda;
	}


	public Periodicidad getPeriodicidad() {
		return periodicidad;
	}

	public void setPeriodicidad(Periodicidad periodicidad) {
		this.periodicidad = periodicidad;
	}


	public Integer getNroUtltimoRegistroDeudaLiquidado() {
		return nroUtltimoRegistroDeudaLiquidado;
	}
	public void setNroUtltimoRegistroDeudaLiquidado(
			Integer nroUtltimoRegistroDeudaLiquidado) {
		this.nroUtltimoRegistroDeudaLiquidado = nroUtltimoRegistroDeudaLiquidado;
	}

	@Override
	public int hashCode() {
		if (this.idDocGeneradorDeuda==-1) return super.hashCode();
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + (int) (idDocGeneradorDeuda ^ (idDocGeneradorDeuda >>> 32));
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
		final DocGeneradorDeuda other = (DocGeneradorDeuda) obj;
		if (idDocGeneradorDeuda != other.idDocGeneradorDeuda)
			return false;
		return true;
	}

}