package com.trascender.saic.recurso.transients.pagoFacil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

import com.trascender.framework.recurso.persistent.Municipalidad;
import com.trascender.saic.exception.SaicException;

public class ArchivoTransmisionPagoFacil {

	private CabeceraArchivoPF cabecera = new CabeceraArchivoPF();
	
	private LoteArchivoPF[] listaLotes = new LoteArchivoPF[]{null,null,null};
	private RegistroColaArchivoPF registroColaArchivo = new RegistroColaArchivoPF();
	
	public ArchivoTransmisionPagoFacil(File pFile) throws Exception {
		
		Set<Integer> locListaResultado = new HashSet<Integer>(); 
		
//		pFile = new File("/home/juanma/Escritorio/PF040609.011");
		FileReader locLector = null;
		try{
		locLector = new FileReader(pFile);
		BufferedReader locDocumento = new BufferedReader(locLector);
		String cadaLinea = "";
		int positioReader = 0; 
		int locNumeroLote = -1; 
		SimpleDateFormat locSimpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		while( (cadaLinea = locDocumento.readLine()) != null){
			if(positioReader == 0){
//				if(cadaLinea.indexOf(locMunicipalidad.getNumeroClientePagoFacil().toString()) == -1){
//					throw new SaicException(53);
//				}
				
				//Agarro la primera posicion xq extraigo la cabecera
				this.getCabecera().setCreateDate(locSimpleDateFormat.parse(cadaLinea.substring(1, 9)));
				this.getCabecera().setOriginName(cadaLinea.substring(9, 34).trim());
				this.getCabecera().setClientNumber(Integer.valueOf(cadaLinea.substring(34, 43)));
				this.getCabecera().setClientName(cadaLinea.substring(43, 78).trim());
				System.out.println("INICIO DE ARCHIVO");
			}
			
			
			if(cadaLinea.startsWith("3")){
				LoteArchivoPF locLote = new LoteArchivoPF();
				locLote.setCreateDate(locSimpleDateFormat.parse(cadaLinea.substring(1, 9)));
				locLote.setBatchNumber(Integer.valueOf(cadaLinea.substring(9, 15)));
				locLote.setDescription(cadaLinea.substring(15,50).trim());
				System.out.println("INICIO DE LOTE\n\tNumero lote: "+ ++locNumeroLote);
				
				while( ( ((cadaLinea = locDocumento.readLine()) != null) && !(cadaLinea.startsWith("8") || cadaLinea.startsWith("3"))) ){
					//generar el registro detalle leyendo 2 veces mas el archivo
					RegistroDetallePF locRegistro = new RegistroDetallePF();
						System.out.println("PARTE 1 REGISTRO DETALLE: " + cadaLinea);
					locRegistro.setDetalleTransaccion(this.generarDetalleTransaccion(cadaLinea));
					
						System.out.println("PARTE 2 REGISTRO DETALLE: " + (cadaLinea = locDocumento.readLine()));
					locRegistro.setCodigoBarrasTransaccion(this.generarcodigoBarrasTransaccion(cadaLinea));
					
						System.out.println("PARTE 3 REGISTRO DETALLE: " + (cadaLinea = locDocumento.readLine()));
					locRegistro.setDetalleInstrumento(this.generarDetalleInstrumento(cadaLinea));
					
					locLote.addRegistroDetallePF(locRegistro);
				}
				
				if(cadaLinea.startsWith("8")){
					//generar el cola lote
					ColaLotePF locColaLote = new ColaLotePF();
					locColaLote.setCreateDate(locSimpleDateFormat.parse(cadaLinea.substring(1,9)));
					locColaLote.setBatchNumber(Integer.valueOf(cadaLinea.substring(9, 15)));
					locColaLote.setBatchPaymentCount(Integer.valueOf(cadaLinea.substring(15, 22)));
					locColaLote.setBatchPaymentMount(Double.valueOf(cadaLinea.substring(22, 34)));
					locColaLote.setBatchCount(Integer.valueOf(cadaLinea.substring(60, 65)));
					locLote.setColaLote(locColaLote);
					
					this.addLote(locLote);
					System.out.println("FIN DE LOTE");
				}
			
			}
			
			if(cadaLinea.startsWith("9")){
				//generar el cola arhivo
				this.registroColaArchivo = new RegistroColaArchivoPF();
				this.registroColaArchivo.setCreateDate(locSimpleDateFormat.parse(cadaLinea.substring(1, 9)));
				this.registroColaArchivo.setTotalBatches(Integer.valueOf(cadaLinea.substring(9, 15)));
				this.registroColaArchivo.setFilePaymentCount(Integer.valueOf(cadaLinea.substring(15, 22)));
				this.registroColaArchivo.setFilePaymentAmount(Double.valueOf(cadaLinea.substring(22, 34))/100);
				this.registroColaArchivo.setFileCount(Integer.valueOf(cadaLinea.substring(60, 67)));
				System.out.println("FIN DE ARCHIVO");
			}
			
			
			positioReader++;
		}
		
		}catch (FileNotFoundException e) {
			throw new Exception("No se pudo encontrar el archivo pago facil.");
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			if(locLector != null){
				locLector.close();
			}
		}
	}
		

		private DetalleInstrumentoPF generarDetalleInstrumento(String pLinea){
			DetalleInstrumentoPF locDetalle = new DetalleInstrumentoPF();
			
			locDetalle.setCurrencyCode(pLinea.substring(1,3));
			locDetalle.setPayInstrument(pLinea.charAt(4));
			locDetalle.setCodeBarPayment(pLinea.substring(4, 80));
			locDetalle.setAmount(Double.valueOf( pLinea.substring(80, 95))/100);
			
			
			return locDetalle;
		}
		
		private CodigoBarraTransaccionPF generarcodigoBarrasTransaccion(String pLinea) throws Exception{
			CodigoBarraTransaccionPF locCodigoBarras = new CodigoBarraTransaccionPF();
			locCodigoBarras.setBarCode(pLinea.substring(1, 80).trim());
			locCodigoBarras.setTypeCode(pLinea.charAt(80));
			
			
			return locCodigoBarras;
		}
		
		private DetalleRegistroPF generarDetalleTransaccion(String pLinea) throws Exception{
		SimpleDateFormat locDateFormat = new SimpleDateFormat("yyyyMMdd");
		
		DetalleRegistroPF locDetalle = new DetalleRegistroPF();
		
			locDetalle.setRecordSequence(Integer.valueOf(pLinea.substring(1, 6)));
			locDetalle.setTransactionCode(Integer.valueOf(pLinea.substring(6, 8)));
			locDetalle.setWorkDate(locDateFormat.parse(pLinea.substring(8, 16)));
			locDetalle.setTransferDate(locDateFormat.parse(pLinea.substring(16, 24)));
			locDetalle.setAccountNumber(pLinea.substring(24, 45).trim());
			locDetalle.setCurrencyCode(pLinea.substring(45, 48).toUpperCase());
			locDetalle.setAmount( (Double.valueOf( this.clearFillNumbers(pLinea.substring(48, 58)).trim())/100) );
			locDetalle.setTerminalId(pLinea.substring(58, 64));
			locDetalle.setPaymentDate(locDateFormat.parse(pLinea.substring(64, 72)));
			locDateFormat.applyPattern("HHmm");
			locDetalle.setPaymentDate(locDateFormat.parse(pLinea.substring(72, 76)));
			locDetalle.setSecuenciaTransaccion(Integer.valueOf(this.clearFillNumbers(pLinea.substring(76, 127).trim())));
			System.out.println(locDetalle);
			
			return locDetalle;
		}

		private String clearFillNumbers(String pString){
			while(pString.startsWith("0")){
				pString = pString.replaceFirst("0", " ").trim();
			}
		return pString.trim();
		}
	
	public void addLote(LoteArchivoPF pLote) throws Exception{
		if(listaLotes[0] != null && listaLotes[1] != null && listaLotes[3] != null){
			throw new Exception("La lista de lotes esta llena.");
		}
		
		if(listaLotes[0] == null){
			listaLotes[0] = pLote;
		} else if(listaLotes[1] == null){
			listaLotes[1] = pLote;
		} else if(listaLotes[2] == null){
			listaLotes[2] = pLote;
		}
		
	}
	
	

	public CabeceraArchivoPF getCabecera() {
		return cabecera;
	}

	public void setCabecera(CabeceraArchivoPF cabecera) {
		this.cabecera = cabecera;
	}

	public LoteArchivoPF[] getListaLotes() {
		return listaLotes;
	}

	public void setListaLotes(LoteArchivoPF[] listaLotes) {
		this.listaLotes = listaLotes;
	}
	
	
	
	
	
}
