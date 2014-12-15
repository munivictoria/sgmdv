package com.trascender.expedientes.recurso.persistent;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "EXP_DOCUMENTO")
public class Documento implements Serializable {

	public static final long serialVersionUID = 6378876176303628409L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_id_exp_documento")
	@SequenceGenerator(name = "gen_id_exp_documento", sequenceName = "gen_id_exp_documento",
			allocationSize = 1)
	@Column(name = "ID_DOCUMENTO")
	private long idDocumento;

	@Column(name = "FECHA")
	private Date fecha;

	private boolean activo = true;
	
	@Column(name = "PRESENTADO")
	private boolean presentado = false;

	@Column(name = "OBSERVACION")
	private String observacion;

	@Column(name = "LOCALIZACION")
	private String localizacion;

	@Column(name = "NroRegistro")
	private Long nroRegistro;

	@ManyToOne
	@JoinColumn(name = "ID_TRAMITE")
	private Tramite tramite;

	@ManyToOne
	@JoinColumn(name = "ID_DOCUMENTOPROCEDIMIENTO")
	private DocumentoProcedimiento documentoProcedimiento;

	public Documento() {
	}

	public Documento(DocumentoProcedimiento pDocumentoProcedimiento, Tramite pTramite) {
		this.documentoProcedimiento = pDocumentoProcedimiento;
		this.tramite = pTramite;
	}

	public long getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(long idDocumento) {
		this.idDocumento = idDocumento;
	}

	
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
	}

	public Long getNroRegistro() {
		return nroRegistro;
	}

	public void setNroRegistro(Long nroRegistro) {
		this.nroRegistro = nroRegistro;
	}

	public Tramite getTramite() {
		return tramite;
	}

	public boolean isPresentado() {
		return presentado;
	}

	public void setPresentado(boolean presentado) {
		this.presentado = presentado;
	}

	public void setTramite(Tramite tramite) {
		this.tramite = tramite;
	}

	public DocumentoProcedimiento getDocumentoProcedimiento() {
		return documentoProcedimiento;
	}

	public void setDocumentoProcedimiento(DocumentoProcedimiento documentoProcedimiento) {
		this.documentoProcedimiento = documentoProcedimiento;
	}

	
	public String getPlantilla(){
		return getDocumentoProcedimiento().getDocumentoCatalogo().getNombre();
	}
	
	public String getNombre() {
		return documentoProcedimiento.getDocumentoCatalogo().getNombre();
	}
	
}
