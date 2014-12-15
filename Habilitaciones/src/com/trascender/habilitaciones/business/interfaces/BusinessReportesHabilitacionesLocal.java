package com.trascender.habilitaciones.business.interfaces;

import javax.ejb.Local;

import net.sf.jasperreports.engine.JasperPrint;

@Local
public interface BusinessReportesHabilitacionesLocal {

	public final static String JNDI_NAME = "ejb/BusinessReportesHabilitaciones";
	
	public JasperPrint getReporteInformacionParcelaria(long idParcela) throws Exception;
}
