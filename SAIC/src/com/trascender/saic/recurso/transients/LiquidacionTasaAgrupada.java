package com.trascender.saic.recurso.transients;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.util.THashMap;
import com.trascender.habilitaciones.recurso.persistent.CuotaLiquidacion;
import com.trascender.habilitaciones.recurso.persistent.DocHabilitanteEspecializado;
import com.trascender.saic.recurso.persistent.AlicuotaLiquidada;
import com.trascender.saic.recurso.persistent.DocGeneradorDeuda;
import com.trascender.saic.recurso.persistent.LiquidacionTasa;
import com.trascender.saic.recurso.persistent.ModificadorLiquidacion;
import com.trascender.saic.recurso.persistent.ModificadorLiquidacionFormula;
import com.trascender.saic.recurso.persistent.ModificadorLiquidacionManual;
import com.trascender.saic.recurso.persistent.ParametroValuado;
import com.trascender.saic.recurso.persistent.RegistroCancelacion;

public class LiquidacionTasaAgrupada extends LiquidacionTasa {
	private static final long serialVersionUID = -2572224177074209752L;
	
	private List<LiquidacionTasa> listaLiquidacionesTasa = new ArrayList<LiquidacionTasa>();

	public List<LiquidacionTasa> getListaLiquidacionesTasa() {
		return listaLiquidacionesTasa;
	}

	public void setListaLiquidacionesTasa(
			List<LiquidacionTasa> listaLiquidacionesTasa) {
		this.listaLiquidacionesTasa = listaLiquidacionesTasa;
	}

	@Override
	public Double getRecargo() {
		Double recargo = 0d;
		for(LiquidacionTasa cadaLiquidacion : listaLiquidacionesTasa){
			recargo+=cadaLiquidacion.getRecargo();
		}
		return recargo;
	}

	@Override
	public Double getInteres() {
		Double interes = 0d;
		for(LiquidacionTasa cadaLiquidacion : listaLiquidacionesTasa){
			interes+=cadaLiquidacion.getInteres();
		}
		return interes;
	}

	@Override
	public Set<AlicuotaLiquidada> getListaAlicuotasLiquidadas() {
		Set<AlicuotaLiquidada> setAlicuotasLiquidadas = new HashSet<AlicuotaLiquidada>();
		for(LiquidacionTasa cadaLiquidacion : listaLiquidacionesTasa){
			setAlicuotasLiquidadas.addAll(cadaLiquidacion.getListaAlicuotasLiquidadas());
		}
		return setAlicuotasLiquidadas;
	}
	
	@Override
	public Set<ModificadorLiquidacionFormula> getListaModificadoresLiquidacionFormula() {
		Set<ModificadorLiquidacionFormula> setModificadores = new HashSet<ModificadorLiquidacionFormula>();
		THashMap<String> mapModificadoresLiquidacion = new THashMap<String>();
		for(LiquidacionTasa cadaLiquidacion : listaLiquidacionesTasa){
			for(ModificadorLiquidacionFormula cadaModif 
					: cadaLiquidacion.getListaModificadoresLiquidacionFormula()){
				mapModificadoresLiquidacion.add(cadaModif.getTipoModificador().getNombreColumnaReportes(), 
						cadaModif.getValorModificador());
			}
		}
		for(String cadaLlave : mapModificadoresLiquidacion.keySet()){
			Double cadaValor = mapModificadoresLiquidacion.get(cadaLlave);
			ModificadorLiquidacionFormula locModificador = new ModificadorLiquidacionFormula();
			locModificador.setNombre(cadaLlave);
			locModificador.setValorModificador(cadaValor);
			setModificadores.add(locModificador);
		}
		return setModificadores;
	}

	@Override
	public Set<ModificadorLiquidacion> getListaModificadoresLiquidacion() {
		Set<ModificadorLiquidacion> setModificadores = new HashSet<ModificadorLiquidacion>();
		THashMap<String> mapModificadoresLiquidacion = new THashMap<String>();
		for(LiquidacionTasa cadaLiquidacion : listaLiquidacionesTasa){
			for(ModificadorLiquidacion cadaModif : cadaLiquidacion.getListaModificadoresLiquidacion()){
				mapModificadoresLiquidacion.add(cadaModif.getNombre(), cadaModif.getValorModificador());
			}
		}
		for(String cadaLlave : mapModificadoresLiquidacion.keySet()){
			Double cadaValor = mapModificadoresLiquidacion.get(cadaLlave);
			ModificadorLiquidacion locModificador = new ModificadorLiquidacionManual();
			locModificador.setNombre(cadaLlave);
			locModificador.setValorModificador(cadaValor);
			setModificadores.add(locModificador);
		}
		return setModificadores;
	}
	
	public ModificadorLiquidacion getModificadorPorNombre(String pNombre){
		for (ModificadorLiquidacion cadaModificador : getListaModificadoresLiquidacion()){
			if (cadaModificador.getNombre().equalsIgnoreCase(pNombre)){
				return cadaModificador;
			}
		}
		return null;
	}

	@Override
	public Set<ParametroValuado> getListaParametrosValuados() {
		Set<ParametroValuado> setParametrosValuados = new HashSet<ParametroValuado>();
		for(LiquidacionTasa cadaLiquidacion : listaLiquidacionesTasa){
			setParametrosValuados.addAll(cadaLiquidacion.getListaParametrosValuados());
		}
		return setParametrosValuados;
	}

	@Override
	public Double getValorModificadoresSobreSaldoNeto() {
		Double valoresModifSaldoNeto = 0d;
		for(LiquidacionTasa cadaLiquidacion : listaLiquidacionesTasa){
			valoresModifSaldoNeto += cadaLiquidacion.getValorModificadoresSobreSaldoNeto();
		}
		return valoresModifSaldoNeto;
	}

	@Override
	public Double getValorModificadoresSobreSubTotal() {
		Double valoresModifSubTotal = 0d;
		for(LiquidacionTasa cadaLiquidacion : listaLiquidacionesTasa){
			valoresModifSubTotal += cadaLiquidacion.getValorModificadoresSobreSubTotal();
		}
		return valoresModifSubTotal;
	}

	@Override
	public Double getValorTotal() {
		Double valoresTotales = 0d;
		for(LiquidacionTasa cadaLiquidacion : listaLiquidacionesTasa){
			valoresTotales += cadaLiquidacion.getValorTotal();
		}
		return valoresTotales;
	}

	@Override
	public Double getMonto() {
		Double montos = 0d;
		for(LiquidacionTasa cadaLiquidacion : listaLiquidacionesTasa){
			montos += cadaLiquidacion.getMonto();
		}
		return montos;
	}

	@Override
	public Double getMultas() {
		Double multas = 0d;
		for(LiquidacionTasa cadaLiquidacion : listaLiquidacionesTasa){
			multas += cadaLiquidacion.getMultas();
		}
		return multas;
	}

	@Override
	public Persona getPersona() {
		return listaLiquidacionesTasa.get(0).getPersona();
	}
	
	@Override
	public DocGeneradorDeuda getDocGeneradorDeuda(){
		return listaLiquidacionesTasa.get(0).getDocGeneradorDeuda();
	}

	@Override
	public CuotaLiquidacion getCuotaLiquidacion() {
		return listaLiquidacionesTasa.get(0).getCuotaLiquidacion();
	}

	@Override
	public Date getFechaVencimiento() {
		return listaLiquidacionesTasa.get(0).getFechaVencimiento();
	}
	
	@Override
	public Date getFechaVencimientoMayor() {
		return listaLiquidacionesTasa.get(0).getFechaVencimientoMayor();
	}

	@Override
	public String getStringFormula() {
		return "";
	}

	@Override
	public String getStringParametrosValuados() {
		String stringParametrosValuados = "";
		for(LiquidacionTasa cadaLiquidacion : listaLiquidacionesTasa){
			stringParametrosValuados+= cadaLiquidacion.getStringParametrosValuados() + "\n";
		}
		return stringParametrosValuados;
	}

	@Override
	public String getStringParametrosValuadosAlicuota() {
		String stringParametrosValAlic = "";
		for(LiquidacionTasa cadaLiquidacion : listaLiquidacionesTasa){
			stringParametrosValAlic += cadaLiquidacion.getStringParametrosValuadosAlicuota() +  "\n";
		}
		return stringParametrosValAlic;
	}
	
	@Override
	public String getStringObligacion() {
		String stringObligaciones = "";
		for(LiquidacionTasa cadaLiquidacion : listaLiquidacionesTasa){
			stringObligaciones+= cadaLiquidacion.getStringObligacion() + "\n";
		}
		return stringObligaciones;
	}
	
	@Override
	public String getStringPeriodoLiquidado(){
		return listaLiquidacionesTasa.get(0).getPeriodoAnio();
	}

	@Override
	public long getIdRegistroDeuda() {
		return this.listaLiquidacionesTasa.get(0).getIdRegistroDeuda();
	}

	@Override
	public String getCuotaLiquidada() {
		return "";
	}

	@Override
	public RegistroCancelacion getRegistroCancelacion() {
		return listaLiquidacionesTasa.get(0).getRegistroCancelacion();
	}

	@Override
	public EstadoRegistroDeuda getEstado() {
		return listaLiquidacionesTasa.get(0).getEstado();
	}
	
	@Override
	public Double getValor() {
		Double valor = 0d;
		for(LiquidacionTasa cadaLiquidacion : listaLiquidacionesTasa){
			valor += cadaLiquidacion.getValor();
		}
		return valor;
	}

	public <T extends DocHabilitanteEspecializado> T getDocHabilitanteEspecializado(Class<T> pClase){
		for (LiquidacionTasa cadaLiquidacion : this.listaLiquidacionesTasa){
			DocHabilitanteEspecializado locDoc = cadaLiquidacion.getDocGeneradorDeuda().getObligacion().getDocumentoEspecializado(); 
			if (pClase.isInstance(locDoc)){
				return (T) locDoc;
			}
		}
		return null;
	}
	
	public <T extends DocHabilitanteEspecializado> LiquidacionTasa getLiquidacionPorDoc(Class<T> pClase){
		for (LiquidacionTasa cadaLiquidacion : this.listaLiquidacionesTasa){
			DocHabilitanteEspecializado locDoc = cadaLiquidacion.getDocGeneradorDeuda().getObligacion().getDocumentoEspecializado(); 
			if (pClase.isInstance(locDoc)){
				return cadaLiquidacion;
			}
		}
		return null;
	}
	
	private long decode(final String value) {
	    return Long.parseLong(value, 36);
	}
	 
	private String encode(final long value) {
	    return Long.toString(value, 36);
	}
	
	public String getCodigoBarraCodificado(){
		String locCodigoTotal = "";
		for (LiquidacionTasa cadaLiquidacion : this.listaLiquidacionesTasa){
			//Codifico cada codigo y agrego ceros hasta completar 8 digitos.
			String cadaCodigoLiquidacion = encode(cadaLiquidacion.getIdRegistroDeuda());
			for (int i = cadaCodigoLiquidacion.length() ; i < 8 ; i++){
				cadaCodigoLiquidacion = "0" + cadaCodigoLiquidacion;
			}
			locCodigoTotal += cadaCodigoLiquidacion;
		}
		//Agrego ceros hasta completar 24 caracteres totales.
		for (int i = locCodigoTotal.length() ; i < 24 ; i++){
			locCodigoTotal = "0" + locCodigoTotal;
		}
		//Agrego un 3
		locCodigoTotal = "3" + locCodigoTotal;
		return locCodigoTotal.toUpperCase();
	}

	@Override
	public Date getFechaNotificacion() {
		return listaLiquidacionesTasa.iterator().next().getFechaNotificacion();
	}

	@Override
	public Date getFechaApremio() {
		return listaLiquidacionesTasa.iterator().next().getFechaApremio();
	}
	
}
