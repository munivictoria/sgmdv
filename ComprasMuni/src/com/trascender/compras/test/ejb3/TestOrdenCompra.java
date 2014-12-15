package com.trascender.compras.test.ejb3;


import static org.junit.Assert.*;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.junit.Before;
import org.junit.Test;

import com.trascender.compras.recurso.persistent.suministros.OrdenCompra;
import com.trascender.compras.recurso.persistent.suministros.Proveedor;
import com.trascender.compras.system.interfaces.SystemAdministracionOrdenCompra;
import com.trascender.compras.system.interfaces.SystemAdministracionProveedores;
import com.trascender.framework.system.interfaces.SystemUsuario;

public class TestOrdenCompra {
	
	private long llave = -1;
	private SystemUsuario systemUsuario;
	private SystemAdministracionOrdenCompra systemOrdenCompra;
	private SystemAdministracionProveedores systemProveedor;
	
	@Before
	public void setUp() throws Exception {
		try{
			Context ctx = new InitialContext();
			systemUsuario =  (SystemUsuario) ctx.lookup(SystemUsuario.JNDI_NAME);
			llave = systemUsuario.login("root", "Emilia15");
			
			systemOrdenCompra = (SystemAdministracionOrdenCompra) ctx.lookup(SystemAdministracionOrdenCompra.JNDI_NAME);
			systemOrdenCompra.setLlave(llave);
			
			systemProveedor = (SystemAdministracionProveedores) ctx.lookup(SystemAdministracionProveedores.JNDI_NAME);
			systemProveedor.setLlave(llave);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFindOrdenCompra(){
		try{
//			System.out.println(systemOrdenCompra.findOrdenCompra(null, null, null, null, null));
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFirmarOrdenCompra() throws Exception {
		try {
			OrdenCompra locOrden = this.systemOrdenCompra.findOrdenCompraByID(27l);
				assertNotNull(locOrden);
				System.out.println(locOrden);
				System.out.println(locOrden.getEstado());
			
//				fail();
			System.out.println(this.systemOrdenCompra.firmarOrdenCompra(locOrden));
				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testTransferirOrdenCompra() throws Exception {
		try {
			OrdenCompra locOrden = this.systemOrdenCompra.findOrdenCompraByID(27l);
				assertNotNull(locOrden);
				System.out.println(locOrden);
				
			Proveedor locNuevoProveedor = this.systemProveedor.findProveedorByID(23l);
				assertNotNull(locNuevoProveedor);
				System.out.println(locNuevoProveedor);
				
				
			systemOrdenCompra.transferirOrdenCompra(locOrden, locNuevoProveedor, "Transferencia madafaca");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
