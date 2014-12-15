package com.trascender.caja.gui.abmCaja;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.caja.gui.abmCaja.abm.AgregarCaja;
import com.trascender.caja.gui.abmCaja.abm.EliminarCaja;
import com.trascender.caja.gui.abmCaja.abm.ModificarCaja;
import com.trascender.caja.gui.main.CajaGUI;
import com.trascender.contabilidad.recurso.persistent.Caja;
import com.trascender.contabilidad.recurso.persistent.Caja.Estado;
import com.trascender.gui.framework.abmStandard.AdminController;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.model.TDefaultComboBoxModel;
import com.trascender.gui.framework.recursos.Messages;
import com.trascender.gui.framework.util.Constantes;
/**
 * @author adrian
 */
public class AdminCaja extends AdminController<Caja> {

	private AdminCajaView view;
	private CajaTableModel tableModel = new CajaTableModel();
	private CajaBusquedaModel busquedaModel = new CajaBusquedaModel();

	public AdminCaja(JFrame owner) throws Exception {
		this.view = new AdminCajaView(owner);
		this.init();
	}

	public AdminCaja(JDialog owner) throws Exception {
		this.view = new AdminCajaView(owner);
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

		this.getView().getCbEstado().setModel(new TDefaultComboBoxModel(Estado.values(), false));
	}

	private void setListeners() {
		this.getView().getPnlBotonesBusqueda().getBtnBuscar().addActionListener(new BtnBuscarListener(this));
		this.getView().getPnlPie().getBtnAgregar().addActionListener(new BtnAgregarListener(this));
		this.getView().getPnlPie().getBtnModificar().addActionListener(new BtnModificarListener(this));
		this.getView().getPnlPie().getBtnEliminar().addActionListener(new BtnEliminarListener(this));
		this.getView().getCbEstado().addActionListener(new CboEstadoListener(this));
		this.getBusquedaModel().addActionListener(new ActualizarVistaBusqueda(this));
	}

	@Override
	protected void actualizarBusquedaModel() {
		CajaBusquedaModel locModel = this.getView().getBusquedaModel();

		locModel.setNombre(this.getView().getTfNombre().getText());

		Object locEstado = this.getView().getCbEstado().getSelectedItem();
		if (locEstado != null) locModel.setEstado((Estado)locEstado); 
		else locModel.setEstado(null);

		locModel.fireActualizarDatos();
	}

	@Override
	protected void actualizarBusquedaView() {
		this.getView().getTfNombre().setText(this.getBusquedaModel().getNombre());
		this.getView().getCbEstado().setSelectedItem(this.getBusquedaModel().getEstado());
	}

	@Override
	protected CajaBusquedaModel getBusquedaModel() {
		return busquedaModel;
	}

	@Override
	protected CajaTableModel getTableModel() {
		return tableModel;
	}

	@Override
	protected AdminCajaView getView() {
		return view;
	}

	
	/**
	 * Restaura una caja
	 * @throws Exception
	 */
	protected void restaurarCaja() throws Exception {
		Caja locCaja = this.getSelectedRow();
		if (locCaja != null) {
			Boolean locMensaje = AppManager.getInstance().showConfirmMsg(this.getView(), "¿Desea Restaurar la Caja Seleccionada?");
			if (locMensaje) {
				locCaja = CajaGUI.getInstance().getAdminSystemsCaja().getSystemAdministracionIngresos().findCajaByID(locCaja.getIdCaja());
				locCaja.setEstado(Estado.ACTIVO);
				CajaGUI.getInstance().getAdminSystemsCaja().getSystemAdministracionIngresos().updateCaja(locCaja);
				this.buscar();
			}
		}
	}

	/**
	 * Realiza la búsqueda y actualiza los datos del modelo de la tabla
	 */
	void buscar() {
		final AdminCaja controller = this;
		
		Thread locThread = new Thread(new Runnable(){
			public void run() {
				try {
					controller.actualizarBusquedaModel();
					controller.getView().iniBusqueda();
					List<Caja> locLista = controller.getBusquedaModel().buscar();
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
	}

	
	
	protected void openAgregarCaja() throws Exception {
		AgregarCaja agregarCaja = new AgregarCaja(this.getView());
		agregarCaja.open();
		if (agregarCaja.isOperacionRealizada()) {
			this.buscar();
		}
	}

	protected void openModificarCaja() throws Exception {
		Caja locCaja = this.getSelectedRow();
		if (locCaja != null) {
			locCaja = CajaGUI.getInstance().getAdminSystemsCaja().getSystemAdministracionIngresos().findCajaByID(locCaja.getIdCaja());
			ModificarCaja locModificarCaja = new ModificarCaja(this.getView());
			locModificarCaja.getAbmModel().setObjetoABM(locCaja);
			locModificarCaja.actualizarView();
			locModificarCaja.open();
			if (locModificarCaja.isOperacionRealizada()) {
				this.getTableModel().updateRow(locCaja);
			}
		}
	}

	protected void openEliminarCaja() throws Exception {
		Caja locCaja = this.getSelectedRow();
		if (locCaja != null) {
			locCaja = CajaGUI.getInstance().getAdminSystemsCaja().getSystemAdministracionIngresos().findCajaByID(locCaja.getIdCaja());
			
			if (CajaGUI.getInstance().getUsuario().getUser().equals(Constantes.SUPER_USUARIO) || 
					!locCaja.getIpAddress().equals(CajaGUI.getInstance().getCaja().getIpAddress())) {
				EliminarCaja locEliminarCaja = new EliminarCaja(this.getView());
				locEliminarCaja.getAbmModel().setObjetoABM(locCaja);
				locEliminarCaja.actualizarView();
				locEliminarCaja.open();
				if (locEliminarCaja.isOperacionRealizada()) {
					this.getTableModel().deleteRow(locCaja);
				}
			} else {
				AppManager.getInstance().showErrorMsg(this.getView(), "La caja está siendo utilizada.");
			}
		}
	}

	/**
	 * Actualiza el estado de los botones según el estado
	 */
	public void actualizarEstadoBotones(){
		boolean activos;
		Estado locEstado = this.busquedaModel.getEstado();
		if (locEstado == null){
			locEstado = Estado.ACTIVO;
		}
		switch(locEstado){
			case ACTIVO: 
				this.getView().getPnlPie().getBtnEliminar().setText(Messages.getString("Application.btnEliminar"));
				activos = true;
				break;
			case INACTIVO: 
				this.getView().getPnlPie().getBtnEliminar().setText(Messages.getString("Application.btnRestaurar"));
				activos = false;
				break;
			default: 
				activos = false;
		}
		this.getView().getPnlPie().getBtnModificar().setEnabled(activos);
		this.getView().getPnlPie().getBtnAgregar().setEnabled(activos);
	}
}


/**
 * 
 * @author Mariano Lusardi
 * @author Alguien más
 * FIXME PONER EL NOMBRE DE LA PERSONA QUE LO HIZO, YO LO MODIFIQUÉ NOMÁS.
 */
class BtnBuscarListener implements ActionListener {
	private AdminCaja controller;
	
	public BtnBuscarListener(AdminCaja controller) {
		this.controller = controller;
	}
	
	public void actionPerformed(ActionEvent e) {
		this.controller.buscar();
/*		if(this.controller.getView().getCbEstado().getSelectedItem() != null &&
				this.controller.getView().getCbEstado().getSelectedItem().equals(Estado.INACTIVO)) {
			this.controller.setEstadoRestauracion();
			
		}*/
	}
}

class BtnRestaurarListener implements ActionListener {
	private AdminCaja controller;
	public BtnRestaurarListener(AdminCaja controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.restaurarCaja();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}

	}
}

class BtnAgregarListener implements ActionListener {
	private AdminCaja controller;
	public BtnAgregarListener(AdminCaja controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openAgregarCaja();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnModificarListener implements ActionListener {
	private AdminCaja controller;
	public BtnModificarListener(AdminCaja controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.openModificarCaja();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}


/**
 * Cambia los botones según lo que se seleccione en el estado
 * @author Mariano Lusardi
 *
 */
class CboEstadoListener implements ActionListener{

	private AdminCaja controller;
	
	public CboEstadoListener(AdminCaja pController){
		this.controller = pController;
	}
	
	public void actionPerformed(ActionEvent e) {
		JComboBox locCombo = (JComboBox)e.getSource();
		Estado locEstadoCaja = (Estado)locCombo.getSelectedItem();
		controller.getBusquedaModel().setEstado(locEstadoCaja);
		
	}
	
}

class BtnEliminarListener implements ActionListener {
	private AdminCaja controller;
	public BtnEliminarListener(AdminCaja controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			JButton fuente = (JButton)e.getSource();
			if (fuente.getText().equalsIgnoreCase("eliminar")) {
				this.controller.openEliminarCaja();
			}
			else if (fuente.getText().equalsIgnoreCase("restaurar")) {
				this.controller.restaurarCaja();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}


/**
 * Actualiza el modelo de búsque a partir de la interfaz
 * @author Mariano Lusardi
 */
class ActualizarModeloBusqueda implements ActionListener{
	private AdminCaja controller;
	public ActualizarModeloBusqueda(AdminCaja pController){
		this.controller = pController;
	}
	
	public void actionPerformed(ActionEvent e) {
		this.controller.getBusquedaModel().setEstado((Estado)this.controller.getView().getCbEstado().getSelectedItem());
		this.controller.getBusquedaModel().setNombre(this.controller.getView().getTfNombre().getText());
		this.controller.actualizarEstadoBotones();
	}
}


/**
 * Actualiza los valores de la vista a partir del modelo
 * @author Mariano Lusardi
 *
 */
class ActualizarVistaBusqueda implements ActionListener{
	private AdminCaja controller;
	public ActualizarVistaBusqueda(AdminCaja pController) {
		this.controller = pController;
	}

	public void actionPerformed(ActionEvent e) {
		this.controller.getView().getCbEstado().setSelectedItem(this.controller.getBusquedaModel().getEstado());
		this.controller.getView().getTfNombre().setText(this.controller.getBusquedaModel().getNombre());
		this.controller.actualizarEstadoBotones();
	}
}
