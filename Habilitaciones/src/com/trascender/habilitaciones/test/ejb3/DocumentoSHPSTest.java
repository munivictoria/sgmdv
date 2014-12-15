package com.trascender.habilitaciones.test.ejb3;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.naming.InitialContext;

import org.junit.BeforeClass;
import org.junit.Test;

import com.trascender.catastro.recurso.persistent.Calle;
import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.catastro.system.interfaces.SystemInformacionGeografica;
import com.trascender.catastro.system.interfaces.SystemInformacionParcelaria;
import com.trascender.framework.recurso.persistent.Domicilio;
import com.trascender.framework.recurso.persistent.Localidad;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.framework.recurso.persistent.RfrCalle;
import com.trascender.framework.system.interfaces.SystemMunicipalidad;
import com.trascender.framework.system.interfaces.SystemPersonaFisica;
import com.trascender.framework.system.interfaces.SystemUsuario;
import com.trascender.framework.util.JAserciones;
import com.trascender.habilitaciones.recurso.filtros.FiltroObligacionSHPS;
import com.trascender.habilitaciones.recurso.persistent.LogModificacionDocEsp;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.PlantillaObligacion;
import com.trascender.habilitaciones.recurso.persistent.TipoObligacion;
import com.trascender.habilitaciones.recurso.persistent.shps.ClausuraSHPS;
import com.trascender.habilitaciones.recurso.persistent.shps.DocumentoSHPS;
import com.trascender.habilitaciones.recurso.persistent.shps.LibretaSanitaria;
import com.trascender.habilitaciones.recurso.persistent.shps.LocalComercial;
import com.trascender.habilitaciones.recurso.persistent.shps.TransporteVehicular;
import com.trascender.habilitaciones.system.interfaces.SystemAlicuota;
import com.trascender.habilitaciones.system.interfaces.SystemBromatologia;
import com.trascender.habilitaciones.system.interfaces.SystemObligacion;
import com.trascender.habilitaciones.system.interfaces.SystemPlantillaObligaciones;
import com.trascender.habilitaciones.system.interfaces.SystemTipoTasa;
import com.trascender.habilitaciones.system.interfaces.SystemTransito;

public class DocumentoSHPSTest extends JAserciones{
	
	
	private static SystemUsuario systemUsuario;

	private static SystemBromatologia systemBromatologia;
	private static SystemTransito systemTransito;
	private static SystemObligacion systemObligacion;
	private static SystemInformacionParcelaria systemParcela;
	private static SystemPersonaFisica systemPersonaF;
	private static SystemPlantillaObligaciones systemPlantillaObligaciones;
	private static SystemAlicuota systemAlicuota;
	private static SystemMunicipalidad systemMunicipalidad;
	private static SystemInformacionGeografica systemInfGeografica;
	private static SystemTipoTasa systemTipoTasa;
	
	private static long llave = 0;

	

	@BeforeClass
	public static void setUpBeforeClass() {
		try {
			InitialContext initialContext = new InitialContext();

			systemUsuario = (SystemUsuario) initialContext.lookup(SystemUsuario.JNDI_NAME);
			llave = systemUsuario.login("root", "Emilia15");

			systemBromatologia = (SystemBromatologia) initialContext.lookup(SystemBromatologia.JNDI_NAME);
			systemBromatologia.setLlave(llave);

			systemTransito = (SystemTransito) initialContext.lookup(SystemTransito.JNDI_NAME);
			systemTransito.setLlave(llave);
			
			systemObligacion = (SystemObligacion) initialContext.lookup(SystemObligacion.JNDI_NAME);
			systemObligacion.setLlave(llave);
			
			systemParcela = (SystemInformacionParcelaria) initialContext.lookup(SystemInformacionParcelaria.JNDI_NAME);
			systemObligacion.setLlave(llave);
			
			systemPersonaF = (SystemPersonaFisica) initialContext.lookup(SystemPersonaFisica.JNDI_NAME);
			systemPersonaF.setLlave(llave);
			
			systemPlantillaObligaciones = (SystemPlantillaObligaciones) initialContext.lookup(SystemPlantillaObligaciones.JNDI_NAME);
			systemPlantillaObligaciones.setLlave(llave);
			
			systemAlicuota = (SystemAlicuota) initialContext.lookup(SystemAlicuota.JNDI_NAME);
			systemAlicuota.setLlave(llave);
			
			systemMunicipalidad = (SystemMunicipalidad) initialContext.lookup(SystemMunicipalidad.JNDI_NAME);
			systemMunicipalidad.setLlave(llave);
			
			systemInfGeografica = (SystemInformacionGeografica) initialContext.lookup(SystemInformacionGeografica.JNDI_NAME);
			systemInfGeografica.setLlave(llave);
			
			systemTipoTasa = (SystemTipoTasa) initialContext.lookup(SystemTipoTasa.JNDI_NAME);
			systemTipoTasa.setLlave(llave);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFindLocalesComerciales () throws Exception{
		try{
			Parcela locParcela = this.systemParcela.getParcelaPorId(13755L);
				assertNotNull(locParcela);
				System.out.println("PARCELA: "+locParcela);
			
//			List locListaResultado = this.systemBromatologia.findListaLocalesComerciales(null, null, locParcela, null);
//				assertNotNull("Lista NULA", locListaResultado);
//				assertIsEmpty("Lista Vacia", locListaResultado);
			
//			mostrarLista(LocalComercial.class, locListaResultado);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetalgoPorID() throws Exception{
		try{
			LocalComercial locLocalcomercial = this.systemBromatologia.getLocalComercialPorId(1L);
			assertNotNull("No encontro el local", locLocalcomercial);
			
			System.out.println(locLocalcomercial);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void addLocalComercial() throws Exception{
		try{
			LocalComercial locLocalComercial = new LocalComercial();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFindListaClausurasPorUsuario() throws Exception{
		try{
			List locListaResultado = this.systemBromatologia.findListaClausurasPorUsuario();
				assertNotNull("Lista Nula", locListaResultado);
				assertIsEmpty("ListaVacia", locListaResultado);
				
			mostrarLista(ClausuraSHPS.class, locListaResultado);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFindVehiculos(){
		try{
//			List locListaResultado = this.systemBromatologia.findListaTransportesVehiculares(null, null, null, null);
//				assertNotNull("Lista Nula", locListaResultado);
//				assertIsEmpty("ListaVacia", locListaResultado);
//				
//			mostrarLista(TransporteVehicular.class, locListaResultado);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testAddTransporteVehicular() throws Exception{
		try{
			TransporteVehicular locTransporteVehicular = new TransporteVehicular();
			locTransporteVehicular.setDescripcion("Una descripcion");
			locTransporteVehicular.setFechaAlta(Calendar.getInstance().getTime());
			locTransporteVehicular.setNumeroInscripcion("156789");
//			Vehiculo locVehiculo = (Vehiculo) this.systemTransito.findListaVehiculo(null).get(0);
//			locTransporteVehicular.setVehiculo(locVehiculo);
			this.systemBromatologia.addTransporteVehicular(locTransporteVehicular);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFindLibretaSanitaria() throws Exception{
		try{
//			List locListaResultado = this.systemBromatologia.findListaLibretasSanitarias(null, null, null);
//				assertNotNull("Lista Nula", locListaResultado);
//				assertIsEmpty("ListaVacia", locListaResultado);
//				
//			mostrarLista(LibretaSanitaria.class, locListaResultado);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddLibretaSanitaria() throws Exception{
		try{
		LibretaSanitaria locLibretaSanitaria = new LibretaSanitaria();
		
		locLibretaSanitaria.setActivo(true);
		locLibretaSanitaria.setNumeroLibretaSanitaria(1445);
			PersonaFisica locPersona = this.systemPersonaF.getPersonaFisicaPorCuim("20-34335998-6");
			assertNotNull(locPersona);
			System.out.println(locPersona);
//		locLibretaSanitaria.setPersonaFisica(locPersona);
		locLibretaSanitaria.setPersonaFisica(locPersona);
		
		System.out.println(locLibretaSanitaria.getPersonaFisica().getIdPersonaFisica());
//		fail();
		this.systemBromatologia.addLibretaSanitaria(locLibretaSanitaria);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFindListaObligacionesSHPS() throws Exception{
		Persona locPersona = this.systemPersonaF.getPersonaFisicaPorCuim("20-34335998-6");
			assertNotNull(locPersona);
			
//		String locNroInscripcion = "4567";
//			assertNotNull(locNroInscripcion);
//			
//		Persona[] pPersonaParams = {null, locPersona};
//		String[] pNrosInscripcionesParams = {null, locNroInscripcion};
//			
//		System.out.println(pPersonaParams.length);
//		System.out.println(pNrosInscripcionesParams.length);
//		int i=0; int j=0;
//		try{
//			for (; i < pPersonaParams.length; i++) {
//				System.out.println(i);
//				for (; j < pNrosInscripcionesParams.length; j++) {
//					System.out.println(j);
//					List locListaResultado = this.systemObligacion.findListaObligacionesSHPS(pPersonaParams[i], pNrosInscripcionesParams[j]);
//					assertNotNull(locListaResultado);
//					assertIsEmpty(locListaResultado);
//					
//					mostrarLista(Obligacion.class, locListaResultado);
//				}
//			}
//		}catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("ERROR CUANDO LA PERSONA ERA: -" + pPersonaParams[i]+ "- \nY EL NRO DE INSCRIPCION ERA: -"+ pNrosInscripcionesParams[j] +"-");
//		}
			
			try{
//			List<Obligacion> locListaResultado = this.systemObligacion.findListaObligacionesSHPS(locPersona, null);
//			assertNotNull(locListaResultado);
//			assertIsEmpty(locListaResultado);
//			
//			mostrarLista(Obligacion.class, locListaResultado);
			
//			for (Obligacion cadaObligacion : locListaResultado) {
//				if(cadaObligacion.getNumeroTramite() == 47){
//					System.out.println(cadaObligacion);
//					System.out.println(((DocumentoSHPS)cadaObligacion.getDocumentoEspecializado()).getListaLibretaSanitarias().iterator().next());
//					System.out.println(((DocumentoSHPS)cadaObligacion.getDocumentoEspecializado()).getListaRegAlicuotas().iterator().next());
//				}
//			}
			}catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	@Test
	public void testAddDocumentoSHPS() throws Exception{
		try{
		//CON LA PLANTILLA GENERO LA OBLIGACION
		PlantillaObligacion locPlantillaObligacion = this.systemPlantillaObligaciones.getPlantillaObligacion(6L);
        	assertNotNull(locPlantillaObligacion);
        	System.out.println("Plantilla: "+ locPlantillaObligacion);
        	
        //CON LA OBLIGACION Y LA PERSONA GENERO LA OBLIGACION	
        Obligacion locObligacion = this.systemPlantillaObligaciones.generarArbol(locPlantillaObligacion);
        	assertNotNull(locObligacion);
        	System.out.println("Obligacion: "+ locObligacion);
        	
        PersonaFisica locPersona = this.systemPersonaF.getPersonaFisicaPorCuim("20-34335998-6");
        	assertNotNull(locPersona);
        	System.out.println("Persona: "+ locPersona);
        
        TipoObligacion locTipoObligacion = this.systemTipoTasa.findListaTipoObligacion("SHPS", null).get(0);
        	assertNotNull(locTipoObligacion);
        	System.out.println(locTipoObligacion);
       
//        List<Rubro> locLista = this.systemAlicuota.findListaRubros(null, locTipoObligacion, null, null, null, null, null);
//        		assertNotNull(locLista);
//        		assertIsEmpty(locLista);
        		
//        RegAlicuota[] locRegistro = {locLista.get(0)};
//        		System.out.println("Registro Alicuota: "+ locRegistro);
//        		System.out.println(locRegistro);
        		
        LibretaSanitaria locLibretaSanitaria = this.systemBromatologia.getLibretaSanitariaPorId(1L);
        		assertNotNull(locLibretaSanitaria);
        		System.out.println(locLibretaSanitaria);
        		
        LocalComercial locLocalComercial = this.systemBromatologia.getLocalComercialPorId(2L);
        		assertNotNull(locLocalComercial);
        		System.out.println("ID: "+locLocalComercial.getIdLocalComercial() + " "+ locLocalComercial);
        
        //**********************************************************************************************************
        	
        DocumentoSHPS locDocumento = new DocumentoSHPS();
        	locDocumento.setNumeroInscripcion("55474");
        	locDocumento.setNombre("Documento from Logica");
        		Calendar locFechaInicio = Calendar.getInstance();
        			locFechaInicio.set(Calendar.YEAR, 2011);
        			locFechaInicio.set(Calendar.DAY_OF_MONTH, 5);
        			locFechaInicio.set(Calendar.MONTH, 5);
        			
        		Calendar locFechaFin = Calendar.getInstance();
        			locFechaInicio.set(Calendar.YEAR, 2013);
        			locFechaInicio.set(Calendar.DAY_OF_MONTH, 11);
        			locFechaInicio.set(Calendar.MONTH, 3);
        			
        	locDocumento.setFechaInicioActividad(locFechaInicio.getTime());
        	locDocumento.setFechaCeseActividad(locFechaFin.getTime());
        	locDocumento.setFechaCreacion(locFechaInicio.getTime());
        	
        	locDocumento.setDenominacionEntidad("Gran Hermano");
//        	this.addElementos(locDocumento.getListaRegAlicuotas(), locRegistro);
        	locDocumento.setDomicilio(generarDomicilio("3100", "347"));
        	locDocumento.addLibretaSanitaria(locLibretaSanitaria);
        	
        	locDocumento.getListaLocalesComerciales().add(locLocalComercial);
        	
        	locObligacion.setDocumentoEspecializado(locDocumento);
        	
        	System.out.println(locDocumento);
        	System.out.println(locObligacion);
        	System.out.println(locDocumento.getListaLibretaSanitarias().size());
        	System.out.println(locDocumento.getListaRegAlicuotas().size());
//        	fail("Tenemos todo?"); //Fuck yeah...
        	
        	this.systemObligacion.addObligacion(locPersona, locObligacion);
        	
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void addElementos(Collection pColeccion, Object[] pElementos){
		for(Object cadaOb : pElementos){
			pColeccion.add(cadaOb);
		}
	}
	
	private Domicilio generarDomicilio(String pCodigoPostal, String pAltura) throws Exception {
		Domicilio locDomicilio = new Domicilio();
		try{
		System.out.println("GENERANDO DOMICILIO *********************************");
			Localidad locLocalidad = this.systemMunicipalidad.getLocalidadMunicipal();
				assertNotNull(locLocalidad);
				System.out.println("\tLocalidad: "+locLocalidad);
			Calle locCalle = this.systemInfGeografica.getCallePorId(3L);
				assertNotNull(locCalle);
				System.out.println("\tCalle: "+locCalle);
				
		locDomicilio.setLocalidad(locLocalidad);
		locDomicilio.setCalle(locCalle.toString());
		RfrCalle locRFR = new RfrCalle();
			locRFR.setCodigo(locCalle.getCodigo());
			locRFR.setNombre(locCalle.getNombre());
			locRFR.setIdAbstractCalle(locCalle.getIdCalle());
		locDomicilio.setRelacionCalle(locRFR);
		
		locDomicilio.setCodigoPostal( ((pCodigoPostal != null)?pCodigoPostal:"") );
		locDomicilio.setNumero( ((pAltura!=null)?pAltura:""));
		
		System.out.println("\tDomicilio Final: "+locDomicilio);
		System.out.println("FIN GENERAR DOMICILIO *******************************");
		}catch (Exception e) {
			System.out.println("EL DOMICILIO SE GENERO PARA EL ORTO. Segui participando.");
		}
		
		return locDomicilio;
	}
	@Test
	public void testLogModificaciones(){
		
		try{
			
			Obligacion locObligacion = systemObligacion.findListaObligacionesSHPS(new FiltroObligacionSHPS()).getListaResultados().get(0);
			DocumentoSHPS locDocSHPS = systemBromatologia.getDocumentoHabilitanteSHPS(locObligacion);
			
			LogModificacionDocEsp locLogMod = new LogModificacionDocEsp();
			locLogMod.setComentario("comment");
			locLogMod.setDocHabilitanteEspecializado(locDocSHPS);
			locLogMod.setFecha(new Date());
			locDocSHPS.getListaLogsModificaciones().add(locLogMod);
			System.out.println(locDocSHPS.getListaLogsModificaciones().size());
			System.out.println(locDocSHPS.getListaLogsModificaciones().get(0).getComentario());
			systemBromatologia.updateDocumentoSHPS(locDocSHPS);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
