package com.trascender.habilitaciones.recurso.persistent;

import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.trascender.habilitaciones.recurso.persistent.shps.DocumentoSHPS;

@Entity
@DiscriminatorValue(value = "PARAMETROS_SHPS")
public class TipoParametroSHPS extends TipoParametro {

	/**
	 * 
	 */
	public static final long serialVersionUID = 6167225254633447414L;
	
	public TipoParametroSHPS() throws NamingException, CreateException{
		super();
	}
	
	public enum AtributoSHPS{
		//este se delega a la lógica
		INGRESOS_DEVENGADOS_ANIO_ANTERIOR,
		//Correspondientes a Multas:
		PRESENTO_DDJJ, //Usado para las multas formales
		//VALOR_MULTA_FORMAL,//Puede no ir
		VALOR_MULTA_POR_OMISION,
		DESCUENTO_POR_RETENCION;
		@Override
		public String toString() {
			return com.trascender.framework.util.Util.capitalizeEnumName(super.toString());
		}
	}
	

	@Enumerated(EnumType.STRING)
	@Column(name = "ATRIBUTO")
	private AtributoSHPS atributoSHPS;
	
	public AtributoSHPS getAtributoSHPS() {
		return atributoSHPS;
	}
	public void setAtributoSHPS(AtributoSHPS atributoSHPS) {
		this.atributoSHPS = atributoSHPS;
	}


	@Override
	public Double getValor(DocHabilitanteEspecializado pDocumentoHabilitanteEspecializado) throws Exception{
		
		if (pDocumentoHabilitanteEspecializado instanceof DocumentoSHPS){
			DocumentoSHPS locDocumentoSHPS = (DocumentoSHPS)pDocumentoHabilitanteEspecializado;
			switch (this.atributoSHPS){
				//aplicar la logica correspondiente aca
				case INGRESOS_DEVENGADOS_ANIO_ANTERIOR: {
					Double locIngresosDevengadosAnioAnterior = 0d;
						for(RegistroValuado cadaRegistroValuado : pDocumentoHabilitanteEspecializado.getListaRegistrosValuados()){
							
							if( (this.getPeriodoLiquidacion().getCalendario().getAnio() - 
									cadaRegistroValuado.getCuotaLiquidacion().getPeriodo().getCalendario().getAnio()) == 1){
								try{
									locIngresosDevengadosAnioAnterior+=cadaRegistroValuado.getMontoImponible();
								}
								catch(Exception e){
									e.printStackTrace();
								}
							}
						}
					return locIngresosDevengadosAnioAnterior;
				}
				case DESCUENTO_POR_RETENCION: {
					Double locDescuentoPorRetencion = 0d;
					//Tomamos cualquier registro valuado, pues todos tienen el mismo valor de retencion.
					 List<RegistroValuado> locListaRegistrosValuados = pDocumentoHabilitanteEspecializado.getRegistroValuado(getCuotaLiquidacion());
					 if (!locListaRegistrosValuados.isEmpty()) {
						 DeclaracionJuradaSHPS unaDeclaracion = (DeclaracionJuradaSHPS) locListaRegistrosValuados.get(0);
						 if (unaDeclaracion.getDescuentoPorRetenciones() != null) {
							 locDescuentoPorRetencion = unaDeclaracion.getDescuentoPorRetenciones();
						 }
					 }
					return locDescuentoPorRetencion;
				}
				case PRESENTO_DDJJ: return pDocumentoHabilitanteEspecializado.getRegistroValuado(this.getCuotaLiquidacion()).isEmpty() ? 0d : 1d;
				//case VALOR_MULTA : (())
			}
			return 0d;
		}
		else{
			throw new IllegalArgumentException("El documento habilitante especializado debe ser del tipo DocumentoSHPS");
			
		}
	}

	@Override
	public void setNombreAtributo(String pNombreAtributo) {
		this.atributoSHPS = AtributoSHPS.valueOf(pNombreAtributo);
	}
}
