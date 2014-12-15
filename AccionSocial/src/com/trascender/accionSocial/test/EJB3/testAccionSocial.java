package com.trascender.accionSocial.test.EJB3;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.junit.BeforeClass;
import org.junit.Test;

import com.trascender.accionSocial.recurso.persistent.Beneficio;
import com.trascender.accionSocial.recurso.persistent.Beneficio.TipoBeneficio;
import com.trascender.accionSocial.system.interfaces.SystemFichaSocial;
import com.trascender.framework.system.interfaces.SystemUsuario;
import com.trascender.framework.util.JAserciones;

public class testAccionSocial extends JAserciones{
	
	private static long llave;
	private static SystemUsuario systemUsuario;
	private static SystemFichaSocial systemFichaSocial;
	
	@BeforeClass
	public static void beforeClass() throws Exception{
		Context locCtx = new InitialContext();
		
		systemUsuario = (SystemUsuario) locCtx.lookup(SystemUsuario.JNDI_NAME);
		llave = systemUsuario.login("root", "Emilia15");
		
		systemFichaSocial = (SystemFichaSocial) locCtx.lookup(SystemFichaSocial.JNDI_NAME);
		systemFichaSocial.setLlave(llave);
		
	}
	
	@Test
	public void testFindBeneficio() throws Exception{
		try{
			System.out.println(systemFichaSocial.findListaBeneficio(null, null).size());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFindFichaSocial() throws Exception{
		try{
		int cant = systemFichaSocial.findListaFichaSocial(null).getListaResultados().size();
		System.out.println("fichas encontradas: "+cant);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFindObraSocial() throws Exception{
		try{
			System.out.println(systemFichaSocial.findListaObraSocial(null));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void addBeneficio() throws Exception{
		try{
			Beneficio locBeneficio = new Beneficio();
				locBeneficio.setDescripcion("Its Free");
				locBeneficio.setMonto(50D);
				locBeneficio.setNombre("Papas");
				locBeneficio.setTipoBeneficio(TipoBeneficio.INSUMO);
				
			this.systemFichaSocial.addbeneficio(locBeneficio);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

}
