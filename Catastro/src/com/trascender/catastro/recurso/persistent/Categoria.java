
package com.trascender.catastro.recurso.persistent;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Where;

import com.trascender.framework.util.EntidadTrascender;
import com.trascender.framework.util.LogAuditoria;

/**
 * 
 * @author Mariano Lusardi
 * @hibernate.class table = "CATEGORIA"
 */
@Entity
@Table(name = "CATEGORIA")
public class Categoria implements Serializable, EntidadTrascender {
	public static final long serialVersionUID = 7397097492010441787L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_id_categoria")
	@SequenceGenerator(name = "gen_id_categoria", sequenceName = "gen_id_categoria", allocationSize = 1)
	@Column(name = "ID_CATEGORIA")
	private long idCategoria = -1;

	private Integer codigo;
	private String nombre;
	private boolean activo;

	@ManyToOne
	@JoinColumn(name = "ID_TIPO_CONSTRUCCION")
	private TipoConstruccion tipoConstruccion;

	@OneToMany(mappedBy = "categoria")
	private List<ValorBasicoMejora> listaValoresBasicosMejora;

	/**
	 * @hibernate.bag cascade = "all" lazy = "false" inverse = "true"
	 * @hibernate.collection-one-to-many class = "com.trascender.catastro.recurso.persistent.ValorBasicoMejora"
	 * @hibernate.collection-key column = "ID_CATEGORIA"
	 * 
	 */
	public List<ValorBasicoMejora> getListaValoresBasicosMejora() {
		return listaValoresBasicosMejora;
	}

	public void setListaValoresBasicosMejora(List<ValorBasicoMejora> listaValoresBasicosMejora) {
		this.listaValoresBasicosMejora = listaValoresBasicosMejora;
	}

	/**
	 * 
	 * @hibernate.property not-null = "true"
	 */
	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	/**
	 * 
	 * @hibernate.property not-null = "true"
	 */
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String pNombre) {
		this.nombre = pNombre;
	}

	/**
	 * 
	 * @hibernate.property
	 */
	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean pActivo) {
		this.activo = pActivo;
	}

	/**
	 * 
	 * @hibernate.id column = "ID_CATEGORIA" generator-class = "increment" unsaved-value = "-1"
	 */
	public long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(long idCategoria) {
		this.idCategoria = idCategoria;
	}

	/**
	 * @hibernate.many-to-one column = "ID_TIPO_CONSTRUCCION" cascade = "none" not-null = "true"
	 */
	public TipoConstruccion getTipoConstruccion() {
		return tipoConstruccion;
	}

	public void setTipoConstruccion(TipoConstruccion tipoConstruccion) {
		this.tipoConstruccion = tipoConstruccion;
	}

	@Override
	public String toString() {
		return ((this.getCodigo() != null) ? (this.getCodigo() + " - ") : "") + ((this.getNombre() != null) ? this.getNombre() : "");
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
		return this.idCategoria;
	}

	public String getNombrePropiedadId() {
		return "idCategoria";
	}

	public boolean isAuditable() {
		return true;
	}
}