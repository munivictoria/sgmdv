
package com.trascender.habilitaciones.recurso.persistent.shps;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Transient;

import org.hibernate.annotations.Where;

import com.trascender.framework.recurso.persistent.CodigoCiiu;
import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
import com.trascender.framework.util.EntidadTrascender;
import com.trascender.framework.util.LogAuditoria;
import com.trascender.habilitaciones.recurso.persistent.RegAlicuota;

@Entity
@DiscriminatorValue(value = "RUBRO")
public class Rubro extends RegAlicuota implements Serializable, EntidadTrascender {

	public static final long serialVersionUID = 3497514195558450896L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CODIGO_CIIU", nullable = false)
	private CodigoCiiu codigoCiiu;

	private String descripcion;

	@OrderBy(value = "fecha")
	@Where(clause = "id_recurso = " + serialVersionUID)
	@OneToMany(mappedBy = "idEntidad", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<LogAuditoria> listaLogsAuditoria = new ArrayList<LogAuditoria>();

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcionCiiu() {
		return codigoCiiu.getDescripcion();
	}

	public CodigoCiiu getCodigoCiiu() {
		return codigoCiiu;
	}

	public void setCodigoCiiu(CodigoCiiu codigoCiiu) {
		this.codigoCiiu = codigoCiiu;
	}

	@Override
	public String toString() {
		return "[" + this.getCodigoCiiu().getCodigo() + "]" + this.codigoCiiu.getDescripcion();
	}

	// Atributos Dinamicos

	@Where(clause = "id_recurso = " + serialVersionUID)
	@OneToMany(mappedBy = "idEntidad", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
	private List<AtributoDinamico<?>> listaAtributosDinamicos = new ArrayList<AtributoDinamico<?>>();

	public void addAtributoDinamico(AtributoDinamico<?> pAtributoDinamico) {
		pAtributoDinamico.setIdEntidad(this.getIdTipoAlicuota());
		this.listaAtributosDinamicos.add(pAtributoDinamico);
	}

	public List<AtributoDinamico<?>> getListaAtributosDinamicos() {
		return listaAtributosDinamicos;
	}

	public void setListaAtributosDinamicos(List<AtributoDinamico<?>> pListaAtributosDinamicos) {
		this.listaAtributosDinamicos.clear();
		for(AtributoDinamico<?> cadaAtributo : pListaAtributosDinamicos) {
			if(cadaAtributo.getValor() != null) {
				this.addAtributoDinamico(cadaAtributo);
			}
		}
	}

	// *********************************************************************************************************************************************************************************/
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
		return this.getIdTipoAlicuota();
	}

	public long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getNombrePropiedadId() {
		return "idTipoAlicuota";
	}

	public boolean isAuditable() {
		return true;
	}
}
