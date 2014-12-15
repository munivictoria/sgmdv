package com.trascender.framework.test.ejb3;

import java.util.List;

import javax.naming.InitialContext;

import org.junit.BeforeClass;
import org.junit.Test;

import com.trascender.framework.recurso.persistent.Contrato;
import com.trascender.framework.system.interfaces.SystemContrato;
import com.trascender.framework.system.interfaces.SystemUsuario;

public class TestContrato {
	
	private static SystemContrato systemContrato;
	private static SystemUsuario systemUsuario;
	
	private static long llave = 0;
	
	@BeforeClass
	public static void inicializar(){
		try{
			InitialContext initial = new InitialContext();
			systemUsuario = (SystemUsuario) initial.lookup(SystemUsuario.JNDI_NAME);
			llave = systemUsuario.login("root", "Emilia15");
			
			systemContrato = (SystemContrato) initial.lookup(SystemContrato.JNDI_NAME);
			systemContrato.setLlave(llave);
			
		} catch (Exception e){
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testFindContrato(){
		try{
			List<Contrato> locListaResultado = null; //systemContrato.findListaContratos(null, null, null,null,null);
			for (Contrato cadaContr : locListaResultado) {
				System.out.println(cadaContr);
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}

}
