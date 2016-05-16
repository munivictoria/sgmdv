package com.trascender.caja.gui.impresion;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.trascender.caja.gui.main.CajaGUI;
import com.trascender.contabilidad.recurso.persistent.Caja;
import com.trascender.contabilidad.recurso.persistent.IngresoVario;
import com.trascender.contabilidad.recurso.persistent.PagoTicket;
import com.trascender.contabilidad.recurso.persistent.TicketCaja;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.habilitaciones.recurso.persistent.DocHabilitanteEspecializado;
import com.trascender.habilitaciones.recurso.persistent.cementerio.DocumentoCementerio;
import com.trascender.habilitaciones.recurso.persistent.osp.DocumentoOSP;
import com.trascender.habilitaciones.recurso.persistent.pfo.DocumentoPlanObra;
import com.trascender.habilitaciones.recurso.persistent.shps.DocumentoSHPS;
import com.trascender.habilitaciones.recurso.persistent.tgi.DocumentoTGI;
import com.trascender.saic.recurso.interfaces.Pagable;
import com.trascender.saic.recurso.persistent.LiquidacionTasa;

public abstract class Imprimible {
	protected TicketCaja ticketCaja;
	protected Persona persona;
	protected Caja caja;
	protected boolean valido=false;

	public Imprimible(TicketCaja pTicketCaja, Persona pPersona){
		this.ticketCaja = pTicketCaja;
		this.persona = pPersona;
		this.caja = CajaGUI.getInstance().getCaja();
	}

	private NumberFormat getNumberFormat(){
		NumberFormat locNumberFormat = NumberFormat.getInstance();
		locNumberFormat.setMinimumIntegerDigits(1);
		locNumberFormat.setMinimumFractionDigits(2);
		locNumberFormat.setMaximumFractionDigits(2);
		return locNumberFormat;	
	}

	private DateFormat getDateFormat(){
		return DateFormat.getDateTimeInstance(DateFormat.SHORT,DateFormat.SHORT);
	}

	public boolean isValido() {
		return valido;
	}

	public void setValido(boolean valido) {
		this.valido = valido;
	}

	//Editado por José //Y que editaste??
	public String getTextoImpresion(){
		DateFormat locDateFormat = this.getDateFormat();
		NumberFormat locNumberFormat = this.getNumberFormat();

		Pagable locDeuda = this.ticketCaja.getDetalles().iterator().next().getDeuda();
		//Con un solo detalle alcanza por ahora, lo unico que necesitamos es el numero de parcela.

		TicketCaja locTicket = this.ticketCaja;

		String resultado = "\n ";

		resultado+="\n ";
		resultado+="\n ";

		resultado= "\n MUNICIPALIDAD DE VICTORIA \n";
		resultado+=" F.Ezpeleta y Sarmiento-Victoria-E.R.\n";
		resultado+="CUIT: 30-9904126-0\n";
		resultado+=" \n";
		resultado+= this.persona.toString();
		resultado+=" \n";
		resultado+="\nNro. Ticket: "+ this.ticketCaja.getNumero()+"\n";
		resultado+="Nro. Caja:     "+ this.ticketCaja.getCaja().getNumero()+"\n";
		resultado+="Cajero:        "+ AppManager.getInstance().getUsuario().getNombrePersonaFisica()+"\n";
		resultado+=" \n";		

		if (locDeuda instanceof LiquidacionTasa){
			LiquidacionTasa locLiquidacionTasa =  (LiquidacionTasa)locDeuda;
			if (locLiquidacionTasa.getDocGeneradorDeuda().getObligacion().getDocumentoEspecializado() instanceof DocumentoSHPS){						//SHPS
				resultado+="Concepto: Pago Tasa Higiene y Seguridad \n";
			} else if (locLiquidacionTasa.getDocGeneradorDeuda().getObligacion().getDocumentoEspecializado() instanceof DocumentoCementerio) {
				resultado+="Concepto: Pago Cementerio \n";
			}else{
				resultado+="Concepto: Pago Servicios Municipales \n";
			}

			//			resultado+="Nº Registro Deuda:        " + locLiquidacionTasa.getIdRegistroDeuda() + "\n";
			resultado+=" \n";
			resultado+="Fecha de Vencimiento: " + new SimpleDateFormat("dd/MM/yyyy").format(locLiquidacionTasa.getFechaVencimiento()) + " \n";
			DocHabilitanteEspecializado locDocumento = locLiquidacionTasa.getDocGeneradorDeuda().getObligacion().getDocumentoEspecializado();
			if (locDocumento instanceof DocumentoOSP || locDocumento instanceof DocumentoTGI){ 						//OSP
				resultado+="Nro. Cuenta:       "+locDocumento.getParcela().getNomenclaturaCatastral().getNroParcela()+ " \n";
			}
			if (locDocumento instanceof DocumentoSHPS){						//SHPS
				DocumentoSHPS locDocumentoSHPS = (DocumentoSHPS) locDocumento;
				resultado+="Nro. Inscripción:       "+locDocumentoSHPS.getNumeroInscripcion() +" \n";
			}
			if (locDocumento instanceof DocumentoCementerio){						//SHPS
				DocumentoCementerio locDocumentoCementerio = (DocumentoCementerio) locDocumento;
				resultado+="Nro. Cuenta:       "+locDocumentoCementerio.getNumeroCuenta() +" \n";
			}
			if (locLiquidacionTasa.getDocGeneradorDeuda().getObligacion().getDocumentoEspecializado() instanceof DocumentoPlanObra){ 					//PFO
				DocumentoPlanObra locDocumentoPlanObra = (DocumentoPlanObra)locLiquidacionTasa.getDocGeneradorDeuda().getObligacion().getDocumentoEspecializado();
				resultado+="Nro. Obra:       "+locDocumentoPlanObra.getObra().getNumeroObra() +" \n";
				resultado+="Nro. Inmueble:       "+locDocumentoPlanObra.getParcela().getNroRegistro() +" \n";
			}

			resultado+="Forma de Pago: "+locLiquidacionTasa.getCuotaLiquidacion().getPeriodo().getCalendario().getNombre() +" \n";
			if (locLiquidacionTasa.getDocGeneradorDeuda().getObligacion().getDocumentoEspecializado() instanceof DocumentoPlanObra){					//PFO
				resultado+="Período: "+ locLiquidacionTasa.getCuotaLiquidada() +" \n";
			}else{
				resultado+="Período: "+ locLiquidacionTasa.getCuotaLiquidacion().getPeriodo() +" \n";
			}
		} else if (locDeuda instanceof IngresoVario){ 
			resultado+=acortarString("Concepto: "+locDeuda.getNombre()+" \n", 40, 3) + "\n"; //Administrativo
		} else {
			resultado+=locDeuda.getNombre()+"\n";
		}

		resultado+=" \n";
		Double interes = this.ticketCaja.getImporteInteres();
		if (interes > 0D) {
			resultado+="Importe: $     "+locNumberFormat.format(this.ticketCaja.getImporteTotal() - interes)+"\n";
			resultado+="Interes: $     "+locNumberFormat.format(interes)+"\n";
		}
		resultado+="Importe total: $     "+locNumberFormat.format(this.ticketCaja.getImporteTotal())+"\n";
		resultado+="Pagos:\n";
		for (PagoTicket cadaPagoTicket : this.ticketCaja.getListaPagosTicket()){
			resultado += cadaPagoTicket.getDescripcion() + ":    $ " + locNumberFormat.format(cadaPagoTicket.getMonto())+"\n";
		}
		resultado+="\n";
		resultado+="Fecha Pago:        "+ locDateFormat.format(Calendar.getInstance().getTime())+"\n";
		resultado+="\n ";
		resultado+="__________________________________"+" \n";
		resultado+="Este comprobante es valido \n";
		resultado+=" solo con el sello de caja\n";

		return resultado;
	}
	
	public static void main(String... args) {
		System.out.println(acortarString("Concepto: CEMENTERIO - Por unidad urnario", 
				40, 3));
		System.out.println(acortarString("CEMENTERIO - Por cada panteón y bóveda de los restantes contribuyentes (NO obras sociales, mutuales y/o asociaciones civiles)", 
				40, 3));
		System.out.println(acortarString("Concepto: CEMENTERIO - Por urnario", 
				40, 3));
		
	}
	
	/**
	 * @param cantidadCaractares El maximo de caracter por linea que puede tener el string
	 * @param cantidadLineas el maximo de lineas que puede tener el string
	 * @return
	 */
	private static String acortarString(String cadena, int cantidadCaractares, int cantidadLineas) {
		cadena = cadena.trim();
		if (cantidadCaractares >= cadena.length())
			return cadena;
		String resultado = "";
		for (int i = 0 ; i < cantidadLineas && !cadena.isEmpty() ; i++) {
			int posicion = cadena.lastIndexOf(" ", cantidadCaractares);
			if (posicion == -1) posicion = cadena.length();
			resultado += cadena.substring(0, posicion) + "\n";
			cadena = cadena.substring(posicion).trim();
		}
		if (!cadena.trim().isEmpty())
			resultado = resultado.substring(0, resultado.length() - 3) + "...";
		return resultado;
	}
	
	/**
	 * Texto a imprimir con leyenda de Anulacion de ticket
	 * @return
	 */
	public String getTextoImpresionAnulacion(){
		DateFormat locDateFormat = this.getDateFormat();
		NumberFormat locNumberFormat = this.getNumberFormat();

		Pagable locDeuda = this.ticketCaja.getDetalles().iterator().next().getDeuda();

		String resultado = "\n ";
		resultado+="\n ";
		resultado+="\n ";

		resultado= "\n MUNICIPALIDAD DE VICTORIA \n";
		resultado+=" F.Ezpeleta y Sarmiento-Victoria-E.R.\n";
		resultado+="CUIT: 30-9904126-0\n";
		resultado+=" \n";
		resultado+= this.persona.toString();
		resultado+=" \n";
		resultado+="\nNro. Ticket: "+ this.ticketCaja.getNumero()+"\n";
		resultado+="Nro. Caja:     "+ this.ticketCaja.getCaja().getNumero()+"\n";
		resultado+="Cajero:        "+ AppManager.getInstance().getUsuario().getNombrePersonaFisica()+"\n";

		resultado+=" \n";		
		resultado+=" \n";		
		resultado+="::::::::::::: Ticket Anulado :::::::::::::"+"\n";
		resultado+=" \n";
		resultado+=" \n";		

		if (locDeuda instanceof LiquidacionTasa){
			LiquidacionTasa locLiquidacionTasa =  (LiquidacionTasa)locDeuda;
			if (locLiquidacionTasa.getDocGeneradorDeuda().getObligacion().getDocumentoEspecializado() instanceof DocumentoSHPS){						//SHPS
				resultado+="Concepto: Pago Tasa Higiene y Seguridad \n";
			} else if (locLiquidacionTasa.getDocGeneradorDeuda().getObligacion().getDocumentoEspecializado() instanceof DocumentoCementerio) {
				resultado+="Concepto: Pago Cementerio \n";
			}else{
				resultado+="Concepto: Pago Servicios Municipales \n";
			}

			//			resultado+="Nº Registro Deuda:        " + locLiquidacionTasa.getIdRegistroDeuda() + "\n";
			resultado+=" \n";
			resultado+="Fecha de Vencimiento: " + new SimpleDateFormat("dd/MM/yyyy").format(locLiquidacionTasa.getFechaVencimiento()) + " \n";
			DocHabilitanteEspecializado locDocumento = locLiquidacionTasa.getDocGeneradorDeuda().getObligacion().getDocumentoEspecializado();
			if (locDocumento instanceof DocumentoOSP || locDocumento instanceof DocumentoTGI){ 						//OSP
				resultado+="Nro. Cuenta:       "+locDocumento.getParcela().getNomenclaturaCatastral().getNroParcela()+ " \n";
			}
			if (locDocumento instanceof DocumentoSHPS){						//SHPS
				DocumentoSHPS locDocumentoSHPS = (DocumentoSHPS) locDocumento;
				resultado+="Nro. Inscripción:       "+locDocumentoSHPS.getNumeroInscripcion() +" \n";
			}
			if (locDocumento instanceof DocumentoCementerio){						//SHPS
				DocumentoCementerio locDocumentoCementerio = (DocumentoCementerio) locDocumento;
				resultado+="Nro. Cuenta:       "+locDocumentoCementerio.getNumeroCuenta() +" \n";
			}
			if (locLiquidacionTasa.getDocGeneradorDeuda().getObligacion().getDocumentoEspecializado() instanceof DocumentoPlanObra){ 					//PFO
				DocumentoPlanObra locDocumentoPlanObra = (DocumentoPlanObra)locLiquidacionTasa.getDocGeneradorDeuda().getObligacion().getDocumentoEspecializado();
				resultado+="Nro. Obra:       "+locDocumentoPlanObra.getObra().getNumeroObra() +" \n";
				resultado+="Nro. Inmueble:       "+locDocumentoPlanObra.getParcela().getNroRegistro() +" \n";
			}

			resultado+="Forma de Pago: "+locLiquidacionTasa.getCuotaLiquidacion().getPeriodo().getCalendario().getNombre() +" \n";
			if (locLiquidacionTasa.getDocGeneradorDeuda().getObligacion().getDocumentoEspecializado() instanceof DocumentoPlanObra){					//PFO
				resultado+="Período: "+ locLiquidacionTasa.getCuotaLiquidada() +" \n";
			}else{
				resultado+="Período: "+ locLiquidacionTasa.getCuotaLiquidacion().getPeriodo() +" \n";
			}
		}
		else if (locDeuda instanceof IngresoVario){ 
			resultado+=acortarString("Concepto: "+locDeuda.getNombre()+" \n", 40, 3) + "\n"; //Administrativo
		} else {
			resultado+=locDeuda.getNombre()+"\n";
		}

		resultado+=" \n";
		Double interes = this.ticketCaja.getImporteInteres();
		if (interes > 0D) {
			resultado+="Importe: $     "+locNumberFormat.format(this.ticketCaja.getImporteTotal() - interes)+"\n";
			resultado+="Interes: $     "+locNumberFormat.format(interes)+"\n";
		}
		resultado+="Importe total: $     "+locNumberFormat.format(this.ticketCaja.getImporteTotal())+"\n";
		resultado+="Pagos:\n";
		for (PagoTicket cadaPagoTicket : this.ticketCaja.getListaPagosTicket()){
			resultado += cadaPagoTicket.getDescripcion() + ":    $ " + locNumberFormat.format(cadaPagoTicket.getMonto())+"\n";
		}
		resultado+="Fecha Pago:                 "+ locDateFormat.format(this.ticketCaja.getDetalles().iterator().next().getFechaCancelacion()) +"\n";
		resultado+="Fecha Anulación:        "+ locDateFormat.format(Calendar.getInstance().getTime())+"\n";
		resultado+="\n ";
		resultado+="__________________________________"+" \n";
		resultado+="Este comprobante es valido \n";
		resultado+=" solo con el sello de caja\n";

		resultado+="\n ";
		resultado+="\n ";
		resultado+="\n ";

		return resultado;
	}

	/**
	 * Texto a imprimir con leyenda de Anulacion de ticket
	 * @return
	 */
	public String getTextoReimpresion(){/////////////////////////////////////////////////////////////////////
		DateFormat locDateFormat = this.getDateFormat();
		NumberFormat locNumberFormat = this.getNumberFormat();

		Pagable locDeuda = this.ticketCaja.getDetalles().iterator().next().getDeuda();

		String resultado = "\n ";
		resultado+="\n ";
		resultado+="\n ";

		resultado= "\n MUNICIPALIDAD DE VICTORIA \n";
		resultado+=" F.Ezpeleta y Sarmiento-Victoria-E.R.\n";
		resultado+="CUIT: 30-9904126-0\n";
		resultado+=" \n";
		resultado+= this.persona.toString();
		resultado+=" \n";
		resultado+="\nNro. Ticket: "+ this.ticketCaja.getNumero()+"\n";
		resultado+="Nro. Caja:     "+ this.ticketCaja.getCaja().getNumero()+"\n";
		resultado+="Cajero:        "+ AppManager.getInstance().getUsuario().getNombrePersonaFisica()+"\n";
		resultado+=" \n";		
		resultado+=" \n";		
		resultado+="::::::::::::: Ticket Reimpreso :::::::::::::"+"\n";
		resultado+=" \n";
		resultado+=" \n";		

		if (locDeuda instanceof LiquidacionTasa){
			LiquidacionTasa locLiquidacionTasa =  (LiquidacionTasa)locDeuda;
			if (locLiquidacionTasa.getDocGeneradorDeuda().getObligacion().getDocumentoEspecializado() instanceof DocumentoSHPS){						//SHPS
				resultado+="Concepto: Pago Tasa Higiene y Seguridad \n";
			} else if (locLiquidacionTasa.getDocGeneradorDeuda().getObligacion().getDocumentoEspecializado() instanceof DocumentoCementerio) {
				resultado+="Concepto: Pago Cementerio \n";
			}else{
				resultado+="Concepto: Pago Servicios Municipales \n";
			}

			//			resultado+="Nº Registro Deuda:        " + locLiquidacionTasa.getIdRegistroDeuda() + "\n";
			resultado+=" \n";
			resultado+="Fecha de Vencimiento: " + new SimpleDateFormat("dd/MM/yyyy").format(locLiquidacionTasa.getFechaVencimiento()) + " \n";
			DocHabilitanteEspecializado locDocumento = locLiquidacionTasa.getDocGeneradorDeuda().getObligacion().getDocumentoEspecializado();
			if (locDocumento instanceof DocumentoOSP || locDocumento instanceof DocumentoTGI){ 						//OSP
				resultado+="Nro. Cuenta:       "+locDocumento.getParcela().getNomenclaturaCatastral().getNroParcela()+ " \n";
			}
			if (locDocumento instanceof DocumentoSHPS){						//SHPS
				DocumentoSHPS locDocumentoSHPS = (DocumentoSHPS) locDocumento;
				resultado+="Nro. Inscripción:       "+locDocumentoSHPS.getNumeroInscripcion() +" \n";
			}
			if (locDocumento instanceof DocumentoCementerio){						//SHPS
				DocumentoCementerio locDocumentoCementerio = (DocumentoCementerio) locDocumento;
				resultado+="Nro. Cuenta:       "+locDocumentoCementerio.getNumeroCuenta() +" \n";
			}
			if (locLiquidacionTasa.getDocGeneradorDeuda().getObligacion().getDocumentoEspecializado() instanceof DocumentoPlanObra){ 					//PFO
				DocumentoPlanObra locDocumentoPlanObra = (DocumentoPlanObra)locLiquidacionTasa.getDocGeneradorDeuda().getObligacion().getDocumentoEspecializado();
				resultado+="Nro. Obra:       "+locDocumentoPlanObra.getObra().getNumeroObra() +" \n";
				resultado+="Nro. Inmueble:       "+locDocumentoPlanObra.getParcela().getNroRegistro() +" \n";
			}

			resultado+="Forma de Pago: "+locLiquidacionTasa.getCuotaLiquidacion().getPeriodo().getCalendario().getNombre() +" \n";
			if (locLiquidacionTasa.getDocGeneradorDeuda().getObligacion().getDocumentoEspecializado() instanceof DocumentoPlanObra){					//PFO
				resultado+="Período: "+ locLiquidacionTasa.getCuotaLiquidada() +" \n";
			}else{
				resultado+="Período: "+ locLiquidacionTasa.getCuotaLiquidacion().getPeriodo()+" \n";
			}
		} else if (locDeuda instanceof IngresoVario){ 
			resultado+=acortarString("Concepto: "+locDeuda.getNombre()+" \n", 40, 3) + "\n"; //Administrativo
		} else {
			resultado+=locDeuda.getNombre()+"\n";
		}

		resultado+=" \n";
		Double interes = this.ticketCaja.getImporteInteres();
		if (interes > 0D) {
			resultado+="Importe: $     "+locNumberFormat.format(this.ticketCaja.getImporteTotal() - interes)+"\n";
			resultado+="Interes: $     "+locNumberFormat.format(interes)+"\n";
		}
		resultado+="Importe total: $     "+locNumberFormat.format(this.ticketCaja.getImporteTotal())+"\n";
		resultado+="Pagos:\n";
		for (PagoTicket cadaPagoTicket : this.ticketCaja.getListaPagosTicket()){
			resultado += cadaPagoTicket.getDescripcion() + ":    $ " + locNumberFormat.format(cadaPagoTicket.getMonto())+"\n";
		}
		resultado+="Fecha Pago:                    "+ locDateFormat.format(this.ticketCaja.getDetalles().iterator().next().getFechaCancelacion()) +"\n";
		resultado+="Fecha Reimpresión:        "+ locDateFormat.format(Calendar.getInstance().getTime())+"\n";
		resultado+="\n ";
		resultado+="__________________________________"+" \n";
		resultado+="Este comprobante es valido \n";
		resultado+=" solo con el sello de caja\n";

		resultado+="\n ";
		resultado+="\n ";
		resultado+="\n ";

		return resultado;
	}

	public Persona getPersona() {
		return persona;
	}
	public TicketCaja getTicketCaja() {
		return ticketCaja;
	}

	public Caja getCaja() {
		return caja;
	}


	public static Imprimible getInstance(TicketCaja pTicketCaja, Persona pPersonaDeRegistroDeuda) {
		Imprimible locImprimible = new ImpresoraSerieTicketCaja(pTicketCaja, pPersonaDeRegistroDeuda);
		if (!locImprimible.isValido()){
			locImprimible = new ImpresoraSpoolTicketCajaSinDialogo(pTicketCaja, pPersonaDeRegistroDeuda);
			if (!locImprimible.isValido()){
				locImprimible = new ImpresoraSpoolTicketCaja(pTicketCaja, pPersonaDeRegistroDeuda);
			}
		}
		return locImprimible;
	}

	public abstract void imprimir() throws Exception;

	public abstract void imprimirAnulacion() throws Exception;

	public abstract void reimprimir() throws Exception;
}
