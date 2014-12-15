package com.trascender.compras.recurso.persistent.suministros;

import java.io.Serializable;

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
@Table(name = "BIEN_PROVISTO")
public class BienProvisto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1787593817825561219L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_bien_provisto")
	@SequenceGenerator(name = "gen_id_bien_provisto", sequenceName = "gen_id_bien_provisto",allocationSize = 1)
	@Column(name="ID_BIEN_PROVISTO")
	private long idBienProvisto=-1;
	
	@ManyToOne
	@JoinColumn(name = "ID_BIEN")
	private Bien bien;
	
	@ManyToOne
	@JoinColumn(name = "ID_PROVEEDOR")
	private Proveedor proveedor;
	
	private double precio=0;
	
	private String descripcion;
	
	public Bien getBien() {
		return bien;
	}
	public void setBien(Bien bien) {
		this.bien = bien;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public long getIdBienProvisto() {
		return idBienProvisto;
	}
	public void setIdBienProvisto(long idBienProvisto) {
		this.idBienProvisto = idBienProvisto;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public Proveedor getProveedor() {
		return proveedor;
	}
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	
	public String getNombre(){
		return bien.getNombre();
	}
	
	public void setNombre(String pNombre){
		this.bien.setNombre(pNombre);
	}
	

	@Override
	public String toString() {
		if(this.getBien() != null && this.getBien().getIdBien() != -1){
			return this.getBien().getNombre();
		}
		return " ";
	}
}
