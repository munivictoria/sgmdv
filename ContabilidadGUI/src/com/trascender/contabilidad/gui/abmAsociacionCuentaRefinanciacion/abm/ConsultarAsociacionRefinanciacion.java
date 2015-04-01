package com.trascender.contabilidad.gui.abmAsociacionCuentaRefinanciacion.abm;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;

import com.trascender.contabilidad.gui.abmAsociacionCuentaRefinanciacion.AsociacionRefinanciacionABMModel;
import com.trascender.contabilidad.gui.abmAsociacionCuentaRefinanciacion.AsociacionRefinanciacionBusquedaModel;
import com.trascender.contabilidad.gui.abmAsociacionCuentaRefinanciacion.ParametroAsociacionTableModel;
import com.trascender.contabilidad.gui.abmAsociacionCuentaRefinanciacion.RefinanciacionTableModel;
import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.contabilidad.recurso.persistent.CuentaRefinanciacion;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.saic.recurso.persistent.ParametroAsociacion;
import com.trascender.saic.recurso.persistent.refinanciacion.DocumentoRefinanciacion;

public class ConsultarAsociacionRefinanciacion extends ABMAsociacionRefinanciacion {

	private ConsultarAsociacionRefinanciacionView view;
	private AsociacionRefinanciacionABMModel abmModel = new AsociacionRefinanciacionABMModel();
	private RefinanciacionTableModel refinanciacionTableModel = new RefinanciacionTableModel();
	private ParametroAsociacionTableModel parametroAsociacionTableModel = new ParametroAsociacionTableModel();
		
	public ConsultarAsociacionRefinanciacion(JDialog owner) throws Exception {
		this.view = new ConsultarAsociacionRefinanciacionView(owner);
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		this.setTextoBtnCerrar();
		this.setListeners();
		this.disabledAll();
		this.editableAll(); 
	}
	
	private void disabledAll() {
		this.getView().getPnlPie().getBtnAceptar().setVisible(false);
		this.getView().getPnlPie().getBtnImprimir().setVisible(false);
		this.getView().getPnlBtnTablaParametroAsociacion().setVisible(false);
		}
	
	private void editableAll() {
	}
	
	private void setListeners() {
	}
	
	public void updateView() {
		this.actualizarView();
		this.buscarRefinanciaciones();
	}
	
	public void setTextoBtnCerrar() {
    	this.getView().getPnlPie().getBtnCancelar().setText(MessagesContabilidad.getString("Application.btnCerrar"));
		this.getView().getPnlPie().getBtnCancelar().setMnemonic(MessagesContabilidad.getChar("Application.btnCerrarMnemonic"));
		this.getView().getPnlPie().getBtnCancelar().setToolTipText(MessagesContabilidad.getString("Application.btnCerrarToolTip"));
    }
	
	public void buscarRefinanciaciones() {
	this.getRefinanciacionTableModel().clearTable();
	
	this.getView().getPnlBtnTablaParametroAsociacion().getBtnAgregar().setEnabled(false);
	this.getView().getPnlBtnTablaParametroAsociacion().getBtnModificar().setEnabled(false);
	
	final ABMAsociacionRefinanciacion controller = this;
	Thread locThread = new Thread(new Runnable(){
		public void run() {
			try {
				//controller.actualizarABMModel();
				controller.iniBusqueda();
				List<DocumentoRefinanciacion> locListaDocumentos = new ArrayList<DocumentoRefinanciacion>();
					
				System.out.println("CANTIDAD DOCUMENTOS REFINANCIACIONES ASOCIADOS EN ESTA ASOCIACION " + controller.getAbmModel().getObjetoABM().getListaCuentaRefinanciacion().size());
				
				for (CuentaRefinanciacion cuentaRefinanciacion : controller.getAbmModel().getObjetoABM().getListaCuentaRefinanciacion()) {
					locListaDocumentos.add(cuentaRefinanciacion.getDocumentoRefinanciacion());
				}
				
				List<ParametroAsociacion> locListaParametros = new ArrayList<ParametroAsociacion>();
				System.out.println(" ");
				System.out.println("CANTIDAD parametros ASOCIADOS EN ESTA ASOCIACION " + controller.getAbmModel().getObjetoABM().getListaParametrosAsociacion().size());
				
				
				for (ParametroAsociacion locParametroAsociacion : controller.getAbmModel().getObjetoABM().getListaParametrosAsociacion()) {
					ParametroAsociacion pa = new ParametroAsociacion();
					pa.setCuenta(locParametroAsociacion.getCuenta());
					pa.setIdParametroAsociacion(locParametroAsociacion.getIdParametroAsociacion());
					pa.setPorcentaje(locParametroAsociacion.getPorcentaje());
					locListaParametros.add(pa);
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
}