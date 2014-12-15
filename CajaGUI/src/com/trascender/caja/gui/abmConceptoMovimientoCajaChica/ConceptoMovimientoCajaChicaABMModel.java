package com.trascender.caja.gui.abmConceptoMovimientoCajaChica;

import com.trascender.caja.gui.main.CajaGUI;
import com.trascender.contabilidad.recurso.persistent.ConceptoMovimientoCajaChica;
import com.trascender.contabilidad.recurso.persistent.ConceptoMovimientoCajaChica.Tipo;
import com.trascender.gui.framework.model.TAbstractABMModel;

public class ConceptoMovimientoCajaChicaABMModel extends TAbstractABMModel<ConceptoMovimientoCajaChica> {
	
	@Override
	public void agregar() throws Exception {
		this.objetoABM = CajaGUI.getInstance().getAdminSystemsCaja().getSystemAdministracionCajaChica().addConceptoMovimientoCajaChica(this.getObjetoABM());
	}

	@Override
	public void eliminar() throws Exception {
		CajaGUI.getInstance().getAdminSystemsCaja().getSystemAdministracionCajaChica().deleteConceptoMovimientoCajaChica(this.getObjetoABM());
	}

	@Override
	public void modificar() throws Exception {
		this.objetoABM = CajaGUI.getInstance().getAdminSystemsCaja().getSystemAdministracionCajaChica().updateConceptoMovimientoCajaChica(this.getObjetoABM());
	}

	public String getDescipcion() {
		return this.objetoABM.getDescripcion();
	}

	public void setDescipcion(String descipcion) {
		this.objetoABM.setDescripcion(descipcion);
	}

	public String getNombre() {
		return this.objetoABM.getNombre();
	}

	public void setNombre(String nombre) {
		this.objetoABM.setNombre(nombre);
	}

	public Tipo getTipo() {
		return this.objetoABM.getTipo();
	}

	public void setTipo(Tipo tipo) {
		this.objetoABM.setTipo(tipo);
	}


}
