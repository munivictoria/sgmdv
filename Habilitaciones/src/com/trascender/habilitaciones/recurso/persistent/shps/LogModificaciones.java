package com.trascender.habilitaciones.recurso.persistent.shps;

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
@Table(name = "LOG_MODIF_DOC_HAB_ESPE")
public class LogModificaciones implements Serializable {

	private static final long serialVersionUID = -4369831764464129170L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="gen_id_log_modif_doc_hab_espe")
	@SequenceGenerator(name = "gen_id_log_modif_doc_hab_espe", sequenceName = "gen_id_log_modif_doc_hab_espe", allocationSize = 1)
	@Column(name = "ID_LOG_MODIF_DOC_HAB_ESPE")
	private long idLogModificaciones=-1;
	
	private Date fecha;
	
	@Column(name = "NUMERO")
	private String numeroInscripcion;
	
	@ManyToOne
	@JoinColumn(name = "ID_DOC_HAB_ESPECIALIZADO", nullable = false)
	private DocumentoSHPS documentoSHPS;

	public DocumentoSHPS getDocumentoSHPS() {
		return documentoSHPS;
	}
	public void setDocumentoSHPS(DocumentoSHPS documentoSHPS) {
		this.documentoSHPS = documentoSHPS;
	}
	
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public String getNumeroInscripcion() {
		return numeroInscripcion;
	}
	public void setNumeroInscripcion(String numeroInscripcion) {
		this.numeroInscripcion = numeroInscripcion;
	}
	public long getIdLogModificaciones() {
		return idLogModificaciones;
	}
	public void setIdLogModificaciones(long idLogModificaciones) {
		this.idLogModificaciones = idLogModificaciones;
	}
	
	
	@Override
	public int hashCode() {
		if(this.getIdLogModificaciones()==-1) return super.hashCode();
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + (int) (idLogModificaciones ^ (idLogModificaciones >>> 32));
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
		final LogModificaciones other = (LogModificaciones) obj;
		if (idLogModificaciones != other.idLogModificaciones)
			return false;
		return true;
	}
	

}
