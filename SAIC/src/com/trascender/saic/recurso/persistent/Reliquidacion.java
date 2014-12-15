package com.trascender.saic.recurso.persistent;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.trascender.framework.recurso.persistent.DigestoMunicipal;


/**
 * 
 * @hibernate.joined-subclass table = "RELIQUIDACION" 
 * @hibernate.joined-subclass-key column = "ID_REGISTRO_CANCELACION" 
 */

@Entity
@Table(name = "RELIQUIDACION")
@PrimaryKeyJoinColumn(columnDefinition="ID_REGISTRO_CANCELACION")
public class Reliquidacion extends RegistroCancelacion {


	public static final long serialVersionUID = 7652808622281826398L;
	
	
	public static Reliquidacion getInstance(LiquidacionTasa pLiquidacionTasa){
		Reliquidacion locReliquidacion = new Reliquidacion();
		locReliquidacion.setDeuda(pLiquidacionTasa);
		pLiquidacionTasa.setRegistroCancelacion(locReliquidacion);
		//Le comento esta linea porque esta trayendo problemas para 
		//obtener el estado de la Liquidacion luego
//		pLiquidacionTasa.setEstado(EstadoRegistroDeuda.RELIQUIDADA);
		return locReliquidacion;
	}
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch=FetchType.LAZY )
	@JoinColumn(name = "ID_NUEVA_LIQUIDACION")
	private LiquidacionTasa liquidacionTasa;
	
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch=FetchType.LAZY )
	@JoinColumn(name = "ID_DIGESTO_MUNICIPAL")
	private DigestoMunicipal digestoMunicipal; //Requerimiento 843:

	
	/**
	 * @hibernate.many-to-one column = "ID_DIGESTO_MUNICIPAL"
	 */
	public DigestoMunicipal getDigestoMunicipal() {
		return digestoMunicipal;
	}

	public void setDigestoMunicipal(DigestoMunicipal digestoMunicipal) {
		this.digestoMunicipal = digestoMunicipal;
	}

	/**
	 * @hibernate.many-to-one column = "ID_NUEVA_LIQUIDACION" cascade = "none" 
	 */
	public LiquidacionTasa getLiquidacionTasa() {
		return liquidacionTasa;
	}

	public void setLiquidacionTasa(LiquidacionTasa liquidacionTasa) {
		this.liquidacionTasa = liquidacionTasa;
	}
}
