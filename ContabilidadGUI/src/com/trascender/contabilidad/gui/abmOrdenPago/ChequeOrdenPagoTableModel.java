package com.trascender.contabilidad.gui.abmOrdenPago;

import java.util.ArrayList;
import java.util.List;

import com.trascender.contabilidad.recurso.persistent.Cheque;
import com.trascender.contabilidad.recurso.persistent.MovimientoBancario;
import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.model.TAbstractTableModel;

public class ChequeOrdenPagoTableModel extends TAbstractTableModel<Cheque>{
	
	private static final long serialVersionUID = -3215566133889848628L;

	public ChequeOrdenPagoTableModel() throws Exception {
		super(Cheque.class);
		List<TColumnField> locListaColumnas = this.inicializarColumnas();
		this.setListaColumnas(locListaColumnas);
	}

	private List<TColumnField> inicializarColumnas() throws Exception {
		List<TColumnField> locListaColumnas = new ArrayList<TColumnField>();
		try {
			locListaColumnas.add(new TColumnField("Nro. Cheque", Cheque.class.getDeclaredField("numeroCheque")));
			locListaColumnas.add(new TColumnField("Importe", MovimientoBancario.class.getDeclaredField("importe"), Double.class));
			return locListaColumnas;
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(6);
		}
	}
}
