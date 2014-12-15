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
@Table(name = "ESTADO_CUENTA_TEMPORAL_OSP")
//@PrimaryKeyJoinColumn(name = "ID_ESTADO_CUENTA_TEMPORAL")
public class EstadoCuentaTemporalOSP extends EstadoCuentaTemporal{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5702894288308957012L;
	
	public EstadoCuentaTemporalOSP(){
		
	}
	
	public EstadoCuentaTemporalOSP(Long idPersona, Long idParcela, String tipoObligacion, long idRegistroDeuda){
		super();
		setIdPersona(idPersona);
		setIdParcela(idParcela);
		setTipoObligacion(tipoObligacion);
		setIdRegistroDeuda(idRegistroDeuda);
	}
	
	
}
