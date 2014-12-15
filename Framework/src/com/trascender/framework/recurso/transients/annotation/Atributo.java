package com.trascender.framework.recurso.transients.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.trascender.framework.recurso.persistent.validacionDinamica.ComponenteValidacion.Operadores;

/**
 *Anotacion utilizada para marcar la configuracion de los atributos de las metaclases. 
 * @author jsantacruz
 */
@Target(value={ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Atributo {
	
	/**
	 * El nombre que se va a mostrar el atributo.
	 */
	public String name();
	
	/**
	 * Indica si la propiedad se puede usar como columna <br>
	 * En una tabla de la vista. 
	 */
	public boolean visibleEnTabla() default false;
	
	/**
	 * Indica los operadores que se puede aplicar una validacion dinamica.
	 */
	public Operadores[] validacionesAdmitidas() default {Operadores.IGUAL, Operadores.DISTINTO};
	
	/**
	 * Indica la clase a la que pertenece el Atributo.
	 * @return
	 */
	public Class clase();
	
}
