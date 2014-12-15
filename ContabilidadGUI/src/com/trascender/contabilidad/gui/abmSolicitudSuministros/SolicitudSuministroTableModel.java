package com.trascender.contabilidad.gui.abmSolicitudSuministros;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.trascender.compras.recurso.persistent.suministros.SolicitudSuministro;
import com.trascender.compras.recurso.persistent.suministros.SolicitudSuministro.Estado;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.model.TAbstractTableModel;

public class SolicitudSuministroTableModel extends TAbstractTableModel<SolicitudSuministro> {

	private static final long serialVersionUID = -4399697089651888813L;

	public SolicitudSuministroTableModel() throws Exception {
		super(SolicitudSuministro.class);
		List<TColumnField> locListaColumnas = this.InicializarColumnas();
		this.setListaColumnas(locListaColumnas);
	}

	private List<TColumnField> InicializarColumnas() throws TrascenderException {
		List<TColumnField> locListaColumnas = new ArrayList<TColumnField>();
		try {
			locListaColumnas.add(new TColumnField("Número", SolicitudSuministro.class.getDeclaredField("numero"), Integer.class));
			locListaColumnas.add(new TColumnField("Fecha Emisión", SolicitudSuministro.class.getDeclaredField("fechaEmision"), Date.class));
			locListaColumnas.add(new TColumnField("Descripción", SolicitudSuministro.class.getDeclaredField("descripcion")));
			locListaColumnas.add(new TColumnField("Productos Involucrados", SolicitudSuministro.class.getDeclaredField("bienes")));
			locListaColumnas.add(new TColumnField("Estado", SolicitudSuministro.class.getDeclaredField("estado"), Estado.class));
			locListaColumnas.add(new TColumnField("Usuario", SolicitudSuministro.class.getDeclaredField("usuario")));
			
			return locListaColumnas;
		} catch (Exception e) {
			e.printStackTrace();
			throw new GuiException(6);
		}
	}
	
}
