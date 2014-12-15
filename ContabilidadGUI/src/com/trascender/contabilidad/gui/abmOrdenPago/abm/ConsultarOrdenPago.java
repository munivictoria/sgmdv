package com.trascender.contabilidad.gui.abmOrdenPago.abm;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JDialog;
import javax.swing.JFrame;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.swing.JRViewer;

import com.trascender.contabilidad.gui.abmLineaOrdenPago.LineaOrdenPagoTableModel;
import com.trascender.contabilidad.gui.abmOrdenPago.ChequeOrdenPagoTableModel;
import com.trascender.contabilidad.gui.abmOrdenPago.DebitoTableModel;
import com.trascender.contabilidad.gui.abmOrdenPago.OrdenPagoABMModel;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.Debito;
import com.trascender.contabilidad.recurso.persistent.MovimientoBancario;
import com.trascender.contabilidad.recurso.persistent.OrdenPago;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.principal.ConexionReportes;
import com.trascender.gui.framework.util.Conversor;

public class ConsultarOrdenPago extends ABMOrdenPago{

	private ConsultarOrdenPagoView view;
	private OrdenPagoABMModel abmModel = new OrdenPagoABMModel();
	private LineaOrdenPagoTableModel facturaTableModel = new LineaOrdenPagoTableModel();
	private ChequeOrdenPagoTableModel chequeTableModel = new ChequeOrdenPagoTableModel();
	private DebitoTableModel debitoTableModel = new DebitoTableModel();

	public ConsultarOrdenPago(JFrame owner, boolean habilitarBtnConfirmar) throws Exception {
		this.view = new ConsultarOrdenPagoView(owner);
		this.init();
		this.getView().getPnlPie().getBtnAceptar().setVisible(habilitarBtnConfirmar);
	}
	
	public ConsultarOrdenPago(JDialog owner, boolean habilitarBtnConfirmar) throws Exception {
		this.view = new ConsultarOrdenPagoView(owner);
		this.init();
		this.getView().getPnlPie().getBtnAceptar().setVisible(habilitarBtnConfirmar);
	}
	
	protected void init() {
	 	super.init();
		this.setListeners();
		this.disabledAll();
		this.visibleAll();
		this.editableAll(); 
	}

	private void disabledAll() {
		
	}
	
	private void visibleAll() {
		this.getView().getPnlBtnSeleccionProveedor().setVisible(false);
		this.getView().getPnlBtnSeleccionRetencion().setVisible(false);
		this.getView().getPnlBotonesTablaFactura().setVisible(false);
		this.getView().getPnlBotonesTblCheque().setVisible(false);
		this.getView().getPnlBotonesTblDebito().setVisible(false);
		this.getView().getPnlPie().getBtnImprimir().setVisible(true);
	}
	
	private void editableAll() {
		this.getView().getTfFechaEmision().setEditable(false);
		this.getView().getTfFechaPago().setEditable(false);
		this.getView().getTfProveedor().setEditable(false);
		this.getView().getTfRetencion().setEditable(false);
	}
	
	private void setListeners() {
		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnAceptarListeners(this));
		this.getView().getPnlPie().getBtnImprimir().addActionListener(new BtnImprimirListener(this));
	}
		
	@Override
	public OrdenPagoABMModel getAbmModel() {
		return abmModel;
	}

	@Override
	public LineaOrdenPagoTableModel getFacturaTableModel() {
		return this.facturaTableModel;
	}

	@Override
	public ABMOrdenPagoView getView() {
		return view;
	}

	@Override
	public ChequeOrdenPagoTableModel getChequeTableModel() {
		return this.chequeTableModel;
	}

	@Override
	public DebitoTableModel getDebitoTableModel() {
		return this.debitoTableModel;
	}
	
	public void confirmarOrdenPago() throws Exception {
//		Date fPago = Conversor.getDate(this.getView().getTfFechaPago().getText());
//		Date fEmi = Conversor.getDate(this.getView().getTfFechaEmision().getText());
//
//		if (this.validarDatos()) {
//			if (fPago.compareTo(fEmi) > 0 || fPago.compareTo(fEmi) == 0) {
//				this.actualizarABMModel();
//				this.getAbmModel().confirmar();
//				this.setOperacionRealizada(true);
//				this.close();
//			}
//			else {
//				AppManager.getInstance().showErrorMsg(this.getView(), "La Fecha de Pago no puede ser anterior a la Fecha de Emisión.");
//			}
//		}
		
		this.actualizarABMModel();
		this.getAbmModel().confirmar();
		this.setOperacionRealizada(true);
		this.close();
	}
	
	public void imprimirOrdenPago() throws Exception {
		OrdenPago locOrdenPago = this.getAbmModel().getObjetoABM();
		JasperPrint locJasperPrint = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemReportesContabilidad().getReporteOrdenPago(locOrdenPago.getIdDocumentoEgreso());
		this.getView().mostrarVistaPreviaReporte(locJasperPrint, "Vista previa de Orden de Pago");
	}
	
	private List calcularTotales() {
		List<Double> locRetorno = new ArrayList<Double>();
		
		OrdenPago locOrdenPago = this.getAbmModel().getObjetoABM();
		Double locTotalCheque = 0d;
		Double locTotalDebito = 0d;
		
		for (MovimientoBancario unMovimiento : locOrdenPago.getMovimientosBancarios()) {
			if(unMovimiento instanceof Debito) {
				locTotalDebito += unMovimiento.getImporte();
			} else {
				locTotalCheque += unMovimiento.getImporte();
			}
		}
		
		locRetorno.add(locTotalDebito);
		locRetorno.add(locTotalCheque);
		
		return locRetorno;		
	}
}

class BtnAceptarListeners implements ActionListener {
	private ConsultarOrdenPago controller;
	
	public BtnAceptarListeners(ConsultarOrdenPago controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.confirmarOrdenPago();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnImprimirListener implements ActionListener {
	
	private ConsultarOrdenPago controller;
	
	public BtnImprimirListener(ConsultarOrdenPago controller) {
		this.controller = controller;
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.imprimirOrdenPago();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(),
					ex.getMessage());
		}
	}

}
