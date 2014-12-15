package com.trascender.contabilidad.gui.abmOrdenPago;

import java.util.ArrayList;
import java.util.List;

import com.trascender.contabilidad.recurso.persistent.Debito;
import com.trascender.contabilidad.recurso.persistent.MovimientoBancario;
import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.model.TAbstractTableModel;

public class DebitoTableModel extends TAbstractTableModel<Debito>{

	private static final long serialVersionUID = -7900406937670990767L;

	public DebitoTableModel() throws Exception {
		super(Debito.class);
		List<TColumnField> locListaColumnas = this.inicializarColumnas();
		this.setListaColumnas(locListaColumnas);
	}

	private List<TColumnField> inicializarColumnas() throws Exception {
		List<TColumnField> locListaColumnas = new ArrayList<TColumnField>();
		try {
			locListaColumnas.add(new TColumnField("Cuenta Bancaria", MovimientoBancario.class.getDeclaredField("cuentaBancaria")));
			locListaColumnas.add(new TColumnField("Importe", MovimientoBancario.class.getDeclaredField("importe"), Double.class));
			return locListaColumnas;
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(6);
		}
	}
}
