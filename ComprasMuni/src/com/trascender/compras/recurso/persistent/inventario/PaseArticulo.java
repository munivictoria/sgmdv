package com.trascender.compras.recurso.persistent.inventario;

import java.io.Serializable;
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

import com.trascender.framework.recurso.persistent.Area;
import com.trascender.framework.recurso.persistent.Usuario;

@Entity
@Table(name = "PASE_ARTICULO")
public class PaseArticulo implements Serializable{
	private static final long serialVersionUID = -6989768298282699728L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_pase_articulo")
	@SequenceGenerator(name = "gen_id_pase_articulo", sequenceName = "gen_id_pase_articulo",allocationSize = 1)
	@Column(name="ID_PASE_ARTICULO")
	private long idPaseArticulo = -1;
	
	private Date fecha;
	private String comentario;
	
	@ManyToOne
	@JoinColumn(name = "ID_USUARIO")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name = "ID_AREA_ORIGEN")
	private Area areaOrigen;
	
	@ManyToOne
	@JoinColumn(name = "ID_AREA_DESTINO")
	private Area areaDestino;
	
	@ManyToOne
	@JoinColumn(name = "ID_ARTICULO")
	private Articulo articulo;
	
	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public long getIdPaseArticulo() {
		return idPaseArticulo;
	}

	public void setIdPaseArticulo(long idPaseArticulo) {
		this.idPaseArticulo = idPaseArticulo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Area getAreaOrigen() {
		return areaOrigen;
	}

	public void setAreaOrigen(Area areaOrigen) {
		this.areaOrigen = areaOrigen;
	}

	public Area getAreaDestino() {
		return areaDestino;
	}

	public void setAreaDestino(Area areaDestino) {
		this.areaDestino = areaDestino;
	}

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

}
