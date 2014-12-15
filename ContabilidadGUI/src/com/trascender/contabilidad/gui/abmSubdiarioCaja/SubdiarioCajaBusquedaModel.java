package com.trascender.contabilidad.gui.abmSubdiarioCaja;

import java.util.Date;
import java.util.List;

import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.SubdiarioCaja;
import com.trascender.contabilidad.system.interfaces.SystemAdministracionConsultaContable;
import com.trascender.gui.framework.model.TAbstractBusquedaModel;

public class SubdiarioCajaBusquedaModel extends TAbstractBusquedaModel<SubdiarioCaja> {
	
	private Date fechaCreacionDesde;
	private Date fechaCreacionHasta;
	private SubdiarioCaja.Tipo tipo;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SubdiarioCaja> buscar() throws Exception {
		SystemAdministracionConsultaContable locSystem = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable();
		List<SubdiarioCaja> locList = locSystem.findListaSubdiarioCaja(this.getTipo(), this.getFechaCreacionDesde(), this.getFechaCreacionHasta());
		return locList;
	}
	
	@Override
	public void reiniciar() {
		this.setFechaCreacionDesde(null);
		this.setFechaCreacionHasta(null);
		this.setTipo(null);
		
		this.fireActualizarDatos();
	}

	public Date getFechaCreacionDesde() {
		return fechaCreacionDesde;
	}

	public void setFechaCreacionDesde(Date fechaCreacionDesde) {
		this.fechaCreacionDesde = fechaCreacionDesde;
	}

	public Date getFechaCreacionHasta() {
		return fechaCreacionHasta;
	}

	public void setFechaCreacionHasta(Date fechaCreacionHasta) {
		this.fechaCreacionHasta = fechaCreacionHasta;
	}

	public SubdiarioCaja.Tipo getTipo() {
		return tipo;
	}

	public void setTipo(SubdiarioCaja.Tipo tipo) {
		this.tipo = tipo;
	}

}
