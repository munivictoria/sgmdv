package com.trascender.contabilidad.gui.abmDocumentoRefinanciacion;

import java.util.List;

import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.gui.framework.model.TAbstractBusquedaModel;
import com.trascender.saic.recurso.persistent.refinanciacion.DocumentoRefinanciacion;
import com.trascender.saic.system.interfaces.SystemLiquidacionTasa;

public class DocumentoRefinanciacionBusquedaModel extends TAbstractBusquedaModel<DocumentoRefinanciacion> {

	private Persona persona;
	private Integer numeroRefinanciacion;
	
	@Override
	public List<DocumentoRefinanciacion> buscar() throws Exception {
		SystemLiquidacionTasa locSystem = ContabilidadGUI.getInstance().getAdminSystemsLiquidacionTasa().getSystemLiquidacionTasa();
//		List<DocumentoRefinanciacion> locList = locSystem.findListaRefinanciaciones(this.getPersona(), this.getNumeroRefinanciacion());
//		return locList;
		return null;
	}

	@Override
	public void reiniciar() {
		this.setPersona(null);
		this.setNumeroRefinanciacion(null);
		
		this.fireActualizarDatos();
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Integer getNumeroRefinanciacion() {
		return numeroRefinanciacion;
	}

	public void setNumeroRefinanciacion(Integer numeroRefinanciacion) {
		this.numeroRefinanciacion = numeroRefinanciacion;
	}
	
}
