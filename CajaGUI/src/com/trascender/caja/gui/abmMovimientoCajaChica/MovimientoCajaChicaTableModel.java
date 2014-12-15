package com.trascender.caja.gui.abmMovimientoCajaChica;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.trascender.contabilidad.recurso.persistent.CajaChica;
import com.trascender.contabilidad.recurso.persistent.ConceptoMovimientoCajaChica;
import com.trascender.contabilidad.recurso.persistent.MovimientoCajaChica;
import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.model.TAbstractTableModel;

public class MovimientoCajaChicaTableModel extends TAbstractTableModel<MovimientoCajaChica> {

	private static final long serialVersionUID = -4001354071294087127L;
	
	public MovimientoCajaChicaTableModel() throws Exception {
		super(MovimientoCajaChica.class);
		List<TColumnField> locListaColumnas = this.inicializarColumnas();
		this.setListaColumnas(locListaColumnas);
	}

	private List<TColumnField> inicializarColumnas() throws Exception {
		List<TColumnField> locListaColumnas = new ArrayList<TColumnField>();
		try {
			locListaColumnas.add(new TColumnField("Fecha",MovimientoCajaChica.class.getDeclaredField("fechaHora"),Date.class));
			locListaColumnas.add(new TColumnField("Concepto",MovimientoCajaChica.class.getDeclaredField("conceptoMovimiento"),ConceptoMovimientoCajaChica.class));
			locListaColumnas.add(new TColumnField("Importe",MovimientoCajaChica.class.getDeclaredField("importe"),Double.class));
			locListaColumnas.add(new TColumnField("Caja Chica",MovimientoCajaChica.class.getDeclaredField("cajaChica"),CajaChica.class));
			return locListaColumnas;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(6);
		}
	}
}
