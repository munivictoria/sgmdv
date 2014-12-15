package com.trascender.framework.recurso.filtros;

import java.util.Date;

import com.trascender.framework.recurso.persistent.Contrato;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.util.FiltroAbstracto;

public class FiltroContrato extends FiltroAbstracto<Contrato>{

	public FiltroContrato(Persona pPPersona, Date pPFechaInicioDesde,
			Date pPFechaInicioHasta, Date pPFechaFinDesde, Date pPFechaFinHasta) {
		persona = pPPersona;
		fechaInicioDesde = pPFechaInicioDesde;
		fechaInicioHasta = pPFechaInicioHasta;
		fechaFinDesde = pPFechaFinDesde;
		fechaFinHasta = pPFechaFinHasta;
	}
	
	public FiltroContrato() {
	}
	
	
	private static final long serialVersionUID = 5113749595719608044L;
	
	
	private Persona persona;
	private Date fechaInicioDesde;
	private Date fechaInicioHasta;
	private Date fechaFinDesde; 
	private Date fechaFinHasta;
	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona pPersona) {
		persona = pPersona;
	}

	public Date getFechaInicioDesde() {
		return fechaInicioDesde;
	}

	public void setFechaInicioDesde(Date pFechaInicioDesde) {
		fechaInicioDesde = pFechaInicioDesde;
	}

	public Date getFechaInicioHasta() {
		return fechaInicioHasta;
	}

	public void setFechaInicioHasta(Date pFechaInicioHasta) {
		fechaInicioHasta = pFechaInicioHasta;
	}

	public Date getFechaFinDesde() {
		return fechaFinDesde;
	}

	public void setFechaFinDesde(Date pFechaFinDesde) {
		fechaFinDesde = pFechaFinDesde;
	}

	public Date getFechaFinHasta() {
		return fechaFinHasta;
	}

	public void setFechaFinHasta(Date pFechaFinHasta) {
		fechaFinHasta = pFechaFinHasta;
	}
	
	
	
	
	

}
