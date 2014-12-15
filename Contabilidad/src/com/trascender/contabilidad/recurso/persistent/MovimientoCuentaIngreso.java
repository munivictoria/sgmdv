package com.trascender.contabilidad.recurso.persistent;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "MOVIMIENTO_CUENTA_INGRESO")
public class MovimientoCuentaIngreso extends MovimientoCuenta{
	private static final long serialVersionUID = -1861538521093670933L;

}
