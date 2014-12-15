package com.trascender.contabilidad.recurso.persistent;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "MOVIMIENTO_CUENTA_EGRESO")
@PrimaryKeyJoinColumn(name = "ID_MOVIMIENTO_CUENTA")
public class MovimientoCuentaEgreso extends MovimientoCuenta{
	
	private static final long serialVersionUID = 8823976489768478551L;

}
