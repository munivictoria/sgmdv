package com.trascender.catastro.recurso.metamodel;

import com.trascender.catastro.recurso.persistent.Categoria;
import com.trascender.catastro.recurso.persistent.CoeficienteDepreciacion.EstadoCoeficiente;
import com.trascender.framework.recurso.transients.annotation.Atributo;
import com.trascender.framework.util.MetaClase;

public class _CoeficienteDepreciacion extends MetaClase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1946713110126070750L;
	
	public static _CoeficienteDepreciacion i(){
		return new _CoeficienteDepreciacion();
	}
	
	public _CoeficienteDepreciacion(String nombre,
			Class<? extends MetaClase> clase) {
		super(nombre, clase);
		// TODO Auto-generated constructor stub
	}

	public _CoeficienteDepreciacion(String nombre){
		super(nombre);
		this.init();
	}
	
	public _CoeficienteDepreciacion(){
		this.init();
	}
	
	private void init(){}
	
	@Atributo(name = "Id coeficiente depreciacion", visibleEnTabla = true, clase = long.class)
	public String idCoeficienteDepreciacion = this.getNombreCompleto("idCoeficienteDepreciacion");
	
	@Atributo(name = "Valor", visibleEnTabla = true, clase = EstadoCoeficiente.class)
	public String valor = this.getNombreCompleto("valor");
	
	@Atributo(name = "Años de existencia", visibleEnTabla = true, clase = Integer.class)
	public String aniosExistencia = this.getNombreCompleto("aniosExistencia");
	
	@Atributo(name = "Categoria", visibleEnTabla = true, clase = Categoria.class)
	public _Categoria categoria = validarInstanciacion(_Categoria.class, "categoria");
	
}
