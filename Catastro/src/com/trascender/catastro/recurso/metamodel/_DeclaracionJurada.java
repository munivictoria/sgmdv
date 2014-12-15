package com.trascender.catastro.recurso.metamodel;

import java.util.Date;

import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.catastro.recurso.persistent.RegistroMejora;
import com.trascender.framework.recurso.transients.annotation.Atributo;
import com.trascender.framework.util.MetaClase;

public class _DeclaracionJurada extends MetaClase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -437095286439890825L;
	
	public static _DeclaracionJurada i(){
		return new _DeclaracionJurada();
	}
	
	public _DeclaracionJurada(String nombre, Class<? extends MetaClase> clase) {
		super(nombre, clase);
	}

	public _DeclaracionJurada(String nombre){
		super(nombre);
		this.init();
	}
	
	public _DeclaracionJurada(){
		this.init();
	}
	
	private void init(){}
	
	@Atributo(name = "Id declaracion jurada", visibleEnTabla = true, clase = long.class)
	public String idDeclaracionJurada = this.getNombreCompleto("idDeclaracionJurada");
	
	@Atributo(name = "Numero", visibleEnTabla = true, clase = String.class)
	public String numero = this.getNombreCompleto("numero");
	
	@Atributo(name = "Fecha inscripcion", visibleEnTabla = true, clase = Date.class)
	public String fechaInscripcion = this.getNombreCompleto("fechaInscripcion");
	
	@Atributo(name = "Lista de registros mejora", visibleEnTabla = true, clase = RegistroMejora.class)
	public _RegistroMejora listaRegistrosMejora = validarInstanciacion(_RegistroMejora.class, "listaRegistrosMejora");
	
	@Atributo(name = "Parcela", visibleEnTabla = true, clase = Parcela.class)
	public _Parcela parcela = validarInstanciacion(_Parcela.class, "parcela");

}
