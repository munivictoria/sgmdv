package com.trascender.saic.business.ejb;

import java.net.URL;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.engine.util.JRLoader;

import com.trascender.catastro.business.interfaces.BusinessZonificacionLocal;
import com.trascender.framework.util.TrascenderDataSource;
import com.trascender.saic.business.interfaces.BusinessThreaderLocal;
import com.trascender.saic.recurso.transients.LiquidacionTasaAgrupada;
import com.trascender.saic.reporte.dataSource.LiquidacionTasaAgrupadaDS;

@Stateless(name = "BusinessThreaderLocal")
public class BusinessThreaderBean implements BusinessThreaderLocal{
	
	@PersistenceContext
	private EntityManager entity;
	
	public void imprimirLiquidacionesEnServidor(
			List<LiquidacionTasaAgrupada> pListaLiquidaciones, byte[] pLogo, 
			String pTitulo, String pSubtitulo, BusinessZonificacionLocal pBusinessZonificacion){
		int tamañoADividir = 100;
		
		int hasta = pListaLiquidaciones.size();
		int desde = hasta - tamañoADividir;
		while (hasta > 0) {
			if (desde < 0) desde = 0;
			List<LiquidacionTasaAgrupada> locSubLista = pListaLiquidaciones.subList(desde, hasta);
//			LiquidacionTasaAgrupadaDS dataSource = 
//				new LiquidacionTasaAgrupadaDS(locSubLista, pLogo, pTitulo, pSubtitulo, pBusinessZonificacion);
			String nombre = "/opt/reportes/Liquidaciones_"+desde+"-"+hasta+".pdf";
			Thread locThread = new Thread(new ImpresorAPDF(null, nombre));
			locThread.start();
			hasta -= tamañoADividir;
			desde -= tamañoADividir;
		}
	}
	
	class ImpresorAPDF implements Runnable{
		LiquidacionTasaAgrupadaDS ds;
		String nombre;
		
		public ImpresorAPDF(LiquidacionTasaAgrupadaDS ds, String pNombreArchivo){
			this.ds = ds;
			this.nombre = pNombreArchivo;
		}

		@Override
		public void run() {
			try {
			JasperPrint print = this.getJasperPrint(ds);
			JasperExportManager.exportReportToPdfFile(print, nombre);
			} catch (Exception e){
				e.printStackTrace();
			}
		}
		
		private JasperPrint getJasperPrint(
				TrascenderDataSource pTrascenderDataSource) throws Exception {
			URL urlReporte = this.getClass().getResource(
					"/com/trascender/saic/reporte/compilado/"
							+ pTrascenderDataSource.getNombreReporte());
			JasperReport reporte = (JasperReport) JRLoader.loadObject(urlReporte);
			reporte.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);
			JasperPrint jasperPrint = JasperFillManager.fillReport(reporte,
					pTrascenderDataSource.getMapaParametros(),
					pTrascenderDataSource);
			return jasperPrint;
		}
		
	}
	

}
