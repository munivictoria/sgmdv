package com.trascender.framework.recurso.persistent;

import java.io.Serializable;
import java.util.ArrayList;
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
import com.trascender.framework.util.Util;

/**
 * Representa un pais
 * @hibernate.class table = "PAIS"
 */

@Entity
@Table(name="PAIS")
public class Pais implements Serializable, EntidadTrascender {

	public static final long serialVersionUID = -4731188794553475626L;
	
	@Id
	@GeneratedValue(generator="gen_id_pais", strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="gen_id_pais",sequenceName="gen_id_pais", allocationSize = 1)
	@Column(name="ID_PAIS")
	private long idPais=-1;
	
	@Column(nullable=false)
	private String nombre;
	
	@Column(nullable=false)
	private String abreviatura;
	
	/**
	 * 
	 * @return abreviatura que representa al pais
	 * @hibernate.property not-null = "true"
	 */
	public String getAbreviatura() {
		return (abreviatura != null)?abreviatura.toUpperCase():null;
	}
	public void setAbreviatura(String pAbreviatura) {
		abreviatura = pAbreviatura;
	}
	
	/**
	 * 
	 * @return número de identificación del pais
	 * @hibernate.id column = "ID_PAIS" generator-class = "increment"
	 * unsaved-value = "-1"
	 */
	public long getIdPais() {
		return idPais;
	}
	public void setIdPais(long pIdPais) {
		idPais = pIdPais;
	}
	
	/**
	 * 
	 * @return Nombre del país
	 * @hibernate.property not-null = "true"
	 */
	public String getNombre() {
		return Util.capitalize(nombre);
	}
	public void setNombre(String pNombre) {
		nombre = pNombre;
	}
		
	public String toString() {
		return (this.nombre==null)?"":Util.capitalize(nombre);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idPais ^ (idPais >>> 32));
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
		Pais other = (Pais) obj;
		if (idPais != other.idPais)
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
			return this.idPais;
		}

		public String getNombrePropiedadId() {
			return "idPais";
		}

		public boolean isAuditable() {
			return true;
		}
}
