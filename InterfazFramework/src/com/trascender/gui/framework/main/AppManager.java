package com.trascender.gui.framework.main;

import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.trascender.framework.recurso.persistent.Municipalidad;
import com.trascender.framework.recurso.persistent.Permiso;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.gui.framework.principal.Main;
import com.trascender.gui.framework.principal.MainView;
import com.trascender.gui.framework.util.Constantes;

public abstract class AppManager {
	
	private static AppManager instance;
	
	private Configuracion configuracion;
	private AdministracionRecursos adminRecursos;
	private Usuario usuario;
	private long llaveUsuario;
	private AdministracionSystems adminSystems;
	private Municipalidad municipalidad;
	
	public static AppManager getInstance() {
	
		return instance;
	}
	
	
	protected AppManager() {
//		try {
//			this.configuracion = Configuracion.load();
//		}
//		catch(Exception e){
//			e.printStackTrace();
//			this.configuracion = Configuracion.loadDefault();
//		}
		
		AppManager.instance = this;
		
		try {
			this.adminRecursos = new AdministracionRecursos();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			this.adminSystems = new AdministracionSystems();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public Usuario getUsuario() {
		return this.usuario;
	}
	
	public void setUsuario(Usuario pUsuario){
		this.usuario = pUsuario;
	}

	public Configuracion getConfiguracion(){
		return this.configuracion;
	}

	public AdministracionRecursos getAdminRecursos(){
		return this.adminRecursos;
	}

	public AdministracionSystems getAdminSystems() {
		return adminSystems;
	}

	public long getLlaveUsuarioConectado() {
		return this.llaveUsuario;
	}
	
	public void setLlaveUsuarioConectado(long llave) {
		this.llaveUsuario = llave;
	}
	
	
//	public static void main(String[] args){
//		try {
//			
//// ariel: para que respete el minimumSize.
////			JFrame.setDefaultLookAndFeelDecorated(true);
////			JDialog.setDefaultLookAndFeelDecorated(true);
////			Toolkit.getDefaultToolkit().setDynamicLayout(false);
//			
////			OyoahaLookAndFeel lnf = new OyoahaLookAndFeel();
////			UIManager.setLookAndFeel(lnf);
//			
//			AppManager instance = AppManager.getInstance();
//			Login locLogin = new Login();
//			long llave = locLogin.open();
//			instance.getAdminSystems().setLlave(llave);
//			instance.setUsuario(instance.getAdminSystems().getSystemUsuario().findUsuarioPorLlave(llave));
//			AppManager.getInstance().configuracion.save();
//			
//			Main main = new Main();
//			main.open();
//		}
//		catch(Exception e){
//			e.printStackTrace();
//		}
//	}
	
	
	// >>>>>>>>>>>>>>>>>>>> VENTANAS DE MENSAJES DEL SISTEMA

	public Municipalidad getMunicipalidad() {
		return municipalidad;
	}


	public void setMunicipalidad(Municipalidad municipalidad) {
		this.municipalidad = municipalidad;
	}


	/**
	 * Mensaje Plano (sin imagen)
	 * @param pComponente
	 * @param pMensaje
	 */
	public void showPlainMsg(Component pComponente, String pMensaje) {
		String locTitulo = "Mensaje";
		JOptionPane.showMessageDialog(pComponente, pMensaje, locTitulo, JOptionPane.PLAIN_MESSAGE);;
	}
	
	/**
	 * Mensaje de Informacion
	 * @param pComponente
	 * @param pMensaje
	 */
	public void showInformationMsg(Component pComponente, String pMensaje) {
		String locTitulo = "Información";
		JOptionPane.showMessageDialog(pComponente, pMensaje, locTitulo, JOptionPane.INFORMATION_MESSAGE);
		return;
	}
	
	/**
	 * Mensaje de Error
	 * @param pComponente
	 * @param pMensaje
	 */
	public void showErrorMsg(Component pComponente, String pMensaje) {
		String locTitulo = "Error";
		JOptionPane.showMessageDialog(pComponente, pMensaje, locTitulo, JOptionPane.ERROR_MESSAGE);
		return;
	}
	
	/**
	 * Mensaje de Confirmacion
	 * @param pComponente
	 * @param pMensaje
	 * @return <code>true</code> si la respuesta es positiva, <code>false</code> si la respuesta es negativa.
	 */
	public boolean showConfirmMsg(Component pComponente, String pMensaje) {
		String locTitulo = "Confirmación";
		int locResultado = JOptionPane.showConfirmDialog(pComponente, pMensaje, locTitulo, JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
		return (locResultado==JOptionPane.OK_OPTION)?true:false;
	}
	
	/**
	 * Mensaje de Advertencia
	 * @param pComponente
	 * @param pMensaje
	 */
	public void showWarningMsg(Component pComponente, String pMensaje) {
		String locTitulo = "Advertencia";
		JOptionPane.showMessageDialog(pComponente, pMensaje, locTitulo, JOptionPane.WARNING_MESSAGE);
		return;
	}

	// <<<<<<<<<<<<<<<<<<<< FIN VENTANAS DE MENSAJES DEL SISTEMA
	

	/**
	 * Obtiene el permiso de un usuario sobre un recurso
	 * @param pIdRecurso serialVersionUID del recurso (clase persistente)
	 */
	public Permiso getPermiso(long pIdRecurso) throws Exception{
		try {
			Permiso permiso = null;
			if (this.getUsuario() != null) {
				if (this.usuario.getUser().equals(Constantes.SUPER_USUARIO)){
					permiso = this.getPermisoRoot();
				}
				else {
					permiso = this.getUsuario().getPermisos().get(pIdRecurso);
				}
			}
			return permiso;
		}
		catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	}
	
	private Permiso getPermisoRoot(){
		Permiso locPermiso = new Permiso();
		locPermiso.setSelect(true);
		locPermiso.setUpdate(true);
		locPermiso.setDelete(true);
		locPermiso.setInsert(true);
		locPermiso.setAudith(true);
		return locPermiso;
	}
	
	/**
	 * Clone para AppManager: NO permitido.
	 */
	@Override
	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
	
	/**
	 * Metodo para usar si se desea salir del sistema.
	 * @return <code>true</code> para salir, <code>false</code> cancela la operacion.
	 */
	public boolean salir(JFrame frame) {
		int locResultado =JOptionPane.showConfirmDialog(frame, 
								   "¿Está seguro que desea Salir?",
								   "Salir", 
								   JOptionPane.OK_CANCEL_OPTION, 
								   JOptionPane.QUESTION_MESSAGE);
		
		int locAccion = JFrame.DO_NOTHING_ON_CLOSE; 
		if (locResultado == 0) {
			locAccion = JFrame.EXIT_ON_CLOSE;
		}
		frame.setDefaultCloseOperation(locAccion);
		return (locResultado==0)?true:false;
	}

	/**
	 * Habilita o deshabilita las acciones para ingresar al sistema.
	 * @param mainWindow Ventana principal del sistema
	 * @param accion habilitar (true) o deshabilitar (false)
	 */
	public void habilitarConexion(MainView mainWindow, boolean accion) {
		mainWindow.getBtnConectar().setEnabled(accion);
		mainWindow.getMenuSistema().getMiConectar().setEnabled(accion);
		mainWindow.getBtnDesconectar().setEnabled(!accion);
		mainWindow.getMenuSistema().getMiDesconectar().setEnabled(!accion);
	}
	
	public abstract Main getMainController();
	
}
