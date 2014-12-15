package com.trascender.catastro.test.testEjb3;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.naming.InitialContext;
import javax.persistence.Column;

import org.jboss.aspects.dbc.condition.parser.ForAllExpression;
import org.junit.BeforeClass;
import org.junit.Test;

import com.trascender.catastro.recurso.filtros.FiltroParcela;
import com.trascender.catastro.recurso.filtros.FiltroPlanoMensura;
import com.trascender.catastro.recurso.persistent.DeclaracionJurada;
import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.catastro.recurso.persistent.ParcelaPorCuadra;
import com.trascender.catastro.recurso.persistent.Plano.Estado;
import com.trascender.catastro.recurso.persistent.Plano.Tipo;
import com.trascender.catastro.recurso.persistent.PlanoConstruccion;
import com.trascender.catastro.recurso.persistent.PlanoMensura;
import com.trascender.catastro.recurso.persistent.TituloPropiedadParcelario;
import com.trascender.catastro.system.interfaces.SystemAdministracionDDJJ;
import com.trascender.catastro.system.interfaces.SystemInformacionParcelaria;
import com.trascender.catastro.system.interfaces.SystemRegistroPropiedad;
//import com.trascender.compras.recurso.persistent.suministros.Unidad;
import com.trascender.framework.system.interfaces.SystemUsuario;
/**
 * @author jsantacruz
 */
public class RegistroParcelarioTest extends com.trascender.framework.util.JAserciones {
	private static SystemUsuario systemUsuario;
	
	private static SystemInformacionParcelaria systemInformacionParcelaria;
	private static SystemAdministracionDDJJ systemAdministracionDDJJ;
	private static SystemRegistroPropiedad systemRegistroPropiedad;
	
	private static long llave = 0;
	
	@BeforeClass
	public static void inicializar(){
		try{
			InitialContext initialContext = new InitialContext();
			
			systemUsuario = (SystemUsuario) initialContext.lookup(SystemUsuario.JNDI_NAME);
			llave = systemUsuario.login("root", "Emilia15");
			
			systemInformacionParcelaria = (SystemInformacionParcelaria) initialContext.lookup(SystemInformacionParcelaria.JNDI_NAME);
			systemInformacionParcelaria.setLlave(llave);
			
			systemAdministracionDDJJ = (SystemAdministracionDDJJ) initialContext.lookup(SystemAdministracionDDJJ.JNDI_NAME);
			systemAdministracionDDJJ.setLlave(llave);
			
			systemRegistroPropiedad = (SystemRegistroPropiedad) initialContext.lookup(SystemRegistroPropiedad.JNDI_NAME);
			systemRegistroPropiedad.setLlave(llave);
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	@Test
	public void addParcela() throws Exception {
		Parcela locParcela = new Parcela();
		locParcela.setAvaluoPorMejoras(100D);
		locParcela.setAvaluoTerreno(100D);
//		locParcela.setBaldio(false);
		
		//TODO TERMINAR ...
		
	}

	@Test
	public void testGetListaCuadrasDelimitantes() throws Exception{
		Parcela locParcela = this.systemInformacionParcelaria.getParcelaPorId(1L);
		assertNotNull("no hay parcela",locParcela);
		
		List locListaResultado = this.systemInformacionParcelaria.getListaCuadrasPorParcela(locParcela);
		assertNotNull("la lista quedo null",locListaResultado);
		assertIsEmpty(locListaResultado);
		
		for(Object cadaObject : locListaResultado){
			ParcelaPorCuadra cadaCuadra = (ParcelaPorCuadra) cadaObject;
			System.out.println(cadaCuadra);
		}
	}
	@Test
	public void testFindTiposConstruccion() throws Exception{
	}
	
	@Test
	public void testUpdateDeclaracionJurada() throws Exception{
		DeclaracionJurada locDDJJ = null; //(DeclaracionJurada) this.systemInformacionParcelaria.findDeclaracionJurada("996574", null, null, null).get(0);
		assertNotNull("No hayDDJJ", locDDJJ);
		
		try{
		System.out.println(this.systemAdministracionDDJJ.updateDeclaracionJurada(locDDJJ));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddDeclaracionJurada() throws Exception{
		DeclaracionJurada locDeclaracion  = new DeclaracionJurada();
		locDeclaracion.setFechaInscripcion(Calendar.getInstance().getTime());
		locDeclaracion.setNumero("996574");
		Parcela locParecela = this.systemInformacionParcelaria.getParcelaPorId(10117);
//		locDeclaracion.setParcela(locParecela);
		
		try{
			this.systemAdministracionDDJJ.addDeclaracionJurada(locDeclaracion);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdatePlanoConstruccion() throws Exception{
	
		PlanoConstruccion locPlanoConstruccion = null; //(PlanoConstruccion) this.systemInformacionParcelaria.findListaPlanosConstruccion("099/71", null).get(0);
		assertNotNull(locPlanoConstruccion);
		
		System.out.println(locPlanoConstruccion);
		
		try{
			System.out.println(this.systemInformacionParcelaria.updatePlanoConstruccion(locPlanoConstruccion));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddPlanoConstruccion() throws Exception{
		PlanoConstruccion locPlanoConstruccion = new PlanoConstruccion();
		locPlanoConstruccion.setFechaInscripcion(Calendar.getInstance().getTime());
//		locPlanoConstruccion.setNumero("86574");
		
		Parcela locParcela = this.systemInformacionParcelaria.getParcelaPorId(1L);
		assertNotNull("No tenemos Parcela", locParcela);
		
		locPlanoConstruccion.setParcela(locParcela);
		
		try{
			this.systemInformacionParcelaria.addPlanoConstruccion(locPlanoConstruccion);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFindListaParcela(){
		try{
			List<Parcela> listaParcelas = null; //systemInformacionParcelaria.findListaParcelas(null,null, null, null, null, null, null,null);
//			mostrarLista(listaParcelas);
			for (Parcela cadaParcela : listaParcelas){
				System.out.println(cadaParcela);
//				Parcela cadaParcela$ = this.systemInformacionParcelaria.getParcelaPorId(cadaParcela.getIdParcela());
//				this.systemInformacionParcelaria.updateParcela(cadaParcela$);
			}
			listaParcelas = null; //systemInformacionParcelaria.findListaParcelas(null, null, null, null, null, null, null,null);
			for (Parcela cadaParcela : listaParcelas){
				System.out.println(cadaParcela);
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDeletePlanoConstruccion() throws Exception{
		Parcela locParcela = systemInformacionParcelaria.getParcelaPorId(1L);
		PlanoConstruccion locPlanoConstruccion = null; //(PlanoConstruccion) this.systemInformacionParcelaria.findListaPlanosConstruccion(null, locParcela).get(0);
		assertNotNull("No tenemos plano construccion",locPlanoConstruccion);
		try{
			this.systemInformacionParcelaria.deletePlanoConstruccion(locPlanoConstruccion);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testUpdateParcela() throws Exception{
		Parcela locParcela = systemInformacionParcelaria.getParcelaPorId(4L);
		TituloPropiedadParcelario locTitulo = locParcela.getTituloPropiedad();
		locTitulo.setAsiento(1);
		locTitulo.setNroFolio(2);
		PlanoMensura locPlano = locParcela.getPlanoMensura();
		locPlano.setNroExpediente("expediente");
		System.out.println(systemInformacionParcelaria.updateParcela(locParcela));
	}
	
	@Test
	public void testMostrarParcela() throws Exception{
		Parcela locParcela = systemInformacionParcelaria.getParcelaPorId(4L);
		System.out.println(locParcela.toString());
		System.out.println("----------");
		TituloPropiedadParcelario locTitulo = locParcela.getTituloPropiedad();
		System.out.println(locTitulo.toString());
		System.out.println(locTitulo.getAsiento()+" "+locTitulo.getNroFolio());
	}
	
	@Test
	public void testAddPlanoContruccion() throws Exception{
		Parcela locParcela = systemInformacionParcelaria.getParcelaPorId(4L);
		PlanoConstruccion locPlanoConstruccion = new PlanoConstruccion();
		locPlanoConstruccion.setPlano("plano1");
		locPlanoConstruccion.setFolio("folio1");
		locPlanoConstruccion.setTomo("tomo1");
		locPlanoConstruccion.setTipo(Tipo.ORIGINAL);
		locPlanoConstruccion.setEstado(Estado.EN_TRAMITE);
		locPlanoConstruccion.setNroExpediente("nroExpediente1");
		locPlanoConstruccion.setNroRegistro("nroRegistro1");
		locPlanoConstruccion.setFechaInscripcion(new Date());
		locPlanoConstruccion.setParcela(locParcela);
		System.out.println(systemInformacionParcelaria.addPlanoConstruccion(locPlanoConstruccion));
//		System.out.println(systemInformacionParcelaria.addParcela(locParcela));
	}
	
	@Test
	public void testUpdateParcela2() throws Exception{
		Parcela locParcela = systemInformacionParcelaria.getParcelaPorId(4L);
		PlanoConstruccion locPlanoConstruccion = locParcela.getPlanoConstruccion();
		locPlanoConstruccion.setPlano("plano1 (modificado)");
		locPlanoConstruccion.setFolio("folio1 (modificado)");
		System.out.println(systemInformacionParcelaria.updateParcela(locParcela));
	}
	
	@Test
	public void testAddPlanoConstruccion2() throws Exception{
		Parcela locParcela = systemInformacionParcelaria.getParcelaPorId(4L);
		PlanoConstruccion locPlanoConstruccion = new PlanoConstruccion();
		locPlanoConstruccion.setPlano("plano1");
		locPlanoConstruccion.setFolio("folio1");
		locPlanoConstruccion.setTomo("tomo1");
		locPlanoConstruccion.setTipo(Tipo.ORIGINAL);
		locPlanoConstruccion.setEstado(Estado.EN_TRAMITE);
		locPlanoConstruccion.setNroExpediente("nroExpediente1");
		locPlanoConstruccion.setNroRegistro("nroRegistro1");
		locPlanoConstruccion.setFechaInscripcion(new Date());
		locPlanoConstruccion.setParcela(locParcela);
		locParcela.setPlanoConstruccion(locPlanoConstruccion);
		System.out.println(systemInformacionParcelaria.updateParcela(locParcela));
	}
	
	@Test
	public void testFindPlandoMesura() throws Exception{
		FiltroPlanoMensura locFiltroPlanoMensura = new FiltroPlanoMensura();
		locFiltroPlanoMensura.setPlano("16281");
		List<PlanoMensura> locListaPlanoMesura = systemInformacionParcelaria.findListaPlanosMensura(locFiltroPlanoMensura).getListaResultados();
		for (PlanoMensura auxPlanoMensura : locListaPlanoMesura) {
			System.out.println(auxPlanoMensura.toString());
			System.out.println("----------");
			System.out.println(auxPlanoMensura.getPlano());
			System.out.println(auxPlanoMensura.getIdPlanoMensura());
		}
		System.out.println(locListaPlanoMesura);
	}
	
	@Test
	public void testFindParcela() throws Exception{
		FiltroParcela locFiltroParcela = new FiltroParcela();
		locFiltroParcela.setPlanoConstruccion("plano1");
		locFiltroParcela.setFolioConstruccion("folio1");
		List<Parcela> locListaParcela = systemInformacionParcelaria.findListaParcelas(locFiltroParcela).getListaResultados();
		for (Parcela auxParcela : locListaParcela){
			System.out.println(auxParcela.toString());
			System.out.println("----------");
			System.out.println(auxParcela.getPlanoConstruccion());
			System.out.println(auxParcela.getPlanoConstruccion());
			System.out.println(auxParcela.getIdParcela());
		}
	}
	
	@Test
	public void testFindParcela2() throws Exception{
		FiltroParcela locFiltroParcela = new FiltroParcela();
		locFiltroParcela.setPlanoMensura("18221");
		List<Parcela> locListaParcela = systemInformacionParcelaria.findListaParcelas(locFiltroParcela).getListaResultados();
		for (Parcela auxParcela : locListaParcela){
			System.out.println(auxParcela.toString());
			System.out.println("----------");
			System.out.println(auxParcela.getPlanoConstruccion());
			System.out.println(auxParcela.getPlanoConstruccion());
			System.out.println(auxParcela.getIdParcela());
		}
	}
	
	@Test
	public void testFindParcela3() throws Exception{
		FiltroParcela locFiltroParcela = new FiltroParcela();
		locFiltroParcela.setNroAsientoTituloPropiedad(1);
		locFiltroParcela.setNroFolioTituloPropiedad(2);
		List<Parcela> locListaParcela = systemInformacionParcelaria.findListaParcelas(locFiltroParcela).getListaResultados();
		for (Parcela auxParcela : locListaParcela){
			System.out.println(auxParcela.toString());
			System.out.println("----------");
			System.out.println(auxParcela.getPlanoConstruccion());
			System.out.println(auxParcela.getPlanoConstruccion());
			System.out.println(auxParcela.getIdParcela());
		}
	}
	
	
}
