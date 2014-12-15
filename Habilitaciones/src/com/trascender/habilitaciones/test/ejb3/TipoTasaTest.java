package com.trascender.habilitaciones.test.ejb3;

import static org.junit.Assert.*;

import java.util.List;

import javax.naming.InitialContext;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Calendar;
import com.trascender.catastro.recurso.persistent.Zonificacion;
import com.trascender.catastro.system.interfaces.SystemAdministracionZonificacion;
import com.trascender.framework.system.interfaces.SystemUsuario;
import com.trascender.framework.util.JAserciones;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroConstante;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroGrupoZona;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroParcelario;
import com.trascender.habilitaciones.recurso.persistent.TipoTasa;
import com.trascender.habilitaciones.system.interfaces.SystemTipoTasa;

public class TipoTasaTest extends JAserciones{
	
	private static SystemUsuario systemUsuario;

	private static SystemTipoTasa systemFormula;
	private static SystemAdministracionZonificacion systemZonif;

	private static long llave = 0;

	@BeforeClass
	public static void setUpBeforeClass() {
		try {
			InitialContext initialContext = new InitialContext();

			systemUsuario = (SystemUsuario) initialContext.lookup(SystemUsuario.JNDI_NAME);
			llave = systemUsuario.login("root", "Emilia15");

			systemFormula= (SystemTipoTasa) new InitialContext().lookup(SystemTipoTasa.JNDI_NAME);
			systemFormula.setLlave(llave);
			
			systemZonif= (SystemAdministracionZonificacion) new InitialContext().lookup(SystemAdministracionZonificacion.JNDI_NAME);
			systemZonif.setLlave(llave);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFindTipoTasa() throws Exception{
		try{
//		List locListaResultado = systemFormula.findListaTiposTasa(null, TipoObligacion.OYSP, null, null);
//			assertNotNull(locListaResultado);
//			assertIsEmpty(locListaResultado);
//			
//			for(Object cadaObject : locListaResultado){
//				TipoTasa cadaTT = (TipoTasa) cadaObject;
//				System.out.println(cadaTT.getIdTipoTasa() + "    "+ cadaTT);
//				mostrarLista(TipoParametro.class, cadaTipoTasa.getListaParametros());
//			}
			
			
//			mostrarLista(TipoTasa.class, locListaResultado);
//			TipoTasa locTipo = (TipoTasa)locListaResultado.get(0);
//			System.out.println(locTipo);
//			mostrarLista(TipoParametro.class, locTipo.getListaParametros());
//			//***************************************************************************************
//			TipoTasa locTipoPorID = this.systemFormula.getTipoTasaPorId(locTipo.getIdTipoTasa());
//			System.out.println(locTipo);
//			mostrarLista(TipoParametro.class, locTipo.getListaParametros());
			
			TipoParametroGrupoZona asd = systemFormula.getTipoParametroGrupoZonaPorId(1l);
				System.out.println(asd);
				System.out.println(asd.getListaZonas());
				
//			Zonificacion locZonif = systemZonif.getZonificacionPorId(2l);
//				
//			asd.setZonificacion(locZonif);
			
			this.systemFormula.updateTipoParametroGrupoZona(asd);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetListaParametrosParcelarios() throws Exception{
		try{
			mostrarLista(TipoParametroParcelario.class, this.systemFormula.getListaTiposParametrosParcelarios());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdateTipoTasa() throws Exception{
		try{
//		List locListaResultado = this.systemFormula.findListaTiposTasa(null, null, null, TipoTasa.Estado.EN_ESPERA);
//			assertNotNull(locListaResultado);
//			assertIsEmpty(locListaResultado);
//			
//		TipoTasa locUnTipoTasa = this.systemFormula.getTipoTasaPorId(((TipoTasa)locListaResultado.get(0)).getIdTipoTasa());;
//			assertNotNull(locUnTipoTasa);
			
//			System.out.println(locUnTipoTasa);
//		this.systemFormula.updateTipoTasa(locUnTipoTasa);

		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDeleteTipoParametroConstante() throws Exception{
		try{
			TipoParametroConstante locTipoParametro = this.systemFormula.getTipoParametroConstantePorId(40L);
				assertNotNull(locTipoParametro);
				System.out.println(locTipoParametro);
			this.systemFormula.deleteTipoParametroConstante(locTipoParametro);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDeleteTipoParametro() throws Exception{
		try{
//			List locListaResultado = this.systemFormula.findListaTiposTasa(null, null, null, TipoTasa.Estado.EN_ESPERA);
//				assertNotNull(locListaResultado);
//				assertIsEmpty(locListaResultado);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdateTipoParametro() throws Exception{
		try{
			TipoParametroGrupoZona locTipoParametro = this.systemFormula.getTipoParametroGrupoZonaPorId(45L);
				assertNotNull(locTipoParametro);
				
			this.systemFormula.updateTipoParametroGrupoZona(locTipoParametro);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFindListaTipoParametro() throws Exception {
		try {
			List<TipoParametroGrupoZona> locListaParametros = this.systemFormula.findListaTipoParametroGrupoZona(null, null);
			
			for(TipoParametroGrupoZona cadaParam : locListaParametros){
				System.out.println(cadaParam);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddListaTipoParametro() throws Exception {
		try {
//			List<TipoParametroGrupoZona> locListaParametros = this.systemFormula.findListaTipoParametroGrupoZona(null, null);
//			
//			for(TipoParametroGrupoZona cadaParam : locListaParametros){
//				System.out.println(cadaParam);
//			}
			
			Zonificacion locZinif = this.systemZonif.getZonificacionPorId(6l);
				assertNotNull(locZinif);
				
				System.out.println(locZinif);
//				System.out.println(locZinif.getListaZonas().toString());
//			TipoParametroGrupoZona locParam = new TipoParametroGrupoZona();
//			locParam.setFechaAlta(Calendar.getInstance().getTime());
//			locParam.setNombreGrupoZona("NOMBRE1");
//			locParam.setNombreVariable("NOMBRE1");
//			locParam.setZonificacion(locZinif);
////			
//			try{
//				this.systemFormula.addTipoParametroGrupoZona(new TipoParametroGrupoZona());
//			}catch (Exception e) {
//				e.printStackTrace();
//			}
			
			System.out.println(systemFormula.findListaTipoParametroGrupoZona(null, locZinif));
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
}
