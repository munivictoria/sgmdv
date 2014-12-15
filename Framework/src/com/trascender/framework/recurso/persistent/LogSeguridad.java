package com.trascender.framework.recurso.persistent;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Representa una accion echa al sistema
 * @hibernate.class table = "LOG_SEGURIDAD"
 */
@Entity
@Table(name="LOG_SEGURIDAD")
public class LogSeguridad implements Serializable {

	private static final long serialVersionUID = 3401657754636314439L;
	
	@Id
	@GeneratedValue(generator="GEN_ID_LOG_SEGURIDAD")
	@SequenceGenerator(name="GEN_ID_LOG_SEGURIDAD",sequenceName="GEN_ID_LOG_SEGURIDAD",allocationSize = 1)
	@Column(name="ID_LOG_SEGURIDAD")
	private long idLogSeguridad;
	
	@Column(name="NOMBRE_USUARIO")
	private String nombreUsuario;
	
	@Column(name="PERSONA_USUARIO",nullable=false)
	private String personaUsuario;
	
	@Column(name="FECHA_HORA")
	private Date fechaHora;
	
	@Column(name="REMOTE_ADDRESS")
	private String remoteAddress;
	
	@Column(name="REMOTE_HOST", nullable=false)
	private String remoteHost;
	
	@Column(name="REMOTE_PORT", nullable=false)
	private String remotePort;
	
	@Column(name="NOMBRE_RECURSO")
	private String nombreRecurso;
	
	@Enumerated(EnumType.STRING)
	private Permiso.Accion accion;
	
	private boolean habilitado;
	
	/**
	 * 
	 * @hibernate.property column = "ACCION" type = "com.trascender.framework.util.enumerations.PermisoAccion"
	 */
	public Permiso.Accion getAccion() {
		return accion;
	}
	public void setAccion(Permiso.Accion pAccion) {
		accion = pAccion;
	}
	
	/**
	 * 
	 * @hibernate.property column = "FECHA_HORA"
	 */
	public Date getFechaHora() {
		return fechaHora;
	}
	public void setFechaHora(Date pFechaHora) {
		fechaHora = pFechaHora;
	}
	
	/**
	 * 
	 * @hibernate.property column = "HABILITADO" 
	 */
	public boolean isHabilitado() {
		return habilitado;
	}
	public void setHabilitado(boolean pHabilitado) {
		habilitado = pHabilitado;
	}
	
	/**
	 * 
	 * @hibernate.id column = "ID_LOG_SEGURIDAD" generator-class = "increment"
	 */
	public long getIdLogSeguridad() {
		return idLogSeguridad;
	}
	public void setIdLogSeguridad(long pIdLogSeguridad) {
		idLogSeguridad = pIdLogSeguridad;
	}
	
	/**
	 * 
	 * @hibernate.property column = "NOMBRE_RECURSO"
	 */
	public String getNombreRecurso() {
		return nombreRecurso;
	}
	public void setNombreRecurso(String pNombreRecurso) {
		nombreRecurso = pNombreRecurso;
	}
	
	/**
	 * 
	 * @hibernate.property column = "NOMBRE_USUARIO" not-null = "true"
	 */
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String pNombreUsuario) {
		nombreUsuario = pNombreUsuario;
	}
	
	
	/**
	 * 
	 * @hibernate.property column = "PERSONA_USUARIO" not-null = "false"
	 */
	public String getPersonaUsuario() {
		return personaUsuario;
	}
	public void setPersonaUsuario(String pPersonaUsuario) {
		personaUsuario = pPersonaUsuario;
	}
	
	
	/**
	 * 
	 * @hibernate.property column = "REMOTE_ADDRESS"
	 */
	public String getRemoteAddress() {
		return remoteAddress;
	}
	public void setRemoteAddress(String pRemoteAddress) {
		remoteAddress = pRemoteAddress;
	}

	/**
	 * 
	 * @hibernate.property column = "REMOTE_HOST" not-null = "false"
	 */
	public String getRemoteHost() {
		return remoteHost;
	}
	public void setRemoteHost(String pRemoteHost) {
		remoteHost = pRemoteHost;
	}
	
	/**
	 * 
	 * @hibernate.property column = "REMOTE_PORT" not-null = "false"
	 */
	public String getRemotePort() {
		return remotePort;
	}
	public void setRemotePort(String pRemotePort) {
		remotePort = pRemotePort;
	}
	

}
