package com.trascender.habilitaciones.recurso.persistent;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.trascender.framework.recurso.persistent.dinamicos.PlantillaAtributoDinamico;
import com.trascender.habilitaciones.recurso.persistent.transito.DocumentoAutomotor;
import com.trascender.habilitaciones.util.UtilTipoParametroDinamico;

@Entity
@DiscriminatorValue(value = "PARAMETRO_VEHICULO_DINA")
public class TipoParametroVehiculoDinamico extends TipoParametro{
	
	private static final long serialVersionUID = 2159894960236594764L;
	
	@Column(name = "ATRIBUTO")
	private String nombre;

	public TipoParametroVehiculoDinamico(){
	}
	
	public TipoParametroVehiculoDinamico(PlantillaAtributoDinamico pPlantilla){
		this(pPlantilla.getNombre());
	}
	
	public TipoParametroVehiculoDinamico(String pNombre){
		super();
		this.nombre = pNombre;
		setNombreVariable(pNombre);
	}
	
	@Override
	public Object getValor(DocHabilitanteEspecializado pDocHabilitanteEspecializado)
			throws Exception {
		return UtilTipoParametroDinamico.getValor(((DocumentoAutomotor)pDocHabilitanteEspecializado).getVehiculo().getListaAtributosDinamicos(), this.getNombreVariable().substring(0, getNombreVariable().lastIndexOf("_")));
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
