package com.trascender.framework.recurso.persistent;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Representa una relación abstracta a una calle
 */
@Entity
@Table(name="CALLE")
public class RfrCalle implements Serializable {

	public static final long serialVersionUID = 3206535946439918982L;

	@Id
	@Column(name = "ID_CALLE")
	private Long idAbstractCalle=-1L;
	
	@Column(nullable=false)
	private String codigo;
	
	@Column(nullable=false)
	private String nombre;

	/**
	 * Este atributo se usa en domicilio
	 * para mostrar entre que calles esta ubicado el domicilio.
	 */
	@Column(name="ID_CUADRA_ABSTRACTA")
	private Long idCuadraAbstracta;
	
	
	public Long getIdCuadraAbstracta() {
		return idCuadraAbstracta;
	}


	public void setIdCuadraAbstracta(Long pIdCuadraAbstracta) {
		idCuadraAbstracta = pIdCuadraAbstracta;
	}


	public String getNombreCalle(){
		return this.nombre;
	}
	
	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String pCodigo) {
		codigo = pCodigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String pNombre) {
		nombre = pNombre;
	}

	public long getIdAbstractCalle() {
		return idAbstractCalle;
	}

	public void setIdAbstractCalle(long pIdAbstractCalle) {
		idAbstractCalle = pIdAbstractCalle;
	}
	
	
	public String toString() {
		return this.getNombre()!=null?this.getNombre():"";
	}

	
}
