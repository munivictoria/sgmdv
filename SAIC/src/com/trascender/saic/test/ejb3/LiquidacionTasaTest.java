package com.trascender.saic.test.ejb3;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.junit.BeforeClass;
import org.junit.Test;

import com.trascender.catastro.recurso.persistent.Calle;
import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.catastro.system.interfaces.SystemInformacionParcelaria;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.framework.recurso.transients.Calendario;
import com.trascender.framework.system.interfaces.SystemPersonaFisica;
import com.trascender.framework.system.interfaces.SystemUsuario;
import com.trascender.framework.util.JAserciones;
import com.trascender.framework.util.Util;
import com.trascender.habilitaciones.recurso.persistent.CalendarioMunicipal;
import com.trascender.habilitaciones.recurso.persistent.CuotaLiquidacion;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.PeriodoLiquidacion;
import com.trascender.habilitaciones.recurso.persistent.TipoObligacion;
import com.trascender.habilitaciones.recurso.persistent.osp.ServicioOSP;
import com.trascender.habilitaciones.recurso.persistent.pfo.Obra;
import com.trascender.habilitaciones.system.interfaces.SystemAlicuota;
import com.trascender.habilitaciones.system.interfaces.SystemDocumentoOSP;
import com.trascender.habilitaciones.system.interfaces.SystemObligacion;
import com.trascender.habilitaciones.system.interfaces.SystemPeriodo;
import com.trascender.habilitaciones.system.interfaces.SystemPlanFinanciacionObra;
import com.trascender.habilitaciones.system.interfaces.SystemTipoTasa;
import com.trascender.saic.recurso.persistent.LiquidacionTasa;
import com.trascender.saic.recurso.persistent.RegistroDeuda.TipoDeuda;
import com.trascender.saic.recurso.transients.HistorialPagosTasas;
import com.trascender.saic.recurso.transients.LineaHistorialPago;
import com.trascender.saic.system.interfaces.SystemEstadoCuentaContribuyente;
import com.trascender.saic.system.interfaces.SystemLiquidacionTasa;
import com.trascender.saic.system.interfaces.SystemRegistroValuado;

public class LiquidacionTasaTest extends JAserciones{
	
	private static long llave;
	
	private static SystemInformacionParcelaria systemParcela;
	private static SystemPersonaFisica systemPersonaF;
	private static SystemUsuario systemUsuario;
	private static SystemAlicuota systemAlicuota;
	private static SystemRegistroValuado systemRegistroValuado;
	private static SystemPlanFinanciacionObra systemPlanFinanciacionObra;
	private static SystemLiquidacionTasa systemLiquidacionTasa;
	private static SystemObligacion systemObligacion;
	private static SystemEstadoCuentaContribuyente systemEstadoCuenta;
	private static SystemDocumentoOSP systemDocOSP;
	private static SystemPeriodo systemPeriodo;
	private static SystemTipoTasa systemTipoTasa;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception{
		try{
//			
		Context locContext = new InitialContext();
		
		systemUsuario = (SystemUsuario) locContext.lookup(SystemUsuario.JNDI_NAME);
		llave = systemUsuario.login("root", "Emilia15");
		
		systemParcela = (SystemInformacionParcelaria) locContext.lookup(SystemInformacionParcelaria.JNDI_NAME);
		systemParcela.setLlave(llave);
		
		systemPersonaF = (SystemPersonaFisica) locContext.lookup(SystemPersonaFisica.JNDI_NAME);
		systemPersonaF.setLlave(llave);
		
		systemAlicuota = (SystemAlicuota) locContext.lookup(SystemAlicuota.JNDI_NAME);
		systemAlicuota.setLlave(llave);
		
		systemPlanFinanciacionObra = (SystemPlanFinanciacionObra) locContext.lookup(systemPlanFinanciacionObra.JNDI_NAME);
		systemPlanFinanciacionObra.setLlave(llave);
		
		systemLiquidacionTasa = (SystemLiquidacionTasa) locContext.lookup(systemLiquidacionTasa.JNDI_NAME);
		systemLiquidacionTasa.setLlave(llave);
			
		systemRegistroValuado = (SystemRegistroValuado) locContext.lookup(SystemRegistroValuado.JNDI_NAME);
		systemRegistroValuado.setLlave(llave);
		
		systemObligacion = (SystemObligacion) locContext.lookup(SystemObligacion.JNDI_NAME);
		systemObligacion.setLlave(llave);
		
		systemEstadoCuenta = (SystemEstadoCuentaContribuyente) locContext.lookup(SystemEstadoCuentaContribuyente.JNDI_NAME);
		systemEstadoCuenta.setLlave(llave);
		
		systemDocOSP = (SystemDocumentoOSP) locContext.lookup(SystemDocumentoOSP.JNDI_NAME);
		systemDocOSP.setLlave(llave);
		
		systemPeriodo = (SystemPeriodo) locContext.lookup(SystemPeriodo.JNDI_NAME);
		systemPeriodo.setLlave(llave);
		
		systemTipoTasa = (SystemTipoTasa) locContext.lookup(SystemTipoTasa.JNDI_NAME);
		systemTipoTasa.setLlave(llave);
		
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("NO se inicio un chot...");
		}
	}
	
	@Test
	public void testLiquidarTGI() throws Exception{
		try{
			TipoObligacion locTipoObligacion = this.systemTipoTasa.findListaTipoObligacion("TGI", null).get(0);
			System.out.println(locTipoObligacion);
			assertNotNull(locTipoObligacion);
			
			PersonaFisica locPersona = systemPersonaF.getPersonaFisicaPorCuim("20-35706186-6");
				assertNotNull(locPersona);
				System.out.println(locPersona);
			Parcela locParcela = systemParcela.getParcelaPorId(14213L);
				assertNotNull(locParcela);
				System.out.println(locParcela);
				
			Calendario locCalendario = systemPeriodo.getCalendarioById(40l);
				assertNotNull(locCalendario);
				System.out.println(locCalendario);
				CuotaLiquidacion[] locCuotas = {((PeriodoLiquidacion)locCalendario.findPeriodo(null, 1)).getCuotaLiquidacion(1)};
				assertNotNull(locCuotas[0]);
				
			System.out.println("Liquidacion TGI: \n Para: \n\tPersona: "+ locPersona
														+ " \n\tParcela: "+ locParcela
														+ "\n\tCuota " + locCuotas[0]);
			
			System.out.println("*************************************LIQUIDACION TGI*******************************************************");
			List<LiquidacionTasa> locListaResultado = systemLiquidacionTasa.liquidarTgi(locCuotas, locPersona, locParcela);
				assertNotNull(locListaResultado);
				assertIsEmpty(locListaResultado);
				
			mostrarLista(LiquidacionTasa.class, locListaResultado);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test(){
		try {
			Class locClass = Class.forName(Util.capitalizeEnumName(TipoDeuda.RELIQUIDACION.name()));
			
			System.out.println(locClass.getSimpleName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFindLiquidacionesTGI(){
		try{
			Persona locPersona = systemPersonaF.getPersonaFisicaPorCuim("20-35706186-6");
				assertNotNull(locPersona);
				
			List locListaResultado = null; //systemLiquidacionTasa.findListaLiquidacionesTGI(null, locPersona, null, null, null, null, false);
			
			mostrarLista(LiquidacionTasa.class, locListaResultado);
			
//			LiquidacionTasa locLiquidacion = (LiquidacionTasa) locListaResultado.get(0);
//			
//			System.out.println(locLiquidacion);
//			System.out.println(locLiquidacion.getCuotaLiquidacion());
//			System.out.println(locLiquidacion.getPeriodo());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void liquidarPFO() throws Exception{
	
	Persona[] param0 =  {null,/*this.getSystemPersonaFisica().getPersonaFisicaPorCuim("23-25907516-5")*/};
	Obra[] param1 = {systemPlanFinanciacionObra.getObraPorId(5L)};
	Calle[] param2 = {null};
	CuotaLiquidacion[] param3= null;//{systemRegistroValuado.getPeriodo(Periodicidad.MENSUAL,8,2009)};
	assertTrue("Obra vacia",param1.length!=0);
	assertTrue("periodo vacio",param3.length!=0);
	
//	for (Persona locPersona: param0){
//		for (Obra locObra : param1){
//			for (Calle locCalle : param2){
//				for (Periodo locPeriodo: param3){
					try{
						System.out.println("Liquidación de la tasa PFO ");
						System.out.println("\tPersona: "+param0);
						System.out.println("\tObra: "+param1);
						System.out.println("\tCalle: "+param2);
						System.out.println("\tPeriodo: "+param3);
						System.out.println("_______________________Resultado:");
						List result = systemLiquidacionTasa.liquidarPFO(param0[0], param1[0], param2[0], param3);
						for (Object o: result){
							LiquidacionTasa locLiquidacionTasa = (LiquidacionTasa) o;
//							System.out.println("Periodo " + locLiquidacionTasa.getPeriodo().toString());
							System.out.println("Obligacion " + locLiquidacionTasa.getDocGeneradorDeuda().getObligacion().toString());
							System.out.println("Fecha Vencimiento " + locLiquidacionTasa.getFechaVencimiento());
							System.out.println("Monto " + locLiquidacionTasa.getMonto());
							System.out.println("Nro Cuota " + locLiquidacionTasa.getCuotaLiquidada());
							
						}
						System.out.println("_______________________________________");
					}
					catch(Exception e){
						e.printStackTrace();
						throw e;
					}
//				}
//			}
//		}
//	}
	//
	}
	@Test
	public void getHistorialPagos() throws Exception{
		Obligacion locObligacion = systemObligacion.getObligacionPorId(60772);
		
		HistorialPagosTasas locHistorial = systemLiquidacionTasa.getHistorialPagos(locObligacion, 20);
		for(LineaHistorialPago cadaLinea : locHistorial.getListaHitorialPagoAnual()){
			System.out.println("año "+cadaLinea.getAnio()+" estado"+cadaLinea.getListaEstadosString());

		}
	}
	
	@Test
	public void testGenerarRefinanciacion() throws Exception {
		try {
			Persona locPersona = this.systemPersonaF.getPersonaFisicaPorCuim("20-35706186-6");
				assertNotNull(locPersona);
				
			List<Obligacion> locListaObligaciones = new ArrayList<Obligacion>();
			locListaObligaciones = this.systemObligacion.findListaObligaciones(locPersona, null, null);
			this.filtrarObligaciones(locListaObligaciones);
			
			List<Obligacion> locListaObligacionesEncontradas = this.systemEstadoCuenta.getListaDeudasContribuyente(locListaObligaciones);
			
			
			System.out.println(locListaObligacionesEncontradas);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void filtrarObligaciones(List<Obligacion> locListaObligaciones) {
		Iterator locListaIt = locListaObligaciones.iterator();
		
		while(locListaIt.hasNext()){
			Obligacion locObligacion = (Obligacion) locListaIt.next();
			if(!locObligacion.getNumeroTramite().equals(14)){
				locListaIt.remove();
			}
		}
		
	}
	
	@Test
	public void testLiquidarOSP() throws Exception {
		try {
			
			Persona locPersona = this.systemPersonaF.getPersonaFisicaPorCuim("20-34335998-6");
				assertNotNull(locPersona);
				System.out.println(locPersona);
				
			ServicioOSP locServicio = (ServicioOSP) this.systemDocOSP.findListaServiciosOSP("05", null).get(0);
				assertNotNull(locServicio);
				System.out.println(locServicio);
				
			CalendarioMunicipal locCalendario = this.systemPeriodo.getCalendarioById(37l);
				CuotaLiquidacion locCuotaALiquidar = ((PeriodoLiquidacion)locCalendario.findPeriodo(null, 1)).getCuotaLiquidacion(1);
				CuotaLiquidacion[] locCuotas = {locCuotaALiquidar};
				System.out.println(locCalendario);
				System.out.println(locCuotaALiquidar);
				
//				System.out.println(SecurityMgr.getInstance().getPeriodoMensual(Calendar.getInstance()).getFechaFin());
				
//			List listaResultado = this.systemLiquidacionTasa.liquidarOSP(locServicio, 
//															null, 
//															locCuotas, 
//															null);
			
//			System.out.println(listaResultado);
//			System.out.println("LISTA VACIA?: " + listaResultado.size());
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	@Test
	public void testLiquidacionSHPS() throws Exception {
		try {
			Persona locPersona = this.systemPersonaF.getPersonaFisicaPorCuim("20-34335998-6");
				assertNotNull(locPersona);
				System.out.println(locPersona);
				
				TipoObligacion locTipoObligacion = this.systemTipoTasa.findListaTipoObligacion("SHPS", null).get(0);
				System.out.println(locTipoObligacion);
				assertNotNull(locTipoObligacion);
				
//			Periodo locPeriodo = this.systemPeriodo.getPeriodo(null, 1, 2013, Periodicidad.MENSUAL,locTipoObligacion);
//				assertNotNull(locPeriodo);
//				PeriodoLiquidacion locPeriodoLiquidacion = (PeriodoLiquidacion) locPeriodo;
				CalendarioMunicipal locCalendario = this.systemPeriodo.getCalendarioById(37l);
				CuotaLiquidacion locCuotaALiquidar = ((PeriodoLiquidacion)locCalendario.findPeriodo(null, 1)).getCuotaLiquidacion(1);
//				CuotaLiquidacion locCuotaALiquidar = locPeriodoLiquidacion.getListaCuotas().iterator().next();
				CuotaLiquidacion[] locCuotas = {locCuotaALiquidar};
				System.out.println(locCalendario);
				System.out.println(locCuotaALiquidar);
				
				assertNotNull(locCuotaALiquidar);
				
				
//			List<LiquidacionTasa> locListaLiquidaciones = this.systemLiquidacionTasa.liquidarSHPS(locPersona, locCuotas);
			
//			if(locListaLiquidaciones.isEmpty()){
//				System.out.println("NO LIQUIDO NADA");
//			}
//			
//			for(LiquidacionTasa cadaLiq : locListaLiquidaciones){
//				System.out.println(cadaLiq);
//			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testLista() throws Exception{
		try {
			TCollection<Persona> locLista = new TCollection<Persona>(HashSet.class);
			
			locLista.add(new PersonaFisica());
			locLista.add(new PersonaFisica());
			locLista.add(null);
			
			Collection<Persona> lista = locLista.parseInstance(List.class);
//			List<Persona> lista2 = locLista.parseInstance();
//			Vector<Persona> lista3 = locLista.parseInstance();
//			Collection<Persona> lista4 = locLista.parseInstance();
			
			System.out.println(lista);
//			System.out.println(lista2);
//			System.out.println(lista3);
//			System.out.println(lista4);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFindDeudas() throws Exception{
		Obligacion locObligacion = this.systemObligacion.getObligacionPorId(10);
			assertNotNull(locObligacion);
			System.out.println(locObligacion);
			
		List<LiquidacionTasa> listaDeudas = this.systemEstadoCuenta.getListaDeudasContribuyente(locObligacion);
			assertNotNull(listaDeudas);
			assertIsEmpty(listaDeudas);
			
		this.mostrarLista(listaDeudas);
	
	}
	
	@Test
	public void ordenVencimiento(){
		List<Calendar> listaVencimientos = new ArrayList<Calendar>();
		
		Calendar locFecha1 = Calendar.getInstance();
		Calendar locFecha2 = Calendar.getInstance();
		Calendar locFecha3 = Calendar.getInstance();
		Calendar locFecha4 = Calendar.getInstance();
		
		
		locFecha1.set(2013, Calendar.FEBRUARY, 1);
		locFecha2.set(2013, Calendar.AUGUST, 1);
		locFecha3.set(2013, Calendar.JANUARY, 1);
		locFecha4.set(2013, Calendar.SEPTEMBER, 1);
		
		listaVencimientos.add(locFecha1);
		listaVencimientos.add(locFecha2);
		listaVencimientos.add(locFecha3);
		listaVencimientos.add(locFecha4);
		
		System.out.println("Mostrando fechas en orden preliminar.");
		this.mostrarFechaFormateada(listaVencimientos);
		if(listaVencimientos != null && !listaVencimientos.isEmpty()){
			Collections.sort(listaVencimientos, new Comparator<Calendar>() {

				public int compare(Calendar o1, Calendar o2) {
					if(o1.after(o2)){
						return 1;
					}
					return 0;
				}
			});
		}
		
		
		System.out.println("Mostrando fechas en ordenadas:");
		this.mostrarFechaFormateada(listaVencimientos);
		
		
	}
	
	private void mostrarFechaFormateada(List<Calendar> listaVencimientos) {
		SimpleDateFormat locFechaFormater = new SimpleDateFormat("dd/MM/yyyy");
		
		String fechaEncadenada= "";
		for(Calendar cadaFecha : listaVencimientos){
			fechaEncadenada += " - " + locFechaFormater.format(cadaFecha.getTime());
		}
		
		System.out.println(fechaEncadenada);
		
	}

	
}
