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
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "DOCUMENTO_EGRESO")
@Inheritance(strategy = InheritanceType.JOINED)
public class DocumentoEgreso implements Serializable{

	/**
	 * 
	 */
	public static final long serialVersionUID = -430229637735778167L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_documento_egreso")
	@SequenceGenerator(name = "gen_id_documento_egreso", sequenceName = "gen_id_documento_egreso",allocationSize = 1)
	@Column(name="ID_DOCUMENTO_EGRESO")
	private long idDocumentoEgreso = -1;
	
	@Column(name = "FECHA_EMISION")
	private Date fechaEmision;
	
	@Column(name = "FECHA_PAGO")
	private Date fechaPago;
	private Double importe;
	private Integer numero;
	
	@Enumerated(EnumType.STRING)
	private Estado estado = Estado.CREADA;
	//Relacion con otros objetos
	
	@OneToMany(mappedBy = "documentoEgreso", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<MovimientoCajaEgreso> listaMovimientoCajaEgreso = new HashSet<MovimientoCajaEgreso>();
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "RELA_ORDEN_P_MOV_BAN", joinColumns=@JoinColumn(name = "ID_DOCUMENTO_EGRESO"), inverseJoinColumns=@JoinColumn(name = "ID_MOVIMIENTO_BANCARIO"))
	private Set<MovimientoBancario> movimientosBancarios = new HashSet<MovimientoBancario>();
	
	public enum Estado{
		CREADA, CONFIRMADA, CANCELADA;
		@Override
		public String toString() {
			return com.trascender.framework.util.Util.capitalizeEnumName(super.toString());
		}
	}
	public Date getFechaEmision() {
		return fechaEmision;
	}
	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	public Date getFechaPago() {
		return fechaPago;
	}
	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}
	public Double getImporte() {
		return importe;
	}
	public void setImporte(Double importe) {
		this.importe = importe;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public long getIdDocumentoEgreso() {
		return idDocumentoEgreso;
	}
	public void setIdDocumentoEgreso(long idDocumentoEgreso) {
		this.idDocumentoEgreso = idDocumentoEgreso;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		if (this.idDocumentoEgreso ==-1){
			return super.hashCode();
		}
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + (int) (idDocumentoEgreso ^ (idDocumentoEgreso >>> 32));
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
		final DocumentoEgreso other = (DocumentoEgreso) obj;
		if (idDocumentoEgreso != other.idDocumentoEgreso) {
			return false;
		}
		return true;
	}
	public Set<MovimientoCajaEgreso> getListaMovimientoCajaEgreso() {
		return listaMovimientoCajaEgreso;
	}
	public void setListaMovimientoCajaEgreso(
			Set<MovimientoCajaEgreso> listaMovimientoCajaEgreso) {
		this.listaMovimientoCajaEgreso = listaMovimientoCajaEgreso;
	}
	
	@Override
	public String toString(){
		return this.numero+" "+this.importe+" ["+this.estado+"]["+this.fechaEmision+"]";
	}
	
	public Set<MovimientoBancario> getMovimientosBancarios() {
		return movimientosBancarios;
	}
	
	public void setMovimientosBancarios(Set<MovimientoBancario> movimientosBancarios) {
		this.movimientosBancarios = movimientosBancarios;
	}
}
