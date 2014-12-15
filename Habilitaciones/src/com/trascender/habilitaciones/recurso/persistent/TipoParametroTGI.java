package com.trascender.habilitaciones.recurso.persistent;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.trascender.framework.exception.TrascenderException;
import com.trascender.habilitaciones.recurso.persistent.tgi.DocumentoTGI;



/**
 * 
 * @author Mariano Lusardi
 * @hibernate.subclass discriminator-value = "TGI"
 */

@Entity
@DiscriminatorValue(value = "TGI")
public class TipoParametroTGI extends TipoParametro{

	public enum TipoAtributoTGI{
		;
		
		@Override
		public String toString() {
			return com.trascender.framework.util.Util.capitalizeEnumName(super.toString());
		}
	}

	/**
	 * 
	 */
	public static final long serialVersionUID = 8954334705039476242L;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "ATRIBUTO")
	private TipoAtributoTGI atributoTGI;


	public TipoAtributoTGI getAtributoTGI() {
		return atributoTGI;
	}
	public void setAtributoTGI(TipoAtributoTGI atributoTGI) {
		this.atributoTGI = atributoTGI;
	}

	
	
	@Override
	public Double getValor(DocHabilitanteEspecializado pDocumentoHabilitanteEspecializado) throws TrascenderException {
		if (pDocumentoHabilitanteEspecializado instanceof DocumentoTGI){
			
			switch (this.atributoTGI){
			}
			return 0d;
		}
		else{
			throw new IllegalArgumentException("El documento habilitante especializado debe ser del tipo DocumentoTGI");
			
		}
	}
	
	@Override
	public void setNombreAtributo(String pNombreAtributo) {
		this.atributoTGI = TipoAtributoTGI.valueOf(pNombreAtributo);
	}
}