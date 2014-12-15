package com.trascender.saic.reporte.dataSource;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import com.trascender.catastro.business.interfaces.BusinessRegistroParcelarioLocal;
import com.trascender.catastro.recurso.persistent.Zonificacion;
import com.trascender.framework.recurso.persistent.Domicilio;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.util.Util;
import com.trascender.habilitaciones.recurso.persistent.CuotaLiquidacion;
import com.trascender.habilitaciones.recurso.persistent.RegAlicuota;
import com.trascender.habilitaciones.recurso.persistent.shps.DocumentoSHPS;
import com.trascender.saic.recurso.persistent.LiquidacionTasa;

public class LiquidacionShpsDS extends LiquidacionDS{
	DocumentoSHPS locDocumentoSHPS;
	BusinessRegistroParcelarioLocal businessParcela;
	Usuario usuario;

	public LiquidacionShpsDS(LiquidacionTasa pLiquidacionTasa, Zonificacion pZonificacion, String pTitulo, String pSubtitulo, BusinessRegistroParcelarioLocal pBusinessParcela, Usuario pUsuario) {
		super(pLiquidacionTasa, pZonificacion,pTitulo, pSubtitulo);
		locDocumentoSHPS = (DocumentoSHPS) this.liquidacionTasa.getDocGeneradorDeuda().getObligacion().getDocumentoEspecializado();
		businessParcela = pBusinessParcela;
		usuario = pUsuario;
		this.armarParametros();
	}

	private void armarParametros(){

		mapaParametros.put("PAR_USUARIO", usuario.getUser());

		mapaParametros.put("PAR_NRO_CUENTA", locDocumentoSHPS.getNumeroInscripcion());
		CuotaLiquidacion locCuota = liquidacionTasa.getCuotaLiquidacion();
		mapaParametros.put("PAR_PERIODO", locCuota.getPeriodo().getNumero().toString() 
				+" / "+ locCuota.getPeriodo().getCalendario().getAnio());
		//		mapaParametros.put("PAR_PERIODO", locPeriodo.getNumero().toString() + "/" + locPeriodo.getFechaFin().get(Calendar.YEAR));
		mapaParametros.put("PAR_NRO_INSCRIPCION", Util.getFormatIfNull(locDocumentoSHPS.getNumeroInscripcion()));
		Persona locPersona = liquidacionTasa.getDocGeneradorDeuda().getObligacion().getPersona();
		if(locPersona instanceof PersonaFisica){
			mapaParametros.put("PAR_DOCUMENTO", Util.getFormatIfNull(((PersonaFisica)locPersona).getTipoDocumento() + ": " 
					+ ((PersonaFisica)locPersona).getNumeroDocumento()));
		} else {
			mapaParametros.put("PAR_DOCUMENTO", "");
		}
		Domicilio locDomicilio = locPersona.getDomicilio();
		//		try{
		//			Parcela locParcela = businessParcela.getParcelaPorId(locDocumentoSHPS.getListaLocalesComerciales().iterator().next().getIdParcela());
		//			locDomicilio = locParcela.getDomicilioParcelario();
		//		} catch (Exception e){
		//			locDomicilio = locPersona.getDomicilioPostal();
		//		}
		mapaParametros.put("PAR_DOMICILIO_COMERCIAL", locDomicilio.toString());
		mapaParametros.put("PAR_TOTAL", liquidacionTasa.getMonto());
		mapaParametros.put("PAR_DETALLES_DS", new DetallesShpsDS(locDocumentoSHPS));
//		Vencimiento locVencimiento = new ArrayList<Vencimiento>(liquidacionTasa.getListaVencimientos()).get(0);
		mapaParametros.put("PAR_FECHA", liquidacionTasa.getFechaVencimiento());
	}

	@Override
	public String getNombreReporte() {
		return "Reporte_SHPS.jasper";
	}
}

class DetallesShpsDS implements JRDataSource{
	private int lActual = -1;
	private final List<RegAlicuota> locListaRegAlicuotas;

	public DetallesShpsDS(DocumentoSHPS pDocuSHPS){
		this.locListaRegAlicuotas = new ArrayList<RegAlicuota>(pDocuSHPS.getListaRegAlicuotas());
	}

	@Override
	public Object getFieldValue(JRField field) throws JRException {
		Object valor = null;

		if (field.getName().equals("F_DESCRIPCION")){
			valor = locListaRegAlicuotas.get(lActual).toString();
		} 
		//				else if (field.getName().equals("F_ALICUOTA")){
		//				valor = listaDocumentoSHPS.get(lActual).getRegistroAlicuota().getValor().toString() + ( (listaDocumentoSHPS.get(lActual).getRegistroAlicuota().isPorcentual())?"%":" Fija");
		//			} else if (field.getName().equals("F_MONTO_IMPONIBLE")){
		////				List<ParametroValuado> listaParametrosValudaso = new ArrayList<ParametroValuado>(locLiquidacion.getListaParametrosValuados());
		////				if(!listaParametrosValudaso.isEmpty()){
		////					valor = listaParametrosValudaso.get(0).getValorParametro();
		////				}
		//				if (!locLiquidacion.getListaAlicuotasLiquidadas().isEmpty()) {
		//					valor = locLiquidacion.getListaAlicuotasLiquidadas().iterator().next().getValor();
		//				} else {
		//					valor = 0d;
		//				}
		//			} else if (field.getName().equals("F_TASA")){
		//				valor = "$" + locLiquidacion.getValor().toString();
		//			}

		return valor;
	}

	@Override
	public boolean next() throws JRException {
		// TODO Auto-generated method stub
		return ++lActual < locListaRegAlicuotas.size();
	}		

}
