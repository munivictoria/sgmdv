package com.trascender.contabilidad.gui.abmFactura.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.compras.recurso.persistent.suministros.FacturaServicio;
import com.trascender.compras.recurso.persistent.suministros.LineaFactura;
import com.trascender.compras.recurso.persistent.suministros.Factura.Estado;
import com.trascender.compras.recurso.persistent.suministros.Factura.TipoFactura;
import com.trascender.contabilidad.gui.abmCuenta.AdminCuenta;
import com.trascender.contabilidad.gui.abmFactura.FacturaServicioABMModel;
import com.trascender.contabilidad.gui.abmFactura.LineaFacturaTableModel;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.framework.recurso.persistent.referencia.CuentaRfr;
import com.trascender.gui.framework.abmStandard.ABMController;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.model.TDefaultComboBoxModel;
import com.trascender.gui.framework.recursos.Messages;
import com.trascender.gui.framework.util.Conversor;

public class ModificarFacturaServicio  extends ABMController<FacturaServicio> {

	private ModificarFacturaServicioView view;
	private LineaFacturaTableModel tableModel = new LineaFacturaTableModel();
	private FacturaServicioABMModel abmModel = new FacturaServicioABMModel();
	
	public ModificarFacturaServicio(JDialog owner) throws Exception {
		this.view = new ModificarFacturaServicioView(owner);
		this.init();
	}
	
	public ModificarFacturaServicio(JFrame owner) throws Exception  {
		this.view = new ModificarFacturaServicioView(owner);
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		this.setModels();
		this.setListeners();
		this.setTextoBtnModificarAsociacion();
		this.getView().getPnlTablaLineaFP().getPnlVerticalBotones().getBtnEliminar().setVisible(false);
		this.getView().getPnlTablaLineaFP().getPnlVerticalBotones().getBtnQuitarTodos().setVisible(false);
	}
	
	private void setModels() {
		this.getView().setTableModel(this.getTableModel());
		this.getView().getCmbEstado().setModel(new TDefaultComboBoxModel(Estado.values()));
		this.getView().getCmbTipoFactura().setModel(new TDefaultComboBoxModel(TipoFactura.values()));
	}
	
	private void setListeners() {
		this.getView().getPnlTablaLineaFP().getPnlVerticalBotones().getBtnAgregar().addActionListener(new AsociarCuentaFSListener(this));
		this.getView().getPnlTablaLineaFP().getPnlVerticalBotones().getBtnModificar().addActionListener(new QuitarAsocCuentaServicioListener(this));
		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnGuardarFacturaServicioListeners(this));
	}

	@Override
	public void actualizarABMModel() {
		this.getAbmModel().fireActualizarDatos();
	}

	@Override
	public void actualizarView() {
		this.getView().getTfBien().setText(Conversor.getVacioSiNull(this.getAbmModel().getBien()));
		this.getView().getTfProveedor().setText(Conversor.getVacioSiNull(this.getAbmModel().getProveedor()));
		this.getView().getTfFecha().setValue(Conversor.getString(this.getAbmModel().getFechaEmision()));
		this.getView().getTfNumeroFactura().setText(Conversor.getString(this.getAbmModel().getNumero()));
		this.getView().getTfTotal().setValue(this.getAbmModel().getTotal());
		this.getView().getCmbEstado().setSelectedItem(this.getAbmModel().getEstado());
		this.getView().getCmbTipoFactura().setSelectedItem(this.getAbmModel().getTipoFactura());
		
		//Actualización de la tabla principal
		if (this.getAbmModel().getListaLineaFactura() != null && this.getTableModel().getListaObjetos() != null) {
			
			List<LineaFactura> locList = new ArrayList<LineaFactura>(this.getAbmModel().getListaLineaFactura());
			this.getTableModel().clearTable();
			this.getTableModel().addRows(locList);
			
			try {
				//this.setTableCellEditor();
			}
			catch (Exception ex) {
				ex.printStackTrace();
				AppManager.getInstance().showErrorMsg(this.getView(), ex.getMessage());
			}
		}
		
	}
	
	private void setTextoBtnModificarAsociacion() {
		this.getView().getPnlTablaLineaFP().getPnlVerticalBotones().getBtnModificar().setText(Messages.getString("Application.btnQuitar"));
		this.getView().getPnlTablaLineaFP().getPnlVerticalBotones().getBtnModificar().setMnemonic(Messages.getString("Application.btnQuitarMnemonic").charAt(0));
	}

	@Override
	public FacturaServicioABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	protected ModificarFacturaServicioView getView() {
		return this.view;
	}
	
	public LineaFacturaTableModel getTableModel() {
		return tableModel;
	}

	/**
	 * Selecciona una cuenta contable para asientos
	 * @throws Exception
	 */
	void seleccionarCuenta() throws Exception {
		int locLineaFacturaSeleccionada = this.getView().getPnlTablaLineaFP().getPnlTabla().getTblDatos().getSelectedRow();
		
		if(locLineaFacturaSeleccionada > -1){ 
				
				AdminCuenta adminCuenta = new AdminCuenta(this.getView(),true);
				Cuenta locCuenta = adminCuenta.openSelect();
				
				if (locCuenta != null) {
					LineaFactura locLineaFactura = this.getTableModel().getRow(locLineaFacturaSeleccionada);
					locLineaFactura = ContabilidadGUI.getInstance().getAdminSystemsCompras().getSystemAdministracionFactura().getLineaFacturaPorId(locLineaFactura.getIdLineaFactura());
					this.getAbmModel().getObjetoABM().getListaLineaFactura().remove(locLineaFactura);
//					CuentaRfr cuenta = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().getCuentaRfr(locCuenta);
//					
//					locLineaFactura.setCuenta(cuenta);
					
					CuentaRfr cuentaRfr = new CuentaRfr();
					cuentaRfr.setAbreviatura(locCuenta.getAbreviatura());
					cuentaRfr.setCodigoImputacion(locCuenta.getCodigoImputacion());
					cuentaRfr.setIdCuenta(locCuenta.getIdCuenta());
					cuentaRfr.setNombre(locCuenta.getNombre());
					locLineaFactura.setCuenta(cuentaRfr);
					this.getAbmModel().getObjetoABM().getListaLineaFactura().add(locLineaFactura);
					this.actualizarABMModel();
					this.actualizarView();
				}
		}
	}
	
	void quitarAsociacion() throws Exception {
		int locLineaFacturaSeleccionada = this.getView().getPnlTablaLineaFP().getPnlTabla().getTblDatos().getSelectedRow();
		
		if(locLineaFacturaSeleccionada > -1){ 
			LineaFactura locLineaFactura = this.getTableModel().getRow(locLineaFacturaSeleccionada);
			locLineaFactura = ContabilidadGUI.getInstance().getAdminSystemsCompras().getSystemAdministracionFactura().getLineaFacturaPorId(locLineaFactura.getIdLineaFactura());
			this.getAbmModel().getObjetoABM().getListaLineaFactura().remove(locLineaFactura);
			locLineaFactura.setCuenta(null);
			this.getAbmModel().getObjetoABM().getListaLineaFactura().add(locLineaFactura);
			this.actualizarABMModel();
			this.actualizarView();
		}
	}
	
	void guardarFacturaServicio() throws Exception {
		this.actualizarABMModel();
		this.getAbmModel().modificar();
		this.setOperacionRealizada(true);
		this.close();
	}

}

class AsociarCuentaFSListener implements ActionListener {
	private ModificarFacturaServicio controller;
	
	public AsociarCuentaFSListener(ModificarFacturaServicio controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.seleccionarCuenta();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class QuitarAsocCuentaServicioListener implements ActionListener {
	private ModificarFacturaServicio controller;
	
	public QuitarAsocCuentaServicioListener(ModificarFacturaServicio controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.quitarAsociacion();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}


class BtnGuardarFacturaServicioListeners implements ActionListener {
	private ModificarFacturaServicio controller;
	
	public BtnGuardarFacturaServicioListeners(ModificarFacturaServicio controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.guardarFacturaServicio();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

