package com.trascender.catastro.test.testEjb3;

import static org.junit.Assert.*;

import java.util.List;

import javax.naming.InitialContext;

import org.junit.BeforeClass;
import org.junit.Test;

import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.catastro.recurso.persistent.Zona;
import com.trascender.catastro.recurso.persistent.Zonificacion;
import com.trascender.catastro.system.interfaces.SystemAdministracionZonificacion;
import com.trascender.catastro.system.interfaces.SystemInformacionGeografica;
import com.trascender.catastro.system.interfaces.SystemInformacionParcelaria;
import com.trascender.framework.system.interfaces.SystemUsuario;
import com.trascender.framework.util.JAserciones;

public class ZonificacionTest extends JAserciones{

	private static SystemUsuario systemUsuario;
	private static SystemAdministracionZonificacion systemZonificacion;
	private static SystemInformacionGeografica systemInformacionGeografico;
	private static SystemInformacionParcelaria systemParcela;
	private static long llave;

	@BeforeClass
	public static void inicializar() {

		try {
			InitialContext initial = new InitialContext();
			systemUsuario = (SystemUsuario) initial.lookup(SystemUsuario.JNDI_NAME);
			llave = systemUsuario.login("root", "Emilia15");

			systemInformacionGeografico = (SystemInformacionGeografica) initial.lookup(systemInformacionGeografico.JNDI_NAME);
			systemZonificacion = (SystemAdministracionZonificacion) initial.lookup(systemZonificacion.JNDI_NAME);

			systemInformacionGeografico.setLlave(llave);
			systemZonificacion.setLlave(llave);
			
			systemParcela = (SystemInformacionParcelaria) initial.lookup(SystemInformacionParcelaria.JNDI_NAME);
			systemParcela.setLlave(llave);
			

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	

	@Test
	public void addZonificacion() throws Exception {
		try{
		Zonificacion locZonificacion = new Zonificacion();
		locZonificacion.setNombre("Nueva zonificacion 5051");

		Zona locZoba = null; //systemZonificacion.findListaZonas("A", null, null).get(0);
			assertNotNull("La zona esta null", locZoba);

		
		locZonificacion.getListaZonas().add(locZoba);

			systemZonificacion.addZonificacion(locZonificacion);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testRemoveZonificacion() throws Exception {
		try {

			Zonificacion locZonificacion = this.systemZonificacion
					.getZonificacionPorId(2L);

			assertNotNull("No se controntro la zonificacion (NULL)",
					locZonificacion);

			this.systemZonificacion.removeZonificacion(locZonificacion);
		
		} catch (Exception e) {
			
			e.printStackTrace();
		}

	}
	
	@Test
	public void testGetZonificacionPorId() throws Exception{
		try {
			Zonificacion locZonificacion = this.systemZonificacion.getZonificacionPorId(2L);
			assertNotNull("la zonificacion es nula", locZonificacion);
			System.out.println("ZONIFICACION = "+locZonificacion.getNombre());
			System.out.println("LISTA ZONAS.....");
			for(Zona unaZona: locZonificacion.getListaZonas()){
				System.out.println(unaZona.getNombre());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/** comprueba que el find no traiga las zonificaciones eliminadas*/
	@Test
	public void testFindListaZonificacion() throws Exception {
		try {
			Zonificacion locZonificacionEliminada = this.systemZonificacion
					.getZonificacionPorId(17L);
			assertNotNull("No se econtro la zonificacion (NULL)",
					locZonificacionEliminada);

			// compruebo que no traiga los eliminados
			List<Zonificacion> listZonificaciones = null; //this.systemZonificacion.findListaZonificacion(null);

			for (Zonificacion cadaZonificacion : listZonificaciones) {
				assertFalse(
						"LA LISTA TRAJO UNA ZONIFICACION CON ESTADO ELIMINADA",
						cadaZonificacion.equals(locZonificacionEliminada));

			}

		}

		catch (Exception e) {
			e.printStackTrace();

		}
//		@Test
//		public void testAsociacionParcelasPorCalle()throws Exception{
//			try {
//				
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
//		}
	}
	

	@Test
	public void systezoniftest() throws Exception {
		try {
			Parcela locParcela = null; //(Parcela) systemParcela.findListaParcelas(null, null, "12", null, "12", null, null, null).get(0);
			System.out.println(locParcela);
			assertNotNull(locParcela);
			
			System.out.println(this.systemZonificacion.getListaZonasFromParcela(locParcela));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
