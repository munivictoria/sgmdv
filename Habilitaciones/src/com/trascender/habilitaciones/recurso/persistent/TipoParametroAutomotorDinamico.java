package com.trascender.habilitaciones.recurso.persistent;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.trascender.framework.recurso.persistent.dinamicos.PlantillaAtributoDinamico;
import com.trascender.habilitaciones.util.UtilTipoParametroDinamico;

@Entity
@DiscriminatorValue(value = "PARAMETRO_AUTOMOTOR_DINA")
public class TipoParametroAutomotorDinamico extends TipoParametro{
	private static final long serialVersionUID = 6241809328751231830L;

	@Column(name = "ATRIBUTO")
	private String nombre;

	public TipoParametroAutomotorDinamico(){
	}
	
	public TipoParametroAutomotorDinamico(PlantillaAtributoDinamico pPlantilla){
		this(pPlantilla.getNombre());
	}
	
	public TipoParametroAutomotorDinamico(String pNombre){
		super();
		this.nombre = pNombre;
		setNombreVariable(pNombre);
	}
	
	@Override
	public Object getValor(DocHabilitanteEspecializado pDocHabilitanteEspecializado)
			throws Exception {
		return UtilTipoParametroDinamico.getValor(pDocHabilitanteEspecializado.getListaAtributosDinamicos(), this.nombre);
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
