package com.trascender.framework.test.ejb3;

import java.rmi.RemoteException;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.BeforeClass;
import org.junit.Test;

import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.system.interfaces.SystemPersonaFisica;
import com.trascender.framework.system.interfaces.SystemUsuario;

import junit.framework.Assert;

public class testUsuarioBean extends Assert{
	
	private static SystemUsuario systemUsuario;
	
	private static SystemPersonaFisica systemPersonaFisica;
	
	private static long llave;
	
	@BeforeClass
	public static void setUpBeforeClass() throws RemoteException, TrascenderException, Exception{
		try {
			systemUsuario = (SystemUsuario) new InitialContext().lookup(SystemUsuario.JNDI_NAME);
			llave = systemUsuario.login("root", "Emilia15");
			systemUsuario.setLlave(llave);
			
			systemPersonaFisica = (SystemPersonaFisica) new InitialContext().lookup(SystemPersonaFisica.JNDI_NAME);
			systemPersonaFisica.setLlave(llave);
			
		} catch (NamingException e) {
			e.printStackTrace();
		} 
	}
	
	@Test
	public void testFindUsuario() throws Exception{
		System.out.println("LLEGO AL TEST");
		PersonaFisica locPersona = this.systemPersonaFisica.getPersonaFisicaPorId(4L);
		assertNotNull("La persona es nula", locPersona);
		
		List locListaResultado = null; //this.systemUsuario.findUsuario(null, null, null, locPersona);
		assertNotNull(locListaResultado);
		assertTrue(!locListaResultado.isEmpty());
		
		for(Object cadaOb : locListaResultado){
			System.out.println(cadaOb);	
		}
	}
	
	@Test
	public void testAddUsuario() throws Exception{
		Usuario locUsuario = new Usuario();
		locUsuario.setEstado(Usuario.Estado.ACTIVO);
		locUsuario.setPassword("Emilia15");
		locUsuario.setUser("root");
		
	}
	
}

