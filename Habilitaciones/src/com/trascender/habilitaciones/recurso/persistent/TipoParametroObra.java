package com.trascender.habilitaciones.recurso.persistent;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;
import javax.rmi.PortableRemoteObject;

import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.catastro.recurso.persistent.ParcelaPorCuadra;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.habilitaciones.business.interfaces.BusinessDocumentoPlanObraLocal;
import com.trascender.habilitaciones.recurso.persistent.pfo.DocumentoPlanObra;

@Entity
@DiscriminatorValue(value = "OBRA")
public class TipoParametroObra extends TipoParametro{

	public static final long serialVersionUID = 1252942082365552983L;

	@Transient
	private BusinessDocumentoPlanObraLocal businessDocumentoPlanObraLocal;
	
	public enum TipoAtributo{
		VALOR_POR_METRO,
		METROS_OBRA,
		COSTO_TOTAL,
		TOTAL_METROS_AFECTADOS_POR_CUADRA,
		CANTIDAD_CUOTAS_PLAN_CUENTA,
		NUMERO_CUOTA,
		OBRA_VIA_ADMINISTRACION;
		
		@Override
		public String toString() {
			return com.trascender.framework.util.Util.capitalizeEnumName(super.toString());
		}
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name = "ATRIBUTO")
	private TipoAtributo tipoAtributoObra;
	
	public TipoParametroObra() throws Exception {
		super();
//		try{
//			 this.businessDocumentoPlanObraLocal = (BusinessDocumentoPlanObraLocal) new InitialContext().lookup(BusinessDocumentoPlanObraLocal.JNDI_NAME);
//		 } catch (Exception e){
//			e.printStackTrace();
//		}
	}
	
	public TipoAtributo getTipoAtributoObra() {
		return tipoAtributoObra;
	}
	public void setTipoAtributoObra(TipoAtributo tipoAtributoObra) {
		this.tipoAtributoObra = tipoAtributoObra;
	}

	@Override
	public Double getValor(DocHabilitanteEspecializado pDocHabilitanteEspecializado ) throws Exception {
		if (pDocHabilitanteEspecializado instanceof DocumentoPlanObra){
			DocumentoPlanObra locDocumentoPlanObra = (DocumentoPlanObra) this.businessDocumentoPlanObraLocal.getDocumentoPlanObra(pDocHabilitanteEspecializado.getObligacion());
			switch(this.tipoAtributoObra){
				case CANTIDAD_CUOTAS_PLAN_CUENTA: return locDocumentoPlanObra.getPlanCuentaObra().getCantidadCuotas().doubleValue();
				case COSTO_TOTAL: return locDocumentoPlanObra.getObra().getCostoTotalObra().doubleValue();
				case METROS_OBRA: return locDocumentoPlanObra.getObra().getMetrosTotalAfectados().doubleValue();
				case VALOR_POR_METRO: return locDocumentoPlanObra.getObra().getValorPorMetro().doubleValue();
				case TOTAL_METROS_AFECTADOS_POR_CUADRA: {
					Double cantidadTotal = 0d;
					Parcela locParcela = locDocumentoPlanObra.getParcela();
					for (ParcelaPorCuadra cadaParcelaPorCuadra : locParcela.getListaParcelasPorCuadra()){
						if (locDocumentoPlanObra.getObra().getListaCuadras().contains(cadaParcelaPorCuadra.getCuadra())){
							cantidadTotal+=cadaParcelaPorCuadra.getMetrosPorCuadra();
						}
					}
					return cantidadTotal;
				}
				case NUMERO_CUOTA: return 0d;
				case OBRA_VIA_ADMINISTRACION: return ((locDocumentoPlanObra.getObra().isObraViaAdministracion())?1d:0d);
				
			}
			return 0d;
		}
		else{
			throw new java.lang.IllegalArgumentException("El documento habilitante especializado utilizado en el TipoParametroObra debe ser DocumentoPlanObra");
		}
	}

	@Override
	public void setNombreAtributo(String pNombreAtributo) {
		this.tipoAtributoObra = TipoAtributo.valueOf(pNombreAtributo);
	}
}
