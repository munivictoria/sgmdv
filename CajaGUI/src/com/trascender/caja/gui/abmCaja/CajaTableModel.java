package com.trascender.caja.gui.abmCaja;

import java.util.ArrayList;
import java.util.List;

import com.trascender.contabilidad.recurso.persistent.Caja;
import com.trascender.contabilidad.recurso.persistent.Caja.Estado;
import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.model.TAbstractTableModel;
/**
 * @author adrian
 */
public class CajaTableModel extends TAbstractTableModel<Caja> {

	private static final long serialVersionUID = -1679444987996444984L;
	
	public CajaTableModel() throws Exception {
		super(Caja.class);
		List<TColumnField> locListaColumnas = this.inicializarColumnas();
		this.setListaColumnas(locListaColumnas);
	}

	private List<TColumnField> inicializarColumnas() throws Exception {
		List<TColumnField> locListaColumnas = new ArrayList<TColumnField>();
		try {
			locListaColumnas.add(new TColumnField("Nombre",Caja.class.getDeclaredField("nombre")));
			locListaColumnas.add(new TColumnField("Numero",Caja.class.getDeclaredField("numero")));
			locListaColumnas.add(new TColumnField("IP",Caja.class.getDeclaredField("ipAddress")));
			locListaColumnas.add(new TColumnField("Puerto",Caja.class.getDeclaredField("puertoImpresion")));
			locListaColumnas.add(new TColumnField("Estado",Caja.class.getDeclaredField("estado"),Estado.class));
			return locListaColumnas;
		} 
		catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(6);
		}
	}

}
