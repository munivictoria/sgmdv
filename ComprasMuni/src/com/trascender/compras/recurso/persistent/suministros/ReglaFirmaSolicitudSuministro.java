package com.trascender.compras.recurso.persistent.suministros;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "REGLA_FIRMA_SOL_SUM")
public class ReglaFirmaSolicitudSuministro implements Serializable, Comparable<ReglaFirmaSolicitudSuministro>{
	public static final long serialVersionUID = 5870774102140910518L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_regla_firma_sol_sum")
	@SequenceGenerator(name = "gen_id_regla_firma_sol_sum", sequenceName = "gen_id_regla_firma_sol_sum",allocationSize = 1)
	@Column(name="ID_REGLA_FIRMA_SOL_SUM")
	private long idReglaFirmaSolicitudSuministro = -1;
	
	private Integer orden;
	
	@ManyToOne
	@JoinColumn(name = "ID_AUTORIZACION_SOL_SUM")
	private AutorizacionSolicitudSuministro autorizacionSolicitudSuministro;
	
	@ManyToMany
	@JoinTable(name = "RELA_REGLA_FIRMA_USUARIO", joinColumns = @JoinColumn(name = "id_regla_firma_sol_sum"),
			inverseJoinColumns = @JoinColumn(name = "id_usuario_autorizador"))
	private List<UsuarioAutorizadorSolicitudSuministro> listaUsuarios;
	
	@ManyToOne
	@JoinColumn(name = "ID_ESTADO_SOL_SUM")
	private EstadoSolicitudSuministro estado;
	
	private boolean urgente = false;
	
	public ReglaFirmaSolicitudSuministro(){
		this.listaUsuarios = new ArrayList<UsuarioAutorizadorSolicitudSuministro>();
	}
	
	public long getIdReglaFirmaSolicitudSuministro() {
		return idReglaFirmaSolicitudSuministro;
	}
	public void setIdReglaFirmaSolicitudSuministro(
			long idReglaFirmaSolicitudSuministro) {
		this.idReglaFirmaSolicitudSuministro = idReglaFirmaSolicitudSuministro;
	}
	public AutorizacionSolicitudSuministro getAutorizacionSolicitudSuministro() {
		return autorizacionSolicitudSuministro;
	}
	public void setAutorizacionSolicitudSuministro(
			AutorizacionSolicitudSuministro autorizacionSolicitudSuministro) {
		this.autorizacionSolicitudSuministro = autorizacionSolicitudSuministro;
	}
	public Integer getOrden() {
		return orden;
	}
	public void setOrden(Integer orden) {
		this.orden = orden;
	}
	public List<UsuarioAutorizadorSolicitudSuministro> getListaUsuarios() {
		return listaUsuarios;
	}
	public void setListaUsuarios(
			List<UsuarioAutorizadorSolicitudSuministro> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}
	public EstadoSolicitudSuministro getEstado() {
		return estado;
	}
	public void setEstado(EstadoSolicitudSuministro estado) {
		this.estado = estado;
	}
	public int compareTo(ReglaFirmaSolicitudSuministro o) {
		return this.orden.compareTo(o.orden);
	}
	
	public String toString(){
		return this.listaUsuarios + " > " + this.getEstado();
	}
	
	public boolean isUrgente() {
		return urgente;
	}

	public void setUrgente(boolean urgente) {
		this.urgente = urgente;
	}

	public String getStringListaUsuarios() {
		String locString = new String();
		for (Iterator<UsuarioAutorizadorSolicitudSuministro> iterator = 
			this.getListaUsuarios().iterator(); iterator.hasNext();) {
			UsuarioAutorizadorSolicitudSuministro cadaUsuario = iterator.next();
			locString += cadaUsuario.getUsuario();
			if (iterator.hasNext()) locString += ", ";
		}
		return locString;
	}
	
	public String getStringUrgente(){
		return this.isUrgente()?"Si":"No";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ (int) (idReglaFirmaSolicitudSuministro ^ (idReglaFirmaSolicitudSuministro >>> 32));
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
		ReglaFirmaSolicitudSuministro other = (ReglaFirmaSolicitudSuministro) obj;
		if (idReglaFirmaSolicitudSuministro != other.idReglaFirmaSolicitudSuministro)
			return false;
		return true;
	}
	
}
