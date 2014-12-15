package com.trascender.saic.recurso.persistent;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;



/**
 * 
 * @author Maximiliano Fontanini	
 *
 */
@Entity
@Table(name = "ESTADO_CUENTA_TEMPORAL_PFO") 
//@PrimaryKeyJoinColumn(name = "ID_ESTADO_CUENTA_TEMPORAL")
public class EstadoCuentaTemporalPFO extends EstadoCuentaTemporal{

	private static final long serialVersionUID = 3260291198490796081L;

}
