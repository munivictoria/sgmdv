package com.trascender.contabilidad.gui.abmFolioLibroDiario;

import java.util.List;

import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.FolioLibroDiario;
import com.trascender.contabilidad.recurso.persistent.LibroDiario;
import com.trascender.contabilidad.system.interfaces.SystemAdministracionConsultaContable;
import com.trascender.gui.framework.model.TAbstractBusquedaModel;

public class FolioLibroDiarioBusquedaModel extends TAbstractBusquedaModel<FolioLibroDiario> {
	
	private String numero;
	private LibroDiario libroDiario;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<FolioLibroDiario> buscar() throws Exception {
		SystemAdministracionConsultaContable locSystem = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable();
		List<FolioLibroDiario> locLista = locSystem.findListaFolioLibroDiario(this.getNumero(), this.getLibroDiario());
		return locLista;
	}

	@Override
	public void reiniciar() {
		this.setNumero(null);
		this.setLibroDiario(null);
		
		super.fireActualizarDatos();
	}
	
	public LibroDiario getLibroDiario() {
		return libroDiario;
	}
	
	public void setLibroDiario(LibroDiario libroDiario) {
		this.libroDiario = libroDiario;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

}
