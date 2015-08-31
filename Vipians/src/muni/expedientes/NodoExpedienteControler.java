/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

package muni.expedientes;

import com.trascender.expedientes.recurso.persistent.NodoExpediente;
import com.trascender.presentacion.abstracts.controller.ABMController;

public interface NodoExpedienteControler extends ABMController {

	public abstract void getValoresPorDefecto(NodoExpediente pNodoExpediente);

	public abstract boolean getValidacion(NodoExpediente pNodoExpediente);

	public abstract String getMessage();

}