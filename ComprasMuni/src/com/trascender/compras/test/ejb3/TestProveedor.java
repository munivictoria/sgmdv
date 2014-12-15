package com.trascender.compras.test.ejb3;


import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.junit.Before;
import org.junit.Test;

import com.trascender.compras.recurso.persistent.suministros.Proveedor;
import com.trascender.compras.system.interfaces.SystemAdministracionProveedores;
import com.trascender.framework.system.interfaces.SystemUsuario;

public class TestProveedor {
	
	private long llave = -1;
	private SystemUsuario systemUsuario;
	private SystemAdministracionProveedores systemProveedor;

	@Before
	public void setUp() throws Exception {
		try{
			Context ctx = new InitialContext();
			systemUsuario = (SystemUsuario) ctx.lookup(SystemUsuario.JNDI_NAME);
			llave = systemUsuario.login("root", "Emilia15");
			systemProveedor = (SystemAdministracionProveedores) ctx.lookup(SystemAdministracionProveedores.JNDI_NAME);
			systemProveedor.setLlave(llave);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFindProveedores(){
		try{
//			System.out.println(systemProveedor.findListadoProveedores(null, null, null, null));
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetProveedorPorId(){
		try{
			Proveedor locProveedor = systemProveedor.findProveedorByID(4l);
//			System.out.println(locProveedor.getGrupoProveedor());
			System.out.println(locProveedor.getProveedorLocal());
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	@Test
	public void findListaGrupoProveedoresPorNodo(){
		try {
//			// armo el arbol para un grupoProveedor aver si funca
//			GrupoProveedor locGrupo = systemProveedor
//					.findGrupoProveedorPorNombre("raiz");
//			imprimirarbol(locGrupo);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	//metodo rec
//	public void imprimirarbol(GrupoProveedor pGrupo) throws RemoteException, Exception{
//		List<GrupoProveedor> listahijos = systemProveedor.findListaGrupoProveedoresPorNodo(pGrupo);
//		System.out.println("tamaño lista = "+listahijos.size());
//		System.out.println("grupo"+pGrupo.getNombre()+" id = "+pGrupo.getIdGrupoProveedor());
//		for(GrupoProveedor cadaGrupo : listahijos){
//			System.out.println("subgrupo = "+cadaGrupo.getNombre()+"grupo que depende = "+cadaGrupo.getGrupo().getNombre());
//			imprimirarbol(cadaGrupo);
//			
//		}
//	}


}
