package com.trascender.habilitaciones.recurso.persistent;

import java.text.SimpleDateFormat;
import java.util.Date;
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
		DESCUENTO_POR_RETENCION,
		FECHA_PRESENTACION_DJ;
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
	public Object getValor(DocHabilitanteEspecializado pDocumentoHabilitanteEspecializado) throws Exception{
		
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
					DeclaracionJuradaSHPS locDeclaracion = locDocumentoSHPS.getDeclaracionJurada(getCuotaLiquidacion());
					 if (locDeclaracion != null && locDeclaracion.getDescuentoPorRetenciones() != null) {
						 locDescuentoPorRetencion = locDeclaracion.getDescuentoPorRetenciones();
					 }
					return locDescuentoPorRetencion;
				}
				case PRESENTO_DDJJ: return pDocumentoHabilitanteEspecializado.getRegistroValuado(this.getCuotaLiquidacion()).isEmpty() ? 0d : 1d;
				case FECHA_PRESENTACION_DJ: {
					DeclaracionJuradaSHPS locDeclaracion = locDocumentoSHPS.getDeclaracionJurada(getCuotaLiquidacion());
					return locDeclaracion != null 
							? new SimpleDateFormat("dd/MM/yyyy").format(locDeclaracion.getFecha()) 
							: 0D;
				}
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
