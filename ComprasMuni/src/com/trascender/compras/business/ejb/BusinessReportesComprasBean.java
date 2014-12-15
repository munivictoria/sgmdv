package com.trascender.compras.business.ejb;

import java.io.ByteArrayInputStream;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.engine.util.JRLoader;
import ar.trascender.criterio.clases.Criterio;
import ar.trascender.criterio.clases.Restriccion;

import com.trascender.compras.business.interfaces.BusinessReportesComprasLocal;
import com.trascender.compras.recurso.persistent.suministros.LiquidacionCompra;
import com.trascender.compras.reporte.dataSource.LiquidacionCompraDS;
import com.trascender.framework.recurso.persistent.ReportesJasper;

@Stateless(name = "ejb/BusinessReportesCompras")
public class BusinessReportesComprasBean implements BusinessReportesComprasLocal{

	private static final String RUTA_REPORTES = "/com/trascender/compras/reporte/compilado/";

	@PersistenceContext
	private EntityManager entityManager;

	public JasperPrint getReporteLiquidacionCompra(	long pIdLiquidacionCompra) throws Exception {
		LiquidacionCompra locLiquidacionCompra = entityManager.find(LiquidacionCompra.class, pIdLiquidacionCompra);
		LiquidacionCompraDS ds = new LiquidacionCompraDS(locLiquidacionCompra);
		
		
		Criterio locCriterio = Criterio.getInstance(entityManager, ReportesJasper.class)
				.add(Restriccion.ILIKE("nombre", "Reporte Liquidacion Compra"));
		ReportesJasper reporte = (ReportesJasper) locCriterio.uniqueResult();
		
		JasperReport JR = (JasperReport)JRLoader.loadObject(new ByteArrayInputStream(reporte.getValor()));
		
//		URL urlMaestro = this.getClass().getResource(RUTA_REPORTES+ds.getNombreReporte());
//		JasperReport JR = (JasperReport)JRLoader.loadObject(urlMaestro);
		JR.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);
		JasperPrint jasperPrint = JasperFillManager.fillReport(JR, ds.getMapaParametros(), ds);
		return jasperPrint;
	}

}
