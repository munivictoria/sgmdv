package com.trascender.habilitaciones.recurso.persistent;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
import com.trascender.framework.recurso.persistent.dinamicos.PlantillaAtributoDinamico;
import com.trascender.habilitaciones.recurso.persistent.tasaMenor.RegistroValuadoTasaMenor;
import com.trascender.habilitaciones.util.UtilTipoParametroDinamico;

@Entity
@DiscriminatorValue(value = "DINAMICO")
public class TipoParametroDinamico extends TipoParametro{

	public static final long serialVersionUID = 359081278766973210L;
	
	@Column(name = "ATRIBUTO")
	private String nombre;

	public TipoParametroDinamico(){
	}
	
	public TipoParametroDinamico(PlantillaAtributoDinamico pPlantilla){
		this(pPlantilla.getNombre());
	}
	
	public TipoParametroDinamico(String pNombre){
		super();
		this.nombre = pNombre;
		setNombreVariable(pNombre);
	}
	
	@Override
	public Object getValor(DocHabilitanteEspecializado pDocHabilitanteEspecializado)
			throws Exception {
		Object valor = UtilTipoParametroDinamico.getValor(pDocHabilitanteEspecializado.getListaAtributosDinamicos(), this.nombre);
		return valor;
	}
	
//	/**
//	 * Busca un Atributo Dinamico por nombre en la lista de atribos dinámicos del documento
//	 * o en la lista de atributos dinamicos del registro valuado del periodo correspondiente.
//	 * @param pDocumento
//	 * @return
//	 */
//	private AtributoDinamico<?> getAtributoPorNombre(DocHabilitanteEspecializado pDocumento){
//		Object valor = UtilTipoParametroDinamico.getValor(pDocumento.getListaAtributosDinamicos(), this.nombre);
//		
//		
//		for (AtributoDinamico<?> cadaAtributo : pDocumento.getListaAtributosDinamicos()){
//			if (formatearString(cadaAtributo.getNombre()).equals(this.nombre)){
//				return cadaAtributo;
//			}
//		}
//		List<RegistroValuado> locListaRegistro = pDocumento.getRegistroValuado(this.getCuotaLiquidacion());
//		if (!locListaRegistro.isEmpty()){
//			for (RegistroValuado cadaRegistroValuado : locListaRegistro){
//				if (cadaRegistroValuado instanceof RegistroValuadoTasaMenor){
//					RegistroValuadoTasaMenor cadaRegistroValuadoTasaMenor = (RegistroValuadoTasaMenor) cadaRegistroValuado;
//					for (AtributoDinamico<?> cadaAtributo : cadaRegistroValuadoTasaMenor.getListaAtributosDinamicos()){
//						if (formatearString(cadaAtributo.getNombre()).equals(this.nombre)){
//							return cadaAtributo;
//						}
//					}
//				}
//			}
//		}
//		return valor;
//	}
//	
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
