/**
 * 
 */
package com.trascender.contabilidad.recurso.persistent;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "LINEA_LIBRO_BANCO")
public class LineaLibroBanco implements Serializable{

	/**
	 * 
	 */
	public static final long serialVersionUID = -6321862804365819705L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_linea_libro_banco")
	@SequenceGenerator(name = "gen_id_linea_libro_banco", sequenceName = "gen_id_linea_libro_banco",allocationSize = 1)
	@Column(name="ID_LINEA_LIBRO_BANCO")
	private long idLineaLibroBanco = -1;
	
	@Enumerated(EnumType.STRING)
	private Tipo tipo;
	private Date fecha;
	private String observaciones;
	private Double importe;
	private boolean conciliado;
	
	public enum Tipo{DEPOSITO, EXTRACCION, SALDO_INICIAL; 
		@Override
		public String toString(){
			return com.trascender.framework.util.Util.capitalizeEnumName(super.toString());
		}
	}
	
	//Relacion con otros objetos
	
	@OneToMany(mappedBy = "lineaLibroBanco", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set <MovimientoBancario> movimientoBancario= new HashSet<MovimientoBancario>();
	
	@OneToMany(mappedBy = "lineaLibroBanco")
	private Set<BoletaDeposito> boletasDeposito = new HashSet<BoletaDeposito>();
	
	@ManyToOne
	@JoinColumn(name = "ID_LIBRO_BANCO")
	private LibroBanco libroBanco;
	
	public Set<MovimientoBancario> getMovimientoBancario() {
		return movimientoBancario;
	}
	public void setMovimientoBancario(Set<MovimientoBancario> movimientoBancario) {
		this.movimientoBancario = movimientoBancario;
	}
	public boolean isConciliado() {
		return conciliado;
	}
	public void setConciliado(boolean conciliado) {
		this.conciliado = conciliado;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public long getIdLineaLibroBanco() {
		return idLineaLibroBanco;
	}
	public void setIdLineaLibroBanco(long idLineaLibroBanco) {
		this.idLineaLibroBanco = idLineaLibroBanco;
	}
	public Double getImporte() {
		return importe;
	}
	public void setImporte(Double importe) {
		this.importe = importe;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
//	/**
//	 * @return the tipoConceptoLibroBanco
//	 * @hibernate.many-to-one column = "ID_TIPO_CONCEPTO_LIBRO_BANCO" not-null = "true" cascade = "all"
//	 */
//	public TipoConceptoLibroBanco getTipoConceptoLibroBanco() {
//		return tipoConceptoLibroBanco;
//	}
//	/**
//	 * @param tipoConceptoLibroBanco the tipoConceptoLibroBanco to set
//	 */
//	public void setTipoConceptoLibroBanco(
//			TipoConceptoLibroBanco tipoConceptoLibroBanco) {
//		this.tipoConceptoLibroBanco = tipoConceptoLibroBanco;
//	}
	public Set<BoletaDeposito> getBoletasDeposito() {
		return boletasDeposito;
	}
	public void setBoletasDeposito(Set<BoletaDeposito> boletasDeposito) {
		this.boletasDeposito = boletasDeposito;
	}
	public LibroBanco getLibroBanco() {
		return libroBanco;
	}
	public void setLibroBanco(LibroBanco libroBanco) {
		this.libroBanco = libroBanco;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		if (this.idLineaLibroBanco == -1){
			return super.hashCode();
		}
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + (int) (idLineaLibroBanco ^ (idLineaLibroBanco >>> 32));
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
		final LineaLibroBanco other = (LineaLibroBanco) obj;
		if (idLineaLibroBanco != other.idLineaLibroBanco) {
			return false;
		}
		return true;
	}
	
}
