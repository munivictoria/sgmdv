package com.trascender.contabilidad.reporte.dataSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import com.trascender.contabilidad.recurso.persistent.Balance;
import com.trascender.contabilidad.recurso.persistent.CuentaHistoricaBalance;
import com.trascender.framework.util.TrascenderDataSource;
import com.trascender.framework.util.Util;

public class BalanceContableDS extends TrascenderDataSource{

	private int lineaActual = -1;
	private Map <String, Object>parametros;
	private List <CuentaHistoricaBalance> listaLineas;
	private Balance locBalance;

	
	public BalanceContableDS(Balance pBalance) throws Exception{ 
		parametros = new HashMap<String, Object> ();
		listaLineas = new ArrayList<CuentaHistoricaBalance>();
		locBalance = pBalance;
		
		parametros.put("PAR_IMAGEN", this.getLogoMunicipalidad());
		parametros.put("PAR_TITULO", this.getTituloReporte());
		parametros.put("PAR_SUBTITULO", this.getSubtituloReporte());
		parametros.put("PAR_FECHA", locBalance.getFecha());
		parametros.put("PAR_TIPO_BALANCE", locBalance.getTipoBalance().getNombre());
		parametros.put("PAR_NOMBRE_BALANCE", locBalance.getNombre());
		
		System.out.println("+++++++++ Cantidad de lineas: " + pBalance.getListaCuentaHistoricoBalance().size());
		
		for (CuentaHistoricaBalance cadaLinea : pBalance.getListaCuentaHistoricoBalance()) {
			listaLineas.add(cadaLinea);
		}
	}
	
	@Override
	public Object getFieldValue(JRField arg0) throws JRException {
		// TODO Auto-generated method stub
		Object locValor = null;
		
		if(listaLineas != null){
			System.out.println("+++++++ PASO LINEA: " + lineaActual);
			if("F_COD_IMPUTACION".equals(arg0.getName())){
				locValor = Util.getFormatIfNull(listaLineas.get(lineaActual).getCodigoImputacion());
			}else if ("F_CUENTA_NOMBRE".equals(arg0.getName())){
				locValor = Util.getFormatIfNull(listaLineas.get(lineaActual).getCuenta().getNombre());
			}else if ("F_CUENTA_ABREV".equals(arg0.getName())){
				locValor = Util.getFormatIfNull(listaLineas.get(lineaActual).getCuenta().getAbreviatura());
			}else if ("F_BALANCE_VALOR".equals(arg0.getName())){
				locValor = Util.getFormatIfNull(listaLineas.get(lineaActual).getValor());
			}
		}
		return locValor;
	}

	@Override
	public boolean next() throws JRException {
		// TODO Auto-generated method stub
		return ++lineaActual < listaLineas.size();
	}

	@Override
	public Map<String, Object> getMapaParametros() {
		// TODO Auto-generated method stub
		return parametros;
	}

	@Override
	public String getNombreReporte() {
		// TODO Auto-generated method stub
		return "Reporte_Balance_Contable.jasper";
	}

}
