package com.trascender.contabilidad.gui.abmBoletaDeposito;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.trascender.contabilidad.gui.abmBoletaDeposito.abm.AgregarBoletaDeposito;
import com.trascender.contabilidad.gui.abmBoletaDeposito.abm.EliminarBoletaDeposito;
import com.trascender.contabilidad.gui.abmBoletaDeposito.abm.ModificarBoletaDeposito;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.BoletaDeposito;
import com.trascender.gui.framework.abmStandard.AdminController;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Conversor;
import com.trascender.gui.framework.util.Validador;

public class AdminBoletaDeposito extends AdminController<BoletaDeposito> {

	private AdminBoletaDepositoView view;
	private BoletaDepositoBusquedaModel busquedModel = new BoletaDepositoBusquedaModel();
	private BoletaDepositoTableModel tableModel = new BoletaDepositoTableModel();
	
	public AdminBoletaDeposito(JFrame owner) throws Exception {
		this.view = new AdminBoletaDepositoView(owner);
		this.init();
	}
	
	public AdminBoletaDeposito(JDialog owner) throws Exception {
		this.view = new AdminBoletaDepositoView(owner);
		this.init();
	}

	@Override
	protected void init() {
		super.init();
		this.setModels();
		this.setListeners();
	}
	
	private void setModels() {
		this.getView().setBusquedaModel(this.getBusquedaModel());
		this.getView().setTableModel(this.getTableModel());
	}
	
	private void setListeners() {
		this.getView().getPnlBotonesBusqueda().getBtnBuscar().addActionListener(new BtnBuscarListener(this));
		
		this.getView().getPnlPie().getBtnAgregar().addActionListener(new BtnAgregarListener(this));
		this.getView().getPnlPie().getBtnModificar().addActionListener(new BtnModificarListener(this));
		this.getView().getPnlPie().getBtnEliminar().addActionListener(new BtnEliminarListener(this));
	}
	
	@Override
	protected void actualizarBusquedaModel() {
		BoletaDepositoBusquedaModel locModel = this.getBusquedaModel();
		
		locModel.setNumeroBoleta(Conversor.getNullSiVacio(this.getView().getTfNumeroBoleta().getText()));
		locModel.setImporteDesde(Conversor.getDouble(this.getView().getTfImporteDesde().getValue()));
		locModel.setImporteHasta(Conversor.getDouble(this.getView().getTfImporteHasta().getValue()));
		locModel.setFechaDesde(Conversor.getDate(this.getView().getTfFechaDesde().getText()));
		locModel.setFechaHasta(Conversor.getDate(this.getView().getTfFechaHasta().getText()));
		
		locModel.fireActualizarDatos();
	}

	@Override
	protected void actualizarBusquedaView() {
		this.getView().getTfNumeroBoleta().setText(Conversor.getVacioSiNull(this.getBusquedaModel().getNumeroBoleta()));
		this.getView().getTfImporteDesde().setValue(this.getBusquedaModel().getImporteDesde());
		this.getView().getTfImporteHasta().setValue(this.getBusquedaModel().getImporteHasta());
		this.getView().getTfFechaDesde().setValue(Conversor.getString(this.getBusquedaModel().getFechaDesde()));
		this.getView().getTfFechaHasta().setValue(Conversor.getString(this.getBusquedaModel().getFechaHasta()));
	}

	@Override
	public boolean validarDatos() {
		boolean validacionOK = true;
		
		List<String> attFecha = new ArrayList<String>();
		List<JLabel> lblFecha = new ArrayList<JLabel>();
		
		attFecha.add(this.getView().getTfFechaDesde().getText());
		lblFecha.add(this.getView().getLblFecha());
		
		attFecha.add(this.getView().getTfFechaHasta().getText());
		lblFecha.add(this.getView().getLblFecha());
		
		List<String> listaErrores = new ArrayList<String>();
		try {
			listaErrores.addAll(Validador.validarFechas(attFecha, lblFecha));
		}
		catch (GuiException ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.getView(), ex.getMessage());
			return false;
		}
		
		if (!listaErrores.isEmpty()) {
			validacionOK = false;
			this.getAbmErrores().getView().setListaErrores(listaErrores);
			this.getAbmErrores().open();
		}
		
		return validacionOK;
	}
	
	@Override
	protected BoletaDepositoBusquedaModel getBusquedaModel() {
		return this.busquedModel;
	}

	@Override
	protected BoletaDepositoTableModel getTableModel() {
		return this.tableModel;
	}

	@Override
	protected AdminBoletaDepositoView getView() {
		return this.view;
	}
	
	void buscar() throws Exception {
//		if (validarDatos()) {
			final AdminBoletaDeposito controller = this;
			Thread locThread = new Thread(new Runnable(){
				public void run() {
					try {
						controller.actualizarBusquedaModel();
						controller.getView().iniBusqueda();
						List<BoletaDeposito> locLista = controller.getBusquedaModel().buscar();
						controller.getTableModel().setListaObjetos(locLista);
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
//		}
	}
	
	protected void openAgregarBoletaDeposito() throws Exception {
		AgregarBoletaDeposito agregarBoletaDeposito = new AgregarBoletaDeposito(this.getView());
		agregarBoletaDeposito.open();
		if (agregarBoletaDeposito.isOperacionRealizada()) {
			this.getTableModel().addRow(agregarBoletaDeposito.getAbmModel().getObjetoABM());
			
		}
	}
	
	protected void openModificarBoletaDeposito() throws Exception {
		BoletaDeposito locBoletaDeposito= this.getSelectedRow();
		
		if (locBoletaDeposito!= null) {
			locBoletaDeposito = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionEgresos().getBoletaDepositoByID((locBoletaDeposito.getIdBoletaDeposito()));
			ModificarBoletaDeposito modificarBoletaDeposito = new ModificarBoletaDeposito(this.getView());
			modificarBoletaDeposito.getAbmModel().setObjetoABM(locBoletaDeposito);
			if (!modificarBoletaDeposito.getAbmModel().getObjetoABM().getListaMovimientoCajaEgreso().isEmpty()) {
				modificarBoletaDeposito.getAbmModel().setCuentaAfectada(modificarBoletaDeposito.getAbmModel().getObjetoABM().getListaMovimientoCajaEgreso().iterator().next().getCuenta());
			}else {
				modificarBoletaDeposito.getAbmModel().setCuentaAfectada(null);
			}
			modificarBoletaDeposito.actualizarView(); 
			modificarBoletaDeposito.open();
			this.buscar();
		}
	}
	
	protected void openEliminarBoletaDeposito() throws Exception {
		BoletaDeposito locBoletaDeposito = this.getSelectedRow();
		if (locBoletaDeposito != null) {
			locBoletaDeposito = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionEgresos().getBoletaDepositoByID(locBoletaDeposito.getIdBoletaDeposito());
			EliminarBoletaDeposito eliminarBoletaDeposito= new EliminarBoletaDeposito(this.getView());
			eliminarBoletaDeposito.getAbmModel().setObjetoABM(locBoletaDeposito);
			if (!eliminarBoletaDeposito.getAbmModel().getObjetoABM().getListaMovimientoCajaEgreso().isEmpty()) {
				eliminarBoletaDeposito.getAbmModel().setCuentaAfectada(eliminarBoletaDeposito.getAbmModel().getObjetoABM().getListaMovimientoCajaEgreso().iterator().next().getCuenta());
			}else {
				eliminarBoletaDeposito.getAbmModel().setCuentaAfectada(null);
			}
			eliminarBoletaDeposito.actualizarView(); 
			eliminarBoletaDeposito.open();
			this.buscar();
		}
	}
}

class BtnBuscarListener implements ActionListener {
	private AdminBoletaDeposito controller;
	public BtnBuscarListener(AdminBoletaDeposito controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.buscar();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnAgregarListener implements ActionListener {

	private AdminBoletaDeposito controller;
	
	public BtnAgregarListener(AdminBoletaDeposito controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openAgregarBoletaDeposito();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}

class BtnModificarListener implements ActionListener {

	private AdminBoletaDeposito controller;
	
	public BtnModificarListener(AdminBoletaDeposito controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openModificarBoletaDeposito();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnEliminarListener implements ActionListener {

	private AdminBoletaDeposito controller;
	
	public BtnEliminarListener(AdminBoletaDeposito controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openEliminarBoletaDeposito();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}