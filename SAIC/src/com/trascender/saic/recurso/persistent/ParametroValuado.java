package com.trascender.saic.recurso.persistent;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 
 * @author Mariano Lusardi
 */

@Entity
@Table(name = "PARAMETRO_VALUADO")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TIPO")
public abstract class ParametroValuado implements Serializable{


	private static final long serialVersionUID = -4701249433357182896L;
	
	@Id
	@Column(name = "ID_PARAMETRO_VALUADO")
	@SequenceGenerator(name = "gen_id_parametro_valuado", sequenceName = "gen_id_parametro_valuado", allocationSize = 1)
	@GeneratedValue(generator = "gen_id_parametro_valuado", strategy = GenerationType.SEQUENCE)
	private long idParametroValuado=-1;

//	@Column(name = "VALOR_PARAMETRO", nullable = false)
//	private Double valorParametro;
	
	@Column(name = "NOMBRE_PARAMETRO")
	private String nombreParametro;
	
	@Transient
	private LiquidacionTasa liquidacionTasa;
	
	public abstract Object getValorParametro();
	
	public LiquidacionTasa getLiquidacionTasa() {
		return liquidacionTasa;
	}
	public void setLiquidacionTasa(LiquidacionTasa liquidacionTasa) {
		this.liquidacionTasa = liquidacionTasa;
	}
	
	public long getIdParametroValuado() {
		return idParametroValuado;
	}
	public void setIdParametroValuado(long idParametroValuado) {
		this.idParametroValuado = idParametroValuado;
	}
	

	public String getNombreParametro() {
		return nombreParametro;
	}
	public void setNombreParametro(String nombreParametro) {
		this.nombreParametro = nombreParametro;
	}
	
//	@Override
//	public int hashCode() {
//		if (this.idParametroValuado==-1) return super.hashCode();
//		final int PRIME = 31;
//		int result = 1;
//		result = PRIME * result + (int) (idParametroValuado ^ (idParametroValuado >>> 32));
//		return result;
//	}
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		final ParametroValuado other = (ParametroValuado) obj;
//		if (idParametroValuado != other.idParametroValuado)
//			return false;
//		return true;
//	}
	
	
	
	@Override
	public String toString() {
		return ((this.getNombreParametro()!=null)?this.getNombreParametro():"")+" "+ ((this.getValorParametro()!=null)?this.getValorParametro():"");
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((nombreParametro == null) ? 0 : nombreParametro.hashCode());
		result = prime * result
				+ ((getValorParametro() == null) ? 0 : getValorParametro().hashCode());
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
		ParametroValuado other = (ParametroValuado) obj;
		if (nombreParametro == null) {
			if (other.nombreParametro != null)
				return false;
		} else if (!nombreParametro.equals(other.nombreParametro))
			return false;
		if (getValorParametro() == null) {
			if (other.getValorParametro() != null)
				return false;
		} else if (!getValorParametro().equals(other.getValorParametro()))
			return false;
		return true;
	}
	
}
