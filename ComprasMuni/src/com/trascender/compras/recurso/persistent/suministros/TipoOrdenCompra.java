package com.trascender.compras.recurso.persistent.suministros;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.trascender.framework.recurso.persistent.Usuario;

@Entity
@Table(name = "TIPO_ORDEN_COMPRA")
public class TipoOrdenCompra implements Serializable {

	public enum Estado
	{ACTIVO, INACTIVO}
	/**
	 * 
	 */
	public static final long serialVersionUID = 2714972140422840187L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_orden_compra")
	@SequenceGenerator(name = "gen_id_orden_compra", sequenceName = "gen_id_orden_compra",allocationSize = 1)
	@Column(name="ID_TIPO_ORDEN_COMPRA")
	private long idTipoOrdenCompra=-1;
	private String nombre;
	
	@Column(name = "MINIMO")
	private Double montoMinimo;
	
	@Column(name = "MAXIMO")
	private Double montoMaximo;
	
	@Enumerated(EnumType.STRING)
	private Estado estado;
	
	public TipoOrdenCompra(){
	}
	
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;		
	}
	public long getIdTipoOrdenCompra() {
		return idTipoOrdenCompra;
	}
	public void setIdTipoOrdenCompra(long idTipoOrdenCompra) {
		this.idTipoOrdenCompra = idTipoOrdenCompra;
	}
	public Double getMontoMaximo() {
		return montoMaximo;
	}
	public void setMontoMaximo(Double montoMaximo) {
		this.montoMaximo = montoMaximo;
	}
	public Double getMontoMinimo() {
		return montoMinimo;
	}
	public void setMontoMinimo(Double montoMinimo) {
		this.montoMinimo = montoMinimo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public boolean checkMonto(OrdenCompra pOrdenCompra){
		if ((pOrdenCompra.getTotal() >= this.montoMinimo) && 
				(pOrdenCompra.getTotal()<= this.montoMaximo))
			return true;
		else
			return false;
	}
	
	@Override
	public String toString(){
		return this.nombre+" "+this.estado+" ("+this.montoMinimo+" - "+this.montoMaximo+")";
	}
}
