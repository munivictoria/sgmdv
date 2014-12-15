package com.trascender.saic.recurso.persistent;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;



@Entity
@Table(name = "ESTADO_CUENTA_TEMPORAL_TGI")
//@PrimaryKeyJoinColumn(name = "ID_ESTADO_CUENTA_TEMPORAL")
public class EstadoCuentaTemporalTGI extends EstadoCuentaTemporal implements Serializable{

	
	
	private static final long serialVersionUID = 997544003050744776L;
	
	public EstadoCuentaTemporalTGI() {
		super();
	}
	
	public EstadoCuentaTemporalTGI(Long idPersona, Long idParcela, String tipoObligacion, long idRegistroDeuda){
		super();
		setIdPersona(idPersona);
		setIdParcela(idParcela);
		setTipoObligacion(tipoObligacion);
		setIdRegistroDeuda(idRegistroDeuda);
	}
	
	
}
