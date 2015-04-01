package com.trascender.contabilidad.gui.abmAsociacionCuentaRefinanciacion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.AsociacionRefinanciacion;
import com.trascender.framework.recurso.transients.Periodo;
import com.trascender.gui.framework.model.TAbstractABMModel;
import com.trascender.saic.recurso.persistent.ParametroAsociacion;

public class AsociacionRefinanciacionABMModel extends TAbstractABMModel<AsociacionRefinanciacion> {
	List<ParametroAsociacion> ListaParametrosAsociacion = new ArrayList<ParametroAsociacion>();
	
	@Override
	public void agregar() throws Exception {
		System.out.println("Cantidad de parametros para mandar a logica : " + this.ListaParametrosAsociacion.size());
		Set<ParametroAsociacion> locSetParametroAsociacion = new HashSet<ParametroAsociacion>();
		for (ParametroAsociacion parametroAsociacion : this.ListaParametrosAsociacion) {
			locSetParametroAsociacion.add(parametroAsociacion);
		}
		
		this.objetoABM = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().addAsociacionRefinanciacion(locSetParametroAsociacion, null);
	}

	@Override
	public void eliminar() throws Exception {
		ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().deleteAsociacionRefinanciacion(this.objetoABM);
	}

	@Override
	public void modificar() throws Exception {
//		this.objetoABM = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().updateCuentaRefinanciacion(this.objetoABM.getListaParametrosAsociacion(), this.objetoABM.getPeriodo());
	}
	
	public Periodo getPeriodo() {
		return null;
	}

	public void setPeriodo(Periodo periodo) {
//		this.objetoABM.setPeriodo(periodo);
	}

	public List<ParametroAsociacion> getListaParametrosAsociacion() {
		return ListaParametrosAsociacion;
	}

	public void setListaParametrosAsociacion(
			List<ParametroAsociacion> listaParametrosAsociacion) {
		ListaParametrosAsociacion = listaParametrosAsociacion;
	}	
}
