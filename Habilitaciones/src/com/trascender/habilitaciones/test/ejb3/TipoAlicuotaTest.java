package com.trascender.habilitaciones.test.ejb3;

import java.util.List;

import javax.naming.InitialContext;

import org.junit.BeforeClass;
import org.junit.Test;

import com.trascender.framework.recurso.persistent.CodigoCiiu;
import com.trascender.framework.system.interfaces.SystemMunicipalidad;
import com.trascender.framework.system.interfaces.SystemUsuario;
import com.trascender.framework.util.JAserciones;
import com.trascender.framework.util.Periodicidad;
import com.trascender.habilitaciones.recurso.persistent.RegAlicuota.TipoRegAlicuota;
import com.trascender.habilitaciones.recurso.persistent.TipoAlicuota.Estado;
import com.trascender.habilitaciones.recurso.persistent.TipoObligacion;
import com.trascender.habilitaciones.recurso.persistent.osp.ServicioOSP;
import com.trascender.habilitaciones.recurso.persistent.shps.Rubro;
import com.trascender.habilitaciones.system.interfaces.SystemAlicuota;
import com.trascender.habilitaciones.system.interfaces.SystemDocumentoOSP;
import com.trascender.habilitaciones.system.interfaces.SystemTipoTasa;

public class TipoAlicuotaTest extends JAserciones{

	private static SystemUsuario systemUsuario;

	private static SystemAlicuota systemAlicuota;
	
	private static SystemDocumentoOSP systemDocumentoOSP;

	private static SystemTipoTasa systemObligacion;
	
	private static SystemMunicipalidad systemMuni;
	
	private static long llave = 0;

	@BeforeClass
	public static void setUpBeforeClass() {
		try {
			InitialContext initialContext = new InitialContext();

			systemUsuario = (SystemUsuario) initialContext.lookup(SystemUsuario.JNDI_NAME);
			llave = systemUsuario.login("root", "Emilia15");

			systemAlicuota = (SystemAlicuota) new InitialContext().lookup(SystemAlicuota.JNDI_NAME);
			systemAlicuota.setLlave(llave);
			
			systemDocumentoOSP = (SystemDocumentoOSP) initialContext.lookup(SystemDocumentoOSP.JNDI_NAME);
			systemDocumentoOSP.setLlave(llave);

			systemObligacion = (SystemTipoTasa) initialContext.lookup(SystemTipoTasa.JNDI_NAME);
			systemObligacion.setLlave(llave);
			
			systemMuni = (SystemMunicipalidad) initialContext.lookup(SystemMunicipalidad.JNDI_NAME);
			systemMuni.setLlave(llave);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFindServicioOSP() throws Exception{
		try{
			List locListaCliente =  this.systemDocumentoOSP.findListaServiciosOSP(null, null);
			assertNotNull("Lista Nula", locListaCliente);
			assertIsEmpty("Lista Vacia", locListaCliente);
			
			mostrarLista(ServicioOSP.class, locListaCliente);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFindListaRubros() throws Exception {
		try {
//			List<Rubro> locListaRubros = this.systemAlicuota.findListaRubros(null, null, null, null, null, null, null);
//			
//			
//			for (Rubro cadaRubro : locListaRubros) {
//				System.out.println(cadaRubro);
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddRubro() throws Exception {
		try {
			
			TipoObligacion locTipoObligacion = this.systemObligacion.findListaTipoObligacion("SHPS", null).get(0);
			CodigoCiiu locCodigoCiiu = this.systemMuni.getCodigoCiiuById(1623l);
				assertNotNull(locCodigoCiiu);
				
			Rubro locRubro = new Rubro();
				locRubro.setCodigo("7789");
				locRubro.setEstado(Estado.ACTIVO);
				locRubro.setMinimo(11D);
				locRubro.setNombre("unnobre");
				locRubro.setPeriodicidad(Periodicidad.ANUAL);
				locRubro.setPorcentual(false);
				locRubro.setValor(12d);
				locRubro.setTipoRegAlicuota(TipoRegAlicuota.FIJO_ANUAL);
				locRubro.setTipoObligacion(locTipoObligacion);
				
				locRubro.setCodigoCiiu(locCodigoCiiu);
			this.systemAlicuota.addRegistroAlicuota(locRubro);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
