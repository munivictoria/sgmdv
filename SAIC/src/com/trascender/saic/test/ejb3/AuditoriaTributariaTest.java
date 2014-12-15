package com.trascender.saic.test.ejb3;

import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.junit.BeforeClass;
import org.junit.Test;

import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.system.interfaces.SystemPersonaFisica;
import com.trascender.framework.system.interfaces.SystemUsuario;
import com.trascender.framework.util.JAserciones;
import com.trascender.habilitaciones.system.interfaces.SystemObligacion;
import com.trascender.saic.recurso.persistent.RegistroDeuda.EstadoRegistroDeuda;
import com.trascender.saic.recurso.persistent.auditoriaTributaria.AuditoriaTributaria;
import com.trascender.saic.system.interfaces.SystemAuditoriaTributaria;
import com.trascender.saic.system.interfaces.SystemEstadoCuentaContribuyente;
import com.trascender.saic.system.interfaces.SystemLiquidacionTasa;

public class AuditoriaTributariaTest extends JAserciones{

	private static SystemUsuario systemUsuario;
	private static SystemAuditoriaTributaria systemAuditoria;
	private static SystemPersonaFisica systemPersonaF;
	private static SystemEstadoCuentaContribuyente systemEstadoCuenta;
	private static SystemObligacion systemObligacion;
	private static SystemLiquidacionTasa systemLiquidacion;
	
	private static long llave;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception{
		try{
			Context locCtx = new InitialContext();
			systemUsuario = (SystemUsuario) locCtx.lookup(SystemUsuario.JNDI_NAME);
			llave = systemUsuario.login("root", "Emilia15");
			
			systemPersonaF = (SystemPersonaFisica) locCtx.lookup(SystemPersonaFisica.JNDI_NAME);
			systemPersonaF.setLlave(llave);
			
			systemAuditoria = (SystemAuditoriaTributaria) locCtx.lookup(SystemAuditoriaTributaria.JNDI_NAME);
			systemAuditoria.setLlave(llave);
			
			systemEstadoCuenta = (SystemEstadoCuentaContribuyente) locCtx.lookup(SystemEstadoCuentaContribuyente.JNDI_NAME);
			systemEstadoCuenta.setLlave(llave);
			
			systemObligacion = (SystemObligacion) locCtx.lookup(SystemObligacion.JNDI_NAME);
			systemObligacion.setLlave(llave);
			
			systemLiquidacion = (SystemLiquidacionTasa) locCtx.lookup(SystemLiquidacionTasa.JNDI_NAME);
			systemLiquidacion.setLlave(llave);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFindListaAuditoriaTributaria() throws Exception{
		try{
			System.out.println(this.systemAuditoria.findListaAuditoriaTributaria(null, null, null, null, null).size());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddAuditoria() throws Exception{
		try{
			Persona locPersona = systemPersonaF.getPersonaFisicaPorCuim("20-34335998-6");
				assertNotNull(locPersona);
			Collection locLista = null; //systemLiquidacion.findListaLiquidacionesOSP(locPersona, null, null, null, null, null, EstadoRegistroDeuda.VIGENTE, null, null);
				assertIsEmpty(locLista);
				
			AuditoriaTributaria locAuditora = new AuditoriaTributaria();
				locAuditora.setContribuyente(locPersona);
				locAuditora.setFechaCreacion(Calendar.getInstance().getTime());
//				locAuditora.setTipoObligacion(TipoObligacion.OYSP);
				locAuditora.setListaRegistroDeuda(new HashSet(locLista));
				
			System.out.println(locAuditora + String.valueOf(locAuditora.getListaRegistroDeuda().size()));
			
			systemAuditoria.addAuditoriaTributaria(locAuditora);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdateAuditoria() throws Exception{
		try{
			AuditoriaTributaria locAuditoria = systemAuditoria.getAuditoriaTributariaById(8L);
				assertNotNull(locAuditoria);
				
			System.out.println(locAuditoria);
			
			locAuditoria.setFechaCreacion(Calendar.getInstance().getTime());
			System.out.println(systemAuditoria.updateAuditoriaTributaria(locAuditoria));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
