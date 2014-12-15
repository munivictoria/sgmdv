package com.trascender.framework.recurso.persistent.validacionDinamica;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.trascender.framework.recurso.persistent.validacionDinamica.ComponenteValidacion.Operadores;

@Entity
@Table(name="VALOR_VALIDACION")
@DiscriminatorColumn(name="TIPO")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public abstract class ValorValidacion implements Serializable{

	public static final long serialVersionUID = 5637736207182635495L;
	
	@Id
	@GeneratedValue(generator="GEN_ID_VALOR_VALIDACION", strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(allocationSize = 1, name="GEN_ID_VALOR_VALIDACION", sequenceName="GEN_ID_VALOR_VALIDACION")
	@Column(name="ID_VALOR_VALIDACION")
	private Long idValorValidacion = -1l;
	
	public abstract <E> E getValor();
	public abstract void setValor(Object pValor);
	
	public abstract boolean comparar(Object pObjeto);
	public abstract boolean comparar(Object pObjeto, Operadores pTipoOperacion);

	public Long getIdValorValidacion() {
		return idValorValidacion;
	}

	public void setIdValorValidacion(Long pIdValorValidacion) {
		idValorValidacion = pIdValorValidacion;
	}

	
	
}
