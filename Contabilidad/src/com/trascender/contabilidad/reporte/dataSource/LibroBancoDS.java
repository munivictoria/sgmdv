package com.trascender.contabilidad.reporte.dataSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import com.trascender.contabilidad.recurso.persistent.LibroBanco;
import com.trascender.contabilidad.recurso.persistent.LineaLibroBanco;
import com.trascender.framework.util.TrascenderDataSource;
import com.trascender.framework.util.Util;

public class LibroBancoDS extends TrascenderDataSource{
	private int lineaActual = -1;
	private Map <String, Object> parametros;
	private List <LineaLibroBanco> listaLineas;
	private LibroBanco locLibroBanco;
	
	public LibroBancoDS(LibroBanco pLibroBanco) throws Exception{
		parametros = new HashMap<String, Object> ();
		listaLineas = new ArrayList<LineaLibroBanco>();
		locLibroBanco = pLibroBanco;
		
		parametros.put("PAR_IMAGEN", this.getLogoMunicipalidad());
		parametros.put("PAR_TITULO", this.getTituloReporte());
		parametros.put("PAR_SUBTITULO", this.getSubtituloReporte());
		parametros.put("PAR_NOMBRE_BANCO", locLibroBanco.getCuentaBancaria().getBanco().getNombre());
		parametros.put("PAR_SUCURSAL", locLibroBanco.getCuentaBancaria().getBanco().getSucursal());
		parametros.put("PAR_NUM_CUENTA", locLibroBanco.getCuentaBancaria().getNumero());
		
		for (LineaLibroBanco cadaLinea : pLibroBanco.getLineasLibroBanco()) {
			listaLineas.add(cadaLinea);
		}
	}
	@Override
	public Object getFieldValue(JRField arg0) throws JRException {
		// TODO Auto-generated method stub
		Object locValor = null;
		
		if(listaLineas.size() != 0)
		{
		if("F_FECHA".equals(arg0.getName())){
			locValor = Util.getFormatIfNull(listaLineas.get(lineaActual).getFecha());
		}else if ("F_TIPO_MOVIMIENTO".equals(arg0.getName())){
			locValor = Util.getFormatIfNull(listaLineas.get(lineaActual).getTipo().toString());
		}else if ("F_IMPORTE".equals(arg0.getName())){
			locValor = Util.getFormatIfNull(listaLineas.get(lineaActual).getImporte());
		}
		}
		return locValor;
	}

	@Override
	public boolean next() throws JRException {
		return ++lineaActual < 1;
	}

	@Override
	public Map<String, Object> getMapaParametros() {
		return parametros;
	}

	@Override
	public String getNombreReporte() {
		return "Reporte_Libro_Banco.jasper";
	}

}
