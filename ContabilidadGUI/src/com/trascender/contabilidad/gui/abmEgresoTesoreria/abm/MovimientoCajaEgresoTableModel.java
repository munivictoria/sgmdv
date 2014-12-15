package com.trascender.contabilidad.gui.abmEgresoTesoreria.abm;

import java.util.ArrayList;
import java.util.List;

import com.trascender.contabilidad.recurso.persistent.DocumentoEgreso;
import com.trascender.contabilidad.recurso.persistent.MovimientoCajaEgreso;
import com.trascender.contabilidad.recurso.persistent.PlanillaDiariaCaja;
import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.model.TAbstractTableModel;

public class MovimientoCajaEgresoTableModel extends TAbstractTableModel<MovimientoCajaEgreso> {

	private static final long serialVersionUID = -5184417561426562047L;
	
	public MovimientoCajaEgresoTableModel() throws Exception {
		super(MovimientoCajaEgreso.class);
		List<TColumnField> locListaColumnas = this.inicializarColumnas();
		this.setListaColumnas(locListaColumnas);
	}

	private List<TColumnField> inicializarColumnas() throws Exception {
		List<TColumnField> locListaColumnas = new ArrayList<TColumnField>();
		try {
			locListaColumnas.add(new TColumnField("Documento Egreso", MovimientoCajaEgreso.class.getDeclaredField("documentoEgreso"), DocumentoEgreso.class));
			locListaColumnas.add(new TColumnField("Planilla", MovimientoCajaEgreso.class.getDeclaredField("planilla"), PlanillaDiariaCaja.class));
			return locListaColumnas;
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(6);
		}
	}

}
