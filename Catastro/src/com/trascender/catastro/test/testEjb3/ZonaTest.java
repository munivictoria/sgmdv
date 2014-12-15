package com.trascender.catastro.test.testEjb3;

import java.util.List;

import javax.inject.Inject;
import javax.naming.InitialContext;

import org.junit.BeforeClass;
import org.junit.Test;

import com.trascender.catastro.recurso.filtros.FiltroZona;
import com.trascender.catastro.recurso.persistent.Zona;
import com.trascender.catastro.recurso.persistent.Zona.Estado;
import com.trascender.catastro.system.interfaces.SystemAdministracionZonificacion;
import com.trascender.framework.system.interfaces.SystemUsuario;

public class ZonaTest {
	
	
	private static SystemUsuario systemUsuario;
	private static SystemAdministracionZonificacion systemZonificacion;
	private static long llave;

	
	
	@BeforeClass
	public static void inicializar(){
		try {
//			InitialContext initial = new InitialContext();
//			systemUsuario = (SystemUsuario) initial
//					.lookup(SystemUsuario.JNDI_NAME);
//			llave = systemUsuario.login("root", "Emilia15");
//
//			systemZonificacion = (SystemAdministracionZonificacion) initial
//					.lookup(systemZonificacion.JNDI_NAME);
//
//			systemZonificacion.setLlave(llave);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	@Test
	public void addZonaTest() {

		try {
//			Zonificacion locZonificacion = systemZonificacion
//					.getZonificacionPorId(17L);

			Zona locZona = new Zona();

			locZona.setDescripcion("descripcion zona 1024");
			locZona.setEstado(Estado.ACTIVO);
			locZona.setNombre("Zona 1024");
			locZona.setPrioridad(10);
//			locZona.setZonificacion(locZonificacion);

//			systemZonificacion.addZona(locZona);

		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void deleteZonaTest() {
		try {
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void findListaZonasTest(){
		try {
			List locListaZonas = systemZonificacion.findListaZonas(new FiltroZona()).getListaResultados();
			
			System.out.println(locListaZonas
					);
			
			
	
		
		} catch (Exception e) {
			
			
		}
	}
	
	@Test
	public void asd(){
//		System.out.println(createRandomAddress());
		for (int i = 0; i < 10; i++) {
			System.out.println(Math.random());
		}
		

	}
	
	public String createRandomAddress(){
		  String address = null;
		  
		  Integer num;
		  String [] addressList = {"25 de Mayo", "25 de Junio", "9 de Julio", "Churruarin", "Andres Pazos", 
		         "Laprida", "P. Perón", "Ilia", "Av. Artigas", "Dorrego"};
		 

		 java.util.Random semilla = new java.util.Random();
		 num = semilla.nextInt(9999);
		  
		
		  address = addressList [semilla.nextInt(addressList.length)] + " " + num.toString();;
		  
		  return address;
		 }




}
