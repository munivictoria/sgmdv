package com.trascender.habilitaciones.recurso.persistent;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.trascender.framework.recurso.persistent.dinamicos.PlantillaAtributoDinamico;
import com.trascender.habilitaciones.util.UtilTipoParametroDinamico;

@Entity
@DiscriminatorValue(value = "PARAMETRO_PERSONA_FISICA_DINA")
public class TipoParametroPersonaFisicaDinamico extends TipoParametro{
	
	private static final long serialVersionUID = -6004770264438735781L;
	
	@Column(name = "ATRIBUTO")
	private String nombre;

	public TipoParametroPersonaFisicaDinamico(){
	}
	
	public TipoParametroPersonaFisicaDinamico(PlantillaAtributoDinamico pPlantilla){
		this(pPlantilla.getNombre());
	}
	
	public TipoParametroPersonaFisicaDinamico(String pNombre){
		super();
		this.nombre = pNombre;
		setNombreVariable(pNombre);
	}
	
	@Override
	public Object getValor(DocHabilitanteEspecializado pDocHabilitanteEspecializado)
			throws Exception {
		return UtilTipoParametroDinamico.getValor(pDocHabilitanteEspecializado.getObligacion().getPersona().getListaAtributosDinamicos(), this.getNombreVariable());
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public void setNombreAtributo(String pNombreAtributo) {
		
	}
}
