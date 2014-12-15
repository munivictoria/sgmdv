package com.trascender.framework.recurso.metamodel;

import com.trascender.framework.recurso.persistent.dinamicos.PlantillaAtributoDinamico;
import com.trascender.framework.recurso.transients.annotation.Atributo;
import com.trascender.framework.util.Metaclase;

public class _AtributoDinamico extends Metaclase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1498050083127154374L;
	
	public static _AtributoDinamico i(){
		return instanciar(_AtributoDinamico.class);
	}
	
	@Atributo(name = "Id atributo dinamico", visibleEnTabla = true, clase = long.class)
	public String idAtributoDinamico = this.getNombreCompleto("idAtributoDinamico");
	
	@Atributo(name = "Id recurso", visibleEnTabla = true, clase = long.class)
	public String idRecurso = this.getNombreCompleto("idRecurso");
	
	@Atributo(name = "Id entidad", visibleEnTabla = true, clase = long.class)
	public String idEntidad = this.getNombreCompleto("idEntidad");
	
	@Atributo(name = "Plantilla", visibleEnTabla = true, clase = PlantillaAtributoDinamico.class)
	public _PlantillaAtributoDinamico plantilla = validarInstanciacion(_PlantillaAtributoDinamico.class, "plantilla");
//	_______________public _PlantillaAtributoDinamico plantilla = validarInstanciacion(_PlantillaAtributoDinamico.class, "plantilla");

}
