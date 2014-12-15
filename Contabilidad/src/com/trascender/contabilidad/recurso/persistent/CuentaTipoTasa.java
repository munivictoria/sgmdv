/**
 * 
 */
package com.trascender.contabilidad.recurso.persistent;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.trascender.habilitaciones.recurso.persistent.TipoTasa;

@Entity
@Table(name = "CUENTA_TIPO_TASA")
@PrimaryKeyJoinColumn(name = "ID_ASOCIACION_CUENTA")
public class CuentaTipoTasa extends AsociacionCuenta {

	public static final long serialVersionUID = 2722400440085209844L;
	
	@ManyToOne
	@JoinColumn(name = "ID_TIPO_TASA")
	private TipoTasa tipoTasa;
	
	public TipoTasa getTipoTasa() {
		return tipoTasa;
	}

	public void setTipoTasa(TipoTasa tipoTasa) {
		this.tipoTasa = tipoTasa;
	}

}
