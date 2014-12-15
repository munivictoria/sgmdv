package com.trascender.framework.recurso.metamodel;

import java.util.Date;

import com.trascender.framework.recurso.persistent.DigestoMunicipal.Ambito;
import com.trascender.framework.recurso.persistent.DigestoMunicipal.EjeTematico;
import com.trascender.framework.recurso.persistent.DigestoMunicipal.Estado;
import com.trascender.framework.recurso.persistent.DigestoMunicipal.Tipo;
import com.trascender.framework.recurso.transients.annotation.Atributo;
import com.trascender.framework.util.Metaclase;

public class _DigestoMunicipal extends Metaclase{

	private static final long serialVersionUID = 7639883183496912691L;
	
	
	public static _DigestoMunicipal i(){
		return new _DigestoMunicipal();
	}
	
	public _DigestoMunicipal(String pNombre, Class<? extends Metaclase> pClase) {
		super(pNombre, pClase);
		// TODO Auto-generated constructor stub
	}

	public _DigestoMunicipal(String nombre) {
		super(nombre);
		this.init();
	}
	
	public _DigestoMunicipal(){
		this.init();
	}
	
	private void init(){
//		this.tipo = getNombreCompleto("tipo");
	}
	
	@Atributo(name = "Id digesto municipal", visibleEnTabla = true, clase = Long.class)
	public String idDigestoMunicipal = this.getNombreCompleto("idDigestoMunicipal");
	
	@Atributo(name = "Tipo", visibleEnTabla = true, clase = Tipo.class)
	public String tipo = getNombreCompleto("tipo");
	
	@Atributo(name = "Estado", visibleEnTabla = true, clase = Estado.class)
	public String estado = getNombreCompleto("estado");

	@Atributo(name = "Eje tematico", visibleEnTabla = true, clase = EjeTematico.class)
	public String ejeTematico = getNombreCompleto("ejeTematico");
	
	@Atributo(name = "Ambito", visibleEnTabla = true, clase = Ambito.class)
	public String ambito = getNombreCompleto("ambito");
	
	@Atributo(name = "Descripcion", visibleEnTabla = true, clase = String.class)
	public String descripcion = getNombreCompleto("descripcion");
	
	@Atributo(name = "Fecha", visibleEnTabla = true, clase = Date.class)
	public String fecha = getNombreCompleto("fecha");
	
	@Atributo(name = "Ultimo cambio de estado", visibleEnTabla = true, clase = String.class)
	public String ultimoCambioDeEstado = getNombreCompleto("ultimoCambioDeEstado");
	
	@Atributo(name = "Numero", visibleEnTabla = true, clase = Integer.class)
	public String numero = getNombreCompleto("numero");

}
