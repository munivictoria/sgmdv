package com.trascender.contabilidad.gui.abmAsociacionCuentaArticulo;

import java.util.ArrayList;
import java.util.List;

import com.trascender.compras.recurso.persistent.inventario.Articulo;
import com.trascender.contabilidad.recurso.persistent.AsociacionCuenta;
import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.contabilidad.recurso.persistent.CuentaArticulo;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.transients.Periodo;
import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.model.TAbstractTableModel;

public class AsociacionCuentaArticuloTableModel extends TAbstractTableModel<CuentaArticulo>{

	private static final long serialVersionUID = 311615514624104981L;

	public AsociacionCuentaArticuloTableModel()
			throws Exception {
		super(CuentaArticulo.class);
		this.setListaColumnas(this.inicializarColumnas());
	}
	
	private List<TColumnField> inicializarColumnas() throws TrascenderException {
		List<TColumnField> locListaColumnas = new ArrayList<TColumnField>();
		try {
			locListaColumnas.add(new TColumnField("Articulo",CuentaArticulo.class.getDeclaredField("articulo"), Articulo.class));
			locListaColumnas.add(new TColumnField("Cuenta", AsociacionCuenta.class.getDeclaredField("cuenta"), Cuenta.class));
			locListaColumnas.add(new TColumnField("Período", AsociacionCuenta.class.getDeclaredField("periodo"), Periodo.class));
			return locListaColumnas;
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(6);
		}
	}

}
