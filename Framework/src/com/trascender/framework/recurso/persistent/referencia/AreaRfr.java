package com.trascender.framework.recurso.persistent.referencia;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @hibernate.class table = "AREA"
 * mutable = "false"
 */

@Entity
@Table(name="AREA")
public class AreaRfr implements Serializable{

	private static final long serialVersionUID = -2964012510465883075L;
	
	@Id
	private long idArea;
	
	/**
	 * @return El id del area al que hace referencia
	 */
	public long getIdArea() {
		return idArea;
	}

	public void setIdArea(long pIdArea) {
		idArea = pIdArea;
	}

	public void areaRfr(){
	}
}
