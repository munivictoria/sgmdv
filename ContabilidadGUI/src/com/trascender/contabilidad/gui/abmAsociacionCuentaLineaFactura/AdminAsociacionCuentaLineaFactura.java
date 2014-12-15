package com.trascender.contabilidad.gui.abmAsociacionCuentaLineaFactura;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.compras.recurso.persistent.suministros.Factura;
import com.trascender.compras.recurso.persistent.suministros.LineaFactura;
import com.trascender.contabilidad.gui.abmAsociacionCuentaLineaFactura.abm.AgregarAsociacionCuentaLineaFactura;
import com.trascender.contabilidad.gui.abmAsociacionCuentaLineaFactura.abm.EliminarAsociacionCuentaLineaFactura;
import com.trascender.contabilidad.gui.abmAsociacionCuentaLineaFactura.abm.ModificarAsociacionCuentaLineaFactura;
import com.trascender.contabilidad.gui.abmFactura.AdminFactura;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.CuentaLineaFactura;
import com.trascender.gui.framework.abmStandard.AdminController;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Conversor;

public class AdminAsociacionCuentaLineaFactura extends AdminController<LineaFactura> {

	private AsociacionCuentaLineaFacturaTableModel tableModel = new AsociacionCuentaLineaFacturaTableModel();
	private AsociacionCuentaLineaFacturaBusquedaModel busquedaModel = new AsociacionCuentaLineaFacturaBusquedaModel();
	private AdminAsociacionCuentaLineaFacturaView view;

	public AdminAsociacionCuentaLineaFactura(JDialog owner) throws Exception {
		this.view = new AdminAsociacionCuentaLineaFacturaView(owner);
		this.init();
	}
	
	public AdminAsociacionCuentaLineaFactura(JFrame owner)throws Exception {
		this.view = new AdminAsociacionCuentaLineaFacturaView(owner);
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		
		this.getView().getPnlBotonesBusqueda().getBtnBuscar().setEnabled(false);
		this.getView().getPnlBotonesBusqueda().getBtnReiniciar().setEnabled(false);
		
		this.setModels();
		this.setListeners();
	}
	
	private void setModels() {
		this.getView().setBusquedaModel(this.getBusquedaModel());
		this.getView().setTableModel(this.getTableModel());
	}
	
	private void setListeners() {
		this.getView().getPnlBotonesSeleccionFactura().getBtnSeleccionar().addActionListener(new BtnSeleccionarFacturaListener(this));
		this.getView().getPnlBotonesSeleccionFactura().getBtnLimpiar().addActionListener(new BtnLimpiarFacturaListener(this));
		
//		this.getView().getPnlBotonesBusqueda().getBtnBuscar().addActionListener(new BtnBuscarListener(this));
		
		this.getView().getPnlPie().getBtnAgregar().addActionListener(new BtnAgregarListener(this));
		this.getView().getPnlPie().getBtnModificar().addActionListener(new BtnModificarListener(this));
		this.getView().getPnlPie().getBtnEliminar().addActionListener(new BtnEliminarListener(this));
	}
	
	@Override
	protected void actualizarBusquedaModel() {
		AsociacionCuentaLineaFacturaBusquedaModel locModel = this.getBusquedaModel();
		
		locModel.fireActualizarDatos();
	}

	@Override
	protected void actualizarBusquedaView() {
		this.getView().getTfFactura().setText(Conversor.getVacioSiNull(this.getBusquedaModel().getFactura()));
	}

	@Override
	protected AsociacionCuentaLineaFacturaBusquedaModel getBusquedaModel() {
		return this.busquedaModel;
	}

	@Override
	protected AsociacionCuentaLineaFacturaTableModel getTableModel() {
		return this.tableModel;
	}

	@Override
	protected AdminAsociacionCuentaLineaFacturaView getView() {
		return this.view;
	}
	

	void seleccionarFactura() throws Exception {
		AdminFactura adminFactura = new AdminFactura(this.getView());
		this.actualizarBusquedaModel();
		Factura locFactura = adminFactura.openSelect();
		
		//FacturaContrato locFactura = ContabilidadGUI.getInstance().getAdminSystemsCompras().getSystemAdministracionFacturaContrato().getFacturaContratoPorId(22);
		if (locFactura != null) {
			this.getBusquedaModel().setFactura(locFactura);
			this.getTableModel().setListaObjetos(locFactura.getListaLineaFactura());
			
			this.actualizarBusquedaView();
		}
	}
	
	public void setBusquedaModel(
			AsociacionCuentaLineaFacturaBusquedaModel busquedaModel) {
		this.busquedaModel = busquedaModel;
	}

	void limpiarFactura() {
		this.getBusquedaModel().setFactura(null);
		this.actualizarBusquedaModel();
		this.actualizarBusquedaView();
	}
	
	void openAgregarAsociacionCuentaLineaFactura() throws Exception {
		LineaFactura locLineaFactura = this.getSelectedRow();
		
		if (locLineaFactura != null) {
			LineaFactura lineaFactura =  ContabilidadGUI.getInstance().getAdminSystemsCompras().getSystemAdministracionFactura().getLineaFacturaPorId(locLineaFactura.getIdLineaFactura());
			
			AgregarAsociacionCuentaLineaFactura agregarCuentaLineaFactura = new AgregarAsociacionCuentaLineaFactura(this.getView());

			agregarCuentaLineaFactura.getAbmModel().setLineaFactura(lineaFactura);

			agregarCuentaLineaFactura.actualizarView(); 
			agregarCuentaLineaFactura.open();

			if (agregarCuentaLineaFactura.isOperacionRealizada()) {
				//lineaFactura.setCuentaRfr(agregarCuentaLineaFactura.getAbmModel().getCuentaRfr());
			}
		}
	}
	
	void openModificarAsociacionCuentaLineaFactura() throws Exception {
		LineaFactura  locLineaFactura = this.getSelectedRow();
		
		if (locLineaFactura != null) {
			LineaFactura lineaFactura =  ContabilidadGUI.getInstance().getAdminSystemsCompras().getSystemAdministracionFactura().getLineaFacturaPorId(locLineaFactura.getIdLineaFactura());
			
			CuentaLineaFactura cuentaLineaFactura =  ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().getCuentaLineaFactura(lineaFactura);
			
			ModificarAsociacionCuentaLineaFactura modificarAsociacionCuentaLineaFactura = new ModificarAsociacionCuentaLineaFactura(this.getView());
			modificarAsociacionCuentaLineaFactura.getAbmModel().setObjetoABM(cuentaLineaFactura);
			modificarAsociacionCuentaLineaFactura.getAbmModel().setLineaFactura(cuentaLineaFactura.getLineaFactura());
			System.out.println("********************************" + cuentaLineaFactura.getLineaFactura());
			modificarAsociacionCuentaLineaFactura.actualizarView();
			modificarAsociacionCuentaLineaFactura.open();
			if (modificarAsociacionCuentaLineaFactura.isOperacionRealizada() && this.getTableModel().getRowCount() > 0) {
				this.getBusquedaModel().buscar();
			}
		}
	}
	
	void openEliminarAsociacionCuentaLineaFactura() throws Exception {
		LineaFactura  locLineaFactura = this.getSelectedRow();
		
		if (locLineaFactura != null) {
			LineaFactura lineaFactura =  ContabilidadGUI.getInstance().getAdminSystemsCompras().getSystemAdministracionFactura().getLineaFacturaPorId(locLineaFactura.getIdLineaFactura());
			
			CuentaLineaFactura cuentaLineaFactura =  ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().getCuentaLineaFactura(lineaFactura);
			
			EliminarAsociacionCuentaLineaFactura eliminarAsociacionCuentaLineaFactura = new EliminarAsociacionCuentaLineaFactura(this.getView());
			eliminarAsociacionCuentaLineaFactura.getAbmModel().setObjetoABM(cuentaLineaFactura);
			eliminarAsociacionCuentaLineaFactura.getAbmModel().setLineaFactura(cuentaLineaFactura.getLineaFactura());
			eliminarAsociacionCuentaLineaFactura.actualizarView();
			eliminarAsociacionCuentaLineaFactura.open();
			if (eliminarAsociacionCuentaLineaFactura.isOperacionRealizada() && this.getTableModel().getRowCount() > 0) {
				this.getBusquedaModel().buscar();
			}
		}
	}
	
}

class BtnSeleccionarFacturaListener implements ActionListener {
	private AdminAsociacionCuentaLineaFactura controller;
	
	public BtnSeleccionarFacturaListener(AdminAsociacionCuentaLineaFactura controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.seleccionarFactura();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}

class BtnLimpiarFacturaListener implements ActionListener {
	private AdminAsociacionCuentaLineaFactura controller;
	
	public BtnLimpiarFacturaListener(AdminAsociacionCuentaLineaFactura controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.limpiarFactura();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}

class BtnAgregarListener implements ActionListener {
	private AdminAsociacionCuentaLineaFactura controller;
	
	public BtnAgregarListener(AdminAsociacionCuentaLineaFactura controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openAgregarAsociacionCuentaLineaFactura();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}

class BtnModificarListener implements ActionListener {
	private AdminAsociacionCuentaLineaFactura controller;
	
	public BtnModificarListener(AdminAsociacionCuentaLineaFactura controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openModificarAsociacionCuentaLineaFactura();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}

class BtnEliminarListener implements ActionListener {
	private AdminAsociacionCuentaLineaFactura controller;

	public BtnEliminarListener(AdminAsociacionCuentaLineaFactura controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openEliminarAsociacionCuentaLineaFactura();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}
