
package com.trascender.compras.reporte.dataSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import com.trascender.compras.recurso.persistent.suministros.Contratacion;
import com.trascender.compras.recurso.persistent.suministros.LineaContratacion;
import com.trascender.compras.recurso.persistent.suministros.Proveedor;
import com.trascender.framework.recurso.persistent.Municipalidad;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.framework.util.TrascenderDataSource;
import com.trascender.framework.util.Util;

public class PresupuestoDS extends TrascenderDataSource {
	private int lineaActual = -1;
	private List<LineaContratacion> listaLineas;
	private Map<String, Object> parametros = new HashMap<String, Object>();

	public PresupuestoDS(Contratacion pContratacion, Proveedor pProveedor) {
		listaLineas = pContratacion.getListaLineasContratacion();
		Municipalidad municipalidad = SecurityMgr.getInstance().getMunicipalidad();
		
		if(pProveedor != null){
			parametros.put("PAR_PROVEEDOR", pProveedor);
			parametros.put("PAR_NOMBRE_PROVEEDOR", pProveedor.getRazonSocial());
		}else{
			parametros.put("PAR_PROVEEDOR", new Proveedor());
			parametros.put("PAR_NOMBRE_PROVEEDOR", "---------------");
		}
		
		parametros.put("PAR_MUNICIPALIDAD", municipalidad);
		parametros.put("PAR_CONTRATACION", pContratacion);
	}

	public Object getFieldValue(JRField field) throws JRException {
		Object valor = null;

		if(field.getName().equals("F_NUMERO_ITEM")) {
			valor = Util.getFormatIfNull(lineaActual + 1);

		} else if(field.getName().equals("F_CANTIDAD")) {
			valor = Util.getFormatIfNull(listaLineas.get(lineaActual).getCantidad().toString());

		} else if(field.getName().equals("F_UNIDAD")) {
			valor = Util.getFormatIfNull(listaLineas.get(lineaActual).getBien().getUnidad().toString());

		} else if(field.getName().equals("F_PRODUCTO")) {
			valor = Util.getFormatIfNull(listaLineas.get(lineaActual).getBien().getNombre().toString());
		}
		return valor;
	}

	public boolean next() throws JRException {
		return ++lineaActual < listaLineas.size();
	}

	public Map<String, Object> getMapaParametros() {
		return parametros;
	}

	public String getNombreReporte() {
		return "Reporte_presupuesto.jasper";
	}

}