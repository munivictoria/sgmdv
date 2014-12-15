package com.trascender.framework.recurso.persistent.referencia;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.trascender.framework.recurso.persistent.Area;

/**
 * Representa una referencia a un ROL
 * @hibernate.class table = "ROL"
 */

@Entity
@Table(name="ROL")
public class RolRfr implements Serializable {

	private static final long serialVersionUID = -7711869913758378181L;
	
	@Id
	@Column(name="ID_ROL")
	private long idRol;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_AREA")
	private Area area;
	
	/**
	 * @return El area al que hace referencia este rol
	 */
	public Area getArea() {
		return area;
	}

	public void setArea(Area pArea) {
		area = pArea;
	}

	public RolRfr(){
	}

	/**
	 * @return El id del rol al que hace referencia
	 */
	public long getIdRol() {
		return idRol;
	}

	public void setIdRol(long pIdRol) {
		idRol = pIdRol;
	}
}
