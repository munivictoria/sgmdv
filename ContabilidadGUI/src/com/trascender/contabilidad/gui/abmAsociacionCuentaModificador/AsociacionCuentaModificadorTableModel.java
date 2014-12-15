package com.trascender.contabilidad.gui.abmAsociacionCuentaModificador;

import java.util.ArrayList;
import java.util.List;

import com.trascender.contabilidad.recurso.persistent.AsociacionCuenta;
import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.contabilidad.recurso.persistent.CuentaModificador;
import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.model.TAbstractTableModel;
import com.trascender.habilitaciones.recurso.persistent.TipoModificador;

public class AsociacionCuentaModificadorTableModel extends TAbstractTableModel<CuentaModificador> {

	private static final long serialVersionUID = -1587896653552425164L;

	public AsociacionCuentaModificadorTableModel() throws Exception {
		super(CuentaModificador.class);
		List<TColumnField> locListaColumnas = this.inicializarColumnas();
		this.setListaColumnas(locListaColumnas);	
	}

	private List<TColumnField> inicializarColumnas() throws Exception {
		List<TColumnField> locListaColumnas = new ArrayList<TColumnField>();
		try {
			locListaColumnas.add(new TColumnField("Modificador",CuentaModificador.class.getDeclaredField("tipoModificador"), TipoModificador.class));
			locListaColumnas.add(new TColumnField("Cuenta", AsociacionCuenta.class.getDeclaredField("cuenta"), Cuenta.class));
			return locListaColumnas;
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(6);
		}
	}

}
