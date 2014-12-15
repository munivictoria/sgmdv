package com.trascender.habilitaciones.test.ejb3;


import java.rmi.RemoteException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.junit.Before;
import org.junit.Test;

import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.exception.TrascenderFrameworkException;
import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamicoEntero;
import com.trascender.framework.recurso.persistent.dinamicos.PlantillaAtributoDinamico;
import com.trascender.framework.system.interfaces.SystemPersonaFisica;
import com.trascender.framework.system.interfaces.SystemUsuario;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.PlantillaObligacion;
import com.trascender.habilitaciones.recurso.persistent.tasaMenor.DocumentoTasaMenor;
import com.trascender.habilitaciones.recurso.persistent.tasaMenor.PlantillaDocumentoTasaMenor;
import com.trascender.habilitaciones.system.interfaces.SystemDocumentoTasaMenor;
import com.trascender.habilitaciones.system.interfaces.SystemObligacion;
import com.trascender.habilitaciones.system.interfaces.SystemPlantillaObligaciones;

public class DocumentoTasaMenorTest {

	private long llave;
	private SystemUsuario systemUsuario;
	private SystemDocumentoTasaMenor systemDocumentoTasaMenor;
	private SystemPersonaFisica systemPersonaFisica;
	private SystemPlantillaObligaciones systemPlantillaObligaciones;
	private SystemObligacion systemObligacion;
	
	@Before
	public void setUp() throws Exception {
		Context ctx = new InitialContext();
		systemUsuario = (SystemUsuario) ctx.lookup(SystemUsuario.JNDI_NAME);
		llave = systemUsuario.login("root", "Emilia15");
		
		systemDocumentoTasaMenor = (SystemDocumentoTasaMenor) ctx.lookup(SystemDocumentoTasaMenor.JNDI_NAME);
		systemDocumentoTasaMenor.setLlave(llave);
		
		systemPersonaFisica = (SystemPersonaFisica) ctx.lookup(SystemPersonaFisica.JNDI_NAME);
		systemPersonaFisica.setLlave(llave);
		
		systemPlantillaObligaciones = (SystemPlantillaObligaciones) ctx.lookup(SystemPlantillaObligaciones.JNDI_NAME);
		systemPlantillaObligaciones.setLlave(llave);
		
		systemObligacion = (SystemObligacion) ctx.lookup(SystemObligacion.JNDI_NAME);
		systemObligacion.setLlave(llave);
		
	}
	
	@Test
	public void testFindListaPlantilla(){
		try{
			List<PlantillaDocumentoTasaMenor> locListaPlantilla = systemDocumentoTasaMenor.findListaPlantillaDocumentoTasaMenor(null);
			PlantillaDocumentoTasaMenor locPlantilla = systemDocumentoTasaMenor.getPlantillaDocumentoPorId(locListaPlantilla.get(0).getIdDocumentoTasaMenor());
			System.out.println(locPlantilla);
			System.out.println(locPlantilla.getListaPlantillasAtributos());
			System.out.println(locPlantilla.getListaPlantillasRegistroValuado());
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddPlantilla(){
		try{
			PlantillaDocumentoTasaMenor locPlantilla = new PlantillaDocumentoTasaMenor();
			locPlantilla.setNombre("Tasa por barrido");
			systemDocumentoTasaMenor.addPlantillaDocumentoTasaMenor(locPlantilla);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdatePlantilla(){
		try{
			PlantillaDocumentoTasaMenor locPlantilla = systemDocumentoTasaMenor.findListaPlantillaDocumentoTasaMenor(null).get(0);
			locPlantilla.setNombre("Update de la primer plantilla");
			systemDocumentoTasaMenor.updatePlantillaDocumentoTasaMenor(locPlantilla);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAgregarPlantillaRegistroValuado(){
		try{
			PlantillaDocumentoTasaMenor locPlantilla = systemDocumentoTasaMenor.findListaPlantillaDocumentoTasaMenor(null).get(0);
			locPlantilla = systemDocumentoTasaMenor.getPlantillaDocumentoPorId(locPlantilla.getIdDocumentoTasaMenor());
			PlantillaAtributoDinamico locPlantillaAtributo = new PlantillaAtributoDinamico();
			locPlantillaAtributo.setNombre("cantidad de balanzas");
			locPlantillaAtributo.setTipo(PlantillaAtributoDinamico.Tipo.ENTERO);
			locPlantilla.addPlantillaAtributoRegistroValuado(locPlantillaAtributo);
			systemDocumentoTasaMenor.updatePlantillaDocumentoTasaMenor(locPlantilla);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void addPlantillaAtributoDinamico(){
		try{
			PlantillaDocumentoTasaMenor locPlantilla = systemDocumentoTasaMenor.findListaPlantillaDocumentoTasaMenor(null).get(0);
			locPlantilla = systemDocumentoTasaMenor.getPlantillaDocumentoPorId(locPlantilla.getIdDocumentoTasaMenor());
			System.out.println(locPlantilla.getListaPlantillasAtributos());
			PlantillaAtributoDinamico locPlantillaAtributo = new PlantillaAtributoDinamico();
			locPlantillaAtributo.setNombre("Cantidad de balanzas");
			locPlantillaAtributo.setTipo(PlantillaAtributoDinamico.Tipo.ENTERO);
			locPlantilla.addPlantillaAtributoDinamico(locPlantillaAtributo);
			systemDocumentoTasaMenor.updatePlantillaDocumentoTasaMenor(locPlantilla);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void addObligacionTasaMenor(){
		try{
			PlantillaDocumentoTasaMenor locPlantilla = systemDocumentoTasaMenor.findListaPlantillaDocumentoTasaMenor(null).get(0);
			locPlantilla = systemDocumentoTasaMenor.getPlantillaDocumentoPorId(locPlantilla.getIdDocumentoTasaMenor());
			DocumentoTasaMenor locDocumento = locPlantilla.generarDocumentoTasaMenor();
			Obligacion locObligacion = this.getObligacion();
			locObligacion.setDocumentoEspecializado(locDocumento);
			systemObligacion.addObligacion(getASanta(), locObligacion);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	private Obligacion getObligacion() throws Exception{
//		PlantillaObligacion locPlantilla = this.getPlantillaObligacion();
		Obligacion locObligacion = new Obligacion();
//		locObligacion.setNombre(locPlantilla.getNombre());
		locObligacion.setPersona(getASanta());
		return locObligacion;
	}
	
	private PersonaFisica getASanta() throws TrascenderFrameworkException, RemoteException{
		PersonaFisica locPersonaFisica = systemPersonaFisica.getPersonaFisicaPorId(0);
		return locPersonaFisica;
	}
	
//	private PlantillaObligacion getPlantillaObligacion() throws RemoteException, TrascenderException{
//		return (PlantillaObligacion) systemPlantillaObligaciones.findListaPlantillaObligaciones(null, null).get(0);
//	}
	
	@Test
	public void testUpdateObligacion(){
		try{
			Obligacion locObligacion = systemObligacion.getObligacionPorId(60834);
			DocumentoTasaMenor locDocumento = systemDocumentoTasaMenor.getDocumentoTasaMenor(locObligacion);
			List<AtributoDinamico<?>> locLista = locDocumento.generarAtributosDinamicos();
			AtributoDinamicoEntero locAtributo = (AtributoDinamicoEntero) locLista.get(0);
			locAtributo.setValor(2l);
			locDocumento.setListaAtributosDinamicos(locLista);
			locObligacion.setDocumentoEspecializado(locDocumento);
			systemObligacion.updateObligacion(locObligacion);
		} catch (Exception e){
			e.printStackTrace();
		}
	}

}
