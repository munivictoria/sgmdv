
package com.trascender.habilitaciones.recurso.persistent.cementerio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Transient;

import org.hibernate.annotations.Where;

import com.trascender.catastro.recurso.persistent.TituloPropiedad;
import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
import com.trascender.framework.util.EntidadTrascender;
import com.trascender.framework.util.LogAuditoria;

@Entity
@DiscriminatorValue("CONCESION")
public class Concesion extends TituloPropiedad implements Serializable, EntidadTrascender {

	public static final long serialVersionUID = 7945340834007868950L;

	public enum TipoConcesion {
		COMUN, GRATUITA;
		@Override
		public String toString() {
			return com.trascender.framework.util.Util.capitalizeEnumName(super.toString());
		}
	};

	@Column(name = "FECHA_FIN")
	private Date fechaFin;

	@Enumerated(EnumType.STRING)
	@Column(name = "TIPO_CONCESION")
	private TipoConcesion tipoConcesion = TipoConcesion.COMUN;

	@Where(clause = "id_recurso = " + serialVersionUID)
	@OneToMany(mappedBy = "idEntidad", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
	private List<AtributoDinamico<?>> listaAtributosDinamicos = new ArrayList<AtributoDinamico<?>>();

	public void addAtributoDinamico(AtributoDinamico<?> pAtributoDinamico) {
		pAtributoDinamico.setIdEntidad(this.getIdTituloPropiedad());
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

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public TipoConcesion getTipoConcesion() {
		return tipoConcesion;
	}

	public void setTipoConcesion(TipoConcesion tipoConcesion) {
		this.tipoConcesion = tipoConcesion;
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
		return this.getIdTituloPropiedad();
	}

	public String getNombrePropiedadId() {
		return "idTituloPropiedad";
	}

	public boolean isAuditable() {
		return true;
	}
}
