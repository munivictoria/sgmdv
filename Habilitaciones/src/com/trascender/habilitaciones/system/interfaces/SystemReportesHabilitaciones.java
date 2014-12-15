package com.trascender.habilitaciones.system.interfaces;

import java.rmi.RemoteException;

import javax.ejb.Remote;

import net.sf.jasperreports.engine.JasperPrint;

import com.trascender.framework.exception.TrascenderException;

@Remote
public interface SystemReportesHabilitaciones {

	public final static String JNDI_NAME = "ejb/SystemReportesHabilitaciones/remote";

	public void setLlave(long pLlave) throws Exception;

	JasperPrint getReporteLibretaSanitaria(long pIdLibretaSanitaria)
			throws TrascenderException, RemoteException;
	
	public JasperPrint getReporteInformacionParcelaria(long idParcela) throws Exception;
	
	
}
