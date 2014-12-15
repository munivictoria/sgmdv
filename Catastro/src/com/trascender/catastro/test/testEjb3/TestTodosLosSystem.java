package com.trascender.catastro.test.testEjb3;


import javax.naming.Context;
import javax.naming.InitialContext;

import org.junit.Before;
import org.junit.Test;

import com.trascender.catastro.system.interfaces.SystemAdministracionDDJJ;
import com.trascender.catastro.system.interfaces.SystemAdministracionZona;
import com.trascender.catastro.system.interfaces.SystemAdministracionZonificacion;
import com.trascender.catastro.system.interfaces.SystemCodigosCatastrales;
import com.trascender.catastro.system.interfaces.SystemInformacionGeografica;
import com.trascender.catastro.system.interfaces.SystemInformacionParcelaria;
import com.trascender.catastro.system.interfaces.SystemRegistroPropiedad;

public class TestTodosLosSystem {
	
	static SystemAdministracionDDJJ systemAdministraDDJJBean;
	static SystemAdministracionZonificacion systemAdministracionZonificacion;
	static SystemAdministracionZona systemAdministracionZona;
	static SystemCodigosCatastrales systemCodigosCatastrales;
	static SystemInformacionGeografica systemInformacionGeografica;
	static SystemInformacionParcelaria systemInformacionParcelaria;
	static SystemRegistroPropiedad systemRegistroPropiedad;

	@Before
	public void setUp() throws Exception {
		try{
			Context ctx = new InitialContext();
			systemAdministraDDJJBean = (SystemAdministracionDDJJ) ctx.lookup(SystemAdministracionDDJJ.JNDI_NAME);
			systemAdministracionZonificacion = (SystemAdministracionZonificacion) ctx.lookup(SystemAdministracionZonificacion.JNDI_NAME);
//			systemAdministracionZona = (SystemAdministracionZona) ctx.lookup(SystemAdministracionZona.JNDI_NAME);
//			systemCodigosCatastrales = (SystemCodigosCatastrales) ctx.lookup(SystemCodigosCatastrales.JNDI_NAME);
//			systemInformacionGeografica = (SystemInformacionGeografica) ctx.lookup(SystemInformacionGeografica.JNDI_NAME);
//			systemInformacionParcelaria = (SystemInformacionParcelaria) ctx.lookup(SystemInformacionParcelaria.JNDI_NAME);
//			systemRegistroPropiedad = (SystemRegistroPropiedad) ctx.lookup(SystemRegistroPropiedad.JNDI_NAME);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testTest(){
		System.out.println("Test");
	}

}
