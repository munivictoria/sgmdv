package com.trascender.framework.test.ejb3;

import static org.junit.Assert.*;

import javax.naming.InitialContext;

import org.junit.BeforeClass;
import org.junit.Test;

import com.trascender.framework.recurso.persistent.DigestoMunicipal;
import com.trascender.framework.system.interfaces.SystemMunicipalidad;
import com.trascender.framework.system.interfaces.SystemUsuario;
import com.trascender.framework.util.JAserciones;

public class TestDigestoMunicipal extends JAserciones{
	
	private static SystemUsuario systemUsuario;
	private static SystemMunicipalidad systemMunicipalidad;
	
	private static long llave = 0;
	@BeforeClass
	public static void inicializar(){
		try{
			InitialContext initial = new InitialContext();
			systemUsuario = (SystemUsuario) initial.lookup(SystemUsuario.JNDI_NAME);
			llave = systemUsuario.login("root", "Emilia15");
			systemMunicipalidad = (SystemMunicipalidad) initial.lookup(SystemMunicipalidad.JNDI_NAME);
			systemMunicipalidad.setLlave(llave);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFindDigesto(){
		try{
			DigestoMunicipal locDigesto = systemMunicipalidad.getDigestoMunicipalPorId(1l);
				assertNotNull(locDigesto);
				
			System.out.println(locDigesto);
			if(locDigesto.getListaConcordancias() != null && 
					!locDigesto.getListaConcordancias().isEmpty()){
				for (DigestoMunicipal cadaDigestoAsociado : locDigesto.getListaConcordancias()) {
					System.out.println("\t"+ cadaDigestoAsociado.toString());
				}
			}else{
				System.out.println("es nulo o esta vacio");
			}
				
			System.out.println();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddConcordanciaDigesto() throws Exception {
		try {
			DigestoMunicipal locDigesto = this.systemMunicipalidad.getDigestoMunicipalPorId(1l);
			
			DigestoMunicipal locDigestoConcordancia = this.systemMunicipalidad.getDigestoMunicipalPorId(3l);
			
			locDigesto.addConcordancia(locDigestoConcordancia);
			
			this.systemMunicipalidad.updateDigestoMunicipal(locDigesto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
	
