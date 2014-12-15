package com.trascender.caja.gui.cobro;

import java.util.ArrayList;
import java.util.List;

import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.model.TAbstractTableModel;
/**
 * @author adrian
 */
public class CobroTableModel extends TAbstractTableModel<Deuda> {

	private static final long serialVersionUID = 6322361338908372426L;
	private List<Deuda> listaDeuda = new ArrayList<Deuda>();
	private List<TicketDeuda> listaTicket = new ArrayList<TicketDeuda>();
	
	public CobroTableModel(/*TicketCaja pTicket*/) throws Exception {
		super(Deuda.class);
	
//		if (pTicket!=null) {
//			this.ticket = pTicket;
//		}
//		else{
//			
//		}
		List<TColumnField> locListaColumnas = this.inicializarColumnas();
		this.setListaColumnas(locListaColumnas);
	}

	private List<TColumnField> inicializarColumnas() throws Exception {

		List<TColumnField> locListaColumnas = new ArrayList<TColumnField>();
		try {
			locListaColumnas.add(new TColumnField("Descripción",Deuda.class.getDeclaredField("nombre")));
			locListaColumnas.add(new TColumnField("Nombre", Deuda.class.getDeclaredField("persona")));
			locListaColumnas.add(new TColumnField("Monto",Deuda.class.getDeclaredField("monto"), Double.class));
			return locListaColumnas;
		} catch (Exception ex) {			
			ex.printStackTrace();
			throw new GuiException(6);
		}
	}

	
	@Override
	public void addRow(Deuda pRow) {
//		super.addRow(pRow); ESTE METODO NO LO USO
//		this.listaDeuda.add(pRow);
	}	
	
	public void addRow(Deuda pRow, TicketDeuda pTicket) {
		this.listaDeuda.add(pRow);
		super.addRow(pRow);
		this.listaTicket.add(pTicket);
	}	
	
	
	@Override
	public void deleteRow(Deuda pRow) {
		this.listaTicket.remove(this.listaDeuda.indexOf(pRow));
		this.listaDeuda.remove(pRow);
		super.deleteRow(pRow);
//		super.deleteRow(pRow); ESTE METODO NO LO USO
//		this.listaTicket.remove(pRow);
	}
	

	public void deleteRow(Deuda pRow, TicketDeuda pTicket) {
		
		
//		this.listaDeuda.remove(pRow);
//		this.listaTicket.remove(pTicket);
	}	
	
	@Override
	public void deleteRow(int pRowIndex) {
//		FIXME:solo anda con sellado administrativo porque es siempre una sola linea arreglarlo para que tb ande con Registro Deuda - Jose
		this.listaTicket.remove(this.getListaObjetos().get(pRowIndex));
		super.deleteRow(pRowIndex);
	}

	public List<TicketDeuda> getListaTicket() {
		return listaTicket;
	}

	public void setListaTicket(List<TicketDeuda> listaTicket) {
		this.listaTicket = listaTicket;
	}

	public List<Deuda> getListaDeuda() {
		return listaDeuda;
	}

	public void setListaDeuda(List<Deuda> listaDeuda) {
		this.listaDeuda = listaDeuda;
	}
	
	
}
