package com.trascender.habilitaciones.test.ejb3;

import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.naming.InitialContext;

import org.junit.BeforeClass;
import org.junit.Test;

import com.trascender.catastro.recurso.persistent.Calle;
import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.catastro.recurso.persistent.SubParcela;
import com.trascender.catastro.recurso.persistent.Zona;
import com.trascender.catastro.recurso.persistent.Zonificacion;
import com.trascender.catastro.system.interfaces.SystemInformacionGeografica;
import com.trascender.catastro.system.interfaces.SystemInformacionParcelaria;
import com.trascender.framework.recurso.persistent.Domicilio;
import com.trascender.framework.recurso.persistent.Localidad;
import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.framework.recurso.persistent.RfrCalle;
import com.trascender.framework.system.interfaces.SystemMunicipalidad;
import com.trascender.framework.system.interfaces.SystemPersonaFisica;
import com.trascender.framework.system.interfaces.SystemUsuario;
import com.trascender.framework.util.JAserciones;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.PlantillaObligacion;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroGrupoZona;
import com.trascender.habilitaciones.recurso.persistent.osp.ConsumoBasico;
import com.trascender.habilitaciones.recurso.persistent.osp.DocumentoOSP;
import com.trascender.habilitaciones.recurso.persistent.osp.ServicioOSP;
import com.trascender.habilitaciones.system.interfaces.SystemDocumentoOSP;
import com.trascender.habilitaciones.system.interfaces.SystemObligacion;
import com.trascender.habilitaciones.system.interfaces.SystemPlantillaObligaciones;

public class DocumentoOSPTest extends JAserciones{
	
	private static SystemUsuario systemUsuario;
	private static SystemDocumentoOSP systemDocumentoOSP;
	private static SystemInformacionParcelaria systemParcela;
	private static SystemPersonaFisica systemPersonaFisica; 
	private static SystemPlantillaObligaciones systemPlantillaObligaciones;
	private static SystemInformacionGeografica systemInfGeografica;
	private static SystemMunicipalidad systemMunicipalidad;
	private static SystemObligacion systemObligacion; 
	

	private static long llave = 0;

	@BeforeClass
	public static void setUpBeforeClass() {
		try {
			InitialContext initialContext = new InitialContext();

			systemUsuario = (SystemUsuario) initialContext.lookup(SystemUsuario.JNDI_NAME);
			llave = systemUsuario.login("root", "Emilia15");

			systemDocumentoOSP = (SystemDocumentoOSP) new InitialContext().lookup(SystemDocumentoOSP.JNDI_NAME);
			systemDocumentoOSP.setLlave(llave);
			
			systemParcela = (SystemInformacionParcelaria) new InitialContext().lookup(SystemInformacionParcelaria.JNDI_NAME);
			systemParcela.setLlave(llave);
			
			systemPersonaFisica = (SystemPersonaFisica) new InitialContext().lookup(SystemPersonaFisica.JNDI_NAME);
			systemPersonaFisica.setLlave(llave);
			
			systemPlantillaObligaciones = (SystemPlantillaObligaciones) new InitialContext().lookup(SystemPlantillaObligaciones.JNDI_NAME);
			systemPlantillaObligaciones.setLlave(llave);
			
			systemInfGeografica = (SystemInformacionGeografica) new InitialContext().lookup(SystemInformacionGeografica.JNDI_NAME);
			systemInfGeografica.setLlave(llave);

			systemMunicipalidad =(SystemMunicipalidad) new InitialContext().lookup(SystemMunicipalidad.JNDI_NAME);
			systemMunicipalidad.setLlave(llave);
			
			systemObligacion = (SystemObligacion) new InitialContext().lookup(SystemObligacion.JNDI_NAME);
			systemObligacion.setLlave(llave);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFindConsumoBasico() throws Exception{
		try{
//			List locLista = this.systemDocumentoOSP.getListaConsumosBasicos();
//			assertNotNull("Lista Nula", locLista);
//			assertIsEmpty("La lista esta Vacia",locLista);
//			
//			mostrarLista(ConsumoBasico.class, locLista);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFindDocumentoOSP() throws Exception{
		try{
			PersonaFisica locPersona = this.systemPersonaFisica.getPersonaFisicaPorCuim("20-34335998-6");
				assertNotNull(locPersona);
				
			SubParcela locSubParcela = null; //this.systemParcela.findListaSubParcela(this.systemParcela.getParcelaPorId(13757L), null).iterator().next();
				assertNotNull(locSubParcela);
				
			List locListaResultado = this.systemDocumentoOSP.findListaDocumentosOSP(null, locSubParcela, null);
				assertNotNull(locListaResultado);
				assertIsEmpty(locListaResultado);
				
			mostrarLista(DocumentoOSP.class, locListaResultado);
			System.out.println("Codigo medidor: "+((DocumentoOSP)locListaResultado.get(0)).getCodigoMedidor()+ " coef zon: "+ ((DocumentoOSP)locListaResultado.get(0)).getCoeficienteZonal());
			
		}catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	@Test
	public void testFindObligacioneOSP() throws Exception{
		try{
			PersonaFisica locPersona = this.systemPersonaFisica.getPersonaFisicaPorCuim("20-34335998-6");
				assertNotNull(locPersona);
				
			Parcela locParcela = this.systemParcela.getParcelaPorId(13757l);
				assertNotNull(locParcela);
				
			SubParcela locSubParcela = locParcela.getListaSubParcelas().iterator().next();
				System.out.println(locSubParcela.getIdParcela());
//			List locListaResultado = this.systemObligacion.findListaObligacionesOSP(null, locParcela, null);
//				assertNotNull(locListaResultado);
//				assertIsEmpty(locListaResultado);
//				
//			mostrarLista(Obligacion.class, locListaResultado);
		}catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	@Test
	public void testAddDocumentoOSP() throws Exception{
		//RECUPERO TODOS LOS DATOS NECESARIOS PARA GENERAR EL DOCUMENTO.{UNA PERSONA, UNA PARCELA, UNA OBLIGACION, DOMICILIO, UN SERVICIO Y POR ULTIMO ARMO EL DOCUMENTO}
		
		//CON LA PLANTILLA GENERO LA OBLIGACION
		PlantillaObligacion locPlantillaObligacion = this.systemPlantillaObligaciones.getPlantillaObligacion(12L);
        	assertNotNull(locPlantillaObligacion);
        	System.out.println("Plantilla: "+ locPlantillaObligacion);
        	
        //CON LA OBLIGACION Y LA PERSONA GENERO LA OBLIGACION	
        Obligacion locObligacion = this.systemPlantillaObligaciones.generarArbol(locPlantillaObligacion);
        	assertNotNull(locObligacion);
        	System.out.println("Obligacion: "+ locObligacion);
        	
        PersonaFisica locPersona = this.systemPersonaFisica.getPersonaFisicaPorCuim("20-17412018-9");
        	assertNotNull(locPersona);
        	System.out.println("Persona: "+ locPersona);
        	
        //CON LA PARCELA Y EL DOMICILIO Y EL SERVICIO GENERO EL DOCUMENTO	
        Parcela locParcela = this.systemParcela.getParcelaPorId(13758L);
        	assertNotNull(locParcela);
        	System.out.println("Parcela: "+ locParcela);
    	
        Domicilio locDomicilio = this.generarDomicilio("3100", "1553");
        	assertNotNull(locParcela);
        	System.out.println(locDomicilio);
        	
        ServicioOSP locServicio = this.systemDocumentoOSP.getServicioOSPPorId(5l);
        	assertNotNull(locServicio);
        	System.out.println("Servicio: "+locServicio);
        
        	Random locRandom = new Random();
        	
        DocumentoOSP locDocumento = new DocumentoOSP();
        locDocumento.setNombre("Primer Documento :D");
        locDocumento.setDomicilio(locDomicilio);
        locDocumento.setParcela(locParcela);
        	Calendar locFecha = Calendar.getInstance();
        	locFecha.set(Calendar.YEAR, 2011);
        	locFecha.set(Calendar.DAY_OF_MONTH, 1);
        	locFecha.set(Calendar.MONTH, 1);
        locDocumento.setFechaCreacion(locFecha.getTime());
        locDocumento.setFechaInicioActividad(locFecha.getTime());
//        locDocumento.setRegistroAlicuota(locServicio);
        
        locObligacion.setDocumentoEspecializado(locDocumento);
        
        try{
        	for(int i =0; i<2; i++){
        		System.out.println("OBLIGACION NRO: "+ i);
        	 
            locDocumento.setNumeroCuenta(locRandom.nextInt(500));
            locDocumento.setNumeroSubCuenta(locRandom.nextInt(500));
            System.out.println(locDocumento.getNumeroCuenta());
            System.out.println(locDocumento.getNumeroSubCuenta());
        	this.systemObligacion.addObligacion(locPersona, locObligacion);
        	}
        }catch (Exception e) {
			e.printStackTrace();
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
	public void testAddConsumoBasico() throws Exception{
		try {
			ConsumoBasico locConsumoBasico = new ConsumoBasico();
				locConsumoBasico.setActivo(true);
				locConsumoBasico.setConsumoInicial(0d);
				locConsumoBasico.setConsumoPorExcedente(0d);
				locConsumoBasico.setSuperficieMejorasMaximo(0d);
				locConsumoBasico.setSuperficieMejorasMinimo(0d);
				
			this.systemDocumentoOSP.addConsumoBasico(locConsumoBasico);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAdgsd() throws Exception{
		try {
			TipoParametroGrupoZona asd = new TipoParametroGrupoZona();
				asd.setActivo(true);
				asd.setFechaAlta(Calendar.getInstance().getTime());
				asd.setIdTipoParametro(432l);
				asd.setNombreGrupoZona("un NOMBRE");
				asd.setNombreVariable("UN_NOMBRE");
//				asd.setPeriodoLiquidacion(SecurityMgr.getInstance().getPeriodoAnual(Calendar.getInstance()));
				asd.setZonificacion(new Zonificacion());
				
				System.out.println(asd.getListaZonas());
				
				TipoParametroGrupoZona asd2 = (TipoParametroGrupoZona)asd.clone();
				
				System.out.println(asd.getListaZonas());
				
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void trollFace() throws Exception{
		Double xx = 999999999999d;
		

		System.out.println((xx * 2.36847678));
		
		System.out.println(xx.longBitsToDouble(xx.longValue()));
	}
}
