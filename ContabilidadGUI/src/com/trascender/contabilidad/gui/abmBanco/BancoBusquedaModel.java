package com.trascender.contabilidad.gui.abmBanco;

import java.util.List;

import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.Banco;
import com.trascender.contabilidad.system.interfaces.SystemAdministracionConciliacionBancaria;
import com.trascender.gui.framework.model.TAbstractBusquedaModel;

public class BancoBusquedaModel extends TAbstractBusquedaModel<Banco>{

	private String nombre;
	private String sucursal;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Banco> buscar() throws Exception {
		SystemAdministracionConciliacionBancaria locSystem = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConciliacionBancaria();  
		List<Banco> locLista = locSystem.findListaBanco(this.getNombre(), this.getSucursal());
		return locLista;
	}

	@Override
	public void reiniciar() {
		this.setNombre(null);
		this.setSucursal(null);
		
		this.fireActualizarDatos();
	}

	public String getSucursal() {
		return sucursal;
	}

	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
