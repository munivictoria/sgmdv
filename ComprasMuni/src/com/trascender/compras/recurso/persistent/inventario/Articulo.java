package com.trascender.compras.recurso.persistent.inventario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.trascender.framework.recurso.persistent.Area;

@Entity
@Table(name = "ARTICULO")
public class Articulo implements Serializable{
	public static final long serialVersionUID = -1543637512518803197L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_articulo")
	@SequenceGenerator(name = "gen_id_articulo", sequenceName = "gen_id_articulo",allocationSize = 1)
	@Column(name="ID_ARTICULO")
	private long idArticulo = -1;
	
	private String codigo;
	private String nombre;
	private String descripcion;
	
	@Column(name = "FECHA_COMPRA")
	private Date fechaCompra;
	
	@Column(name = "FECHA_PUESTA_SERVICIO")
	private Date fechaPuestaServicio;
	
	@Column(name = "COSTO")
	private Double costo;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "ESTADO_CONTABLE")
	private EstadoContable estadoContable = EstadoContable.NUEVO;

	@Embedded
	private InformacionTecnica informacionTecnica;
	
	@ManyToOne
	@JoinColumn(name = "ID_AREA")
	private Area area;
	
	@OneToMany(mappedBy = "articulo", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PaseArticulo> listaPaseArticulo = new ArrayList<PaseArticulo>();
	
	public enum EstadoContable{
		NUEVO, ACTIVO, INACTIVO
	}
	
	public EstadoContable getEstadoContable() {
		return estadoContable;
	}
	public void setEstadoContable(EstadoContable estadoContable) {
		this.estadoContable = estadoContable;
	}
	public long getIdArticulo() {
		return idArticulo;
	}
	public void setIdArticulo(long idArticulo) {
		this.idArticulo = idArticulo;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Date getFechaCompra() {
		return fechaCompra;
	}
	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}
	public Date getFechaPuestaServicio() {
		return fechaPuestaServicio;
	}
	public void setFechaPuestaServicio(Date fechaPuestaServicio) {
		this.fechaPuestaServicio = fechaPuestaServicio;
	}
	public Double getCosto() {
		return costo;
	}
	public void setCosto(Double costo) {
		this.costo = costo;
	}
	public InformacionTecnica getInformacionTecnica() {
		return informacionTecnica;
	}
	public void setInformacionTecnica(InformacionTecnica informacionTecnica) {
		this.informacionTecnica = informacionTecnica;
	}
	public Area getArea() {
		return area;
	}
	public void setArea(Area area) {
		this.area = area;
	}
	public List<PaseArticulo> getListaPaseArticulo() {
		return listaPaseArticulo;
	}
	public void setListaPaseArticulo(List<PaseArticulo> listaPaseArticulo) {
		this.listaPaseArticulo = listaPaseArticulo;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idArticulo ^ (idArticulo >>> 32));
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
		Articulo other = (Articulo) obj;
		if (idArticulo != other.idArticulo)
			return false;
		return true;
	}
	
	@Override
	public String toString(){
		return "Nombre: "+ nombre + ", codigo: " + codigo;
	}

}
