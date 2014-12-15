package com.trascender.compras.recurso.persistent.suministros;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Where;

import com.trascender.compras.recurso.persistent.inventario.LineaMovimientoMercaderia;
import com.trascender.framework.recurso.persistent.Area;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.util.EntidadTrascender;
import com.trascender.framework.util.LogAuditoria;
import com.trascender.framework.util.anotations.NoAuditable;

@Entity
@Table(name = "SOLICITUD_SUMINISTRO")
public class SolicitudSuministro implements Serializable, EntidadTrascender{

	public static final long serialVersionUID = -5279532128176911209L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_solicitud_suministro")
	@SequenceGenerator(name = "gen_id_solicitud_suministro", sequenceName = "gen_id_solicitud_suministro",allocationSize = 1)
	@Column(name="ID_SOLICITUD_SUMINISTRO")
	private long idSolicitudSuministro = -1;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_USUARIO")
	private Usuario usuario;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_AREA")
	private Area area;

	private String descripcion;

	private Integer numero;

	@NoAuditable//No cambia nunca
	@Column(name = "FECHA_EMISION")
	private Date fechaEmision;
	
	private boolean urgente = false;

	@OneToMany(mappedBy = "solicitudSuministro", cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH}, orphanRemoval = true)
	private Set<FirmaPermisoSolicitud> listaFirmaPermiso = new HashSet<FirmaPermisoSolicitud>();

	@OneToMany(mappedBy = "solicitudSuministro", fetch = FetchType.EAGER, 
			cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<LineaSolicitudSuministro> listaLineaSolSuministro = new HashSet<LineaSolicitudSuministro>();

	@ManyToOne
	@JoinColumn(name = "ID_ESTADO_SOL_SUM")
	private EstadoSolicitudSuministro estado;

	@Column(name = "COMENTARIO_FINALIZACION")
	private String comentarioFinalizacion;

	@Transient
	private String bienes; 

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "solicitudSuministro", orphanRemoval = true)
	private List<PresupuestoSolicitudSuministro> listaPresupuestos = new ArrayList<PresupuestoSolicitudSuministro>();

	@OrderBy(value="fecha")
	@Where(clause = "id_recurso = " + serialVersionUID)
	@OneToMany(mappedBy = "idEntidad", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<LogAuditoria> listaLogsAuditoria = new ArrayList<LogAuditoria>();

	public List<PresupuestoSolicitudSuministro> getListaPresupuestos() {
		return listaPresupuestos;
	}

	public void setListaPresupuestos(
			List<PresupuestoSolicitudSuministro> listaPresupuestos) {
		this.listaPresupuestos = listaPresupuestos;
	}

	public void addPresupuestoSolicitudSuministro(PresupuestoSolicitudSuministro pPresupuesto){
		pPresupuesto.setSolicitudSuministro(this);
		this.listaPresupuestos.add(pPresupuesto);
	}

	public EstadoSolicitudSuministro getEstado() {
		return estado;
	}

	public void setEstado(EstadoSolicitudSuministro estado) {
		this.estado = estado;
	}

	public String getStringEstado(){
		return this.estado.getNombre();
	}

	public String getComentarioFinalizacion() {
		return comentarioFinalizacion;
	}

	public void setComentarioFinalizacion(String comentarioFinalizacion) {
		this.comentarioFinalizacion = comentarioFinalizacion;
	}

	@Override
	public String toString(){
		return "Solicitud de Suministro ["+this.numero+"]";
	}

	public Usuario getUsuario() {

		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Date getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public Set<FirmaPermisoSolicitud> getListaFirmaPermiso() {
		return listaFirmaPermiso;
	}

	public void setListaFirmaPermiso(Set<FirmaPermisoSolicitud> listaFirmaPermiso) {
		this.listaFirmaPermiso = listaFirmaPermiso;
	}

	public long getIdSolicitudSuministro() {
		return idSolicitudSuministro;
	}

	public void setIdSolicitudSuministro(long idSolicitudSuministro) {
		this.idSolicitudSuministro = idSolicitudSuministro;
	}

	public Set<LineaSolicitudSuministro> getListaLineaSolSuministro() {
		return listaLineaSolSuministro;
	}

	public void setListaLineaSolSuministro(
			Set<LineaSolicitudSuministro> listaLineaSolSuministro) {
		this.listaLineaSolSuministro = listaLineaSolSuministro;
	}

	public List<LineaMovimientoMercaderia> getListaLineasMovimientoMercaderia(){
		List<LineaMovimientoMercaderia> locListaLineasMovimiento = new ArrayList<LineaMovimientoMercaderia>();
		for (LineaSolicitudSuministro cadaLinea : this.listaLineaSolSuministro){
			locListaLineasMovimiento.addAll(cadaLinea.getListaLineasMovimientosMercaderia());
		}
		return locListaLineasMovimiento;
	}

	public boolean yaFirmo(Usuario pUsuario){
		boolean encontrado = false;
		for (Iterator<FirmaPermisoSolicitud> iterator = this.getListaFirmaPermiso().iterator(); iterator.hasNext() && !encontrado;) {
			FirmaPermisoSolicitud cadaFirmaPermiso = iterator.next();
			encontrado = cadaFirmaPermiso.getFirmaPermiso().getUsuario().equals(pUsuario); 
		}
		return encontrado;
	}

	public String getBienes() {
		String locBienes = new String();
		for (Iterator<LineaSolicitudSuministro> iterator = 
				this.getListaLineaSolSuministro().iterator(); iterator.hasNext();) {
			LineaSolicitudSuministro cadaLinea = iterator.next();
			locBienes += cadaLinea.getBien().getNombre();
			if (iterator.hasNext()) locBienes += ", ";
		}
		this.bienes = locBienes;
		return bienes;
	}

	public void setBienes(String bienes) {
		this.bienes = bienes;
	}
	
	public boolean isUrgente() {
		return urgente;
	}

	public void setUrgente(boolean urgente) {
		this.urgente = urgente;
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
				+ (int) (idSolicitudSuministro ^ (idSolicitudSuministro >>> 32));
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
		SolicitudSuministro other = (SolicitudSuministro) obj;
		if (idSolicitudSuministro != other.idSolicitudSuministro)
			return false;
		return true;
	}

	//*********************************************************************************************************************************************************************************/
	// AUDITORIA

	@Transient
	private long llaveUsuarioAuditoria;
	@Transient
	private String comentarioAuditoria;

	public List<LogAuditoria> getListaLogsAuditoria() {
		return listaLogsAuditoria;
	}

	public void setListaLogsAuditoria(List<LogAuditoria> pListaLogsAuditoria) {
		this.listaLogsAuditoria = pListaLogsAuditoria;
	}

	public long getLlaveUsuarioAuditoria() {
		return llaveUsuarioAuditoria;
	}

	public void setLlaveUsuarioAuditoria(long llaveUsuarioAuditoria) {
		this.llaveUsuarioAuditoria = llaveUsuarioAuditoria;
	}

	public String getComentarioAuditoria() {
		return comentarioAuditoria;
	}

	public void setComentarioAuditoria(String comentarioAuditoria) {
		this.comentarioAuditoria = comentarioAuditoria;
	}

	public long getIdEntidad() {
		return this.idSolicitudSuministro;
	}

	public long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getNombrePropiedadId() {
		return "idSolicitudSuministro";
	}

	public boolean isAuditable() {
		return true;
	}
}
