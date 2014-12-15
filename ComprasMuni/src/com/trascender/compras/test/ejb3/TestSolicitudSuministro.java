package com.trascender.compras.test.ejb3;


import javax.naming.Context;
import javax.naming.InitialContext;

import org.junit.Before;
import org.junit.Test;

import com.trascender.compras.recurso.persistent.suministros.LineaSolicitudSuministro;
import com.trascender.compras.recurso.persistent.suministros.Proveedor;
import com.trascender.compras.recurso.persistent.suministros.SolicitudSuministro;
import com.trascender.compras.system.interfaces.SystemAdministracionProveedores;
import com.trascender.compras.system.interfaces.SystemAdministracionSolicitudSuministro;
import com.trascender.framework.system.interfaces.SystemUsuario;

public class TestSolicitudSuministro {
	
	private long llave = -1;
	private SystemUsuario systemUsuario;
	private SystemAdministracionSolicitudSuministro systemSolicitud;
	private SystemAdministracionProveedores systemProveedores;

	@Before
	public void setUp() throws Exception {
		try{
			Context ctx = new InitialContext();
			systemUsuario = (SystemUsuario) ctx.lookup(SystemUsuario.JNDI_NAME);
			llave = systemUsuario.login("root", "Emilia15");
			systemSolicitud = (SystemAdministracionSolicitudSuministro) ctx.lookup(SystemAdministracionSolicitudSuministro.JNDI_NAME);
			systemSolicitud.setLlave(llave);
			
			systemProveedores = (SystemAdministracionProveedores) ctx.lookup(SystemAdministracionProveedores.JNDI_NAME);
			systemProveedores.setLlave(llave);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFindListaSolicitud(){
		try{
//			System.out.println(systemSolicitud.findListadoSolicitudSuministro(null, null, null, null, null, null));
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFirmarSolicitud(){
		try{
//			SolicitudSuministro locSolitud = (SolicitudSuministro) systemSolicitud.findListadoSolicitudSuministro(null, null, null, null, null, 2).get(0);
//			locSolitud = systemSolicitud.findSolicitudSuministroByID(locSolitud.getIdSolicitudSuministro());
//			System.out.println(locSolitud);
////			Assert.assertNotNull(locSolitud);
//			systemSolicitud.firmarSolicitudSuminstro(locSolitud);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFindListaLineas(){
		try{
//			Proveedor locProveedor = (Proveedor) systemProveedores.findListadoProveedores(null, "Autoservicio", null, null).get(0);
//			for (LineaSolicitudSuministro cadaLinea : systemSolicitud.findListaLineasSolicitudSuministro(locProveedor)){
//				System.out.println(cadaLinea.getBien());
//				System.out.println(cadaLinea.getCantidad());
//			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFinalizarSolicitudSuministro(){
//		try{
//			SolicitudSuministro locSolicitud = (SolicitudSuministro) systemSolicitud.findListadoSolicitudSuministro(null, null, null, null, null, null).get(0);
//			locSolicitud = systemSolicitud.findSolicitudSuministroByID(locSolicitud.getIdSolicitudSuministro());
//			locSolicitud.setEstado(SolicitudSuministro.Estado.ANULADA);
//			locSolicitud.setComentarioFinalizacion("Se anula por improcedente");
//			systemSolicitud.finalizarSolicitud(locSolicitud);
//		} catch (Exception e){
//			e.printStackTrace();
//		}
	}

}
