package com.trascender.habilitaciones.util;

import java.util.Stack;

import org.nfunk.jep.JEP;
import org.nfunk.jep.ParseException;
import org.nfunk.jep.function.PostfixMathCommand;

import com.trascender.habilitaciones.recurso.persistent.tipoParametroGrilla.TipoParametroGrilla;
import com.trascender.habilitaciones.recurso.persistent.tipoParametroGrilla.TipoParametroGrillaFila;
import com.trascender.habilitaciones.recurso.persistent.tipoParametroGrilla.TipoParametroGrillaFilaColumna;

public class GrillaFunction extends PostfixMathCommand {
	
	private TipoParametroGrilla grilla;
	
	public GrillaFunction(TipoParametroGrilla grilla) {
		numberOfParameters = grilla.getListaVariables().size();
		this.grilla = grilla;
	}
	
	@Override
	public void run(Stack stack) throws ParseException {
		try {
			checkStack(stack);
			Object params[] = new Object[numberOfParameters];
			//Los parametros se leen de atras para adelante, por eso recorremos al reves
			for (int i = numberOfParameters - 1 ; i >= 0 ; i--) {
				Object param = stack.pop();
				params[i] = param;
			}
			JEP locJep = MotorFormulas.initializeJEP();
			for (int i = 0 ; i < grilla.getListaVariables().size() ; i++) {
				locJep.addVariable(grilla.getListaVariables().get(i).getNombre(), params[i]);
			}
			String ifFilas = "";
			for (TipoParametroGrillaFila cadaFila : grilla.getFilas()) {
				ifFilas += "IF("+cadaFila.getCondicion()+","+cadaFila.getNroFila()+",";
			}
			ifFilas += "-1";
			for (int i = 0 ; i < grilla.getFilas().size() ; i++) {
				ifFilas += ")";
			}
			locJep.parseExpression(ifFilas);
			Integer numeroFila = new Double(locJep.getValue()).intValue();
			TipoParametroGrillaFila locFila = this.grilla.getFilaPorNumero(numeroFila);
			String ifColumnas = "";
			for (TipoParametroGrillaFilaColumna cadaColumna : locFila.getColumnas()) {
				ifColumnas += "IF("+cadaColumna.getCondicion()+","+cadaColumna.getValor()+",";
			}
			ifColumnas += "0";
			for (int i = 0 ; i < locFila.getColumnas().size() ; i++) {
				ifColumnas += ")";
			}
			locJep.parseExpression(ifColumnas);
			stack.push(locJep.getValue());
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}

}
