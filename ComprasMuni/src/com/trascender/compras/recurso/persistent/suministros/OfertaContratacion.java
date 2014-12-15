package com.trascender.compras.recurso.persistent.suministros;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "OFERTA_CONTRATACION")
public class OfertaContratacion implements Serializable{
	public static final long serialVersionUID = 5793184552964700360L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_oferta_contratacion")
	@SequenceGenerator(name = "gen_id_oferta_contratacion", sequenceName = "gen_id_oferta_contratacion",allocationSize = 1)
	@Column(name="ID_OFERTA_CONTRATACION")
	private long idOfertaContratacion = -1;
	
	private String garantia;
	private String comentarios;
	private String plazo;
	
	@ManyToOne
	@JoinColumn(name = "ID_PROVEEDOR")
	private Proveedor proveedor;
	
	@OneToMany(mappedBy = "ofertaContratacion", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<LineaOfertaContratacion> listaLineasOfertasContratacion = new ArrayList<LineaOfertaContratacion>();
	
	@ManyToOne
	@JoinColumn(name = "ID_CONTRATACION")
	private Contratacion contratacion;
	
	public Contratacion getContratacion() {
		return contratacion;
	}
	public void setContratacion(Contratacion contratacion) {
		this.contratacion = contratacion;
	}
	
	public void addLineaOfertaContratacion(LineaOfertaContratacion pLineaOferta){
		pLineaOferta.setOfertaContratacion(this);
		this.listaLineasOfertasContratacion.add(pLineaOferta);
	}
	
	public List<LineaOfertaContratacion> getListaLineasOfertasContratacion() {
		return listaLineasOfertasContratacion;
	}
	public void setListaLineasOfertasContratacion(
			List<LineaOfertaContratacion> listaLineasOfertasContratacion) {
		this.listaLineasOfertasContratacion = listaLineasOfertasContratacion;
	}
	public long getIdOfertaContratacion() {
		return idOfertaContratacion;
	}
	public void setIdOfertaContratacion(long idOfertaContratacion) {
		this.idOfertaContratacion = idOfertaContratacion;
	}
	public String getGarantia() {
		return garantia;
	}
	public void setGarantia(String garantia) {
		this.garantia = garantia;
	}
	public String getComentarios() {
		return comentarios;
	}
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
	public String getPlazo() {
		return plazo;
	}
	public void setPlazo(String plazo) {
		this.plazo = plazo;
	}
	public Proveedor getProveedor() {
		return proveedor;
	}
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	
	public Double getTotal(){
		Double locTotal = new Double(0);
		for (LineaOfertaContratacion cadaLinea : this.getListaLineasOfertasContratacion()){
			locTotal += cadaLinea.getPrecioTotal();
		}
		return locTotal;
	}

	public LineaOfertaContratacion getLineaOfertaPorBien(Bien pBien){
		for(LineaOfertaContratacion cadaLinea : this.listaLineasOfertasContratacion){
			if(cadaLinea.getBien().equals(pBien)){
				return cadaLinea;
			}
		}
		return null;
	}
	
	public String toString(){
		return "Oferta de: "+proveedor;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ (int) (idOfertaContratacion ^ (idOfertaContratacion >>> 32));
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
		OfertaContratacion other = (OfertaContratacion) obj;
		if (this.idOfertaContratacion == -1 && other.idOfertaContratacion == -1){
			return this == other;
		}
		if (idOfertaContratacion != other.idOfertaContratacion)
			return false;
		return true;
	}
}
