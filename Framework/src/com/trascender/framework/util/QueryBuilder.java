package com.trascender.framework.util;


/**
 * Construye la cadena de consultas para la base de datos (solo para hql)
 * @author Mariano Lusardi
 *
 */
public class QueryBuilder {
	private StringBuilder consulta;
	private boolean primera=true;
	private String conector = "and";
	
	
	public QueryBuilder(String pConsulta){
		this.consulta = new StringBuilder(pConsulta);
	}
	
	/**
	 * agrega una condición a la consulta
	 * @param pCondicion condición a agregar
	 */
	public void add(String pCondicion){
		if (primera){
			this.consulta.append(" where "+pCondicion);
			this.primera=false;
		}
		else{
			this.consulta.append(" "+this.conector+" "+pCondicion);
		}
	}


	/**
	 * @return el conector que se utilizará para el próximo parámetro
	 */
	public String getConector() {
		return conector;
	}
	
	/**
	 * Setea el conector para el próximo parámetro 
	 * @param pConector conector asociado. Puede ser <code>and</code> u <code>or</code>
	 */
	public void setConector(String pConector) {
		conector = pConector;
	}

	public String getConsulta() {
		System.out.println(this.consulta.toString());
		return this.consulta.toString().trim();
	}
	
	@Override
	public String toString() {
		return this.getConsulta();
	}
}
