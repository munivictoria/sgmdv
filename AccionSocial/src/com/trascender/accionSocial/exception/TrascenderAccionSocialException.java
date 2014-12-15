package com.trascender.accionSocial.exception;

import com.trascender.framework.exception.TrascenderException;

public class TrascenderAccionSocialException extends TrascenderException{

	public TrascenderAccionSocialException(int pAccionSocialException) {
		super(7000 + pAccionSocialException);
	}

	@Override
	protected void setMsg() {
		
		switch(getCodeTrascenderException()){
		//FICHA SOCIAL
		case 7001: this.msg = "El numero de Ficha Social ya se encuentra en uso.";break;
		case 7002: this.msg = "El Titular que intenta agregar ya se encuentra registrado en otra Ficha Social.";break;
		case 7003: this.msg = "Uno de los Miembros del Grupo Familiar ya se encuentra registrado en otra Ficha Social.";break;
		
		
		//BENEFICIOS
		case 7020: this.msg = "Ya existe un Beneficio con el mismo Nombre.";break;
		case 7021: this.msg = "No puede Eliminarse el Beneficio ya que existen Fichas Sociales relacioanadas.";break;
		
		
		//OBRA SOCIAL
		case 7040: this.msg = "Ya existe una Obra Social con el nombre dado.";break;
		case 7041: this.msg = "La Obra Social esta asociada a uno o mas Beneficiarios"; break;
		
		
		//Otros
		case 7900: this.msg = "No posee permisos suficientes para realizar la operación. Póngase en contacto con el administrador.";break;
		case 7901: this.msg = "Ha ocurrido un error inesperado. Póngase en contacto con el administrador.";break;
		
		
		
		default: this.msg = "Ha ocurrido un error inesperado."; break;
		
		}
	}

}
