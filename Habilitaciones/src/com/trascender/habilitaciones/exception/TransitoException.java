package com.trascender.habilitaciones.exception;

import com.trascender.framework.exception.TrascenderException;

public class TransitoException extends TrascenderException {

	public TransitoException(int pCodeSystemException) {
		super(9000 + pCodeSystemException);
	}

	private static final long serialVersionUID = 6987001555510518657L;

	@Override
	protected void setMsg() {
		switch (this.getCodeTrascenderException()) {
			//AUTOMOR
			case 9000: this.msg = "Error de Transito";break;
			case 9001: this.msg = "Los datos de la patente no son validos"; break;
			case 9002: this.msg = "Ya hay una patente igual registrada."; break;
			case 9003: this.msg = "El formato de la patente no es valido. Debe ser XXX-999"; break;
			case 9004: this.msg = "Ya existe una Tabla de Base Imponible activa."; break;
			case 9005: this.msg = "El Modelo no puede ser nulo."; break;
			case 9006: this.msg = "Ya existe un Modelo con el mismo nombre."; break;
			case 9007: this.msg = "La Marca no puede ser nula."; break;
			case 9008: this.msg = "El Tipo del Vehículo no puede ser nulo."; break;
			case 9009: this.msg = "No se puede eliminar un Modelo que esté asociado a un Vehículo."; break;
			case 9010: this.msg = "El Código o el Nombre no son válidos."; break;
			case 9011: this.msg = "Ya existe una Marca con el mismo Codígo."; break;
			case 9012: this.msg = "Ya existe una Marca con el mismo Nombre."; break;
			case 9013: this.msg = "No se puede eliminar una Marca que esté asociado a un Modelo."; break;
			case 9014: this.msg = "Ya existe un Tipo Vehículo con el mismo nombre."; break;
			case 9015: this.msg = "Ya existe un Tipo Vehículo con el mismo codigo"; break;
			case 9016: this.msg = "No se puede eliminar un Tipo Vehículo que esté asociado a un Modelo."; break;
			case 9020: this.msg = "No se puede eliminar un Vehiculo asociado a un Transporte Vehicular";break;
			case 9021: this.msg = "No se puede eliminar un Vehiculo asociado a una Obligacion";break;
			
			
			//TRANSITO
			case 9200: this.msg = "La Base Imponible no puede ser nula."; break;
			case 9201: this.msg = "La Base Imponible debe estar asociada a una Tabla de Base Imponible."; break;
			case 9202: this.msg = "La Base Imponible debe poseer una Marca valida asociada"; break;
			case 9203: this.msg = "El valor de la Base Imponible debe ser mayor o igual a 0."; break; 
			case 9204: this.msg = "La Tabla de Base Imponible debe ser una tabla activa."; break;
			case 9205: this.msg = "Ya existe una Base Imponible para ese Modelo con esa Marca y Tipo Vehiculo."; break;
			
			
			//GENERALES
			case 9800: this.msg = "No se ha podido agregar el Vehículo. Intentelo nuevamente mas tarde."; break;
			case 9801: this.msg = "No se ha podido actualizar el Vehículo. Intentelo nuevamente mas tarde."; break;
			case 9802: this.msg = "No se ha podido eliminar el Vehículo. Intentelo nuevamente mas tarde."; break;
			case 9803: this.msg = "No se ha podido recuperar el Vehículo. Intentelo nuevamente mas tarde."; break;
			case 9804: this.msg = "No se ha podido recuperar la lista de Vehículos. Intentelo nuevamente mas tarde."; break;
			case 9805: this.msg = "No se ha podido agregar el Modelo. Intentelo nuevamente mas tarde."; break;
			case 9806: this.msg = "No se ha podido actualizar el Modelo. Intentelo nuevamente mas tarde."; break;
			case 9807: this.msg = "No se ha podido eliminar el Modelo. Intentelo nuevamente mas tarde."; break;
			case 9808: this.msg = "No se ha podido recuperar la lista de Modelos. Intentelo nuevamente mas tarde."; break;
			case 9809: this.msg = "No se ha podido agregar la Marca. Intentelo nuevamente mas tarde."; break;
			case 9810: this.msg = "No se ha podido actualizar la Marca. Intentelo nuevamente mas tarde."; break;
			case 9811: this.msg = "No se ha podido eliminar la Marca. Intentelo nuevamente mas tarde."; break;
			case 9812: this.msg = "No se ha podido recuperar la lista de Marcas. Intentelo nuevamente mas tarde."; break;
			case 9813: this.msg = "No se ha podido agregar el Tipo Vehículo. Intentelo nuevamente mas tarde."; break;
			case 9814: this.msg = "No se ha podido actualizar el Tipo Vehículo. Intentelo nuevamente mas tarde."; break;
			case 9815: this.msg = "No se ha podido eliminar el Tipo Vehículo. Intentelo nuevamente mas tarde."; break;
			case 9816: this.msg = "No se ha podido recuperar la lista de Tipos Vehículos. Intentelo nuevamente mas tarde."; break;
			case 9817: this.msg = "No se ha podido recuperar el Modelo. Intentelo nuevamente mas tarde."; break;
			case 9818: this.msg = "No se ha podido recuperar el Marca. Intentelo nuevamente mas tarde."; break;
			case 9819: this.msg = "No se ha podido recuperar el Tipo Vehiculo. Intentelo nuevamente mas tarde."; break;
			case 9820: this.msg = "No se ha podido agregar la Base Imponible. Intentelo nuevamente mas tarde."; break;
			case 9821: this.msg = "No se ha podido actualizar la Base Imponible. Intentelo nuevamente mas tarde."; break;
			case 9822: this.msg = "No se ha podido eliminar la Base Imponible. Intentelo nuevamente mas tarde."; break;
			case 9823: this.msg = "No se ha podido recuperar la lista de Bases Imponibles. Intentelo nuevamente mas tarde."; break;
			case 9824: this.msg = "No se ha podido recuperar la Base Imponible. Intentelo nuevamente mas tarde."; break;
			case 9825: this.msg = "No se ha podido recuperar el Valor de la Base Imponible. Intentelo nuevamente mas tarde."; break;
			case 9826: this.msg = "No se ha podido recuperar la propiedad Es Exento de la Base Imponible. Intentelo nuevamente mas tarde."; break;
			case 9827: this.msg = "No se ha podido agregar la Tabla Base Imponible. Intentelo nuevamente mas tarde."; break;
			case 9828: this.msg = "No se ha podido actualizar la Tabla Base Imponible. Intentelo nuevamente mas tarde."; break;
			case 9829: this.msg = "No se ha podido eliminar la Tabla Base Imponible. Intentelo nuevamente mas tarde."; break;
			case 9830: this.msg = "No se ha podido recuperar la lista Tablas de Bases Imponibles. Intentelo nuevamente mas tarde."; break;
			case 9831: this.msg = "No se ha podido recuperar la Tabla Base Imponible. Intentelo nuevamente mas tarde."; break;
			case 9832: this.msg = "No se ha podido agregar el Titulo de Propiedad Automotor. Intentelo nuevamente mas tarde."; break;
			case 9833: this.msg = "No se ha podido actualizar el Titulo de Propiedad Automotor. Intentelo nuevamente mas tarde."; break;
			case 9834: this.msg = "No se ha podido recuperar la lista de Titulos de Propiedad Automotor. Intentelo nuevamente mas tarde."; break;
			case 9835: this.msg = "No se ha podido recuperar el Titulo de Propiedad Automotor. Intentelo nuevamente mas tarde."; break;
			case 9836: this.msg = "No se ha podido agregar la Valuación Acara. Intentelo nuevamente mas tarde."; break;
			case 9837: this.msg = "No se ha podido actualizar la Valuación Acara. Intentelo nuevamente mas tarde."; break;
			case 9838: this.msg = "No se ha podido eliminar la Valuación Acara. Intentelo nuevamente mas tarde."; break;
			case 9839: this.msg = "No se ha podido recuperar la Valuación Acara. Intentelo nuevamente mas tarde."; break;
			case 9840: this.msg = "No se ha podido recuperar la lista de Valuaciones Acara. Intentelo nuevamente mas tarde."; break;
			
		case 9799: this.msg = "No posee Permisos suficientes para realizar la Operación. Póngase en contacto con el Administrador."; break;
		
		default: this.msg = "Ha ocurrido un error inesperado. Por favor comuniquese con el Administrador.";	 break;
		}

	}

}
