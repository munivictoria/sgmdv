package com.trascender.contabilidad.reporte.dataSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.contabilidad.business.interfaces.BusinessCajaLocal;
import com.trascender.contabilidad.recurso.persistent.MovimientoCajaIngreso;
import com.trascender.framework.util.TrascenderDataSource;
import com.trascender.habilitaciones.recurso.persistent.osp.DocumentoOSP;
import com.trascender.habilitaciones.recurso.persistent.tgi.DocumentoTGI;
import com.trascender.saic.recurso.interfaces.Pagable;
import com.trascender.saic.recurso.persistent.LiquidacionTasa;
import com.trascender.saic.recurso.persistent.ModificadorLiquidacionFormula;
import com.trascender.saic.recurso.transients.LiquidacionTasaAgrupada;

public class LiquidacionDeudaDS extends TrascenderDataSource{
			
	List<Map<String, Object>> mapaLineas = new ArrayList<Map<String,Object>>();
	Map<String, Object> mapaParametro = new HashMap<String, Object>();
	private int lineaActual = -1;
	List<Pagable> listaLiquidacionTasa = new ArrayList<Pagable>();
	
	public LiquidacionDeudaDS(List<LiquidacionTasaAgrupada> pListaLiquidacionTasa, 
			BusinessCajaLocal pBusinessCajaLocal, String pTitulo, String pSubTitulo) {
		Map<String, Object> locMapa;
		mapaParametro.put("PAR_TITULO", pTitulo);
		mapaParametro.put("PAR_SUBTITULO", pSubTitulo);
		mapaParametro.put("PAR_IMAGEN", this.getLogoMunicipalidad());
		
		//Todas las liquidaciones tienen la misma fecha de vencimiento, se toma una cualquiera.
		Date ultimaFechaVencimiento = pListaLiquidacionTasa.get(0).getListaLiquidacionesTasa().get(0).getListaVencimientos().last().getFecha();
		mapaParametro.put("PAR_FECHA_LIQUIDACION_DEUDA", ultimaFechaVencimiento);
		
		Parcela locParcela = pListaLiquidacionTasa.get(0).getDocGeneradorDeuda().getObligacion().getDocumentoEspecializado().getParcela();
		DocumentoTGI locDocTGI = pListaLiquidacionTasa.get(0).getDocHabilitanteEspecializado(DocumentoTGI.class);
		DocumentoOSP locDocOSP = pListaLiquidacionTasa.get(0).getDocHabilitanteEspecializado(DocumentoOSP.class);
		mapaParametro.put("PAR_PARCELA", locParcela);
		mapaParametro.put("PAR_DOCUMENTO_TGI", locDocTGI);
		mapaParametro.put("PAR_DOCUMENTO_OSP", locDocOSP);
		mapaParametro.put("PAR_TITULAR", locParcela.getTituloPropiedad().getRegistroPropietarioEncargadoObligaciones().getPersona());
		
		for (LiquidacionTasaAgrupada cadaLiquidacionAgrupada : pListaLiquidacionTasa) {
			//Importe de cada tasa:
			for (LiquidacionTasa cadaLiquidacion : cadaLiquidacionAgrupada.getListaLiquidacionesTasa()) {
				//Primero actualizar la deuda.
				listaLiquidacionTasa.add(cadaLiquidacion);
				locMapa = this.getMapaInicial(cadaLiquidacionAgrupada);
				String nombreObligacion = cadaLiquidacion.getTipoTasa().getTipoObligacion().getNombre();
				locMapa.put("F_NOMBRE_COLUMNA", nombreObligacion.equalsIgnoreCase("OYSP") ? "OSM" : nombreObligacion);
				locMapa.put("F_VALOR_COLUMNA",  cadaLiquidacion.getValorTasaConDescuentos() 
							+ cadaLiquidacion.getSumaRecargosManuales());
				mapaLineas.add(locMapa);
			}
			//modificadores
			for (ModificadorLiquidacionFormula cadaModificador : 
				cadaLiquidacionAgrupada.getListaModificadoresLiquidacionFormula()) {
				locMapa = getMapaInicial(cadaLiquidacionAgrupada);
				locMapa.put("F_NOMBRE_COLUMNA", cadaModificador.getNombre());
				locMapa.put("F_VALOR_COLUMNA", cadaModificador.getValorModificador());
				mapaLineas.add(locMapa);
			}
			//Intereses
			locMapa = getMapaInicial(cadaLiquidacionAgrupada);
			locMapa.put("F_NOMBRE_COLUMNA", "ReAc");
			locMapa.put("F_VALOR_COLUMNA", cadaLiquidacionAgrupada.getInteres());
			mapaLineas.add(locMapa);
		}

		try {
			 List<MovimientoCajaIngreso> locListaMovimientos = pBusinessCajaLocal.getListaMovimientosCaja(listaLiquidacionTasa, false);
			 Collections.sort(locListaMovimientos, new Comparator<MovimientoCajaIngreso>() {

				@Override
				public int compare(MovimientoCajaIngreso o1,
						MovimientoCajaIngreso o2) {
					return o1.getCuenta().getNombre().compareTo(o2.getCuenta().getNombre());
				}
			});
			 mapaParametro.put("PAR_LISTA_MOV_CAJA_INGRESO", locListaMovimientos);
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private Map<String, Object> getMapaInicial(LiquidacionTasa pLiquidacion) {
		Map<String, Object> locMapa = new HashMap<String, Object>();
		locMapa.put("F_LIQUIDACION_TASA", pLiquidacion);
		locMapa.put("F_CUOTA_LIQUIDACION", 
				pLiquidacion.getCuotaLiquidacion());
		locMapa.put("F_DOCUMENTO_ESPECIALIZADO", pLiquidacion.getDocGeneradorDeuda().getObligacion().getDocumentoEspecializado());
		return locMapa;
	}

	@Override
	public Object getFieldValue(JRField arg0) throws JRException {
		return mapaLineas.get(lineaActual).get(arg0.getName());
	}

	@Override
	public boolean next() throws JRException {
		return ++lineaActual < mapaLineas.size();
	}

	@Override
	public Map<String, Object> getMapaParametros() {
		return mapaParametro;
	}

	@Override
	public String getNombreReporte() {
		return "Reporte_Liquidacion_Deuda.jasper";
	}

}
