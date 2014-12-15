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
@Table(name = "ASIENTO_CONTABLE")
public class AsientoContable implements Serializable{

	/**
	 * 
	 */
	public static final long serialVersionUID = 7610386852695231419L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_asiento_contable")
	@SequenceGenerator(name = "gen_id_asiento_contable", sequenceName = "gen_id_asiento_contable",allocationSize = 1)
	@Column(name="ID_ASIENTO_CONTABLE")
	private long idAsientoContable = -1;
	
	@Column(name = "NUMERO_ASIENTO")
	private Integer numeroAsiento;
	private Date fecha;
	private String Observaciones;
	
	@Enumerated(EnumType.STRING)
	private Tipo tipo;
	public enum Tipo{ COBRO, PAGO, DEVENGAMIENTO, MANUAL, PRESUPUESTARIO;
		@Override
		public String toString() {
			return com.trascender.framework.util.Util.capitalizeEnumName(super.toString());
		}
	}
	//Relacion con otros objetos
	
	@ManyToOne
	@JoinColumn(name = "ID_FOLIO_LIBRO_DIARIO")
	private FolioLibroDiario folioLibroDiario;
	
	@OneToMany(mappedBy = "asientoContable", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set <LineaAsientoContable> lineasAsientoContable = new HashSet<LineaAsientoContable>();
	
	public long getIdAsientoContable() {
		return idAsientoContable;
	}
	
	
	public void setIdAsientoContable(long idAsientoContable) {
		this.idAsientoContable = idAsientoContable;
	}
	
	public Date getFecha() {
		return fecha;
	}
	
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public FolioLibroDiario getFolioLibroDiario() {
		return folioLibroDiario;
	}
	public void setFolioLibroDiario(FolioLibroDiario folioLibroDiario) {
		this.folioLibroDiario = folioLibroDiario;
	}
	
	public Integer getNumeroAsiento() {
		return numeroAsiento;
	}
	public void setNumeroAsiento(Integer numeroAsiento) {
		this.numeroAsiento = numeroAsiento;
	}
	public String getObservaciones() {
		return Observaciones;
	}
	public void setObservaciones(String observaciones) {
		Observaciones = observaciones;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		if (this.idAsientoContable == -1){
			return super.hashCode();
		}
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + (int) (idAsientoContable ^ (idAsientoContable >>> 32));
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
		final AsientoContable other = (AsientoContable) obj;
		if (idAsientoContable != other.idAsientoContable) {
			return false;
		}
		return true;
	}
	public Set<LineaAsientoContable> getLineasAsientoContable() {
		return lineasAsientoContable;
	}
	public void setLineasAsientoContable(
			Set<LineaAsientoContable> lineasAsientoContable) {
		this.lineasAsientoContable = lineasAsientoContable;
	}
	
	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	
}
