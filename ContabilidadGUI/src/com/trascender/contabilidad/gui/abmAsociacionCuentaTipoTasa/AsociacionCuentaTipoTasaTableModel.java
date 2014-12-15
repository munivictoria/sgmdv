package com.trascender.contabilidad.gui.abmAsociacionCuentaTipoTasa;

import java.util.ArrayList;
import java.util.List;

import com.trascender.contabilidad.recurso.persistent.AsociacionCuenta;
import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.contabilidad.recurso.persistent.CuentaTipoTasa;
import com.trascender.framework.recurso.transients.Periodo;
import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.model.TAbstractTableModel;
import com.trascender.habilitaciones.recurso.persistent.TipoTasa;

public class AsociacionCuentaTipoTasaTableModel extends TAbstractTableModel<CuentaTipoTasa>  {

	private static final long serialVersionUID = 2184800465540381894L;

	public AsociacionCuentaTipoTasaTableModel() throws Exception {
		super(CuentaTipoTasa.class);
		List<TColumnField> locListaColumnas = this.inicializarColumnas();
		this.setListaColumnas(locListaColumnas);	
	}

	private List<TColumnField> inicializarColumnas() throws Exception {
		List<TColumnField> locListaColumnas = new ArrayList<TColumnField>();
		try {
			locListaColumnas.add(new TColumnField("Fórmula",CuentaTipoTasa.class.getDeclaredField("tipoTasa"), TipoTasa.class));
			locListaColumnas.add(new TColumnField("Cuenta", AsociacionCuenta.class.getDeclaredField("cuenta"), Cuenta.class));
//			locListaColumnas.add(new TColumnField("Año", AsociacionCuenta.class.getDeclaredField("anio"), Periodo.class));
			return locListaColumnas;
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(6);
		}
	}

}
