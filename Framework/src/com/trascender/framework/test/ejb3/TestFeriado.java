package com.trascender.framework.test.ejb3;

import javax.naming.InitialContext;

import org.junit.BeforeClass;
import org.junit.Test;

import com.trascender.framework.system.interfaces.SystemMunicipalidad;
import com.trascender.framework.system.interfaces.SystemUsuario;

public class TestFeriado {
	
	private static SystemUsuario systemUsuario;
	private static SystemMunicipalidad systemMunicipalidad;
	
	private static long llave = 0;
	
	@BeforeClass
	public static void inicializar(){
		try{
			InitialContext initial = new InitialContext();
			systemUsuario = (SystemUsuario) initial.lookup(SystemUsuario.JNDI_NAME);
			llave = systemUsuario.login("root", "Emilia15");
			systemMunicipalidad = (SystemMunicipalidad) initial.lookup(SystemMunicipalidad.JNDI_NAME);
			systemMunicipalidad.setLlave(llave);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void findDiaFeriado(){
		try{
//			System.out.println(systemMunicipalidad.findListaDiasFeriados(null, null));
//			System.out.println(systemMunicipalidad.findListaDiasFeriados(null, 2010));
//			System.out.println(systemMunicipalidad.findListaDiasFeriados("dia", null));
		} catch (Exception e){
			e.printStackTrace();
		}
	}


}
