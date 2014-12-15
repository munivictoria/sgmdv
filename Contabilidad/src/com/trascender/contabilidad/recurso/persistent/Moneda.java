package com.trascender.contabilidad.recurso.persistent;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "MONEDA")
public class Moneda implements Serializable{

	public static final long serialVersionUID = -3981501613977176155L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_tipo_moneda")
	@SequenceGenerator(name = "gen_id_tipo_moneda", sequenceName = "gen_id_tipo_moneda",allocationSize = 1)
	@Column(name="ID_MONEDA")
	private long idTipoMoneda=-1;
	private String nombre;
	
	@Enumerated(EnumType.STRING)
	private Tipo tipo;
	public enum Tipo{BILLETE, MONEDA;
		@Override
		public String toString(){
			return com.trascender.framework.util.Util.capitalizeEnumName(super.toString());
		}
		
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public long getIdTipoMoneda() {
		return idTipoMoneda;
	}

	public void setIdTipoMoneda(long idTipoMoneda) {
		this.idTipoMoneda = idTipoMoneda;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		if (this.idTipoMoneda == -1){
			super.hashCode();
		}
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + (int) (idTipoMoneda ^ (idTipoMoneda >>> 32));
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
		final Moneda other = (Moneda) obj;
		if (idTipoMoneda != other.idTipoMoneda) {
			return false;
		}
		return true;
	}
}
