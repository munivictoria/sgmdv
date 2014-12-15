package com.trascender.habilitaciones.test.ejb3;

import java.util.Calendar;
import java.util.List;

import javax.naming.InitialContext;

import org.junit.BeforeClass;
import org.junit.Test;

import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.catastro.system.interfaces.SystemInformacionGeografica;
import com.trascender.catastro.system.interfaces.SystemInformacionParcelaria;
import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.framework.system.interfaces.SystemMunicipalidad;
import com.trascender.framework.system.interfaces.SystemPersonaFisica;
import com.trascender.framework.system.interfaces.SystemUsuario;
import com.trascender.framework.util.JAserciones;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.PlantillaObligacion;
import com.trascender.habilitaciones.recurso.persistent.TipoObligacion;
import com.trascender.habilitaciones.recurso.persistent.tgi.DocumentoTGI;
import com.trascender.habilitaciones.system.interfaces.SystemAlicuota;
import com.trascender.habilitaciones.system.interfaces.SystemDocumentoTGI;
import com.trascender.habilitaciones.system.interfaces.SystemObligacion;
import com.trascender.habilitaciones.system.interfaces.SystemPlantillaObligaciones;
import com.trascender.habilitaciones.system.interfaces.SystemDocumentoTransito;

public class DocumentoTGITest extends JAserciones{
	private static SystemUsuario systemUsuario;

	private static SystemDocumentoTGI systemTGI;
	private static SystemDocumentoTransito systemTransito;
	private static SystemObligacion systemObligacion;
	private static SystemInformacionParcelaria systemParcela;
	private static SystemPersonaFisica systemPersonaF;
	private static SystemPlantillaObligaciones systemPlantillaObligaciones;
	private static SystemAlicuota systemAlicuota;
	private static SystemMunicipalidad systemMunicipalidad;
	private static SystemInformacionGeografica systemInfGeografica;
	
	private static long llave = 0;

	

	@BeforeClass
	public static void setUpBeforeClass() {
		try {
			InitialContext initialContext = new InitialContext();

			systemUsuario = (SystemUsuario) initialContext.lookup(SystemUsuario.JNDI_NAME);
			llave = systemUsuario.login("root", "Emilia15");

			systemTGI = (SystemDocumentoTGI) new InitialContext().lookup(SystemDocumentoTGI.JNDI_NAME);
			systemTGI.setLlave(llave);

			systemTransito = (SystemDocumentoTransito) new InitialContext().lookup(SystemDocumentoTransito.JNDI_NAME);
			systemTransito.setLlave(llave);
			
			systemObligacion = (SystemObligacion) new InitialContext().lookup(SystemObligacion.JNDI_NAME);
			systemObligacion.setLlave(llave);
			
			systemParcela = (SystemInformacionParcelaria) new InitialContext().lookup(SystemInformacionParcelaria.JNDI_NAME);
			systemParcela.setLlave(llave);
			
			systemPersonaF = (SystemPersonaFisica) new InitialContext().lookup(SystemPersonaFisica.JNDI_NAME);
			systemPersonaF.setLlave(llave);
			
			systemPlantillaObligaciones = (SystemPlantillaObligaciones) new InitialContext().lookup(SystemPlantillaObligaciones.JNDI_NAME);
			systemPlantillaObligaciones.setLlave(llave);
			
			systemAlicuota = (SystemAlicuota) new InitialContext().lookup(SystemAlicuota.JNDI_NAME);
			systemAlicuota.setLlave(llave);
			
			systemMunicipalidad = (SystemMunicipalidad) new InitialContext().lookup(SystemMunicipalidad.JNDI_NAME);
			systemMunicipalidad.setLlave(llave);
			
			systemInfGeografica = (SystemInformacionGeografica) new InitialContext().lookup(SystemInformacionGeografica.JNDI_NAME);
			systemInfGeografica.setLlave(llave);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddDocumentoTGI() throws Exception{
		try{
		PlantillaObligacion locPlantillaObligacion = systemPlantillaObligaciones.getPlantillaObligacion(13L);
			assertNotNull(locPlantillaObligacion);
			assertTrue(locPlantillaObligacion.getTipoObligacion().getNombre().equals("TGI"));
			System.out.println(locPlantillaObligacion
					);
		Obligacion locObligacion = this.systemPlantillaObligaciones.generarArbol(locPlantillaObligacion);
			assertNotNull(locPlantillaObligacion);
			
		PersonaFisica locPersona = systemPersonaF.getPersonaFisicaPorCuim("20-34335998-6");
			assertNotNull(locPersona);
			
		//---------------------------------------------------------------------
			
		DocumentoTGI locDocumentoTGI = new DocumentoTGI();
		locDocumentoTGI.setDomicilio(locPersona.getDomicilio());
			Calendar locFecha = Calendar.getInstance();
			locFecha.set(Calendar.YEAR, 2011);
			locFecha.set(Calendar.DAY_OF_MONTH, 19);
			locFecha.set(Calendar.MONTH, 4);
		locDocumentoTGI.setFechaInicioActividad(locFecha.getTime());
		locDocumentoTGI.setNombre("Obligacion TGI UNO");
			Parcela locParcela = systemParcela.getParcelaPorId(13755L);
			assertNotNull(locParcela);
		locDocumentoTGI.setParcela(locParcela);
		
		locObligacion.setDocumentoEspecializado(locDocumentoTGI);
		
		systemObligacion.addObligacion(locPersona, locObligacion);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testFindObligacionesTGI() throws Exception{
//		List locListaRetorno =  systemObligacion.findListaObligacionesTGI(null, null,Obligacion.Estado.PENDIENTE_DE_ALTA);
//			assertNotNull(locListaRetorno);
//			assertIsEmpty(locListaRetorno);
//			
//			mostrarLista(Obligacion.class, locListaRetorno);
	}
	
	@Test
	public void testGetDocumentoTGI() throws Exception {
		try {
			Obligacion locObligacion = this.systemObligacion.getObligacionPorId(91l);
				assertNotNull(locObligacion);
				System.out.println(locObligacion);
			DocumentoTGI locDocumentoTGI = this.systemTGI.getDocumentoTGI(locObligacion);
				assertNotNull(locDocumentoTGI);
				System.out.println(locDocumentoTGI);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
