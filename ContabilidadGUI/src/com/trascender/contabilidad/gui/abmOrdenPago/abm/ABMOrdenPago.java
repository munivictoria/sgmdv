package com.trascender.contabilidad.gui.abmOrdenPago.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JLabel;

import com.trascender.compras.recurso.persistent.suministros.Factura;
import com.trascender.compras.recurso.persistent.suministros.Proveedor;
import com.trascender.contabilidad.gui.abmCheque.AdminCheque;
import com.trascender.contabilidad.gui.abmDebitoBancario.AdminDebitoBancario;
import com.trascender.contabilidad.gui.abmFactura.AdminFactura;
import com.trascender.contabilidad.gui.abmLineaOrdenPago.LineaOrdenPagoTableModel;
import com.trascender.contabilidad.gui.abmLineaOrdenPago.abm.ModificarLineaOrdenPago;
import com.trascender.contabilidad.gui.abmOrdenPago.ChequeOrdenPagoTableModel;
import com.trascender.contabilidad.gui.abmOrdenPago.DebitoTableModel;
import com.trascender.contabilidad.gui.abmOrdenPago.OrdenPagoABMModel;
import com.trascender.contabilidad.gui.abmProveedor.AdminProveedor;
import com.trascender.contabilidad.gui.abmRetencion.abm.ConsultarRetencion;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.Cheque;
import com.trascender.contabilidad.recurso.persistent.Debito;
import com.trascender.contabilidad.recurso.persistent.LineaOrdenPago;
import com.trascender.contabilidad.recurso.persistent.MovimientoBancario;
import com.trascender.contabilidad.recurso.persistent.OrdenPago;
import com.trascender.framework.recurso.transients.Periodo;
import com.trascender.framework.util.Periodicidad;
import com.trascender.gui.framework.abmStandard.ABMController;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Conversor;
import com.trascender.gui.framework.util.Validador;

public abstract class ABMOrdenPago extends ABMController<OrdenPago>{

	public abstract ABMOrdenPagoView getView();
	public abstract OrdenPagoABMModel getAbmModel();
	public abstract LineaOrdenPagoTableModel getFacturaTableModel();
	public abstract ChequeOrdenPagoTableModel getChequeTableModel();
	public abstract DebitoTableModel getDebitoTableModel();
	
	@Override
	protected void init() {
		super.init();
		this.setModels();
		this.setListeners();
		this.setTextoBtnSeleccionarRetencion();
		this.habilitarBotonesTablaOrdenCompra(false);
	}
	
	private void setModels() {
		this.getView().setAbmModel(this.getAbmModel());
		this.getView().getPnlTablaFactura().getTblDatos().setModel(this.getFacturaTableModel());
		this.getView().getPnlTablaCheque().getTblDatos().setModel(this.getChequeTableModel());
		this.getView().getPnlTablaDebito().getTblDatos().setModel(this.getDebitoTableModel());
	}
	
	private void setListeners() {
		this.getView().getPnlBtnSeleccionProveedor().getBtnSeleccionar().addActionListener(new BtnSeleccionarProveedorListener(this));
		this.getView().getPnlBtnSeleccionProveedor().getBtnLimpiar().addActionListener(new BtnLimpiarProveedorListener(this));
//		
		this.getView().getPnlBotonesTblCheque().getBtnAgregar().addActionListener(new BtnSeleccionarChequeListener(this));
		this.getView().getPnlBotonesTblCheque().getBtnEliminar().addActionListener(new BtnQuitarChequeListener(this));
		this.getView().getPnlBotonesTblCheque().getBtnQuitarTodos().addActionListener(new BtnQuitarTodosChequesListener(this));
			
		this.getView().getPnlBotonesTblDebito().getBtnAgregar().addActionListener(new BtnSeleccionarDebitoBancarioListener(this));
		this.getView().getPnlBotonesTblDebito().getBtnEliminar().addActionListener(new BtnQuitarDebitoListener(this));
		this.getView().getPnlBotonesTblDebito().getBtnQuitarTodos().addActionListener(new BtnQuitarTodosDebitosListener(this));
			
		this.getView().getPnlBtnSeleccionRetencion().getBtnSeleccionar().addActionListener(new BtnSeleccionarComprobanteRetencionListener(this));
//		this.getView().getPnlBtnSeleccionComprobanteRetencion().getBtnLimpiar().addActionListener(new BtnLimpiarComprobanteRetencionListener(this));
		
		this.getView().getPnlBotonesTablaFactura().getBtnAgregar().addActionListener(new BtnAgregarLineaOrdenPagoListener(this));
		this.getView().getPnlBotonesTablaFactura().getBtnModificar().addActionListener(new BtnModificarLineaOrdenPagoListener(this));
		this.getView().getPnlBotonesTablaFactura().getBtnEliminar().addActionListener(new BtnQuitarLineaOrdenPagoListener(this));
		this.getView().getPnlBotonesTablaFactura().getBtnQuitarTodos().addActionListener(new BtnQuitarTodosLineaOrdenPagoListener(this));
		
	}
	
	@Override
	protected void actualizarABMModel() {
		this.getAbmModel().setFechaEmision(Conversor.getDate(this.getView().getTfFechaEmision().getText()));
		this.getAbmModel().setFechaPago(Conversor.getDate(this.getView().getTfFechaPago().getText()));
		this.getAbmModel().fireActualizarDatos();
	}

	@Override
	public void actualizarView() {
		this.getView().getTfFechaEmision().setValue(Conversor.getString(this.getAbmModel().getFechaEmision()));
		this.getView().getTfFechaPago().setValue(Conversor.getString(this.getAbmModel().getFechaPago()));
		this.getView().getTfProveedor().setText(Conversor.getVacioSiNull(this.getAbmModel().getProveedor()));
		this.getView().getTfRetencion().setText(Conversor.getVacioSiNull(this.getAbmModel().getRetencion()));
	
		//Actualización de la tabla principal
		if (this.getAbmModel().getListaLineasOrdenPago() != null && this.getFacturaTableModel().getListaObjetos() != null) {
			
			List<LineaOrdenPago> locList = new ArrayList<LineaOrdenPago>(this.getAbmModel().getListaLineasOrdenPago());
			
			this.getFacturaTableModel().clearTable();
			this.getFacturaTableModel().addRows(locList);
			
			try {
				//this.setTableCellEditor();
			}
			catch (Exception ex) {
				ex.printStackTrace();
				AppManager.getInstance().showErrorMsg(this.getView(), ex.getMessage());
			}
		}

		try {
			//Actualización de la tabla Movimiento Bancario
			if (this.getAbmModel().getMovimientoBancario() != null && this.getChequeTableModel()!= null) {
				List<Cheque> locListCheque = new ArrayList<Cheque>();
				List<Debito> locListDebito = new ArrayList<Debito>();
				
				this.getChequeTableModel().clearTable();
				this.getDebitoTableModel().clearTable();
				for (MovimientoBancario locMovimientoBancario : this.getAbmModel().getMovimientoBancario()) {
					if (locMovimientoBancario instanceof Cheque) {
						locListCheque.add((Cheque)locMovimientoBancario);
					//	this.getChequeTableModel().clearTable();
					} else {
						locListDebito.add((Debito)locMovimientoBancario);
				//		this.getDebitoTableModel().clearTable();
					}
				}
				this.getChequeTableModel().addRows(locListCheque);
				this.getDebitoTableModel().addRows(locListDebito);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

//	protected void enableBotonesTabla(boolean flag) {
//		this.getView().getPnlBotonesTabla().getBtnAgregar().setEnabled(flag);
//		this.getView().getPnlBotonesTabla().getBtnModificar().setEnabled(flag);
//		this.getView().getPnlBotonesTabla().getBtnEliminar().setEnabled(flag);
//		this.getView().getPnlBotonesTabla().getBtnQuitarTodos().setEnabled(flag);
//	}
	
	/**
	 * Cambia el texto del botón seleccionar para que parezca un botón consultar
	 */
	public void setTextoBtnSeleccionarRetencion() {
    	this.getView().getPnlBtnSeleccionRetencion().getBtnSeleccionar().setText("...");
    	this.getView().getPnlBtnSeleccionRetencion().getBtnSeleccionar().setToolTipText("Consultar datos de la Retención.");
    }
	
	@Override
	public boolean validarDatos() {
		boolean validacionOK = true;
		
		List<Object> attNulos = new ArrayList<Object>();
		List<JLabel> lblNulos = new ArrayList<JLabel>();
		
		List<Integer> attMin = new ArrayList<Integer>();
		List<JLabel> lblMin = new ArrayList<JLabel>();
		List<Integer> cantMin = new ArrayList<Integer>();
		
		List<String> attFechas = new ArrayList<String>();
		List<JLabel> lblFechas = new ArrayList<JLabel>();
		
		attNulos.add(this.getView().getTfFechaEmision().getText());
		lblNulos.add(this.getView().getLblFechaEmision());
		
		attNulos.add(this.getView().getTfFechaPago().getText());
		lblNulos.add(this.getView().getLblFechaPago());
		
		attNulos.add(this.getView().getTfProveedor().getText());
		lblNulos.add(this.getView().getLblProveedor());
		
		attMin.add(this.getView().getPnlTablaFactura().getTblDatos().getRowCount());
		lblMin.add(new JLabel("La Lista de Facturas"));
		cantMin.add(1);
		
		attFechas.add(this.getView().getTfFechaEmision().getText());
		lblFechas.add(this.getView().getLblFechaEmision());
		
		attFechas.add(this.getView().getTfFechaPago().getText());
		lblFechas.add(this.getView().getLblFechaPago());
		
		List<String> listaErrores = new ArrayList<String>();
		try {
			listaErrores.addAll(Validador.validarNulos(attNulos, lblNulos));
			listaErrores.addAll(Validador.validarCantidadMinima(attMin, lblMin, cantMin));
			listaErrores.addAll(Validador.validarFechaNoMayorALaActual(this.getView().getTfFechaEmision().getText(), this.getView().getLblFechaEmision()));
			listaErrores.addAll(Validador.validarFechas(attFechas, lblFechas));
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

	void seleccionarProveedor() throws Exception {
		AdminProveedor adminProveedor = new AdminProveedor(this.getView());
		Proveedor locProveedor = adminProveedor.openSelect();
		
		if (locProveedor != null) {
			this.getAbmModel().setProveedor(locProveedor);
			this.actualizarABMModel();
			this.actualizarView();
			this.habilitarBotonesTablaOrdenCompra(true);
			this.getFacturaTableModel().clearTable();
			this.getAbmModel().getObjetoABM().getLineasOrdenPago().clear();
		}
	}
	
	void limpiarProveedor() throws Exception {
		this.getAbmModel().setProveedor(null);
		this.actualizarABMModel();
		this.actualizarView();
		this.habilitarBotonesTablaOrdenCompra(false);
//		this.quitarTodosLineaOrdenPago();
		if (!this.getFacturaTableModel().getListaObjetos().isEmpty()) {
			this.getFacturaTableModel().clearTable();
			this.getAbmModel().getObjetoABM().getLineasOrdenPago().clear();
		}
	}
	
	void seleccionarCheque() throws Exception {
		AdminCheque adminCheque = new AdminCheque(this.getView());
		Cheque locCheque = adminCheque.openSelect();
		if(locCheque != null){
			locCheque = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionEgresos().getChequeByID(locCheque.getIdMovimientoBancario());
			this.getAbmModel().getMovimientoBancario().add(locCheque);
			this.actualizarView();
		}
	}
	
	void seleccionarDebitoBancario() throws Exception {
		AdminDebitoBancario adminDebitoBancario = new AdminDebitoBancario(this.getView());
		Debito locDebitoBancario = adminDebitoBancario.openSelect();
		if (locDebitoBancario!= null) {
			locDebitoBancario = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionEgresos().getDebitoByID(locDebitoBancario.getIdMovimientoBancario());
				this.getAbmModel().getMovimientoBancario().add(locDebitoBancario);
				this.actualizarView();
		}
	}
	
	void limpiarMovimientoBancario() throws Exception {
		this.getAbmModel().setMovimientoBancario(null);
		this.actualizarABMModel();
		this.actualizarView();
	}
	
	void seleccionarRetencion() throws Exception {
		if (this.getAbmModel().getRetencion() != null) {
			if (this.getAbmModel().getProveedor() != null && this.getAbmModel().getFechaEmision() != null) {
					Calendar calendario= Calendar.getInstance();
					calendario.setTime(this.getAbmModel().getFechaEmision());
					Periodo periodo = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemRegistroValuado()
					.getPeriodo(Periodicidad.MENSUAL, calendario.get(Calendar.MONTH)+1, calendario.get(Calendar.YEAR));

					Proveedor locProveedor = this.getAbmModel().getProveedor();
					if (locProveedor != null) {
						ConsultarRetencion consultarRetencion= new ConsultarRetencion(this.getView(), this.getAbmModel().getComprobanteRetencion());
						consultarRetencion.getAbmModel().setProveedor(locProveedor);
						consultarRetencion.getAbmModel().setPeriodo(periodo);
						consultarRetencion.getView().getTfMes().setText(Integer.toString(periodo.getFechaInicio().get(Calendar.MONTH)+1));
						consultarRetencion.getView().getTfAnio().setText(Integer.toString(periodo.getFechaInicio().get(Calendar.YEAR)));
						
						consultarRetencion.actualizarView();
						consultarRetencion.actualizarABMModel();
						
						consultarRetencion.getOrdenPagoBusquedaModel().setProveedor(locProveedor);
						consultarRetencion.getOrdenPagoBusquedaModel().setFechaEmisionDesde(periodo.getFechaInicio().getTime());
						consultarRetencion.getOrdenPagoBusquedaModel().setFechaEmisionHasta(periodo.getFechaFin().getTime());
						
						consultarRetencion.actualizarView();
						consultarRetencion.buscarOrdenesDePago();
						consultarRetencion.open();
					}
			} 
//			else {
//				AppManager.getInstance().showInformationMsg(this.getView(), "ver");
//			}
		} else {
			AppManager.getInstance().showInformationMsg(this.getView(), "Esta Orden de Pago no tiene una Retención aplicada.");
		}
	}
	
	void limpiarComprobanteRetencion() throws Exception {
		this.getAbmModel().setComprobanteRetencion(null);
		this.actualizarABMModel();
		this.actualizarView();
	}
	
	void agregarLineaOrdenPago() throws Exception{
//		AgregarLineaOrdenPago agregarLineaOrdenPago = new AgregarLineaOrdenPago(this.getView());
//		agregarLineaOrdenPago.open();
//		
//		if (agregarLineaOrdenPago.isOperacionRealizada()) {
//			if(!this.getAbmModel().getObjetoABM().getLineasOrdenPago().contains(agregarLineaOrdenPago.getAbmModel().getObjetoABM())){
//				this.getAbmModel().getObjetoABM().getLineasOrdenPago().add(agregarLineaOrdenPago.getAbmModel().getObjetoABM());
//			}
//			this.getTableModel().addRow(agregarLineaOrdenPago.getAbmModel().getObjetoABM());
//		}
		
//		AdminOrdenCompra adminOrdenCompra = new AdminOrdenCompra(this.getView());
//		adminOrdenCompra.getBusquedaModel().setProveedor(this.getAbmModel().getProveedor());
//		adminOrdenCompra.actualizarBusquedaView();
//		OrdenCompra locOrdenCompra = adminOrdenCompra.openSelect();
		
		AdminFactura adminFactura = new AdminFactura(this.getView());
		adminFactura.getBusquedaModel().setProveedor(this.getAbmModel().getProveedor());
		adminFactura.actualizarBusquedaView();
		Factura factura = adminFactura.openSelect();
		
		if (factura != null) {
			Factura locFactura = ContabilidadGUI.getInstance().getAdminSystemsCompras().getSystemAdministracionFactura().getFacturaPorId(factura.getIdFactura());
			
			LineaOrdenPago lineaOrdenPago = new LineaOrdenPago();
			lineaOrdenPago.setFactura(locFactura);
			lineaOrdenPago.setImporte(locFactura.getTotal());
			
			this.getAbmModel().getObjetoABM().getLineasOrdenPago().add(lineaOrdenPago);
			
			this.actualizarABMModel();
			this.actualizarView();
		}
		Double totalOrdenPago = new Double(0);
		for(LineaOrdenPago locLineaordenPago : this.getAbmModel().getObjetoABM().getLineasOrdenPago()){
			totalOrdenPago += locLineaordenPago.getImporte();
		}
		this.getAbmModel().getObjetoABM().setImporte(totalOrdenPago);
	}
	
	void modificarLineaOrdenPago() throws Exception{

		int locLineaOrdenPagoTabla = this.getView().getPnlTablaFactura().getTblDatos().getSelectedRow();
		LineaOrdenPago objetoSeleccionado = this.getFacturaTableModel().getRow(locLineaOrdenPagoTabla);

		if(objetoSeleccionado!= null) {
			objetoSeleccionado = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionEgresos().getLineaOrdenPagoByID(objetoSeleccionado.getIdLineaOrdenPago());
			ModificarLineaOrdenPago modificarLineaOrdenPago = new ModificarLineaOrdenPago(this.getView());
			modificarLineaOrdenPago.getAbmModel().setObjetoABM(objetoSeleccionado);
		}

	}

	void quitarLineaOrdenPago() throws Exception{
		int locLineaOrdenPagoSeleccionado = this.getView().getPnlTablaFactura().getTblDatos().getSelectedRow();
		
		if(locLineaOrdenPagoSeleccionado > -1){ 
			if (this.getFacturaTableModel() != null) {
				LineaOrdenPago objetoSeleccionado = this.getFacturaTableModel().getRow(locLineaOrdenPagoSeleccionado);
				this.getAbmModel().getListaLineasOrdenPago().remove(objetoSeleccionado);
				this.actualizarView();
			}
		}
	}
	
	
	void quitarTodosLineaOrdenPago() throws Exception{
		if (!this.getFacturaTableModel().getListaObjetos().isEmpty()) {
			if (AppManager.getInstance().showConfirmMsg(this.getView(), "¿Desea quitar todas las Lineas de Ordenes de Pago?")) {
				this.getFacturaTableModel().clearTable();
				this.getAbmModel().getObjetoABM().getLineasOrdenPago().clear();
			}
		}
	}
	
	void quitarCheque() throws Exception{
		int locChequeSeleccionado = this.getView().getPnlTablaCheque().getTblDatos().getSelectedRow();
		
		if(locChequeSeleccionado > -1){ 
			if (this.getChequeTableModel()!= null) {
				Cheque objetoSeleccionado = this.getChequeTableModel().getRow(locChequeSeleccionado);
				objetoSeleccionado = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionEgresos().getChequeByID(objetoSeleccionado.getIdMovimientoBancario());
				this.getAbmModel().getMovimientoBancario().remove(objetoSeleccionado);
				this.actualizarView();
			}
		}
	}
	
	void quitarDebito() throws Exception{
		int locDebitoSeleccionado = this.getView().getPnlTablaDebito().getTblDatos().getSelectedRow();
		
		if(locDebitoSeleccionado > -1){ 
			if (this.getDebitoTableModel()!= null) {
				Debito objetoSeleccionado = this.getDebitoTableModel().getRow(locDebitoSeleccionado);
				objetoSeleccionado = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionEgresos().getDebitoByID(objetoSeleccionado.getIdMovimientoBancario());
				this.getAbmModel().getMovimientoBancario().remove(objetoSeleccionado);
				this.actualizarView();
			}
		}
	}
	
	void quitarTodosCheques() throws Exception{
		if (this.getAbmModel().getMovimientoBancario() != null && this.getAbmModel().getMovimientoBancario().size()>0) {
			List<MovimientoBancario> locListMovimientosBancarios = new ArrayList<MovimientoBancario>();
			for (MovimientoBancario locMovimientoBancario : this.getAbmModel().getMovimientoBancario()) {
				locListMovimientosBancarios.add(locMovimientoBancario);
			}
	
			for (MovimientoBancario locMovimientoBancario : locListMovimientosBancarios) {
				if (locMovimientoBancario instanceof Cheque){ 
					this.getAbmModel().getMovimientoBancario().remove(locMovimientoBancario);
				}
			}
			this.actualizarView();
		}
	}
	
	void quitarTodosDebitos() throws Exception{
		if (this.getAbmModel().getMovimientoBancario() != null && this.getAbmModel().getMovimientoBancario().size()>0) {
			List<MovimientoBancario> locListMovimientosBancarios = new ArrayList<MovimientoBancario>();
			for (MovimientoBancario locMovimientoBancario : this.getAbmModel().getMovimientoBancario()) {
				locListMovimientosBancarios.add(locMovimientoBancario);
			}
	
			for (MovimientoBancario locMovimientoBancario : locListMovimientosBancarios) {
				if (locMovimientoBancario instanceof Debito){ 
					this.getAbmModel().getMovimientoBancario().remove(locMovimientoBancario);
				}
			}
			this.actualizarView();
		}
	}
	
	protected void habilitarBotonesTablaOrdenCompra(Boolean habilitar) {
		this.getView().getPnlBotonesTablaFactura().getBtnAgregar().setEnabled(habilitar);
		this.getView().getPnlBotonesTablaFactura().getBtnEliminar().setEnabled(habilitar);
		this.getView().getPnlBotonesTablaFactura().getBtnQuitarTodos().setEnabled(habilitar);
	}
}

class BtnSeleccionarProveedorListener implements ActionListener {
	private ABMOrdenPago controller;
	
	public BtnSeleccionarProveedorListener(ABMOrdenPago controller) {
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
	private ABMOrdenPago controller;
	
	public BtnLimpiarProveedorListener(ABMOrdenPago controller) {
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

class BtnSeleccionarChequeListener implements ActionListener {
	private ABMOrdenPago controller;
	
	public BtnSeleccionarChequeListener(ABMOrdenPago controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.seleccionarCheque();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnSeleccionarDebitoBancarioListener implements ActionListener {
	private ABMOrdenPago controller;
	
	public BtnSeleccionarDebitoBancarioListener(ABMOrdenPago controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.seleccionarDebitoBancario();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnLimpiarMovimientoBancarioListener implements ActionListener {
	private ABMOrdenPago controller;
	
	public BtnLimpiarMovimientoBancarioListener(ABMOrdenPago controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.limpiarMovimientoBancario();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnSeleccionarComprobanteRetencionListener implements ActionListener {
	private ABMOrdenPago controller;
	
	public BtnSeleccionarComprobanteRetencionListener(ABMOrdenPago controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.seleccionarRetencion();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnLimpiarComprobanteRetencionListener implements ActionListener {
	private ABMOrdenPago controller;
	
	public BtnLimpiarComprobanteRetencionListener(ABMOrdenPago controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.limpiarComprobanteRetencion();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}


class BtnAgregarLineaOrdenPagoListener implements ActionListener {
	private ABMOrdenPago controller;
	
	public BtnAgregarLineaOrdenPagoListener(ABMOrdenPago controller) {
		this.controller = controller;
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.agregarLineaOrdenPago();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnModificarLineaOrdenPagoListener implements ActionListener {
	private ABMOrdenPago controller;
	
	public BtnModificarLineaOrdenPagoListener(ABMOrdenPago controller) {
		this.controller = controller;
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.modificarLineaOrdenPago();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnQuitarLineaOrdenPagoListener implements ActionListener {
	private ABMOrdenPago controller;
	
	public BtnQuitarLineaOrdenPagoListener(ABMOrdenPago controller) {
		this.controller = controller;
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.quitarLineaOrdenPago();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnQuitarTodosLineaOrdenPagoListener implements ActionListener {
	private ABMOrdenPago controller;
	
	public BtnQuitarTodosLineaOrdenPagoListener(ABMOrdenPago controller) {
		this.controller = controller;
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.quitarTodosLineaOrdenPago();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnQuitarChequeListener implements ActionListener {
	private ABMOrdenPago controller;
	
	public BtnQuitarChequeListener(ABMOrdenPago controller) {
		this.controller = controller;
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.quitarCheque();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnQuitarDebitoListener implements ActionListener {
	private ABMOrdenPago controller;
	
	public BtnQuitarDebitoListener(ABMOrdenPago controller) {
		this.controller = controller;
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.quitarDebito();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}


class BtnQuitarTodosChequesListener implements ActionListener {
	private ABMOrdenPago controller;
	
	public BtnQuitarTodosChequesListener(ABMOrdenPago controller) {
		this.controller = controller;
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.quitarTodosCheques();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnQuitarTodosDebitosListener implements ActionListener {
	private ABMOrdenPago controller;
	
	public BtnQuitarTodosDebitosListener(ABMOrdenPago controller) {
		this.controller = controller;
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.quitarTodosDebitos();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

