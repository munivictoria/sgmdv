package com.trascender.compras.business.interfaces;

import javax.ejb.Local;

import net.sf.jasperreports.engine.JasperPrint;

@Local
public interface BusinessReportesComprasLocal {

	public final static String JNDI_NAME = "ejb/BusinessReportesCompras";

	public JasperPrint getReporteLiquidacionCompra(	long pIdLiquidacionCompra) throws Exception;

}
