/**
 * 
 */
package com.trascender.contabilidad.recurso.persistent;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.trascender.habilitaciones.recurso.persistent.TipoModificador;

@Entity
@Table(name = "CUENTA_TIPO_MODIFICADOR")
@PrimaryKeyJoinColumn(name = "ID_ASOCIACION_CUENTA")
public class CuentaModificador extends AsociacionCuenta {

	public static final long serialVersionUID = -8259071776134714051L;
	
	@ManyToOne
	@JoinColumn(name = "ID_TIPO_MODIFICADOR")
	private TipoModificador tipoModificador;
	
	public TipoModificador getTipoModificador() {
		return tipoModificador;
	}

	public void setTipoModificador(TipoModificador tipoModificador) {
		this.tipoModificador = tipoModificador;
	}
	
}
