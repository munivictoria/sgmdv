package com.trascender.saic.recurso.persistent;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.trascender.habilitaciones.recurso.persistent.TipoModificador;

@Entity
@DiscriminatorValue("M")
public class ModificadorLiquidacionManual extends ModificadorLiquidacion{
	private static final long serialVersionUID = -7134617280224321092L;

	@Column(name = "SOBRE_SALDO_NETO")
	private boolean sobreSaldoNeto;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "ENUM_TIPO_MODIFICADOR")
	private TipoModificador.EnumTipoModificador enumTipoModificador;

	@Override
	public TipoModificador.EnumTipoModificador getEnumTipoModificador() {
		return enumTipoModificador;
	}
	
	@Override
	public boolean isSobreSaldoNeto() {
		return sobreSaldoNeto;
	}

	public void setEnumTipoModificador(
			TipoModificador.EnumTipoModificador enumTipoModificador) {
		this.enumTipoModificador = enumTipoModificador;
	}

	public void setSobreSaldoNeto(boolean sobreSaldoNeto) {
		this.sobreSaldoNeto = sobreSaldoNeto;
	}
	
	/**
	 * No clonamos, porque este tipo de modificador esta asociado solamente a una liquidacion.
	 */
	@Override
	public ModificadorLiquidacionManual clone() throws CloneNotSupportedException{
		return this;
	}

}
