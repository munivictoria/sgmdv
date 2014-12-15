package com.trascender.catastro.recurso.persistent;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.trascender.framework.util.Util;
/**
 * @author jsantacruz
 */
public class Planta extends TipoPlanta{

	public static final long serialVersionUID = -7317049571189765300L;
	
	/**
	 * @nota setea por defecto de tipo Urbano
	 */
	public Planta(){
		this.setTipoPlanta(TipoPlanta.Tipo.URBANA);
	}
	
	public Planta(Tipo pTipo){
		this.setTipoPlanta(pTipo);
	}
	
	/**
	 * Tipo Edificaciones {EDIFICADO_NO_PH,
		EDIFICADO_PH,
		BALDIO}
	 */
	public enum Edificacion{
		EDIFICADO_NO_PH,
		EDIFICADO_PH,
		BALDIO,
		BALDIO_A_VERIFICAR;
		
		@Override
		public String toString() {
			return Util.capitalizeEnumName(this.name());
		}
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name="TIPO_EDIFICACION")
	private Edificacion tipoEdificacion = Edificacion.EDIFICADO_NO_PH;
	
	@Column(name="DE_REGADIO")
	private boolean deRegadio = false;
	
	@Column(name="DE_SECANO")
	private boolean deSecano = false;
	
	public Edificacion getTipoEdificacion() {
		return tipoEdificacion;
	}
	
	public void setTipoEdificacion(Edificacion tipoEdificacion) {
		this.tipoEdificacion = tipoEdificacion;
	}
	
	public boolean getDeRegadio() {
		return deRegadio;
	}
	
	public void setDeRegadio(boolean deRegadio) {
		this.deRegadio = deRegadio;
	}
	
	public boolean getDeSecano() {
		return deSecano;
	}
	
	public void setDeSecano(boolean deSecano) {
		this.deSecano = deSecano;
	}
	
	@Override
	public String toString() {
		return super.getTipoPlanta().toString();
	}
}
