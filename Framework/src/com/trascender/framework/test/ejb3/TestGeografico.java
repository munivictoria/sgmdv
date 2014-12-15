package com.trascender.framework.test.ejb3;

import javax.naming.InitialContext;

import org.junit.BeforeClass;
import org.junit.Test;

import com.trascender.framework.recurso.persistent.Pais;
import com.trascender.framework.recurso.persistent.Provincia;
import com.trascender.framework.system.interfaces.SystemMunicipalidad;
import com.trascender.framework.system.interfaces.SystemUsuario;

public class TestGeografico {

	private static SystemUsuario systemUsuario;
	private static SystemMunicipalidad systemMunicipalidad;
	
	private static long llave = 0;
	
	@BeforeClass
	public static void inicializar(){
		try{
			InitialContext initialContext = new InitialContext();
			
			systemUsuario = (SystemUsuario) initialContext.lookup(SystemUsuario.JNDI_NAME);
			llave = systemUsuario.login("root", "Emilia15");
			
			systemMunicipalidad = (SystemMunicipalidad) initialContext.lookup(SystemMunicipalidad.JNDI_NAME);
			systemMunicipalidad.setLlave(llave);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFindPais(){
		try{
			System.out.println(systemMunicipalidad.findPais(null));
//			System.out.println(systemMunicipalidad.findPais("Ar"));
			System.out.println(systemMunicipalidad.getPaisPorId(7));
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddPais(){
		try{
			Pais locPais = new Pais();
			locPais.setNombre("lalalal");
			locPais.setAbreviatura("sdfsdf");
			systemMunicipalidad.addPais(locPais);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdatePais(){
		try{
			Pais locPais = systemMunicipalidad.getPaisPorId(7);
			locPais.setNombre("Nombre updateado con EJB3");
			systemMunicipalidad.updatePais(locPais);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDeletePais(){
		try{
			Pais locPais = systemMunicipalidad.getPaisPorId(7);
			systemMunicipalidad.removePais(locPais);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFindProvincia(){
		try{
//			System.out.println(systemMunicipalidad.findProvincia(null, null));
//			System.out.println(systemMunicipalidad.findProvincia("entre", null));
			System.out.println(systemMunicipalidad.getProvinciaPorId(7));
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFindLocalidad(){
		try{
			Pais locPais = systemMunicipalidad.getPaisPorId(8);
//			Provincia locProvincia = systemMunicipalidad.getProvinciaPorId(7);
//			System.out.println(systemMunicipalidad.findLocalidad("nombre", null, null, locPais));
//			System.out.println(systemMunicipalidad.findLocalidad("par", null, null, null));
//			System.out.println(systemMunicipalidad.findLocalidad(null, null, locProvincia, null));
//			System.out.println(systemMunicipalidad.findLocalidad(null, null, null, locPais));
//			System.out.println(systemMunicipalidad.getLocalidadPorId(1));
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
}
