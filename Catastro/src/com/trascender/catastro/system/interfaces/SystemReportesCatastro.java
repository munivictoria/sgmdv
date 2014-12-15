package com.trascender.catastro.system.interfaces;

import java.util.List;

import javax.ejb.Remote;

import net.sf.jasperreports.engine.JasperPrint;

import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.catastro.recurso.persistent.VolanteCatastral;
import com.trascender.framework.exception.TrascenderException;

@Remote
public interface SystemReportesCatastro{
	
	public static final String JNDI_NAME = "ejb/SystemReportesCatastro/remote";
	
	public void setLlave(long pIdLlave) throws Exception;
	
	public JasperPrint getReporteVolanteCatastral(VolanteCatastral pVolanteCatastral) throws Exception;
	public JasperPrint getReportePadronCatastral(List<Parcela> pListasParcela, boolean incluyeMejoras) throws TrascenderException;
	public JasperPrint getReporteInformacionParcelaria(long idParcela) throws Exception;
}
