package com.trascender.habilitaciones.recurso.persistent;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.trascender.habilitaciones.recurso.persistent.osp.DocumentoOSP;
import com.trascender.habilitaciones.recurso.persistent.osp.ServicioOSP;

@Entity
@DiscriminatorValue(value = "PARAMETRO_OSP")
public class TipoParametroOSP extends TipoParametro{
	
	public static final long serialVersionUID = -4960767527509085821L;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "ATRIBUTO")
	private TipoAtributoOSP tipoAtributoOSP;
	
	
	public enum TipoAtributoOSP{
		VALOR_SERVICIO_OSP,
		BASE_CONSUMO,
		ES_MEDIDO,
		VALOR_POR_EXCEDENTE,
		COEFICIENTE_ZONAL,
		COEFICIENTE_SOBRE_TERRENO,
		COEFICIENTE_SOBRE_EDIFICADO,
		VALOR_MINIMO,
		COEFICIENTE_CODIGO_SERVICIO,
		CODIGO_SERVICIO,
		CONSUMO,
		VOLCADO_EFLUENTES_INDUSTRIALES;
		
		@Override
		public String toString() {
			return com.trascender.framework.util.Util.capitalizeEnumName(super.toString());
		}
	}

	public TipoParametroOSP() throws NamingException, CreateException {
		try{

		} catch (Exception e){
			e.printStackTrace();
		}
	}

	public TipoAtributoOSP getTipoAtributoOSP() {
		return tipoAtributoOSP;
	}

	public void setTipoAtributoOSP(TipoAtributoOSP tipoAtributoOSP) {
		this.tipoAtributoOSP = tipoAtributoOSP;
	}

	@Override
	public Double getValor(DocHabilitanteEspecializado pDocumentoHabilitanteEspecializado) throws Exception {
		if (pDocumentoHabilitanteEspecializado instanceof DocumentoOSP){
			DocumentoOSP locDocumento = (DocumentoOSP) pDocumentoHabilitanteEspecializado;
			//Sabemos que es siempre el unico de la lista, y es un Servicio OSP
			ServicioOSP locServicioOSP = (ServicioOSP) locDocumento.getListaRegAlicuotas().iterator().next();
			
			
			switch(tipoAtributoOSP){
					//base de consumo se toma en cuenta solo cuando se trata de servicio medido
				case BASE_CONSUMO: {
					try {
						if(locServicioOSP.isMedido()){
							//locDocumento.setConsumoBasico(systemDocumentoOSP.getConsumoBasico(locDocumento));
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					return (locServicioOSP.isMedido())?locDocumento.getBaseConsumo():0d;
				}
				
				case COEFICIENTE_SOBRE_EDIFICADO: return (locServicioOSP.getCoeficienteValorEdificado() != null)?locServicioOSP.getCoeficienteValorEdificado().doubleValue():0d;
				case COEFICIENTE_SOBRE_TERRENO: return (locServicioOSP.getCoeficienteValorTerreno() != null)?locServicioOSP.getCoeficienteValorTerreno().doubleValue():0d;
				case COEFICIENTE_ZONAL: return locDocumento.getCoeficienteZonal();
				case ES_MEDIDO: return locServicioOSP.isMedido()?1d:0d;
				case VALOR_MINIMO: return (locServicioOSP.getMinimo() != null)?locServicioOSP.getMinimo().doubleValue():0d;
				case VALOR_POR_EXCEDENTE: return (locServicioOSP.getValorPorExcedente() != null)?locServicioOSP.getValorPorExcedente().doubleValue():0d;
				case VALOR_SERVICIO_OSP: return locServicioOSP.getValor().doubleValue();
				case CONSUMO: {
								if(locServicioOSP.isMedido()){
									//Por ahora el contribuyente solo tiene un servicio, entonces se asume que hay solo una declaracion jurada.
									RegistroValuado locRegistroValuado = locDocumento.getRegistroValuado(this.getCuotaLiquidacion()).get(0);
									//El consumo es la diferencia entre la medición del periodo y la medición del periodo anterior
									if(locRegistroValuado != null){ 
										return locRegistroValuado.getMontoImponible().doubleValue();
									}
									else{
//										throw new HabilitacionesException(900);
									}
								}
								return 0d;
					
				}
				
				case VOLCADO_EFLUENTES_INDUSTRIALES: return ((locServicioOSP.isVolcadoEfluentesIndustriales())?1d:0d);
				case COEFICIENTE_CODIGO_SERVICIO : return (locServicioOSP.getCoeficienteCodigoServicio() != null)?locServicioOSP.getCoeficienteCodigoServicio().doubleValue():0f;
				case CODIGO_SERVICIO: return Double.valueOf(locServicioOSP.getCodigo());
				
			}
			return 0d;
		}
		else{
			throw new IllegalArgumentException("El tipo de documento especializado para los parámetros OSP debe ser DocumentoOSP");
		}
	}
	
	@Override
	public void setNombreAtributo(String pNombreAtributo) {
		this.tipoAtributoOSP = TipoAtributoOSP.valueOf(pNombreAtributo);
	}
}
