package com.trascender.framework.recurso.filtros;

import com.trascender.framework.recurso.persistent.Pais;
import com.trascender.framework.recurso.persistent.Provincia;
import com.trascender.framework.util.FiltroAbstracto;

public class FiltroProvincia extends FiltroAbstracto<Provincia>{

	public FiltroProvincia() {
	}
	
	public FiltroProvincia(String pProvincia, Pais pPais) {
		provincia = pProvincia;
		pais = pPais;
	}
	
	private static final long serialVersionUID = -9114538310739550140L;

	private String provincia;
	private Pais pais;
	
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String pProvincia) {
		provincia = pProvincia;
	}
	public Pais getPais() {
		return pais;
	}
	public void setPais(Pais pPais) {
		pais = pPais;
	}
	
	

}
