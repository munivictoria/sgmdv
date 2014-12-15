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
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return jep;
	}
	
	
}
