package com.trascender.contabilidad.gui.abmCuenta;

import java.util.List;

import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.contabilidad.recurso.persistent.PlanDeCuenta;
import com.trascender.contabilidad.recurso.persistent.TipoCuenta;
import com.trascender.framework.recurso.filtros.FiltroArea;
import com.trascender.framework.recurso.persistent.Area;
import com.trascender.framework.recurso.persistent.Municipalidad;
import com.trascender.gui.framework.model.TAbstractABMModel;

public class CuentaABMModel extends TAbstractABMModel<Cuenta> {

	@Override
	public void agregar() throws Exception {
		// no se guarda directamente en la base de datos. Se hace a traves de PlanDeCuenta
	}
	
	@Override
	public void modificar() throws Exception {
		// no se guarda directamente en la base de datos. Se hace a traves de PlanDeCuenta
	}
	
	@Override
	public void eliminar() throws Exception {
		// no se elimina directamente de la base de datos. Se hace a traves de PlanDeCuenta
	}

	public String getAbreviatura() {
		return this.objetoABM.getAbreviatura();
	}

	public void setAbreviatura(String abreviatura) {
		this.objetoABM.setAbreviatura(abreviatura);
	}

	public String getCodigoImputacionCompleto() {
		return this.objetoABM.getCodigoImputacionCompleto();
	}

	public void setCodigoImputacionCompleto(String codigoImputacion) {
		this.objetoABM.setCodigoImputacionCompleto(codigoImputacion);
	}

	public String getCodigoImputacion() {
		return this.objetoABM.getCodigoImputacion();
	}

	public void setCodigoImputacion(String codigoImputacion) {
		this.objetoABM.setCodigoImputacion(codigoImputacion);
	}
	
	public String getNombre() {
		return this.objetoABM.getNombre();
	}

	public void setNombre(String nombre) {
		this.objetoABM.setNombre(nombre);
	}

	public List<TipoCuenta> getListaTipoCuenta() {
		return this.objetoABM.getListaTipoCuenta();
	}

	public void setListaTipoCuenta(List<TipoCuenta> pListaTipoCuenta) {
		this.objetoABM.setListaTipoCuenta(pListaTipoCuenta);
	}

	public Cuenta getCuentaPadre() {
		return this.objetoABM.getCuentaPadre();
	}

	public void setCuentaPadre(Cuenta cuentaPadre) {
		this.objetoABM.setCuentaPadre(cuentaPadre);
	}
	
	public void setArea(Area area){
		this.objetoABM.setArea(area);
	}
	
	public Area getArea(){
		return this.objetoABM.getArea();
	}
	
	@Override
	public Cuenta getObjetoABM() {
		return this.objetoABM;
	}
	
	@Override
	public void setObjetoABM(Cuenta objetoABM) {
		this.objetoABM = objetoABM;
	}
	
	public void setPadre(Object pObj) throws Exception {
		if (pObj != null){
			if (pObj instanceof Cuenta){
				this.setPadre( (Cuenta)pObj );
			}
			else if (pObj instanceof PlanDeCuenta){
				this.setPadre( (PlanDeCuenta)pObj );
			}
		}
	}
	
	public void setPadre(Cuenta pCuenta){
		this.objetoABM.setCuentaPadre(pCuenta);
	}
	
	public void setPadre(PlanDeCuenta pPlanDeCuenta){
		this.objetoABM.setPlanDeCuenta(pPlanDeCuenta);
	}
	
	public void armarCodigoImputacionCompleto(){
		Municipalidad locMunicipalidad = ContabilidadGUI.getInstance().getMunicipalidad();
		this.objetoABM.armarCodigoImputacionCompleto(locMunicipalidad.isNombreCuentaConcatenado());
	}
	
	public List<Area> getListaAreas(){
		List<Area> locListaRetorno = null;
		try {
			locListaRetorno = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemMunicipalidad().findArea(new FiltroArea()).getListaResultados();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return locListaRetorno;
	}
}
