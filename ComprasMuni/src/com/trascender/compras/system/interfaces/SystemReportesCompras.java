package com.trascender.compras.system.interfaces;

import java.util.Map;

import javax.ejb.Remote;

import net.sf.jasperreports.engine.JasperPrint;

import com.trascender.compras.recurso.persistent.suministros.Contratacion;
import com.trascender.framework.exception.TrascenderException;

@Remote
public interface SystemReportesCompras{
	
	public static final String JNDI_NAME = "ejb/SystemReportesCompras/remote";
	
	public void setLlave(long pLlave) throws Exception;
	
	public JasperPrint getReporteOrdenCompra(long pId) throws Exception;
	public JasperPrint getReporteProveedor(long pIdProveedor) throws TrascenderException;
	public JasperPrint getReporteSolicitudSuministro(long pIdSolicitudSuministro) throws TrascenderException;
	public Map<String, JasperPrint> getReportePresupuesto(Contratacion pContratacion) throws TrascenderException;
	public JasperPrint getReporteMovimientoMercaderia(long pIdMovimientoMercaderia) throws TrascenderException;
	public JasperPrint getReporteLiquidacionCompra (long pIdLiquidacionCompra) throws TrascenderException;
}
