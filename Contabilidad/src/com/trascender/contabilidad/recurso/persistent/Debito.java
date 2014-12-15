/**
 * 
 */
package com.trascender.contabilidad.recurso.persistent;

import java.text.DateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "DEBITO")
@PrimaryKeyJoinColumn(name = "ID_MOVIMIENTO_BANCARIO")
public class Debito extends MovimientoBancario{

	public static final long serialVersionUID = -3895337094080743781L;
	
	private Date fecha;

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	
	@Override
	public String toString(){
		DateFormat locDateFormat = DateFormat.getDateInstance(DateFormat.SHORT);
		return "Débito" +"  "+ this.getImporte()+" ("+locDateFormat.format(this.fecha)+")";
	}
	
	
}
