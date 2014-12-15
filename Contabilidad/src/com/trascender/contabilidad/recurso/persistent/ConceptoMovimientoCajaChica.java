package com.trascender.contabilidad.recurso.persistent;

import java.io.Serializable;
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
@Table(name = "CONCEPTO_MOV_CAJA_CHICA")
public class ConceptoMovimientoCajaChica implements Serializable {

	/**
	 * 
	 */
	public static final long serialVersionUID = 4674426100034453820L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_concepto_mov_caja_chica")
	@SequenceGenerator(name = "gen_id_concepto_mov_caja_chica", sequenceName = "gen_id_concepto_mov_caja_chica",allocationSize = 1)
	@Column(name="ID_CONCEPTO_MOV_CAJA_CHICA")
	private long idConceptoMovimientoCajaChica =-1;
	private String nombre;
	private String descripcion;
	
	@Enumerated(EnumType.STRING)
	private Tipo tipo;
	public enum Tipo{INGRESO, EGRESO;
		@Override
		public String toString(){
			return com.trascender.framework.util.Util.capitalizeEnumName(super.toString());
		}
	}
	//Relacion con otros objetos
	
	@OneToMany(mappedBy = "conceptoMovimiento", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<MovimientoCajaChica> movimientos;

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public long getIdConceptoMovimientoCajaChica() {
		return idConceptoMovimientoCajaChica;
	}

	public void setIdConceptoMovimientoCajaChica(long idConceptoMovimientoCajaChica) {
		this.idConceptoMovimientoCajaChica = idConceptoMovimientoCajaChica;
	}

	public Set<MovimientoCajaChica> getMovimientos() {
		return movimientos;
	}

	public void setMovimientos(Set<MovimientoCajaChica> movimientos) {
		this.movimientos = movimientos;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		if (this.idConceptoMovimientoCajaChica == -1){
			super.hashCode();
		}
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + (int) (idConceptoMovimientoCajaChica ^ (idConceptoMovimientoCajaChica >>> 32));
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
		final ConceptoMovimientoCajaChica other = (ConceptoMovimientoCajaChica) obj;
		if (idConceptoMovimientoCajaChica != other.idConceptoMovimientoCajaChica) {
			return false;
		}
		return true;
	}
	@Override
	public String toString(){
		return this.nombre;
	}
	
}
