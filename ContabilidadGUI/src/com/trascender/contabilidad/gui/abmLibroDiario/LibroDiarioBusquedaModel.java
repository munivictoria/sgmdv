package com.trascender.contabilidad.gui.abmLibroDiario;

import java.util.List;

import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.LibroDiario;
import com.trascender.contabilidad.system.interfaces.SystemAdministracionConsultaContable;
import com.trascender.gui.framework.model.TAbstractBusquedaModel;

public class LibroDiarioBusquedaModel extends TAbstractBusquedaModel<LibroDiario> {
	
	private String codigoAutorizacion;
	private String numero;
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<LibroDiario> buscar() throws Exception {
		SystemAdministracionConsultaContable locSystem = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable();
		List<LibroDiario> locLista = locSystem.findListaLibroDiario(this.getCodigoAutorizacion(), this.getNumero());
		return locLista;
	}

	@Override
	public void reiniciar() {
		this.setCodigoAutorizacion(null);
		this.setNumero(null);
		
		this.fireActualizarDatos();
	}

	public String getCodigoAutorizacion() {
		return codigoAutorizacion;
	}

	public void setCodigoAutorizacion(String codigoAutorizacion) {
		this.codigoAutorizacion = codigoAutorizacion;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

}
