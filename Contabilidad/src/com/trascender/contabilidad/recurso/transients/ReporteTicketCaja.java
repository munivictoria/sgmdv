/**
 * 
 */
package com.trascender.contabilidad.recurso.transients;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.contabilidad.recurso.persistent.Caja;
import com.trascender.contabilidad.recurso.persistent.TicketCaja;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.saic.recurso.persistent.LiquidacionTasa;
import com.trascender.saic.recurso.persistent.ModificadorLiquidacion;
import com.trascender.saic.recurso.persistent.RegistroDeuda;

/**
 * Clase utilizada para el reporte del Ticket de Caja.
 * @author Ignacio Sarnaglia
 *
 */
public class ReporteTicketCaja implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6248344726539908963L;
	private LiquidacionTasa liquidacionTasa;
	private Caja caja;
	private Usuario usuario;
	private TicketCaja ticketCaja;
	private Parcela parcela;
	private RegistroDeuda registroDeuda;
	
	private List<String> detalleLiquidacion;
	private List<Double> importesLiquidacion;
	
	public ReporteTicketCaja(TicketCaja pTicketCaja, RegistroDeuda pRegistroDeuda, Parcela pParcela, LiquidacionTasa pLiquidacionTasa, Usuario pUsuario, Caja pCaja){
		
		this.registroDeuda = pRegistroDeuda;
		this.caja = pCaja;
		this.usuario = pUsuario;
		this.parcela = pParcela;
		this.liquidacionTasa = pLiquidacionTasa;
		
		this.setCalculosLiquidacion();
		//Tiro los toString para recuperar todo lo que pueda directamente desde el constructor
		this.caja.toString();
		this.usuario.toString();
		this.ticketCaja.toString();
		this.parcela.toString();
		this.liquidacionTasa.toString();
	}
	/**
	 * @return numero de ticket
	 */
	public String getNroTicket(){
		return this.ticketCaja.toString();
	}
	
	/**
	 * @return nombre y numero de la caja
	 */
	public String getCaja(){
		return this.caja.toString();
	}
	/**
	 * @return nombre del cajero
	 */
	public String getCajero(){
		return this.usuario.getNombrePersonaFisica();		
	}
	/**
	 * @return la fecha del pago
	 */
	public Date getFechaDePago(){
		return this.ticketCaja.getFecha();
	}
	/**
	 * @return la hora del pago
	 */
	public Date getHoraDePago(){
		return this.ticketCaja.getHora();
	}
	/**
	 * @return el concepto del pago
	 */
	public String getConcepto(){
		return this.liquidacionTasa.toString();
	}
	/**
	 * @return los detalles de la liquidacion
	 */
	public List getDetallesLiquidacion(){
		return this.detalleLiquidacion;
	}
	
	/**
	 * @return los importes de la liquidacion
	 */
	public List getImportesLiquidacion(){
		return this.importesLiquidacion;
	}
	/**
	 * @return el registro de deuda
	 */
	public String getRegistroDeDeuda(){
		return this.registroDeuda.toString();
	}
	/**
	 * Prepara las listas de detalles y monto para ser retornadas en las liquidaciones
	 */
	private void setCalculosLiquidacion(){
		List<ModificadorLiquidacion> locListaModificadoresLiquidacion=new ArrayList<ModificadorLiquidacion>();
		List<ModificadorLiquidacion> locListaModificadoresSobreSubTotal = new ArrayList<ModificadorLiquidacion>();
		
		
		for (ModificadorLiquidacion locModificadorLiquidacion:this.liquidacionTasa.getListaModificadoresLiquidacion()){
			if (locModificadorLiquidacion.isSobreSaldoNeto()){
				locListaModificadoresLiquidacion.add(locModificadorLiquidacion);
			}
			else{
				locListaModificadoresSobreSubTotal.add(locModificadorLiquidacion);
			}
		}
		
		
		this.detalleLiquidacion = new ArrayList<String>();;
		this.importesLiquidacion = new ArrayList<Double>();

		
		//Valor de la tasa		
		this.detalleLiquidacion.add("Tasa "+this.liquidacionTasa.getCuotaLiquidacion().getPeriodicidad().getNombrePeriodo());
		this.importesLiquidacion.add(this.liquidacionTasa.getValor());
		
		
		
		//Valor de los modificadores sobre la tasa
		for (ModificadorLiquidacion locModificadorLiquidacion: locListaModificadoresLiquidacion){
			this.detalleLiquidacion.add(locModificadorLiquidacion.getNombre());
			this.importesLiquidacion.add(locModificadorLiquidacion.getValorModificador());
		}
		
		//Sub total
		this.detalleLiquidacion.add("SubTotal");
		this.importesLiquidacion.add(liquidacionTasa.getValor() + liquidacionTasa.getValorModificadoresSobreSaldoNeto());
		
		//Modificadores sobre saldo sub total
		for (ModificadorLiquidacion locModificadorLiquidacion: locListaModificadoresSobreSubTotal){
			this.detalleLiquidacion.add(locModificadorLiquidacion.getNombre());
			this.importesLiquidacion.add(locModificadorLiquidacion.getValorModificador());
		}
		
		//Total;
		this.detalleLiquidacion.add("Total");
		this.importesLiquidacion.add(this.liquidacionTasa.getValorTotal());
		
//		this.liquidacionTasa
	}
}
