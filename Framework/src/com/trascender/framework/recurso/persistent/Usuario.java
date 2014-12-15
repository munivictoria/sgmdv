package com.trascender.framework.recurso.persistent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Where;

import com.trascender.framework.exception.TrascenderFrameworkException;
import com.trascender.framework.recurso.persistent.referencia.PersonaFisicaRfr;
import com.trascender.framework.util.EntidadTrascender;
import com.trascender.framework.util.LogAuditoria;
import com.trascender.framework.util.anotations.NoAuditable;


/**
 * Representa un usuario del sistema
 * @hibernate.class table = "USUARIO"
 */

@Entity
@Table(name="USUARIO")
public class Usuario implements Serializable, EntidadTrascender {

	static public final long serialVersionUID = -4223499662704844380L;
	
	@Id
	@GeneratedValue(generator="gen_id_usuario",strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="gen_id_usuario", sequenceName="gen_id_usuario",allocationSize = 1)
	@Column(name="ID_USUARIO")
	private long idUsuario=-1;
	
	@Column(name="USUARIO", nullable=false)
	private String user;
	
	@NoAuditable
	@Column(name="CLAVE", nullable=false)
	private String password;

	@Enumerated(EnumType.STRING)
	private Estado estado = Estado.ACTIVO;
	
	@Transient
	private long keyGen;
		
	@Transient
	private List<FirmaPermiso> firmaPermiso;
	@Transient
	private Map<Long,Permiso> permisos;
	
	@Transient
//	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="ID_PERSONA")
	private PersonaFisicaRfr personaFisicaRfr;
	
	@ManyToOne
	@JoinColumn(name="ID_PERSONA")
	private PersonaFisica personaFisica;
	
	//Con esta columna recuperamos la persona fisica asociada al usuario.
//	@Column(name = "ID_PERSONA")
	@Transient
	private Long idPersonaFisica = -1L;
	
	@ManyToMany(fetch=FetchType.LAZY,cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "RELA_ROLES_USUARIO", joinColumns = @JoinColumn(name = "ID_USUARIO"), inverseJoinColumns = @JoinColumn(name = "ID_ROL"))
	private Set<Rol> listaRoles;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name = "RELA_AREAS_USUARIO", joinColumns = @JoinColumn(name = "ID_USUARIO"), inverseJoinColumns = @JoinColumn(name = "ID_AREA"))
	private List<Area> listaAreas;
	
	/**
	 * Estados posibles {ACTIVO,ELIMINADO}
	 * @author jsantacruz
	 */
	public enum Estado{ACTIVO,ELIMINADO;
		@Override
		public String toString() {
			return com.trascender.framework.util.Util.capitalizeEnumName(super.toString());
		}

	};
	
	public List<FirmaPermiso> getFirmaPermiso() {
		return firmaPermiso;
	}
	public void setFirmaPermiso(List<FirmaPermiso> pFirmaPermiso) {
		firmaPermiso = pFirmaPermiso;
	}
	
	/**
	 * @return n�mero de identificaci�n �nica del usuario
	 */
	public long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(long pIdUsuario) {
		idUsuario = pIdUsuario;
	}
	
	/**
	 * @return llave generada del usuario una vez ingresado al sistema
	 */
	public long getKeyGen() {
		return keyGen;
	}
	public void setKeyGen(long pKeyGen) {
		keyGen = pKeyGen;
	}
	
	
	/**
	 * @return clave del usuario
	 */
	public String getPassword() {
		return password;
	}
	public void setPassword(String pPassword) {
		password = pPassword;
	}
	
	/**
	 * @return nick del usuario
	 */
	public String getUser() {
		return user;
	}
	public void setUser(String pUser) {
		user = pUser;
	}
	
	
	/**
	 * @return devuelve la persona fisica a la que hace refereSncia
	 */
	protected PersonaFisicaRfr getPersonaFisicaRfr() {
		return personaFisicaRfr;
	}

	protected void setPersonaFisicaRfr(PersonaFisicaRfr pPersonaFisicaRfr) {
		if (pPersonaFisicaRfr != null) {
			personaFisicaRfr = pPersonaFisicaRfr;
			this.idPersonaFisica = personaFisicaRfr.getIdPersonaFisica();
		}
	}
	
	/**
	 * @return devuelve el estado del Usuario 
	 */
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado pEstado) {
		estado = pEstado;
	}
	

	/**
	 * @return Lista de roles que pertenecen al usuario
	 */                                    
	public Set<Rol> getListaRoles() {
		return listaRoles;
	}
	public void setListaRoles(Set<Rol> pListaRoles) {
		listaRoles = pListaRoles;
	}	
	
	public List<Area> getListaAreas() {
		return listaAreas;
	}
	public void setListaAreas(List<Area> pListaAreas) {
		listaAreas = pListaAreas;
	}
	
	/**
	 * Atributo transitorio, mantiene los permisos asignados al usuario
	 * @return Listado de los permisos que posee el usuario, Map(idRecurso (al recurso que hace referencia, Recurso con el permiso al mismo) 
	 */
	public Map<Long, Permiso> getPermisos() {
		if (this.permisos==null){
			this.refreshListaPermisos();
		}
		return permisos;
	}
	public void setPermisos(Map<Long, Permiso> pPermisos) {
		permisos = pPermisos;
	}
	
	/**
	 * Refresca la lista de permisos del usuario en el momento de ser solicitado
	 */
	public void refreshListaPermisos(){
		this.permisos=new HashMap<Long,Permiso>();
		
		Set<Rol> locListaRoles=this.getListaRoles();
		if (locListaRoles!=null){
			for (Rol rol : locListaRoles){
				Set<Permiso> locListaPermisos=rol.getListaPermisos();
				if (locListaPermisos!=null){
					for (Permiso permiso : locListaPermisos){
						if (this.permisos.get(permiso.getIdRecurso())!=null){
							try{
								this.permisos.put(permiso.getIdRecurso(),permiso.merge(this.permisos.get(permiso.getIdRecurso())));
							}
							catch(TrascenderFrameworkException e){
								//En teor�a jam�s deber�a pasar esta excepcion
							}
						}
						else{
							this.permisos.put(permiso.getIdRecurso(),permiso);
						}
					}
				}
			}
		}
	}
	
	public PersonaFisica getPersonaFisica() {
		return personaFisica;
	}
	
	public void setPersonaFisica(PersonaFisica pPersonaFisica) {
		if (pPersonaFisica==null){
			this.setPersonaFisicaRfr(null);
		}
		else{
			PersonaFisicaRfr personaFisicaRfr=new PersonaFisicaRfr(pPersonaFisica);
			this.setPersonaFisicaRfr(personaFisicaRfr);
		}
		this.personaFisica = pPersonaFisica;
	}
	
	/**
	 * 
	 * @return el id de la persona fisica
	 */
	public long getIdPersonaFisica(){
//		return (this.getPersonaFisicaRfr()==null)?-1:this.getPersonaFisicaRfr().getIdPersonaFisica();
//		return idPersonaFisica;
		return this.personaFisica.getIdPersona();
	}
	
	public void setIdPersonaFisica(long pIdPersonaFisica) {
		idPersonaFisica = pIdPersonaFisica;
	}
	
	public String toString() {	
		return this.user;
	}
	
	/**
	 * @return Devuelve el nombre de la persona fisica a la que hace referencia
	 */
	public String getNombrePersonaFisica(){
		return (this.getPersonaFisicaRfr()!=null)?(this.getPersonaFisicaRfr().getNombre()+" "+this.getPersonaFisicaRfr().getApellido()):"";
	}
	
	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + (int) (idUsuario ^ (idUsuario >>> 32));
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
		final Usuario other = (Usuario) obj;
		if (idUsuario != other.idUsuario)
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
			return this.idUsuario;
		}

		public String getNombrePropiedadId() {
			return "idUsuario";
		}

		public boolean isAuditable() {
			return true;
		}
}
