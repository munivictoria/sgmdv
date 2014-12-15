package com.trascender.framework.recurso.filtros;

import com.trascender.framework.recurso.persistent.CodigoCiiu;
import com.trascender.framework.recurso.persistent.GrupoCiiu;
import com.trascender.framework.recurso.persistent.SeccionCiiu;
import com.trascender.framework.util.FiltroAbstracto;

public class FiltroCodigoCiiu extends FiltroAbstracto<CodigoCiiu>{

	
	public FiltroCodigoCiiu() {
	}
	
	public FiltroCodigoCiiu(SeccionCiiu pSeccionCiiu, GrupoCiiu pGrupoCiiu, 	String pCodigo, String pInformacion) {
		seccionCiiu = pSeccionCiiu;
		grupoCiiu = pGrupoCiiu;
		codigo = pCodigo;
		informacion = pInformacion;
	}
	
	private static final long serialVersionUID = -2876774242564529514L;
	
	private SeccionCiiu seccionCiiu;
	private GrupoCiiu grupoCiiu;
	private String codigo; 
	private String informacion;
	
	public SeccionCiiu getSeccionCiiu() {
		return seccionCiiu;
	}
	public void setSeccionCiiu(SeccionCiiu pSeccionCiiu) {
		seccionCiiu = pSeccionCiiu;
	}
	public GrupoCiiu getGrupoCiiu() {
		return grupoCiiu;
	}
	public void setGrupoCiiu(GrupoCiiu pGrupoCiiu) {
		grupoCiiu = pGrupoCiiu;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String pCodigo) {
		codigo = pCodigo;
	}
	public String getInformacion() {
		return informacion;
	}
	public void setInformacion(String pInformacion) {
		informacion = pInformacion;
	}

}
