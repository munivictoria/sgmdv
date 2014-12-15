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
import com.trascender.framework.util.Util;

/**
 * @hibernate.class table = "PROVINCIA"
 */
@Entity
@Table(name="PROVINCIA")
public class Provincia implements Serializable, EntidadTrascender {

	public static final long serialVersionUID = -9057891863082885998L;
	
	@Id
	@GeneratedValue(generator="gen_id_provincia", strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="gen_id_provincia", sequenceName="gen_id_provincia",allocationSize = 1)
	@Column(name="ID_PROVINCIA")
	private long idProvincia=-1;
	
	@Column(nullable=false)
	private String nombre;
	
	@Column(nullable=false)
	private String abreviatura;
	
	@Column(nullable=false)
	private String codigo;
	
	@ManyToOne
	@JoinColumn(name="ID_PAIS")
	private Pais pais;
	
	
	/**
	 * @return El codigo de la provincia
	 */
	public String getCodigo() {
		return (codigo!=null)?codigo.toUpperCase():null;
	}
	public void setCodigo(String pCodigo) {
		codigo = pCodigo;
	}
	/**
	 * @return Pais al que pertenece la provincia
	 */
	public Pais getPais() {
		if (this.pais==null) return null;
		return pais;
	}
	public void setPais(Pais pPais) {
		pais = pPais;
	}
	/**
	 * @return abreviatura que representa la provincia
	 */
	public String getAbreviatura() {
		return (abreviatura != null)?abreviatura.toUpperCase():null;
	}
	public void setAbreviatura(String pAbreviatura) {
		abreviatura = pAbreviatura;
	}
	
	/**
	 * @return número de identificación de la provincia
	 */
	public long getIdProvincia() {
		return idProvincia;
	}
	public void setIdProvincia(long pIdProvincia) {
		idProvincia = pIdProvincia;
	}
	
	/**
	 * @return nombre de la provincia
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
		result = prime * result + (int) (idProvincia ^ (idProvincia >>> 32));
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
		Provincia other = (Provincia) obj;
		if (idProvincia != other.idProvincia)
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
			return this.idProvincia;
		}

		public String getNombrePropiedadId() {
			return "idProvincia";
		}

		public boolean isAuditable() {
			return true;
		}
}
