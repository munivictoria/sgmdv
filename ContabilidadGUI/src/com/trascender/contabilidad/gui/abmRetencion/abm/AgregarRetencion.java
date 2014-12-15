package com.trascender.contabilidad.gui.abmRetencion.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.trascender.contabilidad.gui.abmOrdenPago.OrdenPagoBusquedaModel;
import com.trascender.contabilidad.gui.abmOrdenPago.OrdenPagoTableModel;
import com.trascender.contabilidad.gui.abmParametroRetencion.ParametroRetencionTableModel;
import com.trascender.contabilidad.gui.abmRetencion.LineaRetencionTableModel;
import com.trascender.contabilidad.gui.abmRetencion.RetencionABMModel;
import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.contabilidad.recurso.persistent.ComprobanteRetencion;
import com.trascender.gui.framework.main.AppManager;

public class AgregarRetencion extends ABMRetencion {

	private AgregarRetencionView view;
	private RetencionABMModel abmModel = new RetencionABMModel();
	private OrdenPagoTableModel ordenPagoTableModel = new OrdenPagoTableModel();
	private ParametroRetencionTableModel parametroRetencionTableModel = new ParametroRetencionTableModel();
	private OrdenPagoBusquedaModel ordenPagoBusquedaModel= new OrdenPagoBusquedaModel();
	
	private LineaRetencionTableModel lineaRetencionTableModel = new LineaRetencionTableModel();
	
	public AgregarRetencion(JDialog owner) throws Exception {
		this.view = new AgregarRetencionView(owner);
		this.getAbmModel().setObjetoABM(new ComprobanteRetencion());
//		this.getAbmModel().setProveedor(proveedor);
//		this.getAbmModel().setFecha(fecha);
		this.init();
	}

	@Override
	protected void init() {
		super.init();
		this.setModels();
//		this.buscarOrdenesDePago();
		this.getView().getPnlBtnTablaParametroRetencion().getBtnModificar().setText(MessagesContabilidad.getString("Application.btnQuitar"));
		this.getView().getPnlBtnTablaParametroRetencion().getBtnModificar().setMnemonic(MessagesContabilidad.getChar("Application.btnQuitarMnemonic"));
		this.getView().getPnlBtnTablaParametroRetencion().getBtnModificar().setToolTipText(MessagesContabilidad.getString("Application.btnQuitarToolTip"));
		
		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnAgregarListener(this));
	}
	
	private void setModels() {
		this.getView().setOrdenPagoBusquedaModel(this.ordenPagoBusquedaModel);
		this.getView().setOrdenPagoTableModel(this.ordenPagoTableModel);
//		this.getView().setParametroRetencionTableModel(this.parametroRetencionTableModel);
		this.getView().setLineaRetencionTableModel(this.lineaRetencionTableModel);
	}
	
	@Override
	public ABMRetencionView getView() {
		return this.view;
	}

	@Override
	public RetencionABMModel getAbmModel() {
		return abmModel;
	}

	@Override
	public OrdenPagoTableModel getOrdenPagoTableModel() {
		return this.ordenPagoTableModel;
	}

//	protected void actualizarBusquedaModelOrdenPago() {
//		OrdenPagoBusquedaModel locModel = this.getOrdenPagoBusquedaModel();
//		
//		if ((this.getAbmModel().getPeriodo().getFechaInicio() != null)) {
//			try {
//				String fecha = "01/" + 
//								Integer.toString(this.getAbmModel().getPeriodo().getFechaInicio().get(Calendar.MONTH)+1) + 
//								"/" + 
//								Integer.toString(this.getAbmModel().getPeriodo().getFechaInicio().get(Calendar.YEAR));
//		        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//		        Date fechaFin = sdf.parse(fecha); 
//				Calendar calendario= Calendar.getInstance();
//				calendario.setTime(fechaFin);
//				
//				Periodo locPeriodo = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemRegistroValuado()
//				.getPeriodo(Periodicidad.MENSUAL, Conversor.getInteger(this.getView().getTfMes().getText()), 
//						Conversor.getInteger(this.getView().getTfAnio().getText()));
//				
//				System.out.println("antes de ___this.getFechaEmisionDesde() ---> " + Conversor.getDate(locPeriodo.getFechaInicio()).toString());
//				System.out.println("antes de ___this.getFechaEmisionHasta() ---> " + Conversor.getDate(locPeriodo.getFechaFin()).toString());
//				
//				locModel.setFechaEmisionDesde(Conversor.getDate(locPeriodo.getFechaInicio()));
//				locModel.setFechaEmisionHasta(Conversor.getDate(locPeriodo.getFechaFin()));
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		locModel.setFechaPagoDesde(null);
//		locModel.setFechaPagoHasta(null);
//		locModel.setImporteDesde(null);
//		locModel.setImporteHasta(null);
//		locModel.setProveedor(this.getAbmModel().getProveedor());
//		
//		locModel.fireActualizarDatos();		
//	}

	@Override
	public OrdenPagoBusquedaModel getOrdenPagoBusquedaModel() {
		return this.ordenPagoBusquedaModel;
	}
	
	@Override
	public LineaRetencionTableModel getLineaRetencionTableModel() {
		return this.lineaRetencionTableModel;
	}
	
	void agregarRetencion() throws Exception {
		if (this.validarDatos() && !this.getAbmModel().getListaLineaRetencion().isEmpty()) {
			this.actualizarABMModel();
			this.getAbmModel().agregar();
			this.setOperacionRealizada(true);
			this.close();
		}
	}
	
}

class BtnAgregarListener implements ActionListener {
	private AgregarRetencion controller;
	public BtnAgregarListener(AgregarRetencion controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.agregarRetencion();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(),ex.getMessage());
		}
	}
}


