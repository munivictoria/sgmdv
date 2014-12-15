package com.trascender.saic.test.ejb3;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;

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
import com.trascender.habilitaciones.recurso.persistent.TipoObligacion;
import com.trascender.habilitaciones.recurso.transients.FiltroCalendarioMunicipal;
import com.trascender.habilitaciones.system.interfaces.SystemObligacion;
import com.trascender.habilitaciones.system.interfaces.SystemPeriodo;
import com.trascender.habilitaciones.system.interfaces.SystemTipoTasa;
import com.trascender.saic.system.interfaces.SystemRegistroValuado;

public class RegistroValuadoTest extends JAserciones{
	
	private static SystemUsuario systemUsuario;
	private static SystemRegistroValuado systemRegistroValuado;
	private static SystemObligacion systemObligacion;
	private static SystemTipoTasa systemTipoTasa;
	private static SystemPeriodo systemPeriodo;
	
	
	@BeforeClass
	public static void init () throws Exception{
		Context locContext = new InitialContext();
		
		systemUsuario = (SystemUsuario) locContext.lookup(SystemUsuario.JNDI_NAME);
		
		long llave = systemUsuario.login("root", "Emilia15");
		
		systemRegistroValuado = (SystemRegistroValuado) locContext.lookup(SystemRegistroValuado.JNDI_NAME);
		systemRegistroValuado.setLlave(llave);
		
		systemObligacion = (SystemObligacion) locContext.lookup(SystemObligacion.JNDI_NAME);
		systemObligacion.setLlave(llave);
		
		systemTipoTasa = (SystemTipoTasa) locContext.lookup(SystemTipoTasa.JNDI_NAME);
		systemTipoTasa.setLlave(llave);
		
		systemPeriodo = (SystemPeriodo) locContext.lookup(SystemPeriodo.JNDI_NAME);
		systemPeriodo.setLlave(llave);
	}
	
	@Test
	public void testAddCalendario() throws Exception{
		try {
			TipoObligacion locTipoObligacion = this.systemTipoTasa.findListaTipoObligacion("OYSP", null).get(0);
				System.out.println(locTipoObligacion);
				assertNotNull(locTipoObligacion);
				
				Calendar locFechaInicio = Calendar.getInstance();
				locFechaInicio.set(2013, Calendar.JANUARY, 1);
//			CalendarioMunicipal locCalendario = new CalendarioMunicipal("Calendario Mensual OSP", 
//															2013, 
//															Periodicidad.MENSUAL,
//															locFechaInicio,
//															locTipoObligacion,
//															Periodicidad.MENSUAL,
//															1);
				CalendarioMunicipal locCalendario = this.crearCalendarioTGI();
			
			System.out.println(locCalendario);
//			System.out.println(locCalendario.getListaPeriodos());
//			System.out.println(locCalendario.getTipoObligacion().getListaCalendarioMunicipal());
			
			System.out.println("MOSTRANDO PERIODOS ***************************************************");
			for(Periodo cadaPeriodo : locCalendario.getListaPeriodos()){
				System.out.println(cadaPeriodo);
				PeriodoLiquidacion cadaPeriodoLiquidacion = (PeriodoLiquidacion) cadaPeriodo;
				System.out.println("\tMOSTRANDO CUOTAS **************************************************");
				for(CuotaLiquidacion cadaCuota : cadaPeriodoLiquidacion.getListaCuotas()){
					System.out.println("\t" + cadaCuota);
					Calendar locVencimiento = (Calendar) cadaCuota.getFechaFin().clone();
						locVencimiento.add(Calendar.DAY_OF_YEAR, 7);
					cadaCuota.addVencimiento(locVencimiento);
				}
			}
			
//				locCalendario.setNombre("OSP Anual");
//				locCalendario.setAnio(2013);
//				locCalendario.setEstado(EstadoCalendario.ACTIVO);
//				locCalendario.setPeriodicidad(Periodicidad.ANUAL);
////				locCalendario.setListaPeriodos(this.getListaPeriodos());
//				locCalendario.addPeriodo(this.getListaPeriodos().iterator().next());
//				
//				System.out.println("RETORNO: " + this.systemPeriodo.addCalendarioMunicipal(locCalendario));
				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public CalendarioMunicipal crearCalendarioTGI(){
		CalendarioMunicipal locCalendario = new CalendarioMunicipal();
		 
		 locCalendario.setNombre("Calendario TGI Bimestral 2013");
		 locCalendario.setPeriodicidad(Periodicidad.BIMESTRAL);
		 
		 for(PeriodoLiquidacion cadaperiodo : this.getListaPeriodosNuevos()){
			 locCalendario.addPeriodo(cadaperiodo);
		 }
		 
		 PeriodoLiquidacion locPeriodo = new PeriodoLiquidacion();
		 	locPeriodo.setPeriodicidad(Periodicidad.MENSUAL);
		 	locPeriodo.setNombre("Periodo 1 - Calendario TGI Bimestral");
		 	
		 	
		 locCalendario.addPeriodo(locPeriodo);
		 CuotaLiquidacion locCuota1 = new CuotaLiquidacion();
//		 	locCuota.set
		 	
		 Calendar locVencimiento1Per1 = Calendar.getInstance();
		 	locVencimiento1Per1.set(2013, Calendar.FEBRUARY, 5);
		 Calendar locVencimiento2Per1 = Calendar.getInstance();
		 	locVencimiento2Per1.set(2013, Calendar.FEBRUARY, 10);
		 	
		 locCuota1.addVencimiento(locVencimiento2Per1);
		 locCuota1.addVencimiento(locVencimiento1Per1);
		 	
		 locPeriodo.addCuotaLiquidacion(locCuota1);
		 
		 locCalendario.addPeriodo(locPeriodo);
		 
		 
		return locCalendario;
	}
	
	private List<PeriodoLiquidacion> getListaPeriodosNuevos() {
		return null;
	}

	@Test
	public void updateCalendario() throws Exception{
		try {
			TipoObligacion locTipoObligacion = this.systemTipoTasa.findListaTipoObligacion("TGI", null).get(0);
				System.out.println(locTipoObligacion);
				assertNotNull(locTipoObligacion);
			
			FiltroCalendarioMunicipal locFiltro = new FiltroCalendarioMunicipal();
			CalendarioMunicipal locCalendario = this.systemPeriodo.findListaCalendarios(locFiltro).getListaResultados().get(0);
				assertNotNull(locCalendario);
				System.out.println(locCalendario);
				locCalendario.setNombre("Calendario ANnUAL TGI");
			locCalendario = this.systemPeriodo.updateCalendarioMunicipal(locCalendario);	
			System.out.println(locCalendario.getNombre());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private Set<PeriodoLiquidacion> getListaPeriodos(){
		Set<PeriodoLiquidacion> locListaPeriodos = new HashSet<PeriodoLiquidacion>();
		
		PeriodoLiquidacion locPeriodo = new PeriodoLiquidacion();
			Calendar fechaInicio = Calendar.getInstance();
				fechaInicio.set(2013,Calendar.JANUARY, 15);
			Calendar fechaFin= Calendar.getInstance();
				fechaFin.set(2013,Calendar.DECEMBER, 7);
			Calendar fechaVencimiento= Calendar.getInstance();
				fechaVencimiento.set(2013,Calendar.DECEMBER, 30);
			
		locPeriodo.setFechaInicio(fechaInicio);
		locPeriodo.setFechaFin(fechaFin);
//		locPeriodo.setNumeroPeriodo(1);
//		locPeriodo.setNombrePeriodo("Periodo UNICO");
		
		locListaPeriodos.add(locPeriodo);
		
		return locListaPeriodos;
	}
	
	@Test
	public void asddd() throws Exception{
		
		FiltroCalendarioMunicipal locFiltro = new FiltroCalendarioMunicipal();
		
		locFiltro = this.systemPeriodo.findListaCalendarios(locFiltro);
		
		System.out.println(locFiltro.getListaResultados());
	}

	@Test
	public void testGetPeriodo() throws Exception{
		try{
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			TipoObligacion locTipoObligacion = this.systemTipoTasa.findListaTipoObligacion("TGI", null).get(0);
			System.out.println(locTipoObligacion);

			FiltroCalendarioMunicipal locFiltro = new FiltroCalendarioMunicipal();
				locFiltro.setAnio(2013);
				
//			System.out.println("Calendario ACTIVO: " + locTipoObligacion.getCalendarioActivo(Periodicidad.ANUAL, 2013));
//			for(Calendario cadaCalendario : locTipoObligacion.getListaCalendarioMunicipal()){
//				System.out.println(cadaCalendario);
//			}
//			System.out.println(locTipoObligacion.getListaCalendarioMunicipal());
			
//			System.out.println(locTipoObligacion.getCalendarioActivo(Periodicidad.ANUAL, 2013));
//			System.out.println(this.systemPeriodo.getPeriodo(null, 1, 2013, Periodicidad.ANUAL, locTipoObligacion).getCalendario().getAnio());
			CalendarioMunicipal cadaCalendarioMunicipal = null;
			for(Calendario cadaCalendario : systemPeriodo.findListaCalendarios(locFiltro).getListaResultados()){
				cadaCalendarioMunicipal = systemPeriodo.getCalendarioById(cadaCalendario.getIdCalendario());
				
				System.out.println(cadaCalendarioMunicipal + " Cantidad Periodos: " + cadaCalendarioMunicipal.getListaPeriodos().size());
				for(Periodo cadaPeriodo : cadaCalendarioMunicipal.getListaPeriodos()){
					PeriodoLiquidacion cadaPeriodoLiquidacion = (PeriodoLiquidacion) cadaPeriodo;
					System.out.println(cadaPeriodo + " Cantidad de Cuotas: " + cadaPeriodoLiquidacion.getListaCuotas().size());
					for(CuotaLiquidacion cadaCuota : cadaPeriodoLiquidacion.getListaCuotas()){
						System.out.println("\t|_ " + cadaCuota);
						int cont =1;
						for(Calendar cadaVencimiento : cadaCuota.getListaVencimientos()){
							System.out.println("\t\t|_ Vencimientos: " + cont + " "+ dateFormat.format(cadaVencimiento.getTime()));
						}
					}
				}
			}
			
			CuotaLiquidacion locCal = cadaCalendarioMunicipal.getPrimerCuota();
			System.out.println(locCal);
			System.out.println(locCal.getPeriodo().getNombre());
			
			
//			System.out.println(calendarioNuevo);
//			System.out.println(calendarioNuevo.getListaPeriodos().size());
//			for(Periodo cadaPeriodo : calendarioNuevo.getListaPeriodos()){
//				System.out.println("\t-" + cadaPeriodo.toString());
//			}
		}catch (Exception e) {
				e.printStackTrace();
			}
	}
	
}
