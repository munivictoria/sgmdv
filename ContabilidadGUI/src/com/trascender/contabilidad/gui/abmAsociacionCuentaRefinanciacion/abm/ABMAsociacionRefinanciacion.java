package com.trascender.contabilidad.gui.abmAsociacionCuentaRefinanciacion.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.swing.JLabel;

import com.trascender.contabilidad.gui.abmAsociacionCuentaRefinanciacion.AsociacionRefinanciacionABMModel;
import com.trascender.contabilidad.gui.abmAsociacionCuentaRefinanciacion.AsociacionRefinanciacionBusquedaModel;
import com.trascender.contabilidad.gui.abmAsociacionCuentaRefinanciacion.ParametroAsociacionTableModel;
import com.trascender.contabilidad.gui.abmAsociacionCuentaRefinanciacion.RefinanciacionTableModel;
import com.trascender.contabilidad.gui.abmParametroAsociacion.AdminParametroAsociacion;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.AsociacionRefinanciacion;
import com.trascender.contabilidad.recurso.persistent.ParametroAsociacion;
import com.trascender.framework.recurso.transients.Periodo;
import com.trascender.framework.util.Periodicidad;
import com.trascender.gui.framework.abmStandard.ABMController;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Conversor;
import com.trascender.gui.framework.util.Util;
import com.trascender.gui.framework.util.Validador;

public abstract class ABMAsociacionRefinanciacion extends ABMController<AsociacionRefinanciacion>{
	
	public abstract ABMAsociacionRefinanciacionView getView();
	public abstract AsociacionRefinanciacionABMModel getAbmModel();
	public abstract RefinanciacionTableModel getRefinanciacionTableModel();
	public abstract ParametroAsociacionTableModel getParametroAsociacionTableModel();
	public abstract AsociacionRefinanciacionBusquedaModel getRefinanciacionBusquedaModel();
	
	@Override
	protected void init() {
		super.init();
		this.setModels();
		this.setListeners();
		this.acomodarBotonesTblParametros();
	}
	
	private void acomodarBotonesTblParametros() {
		this.getView().getPnlBtnTablaParametroAsociacion().remove(this.getView().getPnlBtnTablaParametroAsociacion().getBtnModificar());
		this.getView().getPnlBtnTablaParametroAsociacion().getBtnEliminar().setBounds(Util.getBoundsBotonesPnlVertical(1));
		this.getView().getPnlBtnTablaParametroAsociacion().getBtnQuitarTodos().setVisible(false);
		this.getView().getPnlBtnTablaParametroAsociacion().getBtnModificar().setVisible(false);
	}

	private void setModels() {
		this.getView().setRefinanciacionTableModel(this.getRefinanciacionTableModel());
		this.getView().setParametroAsociacionTableModel(this.getParametroAsociacionTableModel());
	}

	private void setListeners() {
		this.getView().getPnlBtnTablaParametroAsociacion().getBtnAgregar().addActionListener(new AgregarParametroAsociacionListener(this));
		this.getView().getPnlBtnTablaParametroAsociacion().getBtnEliminar().addActionListener(new QuitarLineaRetencionListener(this));
	}
	
	@Override
	public void actualizarABMModel() {
		try {
			this.getAbmModel().fireActualizarDatos();
		}			
		 catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void actualizarView() {
		
		try {  //Actualización de la tabla Parametro Asociacion
			if (this.getParametroAsociacionTableModel()!= null) {
				this.getParametroAsociacionTableModel().setListaObjetos(this.getAbmModel().getListaParametrosAsociacion());
				
//				System.out.println("Cantidad de datos actualizarView : " + this.getAbmModel().getListaParametrosAsociacion().size());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean validarDatos() {
		boolean validacionOK = true;
		
		return validacionOK;
	}
	
	public void openAgregarParametroAsociacion() throws Exception {
		AdminParametroAsociacion adminParametroAsociacion = new AdminParametroAsociacion(this.getView());
		ParametroAsociacion locParametroAsociacion = adminParametroAsociacion.openSelect();
		if(locParametroAsociacion != null){
			locParametroAsociacion = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().getParametroAsociacionByID(locParametroAsociacion.getIdParametroAsociacion());
			
			List<ParametroAsociacion> locListaParametroAsociacion = this.getAbmModel().getListaParametrosAsociacion();
				
			boolean locExiste = false;
			Iterator<ParametroAsociacion> locIterador = locListaParametroAsociacion.iterator();
					
			while(locIterador.hasNext() && !locExiste){
				ParametroAsociacion locParametro = locIterador.next();
				if(locParametro.equals(locParametroAsociacion))
					locExiste = true;
			} 
			
			if(!locExiste){
				this.getAbmModel().getListaParametrosAsociacion().add(locParametroAsociacion);
				this.actualizarABMModel();
				this.actualizarView();
			}	
		}
	}
	
	/**
	 * Cambia los componentes correspondientes para indicar que comenzó la búsqueda.
	 */
	public void iniBusqueda() {
		this.getView().getPnlTablaRefinanciaciones().setCargando(true);
		this.getView().getPnlTablaRefinanciaciones().repaint();
		this.getView().getPnlTablaRefinanciaciones().getTblDatos().setEnabled(false);
	}
	
	/**
	 * Restablece el estado de los componentes para poder iniciar una nueva búsqueda.
	 */
	public void finBusqueda() {
		this.getView().getPnlTablaRefinanciaciones().setCargando(false);
		this.getView().getPnlTablaRefinanciaciones().setEnabled(true);
		this.getView().getPnlTablaRefinanciaciones().getTblDatos().setEnabled(true);
		this.getView().getPnlTablaRefinanciaciones().repaint();
	}
	
	void openQuitarLineaRetencion() throws Exception{
		int locLineaRetencion = this.getView().getPnlTablaParametrosAsociacion().getTblDatos().getSelectedRow();
		
		ParametroAsociacion locParametro = new ParametroAsociacion();
		if(locLineaRetencion > -1){ 
			if (this.getParametroAsociacionTableModel() != null) {
				locParametro = this.getParametroAsociacionTableModel().getRow(locLineaRetencion);
				this.getAbmModel().getListaParametrosAsociacion().remove(locParametro);
				this.getParametroAsociacionTableModel().getListaObjetos().remove(locParametro);
				this.actualizarView();
			}
		}
	}
}

class AgregarParametroAsociacionListener implements ActionListener {
	private ABMAsociacionRefinanciacion controller;
	
	public AgregarParametroAsociacionListener(ABMAsociacionRefinanciacion controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openAgregarParametroAsociacion();
		} catch (Exception e2) {
			e2.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), e2.getMessage());
		}
	}
}

class QuitarLineaRetencionListener implements ActionListener {
	private ABMAsociacionRefinanciacion controller;
	
	public QuitarLineaRetencionListener(ABMAsociacionRefinanciacion controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openQuitarLineaRetencion();
		} catch (Exception e2) {
			e2.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), e2.getMessage());
		}
	}
}