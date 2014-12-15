/**
 * 
 */
package com.trascender.contabilidad.recurso.persistent;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "SUBDIARIO_CAJA")
public class SubdiarioCaja implements Serializable{

	public static final long serialVersionUID = 8203019364856561214L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_subdiario_caja")
	@SequenceGenerator(name = "gen_id_subdiario_caja", sequenceName = "gen_id_subdiario_caja",allocationSize = 1)
	@Column(name="ID_SUBDIARIO_CAJA")
	private long idSubdiarioCaja =-1;
	
	@Enumerated(EnumType.STRING)
	private Tipo tipo;
	
	@Column(name = "FECHA_GENERACION")
	private Date fechaCreacion;
	
	public enum Tipo{INGRESO, EGRESO, COMPRAS, PRESUPUESTARIO;
		@Override
		public String toString(){
			return com.trascender.framework.util.Util.capitalizeEnumName(super.toString());
		}
	}
	//Relacion con otros objetos
	@OneToMany(mappedBy = "subdiarioCaja", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<LineaSubdiarioCaja> lineasSubdiarioCaja = new HashSet<LineaSubdiarioCaja>();
	
	public long getIdSubdiarioCaja() {
		return idSubdiarioCaja;
	}
	
	public void setIdSubdiarioCaja(long idSubdiarioCaja) {
		this.idSubdiarioCaja = idSubdiarioCaja;
	}

	public Tipo  getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public Set<LineaSubdiarioCaja> getLineasSubdiarioCaja() {
		return lineasSubdiarioCaja;
	}

	public void setLineasSubdiarioCaja(Set<LineaSubdiarioCaja> lineasSubdiarioCaja) {
		this.lineasSubdiarioCaja = lineasSubdiarioCaja;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		if (this.idSubdiarioCaja == -1){
			return super.hashCode();
		}
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + (int) (idSubdiarioCaja ^ (idSubdiarioCaja >>> 32));
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final SubdiarioCaja other = (SubdiarioCaja) obj;
		if (idSubdiarioCaja != other.idSubdiarioCaja) {
			return false;
		}
		return true;
	}
	
	@Override
	public String toString(){
		return this.fechaCreacion+" - "+this.tipo;
	}
}
