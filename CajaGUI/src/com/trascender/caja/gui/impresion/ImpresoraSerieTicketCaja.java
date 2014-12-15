package com.trascender.caja.gui.impresion;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

import com.trascender.caja.gui.exception.CajaGUIException;
import com.trascender.contabilidad.recurso.persistent.TicketCaja;
import com.trascender.framework.recurso.persistent.Persona;

public class ImpresoraSerieTicketCaja extends Imprimible {
	private static int CANTIDAD_ESPACIOS = 15;
	private BufferedOutputStream archivoSalida;
	private String puerto;
	
	public ImpresoraSerieTicketCaja(TicketCaja pTicketCaja, Persona pPersona){
		super(pTicketCaja,pPersona);
		this.puerto = this.getCaja().getPuertoImpresion();
		System.out.println("---> puerto " + this.puerto);
		//Si no es una impresora asi, el puerto no es paralelo o serie..
		if (this.puerto.contains("COM") || this.puerto.contains("LTP")){
			try{
				this.archivoSalida = new BufferedOutputStream(new FileOutputStream(this.puerto));
				this.archivoSalida.flush();
				this.valido=true;
			}
			catch(Exception e ){
				e.printStackTrace();
				this.valido=false;
			}
		}
	}
	
	public void imprimir() throws CajaGUIException {
		System.out.println("---> puerto in imprimir " + this.puerto);
		if (this.puerto==null) {
			throw new CajaGUIException(0);
		}
		
		try{
			String texto = this.getTextoImpresion();
			System.out.println("---> texto " + texto);
			
			byte[] cadenaSalida = texto.getBytes("ASCII");
//			
//			for (int i=0;i<cadenaSalida.length;i++){
//				if (texto.charAt(i) == 'ñ'){
//					cadenaSalida[i] = 0x4a;
//				}
//				else if (texto.charAt(i) == 'í'){
//					cadenaSalida[i] = 0x1a;
//				}
//				else if (texto.charAt(i) == 'á'){
//					cadenaSalida[i] = 0x0a;
//				}
//				else if (texto.charAt(i) == 'ó'){
//					cadenaSalida[i] = 0x2a;
//				}
//				else if (texto.charAt(i) == 'é'){
//					cadenaSalida[i]=0x28;
//				}
//				else if (texto.charAt(i) == 'ú'){
//					cadenaSalida[i] = 0x3a;
//				}
//			}
			
		
			this.archivoSalida.write(cadenaSalida);
			//PrintStream locFlujo = new PrintStream(this.archivoSalida);
			
			
//			locFlujo.println(this.getTextoImpresion());
			
			for (int i=0;i<CANTIDAD_ESPACIOS;i++){
				this.archivoSalida.write("\n".getBytes());
			}
		//	locFlujo.close();
			
			this.archivoSalida.close();
		}
		catch(Exception e){
			e.printStackTrace();
			throw new CajaGUIException(0);
		}
	}
	
	//Marina
	@Override
	public void imprimirAnulacion() throws CajaGUIException {
		System.out.println("---> puerto in imprimir " + this.puerto);
		if (this.puerto==null) {
			throw new CajaGUIException(0);
		}
		
		try{
			String texto = this.getTextoImpresionAnulacion();
			System.out.println("---> texto " + texto);
			
			byte[] cadenaSalida = texto.getBytes("ASCII");
		
			this.archivoSalida.write(cadenaSalida);
			
			for (int i=0;i<CANTIDAD_ESPACIOS;i++){
				this.archivoSalida.write("\n".getBytes());
			}
			
			this.archivoSalida.close();
		}
		catch(Exception e){
			e.printStackTrace();
			throw new CajaGUIException(0);
		}
	}
	
	public boolean isValid() {
		return this.valido;
	}

	@Override
	public void reimprimir() throws Exception {
		System.out.println("---> puerto in reimprimir " + this.puerto);
		if (this.puerto==null) {
			throw new CajaGUIException(0);
		}
		
		try{
			String texto = this.getTextoReimpresion();
			System.out.println("---> texto " + texto);
			
			byte[] cadenaSalida = texto.getBytes("ASCII");
		
			this.archivoSalida.write(cadenaSalida);
			
			for (int i=0;i<CANTIDAD_ESPACIOS;i++){
				this.archivoSalida.write("\n".getBytes());
			}
			
			this.archivoSalida.close();
		}
		catch(Exception e){
			e.printStackTrace();
			throw new CajaGUIException(0);
		}
	}
	
}
