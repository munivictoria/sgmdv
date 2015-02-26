/*
 * Generated by XDoclet - Do not edit!
 */
package com.trascender.saic.business.interfaces;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;
import javax.persistence.EntityManager;

import net.sf.jasperreports.engine.JasperPrint;

import com.trascender.catastro.recurso.persistent.Calle;
import com.trascender.catastro.recurso.persistent.Cuadra;
import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
import com.trascender.framework.recurso.transients.Calendario;
import com.trascender.framework.util.Periodicidad;
import com.trascender.habilitaciones.recurso.filtros.FiltroObligacionSHPS;
import com.trascender.habilitaciones.recurso.persistent.CuotaLiquidacion;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.PeriodoLiquidacion;
import com.trascender.habilitaciones.recurso.persistent.TipoObligacion;
import com.trascender.habilitaciones.recurso.persistent.osp.ServicioOSP;
import com.trascender.habilitaciones.recurso.persistent.pfo.Obra;
import com.trascender.saic.recurso.filtros.FiltroLiquidacionSHPS;
import com.trascender.saic.recurso.persistent.LiquidacionTasa;
import com.trascender.saic.recurso.persistent.RegistroDeuda;
import com.trascender.saic.recurso.persistent.RegistroDeuda.EstadoRegistroDeuda;
import com.trascender.saic.recurso.persistent.RegistroDeuda.TipoDeuda;
import com.trascender.saic.recurso.persistent.refinanciacion.DocumentoRefinanciacion;
import com.trascender.saic.recurso.references.LiquidacionTasaRefer;

/**
 * Local interface for BusinessImpresion.
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version ${version}
 */

@Local
public interface BusinessImpresionLocal
{
	public static final String JNDI_NAME="BusinessImpresionLocal/local";

	public void setEntityManager(EntityManager pEntityManager);

	public JasperPrint getReporteOSP(Persona pPersona,
			Cuadra pCuadra,
			ServicioOSP pServicioOSP,
			Boolean pServicioMedido,
			Calle pCalle,
			CuotaLiquidacion pCuota,
			EstadoRegistroDeuda pEstadoRegistroDeuda,
			String pCuentaSubcuenta )
					throws Exception;

	public JasperPrint getReportePFO(
			Persona pPersona,
			Obra pObra,
			Cuadra pCuadra, 
			Calle pCalle, 
			CuotaLiquidacion pCuota,
			EstadoRegistroDeuda pEstadoRegistroDeuda,
			TipoDeuda pTipoDeuda,
			Integer pCuotaDesde,
			Integer pCuotaHasta
			) throws Exception;

	public JasperPrint getReporteSHPS(FiltroLiquidacionSHPS filtro, Usuario pUsuario) throws Exception;

	public JasperPrint getReporteSHPS(List<LiquidacionTasa> pListaLiquidaciones, Usuario pUsuario) throws Exception;

	public JasperPrint getReporteDDJJSHPS(List<Obligacion> pListaObligaciones, CuotaLiquidacion pCuota) throws Exception;

	public JasperPrint getReporteTGI(
			CuotaLiquidacion pCuota, 
			Persona pPersona, 
			Parcela pParcela, 
			Periodicidad pPeriodicidad, 
			Integer pNumeroCuota, 
			EstadoRegistroDeuda 
			pEstadoRegistroDeuda, 
			boolean pIgualFormaPago,
			TipoDeuda pTipoDeuda) throws Exception;

	public JasperPrint getReporteListadoCuotasRefinanciacion(DocumentoRefinanciacion pDocumentoRefinanciacion) throws Exception;

	public JasperPrint getReporteReliquidacion(RegistroDeuda pRegistroDeuda,
			Usuario pUsuario,
			boolean pImprimirReporteSimple) 
					throws Exception;

	public JasperPrint getReporteReconocimientoDeuda(DocumentoRefinanciacion pDocumentoRefinanciacion)throws Exception;

	public JasperPrint getReporteTasaUnificada(List<LiquidacionTasaRefer> pListaLiquidacionesTasa, List<TipoObligacion> pLista, Usuario pUsuario) throws Exception;

	public void imprimirLiquidacionesEnServidor(
			List<LiquidacionTasaRefer> pListaLiquidacionesTasa, Usuario pUsuario);
	
	public JasperPrint getReporteLiquidacionAutomotor(List<LiquidacionTasa> pListaLiquidaciones) throws Exception;
	
	public JasperPrint getReporteLiquidacionOSP(List<LiquidacionTasa> pListaLiquidaciones) throws Exception;

	public void imprimirLiquidacionesTasasEnServidor(
			List<LiquidacionTasa> pListaLiquidacionesTasa, Usuario pUsuario);
	
	public Map<String, JasperPrint> getListaObligacionSHPSPorContador(FiltroObligacionSHPS pFiltro,CuotaLiquidacion pCuota,String pTipoZip) throws Exception;
}