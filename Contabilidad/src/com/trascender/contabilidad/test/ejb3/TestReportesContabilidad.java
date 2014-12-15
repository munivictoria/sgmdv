package com.trascender.contabilidad.test.ejb3;

import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.junit.Before;
import org.junit.Test;

import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.contabilidad.recurso.persistent.MovimientoCajaIngreso;
import com.trascender.contabilidad.system.interfaces.SystemAdministracionIngresos;
import com.trascender.contabilidad.system.interfaces.SystemReportesContabilidad;
import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.framework.recurso.transients.Calendario;
import com.trascender.framework.system.interfaces.SystemUsuario;
import com.trascender.saic.recurso.interfaces.Pagable;
import com.trascender.saic.recurso.persistent.LiquidacionTasa;
import com.trascender.saic.system.interfaces.SystemLiquidacionTasa;


public class TestReportesContabilidad {
	
	
	private SystemUsuario systemUsuario;
	private SystemReportesContabilidad systemReportesContabilidad;
	private SystemAdministracionIngresos systemIngresos;
	private SystemLiquidacionTasa systemLiquidacionTasa;
	private long llave;
	
	@Before
	public void setUp(){
		try{
			Context ctx = new InitialContext();
			systemUsuario = (SystemUsuario) ctx.lookup(SystemUsuario.JNDI_NAME);
			llave = systemUsuario.login("root", "Emilia15");
			
			systemReportesContabilidad = (SystemReportesContabilidad) ctx.lookup(SystemReportesContabilidad.JNDI_NAME);
			systemReportesContabilidad.setLlave(llave);
			
			systemIngresos = (SystemAdministracionIngresos) ctx.lookup(SystemAdministracionIngresos.JNDI_NAME);
			systemIngresos.setLlave(llave);
			
			systemLiquidacionTasa = (SystemLiquidacionTasa) ctx.lookup(SystemLiquidacionTasa.JNDI_NAME);
			systemLiquidacionTasa.setLlave(llave);
		
		} catch (Exception e){
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testMovimientosCajaIngreso(){
		try {
//			systemUsuario.setLlave(llave);
//			systemUsuario.logout();
			Parcela locParcela = new Parcela();
			locParcela.setIdParcela(5308);
//			llave = systemUsuario.login("root", "Emilia15");
//			systemLiquidacionTasa = (SystemLiquidacionTasa) new InitialContext().lookup(SystemLiquidacionTasa.JNDI_NAME);
			systemLiquidacionTasa.setLlave(llave);
			PersonaFisica locPersona = new PersonaFisica();
//			locPersona.setIdPersona(15201);
			locPersona.setIdPersona(24242);
			Calendario locCalendario = new Calendario();
			locCalendario.setIdCalendario(12l);
//			List<LiquidacionTasa> locListaLiquidaciones = systemLiquidacionTasa.findListaLiquidacionesOSP(
//					locPersona, locParcela, null, null, null, 
//					null, null, null, locCalendario, 
//					2013, null, null, null);
			List<LiquidacionTasa> locListaLiquidaciones = systemLiquidacionTasa.findListaLiquidacionesSHPS(
					locPersona, null, null, null, null, null, null, null, null);
			
			List<Pagable> locListaPagables = new ArrayList<Pagable>();
			Double montoLiquidaciones = 0D;
			for (LiquidacionTasa cadaLiquidacion : locListaLiquidaciones) {
				locListaPagables.add(systemLiquidacionTasa.getLiquidacionTasaPorId(cadaLiquidacion.getIdRegistroDeuda()));
				montoLiquidaciones += cadaLiquidacion.getMonto();
			}
			System.out.println("Monto liquidaciones: " + montoLiquidaciones);
			List<MovimientoCajaIngreso> locListaMovimientos = 
					systemIngresos.getListaMovimientosCaja(locListaPagables, false);
			Double montoIngresos = 0D;
			for (MovimientoCajaIngreso cadaMov : locListaMovimientos) {
				montoIngresos += cadaMov.getImporte();
				System.out.println(cadaMov.getCuenta() + " >>>>>> " + cadaMov.getImporte());
			}
			System.out.println("Monto ingresos: " + montoIngresos);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAplicarIntereses(){
		try {
			Parcela locParcela = new Parcela();
			locParcela.setIdParcela(5308);
			systemLiquidacionTasa.setLlave(llave);
			PersonaFisica locPersona = new PersonaFisica();
			locPersona.setIdPersona(15201);
			Calendario locCalendario = new Calendario();
			locCalendario.setIdCalendario(12l);
			List<LiquidacionTasa> locListaLiquidaciones = systemLiquidacionTasa.findListaLiquidacionesOSP(
					locPersona, locParcela, null, null, null, 
					null, null, null, locCalendario, 
					2013, null, null, null);
			for (LiquidacionTasa cadaLiquidacion : locListaLiquidaciones) {
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}

}
