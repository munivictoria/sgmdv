package com.trascender.habilitaciones.test.ejb3;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.naming.InitialContext;

import org.junit.BeforeClass;
import org.junit.Test;

import com.trascender.catastro.system.interfaces.SystemInformacionParcelaria;
import com.trascender.framework.recurso.persistent.Permiso;
import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.system.interfaces.SystemPersonaFisica;
import com.trascender.framework.system.interfaces.SystemUsuario;
import com.trascender.framework.util.JAserciones;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.Obligacion.Estado;
import com.trascender.habilitaciones.system.interfaces.SystemObligacion;

public class ObligacionTest extends JAserciones{
	
	private static SystemUsuario systemUsuario;
	private static SystemObligacion systemObligacion;
	private static SystemInformacionParcelaria systemParcela;
	private static SystemPersonaFisica systemPersonaF;
	
	private static long llave = 0;

	@BeforeClass
	public static void setUpBeforeClass() {
		try {
			InitialContext initialContext = new InitialContext();

			systemUsuario = (SystemUsuario) initialContext.lookup(SystemUsuario.JNDI_NAME);
			llave = systemUsuario.login("root", "Emilia15");

			systemObligacion = (SystemObligacion) new InitialContext().lookup(SystemObligacion.JNDI_NAME);
			systemObligacion.setLlave(llave);
			
			systemParcela = (SystemInformacionParcelaria) new InitialContext().lookup(SystemInformacionParcelaria.JNDI_NAME);
			systemParcela.setLlave(llave);
			
			systemPersonaF = (SystemPersonaFisica) new InitialContext().lookup(SystemPersonaFisica.JNDI_NAME);
			systemPersonaF.setLlave(llave);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void findListaObligacionesPFO() throws Exception{
		try{
			PersonaFisica locPersona = this.systemPersonaF.getPersonaFisicaPorId(22125L);
			
			List locListaResultado = this.systemObligacion.findListaObligacionesPFO(locPersona, null, null);
			assertNotNull("Lista NULL", locListaResultado);
			assertIsEmpty("Lista Vacia", locListaResultado);
			
			
			mostrarLista(Obligacion.class, locListaResultado);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFindListaObligacionesSHPS() throws Exception{
		try{
			PersonaFisica locPersona = this.systemPersonaF.getPersonaFisicaPorId(22125L);
			
			List locListaResultado = this.systemObligacion.findListaObligacionesPFO(locPersona, null, null);
			assertNotNull("Lista NULL", locListaResultado);
			assertIsEmpty("Lista Vacia", locListaResultado);
			
			mostrarLista(Obligacion.class, locListaResultado);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFindListaPermisosHabAFirmar() throws Exception{
		try{
			PersonaFisica locPersona = this.systemPersonaF.getPersonaFisicaPorId(22125L);
				assertNotNull("Persona NULL",  locPersona);
			
			Usuario locUsuario = this.systemUsuario.findUsuarioPorLlave(llave);
				assertNotNull("Usuario NULL",  locPersona);
				
			List locListaResultado = this.systemObligacion.findListaPermisosHabAFirmar(locUsuario, null);
				assertNotNull("Lista NULL", locListaResultado);
				assertIsEmpty("Lista Vacia", locListaResultado);
			
			
			mostrarLista(Permiso.class, locListaResultado);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void asd(){
		SimpleDateFormat locFormant = new SimpleDateFormat();
		locFormant.applyPattern("dd/MM/yyyy");
		
//		System.out.println(locFormant.format(Calendar.getInstance().getTime()));
		
//		System.out.println(locFormant.format("08/10/2011"));
		try {
			System.out.println(locFormant.parse("08/10/2011"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testFindObligacionTGI() throws Exception{
		try {
//			 List<Obligacion> lisaResult = this.systemObligacion.findListaObligacionesTGI(null, null, Estado.CREADO);
//					 assertNotNull(lisaResult);
//					 assertIsEmpty(lisaResult);
//					 
//			 this.mostrarLista(lisaResult);			
			 
//			 System.out.println(lisaResult.get(0).getStringPoseeExenciones());
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	} 
	
	@Test
	public void TestGetObligacionPorId() throws Exception{
		try {
			Obligacion locObligacion = this.systemObligacion.getObligacionPorId(137l);
				assertNotNull("LA OBLIG ES NULL",locObligacion);
				
			System.out.println(locObligacion);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
