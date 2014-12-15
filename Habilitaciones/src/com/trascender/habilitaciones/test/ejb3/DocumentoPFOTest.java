package com.trascender.habilitaciones.test.ejb3;

import java.util.List;

import javax.naming.InitialContext;

import org.junit.BeforeClass;
import org.junit.Test;

import com.trascender.framework.system.interfaces.SystemPersonaFisica;
import com.trascender.framework.system.interfaces.SystemUsuario;
import com.trascender.framework.util.JAserciones;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.pfo.Obra;
import com.trascender.habilitaciones.recurso.persistent.pfo.PlanCuentaObra;
import com.trascender.habilitaciones.system.interfaces.SystemObligacion;
import com.trascender.habilitaciones.system.interfaces.SystemPlanFinanciacionObra;

public class DocumentoPFOTest extends JAserciones{
	
	private static SystemUsuario systemUsuario;
	private static SystemPlanFinanciacionObra systemPFO;
	private static SystemPersonaFisica systemPersonaF;
	private static SystemObligacion systemObligacion;

	private static long llave = 0;

	@BeforeClass
	public static void setUpBeforeClass() {
		try {
			InitialContext initialContext = new InitialContext();

			systemUsuario = (SystemUsuario) initialContext.lookup(SystemUsuario.JNDI_NAME);
			llave = systemUsuario.login("root", "Emilia15");

			systemPFO = (SystemPlanFinanciacionObra) new InitialContext().lookup(SystemPlanFinanciacionObra.JNDI_NAME);
			systemPFO.setLlave(llave);

			systemPersonaF = (SystemPersonaFisica) new InitialContext().lookup(SystemPersonaFisica.JNDI_NAME);
			systemPersonaF.setLlave(llave);
			
			systemObligacion = (SystemObligacion) new InitialContext().lookup(SystemObligacion.JNDI_NAME);
			systemObligacion.setLlave(llave);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdatePlanCuenta() throws Exception{
		try{
			PlanCuentaObra locPlanCuenta = this.systemPFO.getPlanCuentaObraPorId(1L);
				assertNotNull("No trajo Plan Cunta", locPlanCuenta);
			
			System.out.println(locPlanCuenta);
			
			this.systemPFO.updatePlanCuentaObra(locPlanCuenta);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDeletePlanCuentaObra() throws Exception{
		PlanCuentaObra locPlanCuenta = this.systemPFO.getPlanCuentaObraPorId(1L);
			assertNotNull("No trajo Plan Cunta", locPlanCuenta);
			
		this.systemPFO.deletePlanCuentaObra(locPlanCuenta);
	}
	
	@Test
	public void testFindPlanFinanciacionObra() throws Exception{
		try{
		List locListaResultado = this.systemPFO.findListaPlanCuentaObra(null);
			assertNotNull("La lista quedo nula", locListaResultado);
			assertIsEmpty("La Lista quedo vacia", locListaResultado);
		
		mostrarLista(PlanCuentaObra.class, locListaResultado);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFindObligacionesPFO() throws Exception{
		try{
			List locListaResultado = this.systemObligacion.findListaObligacionesPFO(null, 34, null);
				assertNotNull("La lista quedo nula", locListaResultado);
				assertIsEmpty("La Lista quedo vacia", locListaResultado);
		
				mostrarLista(Obligacion.class, locListaResultado);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void aa() throws Exception{
		try{
//		Integer cand = this.systemPFO.generarObligacionesFromObra(null, null, null); //DONT TACH THIS...
		System.out.println("");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void findListaObra() throws Exception{
		try {
//			for(Object cadaOb : systemPFO.findListaObras("fal", null, null, null)){
//				Obra cadaObra = (Obra) cadaOb;
//				
//				System.out.println(cadaObra);
//				System.out.println(cadaObra.getDescripcion());
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
}
