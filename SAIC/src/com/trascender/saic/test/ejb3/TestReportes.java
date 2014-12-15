package com.trascender.saic.test.ejb3;

import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import org.junit.Before;
import org.junit.Test;

import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.framework.recurso.transients.Calendario;
import com.trascender.framework.recurso.transients.Periodo;
import com.trascender.framework.system.interfaces.SystemPersonaFisica;
import com.trascender.framework.system.interfaces.SystemUsuario;
import com.trascender.habilitaciones.recurso.persistent.TipoObligacion;
import com.trascender.habilitaciones.system.interfaces.SystemTipoTasa;
import com.trascender.saic.recurso.persistent.LiquidacionTasa;
import com.trascender.saic.system.interfaces.SystemEstadoCuentaContribuyente;
import com.trascender.saic.system.interfaces.SystemImpresion;
import com.trascender.saic.system.interfaces.SystemLiquidacionTasa;
import com.trascender.saic.system.interfaces.SystemRegistroValuado;

public class TestReportes {

	private SystemImpresion systemImpresion;
	private SystemLiquidacionTasa systemLiquidacion;
	private SystemUsuario systemUsuario;
	private long llave;
	private static SystemEstadoCuentaContribuyente systemContribuyente;
	private static SystemRegistroValuado systemRegistroValuado;
	private static SystemTipoTasa systemTipoTasa;
	private static SystemPersonaFisica systemPersonaFisica;
	
	@Before
	public void setUp() throws Exception {
		try{
			Context ctx = new InitialContext();
			systemUsuario = (SystemUsuario) ctx.lookup(SystemUsuario.JNDI_NAME);
			llave = systemUsuario.login("root", "Emilia15");
			System.out.println("Lleva llave?");
			System.out.println(llave);
			systemImpresion = (SystemImpresion) ctx.lookup(SystemImpresion.JNDI_NAME);
			systemImpresion.setLlave(llave);
			systemLiquidacion = (SystemLiquidacionTasa) ctx.lookup(SystemLiquidacionTasa.JNDI_NAME);
			systemLiquidacion.setLlave(llave);
			systemContribuyente= (SystemEstadoCuentaContribuyente) ctx.lookup(SystemEstadoCuentaContribuyente.JNDI_NAME);
			systemContribuyente.setLlave(llave);
			systemRegistroValuado = (SystemRegistroValuado) ctx.lookup(SystemRegistroValuado.JNDI_NAME);
			systemRegistroValuado.setLlave(llave);
			systemTipoTasa = (SystemTipoTasa) ctx.lookup(SystemTipoTasa.JNDI_NAME);
			systemTipoTasa.setLlave(llave);
			systemPersonaFisica = (SystemPersonaFisica) ctx.lookup(SystemPersonaFisica.JNDI_NAME);
			systemPersonaFisica.setLlave(llave);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testReporteTasaUnificada(){
		try{
			/**
			 * JUANMA: Te agregue que busque por persona, porque las otras obligaciones no estan andando bien. Probalo asi.
			 */
			//FIXME cambio periodo
			Periodo locPeriodo = null; //systemRegistroValuado.getPeriodo(Periodicidad.MENSUAL, 6, 2012);
//			Persona locPersonaFisica = (Persona) systemPersonaFisica.findPersonaFisica(null, "fernando", "gareis", null, null, null, null, null, null, null, null, null, null, null).get(0);
			List<TipoObligacion> locListaTipoObligaciones = new ArrayList<TipoObligacion>();
			locListaTipoObligaciones.add(systemTipoTasa.findListaTipoObligacion("TGI", null).get(0));
			locListaTipoObligaciones.add(systemTipoTasa.findListaTipoObligacion("OYSP", null).get(0));
//			List<LiquidacionTasa> locListaLiquidacionesTasa = systemContribuyente.findListaTasasUnificadas(locPersonaFisica, null, locPeriodo, locListaTipoObligaciones);
			
//			System.out.println("-------------------------------------------------------------");
//			System.out.println("Cantidad de Liquidaciones");
//			System.out.println(locListaLiquidacionesTasa.size());
//			for (LiquidacionTasa cadaLiquidacion : locListaLiquidacionesTasa){
//				System.out.println("Persona");
//				System.out.println(cadaLiquidacion.getPersona().getDenominacion());
//				System.out.println("Nro Parcela");
//				System.out.println(cadaLiquidacion.getDocGeneradorDeuda().getObligacion().getDocumentoEspecializado().getParcela());
//				System.out.println("Monto de la Liquidacion");
//				System.out.println(cadaLiquidacion.getMonto());
//			}
//			System.out.println("-------------------------------------------------------------");
			
//			JasperPrint jasperPrint = systemImpresion.getReporteTasaUnificada(locListaLiquidacionesTasa);
//			JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
//			jasperViewer.setVisible(true);
//			Thread.sleep(20000);
//			
//			System.out.println(locListaLiquidacionesTasa);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAplicarIntereses(){
		try {
			Parcela locParcela = new Parcela();
			locParcela.setIdParcela(5308);//Parcela 5353
			systemLiquidacion.setLlave(llave);
			PersonaFisica locPersona = new PersonaFisica();
			locPersona.setIdPersona(15201); // Francisco Garcilazo
			Calendario locCalendario = new Calendario();
			locCalendario.setIdCalendario(12l);
			List<LiquidacionTasa> locListaLiquidaciones = systemLiquidacion.findListaLiquidacionesOSP(
					locPersona, locParcela, null, null, null, 
					null, null, null, locCalendario, 
					2013, null, null, null);
			JasperPrint jp = systemImpresion.getReporteLiquidacionDeuda(locListaLiquidaciones);
			JasperViewer.viewReport(jp);
			Thread.sleep(10000);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
}
