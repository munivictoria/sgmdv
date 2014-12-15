package com.trascender.catastro.recurso.persistent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

import com.trascender.framework.util.EntidadTrascender;
import com.trascender.framework.util.LogAuditoria;


/**
 * Representa una calle
 * @author Mariano Lusardi	
 */
@Entity
@Table(name = "CALLE")
public class Calle implements Serializable, EntidadTrascender {
	public static final long serialVersionUID = -7066539966152625725L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_calle")
	@SequenceGenerator(name = "gen_id_calle", sequenceName = "gen_id_calle",allocationSize = 1)
	@Column(name="ID_CALLE")
	private long idCalle=-1;
	
	private String codigo;
	private String nombre;
	private boolean activo;
	
	@OneToMany(mappedBy = "calleComienza", cascade = CascadeType.ALL)
	private List<Cuadra> listaCuadrasComenzadas;
	
	@OneToMany(mappedBy = "calleFinaliza", cascade = CascadeType.ALL)
	private List<Cuadra> listaCuadrasFinalizadas;
	
	@OneToMany(mappedBy = "calle")
	private List<Cuadra> listaCuadras;
	
	@ManyToOne
	@JoinColumn(name = "ID_TIPO_CALLE")
	private TipoCalle tipoCalle;
	
	@OneToMany(mappedBy = "calle", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<AsociacionParcelaCalle> listaAsociacionParcelaCalle = new ArrayList<AsociacionParcelaCalle>();
	
	public void addZona(Zona pZona){
		AsociacionParcelaCalle locAsociacion = new AsociacionParcelaCalle();
		locAsociacion.setZona(pZona);
		locAsociacion.setCalle(this);
		this.listaAsociacionParcelaCalle.add(locAsociacion);
	}
	
	public void removeZona(AsociacionParcelaCalle pAsociacion){
		this.listaAsociacionParcelaCalle.remove(pAsociacion);
	}

	public List<AsociacionParcelaCalle> getListaAsociacionParcelaCalle() {
		return listaAsociacionParcelaCalle;
	}

	public void setListaAsociacionParcelaCalle(
			List<AsociacionParcelaCalle> listaAsociacionParcelaCalle) {
		this.listaAsociacionParcelaCalle = listaAsociacionParcelaCalle;
	}
	
	public TipoCalle getTipoCalle() {
		return tipoCalle;
	}
	public void setTipoCalle(TipoCalle tipoCalle) {
		this.tipoCalle = tipoCalle;
	}
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	/**
	 * 
	 * @hibernate.id column = "ID_CALLE" generator-class = "increment"
	 * unsaved-value = "-1"
	 */
	public long getIdCalle() {
		return idCalle;
	}
	public void setIdCalle(long idCalle) {
		this.idCalle = idCalle;
	}
	
	public List<Cuadra> getListaCuadras() {
		return listaCuadras;
	}
	public void setListaCuadras(List<Cuadra> listaCuadras) {
		this.listaCuadras = listaCuadras;
	}
	
	public List<Cuadra> getListaCuadrasComenzadas() {
		return listaCuadrasComenzadas;
	}
	public void setListaCuadrasComenzadas(List<Cuadra> listaCuadrasComenzadas) {
		this.listaCuadrasComenzadas = listaCuadrasComenzadas;
	}
	
	public List<Cuadra> getListaCuadrasFinalizadas() {
		return listaCuadrasFinalizadas;
	}
	public void setListaCuadrasFinalizadas(List<Cuadra> listaCuadrasFinalizadas) {
		this.listaCuadrasFinalizadas = listaCuadrasFinalizadas;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public String toString() {
		return (this.getNombre()!=null)?this.getNombre():"";
	}
	
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idCalle ^ (idCalle >>> 32));
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
		Calle other = (Calle) obj;
		if (idCalle != other.idCalle)
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
			return this.idCalle;
		}

		public String getNombrePropiedadId() {
			return "idCalle";
		}

		public boolean isAuditable() {
			return true;
		}
}
