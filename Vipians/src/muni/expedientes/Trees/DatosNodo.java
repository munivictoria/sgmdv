/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

package muni.expedientes.Trees;

import java.util.List;

import muni.expedientes.Trees.TreeView.Datos;

public class DatosNodo implements TreeView.Datos {

	private String texto;
	private String id;
	private String url;
	@SuppressWarnings("rawtypes")
	private List hijos;

	@SuppressWarnings("rawtypes")
	public DatosNodo(String texto, String id, String url, List hijos) {

		this.texto = texto;
		this.id = id;
		this.url = url;
		this.hijos = hijos;

	}

	@Override
	public String getTexto() {
		return texto;
	}

	@Override
	public String getId() {
		return id;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Datos> getHijos() {
		return hijos;
	}

	@Override
	public String getUrl() {
		return url;
	}

}