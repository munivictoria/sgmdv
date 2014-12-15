package com.trascender.compras.recurso.persistent.suministros;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table(name = "RELA_USR_AUTOR_SOL_SUM")
@PrimaryKeyJoinColumn(name = "ID_USUARIO_AUTORIZADOR")
public class UsuarioAutorizadorSolicitudSuministro extends UsuarioAutorizador {

	private static final long serialVersionUID = -4718654686264377854L;
	
	@ManyToOne
	@JoinColumn(name = "ID_AUTORIZACION_SOL_SUMINISTRO")
	protected AutorizacionSolicitudSuministro autorizacion;
	
	@ManyToMany
	@JoinTable(name = "rela_aut_sol_sum_estado_sol_sum", 
			joinColumns = @JoinColumn(name = "ID_USUARIO_AUTORIZADOR"),
			inverseJoinColumns = @JoinColumn(name = "ID_ESTADO_SOL_SUM"))
	protected List<EstadoSolicitudSuministro> listaEstadosFirmables;
	
	@ManyToMany
	@JoinTable(name = "rela_usuario_autorizador_estado_sol_sum_finalizable", 
			joinColumns = @JoinColumn(name = "ID_USUARIO_AUTORIZADOR"),
			inverseJoinColumns = @JoinColumn(name = "ID_ESTADO_SOL_SUM"))
	protected List<EstadoSolicitudSuministro> listaEstadosFinalizables;
	
	@ManyToMany
	@JoinTable(name = "rela_usuario_autorizador_estado_sol_sum_finalizacion", 
			joinColumns = @JoinColumn(name = "ID_USUARIO_AUTORIZADOR"),
			inverseJoinColumns = @JoinColumn(name = "ID_ESTADO_SOL_SUM"))
	protected List<EstadoSolicitudSuministro> listaEstadosFinalizacion;
	
	@Column(name="OPERA_URGENTES")
	private boolean operaUrgentes = false;
	
	public String getStringEstadosFirmables(){
		String locResultado = "";
		for (Iterator<EstadoSolicitudSuministro> iterator = 
			this.listaEstadosFirmables.iterator(); iterator.hasNext();) {
			EstadoSolicitudSuministro cadaEstado = iterator.next();
			locResultado += cadaEstado.getNombre();
			if (iterator.hasNext()) locResultado += ", ";
		}
		return locResultado;
	}
	
	public UsuarioAutorizadorSolicitudSuministro(){
		this.listaEstadosFirmables = new ArrayList<EstadoSolicitudSuministro>();
	}
	
	public List<EstadoSolicitudSuministro> getListaEstadosFirmables() {
		return listaEstadosFirmables;
	}

	public void setListaEstadosFirmables(
			List<EstadoSolicitudSuministro> listaEstadosFirmables) {
		this.listaEstadosFirmables = listaEstadosFirmables;
	}

	public AutorizacionSolicitudSuministro getAutorizacion() {
		return autorizacion;
	}
	public void setAutorizacion(AutorizacionSolicitudSuministro autorizacion) {
		this.autorizacion = autorizacion;
	}

	public List<EstadoSolicitudSuministro> getListaEstadosFinalizables() {
		return listaEstadosFinalizables;
	}

	public void setListaEstadosFinalizables(
			List<EstadoSolicitudSuministro> listaEstadosFinalizables) {
		this.listaEstadosFinalizables = listaEstadosFinalizables;
	}

	public List<EstadoSolicitudSuministro> getListaEstadosFinalizacion() {
		return listaEstadosFinalizacion;
	}

	public void setListaEstadosFinalizacion(
			List<EstadoSolicitudSuministro> listaEstadosFinalizacion) {
		this.listaEstadosFinalizacion = listaEstadosFinalizacion;
	}
	
	public boolean isOperaUrgentes() {
		return operaUrgentes;
	}

	public void setOperaUrgentes(boolean operaUrgentes) {
		this.operaUrgentes = operaUrgentes;
	}
	
	public String getStringOperaUrgentes(){
		return this.isOperaUrgentes()?"Si":"No";
	}

	@Override
	public boolean equals(Object obj) {
			if (this == obj)
		        return true;
		if (obj == null)
		        return false;
		if (getClass() != obj.getClass())
		        return false;
		UsuarioAutorizadorSolicitudSuministro other = (UsuarioAutorizadorSolicitudSuministro) obj;
		if (this.idUsuarioAutorizador == -1 && other.idUsuarioAutorizador == -1){
		        return this == other;
		}
		if (idUsuarioAutorizador != other.idUsuarioAutorizador)
		        return false;
		return true;
	}
}
