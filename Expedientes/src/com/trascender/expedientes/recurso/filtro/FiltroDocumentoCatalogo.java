/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

package com.trascender.expedientes.recurso.filtro;

import com.trascender.expedientes.enums.EstadoPlantilla;
import com.trascender.expedientes.recurso.persistent.DocumentoCatalogo;
import com.trascender.framework.util.FiltroAbstracto;

public class FiltroDocumentoCatalogo extends FiltroAbstracto<DocumentoCatalogo> {

	private static final long serialVersionUID = -767479264816049089L;

	private String nombre;
	private EstadoPlantilla estado;
	private DocumentoCatalogo.Tipo tipo;

	public FiltroDocumentoCatalogo() {
	}

	public FiltroDocumentoCatalogo(String nombre, EstadoPlantilla estado) {
		super();
		this.nombre = nombre;
		this.estado = estado;
	}

	public EstadoPlantilla getEstado() {
		return estado;
	}

	public void setEstado(EstadoPlantilla estado) {
		this.estado = estado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public DocumentoCatalogo.Tipo getTipo() {
		return tipo;
	}

	public void setTipo(DocumentoCatalogo.Tipo tipo) {
		this.tipo = tipo;
	}

}