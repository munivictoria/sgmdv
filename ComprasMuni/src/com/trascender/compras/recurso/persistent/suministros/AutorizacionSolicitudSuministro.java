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

import com.trascender.compras.exception.TrascenderComprasException;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Area;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.util.EntidadTrascender;
import com.trascender.framework.util.LogAuditoria;
import com.trascender.framework.util.Util;

@Entity
@Table(name = "AUTORIZACION_SOL_SUMINISTRO")
public class AutorizacionSolicitudSuministro implements Serializable, EntidadTrascender {

	public static final long serialVersionUID = -8536332488628169513L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_autorizacion_sol_sum")
	@SequenceGenerator(name = "gen_id_autorizacion_sol_sum", sequenceName = "gen_id_autorizacion_sol_sum",allocationSize = 1)
	@Column(name="ID_AUTORIZACION_SOL_SUMINISTRO")
	private long idAutorizacionSolicitudSuministro = -1l;
	
	@OneToMany(mappedBy = "autorizacion", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	private Set<UsuarioAutorizadorSolicitudSuministro> listaUsuarios = new HashSet<UsuarioAutorizadorSolicitudSuministro>();
	
	@OneToMany(mappedBy = "autorizacionSolicitudSuministro", fetch = FetchType.EAGER, 
			cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderBy("orden")
	private List<ReglaFirmaSolicitudSuministro> listaReglasFirma = new ArrayList<ReglaFirmaSolicitudSuministro>();
	
	@ManyToOne
	@JoinColumn(name = "ID_AREA")
	private Area area;
	
	public Set<UsuarioAutorizadorSolSumSuplente> getListaUsuariosSuplentes() {
		Set<UsuarioAutorizadorSolSumSuplente> locListaSuplentes = 
			new HashSet<UsuarioAutorizadorSolSumSuplente>();
		for (UsuarioAutorizadorSolicitudSuministro cadaUsuario : this.listaUsuarios){
			//No usamos instanceof por que UsuarioAutorizadorSolSumSuplente tambien
			//es intancia de UsuarioAutorizadorSolicitudSuministro
			if (cadaUsuario.getClass() == UsuarioAutorizadorSolSumSuplente.class){
				locListaSuplentes.add((UsuarioAutorizadorSolSumSuplente) cadaUsuario);
			}
		}
		return locListaSuplentes;
	}
	
	public void setListaUsuariosSuplentes(
			Set<UsuarioAutorizadorSolSumSuplente> listaUsuariosSuplentes) {
		this.listaUsuarios.removeAll(this.getListaUsuariosSuplentes());
		this.listaUsuarios.addAll(listaUsuariosSuplentes);
	}
	
	public void addUsuarioAutorizador(UsuarioAutorizadorSolicitudSuministro pUsuario ){
		this.listaUsuarios.add(pUsuario);
	}
	
	public void removeUsuarioAutorizador(UsuarioAutorizadorSolicitudSuministro pUsuario){
		this.listaUsuarios.remove(pUsuario);
	}
	
	public long getIdAutorizacionSolicitudSuministro() {
		return idAutorizacionSolicitudSuministro;
	}
	public void setIdAutorizacionSolicitudSuministro(
			long idAutorizacionSolicitudSuministro) {
		this.idAutorizacionSolicitudSuministro = idAutorizacionSolicitudSuministro;
	}
	public Set<UsuarioAutorizadorSolicitudSuministro> getListaUsuarios() {
		Set<UsuarioAutorizadorSolicitudSuministro> locListaUsuarios = 
			new HashSet<UsuarioAutorizadorSolicitudSuministro>();
		for (UsuarioAutorizadorSolicitudSuministro cadaUsuario : this.listaUsuarios){
			//No usamos instanceof por que UsuarioAutorizadorSolSumSuplente tambien
			//es intancia de UsuarioAutorizadorSolicitudSuministro
			if (cadaUsuario.getClass() == UsuarioAutorizadorSolicitudSuministro.class){
				locListaUsuarios.add(cadaUsuario);
			}
		}
		return locListaUsuarios;
	}
	
	public Set<UsuarioAutorizadorSolicitudSuministro> getListaTodosLosUsuarios(){
		return this.listaUsuarios;
	}
	
	public void setListaUsuarios(
			Set<UsuarioAutorizadorSolicitudSuministro> listaUsuarios) {
		this.listaUsuarios.removeAll(this.getListaUsuarios());
		this.listaUsuarios.addAll(listaUsuarios);
	}
	
	/**
	 * Valida que el usuario sea firmante normal o suplente (con fechas incluidas) de esta autorización.
	 * Valida que el usuario pueda firmar el estado actual de la solicitur
	 * @param pUsuario
	 * @return
	 */
	public void validarFirma(Usuario pUsuario, SolicitudSuministro pSolicitud) throws TrascenderException{
		UsuarioAutorizadorSolicitudSuministro locUsuario = null;
		for (UsuarioAutorizadorSolicitudSuministro cadaUsuario : this.getListaUsuarios()){
			if (cadaUsuario.getUsuario().equals(pUsuario)) {
				locUsuario = cadaUsuario;
				break;
			}
		}

		Date hoy = new Date();
		for (UsuarioAutorizadorSolSumSuplente cadaUsuarioSuplente : this
				.getListaUsuariosSuplentes()) {
			if (cadaUsuarioSuplente.getUsuario().equals(pUsuario)
					&& Util.isFechaBetweenNoTima(hoy,
							cadaUsuarioSuplente.getFechaDesde(),
							cadaUsuarioSuplente.getFechaHasta())) {
				locUsuario = cadaUsuarioSuplente.getUsuarioSuplido();
				break;
			} else if(locUsuario != null && cadaUsuarioSuplente.getUsuarioSuplido().equals(locUsuario) && Util.isFechaBetweenNoTima(hoy,
					cadaUsuarioSuplente.getFechaDesde(),
					cadaUsuarioSuplente.getFechaHasta())){
				throw new TrascenderComprasException(930);
			}
		}
	
		if (locUsuario == null){
			throw new TrascenderComprasException(928);
		}
		if (!locUsuario.getListaEstadosFirmables().contains(pSolicitud.getEstado())){
			throw new TrascenderComprasException(929);
		}
	}
	
	public void validarSolicitudSegunReglas(SolicitudSuministro pSolicitud){
		//Lista temporal de los suplentes
		Set<UsuarioAutorizadorSolSumSuplente> locListaSuplentes = this.getListaUsuariosSuplentes();
		List<Usuario> locListaUsuariosFirmaron = new ArrayList<Usuario>();
		forfirmas: for (FirmaPermisoSolicitud cadaFirma : pSolicitud.getListaFirmaPermiso()){
			//Si la firma es de un suplente, coloco que firmo el usuario suplido.
			for (UsuarioAutorizadorSolSumSuplente cadaSuplente : locListaSuplentes){
				if (cadaSuplente.getUsuario().equals(cadaFirma.getFirmaPermiso().getUsuario())
						&& Util.isFechaBetweenNoTima(
								cadaFirma.getFirmaPermiso().getFechaHora(),	cadaSuplente.getFechaDesde(), cadaSuplente.getFechaHasta())){
					locListaUsuariosFirmaron.add(cadaSuplente.getUsuarioSuplido().getUsuario());
					continue forfirmas;
				}
			}
			locListaUsuariosFirmaron.add(cadaFirma.getFirmaPermiso().getUsuario());
		}
		//Ya vienen ordenadas desde la base, si se cumple la regla, le seteo el estado correcto
		for (ReglaFirmaSolicitudSuministro cadaRegla : this.listaReglasFirma){
			boolean flagTodos = true;
			for (Iterator<UsuarioAutorizadorSolicitudSuministro> iterator = 
					cadaRegla.getListaUsuarios().iterator(); 
					iterator.hasNext() && flagTodos;) {
				if (!locListaUsuariosFirmaron.contains(iterator.next().getUsuario())){
					flagTodos = false;
				}
			}
			
			if(pSolicitud.isUrgente() && cadaRegla.isUrgente() && flagTodos){
				pSolicitud.setEstado(cadaRegla.getEstado());
			} else if(flagTodos && !cadaRegla.isUrgente()){
				pSolicitud.setEstado(cadaRegla.getEstado());
			}
		}
	}
	
	public Area getArea() {
		return area;
	}
	public void setArea(Area area) {
		this.area = area;
	}
	public List<ReglaFirmaSolicitudSuministro> getListaReglasFirma() {
		return listaReglasFirma;
	}
	public void setListaReglasFirma(
			List<ReglaFirmaSolicitudSuministro> listaReglasFirma) {
		this.listaReglasFirma = listaReglasFirma;
	}
	@Override
	public String toString(){
		String locRetorno = (this.area != null ? "["+this.area.toString()+"]" : "")
				+this.getStringUsuarios();
		return locRetorno;
	}
	
	public String getStringUsuarios(){
		String locResultado = "";
		for (Iterator<UsuarioAutorizadorSolicitudSuministro> iterator = 
			this.getListaUsuarios().iterator(); iterator.hasNext();) {
			UsuarioAutorizadorSolicitudSuministro cadaUsuario = iterator.next();
			locResultado += cadaUsuario.getUsuario().getUser();
			if (iterator.hasNext()) locResultado += ", ";
		}
		return locResultado;
	}
	
	public void setStringUsuarios(String pString){
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ (int) (idAutorizacionSolicitudSuministro ^ (idAutorizacionSolicitudSuministro >>> 32));
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
		AutorizacionSolicitudSuministro other = (AutorizacionSolicitudSuministro) obj;
		if (idAutorizacionSolicitudSuministro != other.idAutorizacionSolicitudSuministro)
			return false;
		return true;
	}
	
	// ********************************************************************************************************************************/
		// AUDITORIA

		@Transient
		private long llaveUsuarioAuditoria;
		@Transient
		private String comentarioAuditoria;

		@OrderBy(value = "fecha")
		@Where(clause = "id_recurso = " + serialVersionUID)
		@OneToMany(mappedBy = "idEntidad", cascade = CascadeType.REMOVE, orphanRemoval = true)
		private List<LogAuditoria> listaLogsAuditoria = new ArrayList<LogAuditoria>();
		
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

		public long getSerialVersionUID() {
			return serialVersionUID;
		}

		public long getIdEntidad() {
			return this.idAutorizacionSolicitudSuministro;
		}

		public String getNombrePropiedadId() {
			return "idAutorizacionSolicitudSuministro";
		}

		public boolean isAuditable() {
			return true;
		}
}
