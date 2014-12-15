package com.trascender.saic.recurso.transients;

import java.io.Serializable;
import java.util.ArrayList;

public class HistorialPagosTasas implements Serializable{

	private ArrayList<LineaHistorialPago> listaHitorialPagoAnual;

	public ArrayList<LineaHistorialPago> getListaHitorialPagoAnual() {
		return listaHitorialPagoAnual;
	}

	public void setListaHitorialPagoAnual(
			ArrayList<LineaHistorialPago> listaHitorialPagoAnual) {
		this.listaHitorialPagoAnual = listaHitorialPagoAnual;
	}
	public void addLineaHistorialAnual(LineaHistorialPago pLinea){
		
		this.listaHitorialPagoAnual.add(pLinea);
		
	}
	public HistorialPagosTasas(){
		this.listaHitorialPagoAnual = new ArrayList<LineaHistorialPago>();
	}
	
}
