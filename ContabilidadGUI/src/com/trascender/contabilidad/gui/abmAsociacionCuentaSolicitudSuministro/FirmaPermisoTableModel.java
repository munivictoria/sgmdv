package com.trascender.contabilidad.gui.abmAsociacionCuentaSolicitudSuministro;

import java.util.ArrayList;
import java.util.List;

import com.trascender.compras.recurso.persistent.suministros.FirmaPermisoSolicitud;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.model.TAbstractTableModel;

public class FirmaPermisoTableModel extends TAbstractTableModel<FirmaPermisoSolicitud>{

	private static final long serialVersionUID = -6602533942337453876L;

	public FirmaPermisoTableModel() throws Exception {
		super(FirmaPermisoSolicitud.class);
		List<TColumnField> locListaColumnas = this.inicializarColumnas();
		this.setListaColumnas(locListaColumnas);
	}

	private List<TColumnField> inicializarColumnas() throws Exception {
		List<TColumnField> listaColumnas = new ArrayList<TColumnField>();
		try {
			listaColumnas.add(new TColumnField("Firma", FirmaPermisoSolicitud.class.getDeclaredField("firmaPermiso"), Usuario.class));
//			listaColumnas.add(new TColumn("Usuario", FirmaPermiso.class.getDeclaredField("usuario"), Usuario.class));
//			listaColumnas.add(new TColumn("Fecha/Hora", FirmaPermiso.class.getDeclaredField("fechaHora"), Date.class));
//			listaColumnas.add(new TColumn("Comentarios", FirmaPermiso.class.getDeclaredField("comentario")));
			return listaColumnas;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(6);
		}
	}
	
}
