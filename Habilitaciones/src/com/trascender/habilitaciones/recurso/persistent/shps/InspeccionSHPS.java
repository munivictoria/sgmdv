package com.trascender.habilitaciones.recurso.persistent.shps;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "INSPECCION_SHPS")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TIPO_INSPECCION_SHPS")
public abstract class InspeccionSHPS implements Serializable{

	/**
	 * 
	 */
	public enum Estado{APROBADA,RECHAZADA,EN_CURSO,POSTERGADA;
		@Override
		public String toString() {
			return com.trascender.framework.util.Util.capitalizeEnumName(super.toString());
		}
		
	};
	public static final long serialVersionUID = 5601269050475464461L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="gen_id_inspeccion_shps")
	@SequenceGenerator(name = "gen_id_inspeccion_shps", sequenceName = "gen_id_inspeccion_shps", allocationSize = 1)
	@Column(name = "ID_INSPECCION_SHPS")
	private long idInspeccion=-1;
	
	private String descripcion;
	
	private Date fecha;
	
	@Enumerated(EnumType.STRING)
	private Estado estado;
	
	@ManyToOne
	@JoinColumn(name = "ID_INSPECTOR", nullable = false)
	private Inspector inspector;
	
	public Inspector getInspector() {
		return inspector;
	}
	public void setInspector(Inspector inspector) {
		this.inspector = inspector;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public long getIdInspeccion() {
		return idInspeccion;
	}
	public void setIdInspeccion(long idInspeccion) {
		this.idInspeccion = idInspeccion;
	}
	
	
	@Override
	public int hashCode() {
		if(this.getIdInspeccion()!=-1){
			final int PRIME = 31;
			int result = 1;
			result = PRIME * result + (int) (idInspeccion ^ (idInspeccion >>> 32));
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
		final InspeccionSHPS other = (InspeccionSHPS) obj;
		if (idInspeccion != other.idInspeccion)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "InspeccionSHPS [idInspeccion=" + idInspeccion
				+ ", descripcion=" + descripcion + ", fecha=" + fecha
				+ ", estado=" + estado + "]";
	}
	
	
}
