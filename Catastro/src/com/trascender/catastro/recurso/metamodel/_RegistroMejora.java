package com.trascender.catastro.recurso.metamodel;

import com.trascender.catastro.recurso.persistent.Categoria;
import com.trascender.catastro.recurso.persistent.CoeficienteDepreciacion;
import com.trascender.catastro.recurso.persistent.DeclaracionJurada;
import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.framework.recurso.transients.annotation.Atributo;
import com.trascender.framework.util.MetaClase;

public class _RegistroMejora extends MetaClase {
	private static final long serialVersionUID = -7012632482678469770L;
	
	public static _RegistroMejora i(){
		return new _RegistroMejora();
	}
	
	public _RegistroMejora(String nombre, Class<? extends MetaClase> clase) {
		super(nombre, clase);
	}

	public _RegistroMejora(String nombre){
		super(nombre);
		this.init();
	}
	
	public _RegistroMejora(){
		this.init();
	}
	
	private void init(){}
	
	@Atributo(name = "Id registro mejora", visibleEnTabla = true, clase = long.class)
	public String idRegistroMejora = this.getNombreCompleto("idRegistroMejora");
	
	@Atributo(name = "Superficie", visibleEnTabla = true, clase = Double.class)
	public String superficie = this.getNombreCompleto("superficie");
	
	@Atributo(name = "Año construccion", visibleEnTabla = true, clase = Integer.class)
	public String anioConstruccion = this.getNombreCompleto("anioConstruccion");
	
	@Atributo(name = "Estado mejora", visibleEnTabla = true, clase = CoeficienteDepreciacion.EstadoCoeficiente.class)
	public String estadoMejora = this.getNombreCompleto("estadoMejora");
	
	@Atributo(name = "Parcela", visibleEnTabla = true, clase = Parcela.class)
	public _Parcela parcela = validarInstanciacion(_Parcela.class, "parcela");
//	public _Parcela parcela = new _Parcela(getNombreCompleto("parcela"));
	
	@Atributo(name = "Declaracion jurada", visibleEnTabla = true, clase = DeclaracionJurada.class)
	public _DeclaracionJurada declaracionJurada = validarInstanciacion(_DeclaracionJurada.class, "declaracionJurada");
	
	@Atributo(name = "Activo", visibleEnTabla = true, clase = boolean.class)
	public String activo = this.getNombreCompleto("activo");
	
	@Atributo(name = "Id de calle", visibleEnTabla = true, clase = Categoria.class)
	public _Categoria categoria = validarInstanciacion(_Categoria.class, "categoria");

}
