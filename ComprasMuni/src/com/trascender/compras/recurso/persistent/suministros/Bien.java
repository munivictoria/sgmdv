package com.trascender.compras.recurso.persistent.suministros;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Where;

import com.trascender.framework.recurso.persistent.CodigoCiiu;
import com.trascender.framework.util.EntidadTrascender;
import com.trascender.framework.util.LogAuditoria;

@Entity
@Table(name = "BIEN")
public class Bien implements Serializable, EntidadTrascender {

	public enum Estado{
		ACTIVO,INACTIVO
	}
	
	public enum Tipo{
		BIEN_FISICO, SERVICIO
	}
	
	public static final long serialVersionUID = 6790812488137859931L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_bien")
	@SequenceGenerator(name = "gen_id_bien", sequenceName = "gen_id_bien",allocationSize = 1)
	@Column(name="ID_BIEN")
	private long idBien=-1;
	
	private String nombre;
	private String codigo;
	private String descripcion;
	
	@Column(name = "VALOR_REFERENCIAL")
	private Double valorReferencial;
	
	@Column(name = "FECHA_ULTIMA_ACTUALIZACION")
	private Date fechaUltimaActualizacion;
	
	@Enumerated(EnumType.STRING)
	private Estado estado;
	
	@Enumerated(EnumType.STRING)
	private Tipo tipo;
	
	@ManyToOne
	@JoinColumn(name = "ID_UNIDAD")
	private Unidad unidad;
	
	@OneToMany
    @JoinTable(name="RELA_BIEN_CIIU", joinColumns=@JoinColumn(name="ID_BIEN", nullable = false),
    								inverseJoinColumns=@JoinColumn(name="ID_CODIGO_CIIU", nullable = false))
    private List<CodigoCiiu> listaCodigosCiiu = new ArrayList<CodigoCiiu>();
	
	@OneToMany
	@JoinTable(name="RELA_BIEN_TIPO_BIEN", joinColumns=@JoinColumn(name="ID_BIEN", nullable = false), 
									inverseJoinColumns=@JoinColumn(name="ID_TIPO_BIEN", nullable = false))
	private List<TipoBien> listaTipoBien = new ArrayList<TipoBien>();
	
	@OrderBy(value="fecha")
	@Where(clause = "id_recurso = " + serialVersionUID)
	@OneToMany(mappedBy = "idEntidad", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<LogAuditoria> listaLogsAuditoria = new ArrayList<LogAuditoria>();
	
	public Bien(){	
	}
	
	public Double getValorReferencial() {
		return valorReferencial;
	}

	public void setValorReferencial(Double valorReferencial) {
		this.valorReferencial = valorReferencial;
	}

	public Date getFechaUltimaActualizacion() {
		return fechaUltimaActualizacion;
	}

	public void setFechaUltimaActualizacion(Date fechaUltimaActualizacion) {
		this.fechaUltimaActualizacion = fechaUltimaActualizacion;
	}

	public List<CodigoCiiu> getListaCodigosCiiu() {
		return listaCodigosCiiu;
	}

	public void setListaCodigosCiiu(List<CodigoCiiu> listaCodigosCiiu) {
		this.listaCodigosCiiu = listaCodigosCiiu;
	}

	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public long getIdBien() {
		return idBien;
	}
	public void setIdBien(long idBien) {
		this.idBien = idBien;
	}
	public String getNombre() {
		if(nombre == null){
			return "";
		}
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	@Override
	public String toString() {
		String cadena = this.nombre +" [" + this.unidad + "]";
		return cadena;
	}
	public Unidad getUnidad() {
		return unidad;
	}
	public void setUnidad(Unidad unidad) {
		this.unidad = unidad;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idBien ^ (idBien >>> 32));
		return result;
	}
	
	public List<TipoBien> getListaTipoBien() {
		return listaTipoBien;
	}

	public void setListaTipoBien(List<TipoBien> listaTipoBien) {
		this.listaTipoBien = listaTipoBien;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bien other = (Bien) obj;
		if (idBien != other.idBien)
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
		return this.idBien;
	}

	public long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getNombrePropiedadId() {
		return "idBien";
	}

	public boolean isAuditable() {
		return true;
	}
}
