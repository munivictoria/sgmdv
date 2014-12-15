package com.trascender.framework.recurso.persistent.dinamicos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "FECHA")
public class AtributoDinamicoFecha extends AtributoDinamico<Date>{

	private static final long serialVersionUID = -2274501245468515741L;
	
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
	@Column(name = "VALOR_FECHA")
	private Date valorFecha;
	
	@Override
	public void setValor(Date pValor) {
		valorFecha = pValor;
	}

	@Override
	public Date getValor() {
		return valorFecha;
	}

	@Override
	public void setValorString(String pValor) {
		if (pValor != null && !pValor.trim().isEmpty()){
			try {
				this.valorFecha = dateFormat.parse(pValor);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} else {
			this.valorFecha = null;
		}
	}

	@Override
	public String getValorString() {
		return this.valorFecha == null ? null : dateFormat.format(valorFecha);
	}

	@Override
	public String getNombreAtributoValor() {
		return "valorFecha";
	}

}
