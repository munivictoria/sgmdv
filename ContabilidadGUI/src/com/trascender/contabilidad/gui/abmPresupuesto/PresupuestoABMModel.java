package com.trascender.contabilidad.gui.abmPresupuesto;

import java.util.Set;

import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.LineaPresupuesto;
import com.trascender.contabilidad.recurso.persistent.Presupuesto;
import com.trascender.contabilidad.recurso.persistent.Presupuesto.Estado;
import com.trascender.contabilidad.recurso.persistent.Presupuesto.Tipo;
import com.trascender.framework.recurso.persistent.DigestoMunicipal;
import com.trascender.gui.framework.model.TAbstractABMModel;

public class PresupuestoABMModel extends TAbstractABMModel<Presupuesto> {

	@Override
	public void agregar() throws Exception {
		this.objetoABM = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().addPresupuesto(this.getObjetoABM());
	}

	@Override
	public void modificar() throws Exception {
		this.objetoABM = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().updatePresupuesto(this.getObjetoABM());
	}

	@Override
	public void eliminar() throws Exception {
		ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().deletePresupuesto(this.getObjetoABM());
	}

	public Estado getEstado() {
		return this.objetoABM.getEstado();
	}

	public void setEstado(Estado estado) {
		this.objetoABM.setEstado(estado);
	}

	public Set<LineaPresupuesto> getLineaPresupuesto() {
		return this.objetoABM.getLineaPresupuesto();
	}

	public void setLineaPresupuesto(Set<LineaPresupuesto> lineaPresupuesto) {
		this.objetoABM.setLineaPresupuesto(lineaPresupuesto);
	}

	public String getNombre() {
		return this.objetoABM.getNombre();
	}

	public void setNombre(String nombre) {
		this.objetoABM.setNombre(nombre);
	}

	public Tipo getTipo() {
		return this.objetoABM.getTipo();
	}

	public void setTipo(Tipo tipo) {
		this.objetoABM.setTipo(tipo);
	}

	public DigestoMunicipal getDigestoMunicipal() {
		return this.objetoABM.getDigestoMunicipal();
	}

	public void setDigestoMunicipal(DigestoMunicipal digestoMunicipal) {
		this.objetoABM.setDigestoMunicipal(digestoMunicipal);
	}

	public void setAnio(Integer pAnio){
		this.objetoABM.setAnio(pAnio);
	}

	public Integer getAnio(){
		return this.objetoABM.getAnio();
	}

}
