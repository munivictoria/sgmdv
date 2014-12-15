package com.trascender.habilitaciones.util;

import java.util.Stack;

import org.nfunk.jep.ParseException;
import org.nfunk.jep.function.PostfixMathCommand;

public class NumFunction extends PostfixMathCommand {
	
	public NumFunction(){
		numberOfParameters = 1;
	}

	public void run(Stack stack) throws ParseException {
		checkStack(stack);
		Object param = stack.pop();
		stack.push(this.parsear(param));
	}
	
	private Object parsear(Object pObject) throws ParseException{
		try{
			return Double.valueOf(pObject.toString());
		} catch (NumberFormatException e){
			System.out.println("Error al parsear, se devuelve 0 (cero)");
			return new Double(0);
		}
	}

}
