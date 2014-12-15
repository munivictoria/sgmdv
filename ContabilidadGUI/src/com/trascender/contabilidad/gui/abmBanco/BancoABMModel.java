package com.trascender.contabilidad.gui.abmBanco;

import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.Banco;
import com.trascender.gui.framework.model.TAbstractABMModel;

public class BancoABMModel extends TAbstractABMModel<Banco> {

	@Override
	public void agregar() throws Exception {
		this.objetoABM = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConciliacionBancaria().addBanco(this.getObjetoABM());
	}

	@Override
	public void eliminar() throws Exception {
		ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConciliacionBancaria().deleteBanco(this.getObjetoABM());
	}

	@Override
	public void modificar() throws Exception {
		this.objetoABM = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConciliacionBancaria().updateBanco(this.getObjetoABM());
	}
	
	public String getNombre() {
		return this.objetoABM.getNombre();
	}
	
	public void setNombre(String nombre) {
		this.objetoABM.setNombre(nombre);
	}

	public String getSucursal() {
		return this.objetoABM.getSucursal();
	}

	public void setSucursal(String sucursal) {
		this.objetoABM.setSucursal(sucursal);
	}
	
}
