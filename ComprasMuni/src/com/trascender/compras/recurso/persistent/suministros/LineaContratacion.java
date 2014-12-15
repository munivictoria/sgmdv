package com.trascender.compras.recurso.persistent.suministros;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;


@Entity
@Table(name = "LINEA_CONTRATACION")
public class LineaContratacion implements Serializable{
	public static final long serialVersionUID = -2398384407096954118L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_linea_contratacion")
	@SequenceGenerator(name = "gen_id_linea_contratacion", sequenceName = "gen_id_linea_contratacion",allocationSize = 1)
	@Column(name="ID_LINEA_CONTRATACION")
	private long idLineaContratacion = -1;
	
	private String descripcion;
	
	private Double cantidad;
	
	@ManyToOne(cascade = CascadeType.MERGE) //Merge para updatear el valor referencial.
	@JoinColumn(name = "ID_BIEN")
	private Bien bien;
	
	@ManyToOne
	@JoinColumn(name = "ID_CONTRATACION")
	private Contratacion contratacion;
	
	@ManyToOne
	@JoinColumn(name="ID_LINEA_OFERTA_CONTRATACION")
	private LineaOfertaContratacion lineaOfertaContratacionAdjudicada;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "RELA_LINEA_CONTRATACION_LINEA_SOL_SUM", joinColumns = @JoinColumn(name = "ID_LINEA_CONTRATACION"), inverseJoinColumns = @JoinColumn(name = "ID_LINEA_SOLICITUD_SUMINISTRO"))
	private List<LineaSolicitudSuministro> listaLineasSolicitudSuministro;
	
	public LineaContratacion(){
		this.listaLineasSolicitudSuministro = new ArrayList<LineaSolicitudSuministro>();
	}
	
	public void addLineaSolicitudSuministro(LineaSolicitudSuministro pLineaSolicitud){
		pLineaSolicitud.getListaLineaContratacion().add(this);
		this.getListaLineasSolicitudSuministro().add(pLineaSolicitud);
	}
	
	public List<LineaSolicitudSuministro> getListaLineasSolicitudSuministro() {
		return listaLineasSolicitudSuministro;
	}
	public void setListaLineasSolicitudSuministro(
			List<LineaSolicitudSuministro> listaLineasSolicitudSuministro) {
		this.listaLineasSolicitudSuministro = listaLineasSolicitudSuministro;
	}
	public Bien getBien() {
		return bien;
	}
	public void setBien(Bien bien) {
		this.bien = bien;
	}
	public long getIdLineaContratacion() {
		return idLineaContratacion;
	}
	public void setIdLineaContratacion(long idLineaContratacion) {
		this.idLineaContratacion = idLineaContratacion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Double getCantidad() {
		return cantidad;
	}
	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}
	public Contratacion getContratacion() {
		return contratacion;
	}
	public void setContratacion(Contratacion contratacion) {
		this.contratacion = contratacion;
	}
	public LineaOfertaContratacion getLineaOfertaContratacionAdjudicada() {
		return lineaOfertaContratacionAdjudicada;
	}
	public void setLineaOfertaContratacionAdjudicada(
			LineaOfertaContratacion lineaOfertaContratacionAdjudicada) {
		this.lineaOfertaContratacionAdjudicada = lineaOfertaContratacionAdjudicada;
	}
	
	public void setValorReferencial(Double pValor){
		if (this.bien != null){
			this.bien.setValorReferencial(pValor);
		}
	}
	
	public Double getValorReferencial(){
		if (this.getBien() != null && this.getBien().getValorReferencial() != null){
			return this.getBien().getValorReferencial();
		}
		return 0D;
	}
	
	public Double getMontoTotalReferencial(){
		if (this.getBien() != null 
				&& this.getBien().getValorReferencial() != null
				&& this.getCantidad() != null){
			return this.getCantidad() * this.getValorReferencial();
		}
		return 0D;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ (int) (idLineaContratacion ^ (idLineaContratacion>>> 32));
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
		LineaContratacion other = (LineaContratacion) obj;
		if (idLineaContratacion != other.idLineaContratacion)
			return false;
		return true;
	}
	
	public String toString(){
		return "";
	}
	

}
