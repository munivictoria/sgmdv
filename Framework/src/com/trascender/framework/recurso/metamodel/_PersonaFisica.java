package com.trascender.framework.recurso.metamodel;

import java.util.Date;

import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
import com.trascender.framework.recurso.persistent.validacionDinamica.ComponenteValidacion.Operadores;
import com.trascender.framework.recurso.transients.annotation.Atributo;
import com.trascender.framework.util.Metaclase;

public class _PersonaFisica extends _Persona {
	
	private static final long serialVersionUID = 8383301578405683273L;

	public _PersonaFisica() {
		this.init();
	}
	
	public _PersonaFisica(String pNombre, Class<? extends Metaclase> pClase) {
		super(pNombre, pClase);
		// TODO Auto-generated constructor stub
	}

	public _PersonaFisica(String nombre) {
		super(nombre);
		this.init();
	}
	
	public static _PersonaFisica i(){
		return new _PersonaFisica();
	}
	
	private void init(){
		this.apellido = getNombreCompleto("apellido");
		this.nombre = getNombreCompleto("nombre");
		this.fechaNacimiento = getNombreCompleto("fechaNacimiento");
		this.tipoDocumento = getNombreCompleto("tipoDocumento");
		this.numeroDocumento = getNombreCompleto("numeroDocumento");
		this.edad = getNombreCompleto("edad");
		this.sexo = getNombreCompleto("sexo");
	}
	
	@Atributo(name = "Apellido", visibleEnTabla = true, validacionesAdmitidas={Operadores.IGUAL, Operadores.DISTINTO}, clase = String.class)
	public String apellido = getNombreCompleto("apellido");
	
	@Atributo(name = "Nombre", visibleEnTabla = true, validacionesAdmitidas={Operadores.IGUAL, Operadores.DISTINTO}, clase = String.class )
	public String nombre = getNombreCompleto("nombre");
	
	@Atributo(name = "Fecha de nacimiento", visibleEnTabla = true, clase = Date.class)
	public String fechaNacimiento = getNombreCompleto("fechaNacimiento");
	
	@Atributo(name = "Tipo de documento", visibleEnTabla = true, clase = PersonaFisica.TipoDocumento.class)
	public String tipoDocumento = getNombreCompleto("tipoDocumento");
	
	@Atributo(name = "Número de documento", visibleEnTabla = true, clase = String.class)
	public String numeroDocumento = getNombreCompleto("numeroDocumento");
	
	@Atributo(name = "Edad", visibleEnTabla = true, clase = String.class)
	public String edad = getNombreCompleto("edad");
	
	@Atributo(name = "Sexo", visibleEnTabla = true, clase = PersonaFisica.Sexo.class)
	public String sexo = getNombreCompleto("sexo");
	
	@Atributo(name = "Estado civil", visibleEnTabla = true, clase = PersonaFisica.EstadoCivil.class)
	public String estadoCivil = getNombreCompleto("estado");
	
	@Atributo(name = "Jubilado", visibleEnTabla = true, clase = boolean.class)
	public String jubilado = getNombreCompleto("jubilado");
	
	@Atributo(name = "Lista de atributos dinamicos", visibleEnTabla = true, clase = AtributoDinamico.class)
	public _AtributoDinamico listaAtributosDinamicos = validarInstanciacion(_AtributoDinamico.class, "listaAtributosDinamicos");
}
