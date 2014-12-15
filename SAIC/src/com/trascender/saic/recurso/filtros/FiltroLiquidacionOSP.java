package com.trascender.saic.recurso.filtros;

import com.trascender.catastro.recurso.persistent.Calle;
import com.trascender.catastro.recurso.persistent.Cuadra;
import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.habilitaciones.recurso.persistent.osp.ServicioOSP;

/**
 * 
 * @author nico
 *
 */
public class FiltroLiquidacionOSP extends FiltroLiquidacionTasa {

	private static final long serialVersionUID = 6872023534558011383L;
	
	private Cuadra cuadra;
	private Calle calle;
	private ServicioOSP servicioOSP;
	private Parcela parcela;
	private Boolean servicioMedido;
	
	public Cuadra getCuadra() {
		return cuadra;
	}
	
	public void setCuadra(Cuadra cuadra) {
		this.cuadra = cuadra;
	}
	
	public Calle getCalle() {
		return calle;
	}
	
	public void setCalle(Calle calle) {
		this.calle = calle;
	}
	
	public ServicioOSP getServicioOSP() {
		return servicioOSP;
	}
	
	public void setServicioOSP(ServicioOSP servicioOSP) {
		this.servicioOSP = servicioOSP;
	}
	
	public Parcela getParcela() {
		return parcela;
	}
	
	public void setParcela(Parcela parcela) {
		this.parcela = parcela;
	}
	
	public Boolean getServicioMedido() {
		return servicioMedido;
	}
	
	public void setServicioMedido(Boolean servicioMedido) {
		this.servicioMedido = servicioMedido;
	}
}
