package com.trascender.habilitaciones.util;

import java.util.Stack;

import org.nfunk.jep.ParseException;
import org.nfunk.jep.function.PostfixMathCommand;

public class FuncionBoba extends PostfixMathCommand {
	
	public FuncionBoba(){
		numberOfParameters = -1;
	}

	public void run(Stack stack) throws ParseException {
		checkStack(stack);
		Object param = stack.pop();
		stack.push(new Double(0D));
	}

}
