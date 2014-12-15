package com.trascender.contabilidad.gui.abmAsociacionCuentaSolicitudSuministro;

import java.util.ArrayList;
import java.util.List;

import com.trascender.compras.recurso.persistent.suministros.LineaSolicitudSuministro;
import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.model.TAbstractTableModel;

public class LineaSolicitudSuministroTableModel extends TAbstractTableModel<LineaSolicitudSuministro> {
	
	private static final long serialVersionUID = 8790256656138616836L;

	public LineaSolicitudSuministroTableModel() throws Exception {
		super(LineaSolicitudSuministro.class);
		List<TColumnField> locListaColumnas = this.inicializarColumnas();
		this.setListaColumnas(locListaColumnas);
	}

	private List<TColumnField> inicializarColumnas() throws Exception {
		List<TColumnField> listaColumnas = new ArrayList<TColumnField>();
		try {
			listaColumnas.add(new TColumnField("Producto", LineaSolicitudSuministro.class.getDeclaredField("bienAsociado")));
			listaColumnas.add(new TColumnField("Cantidad", LineaSolicitudSuministro.class.getDeclaredField("cantidad"), Integer.class));
//			listaColumnas.add(new TColumn("Orden de Compra", LineaSolicitudSuministro.class.getDeclaredField("ordenCompra"), OrdenCompra.class));
			listaColumnas.add(new TColumnField("Valor Estimado", LineaSolicitudSuministro.class.getDeclaredField("valorEstimado"), Double.class));
			listaColumnas.add(new TColumnField("Cuenta", LineaSolicitudSuministro.class.getDeclaredField("cuentaRfr"), Cuenta.class));
			return listaColumnas;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(6);
		}
	}

}
