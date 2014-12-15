package com.trascender.compras.recurso.persistent.suministros;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.trascender.framework.recurso.persistent.DigestoMunicipal;

@Embeddable
public class Adjudicacion implements Serializable{
	
	public static final long serialVersionUID = 336266361732782072L;
	
	private Date fecha;
	
	@ManyToOne
	private DigestoMunicipal digestoMunicipal;
	
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public DigestoMunicipal getDigestoMunicipal() {
		return digestoMunicipal;
	}
	public void setDigestoMunicipal(DigestoMunicipal digestoMunicipal) {
		this.digestoMunicipal = digestoMunicipal;
	}
	
	public String toString(){
		return "";
	}

}
