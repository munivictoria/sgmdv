package com.trascender.contabilidad.recurso.transients;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import com.trascender.contabilidad.recurso.persistent.IngresoVario;
import com.trascender.contabilidad.recurso.persistent.TicketCaja;
import com.trascender.framework.util.TrascenderDataSource;

public class ResumenActualCajaIngresoVarioDS extends TrascenderDataSource implements Serializable{
	private static final long serialVersionUID = 2448917273213765504L;
	private int lineaActual = -1;
	private int lineaActualCuenta = -1;
	private int lineaActualImputaciones = -1;
	private List<TicketCaja> listaTicketsCaja;
	
	public ResumenActualCajaIngresoVarioDS(List<TicketCaja> pListaTicketsCaja) { 
//		parametros = new HashMap<String, Object> ();
		
		this.listaTicketsCaja = pListaTicketsCaja;
		
//		parametros.put("PAR_", new IngresosVariosDS(pListaTicketsCaja));
	}
	
	@Override
	public Object getFieldValue(JRField field) throws JRException {
		Object valor = null;
		IngresoVario locIngreso = (IngresoVario)listaTicketsCaja.get(lineaActual).getDetalles().iterator().next().getDeuda();
		
		if (field.getName().equals("CONCEPTO")){
			valor =  locIngreso.getConceptoIngresoVario();
		} else if(field.getName().equals("CUENTA")){
			
		}
		
		return valor;
	}

//	@Override
//	public boolean next() throws JRException {
//		// TODO Auto-generated method stub
//		return ++lineaActual < 1;
//	}

	@Override
	public Map<String, Object> getMapaParametros() {
		return null;
	}
	
	@Override
	public boolean next() throws JRException {
		// TODO Auto-generated method stub
		return ++lineaActual < listaTicketsCaja.size();
	}

	@Override
	public String getNombreReporte() {
		return "Reporte_Planilla_Diaria_Caja_Ingreso_Vario.jasper";
	}
}
	
//class IngresosVariosDS implements JRDataSource{
//		private int lActual = -1;
//		private final List<IngresoVario> locListaIngresos;
//
//		public IngresosVariosDS(List<IngresoVario> pListaIngresos){
//			this.locListaIngresos = pListaIngresos;
//		}
//
//		@Override
//		public Object getFieldValue(JRField field) throws JRException {
//			Object valor = null;
//			IngresoVario locIngresoVario = (IngresoVario)locListaIngresos.get(lActual).getDetalles().iterator().next().getDeuda();
//			if (field.getName().equals("F_CONCEPTO")){
//				valor =  locIngresoVario.getConceptoIngresoVario();
//			} else if(field.getName().equals("F_CUENTA")){
//				valor = locIngresoVario.getListaImputacionIngresos().get(index);
//			} else if(field.getName().equals("F_VALOR_IMPUTACION")){
//				valor = locListaIngresos.get(lActual).getMonto();
//			}
//			
//			return valor;
//		}
//
//		@Override
//		public boolean next() throws JRException {
//			// TODO Auto-generated method stub
//			return ++lActual < locListaIngresos.size();
//		}		
//}