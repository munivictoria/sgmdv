package com.trascender.catastro.test.testEjb3;

import java.util.List;

import javax.naming.InitialContext;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

import com.trascender.catastro.recurso.persistent.Categoria;
import com.trascender.catastro.recurso.persistent.TipoConstruccion;
import com.trascender.catastro.system.interfaces.SystemCodigosCatastrales;
import com.trascender.framework.system.interfaces.SystemUsuario;
import com.trascender.framework.util.JAserciones;

public class CodigosCatastralesTest extends JAserciones{

	private static SystemUsuario systemUsuario;
	
	private static SystemCodigosCatastrales systemCodigosCatastrales;
	
	private static long llave = 0;
	
	@BeforeClass
	public static void setUpBeforeClass(){
		try{
			InitialContext initialContext = new InitialContext();
			
			systemUsuario = (SystemUsuario) initialContext.lookup(SystemUsuario.JNDI_NAME);
			llave = systemUsuario.login("root", "Emilia15");
			
			systemCodigosCatastrales = (SystemCodigosCatastrales) initialContext.lookup(SystemCodigosCatastrales.JNDI_NAME);
			systemCodigosCatastrales.setLlave(llave);
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFindListaTiposConstruccion() throws Exception{
		List locListaResultado = null; //this.systemCodigosCatastrales.findTiposConstruccion(null, "Tipo", null);
		Assert.assertNotNull("La lista esta vacia", locListaResultado);
		Assert.assertTrue("La lista esta Vacia", !locListaResultado.isEmpty());
		
		for(Object cadaObject : locListaResultado){
			TipoConstruccion cadaTipo = (TipoConstruccion) cadaObject;
			System.out.println(cadaTipo);
		}
		
	}
	
	@Test
	public void testFindListaCategorias() throws Exception{
		List locListaResultado = null; //this.systemCodigosCatastrales.findListaCategorias("Tipo",null, null, null);
		Assert.assertNotNull("La lista esta vacia", locListaResultado);
		Assert.assertTrue("La lista esta Vacia", !locListaResultado.isEmpty());
		
		for(Object cadaObject : locListaResultado){
			Categoria cadaTipo = (Categoria) cadaObject;
			System.out.println(cadaTipo);
		}
	}
	
	@Test
	public void testEliminarCategoria() throws Exception{
		List locLista = null; //this.systemCodigosCatastrales.findListaCategorias(null, null, false, null);
		
		Categoria categoriaAux = null;
		for(Object locObj : locLista){
			Categoria locCategoria = (Categoria)locObj;
			System.out.println(locCategoria.getIdCategoria()+"  "+locCategoria);
			
			if(locCategoria.getIdCategoria() == 24){
				categoriaAux = locCategoria;
			}
		}
		
		try{
//			systemCodigosCatastrales.deleteCategoria(categoriaAux);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDeleteTipoContruccion() throws Exception{
		TipoConstruccion locTipoConstruccion = null; //(TipoConstruccion) this.systemCodigosCatastrales.findTiposConstruccion(null, "nuev", null).get(0);
		assertNotNull("no tenemos tipo construccion",locTipoConstruccion);
		
		System.out.println(locTipoConstruccion);
		Long idLocTipoConstruccion = new Long(locTipoConstruccion.getIdTipoConstruccion());
		
		try{
			this.systemCodigosCatastrales.deleteTipoConstruccion(locTipoConstruccion);
			
//			if(this.systemCodigosCatastrales.findTiposConstruccion(idLocTipoConstruccion, null, null).get(0) == null){
//				System.out.println("Se elimino correctamente");
//			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddTipoConstruccion() throws Exception{
		TipoConstruccion locTipoConstruccion = new TipoConstruccion();
		locTipoConstruccion.setActivo(true);
		locTipoConstruccion.setDescripcion("asdasdasad");
		locTipoConstruccion.setNombre("NUEVO TIPO CONSTRUCCIO");
		
		try{
			System.out.println(this.systemCodigosCatastrales.addTipoConstruccion(locTipoConstruccion));
		}catch (Exception e) {
			e.printStackTrace();
		}
	} 
}
