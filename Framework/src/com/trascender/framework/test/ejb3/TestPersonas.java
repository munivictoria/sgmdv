package com.trascender.framework.test.ejb3;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.naming.InitialContext;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

import com.trascender.framework.recurso.persistent.Domicilio;
import com.trascender.framework.recurso.persistent.Localidad;
import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.framework.recurso.persistent.PersonaFisica.Sexo;
import com.trascender.framework.recurso.persistent.PersonaJuridica;
import com.trascender.framework.recurso.persistent.PersonaJuridica.TipoSocietario;
import com.trascender.framework.recurso.persistent.Socio;
import com.trascender.framework.system.interfaces.SystemMunicipalidad;
import com.trascender.framework.system.interfaces.SystemPersonaFisica;
import com.trascender.framework.system.interfaces.SystemPersonaJuridica;
import com.trascender.framework.system.interfaces.SystemUsuario;
import com.trascender.framework.util.JAserciones;

public class TestPersonas extends JAserciones{

	
	private static SystemUsuario systemUsuario;
	private static SystemPersonaFisica systemPersonaFisica;
	private static SystemMunicipalidad systemMunicipalidad;
	private static SystemPersonaJuridica systemJuridica;
	private static long llave = 0;
	
	@BeforeClass
	public static void inicializar(){
		try{
			InitialContext initial = new InitialContext();
			
			systemUsuario = (SystemUsuario) initial.lookup(SystemUsuario.JNDI_NAME);
			llave = systemUsuario.login("root", "Emilia15");
			
			systemPersonaFisica = (SystemPersonaFisica) initial.lookup(SystemPersonaFisica.JNDI_NAME);
			systemPersonaFisica.setLlave(llave);
			
			systemJuridica = (SystemPersonaJuridica) initial.lookup(SystemPersonaJuridica.JNDI_NAME);
			systemJuridica.setLlave(llave);
			
			systemMunicipalidad = (SystemMunicipalidad) initial.lookup(SystemMunicipalidad.JNDI_NAME);
			systemMunicipalidad.setLlave(llave);
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFindListaPersonasFisicas() throws Exception{
		try {
			String[] nombres = {"Ignacio", "igNa", null };
			String[] apellidos = {"Tortul", "torT", null};
			
			for(String cadaNombre : nombres){
				for(String cadaApellido : apellidos){
////					for (Object cadaOb: this.systemPersonaFisica.findPersonaFisica(null, cadaApellido, null, null, null, null, null, null, null, null, null, null, null, null)) {
//						PersonaFisica cadaPersona = (PersonaFisica) cadaOb;
//						System.out.println(cadaPersona);
//					}
				}
			}
//			List<PersonaFisica> locListaResultados = this.systemPersonaFisica.findPersonaFisica(null, null, null, null, null, null, null, null, null, null, null, null, null, null);
//				assertNotNull("La lista de resultados es nula", locListaResultados);
//				assertIsEmpty(locListaResultados);
//				
//			for (PersonaFisica cadaPersona : locListaResultados) {
//				System.out.println(cadaPersona);
//			}
			
			
		} catch (Exception locE) {
			locE.printStackTrace();
		}
	}
	
	@Test
	public void testAddFisica(){
		try{
			PersonaFisica personaFisica = new PersonaFisica();
			personaFisica.setNombre("Fernando");
			personaFisica.setApellido("Gareis");
			personaFisica.setCuil("20-14094116-4");
			personaFisica.setTipoDocumento(PersonaFisica.TipoDocumento.DNI);
			personaFisica.setNumeroDocumento("14094116");
			personaFisica.setEdad(22);
			personaFisica.setTelefono("4350908");
			
			Domicilio locDomicilio = new Domicilio();
			Localidad locLocalidad = systemMunicipalidad.getLocalidadPorId(22);
			assertNotNull(locLocalidad);
			locDomicilio.setLocalidad(locLocalidad);
			locDomicilio.setCalle("Miguel david");
			locDomicilio.setBarrio("Santa Lucia");
			locDomicilio.setCodigoPostal("0343");
			locDomicilio.setManzana("123");
			personaFisica.setDomicilio(locDomicilio);
			personaFisica.setDomicilioPostal(locDomicilio);
			
			systemPersonaFisica.addPersonaFisica(personaFisica);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddJuridica(){
		try{
			PersonaJuridica personaJuridica = new PersonaJuridica();
			personaJuridica.setCuit("10-12345678-1");
			personaJuridica.setRazonSocial("PersonaJuridicaFernando");

			Localidad locLocalidad = systemMunicipalidad.getLocalidadPorId(23);
			assertNotNull(locLocalidad);
			Domicilio locDomicilio = new Domicilio();
			locDomicilio.setCalle("Miguel david");
			locDomicilio.setBarrio("Santa lucia");
			locDomicilio.setManzana("33");
			locDomicilio.setLocalidad(locLocalidad);
			
			personaJuridica.setDomicilio(locDomicilio);
			personaJuridica.setDomicilioPostal(locDomicilio);
			
			PersonaFisica titular = systemPersonaFisica.getPersonaFisicaPorId(25003);
			assertNotNull(titular);
//			personaJuridica.setTitular(titular);
			
			systemJuridica.addPersonaJuridica(personaJuridica);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFindPersonaFisica() throws Exception{
//		List locListaPersonas = this.systemPersonaFisica.findPersonaFisica(null, null, null, null, null, "DNI", null, null, null, null, null, null, null,null);
//		
//		System.out.println(locListaPersonas.size() + " - "+ contarDNIs(locListaPersonas));
	}
	
	private int contarDNIs(List pListaPersonas){
		int locContador =0;
		for(Object cadaObje : pListaPersonas){
			PersonaFisica cadaPersona = (PersonaFisica) cadaObje;
			if(cadaPersona.getTipoDocumento().equals(PersonaFisica.TipoDocumento.DNI)){
				locContador++;
			}
		}
		return locContador;
	}
	
	@Test
	public void testFindPersonaJuridica() throws Exception{
		try{
			List locListaResultado = null; //this.systemJuridica.findPersonaJuridica(null, null, null, null, null, null);
			assertNotNull("Lista nula", locListaResultado);
			assertTrue(!locListaResultado.isEmpty());
			
			for(Object cadaOjbect : locListaResultado){
				System.out.println(cadaOjbect);
			}
			
		}catch (Exception locE) {
			locE.printStackTrace();
		}
	}
	
	@Test
	public void testDeletePersonaF() throws Exception {
		try {
			PersonaFisica locPersona = this.systemPersonaFisica.getPersonaFisicaPorId(38l);
				assertNotNull(locPersona);
				
			this.systemPersonaFisica.removePersonaFisica(locPersona);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdatePersonaF() throws Exception {
		try {
			PersonaFisica locPersona = this.systemPersonaFisica.getPersonaFisicaPorCuim("20-34335998-6");
				assertNotNull(locPersona);
				
				locPersona.setSexo(Sexo.FEMENINO);
			this.systemPersonaFisica.updatePersonaFisica(locPersona);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetPersonaJuridicaById() throws Exception {
		try {
			PersonaJuridica locPersona = this.systemJuridica.getPersonaJuridicaPorId(120l);
				assertNotNull(locPersona);
				
			System.out.println(locPersona);
			for(Socio cadaSocio : locPersona.getListaSocios()){
				System.out.println("\t" + cadaSocio);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testname() throws Exception {
		try {
			List<PersonaJuridica> locListaResultado = null;//this.systemJuridica.findPersonaJuridica(null, null, null, null, null, null);
				assertNotNull("Lista nula", locListaResultado);
				
			for(PersonaJuridica cadaPersona : locListaResultado){
				Socio locSocio = new Socio();
//				locSocio.setCargo(TipoSocietario.FONDO_COMUN_DE_INVERSION);
//				locSocio.setPersona(locPersona);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
