package com.trascender.compras.recurso.persistent.suministros;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TRANSFERENCIA_ORDEN_COMPRA")
public class TransferenciaOrdenCompra implements Serializable{
	
	private static final long serialVersionUID = -3560308341569737986L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_trans_orden_compra")
	@SequenceGenerator(name = "gen_id_trans_orden_compra", sequenceName = "gen_id_trans_orden_compra",allocationSize = 1)
	@Column(name="ID_TRANSFERENCIA_ORDEN_COMPRA")
	private long idTransferenciaOrdenCompra = -1;
	
	@ManyToOne
	@JoinColumn(name = "ID_ORDEN_COMPRA")
	private OrdenCompra ordenCompra;
	
	@ManyToOne
	@JoinColumn(name="ID_PROVEEDOR")
	private Proveedor proveedorAnterior;
	
	private Date fecha = Calendar.getInstance().getTime();
	
	private String comentario;
	
	public TransferenciaOrdenCompra(){}
	
	public TransferenciaOrdenCompra(OrdenCompra pOrden, String pComentario){
		this.setProveedorAnterior(pOrden.getProveedor());
		this.setFecha(Calendar.getInstance().getTime());
		this.setOrdenCompra(pOrden);
		this.setComentario(pComentario);
	}
	
	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public long getIdTransferenciaOrdenCompra() {
		return idTransferenciaOrdenCompra;
	}

	public void setIdTransferenciaOrdenCompra(long idTransferenciaOrdenCompra) {
		this.idTransferenciaOrdenCompra = idTransferenciaOrdenCompra;
	}

	public OrdenCompra getOrdenCompra() {
		return ordenCompra;
	}

	public void setOrdenCompra(OrdenCompra ordenCompra) {
		this.ordenCompra = ordenCompra;
	}

	public Proveedor getProveedorAnterior() {
		return proveedorAnterior;
	}

	public void setProveedorAnterior(Proveedor proveedorAnterior) {
		this.proveedorAnterior = proveedorAnterior;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	

}
