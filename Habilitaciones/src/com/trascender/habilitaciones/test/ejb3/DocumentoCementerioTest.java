package com.trascender.habilitaciones.test.ejb3;

import java.util.Calendar;

import javax.naming.InitialContext;

import org.junit.BeforeClass;
import org.junit.Test;

import com.trascender.framework.system.interfaces.SystemUsuario;
import com.trascender.framework.util.JAserciones;
import com.trascender.habilitaciones.system.interfaces.SystemDocumentoCementerio;
import com.trascender.habilitaciones.system.interfaces.SystemObligacion;
import com.trascender.habilitaciones.system.interfaces.SystemPlantillaObligaciones;

public class DocumentoCementerioTest extends JAserciones{
	
	private static SystemUsuario systemUsuario;
	private static SystemDocumentoCementerio systemDocCementerio;
	private static SystemObligacion systemObligacion;
	private static SystemPlantillaObligaciones systemPlantillaObligaciones;
	
	private static long llave = 0;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		try {
			InitialContext initialContext = new InitialContext();

			systemUsuario = (SystemUsuario) initialContext.lookup(SystemUsuario.JNDI_NAME);
			llave = systemUsuario.login("root", "Emilia15");

			systemDocCementerio = (SystemDocumentoCementerio) new InitialContext().lookup(SystemDocumentoCementerio.JNDI_NAME);
			systemDocCementerio.setLlave(llave);
			
			systemObligacion = (SystemObligacion) new InitialContext().lookup(SystemObligacion.JNDI_NAME);
			systemObligacion.setLlave(llave);
			
			systemPlantillaObligaciones = (SystemPlantillaObligaciones) new InitialContext().lookup(SystemPlantillaObligaciones.JNDI_NAME);
			systemPlantillaObligaciones.setLlave(llave);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddDocumentoCementerio() throws Exception{
		try{
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
