/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

package com.trascender.expedientes.test.expediente;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.junit.BeforeClass;

import com.trascender.expedientes.recurso.filtro.FiltroFaseCatalogo;
import com.trascender.expedientes.recurso.filtro.FiltroTramiteCatalogo;
import com.trascender.expedientes.recurso.persistent.FaseCatalogo;
import com.trascender.expedientes.recurso.persistent.FaseProcedimiento;
import com.trascender.expedientes.recurso.persistent.PlazoProcedimiento;
import com.trascender.expedientes.recurso.persistent.Procedimiento;
import com.trascender.expedientes.recurso.persistent.TramiteCatalogo;
import com.trascender.expedientes.system.interfaces.SystemCatalogos;
import com.trascender.expedientes.system.interfaces.SystemExpedientes;
import com.trascender.expedientes.system.interfaces.SystemProcedimientos;
import com.trascender.framework.system.interfaces.SystemUsuario;

public class TestExpediente {

	private static SystemUsuario systemUsuario;
	private static SystemExpedientes systemExpedientes;
	private static SystemProcedimientos systemProcedimiento;
	private static SystemCatalogos systemCatalogos;

	private static long llave = 0;

	@BeforeClass
	public static void inicializar() {

		Context ctx;
		try {
			ctx = new InitialContext();
			systemUsuario = (SystemUsuario) ctx.lookup(SystemUsuario.JNDI_NAME);
			llave = systemUsuario.login("root", "Emilia15");

			systemExpedientes = (SystemExpedientes) ctx.lookup(SystemExpedientes.JNDI_NAME);
			systemExpedientes.setLlave(llave);

			systemProcedimiento = (SystemProcedimientos) ctx.lookup(SystemProcedimientos.JNDI_NAME);
			systemProcedimiento.setLlave(llave);

			systemCatalogos = (SystemCatalogos) ctx.lookup(SystemCatalogos.JNDI_NAME);
			systemCatalogos.setLlave(llave);

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void addTramiteCatalogo() throws RemoteException, Exception {
		String[] arr = {"t firma", "t doc", "t ficha", "t cancelacion", "t confirma", "t revision", "t aviso", "t seguridad", "t suscripcion", "t sellado"};
		for(int i = 0; i < arr.length; i++) {
			TramiteCatalogo pTramiteCatalogo = new TramiteCatalogo();
			pTramiteCatalogo.setNombre(arr[i]);
			systemCatalogos.addTramiteCatalogo(pTramiteCatalogo);
		}

	}

	public void addFaseCatalogo() {
		String arr[] = {"Fase Inicial", "Fase General", "Fase Secundaria", "Fase Final"};
		List<TramiteCatalogo> lista = new ArrayList<TramiteCatalogo>();
		try {
			lista = systemCatalogos.findListaTramiteCatalogos(new FiltroTramiteCatalogo()).getListaResultados();
		} catch(RemoteException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}

		for(int i = 0; i < arr.length; i++) {
			FaseCatalogo pFaseCatalogo = new FaseCatalogo();
			pFaseCatalogo.setNombre(arr[0]);
			List<TramiteCatalogo> l = new ArrayList<TramiteCatalogo>(lista.subList(i, lista.size() - 1));
			pFaseCatalogo.setListaTramitesCatalogos(l);
			try {
				systemCatalogos.addFaseCatalogo(pFaseCatalogo);
			} catch(RemoteException e) {
				e.printStackTrace();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void addProcedimiento() {
		String arr[] = {"Procedimiento 1", "Procedimiento 2", "Procedimiento 3", "Procedimiento 4"};

		List<FaseCatalogo> lista = new ArrayList<FaseCatalogo>();
		try {
			lista = systemCatalogos.findListaFaseCatalogo(new FiltroFaseCatalogo()).getListaResultados();
			for(int i = 0; i < arr.length; i++) {
				PlazoProcedimiento plazo = new PlazoProcedimiento();
				plazo.setDias(30);
				plazo.setDiasCorridos(false);

				Procedimiento p = new Procedimiento();
				p.setNombre(arr[i]);
				// p.setResponsable(r);
				p.setPlazo(plazo);

				List<FaseCatalogo> l = new ArrayList<FaseCatalogo>(lista.subList(i, lista.size() - 1));
				for(FaseCatalogo faseCatalogo : l) {
					faseCatalogo = systemCatalogos.getFaseCatalogoPorId(faseCatalogo.getIdFaseCatalogo());
					p.getListaNodosHijos().add(new FaseProcedimiento(faseCatalogo, p));
				}
				systemProcedimiento.addProcedimiento(p);
			}
		} catch(RemoteException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void getListaExpedienteSoyResponsableTest() {
		try {
			System.out.println(systemExpedientes.getListaExpedienteSoyResponsable(llave));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}