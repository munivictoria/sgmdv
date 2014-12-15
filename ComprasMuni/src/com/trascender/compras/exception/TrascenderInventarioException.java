package com.trascender.compras.exception;

import java.util.List;

import com.trascender.framework.exception.TrascenderException;

public class TrascenderInventarioException extends TrascenderException{

	private static final long serialVersionUID = -2326819769850055662L;
	private Object objetoRelacion1;
	private Object objetoRelacion2;
	private List<Object> listaObjetoRelacion1;
	
	public TrascenderInventarioException(int pCodeSystemException) {
		super(8000 + pCodeSystemException);
	}
	
	@Override
	protected void setMsg() {
		switch (getCodeTrascenderException()){
		//BUSINESS 
		//PuntoDeReposicion
		case 8100: this.msg = "No se puede cargar dos puntos de reposicion para el mismo bien.";break;
		//Stock
		case 8120: this.msg = "No se pueden cargar mas de un Stock, en el mismo deposito, para el mismo bien."; break;
		case 8121: this.msg = "No se ha podido recuperar el Stock.";break;
		case 8122: this.msg = "Para eliminar el Stock la cantidad del mismo debe ser 0.";break;
		case 8123: this.msg = "Para eliminar el Stock no debe poseer movimientos durante el año.";break;
		//MovimientoDeMercaderia
		case 8140: this.msg = "No posee cantidad suficiente de ese producto para realizar el movimiento de mercadería.";break;
		case 8141: this.msg = "El movimiento debe estar en estado PENDIENTE para poder ser modificado.";break;
		case 8142: this.msg = "El movimiento debe estar en estado PENDIENTE para poder ser eliminado.";break;
		case 8143: this.msg = "Los Bienes del Stock Origen y Stock Destino deben ser iguales.";break;
		case 8144: this.msg = "No se pueden hacer movimientos del mismo bien dentro del mismo depósito.";break;
		case 8145: this.msg = "El Stock de las Líneas no puede ser nulo.";break;
		case 8146: this.msg = "El Stock Destino de las Líneas no puede ser nulo.";break;
		case 8147: this.msg = "La cantidad de uno o mas movimientos supera la cantidad requerida en Solicitud.";break;
		case 8148: this.msg = "La cantidad de las Líneas no puede ser menor a cero.";break;
		//Deposito
		case 8150: this.msg = "El nombre del deposito ya existe en el area."; break;
		case 8151: this.msg = "No se puede borra un depósito con un stock asociado."; break;
		
		//SYSTEM
		//Stock
		//PuntoDeReposicion
		//MovimientoDeMercaderia
		case 8950: this.msg = "No posee permisos suficientes para realizar la operación. Póngase en contacto con el administrador.";break;
		case 8951: this.msg = "No se ha podido setear la llave. Póngase en contacto con el administrador.";break;
		}
	}

	public Object getObjetoRelacion1() {
		return objetoRelacion1;
	}

	public Object getObjetoRelacion2() {
		return objetoRelacion2;
	}

	public List<Object> getListaObjetoRelacion1() {
		return listaObjetoRelacion1;
	}

}
