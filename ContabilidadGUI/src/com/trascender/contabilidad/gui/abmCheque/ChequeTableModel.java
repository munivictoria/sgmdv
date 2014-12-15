package com.trascender.contabilidad.gui.abmCheque;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.trascender.contabilidad.recurso.persistent.Cheque;
import com.trascender.contabilidad.recurso.persistent.MovimientoBancario;
import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.model.TAbstractTableModel;

public class ChequeTableModel extends TAbstractTableModel<Cheque> {
	
	private static final long serialVersionUID = 5513255615940373091L;
	
	public ChequeTableModel() throws Exception {
		super(Cheque.class);
		List<TColumnField> locListaColumnas = this.inicializarColumnas();
		this.setListaColumnas(locListaColumnas);
	}
	
	private List<TColumnField> inicializarColumnas() throws Exception {
		List<TColumnField> listaColumnas = new ArrayList<TColumnField>();
		try {
			listaColumnas.add(new TColumnField("Número", Cheque.class.getDeclaredField("numeroCheque")));
			listaColumnas.add(new TColumnField("Fecha Emisión", Cheque.class.getDeclaredField("fechaEmision"), Date.class));
			listaColumnas.add(new TColumnField("Fecha Pago", Cheque.class.getDeclaredField("fechaPago"), Date.class));
			listaColumnas.add(new TColumnField("Importe", MovimientoBancario.class.getDeclaredField("importe"), Double.class));
			//listaColumnas.add(new TColumn("PostDatado", Cheque.class.getDeclaredField("postdatado"), Boolean.class));
			return listaColumnas;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(6);
		}
	}

	
}
