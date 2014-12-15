package com.trascender.caja.gui.abmMovimientoCajaChica;

import java.util.Date;

import com.trascender.caja.gui.main.CajaGUI;
import com.trascender.contabilidad.recurso.persistent.CajaChica;
import com.trascender.contabilidad.recurso.persistent.ConceptoMovimientoCajaChica;
import com.trascender.contabilidad.recurso.persistent.MovimientoCajaChica;
import com.trascender.gui.framework.model.TAbstractABMModel;

public class MovimientoCajaChicaABMModel extends TAbstractABMModel<MovimientoCajaChica> {
	
	@Override
	public void agregar() throws Exception {
		this.objetoABM = CajaGUI.getInstance().getAdminSystemsCaja().getSystemAdministracionCajaChica().addMovimientoCajaChica(this.objetoABM);
	}

	@Override
	public void eliminar() throws Exception {
		CajaGUI.getInstance().getAdminSystemsCaja().getSystemAdministracionCajaChica().deleteMovimientoCajaChica(this.objetoABM);
	}

	@Override
	public void modificar() throws Exception {
		this.objetoABM = CajaGUI.getInstance().getAdminSystemsCaja().getSystemAdministracionCajaChica().updateMovimientoCajaChica(this.objetoABM);
	}
	
	public Date getFechaHora() {
		return this.objetoABM.getFechaHora();
	}

	public void setFechaHora(Date fechaHora) {
		this.objetoABM.setFechaHora(fechaHora);
	}

	public CajaChica getCajaChica() {
		return this.objetoABM.getCajaChica();
	}

	public void setCajaChica(CajaChica cajaChica) {
		this.objetoABM.setCajaChica(cajaChica);
	}

	public ConceptoMovimientoCajaChica getConceptoMovimiento() {
		return this.objetoABM.getConceptoMovimiento();
	}

	public void setConceptoMovimiento(ConceptoMovimientoCajaChica conceptoMovimiento) {
		this.objetoABM.setConceptoMovimiento(conceptoMovimiento);
	}

	public Double getImporte() {
		return this.objetoABM.getImporte();
	}

	public void setImporte(Double importe) {
		this.objetoABM.setImporte(importe);
	}

}
