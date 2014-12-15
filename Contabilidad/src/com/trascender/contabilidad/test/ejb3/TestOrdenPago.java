package com.trascender.contabilidad.test.ejb3;


import javax.naming.Context;
import javax.naming.InitialContext;

import org.junit.Before;
import org.junit.Test;

import com.trascender.contabilidad.system.interfaces.SystemAdministracionEgresos;
import com.trascender.framework.system.interfaces.SystemUsuario;

public class TestOrdenPago {
	
	private SystemUsuario systemUsuario;
	private SystemAdministracionEgresos systemEgresos;

	private long llave = 0;
	@Before
	public void setUp() throws Exception {
		
		Context ctx = new InitialContext();
		systemUsuario = (SystemUsuario) ctx.lookup(SystemUsuario.JNDI_NAME);
		llave = systemUsuario.login("root", "Emilia15");
		
		systemEgresos = (SystemAdministracionEgresos) ctx.lookup(SystemAdministracionEgresos.JNDI_NAME);
		systemEgresos.setLlave(llave);
	}
	
	@Test
	public void testFindOrdenPago(){
		try{
			System.out.println(systemEgresos.findListaOrdenPago(null, null, null, null, null, null, null, null));
		} catch (Exception e){
			e.printStackTrace();
		}
	}

}
