package com.trascender.contabilidad.gui.abmAsociacionCuentaLineaOrdenCompra;

import java.util.List;

import com.trascender.compras.recurso.persistent.suministros.LineaOrdenCompra;
import com.trascender.compras.recurso.persistent.suministros.OrdenCompra;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.gui.framework.model.TAbstractABMModel;

public class AsociacionCuentaLineaOrdenCompraABMModel extends TAbstractABMModel<OrdenCompra>{

	@Override
	public void agregar() throws Exception {
		
	}

	@Override
	public void eliminar() throws Exception {
		
	}

	@Override
	public void modificar() throws Exception {
		this.objetoABM = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionOrdenCompra().updateOrdenCompra(this.getObjetoABM());
	}

	public List<LineaOrdenCompra> getListaLineaOrdenCompra() {
		return this.objetoABM.getListaLineaOrdenCompra();
	}

	public void setListaLineaOrdenCompra(
			List<LineaOrdenCompra> listaLineaOrdenCompra) {
		this.objetoABM.setListaLineaOrdenCompra(listaLineaOrdenCompra);
		this.fireActualizarDatos();
	}

}
