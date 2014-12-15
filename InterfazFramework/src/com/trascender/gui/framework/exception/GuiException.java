package com.trascender.gui.framework.exception;

import com.trascender.framework.exception.TrascenderException;

public class GuiException extends TrascenderException {
	
	private static final long serialVersionUID = 8136103623968556229L;
	private static final int  COD_PROC = 10000; // codigo del proceso
	
	public GuiException(int pCodigo) {
		super(COD_PROC+pCodigo);
	}
	
	@Override
	protected void setMsg() {
		switch (this.getCodeTrascenderException()) {
		
		case COD_PROC+ 0: this.msg = "No se ha podido guardar la configuración. Nombre de archivo inválido."; break;
		case COD_PROC+ 1: this.msg = "No se ha podido guardar la configuración."; break;
		case COD_PROC+ 2: this.msg = "Ha ocurrido un error al intentar conectarse con el servidor. Intente nuevamente más tarde."; break;
		
		case COD_PROC+ 4: this.msg = "No se ha podido realizar la conexión con el servidor. Intente nuevamente más tarde."; break;
		case COD_PROC+ 5: this.msg = "No tiene permisos suficientes para acceder. Comuníquese el Administrador."; break;
		case COD_PROC+ 6: this.msg = "No se pudo armar una o más columnas de la tabla."; break;
		
		case COD_PROC+ 7: this.msg = "No se puede realizar la validación de los datos."; break;
		
		case COD_PROC+ 8: this.msg = "Uno o más parametros requeridos no han sido completados para la impresión del reporte."; break;
		case COD_PROC+ 9: this.msg = "No se pudo recuperar el Servicio de Impresión."; break;
		case COD_PROC+10: this.msg = "El tipo de impresora no es la adecuada para imprimir el reporte."; break;
		
		default: this.msg = "Ha ocurrido un error.";
		
		}
	}
}
