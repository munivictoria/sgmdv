package com.trascender.framework.util;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.naming.InitialContext;

import ar.trascender.criterio.interfaces.Restringible;

import com.trascender.framework.recurso.persistent.validacionDinamica.ComponenteValidacion.Operadores;
import com.trascender.framework.recurso.transients.annotation.Atributo;
import com.trascender.framework.system.interfaces.SystemMetaclase;

public abstract class Metaclase implements Serializable, Restringible{
	
	private static final long serialVersionUID = -5509839702828670402L;
	
	private SystemMetaclase systemMetaclase;
	
	protected abstract <T extends Metaclase> T getClase();
	
	protected synchronized static <T extends Metaclase> T i(){		
		try{
			SystemMetaclase systemMetaclase = (SystemMetaclase) new InitialContext().lookup(SystemMetaclase.JNDI_NAME);
			Metaclase metaClase = systemMetaclase.getMapaMetaclases().get(getClase().getName());
			if (metaClase == null) {
				metaClase = getClase().newInstance();
				systemMetaclase.getMapaMetaclases().put(getClase().getName(), metaClase);
				metaClase.systemMetaclase = systemMetaclase;
			}
			return (T) metaClase;
		} catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	private void checkSystemMetaclase(){
		if (this.systemMetaclase == null){
			
		}
	}
	
	protected String nombrePropiedad;
	
	public Metaclase(){
	}

	public String getNombre() {
		return nombrePropiedad;
	}

	public void setNombre(String nombre) {
		this.nombrePropiedad = nombre;
	}
	
	public String getNombreCompleto(String pAtributo){
		return this.nombrePropiedad == null ? pAtributo : this.nombrePropiedad+"."+pAtributo;
	}

	public String toString(){
		return nombrePropiedad;
	}

	protected <T extends Metaclase> T validarInstanciacion(Class<T> claseValidar, String nombre){
		try {
			Metaclase locMetaclase = systemMetaclase.getMapaMetaclases().get(claseValidar.getName());
			if (locMetaclase == null) {
				locMetaclase = claseValidar.newInstance();
				systemMetaclase.getMapaMetaclases().put(claseValidar.getName(), locMetaclase);
				locMetaclase.systemMetaclase = systemMetaclase;
			}
			locMetaclase.setNombre(this.getNombreCompleto(nombre));
			return (T) locMetaclase;
		} catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public void getFieldsConXAnotacion(){
		
	}
	
	/**
	 * Devuelve la lista de Atributos que coincidan con los parametros dados.
	 * @param pName
	 * @param pVisibleTabla
	 * @param pOperdaor
	 * @return Array de String[];
	 */
	public String[] getPropiedadAsString(String pName, Boolean pVisibleTabla, Operadores... pOperdaor){
		Field[] listaAtributos = getPropiedad(pName, pVisibleTabla, pOperdaor);
		String[] listaRetorno = new String[listaAtributos.length];
		int index = 0;
		while ( index < listaAtributos.length) {
			listaRetorno[index] = listaAtributos[index].getName();
			index++;
		}
		
		return listaRetorno;
	}
	
	/**
	 * Devuelve la lista de Atributos que coincidan con los parametros dados.
	 * @param pName
	 * @param pVisibleTabla
	 * @param pOperdaor
	 * @return Array de Field[];
	 */
	public Field[] getPropiedad(String pName, Boolean pVisibleTabla, Operadores... pOperdaor){
		Set<Field> listaRetorno = new HashSet<Field>();
		Field[] listaFields = this.getClass().getDeclaredFields();
		Atributo locAnotation;
		for(Field cadaAtributo : listaFields){
			if((locAnotation = cadaAtributo.getAnnotation(Atributo.class)) != null){
				if( ((pName != null && cadaAtributo.getName().equals(pName) || pName == null)) 
						&& ((pVisibleTabla != null && locAnotation.visibleEnTabla() == pVisibleTabla.booleanValue() || pVisibleTabla == null))
						&& (( pOperdaor != null && this.anotacionContieneOperador(locAnotation, pOperdaor)) || pOperdaor == null) ){
					listaRetorno.add(cadaAtributo);
				}
			}
		}
		
		return listaRetorno.toArray(new Field[listaRetorno.size()]);
	}
	
	/**
	 * Verifica que una anotacion contenga al menos uno de los operadores pasados por parametro.
	 * @param pAnotation
	 * @param pOperdaor
	 * @return
	 */
	private boolean anotacionContieneOperador(Atributo pAnotation, Operadores[] pOperdaor) {
		if(pOperdaor == null || pOperdaor.length < 1){
			return false;
		}
		
		for(Operadores cadaOperadorPosible : pAnotation.validacionesAdmitidas()){
			for(Operadores cadaOperador : pOperdaor){
				if(cadaOperador.equals(cadaOperadorPosible)){
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Devuelve la Clase nativa segun el parametro.
	 * @param pName
	 * @return
	 */
	public Class getPropertyClass(String pName){
		Field[] listaFields = this.getPropiedad(pName, null, null);
		if(listaFields.length == 1){
			return listaFields[0].getAnnotation(Atributo.class).clase();
		}else {
			System.out.println("No se encontro Ninguna propiedad o devolvio mas de un resultado con el nombre: " + pName);
			return null;
		}
	}
	
	private static Map<String, Metaclase> mapaInstancias = new HashMap<String, Metaclase>();

	public static <T extends Metaclase> T getInstancia(Class<T> clase) {
		T instancia = (T) mapaInstancias.get(clase.getName());
		if(instancia == null) {
			try {
				instancia = clase.newInstance();
				mapaInstancias.put(clase.getName(), instancia);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return instancia;
	}

	protected <T extends Metaclase, E extends Metaclase> AsociacionMetaclase<E, T> getAsociacion(String pNombre, Class<E> claseRefenciador, Class<T> claseReferencia) {
		T referencia = getInstancia(claseReferencia);
		E referenciador = getInstancia(claseRefenciador);
		AsociacionMetaclase<E, T> locAsociacion = new AsociacionMetaclase<E, T>();
		locAsociacion.nombre = pNombre;
		locAsociacion.referencia = referencia;
		locAsociacion.referenciador = referenciador;
		return locAsociacion;
	}

	// De restringible.
	public String getString(){
		return this.getNombreCompleto(nombrePropiedad);
	}

	
}
