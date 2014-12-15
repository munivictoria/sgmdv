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
@Table(name = "ESTADO_CUENTA_TEMPORAL_SHPS")
//@PrimaryKeyJoinColumn(name = "ID_ESTADO_CUENTA_TEMPORAL")
public class EstadoCuentaTemporalSHPS extends EstadoCuentaTemporal{

	private static final long serialVersionUID = 2279038986128964264L;
	
	public EstadoCuentaTemporalSHPS(){
		
	}
	
	public EstadoCuentaTemporalSHPS(Long idPersona, Long idParcela, String tipoObligacion){
		super();
		setIdPersona(idPersona);
		setIdParcela(idParcela);
		setTipoObligacion(tipoObligacion);
	}

}
