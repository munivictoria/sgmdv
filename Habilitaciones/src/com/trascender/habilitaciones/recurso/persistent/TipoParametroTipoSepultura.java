package com.trascender.habilitaciones.recurso.persistent;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.trascender.habilitaciones.recurso.persistent.TipoParametroVencimiento.TipoAtributoVencimiento;
import com.trascender.habilitaciones.recurso.persistent.cementerio.TipoSepultura;

@Entity
@DiscriminatorValue(value = "TIPO_SEPULTURA")
public class TipoParametroTipoSepultura extends TipoParametroAlicuota{
	
	public static final long serialVersionUID = 289233365651248774L;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "ATRIBUTO")
	private AtributoTipoSepultura atributoTipoSepultura;
	
	public enum AtributoTipoSepultura{
		MINIMO, VALOR, CODIGO, ES_PORCENTUAL;
		@Override
		public String toString() {
			return com.trascender.framework.util.Util.capitalizeEnumName(super.toString());
		}
	}

	@Override
	public Object getValor(AsocRegAlicuota pAsocRegAlicuota) {
		if (pAsocRegAlicuota.getRegistroAlicuota() instanceof TipoSepultura){
			switch (this.atributoTipoSepultura){
				case MINIMO: return pAsocRegAlicuota.getRegistroAlicuota().getMinimo();
				case VALOR: return pAsocRegAlicuota.getRegistroAlicuota().getValor();
				case CODIGO: return pAsocRegAlicuota.getRegistroAlicuota().getCodigo();
				case ES_PORCENTUAL: return pAsocRegAlicuota.getRegistroAlicuota().isPorcentual();
			}
			return 0d;
		} else {
			throw new IllegalArgumentException("" /* Aca mostrar un msj */);
		}
	}
	
	public AtributoTipoSepultura getAtributoTipoSepultura() {
		return atributoTipoSepultura;
	}

	public void setAtributoTipoSepultura(AtributoTipoSepultura atributoTipoSepultura) {
		this.atributoTipoSepultura = atributoTipoSepultura;
	}
	
	@Override
	public void setNombreAtributo(String pNombreAtributo) {
		this.atributoTipoSepultura = AtributoTipoSepultura.valueOf(pNombreAtributo);
	}
}
