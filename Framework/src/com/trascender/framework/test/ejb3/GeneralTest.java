package com.trascender.framework.test.ejb3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Test;

import com.trascender.framework.recurso.metamodel._PersonaFisica;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.framework.recurso.persistent.validacionDinamica.ComponenteValidacion;
import com.trascender.framework.recurso.persistent.validacionDinamica.ComponenteValidacion.Operadores;
import com.trascender.framework.recurso.persistent.validacionDinamica.ComponenteValidacion.TipoValidacion;
import com.trascender.framework.recurso.persistent.validacionDinamica.ValidacionDinamica;
import com.trascender.framework.recurso.persistent.validacionDinamica.ValidadorDinamicoCore;
import com.trascender.framework.recurso.persistent.validacionDinamica.ValorEntidad;
import com.trascender.framework.recurso.persistent.validacionDinamica.ValorString;
import com.trascender.framework.recurso.persistent.validacionDinamica.ValorValidacion;
import com.trascender.framework.system.interfaces.SystemMunicipalidad;

public class GeneralTest {
	
	private SystemMunicipalidad systemMuni;
	
	@Test
	public void validacionDinamica() throws Exception{
		try{
			
			PersonaFisica locP = new PersonaFisica();
			locP.setNombre("asd");
			locP.setNumeroDocumento("345345");
			
				
			ComponenteValidacion locComponente = new ComponenteValidacion();
				locComponente.setAtributo("numeroDocumento");
//				locComponente.setOperador(Operadores.IGUAL);
				locComponente.setTipoValidacion(TipoValidacion.UNICO);
//				locComponente.setValor("ignacio");
				
			ComponenteValidacion locComponenteB = new ComponenteValidacion();
				locComponenteB.setAtributo("tipoDocumento");
				locComponenteB.setOperador(Operadores.IGUAL);
				locComponenteB.setTipoValidacion(TipoValidacion.AND);
//				locComponenteB.setValor("DNI");
				
				locComponente.addComponente(locComponenteB);
				locComponente.setObjeto(locP);
				System.out.println(locComponente.getValidacion());
			
				
				for(String cadaString : locComponente.getMapaParametros().keySet()){
					System.out.println(cadaString + " " +locComponente.getMapaParametros().get(cadaString) );
				}
				
		}catch (Exception locE) {
			locE.printStackTrace();
		}
		
	}
	
	@Test
	public void EjecucionValidacionDinamica() throws Exception{
		ComponenteValidacion locComponente = new ComponenteValidacion();
		locComponente.setAtributo("nombre");
		locComponente.setOperador(Operadores.IGUAL);
		locComponente.setTipoValidacion(TipoValidacion.AND);
//		locComponente.setValor("ignacio");
		
		ValidacionDinamica locValidacion = new ValidacionDinamica();
			locValidacion.setClase(PersonaFisica.class);
			locValidacion.setIdRecurso(PersonaFisica.serialVersionUID);
			locValidacion.getListaComponentes().add(locComponente);
			
		List<ValidacionDinamica> asd = new ArrayList<ValidacionDinamica>();
		asd.add(locValidacion);
		
		PersonaFisica locP = new PersonaFisica();
		locP.setNombre("asd");
		EntityManager e = null;
		ValidadorDinamicoCore.getInstance(e,Persona.class).empezarValidacion(locP);
	}

	@Test
	public void validaciondasDinamica() throws Exception{
		ComponenteValidacion asd = new ComponenteValidacion();
		
		System.out.println(asd.capitalizeAtribute("numeroDocumento"));
	}			
	
	@Test
	public void ffff() throws Exception {
		try {
//			MensajeErrorVD locValidacion = new MensajeErrorVD();
//				locValidacion.setMensaje("algo");
//				locValidacion.setNumeroMsg(11l);
//				
//			locValidacion.throwException();
			
			ValorEntidad locEntidad = new ValorEntidad();
			
			locEntidad.setIdRecurso(11l);
			locEntidad.setSerialVersionRecurso(ValorString.serialVersionUID);
			
//			PersonaFisica locpersona = new PersonaFisica();
//				locpersona.setIdPersona(11);
			System.out.println(ValorValidacion.class.getSuperclass().getSimpleName());
			System.out.println(locEntidad.comparar(locEntidad));
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		
//		Atributo locAtr = new a;
		
//		System.out.println(locAtr.getClass().getDeclaredMethods());
//		int i = 0;
//		for(Field cadafo : _PersonaFisica.i().getPropiedad(null, false, null)){
//			System.out.println(i++ +" - "+ cadafo.getName());
//		}
		
		System.out.println("\n**********************************************\n");
		for(String cadafo : _PersonaFisica.i().getPropiedadAsString(null, true, null)){
			System.out.println(cadafo);
		}
		
	}
	
	@Test
	public void testReadDocumento() throws Exception {
		File archivo = new File ("/opt/parteinsert");
		FileReader fr = new FileReader (archivo);
		BufferedReader br = new BufferedReader(fr);

		String linea = br.readLine();
		
		
		System.out.println(linea);
		
		linea.indexOf("to_timestamp(");
		
		String nuevaLinea = linea.replace("to_timestamp(", "");
		
		System.out.println(nuevaLinea);
		
		
	}
	
}
