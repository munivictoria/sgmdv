package com.trascender.contabilidad.gui.abmReporteContable;

import java.util.List;

import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.recurso.persistent.reporteDinamico.ParametroReporte;
import com.trascender.framework.recurso.persistent.reporteDinamico.Reporte;
import com.trascender.gui.framework.model.TAbstractABMModel;

public class ReporteContableABMModel extends TAbstractABMModel<Reporte>{
	
	@Override
	public void agregar() throws Exception {
///*this.objetoABM = */ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemReportesContabilidad().addReporteContable(this.getObjetoABM());
	}

	@Override
	public void eliminar() throws Exception {
//		ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemReportesContabilidad().deleteReporteContable(this.getObjetoABM());
	}

	@Override
	public void modificar() throws Exception {
//		this.objetoABM = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemReportesContabilidad().updateReporteContable(this.getObjetoABM());
	}
	
	public String getNombre() {
		return this.objetoABM.getNombre();
	}
	
	public void setNombre(String nombre) {
		this.objetoABM.setNombre(nombre);
	}
	
	public String getNombreArchivoJasper() {
		return this.objetoABM.getNombreArchivoJasper();
	}
	
	public void setNombreArchivoJasper(String nombreArchivoJasper) {
		this.objetoABM.setNombreArchivoJasper(nombreArchivoJasper);
	}
	
	public List<Usuario> getListaUsuario() {
		return this.objetoABM.getListaUsuario();
	}

	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.objetoABM.setListaUsuario(listaUsuario);
	}
	
	public List<ParametroReporte> getListaParametroReporte() {
		return this.objetoABM.getListaParametroReporte();
	}

	public void setListaParametroReporte(List<ParametroReporte> listaParametroReporte) {
		this.objetoABM.setListaParametroReporte(listaParametroReporte);
	}
}
