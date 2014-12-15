package com.trascender.saic.recurso.persistent;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.trascender.habilitaciones.recurso.persistent.TipoModificador;
import com.trascender.habilitaciones.recurso.persistent.TipoModificador.EnumTipoModificador;

@Entity
@DiscriminatorValue("F")
public class ModificadorLiquidacionFormula extends ModificadorLiquidacion{
	private static final long serialVersionUID = -2480682372575998287L;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_TIPO_MODIFICADOR")
	private TipoModificador tipoModificador;
	
	@Override
	public EnumTipoModificador getEnumTipoModificador() {
		EnumTipoModificador locEnum = null;
		if (this.tipoModificador != null){
			locEnum = this.tipoModificador.getTipo();
		}
		return locEnum;
	}
	
	@Override
	public boolean isSobreSaldoNeto() {
		return this.getTipoModificador() != null && this.getTipoModificador().isSobreSaldoNeto();
	}
	
	public TipoModificador getTipoModificador() {
		return tipoModificador;
	}

	public void setTipoModificador(TipoModificador tipoModificador) {
		this.tipoModificador = tipoModificador;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result
				+ (this.tipoModificador != null && this.tipoModificador.isSobreSaldoNeto() ? 1231 : 1237);
		result = prime
				* result
				+ ((valorModificador == null) ? 0 : valorModificador.hashCode());
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
		ModificadorLiquidacionFormula other = (ModificadorLiquidacionFormula) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (other.getTipoModificador().isSobreSaldoNeto() != other
				.getTipoModificador().isSobreSaldoNeto())
			return false;
		if (valorModificador == null) {
			if (other.valorModificador != null)
				return false;
		} else if (!valorModificador.equals(other.valorModificador))
			return false;
		return true;
	}
	
	@Override
	public ModificadorLiquidacionFormula clone() throws CloneNotSupportedException{
		ModificadorLiquidacionFormula locModificador = (ModificadorLiquidacionFormula) super.clone();
		locModificador.setIdModificadorLiquidacion(-1);
		return locModificador;
	}

}
