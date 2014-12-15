package com.trascender.habilitaciones.recurso.persistent.transito;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
import com.trascender.framework.util.Util;
import com.trascender.framework.util.anotations.NoAuditable;
import com.trascender.habilitaciones.util.TransitoUtils;

/**
 * @author jsantacruz
 */

@Entity
@Table(name = "VEHICULO")
public class Vehiculo implements Serializable, EntidadTrascender{

	public static final long serialVersionUID = -8045723384860386258L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="gen_id_vehiculo")
	@SequenceGenerator(name = "gen_id_vehiculo", sequenceName = "gen_id_vehiculo", allocationSize = 1)
	@Column(name = "ID_VEHICULO")
	private long idVehiculo=-1;
	
	private String descripcion;
	
	@Column(nullable = false, unique=true)
	private String patente;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="ID_MODELO", nullable=false)
	private Modelo modelo;
	
	@NoAuditable
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="ID_TITULO_PROPIEDAD")
	private TituloPropiedadAutomotor tituloPropiedad;

	@Where(clause = "id_recurso = " + serialVersionUID)
	@OneToMany(mappedBy = "idEntidad", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
	private List<AtributoDinamico<?>> listaAtributosDinamicos = new ArrayList<AtributoDinamico<?>>();
	
	@OrderBy(value="fecha")
	@Where(clause = "id_recurso = " + serialVersionUID)
	@OneToMany(mappedBy = "idEntidad", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<LogAuditoria> listaLogsAuditoria = new ArrayList<LogAuditoria>();
	
	private Integer anio;
	private Double peso;
	private Double capacidad;
	private String chasis;
	private String motor;
	
	public void addAtributoDinamico(AtributoDinamico<?> pAtributoDinamico){
		pAtributoDinamico.setIdEntidad(this.idVehiculo);
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
	
	public TituloPropiedadAutomotor getTituloPropiedad() {
		return tituloPropiedad;
	}

	public void setTituloPropiedad(TituloPropiedadAutomotor tituloPropiedad) {
		this.tituloPropiedad = tituloPropiedad;
	}

	public Modelo getModelo() {
		return modelo;
	}
	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}
	public String getPatente() {
		if(this.patente != null && TransitoUtils.validarPatente(patente)){
			return this.patente.toUpperCase();
		}
		
		return patente;
	}
	public void setPatente(String patente) {
		this.patente = patente;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public long getIdVehiculo() {
		return idVehiculo;
	}
	public void setIdVehiculo(long idVehiculo) {
		this.idVehiculo = idVehiculo;
	}
	
	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public Double getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(Double capacidad) {
		this.capacidad = capacidad;
	}

	public String getChasis() {
		return chasis;
	}

	public void setChasis(String chasis) {
		this.chasis = chasis;
	}

	public String getMotor() {
		return motor;
	}

	public void setMotor(String motor) {
		this.motor = motor;
	}

	@PostPersist
	public void postPersist(){
		for (AtributoDinamico<?> cadaAtributo : getListaAtributosDinamicos()){
			cadaAtributo.setIdEntidad(this.idVehiculo);
		}
	}
	
	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + (int) (idVehiculo ^ (idVehiculo >>> 32));
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
		final Vehiculo other = (Vehiculo) obj;
		if (idVehiculo != other.idVehiculo)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		String locString= "";
		if (this.getModelo() != null && this.getModelo().getMarca() != null){
			locString = Util.capitalize(this.getModelo().getMarca().getNombre()) + " ";
		}
		if (this.getPatente() != null){
			locString += "Patente " + this.getPatente();
		}
		return locString;
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
		if(this.getTituloPropiedad() != null) {
			this.getTituloPropiedad().setLlaveUsuarioAuditoria(llaveUsuarioAuditoria);
		}
	}

	public String getComentarioAuditoria() {
		return comentarioAuditoria;
	}

	public void setComentarioAuditoria(String comentarioAuditoria) {
		this.comentarioAuditoria = comentarioAuditoria;
		if(this.getTituloPropiedad() != null) {
			this.getTituloPropiedad().setComentarioAuditoria(comentarioAuditoria);
		}
	}

	public long getIdEntidad() {
		return this.idVehiculo;
	}

	public long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getNombrePropiedadId() {
		return "idVehiculo";
	}

	public boolean isAuditable() {
		return true;
	}
}