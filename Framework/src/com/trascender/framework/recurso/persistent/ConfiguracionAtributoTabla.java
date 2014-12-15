
package com.trascender.framework.recurso.persistent;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.trascender.framework.recurso.transients.AtributoConsultable;

@Entity
@Table(name = "CONFIG_ATTR_TABLA")
public class ConfiguracionAtributoTabla implements Serializable {

	public static final long serialVersionUID = -2066878386018117847L;

	@Id
	@GeneratedValue(generator = "GEN_ID_CONFIG_AT_TAB", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(allocationSize = 1, name = "GEN_ID_CONFIG_AT_TAB", sequenceName = "GEN_ID_CONFIG_AT_TAB")
	@Column(name = "ID_CONF_ATTR_TABLA")
	private Long idConfiguracionAtributoTabla = -1l;

	@Column(nullable = false)
	private Integer orden;

	@Column(name = "NOMBRE_ATRIBUTO")
	private String nombreAtributo;

	@Column(name = "NOMBRE_ATRIBUTO_TABLA")
	private String nombreAtributoTabla;

	@Column(name = "TIPO_DATO")
	@Enumerated(EnumType.STRING)
	private AtributoConsultable.Tipo tipoDato = AtributoConsultable.Tipo.TEXTO;

	@Column(name = "ANCHO_COLUMNA")
	private Integer anchoColumna = 0;
	
	@ManyToOne
	@JoinColumn(name = "ID_CONJ_ATTR_TABLA")
	private ConjuntoAtributoTabla conjuntoAtributoTabla;

	public Integer getOrden() {
		return orden;
	}

	public void setOrden(Integer pOrden) {
		orden = pOrden;
	}

	public String getNombreAtributo() {
		return nombreAtributo;
	}

	public void setNombreAtributo(String pNombreAtributo) {
		nombreAtributo = pNombreAtributo;
	}

	public String getNombreAtributoTabla() {
		return nombreAtributoTabla;
	}

	public void setNombreAtributoTabla(String pNombreAtributoTabla) {
		nombreAtributoTabla = pNombreAtributoTabla;
	}

	public Long getIdConfiguracionAtributoTabla() {
		return idConfiguracionAtributoTabla;
	}

	public void setIdConfiguracionAtributoTabla(Long pIdConfiguracionAtributoTabla) {
		idConfiguracionAtributoTabla = pIdConfiguracionAtributoTabla;
	}

	public AtributoConsultable.Tipo getTipoDato() {
		return tipoDato;
	}

	public void setTipoDato(AtributoConsultable.Tipo pTipoDato) {
		tipoDato = pTipoDato;
	}

	public Integer getAnchoColumna() {
		return anchoColumna;
	}

	public void setAnchoColumna(Integer pAnchoColumna) {
		anchoColumna = pAnchoColumna;
	}

	public ConjuntoAtributoTabla getConjuntoAtributoTabla() {
		return conjuntoAtributoTabla;
	}

	public void setConjuntoAtributoTabla(ConjuntoAtributoTabla pConjuntoAtributoTabla) {
		conjuntoAtributoTabla = pConjuntoAtributoTabla;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orden == null) ? 0 : orden.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		ConfiguracionAtributoTabla other = (ConfiguracionAtributoTabla) obj;
		if(orden == null) {
			if(other.orden != null)
				return false;
		} else if(!orden.equals(other.orden))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Configuración Atributo: " + this.getNombreAtributo() + " Orden - Alias en Tabla: " + this.getOrden() + " - " + this.getNombreAtributoTabla();
	}

}