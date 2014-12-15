package com.trascender.contabilidad.gui.abmPlanDeCuenta.abmConsultar;

import java.util.ArrayList;
import java.util.List;

import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.model.TAbstractTableModel;

public class PlanDeCuentaTableModelConsultar extends TAbstractTableModel<Cuenta> {

	private static final long serialVersionUID = 4886884144617907817L;
	
	public PlanDeCuentaTableModelConsultar() throws Exception {
		super(Cuenta.class);
		List<TColumnField> locListaColumnas = this.inicializarColumnas();
		this.setListaColumnas(locListaColumnas);
	}

	private List<TColumnField> inicializarColumnas() throws TrascenderException {
		List<TColumnField> locListaColumnas = new ArrayList<TColumnField>();
		try {
			locListaColumnas.add(new TColumnField("Código de Imputación",Cuenta.class.getDeclaredField("codigoImputacionCompleto")));
			locListaColumnas.add(new TColumnField("Nombre",Cuenta.class.getDeclaredField("nombre")));
			return locListaColumnas;	
		} 
		catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(6);
		}
	}
	
}
