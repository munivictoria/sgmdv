package com.trascender.contabilidad.gui.abmMayor;

import java.util.Calendar;
import java.util.Set;

import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.contabilidad.recurso.persistent.LineaMayor;
import com.trascender.contabilidad.recurso.persistent.Mayor;
import com.trascender.contabilidad.recurso.persistent.Mayor.Tipo;
import com.trascender.gui.framework.model.TAbstractABMModel;
import com.trascender.gui.framework.util.Conversor;

public class MayorABMModel extends TAbstractABMModel<Mayor>{

	private Integer mes;
	private Integer anio;
	
	public void generarLineaMayor() throws Exception {
		Tipo locTipo = this.objetoABM.getTipo();
		
		Calendar locCalendar = Calendar.getInstance();
		locCalendar.set(Calendar.DAY_OF_MONTH, 1);
		locCalendar.set(Calendar.MONTH, this.getMes()-1);
		locCalendar.set(Calendar.YEAR, this.getAnio());
		
		System.out.println("fecha ingresada: " + Conversor.getString(locCalendar.getTime()));
		
		this.objetoABM = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().generarMayor(locCalendar, this.getCuenta());
		this.objetoABM.setTipo(locTipo);
	}
	
	@Override
	public void agregar() throws Exception {
		this.objetoABM = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().addMayor(this.getObjetoABM());
		
	}

	@Override
	public void eliminar() throws Exception {
		ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().deleteMayor(this.getObjetoABM());
	}

	@Override
	public void modificar() throws Exception {
		this.objetoABM = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().updateMayor(this.getObjetoABM());
	}
	
	public Cuenta getCuenta() {
		return this.getObjetoABM().getCuenta();
	}

	public void setCuenta(Cuenta cuenta) {
		this.getObjetoABM().setCuenta(cuenta);
	}
	
	public Mayor.Tipo getTipo() {
		return this.getObjetoABM().getTipo();
	}

	public void setTipo(Mayor.Tipo tipo) {
		this.getObjetoABM().setTipo(tipo);
	}
	
	public Set<LineaMayor> getListaLineaMayor() {
		return this.objetoABM.getListaLineaMayor();
	}

	public void setListaLineaMayor(Set<LineaMayor> listaLineaMayor) {
		this.objetoABM.setListaLineaMayor(listaLineaMayor);
	}

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}


}
