/**
 * 
 */
package com.trascender.compras.recurso.persistent.suministros;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.trascender.framework.recurso.persistent.referencia.CuentaRfr;
import com.trascender.framework.util.AuditoriaIndirecta;
import com.trascender.framework.util.EntidadTrascender;

@Entity
@Table(name = "LINEA_FACTURA")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="DISC")
public abstract class LineaFactura implements Serializable, AuditoriaIndirecta{

	public static final long serialVersionUID = 4781770898521048876L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_linea_factura")
	@SequenceGenerator(name = "gen_id_linea_factura", sequenceName = "gen_id_linea_factura",allocationSize = 1)
	@Column(name="ID_LINEA_FACTURA")
	private long idLineaFactura = -1;
	
	private Double cantidad=0D;
	
	@Column(name = "MONTO_UNITARIO")
	private Double montoUnitario = 0D;
	private Double total=0D;
	
	@ManyToOne
	@JoinColumn(name = "ID_CUENTA")
	private CuentaRfr cuenta;
	
	@ManyToOne
	@JoinColumn(name = "ID_FACTURA")
	private Factura factura;
	
	public abstract String getNombre();
	
	public long getIdLineaFactura() {
		return idLineaFactura;
	}
	public Double getCantidad() {
		return cantidad;
	}
	public Double getTotal() {
		return this.total;
	}
	public Factura getFactura() {
		return factura;
	}
	
	public void setFactura(Factura pFactura) {
		this.factura= pFactura; 
	}
	
	public void setIdLineaFactura(long pIdLineaFactura) {
		this.idLineaFactura = pIdLineaFactura;
	}
	
	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}
	
	public void setTotal(Double pTotal) {
		this.total = this.montoUnitario * this.cantidad;
	}
	
	@Override
	public String toString(){
		if(this.getNombre() != null){
			//this.bienProvisto.toString();
			
			return "Descripción: " + this.getNombre() + " - Precio: $" +
			this.montoUnitario + " - Cantidad: " + this.cantidad + " - Total: $"+
			this.total;
		}
		else return null;
	}
	
	@Override
	public int hashCode() {
		if ( this.idLineaFactura == -1){
			return super.hashCode();
		}
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ (int) (idLineaFactura ^ (idLineaFactura >>> 32));
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
		LineaFactura other = (LineaFactura) obj;
		if (idLineaFactura != other.idLineaFactura)
			return false;
		return true;
	}
	
	public Double getMontoUnitario() {
		return montoUnitario;
	}
	public void setMontoUnitario(Double montoUnitario) {
		this.montoUnitario = montoUnitario;
	}
	public CuentaRfr getCuenta() {
		return cuenta;
	}
	public void setCuenta(CuentaRfr cuenta) {
		this.cuenta = cuenta;
	}
	
	public EntidadTrascender getEntidadTrascender() {
		return factura;
	}

	public String getNombrePropiedad() {
		return "Linea ["+getNombre()+"]";
	}

	public boolean concatenaNombre() {
		return true;
	}
}
