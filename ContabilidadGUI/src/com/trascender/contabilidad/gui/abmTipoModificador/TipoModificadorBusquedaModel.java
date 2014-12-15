package com.trascender.contabilidad.gui.abmTipoModificador;

import java.util.ArrayList;
import java.util.List;

import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.gui.framework.model.TAbstractBusquedaModel;
import com.trascender.habilitaciones.recurso.persistent.TipoModificador;
import com.trascender.habilitaciones.recurso.persistent.TipoTasa;

public class TipoModificadorBusquedaModel extends TAbstractBusquedaModel<TipoModificador> {
	
	private TipoTasa tipoTasa;
	
	@Override
	public List<TipoModificador> buscar() throws Exception {
		List<TipoModificador> locList = new ArrayList<TipoModificador>();
		if (this.tipoTasa != null) {
			this.tipoTasa = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemTipoTasa().getTipoTasaPorId(this.tipoTasa.getIdTipoTasa());
			locList.addAll(this.tipoTasa.getListaModificadores());
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
