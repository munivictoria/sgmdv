package com.trascender.framework.recurso.persistent.validacionDinamica;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * Representa una validacion contra una entidad o atributo determinada.
 * @author jsantacruz
 */
@Entity
@Table(name="COMPONENTE_VALIDACION")
public class ComponenteValidacion implements Serializable, Cloneable{
	
	public static final long serialVersionUID = 4858826249253583399L;
	
	@Transient
	private Map<String, Object> mapaParametros = new HashMap<String, Object>();

	/**
	 * Representa el objeto sobre el cual se van a hacer las validaciones.
	 */
	@Transient
	private Object objeto;
	
	@ManyToOne
	@JoinColumn(name="ID_VALIDACION_DINAMICA")
	private ValidacionDinamica validacionDinamica;
	
	@Id
	@GeneratedValue(generator="GEN_ID_COMPONENTE_V", strategy= GenerationType.SEQUENCE)
	@SequenceGenerator(allocationSize=1, name="GEN_ID_COMPONENTE_V", sequenceName="GEN_ID_COMPONENTE_V")
	@Column(name="ID_COMPONENTE_VALIDACION")
	private Long idComponenteValidacion = -1l;
	
	private String atributo;
	
	private String descripcion;
	
	@Enumerated(EnumType.STRING)
	@Column(name="TIPO_VALIDACION")
	private TipoValidacion tipoValidacion = TipoValidacion.AND;
	
	@Enumerated(EnumType.STRING)
	private Operadores operador = Operadores.IGUAL;
	
	/**
	 * Por convencion se debe asumir que si el valor el nulo 
	 * se debe acceder a travez del getter del atributo.
	 */
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="ID_VALOR_VALIDACION")
	private ValorValidacion valor;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="ID_MENSAJE_ERROR_VD")
	private MensajeErrorVD mensajeError;
	
	@Enumerated(EnumType.STRING)
	private AlcanceValidacion alcance;
	
	private String defaultValue = "";
	
	@OneToMany(orphanRemoval=true, fetch=FetchType.EAGER)
	@JoinTable(name="RELA_COMPONENTE_VALIDACION", joinColumns=@JoinColumn(name="ID_COMPONENTE_VALIDACION"), 
				inverseJoinColumns=@JoinColumn(name="ID_COMPONENTE_VAL_RELA"))
	private Set<ComponenteValidacion> listaComponenteValidacion = new HashSet<ComponenteValidacion>();
	
	private Object obtenerValorFromObjeto(String pGetter) throws Exception{
			if(	this.atributo.indexOf(".") > -1){
				Object locNodo = null;
				String[] listaJoins = this.atributo.split("\\.");
				for(int locPosicion = 0; locPosicion < listaJoins.length - 1; locPosicion++){
					locNodo = ((locNodo != null)?locNodo:this.objeto).getClass().getDeclaredMethod("get"+this.capitalizeAtribute(listaJoins[locPosicion]),null).invoke(((locNodo != null)?locNodo: this.objeto), null);
				}
				
				return locNodo.getClass().getDeclaredMethod("get"+this.capitalizeAtribute(listaJoins[listaJoins.length -1]),null).invoke(locNodo, null);
			}
			
			return this.objeto.getClass().getDeclaredMethod(pGetter).invoke(objeto,null);
		}
		
	private Object obtenerValorFromObjeto() throws Exception{
		if(this.requiereValorDelObjeto()){
			try{
				if(	this.atributo.indexOf(".") > -1){
					Object locNodo = null;
					String[] listaJoins = this.atributo.split("\\.");
					for(int locPosicion = 0; locPosicion < listaJoins.length - 1; locPosicion++){
						locNodo = ((locNodo != null)?locNodo:this.objeto).getClass().getDeclaredMethod("get"+this.capitalizeAtribute(listaJoins[locPosicion]),null).invoke(((locNodo != null)?locNodo: this.objeto), null);
					}
					
					return locNodo.getClass().getDeclaredMethod("get"+this.capitalizeAtribute(listaJoins[listaJoins.length -1]),null).invoke(locNodo, null);
				}
				
				return this.objeto.getClass().getDeclaredMethod("get"+this.capitalizeAtribute(atributo),null).invoke(objeto, null);
			}catch (NoSuchMethodException locE) {
				return this.objeto.getClass().getDeclaredMethod("is"+this.capitalizeAtribute(atributo),null).invoke(objeto, null);
			}catch (Exception locE) {
				locE.printStackTrace();
				throw new Exception("El nombre del atributo para la validacion no es valido.");
			}
			}else{
				return this.getValor().getValor();
			}
	}
	
	private Object obtenerValorFromAtributo() throws Exception{
		try{
			if(	this.atributo.indexOf(".") > -1){
				Object locNodo = null;
				String[] listaJoins = this.atributo.split("\\.");
				for(int locPosicion = 0; locPosicion < listaJoins.length - 1; locPosicion++){
					try{
						locNodo = ((locNodo != null)?locNodo:this.objeto).getClass().getDeclaredMethod("get"+this.capitalizeAtribute(listaJoins[locPosicion]),null).invoke(((locNodo != null)?locNodo: this.objeto), null);
					}catch (NoSuchMethodException locE) {
						locNodo = ((locNodo != null)?locNodo:this.objeto).getClass().getDeclaredMethod("is"+this.capitalizeAtribute(listaJoins[locPosicion]),null).invoke(((locNodo != null)?locNodo: this.objeto), null);
					}catch (Exception locE) {
					}
				}
				
				return locNodo.getClass().getDeclaredMethod("get"+this.capitalizeAtribute(listaJoins[listaJoins.length -1]),null).invoke(locNodo, null);
			}
			return this.objeto.getClass().getDeclaredMethod("get"+this.capitalizeAtribute(atributo),null).invoke(objeto, null);
		}catch (NoSuchMethodException locE) {
			return this.objeto.getClass().getDeclaredMethod("is"+this.capitalizeAtribute(atributo),null).invoke(objeto, null);
		}catch (Exception locE) {
			locE.printStackTrace();
			throw new Exception("El nombre del atributo para la validacion no es valido.");
		}
	}
	
	/**
	 * Por convencion se debe asumir que si el "valor" es nulo 
	 * se debe acceder a travez del getter del atributo.
	 */
	private boolean requiereValorDelObjeto() {
		if(this.valor == null){
			return true;
		}else{
			return false;
		}
			
	}

	/**
	 *El alcance es si necesita hacer una consulta a la base para obtener un resultado o no
	 * <li>INTEGRIDAD: EVALUAN LA INTEGRIDAD DEL OBJETO, SIEMPRE SE EJECUTAN PRIMERO</li>
	 * <li>VALIDACION_DB: EVALUA LAS CONDICIONES CONTRA LA BASE DE DATOS </li>
	 */
	public enum AlcanceValidacion{
		INTEGRIDAD, 
		VALIDACION_DB 
	}
	/**
	 * Determina la conjuncion entre otras validaciones.<br>
	 * <tt>Tipos: <li>OR</li> </tt>
	 * <li>AND</li> 
	 * <li>UNICO</li>
	 * <li>NO_NULO</li>
	 */
	public enum TipoValidacion{
		OR,
		AND,
		UNICO,
		NO_NULO
	}
	
	/**
	 * <li>MAYOR
		<li>MENOR
		<li>IGUAL
		<li>MAYOR_IGUAL
		<li>MENOR_IGUAL
		<li>DISTINTO
	 */
	public enum Operadores{
		MAYOR,
		MENOR,
		IGUAL,
		MAYOR_IGUAL,
		MENOR_IGUAL,
		DISTINTO;
	}
	
	
	public void addComponente(ComponenteValidacion pComponente){
		this.listaComponenteValidacion.add(pComponente);
	}
	
	public void addToMapaParametros(String pKey, Object pValor){
		this.mapaParametros.put(pKey, pValor);
	}
	
	@Transient
	public String getOperadorComoSimbolo(Operadores pOperador){
		switch (pOperador) {
			case MAYOR: return ">";
			case MENOR: return "<";
			case IGUAL: return "=";
			case MAYOR_IGUAL: return ">=";
			case MENOR_IGUAL: return "<=";
			case DISTINTO: return "!=";
			default: return null;
		}
	}
	
	


	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String pDefaultValue) {
		defaultValue = pDefaultValue;
	}

	public MensajeErrorVD getMensajeError() {
		return mensajeError;
	}

	public void setMensajeError(MensajeErrorVD pMensajeError) {
		mensajeError = pMensajeError;
	}

	public AlcanceValidacion getAlcance() {
		return alcance;
	}

	public void setAlcance(AlcanceValidacion pAlcance) {
		alcance = pAlcance;
	}

	public void setObjeto(Object pObject){
		this.objeto = pObject;
	}
	public Long getIdComponenteValidacion() {
		return idComponenteValidacion;
	}

	public void setIdComponenteValidacion(Long pIdComponenteValidacion) {
		idComponenteValidacion = pIdComponenteValidacion;
	}

	public String getAtributo() {
		return atributo;
	}

	public void setAtributo(String pAtributo) {
		atributo = pAtributo;
	}

	public TipoValidacion getTipoValidacion() {
		return tipoValidacion;
	}

	public void setTipoValidacion(TipoValidacion pTipoValidacion) {
		if(pTipoValidacion.equals(TipoValidacion.UNICO)){
			this.operador = Operadores.IGUAL;
		}
		tipoValidacion = pTipoValidacion;
	}

	public Operadores getOperador() {
		return operador;
	}

	public void setOperador(Operadores pOperador) {
		operador = pOperador;
	}
	
	public ValorValidacion getValor() {
		return valor;
	}

	public void setValor(ValorValidacion pValor) {
		valor = pValor;
	}

	public Map<String, Object> getMapaParametros() {
		return mapaParametros;
	}

	public void setMapaParametros(Map<String, Object> pMapaParametros) {
		mapaParametros = pMapaParametros;
	}

	public Set<ComponenteValidacion> getListaComponenteValidacion() {
		return listaComponenteValidacion;
	}

	public void setListaComponenteValidacion(
			Set<ComponenteValidacion> pListaComponenteValidacion) {
		listaComponenteValidacion = pListaComponenteValidacion;
	}
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String pDescripcion) {
		descripcion = pDescripcion;
	}
	
	public ValidacionDinamica getValidacionDinamica() {
		return validacionDinamica;
	}

	public void setValidacionDinamica(ValidacionDinamica pValidacionDinamica) {
		validacionDinamica = pValidacionDinamica;
	}

	@Transient
	public String getValidacion() throws Exception{
		this.mapaParametros.clear();
		StringBuilder locRetorno = new StringBuilder("(");
		
		locRetorno.append(this.getValidacionSimple());
		
		if(!this.listaComponenteValidacion.isEmpty()){
			for(ComponenteValidacion cadaComponente : this.listaComponenteValidacion){
				locRetorno.append(cadaComponente.getTipoValidacion().toString() +" " +cadaComponente.getValidacionSimple());
				this.mapaParametros.putAll(cadaComponente.getMapaParametros());
			}
		}
		
		locRetorno.append(")");
		
		return locRetorno.toString();
	}
	
	/**
	 * Devuelve la consulta-validacion en si ej e.id = 1;
	 * @return
	 * @throws Exception
	 */
	@Transient
	public String getValidacionSimple() throws Exception{
		StringBuilder locRetorno = new StringBuilder();
//			locRetorno.append(this.tipoValidacion.toString() + " ");
			locRetorno.append(("e." + this.atributo + " "));
			if(this.tipoValidacion.equals(TipoValidacion.NO_NULO)){
				locRetorno.append("IS NULL ");
				
				return locRetorno.toString();
			}
			locRetorno.append(this.getOperadorComoSimbolo(this.operador) + " ");
			locRetorno.append(":loc"+this.capitalizeAtribute(this.getFinalNodeFromAttr(this.atributo)) + " ");
			
			if(this.tipoValidacion.equals(TipoValidacion.UNICO)){
				locRetorno.append("AND e.id" + this.objeto.getClass().getSimpleName() + " != " + this.obtenerValorFromObjeto("getId"+ this.objeto.getClass().getSimpleName()));
			}
		this.addToMapaParametros("loc"+this.capitalizeAtribute(this.getFinalNodeFromAttr(this.atributo)), this.obtenerValorFromObjeto());
		
		return locRetorno.toString();
	}
	
	@Override
	public ComponenteValidacion clone() throws CloneNotSupportedException {
		
		ComponenteValidacion clon = (ComponenteValidacion) super.clone();
		clon.setIdComponenteValidacion(-1l);
		clon.getMapaParametros().clear();
		clon.getListaComponenteValidacion().clear();
		
		return clon;
	}
	
	/**
	 * Hace una validacion en modo "offline"
	 * es exclesivo para las validaciones de primer nivel
	 * las cuales no requieren consultas a la base
	 * por ejemplo: no nulo, o algun atributo sea mayor que tal cosa.
	 * @throws Exception
	 */
	public void validar() throws Exception{
		if(this.getAlcance().equals(AlcanceValidacion.INTEGRIDAD)){
			System.out.println();
			if(this.validarSegunOperador(this.obtenerValorFromAtributo())){
				this.mensajeError.throwException();
			}else{
				System.out.println("Andubo todo bien.");
			}
		}
	}
	
	private boolean validarSegunOperador(Object pObject) throws Exception {
		System.out.println(pObject);
		System.out.println(this.operador);
		if(this.tipoValidacion.equals(TipoValidacion.NO_NULO)){
			if(this.obtenerValorFromAtributo() == null){
				return true;
			}else{
				return false;
			}
		}
		
		return this.valor.comparar(pObject, this.operador);
		
	}

	private double isNumeric(Object pObject) {
		if(pObject.getClass().isPrimitive()){
			return Double.valueOf(pObject.toString());
		}
		return 0.0d;
	}
	
	private String getFinalNodeFromAttr(String pAtributo){
		System.out.println(pAtributo);
		if(pAtributo.indexOf(".") == -1){
			return pAtributo;
		}
		
		String[] locStrings = pAtributo.split("\\.");
		return locStrings[locStrings.length - 1];
	}

	/**
	 * Prepara un atributo para una consulta<br>
	 * Ej. atributo sexo devolveria Sexo --> "get"+Sexo = getSexo
	 * @param pCadena
	 * @return
	 */
	public String capitalizeAtribute(String pCadena){
		if(Character.isLetter(pCadena.charAt(0))){
			pCadena = pCadena.replaceFirst(String.valueOf(pCadena.charAt(0)), String.valueOf(Character.toUpperCase(pCadena.charAt(0))));
		}
		
		return pCadena;
	}
}
