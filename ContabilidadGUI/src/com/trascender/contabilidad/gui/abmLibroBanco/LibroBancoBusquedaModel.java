package com.trascender.contabilidad.gui.abmLibroBanco;

import java.util.List;

import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.CuentaBancaria;
import com.trascender.contabilidad.recurso.persistent.LibroBanco;
import com.trascender.contabilidad.system.interfaces.SystemAdministracionConciliacionBancaria;
import com.trascender.gui.framework.model.TAbstractBusquedaModel;

public class LibroBancoBusquedaModel extends TAbstractBusquedaModel<LibroBanco>{

	private String nombre;
	private CuentaBancaria cuentaBancaria;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<LibroBanco> buscar() throws Exception {
		SystemAdministracionConciliacionBancaria locSystem = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConciliacionBancaria();
		List<LibroBanco> listaLibroBanco = locSystem.findListaLibroBanco(this.getNombre(), this.getCuentaBancaria());
		return listaLibroBanco;
	}

	@Override
	public void reiniciar() {
		this.setNombre(null);
		this.setCuentaBancaria(null);
		
		fireActualizarDatos();
	}

	public CuentaBancaria getCuentaBancaria() {
		return cuentaBancaria;
	}

	public void setCuentaBancaria(CuentaBancaria cuentaBancaria) {
		this.cuentaBancaria = cuentaBancaria;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
