package com.trascender.habilitaciones.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Stack;

import org.nfunk.jep.ParseException;
import org.nfunk.jep.function.PostfixMathCommand;

import com.trascender.framework.util.Util;

public class FuncionesFecha {
	
	private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	
	private Calendar parsear(Object fecha) throws ParseException{
		Date locDate;
		//Si es doble probablemente sea el time en epoch.
		if (fecha instanceof Double) {
			locDate = new Date(((Double)fecha).longValue());
		} else {
			try {
				 locDate = format.parse(fecha.toString());
			} catch (java.text.ParseException e) {
				throw new ParseException(e.getMessage());
			}
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(locDate);
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
	
	class HoyFunction extends PostfixMathCommand {
		
		public HoyFunction(){
			numberOfParameters = 0;
		}
	
		@Override
		public void run(Stack stack) throws ParseException {
			checkStack(stack);
			stack.push(format.format(new Date()));
		}
	}
	
	class MenorFunction extends PostfixMathCommand {
		
		public MenorFunction(){
			numberOfParameters = 2;
		}
	
		@Override
		public void run(Stack stack) throws ParseException {
			checkStack(stack);
			Date fecha1 = parsear(stack.pop()).getTime();
			Date fecha2 = parsear(stack.pop()).getTime();
			boolean menor = !Util.isFechaAfterNoTima(fecha1, fecha2);
			stack.push(menor ? 1D : 0D);
		}
	}
	
	class MayorFunction extends PostfixMathCommand {
		
		public MayorFunction(){
			numberOfParameters = 2;
		}
	
		@Override
		public void run(Stack stack) throws ParseException {
			checkStack(stack);
			Date fecha1 = parsear(stack.pop()).getTime();
			Date fecha2 = parsear(stack.pop()).getTime();
			boolean mayor = Util.isFechaAfterNoTima(fecha1, fecha2);
			stack.push(mayor ? 1D : 0D);
		}
	}
	
	class IgualFunction extends PostfixMathCommand {
		
		public IgualFunction(){
			numberOfParameters = 2;
		}
	
		@Override
		public void run(Stack stack) throws ParseException {
			checkStack(stack);
			Date fecha1 = parsear(stack.pop()).getTime();
			Date fecha2 = parsear(stack.pop()).getTime();
			boolean igual = Util.isFechaEqualsNoTima(fecha1, fecha2);
			stack.push(igual ? 1D : 0D);
		}
	}

}
