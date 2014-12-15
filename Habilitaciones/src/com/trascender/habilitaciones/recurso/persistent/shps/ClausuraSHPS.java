package com.trascender.habilitaciones.recurso.persistent.shps;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.trascender.framework.recurso.persistent.FirmaPermiso;
import com.trascender.framework.recurso.persistent.Rol;

@Entity
@Table(name = "CLAUSURA")
public class ClausuraSHPS implements Serializable{

	/**
	 * 
	 */
	public static final long serialVersionUID = 6035921153672943200L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="gen_id_clausura")
	@SequenceGenerator(name = "gen_id_clausura", sequenceName = "gen_id_clausura", allocationSize = 1)
	@Column(name = "ID_CLAUSURA")
	private long idClausuraSHPS=-1;
	
	@Column(name = "CLAUSURA_ACTIVA")
	private boolean clausuraActiva=true;
	
	@Column(name = "FECHA_ALTA_CLAUSURA")
	private Date fechaAlta;
	
	@Column(name = "FECHA_BAJA_CLAUSURA")
	private Date fechaBaja;
	
	@ManyToOne
	@JoinColumn(name = "ID_ROL", nullable = false)
	private Rol rol;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_FIRMA_PERMISO_HAB")
	private FirmaPermiso firma;
	
	@ManyToOne
	@JoinColumn(name = "ID_DOC_HAB_ESPECIALIZADO", nullable = false)
	private DocumentoSHPS documentoSHPS;
	
	private String descripcion;
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public DocumentoSHPS getDocumentoSHPS() {
		return documentoSHPS;
	}
	public void setDocumentoSHPS(DocumentoSHPS documentoSHPS) {
		this.documentoSHPS = documentoSHPS;
	}
	public boolean isClausuraActiva() {
		return clausuraActiva;
	}
	public void setClausuraActiva(boolean clausuraActiva) {
		this.clausuraActiva = clausuraActiva;
	}
	
	public Date getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	
	public Date getFechaBaja() {
		return fechaBaja;
	}
	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}
	public FirmaPermiso getFirma() {
		return firma;
	}
	public void setFirma(FirmaPermiso firma) {
		this.firma = firma;
	}
	
	public long getIdClausuraSHPS() {
		return idClausuraSHPS;
	}
	public void setIdClausuraSHPS(long idClausuraSHPS) {
		this.idClausuraSHPS = idClausuraSHPS;
	}
	
	public Rol getRol() {
		return rol;
	}
	public void setRol(Rol rol) {
		this.rol = rol;
	}
	
	@Override
	public int hashCode() {
		if(this.getIdClausuraSHPS()!=-1){
			final int PRIME = 31;
			int result = 1;
			result = PRIME * result + (int) (idClausuraSHPS ^ (idClausuraSHPS >>> 32));
			return result;
		}
		else{
			return super.hashCode();
		}
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final ClausuraSHPS other = (ClausuraSHPS) obj;
		if (idClausuraSHPS != other.idClausuraSHPS)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		DateFormat locDateFormat=new SimpleDateFormat("dd/MM/yyyy");
		
		
		return	((this.getDocumentoSHPS()!=null)?("["+this.getDocumentoSHPS().getNumeroInscripcion()+"] - "):"")
					+
				((this.getFechaAlta()!=null)?("Alta: "+locDateFormat.format(this.getFechaAlta())):"")
				    +
				(((this.getFechaBaja()!=null)&&(!this.isClausuraActiva()))?(" Baja: "+locDateFormat.format(this.getFechaBaja())):" Clausura activa ");
		
	}
	
}
