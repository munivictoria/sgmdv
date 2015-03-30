package com.trascender.saic.util;

import java.util.Stack;

import org.nfunk.jep.ParseException;
import org.nfunk.jep.function.PostfixMathCommand;

import com.trascender.saic.recurso.persistent.LiquidacionTasa;
import com.trascender.saic.recurso.persistent.ModificadorLiquidacion;

public class ValorBasicoTasaFuncion extends PostfixMathCommand {
	
	public static String VALOR_BASICO_TASA = "VALOR_BASICO_TASA";

	private LiquidacionTasa liquidacionTasa;
	
	public ValorBasicoTasaFuncion(LiquidacionTasa pLiquidacion) {
		numberOfParameters = 0;
		this.liquidacionTasa = pLiquidacion;
	}

	public void run(Stack stack) throws ParseException {
		checkStack(stack);
		Double valor = liquidacionTasa.getValor();
		stack.push(valor);
	}
}