package com.trascender.contabilidad.gui.abmRetencion.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.JLabel;

import com.trascender.compras.recurso.persistent.suministros.Proveedor;
import com.trascender.contabilidad.gui.abmOrdenPago.OrdenPagoBusquedaModel;
import com.trascender.contabilidad.gui.abmOrdenPago.OrdenPagoTableModel;
import com.trascender.contabilidad.gui.abmParametroRetencion.AdminParametroRetencion;
import com.trascender.contabilidad.gui.abmProveedor.AdminProveedor;
import com.trascender.contabilidad.gui.abmRetencion.LineaRetencionTableModel;
import com.trascender.contabilidad.gui.abmRetencion.RetencionABMModel;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.LineaRetencion;
import com.trascender.contabilidad.recurso.persistent.OrdenPago;
import com.trascender.contabilidad.recurso.persistent.ParametroRetencion;
import com.trascender.framework.recurso.transients.Periodo;
import com.trascender.framework.util.Periodicidad;
import com.trascender.gui.framework.abmStandard.ABMController;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Conversor;
import com.trascender.gui.framework.util.Validador;
import com.trascender.habilitaciones.recurso.persistent.Retencion;

public abstract class ABMRetencion extends ABMController<Retencion>{
	
	public abstract ABMRetencionView getView();
	public abstract RetencionABMModel getAbmModel();
	public abstract OrdenPagoTableModel getOrdenPagoTableModel();
	public abstract LineaRetencionTableModel getLineaRetencionTableModel();
	public abstract OrdenPagoBusquedaModel getOrdenPagoBusquedaModel();
	
	@Override
	protected void init() {
		super.init();
		
		this.setModels();
		this.setListeners();
//		this.actualizarView();
		
		this.getView().getPnlBtnTablaParametroRetencion().getBtnEliminar().setVisible(false);
		this.getView().getPnlBtnTablaParametroRetencion().getBtnQuitarTodos().setVisible(false);
		
		this.getView().getPnlBtnTablaParametroRetencion().getBtnAgregar().setEnabled(false);
		this.getView().getPnlBtnTablaParametroRetencion().getBtnModificar().setEnabled(false);
	}
	
	private void setModels() {
		this.getView().setOrdenPagoTableModel(this.getOrdenPagoTableModel());
		this.getView().setLineaRetencionTableModel(this.getLineaRetencionTableModel());
	}

	private void setListeners() {
		this.getView().getPnlBotonesSeleccionProveedor().getBtnSeleccionar().addActionListener(new BtnSeleccionarProveedorListener(this));
		this.getView().getPnlBotonesSeleccionProveedor().getBtnLimpiar().addActionListener(new BtnLimpiarProveedorListener(this));
		
		this.getView().getBtnBuscarOrdenesDePagos().addActionListener(new BtnBuscarOrdenesDePagoListener(this));
		
		this.getView().getPnlBtnTablaParametroRetencion().getBtnAgregar().addActionListener(new AgregarLineaRetencionListener(this));
		this.getView().getPnlBtnTablaParametroRetencion().getBtnModificar().addActionListener(new QuitarLineaRetencionListener(this));
	}
	
	@Override
	public void actualizarABMModel() {
		try {
//			if (!this.getView().getTfMes().getText().equals("") && !this.getView().getTfAnio().getText().equals("")
//					&& this.getView().getTfMes().getText() != null && this.getView().getTfAnio().getText() != null) {
				String fecha = "01/" + this.getView().getTfMes().getText() + "/" + this.getView().getTfAnio().getText();
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Date fechaFin = sdf.parse(fecha); 
				Calendar calendario= Calendar.getInstance();
				calendario.setTime(fechaFin);
					
//			} else {
//				this.getAbmModel().setPeriodo(null);
//			}
//			
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		this.getAbmModel().fireActualizarDatos();
	}
	
	@Override
	public void actualizarView() {
		this.getView().getTfProveedor().setText(Conversor.getVacioSiNull(this.getAbmModel().getProveedor()));
		
		if (this.getAbmModel().getPeriodo() != null) {
			this.getView().getTfMes().setText(Conversor.getVacioSiNull(Integer.toString(this.getAbmModel().getPeriodo().getFechaInicio().get(Calendar.MONTH)+1)));
			this.getView().getTfAnio().setText(Conversor.getVacioSiNull(Integer.toString(this.getAbmModel().getPeriodo().getFechaInicio().get(Calendar.YEAR))));
		} else {
			this.getView().getTfMes().setText("");
			this.getView().getTfAnio().setText("");
		}
		
		try {  //Actualización de la tabla Linea Retencion
			if (this.getLineaRetencionTableModel()!= null) {
				List<LineaRetencion> locListaRetancion = new ArrayList<LineaRetencion>();
				this.getLineaRetencionTableModel().clearTable();
				for (LineaRetencion locLineaRetencion : this.getAbmModel().getListaLineaRetencion()) {
					locListaRetancion.add((LineaRetencion)locLineaRetencion);
				}
				this.getLineaRetencionTableModel().addRows(locListaRetancion);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	@Override
	public boolean validarDatos() {
		boolean validacionOK = true;
		
		List<Object> attNulos = new ArrayList<Object>();
		List<JLabel> lblNulos = new ArrayList<JLabel>();
		
		List<String> attMes = new ArrayList<String>();
		List<JLabel> lblMes = new ArrayList<JLabel>();
		
		List<Object> attEnteros = new ArrayList<Object>();
		List<JLabel> lblEnteros = new ArrayList<JLabel>();
		
		List<Object> attAnio = new ArrayList<Object>();
		List<JLabel> lblAnio = new ArrayList<JLabel>();
		List<Integer> longAnio = new ArrayList<Integer>();
		
		attEnteros.add(this.getView().getTfMes().getText());
		lblEnteros.add(this.getView().getLblMes());
		
		attEnteros.add(this.getView().getTfAnio().getText());
		lblEnteros.add(this.getView().getLblAnio());
		
		attNulos.add(this.getView().getTfProveedor().getText());
		lblNulos.add(this.getView().getLblProveedor());
		
		attNulos.add(this.getView().getTfMes().getText());
		lblNulos.add(this.getView().getLblMes());
		
		attNulos.add(this.getView().getTfAnio().getText());
		lblNulos.add(this.getView().getLblAnio());
		
		attMes.add(this.getView().getTfMes().getText());
		lblMes.add(this.getView().getLblMes());
		
		attAnio.add(this.getView().getTfAnio().getText());
		lblAnio.add(this.getView().getLblAnio());
		longAnio.add(4);	
		
		List<String> listaErrores = new ArrayList<String>();
		try {
			listaErrores.addAll(Validador.validarEnteros(attEnteros, lblEnteros));
			listaErrores.addAll(Validador.validarNulos(attNulos, lblNulos));
			listaErrores.addAll(Validador.validarMes(attMes, lblMes));
			listaErrores.addAll(Validador.validarLongitudExacta(attAnio, lblAnio, longAnio));
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
	
	public void openAgregarLineaRetencion() throws Exception {
		AdminParametroRetencion adminParametroRetencion = new AdminParametroRetencion(this.getView());
		ParametroRetencion locParametroRetencion = adminParametroRetencion.openSelect();
		if(locParametroRetencion != null){
			locParametroRetencion = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionEgresos().getParametroRetencionByID(locParametroRetencion.getIdParametroRetencion());
			
			List<LineaRetencion> locListaLineaRetencion = new ArrayList<LineaRetencion>(this.getLineaRetencionTableModel().getListaObjetos());
				
			boolean locExiste = false;
			Iterator<LineaRetencion> locIterador = locListaLineaRetencion.iterator();
					
			while(locIterador.hasNext() && !locExiste){
				LineaRetencion locLineaPresupuesto = locIterador.next();
				if(locLineaPresupuesto.getParametroRetencion().getIdParametroRetencion() == locParametroRetencion.getIdParametroRetencion()){
					locExiste = true;
				} 
			}
			if(!locExiste){
				LineaRetencion lR = new LineaRetencion();
				lR.setParametroRetencion(locParametroRetencion);
				lR.setImporte(ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionEgresos()
						.calcularMontoRetencion(locParametroRetencion, this.getAbmModel().getPeriodo(), this.getAbmModel().getProveedor()));
				
				this.getAbmModel().getListaLineaRetencion().add(lR);
				this.actualizarABMModel();
				this.actualizarView();
			}		
		}
	}
	
	void seleccionarProveedor() throws Exception {
//		if (this.getView().getTfMes().getText() != null && !this.getView().getTfMes().getText().equals("")
//				&& this.getView().getTfAnio().getText() != null && !this.getView().getTfAnio().getText().equals("")) {
			AdminProveedor adminProveedor = new AdminProveedor(this.getView());
			Proveedor locProveedor = adminProveedor.openSelect();
			
			if (locProveedor != null) {
				this.actualizarABMModel();
				this.getAbmModel().setProveedor(locProveedor);
				
				//this.buscarOrdenesDePago();
				
			}
//		} else {
//			AppManager.getInstance().showInformationMsg(this.getView(), "Debe ingrasar los datos solicitados previamente en los campos Mes y Año.");
//		}
//		
		this.actualizarView();
		this.getLineaRetencionTableModel().clearTable();
	}
	
	void limpiarProveedor() {
		this.getAbmModel().setProveedor(null);
		this.getAbmModel().setPeriodo(null);
		this.getOrdenPagoTableModel().clearTable();
		this.getLineaRetencionTableModel().clearTable();
		this.actualizarABMModel();
		this.actualizarView();
		this.getView().getPnlBtnTablaParametroRetencion().getBtnAgregar().setEnabled(false);
		this.getView().getPnlBtnTablaParametroRetencion().getBtnModificar().setEnabled(false);
	}
	
	public void buscarOrdenesDePago() {
		this.actualizarABMModel();
		this.actualizarView();
		this.getOrdenPagoTableModel().clearTable();
		
		this.getOrdenPagoBusquedaModel().setProveedor(this.getAbmModel().getProveedor());
		this.getOrdenPagoBusquedaModel().setFechaEmisionDesde(this.getAbmModel().getPeriodo().getFechaInicio().getTime());
		this.getOrdenPagoBusquedaModel().setFechaEmisionHasta(this.getAbmModel().getPeriodo().getFechaFin().getTime());
		
		this.getView().getPnlBtnTablaParametroRetencion().getBtnAgregar().setEnabled(false);
		this.getView().getPnlBtnTablaParametroRetencion().getBtnModificar().setEnabled(false);
		
		final ABMRetencion controller = this;
		Thread locThread = new Thread(new Runnable(){
			public void run() {
				try {
					controller.actualizarABMModel();
					controller.iniBusqueda();
					List<OrdenPago> locLista = controller.getOrdenPagoBusquedaModel().buscar();
					controller.getOrdenPagoTableModel().setListaObjetos(locLista);
					if (locLista == null) {
						controller.getView().getPnlBtnTablaParametroRetencion().getBtnAgregar().setEnabled(false);
						controller.getView().getPnlBtnTablaParametroRetencion().getBtnModificar().setEnabled(false);
					} else {
						controller.getView().getPnlBtnTablaParametroRetencion().getBtnAgregar().setEnabled(true);
						controller.getView().getPnlBtnTablaParametroRetencion().getBtnModificar().setEnabled(true);
					}
					
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
	
	/**
	 * Cambia los componentes correspondientes para indicar que comenzó la búsqueda.
	 */
	public void iniBusqueda() {
		this.getView().getPnlTablaOrdenesPago().setCargando(true);
		this.getView().getPnlTablaOrdenesPago().repaint();
		this.getView().getPnlTablaOrdenesPago().getTblDatos().setEnabled(false);
	}
	
	/**
	 * Restablece el estado de los componentes para poder iniciar una nueva búsqueda.
	 */
	public void finBusqueda() {
		this.getView().getPnlTablaOrdenesPago().setCargando(false);
		this.getView().getPnlTablaOrdenesPago().setEnabled(true);
		this.getView().getPnlTablaOrdenesPago().getTblDatos().setEnabled(true);
		this.getView().getPnlTablaOrdenesPago().repaint();
	}
	
	void quitarLineaRetencion() throws Exception{
		int locLineaRetencion = this.getView().getPnlTablaParametrosRetencion().getTblDatos().getSelectedRow();
		
		if(locLineaRetencion > -1){ 
			if (this.getLineaRetencionTableModel()!= null) {
				LineaRetencion objetoSeleccionado = this.getLineaRetencionTableModel().getRow(locLineaRetencion);
				//objetoSeleccionado = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionEgresos().getLineaRetencionByID(objetoSeleccionado.getIdLineaRetencion());
					
				this.getAbmModel().getListaLineaRetencion().remove(objetoSeleccionado);
				this.actualizarView();
			}
		}
	}

}

class BtnSeleccionarProveedorListener implements ActionListener {
	private ABMRetencion controller;
	
	public BtnSeleccionarProveedorListener(ABMRetencion controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.seleccionarProveedor();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnLimpiarProveedorListener implements ActionListener {
	private ABMRetencion controller;
	
	public BtnLimpiarProveedorListener(ABMRetencion controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.limpiarProveedor();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnBuscarOrdenesDePagoListener implements ActionListener {
	private ABMRetencion controller;
	
	public BtnBuscarOrdenesDePagoListener(ABMRetencion controller) {
		this.controller = controller;
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			if (this.controller.validarDatos()) {
				this.controller.buscarOrdenesDePago();
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class AgregarLineaRetencionListener implements ActionListener {
	private ABMRetencion controller;
	
	public AgregarLineaRetencionListener(ABMRetencion controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openAgregarLineaRetencion();
		} catch (Exception e2) {
			e2.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), e2.getMessage());
		}

	}
	
}

class QuitarLineaRetencionListener implements ActionListener {
	private ABMRetencion controller;
	
	public QuitarLineaRetencionListener(ABMRetencion controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.quitarLineaRetencion();
		} catch (Exception e2) {
			e2.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), e2.getMessage());
		}

	}
	
}
