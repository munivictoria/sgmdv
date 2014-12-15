package com.trascender.saic.test.ejb3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.naming.InitialContext;

import org.junit.BeforeClass;
import org.junit.Test;

import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.transients.Periodo;
import com.trascender.framework.system.interfaces.SystemPersonaFisica;
import com.trascender.framework.system.interfaces.SystemUsuario;
import com.trascender.framework.util.JAserciones;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.TipoObligacion;
import com.trascender.habilitaciones.system.interfaces.SystemTipoTasa;
import com.trascender.saic.recurso.persistent.RegistroDeuda;
import com.trascender.saic.recurso.transients.EstadoCuentaContribuyente;
import com.trascender.saic.system.interfaces.SystemEstadoCuentaContribuyente;
import com.trascender.saic.system.interfaces.SystemRegistroValuado;

public class EstadoCuentaContribuyenteTest extends JAserciones{
	
	private static long llave;
	
	private static SystemUsuario systemUsuario;
	private static SystemEstadoCuentaContribuyente systemContribuyente;
	private static SystemPersonaFisica systemPersonaF;
	private static SystemRegistroValuado systemRegistroValuado;
	private static SystemTipoTasa systemTipoTasa;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception{
		
		systemUsuario = (SystemUsuario) new InitialContext().lookup(SystemUsuario.JNDI_NAME);
		llave = systemUsuario.login("root", "Emilia15");
		
		systemContribuyente= (SystemEstadoCuentaContribuyente) new InitialContext().lookup(SystemEstadoCuentaContribuyente.JNDI_NAME);
		systemContribuyente.setLlave(llave);
		
		systemPersonaF = (SystemPersonaFisica) new InitialContext().lookup(SystemPersonaFisica.JNDI_NAME);
		systemPersonaF.setLlave(llave);
		
		systemRegistroValuado = (SystemRegistroValuado) new InitialContext().lookup(SystemRegistroValuado.JNDI_NAME);
		systemRegistroValuado.setLlave(llave);
		
		systemTipoTasa = (SystemTipoTasa) new InitialContext().lookup(SystemTipoTasa.JNDI_NAME);
		systemTipoTasa.setLlave(llave);
	}
	
	@Test
	public void testGetListaEstadosCuentaContribuyente() throws Exception{
		try{
			Persona locPersona = systemPersonaF.getPersonaFisicaPorCuim("23-27426367-9");
				assertNotNull(locPersona);
				
			List locListaResultado = this.systemContribuyente.getListaEstadosCuentaContribuyente(locPersona, null);
				assertNotNull(locListaResultado);
				assertIsEmpty(locListaResultado);
			
			System.out.println(((EstadoCuentaContribuyente)locListaResultado.get(0)).getStringObligacion());
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testListadoTasasUnificadas(){
		try{
			Periodo locPeriodo = null; //systemRegistroValuado.getPeriodo(Periodicidad.MENSUAL, 6, 2012);
			List<TipoObligacion> locListaTipoObligaciones = new ArrayList<TipoObligacion>();
			locListaTipoObligaciones.add(systemTipoTasa.findListaTipoObligacion("TGI", null).get(0));
			locListaTipoObligaciones.add(systemTipoTasa.findListaTipoObligacion("OYSP", null).get(0));
//			List<LiquidacionTasa> locListaLiquidacionesTasa = systemContribuyente.findListaTasasUnificadas(null, null, locPeriodo, locListaTipoObligaciones);
//			System.out.println(locListaLiquidacionesTasa.size());
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	@Test
	public void testFindListaDeudas() throws Exception {
		try {
			Persona locPersona = null; 
//			List locListaPersonas = systemPersonaF.findPersonaFisica(null, null, "tort", null, null, null, null, null, null, null, null, null, null, null);
//			if(locListaPersonas != null && !locListaPersonas.isEmpty()){
//				locPersona = (Persona) locListaPersonas.get(0);
//			}else{
//				throw new AssertionError("La persona es nula");
//			}
			
			System.out.println(locPersona.getCuim());
			
			List<Obligacion> locListaObligacion = this.systemContribuyente.getListaObligacionesContribuyente(locPersona, null);
				assertNotNull(locListaObligacion);
				assertIsEmpty(locListaObligacion);
			System.out.println("Tamanio sin limpiar " +locListaObligacion.size());
				Iterator<Obligacion> locIterador = locListaObligacion.iterator();
				while(locIterador.hasNext()){
				Obligacion cadaObligacion = locIterador.next();
				if(!cadaObligacion.getEstado().equals(Obligacion.Estado.CREADO)
						|| cadaObligacion.getIdObligacion() != 92){
					locIterador.remove();
				}
			}
				
			System.out.println("Tamanio limpio " +locListaObligacion.size());
			
			for(Obligacion cadaObligacion : locListaObligacion){
				System.out.println("ID: "+ cadaObligacion.getIdObligacion() + " - "+cadaObligacion.toString());
				
				boolean flagTieneDeuda = false;
				for(Object cadaOb: this.systemContribuyente.getListaDeudasContribuyente(locListaObligacion)){
					RegistroDeuda cadaDeuda = (RegistroDeuda) cadaOb;
					System.out.println(cadaDeuda);
					flagTieneDeuda=true;
					break;
				}
				if(flagTieneDeuda)
				break;
			}
				
				
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
