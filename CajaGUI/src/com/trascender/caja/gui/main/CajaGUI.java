package com.trascender.caja.gui.main;

import java.awt.Font;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.trascender.caja.gui.principal.MainCaja;
import com.trascender.contabilidad.recurso.persistent.Caja;
import com.trascender.contabilidad.recurso.persistent.Caja.Estado;
import com.trascender.contabilidad.recurso.persistent.CajaChica;
import com.trascender.contabilidad.recurso.persistent.ConceptoMovimientoCajaChica;
import com.trascender.contabilidad.recurso.persistent.Moneda;
import com.trascender.contabilidad.recurso.persistent.MovimientoCajaChica;
import com.trascender.contabilidad.recurso.persistent.TicketCaja;
import com.trascender.framework.recurso.persistent.Permiso;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Constantes;

import de.muntjak.tinylookandfeel.TinyLookAndFeel;


/**
 * @author ferna
 *
 */
public class CajaGUI extends AppManager {

	private AdministracionSystemsCaja adminSystemsCaja; 

	private static final CajaGUI instance = new CajaGUI();

	private MainCaja mainController = null;
	private Caja caja;

	public static CajaGUI getInstance() {
		return instance;
	}

	private CajaGUI() {
		super();
		
		try {
			this.mainController = new MainCaja();
			this.adminSystemsCaja = new AdministracionSystemsCaja();
			
			TinyLookAndFeel locTiny = new TinyLookAndFeel();
			UIManager.setLookAndFeel(locTiny);
			// actualiza la vista
			
			SwingUtilities.updateComponentTreeUI(this.getMainController().getView());
		} 
		catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	private static void setearFuentesGenerales() {
		Font fuente = new Font("Verdana",Font.BOLD,14);
		UIManager.put("Table.font", fuente);
		UIManager.put("Button.font", fuente);
		UIManager.put("TextField.font", fuente);
		UIManager.put("TextArea.font", fuente);
		UIManager.put("Label.font", fuente);
		UIManager.put("ComboBox.font", fuente);
		UIManager.put("MenuItem.font", fuente);
		UIManager.put("TableHeader.font", fuente);
		UIManager.put("Menu.font", fuente);
	}

	public static void main (String[] args) {
		try {			
			setearFuentesGenerales();
			MainCaja main = CajaGUI.getInstance().getMainController();

//			boolean habilitar = false;
//			for (int i = 0; i < main.getView().getMenuAdministracionCaja().getItemCount(); i++) {
//				Component c = main.getView().getMenuAdministracionCaja().getItem(i); 
//				if (c != null) c.setEnabled(habilitar);
//			}
//			for (int i = 0; i < main.getView().getMenuAdministracionCobro().getItemCount(); i++) {
//				Component c = main.getView().getMenuAdministracionCobro().getItem(i); 
//				if (c != null) c.setEnabled(habilitar);
//			}
			
			CajaGUI.getInstance().habilitarMenuesSegunPermisos();
			
			main.open();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void habilitarMenuesSegunPermisos() throws Exception {
		Permiso permiso = null;
		boolean habilitar = false;

		// Caja
		permiso = AppManager.getInstance().getPermiso(Caja.serialVersionUID);
		habilitar = (permiso != null && permiso.isSelect());
		this.getMainController().getView().getMenuAdministracionCaja().getMiAdminCaja().setEnabled(habilitar);

		// Caja Chica
		permiso = AppManager.getInstance().getPermiso(CajaChica.serialVersionUID);
		habilitar = (permiso != null && permiso.isSelect());
		this.getMainController().getView().getMenuAdministracionCaja().getMiAdminCajaChica().setEnabled(habilitar);

		// Concepto Movimiento Caja Chica 
		permiso = AppManager.getInstance().getPermiso(ConceptoMovimientoCajaChica.serialVersionUID);
		habilitar = (permiso != null && permiso.isSelect());
		this.getMainController().getView().getMenuAdministracionCaja().getMiAdminConceptoMovimientoCajaChica().setEnabled(habilitar);

		// Movimiento Caja Chica
		permiso = AppManager.getInstance().getPermiso(MovimientoCajaChica.serialVersionUID);
		habilitar = (permiso != null && permiso.isSelect());
		this.getMainController().getView().getMenuAdministracionCaja().getMiAdminMovimientoCajaChica().setEnabled(habilitar);

		// Moneda
		permiso = AppManager.getInstance().getPermiso(Moneda.serialVersionUID);
		habilitar = (permiso != null && permiso.isSelect());
		this.getMainController().getView().getMenuAdministracionCaja().getMiAdminMoneda().setEnabled(habilitar);

		// Menu Cobro
		permiso = AppManager.getInstance().getPermiso(TicketCaja.serialVersionUID);
		// Solo se habilitan si el usuario tiene permisos y la PC tiene permitido el cobro (verificando ip).
		habilitar = isIpValido() && (permiso != null && permiso.isSelect());
		this.getMainController().getView().getMenuAdministracionCobro().getMiAdminCobro().setEnabled(habilitar);
		this.getMainController().getView().getMenuAdministracionCobro().getMiAdminResumenCaja().setEnabled(habilitar);
		this.getMainController().getView().getMenuAdministracionCobro().getMiAdminResumenActualCaja().setEnabled(habilitar);
		
	

		
		this.mainController.getView().getLblMensajeImportante().setText(" ");
		this.mainController.getView().repaint();
		
		if (this.getUsuario() != null && !habilitar) {
			this.mainController.getView().getLblMensajeImportante().setText("Usuario sin permisos para realizar Cobros.");
			this.mainController.getView().repaint();
		}
		
	}


	@Override
	public MainCaja getMainController() {
		return mainController;
	}

	public AdministracionSystemsCaja getAdminSystemsCaja() {
		return adminSystemsCaja;
	}

	public Caja getCaja() {
		return caja;
	}

	/**
	 * Metodo para verificar si el IP de la PC que se quiere conectar se encuentra habilitado para realizar cobros
	 * @return <code>true</code> si el IP es valido, <code>false</code> si el IP es incorrecto o se ingresa como root
	 * @throws Exception
	 */

	private boolean isIpValido() throws Exception {

		Enumeration<NetworkInterface> locInterfaces = NetworkInterface.getNetworkInterfaces();

		List<String> locListaIps = new ArrayList<String>();

		while(locInterfaces.hasMoreElements()){
			NetworkInterface cadaNetwork = locInterfaces.nextElement();

			Enumeration<InetAddress> direcciones = cadaNetwork.getInetAddresses();

			while(direcciones.hasMoreElements()){
				InetAddress locAddress = direcciones.nextElement();
				locListaIps.add(locAddress.getHostAddress());
			}
		}

		// TODO: ARIEL: Agregada la primera parte de la condicion: AppManager.getInstance().getUsuario() != null 
		if (AppManager.getInstance().getUsuario() != null && !AppManager.getInstance().getUsuario().getUser().equals(Constantes.SUPER_USUARIO))	{
			
			Iterator<String> cadaDireccion = locListaIps.iterator();
			while((cadaDireccion.hasNext())&&(caja==null)){
				String locDireccion = cadaDireccion.next();
				try{
					caja = this.adminSystemsCaja.getSystemAdministracionIngresos().findCajaByIP(locDireccion);
				}
				catch(Exception ex){
					caja = null;
					ex.printStackTrace();
					AppManager.getInstance().showErrorMsg(this.mainController.getView(), ex.getMessage());
				}
			}
			
			if (caja == null || caja.getEstado().equals(Estado.INACTIVO)) {
				//mines
				//AppManager.getInstance().showWarningMsg(this.mainController.getView(), "PC no habilitada para realizar cobros.");
				return false;
			} 
			else {
				return true;
			}
		}
		else {
			caja = null;
			// Marina
			//AppManager.getInstance().showWarningMsg(this.mainController.getView(), "Usted se ha conectado como Administrador. No podrá realizar cobros.");
			return false;
		}
	}

}
