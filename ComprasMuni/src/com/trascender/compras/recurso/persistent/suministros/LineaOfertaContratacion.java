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
@Table(name = "LINEA_OFERTA_CONTRATACION")
public class LineaOfertaContratacion implements Serializable{
	private static final long serialVersionUID = 2730125704679656543L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_linea_oferta_contratacion")
	@SequenceGenerator(name = "gen_id_linea_oferta_contratacion", sequenceName = "gen_id_linea_oferta_contratacion",allocationSize = 1)
	@Column(name="ID_LINEA_OFERTA_CONTRATACION")
	private long idLineaOfertaContratacion = -1;
	
	@Column(name = "PRECIO_UNITARIO")
	private Double precioUnitario;
	
	@Column(name = "PRECIO_TOTAL")
	private Double precioTotal;
	
	private String comentarios;
	
	@ManyToOne
	@JoinColumn(name = "ID_OFERTA_CONTRATACION")
	private OfertaContratacion ofertaContratacion;
	
	@ManyToOne
	@JoinColumn(name = "ID_LINEA_CONTRATACION")
	private LineaContratacion lineaContratacion;
	
	public Double getPrecioTotal() {
		if (precioUnitario != null && lineaContratacion != null && lineaContratacion.getCantidad() != null){
			return precioUnitario * lineaContratacion.getCantidad();
		}
		else return 0d;
	}
	public void setPrecioTotal(Double precioTotal) {
		this.precioTotal = precioTotal;
	}
	
	public long getIdLineaOfertaContratacion() {
		return idLineaOfertaContratacion;
	}

	public void setIdLineaOfertaContratacion(long idLineaOfertaContratacion) {
		this.idLineaOfertaContratacion = idLineaOfertaContratacion;
	}

	public Double getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(Double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public OfertaContratacion getOfertaContratacion() {
		return ofertaContratacion;
	}

	public void setOfertaContratacion(OfertaContratacion ofertaContratacion) {
		this.ofertaContratacion = ofertaContratacion;
	}

	public LineaContratacion getLineaContratacion() {
		return lineaContratacion;
	}

	public void setLineaContratacion(LineaContratacion lineaContratacion) {
		this.lineaContratacion = lineaContratacion;
	}
	
	//Para la interfaz
	public Bien getBien(){
		return this.lineaContratacion.getBien();
	}
	
	public Unidad getUnidad(){
		return this.lineaContratacion.getBien().getUnidad();
	}
	
	public Double getCantidadRenglon(){
		return lineaContratacion.getCantidad();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ (int) (idLineaOfertaContratacion ^ (idLineaOfertaContratacion >>> 32));
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
		LineaOfertaContratacion other = (LineaOfertaContratacion) obj;
		if (this.idLineaOfertaContratacion == -1 && other.idLineaOfertaContratacion == -1)
			return this == other;
		if (idLineaOfertaContratacion != other.idLineaOfertaContratacion)
			return false;
		return true;
	}
	
	public String toString(){
		return this.getPrecioUnitario()+" ["+this.getOfertaContratacion().getProveedor()+"]";
	}
	
}
