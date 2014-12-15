package com.trascender.compras.test.ejb3;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.junit.BeforeClass;
import org.junit.Test;

import com.ibm.icu.util.Calendar;
import com.trascender.compras.recurso.persistent.suministros.ActaApertura;
import com.trascender.compras.recurso.persistent.suministros.Proveedor;
import com.trascender.compras.recurso.persistent.suministros.RepresentanteActaApertura;
import com.trascender.compras.system.interfaces.SystemAdministracionLicitacion;
import com.trascender.compras.system.interfaces.SystemAdministracionProveedores;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.system.interfaces.SystemPersonaFisica;
import com.trascender.framework.system.interfaces.SystemUsuario;
import com.trascender.framework.util.JAserciones;

public class TestLicitacion extends JAserciones{
	
	private static SystemUsuario systemUsuario;
	private static SystemPersonaFisica systemPersona;
	private static SystemAdministracionLicitacion systemLicitacion;
	private static SystemAdministracionProveedores systemProveedores;
	
	
	@BeforeClass
	public static void setUpBefor() throws Exception{
		Context locContext = new InitialContext();
		
		systemUsuario = (SystemUsuario) locContext.lookup(SystemUsuario.JNDI_NAME);
		long llave = systemUsuario.login("root", "Emilia15");
		
		systemLicitacion = (SystemAdministracionLicitacion) locContext.lookup(SystemAdministracionLicitacion.JNDI_NAME);
		systemLicitacion.setLlave(llave);
		
		systemPersona = (SystemPersonaFisica) locContext.lookup(SystemPersonaFisica.JNDI_NAME);
		systemPersona.setLlave(llave);
		
		systemProveedores = (SystemAdministracionProveedores) locContext.lookup(SystemAdministracionProveedores.JNDI_NAME);
		systemProveedores.setLlave(llave);
	}
	
	@Test
	public void testAddActaApertura() throws Exception{
		try {
			ActaApertura locActa = new ActaApertura();
			
			Proveedor locProveedor = this.systemProveedores.findProveedorByID(1l);
				assertNotNull(locProveedor);
				System.out.println(locProveedor);
				
			Persona locPersona = this.systemPersona.getPersonaFisicaPorId(1l);
				assertNotNull(locPersona);
				System.out.println(locPersona);
				
			RepresentanteActaApertura locRepresentante = new RepresentanteActaApertura();
				locRepresentante.setActaApertura(locActa);
				locRepresentante.setCargo("Gerente de Compras");
				locRepresentante.setPersona(locPersona);
			
//			locActa.setFechaApertura(Calendar.getInstance().getTime());
			locActa.addRepresentante(locRepresentante);
			locActa.setLugar("La muni de victoria");
			locActa.setRegistroEscrito("un monton de blablablabalblblalblbalbalbalbalblblbalbalba");
			
			System.out.println("NUEVO ACTA: " + this.systemLicitacion.addActaApertura(locActa));
		} catch (Exception e) {
			e.printStackTrace(); 
		}
	}
	
	@Test
	public void testgetActaAperturaById() throws Exception {
		try {
			ActaApertura locActa = this.systemLicitacion.getActaAperturaById(1l);
				assertNotNull(locActa);
				
			System.out.println(locActa);
			System.out.println("Lugar: " + locActa.getLugar());
			System.out.println("Registro: " + locActa.getRegistroEscrito());
		//	System.out.println("Fecha Apertura: " + locActa.getFechaApertura());
			
			System.out.println("Oferentes: *****");
			int candOferentes=0;
			
			System.out.println("Representantes: *****");
			for(RepresentanteActaApertura cadaRepresentante : locActa.getListaRepresentantes()){
				System.out.println("\t" + cadaRepresentante);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFindListaActaApertura() throws Exception {
		try {
			Proveedor locProveedor = this.systemProveedores	.findProveedorByID(1l);
			assertNotNull(locProveedor);

		} catch (Exception e) {
		}
	}
	
	@Test
	public void testUpdateAcataApertura() throws Exception {
		try {
			ActaApertura locActa = this.systemLicitacion.getActaAperturaById(1l);
				locActa.setLugar("El bar enfrente de la muni.");
				
			this.systemLicitacion.updateActaApertura(locActa);
			
			locActa = this.systemLicitacion.getActaAperturaById(1l);
			System.out.println(locActa + " " + locActa.getLugar());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRemoveAcataApertura() throws Exception {
		try {
			ActaApertura locActa = this.systemLicitacion.getActaAperturaById(1l);
				locActa.setLugar("El bar enfrente de la muni.");
				
			System.out.println("Borre el acta?: " + ((this.systemLicitacion.deleteActaApertura(locActa)?"Si":"No")));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
