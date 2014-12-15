package com.trascender.framework.test.ejb3;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;

import org.junit.BeforeClass;
import org.junit.Test;

import ar.trascender.criterio.clases.Criterio;

import com.trascender.framework.recurso.persistent.CodigoCiiu;
import com.trascender.framework.recurso.persistent.DigestoMunicipal;
import com.trascender.framework.recurso.persistent.GrupoCiiu;
import com.trascender.framework.recurso.persistent.Localidad;
import com.trascender.framework.recurso.persistent.Pais;
import com.trascender.framework.recurso.persistent.Provincia;
import com.trascender.framework.recurso.persistent.SeccionCiiu;
import com.trascender.framework.system.interfaces.SystemMunicipalidad;
import com.trascender.framework.system.interfaces.SystemUsuario;

import junit.framework.Assert;

public class testMunicipalidadBean extends Assert {
	
	private static SystemMunicipalidad systemMunicipalidad;
	private static SystemUsuario systemUsuario;
	
	private static Long llave;
	
	
	
	@BeforeClass
	public static void SetUpBeforeClass() throws Exception{
		InitialContext initial = new InitialContext();
		
		systemUsuario = (SystemUsuario) initial.lookup(SystemUsuario.JNDI_NAME);
		llave = systemUsuario.login("root", "Emilia15");
		
		systemMunicipalidad = (SystemMunicipalidad) initial.lookup(SystemMunicipalidad.JNDI_NAME);
		systemMunicipalidad.setLlave(llave);
	}
	
	@Test
	public void findListaSeccionCiiu() throws Exception{
		try {
//			System.out.println(this.systemMunicipalidad.findListaCodigosCiiu(null, null,null,null));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
	@Test
	public void addProvincia() throws Exception{
		Provincia locProvincia = new Provincia();
		locProvincia.setAbreviatura("JP");
		locProvincia.setCodigo("Code kennet2");
		locProvincia.setNombre("Japon");
		
		Pais locPais = this.systemMunicipalidad.getPaisPorId(16L);
		assertNotNull("No tenemos pais", locPais);
		locProvincia.setPais(locPais);
		
		try{
			this.systemMunicipalidad.addProvincia(locProvincia);
		}catch (Exception locE) {
			locE.printStackTrace();
		}		
	}
	
	@Test
	public void testUpdateProvincia() throws Exception{
		Provincia locProvincia = null; //(Provincia) this.systemMunicipalidad.findProvincia("Entre Rios", null).get(0);
		assertNotNull("No hay provincia",locProvincia);
		
		System.out.println(locProvincia);
		Long locId = new Long(locProvincia.getIdProvincia());
		try{
			this.systemMunicipalidad.updateProvincia(locProvincia);
			
			System.out.println("Updated provincia: "+ this.systemMunicipalidad.getProvinciaPorId(locId));
		}catch (Exception locE) {
			locE.printStackTrace();
		}
	}
	
	@Test
	public void addPais() throws Exception{
		Pais locPais = new Pais();
		locPais.setAbreviatura("MY");
		locPais.setNombre("Malasya");
		try{
			this.systemMunicipalidad.addPais(locPais);
			
			Pais locElmismoPais = null; //(Pais) this.systemMunicipalidad.findPais("Malasya");
			
			System.out.println(locElmismoPais);
		}catch (Exception locE) {
			locE.printStackTrace();
		}
	}
	
	@Test
	public void testFindLocalidades() throws Exception{
		
		List locListaBusqueda2 = null; //this.systemMunicipalidad.findLocalidad(null, null, null, this.systemMunicipalidad.getPaisPorId(4L));
		for(Object cadaObject : locListaBusqueda2){
			Localidad cadaLocalidad = (Localidad) cadaObject;
			System.out.println(cadaLocalidad);
		}
		fail("averga");
		
//		String[] locNombres = {"Crespo", null};
//		String[] locCodigosPostales = {"1826", null};
//		Provincia[] locProvincias = {this.systemMunicipalidad.getProvinciaPorId(7L), null};
//			assertNotNull("falta 1 provincia", locProvincias[0]);
//		Pais[] locPaises = {this.systemMunicipalidad.getPaisPorId(4L), null};
//			assertNotNull("falta 1 pais", locPaises[0]);
//		
//		int indexNombre = 0; int indexCodigoPostal = 0; int indexProvincia = 0; int indexPais = 0;
//		try{
//			for(String cadaNombre : locNombres){
//				indexCodigoPostal=0;
//				for(String cadaCodigoPostal : locCodigosPostales){
//					indexProvincia=0;
//					for(Provincia cadaProvincia : locProvincias){
//						indexPais=0;
//						for(Pais cadaPais : locPaises){
//							List locListaBusqueda = this.systemMunicipalidad.findLocalidad(cadaNombre, cadaCodigoPostal, cadaProvincia, cadaPais);
//							for(Object cadaObject : locListaBusqueda){
//								Localidad cadaLocalidad = (Localidad) cadaObject;
//								System.out.println("Criterios Busqueda: Nombre: "+locNombres[indexNombre]
//								          			                   +" Codigo Postal" + locCodigosPostales[indexCodigoPostal]
//								          			                   +" Provincia: "+ locProvincias[indexProvincia]
//								          			                   +" Pais: "+ locPaises[indexPais]);
//							}
//							System.out.println("FIN BUSQUEDA:**********************************************************************");
//							indexPais++;
//							if(indexPais == 2){
//								System.out.println("Las pruebas con PAIS Fueron Satisfactorias");
//							}
//						}
//						indexProvincia++;
//						if(indexProvincia == 2){
//							System.out.println("Las pruebas con PROVINCIA Fueron Satisfactorias");
//						}
//					}
//					indexCodigoPostal++;
//					if(indexCodigoPostal == 2){
//						System.out.println("Las pruebas con CODIGO POSTAL Fueron Satisfactorias");
//					}
//				}
//				indexNombre++;
//				if(indexNombre == 2){
//					System.out.println("Las pruebas con NOMBRE Fueron Satisfactorias");
//				}
//			}
//			
//		fail("Las busqueda de localidades andan perfecto");
//			
//		}catch (Exception locE) {
//			System.out.println("El error ocurrio cuando: El estado de la busqueda era: ");
//			if(indexCodigoPostal == 2) indexCodigoPostal--;
//			if(indexNombre == 2) indexNombre--;
//			if(indexPais == 2) indexPais--;
//			if(indexProvincia == 2) indexProvincia--;
//			System.out.println("Nombre: "+locNombres[indexNombre]
//			                   +" - Codigo Postal" + locCodigosPostales[indexCodigoPostal]
//			                   +" - Provincia: "+ locProvincias[indexProvincia]
//			                   +" - Pais: "+ locPaises[indexPais]);
//			locE.printStackTrace();
//		}
		
	}
	
	@Test
	public void testUpdateDigesto() throws Exception{
//		DigestoMunicipal locDigesto = this.systemMunicipalidad.findDigestoMunicipalPorNombre("Decreto Nº 034/10");
//		
//		try{
//			
//			this.systemMunicipalidad.updateDigestoMunicipal(locDigesto);
//			
//		}catch (Exception locE) {
//			locE.printStackTrace();
//		}
//		
	} 
	
	@Test
	public void testAddLocalidad() throws Exception{
		Localidad locLocalidad = new Localidad();
		locLocalidad.setCodigoPostal("6665");
		locLocalidad.setNombre("Una localid");
		locLocalidad.setProvincia(this.systemMunicipalidad.getProvinciaPorId(6L));
		
		try{
			this.systemMunicipalidad.addLocalidad(locLocalidad);
		}catch (Exception locE) {
			locE.printStackTrace();
		}
	}
	
	@Test
	public void migrarRubrosAfip() throws Exception{
		//ACLARACION INICIAL: Esta adaptado para un archivo q paso Francisco(de la muni de Victoria)
		//La principal diferencia con los de la AFIP es q agrupo 2 grupos en 1 (2 veces xD) 
			
		 //*****************************************
        Map<String, SeccionCiiu> listaSecciones = new HashMap<String, SeccionCiiu>();
        Map<String, GrupoCiiu> listaGrupos = new HashMap<String, GrupoCiiu>();
        Map<String, CodigoCiiu> listaCodigo = new HashMap<String, CodigoCiiu>();
        //*******************************************
        
		try{
			  FileInputStream fstream = new FileInputStream("/home/jsantacruz/Escritorio/rubros_afip.csv");
	            // Creamos el objeto de entrada
	            DataInputStream entrada = new DataInputStream(fstream);
	            // Creamos el Buffer de Lectura
	            BufferedReader buffer = new BufferedReader(new InputStreamReader(entrada));
	            String cadaLinea;
	            
	            // Leer el archivo linea por linea
	            while ((cadaLinea = buffer.readLine()) != null)   {
	            	String[] locSeparacion = cadaLinea.split(";");
	            	String locCodigoSseccion = locSeparacion[0].trim();
	            	String locCodigoGrupo = locSeparacion[1].trim();
	            	String locCodigoCodigo = locSeparacion[2].trim();
	            	
	            	if(!listaSecciones.containsKey(locCodigoSseccion)){
	            		SeccionCiiu locSec = new SeccionCiiu();
            				locSec.setCodigo(locCodigoSseccion);
            				locSec.setNombre("Nombre a asignar");
            			listaSecciones.put(locCodigoSseccion, locSec);
	            	}
	            	
	            	if(!listaGrupos.containsKey((locCodigoSseccion +"/"+locCodigoGrupo))){
	            		GrupoCiiu locGrupo = new GrupoCiiu();
        					locGrupo.setCodigo(locCodigoGrupo);
        					locGrupo.setNombre("Nombre a Asignar");
        					locGrupo.setSeccionCiiu(listaSecciones.get(locCodigoSseccion));
        				listaSecciones.get(locCodigoSseccion).getListaGruposCiiu().add(locGrupo);
        				listaGrupos.put((locCodigoSseccion +"/"+locCodigoGrupo), locGrupo);	
	            	}
	            	
	            	CodigoCiiu locCodigo = new CodigoCiiu();
	            		locCodigo.setCodigo(locCodigoCodigo);
	            		locCodigo.setDescripcion(locSeparacion[3]);
	            		try{
	            			locCodigo.setInformacion(locSeparacion[4]);
	    	            }catch (ArrayIndexOutOfBoundsException locE) {
	    	            	locCodigo.setInformacion("");
	    	            }
	            		
	            		locCodigo.setGrupoCiiu(listaGrupos.get((locCodigoSseccion +"/"+locCodigoGrupo)));
    				listaGrupos.get((locCodigoSseccion +"/"+locCodigoGrupo)).getListaCodigosCiiu().add(locCodigo);
    				listaCodigo.put(locCodigoCodigo, locCodigo);
	            }
	            
	            // Cerramos el archivo
	            entrada.close();
		}catch (FileNotFoundException e) {
			System.out.println("NO encontro el archivo");
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		this.actualizacionNombresSeccion(listaSecciones);
		this.actualizacionNombresGrupos(listaGrupos);
		
		 int nroRubro = 0;
         int nroGrupo = 0;
         int nroSeccion = 0;
         System.out.println(listaGrupos.keySet());
     	for (SeccionCiiu cadaSeccion : listaSecciones.values()) {
				System.out.println(cadaSeccion.getCodigo() + " " + cadaSeccion.getNombre());
				nroSeccion++;
//				System.out.println(cadaSeccion.getCodigo());
				for (GrupoCiiu cadaGrupo : cadaSeccion.getListaGruposCiiu()) {
					System.out.println("\t" + cadaGrupo.getCodigo() + " "+ cadaGrupo.getNombre());
					nroGrupo++;
					for (CodigoCiiu cadaCodigo : cadaGrupo.getListaCodigosCiiu()) {
//						System.out.println("\t\t" + cadaCodigo.getCodigo() + " "
//													+cadaCodigo.getDescripcion() + " "
//													+cadaCodigo.getInformacion());
//						System.out.println(cadaCodigo.getInformacion());
						nroRubro++;
					}
				}
			}
         
     	System.out.println("HAY " + nroSeccion + " Secciones");
     	System.out.println("HAY " + nroGrupo + " Grupos");
     	System.out.println("HAY " + nroRubro + " Rubros");
     	
     	for(SeccionCiiu cadaSeccion : listaSecciones.values()){
//			this.systemMunicipalidad.addSeccionCiuu(cadaSeccion);
			System.out.println("Se inserto: " + cadaSeccion.getCodigo() + " " + cadaSeccion.getNombre());
		}
	}
	
	private void actualizacionNombresSeccion(Map<String, SeccionCiiu> plistaSecciones){
		String[] listaValores = { "A!Agricultura, ganaderia, caza y silvicultura",
				"B!Pesca y servicios conexos",
				"C!Explotacion  de  minas  y  canteras",
				"D!Industria manufacturera",
				"E!Electricidad, gas y agua",
				"F!Construccion",
				"G!Comercio al por mayor y al por menor; reparacion de vehiculos automotores, motocicletas, efectos personales",
				"H!Servicios de hoteleria y restaurantes",
				"I!Servicio de transporte, de almacenamiento y de comunicaciones",
				"J!Intermediacion financiera y otros servicios financieros",
				"K!Servicios inmobiliarios , empresariales y de alquiler",
				"L!Administracion publica, defensa y seguridad social obligatoria",
				"M!Enseñanza",
				"N!Servicios sociales y de salud",
				"O!Servicios comunitarios, sociales y personales n.c.p.",
				"P!Servicios de hogares privados que contratan servicio domestico",
				"Q!Servicios de organizaciones y organos extraterritoriales"
		};
		
		for(String cadaCodigo : listaValores){
			String[] listaSecciones = cadaCodigo.split("!");
			String locCodigo = listaSecciones[0];
			String locNombre = listaSecciones[1];
			
			plistaSecciones.get(locCodigo).setNombre(locNombre);
		}
	}
	
	private void actualizacionNombresGrupos(Map<String, GrupoCiiu> plistaSecciones){
		String[] listaValores = {"A/1!Cultivos agrícolas",
									"A/2!Cría de animales",
									"A/3!Servicios agrícolas y pecuarios, excepto los veterinarios",
									"A/4!Caza  y captura de animales vivos, repoblación  de animales de caza y servicios conexos",
									"A/1!Silvicultura, extracción de madera y servicios conexos",
									"B/1!Pesca y servicios conexos",
									"C/1!Extracción y aglomeración de carbón",
									"C/2!Extracción y aglomeración de lignito",
									"C/3!Extracción y aglomeración de turba",
									"C/4!Extracción de petróleo crudo y gas natural",
									"C/5!Actividades  de servicios relacionadas con la extracción de petróleo y gas, excepto las actividades",
									"C/6!Extracción de minerales y concentrados de uranio y torio",
									"C/7!Extracción de minerales de hierro",
									"C/8!Extracción de minerales metalíferos no ferrosos, excepto minerales de uranio y torio",
									"C/9!Extracción de piedra, arena y arcillas",
									"C/10!Explotación de minas y canteras n.c.p.",
									"D/1!Producción y procesamiento de carne, pescado, frutas, legumbres, hortalizas, aceites y grasas",
									"D/2!Elaboración de productos lácteos",
									"D/3!Elaboración de productos de molinería, almidones y productos derivados del almidón y de alimentos pr",
									"D/4!Elaboración de productos alimenticios n.c.p.",
									"D/5!Elaboración de bebidas",
									"D/6!Elaboración de productos de tabaco",
									"D/7!Fabricación  de hilados y  tejidos, acabado de productos textiles",
									"D/8!Fabricación de productos textiles n.c.p.",
									"D/9!Fabricación de tejidos de punto y artículos de punto y ganchillo",
									"D/10!Confección de prendas de vestir, excepto prendas de piel",
									"D/11!Curtido y terminación de cueros; fabricación de artículos de marroquinería y talabartería",
									"D/12!Fabricación de calzado y de sus partes",
									"D/13!Aserrado y cepillado de madera",
									"D/14!Fabricación de productos de madera, corcho, paja y materiales trenzables",
									"D/15!Fabricación de papel y de productos de papel",
									"D/16!Edición",
									"D/17!Impresión y servicios conexos",
									"D/18!Reproducción de grabaciones",
									"D/19!Fabricación de productos de hornos de coque",
									"D/20!Fabricación de productos de la refinación del petróleo",
									"D/21!Elaboración de combustible nuclear",
									"D/22!Fabricación de sustancias químicas básicas",
									"D/23!Fabricación de productos químicos n.c.p.",
									"D/24!Fabricación de fibras manufacturadas",
									"D/25!Fabricación de productos de caucho",
									"D/26!Fabricación de productos de plástico",
									"D/27!Fabricación de vidrio y productos de vidrio",
									"D/28!Fabricación de productos minerales no metálicos n.c.p.",
									"D/29!Industrias básicas de hierro y acero",
									"D/30!Fabricación de productos primarios de metales preciosos y metales no ferrosos",
									"D/31!Fundición de metales",
									"D/32!Fabricación de productos metálicos para uso estructural, tanques, depósitos y generadores de vapor",
									"D/33!Fabricación de productos elaborados de metal n.c.p.; servicios de trabajo de metales",
									"D/34!Fabricación de maquinaria de uso general",
									"D/35!Fabricación de maquinaria de uso especial",
									"D/36!Fabricación de aparatos de uso doméstico n.c.p.",
									"D/37!Fabricación de maquinaria de oficina, contabilidad e informática",
									"D/38!Fabricación de motores, generadores y transformadores eléctricos",
									"D/39!Fabricación de aparatos de distribución y control de la energía eléctrica",
									"D/40!Fabricación de hilos y cables aislados",
									"D/41!Fabricación de acumuladores y de pilas y baterías primarias",
									"D/42!Fabricación de lámparas eléctricas y equipo de iluminación",
									"D/43!Fabricación  de equipo eléctrico n.c.p.",
									"D/44!Fabricación de tubos, válvulas  y otros  componentes electrónicos",
									"D/45!Fabricación de transmisores de radio y televisión y de aparatos para telefonía y telegrafía con hilo",
									"D/46!Fabricación de receptores de radio y televisión, aparatos de grabación y reproducción de sonido y vi",
									"D/47!Fabricación de aparatos e instrumentos médicos y de aparatos para medir, verificar, ensayar, navegar",
									"D/48!Fabricación de instrumentos de óptica y equipo fotográfico",
									"D/49!Fabricación de relojes",
									"D/50!Fabricación de vehículos automotores",
									"D/51!Fabricación de carrocerías para vehículos automotores; fabricación de remolques y semirremolques",
									"D/52!Fabricación de partes; piezas y accesorios para vehículos automotores y sus motores",
									"D/53!Construcción y reparación de buques y embarcaciones n.c.p.",
									"D/54!Fabricación de locomotoras y de material rodante para ferrocarriles y tranvías",
									"D/55!Fabricación y reparación de aeronaves",
									"D/56!Fabricación de equipo de transporte n.c.p.",
									"D/57!Fabricación de muebles y colchones",
									"D/58!Industrias manufactureras n.c.p.",
									"D/59!Reciclamiento de desperdicios y desechos metálicos",
									"D/60!Reciclamiento de desperdicios y desechos no metálicos",
									"D/61!Terminación y teñido de pieles; fabricación de artículos de piel",
									"E/1!Generación, transporte y distribución de energía eléctrica",
									"E/2!Fabricación de gas y distribución de combustibles gaseosos por tuberías",
									"E/3!Suministro de vapor y agua caliente",
									"E/4!Captación, depuración y distribución de agua",
									"F/1!Preparación de terrenos para obras",
									"F/2!Construcción de edificios y sus partes y obras de ingeniería civil",
									"F/3!Instalaciones para edificios y obras de ingeniería civil",
									"F/4!Terminación de edificios y obras de ingeniería civil",
									"F/5!Alquiler de equipo de construcción o demolición dotado de operarios",
									"G/1!Venta de vehículos automotores, excepto motocicletas",
									"G/2!Mantenimiento y reparación de vehículos automotores, excepto motocicletas",
									"G/3!Venta de partes, piezas y accesorios de vehículos automotores",
									"G/4!Venta, mantenimiento y reparación de motocicletas y de sus partes, piezas y accesorios",
									"G/5!Venta al por menor de combustible para vehículos automotores y motocicletas",
									"G/6!Venta al por mayor en comisión o consignación",
									"G/7!Venta al por mayor de materias primas agropecuarias, de animales vivos, alimentos, bebidas y tabaco",
									"G/8!Venta al por mayor de artículos de uso doméstico y/o personal",
									"G/9!Venta al por mayor de productos intermedios, desperdicios y desechos no agropecuarios",
									"G/10!Venta al por mayor de máquinas, equipo y materiales conexos",
									"G/11!Venta al por mayor de mercancías n.c.p.",
									"G/12!Venta al por menor excepto la especializada",
									"G/13!Venta al por menor de productos alimentarios, bebidas y tabaco en comercios especializados",
									"G/14!Venta al por menor de productos n.c.p. excepto los usados, en comercios especializados",
									"G/15!Venta al por menor de artículos usados excluidos automotores y motocicletas",
									"G/16!Venta al por menor no realizada en establecimientos",
									"G/17!Reparación de efectos personales y enseres domésticos",
									"H/1!Servicios de alojamiento en hoteles, campamentos y otros tipos de hospedaje temporal",
									"H/2!Servicios de expendio de comidas y bebidas",
									"I/2!Servicio de transporte ferroviario",
									"I/2!Servicio de transporte automotor",
									"I/3!Servicio de transporte por tuberías",
									"I/4!Servicio de transporte marítimo",
									"I/5!Servicio de transporte fluvial",
									"I/6!Servicio de transporte aéreo de cargas", // segun la AFIP el I/7 SEria lo mismo pero de personas no de cargas
									"I/7!Servicios de manipulación de carga",
									"I/8!Servicios de almacenamiento y depósito",
									"I/9!Servicios complementarios para el transporte",
									"I/10!Servicios de agencias de viaje y otras actividades complementarias de apoyo turístico",
									"I/11!Servicios de gestión y logística para el transporte de mercaderías",
									"I/12!Servicios de correos",
									"I/13!Servicios de telecomunicaciones",
									"J/1!Intermediación monetaria y financiera de la banca central",
									"J/2!Intermediación monetaria y financiera de las entidades financieras bancarias y no bancarias",
									"J/3!Servicios financieros excepto los de la banca central y las entidades financieras",
									"J/4!Servicios de seguros",
									"J/5!Servicios de administración de fondos de jubilaciones y pensiones",
									"J/6!Servicios auxiliares a la actividad financiera, excepto a los servicios de seguros y de administracion",
									"J/7!Servicios auxiliares a los servicios de seguros y de administración de fondos de jubilaciones y pensiones",
									"K/1!Servicios inmobiliarios realizados por cuenta propia, con bienes propios o arrendados",
									"K/2!Servicios inmobiliarios realizados a cambio de una retribución o por contrata",
									"K/3!Alquiler de equipo de transporte",
									"K/4!Alquiler de maquinaria y equipo n.c.p.",
									"K/5!Alquiler de efectos personales y enseres domésticos n.c.p.",
									"K/6!Servicios de consultores en equipo de informática",
									"K/7!Servicios de consultores en informática y suministros de programas de informática",
									"K/8!Procesamiento de datos",
									"K/9!Servicios relacionados con bases de datos",
									"K/10!Mantenimiento y reparación de maquinaria de oficina, contabilidad e informática",
									"K/11!Actividades de informática n.c.p.",
									"K/12!Investigación  y desarrollo experimental en el campo de la ingeniería y de las ciencias exactas, naturales, y humanas",// Segun la AFIP el K/13 es similar pero tiene q ver con las ciencias "humanas" 
									"K/13!Servicios jurídicos y de contabilidad, teneduría de libros y auditoría; asesoramiento en materia de ",
									"K/14!Servicios de arquitectura e ingeniería y servicios técnicos n.c.p.",
									"K/15!Servicios de publicidad",
									"K/16!Servicios empresariales n.c.p.",
									"L/1!Servicios de la administración pública",
									"L/2!Prestación pública de servicios a la comunidad en general",
									"L/3!Servicios de la seguridad social obligatoria",
									"M/1!Enseñanza inicial y primaria",
									"M/2!Enseñanza secundaria",
									"M/3!Enseñanza superior y formación de postgrado",
									"M/4!Enseñanza para adultos y  servicios de enseñanza n.c.p.",
									"N/1!Servicios relacionados con la salud humana",
									"N/2!Servicios veterinarios",
									"N/3!Servicios sociales",
									"O/1!Eliminación de desperdicios y aguas residuales, saneamiento y servicios similares",
									"O/2!Servicios de organizaciones empresariales, profesionales y de empleadores",
									"O/3!Servicios de sindicatos",
									"O/4!Servicios de asociaciones n.c.p.",
									"O/5!Servicios de cinematografía, radio y televisión y servicios de espectáculos artísticos y de diversió",
									"O/6!Servicios de agencias de noticias",
									"O/7!Servicios de bibliotecas, archivos y museos y  servicios culturales n.c.p.",
									"O/8!Servicios para la práctica deportiva y de entretenimiento n.c.p.",
									"O/9!Servicios n.c.p.",
									"P/1!Servicios de hogares privados que contratan servicio domestico",
									"Q/1!Servicios de organizaciones y órganos extraterritoriales"
		};
		
		for(String cadaCodigo : listaValores){
			String[] listaSecciones = cadaCodigo.split("!");
			String locCodigo = listaSecciones[0];
			String locNombre = listaSecciones[1];
//			System.out.println("Ultimo codigo****: " + locCodigo);
			plistaSecciones.get(locCodigo).setNombre(locNombre);
		}
	}
	
	@Test
	public void testFindListaCiiu() throws Exception {
		try {
			System.out.println(this.systemMunicipalidad.findListaSeccionCiiu(null, null));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFindListaGrupoCiiu() throws Exception{
		try{
			System.out.println(this.systemMunicipalidad.findListaGrupoCiiu(null, null, null));
		}catch (Exception e) {
			e.printStackTrace();
		}		
	}

	
	@Test
	public void testGetCodigoCiiuById() throws Exception {
		try {
			System.out.println(this.systemMunicipalidad.getCodigoCiiuById(1622l));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
