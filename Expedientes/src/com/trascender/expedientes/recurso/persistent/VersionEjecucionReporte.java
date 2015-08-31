/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

package com.trascender.expedientes.recurso.persistent;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "EXP_VERSION_EJECUCION_REPORTE")
public class VersionEjecucionReporte implements Serializable {

	private static final long serialVersionUID = -8339455943350877201L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_id_version_ejecucion_rp")
	@SequenceGenerator(name = "gen_id_version_ejecucion_rp", sequenceName = "gen_id_version_ejecucion_rp", allocationSize = 1)
	@Column(name = "ID_VERSION_EJECUCION_RP")
	private long idVersionEjecucionReporte = -1;

	boolean activo = true;

	@Column(name = "FECHA_EJECUCION")
	Date fechaEjecucion = new Date();

	@ManyToOne
	@JoinColumn(name = "ID_DOCUMENTO")
	private Documento documento;

	@Column(name = "documento_adjunto")
	@Type(type = "org.hibernate.type.BinaryType")
	private byte[] documentoAdjunto;

	@OneToMany(mappedBy = "versionEjecucionReporte", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ParametroValuadoReporte> listaParametrosValuadosReporte;

	public long getIdVersionEjecucionReporte() {
		return idVersionEjecucionReporte;
	}

	public void setIdVersionEjecucionReporte(long idVersionEjecucionReporte) {
		this.idVersionEjecucionReporte = idVersionEjecucionReporte;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public Date getFechaEjecucion() {
		return fechaEjecucion;
	}

	public void setFechaEjecucion(Date fechaEjecucion) {
		this.fechaEjecucion = fechaEjecucion;
	}

	public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

	public byte[] getDocumentoAdjunto() {
		return documentoAdjunto;
	}

	public void setDocumentoAdjunto(byte[] documentoAdjunto) {
		this.documentoAdjunto = documentoAdjunto;
	}

	public List<ParametroValuadoReporte> getListaParametrosValuadosReporte() {
		return listaParametrosValuadosReporte;
	}

	public void setListaParametrosValuadosReporte(List<ParametroValuadoReporte> listaParametrosValuadosReporte) {
		this.listaParametrosValuadosReporte = listaParametrosValuadosReporte;
	}

}