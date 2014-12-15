package com.trascender.catastro.test.testEjb3;

import java.util.List;

import javax.naming.InitialContext;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

import com.trascender.catastro.recurso.persistent.Calle;
import com.trascender.catastro.recurso.persistent.Cuadra;
import com.trascender.catastro.recurso.persistent.Manzana;
import com.trascender.catastro.recurso.persistent.Servicio;
import com.trascender.catastro.recurso.persistent.TipoCalle;
import com.trascender.catastro.system.interfaces.SystemInformacionGeografica;
import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
import com.trascender.framework.recurso.persistent.dinamicos.PlantillaAtributoDinamico;
import com.trascender.framework.system.interfaces.SystemParametro;
import com.trascender.framework.system.interfaces.SystemUsuario;
/**
 * @author jsantacruz
 */
public class testRegistroGeografico extends com.trascender.framework.util.JAserciones{
private static SystemUsuario systemUsuario;
	
	private static SystemInformacionGeografica systemInformacionGeografica;
	private static SystemParametro systemParametros;
	
	private static long llave = 0;
	
	@BeforeClass
	
	public static void setUpBeforeClass(){
		try{
			InitialContext initialContext = new InitialContext();
			
			systemUsuario = (SystemUsuario) initialContext.lookup(SystemUsuario.JNDI_NAME);
			llave = systemUsuario.login("root", "Emilia15");
			
			systemInformacionGeografica = (SystemInformacionGeografica) initialContext.lookup(SystemInformacionGeografica.JNDI_NAME);
			systemInformacionGeografica.setLlave(llave);
			
			systemParametros = (SystemParametro) initialContext.lookup(SystemParametro.JNDI_NAME);
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddTipoCalle() throws Exception{
		TipoCalle locTipoCalle = new TipoCalle();
			locTipoCalle.setActivo(true);
			locTipoCalle.setDescripcion("asdasd");
			locTipoCalle.setNombre("ACeras");
		try{
			this.systemInformacionGeografica.addTipoCalle(locTipoCalle);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDeleteTipoCalle() throws Exception{
		TipoCalle locTipoCalle = null; //(TipoCalle) this.systemInformacionGeografica.findListaTipoCalles("Aceras").get(0);
		Assert.assertNotNull("No hay tipo calle",locTipoCalle);

		try{
			this.systemInformacionGeografica.deleteTipoCalle(locTipoCalle);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdateCalle() throws Exception{
		Calle locCalle = null; //(Calle) this.systemInformacionGeografica.findListaCalles("urquiza",null,true).get(0);
		Assert.assertNotNull("No hay tipo calle",locCalle);

		try{
			this.systemInformacionGeografica.updateCalle(locCalle);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdateCuadra() throws Exception{
		Cuadra locCuandra = (Cuadra) this.systemInformacionGeografica.getCuadraPorId(9L);
		assertNotNull("No hay Cuadra", locCuandra);
		System.out.println(locCuandra);
		
		try{
			this.systemInformacionGeografica.updateCuadra(locCuandra);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddServicio() throws Exception{
		Servicio locServicio = new Servicio();
		locServicio.setDescripcion("Una descripcion");
		locServicio.setEstado(Servicio.Estado.ACTIVO);
		locServicio.setNombre("Nombre xDD");
		
		try{
			System.out.println(this.systemInformacionGeografica.addServicio(locServicio));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test 
	public void testUpdateServicio() throws Exception{
		Servicio locServicio = (Servicio) this.systemInformacionGeografica.findListaServicios("Nombre xDD").get(0);
		assertNotNull("El servicio quedo nulo",locServicio);
		
		try{
		this.systemInformacionGeografica.updateServicio(locServicio);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	@Test 
	public void testDeleteServicio() throws Exception{
		Servicio locServicio = (Servicio) this.systemInformacionGeografica.findListaServicios("Nombre xDD").get(0);
		assertNotNull("El servicio quedo nulo",locServicio);
		
		try{
			this.systemInformacionGeografica.deleteServicio(locServicio);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFindCalleById() throws Exception{
		try{
		Calle locCalle = this.systemInformacionGeografica.getCallePorId(1L);
		assertNotNull("No hay calle", locCalle);
		
		System.out.println(locCalle);
		}catch (Exception e) {
			e.printStackTrace();
		}
			
	}
	
	@Test
	public void testFinListaCuadras() throws Exception{
		try{
			List locListaCuadras = null; //this.systemInformacionGeografica.findListaCuadras(null, null, null, null, 100, 500, null, null);
			assertNotNull(locListaCuadras);
			assertIsEmpty(locListaCuadras);
			
			for(Object cadaOb : locListaCuadras){
				System.out.println((Cuadra) cadaOb);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetManzanaPorId() throws Exception{
		try{
			Manzana locManzana = this.systemInformacionGeografica.getManzanaPorId(2148l);
			locManzana.getListaAtributosDinamicos().clear();
			systemInformacionGeografica.updateManzana(locManzana);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void addPlantillaAtributoDinamico(){
		try{
			PlantillaAtributoDinamico locPlantilla = new PlantillaAtributoDinamico();
			locPlantilla.setNombre("Numero OSM");
			locPlantilla.setTipo(PlantillaAtributoDinamico.Tipo.ENTERO);
			locPlantilla.setIdRecurso(Manzana.serialVersionUID);
			systemParametros.addPlantillaAtributoDinamico(locPlantilla);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void deletePlantillaAtributoDinamico(){
		try{
			PlantillaAtributoDinamico locPlantilla = null; //systemParametros.findListaPlantillaAtritbutosDinamicos(null, null,null).get(0);
			systemParametros.deletePlantillaAtributoDinamico(locPlantilla);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAtributosDinamicos() throws Exception{
		try{
			PlantillaAtributoDinamico plantillaAtributo = null; //systemParametros.findListaPlantillaAtritbutosDinamicos(null, Manzana.serialVersionUID,null).get(0);
			Manzana locManzana = systemInformacionGeografica.getManzanaPorId(612l);
			AtributoDinamico atributo = plantillaAtributo.generarAtributoDinamico();
			atributo.setValor(555L);
			locManzana.addAtributoDinamico(atributo);
			systemInformacionGeografica.updateManzana(locManzana);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFindAtributosDinamicos() {
		try{
			Cuadra locCuadra = systemInformacionGeografica.getCuadraPorId(5983l);
			System.out.println(locCuadra.getListaAtributosDinamicos().size());
			List<AtributoDinamico<?>> locLista = systemParametros.getAtributosPorRecurso(Cuadra.serialVersionUID, locCuadra.getListaAtributosDinamicos(), null);
			System.out.println(locLista);
//			systemInformacionGeografica.updateManzana(locManzana);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
}
