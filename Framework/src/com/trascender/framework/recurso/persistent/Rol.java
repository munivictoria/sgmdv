package com.trascender.framework.recurso.persistent;

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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

import com.trascender.framework.util.EntidadTrascender;
import com.trascender.framework.util.LogAuditoria;

/**
 * Representa un rol de un area
 * @hibernate.class table = "ROL"
 */

@Entity
@Table(name="ROL")
public class Rol implements Serializable, EntidadTrascender{

	static public final long serialVersionUID = 7246127717394787359L;
	
	@Id
	@GeneratedValue(generator="gen_id_rol",strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="gen_id_rol",sequenceName="gen_id_rol",allocationSize = 1)
	@Column(name="ID_ROL")
	private long idRol=-1;
	
	private String nombre;
	
	@Column(name="FECHA_DESDE")
	private Date desde;
	
	@Column(name="FECHA_HASTA")
	private Date hasta;
	
	@Enumerated(EnumType.STRING)
	private Estado estado;
	
	@OneToMany(fetch=FetchType.LAZY, cascade={CascadeType.ALL},mappedBy="rol")
	private Set<Permiso> listaPermisos=new HashSet<Permiso>();
	
	/**
	 * Estados posibles {ELIMINADO, ACTIVO}
	 * @author jsantacruz
	 */
	public enum Estado {
		ELIMINADO, ACTIVO;
		@Override
		public String toString() {
			return com.trascender.framework.util.Util.capitalizeEnumName(super
					.toString());
		}
	};
	
	/**
	 * @return listado de recursos que permite
	 */
	public Set<Permiso> getListaPermisos() {
		return listaPermisos;
	}
	public void setListaPermisos(Set<Permiso> pListaPermisos) {
		listaPermisos = pListaPermisos;
		for(Permiso cadaPermiso: listaPermisos){
			cadaPermiso.setRol(this);
		}
	}
	
	public void addPermiso(Permiso pPermiso){
		boolean encontrado = false;
		for (Iterator<Permiso> locIterator = listaPermisos.iterator(); locIterator
				.hasNext();) {
			Permiso cadaPermiso = locIterator.next();
			if (cadaPermiso.equals(pPermiso)){
				encontrado = true;
				cadaPermiso.setAudith(pPermiso.getAudith());
				cadaPermiso.setDelete(pPermiso.getDelete());
				cadaPermiso.setInsert(pPermiso.getInsert());
				cadaPermiso.setUpdate(pPermiso.getUpdate());
				cadaPermiso.setSelect(pPermiso.getSelect());
			}
		}
		if (!encontrado){
			pPermiso.setRol(this);
			this.listaPermisos.add(pPermiso);
		}
	}
	
	/**
	 * @return fecha desde la cual se encuentra activo el rol
	 */
	public Date getDesde() {
		return desde;
	}
	public void setDesde(Date pDesde) {
		desde = pDesde;
	}
	/**
	 * @return fecha hasta la cual se encuentra activo el rol
	 */
	public Date getHasta() {
		return hasta;
	}
	public void setHasta(Date pHasta) {
		hasta = pHasta;
	}
	
	/**
	 * @return número de identificación del rol
	 */
	public long getIdRol() {
		return idRol;
	}
	public void setIdRol(long pIdRol) {
		idRol = pIdRol;
	}
	
	/**
	 * @return nombre del rol
	 */
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String pNombre) {
		nombre = pNombre;
	}
	
	/**
	 * @return estado en el que se encuentra el rol
	 */
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado pEstado) {
		estado = pEstado;
	}
	
	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + (int) (idRol ^ (idRol >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object pObj) {
		if (this == pObj) return true;
		if (!(pObj instanceof Rol)) return false;
		if ((this.getNombre().equals(((Rol)pObj).getNombre()))
				&& 
				(this.getEstado().equals(((Rol)pObj).getEstado())))
			return true;
		else 
			return false;
	}
	
	public String toString() {	
		return (this.nombre==null)?"":this.nombre;
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
		return this.idRol;
	}

	public String getNombrePropiedadId() {
		return "idRol";
	}

	public boolean isAuditable() {
		return true;
	}
}
