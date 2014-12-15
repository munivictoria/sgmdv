package com.trascender.framework.util;

import java.text.DecimalFormat;



/**
 * Permite obtener en letras cualquier número positivo hasta 999.999.999,99 centavos.
 * Debe pasarse como parámetro el número sin separador de miles y con la coma decimal si se requiere. ejemplos: 15000,56; 15000
 * @author Maximiliano Fontanini
 * @version 1.1
 * 
 */
public class ObtenerNumeroEnLetras{
	
	
	/**
	 * Podría agregarse a la clase Util ya que ésto también es una utilidad más
	 * pero el código va a ser más fácil de mantener teniendo esta clase por separado.
	 */
	
	private static ObtenerNumeroEnLetras instance;
	
	public static ObtenerNumeroEnLetras getInstance(){
		if(instance == null){
			instance = new ObtenerNumeroEnLetras();
		}
		
		return instance;
	}
	
	private static String[] listaUnidades = {"","Un","Dos","Tres","Cuatro","Cinco","Seis","Siete","Ocho","Nueve"};
	private static String[] listaDiezAQuince = {"","Diez","Once","Doce","Trece","Catorce","Quince"};
	private static String[] listaDecenaConcatenado = {"","Dieci","Veinti","Treinta","Cuarenta","Cincuenta","Sesenta","Setenta","Ochenta","Noventa"};
	private static String[] listaCentena = {"","Cien","Doscientos","Trescientos","Cuatrocientos","Quinientos","Seiscientos","Setecientos","Ochocientos","Novecientos"};
	
	private String numeroIngresado = "";
	private Integer puntoDecimal=null;

	public String getNumeroIngresado(){
		return numeroIngresado;
	}
	
	public void setNumeroIngresado(String pNumeroIngresado){
		pNumeroIngresado = DecimalFormat.getInstance().format(Double.valueOf(pNumeroIngresado));
		numeroIngresado = "";
		for(int i = 0; i < pNumeroIngresado.length(); i++){
			if(pNumeroIngresado.charAt(i) != '.'){
				numeroIngresado+=pNumeroIngresado.charAt(i);
			}
		}
	}
	
	public ObtenerNumeroEnLetras() {
	}
	
	public String getNumeroEnLetras(java.lang.String pNumeroIngresado){
		this.setNumeroIngresado(pNumeroIngresado);
		String locNumeroEnLetras = "";
		
		if(this.getParteEntera().length() == 1){
			locNumeroEnLetras = this.getUnidad(this.getParteEntera());
		}
		
		if(this.getParteEntera().length() == 2){
			locNumeroEnLetras = this.getDecena(this.getParteEntera());
		}
		
		if(this.getParteEntera().length() == 3){
			locNumeroEnLetras = this.getCentena(this.getParteEntera());
		}
		
		if(this.getParteEntera().length() >= 4 && this.getParteEntera().length() <7){
			locNumeroEnLetras = this.getMiles(this.getParteEntera());
		}
		
		if(this.getParteEntera().length() >=7){
			locNumeroEnLetras = this.getMillones(this.getParteEntera());
		}
		
		if(locNumeroEnLetras.endsWith("Un")){
			locNumeroEnLetras=locNumeroEnLetras+"o";
		}
		
		if(locNumeroEnLetras.equals("")){
			locNumeroEnLetras="Cero";
		}
		locNumeroEnLetras=locNumeroEnLetras.trim()+this.getParteDecimal()+".-";
		return locNumeroEnLetras;
	}
	
	private String getParteEntera() {
		this.puntoDecimal = null;
		if(this.getNumeroIngresado().contains(",") || this.getNumeroIngresado().contains(".")){
			for(int i = 0; i < this.getNumeroIngresado().length();i++){
				if(this.getNumeroIngresado().charAt(i) == ',' || this.getNumeroIngresado().charAt(i) == '.'){
					this.puntoDecimal = i;
					break;
				}
			}
			
			if(this.puntoDecimal != null){
				return this.getNumeroIngresado().substring(0, this.puntoDecimal);
			}
		}
		return this.getNumeroIngresado();
	}
	

	private String getParteDecimal(){
		String locStringRetorno = "";
		if(this.puntoDecimal != null && this.puntoDecimal > 0){
			String locParteDecimal = this.getNumeroIngresado().substring(this.puntoDecimal+1,this.getNumeroIngresado().length());
			switch(locParteDecimal.length()){
				case 0: locStringRetorno = "";
				case 1: locStringRetorno = this.getUnidad(locParteDecimal);break;
				case 2: locStringRetorno = this.getDecena(locParteDecimal);break;
				//realiza la misma operacion que en 2 en caso de que sean 3 decimales
				case 3: locStringRetorno = this.getDecena(locParteDecimal);break;
				
				default: locStringRetorno = this.getDecena(locParteDecimal);break;
			}
			
			if(!locStringRetorno.equals("")){
				locStringRetorno=" con " +locStringRetorno+ " centavo" + ((!locStringRetorno.equals("Un"))?"s":"");
			}
		}
		return locStringRetorno;
	}

	//métodos para obtener el numero en letras
	private String getUnidad(String pUnidad){
		return listaUnidades[Integer.valueOf(pUnidad)];
	}

	private String getDecena(String pDecena){
		String locNumeroEnLetras="";
		if(pDecena.startsWith("0")){
			locNumeroEnLetras=listaUnidades[Integer.valueOf(pDecena.substring(1, 2))];
		}
		else if(pDecena.startsWith("1")){
			if(pDecena.charAt(1) >= '0' && pDecena.charAt(1) <= '5'){
				locNumeroEnLetras=this.capitalize(listaDiezAQuince[Integer.valueOf(pDecena.substring(1,2))+1]);
			}
			else{
				locNumeroEnLetras=this.capitalize(listaDecenaConcatenado[1]+listaUnidades[Integer.valueOf(pDecena.substring(1, 2))]);
			}
		}
		
		if(locNumeroEnLetras.equals("") && (!pDecena.equals("0") && !pDecena.equals(""))){
			if(pDecena.startsWith("2")){
				if(pDecena.charAt(1) == '0'){
					locNumeroEnLetras="Veinte";
				}
				else{
					locNumeroEnLetras=this.capitalize(listaDecenaConcatenado[2]+listaUnidades[Integer.valueOf(pDecena.substring(1,2))]);
				}
			}
			else{
				locNumeroEnLetras=listaDecenaConcatenado[Integer.valueOf(pDecena.substring(0, 1))];
				if(!(pDecena.charAt(1) == '0')){
					locNumeroEnLetras+=" y "+listaUnidades[Integer.valueOf(pDecena.substring(1,2))];
				}
			}
			
			if(!locNumeroEnLetras.contains("y")){
				locNumeroEnLetras=this.capitalize(locNumeroEnLetras);
			}
			
			if(locNumeroEnLetras.endsWith("un")){
				locNumeroEnLetras=locNumeroEnLetras+"o";
			}
		}
		
		return locNumeroEnLetras;
	}
	
	private String capitalize(String pCadena) {
		if(pCadena != null){
			pCadena = pCadena.toLowerCase();
			char ch;
			char prevCh;
			int i;
			prevCh = '.';
			String salida = "";
			for ( i = 0; i < pCadena.length(); i++ ) {
				ch = pCadena.charAt(i);
				if ( Character.isLetter(ch) && ! Character.isLetter(prevCh) )
					salida += Character.toUpperCase(ch);
				else
					salida += ch;
				prevCh = ch;
			}
			return salida;
		}
		return null;
	}

	private String getCentena(String pCentena) {
		String locNumeroEnLetras="";
		if(pCentena.substring(0, 3).equals("100")){
			locNumeroEnLetras = "Cien";
		}
		else{
			if(pCentena.charAt(0) == '1'){
				locNumeroEnLetras="Ciento";
			}
			else{
				locNumeroEnLetras=listaCentena[Integer.valueOf(pCentena.substring(0, 1))];
			}
			
			if(!(pCentena.charAt(1) == '0')){
					locNumeroEnLetras+=((pCentena.charAt(0) != '0')?" ":"")+this.getDecena(pCentena.substring(1));
			}
			else{
				locNumeroEnLetras+=((pCentena.charAt(0) != '0')?" ":"")+this.getUnidad(pCentena.substring(2,3));
			}
		}
		
		if(locNumeroEnLetras.contains("Ciento")){
			this.capitalize(locNumeroEnLetras);
		}
		return locNumeroEnLetras;
	}
	
	private String getMiles(String pMiles){
		String locNumeroEnLetras="";
		int locContinuacionDesde=0;
		if(pMiles.length() == 4){
			locNumeroEnLetras=" "+listaUnidades[Integer.valueOf(pMiles.substring(0, 1))];
			locContinuacionDesde=1;
		}
		else if(pMiles.length() == 5){
			locNumeroEnLetras=" "+this.getDecena(pMiles.substring(0));
			locContinuacionDesde=2;
		}
		else{
			locNumeroEnLetras=" "+this.getCentena(pMiles.substring(0));
			locContinuacionDesde=3;
		}
		locNumeroEnLetras+=((!pMiles.startsWith("0"))?" Mil ":"")+this.getCentena(pMiles.substring(locContinuacionDesde));
		
		return locNumeroEnLetras;
	}
	
	private String getMillones(String pMillones){
		String locNumeroEnLetras="";
		int locContinuacionDesde=0;
		
		if(pMillones.length() == 7){
			locNumeroEnLetras=((!pMillones.startsWith("1"))?listaUnidades[Integer.valueOf(pMillones.substring(0, 1))]:" Un Millón ");
			locContinuacionDesde=1;
		}
		else if(pMillones.length() == 8){
			locNumeroEnLetras=this.getDecena(pMillones.substring(0));
			locContinuacionDesde=2;
		}
		else{
			locNumeroEnLetras=this.getCentena(pMillones.substring(0));
			locContinuacionDesde=3;
		}
		//" Millones: "
		locNumeroEnLetras+=((!locNumeroEnLetras.contains("Un Millón"))?" Millones":"")+this.getMiles(pMillones.substring(locContinuacionDesde));
		return locNumeroEnLetras;
	}
}
