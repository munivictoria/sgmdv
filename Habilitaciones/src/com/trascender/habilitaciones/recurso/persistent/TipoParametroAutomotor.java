package com.trascender.habilitaciones.recurso.persistent;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;

import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.util.MultiMap;
import com.trascender.habilitaciones.business.interfaces.BusinessDocumentoAutomotorLocal;
import com.trascender.habilitaciones.recurso.filtros.FiltroValuacionAcara;
import com.trascender.habilitaciones.recurso.persistent.transito.DocumentoAutomotor;
import com.trascender.habilitaciones.recurso.persistent.transito.Modelo;
import com.trascender.habilitaciones.recurso.persistent.transito.ValuacionAcara;
import com.trascender.habilitaciones.recurso.persistent.transito.Vehiculo;

@Entity
@DiscriminatorValue(value = "AUTOMOTOR")
public class TipoParametroAutomotor extends TipoParametro{

	public static final long serialVersionUID = -5196742077341161229L;

	public enum AtributoAutomotor{
		VALOR_ACARA, MONEDA_ACARA, CANTIDAD_VEHICULOS;
		
		@Override
		public String toString() {
			return com.trascender.framework.util.Util.capitalizeEnumName(super.toString());
		}
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name = "ATRIBUTO")
	private AtributoAutomotor atributoAutomotor;
	
	@Transient
	private Vehiculo locVehiculoAnterior;
	
	@Transient
	private ValuacionAcara locValuacionAnterior;
	
	@Transient
	private MultiMap<Integer, Modelo, ValuacionAcara> locMapaValuaciones;

	public AtributoAutomotor getAtributoAutomotor() {
		return atributoAutomotor;
	}
	public void setAtributoAutomotor(AtributoAutomotor atributoAutomotor) {
		this.atributoAutomotor = atributoAutomotor;
	}
	
	@Override
	public Object getValor(DocHabilitanteEspecializado pDocumentoHabilitanteEspecializado) throws TrascenderException {
		if (pDocumentoHabilitanteEspecializado instanceof DocumentoAutomotor){
			DocumentoAutomotor locDocAutomotor = (DocumentoAutomotor)pDocumentoHabilitanteEspecializado;
			Vehiculo locVehiculo = locDocAutomotor.getVehiculo();
			ValuacionAcara locValuacion;
			
			double tiempo = System.currentTimeMillis();
			
			switch (this.atributoAutomotor){
				case VALOR_ACARA:					
					locValuacion = this.getValuacionAcara(locVehiculo);
					System.out.println("VALOR_ACARA = "+((System.currentTimeMillis()-tiempo)/1000));
					if(locValuacion != null){
						return locValuacion.getValor();
					}
					return 0d;
				case CANTIDAD_VEHICULOS:
					return pDocumentoHabilitanteEspecializado.getObligacion().getPersona().getCantidadVehiculos().doubleValue();
				case MONEDA_ACARA:					
					locValuacion = this.getValuacionAcara(locVehiculo);
					
					if(locValuacion != null && locValuacion.getMoneda() != null){
						System.out.println("MONEDA_ACARA = "+((System.currentTimeMillis()-tiempo)/1000));
						return locValuacion.getMoneda().toString();
					}
					return "";
			}
			return 0d;
		}
		else{
			throw new IllegalArgumentException("El documento habilitante especializado debe ser del tipo DocumentoAutomotor");
			
		}
	}
	
	@Override
	public void setNombreAtributo(String pNombreAtributo) {
		 this.atributoAutomotor = AtributoAutomotor.valueOf(pNombreAtributo);
	}
	
	private BusinessDocumentoAutomotorLocal getBusinessAutomotor() throws Exception{
		Context ctx = new InitialContext();
		BusinessDocumentoAutomotorLocal businessDocumentoAutomotorLocal =
				(BusinessDocumentoAutomotorLocal) ctx.lookup(BusinessDocumentoAutomotorLocal.JNDI_NAME);
		return businessDocumentoAutomotorLocal;
	}
	
	private ValuacionAcara getValuacionAcara(Vehiculo pVehiculo){
		if(this.locVehiculoAnterior == null){ // Si el vehiculo anterior es nulo, significa que es la primer vez
			//que quiere levantar una valuacion acara
			
			FiltroValuacionAcara locFiltro = new FiltroValuacionAcara();
			locFiltro.setAnio(pVehiculo.getAnio());
			locFiltro.setModelo(pVehiculo.getModelo());
			locFiltro.setActiva(true);

			try{
				List<ValuacionAcara> locListaResultado = getBusinessAutomotor().findListaValuacionesAcara(locFiltro).getListaResultados();
				ValuacionAcara locValuacion = null;
				if(locListaResultado != null && !locListaResultado.isEmpty()){
					locValuacion = locListaResultado.get(0);
				}
				this.locVehiculoAnterior = pVehiculo; // asigno el valor de pVehiculo a locVehiculoAnterior
				this.locValuacionAnterior = locValuacion; // guardo la valuacion que levante, por si la necesito de nuevo
				return locValuacion;
			} catch(Exception e){
				e.printStackTrace();
			}
		} else if(this.locVehiculoAnterior.equals(pVehiculo)){ // Si el vehiculo anterior es igual al vehiculo
			//que me llega por parámetro, le devuelvo la valuacion acara que habia levantado anteriormente.
			return this.locValuacionAnterior;
		}
		else if(locMapaValuaciones == null){ // Si el vehiculo anterior no es nulo, no es igual al que me llega por
			//parámetro, y el mapa de las valuaciones es nulo, significa que tengo que levantar todas las valuaciones.
			FiltroValuacionAcara locFiltro = new FiltroValuacionAcara();
			locFiltro.setActiva(true);
			try {
				List<ValuacionAcara> locListaValuaciones = getBusinessAutomotor().findListaValuacionesAcara(locFiltro).getListaResultados();
				
				locMapaValuaciones = new MultiMap<Integer, Modelo, ValuacionAcara>();
				for(ValuacionAcara cadaValuacion : locListaValuaciones){
					locMapaValuaciones.put(cadaValuacion.getAnio(), cadaValuacion.getModelo(), cadaValuacion);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return this.locMapaValuaciones.get(pVehiculo.getAnio(), pVehiculo.getModelo());
	}
}
