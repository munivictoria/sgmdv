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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "PARAMETRO_VALUADO_ALICUOTA")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TIPO")
public abstract class ParametroValuadoAlicuota implements Serializable{
	private static final long serialVersionUID = 869708861241676963L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_param_valuado_alicuota")
	@SequenceGenerator(name = "gen_id_param_valuado_alicuota", sequenceName = "gen_id_param_valuado_alicuota",allocationSize = 1)
	@Column(name="ID_PARAM_VALUADO_ALICUOTA")
	private long idParametroValuadoAlicuota = -1;

	@ManyToOne
	@JoinColumn(name = "ID_ALICUOTA_LIQUIDADA")
	private AlicuotaLiquidada alicuotaLiquidada;

	private String nombre;

	public abstract Object getValorParametro();

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public long getIdParametroValuadoAlicuota() {
		return idParametroValuadoAlicuota;
	}
	public void setIdParametroValuadoAlicuota(long idParametroValuadoAlicuota) {
		this.idParametroValuadoAlicuota = idParametroValuadoAlicuota;
	}
	public AlicuotaLiquidada getAlicuotaLiquidada() {
		return alicuotaLiquidada;
	}
	public void setAlicuotaLiquidada(AlicuotaLiquidada alicuotaLiquidada) {
		this.alicuotaLiquidada = alicuotaLiquidada;
	}
	/**
	 * Nombre del parametro + nombre de la RegAlicuota correspondiente
	 * @return
	 */
	public String getNombreCompleto(){
		return this.nombre +"("+alicuotaLiquidada.getRegAlicuota().getNombre()+")";
	}

	@Override
	public String toString(){
		return this.getNombreCompleto();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((getValorParametro() == null) ? 0 : getValorParametro().hashCode());
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
		ParametroValuadoAlicuota other = (ParametroValuadoAlicuota) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (getValorParametro() == null) {
			if (other.getValorParametro() != null)
				return false;
		} else if (!getValorParametro().equals(other.getValorParametro()))
			return false;
		return true;
	}

	public static ParametroValuadoAlicuota getInstance(Object valor){
		if (valor instanceof Double){
			ParametroValuadoAlicuotaDouble locParametro = new ParametroValuadoAlicuotaDouble();
			locParametro.setValorParametro((Double) valor);
			return locParametro;
		} else if (valor instanceof String){
			ParametroValuadoAlicuotaString locParametro = new ParametroValuadoAlicuotaString();
			locParametro.setValorParametro((String) valor);
			return locParametro;
		}
		return null;
	}

}
