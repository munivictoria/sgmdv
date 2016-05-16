package com.trascender.habilitaciones.util;

import org.nfunk.jep.JEP;
import org.nfunk.jep.function.If;
import org.nfunk.jep.function.Round;
//Round;

public class MotorFormulas {
	
	public static JEP initializeJEP(){
		JEP jep = new JEP();
		try{
			
			jep.initSymTab();
			jep.initFunTab();
			jep.addStandardConstants();
			jep.addConstant("verdadero", 1);
			jep.addConstant("falso",0);
			jep.addStandardFunctions();
			jep.getFunctionTable().put("redondear", new Round());
			jep.addFunction("IF", new If());
			jep.addFunction("num", new NumFunction());
			jep.addFunction("DIA", new FuncionesFecha().new DiaFunction());
			jep.addFunction("MES", new FuncionesFecha().new MesFunction());
			jep.addFunction("AÑO", new FuncionesFecha().new AnioFunction());
			jep.addFunction("HOY", new FuncionesFecha().new HoyFunction());
			jep.addFunction("FECHA_MENOR", new FuncionesFecha().new MenorFunction());
			jep.addFunction("FECHA_MAYOR", new FuncionesFecha().new MayorFunction());
			jep.addFunction("FECHA_IGUAL", new FuncionesFecha().new IgualFunction());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return jep;
	}
	
	public static void main(String... args) {
		JEP jep = MotorFormulas.initializeJEP();
		jep.addVariable("FECHA_VENCIMIENTO_CUOTA", "21/01/2016");
		jep.addVariable("RÉGIMEN_DE_TRIBUTACIÓN", "RÉGIMEN_GENERAL");
		jep.parseExpression("FECHA_MAYOR(HOY(), FECHA_VENCIMIENTO_CUOTA)");
		Double valor = jep.getValue();
		System.out.println(valor);
	}
	
	
}
