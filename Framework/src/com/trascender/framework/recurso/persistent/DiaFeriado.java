package com.trascender.framework.recurso.persistent;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Where;

import com.trascender.framework.util.EntidadTrascender;
import com.trascender.framework.util.LogAuditoria;


/**
 * @hibernate.class table = "DIA_FERIADO"
 */
@Entity
@Table(name="DIA_FERIADO")
public class DiaFeriado implements Serializable, EntidadTrascender{

	/**
	 * 
	 */
	public static final long serialVersionUID = -4006480996637545267L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="gen_id_dia_feriado")
	@SequenceGenerator(name="gen_id_dia_feriado", sequenceName="gen_id_dia_feriado",allocationSize = 1)
	@Column(name="ID_DIA_FERIADO")
	private long idDiaFeriado=-1;
	
	@Column(nullable=false)
	private Date fecha;
	
	private String nombre;
	
	/**
	 * 
	 * @hibernate.property column = "FECHA" not-null = "true"
	 */
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date pFecha) {
		fecha = pFecha;
	}
	
	/**
	 * 
	 * @hibernate.id column = "ID_DIA_FERIADO" generator-class = "increment" unsaved-value = "-1"
	 */
	public long getIdDiaFeriado() {
		return idDiaFeriado;
	}
	public void setIdDiaFeriado(long pIdDiaFeriado) {
		idDiaFeriado = pIdDiaFeriado;
	}
	
	/**
	 * 
	 * @hibernate.property 
	 */
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String pNombre) {
		nombre = pNombre;
	}
	
	@Override
	public String toString() {
		DateFormat locFormato=new SimpleDateFormat("dd/MM/yyyy");
		return ((this.getFecha()!=null)?locFormato.format(this.getFecha()):"")+" "+((this.getNombre()!=null)?this.getNombre():"");
	}
	
	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + (int) (idDiaFeriado ^ (idDiaFeriado >>> 32));
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
		final DiaFeriado other = (DiaFeriado) obj;
		if (idDiaFeriado != other.idDiaFeriado)
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
			return this.idDiaFeriado;
		}

		public String getNombrePropiedadId() {
			return "idDiaFeriado";
		}

		public boolean isAuditable() {
			return true;
		}
}
