package com.trascender.contabilidad.gui.abmAsociacionCuentaConceptoIngresoVario;

import java.util.ArrayList;
import java.util.List;

import com.trascender.contabilidad.recurso.persistent.AsociacionCuenta;
import com.trascender.contabilidad.recurso.persistent.ConceptoIngresoVario;
import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.contabilidad.recurso.persistent.CuentaConceptoIngresoVario;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.transients.Periodo;
import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.model.TAbstractTableModel;

public class AsociacionCuentaConceptoIngresoVarioTableModel extends TAbstractTableModel<CuentaConceptoIngresoVario> {

	private static final long serialVersionUID = 3679543587732963781L;
	
	public AsociacionCuentaConceptoIngresoVarioTableModel() throws Exception {
		super(CuentaConceptoIngresoVario.class);
		List<TColumnField> locListaColumnas = this.inicializarColumnas();
		this.setListaColumnas(locListaColumnas);
	}

	private List<TColumnField> inicializarColumnas() throws TrascenderException {
		List<TColumnField> locListaColumnas = new ArrayList<TColumnField>();
		try {
			locListaColumnas.add(new TColumnField("Concepto",CuentaConceptoIngresoVario.class.getDeclaredField("conceptoSelladoAdministrativo"), ConceptoIngresoVario.class));
			locListaColumnas.add(new TColumnField("Cuenta", AsociacionCuenta.class.getDeclaredField("cuenta"), Cuenta.class));
			locListaColumnas.add(new TColumnField("Período", AsociacionCuenta.class.getDeclaredField("periodo"), Periodo.class));
			return locListaColumnas;
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(6);
		}
	}

}
