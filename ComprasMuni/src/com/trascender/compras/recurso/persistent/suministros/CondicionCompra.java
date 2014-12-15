/**
 * 
 */
package com.trascender.compras.recurso.persistent.suministros;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "CONDICION_COMPRA")
public class CondicionCompra implements Serializable {

	public enum Estado{
		ACTIVO,INACTIVO
	}
	public static final long serialVersionUID = -8376419914571255088L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_condicion_compra")
	@SequenceGenerator(name = "gen_id_condicion_compra", sequenceName = "gen_id_condicion_compra",allocationSize = 1)
	@Column(name="ID_CONDICION_COMPRA")
	private long idCondicionCompra=-1;
	private String nombre;
	
	@Column(name = "cantidad_vencimientos")
	private Integer cantidadVencimientos;
	
	@Column(name = "dias_entre_vencimientos")
	private Integer diasEntreVencimientos;
	
	@Enumerated(EnumType.STRING)
	private Estado estado;
	
	public Integer getCantidadVencimientos() {
		return cantidadVencimientos;
	}
	public void setCantidadVencimientos(Integer cantidadVencimientos) {
		this.cantidadVencimientos = cantidadVencimientos;
	}
	public Integer getDiasEntreVencimientos() {
		return diasEntreVencimientos;
	}
	public void setDiasEntreVencimientos(Integer diasEntreVencimientos) {
		this.diasEntreVencimientos = diasEntreVencimientos;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public long getIdCondicionCompra() {
		return idCondicionCompra;
	}
	public void setIdCondicionCompra(long idCondicionCompra) {
		this.idCondicionCompra = idCondicionCompra;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
