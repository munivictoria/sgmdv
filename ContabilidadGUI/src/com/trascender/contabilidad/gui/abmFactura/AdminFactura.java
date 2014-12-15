package com.trascender.contabilidad.gui.abmFactura;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.compras.recurso.persistent.suministros.Factura;
import com.trascender.compras.recurso.persistent.suministros.FacturaContrato;
import com.trascender.compras.recurso.persistent.suministros.FacturaProveedor;
import com.trascender.compras.recurso.persistent.suministros.FacturaServicio;
import com.trascender.compras.recurso.persistent.suministros.FacturaSubsidio;
import com.trascender.compras.recurso.persistent.suministros.Proveedor;
import com.trascender.compras.recurso.persistent.suministros.Factura.Estado;
import com.trascender.contabilidad.gui.abmFactura.abm.ModificarFacturaContrato;
import com.trascender.contabilidad.gui.abmFactura.abm.ModificarFacturaProveedor;
import com.trascender.contabilidad.gui.abmFactura.abm.ModificarFacturaServicio;
import com.trascender.contabilidad.gui.abmFactura.abm.ModificarFacturaSubsidio;
import com.trascender.contabilidad.gui.abmProveedor.AdminProveedor;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.gui.framework.abmStandard.AdminController;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.model.TDefaultComboBoxModel;
import com.trascender.gui.framework.util.Conversor;

public class AdminFactura extends AdminController<Factura> {

	private FacturaTableModel tableModel = new FacturaTableModel();
	private FacturaBusquedaModel busquedaModel = new FacturaBusquedaModel();
	private AdminFacturaView view;
	
	public AdminFactura(JDialog owner) throws Exception {
		this.view = new AdminFacturaView(owner);
		this.init();
	}
	
	public AdminFactura(JFrame owner) throws Exception {
		this.view = new AdminFacturaView(owner);
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		
		this.getView().getPnlPie().getBtnAgregar().setEnabled(false);
		this.getView().getPnlPie().getBtnModificar().setEnabled(false);
		this.getView().getPnlPie().getBtnEliminar().setEnabled(false);
		
		this.setModels();
		this.setListeners();
	}
	
	private void setModels() {
		this.getView().getCbEstado().setModel(new TDefaultComboBoxModel(Estado.values()));
		this.getView().setBusquedaModel(this.getBusquedaModel());
		this.getView().setTableModel(this.getTableModel());
	}
	
	private void setListeners() {
		this.getView().getPnlBotonesSeleccionProveedor().getBtnSeleccionar().addActionListener(new BtnSeleccionarProveedorListener(this));
		this.getView().getPnlBotonesSeleccionProveedor().getBtnLimpiar().addActionListener(new BtnLimpiarProveedorListener(this));
		
		this.getView().getPnlBotonesBusqueda().getBtnBuscar().addActionListener(new BtnBuscarListener(this));
		this.getView().getPnlBotonesBusqueda().getBtnReiniciar().addActionListener(new BtnReiniciarListener(this));
		
		this.getView().getPnlPie().getBtnModificar().addActionListener(new BtnModificarListener(this));
	}
	
	@Override
	protected void actualizarBusquedaModel() {
		FacturaBusquedaModel locModel = this.getBusquedaModel();
		
		locModel.setFechaDesde(Conversor.getDate(this.getView().getTfFechaDesde().getText()));
		locModel.setFechaHasta(Conversor.getDate(this.getView().getTfFechaHasta().getText()));
		
		Object locEstado = this.getView().getCbEstado().getSelectedItem();
		if (locEstado != null) locModel.setEstado((Estado)locEstado); 
		else locModel.setEstado(null);
		
		locModel.fireActualizarDatos();
	}

	@Override
	public void actualizarBusquedaView() {
		this.getView().getTfProveedor().setText(Conversor.getVacioSiNull(this.getBusquedaModel().getProveedor()));
		this.getView().getTfFechaDesde().setValue(Conversor.getString(this.getBusquedaModel().getFechaDesde()));
		this.getView().getTfFechaHasta().setValue(Conversor.getString(this.getBusquedaModel().getFechaHasta()));
		this.getView().getCbEstado().setSelectedItem(this.getBusquedaModel().getEstado());
	}

	@Override
	public FacturaBusquedaModel getBusquedaModel() {
		return this.busquedaModel;
	}

	@Override
	protected FacturaTableModel getTableModel() {
		return this.tableModel;
	}

	@Override
	protected AdminFacturaView getView() {
		return this.view;
	}
	
	void seleccionarProveedor() throws Exception {
		AdminProveedor adminProveedor = new AdminProveedor(this.getView());
		Proveedor locProveedor = adminProveedor.openSelect();
		
		if (locProveedor != null) {
			this.getBusquedaModel().setProveedor(locProveedor);
			this.actualizarBusquedaModel();
			this.actualizarBusquedaView();
		}
	}
	
	void limpiarProveedor() {
		this.getBusquedaModel().setProveedor(null);
		this.actualizarBusquedaModel();
		this.actualizarBusquedaView();
	}
	
	public void buscar() throws Exception {
		final AdminFactura controller = this;
		Thread locThread = new Thread(new Runnable(){
			public void run() {
				try {
					controller.actualizarBusquedaModel();
					controller.getView().iniBusqueda();
					List<Factura> locLista = controller.getBusquedaModel().buscar();
					controller.getTableModel().setListaObjetos(locLista);
					if (!locLista.isEmpty()) {
						controller.getView().getPnlPie().getBtnModificar().setEnabled(true);
					}
				}
				catch (Exception ex) {
					ex.printStackTrace();
					AppManager.getInstance().showErrorMsg(controller.getView(), ex.getMessage());
				}
				finally {
					controller.getView().finBusqueda();
				}
			}
		});
		locThread.start();
	}
	
	@Override
	protected void setBtnReiniciarListener() {
		super.setBtnReiniciarListener();
		this.getView().getPnlPie().getBtnModificar().setEnabled(false);
	}
	
	void openModificarFactura() throws Exception {
		Factura locFactura = this.getSelectedRow();
		if (locFactura instanceof FacturaProveedor) {
			System.out.println("Factura Proveedor");
			FacturaProveedor locFacturaProveedor = ContabilidadGUI.getInstance().getAdminSystemsCompras().getSystemAdministracionFacturaProveedor().findFacturaProveedorByID(locFactura.getIdFactura());
			System.out.println("despues del getXid ::::::::::::" + locFacturaProveedor.getListaLineaFactura().get(0).getTotal());
			ModificarFacturaProveedor modificarFacturaProveedor = new ModificarFacturaProveedor(this.getView());
			modificarFacturaProveedor.getAbmModel().setObjetoABM(locFacturaProveedor);
			modificarFacturaProveedor.getAbmModel().setListaLineaFactura(locFacturaProveedor.getListaLineaFactura());
			modificarFacturaProveedor.actualizarView();
			
			modificarFacturaProveedor.open();
		}
		if (locFactura instanceof FacturaContrato) {
			System.out.println("Factura Contrato");
			
			FacturaContrato locFacturaContrato = ContabilidadGUI.getInstance().getAdminSystemsCompras().getSystemAdministracionFacturaContrato().getFacturaContratoPorId(locFactura.getIdFactura());
			System.out.println("despues del getXid ::::::::::::" + locFacturaContrato.getListaLineaFactura().get(0).getTotal());
			ModificarFacturaContrato modificarFacturaContrato = new ModificarFacturaContrato(this.getView());
			modificarFacturaContrato.getAbmModel().setObjetoABM(locFacturaContrato);
			modificarFacturaContrato.getAbmModel().setListaLineaFactura(locFacturaContrato.getListaLineaFactura());
			modificarFacturaContrato.actualizarView();
			
			modificarFacturaContrato.open();
			
		}
		if (locFactura instanceof FacturaServicio) {
			System.out.println("Factura Servicio");
			
			FacturaServicio locFacturaServicio = ContabilidadGUI.getInstance().getAdminSystemsCompras().getSystemAdministracionFacturaServicio().getFacturaServicioPorId(locFactura.getIdFactura());
			System.out.println("despues del getXid ::::::::::::" + locFacturaServicio.getListaLineaFactura().get(0).getTotal());
			ModificarFacturaServicio modificarFacturaServicio = new ModificarFacturaServicio(this.getView());
			modificarFacturaServicio.getAbmModel().setObjetoABM(locFacturaServicio);
			modificarFacturaServicio.getAbmModel().setListaLineaFactura(locFacturaServicio.getListaLineaFactura());
			modificarFacturaServicio.actualizarView();
			
			modificarFacturaServicio.open();
		}
		if (locFactura instanceof FacturaSubsidio) {
			System.out.println("Factura Subsidio");
			
			FacturaSubsidio locFacturaSubsidio = ContabilidadGUI.getInstance().getAdminSystemsCompras().getSystemAdministracionFacturaSubsidio().getFacturaSubsidioPorId(locFactura.getIdFactura());
			System.out.println("despues del getXid ::::::::::::" + locFacturaSubsidio.getListaLineaFactura().get(0).getTotal());
			ModificarFacturaSubsidio modificarFacturaSubsidio = new ModificarFacturaSubsidio(this.getView());
			modificarFacturaSubsidio.getAbmModel().setObjetoABM(locFacturaSubsidio);
			modificarFacturaSubsidio.getAbmModel().setListaLineaFactura(locFacturaSubsidio.getListaLineaFactura());
			modificarFacturaSubsidio.actualizarView();
			
			modificarFacturaSubsidio.open();
		}
		
	}
	
}

class BtnSeleccionarProveedorListener implements ActionListener {
	private AdminFactura controller;
	
	public BtnSeleccionarProveedorListener(AdminFactura controller) {
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
	private AdminFactura controller;
	
	public BtnLimpiarProveedorListener(AdminFactura controller) {
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

class BtnBuscarListener implements ActionListener {
	private AdminFactura controller;
	
	public BtnBuscarListener(AdminFactura controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.buscar();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}

class BtnReiniciarListener implements ActionListener {
	private AdminFactura controller;
	
	public BtnReiniciarListener(AdminFactura controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.setBtnReiniciarListener();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}

class BtnModificarListener implements ActionListener {
	private AdminFactura controller;
	
	public BtnModificarListener(AdminFactura controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openModificarFactura();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}
