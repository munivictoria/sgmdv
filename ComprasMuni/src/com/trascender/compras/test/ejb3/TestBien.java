package com.trascender.compras.test.ejb3;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.junit.Before;
import org.junit.Test;

import com.trascender.compras.recurso.persistent.suministros.Bien;
import com.trascender.compras.recurso.persistent.suministros.GrupoBien;
import com.trascender.compras.system.interfaces.SystemAdministracionBienes;
import com.trascender.framework.system.interfaces.SystemUsuario;


public class TestBien {
	
	SystemUsuario systemUsuario;
	SystemAdministracionBienes systemBien;
	
	long llave;
	
	@Before
	public void setUp() throws Exception {
		try{
			Context ctx = new InitialContext();
			systemUsuario = (SystemUsuario) ctx.lookup(SystemUsuario.JNDI_NAME);
			llave = systemUsuario.login("root", "Emilia15");
			systemBien = (SystemAdministracionBienes) ctx.lookup(SystemAdministracionBienes.JNDI_NAME);
			systemBien.setLlave(llave);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFindListaBienes(){
		try{
//			System.out.println(systemBien.findListadoBienes(null, null, Bien.Estado.ACTIVO));
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRestoreBien(){
		try{
//			Bien locBien = (Bien) systemBien.findListadoBienes(null, null, Bien.Estado.INACTIVO).get(0);
//			System.out.println(locBien);
//			systemBien.restoreBien(locBien);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFindListaGrupoBienes() throws Exception {
		try {
//			GrupoBien locGrupo = this.systemBien.findGrupoBienesPorNombre("Prueba Grupo Bienes");
			
//			System.out.println(locGrupo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
