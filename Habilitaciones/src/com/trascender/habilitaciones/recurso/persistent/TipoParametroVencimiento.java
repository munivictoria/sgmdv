package com.trascender.habilitaciones.recurso.persistent;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.trascender.framework.exception.TrascenderException;

@Entity
@DiscriminatorValue(value = "VENCIMIENTO")
public class TipoParametroVencimiento extends TipoParametro {


	public enum TipoAtributoVencimiento{
		IMPORTE_PRIMER_VENCIMIENTO,
		IMPORTE_BASICO_PRIMER_VENCIMIENTO,
		MESES_DESDE_INICIO_PERIODO,
		DIAS_DESDE_INICIO_PERIODO,
		MESES_DESDE_PRIMER_VENCIMIENTO,
		DIAS_DESDE_PRIMER_VENCIMIENTO,
		NUMERO_CUOTA,NUMERO_PERIODO,FECHA_VENCIMIENTO_CUOTA;
		
		@Override
		public String toString() {
			return com.trascender.framework.util.Util.capitalizeEnumName(super.toString());
		}
		
		public String getNombreVariable(){
			return com.trascender.framework.util.Util.getEnumNameFromString(this.toString());
		}
	}
	/**
	 * 
	 */
	public static final long serialVersionUID = 2383425353616252158L;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "ATRIBUTO")
	private TipoAtributoVencimiento atributoVencimiento;
	
	public TipoAtributoVencimiento getAtributoVencimiento() {
		return atributoVencimiento;
	}
	public void setAtributoVencimiento(TipoAtributoVencimiento atributoVencimiento) {
		this.atributoVencimiento = atributoVencimiento;
	}
	
	
	@Override
	public Object getValor(DocHabilitanteEspecializado docHabilitanteEspecializado)	throws TrascenderException {
		//Algunos se devuelven aqui, otros durante la liquidacion porque dependen de la boleta liquidada.
		switch (this.atributoVencimiento) {
			case NUMERO_PERIODO:
				return new Double(getCuotaLiquidacion().getPeriodo().getNumero());
			case NUMERO_CUOTA:
				return new Double(getCuotaLiquidacion().getNumero());
			case FECHA_VENCIMIENTO_CUOTA:
				return new SimpleDateFormat("dd/MM/yyyy").
						format(getCuotaLiquidacion().getListaVencimientos().get(0).getTime());
			default:
				return 0D;
			}
	}
	
	//Pues hay parametros de vencimiento que se calculan normalmente, y otros que se calculan a mano en la liquidacion.
	public boolean calculoNormal() {
		return getAtributosCalculoNormal().contains(atributoVencimiento);
	}
	
	public static List<TipoAtributoVencimiento> getAtributosCalculoManual() {
		return Arrays.asList(new TipoAtributoVencimiento[]{
			TipoAtributoVencimiento.IMPORTE_PRIMER_VENCIMIENTO, 
			TipoAtributoVencimiento.IMPORTE_BASICO_PRIMER_VENCIMIENTO, 
			TipoAtributoVencimiento.MESES_DESDE_INICIO_PERIODO,
			TipoAtributoVencimiento.DIAS_DESDE_INICIO_PERIODO, 
			TipoAtributoVencimiento.MESES_DESDE_PRIMER_VENCIMIENTO, 
			TipoAtributoVencimiento.DIAS_DESDE_PRIMER_VENCIMIENTO});
	}
	
	public static List<TipoAtributoVencimiento> getAtributosCalculoNormal() {
		return Arrays.asList(new TipoAtributoVencimiento[]{TipoAtributoVencimiento.NUMERO_CUOTA, 
			TipoAtributoVencimiento.NUMERO_PERIODO, 
			TipoAtributoVencimiento.FECHA_VENCIMIENTO_CUOTA});
	}
	
	@Override
	public void setNombreAtributo(String pNombreAtributo) {
		this.atributoVencimiento = TipoAtributoVencimiento.valueOf(pNombreAtributo);
	}
}
