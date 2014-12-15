
package com.trascender.compras.system.ejb;

import java.io.BufferedInputStream;
import java.io.File;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.naming.Context;
import javax.naming.InitialContext;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.engine.util.JRLoader;

import com.trascender.compras.business.interfaces.BusinessLicitacionLocal;
import com.trascender.compras.business.interfaces.BusinessReportesComprasLocal;
import com.trascender.compras.recurso.persistent.inventario.MovimientoDeMercaderia;
import com.trascender.compras.recurso.persistent.suministros.Contratacion;
import com.trascender.compras.recurso.persistent.suministros.OrdenCompra;
import com.trascender.compras.recurso.persistent.suministros.Proveedor;
import com.trascender.compras.recurso.persistent.suministros.SolicitudSuministro;
import com.trascender.compras.reporte.dataSource.MovimientoMercaderiaDS;
import com.trascender.compras.reporte.dataSource.OrdenCompraDS;
import com.trascender.compras.reporte.dataSource.PresupuestoDS;
import com.trascender.compras.reporte.dataSource.ProveedorDS;
import com.trascender.compras.reporte.dataSource.SolicitudSuministroDS;
import com.trascender.compras.system.interfaces.SystemAdministracionFactura;
import com.trascender.compras.system.interfaces.SystemAdministracionOrdenCompra;
import com.trascender.compras.system.interfaces.SystemAdministracionProveedores;
import com.trascender.compras.system.interfaces.SystemAdministracionSolicitudSuministro;
import com.trascender.compras.system.interfaces.SystemReportesCompras;
import com.trascender.compras.system.interfaces.SystemStock;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.habilitaciones.exception.HabilitacionesException;

@Stateful(name = "ejb/SystemReportesCompras")
public class SystemReportesComprasBean implements SystemReportesCompras {

	private static final String RUTA_REPORTES = "/com/trascender/compras/reporte/compilado/";

	private long llave = 0;

	@EJB
	private SystemAdministracionOrdenCompra systemOrdenCompra;
	@EJB
	private SystemAdministracionProveedores systemProveedores;
	@EJB
	private SystemAdministracionSolicitudSuministro systemSolicitudSuministro;
	@EJB
	private BusinessLicitacionLocal businessLicitacion;
	@EJB
	private SystemStock systemStock;
	@EJB
	private SystemAdministracionFactura systemFactura;
	@EJB
	private BusinessReportesComprasLocal businessReportesCompras;

	public void setLlave(long pLlave) throws Exception {
		this.llave = pLlave;
		this.systemOrdenCompra.setLlave(llave);
		this.systemSolicitudSuministro.setLlave(llave);
		this.systemProveedores.setLlave(llave);
		this.systemStock.setLlave(llave);
		this.systemFactura.setLlave(llave);
	}

	public void ejbActivate() throws EJBException, RemoteException {
	}

	public void ejbPassivate() throws EJBException, RemoteException {
	}

	public void ejbRemove() throws EJBException, RemoteException {
	}

	public void setSessionContext(SessionContext arg0) throws EJBException, RemoteException {
	}

	public void ejbCreate() {
		try {
			Context ctx = new InitialContext();
			this.systemOrdenCompra = (SystemAdministracionOrdenCompra) ctx.lookup(SystemAdministracionOrdenCompra.JNDI_NAME);
			this.systemSolicitudSuministro = (SystemAdministracionSolicitudSuministro) ctx.lookup(SystemAdministracionSolicitudSuministroBean.JNDI_NAME);
			this.systemProveedores = (SystemAdministracionProveedores) ctx.lookup(SystemAdministracionProveedores.JNDI_NAME);

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public JasperPrint getReporteOrdenCompra(long pIdOrdenCompra) throws Exception {
		try {
			OrdenCompra locOrdenCompra = this.systemOrdenCompra.findOrdenCompraByID(pIdOrdenCompra);
			OrdenCompraDS ordenCompraDS = new OrdenCompraDS(locOrdenCompra);
			URL urlMaestro = this.getClass().getResource(RUTA_REPORTES + ordenCompraDS.getNombreReporte());
			JasperReport JR = (JasperReport) JRLoader.loadObject(urlMaestro);
			JR.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);
			JasperPrint jasperPrint = JasperFillManager.fillReport(JR, ordenCompraDS.getMapaParametros(), ordenCompraDS);
			return jasperPrint;
		} catch(Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(55);
		}
	}

	public JasperPrint getReporteProveedor(long pIdProveedor) throws TrascenderException {
		try {
			Proveedor locProveedor = this.systemProveedores.findProveedorByID(pIdProveedor);
			ProveedorDS proveedorDS = new ProveedorDS(locProveedor);
			URL urlMaestro = this.getClass().getResource(RUTA_REPORTES + proveedorDS.getNombreReporte());
			BufferedInputStream bf = new BufferedInputStream(this.getClass().getResourceAsStream("recurso"));
			JasperReport JR = (JasperReport) JRLoader.loadObject(urlMaestro);
			JR.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);
			JasperPrint jasperPrint = JasperFillManager.fillReport(JR, proveedorDS.getMapaParametros(), proveedorDS);
			return jasperPrint;
		} catch(Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(55);
		}
	}

	public JasperPrint getReporteSolicitudSuministro(long pIdSolicitudSuministro) throws TrascenderException {
		try {
			SolicitudSuministro locSolicitudSuministro = this.systemSolicitudSuministro.findSolicitudSuministroByID(pIdSolicitudSuministro);
			SolicitudSuministroDS ds = new SolicitudSuministroDS(locSolicitudSuministro);
			URL urlMaestro = this.getClass().getResource(RUTA_REPORTES + ds.getNombreReporte());
			JasperReport JR = (JasperReport) JRLoader.loadObject(urlMaestro);
			JR.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);
			JasperPrint jasperPrint = JasperFillManager.fillReport(JR, ds.getMapaParametros(), ds);
			return jasperPrint;
		} catch(Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(55);
		}
	}

	public Map<String, JasperPrint> getReportePresupuesto(Contratacion pContratacion) throws TrascenderException {
		try {
			if (pContratacion.getIdContratacion() != -1 ) {
				pContratacion = businessLicitacion.getContratacionPorId(pContratacion.getIdContratacion());
			}
			List<PresupuestoDS> listaPresupuestos = new ArrayList<PresupuestoDS>();
			Map<String, JasperPrint> mapaReportesPresupuesto = new HashMap<String, JasperPrint>();

			if(pContratacion.getListaProveedoresAutorizados().size() < 1) {
				listaPresupuestos.add(new PresupuestoDS(pContratacion, null));
			} else {
				for(Proveedor cadaProveedor : pContratacion.getListaProveedoresAutorizados()) {
					listaPresupuestos.add(new PresupuestoDS(pContratacion, cadaProveedor));
				}
			}

			for(PresupuestoDS ds : listaPresupuestos) {
				String rutaReportes = SecurityMgr.getInstance().getMunicipalidad().getRutaReportes();
				File fileReporte = new File(rutaReportes + ds.getNombreReporte());
				JasperReport reporte = (JasperReport) JRLoader.loadObject(fileReporte);
				mapaReportesPresupuesto.put(ds.getMapaParametros().get("PAR_NOMBRE_PROVEEDOR").toString(), JasperFillManager.fillReport(reporte, ds.getMapaParametros(), ds));
			}

			return mapaReportesPresupuesto;
		} catch(Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(55);
		}
	}

	public JasperPrint getReporteMovimientoMercaderia(long pIdMovimientoMercaderia) throws TrascenderException {
		try {
			MovimientoDeMercaderia locMovimientoMercaderia = this.systemStock.getMovimientoDeMercaderiaByID(pIdMovimientoMercaderia);
			MovimientoMercaderiaDS ds = new MovimientoMercaderiaDS(locMovimientoMercaderia);
			URL urlMaestro = this.getClass().getResource(RUTA_REPORTES + ds.getNombreReporte());
			JasperReport JR = (JasperReport) JRLoader.loadObject(urlMaestro);
			JR.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);
			JasperPrint jasperPrint = JasperFillManager.fillReport(JR, ds.getMapaParametros(), ds);
			return jasperPrint;
		} catch(Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(55);
		}
	}

	public JasperPrint getReporteLiquidacionCompra(long pIdLiquidacionCompra) throws TrascenderException {
		try {
			return businessReportesCompras.getReporteLiquidacionCompra(pIdLiquidacionCompra);
		} catch(Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(55);
		}
	}

}