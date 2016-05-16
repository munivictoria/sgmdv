package com.trascender.contabilidad.util;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;

public class CompiladorJasper {

	public static void main(String[] args) {
		ClassLoader originalCL = Thread.currentThread().getContextClassLoader();
		String directorio = "/home/ferna/git/sgmdv/Reportes/fuentes/Victoria/";
		try {
	        URL[] classpath = new URL[]{
	                new File("/opt/jboss-6.0.0.Final/server/default/deploy/logicaVipians.jar").toURI().toURL()};
	        URLClassLoader loader = new URLClassLoader(classpath, originalCL);
	        Thread.currentThread().setContextClassLoader(loader);
	           
			JasperCompileManager.compileReportToFile(
					directorio + "Reporte_Cuotas_Refinanciacion.jrxml", 
					directorio + "Reporte_Cuotas_Refinanciacion.jasper");
			
			System.out.println("Compilacion exitosa.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Thread.currentThread().setContextClassLoader(originalCL);
		}
	}

}
