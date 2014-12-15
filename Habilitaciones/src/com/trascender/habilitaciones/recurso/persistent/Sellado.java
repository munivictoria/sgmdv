package com.trascender.habilitaciones.recurso.persistent;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


/**
 * 
 * @author Mariano Lusardi
 * @hibernate.joined-subclass table = "SELLADO"
 * @hibernate.joined-subclass-key column = "ID_DOC_HABILITANTE" 
 *
 */

@Entity
@Table(name = "SELLADO")
@PrimaryKeyJoinColumn(name = "ID_DOC_HABILITANTE")
public class Sellado extends DocHabilitante {

	/**
	 * 
	 */
	public static final long serialVersionUID = 3756279788634943619L;
	private Double valor=0d;
	private String descripcion;
	private boolean pagado=false;
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public boolean isPagado() {
		return pagado;
	}
	public void setPagado(boolean pagado) {
		this.pagado = pagado;
	}
	
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	} 
}
