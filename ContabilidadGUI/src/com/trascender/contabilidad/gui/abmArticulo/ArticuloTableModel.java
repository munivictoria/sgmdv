package com.trascender.contabilidad.gui.abmArticulo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.trascender.compras.recurso.persistent.inventario.Articulo;
import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.model.TAbstractTableModel;

public class ArticuloTableModel extends TAbstractTableModel<Articulo>{

	private static final long serialVersionUID = -4906793960074549561L;

	public ArticuloTableModel() throws Exception {
		super(Articulo.class);
		this.setListaColumnas(inicializarColumnas());
	}
	
	private List<TColumnField> inicializarColumnas() throws Exception{
		List<TColumnField> listaColumnas = new ArrayList<TColumnField>();
		try{
			listaColumnas.add(new TColumnField("Codigo", Articulo.class.getDeclaredField("codigo"), String.class));
			listaColumnas.add(new TColumnField("Nombre", Articulo.class.getDeclaredField("nombre"), String.class));
			listaColumnas.add(new TColumnField("Fecha entrada servicio", Articulo.class.getDeclaredField("fechaPuestaServicio"), Date.class));
			listaColumnas.add(new TColumnField("Estado contable", Articulo.class.getDeclaredField("estadoContable"), Articulo.EstadoContable.class));
			return listaColumnas;
		} catch (Exception e){
			e.printStackTrace();
			throw new GuiException(6);
		}
		
	}

}
