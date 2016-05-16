package com.trascender.framework.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.trascender.framework.recurso.persistent.PersonaFisica;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import ar.trascender.util.ReflectionUtils;

public class JRCodesoftBeanCollectionDataSource implements JRDataSource {

	private List<Linea> listaLineas = new ArrayList<Linea>();
	private int lineaActual = -1;
	
	public static void main(String... args) {
		JRCodesoftBeanCollectionDataSource ds = new JRCodesoftBeanCollectionDataSource();
		
		List<PersonaFisica> personas = new ArrayList<PersonaFisica>();
		PersonaFisica persona = new PersonaFisica();
		persona.setNombre("Nico");
		persona.setEdad(20);
		personas.add(persona);
		
		persona = new PersonaFisica();
		persona.setNombre("Fernando");
		persona.setEdad(15);
		personas.add(persona);
		
		ds.addLinea(personas, "Nombre", "nombre", "valor", "edad")
			.addLinea("Nombre", "Intereses por actualizacion", "valor", 20);
		
		for (Linea cadaLinea : ds.listaLineas) {
			System.out.println(cadaLinea.getMapa());
		}
	}
	
	public JRCodesoftBeanCollectionDataSource addLinea(Collection<?> coleccion, String... campos) {
		for (Object objeto : coleccion) {
			Linea nuevaLinea = new Linea();
			for (int i = 0 ; i < campos.length ; i = i+2) {
				Object valor = getValorONulo(objeto, campos[i+1]);
				nuevaLinea.getMapa().put(campos[i], valor);
			}
			this.listaLineas.add(nuevaLinea);
		}
		return this;
	}
	
	private Object getValorONulo(Object objeto, String field) {
		try {
			Method metodo = ReflectionUtils.getGeter(objeto.getClass(), field);
			return metodo.invoke(objeto);
		} catch (Exception e) {
			return null;
		}
	}
	
	public JRCodesoftBeanCollectionDataSource addLinea(Object... objetos) {
		Linea locLinea = new Linea();
		for (int i = 0 ; i < objetos.length ; i = i+2) {
			locLinea.getMapa().put(objetos[i].toString(), objetos[i+1]);
		}
		this.listaLineas.add(locLinea);
		return this;
	}
	
	@Override
	public Object getFieldValue(JRField field) throws JRException {
		return listaLineas.get(lineaActual).getMapa().get(field.getName());
	}

	@Override
	public boolean next() throws JRException {
		return ++lineaActual < listaLineas.size();
	}
	
	class Linea {
		private Map<String, Object> mapa = new HashMap<String, Object>();
		public Map<String, Object> getMapa() {
			return mapa;
		}
		public void setMapa(Map<String, Object> pMapa) {
			mapa = pMapa;
		}
	}
}
