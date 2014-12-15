package com.trascender.saic.exception;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.trascender.framework.exception.TrascenderException;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;

public class ResultadoLiquidacion implements Serializable{

	private static final long serialVersionUID = 3826888918033830086L;
	
	private Set<String> listaMensajes = new HashSet<String>();
	
	private int cantidadLiquidadas = 0;
	
	private List<Obligacion> listaObligacionesNoLiquidadas = new ArrayList<Obligacion>();
	
	public Set<String> getListaMensajes() {
		return listaMensajes;
	}
	
	public ResultadoLiquidacion() {
		super();
	}
	
	public void addMensaje(int pNro){
		String mensaje = null;
		switch (pNro){
		case 0: mensaje = "El periodo para esta obligacion ya fue liquidado."; break;
		case 1: mensaje = "No se pudo generar la liquidación por que hay otro plan elegido para el año requerido.";break;
		case 2: mensaje = "No se ha podido realizar la liquidación. El monto de la liquidación es incorrécto.";break;
		case 3: mensaje = "La Obligación se encuentra asociada a una Fórmula de Cálculo en estado 'En Espera'.";break;
		case 4: mensaje = "No hay una formula de calculo vigente para la cuota que desea liquidar.";break;
		case 5: mensaje = "El Período no puede ser Nulo.";break;
		case 6: mensaje = "El Tipo de Tasa Menor no puede ser nulo"; break;
		
		default: mensaje = "Un error inesperado ha ocurrido. Por favor comuníquese con el Administrador";break;
		}
		
		if(mensaje != null){
			this.listaMensajes.add(mensaje);
		}
	}
	
	public String getMensajes(){
		StringBuilder locStringMensaje = new StringBuilder();
		
		for(String cadaString : this.listaMensajes){
			locStringMensaje.append(cadaString);
			locStringMensaje.append("\n");
		}
//		locStringMensaje.delete(locStringMensaje.lastIndexOf("\n"), locStringMensaje.length());
		return locStringMensaje.toString();
	}

	public int getCantidadLiquidadas() {
		return cantidadLiquidadas;
	}

	public void setCantidadLiquidadas(int cantidadLiquidadas) {
		this.cantidadLiquidadas = cantidadLiquidadas;
	}
	
	public List<Obligacion> getListaObligacionesNoLiquidadas() {
		return listaObligacionesNoLiquidadas;
	}

	public void setListaObligacionesNoLiquidadas(
			List<Obligacion> listaObligacionesNoLiquidadas) {
		this.listaObligacionesNoLiquidadas = listaObligacionesNoLiquidadas;
	}

	public void setListaMensajes(Set<String> listaMensajes) {
		this.listaMensajes = listaMensajes;
	}
}
