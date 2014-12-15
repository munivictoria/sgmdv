package com.trascender.caja.gui.abmMovimientoCajaChica;

import java.util.Date;
import java.util.List;

import com.trascender.caja.gui.main.CajaGUI;
import com.trascender.contabilidad.recurso.persistent.CajaChica;
import com.trascender.contabilidad.recurso.persistent.ConceptoMovimientoCajaChica;
import com.trascender.contabilidad.recurso.persistent.MovimientoCajaChica;
import com.trascender.contabilidad.system.interfaces.SystemAdministracionCajaChica;
import com.trascender.gui.framework.model.TAbstractBusquedaModel;

public class MovimientoCajaChicaBusquedaModel extends TAbstractBusquedaModel<MovimientoCajaChica> {

	private Date fechaDesde;
	private Date fechaHasta;
	private ConceptoMovimientoCajaChica conceptoMovimiento;
	private CajaChica cajaChica;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MovimientoCajaChica> buscar() throws Exception {
		SystemAdministracionCajaChica locSystem = CajaGUI.getInstance().getAdminSystemsCaja().getSystemAdministracionCajaChica();
		List<MovimientoCajaChica> locLista = locSystem.findListaMovimientoCajaChica(this.getFechaDesde(), this.getFechaHasta(), this.getConceptoMovimiento(), this.getCajaChica());
		return locLista;
	}

	@Override
	public void reiniciar() {
		this.setFechaDesde(null);
		this.setFechaHasta(null);
		this.setConceptoMovimiento(null);
		this.setCajaChica(null);
	}

	public CajaChica getCajaChica() {
		return cajaChica;
	}

	public void setCajaChica(CajaChica cajaChica) {
		this.cajaChica = cajaChica;
	}

	public ConceptoMovimientoCajaChica getConceptoMovimiento() {
		return conceptoMovimiento;
	}

	public void setConceptoMovimiento(ConceptoMovimientoCajaChica conceptoMovimiento) {
		this.conceptoMovimiento = conceptoMovimiento;
	}

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

}
