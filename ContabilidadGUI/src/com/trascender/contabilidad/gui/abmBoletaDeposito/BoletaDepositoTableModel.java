package com.trascender.contabilidad.gui.abmBoletaDeposito;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.trascender.contabilidad.recurso.persistent.BoletaDeposito;
import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.model.TAbstractTableModel;

public class BoletaDepositoTableModel extends TAbstractTableModel<BoletaDeposito> {

	
	private static final long serialVersionUID = 5513255615940373091L;

	public BoletaDepositoTableModel() throws Exception {
		super(BoletaDeposito.class);
		List<TColumnField> locListaColumnas = this.inicializarColumnas();
		this.setListaColumnas(locListaColumnas);
	}

	private List<TColumnField> inicializarColumnas() throws Exception {
		List<TColumnField> listaColumnas = new ArrayList<TColumnField>();
		try {
			listaColumnas.add(new TColumnField("Número", BoletaDeposito.class.getDeclaredField("numeroBoleta")));
			listaColumnas.add(new TColumnField("Fecha", BoletaDeposito.class.getDeclaredField("fecha"), Date.class));
			listaColumnas.add(new TColumnField("Importe", BoletaDeposito.class.getDeclaredField("importe"), Double.class));
			return listaColumnas;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(6);
		}
	
	}
	
	
}
