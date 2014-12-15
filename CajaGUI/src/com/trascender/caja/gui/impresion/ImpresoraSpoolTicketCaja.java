package com.trascender.caja.gui.impresion;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.JobAttributes;
import java.awt.PrintJob;
import java.awt.Toolkit;
import java.util.StringTokenizer;

import javax.swing.JFrame;

import com.trascender.caja.gui.exception.CajaGUIException;
import com.trascender.contabilidad.recurso.persistent.TicketCaja;
import com.trascender.framework.recurso.persistent.Persona;

public class ImpresoraSpoolTicketCaja extends Imprimible {
	
	private Font fuente = new Font("Dialog",Font.PLAIN,10);
	private PrintJob printJob;
	private Graphics pagina;
	private static int PASO_LINEA = 13;
	
	
	public ImpresoraSpoolTicketCaja(TicketCaja pTicketCaja, Persona pPersona) {
		super(pTicketCaja, pPersona);
		JobAttributes locJobAttributes = new JobAttributes();
		this.printJob = Toolkit.getDefaultToolkit().getPrintJob(new JFrame(), "ImpresionTicket", locJobAttributes, null);
		valido = this.printJob != null;
	}

	
	@Override
	public String getTextoImpresion() {
		String locString  = super.getTextoImpresion();
		Byte locByte = 0x4A;
		locString.replaceAll("ñ", locByte.toString());
		return locString; 
	}
	
	public void imprimir() throws CajaGUIException  {
		try{
			this.pagina = printJob.getGraphics();
			this.pagina.setFont(this.fuente);
			this.pagina.setColor(Color.BLACK);
			StringTokenizer locString = new StringTokenizer(this.getTextoImpresion(),"\n");
			int acumuladorLinea = 60;
			while(locString.hasMoreElements()){
				String linea = locString.nextToken();
				this.pagina.drawString(linea, 60, acumuladorLinea);
				acumuladorLinea+=ImpresoraSpoolTicketCaja.PASO_LINEA;
			}

			System.out.println("::::::::::::::::::::::::." + this.pagina);
			this.pagina.dispose();
			this.printJob.end();
		}
		catch(Exception e){
			e.printStackTrace();
			System.err.println("Impresión cancelada.");
			throw new CajaGUIException(0);
		}
	}
	
	@Override
	public String getTextoImpresionAnulacion() {
		String locString = super.getTextoImpresionAnulacion();
		Byte locByte = 0x4A;
		locString.replaceAll("ñ", locByte.toString());
		return locString; 
	}
	
	@Override
	public String getTextoReimpresion() {
		String locString = super.getTextoReimpresion();
		Byte locByte = 0x4A;
		locString.replaceAll("ñ", locByte.toString());
		return locString; 
	}
	
	@Override
	public void imprimirAnulacion() throws CajaGUIException  {
		try{
			this.pagina = printJob.getGraphics();
			this.pagina.setFont(this.fuente);
			this.pagina.setColor(Color.BLACK);
			StringTokenizer locString = new StringTokenizer(this.getTextoImpresionAnulacion(),"\n");
			int acumuladorLinea = 60;
			while(locString.hasMoreElements()){
				String linea = locString.nextToken();
				this.pagina.drawString(linea, 60, acumuladorLinea);
				acumuladorLinea+=ImpresoraSpoolTicketCaja.PASO_LINEA;
			}

			System.out.println(":::::::::::Anulación:::::::::::::." + this.pagina);
			this.pagina.dispose();
			this.printJob.end();
		}
		catch(Exception e){
			e.printStackTrace();
			System.err.println("Impresión cancelada.");
			throw new CajaGUIException(0);
		}
	}


	@Override
	public void reimprimir() throws Exception {
		try{
			this.pagina = printJob.getGraphics();
			this.pagina.setFont(this.fuente);
			this.pagina.setColor(Color.BLACK);
			StringTokenizer locString = new StringTokenizer(this.getTextoReimpresion(),"\n");
			int acumuladorLinea = 60;
			while(locString.hasMoreElements()){
				String linea = locString.nextToken();
				this.pagina.drawString(linea, 60, acumuladorLinea);
				acumuladorLinea+=ImpresoraSpoolTicketCaja.PASO_LINEA;
			}

			System.out.println(":::::::::::Reimpresión:::::::::::::." + this.pagina);
			this.pagina.dispose();
			this.printJob.end();
		}
		catch(Exception e){
			e.printStackTrace();
			System.err.println("Impresión cancelada.");
			throw new CajaGUIException(0);
		}		
	}
}
