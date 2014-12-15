package com.trascender.contabilidad.gui.abmParametroRetencion;

import java.util.ArrayList;
import java.util.List;

import com.trascender.contabilidad.recurso.persistent.ParametroRetencion;
import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.model.TAbstractTableModel;

public class ParametroRetencionTableModel extends TAbstractTableModel<ParametroRetencion>{

	private static final long serialVersionUID = -6103960532797856220L;

	public ParametroRetencionTableModel() throws Exception {
		super(ParametroRetencion.class);
		List<TColumnField> locListaColumnas = this.inicializarColumnas();
		this.setListaColumnas(locListaColumnas);
	}
	
	private List<TColumnField> inicializarColumnas() throws Exception {
		List<TColumnField> locListaColumnas = new ArrayList<TColumnField>();
		try {
			locListaColumnas.add(new TColumnField("Nombre", ParametroRetencion.class.getDeclaredField("nombre")));
			locListaColumnas.add(new TColumnField("Importe", ParametroRetencion.class.getDeclaredField("importeMinimo"), Double.class));
			locListaColumnas.add(new TColumnField("Porcentaje", ParametroRetencion.class.getDeclaredField("porcentaje"), Integer.class));
			locListaColumnas.add(new TColumnField("Alicuota", ParametroRetencion.class.getDeclaredField("alicuota"), Integer.class));
			locListaColumnas.add(new TColumnField("Deducir IVA", ParametroRetencion.class.getDeclaredField("deducirIVA"), boolean.class));
//			locListaColumnas.add(new TColumn("Porcentaje", ParametroRetencion.class.getDeclaredField("porcentaje"), Integer.class));
			return locListaColumnas;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(6);
		}
	}
	
}
