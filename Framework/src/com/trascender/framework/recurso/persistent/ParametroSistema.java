package com.trascender.framework.recurso.persistent;

import java.io.Serializable;

import javax.persistence.Basic;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TIPO_PARAM")
@Table(name = "PARAMETRO_SISTEMA")
public abstract class ParametroSistema implements Serializable{
	
	private static final long serialVersionUID = 5627967307373047737L;

	public enum Estado{ELIMINADO,ACTIVO;
		@Override
		public String toString() {
			return com.trascender.framework.util.Util.capitalizeEnumName(super.toString());
		}
	};
	
	@Id
	@GeneratedValue(generator="gen_id_parametro_sistema",strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="gen_id_parametro_sistema", sequenceName="gen_id_parametro_sistema", allocationSize = 1)
	@Column(name="ID_PARAMETRO_SISTEMA")
	protected long idParametroSistema = -1;
	
	@Column(name = "CODIGO", nullable = false)
	private String codigo;
	
	@Column(name = "NOMBRE", nullable = false)
	private String nombre;
	
	@Enumerated(EnumType.STRING)
	private Estado estado = Estado.ACTIVO;
	
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String pCodigo) {
		codigo = pCodigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String pNombre) {
		nombre = pNombre;
	}
	public long getIdParametroSistema() {
		return idParametroSistema;
	}
	public void setIdParametroSistema(long pIdParametro) {
		idParametroSistema = pIdParametro;
	}
	
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado pEstado) {
		estado = pEstado;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + (int) (idParametroSistema ^ (idParametroSistema >>> 32));
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
		ParametroSistema other = (ParametroSistema) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (idParametroSistema != other.idParametroSistema)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Parametro [idParametro=" + idParametroSistema + ", codigo=" + codigo
				+ ", nombre=" + nombre + ", estado=" + estado + "]";
	}
	
	public abstract Object getValor();	

}
