package com.trascender.contabilidad.gui.abmAsociacionCuentaSolicitudSuministro.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.trascender.compras.recurso.persistent.reference.CuentaRfr;
import com.trascender.compras.recurso.persistent.suministros.FirmaPermisoSolicitud;
import com.trascender.compras.recurso.persistent.suministros.LineaSolicitudSuministro;
import com.trascender.compras.recurso.persistent.suministros.SolicitudSuministro;
import com.trascender.contabilidad.gui.abmAsociacionCuentaSolicitudSuministro.AsociacionCuentaSolicitudSuministroABMModel;
import com.trascender.contabilidad.gui.abmAsociacionCuentaSolicitudSuministro.FirmaPermisoTableModel;
import com.trascender.contabilidad.gui.abmAsociacionCuentaSolicitudSuministro.LineaSolicitudSuministroTableModel;
import com.trascender.contabilidad.gui.abmCuenta.AdminCuenta;
import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.gui.framework.abmStandard.ABMController;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.recursos.Messages;
import com.trascender.gui.framework.util.Conversor;

public abstract class ABMAsociacionCuentaSolicitudSuministro extends ABMController<SolicitudSuministro> {
	
	public abstract ABMAsociacionCuentaSolicitudSuministroView getView();
	public abstract AsociacionCuentaSolicitudSuministroABMModel getAbmModel(); 
	public abstract FirmaPermisoTableModel getFirmaPermisoTableModel();
	public abstract LineaSolicitudSuministroTableModel getLineaSolicitudSuministroTableModel();
	
	
	@Override
	protected void init() {
		super.init();
		this.setModels();
		this.setListeners();
		this.getView().getPnlTablaLineaSS().getPnlVerticalBotones().getBtnEliminar().setVisible(false);
		this.getView().getPnlTablaLineaSS().getPnlVerticalBotones().getBtnQuitarTodos().setVisible(false);
		this.setTextoBtnModificarAsociacion();
	}
	
	private void setModels() {
		this.getView().setAbmModel(this.getAbmModel());
		this.getView().getPnlTablaFirma().getPnlTabla().getTblDatos().setModel(this.getFirmaPermisoTableModel());
		this.getView().getPnlTablaLineaSS().getPnlTabla().getTblDatos().setModel(this.getLineaSolicitudSuministroTableModel());
	}
	
	private void setListeners() {
//		this.getView().getPnlBotonesSeleccionCuenta().getBtnSeleccionar().addActionListener(new BtnSeleccionarCuentaListener(this));
//		this.getView().getPnlBotonesSeleccionCuenta().getBtnLimpiar().addActionListener(new (this));
		
		this.getView().getPnlTablaLineaSS().getPnlVerticalBotones().getBtnAgregar().addActionListener(new BtnSeleccionarCuentaListener(this));
		this.getView().getPnlTablaLineaSS().getPnlVerticalBotones().getBtnModificar().addActionListener(new BtnLimpiarCuentaListener(this));
	}
	
	@Override
	protected void actualizarABMModel() {
		this.getAbmModel().fireActualizarDatos();
	}
	
	private void setTextoBtnModificarAsociacion() {
		this.getView().getPnlTablaLineaSS().getPnlVerticalBotones().getBtnModificar().setText(Messages.getString("Application.btnQuitar"));
		this.getView().getPnlTablaLineaSS().getPnlVerticalBotones().getBtnModificar().setMnemonic(Messages.getString("Application.btnQuitarMnemonic").charAt(0));
	}
	
	@Override
	public void actualizarView() {
		this.getView().getTfSolicitudSuministro().setText(Conversor.getVacioSiNull(this.getAbmModel().getObjetoABM()));
		this.getView().getTfArea().setText(Conversor.getVacioSiNull(this.getAbmModel().getObjetoABM().getArea()));
		this.getView().getTfEsatado().setText(Conversor.getVacioSiNull(this.getAbmModel().getObjetoABM().getEstado()));
		this.getView().getTfFechaEmision().setValue(Conversor.getString(this.getAbmModel().getObjetoABM().getFechaEmision()));
		this.getView().getTfUsuario().setText(Conversor.getVacioSiNull(this.getAbmModel().getObjetoABM().getUsuario()));
		
		
		if (this.getAbmModel().getObjetoABM() != null && this.getAbmModel().getObjetoABM().getListaFirmaPermiso() != null) {
			
			// Actualización de la tabla de firmas
			List<FirmaPermisoSolicitud> locListFirma = new ArrayList<FirmaPermisoSolicitud>();
			
			for (Iterator iterator = this.getAbmModel().getObjetoABM().getListaFirmaPermiso().iterator(); iterator.hasNext();) {
				FirmaPermisoSolicitud firmaPermiso = (FirmaPermisoSolicitud) iterator.next();
				locListFirma.add(firmaPermiso);
			}
			
			System.out.println("firmaPermisos---- " + locListFirma);
			this.getFirmaPermisoTableModel().clearTable();
			this.getFirmaPermisoTableModel().addRows(locListFirma);
			
			// Actualización de la tabla de Lineas Solicitud Suministros
			List<LineaSolicitudSuministro> locListSS = new ArrayList<LineaSolicitudSuministro>();
			
			for (Iterator iterator = this.getAbmModel().getObjetoABM().getListaLineaSolSuministro().iterator(); iterator.hasNext();) {
				LineaSolicitudSuministro lineaSS = (LineaSolicitudSuministro) iterator.next();
				System.out.println("lineaSS.getCuentaRfr() ----> " + lineaSS.getCuentaRfr());
				locListSS.add(lineaSS);
			}
			
			System.out.println("lineaSS     ---- " + locListSS);
			this.getLineaSolicitudSuministroTableModel().clearTable();
			this.getLineaSolicitudSuministroTableModel().addRows(locListSS);
		}
	}
	
	void seleccionarCuenta() throws Exception {
		int locLineaSolicitudSuministros = this.getView().getPnlTablaLineaSS().getPnlTabla().getTblDatos().getSelectedRow();
		
		System.out.println("········································· " + locLineaSolicitudSuministros);
		if(locLineaSolicitudSuministros > -1){ 
			LineaSolicitudSuministro locLineaSS = this.getLineaSolicitudSuministroTableModel().getRow(locLineaSolicitudSuministros);
			
			AdminCuenta adminCuenta = new AdminCuenta(this.getView(),true);
			Cuenta locCuenta = adminCuenta.openSelect();
			
			if (locCuenta != null) {
				CuentaRfr cuentaRfr = new CuentaRfr();
				cuentaRfr.setAbreviatura(locCuenta.getAbreviatura());
				cuentaRfr.setCodigoImputacion(locCuenta.getCodigoImputacion());
				cuentaRfr.setIdCuenta(locCuenta.getIdCuenta());
				cuentaRfr.setNombre(locCuenta.getNombre());
				
				locLineaSS.setCuentaRfr(cuentaRfr);
				
				this.actualizarABMModel();
				this.actualizarView();
			}
		}
		
	}
	
	void limpiarCuenta() throws RemoteException, TrascenderException, Exception {
		int locLineaSolicitudSuministros = this.getView().getPnlTablaLineaSS().getPnlTabla().getTblDatos().getSelectedRow();
		
		if(locLineaSolicitudSuministros > -1){
			LineaSolicitudSuministro locLineaSS = this.getLineaSolicitudSuministroTableModel().getRow(locLineaSolicitudSuministros);
			locLineaSS.setCuentaRfr(null);
			
			this.actualizarABMModel();
			this.actualizarView();
		}
	}
	
}

class BtnSeleccionarCuentaListener implements ActionListener {
	private ABMAsociacionCuentaSolicitudSuministro controller;

	public BtnSeleccionarCuentaListener(
			ABMAsociacionCuentaSolicitudSuministro controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.seleccionarCuenta();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}

class BtnLimpiarCuentaListener implements ActionListener{

	private ABMAsociacionCuentaSolicitudSuministro controller;
	
	public BtnLimpiarCuentaListener(ABMAsociacionCuentaSolicitudSuministro controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.limpiarCuenta();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}

