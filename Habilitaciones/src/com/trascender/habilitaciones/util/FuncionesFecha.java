package com.trascender.habilitaciones.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Stack;

import org.nfunk.jep.ParseException;
import org.nfunk.jep.function.PostfixMathCommand;

public class FuncionesFecha {
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
	private Calendar parsear(Object fecha) throws Exception{
		Date locFecha = dateFormat.parse(fecha.toString());
		Calendar cal = Calendar.getInstance();
		cal.setTime(locFecha);
		return cal;
	}
	
	private Double getValorOCero(Object fecha, int field){
		try {
			Calendar cal = this.parsear(fecha);
			Integer dia = cal.get(field);
			return Double.valueOf(dia.toString());
		} catch (Exception e){
			System.out.println("Error, se devuelve 0");
			return 0D;
		}
	}
	
	class DiaFunction extends PostfixMathCommand {
		
		public DiaFunction(){
			numberOfParameters = 1;
		}

		@Override
		public void run(Stack stack) throws ParseException {
			checkStack(stack);
			Object param = stack.pop();
			stack.push(getValorOCero(param, Calendar.DAY_OF_MONTH));
		}
	}
	
	class MesFunction extends PostfixMathCommand {
		
		public MesFunction(){
			numberOfParameters = 1;
		}

		@Override
		public void run(Stack stack) throws ParseException {
			checkStack(stack);
			Object param = stack.pop();
			stack.push(getValorOCero(param, Calendar.MONTH));
		}
	}

	class AnioFunction extends PostfixMathCommand {
	
		public AnioFunction(){
			numberOfParameters = 1;
		}
	
		@Override
		public void run(Stack stack) throws ParseException {
			checkStack(stack);
			Object param = stack.pop();
			stack.push(getValorOCero(param, Calendar.YEAR));
		}
	}

}
