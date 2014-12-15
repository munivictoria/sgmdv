package com.trascender.habilitaciones.recurso.persistent;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;

import com.trascender.framework.util.Util;

@Entity
@DiscriminatorValue("COMPUESTA")
public class VariableFormulaCompuesta extends VariableFormula{
	
	public enum Tipo {
		SUMA, PROMEDIO, MAXIMO, MINIMO, CANTIDAD;
		@Override
		public String toString() {
			return Util.capitalizeEnumName(this.name());
		}
	}
	
	@Column(name = "TIPO_COMPUESTA")
	@Enumerated(EnumType.STRING)
	private Tipo tipo = Tipo.SUMA;
	
	@Transient
	protected List<Double> listaEvaluandos = new ArrayList<Double>();
	
	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	public List<Double> getListaEvaluandos() {
		return listaEvaluandos;
	}
	public void setListaEvaluandos(List<Double> listaEvaluandos) {
		this.listaEvaluandos = listaEvaluandos;
	}
	
	/**
	 * Proceso la lista de Evaluandos y calcula el valor correspondiente segun el Tipo.
	 * @return
	 */
	public Double getValor(){
		Double valor = 0D;
		if (!this.listaEvaluandos.isEmpty()){
			switch (tipo) {
			case SUMA:
				for (Double cadaEvaluando : this.listaEvaluandos){
					valor += cadaEvaluando;
				}
				break;
			case PROMEDIO:
				for (Double cadaEvaluando : this.listaEvaluandos){
					valor += cadaEvaluando;
				}
				valor = valor / listaEvaluandos.size();
				break;
			case MAXIMO:
				valor = listaEvaluandos.iterator().next();
				for (Double cadaEvaluando : this.listaEvaluandos){
					valor = Math.max(cadaEvaluando, valor);
				}
				break;
			case MINIMO:
				valor = listaEvaluandos.iterator().next();
				for (Double cadaEvaluando : this.listaEvaluandos){
					valor = Math.min(cadaEvaluando, valor);
				}
				break;	
			case CANTIDAD:
				valor = Double.valueOf(listaEvaluandos.size());
				break;
			default:
				break;
			}
		}
		return valor;
	}
}
