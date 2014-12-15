package com.trascender.framework.test.ejb3;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;

import org.junit.BeforeClass;
import org.junit.Test;

import com.trascender.framework.recurso.persistent.Area;
import com.trascender.framework.system.interfaces.SystemMunicipalidad;
import com.trascender.framework.system.interfaces.SystemUsuario;

public class TestArea {
	
	private static SystemUsuario systemUsuario;
	private static SystemMunicipalidad systemMunicipalidad;
	
	private static long llave = 0;
	
	private static EntityManager entity;
	
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
	public void testFindArea(){
		try{
//			for (Object cadaObject : systemMunicipalidad.findArea(null, null)){
//				Area cadaArea = (Area) cadaObject;
//			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}

}
