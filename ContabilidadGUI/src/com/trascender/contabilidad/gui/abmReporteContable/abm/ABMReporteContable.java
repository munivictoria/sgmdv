package com.trascender.contabilidad.gui.abmReporteContable.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;

import com.trascender.contabilidad.gui.abmReporteContable.ParametroReporteTableModel;
import com.trascender.contabilidad.gui.abmReporteContable.ReporteContableABMModel;
import com.trascender.contabilidad.gui.abmReporteContable.UsuarioTableModel;
import com.trascender.contabilidad.gui.abmUsuario.AdminUsuario;
import com.trascender.contabilidad.recurso.persistent.ParametroReporte;
import com.trascender.contabilidad.recurso.persistent.ParametroReporte.Tipo;
import com.trascender.contabilidad.recurso.persistent.ReporteContable;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.recurso.persistent.reporteDinamico.Reporte;
import com.trascender.gui.framework.abmStandard.ABMController;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Conversor;
import com.trascender.gui.framework.util.Validador;

public abstract class ABMReporteContable extends ABMController<Reporte>{
	
	public abstract ABMReporteContableView getView();
	public abstract ReporteContableABMModel getAbmModel();
	public abstract ParametroReporteTableModel getTableModelParametro();
	public abstract UsuarioTableModel getTableModelUsuario();
	
	@Override
	protected void init() {
		super.init();
		this.setModels();
		this.setListeners();
		
		this.getView().getPnlBtnTablaParametros().getBtnModificar().setVisible(false);
		this.getView().getPnlBtnTablaParametros().getBtnQuitarTodos().setVisible(false);
		
		this.getView().getPnlBtnTablaUsuarios().getBtnModificar().setVisible(false);
		this.getView().getPnlBtnTablaUsuarios().getBtnQuitarTodos().setVisible(false);
	}
	
	private void setModels() {
		this.getView().setAbmModel(getAbmModel());
		this.getView().setTableModelParametros(this.getTableModelParametro());
		this.getView().setTableModelUsuario(this.getTableModelUsuario());
	}
	
	private void setListeners() {
		this.getView().getPnlBtnTablaParametros().getBtnAgregar().addActionListener(new AgregarParametroListener(this));
		this.getView().getPnlBtnTablaParametros().getBtnEliminar().addActionListener(new QuitarParametroListener(this));
		this.getView().getPnlBtnTablaUsuarios().getBtnAgregar().addActionListener(new AgregarUsuarioListener(this));
		this.getView().getPnlBtnTablaUsuarios().getBtnEliminar().addActionListener(new QuitarUsuarioListener(this));
	}
	
	@Override
	public void actualizarABMModel() {
		ReporteContableABMModel locModel = this.getAbmModel();
		
		locModel.setNombre(Conversor.getNullSiVacio(this.getView().getTfNombre().getText()));
		locModel.setNombreArchivoJasper(Conversor.getNullSiVacio(this.getView().getTfNombreArchivoJasper().getText()));
		
		locModel.setListaUsuario(this.getView().getTableModelUsuario().getListaObjetos());
		
		locModel.fireActualizarDatos();
	}
	
	@Override
	public void actualizarView() {
		this.getView().getTfNombre().setText(Conversor.getVacioSiNull(this.getAbmModel().getNombre()));
		this.getView().getTfNombreArchivoJasper().setText(Conversor.getVacioSiNull(this.getAbmModel().getNombreArchivoJasper()));
		
		if (this.getAbmModel().getListaParametroReporte() != null) {
			this.getTableModelParametro().setListaObjetos(this.getAbmModel().getListaParametroReporte());
		}
		
		if (this.getAbmModel().getListaUsuario() != null) {
			this.getTableModelUsuario().setListaObjetos(this.getAbmModel().getListaUsuario());
		}
		
		if (this.getAbmModel().getListaParametroReporte() != null && this.getTableModelParametro() != null) {
			List<ParametroReporte> locListaParametros = new ArrayList<ParametroReporte>(this.getAbmModel().getListaParametroReporte());
//			this.ordenarListaParametros(locListaParametros);
			//			
			this.getTableModelParametro().clearTable();
			this.getTableModelParametro().addRows(locListaParametros);

			try {
				this.setTableCellEditor();
			}
			catch (Exception ex) {
				ex.printStackTrace();
				AppManager.getInstance().showErrorMsg(this.getView(), ex.getMessage());
			}
		}
	}
	
	protected void setTableCellEditor() throws Exception {

		if (this.getTableModelParametro() != null) {

			this.getView().getPnlTablaParametrosReporte().getTblDatos().setRowHeight(22);

			JTextField tfNombre = new JTextField();
			ParametroTextFieldCellEditor ceNombre = new ParametroTextFieldCellEditor(tfNombre);
			ceNombre.addCellEditorListener(new ParametroTextFieldCellEditorListener(this));

			this.getView().setCellEditorTextField(ceNombre);
			
			JComboBox cbTipo = new JComboBox();
			ParametroComboBoxCellEditor ceTipo = new ParametroComboBoxCellEditor(cbTipo);
			ceTipo.addCellEditorListener(new ParametroComboBoxCellEditorListener(this));

			this.getView().setCellEditorComboBox(ceTipo);
			
			JCheckBox ckbRequerido = new JCheckBox();
			ParametroCheckBoxCellEditor ceRequerido = new ParametroCheckBoxCellEditor(ckbRequerido);
			ceRequerido.addCellEditorListener(new ParametroCheckBoxCellEditorListener(this));

			this.getView().setCellEditorCheckBox(ceRequerido);
		}
	}
	
	@Override
	public boolean validarDatos() {
		boolean validacionOK = true;
		
		List<Object> attNulos = new ArrayList<Object>();
		List<JLabel> lblNulos = new ArrayList<JLabel>();
		
		attNulos.add(this.getView().getTfNombre().getText());
		lblNulos.add(this.getView().getLblNombre());
		
		attNulos.add(this.getView().getTfNombreArchivoJasper().getText());
		lblNulos.add(this.getView().getLblNombreArchivoJasper());
		
		List<String> listaErrores = new ArrayList<String>();
		try {
			listaErrores.addAll(Validador.validarNulos(attNulos, lblNulos));
		}
		catch (GuiException ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.getView(), ex.getMessage());
			return false;
		}
		
		if (!listaErrores.isEmpty()) {
			validacionOK = false;
			this.mostrarErroresValidacion(listaErrores);
		}
		
		return validacionOK;
	}
	
	public void openAgregarParametro() throws Exception {
		this.getAbmModel().getListaParametroReporte().add(new ParametroReporte());
//		this.getTableModelParametro().getListaObjetos().add(new ParametroReporte());
		actualizarABMModel();
		this.actualizarView();
	}
	
	public void openAgregarUsuario() throws Exception {
		AdminUsuario adminUsuario = new AdminUsuario(this.getView());
		Usuario locUsuario = adminUsuario.openSelect();
		if (locUsuario != null) {
			this.getTableModelUsuario().addRow(locUsuario);
			actualizarABMModel();
			this.actualizarView();
		}
	}
	
	public void quitarParametro() throws Exception {
		int locParametroSeleccionado = this.getView().getPnlTablaParametrosReporte().getTblDatos().getSelectedRow();
		
		if(locParametroSeleccionado > -1){ 
			if (this.getTableModelParametro() != null) {
				ParametroReporte objetoSeleccionado = this.getTableModelParametro().getRow(locParametroSeleccionado);
				this.getAbmModel().getListaParametroReporte().remove(objetoSeleccionado);
				actualizarABMModel();
				this.actualizarView();
			}
		}
	}
	
	public void quitarUsuario() throws Exception {
		int locUsuarioSeleccionado = this.getView().getPnlTablaUsuarios().getTblDatos().getSelectedRow();
		
		if(locUsuarioSeleccionado > -1){ 
			if (this.getTableModelUsuario() != null) {
				Usuario objetoSeleccionado = this.getTableModelUsuario().getRow(locUsuarioSeleccionado);
				this.getAbmModel().getListaUsuario().remove(objetoSeleccionado);
				actualizarABMModel();
				this.actualizarView();
			}
		}
	}
	
	public ParametroReporte getSelectedRowTablaParametros() {
		ParametroReporte objetoSeleccionado = null;
		if (this.getView() != null && this.getView().getPnlTablaParametrosReporte().getTblDatos() != null) {
			int selectedRow = this.getView().getPnlTablaParametrosReporte().getTblDatos().getSelectedRow();
			if (selectedRow > -1) {
				objetoSeleccionado = this.getTableModelParametro().getRow(selectedRow);
			}
		}
		return objetoSeleccionado;
	}
}
class AgregarParametroListener implements ActionListener {
	private ABMReporteContable controller;
	
	public AgregarParametroListener(ABMReporteContable controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openAgregarParametro();
		} catch (Exception e2) {
			e2.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), e2.getMessage());
		}
	}
}

class QuitarParametroListener implements ActionListener {
	private ABMReporteContable controller;
	
	public QuitarParametroListener(ABMReporteContable controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.quitarParametro();
		} catch (Exception e2) {
			e2.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), e2.getMessage());
		}
	}
}

class AgregarUsuarioListener implements ActionListener {
	private ABMReporteContable controller;
	
	public AgregarUsuarioListener(ABMReporteContable controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openAgregarUsuario();
		} catch (Exception e2) {
			e2.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), e2.getMessage());
		}

	}
	
}

class QuitarUsuarioListener implements ActionListener {
	private ABMReporteContable controller;
	
	public QuitarUsuarioListener(ABMReporteContable controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.quitarUsuario();
		} catch (Exception e2) {
			e2.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), e2.getMessage());
		}

	}
	
}

class ParametroTextFieldCellEditorListener implements CellEditorListener {
	private final ABMReporteContable controller;
	public ParametroTextFieldCellEditorListener(ABMReporteContable controller) {
		this.controller = controller;
	}


	@Override
	public void editingStopped(ChangeEvent e) {
		DefaultCellEditor ce = (DefaultCellEditor)e.getSource();
		String locNombre = ce.getCellEditorValue().toString();
		ParametroReporte locParametro = this.controller.getSelectedRowTablaParametros();
		locParametro.setNombre(locNombre);
	}

	@Override
	public void editingCanceled(ChangeEvent e) {

	}

}

class ParametroComboBoxCellEditorListener implements CellEditorListener {
	private final ABMReporteContable controller;
	public ParametroComboBoxCellEditorListener(ABMReporteContable controller) {
		this.controller = controller;
	}


	@Override
	public void editingStopped(ChangeEvent e) {
		DefaultCellEditor ce = (DefaultCellEditor)e.getSource();
		Tipo locTipo = (Tipo)ce.getCellEditorValue();
		ParametroReporte locParametro = this.controller.getSelectedRowTablaParametros();
		if(locParametro != null){
			locParametro.setTipo(locTipo);
		}
	}

	@Override
	public void editingCanceled(ChangeEvent e) {

	}

}

class ParametroCheckBoxCellEditorListener implements CellEditorListener {
	private final ABMReporteContable controller;
	public ParametroCheckBoxCellEditorListener(ABMReporteContable controller) {
		this.controller = controller;
	}


	@Override
	public void editingStopped(ChangeEvent e) {
		DefaultCellEditor ce = (DefaultCellEditor)e.getSource();
		Boolean locRequerido = (Boolean)ce.getCellEditorValue();
		ParametroReporte locParametro = this.controller.getSelectedRowTablaParametros();
		locParametro.setRequerido(locRequerido);
	}

	@Override
	public void editingCanceled(ChangeEvent e) {

	}

}
