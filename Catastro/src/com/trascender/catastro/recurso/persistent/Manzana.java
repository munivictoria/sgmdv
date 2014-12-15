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
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PostPersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Where;

import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
import com.trascender.framework.util.EntidadTrascender;
import com.trascender.framework.util.LogAuditoria;
import com.trascender.framework.util.anotations.NoAuditable;

@Entity
@Table(name = "MANZANA")
public class Manzana implements Serializable, EntidadTrascender {

	public static final long serialVersionUID = -2010847713221111327L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_id_manzana")
	@SequenceGenerator(name ="gen_id_manzana", sequenceName = "gen_id_manzana", allocationSize = 1)
	@Column(name = "ID_MANZANA")
	private long idManzana=-1;
	
	private String nombre;
	
	@Column(name = "NRO_MANZANA")
	private Integer nroManzana;
	
	@NoAuditable
	@OneToMany(mappedBy = "manzana", cascade = CascadeType.MERGE)
	private List<Cuadra> listaCuadrasDelimitantes = new ArrayList<Cuadra>();
	
	private boolean activo = true;
	
	@Where(clause = "id_recurso = " + serialVersionUID)
	@OneToMany(mappedBy = "idEntidad", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
	private List<AtributoDinamico<?>> listaAtributosDinamicos = new ArrayList<AtributoDinamico<?>>();
	
	@OneToMany(mappedBy = "manzana", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<AsociacionParcelaManzana> listaAsociacionParcelaManzana = new ArrayList<AsociacionParcelaManzana>();
	
	public void addZona(Zona pZona){
		AsociacionParcelaManzana locAsociacion = new AsociacionParcelaManzana();
		locAsociacion.setZona(pZona);
		locAsociacion.setManzana(this);
		this.listaAsociacionParcelaManzana.add(locAsociacion);
	}
	
	public void removeZona(AsociacionParcelaManzana pAsociacion){
		this.listaAsociacionParcelaManzana.remove(pAsociacion);
	}

	public List<AsociacionParcelaManzana> getListaAsociacionParcelaManzana() {
		return listaAsociacionParcelaManzana;
	}

	public void setListaAsociacionParcelaManzana(
			List<AsociacionParcelaManzana> listaAsociacionParcelaManzana) {
		this.listaAsociacionParcelaManzana = listaAsociacionParcelaManzana;
	}
	
	public void addAtributoDinamico(AtributoDinamico<?> pAtributoDinamico){
		pAtributoDinamico.setIdEntidad(this.idManzana);
		this.listaAtributosDinamicos.add(pAtributoDinamico);
	}
	
	public List<AtributoDinamico<?>> getListaAtributosDinamicos() {
		return listaAtributosDinamicos;
	}
	
	public void setListaAtributosDinamicos(List<AtributoDinamico<?>> pListaAtributosDinamicos) {
		this.listaAtributosDinamicos.clear();
		for (AtributoDinamico<?> cadaAtributo : pListaAtributosDinamicos){
			if (cadaAtributo.getValor() != null){
				this.addAtributoDinamico(cadaAtributo);
			}
		}
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean pActivo) {
		this.activo = pActivo;
	}

	public long getIdManzana() {
		return idManzana;
	}

	public void setIdManzana(long idManzana) {
		this.idManzana = idManzana;
	}

	public List<Cuadra> getListaCuadrasDelimitantes() {
		return listaCuadrasDelimitantes;
	}

	public void setListaCuadrasDelimitantes(
			List<Cuadra> listaCuadrasDelimitantes) {
		this.listaCuadrasDelimitantes = listaCuadrasDelimitantes;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;

	}

	public Integer getNroManzana() {
		return nroManzana;
	}

	public void setNroManzana(Integer nroManzana) {
		this.nroManzana = nroManzana;
	}

	@PostPersist
	public void postPersist(){
		for (AtributoDinamico<?> cadaAtributo : listaAtributosDinamicos){
			cadaAtributo.setIdEntidad(idManzana);
		}
	}
	
	@Override
	public String toString() {
		return (this.getNombre() != null) ? this.getNombre() : "";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idManzana ^ (idManzana >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		
		if (getClass() != obj.getClass())
			return false;
		
		if((this.idManzana == -1 && ((Manzana)obj).getIdManzana() == -1) 
				&& (System.identityHashCode(this) != System.identityHashCode(obj))){
			return false;
		}
		
		if (this == obj)
			return true;
		Manzana other = (Manzana) obj;
		
		if (idManzana != other.getIdManzana())
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
			return this.idManzana;
		}

		public String getNombrePropiedadId() {
			return "idManzana";
		}

		public boolean isAuditable() {
			return true;
		}
}