package com.trascender.contabilidad.gui.abmDebitoBancario;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.trascender.contabilidad.recurso.persistent.Debito;
import com.trascender.contabilidad.recurso.persistent.MovimientoBancario;
import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.model.TAbstractTableModel;

public class DebitoBancarioTableModel extends TAbstractTableModel<Debito>{

	private static final long serialVersionUID = 409029352477127267L;

	public DebitoBancarioTableModel() throws Exception {
		super(Debito.class);
		List<TColumnField> locListaColumnas = this.inicializarColumnas();
		this.setListaColumnas(locListaColumnas);
		
		
	}
	
	private List<TColumnField> inicializarColumnas() throws Exception {
		List<TColumnField> listaColumnas = new ArrayList<TColumnField>();
		try {
			listaColumnas.add(new TColumnField("Fecha", Debito.class.getDeclaredField("fecha"), Date.class));
			listaColumnas.add(new TColumnField("Importe", MovimientoBancario.class.getDeclaredField("importe"), Double.class));
			return listaColumnas;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(6);
		}
	}
}
