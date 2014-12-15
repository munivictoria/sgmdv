package com.trascender.framework.recurso.metamodel;

import com.trascender.framework.recurso.persistent.dinamicos.OpcionAtributoDinamicoListado;
import com.trascender.framework.recurso.persistent.dinamicos.PlantillaAtributoDinamico.Tipo;
import com.trascender.framework.recurso.transients.annotation.Atributo;
import com.trascender.framework.util.Metaclase;

public class _PlantillaAtributoDinamico extends Metaclase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -934521489037936322L;
	
	public static _PlantillaAtributoDinamico i(){
		return new _PlantillaAtributoDinamico();
	}
	
	
	
	public _PlantillaAtributoDinamico(String pNombre,
			Class<? extends Metaclase> pClase) {
		super(pNombre, pClase);
	}



	public _PlantillaAtributoDinamico(String nombre) {
		super(nombre);
		this.init();
	}
	
	public _PlantillaAtributoDinamico(){
		this.init();
	}
	
	private void init(){
	}
	
	@Atributo(name = "Id plantilla atributo dinamico", visibleEnTabla = true, clase = long.class)
	public String idPlantillaAtributoDinamico = this.getNombreCompleto("idPlantillaAtributoDinamico");
	
	@Atributo(name = "Nombre", visibleEnTabla = true, clase = String.class)
	public String nombre = this.getNombreCompleto("nombre");
	
	@Atributo(name = "Id recurso", visibleEnTabla = true, clase = Long.class)
	public String idRecurso = this.getNombreCompleto("idRecurso");
	
	@Atributo(name = "Tipo", visibleEnTabla = true, clase = Tipo.class)
	public String tipo = this.getNombreCompleto("tipo");
	
	@Atributo(name = "Lista opciones", visibleEnTabla = true, clase = OpcionAtributoDinamicoListado.class)
	public _OpcionAtributoDinamicoListado listaOpciones = validarInstanciacion(_OpcionAtributoDinamicoListado.class, "listaOpciones");
//	public _OpcionAtributoDinamicoListado listaOpciones = new _OpcionAtributoDinamicoListado(getNombreCompleto("listaOpciones"));
	
	@Atributo(name = "Requerido", visibleEnTabla = true, clase = boolean.class)
	public String requerido = this.getNombreCompleto("requerido");
	
	@Atributo(name = "Busqueda", visibleEnTabla = true, clase = boolean.class)
	public String busqueda = this.getNombreCompleto("busqueda");

}
