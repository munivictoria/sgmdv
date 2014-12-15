package com.trascender.catastro.test.testEjb3;

import java.util.Calendar;

import javax.naming.InitialContext;

import org.junit.BeforeClass;
import org.junit.Test;

import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.catastro.recurso.persistent.RegistroPropietario;
import com.trascender.catastro.recurso.persistent.TituloPropiedadParcelario;
import com.trascender.catastro.system.interfaces.SystemInformacionParcelaria;
import com.trascender.catastro.system.interfaces.SystemRegistroPropiedad;
import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.framework.system.interfaces.SystemPersonaFisica;
import com.trascender.framework.system.interfaces.SystemUsuario;
import com.trascender.framework.util.JAserciones;

public class RegistroPropiedadTest extends JAserciones{
	
	private static SystemRegistroPropiedad systemRegistroPropiedad;
	private static SystemUsuario systemUsuario;
	private static SystemInformacionParcelaria systemParcela; 
	private static SystemPersonaFisica systemPersonaF;
	
	private static long llave = 0;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		try {
			InitialContext initialContext = new InitialContext();

			systemUsuario = (SystemUsuario) initialContext.lookup(SystemUsuario.JNDI_NAME);
			llave = systemUsuario.login("root", "Emilia15");

			systemRegistroPropiedad = (SystemRegistroPropiedad) initialContext.lookup(SystemRegistroPropiedad.JNDI_NAME);
			systemRegistroPropiedad.setLlave(llave);
			
			systemParcela = (SystemInformacionParcelaria) initialContext.lookup(SystemInformacionParcelaria.JNDI_NAME);
			systemParcela.setLlave(llave);
			
			systemPersonaF= (SystemPersonaFisica) initialContext.lookup(SystemPersonaFisica.JNDI_NAME);
			systemPersonaF.setLlave(llave);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testAddTituloPropiedad() throws Exception{
		try{
		TituloPropiedadParcelario locTitulo = new TituloPropiedadParcelario();
//		locTitulo.setActivo(true);
		Calendar locFecha = Calendar.getInstance();
			locFecha.set(Calendar.YEAR, 2011);
			locFecha.set(Calendar.DAY_OF_MONTH, 5);
			locFecha.set(Calendar.MONTH, 17);
		locTitulo.setFechaInscripcion(locFecha.getTime());
		
//		locTitulo.setNroHojas(7);
		locTitulo.setNroTomo(7);
//		TipoTransaccionCatastral locTipo = (TipoTransaccionCatastral) this.systemRegistroPropiedad.findListaTipoTransaccionCatastral("Compra").get(0);
//			assertNotNull(locTipo);
//		locTitulo.setTipoTransaccionCatastral(locTipo);
		Parcela locParcela = this.systemParcela.getParcelaPorId(13760L);
			assertNotNull(locParcela);
			System.out.println(locParcela.getIdParcela());
		locTitulo.setParcela(locParcela);
			System.out.println(locTitulo.getParcela().getIdParcela());
		
		RegistroPropietario locRegistroProp = new RegistroPropietario();
			locRegistroProp.setDescripcion("asdasd");
			PersonaFisica locPersona = this.systemPersonaF.getPersonaFisicaPorCuim("20-24300004-2");
				assertNotNull(locPersona);	
				locRegistroProp.setPersona(locPersona);
				locRegistroProp.setTituloPropiedad(locTitulo);
		locTitulo.getListaRegistrosPropietarios().add(locRegistroProp);
		
		this.systemRegistroPropiedad.addTituloPropiedad(locTitulo);
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
