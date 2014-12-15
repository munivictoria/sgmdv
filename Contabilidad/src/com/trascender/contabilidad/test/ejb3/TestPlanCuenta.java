package com.trascender.contabilidad.test.ejb3;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.junit.Before;
import org.junit.Test;

import com.trascender.contabilidad.system.interfaces.SystemAdministracionConsultaContable;
import com.trascender.framework.system.interfaces.SystemUsuario;


public class TestPlanCuenta {
	
	private SystemUsuario systemUsuario;
	private SystemAdministracionConsultaContable systemConsultaContable;

	private long llave = 0;
	@Before
	public void setUp() throws Exception {
		
		Context ctx = new InitialContext();
		systemUsuario = (SystemUsuario) ctx.lookup(SystemUsuario.JNDI_NAME);
		llave = systemUsuario.login("root", "Emilia15");
		
		systemConsultaContable = (SystemAdministracionConsultaContable) ctx.lookup(SystemAdministracionConsultaContable.JNDI_NAME);
		systemConsultaContable.setLlave(llave);
	}
	
	@Test
	public void testFindCuentas(){
		try{
			System.out.println(systemConsultaContable.findListaCuenta(null, null, null, null, null, true));
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}

}
