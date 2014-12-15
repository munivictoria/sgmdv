package com.trascender.habilitaciones.test.ejb3;

import javax.naming.Context;
import javax.naming.InitialContext;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import org.junit.BeforeClass;
import org.junit.Test;

import com.trascender.framework.system.interfaces.SystemUsuario;
import com.trascender.habilitaciones.system.interfaces.SystemReportesHabilitaciones;

public class testReportes {
	
	private static long llave;
	private static SystemUsuario systemUsuario;
	private static SystemReportesHabilitaciones systemReportesHabilitaciones;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception{
		try{
		Context locContext = new InitialContext();
		
		systemUsuario = (SystemUsuario) locContext.lookup(SystemUsuario.JNDI_NAME);
		llave = systemUsuario.login("root", "Emilia15");
			
		systemReportesHabilitaciones = (SystemReportesHabilitaciones) locContext.lookup(SystemReportesHabilitaciones.JNDI_NAME);
		systemReportesHabilitaciones.setLlave(llave);
		
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("NO se inicio un chot...");
		}
	}
	
	@Test
	public void testReporteParcela(){
		try {
			JasperPrint jp = systemReportesHabilitaciones.getReporteInformacionParcelaria(5308);
			JasperViewer.viewReport(jp);
			Thread.sleep(1000000);
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	

}
