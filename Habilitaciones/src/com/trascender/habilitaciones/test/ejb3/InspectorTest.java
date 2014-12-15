package com.trascender.habilitaciones.test.ejb3;

import java.util.List;

import javax.naming.InitialContext;

import org.junit.BeforeClass;
import org.junit.Test;

import com.trascender.framework.system.interfaces.SystemUsuario;
import com.trascender.framework.util.JAserciones;
import com.trascender.habilitaciones.recurso.persistent.shps.Inspector;
import com.trascender.habilitaciones.system.interfaces.SystemInspectores;

public class InspectorTest extends JAserciones{
	
	private static SystemUsuario systemUsuario;
	private static SystemInspectores systemInspectores;

	private static long llave = 0;

	@BeforeClass
	public static void setUpBeforeClass() {
		try {
			InitialContext initialContext = new InitialContext();

			systemUsuario = (SystemUsuario) initialContext.lookup(SystemUsuario.JNDI_NAME);
			llave = systemUsuario.login("root", "Emilia15");

			systemInspectores = (SystemInspectores) initialContext.lookup(SystemInspectores.JNDI_NAME);
			systemInspectores.setLlave(llave);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdateInspector() throws Exception{
		try{
		List locListaInspector= this.systemInspectores.findListaInspectores(null, null);
			assertNotNull("No hay inspector", locListaInspector);
			mostrarLista(Inspector.class, locListaInspector);
		
			
			Inspector locInspector = (Inspector) locListaInspector.get(0);
			System.out.println("Updateando: "+ locInspector);
			
			this.systemInspectores.updateInspector(locInspector);
		
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFindInspeccion() throws Exception{
//		this.systemInspectores.
	}

}
