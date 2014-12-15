package com.trascender.contabilidad.gui.abmReporteContable.abm;

import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.contabilidad.gui.abmReporteContable.ParametroReporteContableTableModel;
import com.trascender.contabilidad.gui.abmReporteContable.ReporteContableABMModel;
import com.trascender.contabilidad.gui.abmReporteContable.UsuarioTableModel;
import com.trascender.contabilidad.recurso.persistent.ParametroReporteContable;
import com.trascender.contabilidad.recurso.persistent.ReporteContable;
import com.trascender.gui.framework.util.Conversor;

public class ConsultarReporteContable extends ABMReporteContable{

	private static final long serialVersionUID = -39788638968727282L;
	
	private ConsultarReporteContableView view;
	private ReporteContableABMModel abmModel = new ReporteContableABMModel();
	private ParametroReporteContableTableModel tableModelParametros = new ParametroReporteContableTableModel();
	private UsuarioTableModel tableModelUsuarios = new UsuarioTableModel();

	public ConsultarReporteContable(JDialog owner, ReporteContable pReporteContable) throws Exception {
		this.view = new ConsultarReporteContableView(owner);
		this.abmModel.setObjetoABM(pReporteContable);
		this.init();
	}
	
	public ConsultarReporteContable(JFrame owner, ReporteContable pReporteContable) throws Exception {
		this.view = new ConsultarReporteContableView(owner);
		this.abmModel.setObjetoABM(pReporteContable);
		this.init();
	}
	
	@Override
	public ReporteContableABMModel getAbmModel() {
		return this.abmModel;
	}
	
	@Override
	protected void init() {
		super.init();
		this.setModels();
		this.setPropiedadesComponentes();
		this.setListeners();
		this.setVisibleAll();
	}
	
	private void setVisibleAll() {
		this.getView().getPnlPie().getBtnAceptar().setVisible(false);
		this.getView().getPnlPie().getBtnImprimir().setVisible(false);
	}

	private void setListeners() {
//		this.getView().getPnlPie().getBtnImprimir().addActionListener(new BtnImprimirListener(this));
	}
	
	private void setModels() {
		this.getView().setAbmModel(this.getAbmModel());
		this.getView().setTableModelParametros((this.getTableModelParametro()));
		this.getView().setTableModelUsuario((this.getTableModelUsuario()));
	}
	
	private void setPropiedadesComponentes() {
		this.getView().getPnlPie().getBtnAceptar().setVisible(true);
		
		this.getView().getTfNombre().setEditable(false);
		this.getView().getTfNombreArchivoJasper().setEditable(false);
		this.getView().getPnlTablaParametrosReporte().getTblDatos().setEnabled(false);
		this.getView().getPnlTablaUsuarios().getTblDatos().setEnabled(false);
		this.getView().getPnlBtnTablaParametros().getBtnAgregar().setEnabled(false);
		this.getView().getPnlBtnTablaParametros().getBtnEliminar().setEnabled(false);
		this.getView().getPnlBtnTablaUsuarios().getBtnAgregar().setEnabled(false);
		this.getView().getPnlBtnTablaUsuarios().getBtnEliminar().setEnabled(false);
	}
	
	public void instanciarTableModel() {
		this.getView().setTableModelParametros(this.tableModelParametros);
		this.getView().setTableModelUsuario(this.tableModelUsuarios);
	}
	
	@Override
	public void actualizarView() {
		this.getView().getTfNombre().setText(Conversor.getVacioSiNull(this.getAbmModel().getNombre()));
		this.getView().getTfNombreArchivoJasper().setText(Conversor.getVacioSiNull(this.getAbmModel().getNombreArchivoJasper()));
		
		if (this.getAbmModel().getListaParametroReporte() != null) {
			this.getTableModelParametro().setListaObjetos(new ArrayList<ParametroReporteContable>(this.getAbmModel().getListaParametroReporte()));
		}
	}
	
	@Override
	public ParametroReporteContableTableModel getTableModelParametro() {
		return this.tableModelParametros;
	}
	
	@Override
	public UsuarioTableModel getTableModelUsuario() {
		return this.tableModelUsuarios;
	}

	@Override
	public ABMReporteContableView getView() {
		return this.view;
	}
}