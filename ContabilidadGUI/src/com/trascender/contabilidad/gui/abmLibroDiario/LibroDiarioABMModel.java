package com.trascender.contabilidad.gui.abmLibroDiario;

import java.util.HashSet;
import java.util.Set;

import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.FolioLibroDiario;
import com.trascender.contabilidad.recurso.persistent.LibroDiario;
import com.trascender.gui.framework.model.TAbstractABMModel;

public class LibroDiarioABMModel extends TAbstractABMModel<LibroDiario> {
	
	private String codigoAutorizacion;
	private String numero;
	private Integer cantidadFolios;
	
	@Override
	public void agregar() throws Exception {
		this.objetoABM = new LibroDiario();
		this.objetoABM = this.getObjetoABM();
		Set<FolioLibroDiario> foliosLibroDiario = new HashSet<FolioLibroDiario>();
		if (this.getCantidadFolios() == null) this.setCantidadFolios(0);
		for (int i = 0; i < this.getCantidadFolios(); i++) {
			FolioLibroDiario folioLibroDiario = new FolioLibroDiario();
			folioLibroDiario.setNumero(String.valueOf(i+1));
			folioLibroDiario.setLibroDiario(this.objetoABM);
			foliosLibroDiario.add(folioLibroDiario);
		}
		this.objetoABM.setFoliosLibroDiario(foliosLibroDiario);
		
		ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().addLibroDiario(this.objetoABM);
	}
	
	@Override
	public void modificar() throws Exception {
		this.objetoABM = this.getObjetoABM();
		ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().updateLibroDiario(this.objetoABM);
	}
	
	@Override
	public void eliminar() throws Exception {
		ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().deleteLibroDiario(this.objetoABM);
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
	
	public Integer getCantidadFolios() {
		return cantidadFolios;
	}

	public void setCantidadFolios(Integer cantidadFolios) {
		this.cantidadFolios = cantidadFolios;
	}
	
	@Override
	public LibroDiario getObjetoABM() {
		if (this.objetoABM != null) {
			this.objetoABM.setCodigoAutorizacion(this.getCodigoAutorizacion());
			this.objetoABM.setNumero(this.getNumero());
		}
		return this.objetoABM;
	}
	
	@Override
	public void setObjetoABM(LibroDiario objetoABM) {
		this.objetoABM = objetoABM;
		if (this.objetoABM != null) {
			this.setCodigoAutorizacion(this.objetoABM.getCodigoAutorizacion());
			this.setNumero(this.objetoABM.getNumero());
			if (this.objetoABM.getFoliosLibroDiario() != null) {
				this.setCantidadFolios(this.objetoABM.getFoliosLibroDiario().size());
			}
		}
	}

}
