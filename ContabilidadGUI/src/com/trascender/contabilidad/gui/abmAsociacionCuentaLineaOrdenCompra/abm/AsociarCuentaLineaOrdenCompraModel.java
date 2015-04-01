package com.trascender.contabilidad.gui.abmAsociacionCuentaLineaOrdenCompra.abm;

import com.trascender.compras.recurso.persistent.suministros.LineaOrdenCompra;
import com.trascender.framework.recurso.persistent.referencia.CuentaRfr;
import com.trascender.gui.framework.model.TAbstractABMModel;

public class AsociarCuentaLineaOrdenCompraModel extends TAbstractABMModel<LineaOrdenCompra> {
 
	
	@Override
	public void agregar() throws Exception {
	}

	@Override
	public void eliminar() throws Exception {
		
	}

	@Override
	public void modificar() throws Exception {
		
	}

	public CuentaRfr getCuentaRfr() {
		return this.objetoABM.getCuentaRfr();
	}

	public void setCuentaRfr(CuentaRfr cuentaRfr) {
		this.objetoABM.setCuentaRfr(cuentaRfr);
	}

}
