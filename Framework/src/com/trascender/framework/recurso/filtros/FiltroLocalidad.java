package com.trascender.framework.recurso.filtros;

import com.trascender.framework.recurso.persistent.Localidad;
import com.trascender.framework.recurso.persistent.Pais;
import com.trascender.framework.recurso.persistent.Provincia;
import com.trascender.framework.util.FiltroAbstracto;

public class FiltroLocalidad extends FiltroAbstracto<Localidad>{

	public FiltroLocalidad(String pNombre, String pCodPostal,
			Provincia pProvincia, Pais pPais) {
		nombre = pNombre;
		codPostal = pCodPostal;
		provincia = pProvincia;
		pais = pPais;
	}
	
	public FiltroLocalidad() {
	}
	
	private static final long serialVersionUID = 4442781758971937796L;
	
	private String nombre;
	private String codPostal;
	private Provincia provincia;
	private Pais pais;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String pNombre) {
		nombre = pNombre;
	}
	public String getCodPostal() {
		return codPostal;
	}
	public void setCodPostal(String pCodPostal) {
		codPostal = pCodPostal;
	}
	public Provincia getProvincia() {
		return provincia;
	}
	public void setProvincia(Provincia pProvincia) {
		provincia = pProvincia;
	}
	public Pais getPais() {
		return pais;
	}
	public void setPais(Pais pPais) {
		pais = pPais;
	}

}
