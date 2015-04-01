package com.trascender.contabilidad.gui.abmAsociacionCuentaRefinanciacion.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;

import com.trascender.contabilidad.gui.abmAsociacionCuentaRefinanciacion.AsociacionRefinanciacionABMModel;
import com.trascender.contabilidad.gui.abmAsociacionCuentaRefinanciacion.AsociacionRefinanciacionBusquedaModel;
import com.trascender.contabilidad.gui.abmAsociacionCuentaRefinanciacion.ParametroAsociacionTableModel;
import com.trascender.contabilidad.gui.abmAsociacionCuentaRefinanciacion.RefinanciacionTableModel;
import com.trascender.contabilidad.recurso.persistent.CuentaRefinanciacion;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.saic.recurso.persistent.ParametroAsociacion;
import com.trascender.saic.recurso.persistent.refinanciacion.DocumentoRefinanciacion;

public class EliminarAsociacionRefinanciacion extends ABMAsociacionRefinanciacion {

	private EliminarAsociacionRefinanciacionView view;
	private AsociacionRefinanciacionABMModel abmModel = new AsociacionRefinanciacionABMModel();
	private RefinanciacionTableModel refinanciacionTableModel = new RefinanciacionTableModel();
	private ParametroAsociacionTableModel parametroAsociacionTableModel = new ParametroAsociacionTableModel();
	
	public EliminarAsociacionRefinanciacion(JDialog owner) throws Exception {
		this.view = new EliminarAsociacionRefinanciacionView(owner);
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		this.setListeners();
		this.disabledAll();
		this.editableAll(); 
	}
	
	private void disabledAll() {
		this.getView().getPnlBtnTablaParametroAsociacion().setVisible(false);
		this.getView().getPnlPie().getBtnImprimir().setVisible(false);
	}
	
	public void updateView() {
		this.actualizarView();
		this.buscarRefinanciaciones();
	}
	
	private void editableAll() {
		this.getView().getPnlTablas().remove(this.getView().getPnlContenedorRefinanciaciones());
	}
	
	private void setListeners() {
		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnEliminarListener(this));
	}
	
	@Override
	public AsociacionRefinanciacionABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public ParametroAsociacionTableModel getParametroAsociacionTableModel() {
		return this.parametroAsociacionTableModel;
	}

	@Override
	public AsociacionRefinanciacionBusquedaModel getRefinanciacionBusquedaModel() {
		return null;
	}

	@Override
	public RefinanciacionTableModel getRefinanciacionTableModel() {
		return refinanciacionTableModel;
	}

	@Override
	public ABMAsociacionRefinanciacionView getView() {
		return this.view;
	}
	
	void eliminarComprobanteRetencion() throws Exception {
		this.getAbmModel().eliminar();
		this.setOperacionRealizada(true);
		this.close();
	}
	
	public void buscarRefinanciaciones() {
		this.getRefinanciacionTableModel().clearTable();
		
		this.getView().getPnlBtnTablaParametroAsociacion().getBtnAgregar().setEnabled(false);
		this.getView().getPnlBtnTablaParametroAsociacion().getBtnModificar().setEnabled(false);
		
		final ABMAsociacionRefinanciacion controller = this;
		Thread locThread = new Thread(new Runnable(){
			public void run() {
				try {
					controller.iniBusqueda();
					List<DocumentoRefinanciacion> locListaDocumentos = new ArrayList<DocumentoRefinanciacion>();
						
//					System.out.println("CANTIDAD DOCUMENTOS REFINANCIACIONES ASOCIADOS EN ESTA ASOCIACION " + controller.getAbmModel().getObjetoABM().getListaCuentaRefinanciacion().size());
//					System.out.println(" ");
//					System.out.println("CANTIDAD parametros ASOCIADOS EN ESTA ASOCIACION " + controller.getAbmModel().getObjetoABM().getListaParametrosAsociacion().size());
					
					for (CuentaRefinanciacion cuentaRefinanciacion : controller.getAbmModel().getObjetoABM().getListaCuentaRefinanciacion()) {
						locListaDocumentos.add(cuentaRefinanciacion.getDocumentoRefinanciacion());
					}
					
					List<ParametroAsociacion> locListaParametros = new ArrayList<ParametroAsociacion>();
					
					for (ParametroAsociacion locParametroAsociacion : controller.getAbmModel().getObjetoABM().getListaParametrosAsociacion()) {
						ParametroAsociacion parametroAsociacion = new ParametroAsociacion();
						parametroAsociacion.setCuenta(locParametroAsociacion.getCuenta());
						parametroAsociacion.setIdParametroAsociacion(locParametroAsociacion.getIdParametroAsociacion());
						parametroAsociacion.setPorcentaje(locParametroAsociacion.getPorcentaje());
						locListaParametros.add(parametroAsociacion);
					}
					
					controller.getRefinanciacionTableModel().setListaObjetos(locListaDocumentos);
					controller.getParametroAsociacionTableModel().setListaObjetos(locListaParametros);
					controller.getView().getPnlBtnTablaParametroAsociacion().getBtnAgregar().setEnabled(true);
					controller.getView().getPnlBtnTablaParametroAsociacion().getBtnModificar().setEnabled(true);
				}
				catch (Exception ex) {
					ex.printStackTrace();
					AppManager.getInstance().showErrorMsg(controller.getView(), ex.getMessage());
				}
				finally{
					controller.finBusqueda();
				}
			}
		});
		locThread.start();
	}
}

class BtnEliminarListener implements ActionListener {

	private EliminarAsociacionRefinanciacion controller;
	
	public BtnEliminarListener(EliminarAsociacionRefinanciacion controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.eliminarComprobanteRetencion();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}