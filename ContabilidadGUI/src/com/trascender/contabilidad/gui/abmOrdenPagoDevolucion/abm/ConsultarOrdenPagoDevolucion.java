package com.trascender.contabilidad.gui.abmOrdenPagoDevolucion.abm;

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

import com.trascender.contabilidad.gui.abmOrdenPago.ChequeOrdenPagoTableModel;
import com.trascender.contabilidad.gui.abmOrdenPago.DebitoTableModel;
import com.trascender.contabilidad.gui.abmOrdenPagoDevolucion.LineaOrdenPagoDevolucionTableModel;
import com.trascender.contabilidad.gui.abmOrdenPagoDevolucion.OrdenPagoDevolucionABMModel;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.Debito;
import com.trascender.contabilidad.recurso.persistent.MovimientoBancario;
import com.trascender.contabilidad.recurso.persistent.OrdenPagoDevolucion;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.principal.ConexionReportes;
import com.trascender.gui.framework.util.Conversor;

public class ConsultarOrdenPagoDevolucion extends ABMOrdenPagoDevolucion {

	private ConsultarOrdenPagoDevolucionView view;
	private LineaOrdenPagoDevolucionTableModel lineaOPDTableModel = new LineaOrdenPagoDevolucionTableModel();
	private OrdenPagoDevolucionABMModel abmModel = new OrdenPagoDevolucionABMModel();
	private ChequeOrdenPagoTableModel chequeTableModel = new ChequeOrdenPagoTableModel();
	private DebitoTableModel debitoTableModel = new DebitoTableModel();
	
	public ConsultarOrdenPagoDevolucion(JDialog owner) throws Exception {
		this.view = new ConsultarOrdenPagoDevolucionView(owner);
		this.init();
	}
	
	public ConsultarOrdenPagoDevolucion(JFrame owner) throws Exception {
		this.view = new ConsultarOrdenPagoDevolucionView(owner);
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		this.setListeners();
		this.visibleAll();
		this.editableAll(); 
	}

	private void visibleAll() {
		this.getView().getPnlBtnSeleccionPersona().setVisible(false);
		this.getView().getPnlBotonesTblLineaOrdenPagoDev().setVisible(false);
		this.getView().getPnlBotonesTblCheque().setVisible(false);
		this.getView().getPnlBotonesTblDebito().setVisible(false);
		this.getView().getPnlPie().getBtnImprimir().setVisible(true);
	}
	
	private void editableAll() {
		this.getView().getTfFechaEmision().setEditable(false);
		this.getView().getTfFechaPago().setEditable(false);
		this.getView().getTfPersona().setEditable(false);
	}
	
	private void setListeners() {
		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnAceptarListeners(this));
		this.getView().getPnlPie().getBtnImprimir().addActionListener(new BtnImprimirListener(this));
	}
	
	@Override
	public OrdenPagoDevolucionABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public ChequeOrdenPagoTableModel getChequeTableModel() {
		return this.chequeTableModel;
	}

	@Override
	public DebitoTableModel getDebitoTableModel() {
		return this.debitoTableModel;
	}

	@Override
	public LineaOrdenPagoDevolucionTableModel getLineaOrdenPagoDevTableModel() {
		return this.lineaOPDTableModel;
	}

	@Override
	public ConsultarOrdenPagoDevolucionView getView() {
		return this.view;
	}
	
	public void confirmarOrdenPagoDev() throws Exception {
		this.actualizarABMModel();
		this.getAbmModel().confirmar();
		this.setOperacionRealizada(true);
		this.close();
	}
	
	public void imprimirOrdenPagoDev() throws Exception {
		OrdenPagoDevolucion locOrdenPagoDevolucion = this.getAbmModel().getObjetoABM();
		JasperPrint locJasperPrint = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemReportesContabilidad().getReporteOrdenPagoDevolucion(locOrdenPagoDevolucion.getIdDocumentoEgreso());
		this.getView().mostrarVistaPreviaReporte(locJasperPrint, "Vista previa de Orden de Pago devolución");
	}
	
	private List calcularTotales() {
		List<Double> locRetorno = new ArrayList<Double>();
		
		OrdenPagoDevolucion locOrdenPago = this.getAbmModel().getObjetoABM();
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
	private ConsultarOrdenPagoDevolucion controller;
	
	public BtnAceptarListeners(ConsultarOrdenPagoDevolucion controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.confirmarOrdenPagoDev();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnImprimirListener implements ActionListener {
	
	private ConsultarOrdenPagoDevolucion controller;
	
	public BtnImprimirListener(ConsultarOrdenPagoDevolucion controller) {
		this.controller = controller;
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.imprimirOrdenPagoDev();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(),
					ex.getMessage());
		}
	}

}



