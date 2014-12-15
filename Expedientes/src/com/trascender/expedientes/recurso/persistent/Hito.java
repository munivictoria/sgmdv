package com.trascender.expedientes.recurso.persistent;

import java.io.Serializable;
import java.text.SimpleDateFormat;
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

import com.trascender.framework.recurso.persistent.Usuario;

@Entity
@Table(name = "EXP_HITO")
public class Hito implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1131739973990970786L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_id_exp_hito")
	@SequenceGenerator(name = "gen_id_exp_hito", sequenceName = "gen_id_exp_hito",
			allocationSize = 1)
	@Column(name = "ID_HITO")
	protected long idHito = -1l;

	@Column(name = "NOMBRE", nullable = false)
	protected String nombre;

	@Column(name = "DESCRIPCION", nullable = false)
	protected String descripcion;

	@Column(name = "FECHA")
	protected Date fecha;

	@ManyToOne
	@JoinColumn(name = "ID_USUARIO")
	protected Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "ID_NODOEXPEDIENTE")
	protected NodoExpediente nodoExpediente;

	@Column(name = "COMENTARIO")
	protected String comentario;
	
	public long getIdHito() {
		return idHito;
	}

	public void setIdHito(long idHito) {
		this.idHito = idHito;
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

	public NodoExpediente getNodoExpediente() {
		return nodoExpediente;
	}

	public void setNodoExpediente(NodoExpediente nodoExpediente) {
		this.nodoExpediente = nodoExpediente;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	@Override
	public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String comentario = (this.getComentario() != null && this.getComentario().length() > 0) ? " [" + this.getComentario() + "]" : "";
        
		return  sdf.format(this.fecha) + " => " + this.usuario + " - " + this.descripcion + comentario;
	}

	public String getStringHito() {
		return this.toString();
	}
}
