package com.trascender.habilitaciones.recurso.persistent;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.trascender.habilitaciones.recurso.persistent.TipoParametroOSP.TipoAtributoOSP;
import com.trascender.habilitaciones.recurso.persistent.osp.DocumentoOSP;
import com.trascender.habilitaciones.recurso.persistent.osp.ServicioOSP;

@Entity
@DiscriminatorValue("ALICUOTA_OSP")
public class TipoParametroAlicuotaOSP extends TipoParametroAlicuota {
	private static final long serialVersionUID = 5545999723874368533L;

	@Enumerated(EnumType.STRING)
	@Column(name = "ATRIBUTO")
	private TipoParametroOSP.TipoAtributoOSP tipoAtributoOSP;

	@Override
	public Object getValor(AsocRegAlicuota pAsocRegAlicuota) {
		DocHabilitanteEspecializado locDocumento = pAsocRegAlicuota.getDocHabilitanteEspecializado();
		if (locDocumento instanceof DocumentoOSP){
			DocumentoOSP locDocumentoOSP = (DocumentoOSP) locDocumento;
			ServicioOSP locServicioOSP = (ServicioOSP) pAsocRegAlicuota.getRegistroAlicuota();
			switch(tipoAtributoOSP){
			//base de consumo se toma en cuenta solo cuando se trata de servicio medido
			case BASE_CONSUMO: {
				try {
					if(locServicioOSP.isMedido()){
						//locDocumento.setConsumoBasico(systemDocumentoOSP.getConsumoBasico(locDocumento));
						return (locServicioOSP.isMedido())?locDocumentoOSP.getBaseConsumo():0d;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			case COEFICIENTE_SOBRE_EDIFICADO: return (locServicioOSP.getCoeficienteValorEdificado() != null)?locServicioOSP.getCoeficienteValorEdificado().doubleValue():0d;
			case COEFICIENTE_SOBRE_TERRENO: return (locServicioOSP.getCoeficienteValorTerreno() != null)?locServicioOSP.getCoeficienteValorTerreno().doubleValue():0d;
			case COEFICIENTE_ZONAL: return locDocumentoOSP.getCoeficienteZonal();
			case ES_MEDIDO: return locServicioOSP.isMedido()?1d:0d;
			case VALOR_MINIMO: return (locServicioOSP.getMinimo() != null)?locServicioOSP.getMinimo().doubleValue():0d;
			case VALOR_POR_EXCEDENTE: return (locServicioOSP.getValorPorExcedente() != null)?locServicioOSP.getValorPorExcedente().doubleValue():0d;
			case VALOR_SERVICIO_OSP: return locServicioOSP.getValor().doubleValue();
			case CONSUMO: {
				if(locServicioOSP.isMedido()){
					RegistroValuado locRegistroValuado = locDocumentoOSP.getRegistroValuado(this.getCuotaLiquidacion(), locServicioOSP);
					//El consumo es la diferencia entre la medición del periodo y la medición del periodo anterior
					if(locRegistroValuado != null){ 
						return locRegistroValuado.getMontoImponible().doubleValue();
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
