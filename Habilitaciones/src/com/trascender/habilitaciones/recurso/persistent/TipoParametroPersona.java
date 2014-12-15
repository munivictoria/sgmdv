package com.trascender.habilitaciones.recurso.persistent;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.framework.recurso.persistent.PersonaJuridica;

@Entity
@DiscriminatorValue(value = "PERSONA")
public class TipoParametroPersona extends TipoParametro{

	
	public enum AtributoPersona{
		ES_JUBILADO,CANTIDAD_PROPIEDADES, ES_PERSONA_FISICA, TIPO_SOCIETARIO;
		
		@Override
		public String toString() {
			return com.trascender.framework.util.Util.capitalizeEnumName(super.toString());
		}
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "ATRIBUTO")
	private AtributoPersona atributoPersona=AtributoPersona.ES_JUBILADO;
	public static final long serialVersionUID = -554680777670528050L;

	public AtributoPersona getAtributoPersona() {
		return atributoPersona;
	}
	public void setAtributoPersona(AtributoPersona atributoPersona) {
		this.atributoPersona = atributoPersona;
	}
	
	
	@Override
	public Object getValor(DocHabilitanteEspecializado pDocumentoHabilitanteEspecializado)	throws TrascenderException {
		Persona locPersona = pDocumentoHabilitanteEspecializado.getObligacion().getPersona();
		switch(atributoPersona){
			case ES_JUBILADO:{
				boolean retorno = false;
				if (locPersona instanceof PersonaFisica){
					retorno = ((PersonaFisica)locPersona).isJubilado();
				}
				return retorno?1d:0d;
			}
			case CANTIDAD_PROPIEDADES: return ( (pDocumentoHabilitanteEspecializado.getObligacion().getPersona().getCantidadPropiedades() != null)
											   ?pDocumentoHabilitanteEspecializado.getObligacion().getPersona().getCantidadPropiedades().doubleValue():0d);
			case ES_PERSONA_FISICA: return pDocumentoHabilitanteEspecializado.getObligacion().getPersona() instanceof PersonaFisica ? 1D : 0D;
			case TIPO_SOCIETARIO:
				if (locPersona instanceof PersonaJuridica){
					PersonaJuridica locPersonaJuridica = (PersonaJuridica) locPersona;
					return locPersonaJuridica.getTipoSocietario() != null ? locPersonaJuridica.getTipoSocietario().name() : "";
				} 
		}
		return 0d;
	}
	
	@Override
	public void setNombreAtributo(String pNombreAtributo) {
		this.atributoPersona = AtributoPersona.valueOf(pNombreAtributo);
	}
}
