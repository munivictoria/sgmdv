package com.trascender.contabilidad.gui.abmConceptoPorMora;

import java.util.ArrayList;
import java.util.List;

import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.gui.framework.model.TAbstractBusquedaModel;
import com.trascender.habilitaciones.recurso.persistent.ConceptoPorMora;
import com.trascender.habilitaciones.recurso.persistent.TipoTasa;

public class ConceptoPorMoraBusquedaModel extends TAbstractBusquedaModel<ConceptoPorMora> {
	
	private TipoTasa tipoTasa;
	
	@Override
	public List<ConceptoPorMora> buscar() throws Exception {
		List<ConceptoPorMora> locList = new ArrayList<ConceptoPorMora>();
		if (this.tipoTasa != null) {
			this.tipoTasa = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemTipoTasa().getTipoTasaPorId(this.tipoTasa.getIdTipoTasa());
			if (this.tipoTasa.getInteres() != null) {
				locList.add(this.tipoTasa.getInteres());
			}
			if (this.tipoTasa.getRecargo() != null) {
				locList.add(this.tipoTasa.getRecargo());
			}
		}
		return locList;
	}

	@Override
	public void reiniciar() {
		this.setTipoTasa(null);
		this.fireActualizarDatos();
	}

	public TipoTasa getTipoTasa() {
		return tipoTasa;
	}

	public void setTipoTasa(TipoTasa tipoTasa) {
		this.tipoTasa = tipoTasa;
	}

}
