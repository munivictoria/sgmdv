package com.trascender.contabilidad.recurso.persistent;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "MOVIMIENTO_BANCARIO")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class MovimientoBancario implements Serializable {

	
	private static final long serialVersionUID = -5820433635411506475L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_movimiento_bancario")
	@SequenceGenerator(name = "gen_id_movimiento_bancario", sequenceName = "gen_id_movimiento_bancario",allocationSize = 1)
	@Column(name="ID_MOVIMIENTO_BANCARIO")
	private long idMovimientoBancario = -1;
	private Double importe = 0d;
	
	@ManyToMany
	@JoinTable(name = "RELA_ORDEN_P_MOV_BAN", joinColumns=@JoinColumn(name = "ID_MOVIMIENTO_BANCARIO"), inverseJoinColumns=@JoinColumn(name = "ID_DOCUMENTO_EGRESO"))
	private Set<OrdenPago> ordenesPago = new HashSet<OrdenPago>();
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_LINEA_LIBRO_BANCO")
	private LineaLibroBanco lineaLibroBanco;
	
	@ManyToOne
	@JoinColumn(name = "ID_CUENTA_BANCARIA")
	private CuentaBancaria cuentaBancaria;
	
	public long getIdMovimientoBancario() {
		return idMovimientoBancario;
	}
	public void setIdMovimientoBancario(long idMovimientoBancario) {
		this.idMovimientoBancario = idMovimientoBancario;
	}
	
	public Double getImporte() {
		return importe;
	}
	public void setImporte(Double importe) {
		this.importe = importe;
	}
	
	public Set<OrdenPago> getOrdenesPago() {
		return ordenesPago;
	}
	public void setOrdenesPago(Set<OrdenPago> ordenesPago) {
		this.ordenesPago = ordenesPago;
	}
	
	public LineaLibroBanco getLineaLibroBanco() {
		return lineaLibroBanco;
	}
	public void setLineaLibroBanco(LineaLibroBanco lineaLibroBanco) {
		this.lineaLibroBanco = lineaLibroBanco;
	}
	
	public CuentaBancaria getCuentaBancaria() {
		return cuentaBancaria;
	}
	public void setCuentaBancaria(CuentaBancaria cuentaBancaria) {
		this.cuentaBancaria = cuentaBancaria;
	}
	
	@Override
	public int hashCode() {
		if(this.idMovimientoBancario == -1){
			return super.hashCode();
		}
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ (int) (idMovimientoBancario ^ (idMovimientoBancario >>> 32));
		return result;
	}
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
		MovimientoBancario other = (MovimientoBancario) obj;
		if (idMovimientoBancario != other.idMovimientoBancario) {
			return false;
		}
		return true;
	}
	
}
