package com.trascender.saic.util;

import java.util.Stack;

import org.nfunk.jep.ParseException;
import org.nfunk.jep.function.PostfixMathCommand;

import com.trascender.saic.recurso.persistent.LiquidacionTasa;
import com.trascender.saic.recurso.persistent.ModificadorLiquidacion;

public class ValorModificadorFuncion extends PostfixMathCommand {
	
	public static String VALOR_MODIFICADOR = "VALOR_MODIFICADOR";

	private LiquidacionTasa liquidacionTasa;
	
	public ValorModificadorFuncion(LiquidacionTasa pLiquidacion) {
		numberOfParameters = 1;
		this.liquidacionTasa = pLiquidacion;
	}

	public void run(Stack stack) throws ParseException {
		checkStack(stack);
		Object param = stack.pop();
		stack.push(this.getValor(param.toString()));
	}

	private Object getValor(String nombreModificador) throws ParseException {
		Double valor = 0D;
		for (ModificadorLiquidacion cadaModificador : liquidacionTasa.getListaModificadoresLiquidacion()) {
			if (cadaModificador.getNombre().equals(nombreModificador)) {
				valor = cadaModificador.getValorModificador();
				break;
			}
		}
		return valor;
	}
}