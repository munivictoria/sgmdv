package com.trascender.habilitaciones.test.ejb3;

import java.util.List;

import javax.naming.InitialContext;

import org.junit.BeforeClass;
import org.junit.Test;

import com.trascender.framework.system.interfaces.SystemUsuario;
import com.trascender.framework.util.JAserciones;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.PlantillaDocHabCompuesto;
import com.trascender.habilitaciones.recurso.persistent.PlantillaDocHabilitante;
import com.trascender.habilitaciones.recurso.persistent.PlantillaObligacion;
import com.trascender.habilitaciones.system.interfaces.SystemObligacion;
import com.trascender.habilitaciones.system.interfaces.SystemPlantillaObligaciones;

public class PlantillaObligacionTest extends JAserciones {

	private static SystemUsuario systemUsuario;

	private static SystemPlantillaObligaciones systemPlantilla;
	private static SystemObligacion systemObligacion;

	@BeforeClass
	public static void setUpBeforeClass() {
		try {
			InitialContext initialContext = new InitialContext();

			systemUsuario = (SystemUsuario) initialContext.lookup(SystemUsuario.JNDI_NAME);
			long llave = systemUsuario.login("root", "Emilia15");

			systemPlantilla = (SystemPlantillaObligaciones) initialContext.lookup(SystemPlantillaObligaciones.JNDI_NAME);
			systemPlantilla.setLlave(llave);
			
			systemObligacion= (SystemObligacion) initialContext.lookup(SystemObligacion.JNDI_NAME);
			systemObligacion.setLlave(llave);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFindPlantillaObligacion(){
		try{
//			List locListaResultado = this.systemPlantilla.findListaPlantillaObligaciones(null, null);
//			assertNotNull("La lista quedo nulo",locListaResultado);
//			assertIsEmpty("La lista esta vacia",locListaResultado);
//			
//			this.mostrarLista(PlantillaObligacion.class, locListaResultado);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddPlantillaObligacion() throws Exception{
		try{
		PlantillaObligacion locPlantilla = new PlantillaObligacion();
		locPlantilla.setDescripcion("Plantilla TGI");
		locPlantilla.setNombre("PLantilla TGI");
//		locPlantilla.setTipoObligacion(TipoObligacion.TGI);
		
		this.systemPlantilla.addPlantillaObligacion(locPlantilla);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdatePlantillaObligacion() throws Exception{
		try{
		PlantillaObligacion locPlantilla = this.systemPlantilla.getPlantillaObligacion(2L);
			assertNotNull(locPlantilla);
		
		locPlantilla.setNombre("PLantilla OSP-");
		
		for(PlantillaDocHabilitante cadaDocumentolocPlantilla : locPlantilla.getListaDocumentosHabilitantes()){
			System.out.println(cadaDocumentolocPlantilla);
		}
		
		PlantillaDocHabCompuesto locDocHabCompuesto = new PlantillaDocHabCompuesto();
			locDocHabCompuesto.setDescripcion("LAAAA descripcion");
			locDocHabCompuesto.setNombre("Alto name");
			locDocHabCompuesto.setRaiz(locPlantilla);
			locDocHabCompuesto.setPadre((PlantillaDocHabCompuesto) locPlantilla.getListaDocumentosHabilitantes().toArray()[0]);
		
		locPlantilla.getListaDocumentosHabilitantes().add(locDocHabCompuesto);

		this.systemPlantilla.updatePlantillaObligacion(locPlantilla);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRemovePlantillaObligacion() throws Exception{
		try{
		PlantillaObligacion locPlantilla = this.systemPlantilla.getPlantillaObligacion(4L);
			assertNotNull(locPlantilla);
		
		this.systemPlantilla.deletePlantillaObligacion(locPlantilla);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGenerarArbol() throws Exception{
		try{
			PlantillaObligacion locPlantilla = this.systemPlantilla.getPlantillaObligacion(2L);
				assertNotNull(locPlantilla);
				
			Obligacion locObligacion = this.systemPlantilla.generarArbol(locPlantilla);
				assertNotNull(locObligacion);
			
			System.out.println(locObligacion);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetPlantillaPorId() throws Exception{
		try{
			PlantillaObligacion locPLantilla = this.systemPlantilla.getPlantillaObligacion(3L);
			
			for(PlantillaDocHabilitante cadaDocumento : locPLantilla.getListaDocumentosHabilitantes()){
				cadaDocumento.toString();
				cadaDocumento.getPadre().toString();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetPlantillaObFromOb() throws Exception{
		try{
			Obligacion locObligacion = this.systemObligacion.getObligacionPorId(91);
				assertNotNull(locObligacion);
				System.out.println(locObligacion);
			
			PlantillaObligacion locPLantilla = this.systemPlantilla.getPlantillaObligacionFromObligacion(locObligacion);
				assertNotNull(locPLantilla);
				
			System.out.println(locPLantilla);
			
			Obligacion locObligacionGenerada = this.systemPlantilla.generarArbol(locPLantilla);
			
			System.out.println(locObligacionGenerada);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

}
