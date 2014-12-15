package com.trascender.caja.gui.impresion;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.StringTokenizer;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;

import com.trascender.contabilidad.recurso.persistent.TicketCaja;
import com.trascender.framework.recurso.persistent.Persona;

public class ImpresoraSpoolTicketCajaSinDialogo extends Imprimible{
	
	private static int PASO_LINEA = 11;
	
	private PrintService printService;
	

	public ImpresoraSpoolTicketCajaSinDialogo(TicketCaja pTicketCaja,
			Persona pPersona) {
		super(pTicketCaja, pPersona);
		PrintService locPrintService = this.getImpresora();
		if (locPrintService != null){
			this.printService = locPrintService;
			this.valido = true;
		}
	}

	@Override
	public void imprimir() throws Exception {
		PrinterJob locPrinterJob = PrinterJob.getPrinterJob();
		Book book = new Book();
		PageFormat format = locPrinterJob.defaultPage();
		Paper locPaper = new Paper();
		locPaper.setImageableArea(0, 0, format.getImageableWidth(), format.getHeight());
		format.setPaper(locPaper);
		book.append(new TicketPrintable(getTextoImpresion()), format);
		locPrinterJob.setPrintService(printService);
		locPrinterJob.setPageable(book);
		locPrinterJob.print();
	}
	
	@Override
	public void imprimirAnulacion() throws Exception {
		PrinterJob locPrinterJob = PrinterJob.getPrinterJob();
		Book book = new Book();
		PageFormat format = locPrinterJob.defaultPage();
		Paper locPaper = new Paper();
		locPaper.setImageableArea(0, 0, format.getImageableWidth(), format.getHeight());
		format.setPaper(locPaper);
		book.append(new TicketPrintable(getTextoImpresionAnulacion()), format);
		locPrinterJob.setPrintService(printService);
		locPrinterJob.setPageable(book);
		locPrinterJob.print();
	}
	
	private PrintService getImpresora(){
		PrintService service = null;
		try{
			String nombreImpresora = this.getCaja().getPuertoImpresion();
			PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
		      for (PrintService printService : services) {
		    	  if (printService.getName().equalsIgnoreCase(nombreImpresora)){
		    		  service = printService;
		    	  }
		      }
		 
		}catch(Exception e){
			
		}
		   return service;
	}
	
	class TicketPrintable implements Printable{
		
		private String textoImprimible;
		
		public TicketPrintable(String pTextoImprimible){
			this.textoImprimible = pTextoImprimible;
		}

		public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
				throws PrinterException {
			Graphics2D g2d2 = (Graphics2D) graphics;
			g2d2.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
            g2d2.setPaint(Color.black);
			Font customerFont = new Font("Times New Roman", Font.PLAIN, 9);
			g2d2.setFont(customerFont);
            StringTokenizer locString = new StringTokenizer(this.textoImprimible,"\n");
			int acumuladorLinea = 5;
			while(locString.hasMoreElements()){
				String linea = locString.nextToken();
				g2d2.drawString(linea, 7, acumuladorLinea);
				acumuladorLinea+=PASO_LINEA;
			}
			return PAGE_EXISTS;
		}
		
	}

	@Override
	public void reimprimir() throws Exception {
		
		PrinterJob locPrinterJob = PrinterJob.getPrinterJob();
		Book book = new Book();
		PageFormat format = locPrinterJob.defaultPage();
		Paper locPaper = new Paper();
		locPaper.setImageableArea(0, 0, format.getImageableWidth(), format.getHeight());
		format.setPaper(locPaper);
		book.append(new TicketPrintable(getTextoReimpresion()), format);
		locPrinterJob.setPrintService(printService);
		locPrinterJob.setPageable(book);
		System.out.println("TEXTO A IMPRIMIR");
		System.out.println(getTextoImpresion());
		locPrinterJob.print();
	}

}
