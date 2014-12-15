package com.trascender.contabilidad.gui.abmAsientoContable;

import java.util.Date;
import java.util.Set;

import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.AsientoContable;
import com.trascender.contabilidad.recurso.persistent.FolioLibroDiario;
import com.trascender.contabilidad.recurso.persistent.LibroDiario;
import com.trascender.contabilidad.recurso.persistent.LineaAsientoContable;
import com.trascender.contabilidad.recurso.persistent.SubdiarioCaja;
import com.trascender.contabilidad.system.interfaces.SystemAdministracionConsultaContable;
import com.trascender.gui.framework.model.TAbstractABMModel;

public class AsientoContableABMModel extends TAbstractABMModel<AsientoContable> {

	private LibroDiario libroDiario;
	private Set<LineaAsientoContable> lineaAsientoContable;
	//private Boolean subdiarioCajaTipoIngreso = false;

	public void generarAsientoContable() throws Exception {
//		this.subdiarioCajaTipoIngreso = false;
		if (this.getTipoSubdiarioCaja() != null) {
//			if (this.getTipoSubdiarioCaja().equals(SubdiarioCaja.Tipo.INGRESO)) {
//				this.subdiarioCajaTipoIngreso = true;
//			}else {
//				this.subdiarioCajaTipoIngreso = false;
//			}
			SystemAdministracionConsultaContable locSystem = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable();
			this.objetoABM = locSystem.traerSubdiarioCaja(this.getFecha(), this.getTipoSubdiarioCaja());
		}
	}

	@Override
	public void agregar() throws Exception {
//		if (this.getTipoSubdiarioCaja() != null) {
//			if (this.getTipoSubdiarioCaja().equals(SubdiarioCaja.Tipo.COMPRAS)) {
//				this.objetoABM.setTipo(AsientoContable.Tipo.DEVENGAMIENTO);
//			}
//			if (this.getTipoSubdiarioCaja().equals(SubdiarioCaja.Tipo.EGRESO)) {
//				this.objetoABM.setTipo(AsientoContable.Tipo.PAGO);
//			}
//			if (this.getTipoSubdiarioCaja().equals(SubdiarioCaja.Tipo.INGRESO)) {
//				this.objetoABM.setTipo(AsientoContable.Tipo.COBRO);
//			}
//			if (this.getTipoSubdiarioCaja().equals(SubdiarioCaja.Tipo.PRESUPUESTARIO)) {
//				this.objetoABM.setTipo(AsientoContable.Tipo.PRESUPUESTARIO);
//			}
//		} else {
//			this.objetoABM.setTipo(AsientoContable.Tipo.MANUAL);
//		}
		
		SystemAdministracionConsultaContable locSystem = ContabilidadGUI
				.getInstance().getAdminSystemsContabilidad()
				.getSystemAdministracionConsultaContable();
		this.objetoABM = locSystem.addAsientoContable(this.objetoABM);
	}

	@Override
	public void eliminar() throws Exception {
		SystemAdministracionConsultaContable locSystem = ContabilidadGUI
				.getInstance().getAdminSystemsContabilidad()
				.getSystemAdministracionConsultaContable();
		locSystem.deleteAsientoContable(this.objetoABM);
	}

	@Override
	public void modificar() throws Exception {
//		if (this.getTipoSubdiarioCaja() != null) {
//			if (this.getTipoSubdiarioCaja().equals(SubdiarioCaja.Tipo.COMPRAS)) {
//				this.objetoABM.setTipo(AsientoContable.Tipo.DEVENGAMIENTO);
//			}
//			if (this.getTipoSubdiarioCaja().equals(SubdiarioCaja.Tipo.EGRESO)) {
//				this.objetoABM.setTipo(AsientoContable.Tipo.PAGO);
//			}
//			if (this.getTipoSubdiarioCaja().equals(SubdiarioCaja.Tipo.INGRESO)) {
//				this.objetoABM.setTipo(AsientoContable.Tipo.COBRO);
//			}
//			if (this.getTipoSubdiarioCaja().equals(SubdiarioCaja.Tipo.PRESUPUESTARIO)) {
//				this.objetoABM.setTipo(AsientoContable.Tipo.PRESUPUESTARIO);
//			}
//		} else {
//			this.objetoABM.setTipo(AsientoContable.Tipo.MANUAL);
//		}
		
		SystemAdministracionConsultaContable locSystem = ContabilidadGUI
				.getInstance().getAdminSystemsContabilidad()
				.getSystemAdministracionConsultaContable();
		this.objetoABM = locSystem.updateAsientoContable(this.objetoABM);
	}

	public SubdiarioCaja.Tipo getTipoSubdiarioCaja() {
		AsientoContable.Tipo locTipo = this.objetoABM.getTipo();
		if(locTipo == null){
			return null;
		} else if(locTipo.equals(AsientoContable.Tipo.DEVENGAMIENTO)){
			return SubdiarioCaja.Tipo.COMPRAS;
		} else if(locTipo.equals(AsientoContable.Tipo.PAGO)){
			return SubdiarioCaja.Tipo.EGRESO;
		} else if(locTipo.equals(AsientoContable.Tipo.COBRO)){
			return SubdiarioCaja.Tipo.INGRESO;
		} else if(locTipo.equals(AsientoContable.Tipo.PRESUPUESTARIO)){
			return SubdiarioCaja.Tipo.PRESUPUESTARIO;
		}
		return null;
	}

	public void setTipoSubdiarioCaja(SubdiarioCaja.Tipo tipoSubdiarioCaja) {
		if (tipoSubdiarioCaja == null){
			this.objetoABM.setTipo(null);
		} else if (tipoSubdiarioCaja.equals(SubdiarioCaja.Tipo.COMPRAS)) {
			this.objetoABM.setTipo(AsientoContable.Tipo.DEVENGAMIENTO);
		} else if (tipoSubdiarioCaja.equals(SubdiarioCaja.Tipo.EGRESO)) {
			this.objetoABM.setTipo(AsientoContable.Tipo.PAGO);
		} else if (tipoSubdiarioCaja.equals(SubdiarioCaja.Tipo.INGRESO)) {
			this.objetoABM.setTipo(AsientoContable.Tipo.COBRO);
		} else if (tipoSubdiarioCaja.equals(SubdiarioCaja.Tipo.PRESUPUESTARIO)) {
			this.objetoABM.setTipo(AsientoContable.Tipo.PRESUPUESTARIO);
		}
	}
	
	public LibroDiario getLibroDiario() {
		return libroDiario;
	}

	public void setLibroDiario(LibroDiario libroDiario) {
		this.libroDiario = libroDiario;
	}

	public Date getFecha() {
		return this.objetoABM.getFecha();
	}

	public void setFecha(Date fecha) {
		this.objetoABM.setFecha(fecha);
	}

	public FolioLibroDiario getFolioLibroDiario() {
		return this.objetoABM.getFolioLibroDiario();
	}

	public void setFolioLibroDiario(FolioLibroDiario folioLibroDiario) {
		this.objetoABM.setFolioLibroDiario(folioLibroDiario);
	}

	public Integer getNumeroAsiento() {
		return this.objetoABM.getNumeroAsiento();
	}

	public void setNumeroAsiento(Integer numeroAsiento) {
		this.objetoABM.setNumeroAsiento(numeroAsiento);
	}

	public String getObservaciones() {
		return this.objetoABM.getObservaciones();
	}

	public void setObservaciones(String observaciones) {
		this.objetoABM.setObservaciones(observaciones);
	}

	public Set<LineaAsientoContable> getLineaAsientoContable() {
		return this.objetoABM.getLineasAsientoContable();
	}

	public void setLineaAsientoContable(
			Set<LineaAsientoContable> lineaAsientoContable) {
		this.objetoABM.setLineasAsientoContable(lineaAsientoContable);
	}

//	public Boolean getSubdiarioCajaTipoIngreso() {
//		return subdiarioCajaTipoIngreso;
//	}

}
