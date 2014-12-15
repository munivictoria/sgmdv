package com.trascender.habilitaciones.test.ejb3;

import java.util.Calendar;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.BeforeClass;
import org.junit.Test;

import com.trascender.framework.recurso.transients.Calendario;
import com.trascender.framework.recurso.transients.Periodo;
import com.trascender.framework.system.interfaces.SystemUsuario;
import com.trascender.framework.util.JAserciones;
import com.trascender.framework.util.Periodicidad;
import com.trascender.habilitaciones.recurso.persistent.CalendarioMunicipal;
import com.trascender.habilitaciones.recurso.persistent.CuotaLiquidacion;
import com.trascender.habilitaciones.recurso.persistent.PeriodoLiquidacion;
import com.trascender.habilitaciones.recurso.persistent.TipoTasa;
import com.trascender.habilitaciones.recurso.transients.FiltroCalendarioMunicipal;
import com.trascender.habilitaciones.system.interfaces.SystemPeriodo;
import com.trascender.habilitaciones.system.interfaces.SystemTipoTasa;

public class PeriodoTest extends JAserciones{

	private static SystemUsuario systemUsuario;
	private static SystemPeriodo systemPeriodo;
	private static SystemTipoTasa systemTipoTasa;
	
	@BeforeClass
	public static void inicializar() throws Exception{
		Context ctx = new InitialContext();
		long llave;
		
		systemUsuario = (SystemUsuario) ctx.lookup(SystemUsuario.JNDI_NAME);
		llave = systemUsuario.login("root", "Emilia15");
		
		systemPeriodo = (SystemPeriodo) ctx.lookup(SystemPeriodo.JNDI_NAME);
		systemPeriodo.setLlave(llave);
		
		systemTipoTasa = (SystemTipoTasa) ctx.lookup(SystemTipoTasa.JNDI_NAME);
		systemTipoTasa.setLlave(llave);
		
		
	}
	
	@Test
	public void  testFindListaCalendario() throws Exception{
		try {
			List<CalendarioMunicipal> locListaResultados = systemPeriodo.findListaCalendarios(new FiltroCalendarioMunicipal()).getListaResultados();
			Calendario locCalendario = locListaResultados.get(1);
			
			System.out.println(locCalendario.getListaPeriodos());
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Test
	public void testAddCalendario() throws Exception{
		try {
			Calendar locFechaInicioPeriodo = Calendar.getInstance();
				locFechaInicioPeriodo.set(Calendar.DAY_OF_MONTH, 1);
				locFechaInicioPeriodo.set(Calendar.MONTH, Calendar.JANUARY);
				locFechaInicioPeriodo.set(Calendar.YEAR, 2013);
				
			TipoTasa locTipoTasa = this.systemTipoTasa.getTipoTasaPorId(3l);
				assertNotNull(locTipoTasa);
				System.out.println("Tipo tasa recuperado: " + locTipoTasa);
				
//			CalendarioMunicipal locCalendario = new CalendarioMunicipal("TSH Mensual",
//																		2013,
//																		Periodicidad.MENSUAL,
//																		locFechaInicioPeriodo,
//																		locTipoTasa,
//																		Periodicidad.MENSUAL,
//																		1);
			
//			System.out.println("Calendario creado: " + locCalendario);
//			for (Periodo cadaPeriodo : locCalendario.getListaPeriodos()) {
//				PeriodoLiquidacion cadaPeriodoLiquidacion = (PeriodoLiquidacion) cadaPeriodo;
//				System.out.println("\t Periodo " + cadaPeriodoLiquidacion);
//				for(CuotaLiquidacion cadaCuota : cadaPeriodoLiquidacion.getListaCuotas()){
//					Calendar locFechaVencimiento = (Calendar) locFechaInicioPeriodo.clone();
//						locFechaVencimiento.add(Calendar.YEAR, 1);
//						locFechaVencimiento.add(Calendar.DAY_OF_MONTH, 10);
//					
//					cadaCuota.addVencimiento(locFechaVencimiento);
//					System.out.println("\t\t Cuota Generada " + cadaCuota);
//					System.out.println("\t\t Cuota Generada " + cadaCuota.getListaVencimientos().iterator().next());
//					
//				}
//			}
//			
//			this.systemPeriodo.addCalendarioMunicipal(locCalendario);
		} catch (Exception e) {
//			e.printStackTrace();
//			throw e;
		}
//		
	}
}
