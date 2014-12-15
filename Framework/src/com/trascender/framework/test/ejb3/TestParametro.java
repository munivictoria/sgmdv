package com.trascender.framework.test.ejb3;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.naming.InitialContext;

import org.junit.BeforeClass;
import org.junit.Test;

import com.trascender.framework.recurso.filtros.FiltroConfiguracionRecurso;
import com.trascender.framework.recurso.persistent.ConfiguracionRecurso;
import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.framework.recurso.persistent.validacionDinamica.ComponenteValidacion;
import com.trascender.framework.recurso.persistent.validacionDinamica.ComponenteValidacion.AlcanceValidacion;
import com.trascender.framework.recurso.persistent.validacionDinamica.ComponenteValidacion.Operadores;
import com.trascender.framework.recurso.persistent.validacionDinamica.ComponenteValidacion.TipoValidacion;
import com.trascender.framework.recurso.persistent.validacionDinamica.MensajeErrorVD;
import com.trascender.framework.recurso.persistent.validacionDinamica.PlantillasValidaciones;
import com.trascender.framework.recurso.persistent.validacionDinamica.ValidacionDinamica;
import com.trascender.framework.recurso.persistent.validacionDinamica.ValorString;
import com.trascender.framework.recurso.persistent.validacionDinamica.ValorValidacion;
import com.trascender.framework.recurso.transients.Recurso;
import com.trascender.framework.system.interfaces.SystemParametro;
import com.trascender.framework.system.interfaces.SystemUsuario;

public class TestParametro {
	private static SystemParametro systemParametro;
	private static SystemUsuario systemUsuario;
	
	@BeforeClass
	public static void inicializar() {
		try {
			InitialContext initial = new InitialContext();
			
			systemUsuario = (SystemUsuario) initial.lookup(SystemUsuario.JNDI_NAME);
			
			long llave = systemUsuario.login("root", "Emilia15");
			
			systemParametro = (SystemParametro) initial.lookup(SystemParametro.JNDI_NAME);
			systemParametro.setLlave(llave);
		} catch (Exception locE) {
			locE.printStackTrace();
		}

	}

	@Test
	public void TestAddParametro() {
//		ParametroSistema locParametro = new ParametroString();
//		locParametro.setCodigo("2");
//		locParametro.setNombre("Razon social");
////		locParametro.setCadena("Trascender S.R.L");
//		try {
//			systemParametro.addParametro(locParametro);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

	@Test
	public void TestDeleteParametro() {
//		try {
//			Parametro locParamELiminar = systemParametro.getParametroPorCodigo("20");
//			assertNotNull("parametroEliminar nulo", locParamELiminar);
//			systemParametro.deleteParametro(locParamELiminar);
//		}catch(Exception e){
//			e.printStackTrace();
//		}
	}

	@Test
	public void getParametroPorcodigo() {
//		try {
//		Parametro locParametro = systemParametro.getParametroPorCodigo("1");
//		assertNotNull("no se encontro el parametro",locParametro);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

	}
	@Test
	public void getParametroPorId(){
//		try {
//			Parametro locParametro = systemParametro.getParametroPorId(35L);
//			if(locParametro instanceof ParametroImagen){
//				((ParametroImagen) locParametro).rebuildFile("/home/nela/Escritorio/vipiansImagen");
//			}
//			assertNotNull("no se encontro el parametro", locParametro);
//		} catch (Exception locE) {
//			locE.printStackTrace();
//		}
	}
	@Test
	public void addParametroImagen(){
//		try {
//			ParametroImagen paramImagen = new ParametroImagen();
//			paramImagen.setNombre("imagenPie");
//			paramImagen.setCodigo("35");
//			File archivo = new File("/home/nela/Escritorio/db.jpg");
//			paramImagen.setArchivo(archivo);
//			paramImagen.loadFile(false,100000);
//			systemParametro.addParametro(paramImagen);
//			
//		} catch (Exception locE) {
//			locE.printStackTrace();
//		}
	}

	@Test
	public void getValue() throws Exception{
//		Parametro parametro;
//		try {
//			parametro = systemParametro.getParametroPorCodigo("20");
//			if(parametro instanceof ParametroImagen){
//				Image imagen = (Image) parametro.getValor();
//				assertNotNull("No se pudo recuperar la imagen",imagen);
//				JOptionPane.showMessageDialog(null,"imagen recuperada","imagen",JOptionPane.OK_OPTION,new ImageIcon(imagen));
//			}
//			else{
//				System.out.println("VALOR DEL PARAMETRO = "+parametro.getValor());
//			}
//		} catch (TrascenderException e) {
//			e.printStackTrace();
//		}
	}
	
	@Test
	public void testAddConfiguracion() throws Exception {
		try {
			ConfiguracionRecurso locConfig = new ConfiguracionRecurso();
			
				locConfig.setIdRecurso(-1724036473956407989L);
			
				locConfig.getListaAtributosConfigurables().add("nroParcela");
//				locConfig.getListaAtributosConfigurables().add("apellido");
			
				locConfig.setNombreAlias("Parcela");
				locConfig.setOrden(1);
		
//			ConfiguracionAtributoTabla locAtrConf1 = new ConfiguracionAtributoTabla();
//				locAtrConf1.setNombreAtributo("nroParcela");
//				locAtrConf1.setNombreAtributoTabla("El Numero");
//				locAtrConf1.setOrden(1);
			
			this.systemParametro.addConfiguracionRecurso(locConfig);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdateConfiguracion() throws Exception {
		try {
			ConfiguracionRecurso locConfig = this.systemParametro.getConfiguracionPorRecurso(-1724036473956407989l);
				assertNotNull(locConfig);
			
//				locConfig.getListaAtributosConfigurables().add("nombre");
//				locConfig.getListaAtributosConfigurables().add("apellido");
//			
//				locConfig.setNombreAlias("Fisicas Personas");
//				locConfig.setOrden(1);
		
//			ConfiguracionAtributoTabla locAtrConf1 = new ConfiguracionAtributoTabla();
//				locAtrConf1.setNombreAtributo("nombre");
//				locAtrConf1.setNombreAtributoTabla("El nombre");
//				locAtrConf1.setOrden(1);
			
//				ValidacionDinamica locValidacion = new ValidacionDinamica();
//					locValidacion.setIdRecurso(locConfig.getIdRecurso());
//					
//					ComponenteValidacion locComponente = new ComponenteValidacion();
//						locComponente.setAlcance(AlcanceValidacion.VALIDACION_DB);
//						locComponente.setAtributo("sexo");
//						locComponente.setDescripcion("Se valida que el sexo sea masculino");
//							MensajeErrorVD locMensaje = new MensajeErrorVD();
//							locMensaje.setMensaje("La persona solo puede ser masculino.");
//							locMensaje.setNumeroMsg(1l);
//						locComponente.setMensajeError(locMensaje);
//						locComponente.setOperador(Operadores.DISTINTO);
//						locComponente.setTipoValidacion(TipoValidacion.AND);
////						DiaFeriado as= new DiaFeriado();
////						as.setFecha(Calendar.getInstance().getTime());
////						as.setNombre("nommmbre");
//						ValorValidacion locValor = new ValorString();
//							locValor.setValor("FEMENINO");
//						locComponente.setValor(locValor);
				MensajeErrorVD locMensaje = new MensajeErrorVD();
				locMensaje.setMensaje("El Numero Parcela no puede ser nulo.");
//				locMensaje.setNumeroMsg(6546);
					ComponenteValidacion locComponente = PlantillasValidaciones.VALIDACION_NO_NULO("nroParcela");
					locComponente.setMensajeError(locMensaje);
					
					locConfig.addValidacion(locComponente);
					
					
					System.out.println(locConfig.getValidacionDinamica().toString());
				this.systemParametro.updateConfiguracionRecurso(locConfig);
//				System.out.println(locConfig.getValidacionDinamica().toString());
//				
//				System.out.println(locConfig);
//				System.out.println(locConfig.getNombreAlias() + " -ALIAS");
//				System.out.println("CONFIGURACION ATRIBUTO TABLA");
//				for(ConfiguracionAtributoTabla cadaConfig :locConfig.getListaAtributosTabla()){
//					cadaConfig.toString();
//				}
//				
//				System.out.println("ATRIBUTOS CONFIGURABLES: ");
//				for (String cadaValor : locConfig.getListaAtributosConfigurables()) {
//					System.out.println(cadaValor);
//				}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testValidacionNotNUll() throws Exception {
		try {
			
			ConfiguracionRecurso locConfig = this.systemParametro.getConfiguracionPorRecurso(PersonaFisica.serialVersionUID);
				assertNotNull(locConfig);
			ValidacionDinamica locValidacion = locConfig.getValidacionDinamica();
				assertNotNull(locValidacion);
			
			ComponenteValidacion locComponente = new ComponenteValidacion();
				locComponente.setAlcance(AlcanceValidacion.INTEGRIDAD);
				locComponente.setAtributo("sexo");
				locComponente.setDescripcion("Valida que el sexo no sea nulo.");
				locComponente.setOperador(Operadores.IGUAL);
				locComponente.setTipoValidacion(TipoValidacion.NO_NULO);
				
				MensajeErrorVD locMensajeErr = new MensajeErrorVD();
					locMensajeErr.setMensaje("El sexo no puede ser nulo. verificar...");
					locMensajeErr.setNumeroMsg(3l);
				locComponente.setMensajeError(locMensajeErr);
				locValidacion.addComponente(locComponente);
				
				this.systemParametro.updateConfiguracionRecurso(locConfig);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFindValidaciones() throws Exception {
		try {
			ConfiguracionRecurso locConfig = this.systemParametro.getConfiguracionPorRecurso(-1724036473956407989l);
			assertNotNull(locConfig);
		ValidacionDinamica locValidacion = locConfig.getValidacionDinamica();
			assertNotNull(locValidacion);
			
			for(ComponenteValidacion cadaComponente : locConfig.getValidacionDinamica().getListaComponentes()){
				System.out.println(cadaComponente.getValidacionSimple());
				System.out.println(cadaComponente.getValidacion());
				System.out.println(cadaComponente.getValidacionDinamica().getIdValidacionDinamica());
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testValidacionNotMayorrQue() throws Exception {
		try {
			
			ConfiguracionRecurso locConfig = this.systemParametro.getConfiguracionPorRecurso(-1724036473956407989l);
				assertNotNull(locConfig);
			ValidacionDinamica locValidacion = locConfig.getValidacionDinamica();
				assertNotNull(locValidacion);
			
				ValorValidacion locValor = new ValorString();
				locValor.setValor("65");
				
			ComponenteValidacion locComponente = PlantillasValidaciones.VALIDACION_BASICA(AlcanceValidacion.INTEGRIDAD, 
					"nomenclaturaCatastral.nroParcela", 
					locValor, 
					Operadores.MAYOR, 
					TipoValidacion.AND);
				locComponente.setDescripcion("Valida que el numero parcela sea mayor que 5");
				
				MensajeErrorVD locMensajeErr = new MensajeErrorVD();
					locMensajeErr.setMensaje("El Numero de la parcela no puede ser menor que 1. verificar...");
				locComponente.setMensajeError(locMensajeErr);
				locValidacion.addComponente(locComponente);
				
				this.systemParametro.addValidacionARecurso(locComponente, -1724036473956407989l);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testFindListaRecursos() throws Exception {
		try {
			FiltroConfiguracionRecurso locFiltros = new FiltroConfiguracionRecurso();
			
//			Recurso locRecurso = new Recurso();
//			locRecurso.setIdRecurso(-1724036473956407989l);
			
//			locFiltros.setRecurso(locRecurso);
			List<ConfiguracionRecurso> locListaResultados = systemParametro.findListaConfiguracionRecurso(locFiltros).getListaResultados();

			for(ConfiguracionRecurso cadaConfig : locListaResultados){
				System.out.println(cadaConfig);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
