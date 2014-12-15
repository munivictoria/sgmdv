package com.trascender.habilitaciones.test.ejb3;


import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.nfunk.jep.JEP;

import com.trascender.habilitaciones.recurso.persistent.VariableFormula;
import com.trascender.habilitaciones.recurso.persistent.VariableFormulaCompuesta;
import com.trascender.habilitaciones.recurso.persistent.VariableFormulaSimple;
import com.trascender.habilitaciones.recurso.persistent.shps.Rubro;
import com.trascender.habilitaciones.util.MotorFormulas;

public class TestJep {
	
	private JEP jep;

	@Before
	public void setUp() throws Exception {
		this.jep = MotorFormulas.initializeJEP();
	}
	
	@Test
	public void testVariables(){
		try{
			jep.addVariable("a", 0);
			jep.addVariable("b", "nada");
			String expression = "(b == \"nada\") * 20 + 30";
			jep.parseExpression(expression);
			System.out.println(jep.getValue());
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testVariablesLiquidacionAlicuotas(){
		try{
			List<VariableFormulaSimple> locListaVariablesSimples = new ArrayList<VariableFormulaSimple>();
			List<VariableFormulaCompuesta> locListaVariablesCompuestas = new ArrayList<VariableFormulaCompuesta>();
			List<Rubro> locListaRubros = new ArrayList<Rubro>();
			
			//Inicializo los rubros
			Rubro locRubro1 = new Rubro();
			locRubro1.setNombre("Carniceria");
			locRubro1.setMinimo(10d);
			locRubro1.setValor(1.2d);
			locListaRubros.add(locRubro1);
			
			Rubro locRubro2 = new Rubro();
			locRubro2.setNombre("Fiambreria");
			locRubro2.setMinimo(20d);
			locRubro2.setValor(1.5);
			locListaRubros.add(locRubro2);
			
			//Inicializo las variables simples:
			VariableFormulaSimple locVariable1 = new VariableFormulaSimple();
			locVariable1.setNombre("ALICUOTA_DIVIDIDA");
			locVariable1.setExpresion("ALICUOTA_SHPS / 100");
			locListaVariablesSimples.add(locVariable1);
			
			//Inicializo las variables compuestas:
			VariableFormulaCompuesta locVariableCompuesta = new VariableFormulaCompuesta();
			locVariableCompuesta.setNombre("MINIMO_MAXIMO");
			locVariableCompuesta.setTipo(VariableFormulaCompuesta.Tipo.MAXIMO);
			locVariableCompuesta.setExpresion("PAGO_MINIMO");
			locListaVariablesCompuestas.add(locVariableCompuesta);
			
			locVariableCompuesta = new VariableFormulaCompuesta();
			locVariableCompuesta.setNombre("SUMA_IMPORTES_DECLARADOS");
			locVariableCompuesta.setTipo(VariableFormulaCompuesta.Tipo.SUMA);
			locVariableCompuesta.setExpresion("IMPORTE_DECLARADO");
			locListaVariablesCompuestas.add(locVariableCompuesta);
			
			//Simulo una liquidacion:
			
			//Se hace un recorrido inicial para ir obteniendo los acumulandos:
			for (Rubro cadaRubro : locListaRubros){
				jep.addVariable("TRAMO", "SIN_TRAMO");
				jep.addVariable("IMPORTE_DECLARADO", 3000d);
				jep.addVariable("ALICUOTA_SHPS", cadaRubro.getValor());
				jep.addVariable("UCM_PESOS_2010", 1.88d);
				jep.addVariable("PAGO_MINIMO", cadaRubro.getMinimo());
				
				//Calculo las variables simples
				for (VariableFormulaSimple cadaVariable : locListaVariablesSimples){
					jep.parseExpression(cadaVariable.getExpresion());
					cadaVariable.setValor(jep.getValue());
					jep.addVariable(cadaVariable.getNombre(), cadaVariable.getValor());
				}
				
				//Voy acumulando evaluandos en cada variable compuesta
				for (VariableFormulaCompuesta cadaVariableCompuesta : locListaVariablesCompuestas){
					jep.parseExpression(cadaVariableCompuesta.getExpresion());
					cadaVariableCompuesta.getListaEvaluandos().add(jep.getValue());
				}
			}
			
			//Calculo los avaluandos y agrego a la formula.
			for (VariableFormulaCompuesta cadaVariableCompuesta : locListaVariablesCompuestas){
				jep.addVariable(cadaVariableCompuesta.getNombre(), cadaVariableCompuesta.getValor());
			}
			//Al final, tengo la lista de acumulandos, comienzo la iteracion general para liquidar:
			for (Rubro cadaRubro : locListaRubros){
				jep.addVariable("TRAMO", "SIN_TRAMO");
				jep.addVariable("IMPORTE_DECLARADO", 3000d);
				jep.addVariable("ALICUOTA_SHPS", cadaRubro.getValor());
				jep.addVariable("UCM_PESOS_2010", 1.88d);
				jep.addVariable("PAGO_MINIMO", cadaRubro.getMinimo());
				
				//Calculo las variables simples
				for (VariableFormulaSimple cadaVariable : locListaVariablesSimples){
					jep.parseExpression(cadaVariable.getExpresion());
					cadaVariable.setValor(jep.getValue());
					jep.addVariable(cadaVariable.getNombre(), cadaVariable.getValor());
				}
				jep.parseExpression("(SUMA_IMPORTES_DECLARADOS >= MINIMO_MAXIMO) * SUMA_IMPORTES_DECLARADOS + " +
						"(SUMA_IMPORTES_DECLARADOS < MINIMO_MAXIMO) * MINIMO_MAXIMO");
				System.out.println(jep.getValue());
			}
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAlicuotas(){
		try{
			Variable variable1 = new Variable();
			variable1.setNombre("FER");
			variable1.setExpression("PAGO_MINIMO * UCM_PESOS_2010");
			
			Variable variable2 = new Variable();
			variable2.setNombre("ALICUOTA_DIVIDIDA");
			variable2.setExpression("ALICUOTA_SHPS / 100");
			
			Variable variable3 = new Variable();
			variable3.setNombre("IMPORTE_POR_ALICUOTA");
			variable3.setExpression("IMPORTE_DECLARADO * ALICUOTA_DIVIDIDA");
			
			//Añado todos los parametros basicos.
			jep.addVariable("TRAMO", "SIN_TRAMO");
			jep.addVariable("IMPORTE_DECLARADO", 50000d);
			jep.addVariable("ALICUOTA_SHPS", 1.2d);
			jep.addVariable("UCM_PESOS_2010", 1.88d);
			jep.addVariable("PAGO_MINIMO", 80d);
			
			//Calculo el valor de la variable 1
			jep.parseExpression(variable1.getExpression());
			variable1.setValor(jep.getValue());
			jep.addVariable(variable1.getNombre(), variable1.getValor());
			
			//Calculo el valor de la variable 2
			jep.parseExpression(variable2.getExpression());
			variable2.setValor(jep.getValue());
			jep.addVariable(variable2.getNombre(), variable2.getValor());
			
			//Calculo el valor de la variable 3
			jep.parseExpression(variable3.getExpression());
			variable3.setValor(jep.getValue());
			jep.addVariable(variable3.getNombre(), variable3.getValor());
			
			
			
//			jep.parseExpression("(((TRAMO != (\"SIN_TRAMO\"))) * (0))" +
//					"+(((TRAMO != \"SIN_TRAMO\" && IMPORTE_DECLARADO * (ALICUOTA_SHPS / 100) <= (PAGO_MINIMO * UCM_PESOS_2010)))*(PAGO_MINIMO * UCM_PESOS_2010))" +
//					"+(((TRAMO != \"SIN TRAMO\" && IMPORTE_DECLARADO * (ALICUOTA_SHPS / 100) >= (PAGO_MINIMO * UCM_PESOS_2010)))* (IMPORTE_DECLARADO * (ALICUOTA_SHPS / 100)))");
//			jep.parseExpression("(TRAMO == \"SIN_TRAMO\") * ((IMPORTE_DECLARADO * (ALICUOTA_SHPS / 100) <= (PAGO_MINIMO * UCM_PESOS_2010)) * (FER))" +
//					"+(TRAMO == \"SIN_TRAMO\") * ((IMPORTE_DECLARADO * (ALICUOTA_SHPS / 100) >= (FER)) * (IMPORTE_DECLARADO * (ALICUOTA_SHPS / 100)))");
			
			jep.parseExpression("(TRAMO == \"SIN_TRAMO\") * ((IMPORTE_POR_ALICUOTA <= FER) * (FER))" +
			"+(TRAMO == \"SIN_TRAMO\") * ((IMPORTE_POR_ALICUOTA >= FER) * (IMPORTE_POR_ALICUOTA))");
//			jep.parseExpression("TRAMO != \"SIN_TRAMO\"");
			System.out.println("Error: " + jep.getErrorInfo());
			Double valor = jep.getValue();
			System.out.println("Nan? " + valor.isNaN());
			System.out.println("Valor: " + jep.getValue());
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	static class Variable{
		public enum Tipo{MAXIMO, MINIMO, PROMEDIO, CANTIDAD, IGUAL}
		private String nombre;
		private String expression;
		private Double valor;
		private Tipo tipo;
		
		public Tipo getTipo() {
			return tipo;
		}
		public void setTipo(Tipo tipo) {
			this.tipo = tipo;
		}
		public Double getValor() {
			return valor;
		}
		public void setValor(Double valor) {
			this.valor = valor;
		}
		public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		public String getExpression() {
			return expression;
		}
		public void setExpression(String expression) {
			this.expression = expression;
		}
	}

}
