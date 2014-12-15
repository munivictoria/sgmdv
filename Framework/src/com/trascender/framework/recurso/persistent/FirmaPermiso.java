package com.trascender.framework.recurso.persistent;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * Firma del permiso otorgado al usuario 
 * @hibernate.class table = "FIRMA_PERMISO_HAB"
 */

@Entity
@Table(name="FIRMA_PERMISO_HAB")
public class FirmaPermiso implements Serializable {

	public static final long serialVersionUID = -4017355733770412911L;

	@Id
	@GeneratedValue(generator="gen_id_firma_permiso_hab",strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="gen_id_firma_permiso_hab",sequenceName="gen_id_firma_permiso_hab",allocationSize = 1)
	@Column(name="ID_FIRMA_PERMISO_HAB")
	private long idFirmaPermiso=-1;
	
	@Column(name="FECHA_HORA",nullable=false)
	private Date fechaHora;
	private String comentario;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_USUARIO", nullable = false)
	private Usuario usuario;
	
	/**
	 * 
	 * @return el usuario al que pertenece la firma del permiso
	 * @hibernate.many-to-one cascade = "none"
	 * column = "ID_USUARIO" not-null = "true"
	 */
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario pUsuario) {
		usuario = pUsuario;
	}
	/**
	 * 
	 * @return Comentario relacionado con la firma
	 * @hibernate.property not-null = "false"
	 */
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String pComentario) {
		comentario = pComentario;
	}
	
	
	/**
	 * 
	 * @return retorna la fecha de la firma
	 * @hibernate.property not-null = "true" column = "FECHA_HORA" 
	 */
	public Date getFechaHora() {
		return fechaHora;
	}
	public void setFechaHora(Date pFecha) {
		fechaHora = pFecha;
	}
	
	/**
	 * 
	 * @return n�mero de identificaci�n del permiso
	 * @hibernate.id generator-class = "increment" column = "ID_FIRMA_PERMISO_HAB"
	 * unsaved-value = "-1"
	 */
	public long getIdFirmaPermiso() {
		return idFirmaPermiso;
	}
	public void setIdFirmaPermiso(long pIdFirmaPermiso) {
		idFirmaPermiso = pIdFirmaPermiso;
	}
	
	public String toString() {	
		return (this.usuario==null)?"":this.usuario.getUser();
	}
	
}
